
# ImageBank Application

## A Demonstration Project

The ImageBank Application in this repository is a demonstration piece showing the interaction between React and Spring Boot.  Infrastructure issues such as handling logins securely, using TLS in both production and development is maintained through out.  The impetus behind this project is learning what works, what doesn't and expanding upon this knowledge to leverage every piece of React, Spring Boot and Java.

## Versions

|Tool                | Version |
|--------------------|---------|
|Java                | 21LTS   |
|React               | 18.2.0  |
|Spring Boot         | 3.3.2   |
|Spring Security     | 6.1.1   |
|Postgresql          | 15      |
|Liquibase community | 4.29.0  |
|apache maven        | 3.9.8   |
|npm                 | 9.8.1   |
|node.js             | 20.14.0 |

I will be updating these versions from time to time.

## Project Goals

I started this project in the summer of 2024, and will work on it as time permits.  The goal is to get a working version of ImageBank to run in the AWS Cloud, eventually moving towards a microservice architecture.  Once complete, the intention is to document via a series of articles and videos on how this work was accomplished, identify the challenges and what was needed to overcome them.

The project will become a teaching tool for other software developers.  I also intend to learn from those who critique my work and suggest better approaches.

## Liquibase 

Liquibase changesets have been added to establish a process for updating the database.  The initial database may be found in src/main/resources.  Run maven as follows
   
    mvn liquibase:update
   
Run maven to get a listing of the dml created and run

    mvn liquibase:updateSQL
   
## Runing in Developer mode

To get the front end running in developer mode, 

    npm run start
   
The command serves up the front end on port 3000. 

