/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author KarthikeyanS
 */
public class PropertiesReader {

    /**
     * Singleton Object of the PropertiesReader class Object is instantiated on
     * Demand
     */
    private static PropertiesReader reader = null;
    /**
     * Properties object for holding the properties
     */
    private final ExtProperties messages = new ExtProperties();
    /**
     * String Array Which holds the package Names to load from the jar
     */
    private static final String[] RESOURCE_PACKAGE_NAMES = new String[]{"messages"};
    /**
     * Extension of the files to load in confirmationMessages (Properties
     * object)
     */
    private static final String PROPERTIES_EXTENSION = ".properties";
    /**
     * Jar file name to load the properties
     */
    private static final String JAR_FILE_NAME = "gtnProperties";
    /**
     * folder name of the which Jar file presents in
     */
    private static final String FOLDER_NAME = "applicationProperties";
    /**
     * folder name of the which Jar file presents in
     */
    private static String path = "";
    /**
     * Extension of the java archive file
     */
    private static final String JAR_FILE_EXTENSION = ".jar";
    /**
     * META-INF/ packages should be removed from the jar while loading
     * properties
     */
    public static final String META_INF_NAME = "META-INF/";
    /**
     * File separator for Java archive file
     */
    public static final String JAR_SEPERATOR = "/";
    /**
     * File object to hold the jar file
     */
    public static File file;
    /**
     * If the properties need to refresh or reload make the variable as
     * Boolean.TRUE Basically the variable will be changed in STPLWatchService
     * while listening the changes of the file
     */
    private static Boolean needRefresh = Boolean.FALSE;

    /** 
     * java.util.logging.Logger is used to catch the logs
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);

    /**
     * the method returns the singleton object the class PropertiesReader and
     * refresh the properties by changing the needRefresh variable as TRUE
     *
     * @return
     */
    public static PropertiesReader getInstance() {
        if (reader == null) {
            reader = new PropertiesReader();
            STPLWatchService.getInstance();
        }

        return reader;
    }

    private PropertiesReader() {

        path = System.getProperty("jboss.server.config.dir");
        LOGGER.debug("Getting jboss.server.config.dir :" + path);
        path = path.replace("standalone", FOLDER_NAME);
        path = path.replace("configuration", JAR_FILE_NAME + JAR_FILE_EXTENSION);
        try {
            file = new File(path);
            LOGGER.debug("File Name :{0}", new Object[]{file.getAbsoluteFile()});
            spilitAndLoadJarFileEntries(file);
        } catch (Exception e) {
            LOGGER.error("Error While reading the Properties from Jboss:{0}", new Object[]{e});
        }

    }

    private void loadProperties(InputStream is, String fileName) {
        try {
            messages.load(is);
        } catch (IOException ex) {
            LOGGER.error("Unable to load file :{0} from {1} with error{2}", new Object[]{fileName, JAR_FILE_NAME, ex});
        }
    }

    /**
     * Method to spilitAndLoadJarFileEntries from the given path
     *
     * @param jarFile
     */
    private void spilitAndLoadJarFileEntries(File jarFile) {
        JarFile jar = null;
        InputStream is = null;
        try {
            jar = new JarFile(jarFile);
            Enumeration<JarEntry> sf = jar.entries();
            while (sf.hasMoreElements()) {
                JarEntry temp = sf.nextElement();
                if (!temp.isDirectory() && !temp.getName().contains(META_INF_NAME) && !temp.getName().startsWith(".")) {
                    String mkDir = temp.getName().substring(0, temp.getName().indexOf(JAR_SEPERATOR));
                    if (Arrays.asList(RESOURCE_PACKAGE_NAMES).contains(mkDir)) {
                        is = jar.getInputStream(temp);
                        if (is != null && temp.getName().endsWith(PROPERTIES_EXTENSION)) {
                            loadProperties(is, temp.getName());
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Unable to load_Properties jar file from repository :with Error :{0}", new Object[]{e});
        } finally {
            try {
                LOGGER.debug("Size after laoding Properties :{0}", new Object[]{messages.size()});
                if (jar != null) {
                    jar.close();
                    LOGGER.debug("Closing the jar:");
                }
            } catch (IOException e) {
                LOGGER.error("Unable to load_Properties jar file from repository :with Error :{0}", new Object[]{e});
            }
        }
    }

    /**
     * STPL watch service to listen the specific directory
     */
    static class STPLWatchService {

        /**
         * Singleton object for STPLWatchService the object will be created on
         * demand
         */
        private static STPLWatchService STPL_WS = null;
        /**
         * Thread is used to start the watch service API as separate service
         */
        private static Thread watchService = null;
        /**
         * java.util.logging.Logger is used to catch the logs
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(STPLWatchService.class);

        private STPLWatchService() {
            STPLRunnableJob runnable = new STPLRunnableJob();
            watchService = new Thread(runnable, "STPL-Watch Service-Thread :");
            startWatchService();
        }

        /**
         * method to start the thread
         */
        private void startWatchService() {
            LOGGER.debug("Starting watcher service :");
            watchService.start();
        }

        /**
         * the method returns the singleton object the class PropertiesReader
         *
         * @return
         */
        public static STPLWatchService getInstance() {
            if (STPL_WS == null) {
                STPL_WS = new STPLWatchService();
            }
            return STPL_WS;
        }

        /**
         * Having the watch service logic in separate runnable class to start as
         * separate service
         */
        class STPLRunnableJob implements Runnable {

            /**
             * Overriding method implements runnable interface
             */
            @Override
            public void run() {
                executeWatchService();
            }

            /**
             * Method to Get the path jar file path in server path creating new
             * path if not exist Creating new watch service using the directory
             * Registering the directory with ENTRY_CREATE,ENTRY_DELETE and
             * ENTRY_MODIFY Events Iterating the Events returned from Watch key
             *
             */
            private void executeWatchService() {
                try {
                    Path myDir = Paths.get(path.replace(JAR_FILE_NAME + JAR_FILE_EXTENSION, ""));
                    File file = myDir.toFile();
                    LOGGER.debug("file exist:{0}", new Object[]{file.exists()});
                    LOGGER.debug("Path Name  : {0}", new Object[]{myDir.toString()});
                    if (!(file.exists() ? file.isDirectory() : file.mkdir())) {
                        LOGGER.error("System dont have persmission to create the folder in the path :{0}", new Object[]{myDir.toString()});
                        return;
                    }
                    WatchService watcher = myDir.getFileSystem().newWatchService();
                    myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
                            StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
                    LOGGER.debug("Creating watch key:");
                    boolean callPropertiesLoader = Boolean.FALSE;
                    WatchKey watckKey = watcher.take();
                    while (watckKey != null) {
                        List<WatchEvent<?>> events = watckKey.pollEvents();
                        for (WatchEvent event : events) {
                            LOGGER.debug("Event Fired : {0}", new Object[]{event.kind()});
                            if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                                callPropertiesLoader = Boolean.TRUE;
                            }
                            if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                                callPropertiesLoader = Boolean.TRUE;
                            }
                            if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                                callPropertiesLoader = Boolean.TRUE;
                            }
                        }
                        watckKey.reset();
                        if (callPropertiesLoader) {
                            reloadProperties();
                        }
                        watckKey = watcher.take();
                    }
                } catch (IOException | InterruptedException e) {
                    LOGGER.error("Error while Starting the Watch Service API: {0}", new Object[]{e});
                }
            }

        }

        /**
         * Method to set needRefresh variable as TRUE it is used to reload the
         * properties object on demand
         */
        void reloadProperties() {
            LOGGER.debug("Reloading properties :");
            needRefresh = Boolean.TRUE;
        }
    }

    public ExtProperties getConfirmationMessages() {
        return messages;
    }

    public class ExtProperties extends Properties {

        @Override
        public String getProperty(String key) {
            if (needRefresh) {
                reader.spilitAndLoadJarFileEntries(file);
                needRefresh = Boolean.FALSE;
            }
            String val = super.getProperty(key);
            return val == null ? "" : val; //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getProperty(String key, String defaultValue) {
            if (needRefresh) {
                reader.spilitAndLoadJarFileEntries(file);
                needRefresh = Boolean.FALSE;
            }
            String val = super.getProperty(key, defaultValue);
            return val == null ? "" : val;//To change body of generated methods, choose Tools | Templates.
        }
    }
}
