/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.sharedlibrary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnNewArchSharedLibraryContextListener implements ServletContextListener {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnNewArchSharedLibraryContextListener.class);
	private static Properties prop = null;

	public static Properties getProperties() {
		if (prop != null) {
			return prop;
		} else {
			prop = new Properties();

			try {
				InputStream is = GtnNewArchSharedLibraryContextListener.class
						.getResourceAsStream("/shared-library.properties");
				prop.load(is);
			} catch (IOException ex) {
				LOGGER.error(null, ex);
			}
		}
		return prop;
	}

	// Constructor.
	public GtnNewArchSharedLibraryContextListener() {
		super();
	}

	private void prepareLibrary(ServletContextEvent servletContextEvent, String sharedLibName, String path,
			String nonSharedJars) {
		LOGGER.info("shared.library.name=" + sharedLibName);
		LOGGER.info("non.shared.jars=" + nonSharedJars);
		String libDepandancys = getProperties().getProperty(sharedLibName + ".dependency");
		String libOptionalDepandancys = getProperties().getProperty(sharedLibName + ".optional.dependency");
		String[] sharedLibs = sharedLibName.split("\\.");

		StringBuilder sharedLibPath = new StringBuilder("");
		StringBuilder sharedLibBeforePath = new StringBuilder("");
		for (String sharedLib : sharedLibs) {
			sharedLibPath.append(sharedLib + "/");
			sharedLibBeforePath.append("../");
		}
		LOGGER.info("LoadLibrary Web app context initialized." + sharedLibPath);

		ServletContext ct = servletContextEvent.getServletContext();
		String currentPath = ct.getRealPath("/");
		currentPath = currentPath.replace("\\", "/");
		LOGGER.info("currentPath=" + currentPath);
		int index = currentPath.lastIndexOf("/standalone/");

		String moduledirpath = currentPath.substring(0, index) + "/modules/" + sharedLibPath + "main";
		LOGGER.info("moduledirpath=" + moduledirpath);
		String modulepath = moduledirpath + "/module.xml";
		final File modulefile = new File(modulepath);
		StringBuilder content = new StringBuilder("<?xml version=\"1.0\"?>\n" + "\n"
				+ "<module xmlns=\"urn:jboss:module:1.0\" name=\"" + sharedLibName + "\">\n" + "	<resources>\n");
		String moduleEntryPath = currentPath.substring(index + 1);

		String libpath = currentPath + "/" + path + "/";
		final File libfile = new File(libpath);

		if (libfile.isDirectory()) {
			String[] fileList = libfile.list();

			for (String filename : fileList) {
				if (filename.endsWith("jar") && !nonSharedJars.contains(filename)) {
					LOGGER.info(filename);
					GtnNewArchSharedLibraryUI.container
							.addBean(new GTNSharedLibraryInfoDTO(sharedLibName, filename, libpath));
					content.append("		<resource-root path=\"../../" + sharedLibBeforePath + "" + moduleEntryPath
							+ path + "/" + filename + "\" />\n");
				}
			}
		}
		content.append("	</resources>	 \n");
		String start = "<dependencies>\n";
		String end = "";
		if (libDepandancys != null && !libDepandancys.isEmpty()) {
			content.append(start);
			String[] libDepandancy = libDepandancys.split(",");
			for (String depandancy : libDepandancy) {
				content.append("        <module name=\"" + depandancy + "\"/>\n");
			}
			start = "";
			end = "    </dependencies>\n";
		}
		if (libOptionalDepandancys != null && !libOptionalDepandancys.isEmpty()) {
			content.append(start);
			content.append("        <!-- Optional deps -->\n");
			String[] libOptDepandancy = libOptionalDepandancys.split(",");
			for (String depandancy : libOptDepandancy) {
				content.append("        <module name=\"" + depandancy + "\" optional=\"true\"/>\n");
			}
			end = "    </dependencies>\n";
		}
		content.append(end);
		content.append("</module>");
		try {
			if (!modulefile.exists()) {
				// if file doesn't exists, then create it
				new File(moduledirpath).mkdirs();
				boolean isFileCreated = modulefile.createNewFile();
				LOGGER.debug("File created status " + isFileCreated);
			}
		} catch (IOException e) {
			LOGGER.error("", e);
		}
		try (FileOutputStream fop = new FileOutputStream(modulefile)) {

			// get the content in bytes
			byte[] contentInBytes = content.toString().getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

		} catch (IOException e) {
			LOGGER.error("", e);
		}
	}

	// Our web app (Vaadin app) is starting up.
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String sharedLibNames = getProperties().getProperty("shared.library.names");
		String sharedLibPaths = getProperties().getProperty("shared.library.paths");
		String nonSharedJars = getProperties().getProperty("non.shared.jars");
		String[] sharedLibNameArr = sharedLibNames.split(",");
		String[] sharedLibPathArr = sharedLibPaths.split(",");
		GtnNewArchSharedLibraryUI.container.removeAllItems();
		for (int i = 0; i < sharedLibNameArr.length; i++) {
			prepareLibrary(servletContextEvent, sharedLibNameArr[i], sharedLibPathArr[i], nonSharedJars);
		}
	}

	// Our web app (Vaadin app) is shutting down.
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		LOGGER.info("LoadLibrary Web app context destroyed."); // INFO logging.

	}
}
