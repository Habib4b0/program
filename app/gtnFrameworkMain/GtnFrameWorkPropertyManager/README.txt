1. include Property Manager Dependency in GtnFrameWorkCommon pom.xml 
2. reaplace GtnWsLoadPropertiesService class 
2. install Property Manager package before FrameworkCommon
3. include system property in standalone.xml and place the properties file the specified path
  --  <property name="com.stpl.gtnframework.base.path" value="<your_system_path_for_jboss>/jboss-7.1.1/gtnframework_base_path"/>

