#########################
# ---- build stage ---- #
#########################
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

# ---- cache dependencies first ----
COPY pom.xml .
RUN mvn -B dependency:go-offline

# ---- then copy sources & build ----
COPY src ./src
RUN mvn -B package -DskipTests

###########################
# ---- runtime stage ---- #
###########################
FROM tomcat:9-jdk17

# optional: strip sample webapps to speed start-up
RUN rm -rf /usr/local/tomcat/webapps/{docs,examples,host-manager,manager}

# remove everything else and deploy our WAR as ROOT
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# expose 8080 internally; Render maps it to $PORT
CMD ["catalina.sh", "run"]
