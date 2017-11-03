/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.customcomponentdemo.demo.util;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Abhiram
 */
public class HeaderUtils {
    public static CustomTableHeaderDTO getProjectionResultsLeftTableColumns(Map selection) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("group", "Group",String.class);
        return tableHeaderDTO;
}
   public static CustomTableHeaderDTO getProjectionResultsRightTableColumns(Map selection,List<String> discountNames) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object singleColumn="checkRecord";
        Object doubleColumn="checkRecord";
        tableHeaderDTO.addSingleColumn("checkRecord", " ",Boolean.class);
        tableHeaderDTO.addDoubleColumn(doubleColumn, " ");
        tableHeaderDTO.addDoubleHeaderMap(doubleColumn, new Object[]{singleColumn});
        return getCalculatedProjectionResultsColumns(selection,tableHeaderDTO,discountNames);
    }
   public static boolean isInteger(String s) {
            try { 
                Integer.parseInt(s); 
            } catch(NumberFormatException e) { 
                return false; 
}
            return true;
        }
   public static CustomTableHeaderDTO getProjectionResultsRightTableColumns(Map selection) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedDiscountProjectionColumns(selection,tableHeaderDTO);
    }
   public static CustomTableHeaderDTO getCalculatedDiscountProjectionColumns(Map selection, CustomTableHeaderDTO tableHeaderDTO) {
        String freq = selection.get("frequency").toString();
        String hist = selection.get("history").toString();
        String projFreq = selection.get("projectFrequency").toString();
        String projection = selection.get("projection").toString();
        int no = isInteger(selection.get("no").toString()) ? Integer.valueOf(selection.get("no").toString()) : 0;
        int year = isInteger(selection.get("year").toString()) ? Integer.valueOf(selection.get("year").toString()) : 0;
        System.out.println(" Inside header calculation ");
        List<String> discountNameList = new ArrayList<String>();
        discountNameList.add("All Discount");
        for (int i = 0; i < no; i++) {
            if(i==0){
                discountNameList = new ArrayList<String>();
            }
            discountNameList.add("discount-" + i);
        }

        System.out.println(" Discount Names" + discountNameList.size());
        Calendar ob = Calendar.getInstance();

//        ob.set(2015, 8, 25);
        int curMonth = ob.get(Calendar.MONTH);
        int curDate = ob.get(Calendar.DATE);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;
        int frequency = 0;
        int projectFrequency = 0;
        int division = 1;
        if (freq.equals("Quarterly")) {
            current = (curMonth / 3);
            division = 4;
            try {
                frequency = Integer.valueOf(hist.replace("Quarter", "").replace("s", "").trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Quarter", "").replace("s", "").trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.equals("Semi-Annually")) {
            current = (curMonth / 6);
            division = 2;
            try {
                frequency = Integer.valueOf(hist.replace("Semi-Annual", "").trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Semi-Annual", "").trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.equals("Monthly")) {
            current = curMonth;
            division = 12;
            try {
                frequency = Integer.valueOf(hist.replace("Month", "").replace("s", "").trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Month", "").replace("s", "").trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.equals("Annually")) {
            current = curYear;
            division = 1;
            try {
                frequency = Integer.valueOf(hist.replace("Year", "").trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Year", "").trim());
            } catch (NumberFormatException e) {
            }
        }

        projectFrequency = projectFrequency + 1;
        if (year != 0) {
            if (freq.equals("Quarterly")) {
                frequency = 4;
                projectFrequency = 4;
            } else if (freq.equals("Semi-Annually")) {
                frequency = 2;
                projectFrequency = 2;
            } else if (freq.equals("Monthly")) {
                frequency = 12;
                projectFrequency = 12;
            } else if (freq.equals("Annually")) {
                frequency = 1;
                projectFrequency = 1;
            }
            if (year < curYear) {
                projectFrequency = 0;
            }
            if (year > curYear) {
                frequency = 0;
            }
            if (year == curYear) {
                frequency = current;
                projectFrequency = projectFrequency - current;
            }
        }


        int pastYear = curYear;
        int startFreq = current + 1;

        int tempFreq = frequency - current;
        if (tempFreq > 0) {
            pastYear = pastYear - tempFreq / division;
            startFreq = 1;
            if (tempFreq % division > 0) {
                pastYear = pastYear - 1;
                startFreq = division - (tempFreq % division) + 1;
            }
        } else {
            startFreq = startFreq - frequency;
        }
        List<String> futurePropertyIds = new ArrayList<String>();

        if (!discountNameList.isEmpty()) {
            for (String discountName : discountNameList) {
                String discountColumnName = discountName.replaceAll(" ", "");
                List<Object> tmap = new ArrayList<Object>();
                int squr = startFreq;
                if (year != 0) {
                    squr = 1;
                }
                int syear = pastYear;
                if (freq.contains("Annually") && !freq.contains("Semi-Annually")) {
                    syear = current - frequency;
                }
                if (year != 0) {
                    syear = year;
                }
                for (int i = 0; i < frequency; i++) {
                    List<Object> dmap = new ArrayList<Object>();
                    String commonColumn = "";
                    String commonHeader = "";
                   if (freq.contains("Quarterly")) {
                commonColumn = "Q" + squr + "" + syear;
                commonHeader = "Q" + squr + " " + syear;
            } else if (freq.contains("Semi-Annually")) {
                commonColumn = "S" + squr + "" + syear;
                commonHeader = "S" + squr + " " + syear;
            } else if (freq.contains("Annually")) {
                commonColumn = "" + syear;
                commonHeader = "" + syear;
            } else if (freq.contains("Monthly")) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }

                    commonColumn = discountColumnName + commonColumn;
                    if (projection.contains("Both") || projection.contains("Actuals")) {
                        dmap.add(commonColumn + "ActualRate");
                        tableHeaderDTO.addSingleColumn(commonColumn + "ActualRate", "Actual Rate", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(commonColumn + "ActualRate", "Actual Rate");
                    }
                    if (projection.contains("Both") || projection.contains("Projections")) {
                        dmap.add(commonColumn + "ProjectedRate");
                        tableHeaderDTO.addSingleColumn(commonColumn + "ProjectedRate", "Projected Rate", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(commonColumn + "ProjectedRate", "Projected Rate");
                    }
                    if (!dmap.isEmpty()) {
                        tmap.add(commonColumn);
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHistoryHeaderMap(commonColumn, dmap.toArray());
                    }
                    squr++;
                    if (squr > division) {
                        squr = 1;
                        syear++;
                    }
                }
                squr = current + 1;
                syear = curYear;

                if (year > curYear) {
                    squr = 1;
                }

                for (int i = 0; i < projectFrequency; i++) {
                    List<Object> dmap = new ArrayList<Object>();
                    String commonColumn = "";
                    String commonHeader = "";
                    if (freq.contains("Quarterly")) {
                        commonColumn = "Q" + squr + "" + syear;
                        commonHeader = "Q" + squr + " " + syear;
                    } else if (freq.contains("Semi-Annually")) {
                        commonColumn = "S" + squr + "" + syear;
                        commonHeader = "S" + squr + " " + syear;
                    } else if (freq.contains("Annually")) {
                        commonColumn = "" + syear;
                        commonHeader = "" + syear;
                    } else if (freq.contains("Monthly")) {
                        String monthName = getMonthForInt(squr - 1);
                        commonColumn = monthName + syear;
                        commonHeader = monthName + " " + syear;
                    }
                    commonColumn = discountColumnName + commonColumn;

                    dmap.add(commonColumn + "ProjectedRate");
                    tableHeaderDTO.addSingleColumn(commonColumn + "ProjectedRate", "Projected Rate", String.class);
                    tableHeaderDTO.addSingleProjectedColumn(commonColumn + "ProjectedRate", "Projected Rate");
                    futurePropertyIds.add(commonColumn + "ProjectedRate");
                    if (!dmap.isEmpty()) {
                        tmap.add(commonColumn);
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());
                    }
//                    }
                    squr++;
                    if (squr > division) {
                        squr = 1;
                        syear++;
                    }
                }

                if (!tmap.isEmpty()) {
                    tableHeaderDTO.addTripleColumn(discountColumnName, discountName);
                    tableHeaderDTO.addTripleHeaderMap(discountColumnName, tmap.toArray());
                }
            }
        }
//        List<Object> headerContents = new ArrayList<Object>();
//        headerContents.add(tableHeaderDTO);
//        headerContents.add(futurePropertyIds);
        return tableHeaderDTO;
    }
    public static CustomTableHeaderDTO getCalculatedProjectionResultsColumns(Map selection,CustomTableHeaderDTO tableHeaderDTO,List<String> discountNames) {
        String projection = selection.get("projection").toString();
        String pivotView = selection.get("pivotView").toString();
        if(pivotView.toLowerCase().contains("variable")){
           
            for(int i=0;i<7;i++){
                String commonColumn = "";
                String oldCommonColumn = "";
                String commonHeader = "";
                if(i==0){
                    commonColumn = "gts";
                    commonHeader = "Gross Trade Sales";
                }else if(i==1){
                    commonColumn = "perOfBus";
                    commonHeader = "% of Business";
                }else if(i==2){
                    commonColumn = "conSalesWac";
                    commonHeader = "Contract Sales @ WAC";
                }else if(i==3){
                    commonColumn = "unitVol";
                    commonHeader = "Unit Volume";
                }else if(i==4){
                    commonColumn = "totDisPer";
                    commonHeader = "Total Discount %";
                }else if(i==5){
                    commonColumn = "totDisDol";
                    commonHeader = "Total Discount $";
                }else if(i==6){
                    commonColumn = "netSales";
                    commonHeader = "Net Sales";
                }
                oldCommonColumn=commonColumn;
                System.out.println("i= "+i);
                int j=-1;
                boolean disc=true;
                while(disc){
                    System.out.println(commonColumn);
                List<Object> dmap = new ArrayList<Object>();
            if (projection.contains("both")||projection.contains("Both") || projection.contains("actuals")||projection.contains("Actuals")) {
                    System.out.println(commonColumn + "Actuals");
                    String singleColumn=commonColumn + "Actuals";
                    dmap.add(commonColumn + "Actuals");
                    if(i==1||i==4){
                        tableHeaderDTO.addSingleColumn(commonColumn + "Actuals", "Actual Rate",String.class);
                    }else{
                        tableHeaderDTO.addSingleColumn(commonColumn + "Actuals", "Actuals",String.class);
                    }
                    
                }
                if (projection.contains("both")||projection.contains("Both") || projection.contains("projections")|| projection.contains("Projections")) {
                    System.out.println(commonColumn + "Projections");
                    String singleColumn=commonColumn + "Projections";
                    dmap.add(singleColumn);
                    if(i==1||i==4){
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projected Rate",String.class);
                    }else{
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projection",String.class);
                    }
                    
                }
            disc=false;
            if (!dmap.isEmpty()) {
                System.out.println(commonColumn);
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
            if(!discountNames.isEmpty()&&(i==4||i==5)){
                if(discountNames.size()>(j+1)){
                    disc=true;
                    j++;
                    System.out.println("j= "+j);
                commonHeader=discountNames.get(j);
                commonColumn=oldCommonColumn+commonHeader.replace(" ", "");
                }
                
            }
                }
            }
            
        }else{
            String freq = selection.get("frequency").toString();
        String hist = selection.get("history").toString();
        String projFreq = selection.get("projectFrequency").toString();
        
        
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curDate = ob.get(Calendar.DATE);
        int curYear = ob.get(Calendar.YEAR);
        System.out.println("curMonth=" + curMonth);
        System.out.println("curDate=" + curDate);
        System.out.println("curYear=" + curYear);
        int current = 1;
        int frequency = 0;
        int projectFrequency = 0;
        int division = 1;
        if (freq.contains("Quarterly")) {
            current = (curMonth / 3);
            division = 4;
            try {
                frequency = Integer.valueOf(hist.toLowerCase().replace("quarter", "").replace("s", "").trim());
                projectFrequency=Integer.valueOf(projFreq.toLowerCase().replace("quarter", "").replace("s", "").trim());
            } catch (NumberFormatException e) {

            }
        } else if (freq.contains("Semi-Annually")) {
            current = (curMonth / 6);
            division = 2;
            try {
                frequency = Integer.valueOf(hist.toLowerCase().replace("semi-annual", "").trim());
                projectFrequency=Integer.valueOf(projFreq.toLowerCase().replace("semi-annual", "").trim());
            } catch (NumberFormatException e) {

            }
        } else if (freq.contains("Monthly")) {
            current = curMonth;
            division = 12;
            try {
                frequency = Integer.valueOf(hist.toLowerCase().replace("month", "").replace("s", "").trim());
                projectFrequency=Integer.valueOf(projFreq.toLowerCase().replace("month", "").replace("s", "").trim());
            } catch (NumberFormatException e) {

            }
        } else if (freq.contains("Annually")) {
            current = curYear;
            division = 1;
            try {
                frequency = Integer.valueOf(hist.toLowerCase().replace("year", "").trim());
                projectFrequency=Integer.valueOf(projFreq.toLowerCase().replace("year", "").trim());
            } catch (NumberFormatException e) {

            }
        }
        System.out.println("freq="+freq);
        System.out.println("hist="+hist);
        System.out.println("projFreq="+projFreq);
        System.out.println("frequency="+frequency);
        System.out.println("projectFrequency="+projectFrequency);
        projectFrequency=projectFrequency+1;
        int pastYear = curYear;

        int startFreq = current + 1;

        int tempFreq = frequency - current;
        if (tempFreq > 0) {
            pastYear = pastYear - tempFreq / division;
            startFreq = 1;
            if (tempFreq % division > 0) {
                pastYear = pastYear - 1;
                startFreq = division - (tempFreq % division) + 1;
            }
        } else {
            startFreq = startFreq - frequency;
        }

        int squr = startFreq;
        int syear = pastYear;
        if (freq.contains("Annually")&&!freq.contains("Semi-Annually")) {
            syear = current - frequency;
        }
        System.out.println("start Past--"+squr+"--"+syear);
        for (int i = 0; i < frequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = "";
            String commonHeader = "";
            if (freq.contains("Quarterly")) {
                commonColumn = "Q" + squr + "" + syear;
                commonHeader = "Q" + squr + " " + syear;
            } else if (freq.contains("Semi-Annually")) {
                commonColumn = "S" + squr + "" + syear;
                commonHeader = "S" + squr + " " + syear;
            } else if (freq.contains("Annually")) {
                commonColumn = "" + syear;
                commonHeader = "" + syear;
            } else if (freq.contains("Monthly")) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }
            System.out.println(commonColumn);

                if (projection.contains("both")||projection.contains("Both") || projection.contains("actuals")||projection.contains("Actuals")) {
                    System.out.println("Actuals");
                    String singleColumn=commonColumn + "Actuals";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Actuals",String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Actuals");
                }
                if (projection.contains("both")||projection.contains("Both") || projection.contains("projections")|| projection.contains("Projections")) {
                    System.out.println("Projections");
                    String singleColumn=commonColumn + "Projections";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projections",String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Projections");
                }
            
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHistoryHeaderMap(commonColumn, dmap.toArray());
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        squr = current+1;
        syear = curYear;
        System.out.println("start Future--"+squr+"--"+syear);
        for (int i = 0; i < projectFrequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = "";
            String commonHeader = "";
            if (freq.contains("Quarterly")) {
                commonColumn = "Q" + squr + "" + syear;
                commonHeader = "Q" + squr + " " + syear;
            } else if (freq.contains("Semi-Annually")) {
                commonColumn = "S" + squr + "" + syear;
                commonHeader = "S" + squr + " " + syear;
            } else if (freq.contains("Annually")) {
                commonColumn = "" + syear;
                commonHeader = "" + syear;
            } else if (freq.contains("Monthly")) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }
            System.out.println(commonColumn);
            if (projection.contains("both")||projection.contains("Both") || projection.contains("projections")|| projection.contains("Projections")) {
                    System.out.println("Projections");
                    String singleColumn=commonColumn + "Projections";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projections",String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, "Projections");
                }
            if (!dmap.isEmpty()) {
                
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        }
        
        
        return tableHeaderDTO;
    }
    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }
}
