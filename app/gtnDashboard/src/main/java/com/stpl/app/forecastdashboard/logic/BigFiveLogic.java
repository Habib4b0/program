/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.logic;


import com.stpl.app.forecastdashboard.dao.ForecastDashboardDAO;
import com.stpl.app.forecastdashboard.dao.impl.ForecastDashboardDAOImpl;
import com.stpl.app.forecastdashboard.dto.BigFiveDTO;
import static com.stpl.app.forecastdashboard.utils.CommonUtils.formatDecimalPlaces;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class BigFiveLogic {

    private static final Logger LOGGER = Logger.getLogger(BigFiveLogic.class);
    ForecastDashboardDAO dao = new ForecastDashboardDAOImpl();
    String Cquery = "SELECT DISTINCT TOP @Value \n"
            + "                       cm.COMPANY_NAME,\n"
            + "                       Sum(AM.sales_amount) / 1000000 AS sales_amount\n"
            + "FROM   ACTUALS_MASTER AS AM\n"
            + "       JOIN COMPANY_MASTER AS CM\n"
            + "         ON CM.COMPANY_MASTER_SID = AM.COMPANY_MASTER_SID\n"
            + "GROUP  BY COMPANY_NAME\n"
            + "ORDER  BY Sum(am.sales_amount) / 1000000 DESC ";
    String Pquery = "SELECT DISTINCT TOP @Value cm.ITEM_NAME,\n"
            + "                      Sum(AM.sales_amount) / 1000000 AS sales_amount\n"
            + "FROM   ACTUALS_MASTER AS AM\n"
            + "       JOIN item_master AS CM\n"
            + "         ON CM.ITEM_MASTER_SID = AM.ITEM_MASTER_SID\n"
            + "GROUP  BY ITEM_NAME\n"
            + "ORDER  BY Sum(am.sales_amount) / 1000000 DESC";

    public List<BigFiveDTO> getResult(boolean isProduct,String valueDDLB) {
        List list = new ArrayList();
        String queryName;
        if (isProduct) {
//            queryName = "BigFive-Product";
            queryName = Pquery;
        } else {
//            queryName = "BigFive-Customer";
            queryName = Cquery;
        }
        queryName=queryName.replace("@Value", valueDDLB);
        List result = getData(list, queryName);
        List<BigFiveDTO> resultList = getCustomizedList(result, isProduct);
        return resultList;
    }

    private List getData(List input, String queryName) {
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        try {

//            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            sql = new StringBuilder(queryName);

            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

            list = (List<Object[]>) dao.getResultList(sql.toString());
        } catch (Exception ex) {

            LOGGER.error(ex);
        }

        return list;
    }

    private List<BigFiveDTO> getCustomizedList(List<Object[]> result, boolean isProduct) {
        List resultList = new ArrayList();
        DecimalFormat df = new DecimalFormat("#,##0.00");
        double total = 0;
        String value = "";
        for (Object[] obj : result) {
            BigFiveDTO dto = new BigFiveDTO();
            if (isProduct) {
                dto.setProduct(obj[0] != null ? obj[0].toString() : "");
            } else {
                dto.setCompany(obj[0] != null ? obj[0].toString() : "");
            }
            value = formatDecimalPlaces(String.valueOf(obj[1]));
            dto.setSales(value);
            total += Double.valueOf(String.valueOf(obj[1]));
            resultList.add(dto);
        }
        BigFiveDTO dto = new BigFiveDTO();
        dto.setProduct("Total");
        dto.setCompany("Total");
        dto.setSales(df.format(total));
        resultList.add(dto);
        return resultList;
    }

}
