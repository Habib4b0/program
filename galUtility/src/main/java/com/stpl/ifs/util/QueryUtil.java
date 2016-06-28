/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sriram
 */
public class QueryUtil {

    public static List<String> getColumnNames(String query) {
        ArrayList<String> columnNames = new ArrayList<>();
        query = query.replaceFirst("select ", "").replaceFirst("Select ", "").replaceFirst("SELECT ", "").replace("Distinct ", query).replace("distinct ", query).replace("DISTINCT ", query);
        int endIndex = query.indexOf(" from ");
        if (endIndex == -1) {
            endIndex = query.indexOf(" From ");
            if (endIndex == -1) {
                endIndex = query.indexOf(" FROM ");
            }
        }
        if (endIndex > -1) {
            String newQuery = query.substring(0, endIndex);

            String[] str1 = newQuery.split(",");

            for (String columnName : str1) {

                if (columnName.contains(" as ")) {
                    columnNames.add(columnName.split(" as ")[1].trim());
                } else if(columnName.contains(" AS ")){
                    columnNames.add(columnName.split(" AS ")[1].trim());
                } else if(columnName.contains(" As ")){
                    columnNames.add(columnName.split(" As ")[1].trim());
                } else {
                    columnNames.add(columnName.trim());
                }
            }
        }
        return columnNames;
    }
}
