/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemGroup;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author nandhakumar
 */
public class ItemGroupFinderImpl extends BasePersistenceImpl<ItemGroup> implements ItemGroupFinder {

    private static final Logger LOGGER = Logger.getLogger(ItemGroupFinderImpl.class);

    public List getItemGroupMaster(String itemGroupName) {

        Session session = null;
        try {
            session = openSession();
            // String itemGroupName = searchDTO.getItemGroupName();
            String query = "select IG.itemGroupNo,IG.versionNo,IG.itemGroupName from ItemGroup IG where IG.itemGroupName ='" + itemGroupName + "'";
            // + " UNION select IGH.itemGroupNo,IGH.versionNo,IGH.itemGroupName from ItemGroupHistory IGH where IGH.itemGroupName ='"+searchDTO.getItemGroupName()+"'";
            //   + "UNION "
            //  + "select IGH.itemGroupNo,IGH.versionNo,IGH.itemGroupName from ItemGroupHistory IGH where IGH.itemGroupName =:itemGroupName ";
            String sql;
            //String query="select ITEM_GROUP_NO,ITEM_GROUP_NAME,VERSION_NO,ITEM_GROUP_DESCRIPTION,COMPANY_SYSTEM_ID,CREATED_BY from ITEM_GROUP where";
            //String query1="select ITEM_GROUP_NO,ITEM_GROUP_NAME,VERSION_NO,ITEM_GROUP_DESCRIPTION,COMPANY_SYSTEM_ID,CREATED_BY from ITEM_GROUP_HISTORY where";

            //String query="select ITEM_GROUP_NO,ITEM_GROUP_NAME,VERSION_NO,ITEM_GROUP_DESCRIPTION,COMPANY_SYSTEM_ID,CREATED_BY from ITEM_GROUP where";
            //String query1="select ITEM_GROUP_NO,ITEM_GROUP_NAME,VERSION_NO,ITEM_GROUP_DESCRIPTION,COMPANY_SYSTEM_ID,CREATED_BY from ITEM_GROUP_HISTORY where";
            

            sql = query;
//            LOGGER.info("Final sql statement----------->" + sql);
            Query sqlQuery = session.createQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(errorMsg);
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List getItemGroupDetails(String itemType, String itemDesc, String brand, String strength, String itemNoCriteria, String therapeuticCriteria, String formCriteria) {
        Session session = null;
        try {
            session = openSession();

            String sql;
            sql = CustomSQLUtil.get("getItemDetails");
            if (!itemType.equalsIgnoreCase(Constants.EMPTY) || !itemDesc.equalsIgnoreCase(Constants.EMPTY)
                    || !brand.equalsIgnoreCase(Constants.EMPTY) || !strength.equalsIgnoreCase(Constants.EMPTY)
                    || !itemNoCriteria.equalsIgnoreCase(Constants.EMPTY) || !therapeuticCriteria.equalsIgnoreCase(Constants.EMPTY)
                    || !formCriteria.equalsIgnoreCase(Constants.EMPTY)) {
                boolean flag = false;
                sql += " WHERE";
                if (itemType.length() != 0) {
                    if (flag) {
                        sql += " AND ";
                    }
                    flag = true;
                    sql += " HT_IT.description = '" + itemType + "' " + " AND IM.ITEM_TYPE = HT_IT.HELPER_TABLE_SID ";


                }
                if (itemDesc.length() != 0) {
                    if (flag) {
                        sql += " AND ";
                    }
                    flag = true;
                    sql += " IM.item_Desc like '" + itemDesc + "' ";
                }

                if (brand.length() != 0) {
                    if (flag) {
                        sql += " AND ";
                    }
                    flag = true;
                    sql += " BM.brand_Name = '" + brand + "' " + " AND IM.BRAND_MASTER_SID = BM.BRAND_MASTER_SID";

                }

                if (strength.length() != 0) {
                    if (flag) {
                        sql += " AND ";
                    }
                    flag = true;
                    sql += " IM.strength like '" + strength + "' ";

                }

                if (itemNoCriteria.length() != 0) {
                    if (flag) {
                        sql += " AND ";
                    }
                    flag = true;
                    sql += " IM.item_No like '" + itemNoCriteria + "' ";

                }
                if (therapeuticCriteria.length() != 0) {
                    if (flag) {
                        sql += " AND ";
                    }
                    flag = true;
                    sql += " HT_TC.description ='" + therapeuticCriteria + "' " + " AND IM.THERAPEUTIC_CLASS = HT_TC.HELPER_TABLE_SID";

                }
                if (formCriteria.length() != 0) {
                    if (flag) {
                        sql += " AND ";
                    }
                    flag = true;
//				String zip = String.valueOf(formCriteria);
                    sql += " HT_F.description = '" + formCriteria + "' " + " AND IM.FORM = HT_F.HELPER_TABLE_SID";

                }
                if (flag) {
                    sql += " AND ";
                }
                flag = true;
                sql += " IM.inbound_Status not like '" + Constants.INBOUND_STATUS_D + "'";
            }
            LOGGER.info("Sql Queryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy++++++++++:::::::"+sql);
            SQLQuery sqlQuery = session.createSQLQuery(sql);

//            Query sqlQuery = session.createQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public List getAvailableSearchResults(String finalCriteria) {
        Session session = null;
        try {
            session = openSession();

            String sql;
            sql = CustomSQLUtil.get("getItemDetails");

            sql += " WHERE IM.inbound_Status not like '" + Constants.INBOUND_STATUS_D + "'";

            if (!StringUtils.isEmpty(finalCriteria) && !finalCriteria.equalsIgnoreCase("*")) {
                sql += " AND " + finalCriteria;
            }
            SQLQuery sqlQuery = session.createSQLQuery(sql);
//            Query sqlQuery = session.createQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            closeSession(session);
        }
    }
}
