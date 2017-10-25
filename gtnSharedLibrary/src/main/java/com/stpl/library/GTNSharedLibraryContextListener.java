/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.library;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.jboss.logging.Logger;

/**
 *
 * @author Abhiram.Giri
 */
public class GTNSharedLibraryContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(GTNSharedLibraryContextListener.class);
    private static Properties prop = null;
    
    public static Properties getProperties() {
        if (prop != null) {
            return prop;
        } else {
            prop = new Properties();

            try {
                InputStream is = GTNSharedLibraryContextListener.class.getResourceAsStream("/shared-library.properties");
                prop.load(is);
            } catch (IOException ex) {
                LOGGER.info(null, ex);
            }
        }
        return prop;
    }
    // Constructor.
    public GTNSharedLibraryContextListener() {
        super();
    }

    private void prepareLibrary(ServletContextEvent servletContextEvent,String sharedLibName,String path,String nonSharedJars){
        LOGGER.info("shared.library.name="+sharedLibName);
        LOGGER.info("non.shared.jars="+nonSharedJars);
        String libDepandancys = getProperties().getProperty(sharedLibName+".depandancy");
        String libOptionalDepandancys = getProperties().getProperty(sharedLibName+".optional.depandancy");
        String[] sharedLibs = sharedLibName.split("\\.");
        
        String sharedLibPath = "";
        String sharedLibBeforePath = "";
        for (String sharedLib : sharedLibs) {
            sharedLibPath += sharedLib + "/";
            sharedLibBeforePath += "../";
        }        
        LOGGER.info("LoadLibrary Web app context initialized." + sharedLibPath);  // INFO logging.

        ServletContext ct = servletContextEvent.getServletContext();
        String currentPath=ct.getRealPath("/");
        currentPath=currentPath.replace("\\", "/");
        LOGGER.info("currentPath="+currentPath);
        int index=currentPath.lastIndexOf("/standalone/");
        
        String moduledirpath = currentPath.substring(0, index)+"/modules/" + sharedLibPath + "main";
        LOGGER.info("moduledirpath="+moduledirpath);
        String modulepath = moduledirpath+"/module.xml";
        final File modulefile = new File(modulepath);
        String content = "<?xml version=\"1.0\"?>\n"
                + "\n"
                + "<module xmlns=\"urn:jboss:module:1.0\" name=\""
                + sharedLibName
                + "\">\n"
                + "	<resources>\n";  
        String moduleEntryPath=currentPath.substring(index + 1);
        
        String libpath = currentPath+"/"+path+"/";
        final File libfile = new File(libpath);
        
        if (libfile.isDirectory()) {
            String[] fileList = libfile.list();
            
            for (String filename : fileList) {                
                if (filename.endsWith("jar") && !nonSharedJars.contains(filename)){
                    LOGGER.info(filename);
                    GTNSharedLibraryUI.container.addBean(new GTNSharedLibraryInfoDTO(sharedLibName, filename, libpath));
                    content += "		<resource-root path=\"../../" + sharedLibBeforePath +""+ moduleEntryPath+path+"/" + filename + "\" />\n";
                }
            }
        }
        content += "	</resources>	 \n";
        String start="<dependencies>\n";
        String end="";
        if(libDepandancys!=null&&!libDepandancys.isEmpty()){
            content += start;
            String[] libDepandancy = libDepandancys.split(",");
            for (String depandancy : libDepandancy) {
                content += "        <module name=\""+depandancy+"\"/>\n";
            }            
            start="";
            end="    </dependencies>\n";
        }
        if(libOptionalDepandancys!=null&&!libOptionalDepandancys.isEmpty()){
            content += start;
            content +="        <!-- Optional deps -->\n";
            String[] libOptDepandancy = libOptionalDepandancys.split(",");
            for (String depandancy : libOptDepandancy) {
                content += "        <module name=\""+depandancy+"\" optional=\"true\"/>\n";
            }
            end="    </dependencies>\n";
        }
        content += end;
        content += "</module>";
        try{
        if (!modulefile.exists()) {
            // if file doesn't exists, then create it
                new File(moduledirpath).mkdirs();
                modulefile.createNewFile();
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        try (FileOutputStream fop = new FileOutputStream(modulefile)) {            

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
    // Our web app (Vaadin app) is starting up.
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String sharedLibNames = getProperties().getProperty("shared.library.names");
        String sharedLibPaths=getProperties().getProperty("shared.library.paths");
        String nonSharedJars = getProperties().getProperty("non.shared.jars");
        String[] sharedLibNameArr = sharedLibNames.split(",");
        String[] sharedLibPathArr = sharedLibPaths.split(",");
        GTNSharedLibraryUI.container.removeAllItems();
        for (int i=0;i<sharedLibNameArr.length;i++) {
            prepareLibrary(servletContextEvent, sharedLibNameArr[i],sharedLibPathArr[i],nonSharedJars);
        } 
    }    

    // Our web app (Vaadin app) is shutting down.
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("LoadLibrary Web app context destroyed.");  // INFO logging.

    } 
}
