/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class DataQueryLogic {

    private static final Logger LOGGER = org.jboss.logging.Logger.getLogger(DataQueryLogic.class);
    /**
     * 
     * @param parameters
     * @return 
     */
    public List<Object> executeQuery(final Map<String, Object> parameters) {

        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        queryString.append(String.valueOf(parameters.get("query")));
        List<Object> list2 = new ArrayList();
        try {
            BaseRateDAO baseRateDAO = new BaseRateDAOImpl();
            list2 = (List) baseRateDAO.executeSelectQuery(queryString.toString());

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return list2;

    }
    /**
     * 
     * @param parameters
     * @return 
     */
    public List getCustomerProductGroup(final Map<String, Object> parameters) {

        try {
            LOGGER.info("Entering getCustomerProductGroup method");

            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            if (parameters.get("indicator") != null && "CustomerGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryString.append("select distinct CG.company_Group_Sid, CG.company_Group_No, CG.company_Group_Name, CG.version_No\n"
                        + "                    FROM Company_Group CG, Company_Group_Details CGD\n"
                        + "                    WHERE  CG.company_Group_Sid = CGD.company_Group_Sid ");
                if (parameters.get("customerNo") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("customerNo")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("customerNo")))) {
                    queryString.append(" AND CG.company_Group_No LIKE '");
                    queryString.append(String.valueOf(parameters.get("customerNo")));
                    queryString.append("'");
                }
                if (parameters.get("customerName") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("customerName")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("customerName")))) {
                    queryString.append(" AND CG.company_Group_Name LIKE '");
                    queryString.append(String.valueOf(parameters.get("customerName")));
                    queryString.append("'");
                }
                if (parameters.get("companySids") != null) {
                    List<String> companySids = (ArrayList<String>) (parameters.get("companySids"));
                    StringBuilder companiesList = new StringBuilder(StringUtils.EMPTY);
                    if (companySids != null && !companySids.isEmpty()) {
                        for (int loop = 0, limit = companySids.size(); loop < limit; loop++) {
                            companiesList.append("'");
                            companiesList.append(companySids.get(loop));
                            companiesList.append("'");
                            if (loop != (limit - 1)) {
                                companiesList.append(", ");
                            }
                        }
                    }
                    queryString.append(" AND CGD.company_Master_Sid in ( ");
                    queryString.append(companiesList.toString());
                    queryString.append(" )");
                }

            } else if (parameters.get("indicator") != null && "ProductGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryString.append("select distinct IG.item_Group_Sid, IG.item_Group_No, IG.item_Group_Name, CM.company_Name, IG.version_No\n"
                        + "                    FROM Item_Group IG, Company_Master CM, Item_Group_Details IGD\n"
                        + "                    WHERE IG.company_Master_Sid = CM.company_Master_Sid\n"
                        + "                    AND IGD.item_Group_Sid = IG.item_Group_Sid ");
                if (parameters.get("productNo") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("productNo")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("productNo")))) {
                    queryString.append(" AND IG.item_Group_No LIKE '");
                    queryString.append(String.valueOf(parameters.get("productNo")));
                    queryString.append("' ");
                }
                if (parameters.get("productName") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("productName")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("productName")))) {
                    queryString.append(" AND IG.item_Group_Name LIKE '");
                    queryString.append(String.valueOf(parameters.get("productName")));
                    queryString.append("'");
                }

                if (parameters.get("itemSids") != null) {
                    List<String> itemSids = (ArrayList<String>) (parameters.get("itemSids"));
                    StringBuilder itemsList = new StringBuilder(StringUtils.EMPTY);
                    if (itemSids != null && !itemSids.isEmpty()) {
                        for (int loop = 0, limit = itemSids.size(); loop < limit; loop++) {
                            itemsList.append("'");
                            itemsList.append(itemSids.get(loop));
                            itemsList.append("'");
                            if (loop != (limit - 1)) {
                                itemsList.append(", ");
                            }
                        }
                    }
                    queryString.append(" AND IGD.item_Master_Sid in ( ");
                    if (itemsList.length() != 0) {
                        queryString.append(itemsList.toString());
                    } else {
                        queryString.append("''");
                    }
                    queryString.append(" )");
                }
            } else {
                return null;
            }
            BaseRateDAO baseRateDAODAO = new BaseRateDAOImpl();
            List list2 = new ArrayList();
            list2 = (List) baseRateDAODAO.executeSelectQuery(queryString.toString());
            return list2;

        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }
}
