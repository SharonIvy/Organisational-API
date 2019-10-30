# ORGANISATIONAL API
  ### By:
   #### Sharon Ivy Kendi M.

## DESCRIPTION:
  This is a rest REST API for querying and retrieving scoped news and information
  ### Built With:
    * Language - [Java](https://www.java.com/)
    * Intergated Development Environment - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
    * Framework - [Apache Spark](https://spark.apache.org/)


## PROJECT PRE-REQUISITES
  You need the following installed on your machine:
  - Java
  - Java Development Kit(JDK)
  - Maven
  - Gradle
  - An IDE - IntelliJ
  To confirm, run the following commands on your Linux command line:
  ```
  $ java -version
  $ mvn --version
  $ gradle --version
  ```
  
## SETUP PROCESS
 ### Requirements:
  * See pre-requisites
  * Fork this repository
  * Run the command;
  'git clone https://github.com/SharonIvy/Organisational-API.git' to clone this repository locally
 ### Installation process:
  * After cloning onto your local machine navigate to the folder you cloned into and open it with IntelliJ
  * Navigate into the ``` src/main/java/App.java ``` and run in IntelliJ IDEA
  * On your browser and type ``` localhost:4567 ```
  (After this, you will probably get a 500 error since we do not have a database yet.)
  *
 
## RUNNING TESTS 
 Create a test class for running tests in the application
 Below is a sample test:
 ```
 @Test
    public void getAnimal_age() {
        Animal newAnimal = Animal.setUpNewAnimal();
        assertEquals("young",newAnimal.getAnimal_age());
    }
 ```

## KNOWN BUGS
 No bugs were detected during the development of this project
 #### :+1:
  
## LICENSING
  **MIT License**
  (for further details, please see the LICENSE file)
  
## Support and Contact Details:
  For any contributions or troubleshooting, send an e-mail to;
   #### kendishivy72@gmail.com