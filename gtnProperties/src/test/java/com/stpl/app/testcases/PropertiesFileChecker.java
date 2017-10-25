/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.testcases;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author KarthikeyanS
 */
public class PropertiesFileChecker {
    /**
     * the Log
     */
    private static final Logger LOG = Logger.getLogger(PropertiesFileChecker.class.getName());
    private static Path mkDir;
    private static final List<String> keyHolder = new ArrayList();
    private static final List<String> valHolder = new ArrayList();
    private static final List<String> duplicateKeys = new ArrayList();
    private static final List<String> duplicateVals = new ArrayList();
    private static final List<String> valueLessKeys = new ArrayList();

    @Test
    public void testPrintMessage() {
        try {
            LOG.info("Executing the PropertiesFileChecker Test cases :");

            URL url = this.getClass().getResource("/messages");
            mkDir = Paths.get(url.toURI());
            String msg = "Message package is not present in the path :" + mkDir;
            assertEquals(msg, Boolean.TRUE, check_PackagePresent());
            String ext = check_PropertiesFileExtension();
            msg = ext + " Extension is not allowed to keep in the path :" + mkDir;
            assertEquals(msg, "" + Boolean.TRUE, ext);
            msg = "Duplicate keys present in property files, Please rectify it : Keys :" + duplicateKeys;
            assertEquals(msg, Boolean.TRUE, check_KeyDuplicates());
            msg = "Duplicate values present in property files, Please rectify it : Values :" + duplicateVals;
            assertEquals(msg, Boolean.TRUE, check_ValueDuplicates());
            msg = "Empty values present in property files, Please rectify it : Values :" + valueLessKeys;
            assertEquals(msg, Boolean.TRUE, check_ValueEmpties());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.log(Level.SEVERE, "Error While Executing Test cases :{0}", e.getMessage());
        }
    }

    private Boolean check_PackagePresent() {
        LOG.log(Level.INFO, "Path Exist --- {0}", mkDir);
        File file = mkDir.toFile();
        return file.isDirectory();
    }

    private String check_PropertiesFileExtension() {
        List<String> proList = new ArrayList<>();
        File file = mkDir.toFile();
        File[] files = file.listFiles();
        keyHolder.clear();
        valHolder.clear();
        duplicateKeys.clear();
        duplicateVals.clear();
        valueLessKeys.clear();
        for (File file1 : files) {
            try {
                Boolean flag = file1.getName().endsWith(".properties");
                if (!flag) {
                    return file1.getName().substring(file1.getName().lastIndexOf("."));
                }
                proList.clear();
                proList.addAll(Files.readAllLines(file1.toPath(), StandardCharsets.UTF_8));
                UtilClass.loadAllLines(proList, keyHolder, valHolder, duplicateKeys, duplicateVals, valueLessKeys);
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, "Error While reading files :{0}", ex.getMessage());
            }
        }

        return "" + Boolean.TRUE;
    }

    private Boolean check_KeyDuplicates() {

        LOG.log(Level.INFO, "duplicateKeys size:{0}", duplicateKeys.size());
        return duplicateKeys.isEmpty();
    }

    private Boolean check_ValueDuplicates() {
        LOG.log(Level.INFO, "duplicateVals size:{0}", duplicateVals.size());
        return duplicateVals.isEmpty();
    }

    private Boolean check_ValueEmpties() {
        LOG.log(Level.INFO, "Keys with Empty Value size:{0}", valueLessKeys.size());
        return valueLessKeys.isEmpty();
    }

}
