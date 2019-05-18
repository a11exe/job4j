# How to build jars from IntelliJ

**1. Creating artifact from IntelliJ:**

+ Go to project structure:

![FileMenu](https://github.com/a11exe/job4j/blob/master/build_jars/ItelliJProjectStruct.png)

+ Create a new artifact:

![NewArtifact](https://github.com/a11exe/job4j/blob/master/build_jars/ItelliJNewArtifact.png)

+ Select the main class, and be sure to change the manifest folder:

![Manifest](https://github.com/a11exe/job4j/blob/master/build_jars/IntelliJManifest.png)

You have to change manifest directory:
```java
<project folder>\src\main\java
``` 
replace "java" with "resources"
```java
<project folder>\src\main\resources
```
This is how it should look like:

![Manifest2](https://github.com/a11exe/job4j/blob/master/build_jars/IntelljManifest2.png)

Then you choose the dependencies what you want to be packed IN your jar, or NEAR your jar file

To build your artifact go to build artifacts and choose "rebuild". It will create an "out" folder with your jar file and its dependencies.

![Build](https://github.com/a11exe/job4j/blob/master/build_jars/IntelljBuildArtifact.png)

**2. Using maven-assembly-plugin**

+ Add build section to the pom file

```java
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <finalName>ServiceCreate</finalName>
                    <appendAssemblyId>false</appendAssemblyId>
                    <archive>
                        <manifest>
                            <mainClass>com.svt.optimoo.App</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

+ Create a new run/debug configuration:

![NewConf](https://github.com/a11exe/job4j/blob/master/build_jars/IntelljiEditConf.png)

+ Choose application:

![ChooseApp](https://github.com/a11exe/job4j/blob/master/build_jars/IntelljiApp.png)

+ Fill in the form
+ Add the "assembly:single" maven goal after build to be executed last

![Assem](https://github.com/a11exe/job4j/blob/master/build_jars/IntellijAssemb.png)

![Assem2](https://github.com/a11exe/job4j/blob/master/build_jars/IntelljiAssemb2.png)

+ Save it, then run

![Run](https://github.com/a11exe/job4j/blob/master/build_jars/IntelljiRun.png)

+ This procedure will create the jar file under the "target" folder

![Target](https://github.com/a11exe/job4j/blob/master/build_jars/IntelljTarger.png)



