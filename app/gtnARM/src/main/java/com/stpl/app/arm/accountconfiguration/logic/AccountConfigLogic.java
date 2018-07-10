/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import com.stpl.app.arm.common.CommonFilterLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.app.utils.SysDataSourceConnection;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.ui.ComboBox;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class AccountConfigLogic {

    private static AccountConfigLogic accountConfigLogic;
    private HelperDTO selectOneDto = new HelperDTO(NumericConstants.ZERO, GlobalConstants.getSelectOne());
    private HelperDTO showAllDto = new HelperDTO(NumericConstants.ZERO, ConstantsUtils.SHOW_ALL);
    private final DateFormat dbDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    /**
     * The Constant LOGGER.
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AccountConfigLogic.class);

    private AccountConfigLogic() {
    }

    public static synchronized AccountConfigLogic getInstance() {
        if (accountConfigLogic == null) {
            accountConfigLogic = new AccountConfigLogic();
        }
        return accountConfigLogic;
    }

    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<>();
        try {
            resultList = new QueryUtils().fetchFieldsForSecurity(moduleName, tabName);
            LOGGER.debug("resultList -- >>" + resultList.size());
        } catch (Exception ex) {
            LOGGER.error("Error in getFieldsForSecurity :" + ex);
        }
        return resultList;
    }

    public static Map<Integer, String> getUserName() throws SystemException {
        LOGGER.info("Enters getUserName method");
        Map<Integer, String> userMap = CommonUtils.getUserMap();
        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            for (User user : userList) {
                userMap.put((int) user.getUserId(), user.getFullName());
            }
            CommonUtils.setUserMap(userMap);
            LOGGER.debug("userMap -->" + userMap.size());
            LOGGER.info("End of getUserName method");
        } catch (Exception ex) {
            LOGGER.error("Error in getUserName :" + ex);
        }
        return userMap;
    }

    /**
     * Method to get counts of account and config
     *
     * @param binderDto
     * @return int- count
     */
    public int getAccountConfigCount(final AccountConfigDTO binderDto) {
        int count = NumericConstants.ZERO;
        try {
            binderDto.setCountCheck(true);
            List searchInput = getSearchInputList(binderDto);
            count = CommonLogic.getCount(QueryUtils.getItemData(searchInput, "searchAccountConfig-count", null));
        } catch (Exception e) {
            LOGGER.error("Error in getAccountConfigCount :" + e);
        }
        return count;
    }

    public List getAccountConfigData(final AccountConfigDTO binderDto) throws SQLException {
        binderDto.setCountCheck(false);
        List searchInput = getSearchInputList(binderDto);
        StringBuilder orderByQuery = CommonFilterLogic.getInstance().orderByQueryGenerator(binderDto.getSortByColumns(), getSortingMap(), "ACC.GL_COMPANY_MASTER_SID");
        searchInput.add(orderByQuery);
        searchInput.add(binderDto.getStart());
        searchInput.add(binderDto.getOffset());

        List<Object[]> queryResultList = QueryUtils.getItemData(searchInput, "searchAccountConfig-Data", null);
        return getCustomizedAccountConfigResultList(queryResultList);
    }

    private List getSearchInputList(final AccountConfigDTO binderDto) throws SQLException {
        List inputList = new ArrayList<>();
        inputList = getInputs(binderDto, inputList);
        StringBuilder sql = CommonFilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(), binderDto.isCountCheck() ? getFilterMapForCount() : getFilterMap());
        if (sql != null) {
            if (!binderDto.isCountCheck()) {
                inputList.add(sql.toString().replace(CommonConstant.WHERE, CommonConstant.AND));
            } else {
                inputList.add(sql.toString());
            }
        } else {
            inputList.add(StringUtils.EMPTY);
        }
        return inputList;
    }

    private List<AccountConfigDTO> getCustomizedAccountConfigResultList(List<Object[]> resultList) {
        LOGGER.debug("--Inside getCustomizedAccountConfigResultList--");
        List<AccountConfigDTO> fullDataList = new ArrayList<>();
        if (resultList != null && !resultList.isEmpty()) {
            for (Object[] str : resultList) {
                AccountConfigDTO dto = new AccountConfigDTO();
                dto.setCompanyNo(str[NumericConstants.ZERO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.ZERO]));
                dto.setCompanyName(str[NumericConstants.ONE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.ONE]));
                dto.setBusinessUnitNo(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
                dto.setBusinessUnitName(str[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
                dto.setAccount(str[NumericConstants.FOUR] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
                dto.setCostCentre(str[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
                dto.setBrand(str[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIX]));
                dto.setCreatedBy(str[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
                dto.setCreatedDate(str[NumericConstants.EIGHT] == null ? null : dbDate.format((Date) str[NumericConstants.EIGHT]));
                dto.setModifiedDate(str[NumericConstants.NINE] == null ? null : dbDate.format((Date) str[NumericConstants.NINE]));
                dto.setSource(str[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TEN]));
                dto.setCompanySid(str[NumericConstants.ELEVEN] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.ELEVEN])));
                dto.setBuSid(str[NumericConstants.TWELVE] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.TWELVE])));
                dto.setBrandSid(str[NumericConstants.THIRTEEN] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.THIRTEEN])));
                dto.setMasterId(str[NumericConstants.FOURTEEN] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.FOURTEEN])));
                dto.setCompanyIdWithName(str[NumericConstants.FIFTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIFTEEN]));
                dto.setBuIdWithName(str[NumericConstants.SIXTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIXTEEN]));
                dto.setBrandWithIdName(str[NumericConstants.SEVENTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVENTEEN]));
                fullDataList.add(dto);
            }
        }
        LOGGER.debug("fullDataList--" + fullDataList.size());
        return fullDataList;
    }

    private Map<String, String> getFilterMap() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put(CommonConstant.COMPANY_NO, "CM.COMPANY_NO");
        filterMap.put(CommonConstant.COMPANY_NAME, "cm.COMPANY_NAME");
        filterMap.put(CommonConstant.BUSINESS_UNIT_NO, "BU.COMPANY_NO");
        filterMap.put(CommonConstant.BUSINESS_UNIT_NAME, "BU.COMPANY_NAME");
        filterMap.put(CommonConstant.ACCOUNT, "ACC.ACCOUNT");
        filterMap.put(CommonConstant.COST_CENTRE, "ACC.COST_CENTER");
        filterMap.put(CommonConstant.BRAND, CommonConstant.BM_BRAND_NAME);
        filterMap.put(CommonConstant.CREATED_BY, VariableConstants.CRFIRST_NAME_CRLAST_NAME);
        filterMap.put(CommonConstant.CREATED_DATE, "convert(varchar(10),ACC.CREATED_DATE, 101) + ' ' +right(convert(varchar(32),ACC.CREATED_DATE,108),8)");
        filterMap.put(CommonConstant.MODIFIED_DATE, "convert(varchar(10),ACC.MODIFIED_DATE, 101) + ' ' + right(convert(varchar(32),ACC.MODIFIED_DATE,108),8)");
        filterMap.put(CommonConstant.SOURCE, "ACC.\"SOURCE\"");
        return filterMap;
    }

    private Map<String, String> getSortingMap() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put(CommonConstant.COMPANY_NO, "CM.COMPANY_NO");
        filterMap.put(CommonConstant.COMPANY_NAME, "cm.COMPANY_NAME");
        filterMap.put(CommonConstant.BUSINESS_UNIT_NO, "BU.COMPANY_NO");
        filterMap.put(CommonConstant.BUSINESS_UNIT_NAME, "BU.COMPANY_NAME");
        filterMap.put(CommonConstant.ACCOUNT, "ACC.ACCOUNT");
        filterMap.put(CommonConstant.COST_CENTRE, "ACC.COST_CENTER");
        filterMap.put(CommonConstant.BRAND, CommonConstant.BM_BRAND_NAME);
        filterMap.put(CommonConstant.CREATED_BY, VariableConstants.CRFIRST_NAME_CRLAST_NAME);
        filterMap.put(CommonConstant.CREATED_DATE, "ACC.CREATED_DATE");
        filterMap.put(CommonConstant.MODIFIED_DATE, "ACC.MODIFIED_DATE");
        filterMap.put(CommonConstant.SOURCE, "ACC.\"SOURCE\"");
        return filterMap;
    }

    public List<Object[]> loadAccountDdlb(List inputList) {
        return QueryUtils.getItemData(inputList, "loadAccountDdlb", null);
    }

    public int addLine(AccountConfigSelection selection) {
        List input = new ArrayList<>(NumericConstants.THREE);
        input.add(selection.getTempTableName());
        input.add(selection.getSession().getUserId());
        List dataList1 = QueryUtils.getItemData(input, "ADD_Line_to_Temp_tableFor AccountConfig", null);
        if (!dataList1.isEmpty()) {
            return Integer.valueOf(String.valueOf(dataList1.get(0)));
        } else {
            return 0;
        }

    }

    /**
     * Method to insert a new record in DB when the user hit addline with the
     * same combination of company and BU.
     *
     * @param selection
     * @return
     */
    public int addLineForEditMode(AccountConfigSelection selection) {
        List input = new ArrayList<>(NumericConstants.THREE);
        input.add(selection.getTempTableName());
        input.add(selection.getSearchAccConfigDTO().getCompanySid());
        input.add(selection.getSearchAccConfigDTO().getBuSid());
        input.add(selection.getSession().getUserId());
        Boolean success = QueryUtils.itemUpdate(input, "addLine-EditMode");
        return success ? 0 : 1;

    }

    public int getAccountConfigCount(AccountConfigSelection selection, Set<Container.Filter> filters) throws SQLException {
        List<Object> input = new ArrayList();
        List<Object> inputtemp = new ArrayList();
        List<Object[]> data = new ArrayList<>();
        StringBuilder account = new StringBuilder();
        StringBuilder brand = new StringBuilder();
        try (Connection con = SysDataSourceConnection.getConnection()) {
            input.add(selection.getTempTableName());
            input.add(con.getCatalog());
            StringBuilder filterQuery = CommonFilterLogic.getInstance().filterQueryGenerator(filters, loadFilterQueryMap());
            input.add(filterQuery != null ? filterQuery.toString() : StringUtils.EMPTY);
            if (selection.isCurrentView()) {
                data = QueryUtils.getItemData(input, "AccoutConfig-Count", null);
            } else {
                if (!"ADD".equals(selection.getSession().getMode()) && !"Copy".equalsIgnoreCase(selection.getSession().getMode())) {
                    input.remove(selection.getTempTableName());
                    input.add(0, "HIST_ARM_ACC_CONFIG");
                    inputtemp.add(selection.getTempTableName());

                    input.add(selection.getSearchAccConfigDTO().getCompanySid());
                    input.add(selection.getSearchAccConfigDTO().getBuSid());
                    if ("View".equalsIgnoreCase(selection.getSession().getMode())) {
                        account.append("'").append(selection.getSearchAccConfigDTO().getAccount()).append("'");
                        input.add(account);
                        input.add(selection.getSearchAccConfigDTO().getBrandSid());
                    } else {
                        List<Object> list = QueryUtils.getItemData(inputtemp, "temptable-Account", null);
                        List<Object> list1 = QueryUtils.getItemData(inputtemp, "temptable-Brand", null);

                        for (Object obj1 : list1) {
                            brand.append(obj1).append(",");
                        }
                        brand.replace(brand.lastIndexOf(","), brand.lastIndexOf(",") + 1, "");
                        for (Object obj1 : list) {
                            account.append("'").append(obj1).append("'").append(",");
                        }
                        account.replace(account.lastIndexOf(","), account.lastIndexOf(",") + 1, "");
                        input.add(account);
                        input.add(brand);
                    }
                    input.remove(2);
                    input.add(filterQuery != null ? filterQuery.toString() : StringUtils.EMPTY);
                    data = QueryUtils.getItemData(input, "AccoutConfig-Count-History", null);
                }
            }
            return CommonLogic.getCount(data);
        }
    }

    /**
     *
     *
     * @param selection
     * @param start
     * @param offset
     * @param filters
     * @param sortByColumns
     * @return List<AccountConfigDTO>
     * @throws SQLException
     * @throws java.lang.Exception
     */
    public List getAccountConfigData(AccountConfigSelection selection, int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws SQLException {
        List<Object> input = new ArrayList();
        List<Object> inputtemp = new ArrayList();
        List<Object[]> data = new ArrayList<>();
        StringBuilder account = new StringBuilder();
        StringBuilder brand = new StringBuilder();
        try (Connection con = SysDataSourceConnection.getConnection()) {
            input.add(selection.getTempTableName());
            input.add(con.getCatalog());
            StringBuilder filterQuery = CommonFilterLogic.getInstance().filterQueryGenerator(filters, loadFilterQueryMapForLoad());

            if (selection.isCurrentView()) {
                input.add(filterQuery != null ? filterQuery.toString().replace(CommonConstant.WHERE, CommonConstant.AND) : StringUtils.EMPTY);
                StringBuilder sortByQuery = CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, loadSortedQueryMap(), selection.isCurrentView() ? "ARM_ACC_CONFIG_SID" : "GL_COMPANY_MASTER_SID");
                input.add(sortByQuery);
                input.add(start);
                input.add(offset);
                data = QueryUtils.getItemData(input, "AccoutConfig-LoadData", null);
            } else {
                if (!"ADD".equals(selection.getSession().getMode()) && !"Copy".equalsIgnoreCase(selection.getSession().getMode())) {
                    input.remove(selection.getTempTableName());
                    input.add(0, "HIST_ARM_ACC_CONFIG");
                    inputtemp.add(selection.getTempTableName());
                    input.add(selection.getSearchAccConfigDTO().getCompanySid());
                    input.add(selection.getSearchAccConfigDTO().getBuSid());
                    if ("View".equalsIgnoreCase(selection.getSession().getMode())) {
                        account.append("'").append(selection.getSearchAccConfigDTO().getAccount()).append("'");
                        input.add(account);
                        input.add(selection.getSearchAccConfigDTO().getBrandSid());
                    } else {
                        List<Object> list = QueryUtils.getItemData(inputtemp, "temptable-Account", null);
                        List<Object> list1 = QueryUtils.getItemData(inputtemp, "temptable-Brand", null);

                        for (Object obj1 : list1) {
                            brand.append(obj1).append(",");
                        }
                        brand.replace(brand.lastIndexOf(","), brand.lastIndexOf(",") + 1, "");
                        for (Object obj1 : list) {
                            account.append("'").append(obj1).append("'").append(",");
                        }
                        account.replace(account.lastIndexOf(","), account.lastIndexOf(",") + 1, "");
                        input.add(account);
                        input.add(brand);
                    }
                    input.add(filterQuery != null ? filterQuery.toString().replace(CommonConstant.WHERE, CommonConstant.AND) : StringUtils.EMPTY);
                    StringBuilder sortByQuery = CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, loadSortedQueryMap(), selection.isCurrentView() ? "ARM_ACC_CONFIG_SID" : "GL_COMPANY_MASTER_SID");
                    input.add(sortByQuery);
                    input.add(start);
                    input.add(offset);
                    data = QueryUtils.getItemData(input, "AccoutConfig-LoadData-History", null);
                }
            }
            return customizeAccConfig(data);
        }
    }

    /**
     * Popp up screen , Table UI customization
     *
     * @param data
     * @return List<AccountConfigDTO>
     */
    private List<AccountConfigDTO> customizeAccConfig(List<Object[]> data) {
        List<AccountConfigDTO> finalResult = new ArrayList<>();
        HelperDTO companyDto;
        HelperDTO businessUnitDto;
        for (Object[] str : data) {
            AccountConfigDTO dto = new AccountConfigDTO();
            dto.setMasterId(str[0] == null ? 0 : Integer.valueOf(String.valueOf(str[0])));
            dto.setCheckRecord(str[NumericConstants.ONE] == null ? Boolean.FALSE : (Boolean) str[NumericConstants.ONE]);
            companyDto = new HelperDTO();
            companyDto.setId(str[NumericConstants.TWO] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.TWO])));
            companyDto.setDescription(str[NumericConstants.THREE] == null ? GlobalConstants.getSelectOne() : String.valueOf(str[NumericConstants.THREE]));
            dto.setCompanyNoHelperDto(companyDto);
            dto.setCompanySid(str[NumericConstants.TWO] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.TWO])));
            dto.setCompanyName(str[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            businessUnitDto = new HelperDTO();
            businessUnitDto.setId(str[NumericConstants.FOUR] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.FOUR])));
            businessUnitDto.setDescription(str[NumericConstants.FIVE] == null ? GlobalConstants.getSelectOne() : String.valueOf(str[NumericConstants.FIVE]));
            dto.setBusinessNoHelperDto(businessUnitDto);
            dto.setBuSid(str[NumericConstants.FOUR] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.FOUR])));
            dto.setBusinessUnitName(str[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
            dto.setAccount(str[NumericConstants.SIX] == null ? GlobalConstants.getSelectOne() : String.valueOf(str[NumericConstants.SIX]));
            dto.setBrandSid(str[NumericConstants.SEVEN] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.SEVEN])));
            dto.setBrandDdlb(str[NumericConstants.SEVEN] == null ? 0 : Integer.valueOf(String.valueOf(str[NumericConstants.SEVEN])));
            dto.setCostCentre(str[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
            dto.setCreatedBy(str[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTEEN]));
            dto.setCreatedDate(str[NumericConstants.TEN] == null ? null : dbDate.format((Date) str[NumericConstants.TEN]));
            dto.setModifiedDate(str[NumericConstants.ELEVEN] == null ? null : dbDate.format((Date) str[NumericConstants.ELEVEN]));
            dto.setSource(str[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWELVE]));
            dto.setCompanyNo(str[NumericConstants.FOURTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOURTEEN]));
            dto.setBusinessUnitNo(str[NumericConstants.FIFTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIFTEEN]));
            dto.setBrand(str[NumericConstants.SIXTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIXTEEN]));
            dto.setCompanyIdWithName(str[NumericConstants.SEVENTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVENTEEN]));
            dto.setBuIdWithName(str[NumericConstants.EIGHTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHTEEN]));
            dto.setBrandWithIdName(str[NumericConstants.NINETEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.NINETEEN]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    /**
     * Logical part to update the component value to the Data base whenever it
     * is being changed from FieldfactoryX
     *
     * @param value
     * @param columnName
     * @param dto
     * @param selection
     */
    public void updateTableValues(Object value, String columnName, AccountConfigDTO dto, AccountConfigSelection selection) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getTempTableName());
        inputList.add(columnName);
        inputList.add(value);
        inputList.add(dto.getMasterId());
        QueryUtils.itemUpdate(inputList, "updateFieldFactoryValues");
    }

    /**
     * loadCompanyOrBusinessUnitDdlb
     *
     * @param comboBox
     * @param resultsList
     * @param queryName
     */
    public void loadCompanyOrBusinessUnitDdlb(ComboBox comboBox, List<Object[]> resultsList, String queryName, Boolean isNoAndNameRequired, Boolean isFilter) {
        if (!isFilter) {
            comboBox.addItem(selectOneDto);
            comboBox.setItemCaption(selectOneDto, GlobalConstants.getSelectOne());
        } else {
            comboBox.addItem(showAllDto);
            comboBox.setItemCaption(showAllDto, ConstantsUtils.SHOW_ALL);
        }
        if (resultsList.isEmpty()) {
            resultsList.clear();
            resultsList.addAll(QueryUtils.getItemData(new ArrayList(), queryName, null));
        }
        for (Object[] obj : resultsList) {
            HelperDTO helperDto = new HelperDTO();
            if (obj[NumericConstants.ONE] != null && obj[NumericConstants.TWO] != null) {
                helperDto.setId((int) obj[NumericConstants.ZERO]);
                helperDto.setDescription((String) obj[NumericConstants.TWO]);
                comboBox.addItem(helperDto);
                comboBox.setItemCaption(helperDto, (obj[NumericConstants.ONE]) + (isNoAndNameRequired ? (" - " + obj[NumericConstants.TWO]) : ""));
            }
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(isFilter ? showAllDto : selectOneDto);
    }

    /**
     * loadCompanyOrBusinessUnitDdlb
     *
     * @param comboBox
     * @param resultsList
     * @param queryName
     */
    public void loadBrandDdlb(ComboBox comboBox, List<Object[]> resultsList, Boolean isIdAndNameRequired, Boolean isFiler) {

        comboBox.addItem(NumericConstants.ZERO);
        comboBox.setItemCaption(NumericConstants.ZERO, isFiler ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
        List<Object[]> resultsListValue;
        if (resultsList.isEmpty()) {
            resultsListValue = QueryUtils.getItemData(new ArrayList(), "loadBrand", null);
        } else {
            resultsListValue = resultsList;
        }
        for (Object[] obj : resultsListValue) {
            if (obj[NumericConstants.TWO] != null) {
                comboBox.addItem((int) obj[NumericConstants.ZERO]);
                comboBox.setItemCaption((int) obj[NumericConstants.ZERO], (isIdAndNameRequired ? (obj[NumericConstants.ONE] + " - ") : "") + obj[NumericConstants.TWO] + "");
            }
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(NumericConstants.ZERO);
    }

    /**
     * Logical part to update the component value to the Data base whenever it
     * is being changed from Mass update
     *
     * @param value
     * @param selection
     * @param columnName
     */
    public void massUpdateValue(Object value, AccountConfigSelection selection, String columnName) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getTempTableName());
        inputList.add(columnName);
        inputList.add(value);
        QueryUtils.itemUpdate(inputList, "MassUpdateFieldFactoryValues");
    }

    /**
     * Method logic to check atleast one item is checked from the UI table
     *
     * @param tempTableName
     * @return boolean true/false
     */
    public boolean isCheckedAtleastOneItem(String tempTableName) {
        String[] tempTable = new String[]{tempTableName};
        List<Object[]> checkedCountList = QueryUtils.getItemData(Arrays.asList(tempTable), "isCheckedAtleastOneItem", null);
        return CommonLogic.getCount(checkedCountList) != 0;
    }

    /**
     * Logic to remove the checkedline from DB while hit Remove Line command
     * button
     *
     * @param selection
     */
    public void removeLineLogic(AccountConfigSelection selection) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getTempTableName());
        QueryUtils.itemUpdate(inputList, "removeLine");
    }

    /**
     * Loading the account DDLB based on the company and business unit.
     *
     * @param comboBox
     * @param company
     * @param businessUnit
     */
    public void loadAccount(ComboBox comboBox, int company, int businessUnit) {
        comboBox.removeAllItems();
        comboBox.setImmediate(true);
        comboBox.addItem(GlobalConstants.getSelectOne());
        comboBox.setItemCaption(GlobalConstants.getSelectOne(), GlobalConstants.getSelectOne());
        if (company != NumericConstants.ZERO && businessUnit != NumericConstants.ZERO) {
            List inputList = new ArrayList<>();
            inputList.add(company);
            inputList.add(businessUnit);
            List<Object[]> resultsAccountFactoryDdlbList = loadAccountDdlb(inputList);
            for (Object[] obj : resultsAccountFactoryDdlbList) {
                if (obj[NumericConstants.ONE] != null && obj[NumericConstants.TWO] != null) {
                    comboBox.addItem((String) obj[NumericConstants.ONE]);
                    comboBox.setItemCaption((String) obj[NumericConstants.ONE], obj[NumericConstants.ONE] + "");
                }
            }
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(GlobalConstants.getSelectOne());
        comboBox.select(GlobalConstants.getSelectOne());
    }

    /**
     * Logical method to copy the selected record from UI and insert into DB.
     *
     * @param selection
     */
    public void copyLineInDB(AccountConfigSelection selection) {
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        input.add(selection.getSession().getUserId());
        input.add(selection.getSession().getUserId());
        input.add(selection.getTempTableName());
        QueryUtils.itemUpdate(input, "copyLine-AccountConfig");
    }

    /**
     * Deleting the data from temp table for reset.
     *
     * @param selection
     */
    public void resetLineLogic(String tableName) {
        List input = new ArrayList();
        input.add(tableName);
        input.add(tableName);
        QueryUtils.itemUpdate(input, "resetLine");
    }

    private Map<String, String> loadFilterQueryMap() {
        Map<String, String> filterQueryMap = new HashMap<>();
        filterQueryMap.put(VariableConstants.COMPANY_NO_HELPER_DTO, "COMPANY_ID_WITH_NAME");
        filterQueryMap.put(CommonConstant.COMPANY_NO, VariableConstants.COMP_COMPANY_MASTER_SID);
        filterQueryMap.put(VariableConstants.BUSINESS_NO_HELPER_DTO, "BU_ID_WITH_NAME");
        filterQueryMap.put(CommonConstant.BUSINESS_UNIT_NO, VariableConstants.BU_COMPANY_MASTER_SID);
        filterQueryMap.put(CommonConstant.ACCOUNT, VariableConstants.ACCOUNT);
        filterQueryMap.put(VariableConstants.BRAND_DDLB, "BRAND_WITH_ID_NAME");
        filterQueryMap.put(CommonConstant.BRAND, "TEMP.BRAND_MASTER_SID");
        filterQueryMap.put(CommonConstant.COST_CENTRE, VariableConstants.COST_CENTER);
        filterQueryMap.put(VariableConstants.CHECK_RECORD, VariableConstants.CHECK_RECORD1);
        filterQueryMap.put(CommonConstant.COMPANY_NAME, "GL_COM_NAME");
        filterQueryMap.put(CommonConstant.BUSINESS_UNIT_NAME, "BU_COMP_NAME");
        filterQueryMap.put(CommonConstant.CREATED_BY, "created");
        filterQueryMap.put(CommonConstant.MODIFIED_DATE, "convert(varchar(10),MODIFIED_DATE, 101) + ' ' + right(convert(varchar(32),MODIFIED_DATE,108),8)");
        filterQueryMap.put(CommonConstant.CREATED_DATE, "convert(varchar(10),CREATED_DATE, 101) + ' ' + right(convert(varchar(32),CREATED_DATE,108),8)");
        filterQueryMap.put(CommonConstant.SOURCE, "SOURCE");
        filterQueryMap.put(VariableConstants.COMPANY_ID_WITH_NAME, "COMPANY_ID_WITH_NAME");
        filterQueryMap.put(VariableConstants.BU_ID_WITH_NAME, "BU_ID_WITH_NAME");
        filterQueryMap.put(VariableConstants.BRAND_WITH_ID_NAME, "BRAND_WITH_ID_NAME");

        return filterQueryMap;
    }

    private Map<String, String> loadSortedQueryMap() {
        Map<String, String> orderByQueryMap = new HashMap<>();
        orderByQueryMap.put(VariableConstants.COMPANY_NO_HELPER_DTO, CommonConstant.COMP_COMPANY_NO);
        orderByQueryMap.put(CommonConstant.COMPANY_NO, CommonConstant.COMP_COMPANY_NO);
        orderByQueryMap.put(VariableConstants.COMPANY_ID_WITH_NAME, CommonConstant.COMP_COMPANY_NO);
        orderByQueryMap.put(VariableConstants.BUSINESS_NO_HELPER_DTO, CommonConstant.BU_COMPANY_NO);
        orderByQueryMap.put(CommonConstant.BUSINESS_UNIT_NO, CommonConstant.BU_COMPANY_NO);
        orderByQueryMap.put(VariableConstants.BU_ID_WITH_NAME, CommonConstant.BU_COMPANY_NO);
        orderByQueryMap.put(CommonConstant.ACCOUNT, VariableConstants.ACCOUNT);
        orderByQueryMap.put(VariableConstants.BRAND_DDLB, CommonConstant.BM_BRAND_NAME);
        orderByQueryMap.put(CommonConstant.BRAND, CommonConstant.BM_BRAND_NAME);
        orderByQueryMap.put(VariableConstants.BRAND_WITH_ID_NAME, CommonConstant.BM_BRAND_NAME);
        orderByQueryMap.put(CommonConstant.COST_CENTRE, VariableConstants.COST_CENTER);
        orderByQueryMap.put(VariableConstants.CHECK_RECORD, VariableConstants.CHECK_RECORD1);
        orderByQueryMap.put(CommonConstant.COMPANY_NAME, "COMP.COMPANY_NAME");
        orderByQueryMap.put(CommonConstant.BUSINESS_UNIT_NAME, "bU.COMPANY_NAME");
        orderByQueryMap.put(CommonConstant.CREATED_BY, VariableConstants.CRFIRST_NAME_CRLAST_NAME);
        orderByQueryMap.put(CommonConstant.MODIFIED_DATE, "TEMP.MODIFIED_DATE");
        orderByQueryMap.put(CommonConstant.CREATED_DATE, "TEMP.CREATED_DATE");
        orderByQueryMap.put(CommonConstant.SOURCE, "TEMP.SOURCE");
        return orderByQueryMap;

    }

    /**
     * Logic to Copy the selected line from main table and put into temptable
     * button
     *
     * @param selection
     */
    public void insertToMainTable(AccountConfigSelection selection, String queryName, List inputList) {
        AccountConfigDTO dto = selection.getSearchAccConfigDTO();
        inputList.add(dto.getCompanySid());
        inputList.add(dto.getBuSid());
        inputList.add(dto.getAccount());
        inputList.add(dto.getBrandSid());
        inputList.add(dto.getCostCentre());
        QueryUtils.itemUpdate(inputList, queryName);
    }

    /**
     * save or update the Configuration is been added in the list view
     *
     * @param tempTableName
     */
    public void saveTempToMainTable(String tempTableName) {
        List<String> input = new ArrayList<>();
        input.add(tempTableName);
        input.add(tempTableName);
        input.add(tempTableName);
        input.add(tempTableName);
        QueryUtils.itemUpdate(input, "Temp to Main Save- AccountConfig");
    }

    public boolean mandatoryAndDuplicateCheckForSave(List<String> input, String queryName) {
        int count = CommonLogic.getCount(QueryUtils.getItemData(input, queryName, null));
        return count == 0;
    }

    /**
     * Update All the records as per the selection
     *
     */
    public void checkAllUpdate(String tableName, boolean isChecked) {
        List inputList = new ArrayList<>();
        inputList.add(tableName);
        inputList.add(isChecked);
        QueryUtils.itemUpdate(inputList, "checkAllUpdate");
    }

    /**
     * This is logical method to delete the existing account configuration in
     * main table
     *
     * @param masterId
     * @return boolean
     */
    public Boolean deleteAccountConfigCombination(int masterId) {
        List inputList = new ArrayList<>();
        inputList.add(masterId);
        return QueryUtils.itemUpdate(inputList, "deleteAccountConfig");
    }

    private Map<String, String> getFilterMapForCount() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put(CommonConstant.COMPANY_NO, "GL_COMP_NO");
        filterMap.put(CommonConstant.COMPANY_NAME, "GL_COMP_NAME");
        filterMap.put(CommonConstant.BUSINESS_UNIT_NO, "BU_COMP_NO");
        filterMap.put(CommonConstant.BUSINESS_UNIT_NAME, "BU_COMP_NAME");
        filterMap.put(CommonConstant.ACCOUNT, VariableConstants.ACCOUNT);
        filterMap.put(CommonConstant.COST_CENTRE, VariableConstants.COST_CENTER);
        filterMap.put(CommonConstant.BRAND, "BRAND_NAME");
        filterMap.put(CommonConstant.CREATED_BY, "created");
        filterMap.put(CommonConstant.CREATED_DATE, "convert(varchar(10),CREATED_DATE, 101) + ' ' + right(convert(varchar(32),CREATED_DATE,108),8)");
        filterMap.put(CommonConstant.MODIFIED_DATE, "convert(varchar(10),MODIFIED_DATE, 101) + ' ' + right(convert(varchar(32),MODIFIED_DATE,108),8)");
        filterMap.put(CommonConstant.SOURCE, "SOURC");
        return filterMap;
    }

    private Map<String, String> loadFilterQueryMapForLoad() {
        Map<String, String> filterQueryMap = new HashMap<>();
        filterQueryMap.put(VariableConstants.COMPANY_NO_HELPER_DTO, "(comp.COMPANY_ID + ' - ' + comp.COMPANY_NAME)");
        filterQueryMap.put(CommonConstant.COMPANY_NO, VariableConstants.COMP_COMPANY_MASTER_SID);
        filterQueryMap.put(VariableConstants.BUSINESS_NO_HELPER_DTO, "(bu.COMPANY_ID + ' - ' + bu.COMPANY_NAME)");
        filterQueryMap.put(CommonConstant.BUSINESS_UNIT_NO, VariableConstants.BU_COMPANY_MASTER_SID);
        filterQueryMap.put(CommonConstant.ACCOUNT, VariableConstants.ACCOUNT);
        filterQueryMap.put(VariableConstants.BRAND_DDLB, "(bm.BRAND_ID + ' - ' + bm.BRAND_NAME)");
        filterQueryMap.put(CommonConstant.BRAND, "TEMP.BRAND_MASTER_SID");
        filterQueryMap.put(CommonConstant.COST_CENTRE, VariableConstants.COST_CENTER);
        filterQueryMap.put(VariableConstants.CHECK_RECORD, VariableConstants.CHECK_RECORD1);
        filterQueryMap.put(CommonConstant.COMPANY_NAME, "COMP.COMPANY_NAME");
        filterQueryMap.put(CommonConstant.BUSINESS_UNIT_NAME, "bU.COMPANY_NAME");
        filterQueryMap.put(CommonConstant.CREATED_BY, VariableConstants.CRFIRST_NAME_CRLAST_NAME);
        filterQueryMap.put(CommonConstant.MODIFIED_DATE, "convert(varchar(10),temp.MODIFIED_DATE, 101) + ' ' + right(convert(varchar(32),temp.MODIFIED_DATE,108),8)");
        filterQueryMap.put(CommonConstant.CREATED_DATE, "convert(varchar(10),temp.CREATED_DATE, 101) + ' ' + right(convert(varchar(32),temp.CREATED_DATE,108),8)");
        filterQueryMap.put(CommonConstant.SOURCE, "TEMP.SOURCE");
        filterQueryMap.put(VariableConstants.COMPANY_ID_WITH_NAME, "(comp.COMPANY_ID + ' - ' + comp.COMPANY_NAME)");
        filterQueryMap.put(VariableConstants.BU_ID_WITH_NAME, "(bu.COMPANY_ID + ' - ' + bu.COMPANY_NAME)");
        filterQueryMap.put(VariableConstants.BRAND_WITH_ID_NAME, "(bm.BRAND_ID + ' - ' + bm.BRAND_NAME)");
        return filterQueryMap;
    }

    private List getInputs(AccountConfigDTO binderDto, List inputList) throws SQLException {
        if (binderDto.getCompanyDdlb() != 0) {
            inputList.add("AND ACC.GL_COMPANY_MASTER_SID =" + binderDto.getCompanyDdlb());
        } else {
            inputList.add(StringUtils.EMPTY);
        }
        if (binderDto.getBusinessDdlb() != 0) {
            inputList.add("AND ACC.BU_COMPANY_MASTER_SID =" + binderDto.getBusinessDdlb());
        } else {
            inputList.add(StringUtils.EMPTY);
        }
        if (binderDto.getBrandDdlb() != 0) {
            inputList.add("AND ACC.BRAND_MASTER_SID =" + binderDto.getBrandDdlb());
        } else {
            inputList.add(StringUtils.EMPTY);
        }
        try (Connection con = SysDataSourceConnection.getConnection()) {
            inputList.add(con.getCatalog());
        }
        if (binderDto.getAccountDdlb() != null) {
            inputList.add(binderDto.getAccountDdlb().replace("*", "%"));
        } else {
            inputList.add("%");
        }
        if (!binderDto.getCostCentre().isEmpty()) {
            inputList.add(binderDto.getCostCentre().replace("*", "%"));
        } else {
            inputList.add("%");
        }
        return inputList;
    }

}
