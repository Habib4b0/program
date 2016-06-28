package com.stpl.app.forecastdashboard.logic;

import com.stpl.app.forecastdashboard.dao.ForecastDashboardDAO;
import com.stpl.app.forecastdashboard.dao.impl.ForecastDashboardDAOImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
    public List getActualsData(int year) {
        LOGGER.info("Entering MS getActualsData");
        List dataList = new ArrayList();
        List<Object[]> resultList = dao.getResultList(getActualsQuery(year));
        List<String> categoryNamesList = new ArrayList<String>();
        List<BigDecimal> valuesList = new ArrayList<BigDecimal>();

        for (Object[] obj : resultList) {
            categoryNamesList.add(String.valueOf(obj[0]));
            valuesList.add(new BigDecimal(String.valueOf(obj[1])));
        }
        dataList.add(0, categoryNamesList.toArray(new String[0]));
        dataList.add(1, valuesList.toArray(new BigDecimal[0]));
        LOGGER.info("Ending MS getActualsData");
        return dataList;
    }

    /**
     * Query used to retrieve the actuals data
     * @return 
     */
    private String getActualsQuery(int year) {
        LOGGER.info("Entering MS getActualsQuery");
        String query = "";

        query = "SELECT MT.DESCRIPTION, SUM(AM.AMOUNT) from \n"
                + " dbo.ACTUALS_MASTER AM JOIN dbo.CONTRACT_MASTER CM ON AM.CONTRACT_ID=CM.CONTRACT_ID\n"
                + " JOIN dbo.HELPER_TABLE MT ON CM.CONTRACT_TYPE=MT.HELPER_TABLE_SID \n";
                if(year!=0){
                query+= " WHERE YEAR(AM.ACCRUAL_ACTUAL_START_DATE)="+year+" AND YEAR(AM.ACCRUAL_ACTUAL_END_DATE)="+year+" \n";
                }
                query+= " GROUP BY MT.DESCRIPTION";

        LOGGER.info("Entering MS getActualsQuery");
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
}
