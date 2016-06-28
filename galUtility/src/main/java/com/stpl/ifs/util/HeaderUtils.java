/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Dynamic Table Header Creation
 * @author arulmurugan
 */
public class HeaderUtils {

    boolean sales = false;
    boolean unit = false;
    boolean actual = false;
    boolean projection = false;
    public static String ANNUAL = "Annual";
    public static String SEMI_ANNUAL = "semi-Annual";
    public static String MONTH = "monthly";
    public static String QUARTER = "quarterly";
    public static String Actual_sales = "Actuals";
    public static String Actual_units = "Actuals";
    public static String Projected_sales = "Projections";
    public static String Projected_units = "Projections";
    public static String Actual_Rate = "Actual Rate";
    public static String Actual_Amount = "Actual Amount";
    public static String Projected_Rate = "Projected Rate";
    public static String Projected_Amount = "Projected Amount";
    public static String JAN = "JAN";
    public static String FEB = "FEB";
    public static String MARCH = "MARCH";
    public static String APRIL = "APRIL";
    public static String MAY = "MAY";
    public static String JUNE = "JUNE";
    public static String JULY = "JULY";
    public static String AUG = "AUG";
    public static String SEP = "SEP";
    public static String OCT = "OCT";
    public static String NOV = "NOV";
    public static String DECEMBER = "DECEMBER";
    public static String[] MONTHS = {JAN, FEB, MARCH, APRIL, MAY, JUNE, JULY, AUG, SEP, OCT, NOV, DECEMBER};
    public static String Q1 = "q1";
    public static String Q2 = "q2";
    public static String Q3 = "q3";
    public static String Q4 = "q4";
    public static String S1 = "s1";
    public static String S2 = "s2";
    int currentYear;
    int history1;
    int history2;
    int history3;
    int proj1;
    int proj2;
    int proj3;
    public static String Qua1 = "1 Quarters";
    public static String Qua2 = "2 Quarters";
    public static String Qua3 = "3 Quarters";
    public static String Qua4 = "4 Quarters";
    public static String Qua5 = "5 Quarters";
    public static String Qua6 = "6 Quarters";
    public static String Qua7 = "7 Quarters";
    public static String Qua8 = "8 Quarters";
    public static String Qua9 = "9 Quarters";
    public static String Qua10 = "10 Quarters";
    public static String Qua11 = "11 Quarters";
    public static String Qua12 = "12 Quarters";
    public static String Semi1 = "1 Semi-Annual";
    public static String Semi2 = "2 Semi-Annual";
    public static String Semi3 = "3 Semi-Annual";
    public static String Semi4 = "4 Semi-Annual";
    public static String Semi5 = "5 Semi-Annual";
    public static String Semi6 = "6 Semi-Annual";
    public static String Year1 = "1 Year";
    public static String Year2 = "2 Year";
    public static String Year3 = "3 Year";

   public static class NonMantaded {

        public static final Object[] SALES_PROJ_RESULTS_COLUMNS = new Object[]{"group"};
        public static final String[] SALES_PROJ_RESULTS__HEADER = new String[]{"Group"};
    }

   /**
    * Initializing the current year value to past 3 histories and 3 projections 
    */
    public void getCurrentYear() {
        Calendar now = Calendar.getInstance();
        currentYear = now.get(Calendar.YEAR);
        history1 = currentYear - 3;
        history2 = currentYear - 2;
        history3 = currentYear - 1;
        proj1 = currentYear + 1;
        proj2 = currentYear + 2;
        proj3 = currentYear + 3;

    }

    /**
     * To detect the SalesOrUnit and ActualOrProj
     * @param salesOrUnits
     * @param actualOrProj 
     */
    public void setBaseVariables(String salesOrUnits, String actualOrProj) {
        if (salesOrUnits.equalsIgnoreCase("both")) {
            sales = true;
            unit = true;
        } else if (salesOrUnits.equalsIgnoreCase("Sales")) {
            sales = true;
            unit = false;
        } else if (salesOrUnits.equalsIgnoreCase("units")) {
            sales = false;
            unit = true;
        }
        if (actualOrProj != null || !actualOrProj.equals(" ")) {
            if (actualOrProj.equalsIgnoreCase("both")) {
                actual = true;
                projection = true;
            } else if (actualOrProj.equalsIgnoreCase("Actuals")) {
                actual = true;
                projection = false;
            } else if (actualOrProj.equalsIgnoreCase("Projections")) {
                actual = false;
                projection = true;
            }
        }
    }

    //Karthikeyan - Channel Discount
    public List<List> getSalesProjectionResults(String frequency,
            String history, Object viewType) {
        List<String> visibleColomns = new ArrayList<String>();
        List<String> header = new ArrayList<String>();
        List<List> finalList = new ArrayList<List>();
        getCurrentYear();
        int histNum;

        //History
        if (history != null && !"null".equals(history)) {
            if (frequency.equalsIgnoreCase(MONTH)) {
               
                history = history.replace(" Months", "");
                histNum = Integer.parseInt(history);
                
                int i = 11;
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {

                    if (loop % 12 == 0) {
                        year--;
                        i = 11;
                    }
                    visibleColomns.add("histValue" + (loop + 1));
                    header.add(year + " " + MONTHS[i]);
                    i--;
                }
                Collections.reverse(header);
            }

            if (frequency.equalsIgnoreCase(QUARTER)) {
               
                history = history.replace(" Quarters", "");
                histNum = Integer.parseInt(history);
              
                int i = 1;
                int year = proj1;

                for (int loop = 0; loop < histNum; loop++) {
                    if (loop % 4 == 0) {
                        year--;
                        visibleColomns.add("histValue" + (loop + 1));
                        header.add("Q4 " + year);
                    }
                    if (loop % 4 == 1) {
                        visibleColomns.add("histValue" + (loop + 1));
                        header.add("Q3 " + year);
                    }
                    if (loop % 4 == 2) {
                        visibleColomns.add("histValue" + (loop + 1));
                        header.add("Q2 " + year);
                    }
                    if (loop % 4 == 3) {
                        visibleColomns.add("histValue" + (loop + 1));
                        header.add("Q1 " + year);
                    }
                }
                Collections.reverse(header);
            }
            if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
                
                history = history.replace(" Semi-Annual", "");
                histNum = Integer.parseInt(history);
               
                int i = 1;
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {
                    if (loop % 2 == 1) {
                        year--;
                        visibleColomns.add("histValue" + (loop + 1));
                        header.add("S2 " + year);
                    }
                    if (loop % 2 == 0) {
                        visibleColomns.add("histValue" + (loop + 1));
                        header.add("S1 " + year);
                    }
                }
                Collections.reverse(header);
            }
            if (frequency.equalsIgnoreCase(ANNUAL)) {
               
                history = history.replace(" Year", "");
                histNum = Integer.parseInt(history);
               
                int i = 1;
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {
                    year--;
                    visibleColomns.add("histValue" + (loop + 1));
                    header.add(String.valueOf(year));
                }
                Collections.reverse(header);
            }

        }
        //Projected
        if (frequency.equalsIgnoreCase(MONTH)) {
            
            int i = 0;
            int year = proj1 - 1;
            for (int loop = 0; loop < 2; loop++) {

                if (loop % 12 == 0) {
                    year++;
                    i = 0;
                }
                visibleColomns.add("projValue" + (loop + 1));
                header.add(year + " " + MONTHS[i]);
                i++;
            }
        }

        if (frequency.equalsIgnoreCase(QUARTER)) {
           
            int i = 1;
            int year = proj1 - 1;
            for (int loop = 0; loop < 8; loop++) {
                if (loop % 4 == 0) {
                    year++;
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("Q1 " + year);
                }
                if (loop % 4 == 1) {
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("Q2 " + year);
                }
                if (loop % 4 == 2) {
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("Q3 " + year);
                }
                if (loop % 4 == 3) {
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("Q4 " + year);
                }
            }
        }
        if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
            
            int i = 1;
            int year = proj1 - 1;
            for (int loop = 0; loop < 4; loop++) {
                if (loop % 2 == 0) {
                    year++;
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("S1 " + year);
                }
                if (loop % 2 == 1) {
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("S2 " + year);
                }
            }
        }
        if (frequency.equalsIgnoreCase(ANNUAL)) {
            
            int i = 1;
            int year = proj1 - 1;
            for (int loop = 0; loop < 2; loop++) {
                year++;
                visibleColomns.add("projValue" + (loop + 1));
                header.add(String.valueOf(year));
            }

        }

        if (viewType != null && "Descending".equals(viewType)) {
            Collections.reverse(visibleColomns);
            Collections.reverse(header);
            visibleColomns.add(0, "group");
            header.add(0, "Group");
            finalList.add(visibleColomns);
            finalList.add(header);
        } else {
            visibleColomns.add(0, "group");
            header.add(0, "Group");
            finalList.add(visibleColomns);
            finalList.add(header);
        }

        return finalList;
    }
//Elangovan - Contract Sales Allocation

    public List<List> getFreqHistResult(String frequency,
            String history, Object viewType) {
        List<String> visibleColomns = new ArrayList<String>();
        List<String> header = new ArrayList<String>();
        List<List> finalList = new ArrayList<List>();
        getCurrentYear();
        int histNum;
        
        if (viewType != null && "Descending".equals(viewType)) {
            Collections.reverse(visibleColomns);
            Collections.reverse(header);
        }
        visibleColomns.add(0, "brand");
        header.add(0, "Brand");
        visibleColomns.add("overprojValue1");
        header.add("1 Override");
        finalList.add(visibleColomns);
        finalList.add(header);

        return finalList;
    }

    public List<List> getCDDiscountProjection(String frequency,
            String history, Object viewType, String actORproj, String projType, String discType) {
        List<List> finalList = new ArrayList<List>();

        boolean actuals;
        boolean proj;

        String discTypeString = "";

        discTypeString = ("%".equals(discType)) ? "Rate" : "$";

        actuals = ("Actuals".equals(actORproj));
        proj = ("Projection".equals(actORproj));
        if ("Both".equals(actORproj)) {
            actuals = true;
            proj = true;
        }

       

        List<String> visibleColomns = new ArrayList<String>();
        List<String> header = new ArrayList<String>();
        getCurrentYear();
        int histNum;

        //History
        if (history != null && !"null".equals(history)) {
            if (frequency.equalsIgnoreCase(MONTH)) {
               
                history = history.replace(" Months", "");
                histNum = Integer.parseInt(history);
               
                int i = 11;
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {

                    if (loop % 12 == 0) {
                        year--;
                        i = 11;
                    }
                    if (actuals) {
                        visibleColomns.add("histActuals" + (loop + 1));
                    }
                    if (actuals) {
                        header.add(year + " " + MONTHS[i] + " Actuals " + discTypeString);
                    }
                    if (proj) {
                        visibleColomns.add("histProj" + (loop + 1));
                    }
                    if (proj) {
                        header.add(year + " " + MONTHS[i] + " " + discTypeString);
                    }
                    i--;
                }
                Collections.reverse(header);
            }

            if (frequency.equalsIgnoreCase(QUARTER)) {
               
                history = history.replace(" Quarters", "");
                histNum = Integer.parseInt(history);
                
                int i = 1;
                int year = proj1;

                for (int loop = 0; loop < histNum; loop++) {
                    if (loop % 4 == 0) {
                        year--;
                        if (actuals) {
                            visibleColomns.add("histActuals" + (loop + 1));
                        }
                        if (actuals) {
                            header.add("Q4 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add("histProj" + (loop + 1));
                        }
                        if (proj) {
                            header.add("Q4 " + year + " Projected " + discTypeString);
                        }
                    }
                    if (loop % 4 == 1) {
                        if (actuals) {
                            visibleColomns.add("histActuals" + (loop + 1));
                        }
                        if (actuals) {
                            header.add("Q3 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add("histProj" + (loop + 1));
                        }
                        if (proj) {
                            header.add("Q3 " + year + " Projected " + discTypeString);
                        }
                    }
                    if (loop % 4 == 2) {
                        if (actuals) {
                            visibleColomns.add("histActuals" + (loop + 1));
                        }
                        if (actuals) {
                            header.add("Q2 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add("histProj" + (loop + 1));
                        }
                        if (proj) {
                            header.add("Q2 " + year + " Projected " + discTypeString);
                        }
                    }
                    if (loop % 4 == 3) {
                        if (actuals) {
                            visibleColomns.add("histActuals" + (loop + 1));
                        }
                        if (actuals) {
                            header.add("Q1 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add("histProj" + (loop + 1));
                        }
                        if (proj) {
                            header.add("Q1 " + year + " Projected " + discTypeString);
                        }
                    }
                }
                Collections.reverse(header);
            }
            if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
               
                history = history.replace(" Semi-Annual", "");
                histNum = Integer.parseInt(history);
               
                int i = 1;
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {
                    if (loop % 2 == 0) {
                        year--;
                        if (actuals) {
                            visibleColomns.add("histActuals" + (loop + 1));
                        }
                        if (actuals) {
                            header.add("S2 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add("histProj" + (loop + 1));
                        }
                        if (proj) {
                            header.add("S2 " + year + " Projected " + discTypeString);
                        }
                    }
                    if (loop % 2 == 1) {
                        if (actuals) {
                            visibleColomns.add("histActuals" + (loop + 1));
                        }
                        if (actuals) {
                            header.add("S1 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add("histProj" + (loop + 1));
                        }
                        if (proj) {
                            header.add("S1 " + year + " Projected " + discTypeString);
                        }
                    }
                }
                Collections.reverse(header);
            }
            if (frequency.equalsIgnoreCase(ANNUAL)) {
               
                history = history.replace(" Year", "");
                histNum = Integer.parseInt(history);
              
                int i = 1;
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {
                    year--;
                    if (actuals) {
                        visibleColomns.add("histActuals" + (loop + 1));
                    }
                    if (actuals) {
                        header.add(String.valueOf(year) + " Actuals " + discTypeString);
                    }
                    if (proj) {
                        visibleColomns.add("histProj" + (loop + 1));
                    }
                    if (proj) {
                        header.add(String.valueOf(year) + " Projected " + discTypeString);
                    }
                }
                Collections.reverse(header);
            }

        }

        
        if (viewType != null && "Descending".equals(viewType)) {
            Collections.reverse(visibleColomns);
            Collections.reverse(header);
        }
        visibleColomns.add(0, "checkBox");
            header.add(0, "Customer");

        visibleColomns.add(1, "startDate");
        header.add(1, "Start Date");
        if ("%".equals(discType)) {
            visibleColomns.add(2, "discount1");
            header.add(2, "Discount Rate 1");
            visibleColomns.add(3, "discount2");
            header.add(3, "Discount Rate 2");
            visibleColomns.add(4, "total");
            header.add(4, "Total Rate");
        } else {
            visibleColomns.add(2, "discount1");
            header.add(2, "Discount $ 1");
            visibleColomns.add(3, "discount2");
            header.add(3, "Discount $ 2");
            visibleColomns.add(4, "total");
            header.add(4, "Total $");
        }
        finalList.add(visibleColomns);
        finalList.add(header);



        return finalList;

    }
    
    public List getCDDiscountProjectionResult(String frequency, Object viewType)
    {
         List<List> finalList = new ArrayList<List>();
         
         List<String> visibleColomns = new ArrayList<String>();
        List<String> header = new ArrayList<String>();
        getCurrentYear();
         
         if (frequency.equalsIgnoreCase(MONTH)) {
            
            int i = 0;
            int year = proj1 - 1;
            for (int loop = 0; loop < 2; loop++) {

                if (loop % 12 == 0) {
                    year++;
                    i = 0;
                }
                visibleColomns.add("projRate" + (loop + 1));
                header.add(year + " " + MONTHS[i] +" Rate" );
                visibleColomns.add("projAmount" + (loop + 1));
                header.add(year + " " + MONTHS[i] +" Amount" );
                i++;
            }
        }

        if (frequency.equalsIgnoreCase(QUARTER)) {
            
            int i = 1;
            int year = proj1 - 1;
            for (int loop = 0; loop < 8; loop++) {
                if (loop % 4 == 0) {
                    year++;
                    visibleColomns.add("projRate" + (loop + 1));
                    header.add("Q1 " + year +" Rate" );
                    visibleColomns.add("projAmount" + (loop + 1));
                    header.add("Q1 " + year +" Amount");
                }
                if (loop % 4 == 1) {
                    visibleColomns.add("projRate" + (loop + 1));
                    header.add("Q2 " + year +" Rate" );
                    visibleColomns.add("projAmount" + (loop + 1));
                    header.add("Q2 " + year +" Amount");
                }
                if (loop % 4 == 2) {
                    visibleColomns.add("projRate" + (loop + 1));
                    header.add("Q3 " + year +" Rate" );
                    visibleColomns.add("projAmount" + (loop + 1));
                    header.add("Q3 " + year +" Amount");
                }
                if (loop % 4 == 3) {
                    visibleColomns.add("projRate" + (loop + 1));
                    header.add("Q4 " + year +" Rate" );
                    visibleColomns.add("projAmount" + (loop + 1));
                    header.add("Q4 " + year +" Amount");
                }
            }
        }
        if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
           
            int i = 1;
            int year = proj1 - 1;
            for (int loop = 0; loop < 4; loop++) {
                if (loop % 2 == 0) {
                    year++;
                    visibleColomns.add("projRate" + (loop + 1));
                    header.add("S1 " + year +" Rate" );
                    visibleColomns.add("projAmount" + (loop + 1));
                    header.add("S1 " + year +" Amount");
                }
                if (loop % 2 == 1) {
                    visibleColomns.add("projRate" + (loop + 1));
                    header.add("S2 " + year +" Rate" );
                    visibleColomns.add("projAmount" + (loop + 1));
                    header.add("S2 " + year +" Amount");
                }
            }
        }
        
         if (frequency.equalsIgnoreCase(ANNUAL)) {
            
            int i = 1;
            int year = proj1 - 1;
            for (int loop = 0; loop < 2; loop++) {
                year++;
                visibleColomns.add("projRate" + (loop + 1));
                header.add(String.valueOf(year) +" Rate" );
                visibleColomns.add("projAmount" + (loop + 1));
                header.add(String.valueOf(year) +" Amount");
            }

        }
        if (viewType != null && "Descending".equals(viewType)) {
            Collections.reverse(visibleColomns);
            Collections.reverse(header);
        }
        
        visibleColomns.add(0, "group");
        header.add(0, "Customer");
            
        finalList.add(visibleColomns);
        finalList.add(header);
            
         return finalList;
    }
    
     public List<List> getProjectionResultsHeader(String frequency, String viewType) {
        
         List<String> visibleColomns = new ArrayList<String>();
        List<String> header = new ArrayList<String>();
        List<List> finalList = new ArrayList<List>();
        getCurrentYear();
        
         if (frequency.equalsIgnoreCase(MONTH)) {
            
            int i = 0;
            int year = proj1 - 1;
            for (int loop = 0; loop < 2; loop++) {

                if (loop % 12 == 0) {
                    year++;
                    i = 0;
                }
                visibleColomns.add("projValue" + (loop + 1));
                header.add(year + " " + MONTHS[i]);
                i++;
            }
        }

        if (frequency.equalsIgnoreCase(QUARTER)) {
            
            int i = 1;
            int year = proj1 - 1;
            for (int loop = 0; loop < 8; loop++) {
                if (loop % 4 == 0) {
                    year++;
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("Q1 " + year);
                }
                if (loop % 4 == 1) {
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("Q2 " + year);
                }
                if (loop % 4 == 2) {
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("Q3 " + year);
                }
                if (loop % 4 == 3) {
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("Q4 " + year);
                }
            }
        }
        if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
            
            int i = 1;
            int year = proj1 - 1;
            for (int loop = 0; loop < 4; loop++) {
                if (loop % 2 == 0) {
                    year++;
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("S1 " + year);
                }
                if (loop % 2 == 1) {
                    visibleColomns.add("projValue" + (loop + 1));
                    header.add("S2 " + year);
                }
            }
        }
        if (frequency.equalsIgnoreCase(ANNUAL)) {
            
            int i = 1;
            int year = proj1 - 1;
            for (int loop = 0; loop < 2; loop++) {
                year++;
                visibleColomns.add("projValue" + (loop + 1));
                header.add(String.valueOf(year));
            }

        }

        
        if (viewType != null && "Descending".equals(viewType)) {
            Collections.reverse(visibleColomns);
            Collections.reverse(header);
            visibleColomns.add(0, "group");
            header.add(0, "Group");
            finalList.add(visibleColomns);
            finalList.add(header);
        } else {
            visibleColomns.add(0, "group");
            header.add(0, "Group");
            finalList.add(visibleColomns);
            finalList.add(header);
        }

        return finalList;
    }
}