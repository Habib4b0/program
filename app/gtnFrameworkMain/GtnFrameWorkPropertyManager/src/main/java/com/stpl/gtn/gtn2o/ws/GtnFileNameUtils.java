package com.stpl.gtn.gtn2o.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.FilenameUtils;

public class GtnFileNameUtils {
	private GtnFileNameUtils() {

	}

	public static String getFullPath(String filePath) {
		return FilenameUtils.getFullPath(filePath);
	}

	public static File getFile(String fileName) {
		return new File(fileName);
	}

	public static String getName(String fileName) {
		return FilenameUtils.getName(fileName);
	}

	public static File getFile(File directory, String fileName) {
		return new File(directory, FilenameUtils.getName(fileName));
	}

	public static FileInputStream getFileInputStream(String path) throws FileNotFoundException {
		return new FileInputStream(path);
	}
}
