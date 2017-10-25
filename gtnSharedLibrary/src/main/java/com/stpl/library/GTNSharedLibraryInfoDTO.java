/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.library;

/**
 *
 * @author Abhiram.Giri
 */
public class GTNSharedLibraryInfoDTO {
    String libraryName;
    String jarName;
    String jarPath;

    public GTNSharedLibraryInfoDTO() {
    }

    public GTNSharedLibraryInfoDTO(String libraryName, String jarName, String jarPath) {
        this.libraryName = libraryName;
        this.jarName = jarName;
        this.jarPath = jarPath;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }
    
}
