# Comparison of JVM language features
This branch is dedicated to compare similarties between a JVM languages, their constructions and features. 



## 1. Comparision of selection control mechanism 3 JVM languages
The comparison of selection control mechanism used for change the flow of the program. The comparison is done accross a various JVM languages.

### Java - switch statement
The switch statement us used according to the [JEP-420: Pattern Matching](https://openjdk.java.net/jeps/420)

Compile Java Program:
```bash
$ javac --release 18 --enable-preview -g -classpath out -sourcepath java -d out ./java/*java
```
Execute Java Program:
```bash
$ java --enable-preview -cp out VehicleFactory
```

### Kotlin
Kotlin language uses `when` statement with ability to branking the program flow. There is similarity with other JVM languages. 

Compile Kotlin Program: 
```bash
$ kotlinc ./kotlin/*.kt -include-runtime -d ./out/vehicle_factory.jar
```

Execute Kotlin Program:
```bash
$ kotlin -classpath ./out/vehicle_factory.jar KVehicleFactoryKt
```

#### Scala 
Scala introduces a `match` pattern for controling the program flow branching. There are similarities to the Java `swicht` statement

Compile Scala Program:
```bash 
$ scalac -d out ./scala/*.scala 
```

Execute Scala Program
```bash
$ scala -cp out SVehicleFactory
```

