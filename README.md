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