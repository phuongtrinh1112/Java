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
    FROM <base>
    
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
