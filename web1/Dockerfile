FROM bellsoft/liberica-openjdk-alpine:21 AS builder
WORKDIR /application
COPY . .
RUN --mount=type=cache,target=/root/.gradle chmod +x gradlew && ./gradlew clean build -x test -x check

FROM tomcat:8.0
COPY --from=builder /application/build/libs/*.war /usr/local/tomcat/webapps/app.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
