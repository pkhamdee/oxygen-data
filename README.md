get user, GET
http://localhost:8080/users

create user, POST
http://localhost:8080/user
{
	"firstName": "pongsak",
	"lastName": "khamdee"
}

update user, PUT
http://localhost:8080/user/1
{
	"firstName": "pongsak",
	"lastName": "khamdee",
	"userName": "pkhamdee123"
}

get user by user name, GET
http://localhost:8080/user/username/pkhamdee


(new)
get user by first name, GET
http://localhost:8080/user/firstname/pongsak


(new)
get user by type.
http://localhost:8080/user/type/0
http://localhost:8080/user/type/0/total


get device, GET
http://localhost:8080/devices


create device only, POST
http://localhost:8080/device
{
            "name": "test",
        }


create device + user, POST
http://localhost:8080/devices
{
            "name": "test123",
            "user" : {
               "firstName": "pongsak"
            }
        }


update device only (user does not update), PUT       
http://localhost:8080/device/2
{
            "name": "test id 2",
            "user": {
                "firstName": "pongsak khamdee"
            }   
        }


update device with user , PUT     
http://localhost:8080/device/2
{
            "name": "test id 2",
            "user": {
                "id": 2
            }   
        }        


update device will do not update user,  have to explicit call update user api.     
http://localhost:8080/device/2
{
            "name": "test id 2",
            "user": {
                "firstName": "pongsak khamdee"
            }   
        }

get device by status, GET
http://localhost:8080/device/status/0

get device by total device by status, GET 
http://localhost:8080/device/status/0/total


constant
device
status; //1=unknown, 2=inused, 3=returned, 4=available

user 
gender; //1=male, 2=female
type; //1=admin, 2=resuer, 3=patient 
status; //1=unknown, 2=admit, 3=selfisolation, 4=recovered, 5=dead
severity; //1=unknown, 2=low, 3=moderate, 4=severe


To test
docker run --rm \
 -p 5432:5432 \
 -e POSTGRES_DB=oxygendata \
 -e POSTGRES_USER=postgres \
 -e POSTGRES_PASSWORD=postgres \
 bitnami/postgresql:11.11.0-debian-10-r59


run app from repository
docker run --rm \
 -p 8080:8080 \
 -e spring.datasource.url=jdbc:postgresql://192.168.0.100:5432/oxygendata \
 pkhamdee/oxygen-data
