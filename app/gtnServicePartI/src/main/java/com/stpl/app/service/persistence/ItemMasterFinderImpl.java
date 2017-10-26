package com.stpl.app.service.persistence;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.ItemMaster;
import com.stpl.app.serviceUtils.ConstantUtil;
import com.stpl.app.serviceUtils.CommonUtils;
//import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;

public class ItemMasterFinderImpl extends BasePersistenceImpl<ItemMaster> implements ItemMasterFinder {

    private static final Logger LOGGER = Logger.getLogger(ItemMasterFinderImpl.class);

    public List findItemMaster(String itemId, String itemNo,
            String itemName, String itemStatus, String itemType, String itemDesc,
            String manufacturerId, int identifierType, String start, String offset, String orderByColumn, Boolean sortOrder, Map<String, Object> parameters) {
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
          
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                LOGGER.debug(entry.getKey() + "/" + entry.getValue());
            }

            if (StringUtils.isEmpty(start)) {
                sql = "select count(im.ITEM_MASTER_SID) ";
            } else {
                sql = CustomSQLUtil
                        .get("com.itemmaster.service.persistence.ItemMasterFinder.findItemMaster");

                if (parameters.get("itemQualifier") != null) {
                    sql += ",irt.itemIdentifierSid, irt.itemIdentifierValue ";
                }
            }
            sql += " from ItemMaster im, HelperTable therapy ,HelperTable strength ,BrandMaster bm,HelperTable form ";
            if (parameters.get("itemQualifier") != null) {
                sql += " ,ItemIdentifier irt ";
            }
            sql += " where therapy.helperTableSid =  im.therapeuticClass and strength.helperTableSid =  im.strength and bm.brandMasterSid = im.brandMasterSid "
                    + " and form.helperTableSid =  im.form and im.inboundStatus <> 'D' ";
            if (parameters.get("itemQualifier") != null) {
                sql += " and im.itemMasterSid = irt.itemMasterSid ";
            }

            //Where Conditions Starts
            if (parameters.get("systemId") != null) {
                sql += " and im.itemMasterSid like '" + parameters.get("systemId").toString() + "' ";
            }
            if (parameters.get(ConstantUtil.ITEM_ID_M) != null) {
                String item_Id = CommonUtils.getDBinput(parameters.get(ConstantUtil.ITEM_ID_M).toString());
                sql += " and im.itemId like '" + item_Id + "' ";
            }
            if (parameters.get(ConstantUtil.ITEM_NO_M) != null) {
                String item_No = CommonUtils.getDBinput(parameters.get(ConstantUtil.ITEM_NO_M).toString());
                sql += " and im.itemNo like '" + item_No + "' ";
            }
            if (parameters.get(ConstantUtil.ITEM_NAME_M) != null) {
                String item_Name = CommonUtils.getDBinput(parameters.get(ConstantUtil.ITEM_NAME_M).toString());
                sql += " and im.itemName like '" + item_Name + "' ";
            }
             if (parameters.get(ConstantUtil.ITEM_BATCH_ID) != null) {
                String item_BatchId = CommonUtils.getDBinput(parameters.get(ConstantUtil.ITEM_BATCH_ID).toString());
                sql += " and im.batchId like '" + item_BatchId + "' ";
            }
            if (parameters.get(ConstantUtil.ITEM_DESC_M) != null) {
                String item_Desc = CommonUtils.getDBinput(parameters.get(ConstantUtil.ITEM_DESC_M).toString());
                sql += " and im.itemDesc like '" + item_Desc + "' ";
            }

            if (parameters.get("itemStatus") != null) {
                sql += " and im.itemStatus='" + parameters.get("itemStatus").toString() + "' ";
            }
            if (parameters.get("itemType") != null) {
                sql += " and im.itemType='" + parameters.get("itemType").toString() + "' ";
            }
            if (parameters.get("therapyClass") != null) {
                String therapy = parameters.get("therapyClass").toString();
                sql += " and im.therapeuticClass in (" + therapy.substring(1, therapy.length() - 1) + ") ";
            }

            if (parameters.get("ndc9") != null) {
                String ndc9 = parameters.get("ndc9").toString();
                sql += " and im.ndc9 like '" + ndc9 + "' ";
            }

            if (parameters.get(ConstantUtil.FORM) != null) {
                String form = parameters.get(ConstantUtil.FORM).toString();
                sql += " and im.form like '" + form + "' ";
            }

            if (parameters.get("itemQualifier") != null) {
                String identifierTypeValue = parameters.get("itemQualifier").toString();
                sql += " and irt.itemQualifierSid=" + identifierTypeValue + " ";
            }

            if (parameters.get("identiferId") != null) {
                String identifier_Id = CommonUtils.getDBinput(parameters.get("identiferId").toString());
                sql += " and irt.itemIdentifierValue like '" + identifier_Id + "' ";
            }

            if (parameters.get("brandMasterSid") != null) {
                sql += " and im.brandMasterSid in (" + parameters.get("brandMasterSid").toString() + ") ";
            }

            if (parameters.get("ndc8") != null) {
                String ndc8 = parameters.get("ndc8").toString();
                sql += " and im.ndc8 like '" + ndc8 + "' ";
            }

            if (parameters.get("strength") != null) {
                String strength = parameters.get("strength").toString();
                sql += " and im.strength ='" + strength + "' ";
            }

            if (parameters.get("itemQualifier") != null) {
                sql += " and irt.inboundStatus <> '" + "D" + "' ";
            }

            //------------------->  Filter Code Starts  
            if (parameters.get("systemId-equal") != null) {
                String sysId = parameters.get("systemId-equal").toString();
                sql += " and im.itemMasterSid = '" + sysId + "'";
            }

            if (parameters.get("systemId-greater") != null) {
                String sysId = parameters.get("systemId-greater").toString();
                sql += " and im.itemMasterSid > '" + sysId + "'";
            }

            if (parameters.get("systemId-less") != null) {
                String sysId = parameters.get("systemId-less").toString();
                sql += " and im.itemMasterSid < '" + sysId + "'";
            }
            if (parameters.get(ConstantUtil.ITEM_ID_M + "~") != null) {
                sql += " and im.itemId like '" + parameters.get(ConstantUtil.ITEM_ID_M + "~").toString() + "' ";
            }
            if (parameters.get(ConstantUtil.ITEM_NO_M + "~") != null) {
                sql += " and im.itemNo like '" + parameters.get(ConstantUtil.ITEM_NO_M + "~").toString() + "' ";
            }
            if (parameters.get(ConstantUtil.ITEM_NAME_M + "~") != null) {
                sql += " and im.itemName like '" + parameters.get(ConstantUtil.ITEM_NAME_M + "~").toString() + "' ";
            }
            if (parameters.get(ConstantUtil.ITEM_DESC_M + "~") != null) {
                sql += " and im.itemDesc like '" + parameters.get(ConstantUtil.ITEM_DESC_M + "~").toString() + "' ";
            }
            if (parameters.get(ConstantUtil.BATCH_ID + "~") != null) {
                sql += " and im.batchId like '" + parameters.get(ConstantUtil.BATCH_ID + "~").toString() + "' ";
            }
            if (parameters.get("itemStatus~") != null) {
                sql += " and im.itemStatus='" + parameters.get("itemStatus" + "~").toString() + "' ";
            }
            if (parameters.get("itemType~") != null) {
                sql += " and im.itemType='" + parameters.get("itemType" + "~").toString() + "' ";
            }
            if (parameters.get("therapeuticClass~") != null) {
                String therapy = parameters.get("therapeuticClass~").toString();
                sql += " and im.therapeuticClass in (" + therapy + ") ";
            }

            if (parameters.get("ndc9~") != null) {
                String ndc9 = parameters.get("ndc9~").toString();
                sql += " and im.ndc9 like '" + ndc9 + "' ";
            }

            if (parameters.get(ConstantUtil.FORM + "~") != null) {
                String form = parameters.get(ConstantUtil.FORM + "~").toString();
                sql += " and im.form like '" + form + "' ";
            }

            if (parameters.get("itemQualifier~") != null) {
                String identifierTypeValue = parameters.get("itemQualifier~").toString();
                sql += " and irt.itemQualifierSid=" + identifierTypeValue + " ";
            }

            if (parameters.get("itemIdentifier~") != null) {
                String identifierValue = parameters.get("itemIdentifier~").toString();
                sql += " and irt.itemIdentifierValue like '" + identifierValue + "' ";
            }

            if (parameters.get("brandMasterSid~") != null) {
                sql += " and im.brandMasterSid in (" + parameters.get("brandMasterSid~").toString() + ") ";
            }

            if (parameters.get("ndc8~") != null) {
                String ndc8 = parameters.get("ndc8~").toString();
                sql += " and im.ndc8 like '" + ndc8 + "' ";
            }

            if (parameters.get("strength~") != null) {
                String strength = parameters.get("strength~").toString();
                sql += " and im.strength ='" + strength + "' ";
            }

            if (!StringUtils.isEmpty(start)) {
                String orderByColumnChanged = "";
                if (StringUtils.isNotBlank(orderByColumn)) {
                    if (orderByColumn.equals("itemQualifierSid")) {
                        orderByColumnChanged = "irt.itemQualifierSid";
                    } else if (orderByColumn.equals("itemIdentifierValue")) {
                        orderByColumnChanged = "irt.itemIdentifierValue";
                    } else {
                        orderByColumnChanged = "im." + orderByColumn;
                    }
                    sql += " order by " + orderByColumnChanged;
                    if (!sortOrder) {
                        sql += " desc";
                    }
                } else {
                    sql += " order by im.itemMasterSid ";
                }

                sql += "  OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            }
            Query sqlQuery = session.createQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }
}