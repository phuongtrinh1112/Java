Sample RESTful API/Web Services
===

## 1. Build docker image
- Add maven dependencies:
    ```xml
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>${maven-jar-plugin.version}</version>
        <configuration>
            <archive>
                <manifest>
                    <addClasspath>true</addClasspath>
                    <classpathPrefix>libs/</classpathPrefix>
                    <useUniqueVersions>false</useUniqueVersions>
                </manifest>
                <manifestEntries>
                    <Build-Id>${project.artifactId}-${project.version}</Build-Id>
                </manifestEntries>
            </archive>
        </configuration>
    </plugin>
    ```
  ```xml  
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>${maven-dependency-plugin.version}</version>
        <executions>
            <execution>
                <id>copylib</id>
                <!--<phase></phase>-->
                <goals>
                    <goal>copy-dependencies</goal>
                </goals>
                <configuration>
                    <outputDirectory>dist/libs</outputDirectory>
                    <excludeScope>provided</excludeScope>
                </configuration>
            </execution>
            <execution>
                <id>copydist</id>
                <!--<phase></phase>-->
                <goals>
                    <goal>copy</goal>
                </goals>
                <configuration>
                    <artifactItems>
                        <artifactItem>
                            <groupId>${project.groupId}</groupId>
                            <artifactId>${project.artifactId}</artifactId>
                            <version>${project.version}</version>
                            <overWrite>true</overWrite>
                            <outputDirectory>dist</outputDirectory>
                        </artifactItem>
                    </artifactItems>
                </configuration>
            </execution>
        </executions>
    </plugin>
    ```
- Create Dockerfile
    ```dockerfile
    FROM docker.fortna.com/wes/app-jdk8-dev:10
    
    ARG JAR_FILE=target/rest-service-0.0.1-SNAPSHOT.jar
    ADD ${JAR_FILE} app.jar
    
    ENV JAVA_OPTS="-Xmx512m -Xms128m"
    ENV JVM_OPTS=""
    RUN echo "java $JMX_OPTS $JAVA_OPTS $JVM_OPTS -jar /app.jar"
    CMD java $JMX_OPTS $JVM_OPTS $JAVA_OPTS -jar /app.jar
    ```
- Build docker image
```shell
docker build --no-cache -t <image_name>:<version> -f ./path/to/Dockerfile .
```
- Push docker image into docker registry server
```shell
#login
docker login docker.fortna.com
#account: admin/fortnaRH11

#Tag the docker image with the registry name:
docker build --no-cache -t docker.fortna.com/<image_name>:<version> -f ./path/to/Dockerfile .

#Push the image
docker push docker.fortna.com/<image_name>:<version>
```

## 2. How to run
### 2.1. Pre-requisite:
Deploy & setup Postgres DB: https://fortna.atlassian.net/wiki/spaces/WQC/pages/804192291/Deploy+PostgresQL+singleton+mode

### 2.2 Deploy & run locally
```shell
 docker run -it --rm --name=<container_name> <image_name>:<version>
```

### 2.3 Deploy & run via docker-compose
- Create docker compose file:
```dockerfile
version: '3.4'

services:
  restservicesample:
    image: restservice-sample-local:0.0.3.4
    ports:
      - "9999:9999"
    networks:
      fortnawes:
        aliases:
          - restservicesample.fortnawes.com
    deploy:
      mode: replicated
      replicas: 1

networks:
  fortnawes:
    external: true
```
- Notes:
  + Both container should be in same network, so that they can connect together via the network alias name.
