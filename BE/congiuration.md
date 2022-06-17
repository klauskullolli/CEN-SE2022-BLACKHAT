#  Step 1 
Install Intelij Community edition or premium (as wished)

# Step 2
Open project into intelij 

# Step 3
Create Database on your local database ( we named boo)

# Step 4
At application.property of spring project configure:\n
spring.datasource.url = jdbc:mysql://localhost:3306/boo (this is example) put your local batabase address <br />
spring.datasource.username = "your useranme" <br />
spring.datasource.password =  "your password" <br />

# Step 5
Use maven command --> maven  clean install 

# Step 6
Build projcet 

# Step 7
Run project by default is in localhost port 8080 

# Step 8
To test rest api import provided json file into postman

