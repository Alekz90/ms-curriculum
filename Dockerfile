
# Etapa 1: Construcción con Gradle y JDK 21
FROM gradle:8.7-jdk21 AS build
WORKDIR /app

# Copiamos los archivos de configuración de Gradle
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Descargamos dependencias (esto ayuda al cacheado en Docker)
RUN gradle build -x test --no-daemon || return 0

# Copiamos el código fuente y construimos el jar
COPY src ./src
COPY repo ./repo
RUN gradle clean bootJar --no-daemon

# Etapa 2: Imagen final con Eclipse Temurin JDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiamos el jar generado desde la etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Exponemos el puerto de la aplicación (ajústalo si tu app usa otro)
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java","-jar","app.jar"]

# Construir la imagen
# docker build -t "ms-resume-img:1.0.0" . --no-cache

# Ejecutar el contenedor
# docker run --name "ms-resume-container" -p 8080:8080 "ms-resume-img:1.0.0"

# Delete the container
# docker container rm -f "ms-resume-container"

# Delete the image
# docker image rm "ms-resume-img:1.0.0"