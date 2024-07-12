Parent project (businessplatfrm) creation

1. File->New->Maven Project
2. Select archetype as spring-mvc
3. ArtifactId = businessplatfrm


Module Creation

1. Right click on the parent project (businessplatfrm) -> New -> Maven Module
2. Select Parent as businessplatfrm, provide name for your module (dealer/seller)
3. Select archetype as spring-mvc and click on Finish and your module will be created and respective 
   conf in the parent pom will be added as below
   
  
            
  <modules>
    
    <module>seller</module>
            
    <module>util</module>
          
    <module>dealer</module>
      
  </modules>
  
  
 Use one module in other module
  
 1. To use util module in the seller module, open the pom.xml of seller module
 2. Add below dependency in the dependencies section of seller module
 
	    <dependency>
			<groupId>com.org.example</groupId>
			<artifactId>util</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>	
3. Right click on parent/project -> Team -> Maven -> Update Project
4. Clean install maven if required


Note: 

Util module is created using intellij IDE. right click on parent proj -> New -> Module
It won't create any web.xml nothing. You need to add it if you want to generate war file


Build & Run the Docker Image

1. In every pom, I have set the maven plugin to deploy/place the generated war file to tomcat webapps folder in my local
2. Docker file to deploy all war files in one tomcat in Docker is available in the seller module in the root folder.
3. Dockerfile must be in the root folder as it will be able to wrap or build everything in our project
4. Place all the war files inside the target folder of seller module
5. Build the docker file and create image
6. Run the docker image so that a container will be created along with the tomcat inside it which we specified in the Dockerfile
  