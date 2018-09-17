/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.logic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.discountProjection.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.discountProjection.dto.FormulaDTO;
import com.stpl.app.gtnforecasting.discountProjection.dto.LookUpDTO;
import com.stpl.app.gtnforecasting.discountProjection.logic.tableLogic.SupplementalTableLogic;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import static com.stpl.app.utils.Constants.CommonConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.ComboBox;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sooriya.lakshmanan
 */
public class SupplementalDiscountProjectionLogic {

    /**
     * The Currency Four Decimal Places Format.
     */
    private static final DecimalFormat CUR_FOUR_DECIMAL = new DecimalFormat("$#,##0.0000");
    private static final DecimalFormat PER_THREE_DECIMAL = new DecimalFormat("#,##0.000");
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplementalDiscountProjectionLogic.class);
    
    protected SalesProjectionDAO dao = new SalesProjectionDAOImpl();
    protected ProjectionSelectionDTO projectionSelectionDTO = new ProjectionSelectionDTO();
    protected List<String> levelName = new ArrayList<>();
    protected String projectionValue = StringUtils.EMPTY;
    protected boolean projectionValueFlag = false;
    protected String suppHeader = StringUtils.EMPTY;
    protected Object supplemental;
    protected Object supplementalLevelName = null;
    protected static HashMap<String, String> rsFormulaDbMap = new HashMap<>();

    public int getConfiguredSupplementalDiscountCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        if (parentId instanceof DiscountProjectionDTO) {
            DiscountProjectionDTO parentDto = (DiscountProjectionDTO) parentId;
            projSelDTO.setSupplementalLevelNo(parentDto.getSupplementalLevelNo());
            projSelDTO.setCcpDetailsSID(parentDto.getCcpDetailsSID());
            projSelDTO.setSystemID(parentDto.getSystemID());
            if (projSelDTO.getSupplementalLevelNo() == 1) {
                projSelDTO.setCompanyID(parentDto.getSystemID());
                projSelDTO.setSupplementalLevelName(Constant.CONTRACT_SMALL);
            } else if (projSelDTO.getSupplementalLevelNo() == NumericConstants.TWO) {
                projSelDTO.setCompanyID(parentDto.getCompanyID());
                projSelDTO.setContractID(parentDto.getSystemID());
                projSelDTO.setSupplementalLevelName("Therapeutic Class");
            } else if (projSelDTO.getSupplementalLevelNo() == NumericConstants.THREE) {
                projSelDTO.setCompanyID(parentDto.getCompanyID());
                projSelDTO.setContractID(parentDto.getContractID());
                projSelDTO.setTherapeuticID(Integer.valueOf(parentDto.getGroup()));
                projSelDTO.setSupplementalLevelName(Constant.BRAND_CAPS);
            } else if (projSelDTO.getSupplementalLevelNo() == NumericConstants.FOUR) {
                projSelDTO.setCompanyID(parentDto.getCompanyID());
                projSelDTO.setContractID(parentDto.getContractID());
                projSelDTO.setTherapeuticID(parentDto.getTherapeuticID());
                projSelDTO.setBrandID(parentDto.getGroup());
                projSelDTO.setSupplementalLevelName(Constant.PRODUCT_LABEL);
            }
        } else {
            if (projSelDTO.isIsFilter()) {
                Integer supplementalLevelNo = projSelDTO.getSupplementalLevelNo();
                projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
                projSelDTO.setHierarchyIndicator(projSelDTO.getFilterHierarchyNo());
                projSelDTO.setSupplementalLevelNo(supplementalLevelNo);
                projSelDTO.setSupplementalLevelName(getSupplementalLevelName(projSelDTO.getSupplementalLevelNo()));
            } else {
                projSelDTO.setSupplementalLevelNo(0);
                projSelDTO.setSupplementalLevelName(Constant.CUSTOMER_SMALL);
        }
        }
        projSelDTO.setIsCount(true);
        if (projSelDTO.isIsFilter()) {
            List<Object> filterCount = getFilterCount(projSelDTO);
            if (filterCount != null && !filterCount.isEmpty()) {
                count += filterCount.size();
            }
        } else {
            List<Object> masterData = getQueryToHitMasterTable(projSelDTO, false, false);
            if (masterData != null && !masterData.isEmpty()) {
                Object ob = masterData.get(0);
                count += Integer.parseInt(String.valueOf(ob));
            }
        }
        return count;
    }

    public List<DiscountProjectionDTO> getConfiguredSupplementalDiscount(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionDTO> resultList;
        if (parentId instanceof DiscountProjectionDTO) {
            DiscountProjectionDTO parentDto = (DiscountProjectionDTO) parentId;
            projSelDTO.setSupplementalLevelNo(parentDto.getSupplementalLevelNo());
            projSelDTO.setCcpDetailsSID(parentDto.getCcpDetailsSID());
            projSelDTO.setSystemID(parentDto.getSystemID());
            if (projSelDTO.getSupplementalLevelNo() == 1) {
                projSelDTO.setCompanyID(parentDto.getSystemID());
                projSelDTO.setSupplementalLevelName(getSupplementalLevelName(projSelDTO.getSupplementalLevelNo()));
                projSelDTO.setParentCcpDetailIdList(parentDto.getCcpDetailIds());
            } else if (projSelDTO.getSupplementalLevelNo() == 2) {
                projSelDTO.setCompanyID(parentDto.getCompanyID());
                projSelDTO.setContractID(parentDto.getSystemID());
                projSelDTO.setSupplementalLevelName(getSupplementalLevelName(projSelDTO.getSupplementalLevelNo()));
                projSelDTO.setParentCcpDetailIdList(parentDto.getParentCcpDetailIdList());
            } else if (projSelDTO.getSupplementalLevelNo() == 3) {
                projSelDTO.setCompanyID(parentDto.getCompanyID());
                projSelDTO.setContractID(parentDto.getContractID());
                projSelDTO.setTherapeuticID(Integer.valueOf(parentDto.getGroup()));
                projSelDTO.setSupplementalLevelName(getSupplementalLevelName(projSelDTO.getSupplementalLevelNo()));
                projSelDTO.setParentCcpDetailIdList(parentDto.getParentCcpDetailIdList());
            } else if (projSelDTO.getSupplementalLevelNo() == 4) {
                projSelDTO.setCompanyID(parentDto.getCompanyID());
                projSelDTO.setContractID(parentDto.getContractID());
                projSelDTO.setTherapeuticID(parentDto.getTherapeuticID());
                projSelDTO.setBrandID(parentDto.getGroup());
                projSelDTO.setSupplementalLevelName(getSupplementalLevelName(projSelDTO.getSupplementalLevelNo()));
                projSelDTO.setCcpDetailIdList(parentDto.getCcpDetailIds());
                projSelDTO.setParentCcpDetailIdList(parentDto.getParentCcpDetailIdList());
            }
        } else {
            if (projSelDTO.isIsFilter()) {
                Integer supplementalLevel = projSelDTO.getSupplementalLevelNo();
                projSelDTO.setSupplementalLevelNo(supplementalLevel);
                projSelDTO.setSupplementalLevelName(getSupplementalLevelName(projSelDTO.getSupplementalLevelNo()));
            } else {
                projSelDTO.setSupplementalLevelNo(0);
                projSelDTO.setSupplementalLevelName(getSupplementalLevelName(projSelDTO.getSupplementalLevelNo()));
            }
        }
        projSelDTO.setIsCount(false);
        resultList = getSupplementalDiscount(start, offset, projSelDTO);
        LOGGER.debug("resultList size is= {} " , resultList.size());
        return resultList;
    }

    public List<DiscountProjectionDTO> getSupplementalDiscount(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int started = start;

        List<DiscountProjectionDTO> projDTOList = new ArrayList<>();
        List<DiscountProjectionDTO> nextLevelValueList = configureLevels(neededRecord, projSelDTO);
        for (int i = started; (i < nextLevelValueList.size()) && (neededRecord > 0); neededRecord--, i++) {
            projDTOList.add(nextLevelValueList.get(i));
        }

        return projDTOList;
    }

    public List<DiscountProjectionDTO> configureLevels(int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        List<Object> masterData = new ArrayList<>();
        if (neededRecord > 0) {
            masterData = getQueryToHitMasterTable(projSelDTO, false, false);
        }
        return loadDTOList(projSelDTO, masterData);
    }

    public List<Object> getQueryToHitMasterTable(ProjectionSelectionDTO projSelDTO, boolean levelFlag, boolean suppLevelFlag) {
        String[] tableLevelName = getTableAndLevelName(projSelDTO, false, false);

        List<Object> objList = new ArrayList<>();
        if (isValidTableName(tableLevelName)) {
            String levelFieldSelection = StringUtils.EMPTY;

            String selectedId = StringUtils.EMPTY;

            String levelSelection = StringUtils.EMPTY;

            String orderBy = StringUtils.EMPTY;

            String tempTableName = StringUtils.EMPTY;

            String tempWerCon = StringUtils.EMPTY;

            String customerFilter;

            String selectQuery;
            String concat = StringUtils.EMPTY;
            String supplemetalLevelName = "'" + projSelDTO.getSupplementalLevelName() + "'" + " as LEVEL_NAME,";
            String tempName = StringUtils.EMPTY;
            String tempGroupName = StringUtils.EMPTY;
            String finalQuery;

            customerFilter = getCustomerFilterQuery(projSelDTO);

            if (projSelDTO.getSupplementalLevelNo() == 0) {
                levelFieldSelection = tableLevelName[1];
                selectedId = "0 AS COM_ID,0 AS CON_ID,0 AS THERAP_CLASS,0 AS BRAND_SID,0 AS NDC_ID,";
                levelSelection = " CM.COMPANY_MASTER_SID,CM." + levelFieldSelection + ", ";
                tempTableName = "COMPANY_MASTER";
                tempWerCon = " CCP.CCP_DETAILS_SID = SUPMAS.CCP_DETAILS_SID \n"
                        + "and CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n";
                projSelDTO.setLevelFieldSelection(levelFieldSelection);
                tempGroupName = ",CM.COMPANY_ID";
                if (suppLevelFlag) {
                    tempWerCon = tempWerCon + " and  CCP.COMPANY_MASTER_SID = " + projSelDTO.getLevelCompanySid();
                }
                orderBy = " order by CM.COMPANY_MASTER_SID,min(SUPMAS.CHECK_RECORD)";
            } else if (projSelDTO.getSupplementalLevelNo() == 1) {
                levelFieldSelection = tableLevelName[1];
                selectedId = projSelDTO.getCompanyID() + " AS COM_ID,0 AS CON_ID,0 AS THERAP_CLASS,0 AS BRAND_SID,0 AS NDC_ID,";
                levelSelection = " TT.CONTRACT_MASTER_SID,TT." + levelFieldSelection + ", ";
                tempTableName = "CONTRACT_MASTER";
                tempWerCon = " CCP.CCP_DETAILS_SID = SUPMAS.CCP_DETAILS_SID " + "\n"
                        + " and TT.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n";
                if (!projSelDTO.isIsFilter()) {
                    tempWerCon = tempWerCon + " and  CCP.COMPANY_MASTER_SID = " + projSelDTO.getCompanyID();
                }
                if (suppLevelFlag) {
                    tempWerCon = tempWerCon + " and CCP.CONTRACT_MASTER_SID =  " + projSelDTO.getContractID();
                }
                tempGroupName = " ,CCP.COMPANY_MASTER_SID , CCP.CONTRACT_MASTER_SID";
                orderBy = " order by TT.CONTRACT_MASTER_SID";
            } else if (projSelDTO.getSupplementalLevelNo() == 2) {
                levelFieldSelection = tableLevelName[1];
                selectedId = projSelDTO.getCompanyID() + " AS COM_ID ," + projSelDTO.getContractID() + " AS CON_ID,0 AS THERAP_CLASS,0 AS BRAND_SID,0 AS NDC_ID,0 as ITEMID,";
                levelSelection = " TT." + levelFieldSelection + ",";
                tempTableName = "ITEM_MASTER";
                tempWerCon = " CCP.CCP_DETAILS_SID = SUPMAS.CCP_DETAILS_SID \n"
                        + "and TT.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n";
                if (!projSelDTO.isIsFilter()) {
                    tempWerCon = tempWerCon + "  and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCompanyID()
                            + " and CCP.CONTRACT_MASTER_SID =  " + projSelDTO.getContractID();
                }
                tempGroupName = " ,CCP.COMPANY_MASTER_SID , CCP.CONTRACT_MASTER_SID ,CCP.ITEM_MASTER_SID ";
                orderBy = " order by min(SUPMAS.CHECK_RECORD)";
            } else if (projSelDTO.getSupplementalLevelNo() == NumericConstants.THREE) {
                levelFieldSelection = tableLevelName[1];
                if (projSelDTO.isIsFilter()) {
                    selectedId = " CCP.COMPANY_MASTER_SID  AS COM_ID ,CCP.CONTRACT_MASTER_SID AS CON_ID," + projSelDTO.getTherapeuticID() + " AS THERAP_CLASS,0 AS BRAND_SID,0 AS NDC_ID,";
                } else {
                    selectedId = projSelDTO.getCompanyID() + " AS COM_ID," + projSelDTO.getContractID() + " AS CON_ID," + projSelDTO.getTherapeuticID() + " AS THERAP_CLASS,0 AS BRAND_SID,0 AS NDC_ID,";
                }
                levelSelection = "TT.BRAND_MASTER_SID,TT." + levelFieldSelection + ", ";
                tempTableName = "ITEM_MASTER IM, BRAND_MASTER";
                tempWerCon = " CCP.CCP_DETAILS_SID = SUPMAS.CCP_DETAILS_SID\n"
                        + "and TT.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n";
                tempName = " ,SUPMAS.CASH_PAID_DATE";
                if (!projSelDTO.isIsFilter()) {
                    tempWerCon = tempWerCon + " and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCompanyID()
                            + " and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getContractID()
                            + "and IM.THERAPEUTIC_CLASS = " + projSelDTO.getTherapeuticID();
                }
                tempWerCon = tempWerCon + " and CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID ";
                orderBy = " order by TT.BRAND_MASTER_SID";
                tempGroupName = " ,CCP.COMPANY_MASTER_SID , CCP.CONTRACT_MASTER_SID";
            } else if (projSelDTO.getSupplementalLevelNo() == NumericConstants.FOUR) {
                levelFieldSelection = tableLevelName[1];
                projSelDTO.setSupplementalLevelName(Constant.BRAND_CAPS);
                tempName = " ,SUPMAS.CCP_DETAILS_SID,CM.COMPANY_ID,TT.ITEM_ID";
                String[] parentTableLevelName = getTableAndLevelName(projSelDTO, true, false);
                selectedId = projSelDTO.getCompanyID() + " AS COM_ID," + projSelDTO.getContractID() + " AS CON_ID," + projSelDTO.getTherapeuticID() + " AS THERAP_CLASS, '" + projSelDTO.getBrandID() + "' AS BRAND_SID,0 AS NDC_ID,";

                levelSelection = "TT.ITEM_MASTER_SID,TT." + levelFieldSelection + ", ";
                tempTableName = "BRAND_MASTER BM,ITEM_MASTER";
                tempWerCon = " CCP.CCP_DETAILS_SID = SUPMAS.CCP_DETAILS_SID\n"
                        + "and TT.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n"
                        + " and CCP.ITEM_MASTER_SID = TT.ITEM_MASTER_SID"
                        + " and CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID";
                if (!projSelDTO.isIsFilter()) {
                    tempWerCon = tempWerCon + " and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCompanyID()
                            + " and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getContractID()
                            + "and TT.THERAPEUTIC_CLASS = " + projSelDTO.getTherapeuticID()
                            + " and BM." + parentTableLevelName[1] + " = '" + projSelDTO.getBrandID() + "' ";
                }
                orderBy = " order by TT.ITEM_MASTER_SID";
            }

            if (projSelDTO.getSupplementalLevelNo() == 0) {
                tempTableName = "COMPANY_MASTER CM,ITEM_MASTER IM, ";
            } else {
                tempTableName = tempTableName + " TT,COMPANY_MASTER CM,";
            }
            String tableName = " FROM CCP_DETAILS CCP, " + tempTableName +" ST_M_SUPPLEMENTAL_DISC_MASTER SUPMAS";


            if (levelFlag) {
                if (projSelDTO.getSupplementalLevelNo() == 0) {
                    selectQuery = "SELECT Row_number() OVER (ORDER BY CM." + levelFieldSelection + " ASC)  AS TEMP_INDEX";
                } else {
                    selectQuery = "SELECT Row_number() OVER (ORDER BY TT." + levelFieldSelection + " ASC)  AS TEMP_INDEX";
                }
            } else {
                if (projSelDTO.isIsCount()) {
                if (projSelDTO.getSupplementalLevelNo() == 0) {
                    selectQuery = "select Count(distinct CM." + levelFieldSelection + ") ";
                } else {
                    selectQuery = "select Count(distinct TT." + levelFieldSelection + ") ";
                }
            } else {
                selectQuery = "select " + selectedId + levelSelection + " \n"
                        + supplemetalLevelName + " avg(SUPMAS.ACTUAL_DISCOUNT) as ACTUAL_DISCOUNT,"
                        + "CONVERT(VARCHAR(10),SUPMAS.CONTRACT_END_DATE,110) " + tempName;
                selectQuery += ",min(CASE(SUPMAS.CHECK_RECORD) WHEN 1 THEN 1 ELSE 0  END)  AS CHECK_RECORD" + (projSelDTO.getSupplementalLevelNo() == 4 ? ",CM.COMPANY_ID,TT.ITEM_ID " : StringUtils.EMPTY);
                selectQuery += (projSelDTO.getSupplementalLevelNo() == 0 ? ",CM.COMPANY_ID " : StringUtils.EMPTY);
            }
            }
            String werCondition = " where " + tempWerCon ; 

            String groupBy = " group by " + levelSelection + " SUPMAS.CONTRACT_END_DATE" + tempName + tempGroupName;

            if (projSelDTO.isIsCount()) {
                finalQuery = selectQuery + tableName + werCondition + customerFilter;
            } else {
                finalQuery = selectQuery + concat + tableName + werCondition + customerFilter + groupBy + orderBy;
            }
            objList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(finalQuery, projSelDTO.getSessionDTO().getCurrentTableNames()));

            LOGGER.debug("objList  size= {} " , objList.size());
        } else {
            String query = "select LEVEL_NAME,\"TABLE_NAME\",FIELD_NAME from HIERARCHY_LEVEL_DEFINITION "
                    + "where HIERARCHY_DEFINITION_SID in (" + projSelDTO.getCustHierarchySid() + "," + projSelDTO.getProdHierarchySid() + ")";
            LOGGER.debug("Query to trace the Error= {} " , query);

        }

        return objList;
    }

    private String[] getTableAndLevelName(ProjectionSelectionDTO projSelDTO, boolean flag, boolean ddlbFlag) {

        List<Object> list = projSelDTO.getSupplementalList();

        String[] str = new String[NumericConstants.TWO];
        if (list != null && !list.isEmpty()) {

            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                if ((projSelDTO.getSupplementalLevelName()).equalsIgnoreCase(String.valueOf(obj[0]))
                        || (projSelDTO.getSupplementalLevelNo() == NumericConstants.FIVE && Constant.NDC.equalsIgnoreCase(String.valueOf(obj[0])))) {

                    str[0] = String.valueOf(obj[1]);

                    str[1] = String.valueOf(obj[NumericConstants.TWO]);
                    break;
                }
            }
            if (str[0] == null) {

                if (!ddlbFlag) {
                    if (projSelDTO.getSupplementalLevelNo() == 0) {
                        str[0] = Constant.COMPANY_MASTER;
                    } else if (projSelDTO.getSupplementalLevelNo() == 1) {
                        str[0] = Constant.CONTRACT_MASTER;
                    } else if (projSelDTO.getSupplementalLevelNo() == 2 || (projSelDTO.getSupplementalLevelNo() == 4 && !flag)) {
                        str[0] = Constant.ITEM_MASTER;
                    } else {
                        str[0] = BRAND_MASTER;
                    }
                } else {
                    if ((THERAPEUTIC_CLASS.equalsIgnoreCase(projSelDTO.getSupplementalLevelName())) || (Constant.PRODUCT_LABEL.equalsIgnoreCase(projSelDTO.getSupplementalLevelName()))) {
                        str[0] = Constant.ITEM_MASTER;
                    } else if (Constant.BRAND_CAPS.equalsIgnoreCase(projSelDTO.getSupplementalLevelName())) {
                        str[0] = BRAND_MASTER;
                    } else if (projSelDTO.getSupplementalLevelNo() == 2 || (projSelDTO.getSupplementalLevelNo() == 4 && !flag)) {
                        str[0] = Constant.ITEM_MASTER;
                    } else {
                        str[0] = null;
                    }

                }
            }
            if (str[1] == null) {
                if (!ddlbFlag) {
                    if (projSelDTO.getSupplementalLevelNo() == 0) {
                        str[1] = "COMPANY_NO";
                    } else if (projSelDTO.getSupplementalLevelNo() == 1) {
                        str[1] = "CONTRACT_NO";
                    } else if (projSelDTO.getSupplementalLevelNo() == NumericConstants.TWO) {
                        str[1] = THERAPEUTIC_CLASS;
                    } else if (projSelDTO.getSupplementalLevelNo() == NumericConstants.FOUR && !flag) {
                        str[1] = ITEM_ID;
                    } else {
                        str[1] = BRAND_MASTER;
                    }
                    
                } else {
                    if (THERAPEUTIC_CLASS.equalsIgnoreCase(projSelDTO.getSupplementalLevelName())) {
                        str[1] = THERAPEUTIC_CLASS;
                    } else if (Constant.BRAND_CAPS.equalsIgnoreCase(projSelDTO.getSupplementalLevelName())) {
                        str[1] = BRAND_NAME;
                    } else if (Constant.PRODUCT_LABEL.equalsIgnoreCase(projSelDTO.getSupplementalLevelName())) {
                        str[1] = ITEM_ID;
                    } else {
                        str[1] = null;
                    }
                    
                }
            }
        }
        return str;
    }
    public static final String BRAND_NAME = "BRAND_NAME";
    public static final String BRAND_MASTER = "BRAND_MASTER";
    public static final String THERAPEUTIC_CLASS = "THERAPEUTIC_CLASS";
    public static final String ITEM_ID = "ITEM_ID";

    public List<Object> getLevelList(int  customerHierId,int prodHierId) {

        String query = "select LEVEL_NAME,\"TABLE_NAME\",FIELD_NAME from HIERARCHY_LEVEL_DEFINITION "
                + "where HIERARCHY_DEFINITION_SID in (" + customerHierId + "," + prodHierId + ")";
        return (List<Object>) CommonLogic.executeSelectQuery(query);
    }

    public String getFormattedValue(DecimalFormat decFormat, String value) {
        String formatValue;
        if (value == null || value.contains(Constant.NULL) || value.isEmpty()) {
            formatValue = Constant.DASH;
        } else {
            formatValue = decFormat.format(Double.valueOf(value));
        }
        return formatValue;
    }

    private DiscountProjectionDTO loadProjectionValues(DiscountProjectionDTO dto, ProjectionSelectionDTO projSelDTO) {
        List<Object> projectionList = getProjectionList(dto, projSelDTO);
        if (projectionList != null && !projectionList.isEmpty()) {
            List supplementalColumns = projSelDTO.getVariableList();

            for (Object list1 : projectionList) {
                final Object[] obj = (Object[]) list1;
                int year = Integer.parseInt(String.valueOf(obj[NumericConstants.NINE]));
                int period = Integer.parseInt(String.valueOf(obj[NumericConstants.EIGHT]));
                int suppIementLevelNo = projSelDTO.getSupplementalLevelNo();
                List<String> common = HeaderUtils.getCommonColumnHeader(NumericConstants.FOUR, year, period);
                String commonColumn = common.get(0).toLowerCase(Locale.ENGLISH);
                if (supplementalColumns.contains(METHODOLOGY.getConstant()) && (suppIementLevelNo == 0 || suppIementLevelNo == NumericConstants.FOUR)) {
                    supplemental = commonColumn + METHODOLOGY.getConstant();

                    projectionValue = StringUtils.EMPTY + obj[0];

                    projectionValue = projectionValue.isEmpty() || Constant.NULL.equals(projectionValue)
                            || projectionValue.equals(Constant.MULTIPLE) ? SELECTMETHODOLOGY.getConstant() : projectionValue;

                    dto.addStringProperties(supplemental, projectionValue);

                }
                if (supplementalColumns.contains(CONTRACT_PRICE.getConstant()) && suppIementLevelNo == NumericConstants.FOUR) {
                    suppHeader = CONTRACT_PRICE.getConstant().replace(" ", StringUtils.EMPTY).trim();
                    supplemental = commonColumn + suppHeader;
                    projectionValue = StringUtils.EMPTY + obj[1];
                    if (projSelDTO.isExcelFlag()) {
                        projectionValue = getFormattedValue(CUR_FOUR_DECIMAL, projectionValue);
                    }
                    dto.addStringProperties(supplemental, projectionValue);
                }
                if (supplementalColumns.contains(DISCOUNT1.getConstant()) && suppIementLevelNo == NumericConstants.FOUR) {
                    suppHeader = DISCOUNT1.getConstant().replace(" ", StringUtils.EMPTY).trim();
                    supplemental = commonColumn + suppHeader;
                    projectionValue = StringUtils.EMPTY + obj[NumericConstants.TWO];
                    if (projectionValue == null || Constant.NULL.equals(projectionValue)) {
                        projectionValue = String.valueOf(0);
                    }
                    if (projSelDTO.isExcelFlag()) {
                        projectionValue = getFormattedValue(PER_THREE_DECIMAL, projectionValue) + Constant.PERCENT;
                    }
                    dto.addStringProperties(supplemental, projectionValue);
                }
                if (supplementalColumns.contains(DISCOUNT2.getConstant()) && suppIementLevelNo == NumericConstants.FOUR) {
                    suppHeader = DISCOUNT2.getConstant().replace(" ", StringUtils.EMPTY).trim();
                    supplemental = commonColumn + suppHeader;
                    projectionValue = StringUtils.EMPTY + obj[NumericConstants.THREE];
                    if (projectionValue==null || Constant.NULL.equals(projectionValue)) {
                        projectionValue = String.valueOf(0);
                    }
                    if (projSelDTO.isExcelFlag()) {
                        projectionValue = getFormattedValue(PER_THREE_DECIMAL, projectionValue) + Constant.PERCENT;
                    }
                    dto.addStringProperties(supplemental, projectionValue);
                }
                if (supplementalColumns.contains(ACCESS.getConstant()) && suppIementLevelNo > NumericConstants.TWO) {
                    supplemental = commonColumn + ACCESS.getConstant();
                    String projectedValue = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                    projectedValue = projectedValue.isEmpty() || projectedValue.equals(Constant.NULL)
                            || projectedValue.equals(Constant.MULTIPLE) ? SELECT_ONE.getConstant() : projectedValue;
                    dto.addStringProperties(supplemental, projectedValue);
                }
                if (supplementalColumns.contains(PARITY.getConstant()) && suppIementLevelNo == NumericConstants.FOUR) {
                    supplemental = commonColumn + PARITY.getConstant();
                    projectionValueFlag = obj[NumericConstants.FIVE] == null ? false : (Boolean) obj[NumericConstants.FIVE];
                    dto.addBooleanProperties(supplemental, projectionValueFlag);

                    suppHeader = PARITY_REFERENCE.getConstant().replace(" ", StringUtils.EMPTY).trim();
                    supplemental = commonColumn + suppHeader;
                    projectionValue = StringUtils.EMPTY + obj[NumericConstants.SIX];
                    projectionValue = Constant.NULL.equals(projectionValue) ? StringUtils.EMPTY : projectionValue;
                    dto.addStringProperties(supplemental, projectionValue);
                    supplemental = commonColumn + DISCOUNT.getConstant();
                    projectionValue = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    projectionValue = Constant.NULL.equals(projectionValue) ? StringUtils.EMPTY : projectionValue;
                    dto.addStringProperties(supplemental, projectionValue);
                }
            }
        }

        return dto;
    }

    public void updateCheckedRecord(DiscountProjectionDTO checkedDto, ProjectionSelectionDTO projSelDto, int checkValue) {
        try {

            String ccpDetailsId = StringUtils.EMPTY;
            if (checkedDto.getProjectionDetailsSid() != 0 && ("Ndc".equalsIgnoreCase(checkedDto.getLevelName()) || Constant.PRODUCT_LABEL.equalsIgnoreCase(checkedDto.getLevelName()))) {
                ccpDetailsId = StringUtils.EMPTY + checkedDto.getCcpDetailsSID();

            } else if (checkedDto.getCcpDetailIds().size() > 0 && !(Constant.PRODUCT_LABEL.equalsIgnoreCase(checkedDto.getLevelName()))) {
                ccpDetailsId = CommonUtils.collectionToStringMethod(checkedDto.getCcpDetailIds(), false);
            }
            if (!StringUtils.EMPTY.equals(ccpDetailsId) && !Constant.NULL.equals(ccpDetailsId)) {
                queryToUpdateCheckRecord(checkValue, projSelDto.getSessionDTO(), ccpDetailsId);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Used to update checked record using ProjectionDetailsSid
     *
     * @param checkValue
     * @param userId
     * @param sessionId
     * @param projectionDetailsId
     */
    public void queryToUpdateCheckRecord(int checkValue, SessionDTO sessionDto, String ccpDetailsId) {
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" Update ST_M_SUPPLEMENTAL_DISC_MASTER SET CHECK_RECORD =" ).append( checkValue ).append( " WHERE CCP_DETAILS_SID IN (" ).append( ccpDetailsId ).append( ')') ;
            dao.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder.toString(),sessionDto.getCurrentTableNames()));
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public List<Integer> headerCheckALLQuery(SessionDTO sessionDTO, int checkValue, boolean isUpdate) {
        StringBuilder queryBuilder = new StringBuilder();
        try {
            if (isUpdate) {
                queryBuilder.append(" Update ST_M_SUPPLEMENTAL_DISC_MASTER SET CHECK_RECORD =" ).append( checkValue );
                dao.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder.toString(),sessionDTO.getCurrentTableNames()));
            } else {
                queryBuilder.append(" SELECT DISTINCT CHECK_RECORD from ST_M_SUPPLEMENTAL_DISC_MASTER where (CHECK_RECORD IS NOT NULL OR CHECK_RECORD <> '')");
                return (List<Integer>) dao.executeSelectQuery(QueryUtil.replaceTableNames(queryBuilder.toString(),sessionDTO.getCurrentTableNames()));
            }
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return Collections.emptyList();
    }

    public void populateValues(DiscountProjectionDTO checkedDto, String value, Object fieldSelection, SessionDTO session) {

        String ccpDetailsId;
        if (checkedDto.getSupplementalLevelNo() == 5) {
            ccpDetailsId = checkedDto.getCcpDetailsSID().toString();
        } else {
            ccpDetailsId = CommonUtils.collectionToStringMethod(checkedDto.getCcpDetailIds(), false);
        }
        if (fieldSelection.equals(METHODOLOGY.getConstant().toUpperCase(Locale.ENGLISH))) {
            populateMethodologyWithFormulaDetails(checkedDto, value, session);
        } else {
            populateUpadteQuery(checkedDto, value, fieldSelection, session, ccpDetailsId);
        }

    }

    private List<Object> getProjectionList(DiscountProjectionDTO dto, ProjectionSelectionDTO projSelDTO) {
        if (projSelDTO.getSupplementalLevelNo() == 4) {
            projSelDTO.setSupplementalLevelName(Constant.PRODUCT_LABEL);
        }

        StringBuilder queryBuilder1 = new StringBuilder();
        queryBuilder1.append("SELECT ");
        String groupByQuery = StringUtils.EMPTY;
        if (projSelDTO.getSupplementalLevelNo() == 0) {
            queryBuilder1.append("CASE WHEN Count(DISTINCT SUPPROJ.METHODOLOGY) > 1 THEN 'Multiple' ELSE Max(SUPPROJ.METHODOLOGY) END AS METHODOLOGY,");
            queryBuilder1.append(" 0 AS CONTRACT_PRICE,0 AS RATE1,0 AS RATE2, 0 AS ACCESS,");
            queryBuilder1.append("0 AS PARITY,0 AS PARITY_R, 0 AS DISCOUNT,PRD.QUARTER,PRD.\"YEAR\" ");

        } else if (projSelDTO.getSupplementalLevelNo() == 3) {
            queryBuilder1.append("0 AS METHODOLGY,0 AS CONTRACT_PRICE,0 AS RATE1,0 AS RATE2, \n"
                    + "CASE WHEN Count(DISTINCT SUPPROJ.ACCESS) > 1 THEN 'Multiple' ELSE Max(SUPPROJ.ACCESS) END  AS ACCESS, \n");
            queryBuilder1.append("0 AS PARITY,0 AS PARITY_R, 0 AS DISCOUNT,PRD.QUARTER,PRD.\"YEAR\" ");

        } else if (projSelDTO.getSupplementalLevelNo() == 4) {
            queryBuilder1.append("SUPPROJ.METHODOLOGY,AVG(SUPPROJ.CONTRACT_PRICE) as PROJ_CONTRACT_PRICE, \n");
            queryBuilder1.append("AVG(SUPPROJ.DISCOUNT_RATE_1) as PROJ_DISCOUNT_RATE1, \n");
            queryBuilder1.append("AVG(SUPPROJ.DISCOUNT_RATE_2) as PROJ_DISCOUNT_RATE2, \n");
            queryBuilder1.append("SUPPROJ.ACCESS,SUPPROJ.PARITY,SUPPROJ.PARITY_REFERENCE, \n");
            queryBuilder1.append("AVG(SUPPROJ.PARITY_DISCOUNT) as PARITY_DISCOUNT,PRD.QUARTER,PRD.\"YEAR\"  \n");
            groupByQuery = ",SUPPROJ.METHODOLOGY,SUPPROJ.ACCESS,SUPPROJ.PARITY,SUPPROJ.PARITY_REFERENCE";
        }

        queryBuilder1.append(" FROM ST_M_SUPPLEMENTAL_DISC_PROJ SUPPROJ, \n");
        queryBuilder1.append("\"PERIOD\" PRD  ");

        String ccpDetailsId;
        if (projSelDTO.getSupplementalLevelNo() == 4) {
            queryBuilder1.append(" WHERE SUPPROJ.CCP_DETAILS_SID = " ).append( dto.getCcpDetailsSID()).append( " AND PRD.PERIOD_SID=SUPPROJ.PERIOD_SID \n");
        } else {
            ccpDetailsId = CommonUtils.collectionToStringMethod(dto.getCcpDetailIds(), false);
            queryBuilder1.append(" WHERE SUPPROJ.CCP_DETAILS_SID in (" ).append( ccpDetailsId ).append( ") AND PRD.PERIOD_SID=SUPPROJ.PERIOD_SID \n");
        }
        queryBuilder1.append(" group by PRD.QUARTER " ).append( groupByQuery ).append( " ,PRD.\"YEAR\" order by PRD.\"YEAR\" \n");
        return (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(queryBuilder1.toString(), projSelDTO.getSessionDTO().getCurrentTableNames()));
    }

    public ComboBox getNativeSelect(final ComboBox select) {
        try {
            select.addItem(SELECTMETHODOLOGY.getConstant());
            StringBuilder queryBuild = new StringBuilder();

            queryBuild.append("select distinct FORMULA_NAME from FORECASTING_FORMULA FF , HELPER_TABLE HT where FF.FORMULA_TYPE = HT.HELPER_TABLE_SID  \n"
                    + "AND HT.LIST_NAME = 'FORMULA_TYPE' AND HT.DESCRIPTION = 'Supplemental' AND FF.IS_ACTIVE = 1 ORDER BY FORMULA_NAME ");

            List<Object> obList = (List<Object>) CommonLogic.executeSelectQuery(queryBuild.toString());

            if (obList != null && !obList.isEmpty()) {
                for (int i = 0; i < obList.size(); i++) {
                    Object ob = obList.get(i);
                    select.addItem(ob);
                }
            }
        } catch (UnsupportedOperationException e) {
            LOGGER.error(e.getMessage());
        }
        return select;
    }

    public List<Object> loadParityLookup(SessionDTO sessionDTO, LookUpDTO lookUpDTO, boolean nativeFlag) {
        String mtValue = StringUtils.EMPTY;
        StringBuilder queryBuilder1 = new StringBuilder();
        String mtReference = getRelationshipValue(sessionDTO.getProjectionId());
        if (mtReference != null && !StringUtils.EMPTY.equalsIgnoreCase(mtReference)) {
            if ("Linked".equalsIgnoreCase(mtReference)) {
                mtValue = getMTLinked(sessionDTO.getProjectionId());
            } else {
                mtValue = sessionDTO.getMarketType();
            }

        }

        if (nativeFlag) {
            queryBuilder1.append("SELECT DISTINCT CM.CONTRACT_NAME \n");
        } else {
            queryBuilder1.append("SELECT DISTINCT PS_CNT_DET.ITEM_MASTER_SID,\n");
            queryBuilder1.append("CM.CONTRACT_MASTER_SID,CM.CONTRACT_NAME,BM.BRAND_NAME,IM.ITEM_NO,IM.ITEM_DESC,IM.ITEM_ID \n");
        }
        queryBuilder1.append("FROM CONTRACT_MASTER CM JOIN HELPER_TABLE HT ON CM.CONTRACT_TYPE = HT.HELPER_TABLE_SID \n");
        queryBuilder1.append("JOIN PS_CONTRACT PS_CNT ON CM.CONTRACT_MASTER_SID = PS_CNT.CONTRACT_MASTER_SID \n");
        queryBuilder1.append("JOIN PS_CONTRACT_DETAILS PS_CNT_DET ON PS_CNT.PS_CONTRACT_SID = PS_CNT_DET.PS_CONTRACT_SID \n");
        queryBuilder1.append("JOIN ITEM_MASTER IM ON PS_CNT_DET.ITEM_MASTER_SID = IM.ITEM_MASTER_SID \n");
        queryBuilder1.append("JOIN BRAND_MASTER BM ON IM.BRAND_MASTER_SID = BM.BRAND_MASTER_SID \n");
        queryBuilder1.append("WHERE  HT.DESCRIPTION ='" ).append( mtValue ).append( '\'');

        if (StringUtils.isNotBlank(lookUpDTO.getContractName())) {
            queryBuilder1.append("AND CM.CONTRACT_NAME like '").append(lookUpDTO.getContractName()).append("' ");
        } else if (StringUtils.isNotBlank(lookUpDTO.getBrandName())) {
            queryBuilder1.append("AND BM.BRAND_NAME like '").append(lookUpDTO.getBrandName()).append("' ");
        } else if (StringUtils.isNotBlank(lookUpDTO.getItemNo())) {
            queryBuilder1.append("AND IM.ITEM_NO like '").append(lookUpDTO.getItemNo()).append("' ");
        } else if (StringUtils.isNotBlank(lookUpDTO.getItemDesc())) {
            queryBuilder1.append("AND IM.ITEM_DESC like '").append(lookUpDTO.getItemDesc()).append("' ");
        }
        return (List<Object>) CommonLogic.executeSelectQuery(queryBuilder1.toString());
    }

    public String getRelationshipValue(int projId) {
        List<Object> list;
        String query = "select LEVEL_VALUE_REFERENCE from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID in\n"
                + "(Select CUSTOMER_HIERARCHY_SID from PROJECTION_MASTER where PROJECTION_MASTER_SID=" + projId + ")\n"
                + "and LEVEL_NAME='Market Type'";
        list = (List<Object>) CommonLogic.executeSelectQuery(query);


        return String.valueOf(list.get(0));
    }

    public String getMTLinked(int hierId) {
        String str = StringUtils.EMPTY;
        List<Object> list;
        String query = "select DESCRIPTION from HELPER_TABLE where HELPER_TABLE_SID\n"
                + "in(select RELATIONSHIP_LEVEL_VALUES from  RELATIONSHIP_LEVEL_DEFINITION where RELATIONSHIP_LEVEL_SID in (select RELATIONSHIP_LEVEL_SID\n" +
                " from PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID=" + hierId + " and Level_NAME='Market Type'))";

        list = (List<Object>) CommonLogic.executeSelectQuery(query);
        if (!list.isEmpty()) {
            str = String.valueOf(list.get(0));
        }

        return str;
    }

    public ComboBox loadParityNativeSelect(final ComboBox select, SessionDTO sessionDTO, boolean nativeFlag) {
        try {
            select.addItem(SELECT_ONE);
            List<Object> helperList;
            LookUpDTO lookUpDTO = new LookUpDTO();
            helperList = loadParityLookup(sessionDTO, lookUpDTO, nativeFlag);
            for (int i = 0; i < helperList.size(); i++) {
                if (!Constant.NULL.equalsIgnoreCase(String.valueOf(helperList.get(i))) && !StringUtils.EMPTY.equalsIgnoreCase(String.valueOf(helperList.get(i)))) {
                    select.addItem(helperList.get(i));
                }

            }
        } catch (UnsupportedOperationException e) {
            LOGGER.error(e.getMessage());
        }
        return select;
    }

    public String[] getYearAndPeriod(String period, String year) {
        StringBuilder queryBuilder1 = new StringBuilder();
        String[] periodArr = new String[3];
        queryBuilder1.append("select PERIOD_SID from \"PERIOD\" where \"YEAR\" = ").append(year);
        queryBuilder1.append(" and QUARTER =").append(period);
        List<Object> objList = (List<Object>) CommonLogic.executeSelectQuery(queryBuilder1.toString());

        if (objList != null && !objList.isEmpty()) {
            int i = 0;
            for (int j = 0; j < objList.size(); j++) {
                Object ob = objList.get(j);
                periodArr[i++] = String.valueOf(ob);
            }
        }
        return periodArr;
    }

    public void insertInParity(List<LookUpDTO> finalDtoList, int projId) {
        try {
                StringBuilder queryBuilder1 = new StringBuilder();
                SalesProjectionDAO salesDAO = new SalesProjectionDAOImpl();
            for (int i = 0; i < finalDtoList.size(); i++) {
                LookUpDTO dto = finalDtoList.get(i);
                if (projId != 0 && !StringUtils.EMPTY.equals(dto.getMethodology()) && !StringUtils.EMPTY.equals(dto.getContract()) && dto.getContractMasterSid() != 0 && dto.getItemMasterSid() != 0 && !StringUtils.EMPTY.equals(dto.getQuarterValue()) && !StringUtils.EMPTY.equals(dto.getYearValue()) && !StringUtils.EMPTY.equals(dto.getDiscount1()) && !StringUtils.EMPTY.equals(dto.getDiscount2())) {
                    queryBuilder1.append("INSERT INTO M_PARITY_LOOKUP (CONTRACT_MASTER_SID,ITEM_MASTER_SID,QUARTER,\"YEAR\",METHODOLOGY, \n");
                    queryBuilder1.append("CONTRACT_PRICE,DISCOUNT_RATE_1,DISCOUNT_RATE_2,CCP_DETAILS_SID) \n");
                    queryBuilder1.append(" VALUES (").append(dto.getContractMasterSid()).append(", ").append(dto.getItemMasterSid()).append(", ").append(dto.getQuarterValue()).append(", ").append(dto.getYearValue()).append(", '\n");
                    queryBuilder1.append(dto.getMethodology()).append("', ").append(dto.getContract()).append(", ").append(dto.getDiscount1()).append(", ").append(dto.getDiscount2()).append(", ").append(dto.getCcpDetailsSid()).append(')');
                    salesDAO.executeUpdateQuery(queryBuilder1.toString());
                }

            }

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public int getProject(int projId) {
        int value = 0;
        try {
            List<Integer> listInte = new ArrayList<>();
            List list;
            String sql = StringUtils.EMPTY;
            if (projId != 0) {
                sql = "select PROJECTION_DETAILS_SID from dbo.PROJECTION_DETAILS\n"
                        + "where PROJECTION_MASTER_SID=" + projId;
            }

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(sql);
            for (int i = 0; i < list.size(); i++) {
                listInte.add(Integer.valueOf(String.valueOf(list.get(i))));
                value = Integer.parseInt(String.valueOf(list.get(i)));
            }
        } catch (PortalException | SystemException | NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
        return value;
    }

    public void saveProjectionValues(DiscountProjectionDTO saveDto, Object propertyId, SessionDTO session, SupplementalTableLogic tableLogic) {
        if (saveDto.getSupplementalLevelNo() == 1 || saveDto.getSupplementalLevelNo() == NumericConstants.FOUR) {
            projectionSelectionDTO.setSupplementalLevelName(saveDto.getLevelName());
            projectionSelectionDTO.setCustHierarchySid(session.getCustHierarchySid());
            projectionSelectionDTO.setProdHierarchySid(session.getProdHierarchySid());
            manualSave(saveDto, CommonUtils.collectionToStringMethod(saveDto.getCcpDetailIds(), false), propertyId, session, tableLogic);
        } else {
            manualSave(saveDto, saveDto.getCcpDetailsSID().toString(), propertyId, session, tableLogic);
        }
    }

    @SuppressWarnings("empty-statement")
    public void manualSave(DiscountProjectionDTO saveDto, String projDetailsID, Object propertyId, SessionDTO session, SupplementalTableLogic tableLogic) {
        try {
            StringBuilder query = new StringBuilder();
            if (propertyId.toString().contains(METHODOLOGY.getConstant())) {
                updateMethodologyWithFormulaDetails(saveDto, propertyId, session, tableLogic);
            } else {
                query.append("update ST_M_SUPPLEMENTAL_DISC_PROJ SET ").append(saveDto.getPropertyName()).append(" = '").append(saveDto.getPropertyValue(propertyId)).append('\'');
                query.append(" where CCP_DETAILS_SID in (").append(projDetailsID).append(") ");
                query.append("AND PERIOD_SID in (select PERIOD_SID from \"PERIOD\" where \"YEAR\" = ").append(saveDto.getYear()).append(" and QUARTER = ").append(saveDto.getPeriod()).append(" )");
                dao.executeUpdateQuery(QueryUtil.replaceTableNames(query.toString(), session.getCurrentTableNames()));
                if (propertyId.toString().contains(ACCESS.getConstant())) {
                    if (saveDto.getSupplementalLevelNo() == 4) {
                        List<Object> dtoList = (List<Object>) ((Container.Indexed) tableLogic.getContainerDataSource()).getItemIds(0, tableLogic.getContainerDataSource().size());
                        for (Object obj : dtoList) {
                            DiscountProjectionDTO suppDto = (DiscountProjectionDTO) obj;
                            if (suppDto.getSupplementalLevelNo() == 5 && saveDto.getCcpDetailIds().containsAll(suppDto.getCcpDetailIds())) {
                                suppDto.addStringProperties(propertyId, saveDto.getPropertyValue(propertyId).toString());
                                tableLogic.getContainerDataSource().getContainerProperty(obj, propertyId).setValue(saveDto.getPropertyValue(propertyId));
                            }
                        }
                    } else if (saveDto.getSupplementalLevelNo() == 5) {
                        Map<String, Object> mapList = tableLogic.getExpandedTreeLevelList();
                        saveDto.setParentCcpDetailIdList(saveDto.getCcpDetailIds());
                        int ndcCount = ndcCount(session, saveDto, saveDto.getPropertyName());
                        if (ndcCount > 1) {
                            methodologyRefresher(saveDto, mapList, tableLogic, propertyId, SELECT_ONE.getConstant(), 4);
                        } else if (ndcCount == 1) {
                            methodologyRefresher(saveDto, mapList, tableLogic, propertyId, saveDto.getPropertyValue(propertyId).toString(), 4);
                        }
                    }
                }
            }

        } catch (PortalException | SystemException | Property.ReadOnlyException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public void supplementalSave(SessionDTO sessionDTO) {
        try {
            List<StringBuilder> queryList = new ArrayList<>();
            StringBuilder query = new StringBuilder();

            queryList.add(getQueryToSaveMasterTable(sessionDTO));

            query.append("  MERGE M_SUPPLEMENTAL_DISC_PROJ AS TARGET\n"
                    ).append( "   USING (\n"
                    ).append( "   SELECT PROJECTION_DETAILS_SID,\n"
                    ).append( "   PERIOD_SID,\n"
                    ).append( "   METHODOLOGY,\n"
                    ).append( "   CONTRACT_PRICE,\n"
                    ).append( "   DISCOUNT_RATE_1,\n"
                    ).append( "   DISCOUNT_RATE_2,\n"
                    ).append( "   ACCESS,\n"
                    ).append( "   PARITY,\n"
                    ).append( "   PARITY_REFERENCE,\n"
                    ).append( "   PARITY_DISCOUNT,\n"
                    ).append( "   PROJECTION_RATE,\n"
                    ).append( "   PROJECTION_SALES\n"
                    ).append( "   FROM dbo.ST_M_SUPPLEMENTAL_DISC_PROJ\n"
                    ).append( "    WHERE USER_ID=" ).append( sessionDTO.getUserId() ).append( "  AND SESSION_ID= " ).append( sessionDTO.getSessionId() ).append( " \n"
                    ).append( "   ) AS SOURCE\n"
                    ).append( "   ON (TARGET.PROJECTION_DETAILS_SID=SOURCE.PROJECTION_DETAILS_SID AND TARGET.PERIOD_SID=SOURCE.PERIOD_SID)\n"
                    ).append( "   WHEN MATCHED  \n"
                    ).append( Constant.THEN
                    ).append( "   UPDATE SET \n"
                    ).append( "   TARGET.PERIOD_SID=SOURCE.PERIOD_SID,\n"
                    ).append( "   TARGET.METHODOLOGY=SOURCE.METHODOLOGY,\n"
                    ).append( "   TARGET.CONTRACT_PRICE=SOURCE.CONTRACT_PRICE,\n"
                    ).append( "   TARGET.DISCOUNT_RATE_1=SOURCE.DISCOUNT_RATE_1,\n"
                    ).append( "   TARGET.DISCOUNT_RATE_2=SOURCE.DISCOUNT_RATE_2,\n"
                    ).append( "   TARGET.ACCESS=SOURCE.ACCESS,\n"
                    ).append( "   TARGET.PARITY=SOURCE.PARITY,\n"
                    ).append( "   TARGET.PARITY_REFERENCE=SOURCE.PARITY_REFERENCE,\n"
                    ).append( "   TARGET.PARITY_DISCOUNT=SOURCE.PARITY_DISCOUNT,\n"
                    ).append( "   TARGET.PROJECTION_RATE = SOURCE.PROJECTION_RATE,\n"
                    ).append( "   TARGET.PROJECTION_SALES=SOURCE.PROJECTION_SALES\n"
                    ).append( "   WHEN NOT MATCHED BY TARGET\n"
                    ).append( Constant.THEN
                    ).append( "   INSERT (PROJECTION_DETAILS_SID,PERIOD_SID,METHODOLOGY,CONTRACT_PRICE,DISCOUNT_RATE_1,DISCOUNT_RATE_2,ACCESS,PARITY,PARITY_REFERENCE,PARITY_DISCOUNT,PROJECTION_RATE,PROJECTION_SALES) VALUES(SOURCE.PROJECTION_DETAILS_SID,\n"
                    ).append( "   SOURCE.PERIOD_SID,\n"
                    ).append( "   SOURCE.METHODOLOGY,\n"
                    ).append( "   SOURCE.CONTRACT_PRICE,\n"
                    ).append( "   SOURCE.DISCOUNT_RATE_1,\n"
                    ).append( "   SOURCE.DISCOUNT_RATE_2,\n"
                    ).append( "   SOURCE.ACCESS,\n"
                    ).append( "   SOURCE.PARITY,\n"
                    ).append( "   SOURCE.PARITY_REFERENCE,\n"
                    ).append( "   SOURCE.PARITY_DISCOUNT,\n"
                    ).append( "   SOURCE.PROJECTION_RATE,\n"
                    ).append( "   SOURCE.PROJECTION_SALES);");
            queryList.add(query);
            query = new StringBuilder();
            query.append("MERGE M_SUPPLEMENTAL_DISC_ACTUALS AS TARGET\n"
                    ).append( "USING (SELECT PROJECTION_DETAILS_SID,PERIOD_SID,ACTUAL_SALES,ACTUAL_RATE,ACTUAL_PROJECTION_SALES,ACTUAL_PROJECTION_RATE FROM dbo.ST_M_SUPPLEMENTAL_DISC_ACTUALS\n"
                    ).append( "    WHERE  USER_ID=" ).append( sessionDTO.getUserId() ).append( " AND SESSION_ID=" ).append( sessionDTO.getSessionId() ).append( " )  AS SOURCE\n"
                    ).append( "  ON (TARGET.PROJECTION_DETAILS_SID=SOURCE.PROJECTION_DETAILS_SID AND TARGET.PERIOD_SID=SOURCE.PERIOD_SID)\n"
                    ).append( "   WHEN MATCHED\n"
                    ).append( Constant.THEN
                    ).append( "   UPDATE SET\n"
                    ).append( "   TARGET.PERIOD_SID = SOURCE.PERIOD_SID,\n"
                    ).append( "   TARGET.ACTUAL_SALES=SOURCE.ACTUAL_SALES,\n"
                    ).append( "   TARGET.ACTUAL_RATE=SOURCE.ACTUAL_RATE,\n"
                    ).append( "   TARGET.ACTUAL_PROJECTION_SALES=SOURCE.ACTUAL_PROJECTION_SALES,\n"
                    ).append( "   TARGET.ACTUAL_PROJECTION_RATE=SOURCE.ACTUAL_PROJECTION_RATE\n"
                    ).append( "    WHEN NOT MATCHED BY TARGET\n"
                    ).append( Constant.THEN
                    ).append( "   INSERT (PROJECTION_DETAILS_SID,PERIOD_SID,ACTUAL_SALES,ACTUAL_RATE,ACTUAL_PROJECTION_SALES,ACTUAL_PROJECTION_RATE) VALUES(SOURCE.PROJECTION_DETAILS_SID,\n"
                    ).append( "   SOURCE.PERIOD_SID,\n"
                    ).append( "   SOURCE.ACTUAL_SALES,\n"
                    ).append( "   SOURCE.ACTUAL_RATE,\n"
                    ).append( "   SOURCE.ACTUAL_PROJECTION_SALES,\n"
                    ).append( "   SOURCE.ACTUAL_PROJECTION_RATE);");

            queryList.add(query);
            dao.executeUpdateQuery(queryList);

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public boolean procedureCheck(SessionDTO session) {
        boolean procedureFlag = false;
        StringBuilder query = new StringBuilder();
        query.append("IF EXISTS (select 1 from ST_M_SUPPLEMENTAL_DISC_PROJ where USER_ID = " ).append( session.getUserId() ).append( " and SESSION_ID = " ).append( session.getSessionId()).append(" ) SELECT 1 ELSE SELECT 0");
        List<Object> objList = (List<Object>) CommonLogic.executeSelectQuery(query.toString());
        if (objList != null && !objList.isEmpty() && Integer.parseInt(String.valueOf(objList.get(0)))==1)   {
            procedureFlag = true;
        }
        return procedureFlag;
    }

    public void clearTemp() {
        Date tempDate = new Date();
        tempDate.setDate(tempDate.getDate() - NumericConstants.TWO);
        clearTempTables(tempDate);
    }

    public void clearTempTables(Date lastModified1) {
        try {
            final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
            String lastModified = fmt.format(lastModified1);

            String deleteQuery = "DELETE FROM dbo.ST_M_SUPPLEMENTAL_DISC_PROJ WHERE LAST_MODIFIED_DATE< '" + lastModified + "';"
                    + "DELETE FROM dbo.ST_M_SALES_PROJECTION_MASTER WHERE LAST_MODIFIED_DATE<'" + lastModified + "';";

            dao.executeUpdateQuery(deleteQuery);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public void insertInToTempTable(SessionDTO sessionDto) {
        clearTemp();
        try {
            List<StringBuilder> queryList = new ArrayList<>();
            StringBuilder query = new StringBuilder();
            final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
            Date tempDate = fmt.parse(sessionDto.getSessionDate());
            String lastModified = fmt.format(tempDate);
            query.append("INSERT INTO ST_M_SUPPLEMENTAL_DISC_MASTER(\n"
                    ).append( " PROJECTION_DETAILS_SID,\n"
                    ).append( " MARKET_TYPE,\n"
                    ).append( " ACTUAL_DISCOUNT,\n"
                    ).append( " ACTUAL_METHODOLOGY,\n"
                    ).append( " ACTUAL_CONTRACT_PRICE,\n"
                    ).append( " ACTUAL_DISCOUNT_RATE1,\n"
                    ).append( " ACTUAL_DISCOUNT_RATE2,\n"
                    ).append( " CONTRACT_END_DATE,\n"
                    ).append( " CHECK_RECORD, \n"
                    ).append( " CASH_PAID_DATE, \n"
                    ).append( Constant.USER_ID_WITH_COMMA
                    ).append( Constant.SESSION_ID_WITH_COMMA
                    ).append( " LAST_MODIFIED_DATE)\n"
                    ).append( " SELECT A.PROJECTION_DETAILS_SID,\n"
                    ).append( " A.MARKET_TYPE,\n"
                    ).append( " A.ACTUAL_DISCOUNT,\n"
                    ).append( " A.ACTUAL_METHODOLOGY,\n"
                    ).append( " A.ACTUAL_CONTRACT_PRICE,\n"
                    ).append( " A.ACTUAL_DISCOUNT_RATE1,\n"
                    ).append( " A.ACTUAL_DISCOUNT_RATE2,\n"
                    ).append( " A.CONTRACT_END_DATE,\n"
                    ).append( " A.CHECK_RECORD, \n"
                    ).append( " A.CASH_PAID_DATE,\n"
                    ).append( sessionDto.getUserId() ).append( Constant.USER_ID_WITH_COMMA
                    ).append( sessionDto.getSessionId() ).append( Constant.SESSION_ID_WITH_COMMA
                    ).append( '\'' ).append( lastModified ).append( "' LAST_MODIFIED_DATE \n"
                    ).append( " FROM dbo.M_SUPPLEMENTAL_DISC_MASTER A,\n"
                    ).append( " dbo.PROJECTION_DETAILS B\n"
                    ).append( " WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
                    ).append( " AND B.PROJECTION_MASTER_SID=" ).append( sessionDto.getProjectionId() ).append( ';');
            queryList.add(query);

            query = new StringBuilder();

            query.append("INSERT INTO dbo.ST_M_SUPPLEMENTAL_DISC_PROJ(\n"
                    ).append( " PROJECTION_DETAILS_SID,\n"
                    ).append( " PERIOD_SID,\n"
                    ).append( " METHODOLOGY,\n"
                    ).append( " CONTRACT_PRICE,\n"
                    ).append( " DISCOUNT_RATE_1,\n"
                    ).append( " DISCOUNT_RATE_2,\n"
                    ).append( " ACCESS,\n"
                    ).append( " PARITY,\n"
                    ).append( " PARITY_REFERENCE,\n"
                    ).append( " PARITY_DISCOUNT,\n"
                    ).append( " PROJECTION_RATE,\n"
                    ).append( " PROJECTION_SALES,\n"
                    ).append( Constant.USER_ID_WITH_COMMA
                    ).append( Constant.SESSION_ID_WITH_COMMA
                    ).append( " LAST_MODIFIED_DATE )\n"
                    ).append( " SELECT A.PROJECTION_DETAILS_SID,\n"
                    ).append( " A.PERIOD_SID,\n"
                    ).append( " A.METHODOLOGY,\n"
                    ).append( " A.CONTRACT_PRICE,\n"
                    ).append( " A.DISCOUNT_RATE_1,\n"
                    ).append( " A.DISCOUNT_RATE_2,\n"
                    ).append( " A.ACCESS,\n"
                    ).append( " A.PARITY,\n"
                    ).append( " A.PARITY_REFERENCE,\n"
                    ).append( " A.PARITY_DISCOUNT,\n"
                    ).append( " A.PROJECTION_RATE,\n"
                    ).append( " A.PROJECTION_SALES,\n"
                    ).append(  sessionDto.getUserId() ).append( Constant.USER_ID_WITH_COMMA
                    ).append(  sessionDto.getSessionId() ).append( Constant.SESSION_ID_WITH_COMMA
                    ).append( "	'" ).append( lastModified ).append( "' LAST_MODIFIED_DATE \n"
                    ).append( " FROM M_SUPPLEMENTAL_DISC_PROJ A,\n"
                    ).append( " dbo.PROJECTION_DETAILS B\n"
                    ).append( " WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
                    ).append( "	AND B.PROJECTION_MASTER_SID=" ).append( sessionDto.getProjectionId() ).append( ';');
            queryList.add(query);
            dao.executeUpdateQuery(queryList);
        } catch (PortalException | SystemException | ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public String getSupplementalLevelName(Integer supplementalLevelNo) {
        String tempLevelName = StringUtils.EMPTY;

        levelName = getSupplementalLevelNameList();
        for (int i = 0; i < levelName.size(); i++) {
            if (supplementalLevelNo == i) {
                tempLevelName = levelName.get(i);
                break;
            }
        }
        return tempLevelName;
    }

    public List<String> getSupplementalLevelNameList() {
        if (levelName.isEmpty()) {
            levelName.add(Constant.CUSTOMER_SMALL);
            levelName.add(Constant.CONTRACT_SMALL);
            levelName.add("Therapeutic Class");
            levelName.add(Constant.BRAND_CAPS);
            levelName.add(Constant.PRODUCT_LABEL);
        }
        return levelName;
    }

    public List<Object> getLevelDdlbList(ProjectionSelectionDTO projSel) {
        List<Object> returnList = new ArrayList<>();
        try {
            List<String> levelNameList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {

                projSel.setSupplementalLevelName(getSupplementalLevelName(i));
                String[] tableLevelName = getTableAndLevelName(projSel, false, true);
                if (isValidTableName(tableLevelName)) {
                    levelNameList.add(tableLevelName[1]);
                }
            }
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" select DISTINCT IM.THERAPEUTIC_CLASS , CCP.COMPANY_MASTER_SID,CCP.CONTRACT_MASTER_SID , CCP.ITEM_MASTER_SID, CM." 
                    ).append( levelNameList.get(0) ).append( " ,CONT." ).append( levelNameList.get(1) ).append( ",BM." ).append( levelNameList.get(3) ).append( '\n'
                    ).append( " FROM PROJECTION_DETAILS PD,CCP_DETAILS CCP,ITEM_MASTER IM,COMPANY_MASTER CM,CONTRACT_MASTER CONT,BRAND_MASTER BM \n"
                    ).append( "where CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    ).append( " and IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID  \n"
                    ).append( "and CONT.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID  \n"
                    ).append( "and CM.COMPANY_MASTER_SID =  CCP.COMPANY_MASTER_SID\n"
                    ).append( "and BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                    ).append( "and PD.PROJECTION_MASTER_SID = " ).append( projSel.getProjectionId() ).append( '\n');

            returnList.addAll((List<String>) CommonLogic.executeSelectQuery(queryBuilder.toString()));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return returnList;
    }

    public List<DiscountProjectionDTO> loadDTOList(ProjectionSelectionDTO projSelDTO, List<Object> masterData) {
        List<DiscountProjectionDTO> resultList = new ArrayList<>();
        List<Object> getNDCList = new ArrayList<>();
        int count = 0;
        if (projSelDTO.getSupplementalLevelNo() == 2 || projSelDTO.getSupplementalLevelNo() == 4) {
            count = 6;
        } else {
            count = 5;
        }

        if (projSelDTO.getSupplementalLevelNo() == 0) {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append("SELECT Distinct CM." ).append( projSelDTO.getLevelFieldSelection() 
                    ).append( ",IM.ITEM_ID+'~'+CONVERT(varchar(10), SUPMAS.CCP_DETAILS_SID)  FROM ST_M_SUPPLEMENTAL_DISC_MASTER SUPMAS, PROJECTION_DETAILS PD,CCP_DETAILS CCP,\n"
                    ).append( "COMPANY_MASTER CM,ITEM_MASTER IM WHERE\n"
                    ).append( "CCP.CCP_DETAILS_SID = SUPMAS.CCP_DETAILS_SID\n"
                    ).append( "AND CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    ).append( "AND IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n");
            getNDCList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(strQuery.toString(), projSelDTO.getSessionDTO().getCurrentTableNames()));
        }

        boolean isValid = false;

        if (masterData != null && !masterData.isEmpty()) {
            Object tempID = StringUtils.EMPTY;
            for (Object list1 : masterData) {
                final Object[] obj = (Object[]) list1;
                String levelDetails = StringUtils.EMPTY;
                if (projSelDTO.isIsFilter()) {
                    isValid = true;
                } else {
                    isValid = !tempID.equals(obj[count]);
                }

                if (isValid) {
                    DiscountProjectionDTO dto = new DiscountProjectionDTO();
                    dto.setCompanyID(Integer.valueOf(String.valueOf(obj[0])));
                    dto.setContractID(Integer.valueOf(String.valueOf(obj[1])));
                    dto.setTherapeuticID(Integer.valueOf(String.valueOf(obj[2])));
                    dto.setBrandID(String.valueOf(obj[3]));
                    dto.setNdcID(Integer.valueOf(String.valueOf(obj[4])));
                    dto.setSystemID(Integer.valueOf(String.valueOf(obj[5])));
                    dto.setGroup(String.valueOf(obj[6]));

                    for (int i = 0; i < getNDCList.size(); i++) {
                        final Object[] obNdc = (Object[]) getNDCList.get(i);
                        if (String.valueOf(obj[6]).equals(obNdc[0].toString())) {
                            levelDetails += obNdc[1] + ",";
                        }
                        if (i == getNDCList.size() - 1) {
                            levelDetails = levelDetails.substring(0, levelDetails.length() - 1);
                        }
                    }
                    dto.setParentCcpDetailIdList(projSelDTO.getParentCcpDetailIdList());
                    dto.setLevelDetails(levelDetails);
                    dto.setLevelName(String.valueOf(obj[NumericConstants.SEVEN]));
                    if (projSelDTO.getSupplementalLevelNo() == NumericConstants.THREE) {
                        dto.setActualdiscount(String.valueOf(obj[NumericConstants.TEN] == null ? StringUtils.EMPTY : obj[NumericConstants.TEN]));
                    } else if (projSelDTO.getSupplementalLevelNo() == NumericConstants.FOUR) {
                        dto.setActualdiscount(getFormattedValue(CUR_FOUR_DECIMAL, String.valueOf(obj[NumericConstants.EIGHT])));
                    }
                    dto.setCompanyIdForNdcLevel(projSelDTO.getSupplementalLevelNo() == 0 ? String.valueOf(obj[11]) : StringUtils.EMPTY);
                    if (projSelDTO.getSupplementalLevelNo() == 4) {
                        dto.setCcpDetailsSID(Integer.valueOf(String.valueOf(obj[10])));
                        dto.setCcpDetailIds(projSelDTO.getCcpDetailIdList());
                        dto.setCompanyIdForNdcLevel(String.valueOf(obj[14]));
                        dto.setItemIdForNdcLevel(String.valueOf(obj[15]));
                    }

                    SimpleDateFormat sd = new SimpleDateFormat(DATE_FORMAT.getConstant());
                    String date = String.valueOf(obj[NumericConstants.NINE]);
                    date = date.replace('-', '/');
                    try {
                        dto.setContractEndDate(String.valueOf(obj[NumericConstants.NINE] == null ? StringUtils.EMPTY : sd.format(sd.parse(date))));
                    } catch (ParseException ex) {
                        LoggerFactory.getLogger(SupplementalDiscountProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
                    }

                    dto.addBooleanProperties(Constant.CHECK, BooleanConstant.getFalseFlag());
                    if (projSelDTO.getSupplementalLevelNo() == NumericConstants.FOUR || projSelDTO.isIsFilter()) {
                        dto.setSupplementalLevelNo(projSelDTO.getSupplementalLevelNo() + 1);
                        dto.setParent(0);
                    } else {
                        dto.setParent(1);
                        dto.setSupplementalLevelNo(projSelDTO.getSupplementalLevelNo() + 1);
                    }
                    if (projSelDTO.getSupplementalLevelNo() != 1 && projSelDTO.getSupplementalLevelNo() != 2) {
                        if (projSelDTO.getSupplementalLevelNo() != 4 || (projSelDTO.getSupplementalLevelNo() == 4 && projSelDTO.isIsFilter())) {
                            dto = getCcpDetailsId(dto, projSelDTO);

                        }
                        dto = loadProjectionValues(dto, projSelDTO);
                    }
                    dto.setOnExpandTotalRow(1);
                    if (dto.getSupplementalLevelNo() == NumericConstants.FOUR || dto.getSupplementalLevelNo() == NumericConstants.FIVE) {
                        if (!StringUtils.EMPTY.equalsIgnoreCase(String.valueOf(obj[NumericConstants.ELEVEN])) && !Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.ELEVEN]))) {
                            String str;
                            if (String.valueOf(obj[NumericConstants.SEVEN]).equalsIgnoreCase(Constant.BRAND_CAPS)) {
                                str = StringUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.ELEVEN])) || Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.ELEVEN])) ? Constant.DASH : String.valueOf(obj[NumericConstants.ELEVEN]);
                            } else {
                                str = StringUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.THIRTEEN])) || Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.THIRTEEN])) ? Constant.DASH : String.valueOf(obj[NumericConstants.THIRTEEN]);
                            }
                            if (Constant.STRING_ONE.equals(str)) {
                                dto.addBooleanProperties(Constant.CHECK, BooleanConstant.getTrueFlag());
                            }

                        } else {
                            dto.addBooleanProperties(Constant.CHECK, BooleanConstant.getFalseFlag());
                        }
                    } else if (dto.getSupplementalLevelNo() == 1) {
                        if (!StringUtils.EMPTY.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TEN])) && !Constant.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TEN]))) {
                            String str = String.valueOf(obj[NumericConstants.TEN]).equals(StringUtils.EMPTY) && String.valueOf(obj[NumericConstants.FOURTEEN]).equalsIgnoreCase(Constant.NULL) ? Constant.DASH : String.valueOf(obj[NumericConstants.TEN]);
                            if (Constant.STRING_ONE.equals(str)) {
                                dto.addBooleanProperties(Constant.CHECK, BooleanConstant.getTrueFlag());
                            }
                        } else {
                            dto.addBooleanProperties(Constant.CHECK, BooleanConstant.getFalseFlag());
                        }
                    }
                    resultList.add(dto);
                    tempID = obj[count];
                }
            }
        }
        return resultList;
    }

    public int getIndexValue(ProjectionSelectionDTO projSelDTO) {
        List<Object> objList = getQueryToHitMasterTable(projSelDTO, true, true);
        int count = 0;
        if (objList != null && !objList.isEmpty()) {
            Object ob = objList.get(0);
            count = Integer.parseInt(String.valueOf(ob));
        }
        return count;
    }

    private boolean isValidTableName(String[] tableLevelName) {
        boolean validationFlag = false;
        for (String string : tableLevelName) {
            if (StringUtils.isNotBlank(string)) {
                validationFlag = true;
            }
        }
        return validationFlag;
    }

    public List<List> callParityProcedure(List<LookUpDTO> dtoList, String[] periodList, SessionDTO sessionDto) {
        List<List> returnList = new ArrayList<>();
        final List<LookUpDTO> insertDtoList = new ArrayList<>();
        List<String> emptyNdcList = new ArrayList<>();
        List<List> emptyQuarterList = new ArrayList<>();
        List<String> emptyQuarterNdcName = new ArrayList<>();

        
        /*quarterEmptyNdc and quartername*/
        /*insertlist*/
        if (!dtoList.isEmpty()) {

            for (int i = 0; i < dtoList.size(); i++) {
                List<String> quarList = new ArrayList<>();
                LookUpDTO lookUpDto = dtoList.get(i);
                Object[] orderedArgs = {lookUpDto.getContractMasterSid(), lookUpDto.getItemMasterSid(), periodList[0],
                    periodList[periodList.length - 1], sessionDto.getUserId(), sessionDto.getSessionId()};
                List<Object[]> prcList = CommonLogic.callProcedure(Constant.PARITY_M_LOOKUP_PRC, orderedArgs);

                if (prcList != null) {
                    for (Object list1 : prcList) {
                        final Object[] obj = (Object[]) list1;
                        LookUpDTO prclookUpDto = new LookUpDTO();
                        if (obj[NumericConstants.TWO] != null && !Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWO])) && !StringUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.TWO]))) {
                            prclookUpDto.setQuarterValue(String.valueOf(obj[0]));
                            prclookUpDto.setYearValue(String.valueOf(obj[1]));
                            prclookUpDto.setMethodology(String.valueOf(obj[NumericConstants.TWO]));
                            prclookUpDto.setContract(String.valueOf(obj[NumericConstants.THREE]));
                            prclookUpDto.setDiscount1(String.valueOf(obj[NumericConstants.FOUR]));
                            prclookUpDto.setDiscount2(String.valueOf(obj[NumericConstants.FIVE]));
                            prclookUpDto.setContractMasterSid(lookUpDto.getContractMasterSid());
                            prclookUpDto.setItemMasterSid(lookUpDto.getItemMasterSid());
                            prclookUpDto.setItemNo(lookUpDto.getItemNo());
                            if (sessionDto.getCcpDetailsSid()!= 0) {
                                prclookUpDto.setCcpDetailsSid(sessionDto.getCcpDetailsSid());
                            } else {
                                int currentProjectionId = getProjectionValue(lookUpDto.getItemNo(), sessionDto);
                                if (currentProjectionId != 0) {
                                    prclookUpDto.setCcpDetailsSid(currentProjectionId);
                                }
                            }
                            insertDtoList.add(prclookUpDto);
                        } else {
                            quarList.add(Constant.Q + String.valueOf(obj[0]) + " " + String.valueOf(obj[1]));
                        }
                    }
                    if (prcList.size() == quarList.size()) {
                        emptyNdcList.add(lookUpDto.getItemNo());
                    } else {
                        if (!quarList.isEmpty()) {
                        emptyQuarterList.add(quarList);
                        emptyQuarterNdcName.add(lookUpDto.getItemNo());
                    }

                }

            }

        }
        }

        returnList.add(insertDtoList);
        returnList.add(emptyNdcList);
        returnList.add(emptyQuarterList);
        returnList.add(emptyQuarterNdcName);
        return returnList;
    }

    public int getProjectionValue(String levelno, SessionDTO sessionDto) {
        int ccpDetailsId = 0;
        String query;
        List list;

        query = "select CCP_DETAILS_SID from ST_M_SUPPLEMENTAL_DISC_MASTER \n"
                + " CCP_DETAILS_SID in(\n"
                + "select CCP_DETAILS_SID from CCP_DETAILS where ITEM_MASTER_SID in (select ITEM_MASTER_SID from ITEM_MASTER where  ITEM_NO in('" + levelno + "') ))";
        list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDto.getCurrentTableNames()));

        for (int i = 0; i < list.size(); i++) {

            ccpDetailsId = Integer.parseInt(String.valueOf(list.get(i)));

        }

        return ccpDetailsId;
    }

    private List<Object> getFilterCount(ProjectionSelectionDTO projSelDTO) {
        StringBuilder query = new StringBuilder();
        String tempGroupBy = StringUtils.EMPTY;
        String tempTable = StringUtils.EMPTY;
        String tempwerCondition = StringUtils.EMPTY;
        if (projSelDTO.getSupplementalLevelNo() == 0) {
            query.append("SELECT Count(DISTINCT CCP.COMPANY_MASTER_SID) ");
            tempGroupBy = " GROUP by CCP.COMPANY_MASTER_SID ";
        } else if (projSelDTO.getSupplementalLevelNo() == 1) {
            query.append("SELECT Count(Distinct CCP.CONTRACT_MASTER_SID) ");
            tempGroupBy = " GROUP by CCP.COMPANY_MASTER_SID , CCP.CONTRACT_MASTER_SID ";
        } else if (projSelDTO.getSupplementalLevelNo() == NumericConstants.THREE) {
            query.append("SELECT Count(Distinct BM.BRAND_MASTER_SID) ");
            tempTable = ",ITEM_MASTER IM,BRAND_MASTER BM ";
            tempwerCondition = "AND IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n"
                    + "AND BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID";
            tempGroupBy = " GROUP by BM.BRAND_MASTER_SID,CCP.CONTRACT_MASTER_SID,CCP.COMPANY_MASTER_SID";
        } else if (projSelDTO.getSupplementalLevelNo() >= NumericConstants.TWO) {
            query.append("SELECT Count(Distinct CCP.ITEM_MASTER_SID) ");
            tempGroupBy = " GROUP by CCP.COMPANY_MASTER_SID , CCP.CONTRACT_MASTER_SID,CCP.ITEM_MASTER_SID ";
        }
        query.append("FROM CCP_DETAILS CCP,ST_M_SUPPLEMENTAL_DISC_PROJ SDP" ).append( tempTable ).append( " WHERE CCP.CCP_DETAILS_SID = SDP.CCP_DETAILS_SID \n"
                ).append( tempwerCondition);

        query.append(tempGroupBy);
        return (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query.toString(), projSelDTO.getSessionDTO().getCurrentTableNames()));
    }

    private DiscountProjectionDTO getCcpDetailsId(DiscountProjectionDTO dto, ProjectionSelectionDTO projSelDTO) {
        StringBuilder query = new StringBuilder();

        query.append("select Distinct CONVERT(varchar(10), SUP.CCP_DETAILS_SID)  from CCP_DETAILS CCP,\n"
                + "ST_M_SUPPLEMENTAL_DISC_MASTER SUP WHERE \n"
                + "SUP.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n");
        if (projSelDTO.getSupplementalLevelNo() == 0) {
            query.append(" AND CCP.COMPANY_MASTER_SID = " ).append( dto.getSystemID() ).append( " \n");
        } else if (projSelDTO.getSupplementalLevelNo() == 3) {
            query.append(" AND CCP.COMPANY_MASTER_SID = " ).append( dto.getCompanyID() ).append( " \n");
            query.append(" AND CCP.CONTRACT_MASTER_SID = " ).append( dto.getContractID());
        }
        List<String> value = (List<String>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query.toString(), projSelDTO.getSessionDTO().getCurrentTableNames()));
        dto.setCcpDetailIds(value);

        return dto;
    }

    public List<Object> getUpdateRecord(List<String> projectionDetailsList, String userId, String sessionId) {
        List<Object> queryList = new ArrayList<>();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            String projectionDetailsId = StringUtils.EMPTY;
            if (!projectionDetailsList.isEmpty()) {
                projectionDetailsId = CommonUtils.collectionToStringMethod(projectionDetailsList, false);
            }
            if (!StringUtils.EMPTY.equals(projectionDetailsId) && !Constant.NULL.equals(projectionDetailsId)) {
                queryBuilder.append("Select Distinct CHECK_RECORD,CCP_DETAILS_SID from ST_M_SUPPLEMENTAL_DISC_MASTER where CCP_DETAILS_SID in(" 
                        ).append( projectionDetailsId ).append( ") and CHECK_RECORD=1 and USER_ID= " ).append( userId ).append( " and SESSION_ID= " ).append( sessionId ).append( StringUtils.EMPTY);
                queryList = (List<Object>) CommonLogic.executeSelectQuery(queryBuilder.toString());
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return queryList;
    }

    private void updateMethodologyWithFormulaDetails(DiscountProjectionDTO saveDto, Object propertyId, SessionDTO session, SupplementalTableLogic tableLogic) {
        String[] selectedLevelDetails = saveDto.getLevelDetails().split(",");
        int count = saveDto.getSupplementalLevelNo() == 1 ? selectedLevelDetails.length : 1;
        for (int i = 0; i < count; i++) {
            try {
                StringBuilder query = new StringBuilder();
                String[] ndcLevelValues = {saveDto.getItemIdForNdcLevel(), saveDto.getProjectionDetailsSid().toString()};
                String[] tempStr = saveDto.getSupplementalLevelNo() == 1 ? selectedLevelDetails[i].split("~") : (ndcLevelValues);
                query.append("SELECT CONTRACT_PRICE_1,\n"
                        ).append( " REBATE_PERCENT_1,\n"
                        ).append( " REBATE_PERCENT_2 \n"
                        ).append( " FROM   FORMULA_DETAILS_MASTER\n"
                        ).append( " WHERE  Datepart(mm, START_DATE) <= " ).append( ((saveDto.getPeriod() * 3) - 2) ).append( " \n"
                        ).append( " AND Datepart(mm, END_DATE) >= " ).append( (saveDto.getPeriod() * 3) ).append( '\n'
                        ).append( " AND Datepart(YY, START_DATE) <= " ).append( saveDto.getYear() ).append( " \n"
                        ).append( " AND Datepart(YY, END_DATE) >= " ).append( saveDto.getYear() ).append( '\n'
                        ).append( " AND COMPANY_ID = '" ).append( saveDto.getCompanyIdForNdcLevel() ).append( "'\n"
                        ).append( " AND ITEM_ID = '" ).append( tempStr[0] ).append( "' \n"
                        ).append( " AND FORMULA_DESC = '" ).append( saveDto.getPropertyValue(propertyId) ).append( "'\n");
                List<Object> list = (List<Object>) dao.executeSelectQuery(query.toString());
                String tempPropertyId = propertyId.toString().replace(METHODOLOGY.getConstant(), StringUtils.EMPTY);
                boolean[] containerPropertyCheck = new boolean[3];
                containerPropertyCheck[0] = tableLogic.getContainerDataSource().getContainerPropertyIds().contains(tempPropertyId + Constant.CONTRACT_PRICE_PROPERTY);
                containerPropertyCheck[1] = tableLogic.getContainerDataSource().getContainerPropertyIds().contains(tempPropertyId + Constant.DISCOUNT_ONE);
                containerPropertyCheck[2] = tableLogic.getContainerDataSource().getContainerPropertyIds().contains(tempPropertyId + Constant.DISCOUNT_TWO);
                Map<String, Object> mapList = tableLogic.getExpandedTreeLevelList();
                List<Object> currentContainerList = (List<Object>) ((Container.Indexed) tableLogic.getContainerDataSource()).getItemIds(0, tableLogic.getContainerDataSource().size());
                if (list != null && !list.isEmpty()) {
                    Object[] ob = (Object[]) list.get(0);
                    methodologyUpdate(saveDto, session, ob, propertyId, tempStr, false);
                    updateFieldsBasedOnMethodology(tempPropertyId, tableLogic, ob, currentContainerList, propertyId, containerPropertyCheck, saveDto, tempStr);
                } else {
                    Object[] ob = {0, 0, 0};
                    methodologyUpdate(saveDto, session, ob, propertyId, tempStr, false);
                    updateFieldsBasedOnMethodology(tempPropertyId, tableLogic, ob, currentContainerList, propertyId, containerPropertyCheck, saveDto, tempStr);
                }
                if (saveDto.getSupplementalLevelNo() == 5) {
                    int ndcCount = ndcCount(session, saveDto, "METHODOLOGY");
                    if (ndcCount > 1) {
                        methodologyRefresher(saveDto, mapList, tableLogic, propertyId, SELECTMETHODOLOGY.getConstant(), 1);
                    } else if (ndcCount == 1) {
                        methodologyRefresher(saveDto, mapList, tableLogic, propertyId, saveDto.getPropertyValue(propertyId).toString(), 1);
                    }
                }
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

    private void populateMethodologyWithFormulaDetails(DiscountProjectionDTO saveDto, String value, SessionDTO session) {

        String[] selectedLevelDetails = saveDto.getLevelDetails().split(",");
        int count = saveDto.getSupplementalLevelNo() == 1 ? selectedLevelDetails.length : 1;
        List<Object> finalList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            try {
                StringBuilder query = new StringBuilder();
                String[] ndcLevelValues = {saveDto.getItemIdForNdcLevel(), saveDto.getCcpDetailsSID().toString()};
                String[] tempStr = saveDto.getSupplementalLevelNo() == 1 ? selectedLevelDetails[i].split("~") : (ndcLevelValues);
                query.append("SELECT CONTRACT_PRICE_1,REBATE_PERCENT_1,REBATE_PERCENT_2,Datepart(mm, START_DATE) as START_MONTH,Datepart(mm, END_DATE) AS END_MONTH,Datepart(YY, START_DATE) AS START_YEAR,"
                        ).append( " Datepart(YY, END_DATE)  AS END_YEAR\n"
                        ).append( " FROM   FORMULA_DETAILS_MASTER\n"
                        ).append( " WHERE  "
                        ).append( " COMPANY_ID = '" ).append( saveDto.getCompanyIdForNdcLevel() ).append( "'\n"
                        ).append( " AND ITEM_ID = '" ).append( tempStr[0] ).append( "' \n"
                        ).append( " AND FORMULA_DESC = '" ).append( value ).append( "'\n");
                List<Object> list = (List<Object>) dao.executeSelectQuery(query.toString());
                if (list != null && !list.isEmpty()) {
                    Object[] ob = (Object[]) list.get(0);

                    boolean populateDateFlag = Integer.parseInt(ob[NumericConstants.FIVE].toString()) <= saveDto.getStartYear() && Integer.parseInt(ob[NumericConstants.SIX].toString()) >= saveDto.getEndYear()
                            && HeaderUtils.getQuarter(Integer.parseInt(ob[NumericConstants.THREE].toString())) <= (saveDto.getStartPeriod()) && HeaderUtils.getQuarter(Integer.parseInt(ob[NumericConstants.FOUR].toString())) >= saveDto.getEndPeriod();
                    if (populateDateFlag) {
                        methodologyUpdate(saveDto, session, ob, value, tempStr, true);
                    } else {
                        //Used to update the methodology value for all the selected year
                        Object[] defaultVaues = {0, 0, 0};
                        methodologyUpdate(saveDto, session, defaultVaues, value, tempStr, true);
                        List<Object> formulaList = getDataList(HeaderUtils.getQuarter(Integer.parseInt(ob[NumericConstants.THREE].toString())), Integer.parseInt(ob[NumericConstants.FIVE].toString()),
                                HeaderUtils.getQuarter(Integer.parseInt(ob[NumericConstants.FOUR].toString())), Integer.parseInt(ob[NumericConstants.SIX].toString()), false);
                        List<Object> selectionList = getDataList(saveDto.getStartPeriod(), saveDto.getStartYear(), saveDto.getEndPeriod(), saveDto.getEndYear(), false);
                        for (Object object1 : formulaList) {
                            for (Object object2 : selectionList) {
                                if (object1.toString().equalsIgnoreCase(object2.toString())) {
                                    finalList.add(object2);
                                    break;
                                }
                            }
                        }
                        //used to update the contract and Discount 1,2 values for selected methodology
                        if (!finalList.isEmpty()) {
                            String[] dateValue = finalList.get(0).toString().split("-");
                            saveDto.setStartYear(Integer.valueOf(dateValue[0]));
                            saveDto.setStartPeriod(Integer.valueOf(dateValue[1]));
                            dateValue = finalList.get(finalList.size() - 1).toString().split("-");
                            saveDto.setEndYear(Integer.valueOf(dateValue[0]));
                            saveDto.setEndPeriod(Integer.valueOf(dateValue[1]));
                            methodologyUpdate(saveDto, session, ob, value, tempStr, true);
                        }
                    }
                } else {
                    Object[] ob = {0, 0, 0};
                    methodologyUpdate(saveDto, session, ob, value, tempStr, true);
                }
            } catch (PortalException | SystemException | NumberFormatException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public static List<Object> getDataList(int startPeriod, int startYear, int endPeriod, int endYear, boolean populateFlag) {
        int lastPr = NumericConstants.FOUR;
        int startPr = startPeriod;
        List<Object> dmap = new ArrayList<>();
        for (int yr = startYear; yr <= endYear; yr++) {
            if (yr == endYear) {
                lastPr = endPeriod;
            }
            for (int pr = startPr; pr <= lastPr; pr++) {
                startPr = 1;
                dmap.add(populateFlag ? (Constant.Q_SMALL + pr + yr + METHODOLOGY.getConstant()) : (yr + "-" + pr));
            }
        }
        return dmap;
    }

    private void populateUpadteQuery(DiscountProjectionDTO checkedDto, String value, Object fieldSelection, SessionDTO session, String ccpDetailsId) {
        StringBuilder queryBuilder1 = new StringBuilder();
        queryBuilder1.append("update ST_M_SUPPLEMENTAL_DISC_PROJ SET " ).append( fieldSelection ).append( " = '" ).append( value ).append( "' where CCP_DETAILS_SID IN (" ).append( ccpDetailsId ).append( ')');
        
        queryBuilder1.append("AND PERIOD_SID in (SELECT PERIOD_SID FROM \"PERIOD\"  where PERIOD_SID in\n");

        queryBuilder1.append("   (SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" >= ").append(checkedDto.getStartYear()).append(" and \"YEAR\" <= ").append(checkedDto.getEndYear()).append("   \n");

        queryBuilder1.append("   And PERIOD_SID not in(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" = ").append(checkedDto.getStartYear()).append(" and QUARTER <").append(checkedDto.getStartPeriod()).append(")  \n");

        queryBuilder1.append("   and PERIOD_SID not in(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" = ").append(checkedDto.getEndYear()).append(" and QUARTER > ").append(checkedDto.getEndPeriod()).append(" )))  \n");
        try {
            dao.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder1.toString(), session.getCurrentTableNames()));
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private void methodologyUpdate(DiscountProjectionDTO saveDto, SessionDTO session, Object[] ob, Object propertyId, String[] tempStr, boolean populateFlag) {
        StringBuilder query = new StringBuilder();

        String selectedValue = populateFlag ? propertyId.toString() : String.valueOf(saveDto.getPropertyValue(propertyId));
        query.append("UPDATE ST_M_SUPPLEMENTAL_DISC_PROJ SET METHODOLOGY = '" ).append( selectedValue ).append( "' ,\n"
                + " CONTRACT_PRICE = " ).append( ob[0] ).append( ",\n"
                + " DISCOUNT_RATE_1 = " ).append( ob[1] ).append( ",\n"
                + " DISCOUNT_RATE_2 = " ).append( ob[NumericConstants.TWO] ).append( " \n"
                + " FROM   ST_M_SUPPLEMENTAL_DISC_PROJ \n"
                + " WHERE  CCP_DETAILS_SID IN ( " ).append( tempStr[1] ).append( " )\n"
                + " AND PERIOD_SID IN ");

        if (populateFlag) {
            query.append("   (SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" >= ").append(saveDto.getStartYear()).append(" and \"YEAR\" <= ").append(saveDto.getEndYear()).append("   \n");

            query.append("   And PERIOD_SID not in(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" = ").append(saveDto.getStartYear()).append(" and QUARTER <").append(saveDto.getStartPeriod()).append(")  \n");

            query.append("   and PERIOD_SID not in(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\" = ").append(saveDto.getEndYear()).append(" and QUARTER > ").append(saveDto.getEndPeriod()).append(" ))  \n");
        } else {
            query.append(" (SELECT PERIOD_SID\n"
                    + " FROM   \"PERIOD\"\n"
                    + " WHERE  \"YEAR\" = " ).append( saveDto.getYear() ).append( " \n"
                    + " AND QUARTER = " ).append( saveDto.getPeriod() ).append( ") \n");
        }
        try {
            dao.executeUpdateQuery(QueryUtil.replaceTableNames(query.toString(), session.getCurrentTableNames()));
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public int ndcCount(SessionDTO session, DiscountProjectionDTO saveDto, String columnName) {
        List<Object> methodologyCount = new ArrayList<>();
        try {
            StringBuilder queryToCheckNdc = new StringBuilder();
            queryToCheckNdc.append("select DISTINCT " ).append( columnName ).append( " from ST_M_SUPPLEMENTAL_DISC_PROJ WHERE  CCP_DETAILS_SID IN ( " ).append( CommonUtils.collectionToStringMethod(saveDto.getParentCcpDetailIdList(), false) ).append( " )\n"
                    ).append( " AND PERIOD_SID IN  (SELECT PERIOD_SID\n"
                    ).append( " FROM   \"PERIOD\"\n"
                    ).append( " WHERE  \"YEAR\" = " ).append( saveDto.getYear() ).append( " \n"
                    ).append( " AND QUARTER = " ).append( saveDto.getPeriod() ).append( ") \n");
            methodologyCount = (List<Object>) dao.executeSelectQuery(QueryUtil.replaceTableNames(queryToCheckNdc.toString(), session.getCurrentTableNames()));
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return !methodologyCount.isEmpty() ? methodologyCount.size() : 0;
    }

    private void methodologyRefresher(DiscountProjectionDTO saveDto, Map<String, Object> mapList, SupplementalTableLogic tableLogic, Object propertyId, String value, int levelNo) {
        for (Map.Entry<String, Object> entry : mapList.entrySet()) {
            DiscountProjectionDTO itemDtoExpanded = (DiscountProjectionDTO) entry.getValue();
            if (itemDtoExpanded.getSupplementalLevelNo() == levelNo && itemDtoExpanded.getCcpDetailIds().containsAll(saveDto.getParentCcpDetailIdList())) {
                Object tempId = tableLogic.getcurrentTreeData(entry.getKey());
                itemDtoExpanded.addStringProperties(propertyId, value);
                if (tempId != null) {
                    tableLogic.getContainerDataSource().getContainerProperty(tempId, propertyId).setValue(value);
                }
            }
        }
    }

    private void updateFieldsBasedOnMethodology(String tempPropertyId, SupplementalTableLogic tableLogic, Object[] ob, List<Object> currentContainerList, Object propertyId, boolean[] containerPropertyCheck,
            DiscountProjectionDTO saveDto, String[] tempStr) {
        if (saveDto.getSupplementalLevelNo() == NumericConstants.FIVE) {
            if (containerPropertyCheck[0]) {
                tableLogic.getContainerDataSource().getContainerProperty(saveDto, tempPropertyId + Constant.CONTRACT_PRICE_PROPERTY).setValue(ob[0].toString());
            }
            if (containerPropertyCheck[1]) {
                tableLogic.getContainerDataSource().getContainerProperty(saveDto, tempPropertyId + Constant.DISCOUNT_ONE).setValue(ob[1].toString());
            }
            if (containerPropertyCheck[NumericConstants.TWO]) {
                tableLogic.getContainerDataSource().getContainerProperty(saveDto, tempPropertyId + Constant.DISCOUNT_TWO).setValue(ob[NumericConstants.TWO].toString());
            }
        } else {
            for (Object dto1 : currentContainerList) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) dto1;
                if (dto.getSupplementalLevelNo() == NumericConstants.FIVE && dto.getCompanyIdForNdcLevel().equals(saveDto.getCompanyIdForNdcLevel())
                        && String.valueOf(tempStr[0]).equals(dto.getItemIdForNdcLevel())) {
                    tableLogic.getContainerDataSource().getContainerProperty(dto1, propertyId).setValue(saveDto.getPropertyValue(propertyId));
                    if (containerPropertyCheck[0]) {
                        tableLogic.getContainerDataSource().getContainerProperty(dto1, tempPropertyId + Constant.CONTRACT_PRICE_PROPERTY).setValue(ob[0].toString());
                    }
                    if (containerPropertyCheck[1]) {
                        tableLogic.getContainerDataSource().getContainerProperty(dto1, tempPropertyId + Constant.DISCOUNT_ONE).setValue(ob[1].toString());
                    }
                    if (containerPropertyCheck[NumericConstants.TWO]) {
                        tableLogic.getContainerDataSource().getContainerProperty(dto1, tempPropertyId + Constant.DISCOUNT_TWO).setValue(ob[NumericConstants.TWO].toString());
                    }
                }
            }
        }
    }

    private StringBuilder getQueryToSaveMasterTable(SessionDTO sessionDTO) {
        StringBuilder query = new StringBuilder();

        query.append("MERGE M_SUPPLEMENTAL_DISC_MASTER AS TARGET\n"
                ).append( "   USING (\n"
                ).append( "   SELECT PROJECTION_DETAILS_SID,\n"
                ).append( "   MARKET_TYPE,\n"
                ).append( "   ACTUAL_DISCOUNT,\n"
                ).append( "   ACTUAL_METHODOLOGY,\n"
                ).append( "   ACTUAL_CONTRACT_PRICE,\n"
                ).append( "   ACTUAL_DISCOUNT_RATE1,\n"
                ).append( "   ACTUAL_DISCOUNT_RATE2,\n"
                ).append( "   CONTRACT_END_DATE,\n"
                ).append( "   CHECK_RECORD,\n"
                ).append( "   CASH_PAID_DATE \n"
                ).append( "   FROM ST_M_SUPPLEMENTAL_DISC_MASTER\n"
                ).append( "   WHERE USER_ID=" ).append( sessionDTO.getUserId() ).append( " AND SESSION_ID=" ).append( sessionDTO.getSessionId() ).append( " \n"
                ).append( "   ) AS SOURCE\n"
                ).append( "   ON (TARGET.PROJECTION_DETAILS_SID=SOURCE.PROJECTION_DETAILS_SID)\n"
                ).append( "   WHEN MATCHED\n"
                ).append( Constant.THEN
                ).append( "   UPDATE SET\n"
                ).append( "   TARGET.MARKET_TYPE=SOURCE.MARKET_TYPE,\n"
                ).append( "   TARGET.ACTUAL_DISCOUNT=SOURCE.ACTUAL_DISCOUNT,\n"
                ).append( "   TARGET.ACTUAL_METHODOLOGY=SOURCE.ACTUAL_METHODOLOGY,\n"
                ).append( "   TARGET.ACTUAL_CONTRACT_PRICE=SOURCE.ACTUAL_CONTRACT_PRICE,\n"
                ).append( "   TARGET.ACTUAL_DISCOUNT_RATE1=SOURCE.ACTUAL_DISCOUNT_RATE1,\n"
                ).append( "   TARGET.ACTUAL_DISCOUNT_RATE2=SOURCE.ACTUAL_DISCOUNT_RATE2,\n"
                ).append( "   TARGET.CONTRACT_END_DATE=SOURCE.CONTRACT_END_DATE,\n"
                ).append( "   TARGET.CHECK_RECORD=SOURCE.CHECK_RECORD, \n"
                ).append( "  TARGET.CASH_PAID_DATE =SOURCE.CASH_PAID_DATE \n"
                ).append( "   WHEN NOT MATCHED BY TARGET\n"
                ).append( Constant.THEN
                ).append( " INSERT( PROJECTION_DETAILS_SID,\n"
                ).append( "          MARKET_TYPE,\n"
                ).append( "          ACTUAL_DISCOUNT,\n"
                ).append( "          CASH_PAID_DATE,\n"
                ).append( "          ACTUAL_METHODOLOGY,\n"
                ).append( "          ACTUAL_CONTRACT_PRICE,\n"
                ).append( "          ACTUAL_DISCOUNT_RATE1,\n"
                ).append( "          ACTUAL_DISCOUNT_RATE2,\n"
                ).append( "          CONTRACT_END_DATE,\n"
                ).append( "          CHECK_RECORD )\n"
                ).append( "  VALUES(SOURCE.PROJECTION_DETAILS_SID,\n"
                ).append( "         SOURCE.MARKET_TYPE,\n"
                ).append( "         SOURCE.ACTUAL_DISCOUNT,\n"
                ).append( "         SOURCE.CASH_PAID_DATE,\n"
                ).append( "         SOURCE.ACTUAL_METHODOLOGY,\n"
                ).append( "         SOURCE.ACTUAL_CONTRACT_PRICE,\n"
                ).append( "         SOURCE.ACTUAL_DISCOUNT_RATE1,\n"
                ).append( "         SOURCE.ACTUAL_DISCOUNT_RATE2,\n"
                ).append( "         SOURCE.CONTRACT_END_DATE,\n"
                ).append( "         SOURCE.CHECK_RECORD ); ");

        return query;
    }

    public List<String> getProjectionDetailsSid(SessionDTO session) {
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT DISTINCT PROJECTION_DETAILS_SID from PROJECTION_DETAILS WHERE PROJECTION_MASTER_SID = " ).append( session.getProjectionId());
            return (List<String>) dao.executeSelectQuery(query.toString());
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return Collections.emptyList();
    }

    /**
     * Used to copy the data from master table to temp for supplemental screen
     *
     * @param session
     * @param projectionDetailsSids
     */
    public void masterToTempCopy(SessionDTO session, String projectionDetailsSids) {
        try {
            List<StringBuilder> strList = new ArrayList<>();
            String[] tableName = new String[NumericConstants.TWO];
            String columnNames = StringUtils.EMPTY;
            for (int i = 0; i < NumericConstants.THREE; i++) {
                StringBuilder masterQuery = new StringBuilder();
                if (i == 0) {
                    tableName[0] = "ST_M_SUPPLEMENTAL_DISC_MASTER";
                    tableName[1] = "M_SUPPLEMENTAL_DISC_MASTER";
                    columnNames = " PROJECTION_DETAILS_SID, MARKET_TYPE, ACTUAL_DISCOUNT, ACTUAL_METHODOLOGY, ACTUAL_CONTRACT_PRICE, ACTUAL_DISCOUNT_RATE1,\n"
                            + "ACTUAL_DISCOUNT_RATE2, CONTRACT_END_DATE, CHECK_RECORD, CASH_PAID_DATE, ";
                } else if (i == 1) {
                    tableName[0] = "ST_M_SUPPLEMENTAL_DISC_PROJ";
                    tableName[1] = "M_SUPPLEMENTAL_DISC_PROJ";
                    columnNames = " PROJECTION_DETAILS_SID,PERIOD_SID,METHODOLOGY,CONTRACT_PRICE,DISCOUNT_RATE_1,DISCOUNT_RATE_2,ACCESS,PARITY,\n"
                            + "PARITY_REFERENCE,PARITY_DISCOUNT,PROJECTION_RATE,PROJECTION_SALES, ";
                } else if (i == NumericConstants.TWO) {
                    tableName[0] = "ST_M_SUPPLEMENTAL_DISC_ACTUALS";
                    tableName[1] = "M_SUPPLEMENTAL_DISC_ACTUALS";
                    columnNames = " PROJECTION_DETAILS_SID,PERIOD_SID,ACTUAL_SALES,ACTUAL_RATE,ACTUAL_PROJECTION_SALES,ACTUAL_PROJECTION_RATE, ";
                }

                masterQuery.append("INSERT INTO " ).append( tableName[0] ).append( " (" ).append( columnNames ).append( " USER_ID,SESSION_ID) \n"
                        + "(SELECT " ).append( columnNames ).append( session.getUserId() ).append( ',' ).append( session.getSessionId() ).append( " from " ).append( tableName[1] ).append( " where PROJECTION_DETAILS_SID in (" ).append( projectionDetailsSids ).append( ")) ");
                strList.add(masterQuery);
            }
            dao.executeUpdateQuery(strList);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private String getCustomerFilterQuery(ProjectionSelectionDTO projSelDTO) {
        String customerFilter = StringUtils.EMPTY;
        ProjectionSelectionDTO dto = new ProjectionSelectionDTO();
        dto.setSupplementalList(projSelDTO.getSupplementalList());
        dto.setSupplementalLevelName(Constant.CUSTOMER_SMALL);
        dto.setSupplementalLevelNo(0);
        String[] tableLevelName = getTableAndLevelName(dto, false, false);
        if (!projSelDTO.getGroupFilter().isEmpty() && projSelDTO.getSupplementalLevelNo() == 0) {
            customerFilter = " and CM." + tableLevelName[1] + " LIKE '%" + projSelDTO.getGroupFilter() + "%'";
        } else if (!projSelDTO.getGroupFilter().isEmpty() && projSelDTO.isIsFilter()) {
            customerFilter = "and CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID and CM." + tableLevelName[1] + " LIKE '%" + projSelDTO.getGroupFilter() + "%'";
        }
        return customerFilter;
    }

    public List<String> getNativeDropDown() {
        List<String> dropDownList = new ArrayList<>();
        try {

            StringBuilder queryBuild = new StringBuilder();

            dropDownList.add("- Select Methodology -");
            queryBuild.append("select distinct FORMULA_NAME from FORECASTING_FORMULA FF , HELPER_TABLE HT where FF.FORMULA_TYPE = HT.HELPER_TABLE_SID \n"
                    + "AND HT.LIST_NAME = 'FORMULA_TYPE' AND HT.DESCRIPTION = 'Supplemental' AND FF.IS_ACTIVE = 1 ORDER BY FORMULA_NAME");

            List<Object> obList = (List<Object>) CommonLogic.executeSelectQuery(queryBuild.toString());

            if (obList != null && !obList.isEmpty()) {
                for (int i = 0; i < obList.size(); i++) {
                    Object ob = obList.get(i);
                    dropDownList.add(String.valueOf(ob));
                }
            }

        } catch (Exception e) {

            LOGGER.error(e.getMessage());
        }
        return dropDownList;
    }

    /**
     *
     * @param rsFormulaDTO
     * @param start
     * @param offset
     * @param isCount
     * @return
     */
    public Object loadFormula()  {
        List<String> dropDownList = new ArrayList<>();
        try {
            StringBuilder queryBuild = new StringBuilder();
            queryBuild.append("select distinct FORMULA_NAME from FORECASTING_FORMULA FF , HELPER_TABLE HT where FF.FORMULA_TYPE = HT.HELPER_TABLE_SID \n"
                    + "AND HT.LIST_NAME = 'FORMULA_TYPE' AND HT.DESCRIPTION = 'Supplemental' AND FF.IS_ACTIVE = 1 ORDER BY FORMULA_NAME");

            List<Object> obList = (List<Object>) CommonLogic.executeSelectQuery(queryBuild.toString());

            if (obList != null && !obList.isEmpty()) {
                for (int i = 0; i < obList.size(); i++) {
                    Object ob = obList.get(i);
                    dropDownList.add(String.valueOf(ob));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return convertFormulaList(dropDownList);

    }

    /**
     * Converts the list of objects to list of RSFormulaDTO.
     *
     * @param list List<Object[]>
     * @return List<RebatePlanDTO>
     */
    private List<FormulaDTO> convertFormulaList(final List<String> list) {
        List<FormulaDTO> resultList = new ArrayList<>();
        for (String str : list) {
            FormulaDTO rSFormulaDTO = new FormulaDTO();
            rSFormulaDTO.setFormulaName(str);
            resultList.add(rSFormulaDTO);
        }
        return resultList;
    }

}
