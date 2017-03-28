### Number Guessing game

This program uses a mysql database and will initialise itself as long as there is a local mysql instance running.
It can be built using maven with the command
```shell
mvn clean install
```
The resulting .jar can be found in the created target directory. Run it using
```shell
java -jar target/NumberGuess-0.0.1-SNAPSHOT.jar
```

To start guessing, open guess.html found at
```shell
src/main/resources/guess.html
```