FROM openjdk:19-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew -x test build || return 0

ARG GC_OPTS=" \
    -XX:+UseConcMarkSweepGC \
    -XX:+PrintGCDetails \
    -XX:+PrintGCTimeStamps \
    -XX:-TraceClassUnloading \
"

ARG JAVA_OPTS=" \
    -server \
    -Xms1024m -Xmx1024m \
    -Djava.net.preferIPv4Stack=true \
    -Dfile.encoding=utf-8 \
    -Dspring.profiles.active=prod \
"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./study1-0.0.1-SNAPSHOT.jar", "${GC_OPTS}", "${JAVA_OPTS}"]