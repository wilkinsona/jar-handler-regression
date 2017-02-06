# Custom jar handler regression

This repository provides a simple application that illustrates a regression with Java 9
when attempting to use a custom `URLStreamHandler` for `jar` URLs.

## Steps to reproduce

Build the project:

```
$ mvn compile
```

Run with Java 8:

```
$ /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/bin/java -cp target/classes com.example.Main
URLs handled by custom handler: 1
```

Run with Java 9:

```
$ /Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home/bin/java -cp target/classes com.example.Main
URLs handled by custom handler: 0
```

Note that the custom handler was not used with Java 9.

## Analysis

I believe the difference in behaviour is a result of [this change][1] in OpenJDK which
results in a `JarLoader` being used rather than a `Loader`. The former contains an
optimisation for `file:` URLs which means that `URL.openConnection()` isn't called and,
therefore, that the custom `URLStreamHandler` is not used.

[1]: http://hg.openjdk.java.net/jdk9/jdk9/jdk/diff/c49b0409a802/src/java.base/share/classes/jdk/internal/loader/URLClassPath.java