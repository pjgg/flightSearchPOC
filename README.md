# vertx jersey example

This is a simple example about Vertx Jersey. When you try to develop a Rest API basically you care about the followings points: 

- Request filters : sometime you require to handler the request in order to do some validations, before hits the endpoint. Example security reasons
- Response filters : sometime you require hadler the server response in order to add some headers as 'Access-Control-Allow-Origin' to allow cross domaine queries. 
- Exception Mappers: sometimes you require to hanler all the exception that your server throws in order to give a custom error message to your users. 
- Logs : sometimes you require to log your transactions, or some debug messages. 
- Endpoint definition: you need to define the path and endpoint methods, as well as make some parameter validations. 
 
You will find an example of all of this point in this project. In the resource folder you will find a verticle.cfg (verticle configuration) where the server port, jackson features and custom exception mappers are defined. You can launch this server from your eclipse IDE following this instructions:

- Open 'Run Configuration windows'
- Goto 'java application' and create a new launch configuration
- In 'main' tab projectName select your project and in main class write down 'io.vertx.core.Starter'
- In arguments write down 'run java-hk2:org.pablo.configuration.VerticleConf -conf src/main/resources/verticle.cfg'
- In arguments  write down '-Dlogback.configurationFile=src/main/resources/logback.xml -Dorg.vertx.logger-delegate-factory-class-name=org.vertx.java.core.logging.impl.SLF4JLogDelegateFactory'
- then click apply and run!. 

Other way is generate a fatJar, to do it just ```run mvn clean install``` and then go to tarjet folder and run 
```
java -jar -Dlogback.configurationFile=src/main/resources/logback.xml -Dorg.vertx.logger-delegate-factory-class-name=org.vertx.java.core.logging.impl.SLF4JLogDelegateFactory jerseyExample-1.0.0-SNAPSHOT-fat.jar -conf ../src/main/resources/config.json
```

And as a result of this command you should see something like 'INFO: Http server listening for http://localhost:3849' 

Then just make a GET query as: curl http://localhost:3849/resource/currentTime -H "content-type:application/json"  

