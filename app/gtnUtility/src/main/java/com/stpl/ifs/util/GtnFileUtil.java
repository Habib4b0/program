package com.stpl.ifs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GtnFileUtil {

	private GtnFileUtil() {

	}

	public static File getFile(String filename) {
		return new File(filename);
	}

	public static File getFile(File directory, String fileName) {
		return new File(directory, fileName);
	}

	public static FileInputStream getFileInputStream(String fileName) throws FileNotFoundException {
		return new FileInputStream(fileName);
	}

	public static FileOutputStream getFileOutputStream(String fileName) throws FileNotFoundException {
		return new FileOutputStream(fileName);
	}

	public static FileOutputStream getFileOutputStream(File fileName) throws FileNotFoundException {
		return new FileOutputStream(fileName);
	}

	public static FileWriter getFileWriter(String fileName) throws IOException {
		return new FileWriter(fileName);
	}

	public static Path getPath(String fileName) {
		return Paths.get(fileName);
	}
}
