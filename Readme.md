# Getting Started

First of all, this documentation is YOUR documentation :

- Anyone can create/modify/delete anything if you think that it is not the right place or if it is outdated
- Don't worry about loosing things, this documentation is gitted, we will always find things back.
- We use English for the sake of the technic
- 1 Sentence per line (for easiest merge)

# Tools

The tools that you'll need for developping on this project are :

- **Git** :  https://git-scm.com/
- **Java 21**
- **Maven** : https://maven.apache.org/
- **Docker** : https://www.docker.com/
- Your favorite IDE. If you don't have any idea :
    * **Intellij** : https://www.jetbrains.com/idea/ (please buy a valid licence, or use community version, or use EAP version)
    * **Eclipse** : https://www.eclipse.org/downloads/eclipse-packages/ (latest version is recommended to support JDK 21)
    * You also need to install **Lombok** on your IDE (by double-clicking on Lombok Maven JAR dependency).

# Starting dev environment

To start your dev environment, you need to follow these steps:

* **Docker containers** :
    * gateway needs these containers to be running to function properly:
        - mysql

once you have cloned the gateway project, go to the **root folder** and run the command **docker-compose up -d --build**
* **Mysql**
    *  once your mysql database container is running, you can access either from included cloudBeaver client [http://localhost:3306](http://localhost:3306) and setup connection with the mysql container, or a client of your choice.

# Starting API server
wait for all containers to start then start the API server by running in the root folder:

    mvn clean install:  (to clean existing build folder and install a new version of gateway server)
    mvn spring-boot:run :  (to run the new installed version)

# Git

#### Format of the commit message:
```bash
<type>(<scope>): <subject>
```

#### Example commit message:

```bash
fix(header): fixing responsive behaviour
```

##### Allowed `<type>` values:

* **feat** (new feature for the user, not a new feature for build script)
* **fix** (bug fix for the user, not a fix to a build script)
* **docs** (changes to the documentation)
* **style** (formatting, missing semi colons, etc; no production code change)
* **refactor** (refactoring production code, eg. renaming a variable)
* **test** (adding missing tests, refactoring tests; no production code change)
* **chore** (updating grunt tasks etc; no production code change)

# Resetting Dev Env
----------------------
```bash
docker rm -f $(docker ps -qa)
docker volume prune
docker network prune
docker-compose up -d --build
```