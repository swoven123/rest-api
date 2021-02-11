# Read Me First
Below are the steps to build and deploy the application

# Build
1. This program needs Maven to run, make sure you have mvn on your system by running mvn --version command. 
2. Run mvn clean install  

# Deploy and test
 
### Without Docker
Execute the below command and api will be accessible in port 8080
1) mvn spring-boot:run

### With Docker
Execute the below command and api will be accessible in port 8080
1) Build an image = docker build -t anycontainername .
2) Run the image = docker run -p 8080:8080 anycontainername

