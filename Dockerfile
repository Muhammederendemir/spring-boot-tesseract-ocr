#### Stage 1: Build the application
FROM adoptopenjdk/openjdk11:alpine as build

RUN apk update

# Install tesseract library
RUN apk add --no-cache tesseract-ocr

# Download last language package
RUN mkdir -p /tessdata
ADD https://github.com/tesseract-ocr/tessdata/raw/master/tur.traineddata /tessdata/tur.traineddata

# Check the installation status
RUN tesseract --list-langs
RUN tesseract -v

# Set the name of the jar
ENV APP_FILE *.jar


# Copy our JAR
COPY target/$APP_FILE /app.jar

# Launch the Spring Boot application
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
