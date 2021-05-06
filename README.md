# KeyValueStoreService

A Key-value Store is a service that enables a user to store a value linked to a key and it should also
provide ways to retrieve that value using the key and also delete the value.

A Persistent store is something that retains its state across restarts.

So, here you will have to create a service that exposes REST Api’s to create, read, update and delete a key-value pair. 
This service should be fast(Any key-value store should be fast.) and should retain back the state when restarted.

Key can be any STRING and value can be arbitrary JSON.

File storage is used for persisting

Rest API definitions

Create - HTTP POST method
localhost:8080/create/{key}/{value}

Update - HTTP POST method
localhost:8080/update/{key}/{value}

Get - HTTP GET method
localhost:8080/get/{key}

Delete - HTTP POST method
localhost:8080/delete/{key}

Spring boot framework is used to build this project.

