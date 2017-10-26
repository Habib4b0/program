/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import com.stpl.ifs.ui.util.NumericConstants;
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
    public static String Projected_sales = "Projections";
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
    public static final String IMAGE_DISABLED = "Image-Disabled";
    public static final String IMAGE_ENABLED = "Image-Enabled";
    public static final String PAGEDTABLE_LABEL = "pagedtable-label";
    public static final String PAGEDTABLE_BUTTON = "pagedtable-button";
    public static final String RETURNS = "Returns";
    public static final String TWO_ONE_SEVEN_PX = "217px";
    public static final String DISPLAY_VALUE = "displayValue";
    public static final String LEVEL = "Level";
    public static final String FILTERTABLE = "filtertable";
    public static final String MODE = "Mode: ";
    public static final String LABEL_RESULT_ALIGN = "labelresultalign";
    public static final String HORIZONTAL = "horizontal";
    public static final String PRIVATE_VIEWS = "Private Views: ";
    public static final String ONE_ZERO_ZERO_PX = "100px";
    public static final String SEARCH_TEXT = "searchText";
    public static final String COMPANY = "Company: ";
    public static final String PROJECTION_NAME = "Projection Name: ";
    public static final String PUBLIC_VIEWS = "Public Views: ";
    public static final String BUSINESS_UNIT = "Business Unit: ";
    public static final String DESCRIPTION = "Description: ";
    public static final String REBATE_SCHEDULE_ID = "rebateScheduleId";
    public static final String ITEM_NO = "itemNo";
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_NO_HEADER = "item No";
    public static final String ITEM_NAME_HEADER = "item Name";
    public static final String ITEM_TYPE = "itemType";
    public static final String CHECK_BOX = "checkbox";
    public static final String GROUP = "group";
    public static final String GROUP_CAPS = "Group";
    public static final String RATE = " Rate";
    public static final String HIST_ACTUALS = "histActuals";
    public static final String PROJ_AMOUNT = "projAmount";
    public static final String HIST_PROJ = "histProj";
    public static final String HIST_VALUE = "histValue";
    public static final String PROJ_RATE = "projRate";
    public static final String PROJ_VALUE = "projValue";
    public static final String AMOUNT = " Amount";
    public static final String DESCENDING = "Descending";
    public static final String PROJECTED = " Projected ";
    public static final String ACTUALS = " Actuals ";
    public static final String THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME = "The size of columns and headers must same";

   public static class NonMantaded {

        public static final Object[] SALES_PROJ_RESULTS_COLUMNS = new Object[]{GROUP};
        public static final String[] SALES_PROJ_RESULTS__HEADER = new String[]{GROUP_CAPS};
    }

   /**
    * Initializing the current year value to past 3 histories and 3 projections 
    */
    public void getCurrentYear() {
        Calendar now = Calendar.getInstance();
        currentYear = now.get(Calendar.YEAR);
        history1 = currentYear - NumericConstants.THREE;
        history2 = currentYear - NumericConstants.TWO;
        history3 = currentYear - 1;
        proj1 = currentYear + 1;
        proj2 = currentYear + NumericConstants.TWO;
        proj3 = currentYear + NumericConstants.THREE;

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
            } else if (actualOrProj.equalsIgnoreCase(Actual_sales)) {
                actual = true;
                projection = false;
            } else if (actualOrProj.equalsIgnoreCase(Projected_sales)) {
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
                
                int i = NumericConstants.ELEVEN;
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {

                    if (loop % NumericConstants.TWELVE == 0) {
                        year--;
                        i = NumericConstants.ELEVEN;
                    }
                    visibleColomns.add(HIST_VALUE + (loop + 1));
                    header.add(year + " " + MONTHS[i]);
                    i--;
                }
                Collections.reverse(header);
            }

            if (frequency.equalsIgnoreCase(QUARTER)) {
               
                history = history.replace(" Quarters", "");
                histNum = Integer.parseInt(history);
              
                int year = proj1;

                for (int loop = 0; loop < histNum; loop++) {
                    if (loop % NumericConstants.FOUR == 0) {
                        year--;
                        visibleColomns.add(HIST_VALUE + (loop + 1));
                        header.add("Q4 " + year);
                    }
                    if (loop % NumericConstants.FOUR == 1) {
                        visibleColomns.add(HIST_VALUE + (loop + 1));
                        header.add("Q3 " + year);
                    }
                    if (loop % NumericConstants.FOUR == NumericConstants.TWO) {
                        visibleColomns.add(HIST_VALUE + (loop + 1));
                        header.add("Q2 " + year);
                    }
                    if (loop % NumericConstants.FOUR == NumericConstants.THREE) {
                        visibleColomns.add(HIST_VALUE + (loop + 1));
                        header.add("Q1 " + year);
                    }
                }
                Collections.reverse(header);
            }
            if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
                
                history = history.replace(" Semi-Annual", "");
                histNum = Integer.parseInt(history);
               
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {
                    if (loop % NumericConstants.TWO == 1) {
                        year--;
                        visibleColomns.add(HIST_VALUE + (loop + 1));
                        header.add("S2 " + year);
                    }
                    if (loop % NumericConstants.TWO == 0) {
                        visibleColomns.add(HIST_VALUE + (loop + 1));
                        header.add("S1 " + year);
                    }
                }
                Collections.reverse(header);
            }
            if (frequency.equalsIgnoreCase(ANNUAL)) {
               
                history = history.replace(" Year", "");
                histNum = Integer.parseInt(history);
               
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {
                    year--;
                    visibleColomns.add(HIST_VALUE + (loop + 1));
                    header.add(String.valueOf(year));
                }
                Collections.reverse(header);
            }

        }
        //Projected
        if (frequency.equalsIgnoreCase(MONTH)) {
            
            int i = 0;
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.TWO; loop++) {

                if (loop % NumericConstants.TWELVE == 0) {
                    year++;
                    i = 0;
                }
                visibleColomns.add(PROJ_VALUE + (loop + 1));
                header.add(year + " " + MONTHS[i]);
                i++;
            }
        }

        if (frequency.equalsIgnoreCase(QUARTER)) {
           
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.EIGHT; loop++) {
                if (loop % NumericConstants.FOUR == 0) {
                    year++;
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("Q1 " + year);
                }
                if (loop % NumericConstants.FOUR == 1) {
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("Q2 " + year);
                }
                if (loop % NumericConstants.FOUR == NumericConstants.TWO) {
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("Q3 " + year);
                }
                if (loop % NumericConstants.FOUR == NumericConstants.THREE) {
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("Q4 " + year);
                }
            }
        }
        if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
            
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.FOUR; loop++) {
                if (loop % NumericConstants.TWO == 0) {
                    year++;
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("S1 " + year);
                }
                if (loop % NumericConstants.TWO == 1) {
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("S2 " + year);
                }
            }
        }
        if (frequency.equalsIgnoreCase(ANNUAL)) {
            
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.TWO; loop++) {
                year++;
                visibleColomns.add(PROJ_VALUE + (loop + 1));
                header.add(String.valueOf(year));
            }

        }

        if (viewType != null && DESCENDING.equals(viewType)) {
            Collections.reverse(visibleColomns);
            Collections.reverse(header);
            visibleColomns.add(0, GROUP);
            header.add(0, GROUP_CAPS);
            finalList.add(visibleColomns);
            finalList.add(header);
        } else {
            visibleColomns.add(0, GROUP);
            header.add(0, GROUP_CAPS);
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
        
        if (viewType != null && DESCENDING.equals(viewType)) {
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

        discTypeString = "%".equals(discType) ? "Rate" : "$";

        actuals = Actual_sales.equals(actORproj);
        proj = "Projection".equals(actORproj);
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
               
                int i = NumericConstants.ELEVEN;
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {

                    if (loop % NumericConstants.TWELVE == 0) {
                        year--;
                        i = NumericConstants.ELEVEN;
                    }
                    if (actuals) {
                        visibleColomns.add(HIST_ACTUALS + (loop + 1));
                    }
                    if (actuals) {
                        header.add(year + " " + MONTHS[i] + " Actuals " + discTypeString);
                    }
                    if (proj) {
                        visibleColomns.add(HIST_PROJ + (loop + 1));
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
                
                int year = proj1;

                for (int loop = 0; loop < histNum; loop++) {
                    if (loop % NumericConstants.FOUR == 0) {
                        year--;
                        if (actuals) {
                            visibleColomns.add(HIST_ACTUALS + (loop + 1));
                        }
                        if (actuals) {
                            header.add("Q4 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add(HIST_PROJ + (loop + 1));
                        }
                        if (proj) {
                            header.add("Q4 " + year + PROJECTED + discTypeString);
                        }
                    }
                    if (loop % NumericConstants.FOUR == 1) {
                        if (actuals) {
                            visibleColomns.add(HIST_ACTUALS + (loop + 1));
                        }
                        if (actuals) {
                            header.add("Q3 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add(HIST_PROJ + (loop + 1));
                        }
                        if (proj) {
                            header.add("Q3 " + year + PROJECTED + discTypeString);
                        }
                    }
                    if (loop % NumericConstants.FOUR == NumericConstants.TWO) {
                        if (actuals) {
                            visibleColomns.add(HIST_ACTUALS + (loop + 1));
                        }
                        if (actuals) {
                            header.add("Q2 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add(HIST_PROJ + (loop + 1));
                        }
                        if (proj) {
                            header.add("Q2 " + year + PROJECTED + discTypeString);
                        }
                    }
                    if (loop % NumericConstants.FOUR == NumericConstants.THREE) {
                        if (actuals) {
                            visibleColomns.add(HIST_ACTUALS + (loop + 1));
                        }
                        if (actuals) {
                            header.add("Q1 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add(HIST_PROJ + (loop + 1));
                        }
                        if (proj) {
                            header.add("Q1 " + year + PROJECTED + discTypeString);
                        }
                    }
                }
                Collections.reverse(header);
            }
            if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
               
                history = history.replace(" Semi-Annual", "");
                histNum = Integer.parseInt(history);
               
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {
                    if (loop % NumericConstants.TWO == 0) {
                        year--;
                        if (actuals) {
                            visibleColomns.add(HIST_ACTUALS + (loop + 1));
                        }
                        if (actuals) {
                            header.add("S2 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add(HIST_PROJ + (loop + 1));
                        }
                        if (proj) {
                            header.add("S2 " + year + PROJECTED + discTypeString);
                        }
                    }
                    if (loop % NumericConstants.TWO == 1) {
                        if (actuals) {
                            visibleColomns.add(HIST_ACTUALS + (loop + 1));
                        }
                        if (actuals) {
                            header.add("S1 " + year + " Actuals " + discTypeString);
                        }
                        if (proj) {
                            visibleColomns.add(HIST_PROJ + (loop + 1));
                        }
                        if (proj) {
                            header.add("S1 " + year + PROJECTED + discTypeString);
                        }
                    }
                }
                Collections.reverse(header);
            }
            if (frequency.equalsIgnoreCase(ANNUAL)) {
               
                history = history.replace(" Year", "");
                histNum = Integer.parseInt(history);
              
                int year = proj1;
                for (int loop = 0; loop < histNum; loop++) {
                    year--;
                    if (actuals) {
                        visibleColomns.add(HIST_ACTUALS + (loop + 1));
                    }
                    if (actuals) {
                        header.add(String.valueOf(year) + " Actuals " + discTypeString);
                    }
                    if (proj) {
                        visibleColomns.add(HIST_PROJ + (loop + 1));
                    }
                    if (proj) {
                        header.add(String.valueOf(year) + PROJECTED + discTypeString);
                    }
                }
                Collections.reverse(header);
            }

        }

        
        if (viewType != null && DESCENDING.equals(viewType)) {
            Collections.reverse(visibleColomns);
            Collections.reverse(header);
        }
        visibleColomns.add(0, "checkBox");
            header.add(0, "Customer");

        visibleColomns.add(1, "startDate");
        header.add(1, "Start Date");
        if ("%".equals(discType)) {
            visibleColomns.add(NumericConstants.TWO, "discount1");
            header.add(NumericConstants.TWO, "Discount Rate 1");
            visibleColomns.add(NumericConstants.THREE, "discount2");
            header.add(NumericConstants.THREE, "Discount Rate 2");
            visibleColomns.add(NumericConstants.FOUR, "total");
            header.add(NumericConstants.FOUR, "Total Rate");
        } else {
            visibleColomns.add(NumericConstants.TWO, "discount1");
            header.add(NumericConstants.TWO, "Discount $ 1");
            visibleColomns.add(NumericConstants.THREE, "discount2");
            header.add(NumericConstants.THREE, "Discount $ 2");
            visibleColomns.add(NumericConstants.FOUR, "total");
            header.add(NumericConstants.FOUR, "Total $");
        }
        finalList.add(visibleColomns);
        finalList.add(header);



        return finalList;

    }
    
    public List getCDDiscountProjectionResult(String frequency, Object viewType){
         List<List> finalList = new ArrayList<List>();
         
         List<String> visibleColomns = new ArrayList<String>();
        List<String> header = new ArrayList<String>();
        getCurrentYear();
         
         if (frequency.equalsIgnoreCase(MONTH)) {
            
            int i = 0;
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.TWO; loop++) {

                if (loop % NumericConstants.TWELVE == 0) {
                    year++;
                    i = 0;
                }
                visibleColomns.add(PROJ_RATE + (loop + 1));
                header.add(year + " " + MONTHS[i] +RATE );
                visibleColomns.add(PROJ_AMOUNT + (loop + 1));
                header.add(year + " " + MONTHS[i] +AMOUNT );
                i++;
            }
        }

        if (frequency.equalsIgnoreCase(QUARTER)) {
            
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.EIGHT; loop++) {
                if (loop % NumericConstants.FOUR == 0) {
                    year++;
                    visibleColomns.add(PROJ_RATE + (loop + 1));
                    header.add("Q1 " + year +RATE );
                    visibleColomns.add(PROJ_AMOUNT + (loop + 1));
                    header.add("Q1 " + year +AMOUNT);
                }
                if (loop % NumericConstants.FOUR == 1) {
                    visibleColomns.add(PROJ_RATE + (loop + 1));
                    header.add("Q2 " + year +RATE );
                    visibleColomns.add(PROJ_AMOUNT + (loop + 1));
                    header.add("Q2 " + year +AMOUNT);
                }
                if (loop % NumericConstants.FOUR == NumericConstants.TWO) {
                    visibleColomns.add(PROJ_RATE + (loop + 1));
                    header.add("Q3 " + year +RATE );
                    visibleColomns.add(PROJ_AMOUNT + (loop + 1));
                    header.add("Q3 " + year +AMOUNT);
                }
                if (loop % NumericConstants.FOUR == NumericConstants.THREE) {
                    visibleColomns.add(PROJ_RATE + (loop + 1));
                    header.add("Q4 " + year +RATE );
                    visibleColomns.add(PROJ_AMOUNT + (loop + 1));
                    header.add("Q4 " + year +AMOUNT);
                }
            }
        }
        if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
           
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.FOUR; loop++) {
                if (loop % NumericConstants.TWO == 0) {
                    year++;
                    visibleColomns.add(PROJ_RATE + (loop + 1));
                    header.add("S1 " + year +RATE );
                    visibleColomns.add(PROJ_AMOUNT + (loop + 1));
                    header.add("S1 " + year +AMOUNT);
                }
                if (loop % NumericConstants.TWO == 1) {
                    visibleColomns.add(PROJ_RATE + (loop + 1));
                    header.add("S2 " + year +RATE );
                    visibleColomns.add(PROJ_AMOUNT + (loop + 1));
                    header.add("S2 " + year +AMOUNT);
                }
            }
        }
        
         if (frequency.equalsIgnoreCase(ANNUAL)) {
            
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.TWO; loop++) {
                year++;
                visibleColomns.add(PROJ_RATE + (loop + 1));
                header.add(String.valueOf(year) +RATE );
                visibleColomns.add(PROJ_AMOUNT + (loop + 1));
                header.add(String.valueOf(year) +AMOUNT);
            }

        }
        if (viewType != null && DESCENDING.equals(viewType)) {
            Collections.reverse(visibleColomns);
            Collections.reverse(header);
        }
        
        visibleColomns.add(0, GROUP);
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
            for (int loop = 0; loop < NumericConstants.TWO; loop++) {

                if (loop % NumericConstants.TWELVE == 0) {
                    year++;
                    i = 0;
                }
                visibleColomns.add(PROJ_VALUE + (loop + 1));
                header.add(year + " " + MONTHS[i]);
                i++;
            }
        }

        if (frequency.equalsIgnoreCase(QUARTER)) {
            
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.EIGHT; loop++) {
                if (loop % NumericConstants.FOUR == 0) {
                    year++;
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("Q1 " + year);
                }
                if (loop % NumericConstants.FOUR == 1) {
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("Q2 " + year);
                }
                if (loop % NumericConstants.FOUR == NumericConstants.TWO) {
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("Q3 " + year);
                }
                if (loop % NumericConstants.FOUR == NumericConstants.THREE) {
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("Q4 " + year);
                }
            }
        }
        if (frequency.equalsIgnoreCase(SEMI_ANNUAL)) {
            
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.FOUR; loop++) {
                if (loop % NumericConstants.TWO == 0) {
                    year++;
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("S1 " + year);
                }
                if (loop % NumericConstants.TWO == 1) {
                    visibleColomns.add(PROJ_VALUE + (loop + 1));
                    header.add("S2 " + year);
                }
            }
        }
        if (frequency.equalsIgnoreCase(ANNUAL)) {
            
            int year = proj1 - 1;
            for (int loop = 0; loop < NumericConstants.TWO; loop++) {
                year++;
                visibleColomns.add(PROJ_VALUE + (loop + 1));
                header.add(String.valueOf(year));
            }

        }

        
        if (viewType != null && DESCENDING.equals(viewType)) {
            Collections.reverse(visibleColomns);
            Collections.reverse(header);
            visibleColomns.add(0, GROUP);
            header.add(0, GROUP_CAPS);
            finalList.add(visibleColomns);
            finalList.add(header);
        } else {
            visibleColomns.add(0, GROUP);
            header.add(0, GROUP_CAPS);
            finalList.add(visibleColomns);
            finalList.add(header);
        }

        return finalList;
    }
}