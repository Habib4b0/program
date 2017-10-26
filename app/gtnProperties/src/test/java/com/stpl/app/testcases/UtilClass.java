/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.testcases;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 *
 * @author KarthikeyanS
 */
public class UtilClass {

    public static void loadAllLines(List<String> proList, List<String> keyHolder, List<String> valHolder, List<String> duplicateKeys,
            List<String> duplicateVals, List<String> valueLessKeys) throws Exception {
        for (String line : proList) {
            if (line == null || "".equals(line.trim()) || !isKeyValuePair(line)) {
                continue;
            }
            line = line.trim();
            String keyValuePair = line;
            int equals = keyValuePair.indexOf("=");
            if (equals == -1) {
                continue;
            }
//            assert equals != -1;

            String key = keyValuePair.substring(0, equals).trim();
            String value = keyValuePair.substring(equals + 1).trim();

            if (keyHolder.contains(key)) {
                duplicateKeys.add(key);
            } else {
                keyHolder.add(key);
            }
            if (valHolder.contains(value)) {
                duplicateVals.add(value);
            } else {
                valHolder.add(value);
            }
            if ("".equals(value.trim())) {
                valueLessKeys.add(key);
            }

        }

    }

    private static boolean isKeyValuePair(String line) {
        int equals = line.indexOf("=");
        if (equals == -1) {
            return false;
        }
        int cmt = line.indexOf("#");
        return (cmt > 0 && equals < cmt) || (cmt < 0 && equals >= 0);
    }
}
