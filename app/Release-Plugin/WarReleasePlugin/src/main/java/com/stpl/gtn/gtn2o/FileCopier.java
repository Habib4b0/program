/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author abishekram.r
 */
public class FileCopier {

	public void copyFile(File projectFolder) {

		FilenameFilter fr = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.equalsIgnoreCase("target");
			}
		};
		FilenameFilter jarr = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.lastIndexOf('.') > 0) {
					// get last index for '.' char
					int lastIndex = name.lastIndexOf('.');

					// get extension
					String str = name.substring(lastIndex);

					// match path name extension
					if (str.equals(".war")) {
						return true;
					} else {
					}
				}
				return false;
			}

		};

		for (File aan : projectFolder.listFiles(fr)) {
			if (aan.isDirectory()) {
				for (File aasn : aan.listFiles(jarr)) {
					copyTo(aasn, projectFolder);
				}
			}

		}

	}

	private static void copyTo(File aasn, File projectFolder) {
		File copy = new File(projectFolder.getParent() + "/Dist");
                System.out.println("Copy to"+copy.getPath());
		if (copy.isDirectory()) {
		} else {
			copy.mkdir();
		}
		try {
			FileUtils.copyFile(aasn, new File(copy.getPath() + "/" + aasn.getName()));
		} catch (IOException ex) {
			Logger.getLogger(FileCopier.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
