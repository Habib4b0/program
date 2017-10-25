/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.logic;

import com.stpl.app.forecastdashboard.ui.form.CommonLevelDto;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import static com.stpl.app.forecastdashboard.utils.CommonUtils.getMonthForInt;
import com.stpl.app.service.RsModelLocalServiceUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gopinath
 */
public class ChartCommonLogic {

    public List getResults(int projectionId, String frequency, String forecastType) {
        String query = "select sum(FD.GTS_ACTUALS) as GTS_ACTUALS, sum(FD.GTS_PROJECTED) as GTS_PROJECTED, sum(FD.CONTRACT_SALES_ACTUALS) as CONTRACT_SALES_ACTUALS, \n"
                + " sum(FD.CONTRACT_SALES_PROJECTED) as CONTRACT_SALES_PROJECTED, sum(FD.CONTRACT_UNITS_ACTUALS) as CONTRACT_UNITS_ACTUALS, \n"
                + " sum(FD.CONTRACT_UNITS_PROJECTED) as CONTRACT_UNITS_PROJECTED, sum(FD.TOTAL_DISCOUNT_ACTUALS) as TOTAL_DISCOUNT_ACTUALS, sum(FD.TOTAL_DISCOUNT_PROJECTED) as TOTAL_DISCOUNT_PROJECTED, \n"
                + " sum(FD.NET_SALES_ACTUALS) as NET_SALES_ACTUALS, sum(FD.NET_SALES_PROJECTED) as NET_SALES_PROJECTED, \n"
                + " sum(FD.ACTUAL_AMOUNT) as ACTUAL_AMOUNT, sum(FD.PROJECTED_AMOUNT) as PROJECTED_AMOUNT, sum(FD.ACTUAL_RATE) as ACTUAL_RATE, sum(FD.PROJECTED_RATE) as PROJECTED_RATE, \n"
                + " FD.DISCOUNT_TYPE as DISCOUNT_TYPE, P.\"YEAR\" as Years\n";
        if (CommonUtils.MONTHLY.equals(frequency)) {
            query += ", P.\"MONTH\" AS Periods ,sum(FD.COGS_ACTUAL) as COGS_ACTUALS,sum(FD.COGS_PROJECTED) as COGS_PROJECTED,sum(FD.NET_PROFIT_ACTUAL) as NET_PROFIT_ACTUALS,sum(FD.NET_PROFIT_PROJECTED) as NET_PROFIT_PROJECTED ";
        } else if (CommonUtils.SEMI_ANNUAL.equals(frequency)) {
            query += ", P.SEMI_ANNUAL AS Periods ,sum(FD.COGS_ACTUAL) as COGS_ACTUALS,sum(FD.COGS_PROJECTED) as COGS_PROJECTED,sum(FD.NET_PROFIT_ACTUAL) as NET_PROFIT_ACTUALS,sum(FD.NET_PROFIT_PROJECTED) as NET_PROFIT_PROJECTED ";
        } else if (CommonUtils.QUARTERLY.equals(frequency)) {
            query += ", P.QUARTER AS Periods ,sum(FD.COGS_ACTUAL) as COGS_ACTUALS,sum(FD.COGS_PROJECTED) as COGS_PROJECTED,sum(FD.NET_PROFIT_ACTUAL) as NET_PROFIT_ACTUALS,sum(FD.NET_PROFIT_PROJECTED) as NET_PROFIT_PROJECTED ";
        }else{
              query += ", P.\"YEAR\" AS Periods ,sum(FD.COGS_ACTUAL) as COGS_ACTUALS,sum(FD.COGS_PROJECTED) as COGS_PROJECTED,sum(FD.NET_PROFIT_ACTUAL) as NET_PROFIT_ACTUALS,sum(FD.NET_PROFIT_PROJECTED) as NET_PROFIT_PROJECTED ";
        }
        query += " FROM FORECASTING_DASHBOARD FD\n"

                + " JOIN \"PERIOD\" P ON P.PERIOD_SID = FD.PERIOD_SID WHERE FD.PROJECTION_MASTER_SID = "+projectionId+""

                //                + projectionId
  
                + " group by FD.DISCOUNT_TYPE,P.\"YEAR\"";
//         + " group by "+("Non Mandated".equalsIgnoreCase(forecastType)?"FD.DISCOUNT_TYPE,":" ")+"P.\"YEAR\"";
        if (CommonUtils.MONTHLY.equals(frequency)) {
            query += ", P.\"MONTH\" ";
        } else if (CommonUtils.SEMI_ANNUAL.equals(frequency)) {
            query += ", P.SEMI_ANNUAL ";
        } else if (CommonUtils.QUARTERLY.equals(frequency)) {
            query += ", P.QUARTER ";
        }
        if(forecastType.equals("Non Mandated")){
             query += " order by  case when DISCOUNT_TYPE='MEDICAID Mandated' then 1\n" +
              "else 0 end desc, FD.DISCOUNT_TYPE,P.\"YEAR\"";
        }else{
             query += " order by  FD.DISCOUNT_TYPE,P.\"YEAR\"";
        }
       
//        query += " order by  "+("Non Mandated".equalsIgnoreCase(forecastType)?"FD.DISCOUNT_TYPE,":" ")+" P.\"YEAR\"";
        if (CommonUtils.MONTHLY.equals(frequency)) {
            query += ", P.\"MONTH\" ";
        } else if (CommonUtils.SEMI_ANNUAL.equals(frequency)) {
            query += ", P.SEMI_ANNUAL ";
        } else if (CommonUtils.QUARTERLY.equals(frequency)) {
            query += ", P.QUARTER ";
        }
        System.out.println("===chart query================>>>>"+query);
        List<Object[]> list = (List<Object[]>) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        System.out.println("====List size=====>>>>"+list.size());
        return customizeResults(list, frequency, forecastType);
        }
        
    public List customizeResults(List<Object[]> list, String frequency, String forecastType) {
            if ("Mandated".equalsIgnoreCase(forecastType)) {
                return getMandatedDTOList(list, frequency);
            } else if ("Non Mandated".equalsIgnoreCase(forecastType)) {
                return getNonMandatedDTOList(list, frequency);
            } else if ("CFF".equalsIgnoreCase(forecastType)) {
                return getCFFDTOList(list, frequency);
            }
            return Collections.EMPTY_LIST;

        }

    public List getMandatedDTOList(List<Object[]> list, String frequency) {
        CommonLevelDto commonLevelDto1 = new CommonLevelDto();
         List rawList = new ArrayList();
        List<CommonLevelDto> commonFinalList = new ArrayList<CommonLevelDto>();
        List<String> xAxis = new ArrayList<String>();
        CommonLevelDto commonLevelDto2 = new CommonLevelDto();
        CommonLevelDto commonLevelDto3 = new CommonLevelDto();
        CommonLevelDto commonLevelDto4 = new CommonLevelDto();
        CommonLevelDto commonLevelDto5 = new CommonLevelDto();
        CommonLevelDto commonLevelDto6 = new CommonLevelDto();
        CommonLevelDto commonLevelDto7 = new CommonLevelDto();
        CommonLevelDto commonLevelDto8 = new CommonLevelDto();
        CommonLevelDto commonLevelDto9 = new CommonLevelDto();
        CommonLevelDto commonLevelDto10 = new CommonLevelDto();
        CommonLevelDto commonLevelDto11 = new CommonLevelDto();
        CommonLevelDto commonLevelDto12 = new CommonLevelDto();
        CommonLevelDto commonLevelDto13 = new CommonLevelDto();
        CommonLevelDto commonLevelDto14 = new CommonLevelDto();
        CommonLevelDto commonLevelDto15 = new CommonLevelDto();
        CommonLevelDto commonLevelDto16 = new CommonLevelDto();
        CommonLevelDto commonLevelDto17 = new CommonLevelDto();
        CommonLevelDto commonLevelDto18 = new CommonLevelDto();
        CommonLevelDto commonLevelDto19 = new CommonLevelDto();
        CommonLevelDto commonLevelDto20 = new CommonLevelDto();
        CommonLevelDto commonLevelDto21 = new CommonLevelDto();
        CommonLevelDto commonLevelDto22 = new CommonLevelDto();
        commonLevelDto1.setGroup("GTS_SALES_ACTUALS");
        commonLevelDto2.setGroup("GTS_SALES_PROJECTED");
        commonLevelDto3.setGroup("CONTRACT_SALES_ACTUALS");
        commonLevelDto4.setGroup("CONTRACT_SALES_PROJECTED");
        commonLevelDto5.setGroup("CONTRACT_UNITS_ACTUALS");
        commonLevelDto6.setGroup("CONTRACT_UNITS_PROJECTED");
        commonLevelDto7.setGroup("TOTAL_DISCOUNT ACTUALS");
        commonLevelDto8.setGroup("TOTAL_DISCOUNT_PROJECTED");
        commonLevelDto9.setGroup("MANDATED Actual Amount");
        commonLevelDto10.setGroup("MANDATED Projected Amount");
        commonLevelDto11.setGroup("SUPPLEMENTAL Actual Amount");
        commonLevelDto12.setGroup("SUPPLEMENTAL Projected Amount");
        commonLevelDto13.setGroup("MANDATED Actual Rate");
        commonLevelDto14.setGroup("MANDATED Projected Rate");
        commonLevelDto15.setGroup("SUPPLEMENTAL Actual Rate");
        commonLevelDto16.setGroup("SUPPLEMENTAL Projected Rate");
        commonLevelDto17.setGroup("NET_SALES_ACTUALS");
        commonLevelDto18.setGroup("NET_SALES_PROJECTED");
        commonLevelDto19.setGroup("COGS_ACTUAL");
        commonLevelDto20.setGroup("COGS_PROJECTED");
        commonLevelDto21.setGroup("NET_PROFIT_ACTUAL");
        commonLevelDto22.setGroup("NET_PROFIT_PROJECTED");
        for (Object list1 : list) {
            final Object[] obj = (Object[]) list1;
            int i = 4;
            if (!CommonUtils.ANNUAL.equals(frequency)) {
                i = 5;
            }

            String year = String.valueOf(obj[15]);
            if (CommonUtils.SEMI_ANNUAL.equals(frequency)) {
                year = "S" + String.valueOf(obj[16]) + " " + String.valueOf(obj[15]);
            } else if (CommonUtils.QUARTERLY.equals(frequency)) {
                year = "Q" + String.valueOf(obj[16]) + " " + String.valueOf(obj[15]);
            } else if (CommonUtils.MONTHLY.equals(frequency)) {
                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[16]) - 1);
                year = monthName + " " + String.valueOf(obj[15]);
            }
            if (!"null".equals(year)) {
                if(!xAxis.contains(year)){
                    xAxis.add(year);
                }
                commonLevelDto1.addStringProperties(year, String.valueOf(obj[0]));
                commonLevelDto2.addStringProperties(year, String.valueOf(obj[1]));
                commonLevelDto3.addStringProperties(year, String.valueOf(obj[2]));
                commonLevelDto4.addStringProperties(year, String.valueOf(obj[3]));
                commonLevelDto5.addStringProperties(year, String.valueOf(obj[4]));
                commonLevelDto6.addStringProperties(year, String.valueOf(obj[5]));
                commonLevelDto7.addStringProperties(year, String.valueOf(obj[6]));
                commonLevelDto8.addStringProperties(year, String.valueOf(obj[7]));
                commonLevelDto17.addStringProperties(year, String.valueOf(obj[8]));
                commonLevelDto18.addStringProperties(year, String.valueOf(obj[9]));
                if ("MANDATED".equalsIgnoreCase(String.valueOf(obj[14]))) {
                    commonLevelDto9.addStringProperties(year, String.valueOf(obj[10]));
                    commonLevelDto10.addStringProperties(year, String.valueOf(obj[11]));
                    commonLevelDto13.addStringProperties(year, String.valueOf(obj[12]));
                    commonLevelDto14.addStringProperties(year, String.valueOf(obj[13]));
                } else {
                    commonLevelDto11.addStringProperties(year, String.valueOf(obj[10]));
                    commonLevelDto12.addStringProperties(year, String.valueOf(obj[11]));
                    commonLevelDto15.addStringProperties(year, String.valueOf(obj[12]));
                    commonLevelDto16.addStringProperties(year, String.valueOf(obj[13]));
                }
                
                    commonLevelDto19.addStringProperties(year, String.valueOf(obj[17]));
                    commonLevelDto20.addStringProperties(year, String.valueOf(obj[18]));
                    commonLevelDto21.addStringProperties(year, String.valueOf(obj[19]));
                    commonLevelDto22.addStringProperties(year, String.valueOf(obj[20]));
            }

        }
        commonLevelDto1.setxAxis(xAxis);
        commonFinalList.add(commonLevelDto1);
        commonFinalList.add(commonLevelDto2);
        commonFinalList.add(commonLevelDto3);
        commonFinalList.add(commonLevelDto4);
        commonFinalList.add(commonLevelDto5);
        commonFinalList.add(commonLevelDto6);
        commonFinalList.add(commonLevelDto7);
        commonFinalList.add(commonLevelDto8);
        commonFinalList.add(commonLevelDto9);
        commonFinalList.add(commonLevelDto10);
        commonFinalList.add(commonLevelDto11);
        commonFinalList.add(commonLevelDto12);
        commonFinalList.add(commonLevelDto13);
        commonFinalList.add(commonLevelDto14);
        commonFinalList.add(commonLevelDto15);
        commonFinalList.add(commonLevelDto16);
        commonFinalList.add(commonLevelDto17);
        commonFinalList.add(commonLevelDto18);
        commonFinalList.add(commonLevelDto19);
        commonFinalList.add(commonLevelDto20);
        commonFinalList.add(commonLevelDto21);
        commonFinalList.add(commonLevelDto22);
        rawList.add(commonFinalList);
        rawList.add(xAxis);
        return rawList;
    }

    public List getNonMandatedDTOList(List<Object[]> list, String frequency) {
        //NM

        List rawList = new ArrayList();
        List<CommonLevelDto> commonDPRList = new ArrayList<>();
        List<String> xAxis = new ArrayList<>();
        List<CommonLevelDto> commonFinalList = new ArrayList<>();
        CommonLevelDto nmcommonLevelDto1 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto2 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto3 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto4 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto5 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto6 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto7 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto8 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto9 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto10 = new CommonLevelDto();
        
        CommonLevelDto nmcommonLevelDto11 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto12 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto13 = new CommonLevelDto();
        CommonLevelDto nmcommonLevelDto14 = new CommonLevelDto();
        
        
        Set<String> rsName = new HashSet<>();
        nmcommonLevelDto1.setGroup("GTS_SALES_ACTUALS");
        nmcommonLevelDto2.setGroup("GTS_SALES_PROJECTED");
        nmcommonLevelDto3.setGroup("CONTRACT_SALES_ACTUALS");
        nmcommonLevelDto4.setGroup("CONTRACT_SALES_PROJECTED");
        nmcommonLevelDto5.setGroup("CONTRACT_UNITS_ACTUALS");
        nmcommonLevelDto6.setGroup("CONTRACT_UNITS_PROJECTED");
        nmcommonLevelDto7.setGroup("TOTAL_DISCOUNT ACTUALS");
        nmcommonLevelDto8.setGroup("TOTAL_DISCOUNT_PROJECTED");
        nmcommonLevelDto9.setGroup("NET_SALES_ACTUALS");
        nmcommonLevelDto10.setGroup("NET_SALES_PROJECTED");
        nmcommonLevelDto11.setGroup("COGS_ACTUALS");
        nmcommonLevelDto12.setGroup("COGS_PROJECTED");
        nmcommonLevelDto13.setGroup("NET_PROFIT_ACTUALS");
        nmcommonLevelDto14.setGroup("NET_PROFIT_PROJECTED");
        CommonLevelDto commonLevelDto = null;
        for (Object list1 : list) {
         
            final Object[] obj = (Object[]) list1;
            String year = String.valueOf(obj[15]);
            if (CommonUtils.SEMI_ANNUAL.equals(frequency)) {
                year = "S" + String.valueOf(obj[16]) + " " + String.valueOf(obj[15]);
            } else if (CommonUtils.QUARTERLY.equals(frequency)) {
                year = "Q" + String.valueOf(obj[16]) + " " + String.valueOf(obj[15]);
            } else if (CommonUtils.MONTHLY.equals(frequency)) {
                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[16]) - 1);
                year = monthName + " " + String.valueOf(obj[15]);
            }

            if (rsName.add(String.valueOf(obj[14]))) {
                commonLevelDto = new CommonLevelDto();
                commonLevelDto.setGroup(String.valueOf(obj[14]));
                commonDPRList.add(commonLevelDto);
            }
            commonLevelDto.addStringProperties(year + "-Projected Amount", String.valueOf(obj[11]));
            commonLevelDto.addStringProperties(year + "-Projected Rate", String.valueOf(obj[13]));
            commonLevelDto.addStringProperties(year + "-Actual Amount", String.valueOf(obj[10]));
            commonLevelDto.addStringProperties(year + "-Actual Rate", String.valueOf(obj[12]));
            if (rsName.size() == 1) {
//                System.out.println(year+":::!xAxis.contains(year):================>"+!xAxis.contains(year));
               if ( !xAxis.contains(year)) {
                    xAxis.add(year);
                }
                nmcommonLevelDto1.addStringProperties(year, String.valueOf(obj[0]));
                nmcommonLevelDto2.addStringProperties(year, String.valueOf(obj[1]));
                nmcommonLevelDto3.addStringProperties(year, String.valueOf(obj[2]));
                nmcommonLevelDto4.addStringProperties(year, String.valueOf(obj[3]));
                nmcommonLevelDto5.addStringProperties(year, String.valueOf(obj[4]));
                nmcommonLevelDto6.addStringProperties(year, String.valueOf(obj[5]));
                nmcommonLevelDto7.addStringProperties(year, String.valueOf(obj[6]));
                nmcommonLevelDto8.addStringProperties(year, String.valueOf(obj[7]));
                nmcommonLevelDto9.addStringProperties(year, String.valueOf(obj[8]));
                nmcommonLevelDto10.addStringProperties(year, String.valueOf(obj[9]));
                nmcommonLevelDto11.addStringProperties(year, String.valueOf(obj[17]));
                nmcommonLevelDto12.addStringProperties(year, String.valueOf(obj[18]));
                nmcommonLevelDto13.addStringProperties(year, String.valueOf(obj[19]));
                nmcommonLevelDto14.addStringProperties(year, String.valueOf(obj[20]));
                
                
            }

        }
        nmcommonLevelDto1.setxAxis(xAxis);
        nmcommonLevelDto3.setxAxis(xAxis);// did here

//        commonFinalList.addAll(commonDPRList);
        commonFinalList.add(nmcommonLevelDto1);
        commonFinalList.add(nmcommonLevelDto2);
        commonFinalList.add(nmcommonLevelDto3);
        commonFinalList.add(nmcommonLevelDto4);
        commonFinalList.add(nmcommonLevelDto5);
        commonFinalList.add(nmcommonLevelDto6);
        commonFinalList.add(nmcommonLevelDto7);
        commonFinalList.add(nmcommonLevelDto8);
        commonFinalList.add(nmcommonLevelDto9);
        commonFinalList.add(nmcommonLevelDto10);
        commonFinalList.add(nmcommonLevelDto11);
        commonFinalList.add(nmcommonLevelDto12);
        commonFinalList.add(nmcommonLevelDto13);
        commonFinalList.add(nmcommonLevelDto14);
        rawList.add(commonDPRList);
        rawList.add(commonFinalList);
        rawList.add(xAxis);
//        for(CommonLevelDto dtoList:commonDPRList){
//            System.out.println("DTOLIST:==============>"+dtoList.getGroup());
//        }
        
        
        return rawList;
    }

    public List getCFFDTOList(List<Object[]> list, String frequency) {

        List commonDtoList = new ArrayList<CommonLevelDto>();
        List<String> xAxis = new ArrayList<String>();
        List rawList = new ArrayList();
        CommonLevelDto commonLevelDto1 = new CommonLevelDto();
        CommonLevelDto commonLevelDto2 = new CommonLevelDto();
        CommonLevelDto commonLevelDto3 = new CommonLevelDto();
        CommonLevelDto commonLevelDto4 = new CommonLevelDto();
        CommonLevelDto commonLevelDto5 = new CommonLevelDto();
        CommonLevelDto commonLevelDto6 = new CommonLevelDto();
        CommonLevelDto commonLevelDto7 = new CommonLevelDto();
        CommonLevelDto commonLevelDto8 = new CommonLevelDto();
        CommonLevelDto commonLevelDto9 = new CommonLevelDto();
        CommonLevelDto commonLevelDto10 = new CommonLevelDto();
        

        commonLevelDto1.setGroup("EX_FACTORY_SALES_ACTUALS");
        commonLevelDto2.setGroup("EX_FACTORY_SALES_PROJECTED");
        commonLevelDto3.setGroup("CONTRACT_SALES_ACTUALS");
        commonLevelDto4.setGroup("CONTRACT_SALES_PROJECTED");
        commonLevelDto5.setGroup("CONTRACT_UNITS_ACTUALS");
        commonLevelDto6.setGroup("CONTRACT_UNITS_PROJECTED");
        commonLevelDto7.setGroup("COGS_ACTUALS");
        commonLevelDto8.setGroup("COGS_PROJECTED");
        commonLevelDto9.setGroup("NET_PROFIT_ACTUALS");
        commonLevelDto10.setGroup("NET_PROFIT_PROJECTED");
        
        
        for (Object list1 : list) {
            final Object[] obj = (Object[]) list1;

            String year = String.valueOf(obj[15]);
            if (CommonUtils.SEMI_ANNUAL.equals(frequency)) {
                year = "S" + String.valueOf(obj[16]) + " " + String.valueOf(obj[15]);
            } else if (CommonUtils.QUARTERLY.equals(frequency)) {
                year = "Q" + String.valueOf(obj[16]) + " " + String.valueOf(obj[15]);
            } else if (CommonUtils.MONTHLY.equals(frequency)) {
                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[16]) - 1);
                year = monthName + " " + String.valueOf(obj[15]);
            }

            if (!"null".equals(year)) {
                xAxis.add(year);

                commonLevelDto1.addStringProperties(year, String.valueOf(obj[0]));
                commonLevelDto2.addStringProperties(year, String.valueOf(obj[1]));
                commonLevelDto3.addStringProperties(year, String.valueOf(obj[2]));
                commonLevelDto4.addStringProperties(year, String.valueOf(obj[3]));
                commonLevelDto5.addStringProperties(year, String.valueOf(obj[4]));
                commonLevelDto6.addStringProperties(year, String.valueOf(obj[5]));
                commonLevelDto7.addStringProperties(year, String.valueOf(obj[17]));
                commonLevelDto8.addStringProperties(year, String.valueOf(obj[18]));
                commonLevelDto9.addStringProperties(year, String.valueOf(obj[19]));
                commonLevelDto10.addStringProperties(year, String.valueOf(obj[20]));

            }
        }
        commonLevelDto1.setxAxis(xAxis);
        commonDtoList.add(commonLevelDto1);
        commonDtoList.add(commonLevelDto2);
        commonDtoList.add(commonLevelDto3);
        commonDtoList.add(commonLevelDto4);
        commonDtoList.add(commonLevelDto5);
        commonDtoList.add(commonLevelDto6);
        commonDtoList.add(commonLevelDto7);
        commonDtoList.add(commonLevelDto8);
        commonDtoList.add(commonLevelDto9);
        commonDtoList.add(commonLevelDto10);
        rawList.add(commonDtoList);
        rawList.add(xAxis);
        return rawList;
    }

}
