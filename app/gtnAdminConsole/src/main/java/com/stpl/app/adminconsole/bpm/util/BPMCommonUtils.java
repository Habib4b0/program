package com.stpl.app.adminconsole.bpm.util;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import java.util.Properties;

import com.stpl.app.bpm.utils.Constants;
import com.stpl.app.bpm.utils.DroolsProperties;
import org.jboss.logging.Logger;

/**
 *
 * @author arulmurugan
 */

public class BPMCommonUtils {

	public static String groupId = "com.stpl.app";
	public static String artifactId = "HierarchyDefinition";
	public static String version = "1.0.6";

	public static String HR_GROUP = ConstantsUtils.PATH_COM_BPM;
	public static String HR_ARTIFACT_ID = "HierarchyDefinitionRules";
	public static String HR_VERSION = "1.0";

	public static String NM_GROUP = ConstantsUtils.PATH_COM_BPM;
	public static String NM_ARTIFACT_ID = "ProcessFlow";
	public static String NM_VERSION = "1.0";

	public static String REPLACE_STRING = "HDStPl123rUlE";
	public static String HIEARCHYRULES = "maven2/"+HR_GROUP.replace(".", "/")+"/"+HR_ARTIFACT_ID+"/1.0/"+HR_ARTIFACT_ID+"-1.0.jar";
	
	public static String HD_PROJ_NAME="mani";
        
        private static final Logger LOGGER = Logger.getLogger(BPMCommonUtils.class);
	
	static{
		try{
		Properties properties = DroolsProperties.getPropertiesData();
		groupId = properties.getProperty(Constants.HD_groupId, "com.stpl.app");
		artifactId = properties.getProperty(Constants.HD_artifactId, "HierarchyDefinition");
		version = properties.getProperty(Constants.HD__version, "1.0.6");
		
		HR_GROUP = properties.getProperty(Constants.HD_Rules_groupId, ConstantsUtils.PATH_COM_BPM);
		HR_ARTIFACT_ID = properties.getProperty(Constants.HD_Rules_artifactId, "HierarchyDefinitionRules");
		HR_VERSION = properties.getProperty(Constants.HD_Rules_version, "1.0");
		
		}catch(Exception e){
                    LOGGER.error(e);
		}
	}
}


