package com.example.demo.demo.Repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

//@Repository
@Component
public class DataRepository {

    private HashMap<String,String> myData ;
    private String datafile = "target/myData.txt";

    public DataRepository() {
        // read all the data from the file and store in the hashmap
        //for now hard coding the file name
        myData = new HashMap<String,String>();
        try {
            File fh = new File(datafile);
            if(!fh.exists()){
                //create an empty file. since this is a new file,
                //no records will be added in the hashmap
                fh.createNewFile();
            }
            Scanner fileReader = new Scanner(fh);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                System.out.println(data);
                String[] KVpair = data.split(",");
                String key = null,value = null;
                if(KVpair.length >= 2) {
                    key = (KVpair[0] != null) ? KVpair[0] : null;
                    value = (KVpair[1] != null) ? KVpair[1] : null;
                } else if(KVpair.length == 1) {
                    key = KVpair[0];
                }


                System.out.println("Key :" + key + "value : " + value);

                //check if the value is empty. that means its a deleted record
                //if an entry already exists in hashmap remove that also
                if (value == null) {
                    if (myData.containsKey(key)) {
                        myData.remove(key);
                    }
                } else {
                    myData.put(key, value);
                }
            }

            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String GetValue(String key) {
        return myData.get(key);
    }

    public boolean AddOrUpdateEntry(String key, String value) {
        String data = key + "," + value;
        if(!AddFileEntry(data)) {
            //if a corresponding entry cannot be added in the file store
            //lets send out a failure message and lets not add or update in the hashmap
            return false;
        }

        //create or  update the entry
        myData.put(key,value);

        return true;
    }

    public boolean DeleteEntry(String key) {
        //first check if an entry is there for this key
        if(!myData.containsKey(key)){
            //no entry for this key present
            return false;
        }
        //create a file entry with empty value
        String data = key + ",";
        if(!AddFileEntry(data)) {
            //if a corresponding entry cannot be added in the file store
            //lets send out a failure message and lets not delete from the
            //hashmap
            return false;
        }
        String val = myData.remove(key);
        return true;
    }

    private boolean AddFileEntry(String data) {
        //create a file entry
        System.out.println("Trying to write an entry into the file : " + data);
        try {
            File fh = new File(datafile);
            if(!fh.exists()) {
                fh.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(datafile,true);
            fileWritter.write(data);
            fileWritter.write("\n");
            fileWritter.flush();
            fileWritter.close();
        } catch(Exception e) {
            e.printStackTrace();
            return false; //some problem in updating the record
        }
        return true;
    }
}
