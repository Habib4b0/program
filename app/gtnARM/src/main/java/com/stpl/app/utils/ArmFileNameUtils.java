/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

/**
 *
 * @author Satheesh.Nagendran
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

public class ArmFileNameUtils {

    private ArmFileNameUtils() {

    }

    public static String getFullPath(String filePath) {
        return FilenameUtils.getFullPath(filePath);
    }

    public static File getFile(String fileName) {
        return new File(FilenameUtils.getName(fileName));
    }

    public static String getName(String fileName) {
        return FilenameUtils.getName(fileName);
    }

    public static File getFile(File directory, String fileName) {
        return new File(directory, FilenameUtils.getName(fileName));
    }

    public static FileInputStream getFileInputStream(String path) throws FileNotFoundException {
        return new FileInputStream(FilenameUtils.getName(path));
    }

    public static Path getPath(String fileName) {
        return Paths.get(FilenameUtils.getName(fileName));
    }

    public static FileOutputStream getFileOutputStream(String fileName) throws FileNotFoundException {
        return new FileOutputStream(FilenameUtils.getName(fileName));
    }

    public static FileOutputStream getFileOutputStream(File fileName) throws FileNotFoundException {
        return new FileOutputStream(fileName);
    }

    public static FileWriter getFileWriter(String fileName) throws IOException {
        return new FileWriter(FilenameUtils.getName(fileName));
    }
}
