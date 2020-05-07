FROM adoptopenjdk/openjdk13:alpine

# Create app directory
RUN mkdir -p /usr/opt/service

# Copy app
COPY target/*.jar /usr/opt/service/service.jar

EXPOSE 8082

ENTRYPOINT exec java -jar /usr/opt/service/service.jar