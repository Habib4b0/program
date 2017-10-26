package com.stpl.app.forecastdashboard.logic;

import com.stpl.app.forecastdashboard.dao.ForecastDashboardDAO;
import com.stpl.app.forecastdashboard.dao.impl.ForecastDashboardDAOImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class MarketShareLogic {

    /* The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(MarketShareLogic.class);

    /**
     * The Dashboard dao.
     */
    private ForecastDashboardDAO dao = new ForecastDashboardDAOImpl();

    /**
     * Logic to get the actuals data for the specified year
     * @return 
     */
    public List getActualsData(int year,String chartType) {
        LOGGER.info("Entering MS getActualsData");
        List dataList = new ArrayList();
        List<Object[]> resultList = dao.getResultList(getActualsQuery(year,chartType));
          if(chartType.equals("Pie Chart")){
        List<String> categoryNamesList = new ArrayList<String>();
        List<BigDecimal> valuesList = new ArrayList<BigDecimal>();

        for (Object[] obj : resultList) {
            categoryNamesList.add(String.valueOf(obj[0]));
            valuesList.add(new BigDecimal(String.valueOf(obj[1])));
        }
        dataList.add(0, categoryNamesList.toArray(new String[0]));
        dataList.add(1, valuesList.toArray(new BigDecimal[0]));
          }else{
            dataList=resultList;  
          }
        LOGGER.info("Ending MS getActualsData");
        return dataList;
    }

    /**
     * Query used to retrieve the actuals data
     * @return 
     */
    private String getActualsQuery(int year,String chartType) {
        LOGGER.info("Entering MS getActualsQuery");
        String query = "";
        if(chartType.equals("Pie Chart")){
        query = "SELECT MT.DESCRIPTION, SUM(AM.AMOUNT) from \n"
                + " dbo.ACTUALS_MASTER AM JOIN dbo.CONTRACT_MASTER CM ON AM.CONTRACT_ID=CM.CONTRACT_ID\n"
                + " JOIN dbo.HELPER_TABLE MT ON CM.CONTRACT_TYPE=MT.HELPER_TABLE_SID \n";
          if(year!=0){
                query+= " WHERE YEAR(AM.ACCRUAL_ACTUAL_START_DATE) ="+year+" AND YEAR(AM.ACCRUAL_ACTUAL_END_DATE)="+year+" \n";
                }
                query+= " GROUP BY MT.DESCRIPTION";

        }else{
            query = "IF Object_id('TEMPDB..#CONTRACT_YEAR_AMOUNT') IS NOT NULL\n"
                    + "  TRUNCATE TABLE #CONTRACT_YEAR_AMOUNT\n"
                    + "ELSE\n"
                    + "  CREATE TABLE #CONTRACT_YEAR_AMOUNT\n"
                    + "    (\n"
                    + "       [CONTRACT] VARCHAR(100),\n"
                    + "       AMOUNT     NUMERIC(22, 6),\n"
                    + "       [YEAR]     INT\n"
                    + "    )\n"
                    + "\n"
                    + "DECLARE @MIN_YEAR INT="+getYearList().get(0)+",\n"
                    + "        @MAX_YEAR INT="+getYearList().get(getYearList().size()-1)+",\n"
                    + "        @CONTRACT NVARCHAR(MAX)=( '"+getContractList() +"' )\n"
                    + "\n"
                    + "INSERT INTO #CONTRACT_YEAR_AMOUNT\n"
                    + "            ([CONTRACT],\n"
                    + "             [YEAR])\n"
                    + "SELECT DISTINCT MT.DESCRIPTION AS CONTRACT,\n"
                    + "                P.YEAR\n"
                    + "FROM   Udf_splitstring(@CONTRACT, ',')F\n"
                    + "       INNER JOIN HELPER_TABLE MT\n"
                    + "               ON F.TOKEN = MT.DESCRIPTION\n"
                    + "       CROSS JOIN PERIOD P\n"
                    + "WHERE  P.YEAR BETWEEN @MIN_YEAR AND @MAX_YEAR\n"
                    + "\n"
                    + "UPDATE CYA\n"
                    + "SET    CYA.AMOUNT = A.AMOUNT\n"
                    + "FROM   #CONTRACT_YEAR_AMOUNT CYA\n"
                    + "       INNER JOIN (SELECT MT.DESCRIPTION                  AS CONTRACT,\n"
                    + "                          Sum(AM.AMOUNT)                  AMOUNT,\n"
                    + "                          Year(ACCRUAL_ACTUAL_START_DATE) AS YEAR\n"
                    + "                   FROM   dbo.ACTUALS_MASTER AM\n"
                    + "                          JOIN dbo.CONTRACT_MASTER CM\n"
                    + "                            ON AM.CONTRACT_ID = CM.CONTRACT_ID\n"
                    + "                          JOIN dbo.HELPER_TABLE MT\n"
                    + "                            ON CM.CONTRACT_TYPE = MT.HELPER_TABLE_SID AND LIST_NAME='CONTRACT_TYPE'\n"
                    + "                   GROUP  BY MT.DESCRIPTION,\n"
                    + "                             Year(ACCRUAL_ACTUAL_START_DATE))A\n"
                    + "               ON CYA.CONTRACT = A.CONTRACT\n"
                    + "                  AND CYA.YEAR = A.YEAR\n"
                    + "\n"
                    + "SELECT CONTRACT,\n"
                    + "       Isnull(AMOUNT, 0) AMOUNT,\n"
                    + "       YEAR\n"
                    + "FROM   #CONTRACT_YEAR_AMOUNT\n"
                    + "ORDER  BY CONTRACT,\n"
                    + "          Year ";
        }
        LOGGER.info("Ending MS getActualsQuery");
        return query;
    }
    
    public List getYearList(){
        LOGGER.info("Entering MS getActualsData");
        List<Object[]> resultList = dao.getResultList(getYearQuery());
        LOGGER.info("Ending MS getActualsData");
        return resultList;
    }
    private String getYearQuery(){
        LOGGER.info("Entering MS getYearQuery");
        String query = "";

        query = "SELECT DISTINCT YEAR(ACCRUAL_ACTUAL_START_DATE) AS START_YEAR FROM dbo.ACTUALS_MASTER ORDER BY START_YEAR";

        LOGGER.info("Entering MS getYearQuery");
        return query;
    }
    
    private String getContractName(){
        LOGGER.info("Entering MS getContractName");
        String query = "";

        query = "SELECT MT.DESCRIPTION\n"
                + " from ACTUALS_MASTER AM JOIN CONTRACT_MASTER CM ON AM.CONTRACT_ID=CM.CONTRACT_ID\n"
                + "  JOIN dbo.HELPER_TABLE MT ON CM.CONTRACT_TYPE=MT.HELPER_TABLE_SID\n"
                + "  GROUP BY MT.DESCRIPTION\n"
                + "ORDER BY MT.DESCRIPTION";

        LOGGER.info("Entering MS getContractName");
        return query;
    }
    public String getContractList(){
        LOGGER.info("Entering MS getActualsData");
        String name=StringUtils.EMPTY;
        List<Object[]> resultList = dao.getResultList(getContractName());
        for (int i = 0; i < resultList.size(); i++) {
            if(i==0){
                name=String.valueOf(resultList.get(i));
            }else{
                 name+=", "+String.valueOf(resultList.get(i));
            }
        }
        
        LOGGER.info("Ending MS getActualsData");
        return name;
    }
}
