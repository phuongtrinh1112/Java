FROM <base>

ARG JAR_FILE=target/rest-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENV JAVA_OPTS="-Xmx512m -Xms128m"
ENV JVM_OPTS=""
RUN echo "java $JMX_OPTS $JAVA_OPTS $JVM_OPTS -jar /app.jar"
CMD java $JMX_OPTS $JVM_OPTS $JAVA_OPTS -jar /app.jar
