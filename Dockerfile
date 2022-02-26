# Pull base image.
FROM ubuntu:latest

RUN \
# Update
apt-get update -y && \
# Install Java
apt-get install default-jre -y

ADD ./target/todo-list-project-0.1.jar todo-list.jar

CMD java -jar todo-list.jar
