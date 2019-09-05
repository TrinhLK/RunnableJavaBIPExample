This project is to run a simple example of JavaBIP, which is SpriralsDemo.java in the java-itest module.
In order to do that, we need:
* The javabdd-1.0b2.jar
* Build javabip-core: navigate to the javabip-core folder, open terminal and type: mvn clean install
* Build javabip-engine: navigate to each subpackage of the javabip-engine module, copy the javabdd-1.0b2.jar
into them and modify javabdd dependency in the .pom files as follow:
 	> <dependency>
	>	<groupId>javabdd</groupId>
	>	<artifactId>javabdd</artifactId>
	>	<version>1.0b2</version>
	>	<scope>system</scope>
	>	<systemPath>${basedir}/javabdd-1.0b2.jar</systemPath>
	> </dependency>
	Then, build project by typing mvn clean package
* Build javabip-itest: navigate to the javabip-itest folder do the same things as above.
