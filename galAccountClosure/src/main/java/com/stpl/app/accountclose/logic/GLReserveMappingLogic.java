/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.common.AbstractFilterLogic;
import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dao.CommonDao;
import com.stpl.app.accountclose.dao.GLReserveMappingDAO;
import com.stpl.app.accountclose.dao.impl.CommonDaoImpl;
import com.stpl.app.accountclose.dao.impl.GLReserveMappingDAOImpl;
import com.stpl.app.accountclose.dto.GLReserveMappingDTO;
import com.stpl.app.accountclose.dto.GLReserveTreeDTO;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.xmlparser.SQlUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.vaadin.data.Container;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author kasiammal.m
 */
public class GLReserveMappingLogic {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(GLReserveMappingLogic.class);
    private final GLReserveMappingDAO dao = new GLReserveMappingDAOImpl();
    private final CommonDao commonDao = CommonDaoImpl.getInstance();
    public static final SimpleDateFormat DBDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public void loadBusinessUnitddl(ComboBox comboBox) {
        String query = "select cm.COMPANY_MASTER_SID,cm.COMPANY_ID from COMPANY_MASTER cm inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID=cm.COMPANY_TYPE and ht.DESCRIPTION='Glcomp'\n"
                + " and INBOUND_STATUS<>'D'";
        List list = (List) CompanyMasterLocalServiceUtil.executeSelectQuery(query, this, this);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obje = (Object[]) list.get(i);
                comboBox.addItem(String.valueOf(obje[1]));
                comboBox.setItemCaption(String.valueOf(obje[1]), String.valueOf(obje[1]));

            }

        }

    }

    public List<HelperDTO> getDropDownList(final String listType, HelperDTO helperDTO) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("entering getDropDownList method with paramater listType=" + listType);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));

            }

        }
        return helperList;

    }

    public void loadComboBox(ComboBox comboBox, List<HelperDTO> dropDownList) {
        for (int i = 0; dropDownList.size() > i; i++) {
            comboBox.addItem(String.valueOf(dropDownList.get(i)
                    .getId()));
            comboBox.setItemCaption(String.valueOf(dropDownList.get(i)
                    .getId()), dropDownList.get(i).getDescription());

        }
    }

    public void loadGLRSVMasterTable() {

        String queryString = commonDao.getQuery(new ArrayList(), "gl.loadGLSystemData");
        dao.exceuteUpdateQuery(queryString, null, null, null);

    }

    public ComboBox comboBoxAddItems(ComboBox comboBox, String queryName, boolean isFilter, String companyMasterSid, String ptSid) {
        comboBox.addItem(isFilter ? Constants.IndicatorConstants.SHOW_ALL.getConstant() : Constants.IndicatorConstants.SELECT_ONE.getConstant());
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(isFilter ? Constants.IndicatorConstants.SHOW_ALL.getConstant() : Constants.IndicatorConstants.SELECT_ONE.getConstant());
        comboBox.select(isFilter ? Constants.IndicatorConstants.SHOW_ALL.getConstant() : Constants.IndicatorConstants.SELECT_ONE.getConstant());
        comboBox.setValidationVisible(true);
        comboBox.setImmediate(true);
        comboBox.addItems(loadDDLB(queryName, !companyMasterSid.isEmpty() ? companyMasterSid : Constants.PERCENT, !ptSid.isEmpty() ? ptSid : Constants.PERCENT));
        return comboBox;
    }

    public ComboBox loadFrequency(ComboBox fre, boolean isFilter) {
        fre.addItem(!isFilter ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : Constants.IndicatorConstants.SHOW_ALL.getConstant());
        fre.setNullSelectionAllowed(true);
        fre.setNullSelectionItemId(!isFilter ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : Constants.IndicatorConstants.SHOW_ALL.getConstant());
        fre.addItems("Annualy", "Semi-Annualy", "Quarterly", "Monthly");
        fre.select(!isFilter ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : Constants.IndicatorConstants.SHOW_ALL.getConstant());
        fre.setImmediate(true);
        return fre;
    }

    public List<HelperDTO> loadDDLB(String queryName, String compSid, String ptSid) {
        List<HelperDTO> retList = new ArrayList<HelperDTO>();
        Map<String, String> input = new HashMap<String, String>();
        input.put("?C_SID?", compSid);
        input.put("?RPT_ID?", ptSid);
        List<Object[]> tempList = (List<Object[]>) dao.exceuteSelectQuery(getQuery(input, queryName), null, null);
        for (Object[] tempList1 : tempList) {
            HelperDTO temp = new HelperDTO();
            if (!"0".equals(String.valueOf(tempList1[1]))) {
                temp.setDescription(String.valueOf(tempList1[0]));
                temp.setId(Integer.parseInt(String.valueOf(tempList1[1])));
                retList.add(temp);
            }
        }
        return retList;
    }

    public List<GLReserveTreeDTO> loadCompany() {
        List<GLReserveTreeDTO> retList = new ArrayList<GLReserveTreeDTO>();
        List<Object[]> tempList = (List<Object[]>) dao.exceuteSelectQuery(getQuery(null, "gl.getCompany"), null, null);
        for (Object[] tempList1 : tempList) {
            GLReserveTreeDTO temp = new GLReserveTreeDTO();
            temp.setLevelValue(String.valueOf(tempList1[0]));
            temp.setLevelId(Integer.parseInt(String.valueOf(tempList1[1])));
            temp.setLevelNo(1);
            retList.add(temp);
        }
        return retList;
    }

    public List<GLReserveTreeDTO> loadDT(String companyId) {
        List<GLReserveTreeDTO> retList = new ArrayList<GLReserveTreeDTO>();
        Map<String, String> input = new HashMap<String, String>();
        input.put("?C_SID?", companyId);
        List<Object[]> tempList = (List<Object[]>) dao.exceuteSelectQuery(getQuery(input, "gl.loadDType"), null, null);
        for (Object[] tempList1 : tempList) {
            GLReserveTreeDTO temp = new GLReserveTreeDTO();
            temp.setLevelValue(String.valueOf(tempList1[0]));
            temp.setLevelId(Integer.parseInt(String.valueOf(tempList1[1])));
            temp.setLevelNo(2);
            retList.add(temp);
        }
        return retList;
    }

    public List<GLReserveTreeDTO> loadDST(String companyId, String rptId) {
        List<GLReserveTreeDTO> retList = new ArrayList<GLReserveTreeDTO>();
        Map<String, String> input = new HashMap<String, String>();
        input.put("?C_SID?", companyId);
        input.put("?RPT_ID?", rptId);
        List<Object[]> tempList = (List<Object[]>) dao.exceuteSelectQuery(getQuery(input, "gl.loadDSType"), null, null);
        for (Object[] tempList1 : tempList) {
            GLReserveTreeDTO temp = new GLReserveTreeDTO();
            temp.setLevelValue(String.valueOf(tempList1[0]));
            temp.setLevelId(Integer.parseInt(String.valueOf(tempList1[1])));
            temp.setLevelNo(3);
            retList.add(temp);
        }
        return retList;
    }

    public List<GLReserveMappingDTO> loadIndexTableData(GLReserveMappingDTO binderDto, boolean flag) throws PortalException, Exception {
        return configuredSearchResult((List<Object[]>) dao.exceuteSelectQuery(getQuery(getIndexInput(binderDto, flag, true), "gl.loadSearchTable"), null, null));
    }

    public int loadIndexTableCount(GLReserveMappingDTO binderDto, boolean flag) throws PortalException, Exception {
        List<Object[]> listCount = dao.exceuteSelectQuery(getQuery(getIndexInput(binderDto, flag, false), "gl.loadCountSearchTable"), null, null);
        return getCount(listCount);
    }

    public String getQuery(final Map<String, String> input, final String queryName) {
        StringBuilder queryString = new StringBuilder(commonDao.getQuery(new ArrayList(), queryName));
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        return queryString.toString();
    }

    private List<GLReserveMappingDTO> configuredSearchResult(List<Object[]> resultList) throws PortalException, Exception {
        List<GLReserveMappingDTO> retList = new ArrayList<GLReserveMappingDTO>();
        for (Object[] tempList1 : resultList) {
            try {
                int j = -1;
                GLReserveMappingDTO temp = new GLReserveMappingDTO();
                temp.setGlResMasterSid(Integer.parseInt(String.valueOf(tempList1[++j])));
                temp.setBusinessUnit(new HelperDTO(String.valueOf(tempList1[++j])));
                temp.setProgramType(new HelperDTO(String.valueOf(tempList1[++j])));
                temp.setProgramsubType(new HelperDTO(String.valueOf(tempList1[++j])));
                try {
                    String str = String.valueOf(tempList1[++j]);
                    if (!str.equals("0") && !str.equals("1")) {
                        User user = new CommonLogic().getUserById((String.valueOf(str)));
                        temp.setCreatedBy(String.valueOf(user.getLastName() + "," + user.getFirstName()));
                    }
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }

                temp.setCreatedDate(CommonUtil.parsetDate(String.valueOf(tempList1[++j])));
                try {
                    String str = String.valueOf(tempList1[++j]);
                    if (!str.equals("0") && !str.equals("1")) {
                        User user = new CommonLogic().getUserById((str));
                        temp.setModifiedBy(String.valueOf(user.getLastName() + "," + user.getFirstName()));
                    }

                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }
                temp.setModifiedDate(CommonUtil.parsetDate(String.valueOf(tempList1[++j])));
                temp.setSource(String.valueOf(tempList1[++j]));
                temp.setFrequency(tempList1[j+1] != null &&!"null".equals(String.valueOf(tempList1[j+1])) ? (String.valueOf(tempList1[j+1])): " ");
                retList.add(temp);
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        }
        return retList;
    }

    public void saveDetails(List<GLReserveMappingDTO> results, String businessProcess) {
        for (GLReserveMappingDTO result : results) {
            Map<String, String> input = new HashMap<String, String>();
            input.put("?SID?", String.valueOf(result.getGlResMasterSid()));
            input.put("?TYP?", String.valueOf(result.getAcctType().getId()));
            input.put("?AID?", result.getAccountID());
            input.put("?IND?", "Positive".equals(result.getIndicator()) ? "true" : "false");
            input.put("?PIND?", result.getPeriodIndicator());
            Integer count = getCount((List<Object[]>) dao.exceuteSelectQuery(getQuery(input, "gl.recCount"), null, null));
            if (count == 0) {
                Map<String, String> input1 = new HashMap<String, String>();
                input1.put("?SID?", String.valueOf(result.getGlResMasterSid()));
                input1.put("?TYP?", String.valueOf(result.getAcctType().getId()));
                input1.put("?AID?", result.getAccountID());
                input1.put("?ANO?", result.getAccountnumber());
                input1.put("?ADESC?", result.getAccountdesc());
                input1.put("?REMARK?", result.getRemark());
                input1.put("?SLTYP?", result.getSubledgertype());
                input1.put("?IND?", "Positive".equals(result.getIndicator()) ? "true" : "false");
                input1.put("?PIND?", result.getPeriodIndicator());
                input1.put("?BP?", businessProcess);
                dao.exceuteUpdateQuery(getQuery(input1, "gl.accReserveInsert"), null, null, null);
            } else {
                Map<String, String> input1 = new HashMap<String, String>();
                input1.put("?ANO?", result.getAccountnumber());
                input1.put("?ADESC?", result.getAccountdesc());
                input1.put("?REMARK?", result.getRemark());
                input1.put("?SLTYP?", result.getSubledgertype());
                input1.put("?SID?", String.valueOf(result.getGlResMasterSid()));
                input1.put("?TYP?", String.valueOf(result.getAcctType().getId()));
                input1.put("?AID?", result.getAccountID());
                input1.put("?IND?", "Positive".equals(result.getIndicator()) ? "true" : "false");
                input1.put("?PIND?", result.getPeriodIndicator());
                input1.put("?BP?", businessProcess);
                dao.exceuteUpdateQuery(getQuery(input1, "gl.accReserveUpdate"), null, null, null);
            }
        }
    }

    public List<GLReserveMappingDTO> loadAccDtls(String glmastrSid, boolean isHistory) {
        Map<String, String> input = new HashMap<String, String>();
        String query = StringUtils.EMPTY;
        input.put("?GLM_SID?", glmastrSid);
        if (isHistory) {
            query = "gl.loadHistAccDetailsTable";
        } else {
            query = "gl.loadAccDetailsTable";
        }
        return configuredAccDtls((List<Object[]>) dao.exceuteSelectQuery(getQuery(input, query), null, null), isHistory);
    }

    private List<GLReserveMappingDTO> configuredAccDtls(List<Object[]> resultList, boolean isHistory) {
        List<GLReserveMappingDTO> retList = new ArrayList<GLReserveMappingDTO>();
        for (Object[] tempList1 : resultList) {
            int j = -1;
            GLReserveMappingDTO temp = new GLReserveMappingDTO();
            temp.setGlResMasterSid(Integer.parseInt(String.valueOf(tempList1[++j])));
            temp.setAcctType(new HelperDTO(Integer.parseInt(String.valueOf(tempList1[++j])), String.valueOf(tempList1[++j])));
            temp.setAcctTypeId(temp.getAcctType().getDescription());
            temp.setAccountID(String.valueOf(tempList1[++j]));
            temp.setAccountnumber(String.valueOf(tempList1[++j]));
            temp.setAccountdesc(String.valueOf(tempList1[++j]));
            temp.setRemark(String.valueOf(tempList1[++j]));
            temp.setSubledgertype(String.valueOf(tempList1[++j]));
            temp.setIndicator("true".equals(String.valueOf(tempList1[++j])) ? "Positive" : "Negative");
            temp.setPeriodIndicator(String.valueOf(tempList1[++j]));
            temp.setBusinessProcess(String.valueOf(tempList1[++j]));
            if (isHistory) {
                try {
                    temp.setActiveFrom(CommonUtil.parsetDate(String.valueOf(tempList1[++j])));
                    temp.setActiveTo(CommonUtil.parsetDate(String.valueOf(tempList1[++j])));
                } catch (ParseException ex) {
                    LOGGER.error(ex);
                }
            }
            retList.add(temp);
        }
        return retList;
    }

    public void removeLogic(List<GLReserveMappingDTO> removeList) {
        if (!removeList.isEmpty()) {
            for (GLReserveMappingDTO result : removeList) {
                Map<String, String> input1 = new HashMap<String, String>();
                input1.put("?SID?", String.valueOf(result.getGlResMasterSid()));
                input1.put("?TYP?", String.valueOf(result.getAcctType().getId()));
                input1.put("?AID?", result.getAccountID());
                input1.put("?IND?", "Positive".equals(result.getIndicator()) ? "true" : "false");
                input1.put("?PIND?", result.getPeriodIndicator());
                dao.exceuteUpdateQuery(getQuery(input1, "gl.resAccRemoveLine"), null, null, null);
            }
        }
    }

    private int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    public void saveFrequencyLogic(String frequency, String glmasterSid) {
        String userId = (String) VaadinSession.getCurrent().getAttribute("userId");
        Map<String, String> input1 = new HashMap<String, String>();
        input1.put("?FREQ?", frequency);
        input1.put("?GL_SID?", glmasterSid);
        input1.put("?CREATEDBY?", userId);
        input1.put("?MODIFIEDBY?", userId);
        dao.exceuteUpdateQuery(getQuery(input1, "gl.saveFrequency"), null, null, null);
    }

    public List<String> loadCategoryDDLB() {
        List<String> retList = new ArrayList<String>();

        String sql = "Select distinct RULE_FLOW_GROUP_NAME FROM TRANSACTION_RULES_DEFINITION ";
        retList = (List<String>) dao.exceuteSelectQuery(sql, null, null);
        return retList;
    }

    public List<GLReserveMappingDTO> loadAvailableRules(String category) throws ParseException {
        List<GLReserveMappingDTO> resultList = new ArrayList<GLReserveMappingDTO>();

        String sql = "SELECT TRANSACTION_RULES_DEF_SID,RULE_NAME,VERSION_NO,RULE_FLOW_GROUP_NAME as CATEGORY,CREATED_DATE as createdDate,MODIFIED_DATE as modifiedDate,QUERY FROM TRANSACTION_RULES_DEFINITION ";

        if (!category.equals(StringUtils.EMPTY)) {
            sql = sql + " WHERE  RULE_FLOW_GROUP_NAME='" + category + "'  ";
        }
        GLReserveMappingDTO dto = null;
        List<Object[]> list = (List<Object[]>) dao.exceuteSelectQuery(sql, null, null);
        for (int i = 0; i < list.size(); i++) {
            dto = new GLReserveMappingDTO();
            Object[] obj = (Object[]) list.get(i);
            dto.setIdvalue(String.valueOf(obj[0]));
            dto.setName(String.valueOf(obj[1]));
            dto.setVersion(String.valueOf(obj[2]));
            dto.setCategory(String.valueOf(obj[3]));
            if (StringUtils.isNotBlank(String.valueOf(obj[4])) && obj[4] != "null") {
                dto.setCreatedDate(DBDate.parse(DBDate.format((Date) obj[4])));
            } else {
                dto.setCreatedDate(null);
            }
            if (StringUtils.isNotBlank(String.valueOf(obj[5])) && obj[5] != "null") {
                dto.setModifiedDate(DBDate.parse(DBDate.format((Date) obj[5])));
            } else {
                dto.setModifiedDate(null);
            }
            dto.setQueryString(String.valueOf(obj[6]));
            resultList.add(dto);
        }

        return resultList;
    }

    public void saveSelectedRules(List<GLReserveMappingDTO> resultList, int acMasterSid) {
        String versionNo = StringUtils.EMPTY;
        String finalSql = "SELECT SALES_MASTER_SID,"
                + " SALES_ID,"
                + " COMPANY_ID,"
                + " CONTRACT_ID,"
                + " CONTRACT_NO,"
                + " WHOLESALE_OWNER_ID,"
                + " ACCOUNT_ID,"
                + " ACCOUNT_NO,"
                + " ACCOUNT_NAME,"
                + " IDENTIFIER_CODE_QUALIFIER,"
                + " BRAND_ID,"
                + " ITEM_ID,"
                + " ITEM_NO,"
                + " ITEM_CODE_QUALIFIER,"
                + " PROVISION_ID,"
                + " CONTRACT_MASTER_SID,"
                + " COMPANY_MASTER_SID,"
                + " BRAND_MASTER_SID,"
                + " ITEM_MASTER_SID,"
                + " RS_MODEL_SID,"
                + " AMOUNT,"
                + " INVOICE_DATE"
                + " FROM   SALES_MASTER   WHERE ";
        String sql = StringUtils.EMPTY;
        dao.exceuteUpdateQuery(" DELETE FROM  AC_GL_RESERVE_ACCOUNT_RULES WHERE AC_GL_RSV_MAPPING_MASTER_SID=" + acMasterSid + " ", null, null, null);
        for (GLReserveMappingDTO glReserveMappingDTO : resultList) {
            if (sql.equals(StringUtils.EMPTY)) {
                sql = sql + glReserveMappingDTO.getQueryString();

            } else {
                sql = sql + " AND " + glReserveMappingDTO.getQueryString();
            }
            versionNo = glReserveMappingDTO.getVersion();
            dao.exceuteUpdateQuery(" INSERT INTO AC_GL_RESERVE_ACCOUNT_RULES(AC_GL_RSV_MAPPING_MASTER_SID, TRANSACTION_RULES_DEF_SID,VERSION_NO) VALUES('" + acMasterSid + "','" + glReserveMappingDTO.getIdvalue() + "','" + glReserveMappingDTO.getVersion() + "')", null, null, null);
        }
        sql = sql.replace("'", "''");
        dao.exceuteUpdateQuery(" DELETE FROM  AC_GL_RULES_MASTER WHERE AC_GL_RSV_MAPPING_MASTER_SID=" + acMasterSid + " ", null, null, null);
        dao.exceuteUpdateQuery(" INSERT INTO AC_GL_RULES_MASTER(AC_GL_RSV_MAPPING_MASTER_SID, RULES_LIST,VERSION_NO) VALUES('" + acMasterSid + "','" + finalSql + sql + "','" + versionNo + "')", null, null, null);
    }

    public void saveRules(int acMasterSid) {

        String finalSql = "SELECT SALES_MASTER_SID,"
                + " SALES_ID,"
                + " COMPANY_ID,"
                + " CONTRACT_ID,"
                + " CONTRACT_NO,"
                + " WHOLESALE_OWNER_ID,"
                + " ACCOUNT_ID,"
                + " ACCOUNT_NO,"
                + " ACCOUNT_NAME,"
                + " IDENTIFIER_CODE_QUALIFIER,"
                + " BRAND_ID,"
                + " ITEM_ID,"
                + " ITEM_NO,"
                + " ITEM_CODE_QUALIFIER,"
                + " PROVISION_ID,"
                + " CONTRACT_MASTER_SID,"
                + " COMPANY_MASTER_SID,"
                + " BRAND_MASTER_SID,"
                + " ITEM_MASTER_SID,"
                + " RS_MODEL_SID,"
                + " AMOUNT,"
                + " INVOICE_DATE"
                + " FROM   SALES_MASTER    ";


        dao.exceuteUpdateQuery(" INSERT INTO AC_GL_RULES_MASTER(AC_GL_RSV_MAPPING_MASTER_SID, RULES_LIST,VERSION_NO) VALUES('" + acMasterSid + "','" + finalSql + "','1')", null, null, null);
    }

    public Collection<? extends GLReserveMappingDTO> loadSavedRules(String glMastersid, boolean isHistory) throws Exception {
        List<Object[]> list = new ArrayList<Object[]>();
        Map<String, String> input = new HashMap<String, String>();
        String query = StringUtils.EMPTY;
        input.put("?AC_GL_RSV_MAPPING_MASTER_SID?", glMastersid);
        if (isHistory) {
            query = "historyrules";
        } else {
            query = "loadselectedrules";
        }
        list = (List<Object[]>) dao.exceuteSelectQuery(getQuery(input, query), null, null);
        return getCustomisedDto(list);
    }

    private Collection<? extends GLReserveMappingDTO> getCustomisedDto(List<Object[]> list) throws Exception {
        List<GLReserveMappingDTO> resultList = new ArrayList<GLReserveMappingDTO>();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                GLReserveMappingDTO dto = new GLReserveMappingDTO();
                Object[] obj = (Object[]) list.get(i);
                dto.setIdvalue(String.valueOf(obj[0]));
                dto.setName(String.valueOf(obj[1]));
                dto.setVersion(String.valueOf(obj[2]));
                dto.setCategory(String.valueOf(obj[3]));
                if (StringUtils.isNotBlank(String.valueOf(obj[4])) && obj[4] != "null") {
                    dto.setCreatedDate(DBDate.parse(DBDate.format((Date) obj[4])));
                } else {
                    dto.setCreatedDate(null);
                }
                if (StringUtils.isNotBlank(String.valueOf(obj[5])) && obj[5] != "null") {
                    dto.setModifiedDate(DBDate.parse(DBDate.format((Date) obj[5])));
                } else {
                    dto.setModifiedDate(null);
                }
                dto.setQueryString(String.valueOf(obj[6]));
                resultList.add(dto);
            }
        }
        return resultList;
    }

    public boolean deleteMethodLogic(GLReserveMappingDTO glReserveMappingDTO) {
        Map<String, String> input1 = new HashMap<String, String>();
        input1.put("?AC_GL_RSV_MAPPING_MASTER_SID?", String.valueOf(glReserveMappingDTO.getGlResMasterSid()));
        try {
            dao.exceuteUpdateQuery(getQuery(input1, "deleteQuery"), null, null, null);
            return true;
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    public List getVersionNo(boolean versionFlag, String systemId) {
        Map<String, String> input = new HashMap<String, String>();
        String query = StringUtils.EMPTY;
        if (versionFlag) {
            input.put("?AC_GL_RSV_MAPPING_MASTER_SID?", systemId);
            query = "versionNoFromGLtable";
        } else {
            input.put("?TRANSACTION_RULES_DEFINITION_SID?", systemId);
            query = "versionNoFromBPMtables";
        }
        return dao.exceuteSelectQuery(getQuery(input, query), null, null);
    }

    public void updateGlVersionNo(String systemId, String bpmVersionNo) {
        Map<String, String> input1 = new HashMap<String, String>();
        input1.put("?AC_GL_RSV_MAPPING_MASTER_SID?", systemId);
        input1.put("?VERSION_NO?", bpmVersionNo);
        dao.exceuteUpdateQuery(getQuery(input1, "updateGlVersionNo"), null, null, null);
    }

    private Map<String, String> getIndexInput(GLReserveMappingDTO binderDto, boolean flag, boolean isCount) {
        String companyId = CommonUtils.isNull(binderDto.getBusinessUnit() != null ? String.valueOf(((HelperDTO) binderDto.getBusinessUnit()).getId()) : StringUtils.EMPTY);
        String dtId = CommonUtils.isNull(binderDto.getProgramType() != null ? String.valueOf(((HelperDTO) binderDto.getProgramType()).getId()) : StringUtils.EMPTY);
        String dstId = CommonUtils.isNull(binderDto.getProgramsubType() != null ? String.valueOf(((HelperDTO) binderDto.getProgramsubType()).getId()) : StringUtils.EMPTY);
        String frequency = CommonUtils.isNull(binderDto.getFrequency() != null ? String.valueOf(binderDto.getFrequency()) : StringUtils.EMPTY);
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(), getFilterMap()).toString();
        String orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(binderDto.getSortedList(), getFilterMap()).toString();
        Map<String, String> input = new HashMap<>();
        input.put("?CM_SID?", companyId);
        input.put("?DT_SID?", dtId);
        input.put("?DST_SID?", dstId);
        input.put("?FREQUENCY?", frequency);
        if (flag) {
            input.put("?WHERE?", "AND GLM.FREQUENCY <> '' ");
        } else {
            input.put("?WHERE?", " ");
        }
        input.put("?FILTER?", filterQuery);
        input.put("?SORT?", orderBy);
        if (isCount) {
            input.put("?ST?", String.valueOf(binderDto.getStartIndex()));
            input.put("?OFF?", String.valueOf(binderDto.getEndIndex()));
        }
        return input;
    }

    public String getQueryFromApp(final Map<String, String> input, final String queryName) {
        StringBuilder queryString = new StringBuilder(SQlUtil.getQuery(queryName));
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        return queryString.toString();
    }

    private Map<String, String> getFilterMap() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("businessUnit", "CM.COMPANY_NAME");
        filterMap.put("programType", "DT.DESCRIPTION");
        filterMap.put("programsubType", "DST.DESCRIPTION");
        filterMap.put("frequency", "GLM.FREQUENCY");
        filterMap.put("createdBy", "GLM.CREATED_BY");
        filterMap.put("createdDate", "GLM.CREATED_DATE");
        filterMap.put("modifiedBy", "GLM.MODIFIED_BY");
        filterMap.put("modifiedDate", "GLM.MODIFIED_DATE");
        filterMap.put("source", "GLM.\"SOURCE\"");
        filterMap.put(StringUtils.EMPTY, "GLM.AC_GL_RSV_MAPPING_MASTER_SID");
        return filterMap;
    }
}
