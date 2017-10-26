/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.logic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.netsalesformula.dto.DeductionDto;
import static com.stpl.app.global.netsalesformula.logic.NsfLogic.DB_DATE;
import com.stpl.app.model.ImtdDeductionDetails;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdDeductionDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import java.text.ParseException;
import java.util.Arrays;

/**
 *
 * @author karthikraja.k
 */
public class DeductionsLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DeductionsLogic.class);
    SessionDTO sessiondto;
    DateFormat format = new SimpleDateFormat("MM/DD/YYYY");
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    final SimpleDateFormat DB_Date = new SimpleDateFormat("yyyy-MM-dd");
 
    public DeductionsLogic(SessionDTO sessiondto) {
        this.sessiondto = sessiondto;
    }

    private String replaceForWildCardSearch(String input) {
        if (StringUtils.isNotBlank(input)) {
            input = CommonUtil.buildSearchCriteria(input);
        }
        return input;
    }

    public List<DeductionDto> getCountAndResultsForRS(final ErrorfulFieldGroup rebateSchForm, int start, int offset, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, boolean isCount)
            throws SystemException {
        try {
            Map<String, Object> filterCriteria = new HashMap<>();
            Map<String, String> searchCriteria = new HashMap<>();

            if (!String.valueOf((HelperDTO) rebateSchForm.getField("formulaType").getValue()).equals("Contract")) {
                searchCriteria.put(ConstantsUtils.REBATE_TYPE, checkEmptyDataFromFields(ConstantsUtils.DEDUCTION_TYPE, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((HelperDTO) rebateSchForm.getField(ConstantsUtils.DEDUCTION_TYPE).getValue()).getId()));
                searchCriteria.put(ConstantsUtils.REBATE_PROGRAM_TYPE, checkEmptyDataFromFields(ConstantsUtils.DEDUCTION_SUB_TYPE, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((HelperDTO) rebateSchForm.getField(ConstantsUtils.DEDUCTION_SUB_TYPE).getValue()).getId()));
                searchCriteria.put(ConstantsUtils.REBATE_CATEGORY, checkEmptyDataFromFields(ConstantsUtils.DEDUCTION_CATEGORY, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((HelperDTO) rebateSchForm.getField(ConstantsUtils.DEDUCTION_CATEGORY).getValue()).getId()));

                if (filterSet != null) {
                    for (Container.Filter filter : filterSet) {

                        if (filter instanceof SimpleStringFilter) {
                            SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                            String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                            if (ConstantsUtils.DEDUCTION_TYPE_TABLE.equals(stringFilter.getPropertyId())) {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                            } else if (ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE.equals(stringFilter.getPropertyId())) {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                            } else if (ConstantsUtils.DEDUCTION_CATEGORY_TABLE.equals(stringFilter.getPropertyId())) {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                            } else {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), filterString);
                            }
                            }
                                }
                            }

                String column = "RS_TYPE";
                String orderBy = "ASC";
                if (columns != null) {
                    for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                        String columnName = sortByColumn.getName();
                        switch (columnName) {
                            case ConstantsUtils.DEDUCTION_TYPE_TABLE:
                                column = "RS_TYPE";
                                break;
                            case ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE:
                                column = "REBATE_PROGRAM_TYPE";
                                break;
                            case ConstantsUtils.DEDUCTION_CATEGORY_TABLE:
                                column = "RS_CATEGORY";
                                break;
                        }

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            orderBy = "ASC";
                        } else {
                            orderBy = "DESC";
                        }
                    }
                }
                String query = getRSQuery(searchCriteria, start, offset, column, orderBy, filterCriteria, isCount);
                List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);

                if (!isCount) {
                    resultList = getCustomizedDto(resultList, false);
                }
                return resultList;
            } else {

                if (filterSet != null) {
                    filterCriteria = getFilterValues(filterSet, filterCriteria);
                }

                String column = "cm.CONTRACT_NO";
                String orderBy = "ASC";
                if (columns != null) {
                    for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                        String columnName = sortByColumn.getName();
                        switch (columnName) {
                            case ConstantsUtils.DEDUCTION_TYPE_TABLE:
                                column = "rsc.RS_TYPE";
                                break;
                            case ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE:
                                column = "rsc.REBATE_PROGRAM_TYPE";
                                break;
                            case ConstantsUtils.DEDUCTION_CATEGORY_TABLE:
                                column = "rsc.RS_CATEGORY";
                                break;
                            case ConstantsUtils.MARKET_TYPE_TABLE:
                                column = "cm.CONTRACT_TYPE ";
                                break;
                            case ConstantsUtils.CONTRACT_NO:
                                column = "cm.CONTRACT_NO";
                                break;
                            case ConstantsUtils.CONTRACT_NAME:
                                column = "cm.CONTRACT_NAME";
                                break;
                            case ConstantsUtils.CONTRACT_HOLDER:
                                column = "comp.COMPANY_NAME";
                                break;
                            case ConstantsUtils.DEDUCTION_NO:
                                column = "rsc.RS_NO";
                                break;
                            case ConstantsUtils.DEDUCTION_NAME:
                                column = "rsc.RS_NAME";
                                break;
                            case ConstantsUtils.CFP_NO:
                                column = "cfpm.CFP_NO ";
                                break;
                            case ConstantsUtils.CFP_NAME:
                                column = "cfpm.CFP_NAME";
                                break;
                            case ConstantsUtils.ITEM_FAMILY_PLAN_NO:
                                column = "ifpm.IFP_NO";
                                break;
                            case ConstantsUtils.ITEM_FAMILY_PLAN_NAME:
                                column = "ifpm.IFP_NAME";
                                break;
                            case ConstantsUtils.PS_NO:
                                column = "psm.PS_NO ";
                                break;
                            case ConstantsUtils.PS_NAME:
                                column = "psm.PS_NAME";
                                break;
                            case ConstantsUtils.START_DATE:
                                column = "cm.START_DATE";
                                break;
                            case ConstantsUtils.END_DATE:
                                column = "cm.END_DATE";
                                break;

                        }

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            orderBy = "ASC";
                        } else {
                            orderBy = "DESC";
                        }
                    }
                }

                searchCriteria.put(ConstantsUtils.CONTRACT_NO, checkEmptyDataFromFields(ConstantsUtils.CONTRACT_NO, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.CONTRACT_NO).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.CONTRACT_NAME, checkEmptyDataFromFields(ConstantsUtils.CONTRACT_NAME, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.CONTRACT_NAME).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.CONTRACT_HOLDER, checkEmptyDataFromFields(ConstantsUtils.CONTRACT_HOLDER, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.CONTRACT_HOLDER).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.DEDUCTION_NO, checkEmptyDataFromFields(ConstantsUtils.DEDUCTION_NO, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.DEDUCTION_NO).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.DEDUCTION_NAME, checkEmptyDataFromFields(ConstantsUtils.DEDUCTION_NAME, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.DEDUCTION_NAME).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.CFP_NO, checkEmptyDataFromFields(ConstantsUtils.CFP_NO, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.CFP_NO).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.CFP_NAME, checkEmptyDataFromFields(ConstantsUtils.CFP_NAME, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.CFP_NAME).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.ITEM_FAMILY_PLAN_NO, checkEmptyDataFromFields(ConstantsUtils.ITEM_FAMILY_PLAN_NO, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.ITEM_FAMILY_PLAN_NO).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.ITEM_FAMILY_PLAN_NAME, checkEmptyDataFromFields(ConstantsUtils.ITEM_FAMILY_PLAN_NAME, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.ITEM_FAMILY_PLAN_NAME).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.PS_NO, checkEmptyDataFromFields(ConstantsUtils.PS_NO, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.PS_NO).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.PS_NAME, checkEmptyDataFromFields(ConstantsUtils.PS_NAME, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.PS_NAME).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.DEDUCTION_ALAIS, checkEmptyDataFromFields(ConstantsUtils.DEDUCTION_ALAIS, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.DEDUCTION_ALAIS).getValue().toString()).trim());
                searchCriteria.put(ConstantsUtils.DEDUCTION_TYPE, checkEmptyDataFromFields(ConstantsUtils.DEDUCTION_TYPE, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((HelperDTO) rebateSchForm.getField(ConstantsUtils.DEDUCTION_TYPE).getValue()).getId()));
                searchCriteria.put(ConstantsUtils.DEDUCTION_SUB_TYPE, checkEmptyDataFromFields(ConstantsUtils.DEDUCTION_SUB_TYPE, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((HelperDTO) rebateSchForm.getField(ConstantsUtils.DEDUCTION_SUB_TYPE).getValue()).getId()));
                searchCriteria.put(ConstantsUtils.DEDUCTION_CATEGORY, checkEmptyDataFromFields(ConstantsUtils.DEDUCTION_CATEGORY, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((HelperDTO) rebateSchForm.getField(ConstantsUtils.DEDUCTION_CATEGORY).getValue()).getId()));
                searchCriteria.put(ConstantsUtils.MARKET_TYPE, checkEmptyDataFromFields(ConstantsUtils.MARKET_TYPE, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((HelperDTO) rebateSchForm.getField(ConstantsUtils.MARKET_TYPE).getValue()).getId()));
                String query = getContractQuery(searchCriteria, start, offset, column, orderBy, filterCriteria, isCount);
                
                List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (!isCount) {
                    resultList = getCustomizedDtoForContract(resultList, false);
                }

                return resultList;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return null;

        }
    }

    /**
     *
     * @param fieldName
     * @param binder
     * @return
     */
    private boolean checkEmptyDataFromFields(String fieldName, ErrorfulFieldGroup binder) {
        boolean isEmpty = false;

        if (binder.getField(fieldName) instanceof TextField) {
            TextField textField = (TextField) binder.getField(fieldName);
            isEmpty = StringUtils.isBlank(textField.getValue()) || ConstantsUtils.NULL.equals(textField.getValue());
        }
        if (binder.getField(fieldName) instanceof ComboBox) {

            ComboBox comboBox = (ComboBox) binder.getField(fieldName);
            if (comboBox.getValue() instanceof HelperDTO) {
                isEmpty = comboBox.getValue() == null || ConstantsUtils.SELECT_ONE.equals(((HelperDTO) comboBox.getValue()).getDescription());
            } else if (comboBox.getValue() == null) {
                isEmpty = true;
            }

        }
        return isEmpty;
    }

    public String getRSQuery(Map<String, String> searchCriteria, int start, int offset, String column, String orderBy, Map<String, Object> parameters, boolean isCount) {

        if (orderBy == null) {
            orderBy = "ASC";
        }
        String sql;
        if (isCount) {
            sql = "select count(*) from (select DISTINCT RS_TYPE,REBATE_PROGRAM_TYPE,"
                    + " RS_CATEGORY from RS_MODEL "
                    + "  where INBOUND_STATUS <> 'D'";
        } else {
            sql = "select DISTINCT RS_TYPE,REBATE_PROGRAM_TYPE,"
                    + " RS_CATEGORY from RS_MODEL "
                    + "  where INBOUND_STATUS <> 'D' ";
        }
        if (Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_TYPE)) != 0) {
            sql += "AND  RS_TYPE = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_TYPE));
        }
        if (Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_PROGRAM_TYPE)) != 0) {

            sql += " AND REBATE_PROGRAM_TYPE = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_PROGRAM_TYPE));
        }
        if (Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_CATEGORY)) != 0) {
            sql += " AND RS_CATEGORY = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_CATEGORY));
        }

        if (parameters.get(ConstantsUtils.DEDUCTION_TYPE_TABLE) != null && !parameters.get(ConstantsUtils.DEDUCTION_TYPE_TABLE).equals(ConstantsUtils.ZERO)) {
            sql += " AND RS_TYPE like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_TYPE_TABLE)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE) != null && !parameters.get(ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE).equals(ConstantsUtils.ZERO)) {
            sql += " AND REBATE_PROGRAM_TYPE like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.DEDUCTION_CATEGORY_TABLE) != null && !parameters.get(ConstantsUtils.DEDUCTION_CATEGORY_TABLE).equals(ConstantsUtils.ZERO)) {
            sql += " AND RS_CATEGORY like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CATEGORY_TABLE)) + "%' ";
        }
        if (!isCount) {
            sql += " ORDER BY " + column + " " + orderBy;
            sql = sql + " " + "OFFSET ";
            sql = sql + start;
            sql = sql + " ROWS FETCH NEXT " + offset;
            sql = sql + " ROWS ONLY;";
        } else {
            sql += " ) A";
        }

        return sql;

    }

    List<DeductionDto> getCustomizedDto(List list, boolean isSelected) throws PortalException, SystemException {
        final List<DeductionDto> searchItemList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final DeductionDto searchForm = new DeductionDto();

                final Object[] obj = (Object[]) list.get(i);
                searchForm.setDeductionType(helperListUtil.getIdHelperDTOMap().get(obj[0] != null ? Integer.valueOf(String.valueOf(obj[0])) : 0));
                searchForm.setDeductionSubType(helperListUtil.getIdHelperDTOMap().get(obj[1] != null ? Integer.valueOf(String.valueOf(obj[1])) : 0));
                searchForm.setDeductionCategory(helperListUtil.getIdHelperDTOMap().get(obj[NumericConstants.TWO] != null ? Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])) : 0));
              
                searchForm.setDeductionTypeTable((searchForm.getDeductionType().getId()==0) ? StringUtils.EMPTY : searchForm.getDeductionType().getDescription());
                searchForm.setDeductionSubTypeTable((searchForm.getDeductionSubType().getId()==0) ? StringUtils.EMPTY : searchForm.getDeductionSubType().getDescription());
                searchForm.setDeductionCategoryTable((searchForm.getDeductionCategory().getId()==0) ? StringUtils.EMPTY : searchForm.getDeductionCategory().getDescription());
              
                if (isSelected) {
                    searchForm.setTempItemSystemId(obj[NumericConstants.THREE] != null ? (int) (obj[NumericConstants.THREE]) : 0);
                    searchForm.setIndicator(obj[NumericConstants.FOUR] != null && (char)obj[NumericConstants.FOUR] != ' ' ? ((char)(obj[NumericConstants.FOUR]) == '+' ? "Add" : "Subtract") : ConstantsUtils.SELECT_ONE);
                    searchForm.setNetSalesRuleSystemId(obj[NumericConstants.FIVE] != null ? (int) (obj[NumericConstants.FIVE]) : 0);
                    searchForm.setSelectedCheck(obj[NumericConstants.SIX] != null ? (boolean)obj[NumericConstants.SIX] == true : false);
                    searchForm.setNetSalesRuleNo(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : StringUtils.EMPTY);
                    searchForm.setNetSalesRuleName(obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : StringUtils.EMPTY);

                }
                searchItemList.add(searchForm);
            }
        }
        return searchItemList;
    }

    private String getContractQuery(Map<String, String> searchCriteria, int start, int offset, String column, String orderBy, Map<String, Object> parameters, boolean isCount) {
        if (orderBy == null) {
            orderBy = "ASC";
        }
        String sql;
        if (isCount) {
            sql = "select count(*) from (SELECT DISTINCT  CM.CONTRACT_NO,CM.CONTRACT_NAME,RSC.RS_NO,RSC.RS_NAME,RSC.RS_TYPE as RSTYPE,RSC.REBATE_PROGRAM_TYPE as RPTYPE,\n"
                    + "RSC.RS_CATEGORY as RCAT,CM.CONTRACT_TYPE as CTYPE,CM.START_DATE,CM.END_DATE,\n"
                    + "COMP.COMPANY_NAME,CFPM.CFP_NO,CFPM.CFP_NAME,IFPM.IFP_NO,IFPM.IFP_NAME,PS.PS_NO,PS.PS_NAME,CM.CONTRACT_MASTER_SID,RSC.RS_CONTRACT_SID \n"
                    + "\n"
                    + "FROM   CONTRACT_MASTER CM\n"
                    + "       LEFT JOIN HELPER_TABLE conTypeHelper on conTypeHelper.HELPER_TABLE_SID=CM.CONTRACT_TYPE\n"
                    + "       LEFT JOIN CFP_CONTRACT CFC\n"
                    + "              ON CFC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                    + "                 AND CFC.INBOUND_STATUS <> 'D'\n"
                    + "       LEFT JOIN CFP_MODEL CFPM\n"
                    + "              ON CFPM.CFP_MODEL_SID = CFC.CFP_MODEL_SID\n"
                    + "       LEFT JOIN COMPANY_MASTER COMP\n"
                    + "              ON CM.CONT_HOLD_COMPANY_MASTER_SID = COMP.COMPANY_MASTER_SID\n"
                    + "                 AND COMP.COMPANY_NAME LIKE '%'\n"
                    + "       LEFT JOIN IFP_CONTRACT IFC\n"
                    + "              ON IFC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                    + "                 AND IFC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
                    + "                 AND IFC.INBOUND_STATUS <> 'D'\n"
                    + "       LEFT JOIN IFP_MODEL IFPM\n"
                    + "              ON IFPM.IFP_MODEL_SID = IFC.IFP_MODEL_SID\n"
                    + "       LEFT JOIN PS_CONTRACT PSC\n"
                    + "              ON PSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                    + "                 AND PSC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
                    + "                 AND PSC.IFP_CONTRACT_SID = iFC.IFP_CONTRACT_SID\n"
                    + "                 AND PSC.INBOUND_STATUS <> 'D'\n"
                    + "            JOIN PS_MODEL PS \n"
                    + "              ON ps.PS_MODEL_SID=PSC.PS_MODEL_SID\n"
                    + "       LEFT JOIN RS_CONTRACT RSC\n"
                    + "              ON RSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                    + "                 AND RSC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
                    + "                 AND RSC.IFP_CONTRACT_SID = iFC.IFP_CONTRACT_SID\n"
                    + "                 AND RSC.PS_CONTRACT_SID = PSC.PS_CONTRACT_SID\n"
                    + "                 AND RSC.INBOUND_STATUS <> 'D'\n"
                    + "WHERE  CM.PROCESS_STATUS = 1  ";
        } else {
            sql = "SELECT DISTINCT  CM.CONTRACT_NO,CM.CONTRACT_NAME,RSC.RS_NO,RSC.RS_NAME,RSC.RS_TYPE as RSTYPE,RSC.REBATE_PROGRAM_TYPE as RPTYPE,\n"
                    + "RSC.RS_CATEGORY as RCAT,CM.CONTRACT_TYPE as CTYPE,CM.START_DATE,CM.END_DATE,\n"
                    + "COMP.COMPANY_NAME,CFPM.CFP_NO,CFPM.CFP_NAME,IFPM.IFP_NO,IFPM.IFP_NAME,PS.PS_NO,PS.PS_NAME,CM.CONTRACT_MASTER_SID,RSC.RS_CONTRACT_SID \n"
                    + "\n"
                    + "FROM   CONTRACT_MASTER CM\n"
                    + "       LEFT JOIN HELPER_TABLE conTypeHelper on conTypeHelper.HELPER_TABLE_SID=CM.CONTRACT_TYPE\n"
                    + "       LEFT JOIN CFP_CONTRACT CFC\n"
                    + "              ON CFC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                    + "                 AND CFC.INBOUND_STATUS <> 'D'\n"
                    + "       LEFT JOIN CFP_MODEL CFPM\n"
                    + "              ON CFPM.CFP_MODEL_SID = CFC.CFP_MODEL_SID\n"
                    + "       LEFT JOIN COMPANY_MASTER COMP\n"
                    + "              ON CM.CONT_HOLD_COMPANY_MASTER_SID = COMP.COMPANY_MASTER_SID\n"
                    + "                 AND COMP.COMPANY_NAME LIKE '%'\n"
                    + "       LEFT JOIN IFP_CONTRACT IFC\n"
                    + "              ON IFC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                    + "                 AND IFC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
                    + "                 AND IFC.INBOUND_STATUS <> 'D'\n"
                    + "       LEFT JOIN IFP_MODEL IFPM\n"
                    + "              ON IFPM.IFP_MODEL_SID = IFC.IFP_MODEL_SID\n"
                    + "       LEFT JOIN PS_CONTRACT PSC\n"
                    + "              ON PSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                    + "                 AND PSC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
                    + "                 AND PSC.IFP_CONTRACT_SID = iFC.IFP_CONTRACT_SID\n"
                    + "                 AND PSC.INBOUND_STATUS <> 'D'\n"
                    + "            JOIN PS_MODEL PS \n"
                    + "              ON ps.PS_MODEL_SID=PSC.PS_MODEL_SID\n"
                    + "       LEFT JOIN RS_CONTRACT RSC\n"
                    + "              ON RSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
                    + "                 AND RSC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
                    + "                 AND RSC.IFP_CONTRACT_SID = iFC.IFP_CONTRACT_SID\n"
                    + "                 AND RSC.PS_CONTRACT_SID = PSC.PS_CONTRACT_SID\n"
                    + "                 AND RSC.INBOUND_STATUS <> 'D'\n"
                    + "WHERE  CM.PROCESS_STATUS = 1";
        }

        if (Integer.valueOf(searchCriteria.get(ConstantsUtils.DEDUCTION_TYPE)) != 0) {
            sql += " AND  RSC.RS_TYPE = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.DEDUCTION_TYPE));
        }
        if (Integer.valueOf(searchCriteria.get(ConstantsUtils.DEDUCTION_SUB_TYPE)) != 0) { 
            sql += " AND RSC.REBATE_PROGRAM_TYPE = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.DEDUCTION_SUB_TYPE));
        }
        if (Integer.valueOf(searchCriteria.get(ConstantsUtils.DEDUCTION_CATEGORY)) != 0) {
            sql += " AND RSC.RS_CATEGORY = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.DEDUCTION_CATEGORY));
        }
        if (Integer.valueOf(searchCriteria.get(ConstantsUtils.MARKET_TYPE)) != 0) {
            sql += " AND CM.CONTRACT_TYPE = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.MARKET_TYPE));
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.CONTRACT_NO))) {
            sql += " AND CM.CONTRACT_NO LIKE '" + searchCriteria.get(ConstantsUtils.CONTRACT_NO) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.CONTRACT_NAME))) {
            sql += " AND CM.CONTRACT_NAME LIKE '" + searchCriteria.get(ConstantsUtils.CONTRACT_NAME) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.CONTRACT_HOLDER))) {
            sql += " AND COMP.COMPANY_NAME LIKE '" + searchCriteria.get(ConstantsUtils.CONTRACT_HOLDER) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.CFP_NO))) {
            sql += " AND CFPM.CFP_NO LIKE '" + searchCriteria.get(ConstantsUtils.CFP_NO) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.CFP_NAME))) {
            sql += " AND CFPM.CFP_NAME LIKE '" + searchCriteria.get(ConstantsUtils.CFP_NAME) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.ITEM_FAMILY_PLAN_NO))) {
            sql += " AND IFPM.IFP_NO LIKE '" + searchCriteria.get(ConstantsUtils.ITEM_FAMILY_PLAN_NO) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.ITEM_FAMILY_PLAN_NAME))) {
            sql += " AND IFPM.IFP_NAME LIKE '" + searchCriteria.get(ConstantsUtils.ITEM_FAMILY_PLAN_NAME) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.PS_NO))) {
            sql += " AND PS.PS_NO LIKE '" + searchCriteria.get(ConstantsUtils.PS_NO) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.PS_NAME))) {
            sql += " AND PS.PS_NAME LIKE '" + searchCriteria.get(ConstantsUtils.PS_NAME) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.DEDUCTION_ALAIS))) {
            sql += " AND RSC.RS_ALIAS LIKE '" + searchCriteria.get(ConstantsUtils.DEDUCTION_ALAIS) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.DEDUCTION_NO))) {
            sql += " AND RSC.RS_NO LIKE '" + searchCriteria.get(ConstantsUtils.DEDUCTION_NO) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.DEDUCTION_NAME))) {
            sql += " AND RSC.RS_NAME LIKE '" + searchCriteria.get(ConstantsUtils.DEDUCTION_NAME) + ConstantsUtils.SINGLE_QUOTE;
        }
        if (parameters.get(ConstantsUtils.DEDUCTION_TYPE_TABLE) != null && !parameters.get(ConstantsUtils.DEDUCTION_TYPE_TABLE).equals(ConstantsUtils.ZERO)) {
            sql += " AND  RSC.RS_TYPE like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_TYPE_TABLE)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE) != null && !parameters.get(ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE).equals(ConstantsUtils.ZERO)) {
            sql += " AND RSC.REBATE_PROGRAM_TYPE like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.DEDUCTION_CATEGORY_TABLE) != null && !parameters.get(ConstantsUtils.DEDUCTION_CATEGORY_TABLE).equals(ConstantsUtils.ZERO)) {
            sql += " AND RSC.RS_CATEGORY like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CATEGORY_TABLE)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.MARKET_TYPE_TABLE) != null && !parameters.get(ConstantsUtils.MARKET_TYPE_TABLE).equals(ConstantsUtils.ZERO)) {
            sql += " AND  CM.CONTRACT_TYPE like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.MARKET_TYPE_TABLE)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.CONTRACT_NO) != null) {
            sql += " AND CM.CONTRACT_NO like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.CONTRACT_NO)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.CONTRACT_NAME) != null) {
            sql += " AND CM.CONTRACT_NAME like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.CONTRACT_NAME)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.CONTRACT_HOLDER) != null) {
            sql += " AND COMP.COMPANY_NAME like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.CONTRACT_HOLDER)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.CFP_NO) != null) {
            sql += " AND CFPM.CFP_NO like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.CFP_NO)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.CFP_NAME) != null) {
            sql += " AND CFPM.CFP_NAME like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.CFP_NAME)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.ITEM_FAMILY_PLAN_NO) != null) {
            sql += " AND  IFPM.IFP_NO like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_FAMILY_PLAN_NO)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.ITEM_FAMILY_PLAN_NAME) != null) {
            sql += " AND IFPM.IFP_NAME like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.ITEM_FAMILY_PLAN_NAME)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.PS_NO) != null) {
            sql += " AND PS.PS_NO like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.PS_NO)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.PS_NAME) != null) {
            sql += " AND PS.PS_NAME like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.PS_NAME)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.DEDUCTION_NO) != null) {
            sql += " AND RSC.RS_NO  like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_NO)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.DEDUCTION_NAME) != null) {
            sql += " AND  RSC.RS_NAME like '";
            sql += ConstantsUtils.PERCENCTAGE + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_NAME)) + "%' ";
        }
        if (parameters.get(ConstantsUtils.END_DATE + ConstantsUtils.FROM) != null) {
            sql += " AND CM.END_DATE  ";
            sql += String.valueOf(parameters.get(ConstantsUtils.END_DATE + ConstantsUtils.FROM));
        }
        if (parameters.get(ConstantsUtils.END_DATE + ConstantsUtils.TO) != null) {
            sql += " AND CM.END_DATE  ";
            sql += String.valueOf(parameters.get(ConstantsUtils.END_DATE + ConstantsUtils.TO));
        }
        if (parameters.get(ConstantsUtils.END_DATE) != null) {
            sql += " AND CM.END_DATE  ";
            sql += String.valueOf(parameters.get(ConstantsUtils.END_DATE));
        }
        if (parameters.get(ConstantsUtils.START_DATE + ConstantsUtils.FROM) != null) {
            sql += " AND CM.START_DATE  ";
            sql += String.valueOf(parameters.get(ConstantsUtils.START_DATE + ConstantsUtils.FROM));
        }
        if (parameters.get(ConstantsUtils.START_DATE + ConstantsUtils.TO) != null) {
            sql += " AND CM.START_DATE  ";
            sql += String.valueOf(parameters.get(ConstantsUtils.START_DATE + ConstantsUtils.TO));
        }
        if (parameters.get(ConstantsUtils.START_DATE) != null) {
            sql += " AND CM.START_DATE  ";
            sql += String.valueOf(parameters.get(ConstantsUtils.START_DATE));
        }
                  
        if (!isCount) {
            sql += " ORDER BY " + column + " " + orderBy;
            sql = sql + " " + "OFFSET ";
            sql = sql + start;
            sql = sql + " ROWS FETCH NEXT " + offset;
            sql = sql + " ROWS ONLY;";
        } else {
            sql += " ) a";
        }
        return sql;
    }

    private List<DeductionDto> getCustomizedDtoForContract(List list, boolean isSelected) throws PortalException, SystemException, ParseException {
        final List<DeductionDto> searchItemList = new ArrayList<>();
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                DeductionDto searchForm = new DeductionDto();

                final Object[] obj = (Object[]) list.get(i);
                searchForm.setContractNo(obj[0] != null ? String.valueOf(obj[0]) : StringUtils.EMPTY);
                searchForm.setContractName(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY);
                searchForm.setDeductionNo(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : StringUtils.EMPTY);
                searchForm.setDeductionName(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : StringUtils.EMPTY);
                searchForm.setDeductionType(helperListUtil.getIdHelperDTOMap().get(obj[NumericConstants.FOUR] != null ? Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])) : 0));
                searchForm.setDeductionTypeTable((searchForm.getDeductionType().getId()==0) ? StringUtils.EMPTY : searchForm.getDeductionType().getDescription());
                searchForm.setDeductionSubType(helperListUtil.getIdHelperDTOMap().get(obj[NumericConstants.FIVE] != null ? Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE])) : 0));
                searchForm.setDeductionSubTypeTable((searchForm.getDeductionSubType().getId()==0) ? StringUtils.EMPTY : searchForm.getDeductionSubType().getDescription());
                searchForm.setDeductionCategory(helperListUtil.getIdHelperDTOMap().get(obj[NumericConstants.SIX] != null ? Integer.valueOf(String.valueOf(obj[NumericConstants.SIX])) : 0));
                searchForm.setDeductionCategoryTable((searchForm.getDeductionCategory().getId()==0) ? StringUtils.EMPTY : searchForm.getDeductionCategory().getDescription());
                searchForm.setMarketType(helperListUtil.getIdHelperDTOMap().get(obj[NumericConstants.SEVEN] != null ? Integer.valueOf(String.valueOf(obj[NumericConstants.SEVEN])) : 0));
                searchForm.setMarketTypeTable((searchForm.getMarketType().getId()==0) ? StringUtils.EMPTY : searchForm.getMarketType().getDescription());
                searchForm.setStartDateString(formatDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.EIGHT]))));
                searchForm.setStartDate(parsetDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.EIGHT]))));
                searchForm.setEndDateString(formatDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.NINE]))));
                searchForm.setEndDate(parsetDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.NINE]))));
                searchForm.setContractHolder(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : StringUtils.EMPTY);
                searchForm.setCompanyFamilyPlanNo(obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : StringUtils.EMPTY);
                searchForm.setCompanyFamilyPlanName(obj[NumericConstants.TWELVE] != null ? String.valueOf(obj[NumericConstants.TWELVE]) : StringUtils.EMPTY);
                searchForm.setItemFamilyPlanNo(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : StringUtils.EMPTY);
                searchForm.setItemFamilyPlanName(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : StringUtils.EMPTY);
                searchForm.setPriceScheduleNo(obj[NumericConstants.FIFTEEN] != null ? String.valueOf(obj[NumericConstants.FIFTEEN]) : StringUtils.EMPTY);
                searchForm.setPriceScheduleName(obj[NumericConstants.SIXTEEN] != null ? String.valueOf(obj[NumericConstants.SIXTEEN]) : StringUtils.EMPTY);
                searchForm.setContractMasterSId(obj[NumericConstants.SEVENTEEN] != null ? (int) (obj[NumericConstants.SEVENTEEN]) : 0);

                if (isSelected) {
                    searchForm.setTempItemSystemId(obj[NumericConstants.EIGHTEEN] != null ? (int) (obj[NumericConstants.EIGHTEEN]) : 0);
                    searchForm.setIndicator(obj[NumericConstants.NINETEEN] != null && (char)obj[NumericConstants.NINETEEN] != ' ' ? ((char)(obj[NumericConstants.NINETEEN]) == '+' ? "Add" : "Subtract") : ConstantsUtils.SELECT_ONE);
                    searchForm.setNetSalesRuleSystemId(obj[NumericConstants.TWENTY] != null ? (int) (obj[NumericConstants.TWENTY]) : 0);
                    searchForm.setSelectedCheck(obj[NumericConstants.TWENTY_ONE] != null ? (Boolean) obj[NumericConstants.TWENTY_ONE] : false);
                    searchForm.setNetSalesRuleNo(obj[NumericConstants.TWENTY_TWO] != null ? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : StringUtils.EMPTY);
                    searchForm.setNetSalesRuleName(obj[NumericConstants.TWENTY_THREE] != null ? String.valueOf(obj[NumericConstants.TWENTY_THREE]) : StringUtils.EMPTY);
                    searchForm.setRsContractSid(obj[NumericConstants.TWENTY_FOUR] != null ? (int) (obj[NumericConstants.TWENTY_FOUR]) : 0);
                } else {
                    searchForm.setRsContractSid(obj[NumericConstants.EIGHTEEN] != null ? (int) (obj[NumericConstants.EIGHTEEN]) : 0);
                }

                searchItemList.add(searchForm);
            }
        }
        return searchItemList;
    }

    public void addToTempTable(DeductionDto item, SessionDTO sessionDTO, boolean isContract,StringBuilder insertQuery) throws SystemException, PortalException, ParseException {

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        int user = Integer.valueOf(userId);
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

            insertQuery.append(" INSERT INTO IMTD_DEDUCTION_DETAILS (DEDUCTION_DETAILS_SID, NET_SALES_FORMULA_MASTER_SID, CONTRACT_MASTER_SID, CONTRACT_NO, CONTRACT_NAME, RS_CONTRACT_SID, DEDUCTION_NO, DEDUCTION_NAME, DEDUCTION_TYPE, DEDUCTION_SUB_TYPE, DEDUCTION_CATEGORY, CDR_MODEL_SID, \"INDICATOR\", RECORD_LOCK_STATUS, CHECK_RECORD, INBOUND_STATUS, USERS_SID, SESSION_ID, IMTD_CREATED_DATE, \"OPERATION\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE) \n"
                    + "	VALUES (0, 0,");
            if (item.getContractMasterSId() != 0 && isContract) {
                insertQuery.append(item.getContractMasterSId()).append(",NULL, NULL,").append(item.getRsContractSid()).append(", NULL, NULL,");
            } else {
                insertQuery.append("NULL, NULL, NULL, NULL, NULL, NULL,");
            }
            insertQuery.append(item.getDeductionType().getId()).append(",");
            insertQuery.append(item.getDeductionSubType().getId()).append(",");
            insertQuery.append(item.getDeductionCategory().getId()).append(",");
            insertQuery.append("NULL,");
            insertQuery.append("'',");
            insertQuery.append("'false','");
            insertQuery.append(item.getSelectedCheck()).append("','");
            insertQuery.append(ConstantsUtils.A).append("',");
            insertQuery.append("'").append(user).append("',");
            insertQuery.append("'").append(sessionId).append("',");
            insertQuery.append("CURRENT_TIMESTAMP,'");
            insertQuery.append(ConstantsUtils.A).append("',");
            insertQuery.append("'").append(user).append("',");
            insertQuery.append("CURRENT_TIMESTAMP,");
            insertQuery.append("'").append(user).append("',");
            insertQuery.append("CURRENT_TIMESTAMP);");

    }
   
    public List tempTableCount(final Set<Container.Filter> filterSet, final List<SortByColumn> columns, boolean isContract, boolean isCount, int start, int offset) {
        try {

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            int user = Integer.valueOf(userId);
            final String sessionId = String.valueOf(sessiondto.getUiSessionId());

            Map<String, Object> filterCriteria = new HashMap<>();

            if (!isContract) {
                String rssql = StringUtils.EMPTY;
                if (isCount) {
                    rssql = "select count(*) from (select IMD.DEDUCTION_TYPE,IMD.DEDUCTION_SUB_TYPE,IMD.DEDUCTION_CATEGORY,IMD.IMTD_DEDUCTION_DETAILS_SID,\n"
                            + "IMD.INDICATOR,IMD.CDR_MODEL_SID,IMD.CHECK_RECORD,NS.RULE_NO,NS.RULE_NAME\n"
                            + "from dbo.IMTD_DEDUCTION_DETAILS IMD\n"
                            + " LEFT JOIN dbo.CDR_MODEL NS ON NS.CDR_MODEL_SID=IMD.CDR_MODEL_SID ";
                } else {
                    rssql = "select IMD.DEDUCTION_TYPE,IMD.DEDUCTION_SUB_TYPE,IMD.DEDUCTION_CATEGORY,IMD.IMTD_DEDUCTION_DETAILS_SID,\n"
                            + "IMD.INDICATOR,IMD.CDR_MODEL_SID,IMD.CHECK_RECORD,NS.RULE_NO,NS.RULE_NAME\n"
                            + "from dbo.IMTD_DEDUCTION_DETAILS IMD\n"
                            + " LEFT JOIN dbo.CDR_MODEL NS ON NS.CDR_MODEL_SID=IMD.CDR_MODEL_SID ";
                }
                rssql += " WHERE IMD.USERS_SID = " + user;
                rssql += " AND IMD.SESSION_ID like '" + sessionId + ConstantsUtils.SINGLE_QUOTE;

                if (filterSet != null) {
                    for (Container.Filter filter : filterSet) {

                        if (filter instanceof SimpleStringFilter) {
                            SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                            String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                            if (ConstantsUtils.DEDUCTION_TYPE.equals(stringFilter.getPropertyId())) {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                            } else if (ConstantsUtils.DEDUCTION_SUB_TYPE.equals(stringFilter.getPropertyId())) {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                            } else if (ConstantsUtils.DEDUCTION_CATEGORY.equals(stringFilter.getPropertyId())) {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                            } else if (ConstantsUtils.NET_SALES_RULENO.equals(stringFilter.getPropertyId())) {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                            } else if (ConstantsUtils.NET_SALES_RULE_NAME.equals(stringFilter.getPropertyId())) {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                            } else if (ConstantsUtils.INDICATOR_SMALL.equals(stringFilter.getPropertyId())) {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                            } else {
                                filterCriteria.put(stringFilter.getPropertyId().toString(), filterString);
                            }
                        }
                    }
                }

                String column = "IMD.DEDUCTION_TYPE";
                String orderBy = "ASC";
                if (columns != null) {
                    for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                        String columnName = sortByColumn.getName();
                        switch (columnName) {
                            case ConstantsUtils.DEDUCTION_TYPE:
                                column = "IMD.DEDUCTION_TYPE";
                                break;
                            case ConstantsUtils.DEDUCTION_SUB_TYPE:
                                column = "IMD.DEDUCTION_SUB_TYPE";
                                break;
                            case ConstantsUtils.DEDUCTION_CATEGORY:
                                column = "IMD.DEDUCTION_CATEGORY";
                                break;
                            case ConstantsUtils.NET_SALES_RULENO:
                                column = "NS.RULE_NO";
                                break;
                            case ConstantsUtils.NET_SALES_RULE_NAME:
                                column = "NS.RULE_TYPE";
                                break;
                            case ConstantsUtils.INDICATOR_SMALL:
                                column = "IMD.INDICATOR";
                                break;
                        }

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            orderBy = "ASC";
                        } else {
                            orderBy = "DESC";
                        }
                    }
                }
                if (filterCriteria.get(ConstantsUtils.DEDUCTION_TYPE) != null && !filterCriteria.get(ConstantsUtils.DEDUCTION_TYPE).equals(ConstantsUtils.ZERO)) {
                    rssql += " AND IMD.DEDUCTION_TYPE like '";
                    rssql += ConstantsUtils.PERCENCTAGE + (filterCriteria.get(ConstantsUtils.DEDUCTION_TYPE).toString()) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.DEDUCTION_SUB_TYPE) != null && !filterCriteria.get(ConstantsUtils.DEDUCTION_SUB_TYPE).equals(ConstantsUtils.ZERO)) {
                    rssql += " AND IMD.DEDUCTION_SUB_TYPE like '";
                    rssql += ConstantsUtils.PERCENCTAGE + (filterCriteria.get(ConstantsUtils.DEDUCTION_SUB_TYPE)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.DEDUCTION_CATEGORY) != null && !filterCriteria.get(ConstantsUtils.DEDUCTION_CATEGORY).equals(ConstantsUtils.ZERO)) {
                    rssql += " AND IMD.DEDUCTION_CATEGORY like '";
                    rssql += ConstantsUtils.PERCENCTAGE + (filterCriteria.get(ConstantsUtils.DEDUCTION_CATEGORY)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.NET_SALES_RULENO) != null && !filterCriteria.get(ConstantsUtils.NET_SALES_RULENO).equals(ConstantsUtils.ZERO)) {
                    rssql += " AND NS.RULE_NO like '";
                    rssql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.NET_SALES_RULENO)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.NET_SALES_RULE_NAME) != null && !filterCriteria.get(ConstantsUtils.NET_SALES_RULE_NAME).equals(ConstantsUtils.ZERO)) {
                    rssql += " AND NS.RULE_TYPE like '";
                    rssql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.NET_SALES_RULE_NAME)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.INDICATOR_SMALL) != null && !filterCriteria.get(ConstantsUtils.INDICATOR_SMALL).equals(ConstantsUtils.ZERO)) {
                    rssql += " AND IMD.INDICATOR like '";
                    rssql += ConstantsUtils.PERCENCTAGE + (String.valueOf(filterCriteria.get(ConstantsUtils.INDICATOR_SMALL)).equals("Add")?"+":"-" )+ "%' ";
                }
                rssql += " and IMD.Operation <> 'D' and IMD.INBOUND_STATUS  <> 'D'  ";
                if (!isCount) {
                    rssql += " ORDER BY " + column + " " + orderBy;
                    rssql = rssql + " " + "OFFSET ";
                    rssql = rssql + start;
                    rssql = rssql + " ROWS FETCH NEXT " + offset;
                    rssql = rssql + " ROWS ONLY;";
                } else {
                    rssql += " ) a";
                }
                List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(rssql, null, null);
                if (isCount) {
                    return resultList;
                } else {
                    return getCustomizedDto(resultList, true);
                }

            } else {
                String sql;
                if (isCount) {
                    sql = "select count(*) from (select CM.CONTRACT_NO,CM.CONTRACT_NAME,RSC.RS_NO,RSC.RS_NAME,RSC.RS_TYPE as RSTYPE,RSC.REBATE_PROGRAM_TYPE as RPTYPE,\n"
                            + "RSC.RS_CATEGORY as RCAT,CM.CONTRACT_TYPE as CTYPE,CM.START_DATE,CM.END_DATE,\n"
                            + "COMP.COMPANY_NAME,CFPM.CFP_NO,CFPM.CFP_NAME,IFPM.IFP_NO,IFPM.IFP_NAME,PS.PS_NO,PS.PS_NAME,CM.CONTRACT_MASTER_SID,IMD.IMTD_DEDUCTION_DETAILS_SID, IMD.INDICATOR,IMD.CDR_MODEL_SID,IMD.CHECK_RECORD,NS.RULE_NO,NS.RULE_NAME,RSC.RS_CONTRACT_SID from IMTD_DEDUCTION_DETAILS IMD\n"
                            + "JOIN RS_CONTRACT RSC on RSC.RS_CONTRACT_SID=IMD.RS_CONTRACT_SID \n"
                            + "JOIN CONTRACT_MASTER CM on CM.CONTRACT_MASTER_SID=RSC.CONTRACT_MASTER_SID\n"
                            + "LEFT JOIN CDR_MODEL NS ON NS.CDR_MODEL_SID=IMD.CDR_MODEL_SID \n"
                            + "LEFT JOIN DBO.COMPANY_MASTER COMP ON CM.CONT_HOLD_COMPANY_MASTER_SID = COMP.COMPANY_MASTER_SID\n"
                            + "LEFT JOIN HELPER_TABLE conTypeHelper on conTypeHelper.HELPER_TABLE_SID=CM.CONTRACT_TYPE\n"
                            + "LEFT JOIN CFP_CONTRACT CFPC on CFPC.CFP_CONTRACT_SID=RSC.CFP_CONTRACT_SID\n"
                            + "     JOIN CFP_MODEL CFPM ON CFPM.CFP_MODEL_SID = CFPC.CFP_MODEL_SID\n"
                            + "LEFT JOIN IFP_CONTRACT IFPC on IFPC.IFP_CONTRACT_SID=RSC.IFP_CONTRACT_SID\n"
                            + "     JOIN IFP_MODEL IFPM\n"
                            + "              ON IFPM.IFP_MODEL_SID = IFPC.IFP_MODEL_SID\n"
                            + "LEFT JOIN PS_CONTRACT PSC on PSC.PS_CONTRACT_SID=RSC.RS_CONTRACT_SID\n"
                            + "     LEFT JOIN PS_MODEL PS\n"
                            + "              ON PS.PS_MODEL_SID=PSC.PS_MODEL_SID\n"; 
                } else {
                    sql = "select CM.CONTRACT_NO,CM.CONTRACT_NAME,RSC.RS_NO,RSC.RS_NAME,RSC.RS_TYPE as RSTYPE,RSC.REBATE_PROGRAM_TYPE as RPTYPE,\n"
                            + "RSC.RS_CATEGORY as RCAT,CM.CONTRACT_TYPE as CTYPE,CM.START_DATE,CM.END_DATE,\n"
                            + "COMP.COMPANY_NAME,CFPM.CFP_NO,CFPM.CFP_NAME,IFPM.IFP_NO,IFPM.IFP_NAME,PS.PS_NO,PS.PS_NAME,CM.CONTRACT_MASTER_SID,IMD.IMTD_DEDUCTION_DETAILS_SID, IMD.INDICATOR,IMD.CDR_MODEL_SID,IMD.CHECK_RECORD,NS.RULE_NO,NS.RULE_NAME,RSC.RS_CONTRACT_SID from IMTD_DEDUCTION_DETAILS IMD\n"
                            + "JOIN RS_CONTRACT RSC on RSC.RS_CONTRACT_SID=IMD.RS_CONTRACT_SID \n"
                            + "JOIN CONTRACT_MASTER CM on CM.CONTRACT_MASTER_SID=RSC.CONTRACT_MASTER_SID\n"
                            + "LEFT JOIN CDR_MODEL NS ON NS.CDR_MODEL_SID=IMD.CDR_MODEL_SID \n"
                            + "LEFT JOIN DBO.COMPANY_MASTER COMP ON CM.CONT_HOLD_COMPANY_MASTER_SID = COMP.COMPANY_MASTER_SID\n"
                            + "LEFT JOIN HELPER_TABLE conTypeHelper on conTypeHelper.HELPER_TABLE_SID=CM.CONTRACT_TYPE\n"
                            + "LEFT JOIN CFP_CONTRACT CFPC on CFPC.CFP_CONTRACT_SID=RSC.CFP_CONTRACT_SID\n"
                            + "     JOIN CFP_MODEL CFPM ON CFPM.CFP_MODEL_SID = CFPC.CFP_MODEL_SID\n"
                            + "LEFT JOIN IFP_CONTRACT IFPC on IFPC.IFP_CONTRACT_SID=RSC.IFP_CONTRACT_SID\n"
                            + "     JOIN IFP_MODEL IFPM\n"
                            + "              ON IFPM.IFP_MODEL_SID = IFPC.IFP_MODEL_SID\n"
                            + "LEFT JOIN PS_CONTRACT PSC on PSC.PS_CONTRACT_SID=RSC.RS_CONTRACT_SID\n"
                            + "     LEFT JOIN PS_MODEL PS\n"
                            + "              ON PS.PS_MODEL_SID=PSC.PS_MODEL_SID ";
                }


                sql += " WHERE IMD.USERS_SID = " + user;
                sql += " AND IMD.SESSION_ID like '" + sessionId + ConstantsUtils.SINGLE_QUOTE;
                filterCriteria = getFilterValues(filterSet, filterCriteria);

                String column = "cm.CONTRACT_NO";
                String orderBy = "ASC";
                if (columns != null) {
                    for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                        String columnName = sortByColumn.getName();
                        switch (columnName) {
                            case ConstantsUtils.DEDUCTION_TYPE:
                                column = "rsc.RS_TYPE";
                                break;
                            case ConstantsUtils.DEDUCTION_SUB_TYPE:
                                column = "rsc.REBATE_PROGRAM_TYPE";
                                break;
                            case ConstantsUtils.DEDUCTION_CATEGORY:
                                column = "rsc.RS_CATEGORY";
                                break;
                            case ConstantsUtils.MARKET_TYPE:
                                column = "cm.CONTRACT_TYPE ";
                                break;
                            case ConstantsUtils.CONTRACT_NO:
                                column = "cm.CONTRACT_NO";
                                break;
                            case ConstantsUtils.CONTRACT_NAME:
                                column = "cm.CONTRACT_NAME";
                                break;
                            case ConstantsUtils.CONTRACT_HOLDER:
                                column = "comp.COMPANY_NAME";
                                break;
                            case ConstantsUtils.DEDUCTION_NO:
                                column = "rsc.RS_NO";
                                break;
                            case ConstantsUtils.DEDUCTION_NAME:
                                column = "rsc.RS_NAME";
                                break;
                            case ConstantsUtils.CFP_NO:
                                column = "cfpm.CFP_NO ";
                                break;
                            case ConstantsUtils.CFP_NAME:
                                column = "cfpm.CFP_NAME";
                                break;
                            case ConstantsUtils.ITEM_FAMILY_PLAN_NO:
                                column = "ifpm.IFP_NO";
                                break;
                            case ConstantsUtils.ITEM_FAMILY_PLAN_NAME:
                                column = "ifpm.IFP_NAME";
                                break;
                            case ConstantsUtils.PS_NO:
                                column = "psm.PS_NO ";
                                break;
                            case ConstantsUtils.PS_NAME:
                                column = "psm.PS_NAME";
                                break;
                            case ConstantsUtils.START_DATE:
                                column = "cm.START_DATE";
                                break;
                            case ConstantsUtils.END_DATE:
                                column = "cm.END_DATE";
                                break;

                        }

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            orderBy = "ASC";
                        } else {
                            orderBy = "DESC";
                        }
                    }
                }

                if (filterCriteria.get(ConstantsUtils.DEDUCTION_TYPE) != null && !filterCriteria.get(ConstantsUtils.DEDUCTION_TYPE).equals(ConstantsUtils.ZERO)) {
                    sql += " AND  RSC.RS_TYPE like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.DEDUCTION_TYPE)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.DEDUCTION_SUB_TYPE) != null && !filterCriteria.get(ConstantsUtils.DEDUCTION_SUB_TYPE).equals(ConstantsUtils.ZERO)) {
                    sql += " AND RSC.REBATE_PROGRAM_TYPE like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.DEDUCTION_SUB_TYPE)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.DEDUCTION_CATEGORY) != null && !filterCriteria.get(ConstantsUtils.DEDUCTION_CATEGORY).equals(ConstantsUtils.ZERO)) {
                    sql += " AND RSC.RS_CATEGORY like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.DEDUCTION_CATEGORY)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.MARKET_TYPE) != null && !filterCriteria.get(ConstantsUtils.MARKET_TYPE).equals(ConstantsUtils.ZERO)) {
                    sql += " AND  CM.CONTRACT_TYPE like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.MARKET_TYPE)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.CONTRACT_NO) != null) {
                    sql += " AND CM.CONTRACT_NO like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.CONTRACT_NO)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.CONTRACT_NAME) != null) {
                    sql += " AND CM.CONTRACT_NAME like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.CONTRACT_NAME)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.CONTRACT_HOLDER) != null) {
                    sql += " AND COMP.COMPANY_NAME like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.CONTRACT_HOLDER)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.CFP_NO) != null) {
                    sql += " AND CFPM.CFP_NO like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.CFP_NO)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.CFP_NAME) != null) {
                    sql += " AND CFPM.CFP_NAME like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.CFP_NAME)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.ITEM_FAMILY_PLAN_NO) != null) {
                    sql += " AND  IFPM.IFP_NO like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.ITEM_FAMILY_PLAN_NO)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.ITEM_FAMILY_PLAN_NAME) != null) {
                    sql += " AND IFPM.IFP_NAME like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.ITEM_FAMILY_PLAN_NAME)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.PS_NO) != null) {
                    sql += " AND PS.PS_NO like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.PS_NO)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.PS_NAME) != null) {
                    sql += " AND PS.PS_NAME like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.PS_NAME)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.DEDUCTION_NO) != null) {
                    sql += " AND RSC.RS_NO  like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.DEDUCTION_NO)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.DEDUCTION_NAME) != null) {
                    sql += " AND  RSC.RS_NAME like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.DEDUCTION_NAME)) + "%' ";
                }
                
                if (filterCriteria.get(ConstantsUtils.END_DATE+ConstantsUtils.FROM) != null) {
                    sql += " AND CM.END_DATE  ";
                    sql +=  String.valueOf(filterCriteria.get(ConstantsUtils.END_DATE+ConstantsUtils.FROM)) ;
                }
                 if (filterCriteria.get(ConstantsUtils.END_DATE+ConstantsUtils.TO) != null) {
                    sql += " AND CM.END_DATE  ";
                    sql +=  String.valueOf(filterCriteria.get(ConstantsUtils.END_DATE+ConstantsUtils.TO)) ;
                }
                  if (filterCriteria.get(ConstantsUtils.END_DATE) != null) {
                    sql += " AND CM.END_DATE  ";
                    sql +=  String.valueOf(filterCriteria.get(ConstantsUtils.END_DATE)) ;
                }
                if (filterCriteria.get(ConstantsUtils.START_DATE+ConstantsUtils.FROM) != null) {  
                    sql += " AND CM.START_DATE  ";
                    sql += String.valueOf(filterCriteria.get(ConstantsUtils.START_DATE+ConstantsUtils.FROM)) ;
                }
                 if (filterCriteria.get(ConstantsUtils.START_DATE+ConstantsUtils.TO) != null) {  
                    sql += " AND CM.START_DATE  ";
                    sql += String.valueOf(filterCriteria.get(ConstantsUtils.START_DATE+ConstantsUtils.TO)) ;
                }
                  if (filterCriteria.get(ConstantsUtils.START_DATE) != null) {  
                    sql += " AND CM.START_DATE  ";
                    sql += String.valueOf(filterCriteria.get(ConstantsUtils.START_DATE)) ;
                }
                if (filterCriteria.get("contractMasterSId") != null) {
                    sql += " AND IMD.CONTRACT_MASTER_SID like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get("contractMasterSId")) + "%' ";
                }

                if (filterCriteria.get(ConstantsUtils.NET_SALES_RULENO) != null && !filterCriteria.get(ConstantsUtils.NET_SALES_RULENO).equals(ConstantsUtils.ZERO)) {
                    sql += " AND NS.RULE_NO like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.NET_SALES_RULENO)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.NET_SALES_RULE_NAME) != null && !filterCriteria.get(ConstantsUtils.NET_SALES_RULE_NAME).equals(ConstantsUtils.ZERO)) {
                    sql += " AND NS.RULE_TYPE like '";
                    sql += ConstantsUtils.PERCENCTAGE + String.valueOf(filterCriteria.get(ConstantsUtils.NET_SALES_RULE_NAME)) + "%' ";
                }
                if (filterCriteria.get(ConstantsUtils.INDICATOR_SMALL) != null && !filterCriteria.get(ConstantsUtils.INDICATOR_SMALL).equals(ConstantsUtils.ZERO)) {
                    sql += " AND IMD.INDICATOR like '";
                    sql += ConstantsUtils.PERCENCTAGE +  (String.valueOf(filterCriteria.get(ConstantsUtils.INDICATOR_SMALL)).equals("Add")?"+":"-" ) + "%' ";
                }

                sql += " and IMD.Operation <> 'D' and IMD.INBOUND_STATUS  <> 'D'  ";
                if (isCount) {
                    sql += " ) a";
                } else {
                    sql += " ORDER BY " + column + " " + orderBy;
                    sql = sql + " " + "OFFSET ";
                    sql = sql + start;
                    sql = sql + " ROWS FETCH NEXT " + offset;
                    sql = sql + " ROWS ONLY;";
                }
                List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(sql);
                if (isCount) {
                    return resultList;
                } else {
                    return getCustomizedDtoForContract(resultList, true);
                }
            }
        } catch (Exception e) {

            LOGGER.error(e);
            return null;
        }
    }

    public void removeFromTempTable(SessionDTO sessDto) throws SystemException, PortalException {
//
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append("UPDATE IMTD_DEDUCTION_DETAILS SET \"OPERATION\" = 'D',INBOUND_STATUS = 'D'");
        query.append(" WHERE CHECK_RECORD = 1 ");
        query.append(" AND SESSION_ID = '").append(String.valueOf(sessDto.getUiSessionId())).append(ConstantsUtils.SINGLE_QUOTE);
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());


    }

    public void updateTempTable(boolean checkValue, DeductionDto dto) throws PortalException, SystemException {
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append("UPDATE IMTD_DEDUCTION_DETAILS SET CHECK_RECORD = ").append(checkValue ? 1 : 0);
        query.append(" WHERE IMTD_DEDUCTION_DETAILS_SID = ").append(dto.getTempItemSystemId());
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
    }

    public void updateRuleToTempTable(String ruleSystemId, DeductionDto dedDto, String sessionId) {
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);

        query.append("UPDATE IMTD_DEDUCTION_DETAILS SET CDR_MODEL_SID = ").append("0".equals(ruleSystemId)?"NULL":ruleSystemId);
        query.append(" WHERE IMTD_DEDUCTION_DETAILS_SID = ").append(dedDto.getTempItemSystemId());
        query.append(" AND SESSION_ID = '").append(sessionId).append(ConstantsUtils.SINGLE_QUOTE);

        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
    }

    public void populateLogic(boolean netflag,Object... receivedObj) {
        List convertedList = Arrays.asList(receivedObj);
        final String userId = (String) convertedList.get(0);
        final String sessionId = (String) convertedList.get(1);
        final String populateField = (String) convertedList.get(NumericConstants.THREE);
        final String populateValue = (String) convertedList.get(NumericConstants.FOUR);
        updateForPopulate(netflag,userId, sessionId, populateField, populateValue);
    }

    public void updateForPopulate(boolean netflag,String userId, String sessionId, Object populateField, Object populateValue) {
        try {
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));

            StringBuilder query = new StringBuilder();
            query.append("UPDATE IMTD_DEDUCTION_DETAILS SET ");
            query.append(populateField);
           
            if (netflag) {
                query.append("=");
                query.append(populateValue);
            } else {
                query.append("='");
                query.append(populateValue).append("'");
            }
            query.append(" WHERE USERS_SID='");
            query.append(userId);
            query.append("' AND SESSION_ID='");
            query.append(sessionId);
            query.append("' AND OPERATION <> 'D' AND INBOUND_STATUS <> 'D' AND CHECK_RECORD = 1;");
            CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    Map<String, Object> getFilterValues(final Set<Container.Filter> filterSet, Map<String, Object> filterCriteria) {
   if(filterSet!=null){
        for (Container.Filter filter : filterSet) {

            if (filter instanceof SimpleStringFilter) {
                SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                if (ConstantsUtils.DEDUCTION_TYPE_TABLE.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.DEDUCTION_SUB_TYPE_TABLE.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.DEDUCTION_CATEGORY_TABLE.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.MARKET_TYPE_TABLE.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.CONTRACT_NO.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.CONTRACT_NAME.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.CONTRACT_HOLDER.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.DEDUCTION_NO.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.DEDUCTION_NAME.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.CFP_NO.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.CFP_NAME.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.ITEM_FAMILY_PLAN_NO.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.ITEM_FAMILY_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.PS_NO.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.PS_NAME.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                }
                if (ConstantsUtils.NET_SALES_RULENO.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.NET_SALES_RULE_NAME.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else if (ConstantsUtils.INDICATOR_SMALL.equals(stringFilter.getPropertyId())) {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                } else {
                    filterCriteria.put(stringFilter.getPropertyId().toString(), filterString);
                }
             } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());

                    if (startValue != null) {
                        filterCriteria.put(stringFilter.getPropertyId()+ConstantsUtils.FROM ," >= "+ startValue);
                    } 
                    if (endValue != null) {
                            filterCriteria.put(stringFilter.getPropertyId()+ConstantsUtils.TO," <= "+ endValue);
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                         filterCriteria.put(stringFilter.getPropertyId().toString() ," >= "+ filterString);
                        } else {
                            filterCriteria.put(stringFilter.getPropertyId().toString()," <= "+ filterString);
                        }
                    }
                }
        }
   }
        return filterCriteria;
    }


    public List getNetSalesRuleDetails(String netSalesRuleNo) {
        String sql = "select RULE_NO,RULE_NAME,RULE_TYPE,RULE_CATEGORY,CDR_MODEL_SID from dbo.CDR_MODEL where RULE_NO like '";
        sql += netSalesRuleNo + ConstantsUtils.SINGLE_QUOTE;
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return resultList;
    }
    
    public Date dateToDateConvert(final Date date) throws ParseException {

        LOGGER.debug("dateToDateConvert p1:" + date);
        Date formatedDate = null;
        try {
            final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
            formatedDate = inputFormat.parse(inputFormat.format(date));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return formatedDate;

    }

        public void removeAll(SessionDTO sessDto) throws SystemException, PortalException {
//
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append("DELETE FROM IMTD_DEDUCTION_DETAILS ");
        query.append(" WHERE SESSION_ID = '").append(String.valueOf(sessDto.getUiSessionId())).append(ConstantsUtils.SINGLE_QUOTE);
         final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        query.append(" AND USERS_SID='");
        query.append(userId).append(ConstantsUtils.SINGLE_QUOTE);
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());


    }
  public  boolean isEmpty(SessionDTO sessDto)
     {
         
         final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
         int user = Integer.valueOf(userId);
         String sql = "select count(*) from (select * from dbo.IMTD_DEDUCTION_Details ";

         sql += " WHERE USERS_SID = " + user;
         sql += " AND CHECK_RECORD = 1";
         sql += " AND SESSION_ID like '" + sessDto.getUiSessionId() + "' AND \"OPERATION\" <> 'D') a ";
         
         List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(sql);
       int val=resultList == null ? 0 : Integer.valueOf(String.valueOf(resultList.get(0)));
       return val==0;
     }
  
     public void manualUpdate(String sessionId, Object populateField, Object populateValue, DeductionDto searchForm) {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));

            StringBuilder query = new StringBuilder();
            query.append("UPDATE IMTD_DEDUCTION_DETAILS SET ");
            query.append(populateField);
            query.append("='");
            query.append(populateValue).append("' ");
            query.append(" WHERE USERS_SID='");
            query.append(userId);
            query.append("' AND SESSION_ID='");
            query.append(sessionId);
            query.append("' AND IMTD_DEDUCTION_DETAILS_SID='");
            query.append(searchForm.getTempItemSystemId());
            query.append("' AND OPERATION NOT IN ('D') AND INBOUND_STATUS <> 'D';");
            
            CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    public boolean addDuplicateValidation(final DeductionDto deductionDto, final SessionDTO dto) throws SystemException, PortalException {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdDeductionDetails.class);
        if (deductionDto.getRsContractSid() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", deductionDto.getRsContractSid()));
        }
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.DEDUCTION_TYPE, String.valueOf(deductionDto.getDeductionType().getId())));
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.DEDUCTION_SUB_TYPE, String.valueOf(deductionDto.getDeductionSubType().getId())));
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.DEDUCTION_CATEGORY,String.valueOf(deductionDto.getDeductionCategory().getId())));
        if (deductionDto.getContractMasterSId() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq("contractMasterSid", deductionDto.getContractMasterSId()));
        }
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_ID, Integer.valueOf(userId)));
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, dto.getUiSessionId()));
        dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.D));
        dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.D));
        final long count = ImtdDeductionDetailsLocalServiceUtil.dynamicQueryCount(dynamicQuery);
        return count == 0;
    }
     private static Date parsetDate(String value) throws ParseException {
        Date date = null;
        String tempDate = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !ConstantsUtils.NULL.equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = format.parse(tempDate);
        }

        return date;
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || ConstantsUtils.NULL.equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }

    private static String formatDate(String value) throws ParseException {
        String date = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !ConstantsUtils.NULL.equals(value)) {
            date = format.format(parse.parse(value));
        }
        return date;
    }
        
     public void deleteSelectedFromTemp(SessionDTO sessDto) throws SystemException, PortalException {
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append("UPDATE IMTD_DEDUCTION_DETAILS SET \"OPERATION\" = 'D',INBOUND_STATUS = 'D'");
        query.append(" WHERE SESSION_ID = '").append(sessDto.getUiSessionId()).append(ConstantsUtils.SINGLE_QUOTE);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        query.append(" AND USERS_SID='");
        query.append(userId).append(ConstantsUtils.SINGLE_QUOTE);
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

    }
      
}
