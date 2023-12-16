FROM mjalas/javafx
COPY src JavaDocker
WORKDIR JavaDocker
RUN mkdir -p bin
RUN javac -d bin ./application/Main.java
WORKDIR bin
CMD ["java", "application.Main"]