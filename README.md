This project is cloned and modified from related projects of [Simon Bliudze](https://github.com/sbliudze "Named link title") to run a simple JavaBIP example.

The example is _SpriralsDemo.java_ in the _java-itest_ module.
In order to do that, we need:
* The _javabdd-1.0b2.jar_
* Build ___javabip-core___: navigate to the _javabip-core_ folder, open terminal and type: 
`mvn clean install`
* Build ___javabip-engine___: navigate to each subpackage of the _javabip-engine_ module, copy the _javabdd-1.0b2.jar_ into them and modify javabdd dependency in the .pom files as follow:
	
 	 ```
	 <dependency>
		<groupId>javabdd</groupId>
		<artifactId>javabdd</artifactId>
		<version>1.0b2</version>
		<scope>system</scope>
		<systemPath>${basedir}/javabdd-1.0b2.jar</systemPath>
	 </dependency>
	 ```
	Then, build project by typing mvn clean package
* Build ___javabip-itest___: navigate to the _javabip-itest_ folder do the same things as above.
* Put a large file into _inputfolder1_, run _SpriralsDemo.java_ to watch how it works.