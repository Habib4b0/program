/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.logic;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import com.stpl.app.arm.common.CommonFilterLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import static com.stpl.app.utils.CommonUtils.userMap;
import com.stpl.app.utils.SysDataSourceConnection;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Srithar.Raju
 */
public class AccountConfigLogic {

    private static AccountConfigLogic accountConfigLogic;
    HelperDTO selectOneDto = new HelperDTO(NumericConstants.ZERO, GlobalConstants.getSelectOne());
    HelperDTO showAllDto = new HelperDTO(NumericConstants.ZERO, ConstantsUtils.SHOW_ALL);

    /**
     * The Constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(AccountConfigLogic.class);

    private AccountConfigLogic() {
    }

    public static AccountConfigLogic getInstance() {
        if (accountConfigLogic == null) {
            accountConfigLogic = new AccountConfigLogic();
        }
        return accountConfigLogic;
    }

    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<Object>();
        try {
            resultList = ImtdIfpDetailsLocalServiceUtil.fetchFieldsForSecurity(moduleName, tabName, null, null, null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    public static Map<Integer, String> getUserName() throws SystemException {
        LOGGER.info("Enters getUserName method");
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (User user : userList) {
            userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
        }
        LOGGER.info("End of getUserName method");
        return userMap;
    }

    /**
     * Method to get counts of account and config
     *
     * @param binderDto
     * @return int- count
     */
    public int getAccountConfigCount(final AccountConfigDTO binderDto) throws Exception {
        List searchInput = getSearchInputList(binderDto);
        return CommonLogic.getCount(QueryUtils.getItemData(searchInput, "searchAccountConfig-count", null));
    }

    public List getAccountConfigData(final AccountConfigDTO binderDto) throws Exception {
        List searchInput = getSearchInputList(binderDto);
        StringBuilder orderByQuery = CommonFilterLogic.getInstance().orderByQueryGenerator(binderDto.getSortByColumns(), getSortingMap(), "ACC.GL_COMPANY_MASTER_SID");
        searchInput.add(orderByQuery);
        searchInput.add(binderDto.getStart());
        searchInput.add(binderDto.getOffset());

        List<Object[]> queryResultList = QueryUtils.getItemData(searchInput, "searchAccountConfig-Data", null);
        List<AccountConfigDTO> resultList = getCustomizedAccountConfigResultList(queryResultList);
        return resultList;
    }

    private List getSearchInputList(final AccountConfigDTO binderDto) throws SQLException {
        List inputList = new ArrayList<>();
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

        StringBuilder sql = CommonFilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(), getFilterMap());
        if (sql != null) {
            inputList.add(sql.toString().replace("where", " AND "));
        } else {
            inputList.add(StringUtils.EMPTY);
        }
        return inputList;
    }

    private List<AccountConfigDTO> getCustomizedAccountConfigResultList(List<Object[]> resultList) throws Exception {
        List<AccountConfigDTO> fullDataList = new ArrayList<>();
        if (resultList != null & !resultList.isEmpty()) {
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
                dto.setCreatedDate(str[NumericConstants.EIGHT] == null ? null : (Date) str[NumericConstants.EIGHT]);
                dto.setModifiedDate(str[NumericConstants.NINE] == null ? null : (Date) str[NumericConstants.NINE]);
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
        return fullDataList;
    }

    private Map<String, String> getFilterMap() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("companyNo", "CM.COMPANY_MASTER_SID");
        filterMap.put("companyName", "cm.COMPANY_NAME");
        filterMap.put("businessUnitNo", "BU.COMPANY_MASTER_SID");
        filterMap.put("businessUnitName", "BU.COMPANY_NAME");
        filterMap.put("account", "ACC.ACCOUNT");
        filterMap.put("costCentre", "ACC.COST_CENTER");
        filterMap.put("brand", "ACC.BRAND_MASTER_SID");
        filterMap.put("createdBy", "ACC.CREATED_BY");
        filterMap.put("createdDate", "ACC.CREATED_DATE");
        filterMap.put("modifiedDate", "ACC.MODIFIED_DATE");
        filterMap.put("source", "ACC.\"SOURCE\"");
//        filterMap.put("SID", "ACC.GL_COMPANY_MASTER_SID");
        return filterMap;
    }

    private Map<String, String> getSortingMap() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("companyNo", "CM.COMPANY_NO");
        filterMap.put("companyName", "cm.COMPANY_NAME");
        filterMap.put("businessUnitNo", "BU.COMPANY_NO");
        filterMap.put("businessUnitName", "BU.COMPANY_NAME");
        filterMap.put("account", "ACC.ACCOUNT");
        filterMap.put("costCentre", "ACC.COST_CENTER");
        filterMap.put("brand", "bm.BRAND_NAME");
        filterMap.put("createdBy", "(cr.firstName + ' ' + cr.lastName)");
        filterMap.put("createdDate", "ACC.CREATED_DATE");
        filterMap.put("modifiedDate", "ACC.MODIFIED_DATE");
        filterMap.put("source", "ACC.\"SOURCE\"");
//        filterMap.put("SID", "ACC.GL_COMPANY_MASTER_SID");
        return filterMap;
    }

    public List<Object[]> loadAccountDdlb(List inputList) {
        List<Object[]> queryResultList = QueryUtils.getItemData(inputList, "loadAccountDdlb", null);
        return queryResultList;
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

    public int getAccountConfigCount(AccountConfigSelection selection, Set<Container.Filter> filters) throws Exception {
        List<Object> input = new ArrayList();
        List<Object> inputtemp = new ArrayList();
        List<Object[]> data = new ArrayList<>();
        StringBuilder account=new StringBuilder();
        StringBuilder brand=new StringBuilder();
        try (Connection con = SysDataSourceConnection.getConnection()) {
            input.add(selection.getTempTableName());
            input.add(con.getCatalog());
            StringBuilder filterQuery = CommonFilterLogic.getInstance().filterQueryGenerator(filters, loadFilterQueryMap());
            input.add(filterQuery != null ? filterQuery.toString().replace("where", " AND ") : StringUtils.EMPTY);
            if (selection.isCurrentView()) {
                data = QueryUtils.getItemData(input, "AccoutConfig-Count", null);
            } else {
                  if (!selection.getSession().getMode().equals("ADD") && !selection.getSession().getMode().equalsIgnoreCase("Copy")) {
                    input.remove(selection.getTempTableName());
                    input.add(0, "HIST_ARM_ACC_CONFIG");
                    inputtemp.add(selection.getTempTableName());
                     
                    input.add(selection.getSearchAccConfigDTO().getCompanySid());
                    input.add(selection.getSearchAccConfigDTO().getBuSid());
                      if (selection.getSession().getMode().equalsIgnoreCase("View")) {
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
     * @throws java.lang.Exception
     */
    public List getAccountConfigData(AccountConfigSelection selection, int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws Exception {
        List<Object> input = new ArrayList();
        List<Object> inputtemp = new ArrayList();
        List<Object[]> data = new ArrayList<>();
        StringBuilder account=new StringBuilder();
        StringBuilder brand=new StringBuilder();
        try (Connection con = SysDataSourceConnection.getConnection()) {
            input.add(selection.getTempTableName());
            input.add(con.getCatalog());
            StringBuilder filterQuery = CommonFilterLogic.getInstance().filterQueryGenerator(filters, loadFilterQueryMap());
            
            if (selection.isCurrentView()) {
            input.add(filterQuery != null ? filterQuery.toString().replace("where", " AND ") : StringUtils.EMPTY);
            StringBuilder sortByQuery = CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, loadSortedQueryMap(), selection.isCurrentView() ? "ARM_ACC_CONFIG_SID" : "GL_COMPANY_MASTER_SID");
            input.add(sortByQuery);
            input.add(start);
            input.add(offset);
                data = QueryUtils.getItemData(input, "AccoutConfig-LoadData", null);
            } else {
                if (!selection.getSession().getMode().equals("ADD") && !selection.getSession().getMode().equalsIgnoreCase("Copy")) {
                    input.remove(selection.getTempTableName());
                    input.add(0, "HIST_ARM_ACC_CONFIG");
                    inputtemp.add(selection.getTempTableName());
                    input.add(selection.getSearchAccConfigDTO().getCompanySid());
                    input.add(selection.getSearchAccConfigDTO().getBuSid());
                    if (selection.getSession().getMode().equalsIgnoreCase("View")) {
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
                    input.add(filterQuery != null ? filterQuery.toString().replace("where", " AND ") : StringUtils.EMPTY);
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
    private List<AccountConfigDTO> customizeAccConfig(List<Object[]> data) throws Exception {
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
            dto.setCreatedDate(str[NumericConstants.TEN] == null ? null : (Date) str[NumericConstants.TEN]);
            dto.setModifiedDate(str[NumericConstants.ELEVEN] == null ? null : (Date) str[NumericConstants.ELEVEN]);
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
        if (resultsList.isEmpty()) {
            resultsList = QueryUtils.getItemData(new ArrayList(), "loadBrand", null);
        }
        for (Object[] obj : resultsList) {
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
        List<Object[]> checkedCountList = QueryUtils.getItemData(Arrays.asList(new String[]{tempTableName}), "isCheckedAtleastOneItem", null);
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
        filterQueryMap.put("companyNoHelperDto", "comp.COMPANY_MASTER_SID");
        filterQueryMap.put("companyNo", "comp.COMPANY_MASTER_SID");
        filterQueryMap.put("businessNoHelperDto", "bu.COMPANY_MASTER_SID");
        filterQueryMap.put("businessUnitNo", "bu.COMPANY_MASTER_SID");
        filterQueryMap.put("account", "ACCOUNT");
        filterQueryMap.put("brandDdlb", "TEMP.BRAND_MASTER_SID");
        filterQueryMap.put("brand", "TEMP.BRAND_MASTER_SID");
        filterQueryMap.put("costCentre", "COST_CENTER");
        filterQueryMap.put("checkRecord", "CHECK_RECORD");
        filterQueryMap.put("companyName", "COMP.COMPANY_NAME");
        filterQueryMap.put("businessUnitName", "bU.COMPANY_NAME");
        filterQueryMap.put("createdBy", "TEMP.CREATED_BY");
        filterQueryMap.put("modifiedDate", "TEMP.MODIFIED_DATE");
        filterQueryMap.put("createdDate", "TEMP.CREATED_DATE");
        filterQueryMap.put("source", "TEMP.SOURCE");
        return filterQueryMap;
    }

     private Map<String, String> loadSortedQueryMap() {
        Map<String, String> orderByQueryMap = new HashMap<>();
        orderByQueryMap.put("companyNoHelperDto", "comp.COMPANY_NO");
        orderByQueryMap.put("companyNo", "comp.COMPANY_NO");
        orderByQueryMap.put("companyIdWithName", "comp.COMPANY_NO");
        orderByQueryMap.put("businessNoHelperDto", "bu.COMPANY_NO");
        orderByQueryMap.put("businessUnitNo", "bu.COMPANY_NO");
        orderByQueryMap.put("buIdWithName", "bu.COMPANY_NO");
        orderByQueryMap.put("account", "ACCOUNT");
        orderByQueryMap.put("brandDdlb", "bm.BRAND_NAME");
        orderByQueryMap.put("brand", "bm.BRAND_NAME");
        orderByQueryMap.put("brandWithIdName", "bm.BRAND_NAME");
        orderByQueryMap.put("costCentre", "COST_CENTER");
        orderByQueryMap.put("checkRecord", "CHECK_RECORD");
        orderByQueryMap.put("companyName", "COMP.COMPANY_NAME");
        orderByQueryMap.put("businessUnitName", "bU.COMPANY_NAME");
        orderByQueryMap.put("createdBy", "(cr.firstName + ' ' + cr.lastName)");
        orderByQueryMap.put("modifiedDate", "TEMP.MODIFIED_DATE");
        orderByQueryMap.put("createdDate", "TEMP.CREATED_DATE");
        orderByQueryMap.put("source", "TEMP.SOURCE");
        return orderByQueryMap;

    }

    /**
     * Logic to Copy the selected line from main table and put into temptable
     * button
     *
     * @param selection
     */
    public void insertToMainTable(AccountConfigSelection selection, String queryName) {
        List inputList = new ArrayList<>();
        AccountConfigDTO dto = selection.getSearchAccConfigDTO();
        inputList.add(selection.getTempTableName());
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
        Boolean isDeleted = QueryUtils.itemUpdate(inputList, "deleteAccountConfig");
        return isDeleted;
    }

}
