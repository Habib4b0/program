
# GTN Framework 2

### Maven Repository Configuration

##### Eclipse 
  - Import Settings.xml to Windows ->   Preferences -> Maven->User Seetings
  - Change Java Compiler to jdk 1.7.0_71. 
  - Remove any exisiting JRE/JDK version configured as Library
  - Right Click on a project - > Properties->Libraries->Remove existing jre/jdk
  - Right Click on a project - > Properties->Libraries->Add Library->JRE System Library->Alternate JRE->choose  JDK1.7.0_71 path
 
 ##### Netbeans
- Place your settings.xml in .m2/folder 

### Build Steps 
 


 - In Settings.xml change the path to project repository
-  ```sh
   <localRepository>\path to project\Repository</localRepository>
   ```
   
 - Install Resource projects and GtnWsJWT,GtnWsSecurity,GtnFrameworkCommon,GtnFramework,GtnFrameworkBpmEngine,GtnWsHibernateHbm,GtnQueryEngine
- ```sh
  mvn -PInstall-Dependency
   ```
 - Build  respective projects
 - ```sh 
    mvn clean compile package
    ```
### Release 
### War
- Build Release plugin in  `Release\WarReleasePlugin`
- Execute Following maven goal to build all project and get war files in `\Path to Project\Dist`
- ```sh
    mvn -PRelease-Build
	```

### Delivery to QA

- Log in to galderma-dev.sysbiz.org
- Goto /root/gtnFramework/build
- ./buildGtnRelease.sh username develop contract-management	
	
### Delivery to QA - Using jenkins

- Goto http://10.4.48.46:18001/jenkins
- Login using credentials builduser/builduser 
- For admin login using rajadurai.subramanian/jenkins@123
- Run the gtnFrameworkBuild job
- After build successfully completed, goto http://10.4.48.46:18001/jenkins/job/gtnFrameworkBuild/ws/Dist/
  folder in jenkins screen.
- Alternatively, war also will be found in the following path in galderma-dev.sysbiz.org 
- /opt/bpigtn_jenkins/jenkinshome/workspace/gtnFrameworkBuild/Dist  
- Deliver the wars to QA.  
	
### Guidelines
		
-  Refer  excel sheet in docs/GtnRefactoringGuidelines for coding standards for GtnFramework

### Resources

- Increase the version number if any changes done for custom components
- Add a release note for the change

### do's and don'ts

- Always format the code and remove unsed import and commit
- Make sure there are no eclipse warnings or compiler warnings.
- Do not commit in Repository

### Common GTN-Base-Path

- Add the following system property in standalone.xml
- property name="com.stpl.gtnframework.base.path" value="<your_system_path_for_jboss>\jboss-7.1.1\gtnframework_base_path\"
- Place all your property and all other required files inside base path.
- Copy the property file "GtnFramework.properties" from GtnFrameWorkPropertyManager project and place it inside the 
  com.stpl.gtnframework.base.path
- All log files will be found under com.stpl.gtnframework.base.path/gtnFramework-Logs/GTNFrameworkGeneral.log.
- Forecast files will be under com.stpl.gtnframework.base.path/gtnData/tmp.

### GTN Property Manager

-  GtnFramework.properties file can be found under GtnFrameWorkPropertyManager/
-  Place GtnFramework.properties file in  gtnframework_base_path (mentioned as System property in Standalone.xml)
-  For ETL place EtlConfiguration.properties in base_path.
-  Copy the interfaceUrlMapping.properties and place it inside /<base_path>/ETL-InterfaceUriConfig/ folder.(Create if not present)
-  The interfaceUrlMapping file contains the interface location path. Please add in this file whenever any new interface will come.

### GTN Security Filter  

-  gtnWsSecretKey.txt file can be found under GtnFrameWorkPropertyManager/
-  Place gtnWsSecretKey.txt file in  gtnframework_base_path (mentioned as System property in Standalone.xml)
-  To disable Security Filter set Property gtn.security.enabled=no in GtnFramework.properties file
