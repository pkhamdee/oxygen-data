1. Start PostgreSQL with Docker to check the operation of the application locally. Open a new terminal and run the following command.

docker run --rm \
 -p 5432:5432 \
 -e POSTGRES_DB=oxygendata \
 -e POSTGRES_USER=postgres \
 -e POSTGRES_PASSWORD=postgres \
 bitnami/postgresql:11.11.0-debian-10-r59

 2. build package
 ./gradlew clean build

 3. Launch the application
 ./gradlew bootRun

 4. Test app

get all record (GET) 
 curl -v http://localhost:8080/users | jq .

get user
curl -v http://localhost:8080/user/1 | jq .

create user record (POST)
 curl -d @user.json -H "Content-Type: application/json"  http://localhost:8080/user
or
curl -d "{\"firstName\":\"somechai\",\"lastName\":\"ratcha\"}" -H "Content-Type: application/json"  http://localhost:8080/user

update
curl -d "{\"firstName\":\"test\",\"lastName\":\"pass\"}" -H 'Content-Type: application/json' -X PUT http://localhost:8080/user/1

delete
curl -X DELETE http://localhost:8080/user/1

5. creat container
./gradlew jib -Djib.from.image=gcr.io/distroless/java -Djib.to.image=<docker-repository>/oxygen-data 


6. Device endpoint
get all record (GET) 
 curl -v http://localhost:8080/devices 

get device
curl -v http://localhost:8080/device/1 

create device record (POST)
 curl -d @device.json -H "Content-Type: application/json"  http://localhost:8080/device
or
curl -d "{\"name\":\"test\",\"type\":\"test\"}" -H "Content-Type: application/json"  http://localhost:8080/device

update
curl -d "{\"name\":\"test\",\"type\":\"pass\"}" -H 'Content-Type: application/json' -X PUT http://localhost:8080/device/1

delete
curl -X DELETE http://localhost:8080/device/1

7. UserDevice endpoint
get all record (GET) 
 curl -v http://localhost:8080/userdevices  | jq .

get device
curl -v http://localhost:8080/userdevice/1 

create userdevice record (POST)
 curl -d @device.json -H "Content-Type: application/json"  http://localhost:8080/userdevice
or
curl -d "{\"deviceId\":1,\"userId\":1}" -H "Content-Type: application/json"  http://localhost:8080/userdevice

update
curl -d "{\"deviceId\":1,\"userId\":2}" -H 'Content-Type: application/json' -X PUT http://localhost:8080/userdevice/1

delete
curl -X DELETE http://localhost:8080/userdevice/1


run app from repository
docker run --rm \
 -p 8080:8080 \
 -e spring.datasource.url=jdbc:postgresql://192.168.0.100:5432/oxygendata \
 pkhamdee/oxygen-data