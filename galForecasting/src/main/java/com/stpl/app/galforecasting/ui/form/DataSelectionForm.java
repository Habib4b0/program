package com.stpl.app.galforecasting.ui.form;

import com.stpl.app.galforecasting.accrualrateprojection.dto.AccrualDataSelectionDTO;
import com.stpl.app.galforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.galforecasting.accrualrateprojection.ui.view.AccrualRateProjectionView;
import com.stpl.app.galforecasting.bpm.logic.DSCalculationLogic;
import com.stpl.app.galforecasting.bpm.persistance.WorkflowPersistance;
import com.stpl.app.galforecasting.dto.CompanyDdlbDto;
import com.stpl.app.galforecasting.dto.RelationshipDdlbDto;
import com.stpl.app.galforecasting.logic.DataSelectionLogic;
import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.logic.tablelogic.DataSelectionSearchLogic;
import com.stpl.app.galforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.sessionutils.SessionUtil;
import com.stpl.app.galforecasting.ui.ForecastEditWindow;
import com.stpl.app.galforecasting.ui.form.lookups.CustomerProductGroupLookup;
import com.stpl.app.galforecasting.ui.form.lookups.HierarchyLookup;
import com.stpl.app.galforecasting.ui.form.lookups.PrivatePublicView;
import com.stpl.app.galforecasting.ui.form.lookups.SaveViewPopup;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtil;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import static com.stpl.app.galforecasting.utils.Constant.NULL;
import static com.stpl.app.galforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.galforecasting.utils.DataSelectionUtil;
import com.stpl.app.galforecasting.utils.HelperListUtil;
import com.stpl.app.galforecasting.utils.NotificationUtils;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_HIERARCHY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LEVEL_CUSTOMER;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LEVEL_NDC;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PRIVATE_VIEW;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_HIERARCHY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PUBLIC_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_ADD;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_SEARCH;
import static com.stpl.app.utils.Constants.LabelConstants.PRIVATE_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.PUBLIC_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.SAVE_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_CUSTOMER_GROUP_LOOKUP;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_CUSTOMER_HIERARCHY_LOOKUP;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_PRODUCT_GROUP_LOOKUP;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_PRODUCT_HIERARCHY_LOOKUP;
import com.stpl.app.utils.DateToStringConverter;
import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.forecastds.form.ForecastDataSelection;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.UIUtil;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.stpl.ifs.util.HelperDTO;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 *
 * @author sooriya.lakshmanan
 */
public class DataSelectionForm extends ForecastDataSelection {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -1811099338760834050L;

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelectionForm.class);

    /**
     * The Constant NAME.
     */
    protected static final String NAME = "DATA";

    /**
     * The data selection binder.
     */
    public CustomFieldGroup dataSelectionBinder;
    String screenName;

    Map<String, String> customerDescriptionMap = null;
    Map<String, String> productDescriptionMap = null;
    private boolean dismantleCustomerSelection = true;
    private boolean dismantleProductSelection = true;
    private LazyContainer discountDdlbLazyContainer;
    CompanyDdlbDto discountDdlbDefault = new CompanyDdlbDto(0, SELECT_ONE, true);
    CompanyDdlbDto discountDTO = null;
    public static Map<String, String> relationLevelValues = new HashMap<String, String>();
    DataSelectionLogic dataLogic = new DataSelectionLogic();
    DataSelectionSearchLogic tableLogic = new DataSelectionSearchLogic();
    protected ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    List<Integer> customerBeanList = new ArrayList<Integer>();
    List<Integer> productBeanList = new ArrayList<Integer>();
    String publicViewName;
    String privateViewName;
    CommonUtil commonUtils = CommonUtil.getInstance();
    DataSelectionForm dataSelectionForm = this;
    final DataSelectionLogic dsLogic = new DataSelectionLogic();
    final NonMandatedLogic nmLogic = new NonMandatedLogic();
    public static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");
    boolean editnotif = false;

    public String getPublicViewName() {
        return publicViewName;
    }

    public void setPublicViewName(String publicViewName) {
        this.publicViewName = publicViewName;
    }

    public String getPrivateViewName() {
        return privateViewName;
    }

    public void setPrivateViewName(String privateViewName) {
        this.privateViewName = privateViewName;
    }
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    
    ResourceBundle tableName = ResourceBundle.getBundle("properties.Constants");

    /**
     * Instantiates a new data selection index.
     *
     * @param dataSelectionBinder
     * @param dataSelectionDTO
     * @throws java.lang.Exception
     */
    public DataSelectionForm(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, String screenName) throws Exception {
        super(dataSelectionBinder, screenName, true);
        LOGGER.info("DataSelectionIndex Initializing... ");
        this.dataSelectionBinder = dataSelectionBinder;
        this.dataSelectionDTO = dataSelectionDTO;
        this.screenName = screenName;
        configureFields();

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            Map<String, AppPermission> functionCompanyHM = null;
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, getGovernmentConstant() + "," + UISecurityUtil.DATA_SELECTION_INDEX);
            } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, getCommercialConstant() + "," + UISecurityUtil.DATA_SELECTION_INDEX);
            }
            getButtonPermission(functionCompanyHM);
        }
        LOGGER.info("DataSelectionIndex Ends");
    }

    private void getButtonPermission(Map<String, AppPermission> functionCompanyHM) {
        if (functionCompanyHM.get(CommonUtils.GENERATE_BTN) != null && !((AppPermission) functionCompanyHM.get(CommonUtils.GENERATE_BTN)).isFunctionFlag()) {
            buttonLay.removeComponent(getGenerateBtn());
        }
        if (functionCompanyHM.get(CommonUtils.SEARCH_BTN) != null && !((AppPermission) functionCompanyHM.get(CommonUtils.SEARCH_BTN)).isFunctionFlag()) {
            buttonLay.removeComponent(getSearchBtn());
        }
        if (functionCompanyHM.get(CommonUtils.RESET_BTN) != null && !((AppPermission) functionCompanyHM.get(CommonUtils.RESET_BTN)).isFunctionFlag()) {
            buttonLay.removeComponent(getResetBtn());
        }
        if (functionCompanyHM.get(CommonUtils.SAVE_VIEW_BTN) != null && !((AppPermission) functionCompanyHM.get(CommonUtils.SAVE_VIEW_BTN)).isFunctionFlag()) {
            buttonLay.removeComponent(getSaveViewBtn());
        }

        if (functionCompanyHM.get(CommonUtils.DELETE_VIEW) != null && !((AppPermission) functionCompanyHM.get(CommonUtils.DELETE_VIEW)).isFunctionFlag()) {

            buttonLay.removeComponent(getDeleteViewBtn());
        }

        if (functionCompanyHM.get(CommonUtils.RESULT_RESET_BTN) != null && !((AppPermission) functionCompanyHM.get(CommonUtils.RESULT_RESET_BTN)).isFunctionFlag()) {
            finalBtn.removeComponent(getResultResetBtn());
        }
        if (functionCompanyHM.get(CommonUtils.EDIT_BTN) != null && !((AppPermission) functionCompanyHM.get(CommonUtils.EDIT_BTN)).isFunctionFlag()) {
            finalBtn.removeComponent(getEditBtn());
        }
        if (functionCompanyHM.get(CommonUtils.VIEW_BTN) != null && !((AppPermission) functionCompanyHM.get(CommonUtils.VIEW_BTN)).isFunctionFlag()) {
            finalBtn.removeComponent(getViewBtn());
        }
        if (functionCompanyHM.get(CommonUtils.DELETE_BTN) != null && !((AppPermission) functionCompanyHM.get(CommonUtils.DELETE_BTN)).isFunctionFlag()) {
            finalBtn.removeComponent(getDeleteBtn());
        }
    }

    public static Map<String, String> getRelationLevelValues() {
        return relationLevelValues;
    }

    public static void setRelationLevelValues(Map<String, String> relationLevelValues) {
        DataSelectionForm.relationLevelValues = relationLevelValues;
    }

    /**
     * Configure fields.
     */
    private void configureFields() throws Exception {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
            helperTableListNames = DataSelectionUtil.loadHelperTable();
            DataSelectionUtil.mapUsers();
            new NonMandatedLogic().deleteUnsavedProjections(tableName.getString("COMMERCIAL_DETAILS_TABLE").split(","), tableName.getString("COMMERCIAL_PROJECTION_TABLE").split(","), CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(screenName)) {
            new NonMandatedLogic().deleteUnsavedProjections(tableName.getString("GOVERMENT_DETAILS_TABLES").split(","), tableName.getString("GOVERMENT_PROJECTION_TABLES").split(","), CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED);
        } else if(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)){
            new NonMandatedLogic().deleteUnsavedProjections(tableName.getString("RETURNS_DETAILS_TABLE").split(","), tableName.getString("RETURNS_PROJECTION_TABLE").split(","), CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS);
        } else if(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)){
            new NonMandatedLogic().deleteUnsavedProjections(tableName.getString("ACCURAL_DETAILS_TABLE").split(","), tableName.getString("ACCURAL_PROJECTION_TABLE").split(","), CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION);
        }
        resultsTableLayout.addComponent(resultTable);
        resultsTableLayout.addComponent(tableLogic.createControls());
        tableLogic.setContainerDataSource(resultsContainer);
        resultTable.setSelectable(true);
        tableLogic.setPageLength(10);

        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
            resultTable.setVisibleColumns(TableHeaderColumnsUtil.DATASELECTION_COLUMNS_ACCRUAL);
            resultTable.setColumnHeaders(TableHeaderColumnsUtil.DATASELECTION_HEADERS_ACCRUAL);
            productLevel.setVisible(false);
            customerLevel.setVisible(false);
            productForecastLevelLabel.setVisible(false);
            customerForecastLevelLabel.setVisible(false);
            modeOptionChange(true);
        } else {
            resultTable.setVisibleColumns(TableHeaderColumnsUtil.DATASELECTION_COLUMNS);
            resultTable.setColumnHeaders(TableHeaderColumnsUtil.DATASELECTION_HEADERS);
        }

        resultTable.setFilterBarVisible(true);
        resultTable.setSizeFull();
        resultTable.setImmediate(true);
        resultTable.setPageLength(10);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.addStyleName(Constant.FILTER_TABLE);
        resultTable.addStyleName("table-header-normal");
        com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils.getUserName();
        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
            loadDiscountLevel();
        }
    }

    private void loadCompanyLogic() {
        try {
            loadCompanyDdlb(DataSelectionUtil.getCompanySidFromHierarchy(getCompanySidFromHierarchy(false, false), screenName), null);
        } catch (Exception ex) { 
            LOGGER.error(ex + " - in loadCompanyLogic in loading ddlb");
        }
    }

    public void loadFilteredProductSelection(final String selectedLevel, final boolean companyDdlbFlag) {
        try {
            availableProductContainer.removeAllItems();
            int forecastLevel = 0;
            boolean isNdc = false;
            List<Leveldto> innerLevelValues = null;
            String levelName = "Level";
            if (selectedLevel != null && !Constants.CommonConstants.NULL.getConstant().equals(selectedLevel) && !SELECT_ONE.equals(selectedLevel)
                    && productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) {
                if (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) {

                    String relationshipSid = String.valueOf(productRelation.getValue());
                    DataSelectionLogic logic = new DataSelectionLogic();
                    String val[] = selectedLevel.split(" ");
                    forecastLevel = Integer.parseInt(val[1]);
                    String dedLevel = StringUtils.EMPTY;
                    String dedValue = StringUtils.EMPTY;
                    Leveldto tempDto = (Leveldto) innerProdLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        levelName = tempDto.getLevel();
                    }

                    if ((tempDto.getLevel() != null && (Constant.NDC.equalsIgnoreCase(tempDto.getLevel()) || "Item".equalsIgnoreCase(tempDto.getLevel()) || Constant.PRODUCT.equalsIgnoreCase(tempDto.getLevel()))) && Constant.ITEM_MASTER.equals(tempDto.getTableName())) {

                        isNdc = true;
                    } else {
                        isNdc = false;
                    }
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                        dedLevel = "Deduction Program Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "REBATE_PROGRAM_TYPE" : "Deduction Category".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "RS_CATEGORY" : "Deduction Schedule Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? Constant.RS_TYPE : StringUtils.EMPTY;
                        dedValue = String.valueOf(deductionValue.getValue());
                    }
                    ExtTreeContainer<Leveldto> dumbyProductContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);

                    innerLevelValues = logic.loadInnerLevel(tempDto.getLevel(), productHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(dumbyProductContainer.getItemIds()), isNdc,
                            tempDto.getFieldName(), relationshipSid, productDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName,
                            discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo(), dedValue, dedLevel);
                    List<Leveldto> groupFilteredValues = new ArrayList<Leveldto>();
                    List<Leveldto> companyFilteredValues = new ArrayList<Leveldto>();
                    List<Leveldto> brandFilteredValues = new ArrayList<Leveldto>();
                    List<Leveldto> tempLevelValues;
                    List<Leveldto> filteredValues = new ArrayList<Leveldto>(innerLevelValues);
                    productFilterContainer.removeAllItems();
                    productFilterContainer.addAll(innerLevelValues);

                    if (!tempDto.isFromItem() && (Constant.NDC.equalsIgnoreCase(tempDto.getLevel()) || "Items".equalsIgnoreCase(tempDto.getLevel()) || Constant.PRODUCT.equalsIgnoreCase(tempDto.getLevel()))) {
                        List<String> itemSidsFromCcCombo = null;
                        List<String> itemSidsFromBrandType = null;

                        List<String> filteredItemsFromBrandType = null;
                        if (selectedCustomerContainer != null && !selectedCustomerContainer.getItemIds().isEmpty()) {
                            Leveldto currentDto;
                            List<Leveldto> ccList = new ArrayList<Leveldto>();
                            for (Object item : selectedCustomerContainer.getItemIds()) {
                                currentDto = DataSelectionUtil.getBeanFromId(item);
                                if (currentDto != null && !StringUtils.isBlank(currentDto.getTableName())
                                        && (Constant.CONTRACT_MASTER.equalsIgnoreCase(currentDto.getTableName()))
                                        && (Constant.CONTRACT_SMALL.equalsIgnoreCase(currentDto.getLevel()))) {
                                    ccList.add(currentDto);
                                }
                                if (currentDto != null && !StringUtils.isBlank(currentDto.getTableName())
                                        && (Constant.COMPANY_MASTER.equalsIgnoreCase(currentDto.getTableName())
                                        && (Constant.TRADING_PARTNER.equalsIgnoreCase(currentDto.getLevel()) || Constant.COMPANY_SMALL.equalsIgnoreCase(currentDto.getLevel())))) {
                                    ccList.add(currentDto);
                                }
                            }
                            List<String> availableHierNo = null;
                            if (availableCustomerContainer != null && !availableCustomerContainer.getItemIds().isEmpty()) {
                                availableHierNo = DataSelectionUtil.getEndLevelHierNo(availableCustomerContainer.getItemIds());
                            }
                            logic.getEndLevelRelationshipLevels(DataSelectionUtil.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer)), relationshipSid, ccList, availableHierNo);
                            if (!ccList.isEmpty()) {
                                itemSidsFromCcCombo = logic.executeQuery(DataSelectionUtil.getCcpWithCC(ccList));
                            }
                            if (innerLevelValues != null) {
                                itemSidsFromBrandType = new ArrayList<String>();
                                for (Leveldto leveldto : innerLevelValues) {
                                    if (leveldto.isFromItem()) {
                                        itemSidsFromBrandType.add(leveldto.getRelationshipLevelValue());
                                    }
                                }
                            }
                            if (itemSidsFromCcCombo != null && itemSidsFromBrandType != null
                                    && !itemSidsFromBrandType.isEmpty()) {
                                List<String> tempStringList = new ArrayList<String>(itemSidsFromBrandType);
                                tempStringList.retainAll(itemSidsFromCcCombo);
                                filteredItemsFromBrandType = new ArrayList<String>(tempStringList);
                            }
                            if (filteredItemsFromBrandType != null) {
                                try {
                                    tempLevelValues = productFilterContainer.getItemIds();
                                    for (Leveldto leveldto : tempLevelValues) {
                                        if (leveldto.isFromItem() && !filteredItemsFromBrandType.isEmpty() && filteredItemsFromBrandType.contains(leveldto.getRelationshipLevelValue())) {
                                            brandFilteredValues.add(leveldto);
                                        }
                                    }
                                } catch (Exception ex) { 
                                    LOGGER.error(ex + " - in loadFilteredProductSelection");
                                }
                            }
                        }
                        filteredValues.clear();
                        filteredValues.addAll(brandFilteredValues);
                        productFilterContainer.removeAllItems();
                        productFilterContainer.addAll(filteredValues);
                    }
                    if (groupFilteredItems != null && !groupFilteredItems.isEmpty()) {

                        if (Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(tempDto.getTableName())) && ("Item".equals(tempDto.getLevel()) || Constant.NDC.equals(tempDto.getLevel())|| Constant.PRODUCT.equalsIgnoreCase(tempDto.getLevel()))) {
                            try {
                                tempLevelValues = productFilterContainer.getItemIds();
                                for (Leveldto leveldto : tempLevelValues) {
                                    if (groupFilteredItems.contains(leveldto.getRelationshipLevelValue().trim())) {
                                        groupFilteredValues.add(leveldto);
                                    }
                                }
                            } catch (Exception ex) { 
                                LOGGER.error(ex + " - in loadFilteredProductSelection filtering with product group");
                            }
                            filteredValues.clear();
                            filteredValues.addAll(groupFilteredValues);
                            productFilterContainer.removeAllItems();
                            productFilterContainer.addAll(filteredValues);
                        }
                    }
                    if (!companyDdlbFlag) {

                        if (company.getValue() != null && !SELECT_ONE.equals(company.getValue())
                                && Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(tempDto.getTableName())) && ("Item".equals(tempDto.getLevel()) || Constant.NDC.equals(tempDto.getLevel())|| Constant.PRODUCT.equalsIgnoreCase(tempDto.getLevel()))) {
                            List<String> filteredItemsFromCompanyDdlb = null;
                            List<Integer> itemsSidFromCompanyDdlb = logic.getItemIdFromCompanyForGlComp(Integer.parseInt(String.valueOf(company.getValue())));
                            if (itemsSidFromCompanyDdlb != null && !itemsSidFromCompanyDdlb.isEmpty()) {
                                List<String> tempStringList = new ArrayList<String>(DataSelectionUtil.getItemSidFromHierarchy(getItemSidFromHierarchy()));
                                tempStringList.retainAll(UiUtils.convertIngeterListToString(itemsSidFromCompanyDdlb));
                                filteredItemsFromCompanyDdlb = new ArrayList<String>(tempStringList);
                            } else {
                                filteredValues = new ArrayList<Leveldto>();
                            }
                            if (filteredItemsFromCompanyDdlb != null && !filteredItemsFromCompanyDdlb.isEmpty()) {
                                try {
                                    tempLevelValues = productFilterContainer.getItemIds();
                                    for (Leveldto leveldto : tempLevelValues) {
                                        if (filteredItemsFromCompanyDdlb.contains(leveldto.getRelationshipLevelValue().trim())) {
                                            companyFilteredValues.add(leveldto);
                                        }
                                    }
                                } catch (Exception ex) { 
                                    LOGGER.error(ex + " - in loadFilteredProductSelection filtering with company ddlb");
                                }
                            }
                            filteredValues.clear();
                            filteredValues.addAll(companyFilteredValues);
                            productFilterContainer.removeAllItems();
                            productFilterContainer.addAll(filteredValues);
                        }
                    }
                    availableProduct.removeAllItems();
                    availableProductContainer.removeAllItems();
                    availableProductContainer.addAll(productFilterContainer.getItemIds());
                }
            }
            availableProduct.setContainerDataSource(availableProductContainer);
            if (isNdc) {
                availableProduct.setVisibleColumns(new Object[]{"displayValue", "form", "strength"});
                availableProduct.setColumnHeaders(new String[]{Constant.NDC, "Form", "Strength"});
            } else {
                availableProduct.setVisibleColumns(new Object[]{"displayValue"});
                availableProduct.setColumnHeaders(new String[]{levelName});
            }
            availableProduct.setFilterBarVisible(true);
            availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
            availableProduct.setStyleName("filtertable");
        } catch (Exception ex) { 
            LOGGER.error(ex + " - in loadFilteredProductSelection");
        }
    }

    /**
     * Loads data selection after selecting a private/public view
     *
     * @param viewDTO DTO with view parameters
     */
    private void loadView(ViewDTO viewDTO) throws Exception {
        DSLogic dsLogic = new DSLogic();
        dismantleCustomerSelection = true;
        dismantleProductSelection = true;
        customerHierarchyDto = new HierarchyLookupDTO();
        productHierarchyDto = new HierarchyLookupDTO();
        viewId.setValue(viewDTO.getViewId());
        projectionId.setValue(viewDTO.getProjectionId());
        dataSelectionDTO.setProjectionId(UiUtils.parseStringToInteger(viewDTO.getProjectionId()));
        projectionName.setValue(viewDTO.getProjectionName());
        description.setValue(viewDTO.getDescription());
        customerHierarchyDto.setHierarchyId(viewDTO.getCustomerHierarchySid() != null && viewDTO.getCustomerHierarchySid().equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(viewDTO.getCustomerHierarchySid()));
        customerHierarchyDto.setHierarchyName(viewDTO.getCustomerHierarchy());
        productHierarchyDto.setHierarchyId(viewDTO.getProductHierarchySid() != null && viewDTO.getProductHierarchySid().equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(viewDTO.getProductHierarchySid()));
        productHierarchyDto.setHierarchyName(viewDTO.getProductHierarchy());
        customerHierarchy.setValue(viewDTO.getCustomerHierarchy());
        productHierarchy.setValue(viewDTO.getProductHierarchy());

        RelationshipDdlbDto selectedCustomerRelationshipDdlbDto = null;
        if (!StringUtils.isBlank(viewDTO.getCustRelationshipBuilderSid()) && !Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCustRelationshipBuilderSid())) {
            selectedCustomerRelationshipDdlbDto = new RelationshipDdlbDto();
            selectedCustomerRelationshipDdlbDto.setRelationshipBuilderSid(viewDTO.getCustRelationshipBuilderSid());
        }
        loadRelationDdlb(UiUtils.parseStringToInteger(viewDTO.getCustomerHierarchySid()), selectedCustomerRelationshipDdlbDto, customerRelation);
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid()) && !DASH.equals(viewDTO.getCompanyGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
            dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
        }
        customerGroup.setValue(viewDTO.getCustomerGroup());
        RelationshipDdlbDto selectedProductRelationshipDdlbDto = null;
        if (!StringUtils.isBlank(viewDTO.getProdRelationshipBuilderSid()) && !Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProdRelationshipBuilderSid())) {
            selectedProductRelationshipDdlbDto = new RelationshipDdlbDto();
            selectedProductRelationshipDdlbDto.setRelationshipBuilderSid(viewDTO.getProdRelationshipBuilderSid());
        }
        loadRelationDdlb(UiUtils.parseStringToInteger(viewDTO.getProductHierarchySid()), selectedProductRelationshipDdlbDto, productRelation);
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid()) && !DASH.equals(viewDTO.getProductGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
            dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
        }
        productGroup.setValue(viewDTO.getProductGroup());
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCustomerHierarchySid()) && !DASH.equals(viewDTO.getCustomerHierarchySid()) && !StringUtils.EMPTY.equals(viewDTO.getCustomerHierarchySid())) {

            loadCustomerLevel(viewDTO.getCustomerHierarchySid(), viewDTO.getCustomerLevel());
            relationLevelValues.putAll(dataLogic.getLevelValueMap(viewDTO.getCustRelationshipBuilderSid()));
            initializeCustomerHierarchy(UiUtils.parseStringToInteger(viewDTO.getProjectionId()), viewDTO.getCustomerLevel());
            loadInnerCustomerLevel(Integer.parseInt(viewDTO.getCustomerLevel()), UiUtils.parseStringToInteger(viewDTO.getCustomerInnerLevel()),
                    UiUtils.parseStringToInteger(viewDTO.getCustomerHierarchySid()));
            dataSelectionDTO.setCustomerHierSid(viewDTO.getCustomerHierarchySid());
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid()) && !DASH.equals(viewDTO.getCompanyGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
            dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
            triggerCustGrpOnView(viewDTO.getCompanyGroupSid(), Integer.parseInt(viewDTO.getCustomerHierarchySid()), true);
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductHierarchySid()) && !DASH.equals(viewDTO.getProductHierarchySid()) && !StringUtils.EMPTY.equals(viewDTO.getProductHierarchySid())) {
            loadProductLevel(viewDTO.getProductHierarchySid(), viewDTO.getProductLevel());
            relationLevelValues.putAll(dataLogic.getLevelValueMap(viewDTO.getProdRelationshipBuilderSid()));
            initializeProductHierarchy(UiUtils.parseStringToInteger(viewDTO.getProjectionId()), viewDTO.getProductLevel());
            loadInnerProductLevel(Integer.parseInt(viewDTO.getProductLevel()), UiUtils.parseStringToInteger(viewDTO.getProductInnerLevel()),
                    UiUtils.parseStringToInteger(viewDTO.getProductHierarchySid()));
            dataSelectionDTO.setProdHierSid(viewDTO.getProductHierarchySid());
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid()) && !DASH.equals(viewDTO.getProductGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
            dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
            triggerProdGrpOnView(viewDTO.getProductGroupSid(), viewDTO.getProductHierarchySid(), false);
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyMasterSid()) && !DASH.equals(viewDTO.getCompanyMasterSid()) && !StringUtils.EMPTY.equals(viewDTO.getCompanyMasterSid())) {
            dataSelectionDTO.setCompanySid(viewDTO.getCompanyMasterSid());
        }
        deleteViewBtn.setEnabled(true);
        initializeCompanyDdlb();
        DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), screenName);
        fromPeriod.select(viewDTO.getFromPeriod());
        toPeriod.select(viewDTO.getToPeriod());

        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid()) && !DASH.equals(viewDTO.getCompanyGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
            dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
            selectedCustomerGroupDTO.setCustomerGroupSid(String.valueOf(viewDTO.getCompanyGroupSid()));
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid()) && !DASH.equals(viewDTO.getProductGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
            dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
            selectedProductGroupDTO.setProductGroupSid(String.valueOf(viewDTO.getProductGroupSid()));
        }
        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
            deductionLevel.setValue(viewDTO.getDeductionLevel());
            deductionValue.setValue(helperListUtil.getHelperDTOByID(!"null".equals(viewDTO.getDeductionValueId()) && StringUtils.isNumeric(String.valueOf(viewDTO.getDeductionValueId())) ? Integer.valueOf(viewDTO.getDeductionValueId()):0));
        }

    }

    public DataSelectionDTO bindDataselectionDtoToSave() {
        try {
            SimpleDateFormat format = new SimpleDateFormat(Constants.CommonConstants.DATE_FORMAT.getConstant());
            if (company.getValue() != null && !SELECT_ONE.equals(company.getValue())) {

                dataSelectionDTO.setCompanySid(String.valueOf(company.getValue()));
            } else {
                dataSelectionDTO.setCompanySid(String.valueOf(0));
            }
            if (customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) {

                dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(customerRelation.getValue()));
            } else {
                dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(0));
            }
            if (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) {

                dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(productRelation.getValue()));
            } else {
                dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(0));
            }
            if (customerHierarchyDto != null && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(customerHierarchyDto.getHierarchyId()))
                    && !StringUtils.isEmpty(String.valueOf(customerHierarchyDto.getHierarchyId())) && !StringUtils.isBlank(String.valueOf(customerHierarchyDto.getHierarchyId()))) {
           if (!StringUtils.isBlank(customerHierarchy.getValue()) || Constant.NULL.equals(customerHierarchy.getValue()))
                dataSelectionDTO.setCustomerHierSid(String.valueOf(customerHierarchyDto.getHierarchyId()));
           else
                dataSelectionDTO.setCustomerHierSid(String.valueOf(0));
            } else {
                dataSelectionDTO.setCustomerHierSid(String.valueOf(0));
            }
            if (productHierarchyDto != null && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(productHierarchyDto.getHierarchyId()))
                    && !StringUtils.isEmpty(String.valueOf(productHierarchyDto.getHierarchyId())) && !StringUtils.isBlank(String.valueOf(productHierarchyDto.getHierarchyId()))) {
              if(!(StringUtils.isBlank(productHierarchy.getValue()) || Constant.NULL.equals(productHierarchy.getValue())))
                dataSelectionDTO.setProdHierSid(String.valueOf(productHierarchyDto.getHierarchyId()));
              else
                   dataSelectionDTO.setProdHierSid(String.valueOf(0));
                  
            } else {
                dataSelectionDTO.setProdHierSid(String.valueOf(0));
            }

            if (selectedCustomerGroupDTO != null && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
                    && !StringUtils.isEmpty(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid())) && !StringUtils.isBlank(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))) {
                dataSelectionDTO.setCustomerGrpSid(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()));
            } else {
                dataSelectionDTO.setCustomerGrpSid(String.valueOf(0));
            }
            if (selectedProductGroupDTO != null && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
                    && !StringUtils.isEmpty(String.valueOf(selectedProductGroupDTO.getProductGroupSid())) && !StringUtils.isBlank(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))) {
                dataSelectionDTO.setProdGrpSid(String.valueOf(selectedProductGroupDTO.getProductGroupSid()));
            } else {
                dataSelectionDTO.setProdGrpSid(String.valueOf(0));
            }

            dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((customerLevel.getValue() == null ? StringUtils.EMPTY : customerLevel.getValue().toString()))));
            dataSelectionDTO.setProductHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((productLevel.getValue() == null ? StringUtils.EMPTY : productLevel.getValue().toString()))));
            if (customerHierarchyDto != null) {
                dataSelectionDTO.setCustomerHierarchyVer(String.valueOf(customerHierarchyDto.getVersionNo()));
            } else {
                dataSelectionDTO.setCustomerHierarchyVer(String.valueOf(0));
            }
            if (productHierarchyDto != null) {
                dataSelectionDTO.setProductHierarchyVer(String.valueOf(productHierarchyDto.getVersionNo()));
            } else {
                dataSelectionDTO.setProductHierarchyVer(String.valueOf(0));
            }
            if (fromPeriod.getValue() != null) {
                if (SELECT_ONE.equals(String.valueOf(fromPeriod.getValue())) && MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
                    dataSelectionDTO.setFromPeriod((String.valueOf(fromPeriod.getValue())));
                    if (!String.valueOf(fromPeriod.getValue()).isEmpty() && !Constant.SELECT_ONE.equals(String.valueOf(fromPeriod.getValue()))) {

                        dataSelectionDTO.setFromDate(format.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
                    }
                } else {
                    dataSelectionDTO.setFromPeriod(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
                    dataSelectionDTO.setFromDate(format.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
                }

            }
            if (toPeriod.getValue() != null) {
                if (SELECT_ONE.equals(String.valueOf(toPeriod.getValue())) && MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
                    dataSelectionDTO.setToPeriod((String.valueOf(toPeriod.getValue())));
                    if (!String.valueOf(toPeriod.getValue()).isEmpty() && !Constant.SELECT_ONE.equals(String.valueOf(toPeriod.getValue()))) {
                        dataSelectionDTO.setToDate(format.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
                    }

                } else {
                    dataSelectionDTO.setToPeriod(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
                    dataSelectionDTO.setToDate(format.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
                }
            }
            if (discount.getValue() != null && !SELECT_ONE.equals(discount.getValue()) && StringUtils.isNotBlank(discount.getValue().toString())) {
                CompanyDdlbDto dto = (CompanyDdlbDto) discount.getValue();
                dataSelectionDTO.setDiscount(dto.getRsNo());
                dataSelectionDTO.setDiscountSid(dto.getRsModelSid());
            } else {
                dataSelectionDTO.setDiscount(SELECT_ONE);
                dataSelectionDTO.setDiscountSid(0);
            }

            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((level.getValue() == null ? StringUtils.EMPTY : level.getValue().toString()))));
                dataSelectionDTO.setProductHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((productlevelDdlb.getValue() == null ? StringUtils.EMPTY : productlevelDdlb.getValue().toString()))));
            } else {
                dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((customerLevel.getValue() == null ? StringUtils.EMPTY : customerLevel.getValue().toString()))));
                dataSelectionDTO.setProductHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((productLevel.getValue() == null ? StringUtils.EMPTY : productLevel.getValue().toString()))));
            }

            dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(UiUtils.parseStringToInteger((level.getValue() == null ? StringUtils.EMPTY : level.getValue().toString()))));
            dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(UiUtils.parseStringToInteger((productlevelDdlb.getValue() == null ? StringUtils.EMPTY : productlevelDdlb.getValue().toString()))));
            dataSelectionDTO.setProjectionName(projectionName.getValue());
            dataSelectionDTO.setDescription(description.getValue());

        } catch (ParseException ex) {
           
            LOGGER.error(ex + " in binding for save, can't parse dates");
        }
        return dataSelectionDTO;
    }

    /**
     * Move left btn.
     *
     * @param event the event
     */
    @Override
    protected void moveLeftButtonLogic() {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            if (availableCustomer.getValue() != null) {
                int forecastLevel = 0;
                if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                    if (level.getValue() != null) {
                        forecastLevel = UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
                    }
                } else {
                    if (customerLevel.getValue() != null) {
                        forecastLevel = UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
                    }
                }
                Object item = availableCustomer.getValue();
                Object selectedItem = null;
                if (selectedCustomerContainer.size() > 0) {
                    if (selectedCustomer.getValue() != null) {

                        selectedItem = selectedCustomer.getValue();
                        String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<String>();
                        List<Leveldto> newParentLevels = null;
                        List<Leveldto> newChildLevels = null;
                        hierarchyNos.add(hierarchyNo + ".");
                        int pos = 0;
                        Leveldto selectedParent = DataSelectionUtil.getBeanFromId(selectedItem);
                        while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                            pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                            if (pos != -1) {
                                hierarchyNo = hierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(hierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        List<String> selectedHierarchyNos = new ArrayList<String>();
                        for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                        List<String> removeValues = new ArrayList<String>();
                        for (String uncommonHierValue : uncommonValues) {
                            if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                removeValues.add(uncommonHierValue);
                            }
                        }
                        if (!removeValues.isEmpty()) {
                            uncommonValues.removeAll(removeValues);
                        }
                        if (!uncommonValues.isEmpty()) {
                            newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescriptionMap);
                        }
                        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]), customerDescriptionMap);
                        } else {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
                        }
                        if (newParentLevels != null) {
                            int pos2 = 0;
                            String parentHierarchyNo;
                            Leveldto parent = null;
                            for (Leveldto newLevel : newParentLevels) {
                                String tempHNo = newLevel.getHierarchyNo();
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos2 != -1) {
                                    parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                } else {
                                    parentHierarchyNo = tempHNo + ".";
                                }
                                if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                    customerBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedCustomerContainer.addBean(newLevel);
                                    if (forecastLevel != newLevel.getLevelNo()) {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                    }
                                }

                                if (!StringUtils.isBlank(parentHierarchyNo)) {
                                    for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                        if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                            parent = selectedLevel;
                                            break;
                                        }
                                    }
                                }
                                selectedCustomerContainer.setParent(newLevel, parent);
                                parent = newLevel;
                            }
                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                Leveldto childsParent = null;
                                for (Leveldto newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos3 != -1) {
                                        childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = tempHNo + ".";
                                    }
                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        customerBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                            if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                childsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedCustomerContainer.setParent(newLevel, childsParent);
                                }
                            }

                        }

                    } else {
                        String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<String>();
                        List<Leveldto> newParentLevels = null;
                        List<Leveldto> newChildLevels = null;
                        hierarchyNos.add(hierarchyNo + ".");
                        int pos = 0;
                        String selectedParentHierarchyNo = StringUtils.EMPTY;
                        Leveldto selectedParent = null;
                        while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                            pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                            if (pos != -1) {
                                hierarchyNo = hierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(hierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        List<String> selectedHierarchyNos = new ArrayList<String>();
                        for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                        List<String> removeValues = new ArrayList<String>();
                        for (String uncommonHierValue : uncommonValues) {
                            if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                removeValues.add(uncommonHierValue);
                            }
                        }
                        if (!removeValues.isEmpty()) {
                            uncommonValues.removeAll(removeValues);
                        }
                        if (!uncommonValues.isEmpty()) {
                            String tempHNo = uncommonValues.get(0);
                            if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                            }
                            int pos2 = 0;
                            pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                            if (pos2 != -1) {
                                selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                            } else {
                                selectedParentHierarchyNo = tempHNo + ".";
                            }
                        }
                        if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                            for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                    selectedParent = selectedLevel;
                                    break;
                                }
                            }
                        }
                        if (!uncommonValues.isEmpty()) {
                            newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescriptionMap);
                        }
                        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]), customerDescriptionMap);
                        } else {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
                        }
                        if (newParentLevels != null) {
                            for (Leveldto newLevel : newParentLevels) {
                                if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                    customerBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedCustomerContainer.addBean(newLevel);
                                    if (forecastLevel != newLevel.getLevelNo()) {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                    }
                                    selectedCustomerContainer.setParent(newLevel, selectedParent);
                                    selectedParent = newLevel;
                                }
                            }
                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                Leveldto childsParent = null;
                                for (Leveldto newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos3 != -1) {
                                        childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = tempHNo + ".";
                                    }

                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        customerBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                            if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                childsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }

                                    selectedCustomerContainer.setParent(newLevel, childsParent);
                                }
                            }

                        }

                    }
                } else {

                    if (availableCustomer.getValue() != null && (DataSelectionUtil.getBeanFromId(availableCustomer.getValue()).getLevelNo() > 1)) {

                        String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<String>();
                        List<Leveldto> newParentLevels = null;
                        List<Leveldto> newChildLevels = null;
                        hierarchyNos.add(hierarchyNo + ".");
                        int pos = 0;
                        String selectedParentHierarchyNo = StringUtils.EMPTY;
                        Leveldto selectedParent2 = null;
                        while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                            pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                            if (pos != -1) {
                                hierarchyNo = hierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(hierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                            for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                    selectedParent2 = selectedLevel;
                                    break;
                                }
                            }
                        }
                        newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(hierarchyNos), customerDescriptionMap);
                        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]), customerDescriptionMap);
                        } else {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
                        }
                        if (newParentLevels != null) {
                            for (Leveldto newLevel : newParentLevels) {
                                if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {

                                    customerBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedCustomerContainer.addBean(newLevel);
                                    if (forecastLevel != newLevel.getLevelNo()) {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                    }
                                    selectedCustomerContainer.setParent(newLevel, selectedParent2);
                                    selectedParent2 = newLevel;
                                }
                            }
                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                Leveldto childsParent = null;
                                for (Leveldto newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos3 != -1) {
                                        childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = tempHNo + ".";
                                    }
                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        customerBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }
                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                            if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                childsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedCustomerContainer.setParent(newLevel, childsParent);
                                }
                            }

                        }
                    } else {
                        Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

                        String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        List<Leveldto> newChildLevels = null;
                        int pos = 0;
                        if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                            while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos != -1) {
                                    hierarchyNo = hierarchyNo.substring(0, pos) + ".";
                                }
                            }
                        }
                        if (customerBeanList.isEmpty() || !customerBeanList.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
                            customerBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
                            selectedCustomerContainer.addBean(selectedParent);
                            if (forecastLevel != selectedParent.getLevelNo()) {
                                selectedCustomerContainer.setChildrenAllowed(selectedParent, true);
                            } else {
                                selectedCustomerContainer.setChildrenAllowed(selectedParent, false);
                            }
                        }
                        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]), customerDescriptionMap);
                        } else {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
                        }
                        if (newChildLevels != null) {
                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                Leveldto childsParent = null;
                                for (Leveldto newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos3 != -1) {
                                        childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = tempHNo + ".";
                                    }
                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationShipBuilderId())) {
                                        customerBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                            if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                childsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedCustomerContainer.setParent(newLevel, childsParent);
                                }
                            }
                        }

                    }
                }

            } else {
                AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
                        "No Level was selected to move. Please try again.");
            }
        } catch (Exception e) { 
            LOGGER.error(e);
        }
    }

    @Override
    protected void moveAllButtonLogic() {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            int forecastLevel = 0;
            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                if (level.getValue() != null) {
                    forecastLevel = UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
                }
            } else {
                if (customerLevel.getValue() != null) {
                    forecastLevel = UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
                }
            }
            if (availableCustomerContainer.size() > 0) {
                List<Leveldto> iteams = new ArrayList<Leveldto>(availableCustomerContainer.getItemIds());
                Object selectedItem = null;
                if (selectedCustomerContainer.size() > 0) {
                    if (selectedCustomer.getValue() != null) {
                        selectedItem = selectedCustomer.getValue();
                        for (Leveldto item : iteams) {
                            if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil.getBeanFromId(selectedItem).getLevelNo()) {

                                String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                                if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                    hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                                }
                                String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                                List<String> hierarchyNos = new ArrayList<String>();
                                List<Leveldto> newParentLevels = null;
                                List<Leveldto> newChildLevels = null;
                                hierarchyNos.add(hierarchyNo + ".");
                                int pos = 0;
                                while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                    pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos != -1) {
                                        hierarchyNo = hierarchyNo.substring(0, pos);
                                    }
                                    hierarchyNos.add(hierarchyNo + ".");
                                }
                                Collections.reverse(hierarchyNos);
                                List<String> selectedHierarchyNos = new ArrayList<String>();
                                for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                    if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                        selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                    }
                                }
                                List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                                List<String> removeValues = new ArrayList<String>();
                                for (String uncommonHierValue : uncommonValues) {
                                    if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                        removeValues.add(uncommonHierValue);
                                    }
                                }
                                if (!removeValues.isEmpty()) {
                                    uncommonValues.removeAll(removeValues);
                                }
                                if (!uncommonValues.isEmpty()) {
                                    newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescriptionMap);
                                }
                                if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                                    newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]), customerDescriptionMap);
                                } else {
                                    newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
                                }
                                if (newParentLevels != null) {
                                    int pos2 = 0;
                                    String parentHierarchyNo;
                                    Leveldto parent = null;
                                    for (Leveldto newLevel : newParentLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());

                                        if (pos2 != -1) {
                                            parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                        } else {
                                            parentHierarchyNo = tempHNo + ".";
                                        }
                                        if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            customerBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedCustomerContainer.addBean(newLevel);
                                            if (forecastLevel != newLevel.getLevelNo()) {
                                                selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(parentHierarchyNo)) {
                                            for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                                if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    parent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedCustomerContainer.setParent(newLevel, parent);
                                        parent = newLevel;
                                    }
                                    if (!newChildLevels.isEmpty()) {
                                        int pos3 = 0;
                                        String childHierarchyNo;
                                        Leveldto childsParent = null;
                                        for (Leveldto newLevel : newChildLevels) {
                                            String tempHNo = newLevel.getHierarchyNo();
                                            if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                                tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                            }
                                            pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                            if (pos3 != -1) {
                                                childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                            } else {
                                                childHierarchyNo = tempHNo + ".";
                                            }
                                            if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                                customerBeanList.add(newLevel.getRelationshipLevelSid());
                                                selectedCustomerContainer.addBean(newLevel);
                                                if (forecastLevel != newLevel.getLevelNo()) {
                                                    selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                                } else {
                                                    selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                                }
                                            }
                                            if (!StringUtils.isBlank(childHierarchyNo)) {
                                                for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                                    if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                        childsParent = selectedLevel;
                                                        break;
                                                    }
                                                }
                                            }
                                            selectedCustomerContainer.setParent(newLevel, childsParent);
                                        }
                                    }

                                }
                            }
                        }
                    } else {

                        for (Leveldto item : iteams) {
                            String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                            }
                            String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            List<String> hierarchyNos = new ArrayList<String>();
                            List<Leveldto> newParentLevels = null;
                            List<Leveldto> newChildLevels = null;
                            hierarchyNos.add(hierarchyNo + ".");
                            int pos = 0;
                            String selectedParentHierarchyNo = StringUtils.EMPTY;
                            Leveldto selectedParent = null;
                            while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos != -1) {
                                    hierarchyNo = hierarchyNo.substring(0, pos);
                                }
                                hierarchyNos.add(hierarchyNo + ".");
                            }
                            Collections.reverse(hierarchyNos);
                            List<String> selectedHierarchyNos = new ArrayList<String>();
                            for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                    selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                }
                            }
                            List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                            List<String> removeValues = new ArrayList<String>();
                            for (String uncommonHierValue : uncommonValues) {
                                if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                    removeValues.add(uncommonHierValue);
                                }
                            }
                            if (!removeValues.isEmpty()) {
                                uncommonValues.removeAll(removeValues);
                            }
                            if (!uncommonValues.isEmpty()) {
                                String tempHNo = uncommonValues.get(0);
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                int pos2 = 0;
                                pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos2 != -1) {
                                    selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                } else {
                                    selectedParentHierarchyNo = tempHNo + ".";
                                }

                            }
                            if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                                for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                    if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                        selectedParent = selectedLevel;
                                        break;
                                    }
                                }
                            }
                            if (!uncommonValues.isEmpty()) {
                                newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescriptionMap);
                            }
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]), customerDescriptionMap);
                            } else {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
                            }
                            if (newParentLevels != null) {
                                for (Leveldto newLevel : newParentLevels) {
                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        customerBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                        }
                                        selectedCustomerContainer.setParent(newLevel, selectedParent);
                                        selectedParent = newLevel;
                                    }
                                }
                                if (!newChildLevels.isEmpty()) {
                                    int pos3 = 0;
                                    String childHierarchyNo;
                                    Leveldto childsParent = null;
                                    for (Leveldto newLevel : newChildLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                        if (pos3 != -1) {
                                            childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                        } else {
                                            childHierarchyNo = tempHNo + ".";
                                        }
                                        if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            customerBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedCustomerContainer.addBean(newLevel);
                                            if (forecastLevel != newLevel.getLevelNo()) {
                                                selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(childHierarchyNo)) {
                                            for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                                if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    childsParent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedCustomerContainer.setParent(newLevel, childsParent);
                                    }
                                }

                            }

                        }
                    }
                } else {

                    if (level.getValue() != null && UiUtils.parseStringToInteger(String.valueOf(level.getValue())) == 1) {

                        for (Leveldto item : iteams) {
                            selectedCustomerContainer.removeAllItems();
                            selectedCustomer.removeAllItems();
                            Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

                            String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                            }
                            String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            List<Leveldto> newChildLevels = null;
                            int pos = 0;
                            if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                    pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos != -1) {
                                        hierarchyNo = hierarchyNo.substring(0, pos) + ".";
                                    }
                                }
                            }
                            if (customerBeanList.isEmpty() || !customerBeanList.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
                                customerBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
                                selectedCustomerContainer.addBean(selectedParent);
                                if (forecastLevel != selectedParent.getLevelNo()) {
                                    selectedCustomerContainer.setChildrenAllowed(selectedParent, true);
                                } else {
                                    selectedCustomerContainer.setChildrenAllowed(selectedParent, false);
                                }
                            }
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]), customerDescriptionMap);
                            } else {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
                            }
                            if (newChildLevels != null) {
                                if (!newChildLevels.isEmpty()) {
                                    int pos3 = 0;
                                    String childHierarchyNo;
                                    Leveldto childsParent = null;
                                    for (Leveldto newLevel : newChildLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                        if (pos3 != -1) {
                                            childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                        } else {
                                            childHierarchyNo = tempHNo + ".";
                                        }
                                        if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            customerBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedCustomerContainer.addBean(newLevel);
                                            if (forecastLevel != newLevel.getLevelNo()) {
                                                selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }
                                        if (!StringUtils.isBlank(childHierarchyNo)) {
                                            for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                                if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    childsParent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedCustomerContainer.setParent(newLevel, childsParent);
                                    }
                                }
                            }
                        }
                    } else {

                        for (Leveldto item : iteams) {
                            String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                            }
                            String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            List<String> hierarchyNos = new ArrayList<String>();
                            List<Leveldto> newParentLevels = null;
                            List<Leveldto> newChildLevels = null;
                            hierarchyNos.add(hierarchyNo + ".");
                            int pos = 0;
                            String selectedParentHierarchyNo = StringUtils.EMPTY;
                            Leveldto selectedParent = null;
                            while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos != -1) {
                                    hierarchyNo = hierarchyNo.substring(0, pos);
                                }
                                hierarchyNos.add(hierarchyNo + ".");
                            }
                            Collections.reverse(hierarchyNos);
                            List<String> selectedHierarchyNos = new ArrayList<String>();
                            for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                    selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                }
                            }

                            List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                            List<String> removeValues = new ArrayList<String>();
                            for (String uncommonHierValue : uncommonValues) {
                                if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                    removeValues.add(uncommonHierValue);
                                }
                            }
                            if (!removeValues.isEmpty()) {
                                uncommonValues.removeAll(removeValues);
                            }
                            if (!uncommonValues.isEmpty()) {
                                String tempHNo = uncommonValues.get(0);
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                int pos2 = 0;
                                pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos2 != -1) {
                                    selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                } else {
                                    selectedParentHierarchyNo = tempHNo + ".";
                                }

                            }
                            if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                                for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                    if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                        selectedParent = selectedLevel;
                                        break;
                                    }
                                }
                            }
                            newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescriptionMap);
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]), customerDescriptionMap);
                            } else {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
                            }
                            if (newParentLevels != null) {
                                for (Leveldto newLevel : newParentLevels) {
                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        customerBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                        }
                                        selectedCustomerContainer.setParent(newLevel, selectedParent);
                                        selectedParent = newLevel;
                                    }
                                }
                                if (!newChildLevels.isEmpty()) {
                                    int pos3 = 0;
                                    String childHierarchyNo;
                                    Leveldto childsParent = null;
                                    for (Leveldto newLevel : newChildLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                        if (pos3 != -1) {
                                            childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                        } else {
                                            childHierarchyNo = tempHNo + ".";
                                        }
                                        if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            customerBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedCustomerContainer.addBean(newLevel);
                                            if (forecastLevel != newLevel.getLevelNo()) {
                                                selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }
                                        if (!StringUtils.isBlank(childHierarchyNo)) {
                                            for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
                                                if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    childsParent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedCustomerContainer.setParent(newLevel, childsParent);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        } catch (Exception e) { 
            LOGGER.error(e);
        }
    }

    @Override
    protected void moveAllProductButtonLogic() {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            int forecastLevel = 0;
            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                if (productlevelDdlb.getValue() != null) {
                    forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
                }
            } else {
                if (productLevel.getValue() != null) {
                    forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
                }
            }

            if (availableProductContainer.size() > 0) {
                List<Leveldto> iteams = new ArrayList<Leveldto>(availableProductContainer.getItemIds());
                Object selectedItem = null;
                if (selectedProductContainer.size() > 0) {
                    if (selectedProduct.getValue() != null) {
                        selectedItem = selectedProduct.getValue();
                        for (Leveldto item : iteams) {
                            if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil.getBeanFromId(selectedItem).getLevelNo()) {

                                String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                                if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                    hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                                }
                                String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                                List<String> hierarchyNos = new ArrayList<String>();
                                List<Leveldto> newParentLevels = null;
                                List<Leveldto> newChildLevels = null;
                                hierarchyNos.add(hierarchyNo + ".");
                                int pos = 0;
                                while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                    pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos != -1) {
                                        hierarchyNo = hierarchyNo.substring(0, pos);
                                    }
                                    hierarchyNos.add(hierarchyNo + ".");
                                }
                                Collections.reverse(hierarchyNos);
                                List<String> selectedHierarchyNos = new ArrayList<String>();
                                for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                    if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                        selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                    }
                                }
                                List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                                List<String> removeValues = new ArrayList<String>();
                                for (String uncommonHierValue : uncommonValues) {
                                    if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                        removeValues.add(uncommonHierValue);
                                    }
                                }
                                if (!removeValues.isEmpty()) {
                                    uncommonValues.removeAll(removeValues);
                                }
                                newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), productDescriptionMap);
                                if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                                    newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]), productDescriptionMap);
                                } else {
                                    newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                                }
                                if (newParentLevels != null) {
                                    int pos2 = 0;
                                    String parentHierarchyNo;
                                    Leveldto parent = null;
                                    for (Leveldto newLevel : newParentLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                        if (pos2 != -1) {
                                            parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                        } else {
                                            parentHierarchyNo = tempHNo + ".";
                                        }
                                        if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            productBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedProductContainer.addBean(newLevel);
                                            if (forecastLevel != newLevel.getLevelNo()) {
                                                selectedProductContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedProductContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(parentHierarchyNo)) {
                                            for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                                if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    parent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedProductContainer.setParent(newLevel, parent);
                                        parent = newLevel;
                                    }
                                    if (!newChildLevels.isEmpty()) {
                                        int pos3 = 0;
                                        String childHierarchyNo;
                                        Leveldto childsParent = null;
                                        for (Leveldto newLevel : newChildLevels) {
                                            String tempHNo = newLevel.getHierarchyNo();
                                            if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                                tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                            }
                                            pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                            if (pos3 != -1) {
                                                childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                            } else {
                                                childHierarchyNo = tempHNo + ".";
                                            }
                                            if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                                productBeanList.add(newLevel.getRelationshipLevelSid());
                                                selectedProductContainer.addBean(newLevel);
                                                if (forecastLevel != newLevel.getLevelNo()) {
                                                    selectedProductContainer.setChildrenAllowed(newLevel, true);
                                                } else {
                                                    selectedProductContainer.setChildrenAllowed(newLevel, false);
                                                }
                                            }
                                            if (!StringUtils.isBlank(childHierarchyNo)) {
                                                for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                                    if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                        childsParent = selectedLevel;
                                                        break;
                                                    }
                                                }
                                            }
                                            selectedProductContainer.setParent(newLevel, childsParent);
                                        }
                                    }

                                }
                            }
                        }
                    } else {

                        for (Leveldto item : iteams) {
                            String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                            }
                            String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            List<String> hierarchyNos = new ArrayList<String>();
                            List<Leveldto> newParentLevels = null;
                            List<Leveldto> newChildLevels = null;
                            hierarchyNos.add(hierarchyNo + ".");
                            int pos = 0;
                            String selectedParentHierarchyNo = StringUtils.EMPTY;
                            Leveldto selectedParent = null;
                            while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos != -1) {
                                    hierarchyNo = hierarchyNo.substring(0, pos);
                                }
                                hierarchyNos.add(hierarchyNo + ".");
                            }
                            Collections.reverse(hierarchyNos);
                            List<String> selectedHierarchyNos = new ArrayList<String>();
                            for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                    selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                }
                            }
                            List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                            List<String> removeValues = new ArrayList<String>();
                            for (String uncommonHierValue : uncommonValues) {
                                if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                    removeValues.add(uncommonHierValue);
                                }
                            }
                            if (!removeValues.isEmpty()) {
                                uncommonValues.removeAll(removeValues);
                            }
                            if (!uncommonValues.isEmpty()) {
                                String tempHNo = uncommonValues.get(0);
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                int pos2 = 0;
                                pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos2 != -1) {
                                    selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                } else {
                                    selectedParentHierarchyNo = tempHNo + ".";
                                }

                            }
                            if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                                for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                    if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                        selectedParent = selectedLevel;
                                        break;
                                    }
                                }
                            }
                            if (!uncommonValues.isEmpty()) {
                                newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), productDescriptionMap);
                            }
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]), productDescriptionMap);
                            } else {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                            }
                            if (newParentLevels != null) {
                                for (Leveldto newLevel : newParentLevels) {
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(newLevel, false);
                                        }
                                        selectedProductContainer.setParent(newLevel, selectedParent);
                                        selectedParent = newLevel;
                                    }
                                }
                                if (!newChildLevels.isEmpty()) {
                                    int pos3 = 0;
                                    String childHierarchyNo;
                                    Leveldto childsParent = null;
                                    for (Leveldto newLevel : newChildLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                        if (pos3 != -1) {
                                            childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                        } else {
                                            childHierarchyNo = tempHNo + ".";
                                        }
                                        if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            productBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedProductContainer.addBean(newLevel);
                                            if (forecastLevel != newLevel.getLevelNo()) {
                                                selectedProductContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedProductContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(childHierarchyNo)) {
                                            for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                                if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    childsParent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedProductContainer.setParent(newLevel, childsParent);
                                    }
                                }

                            }

                        }
                    }
                } else {

                    String tempProductLevel = String.valueOf(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName) ? productlevelDdlb.getValue() : productLevel.getValue());
                    if (tempProductLevel != null && UiUtils.parseStringToInteger(tempProductLevel) == 1) {

                        for (Leveldto item : iteams) {
                            selectedProductContainer.removeAllItems();
                            selectedProduct.removeAllItems();
                            Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

                            String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                            }
                            String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            List<Leveldto> newChildLevels = null;
                            int pos = 0;
                            if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                    pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos != -1) {
                                        hierarchyNo = hierarchyNo.substring(0, pos) + ".";
                                    }
                                }
                            }
                            if (productBeanList.isEmpty() || !productBeanList.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
                                productBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
                                selectedProductContainer.addBean(selectedParent);
                                if (forecastLevel != selectedParent.getLevelNo()) {
                                    selectedProductContainer.setChildrenAllowed(selectedParent, true);
                                } else {
                                    selectedProductContainer.setChildrenAllowed(selectedParent, false);
                                }
                                availableProductContainer.removeItem(selectedParent);
                                availableProduct.removeItem(selectedParent);
                            }
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]), productDescriptionMap);
                            } else {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                            }
                            if (newChildLevels != null) {
                                if (!newChildLevels.isEmpty()) {
                                    int pos3 = 0;
                                    String childHierarchyNo;
                                    Leveldto childsParent = null;
                                    for (Leveldto newLevel : newChildLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                        if (pos3 != -1) {
                                            childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                        } else {
                                            childHierarchyNo = tempHNo + ".";
                                        }
                                        if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            productBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedProductContainer.addBean(newLevel);
                                            if (forecastLevel != newLevel.getLevelNo()) {
                                                selectedProductContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedProductContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }
                                        if (!StringUtils.isBlank(childHierarchyNo)) {
                                            for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                                if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    childsParent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedProductContainer.setParent(newLevel, childsParent);
                                    }
                                }
                            }
                        }
                    } else {

                        for (Leveldto item : iteams) {
                            String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                            }
                            String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                            List<String> hierarchyNos = new ArrayList<String>();
                            List<Leveldto> newParentLevels = null;
                            List<Leveldto> newChildLevels = null;
                            hierarchyNos.add(hierarchyNo + ".");
                            int pos = 0;
                            String selectedParentHierarchyNo = StringUtils.EMPTY;
                            Leveldto selectedParent = null;
                            while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos != -1) {
                                    hierarchyNo = hierarchyNo.substring(0, pos);
                                }
                                hierarchyNos.add(hierarchyNo + ".");
                            }
                            Collections.reverse(hierarchyNos);
                            List<String> selectedHierarchyNos = new ArrayList<String>();
                            for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                    selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                }
                            }
                            List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                            List<String> removeValues = new ArrayList<String>();
                            for (String uncommonHierValue : uncommonValues) {
                                if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                    removeValues.add(uncommonHierValue);
                                }
                            }
                            if (!removeValues.isEmpty()) {
                                uncommonValues.removeAll(removeValues);
                            }
                            if (!uncommonValues.isEmpty()) {
                                String tempHNo = uncommonValues.get(0);
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                int pos2 = 0;
                                pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos2 != -1) {
                                    selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                } else {
                                    selectedParentHierarchyNo = tempHNo + ".";
                                }

                            }
                            if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                                for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                    if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                        selectedParent = selectedLevel;
                                        break;
                                    }
                                }
                            }
                            if (!uncommonValues.isEmpty()) {
                                newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), productDescriptionMap);
                            }
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]), productDescriptionMap);
                            } else {
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                            }
                            if (newParentLevels != null) {
                                int pos2 = 0;
                                String parentHierarchyNo;
                                Leveldto parent = null;
                                for (Leveldto newLevel : newParentLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());

                                    if (pos2 != -1) {
                                        parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                    } else {
                                        parentHierarchyNo = tempHNo + ".";
                                    }
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }
                                    if (!StringUtils.isBlank(parentHierarchyNo)) {
                                        for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                            if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                parent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedProductContainer.setParent(newLevel, parent);
                                    parent = newLevel;
                                }

                                if (!newChildLevels.isEmpty()) {
                                    int pos3 = 0;
                                    String childHierarchyNo;
                                    Leveldto childsParent = null;
                                    for (Leveldto newLevel : newChildLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                        if (pos3 != -1) {
                                            childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                        } else {
                                            childHierarchyNo = tempHNo + ".";
                                        }
                                        if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            productBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedProductContainer.addBean(newLevel);
                                            if (forecastLevel != newLevel.getLevelNo()) {
                                                selectedProductContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedProductContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }
                                        if (!StringUtils.isBlank(childHierarchyNo)) {
                                            for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                                if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    childsParent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedProductContainer.setParent(newLevel, childsParent);
                                    }
                                }

                            }
                        }
                    }
                }
                DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
            }
        } catch (Exception e) { 
            LOGGER.error(e);
        }
    }

    /**
     * Move left btn.
     *
     * @param event the event
     */
    @Override
    protected void moveRigthButtonLogic() {
        if (selectedCustomer.getValue() != null) {
            Object selectedItem = selectedCustomer.getValue();
            String levelInString = DASH;
            if (level.getValue() != null && !String.valueOf(level.getValue()).equalsIgnoreCase(String.valueOf(SELECT_ONE))) {
                levelInString = String.valueOf(level.getValue());
            }
            int currentLevel = UiUtils.parseStringToInteger(levelInString);
            if (currentLevel != 0 && DataSelectionUtil.getBeanFromId(selectedItem).getLevelNo() == currentLevel) {

            }
            DataSelectionUtil.removeItemsRecursively(selectedItem, selectedCustomer, availableCustomer, selectedCustomerContainer, availableCustomerContainer, currentLevel);
            selectedCustomerContainer.removeItem(DataSelectionUtil.getBeanFromId(selectedItem));
            selectedCustomer.removeItem(selectedItem);
            customerBeanList.remove(DataSelectionUtil.getBeanFromId(selectedItem).getRelationshipLevelSid());
            customerBeanList.removeAll(customerBeanList);
            List<Leveldto> selectedValueItem = selectedCustomerContainer.getItemIds();
            for (Leveldto dto : selectedValueItem) {
                customerBeanList.add(dto.getRelationshipLevelSid());
            }
            if (dismantleCustomerSelection) {
                triggerCustGrpOnView(dataSelectionDTO.getCustomerGrpSid(), Integer.parseInt(dataSelectionDTO.getCustomerHierSid()), false);
                dismantleCustomerSelection = false;
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
                    "No Level was selected to move. Please try again. ");
        }
    }

    /**
     * Move left product.
     *
     * @param event the event
     */
    @Override
    protected void moveRigthProductButtonLogic() {
        try {
            if (selectedProduct.getValue() != null) {
                List<Leveldto> listValue = new ArrayList<Leveldto>();
                Object selectedItem = selectedProduct.getValue();
                Leveldto selectedLevel = (Leveldto) DataSelectionUtil.getBeanFromId(selectedItem);
                String levelInString = DASH;
                if (!String.valueOf(productlevelDdlb.getValue()).equalsIgnoreCase(String.valueOf(SELECT_ONE))) {
                    levelInString = String.valueOf(productlevelDdlb.getValue());
                }
                int currentLevel = UiUtils.parseStringToInteger(levelInString);
                if (currentLevel != 0 && selectedLevel.getLevelNo() == currentLevel) {
                    if (Constant.NDC.equalsIgnoreCase(selectedLevel.getLevel())) {
                        listValue = DataSelectionUtil.getFSValue(selectedLevel.getRelationshipLevelValue(), selectedLevel.getFieldName());
                        selectedLevel.setForm(StringUtils.EMPTY + listValue.get(0).getForm());
                        selectedLevel.setStrength(StringUtils.EMPTY + listValue.get(0).getStrength());
                    }

                }
                DataSelectionUtil.removeItemsRecursively(selectedItem, selectedProduct, availableProduct, selectedProductContainer, availableProductContainer, currentLevel);
                selectedProductContainer.removeItem(selectedLevel);
                selectedProduct.removeItem(selectedItem);
                if (dismantleProductSelection) {
                    triggerProdGrpOnView(dataSelectionDTO.getProdGrpSid(), dataSelectionDTO.getProdHierSid(), false);
                    dismantleProductSelection = false;
                }
                productBeanList.removeAll(productBeanList);
                List<Leveldto> productListValue = selectedProductContainer.getItemIds();
                for (Leveldto dto : productListValue) {
                    productBeanList.add(dto.getRelationshipLevelSid());
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
                        "No Level was selected to move. Please try again. ");
            }
        } catch (Exception ex) { 
            LOGGER.error(ex + " in moveRightProduct");
        }

    }

    /**
     * Move right product.
     *
     * @param event the event
     */
    @Override
    protected void moveLeftProductButtonLogic() {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            int forecastLevel = 0;
            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                if (productlevelDdlb.getValue() != null) {
                    forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
                }
            } else {
                if (productLevel.getValue() != null) {
                    forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
                }
            }

            if (availableProduct.getValue() != null) {
                Object item = availableProduct.getValue();
                if (selectedProductContainer.size() > 0) {
                    if (selectedProduct.getValue() != null) {

                        String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<String>();
                        List<Leveldto> newParentLevels = null;
                        List<Leveldto> newChildLevels = null;
                        hierarchyNos.add(hierarchyNo + ".");
                        int pos = 0;
                        while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                            pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                            if (pos != -1) {
                                hierarchyNo = hierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(hierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        List<String> selectedHierarchyNos = new ArrayList<String>();
                        for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                        List<String> removeValues = new ArrayList<String>();
                        for (String uncommonHierValue : uncommonValues) {
                            if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                removeValues.add(uncommonHierValue);
                            }
                        }
                        if (!removeValues.isEmpty()) {
                            uncommonValues.removeAll(removeValues);
                        }
                        if (!uncommonValues.isEmpty()) {
                            newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), productDescriptionMap);
                        }
                        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]), productDescriptionMap);
                        } else {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                        }
                        if (newParentLevels != null) {
                            int pos2 = 0;
                            String parentHierarchyNo;
                            Leveldto parent = null;
                            for (Leveldto newLevel : newParentLevels) {
                                String tempHNo = newLevel.getHierarchyNo();
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos2 != -1) {
                                    parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                } else {
                                    parentHierarchyNo = tempHNo + ".";
                                }
                                if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                    productBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedProductContainer.addBean(newLevel);
                                    if (forecastLevel != newLevel.getLevelNo()) {
                                        selectedProductContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedProductContainer.setChildrenAllowed(newLevel, false);
                                    }
                                }

                                if (!StringUtils.isBlank(parentHierarchyNo)) {
                                    for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                        if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                            parent = selectedLevel;
                                            break;
                                        }
                                    }
                                }
                                selectedProductContainer.setParent(newLevel, parent);
                                parent = newLevel;
                            }
                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                Leveldto childsParent = null;
                                for (Leveldto newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos3 != -1) {
                                        childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = tempHNo + ".";
                                    }
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                            if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                childsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedProductContainer.setParent(newLevel, childsParent);
                                }
                            }

                        }

                    } else {

                        String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<String>();
                        List<Leveldto> newParentLevels = null;
                        List<Leveldto> newChildLevels = null;
                        hierarchyNos.add(hierarchyNo + ".");
                        int pos = 0;
                        String selectedParentHierarchyNo = StringUtils.EMPTY;
                        Leveldto selectedParent = null;
                        while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                            pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                            if (pos != -1) {
                                hierarchyNo = hierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(hierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        List<String> selectedHierarchyNos = new ArrayList<String>();
                        for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                        List<String> removeValues = new ArrayList<String>();
                        for (String uncommonHierValue : uncommonValues) {
                            if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                removeValues.add(uncommonHierValue);
                            }
                        }
                        if (!removeValues.isEmpty()) {
                            uncommonValues.removeAll(removeValues);
                        }
                        if (!uncommonValues.isEmpty()) {
                            String tempHNo = uncommonValues.get(0);
                            if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                            }
                            int pos2 = 0;
                            pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                            if (pos2 != -1) {
                                selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                            } else {
                                selectedParentHierarchyNo = tempHNo + ".";
                            }
                        }
                        if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                            for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                    selectedParent = selectedLevel;
                                    break;
                                }
                            }
                        }
                        if (!uncommonValues.isEmpty()) {
                            newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), productDescriptionMap);
                        }
                        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]), productDescriptionMap);
                        } else {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                        }
                        if (newParentLevels != null) {
                            for (Leveldto newLevel : newParentLevels) {
                                if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                    productBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedProductContainer.addBean(newLevel);
                                    if (forecastLevel != newLevel.getLevelNo()) {
                                        selectedProductContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedProductContainer.setChildrenAllowed(newLevel, false);
                                    }
                                    selectedProductContainer.setParent(newLevel, selectedParent);
                                    selectedParent = newLevel;
                                }
                            }
                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                Leveldto childsParent = null;
                                for (Leveldto newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos3 != -1) {
                                        childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = tempHNo + ".";
                                    }
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                            if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                childsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }

                                    selectedProductContainer.setParent(newLevel, childsParent);
                                }
                            }

                        }

                    }
                } else {

                    if (availableProduct.getValue() != null && (DataSelectionUtil.getBeanFromId(availableProduct.getValue()).getLevelNo() > 1)) {

                        String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<String>();
                        List<Leveldto> newParentLevels = null;
                        List<Leveldto> newChildLevels = null;
                        hierarchyNos.add(hierarchyNo + ".");
                        int pos = 0;
                        String selectedParentHierarchyNo = StringUtils.EMPTY;
                        Leveldto selectedParent2 = null;
                        while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                            pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                            if (pos != -1) {
                                hierarchyNo = hierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(hierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                            for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                    selectedParent2 = selectedLevel;
                                    break;
                                }
                            }
                        }
                        newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(hierarchyNos), productDescriptionMap);
                        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]), productDescriptionMap);
                        } else {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                        }
                        if (newParentLevels != null) {
                            for (Leveldto newLevel : newParentLevels) {
                                if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                    productBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedProductContainer.addBean(newLevel);
                                    if (forecastLevel != newLevel.getLevelNo()) {
                                        selectedProductContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedProductContainer.setChildrenAllowed(newLevel, false);
                                    }
                                    selectedProductContainer.setParent(newLevel, selectedParent2);
                                    selectedParent2 = newLevel;
                                }
                            }
                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                Leveldto childsParent = null;
                                for (Leveldto newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos3 != -1) {
                                        childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = tempHNo + ".";
                                    }
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                            if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                childsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedProductContainer.setParent(newLevel, childsParent);
                                }
                            }

                        }
                    } else {
                        Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

                        String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
                        List<Leveldto> newChildLevels = null;
                        int pos = 0;
                        if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                            while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
                                pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                if (pos != -1) {
                                    hierarchyNo = hierarchyNo.substring(0, pos) + ".";
                                }
                            }
                        }
                        if (productBeanList.isEmpty() || !productBeanList.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
                            productBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
                            selectedProductContainer.addBean(selectedParent);
                            if (forecastLevel != selectedParent.getLevelNo()) {
                                selectedProductContainer.setChildrenAllowed(selectedParent, true);
                            } else {
                                selectedProductContainer.setChildrenAllowed(selectedParent, false);
                            }
                        }
                        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]), productDescriptionMap);
                        } else {
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                        }
                        if (newChildLevels != null) {
                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                Leveldto childsParent = null;
                                for (Leveldto newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
                                    if (pos3 != -1) {
                                        childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = tempHNo + ".";
                                    }
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (forecastLevel != newLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }
                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
                                            if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                childsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedProductContainer.setParent(newLevel, childsParent);
                                }
                            }
                        }
                    }
                }
                DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
                        "No Level was selected to move. Please try again.");
            }
        } catch (Exception e) { 
            LOGGER.error(e);
        }
    }

    /**
     * Search button logic.
     *
     * @param event the event
     */
    @Override
    protected void searchButtonLogic() {
        try {
            resultsContainer.removeAllItems();
            resultTable.setContainerDataSource(resultsContainer);
            if (resultsLazyContainer != null) {
                resultsLazyContainer.removeAllItems();
            }
            resultTable.removeAllItems();
            if ((StringUtils.isBlank(projectionName.getValue()) || Constant.NULL.equals(projectionName.getValue()))
                    && (StringUtils.isBlank(description.getValue()) || Constant.NULL.equals(description.getValue()))
                    //   && (selectedProductContainer.size() == 0) && (selectedCustomerContainer.size() == 0)) {
                    && (StringUtils.isBlank(productHierarchy.getValue()) || Constant.NULL.equals(productHierarchy.getValue()))
                    && (StringUtils.isBlank(customerHierarchy.getValue()) || Constant.NULL.equals(customerHierarchy.getValue()))) {
                AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(),
                        Constants.MessageConstants.NO_SEARCH_CRITERIA_BODY.getConstant());
                resultTable.setVisibleColumns(TableHeaderColumnsUtil.DATASELECTION_COLUMNS);
                resultTable.setColumnHeaders(TableHeaderColumnsUtil.DATASELECTION_HEADERS);
            } else {
                bindDataselectionDtoToSave();
                dataSelectionDTO.setSelectedCustomerRelationSid(getRelationshipSid(selectedCustomerContainer.getItemIds()));
                dataSelectionDTO.setSelectedProductRelationSid(getRelationshipSid(selectedProductContainer.getItemIds()));
                dataSelectionDTO.setModulName(screenName);
                if (tableLogic.fireSetData(dataSelectionDTO, false)) {
                    resultTable.setRefresh(Boolean.FALSE);

                    setTableHeader();
                    resultTable.setConverter("createdDateSearch", new DateToStringConverter());
                    resultTable.setConverter("modifiedDateSearch", new DateToStringConverter());
                    resultTable.setRefresh(Boolean.TRUE);
                } else {
                    setTableHeader();
                    AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.NO_RECORDS_TITLE.getConstant(),
                            "There were no records matching the search criteria.  Please try again.");
                }
            }
        } catch (Exception ex) { 
            
            LOGGER.error(ex + " searchBtn");
        }

    }

    /**
     * Delete view btn.
     *
     * @param event the event
     */
    @Override
    protected void deleteViewButtonLogic() {
        final ViewDTO dto = getViewDTO();
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                try {

                    if (dto != null) {
                        final String flag = NonMandatedLogic.deleteView(Integer.parseInt(dto.getViewId()));
                        if (!Constant.FAIL.equals(flag)) {
                            CommonUIUtils.getMessageNotification(dto.getViewName() + " has been successfully deleted.");
                        }
                        deleteViewBtn.setEnabled(false);
                        viewDTO = new ViewDTO();
                    }

                } catch (SystemException sysException) {
                    LOGGER.error(sysException);
                } catch (PortalException porException) {
                    LOGGER.error(porException);
                } catch (Exception exception) {
                    LOGGER.error(exception);
                }
            }
        }.getConfirmationMessage(Constants.MessageConstants.CONFIRM_DELETION_TITLE.getConstant(), Constants.MessageConstants.CONFIRM_DELETION_BODY.getConstant().replace("?#", dto.getViewName()));
    }

    /**
     * Edit button logic. Used to edit a projection.
     *
     * @param event the event
     */
    @Override
    protected void editButtonLogic() {
        if (resultTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Select Record", "No record was selected.  Please try again.");
        } else {
            try {
                final DataSelectionDTO dto = (DataSelectionDTO) resultTable.getValue();
                int projectionIdValue = dto.getProjectionId();
                VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
                boolean workflowFlag = false;
                Long processId = 0L;
                List<String> roleList = new ArrayList<String>();

                if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                    List list = WorkflowPersistance.selectWFInstanceInfo(projectionIdValue);

                    if (list != null && !(list.isEmpty())) {
                        processId = Long.valueOf(list.get(0).toString());
                    }
                    String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);

                    User userModel = UserLocalServiceUtil.getUser(Long.parseLong(userId));
                    workflowFlag = DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processId);
                } else {
                    workflowFlag = true;
                }
                if (workflowFlag) {
                    SessionUtil sessionUtil = new SessionUtil();
                    final SessionDTO session = sessionUtil.createSession();

                    session.setProjectionId(projectionIdValue);
                    session.setAction(Constants.CommonConstants.ACTION_EDIT.getConstant());
                    DataSelectionLogic logic = new DataSelectionLogic();
                    session.setProductDescription(logic.getLevelValueMap(dto.getProdRelationshipBuilderSid()));
                    session.setCustomerRelationId(Integer.valueOf(dto.getCustRelationshipBuilderSid()));
                    session.setProductRelationId(Integer.valueOf(dto.getProdRelationshipBuilderSid()));
                    session.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
                    session.setCustomerHierarchyId(Integer.valueOf(dto.getCustomerHierSid()));
                    session.setProductHierarchyId(Integer.valueOf(dto.getProdHierSid()));
                    session.setProcessId(processId);

                    if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(screenName)) {
                        String marketType = dataLogic.getHelperValue(StringUtils.EMPTY + projectionIdValue);
                        session.setMarketTypeValue(marketType);
                    }
                    session.setDiscount(dto.getDiscount());
                    session.setDiscountTypeId(dto.getDiscountSid());
                    session.setProductLevelNumber(dto.getProductHierarchyLevel());
                    session.setDescription(dto.getDescription());
                    relationLevelValues = new HashMap<String, String>();
                    relationLevelValues = null;
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                        relationLevelValues = dataLogic.getLevelValueMap(session.getProductRelationId());
                        session.setReturnsDetailsMap(logic.getReturnDetails(session.getProductDescription(), session, false));
                    } else {
                        session.setCustomerDescription(logic.getLevelValueMap(dto.getCustRelationshipBuilderSid()));
                        relationLevelValues = dataLogic.getLevelValueMap(session.getCustomerRelationId());
                        relationLevelValues.putAll(dataLogic.getLevelValueMap(session.getProductRelationId()));
                    }
                    if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {

                        ForecastEditWindow editWindow = new ForecastEditWindow(dto.getProjectionName(), session, resultTable, screenName, this);
                        UI.getCurrent().addWindow(editWindow);
                    } else {
                        if(!editnotif)
                        {
                            editnotif = true;
                        session.setDeductionLevel(dto.getDeductionLevel());
                        session.setDeductionValue(dto.getDeductionValue());
                        session.setIsFileNotChanged(DSLogic.getFileStatus(projectionIdValue));
                        if (!session.isFileNotChanged()) {
                            try{
                            MessageBox msg = MessageBox.showPlain(Icon.QUESTION, "Alert", "A new Customer Gross Trade Sales file has been activated since this workflow was last saved. Would you like this workflow to be updated based on the new active file?", new MessageBoxListener() {
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                     System.out.println("Edit Noificayion"+editnotif);
                                    if (buttonId.name().equals(Constant.YES)) {
                                        session.setIsNewFileCalculationNeeded(true);
                                        callARPView(dto, session);
                                        editnotif = false;
                                    } else {
                                        session.setIsNewFileCalculationNeeded(false);
                                        callARPView(dto, session);
                                        editnotif = false;
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);

                            }
                        catch(Exception e)
                        {
                           e.printStackTrace();
                        }} else {
                            session.setIsNewFileCalculationNeeded(false);
                            callARPView(dto, session);
                        }
                    }
                        }
                } else {
                    StringBuilder notiMsg = new StringBuilder("You dont have permission to edit a projection.");
                    if (roleList != null && !roleList.isEmpty()) {
                        notiMsg.append("\n Only ").append(roleList).append(" can edit a projection.");
                    }
                    NotificationUtils.getAlertNotification("Permission Denied", notiMsg.toString());
                }
            } catch (Exception ex) { 
                LOGGER.error(ex + " - in editBtn");
            }
        }
    }

    public void callARPView(DataSelectionDTO dto, SessionDTO session) {
        DSLogic dSLogic = new DSLogic();
        dSLogic.insertAccrualTemp(session.getUserId(), session.getSessionId(), "InsertAccrualTempSale", StringUtils.EMPTY + session.getProjectionId());
        AccrualRateProjectionView arpView = new AccrualRateProjectionView(StringUtils.EMPTY + dto.getProjectionId(), session, screenName, this);
        getUI().getNavigator().addView(AccrualRateProjectionView.ARP_VIEW, arpView);
        getUI().getNavigator().navigateTo(AccrualRateProjectionView.ARP_VIEW);
    }

    /**
     * View btn.
     *
     * @param event the event
     */
    @Override
    protected void viewButtonLogic() {
        if (resultTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Select Record", "No record was selected.  Please try again.");
        } else {
            try {
                DataSelectionDTO dto = (DataSelectionDTO) resultTable.getValue();
                int projectionIdValue = dto.getProjectionId();
                SessionUtil sessionUtil = new SessionUtil();
                SessionDTO session = sessionUtil.createSession();
                session.setProjectionId(projectionIdValue);
                session.setProjectionName(dto.getProjectionName());
                session.setAction(Constants.CommonConstants.ACTION_VIEW.getConstant());
                DataSelectionLogic logic = new DataSelectionLogic();
                session.setProductRelationId(Integer.valueOf(dto.getProdRelationshipBuilderSid()));
                session.setProductLevelNumber(dto.getProductHierarchyLevel());
                session.setCustomerDescription(logic.getLevelValueMap(dto.getCustRelationshipBuilderSid()));
                session.setProductDescription(logic.getLevelValueMap(dto.getProdRelationshipBuilderSid()));
                if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {

                    relationLevelValues = new HashMap<String, String>();
                    relationLevelValues = null;
                    relationLevelValues = dataLogic.getLevelValueMap(session.getProductRelationId());
                    session.setReturnsDetailsMap(logic.getReturnDetails(session.getProductDescription(), session, false));
                }
                if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
                    ForecastEditWindow editWindow = new ForecastEditWindow(dto.getProjectionName(), session, resultTable, screenName, this);
                    UI.getCurrent().addWindow(editWindow);
                } else {
                    session.setDeductionLevel(dto.getDeductionLevel());
                    session.setDeductionValue(dto.getDeductionValue());
                    session.setIsFileNotChanged(true);
                    session.setIsNewFileCalculationNeeded(false);
                    callARPView(dto, session);
                }
            } catch (Exception ex) { 
                LOGGER.error(ex + " - in View button");
            }

        }
    }

    /**
     * Delete btn.
     *
     * @param event the event
     */
    @Override
    protected void deleteButtonLogic() {
        if (resultTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Select Record", "No record was selected.  Please try again.");
            return;
        }

        final DataSelectionDTO projection = (DataSelectionDTO) resultTable.getValue();
        MessageBox.showPlain(Icon.QUESTION, "Confirm Deletion", "Are you sure you want to delete record " + projection.getProjectionName()
                + " ?", new MessageBoxListener() {
                    @Override
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals(Constant.YES)) {
                            DataSelectionLogic logic = new DataSelectionLogic();
                            if (resultTable.getValue() != null) {
                                try {
                                    String currentUserId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
                                    String flag = logic.deleteProjection(projection.getProjectionId(), currentUserId, screenName);
                                    if (!Constant.FAIL.equals(flag)) {
                                        if ("accessDenined".equals(flag)) {
                                            NotificationUtils.getErrorNotification("Cannot Delete Record", "You do not have permission to delete this projection.  It can only be deleted by the creator.");
                                            return;
                                        }
                                        resultsContainer.removeItem(projection);
                                        projectionId.setValue(null);
                                        CommonUIUtils.getMessageNotification(projection.getProjectionName() + " has been successfully deleted.");
                                    }
                                } catch (Exception ex) { 
                                    LOGGER.error(ex + " - in deleteBtn");
                                }
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

    }

    public void loadCompanyDdlb(final List<String> companySids, CompanyDdlbDto selectedCompanyDdlbDto) {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            List<CompanyDdlbDto> companyList = logic.getCompanies(companySids);
            company.removeAllItems();
            for (CompanyDdlbDto companyDto : companyList) {
                company.addItem(companyDto.getCompanyMasterSid());
                company.setItemCaption(companyDto.getCompanyMasterSid(), companyDto.getCompanyName());
            }
            if (selectedCompanyDdlbDto != null) {
                company.select(selectedCompanyDdlbDto.getCompanyMasterSid());
            }
            company.setPageLength(7);
            company.setImmediate(true);
            company.setNullSelectionAllowed(true);
            company.setInputPrompt(SELECT_ONE);
        } catch (Exception ex) { 
            LOGGER.error(ex + " in loadCompanyDdlb");
        }
    }

    @Override
    protected void companyValueChangeLogic(Object value) throws Exception {
        String dedLevel = StringUtils.EMPTY;
        String dedValue = StringUtils.EMPTY;
        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
            
            dedLevel = "Deduction Program Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "REBATE_PROGRAM_TYPE" : "Deduction Category".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "RS_CATEGORY" : "Deduction Schedule Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? Constant.RS_TYPE : StringUtils.EMPTY;
            dedValue = String.valueOf(deductionValue.getValue());
        }
        if (company.getValue() != null && innerProdLevels != null && productlevelDdlb.getValue() != null
                && !String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(productlevelDdlb.getValue())) && productRelation.getValue() != null) {
            boolean isNdc = false;

            String relationshipSid = String.valueOf(productRelation.getValue());
            int forecastLevel = 0;
            DataSelectionLogic logic = new DataSelectionLogic();
            forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()));
            Leveldto tempDto = (Leveldto) innerProdLevels.get(forecastLevel - 1);
            List<Leveldto> companyFilteredValues = new ArrayList<Leveldto>();
            List<Leveldto> tempLevelValues;
            List<Leveldto> innerLevelValues = null;
            if (tempDto.getLevel() != null && ("Item".equals(tempDto.getLevel()) || Constant.NDC.equals(tempDto.getLevel())) && Constant.ITEM_MASTER.equals(tempDto.getTableName())) {
                isNdc = true;
            } else {
                isNdc = false;
            }

            innerLevelValues = logic.loadInnerLevel(tempDto.getLevel(), productHierarchyDto.getHierarchyId(),
                    DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds()),
                    isNdc, tempDto.getFieldName(), relationshipSid, productDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo(), dedValue, dedLevel);
            List<Leveldto> filteredValues = new ArrayList<Leveldto>(innerLevelValues);

            if (company.getValue() != null && !String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(company.getValue()))
                    && Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(tempDto.getTableName())) && ("Item".equals(tempDto.getLevel()) || Constant.NDC.equals(tempDto.getLevel()))) {
                loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()), false);
                List<String> filteredItemsFromCompanyDdlb = null;
                List<Integer> itemsSidFromCompanyDdlb = logic.getItemIdFromCompanyForGlComp(Integer.parseInt(String.valueOf(company.getValue())));
                if (itemsSidFromCompanyDdlb != null && !itemsSidFromCompanyDdlb.isEmpty()) {
                    List<String> tempStringList = new ArrayList<String>(DataSelectionUtil.getItemSidFromHierarchy(getItemSidFromHierarchy()));
                    tempStringList.retainAll(UiUtils.convertIngeterListToString(itemsSidFromCompanyDdlb));
                    filteredItemsFromCompanyDdlb = new ArrayList<String>(tempStringList);
                } else {
                    filteredValues = new ArrayList<Leveldto>();
                }
                if (filteredItemsFromCompanyDdlb != null && !filteredItemsFromCompanyDdlb.isEmpty()) {
                    try {
                        tempLevelValues = availableProductContainer.getItemIds();
                        for (Leveldto leveldto : tempLevelValues) {
                            if (filteredItemsFromCompanyDdlb.contains(leveldto.getRelationshipLevelValue().trim())) {
                                companyFilteredValues.add(leveldto);
                            }
                        }
                    } catch (Exception ex) { 
                        LOGGER.error(ex + " - in company value change");
                    }
                }
                filteredValues = new ArrayList<Leveldto>(companyFilteredValues);
                availableProduct.removeAllItems();
                availableProductContainer.addAll(filteredValues);
            } else if (company.getValue() != null && String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(company.getValue()))) {
                loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()), true);
            }
        } else {
            loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()), true);
        }
    }

    public void loadCustomerLevel(final String hierarchyId, final String innerLevel) {
        LOGGER.info("Logging - loadCustomerLevel hierarchyId " + hierarchyId + " innerLevel " + innerLevel);
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY);
            customerForecastLevelContainer.removeAllItems();
            int levelNo = UiUtils.parseStringToInteger(innerLevel);
            String selectedLevelName = innerCustLevels.get(levelNo - 1).getLevel();
            for (int i = 1; i <= innerCustLevels.size(); i++) {
                String levelName = innerCustLevels.get(i - 1).getLevel();
                customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
            }
            customerLevel.setContainerDataSource(customerForecastLevelContainer);
            customerLevel.select(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
            setSelectedCustomerLevel(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
        } catch (Exception ex) { 
            LOGGER.error(ex + " in loadCustomerLevel");
        }

    }

    public void loadProductLevel(final String hierarchyId, final String innerLevel) {
        LOGGER.info("Logging - loadProductLevel hierarchyId " + hierarchyId + " innerLevel " + innerLevel);
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY);
            int levelNo = UiUtils.parseStringToInteger(innerLevel);
            String selectedLevelName = innerProdLevels.get(levelNo - 1).getLevel();
            productForecastLevelContainer.removeAllItems();
            for (int i = 1; i <= innerProdLevels.size(); i++) {
                String levelName = innerProdLevels.get(i - 1).getLevel();
                productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
            }
            productLevel.setContainerDataSource(productForecastLevelContainer);
            productLevel.select(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
            setSelectedProductLevel(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
        } catch (Exception ex) { 
            LOGGER.error(ex + " loadProductLevel");
        }
    }

    public List<Leveldto> getCustomerHierarchyEndLevels(final ExtTreeContainer<Leveldto> selectedCustomerContainer) throws Exception {
        List<Leveldto> customerHierarchyEndLevels = new ArrayList<Leveldto>();
        for (Object item : selectedCustomerContainer.getItemIds()) {
            if (!selectedCustomerContainer.hasChildren(item)) {
                customerHierarchyEndLevels.add(DataSelectionUtil.getBeanFromId(item));
            }
        }
        return customerHierarchyEndLevels;
    }

    public List<Leveldto> getProductHierarchyEndLevels(final ExtTreeContainer<Leveldto> selectedProductContainer) {
        List<Leveldto> productHierarchyEndLevels = new ArrayList<Leveldto>();
        for (Object item : selectedProductContainer.getItemIds()) {
            if (!selectedProductContainer.hasChildren(item)) {
                productHierarchyEndLevels.add(DataSelectionUtil.getBeanFromId(item));
            }
        }
        return productHierarchyEndLevels;
    }

    public String getProductHierarchyEndLevelsHierNo(final ExtTreeContainer<Leveldto> selectedProductContainer) {

        StringBuilder returnString = new StringBuilder(StringUtils.EMPTY);

        List<String> productHierarchyEndLevelsHierNo = new ArrayList<String>();
        for (Object item : selectedProductContainer.getItemIds()) {
            if (!selectedProductContainer.hasChildren(item)) {
                productHierarchyEndLevelsHierNo.add(DataSelectionUtil.getBeanFromId(item).getHierarchyNo());
            }
        }
        if (!productHierarchyEndLevelsHierNo.isEmpty()) {
            for (int loop = 0, limit = productHierarchyEndLevelsHierNo.size(); loop < limit; loop++) {
                returnString.append("'");
                returnString.append(productHierarchyEndLevelsHierNo.get(loop));
                returnString.append("'");
                if (loop != (limit - 1)) {
                    returnString.append(", ");
                }
            }
        }
        return returnString.toString();
    }

    private List<String> getRelationshipSid(List<Leveldto> leveldtos) {
        List<String> relationshipSids = new ArrayList<String>();
        for (Leveldto dto : leveldtos) {
            relationshipSids.add(String.valueOf(dto.getRelationshipLevelSid()));
        }
        return relationshipSids;
    }

    /**
     * Load customer hierarchy.
     *
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void initializeCustomerHierarchy(final int projectionId, String customerLevel) throws Exception {
        DataSelectionLogic logic = new DataSelectionLogic();

        int forecastLevel = 0;
        forecastLevel = UiUtils.parseStringToInteger(customerLevel);
        List<Leveldto> reslistOne;
        reslistOne = logic.getRelationShipValues(projectionId, "customer", customerLevel, customerDescriptionMap);
        for (Leveldto dto : reslistOne) {
            if (dto.getLevelNo() == 1) {
                selectedCustomerContainer.removeAllItems();
                selectedCustomerContainer.addItem(dto);
                selectedCustomerContainer.setChildrenAllowed(dto, true);
            } else {
                for (Object tempdto : selectedCustomerContainer.getItemIds()) {
                    if (dto.getParentNode().contains("~")) {
                        String parentarr[] = dto.getParentNode().split("~");
                        String parentName = parentarr[1];
                        if (DataSelectionUtil.getBeanFromId(tempdto).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
                            selectedCustomerContainer.addBean(dto);
                            if (forecastLevel != dto.getLevelNo()) {
                                selectedCustomerContainer.setChildrenAllowed(dto, true);
                            } else {
                                selectedCustomerContainer.setChildrenAllowed(dto, false);
                            }
                            selectedCustomerContainer.setParent(dto, tempdto);
                            break;
                        }
                    } else {
                        selectedCustomerContainer.addBean(dto);
                        if (forecastLevel != dto.getLevelNo()) {
                            selectedCustomerContainer.setChildrenAllowed(dto, true);
                        } else {
                            selectedCustomerContainer.setChildrenAllowed(dto, false);
                        }
                        selectedCustomerContainer.setParent(dto, tempdto);
                        break;
                    }
                }
            }
        }
        selectedCustomer.setContainerDataSource(selectedCustomerContainer);
        selectedCustomer.setVisibleColumns(new Object[]{"displayValue"});
        selectedCustomer.setColumnHeaders(new String[]{"Customer Hierarchy Group Builder"});
        for (Leveldto ddo : selectedCustomerContainer.getItemIds()) {
            selectedCustomer.setCollapsed(ddo, false);
        }
    }

    /**
     * Load customer hierarchy.
     *
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void initializeProductHierarchy(final int projectionId, String productLevel) throws Exception {
        DataSelectionLogic logic = new DataSelectionLogic();

        int forecastLevel = 0;
        forecastLevel = UiUtils.parseStringToInteger(productLevel);
        List<Leveldto> reslistOne;
        reslistOne = logic.getRelationShipValues(projectionId, "product", productLevel, productDescriptionMap);
        for (Leveldto dto : reslistOne) {
            if (dto.getLevelNo() == 1) {
                selectedProductContainer.removeAllItems();
                selectedProductContainer.addItem(dto);
                selectedProductContainer.setChildrenAllowed(dto, true);
            } else {
                for (Object tempdto : selectedProductContainer.getItemIds()) {
                    if (dto.getParentNode().contains("~")) {
                        String parentarr[] = dto.getParentNode().split("~");
                        String parentName = parentarr[1];
                        if (DataSelectionUtil.getBeanFromId(tempdto).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
                            selectedProductContainer.addBean(dto);
                            if (forecastLevel != dto.getLevelNo()) {
                                selectedProductContainer.setChildrenAllowed(dto, true);
                            } else {
                                selectedProductContainer.setChildrenAllowed(dto, false);
                            }
                            selectedProductContainer.setParent(dto, tempdto);
                            break;
                        }
                    } else {
                        selectedProductContainer.addBean(dto);
                        if (forecastLevel != dto.getLevelNo()) {
                            selectedProductContainer.setChildrenAllowed(dto, true);
                        } else {
                            selectedProductContainer.setChildrenAllowed(dto, false);
                        }
                        selectedProductContainer.setParent(dto, tempdto);
                        break;
                    }
                }
            }
        }
        selectedProduct.setContainerDataSource(selectedProductContainer);
        selectedProduct.setVisibleColumns(new Object[]{"displayValue"});
        selectedProduct.setColumnHeaders(new String[]{"Product Hierarchy Group Builder"});
        for (Leveldto ddo : selectedProductContainer.getItemIds()) {
            selectedProduct.setCollapsed(ddo, false);
        }
    }

    private void initializeCompanyDdlb() {
        DataSelectionLogic logic = new DataSelectionLogic();
        int hierarchyId = 0;
        if (productHierarchyDto == null) {
            hierarchyId = UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid());
        } else {
            hierarchyId = productHierarchyDto.getHierarchyId();
        }
        if (productHierarchyDto == null || (productHierarchyDto != null && innerProdLevels.isEmpty())) {
            innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
        }
        if (innerProdLevels != null && !innerProdLevels.isEmpty()) {
        }
        try {
            CompanyDdlbDto selectedCompanyDdlbDto = null;
            if (!StringUtils.isBlank(dataSelectionDTO.getCompanySid()) && !Constants.CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getCompanySid())) {
                selectedCompanyDdlbDto = new CompanyDdlbDto();
                selectedCompanyDdlbDto.setCompanyMasterSid(UiUtils.parseStringToInteger(dataSelectionDTO.getCompanySid()));
            }
            loadCompanyDdlb(DataSelectionUtil.getCompanySidFromHierarchy(getCompanySidFromHierarchy(false, false), screenName), selectedCompanyDdlbDto);

        } catch (Exception ex) { 
            LOGGER.error(ex + " - in initializeCompanyDdlb");
        }
    }

    private void setRelationshipBuilderSids(String rbSid) {
        relationshipBuilderSids.add(rbSid);
    }

    private void addValidations() {
        TextFieldConverter trimmer = new TextFieldConverter();
        projectionName.setMaxLength(Constants.LengthConstants.LENGTH_200.getConstant());
        privateView.setMaxLength(Constants.LengthConstants.LENGTH_200.getConstant());
        publicView.setMaxLength(Constants.LengthConstants.LENGTH_200.getConstant());
        customerHierarchy.setMaxLength(Constants.LengthConstants.LENGTH_200.getConstant());
        productHierarchy.setMaxLength(Constants.LengthConstants.LENGTH_200.getConstant());
        description.setMaxLength(Constants.LengthConstants.LENGTH_500.getConstant());
        projectionName.setConverter(trimmer);
        description.setConverter(trimmer);
        privateView.setConverter(trimmer);
        publicView.setConverter(trimmer);
        customerHierarchy.setConverter(trimmer);
        productHierarchy.setConverter(trimmer);
        customerGroup.setConverter(trimmer);
        productGroup.setConverter(trimmer);

    }

    private void loadInnerCustomerLevel(int forecastLevel, int innerLevel, int hierarchyId) {
        LOGGER.info("Logging - loadInnerCustomerLevel forecastLevel " + forecastLevel + " innerLevel " + innerLevel + " hierarchyId " + hierarchyId);
        customerInnerLevelContainer.removeAllItems();
        String selectedLevelName = StringUtils.EMPTY;
        for (int i = 1; i <= forecastLevel; i++) {
            String levelName = innerCustLevels.get(i - 1).getLevel();
            customerInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
            if (i == innerLevel) {
                selectedLevelName = levelName;
            }
        }

        level.setContainerDataSource(customerInnerLevelContainer);
        level.select(Constant.LEVEL + innerLevel + " - " + selectedLevelName);
    }

    private void loadInnerProductLevel(int forecastLevel, int innerLevel, int hierarchyId) {
        LOGGER.info("Logging - loadInnerProductLevel forecastLevel " + forecastLevel + " innerLevel " + innerLevel + " hierarchyId " + hierarchyId);
        productInnerLevelContainer.removeAllItems();
        String selectedLevelName = StringUtils.EMPTY;
        for (int i = 1; i <= forecastLevel; i++) {
            String levelName = innerProdLevels.get(i - 1).getLevel();
            productInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
            if (i == innerLevel) {
                selectedLevelName = levelName;
            }
        }
        productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
        productlevelDdlb.select(Constant.LEVEL + innerLevel + " - " + selectedLevelName);
    }

    public String getSelectedCustomerLevel() {
        return selectedCustomerLevel;
    }

    public void setSelectedCustomerLevel(String selectedCustomerLevel) {
        this.selectedCustomerLevel = selectedCustomerLevel;
    }

    public String getSelectedProductLevel() {
        return selectedProductLevel;
    }

    public void setSelectedProductLevel(String selectedProductLevel) {
        this.selectedProductLevel = selectedProductLevel;
    }

    private void filterCustomerOnGroupSelect() {
        LOGGER.info("customer filterCustomerOnGroupSelect");
        availableCustomerContainer.removeAllItems();
        String levelName = "Level";
        try {
            int forecastLevel = 0;
            boolean isNdc = false;
            List<Leveldto> custVlues = null;
            if (level.getValue() != null && customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) {
                String selectedLevel = String.valueOf(level.getValue());

                if (customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) {

                    String relationshipSid = String.valueOf(customerRelation.getValue());
                    DataSelectionLogic logic = new DataSelectionLogic();
                    String val[] = selectedLevel.split(" ");
                    forecastLevel = Integer.parseInt(val[1]);
                    Leveldto tempDto = (Leveldto) innerCustLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        levelName = tempDto.getLevel();
                    }
                    if (tempDto.getLevel() != null && (Constant.NDC.equalsIgnoreCase(tempDto.getLevel()) || "Item".equalsIgnoreCase(tempDto.getLevel())) && Constant.ITEM_MASTER.equals(tempDto.getTableName())) {
                        isNdc = true;
                    } else {
                        isNdc = false;
                    }

                    custVlues = logic.loadInnerLevel(tempDto.getLevel(), customerHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()), isNdc,
                            tempDto.getFieldName(), relationshipSid, customerDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY);

                    if (groupFilteredCompanies != null && Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(tempDto.getTableName()))
                            && (Constant.TRADING_PARTNER.equals(levelName) || Constant.COMPANY_SMALL.equals(levelName))) {
                        List<Leveldto> filteredValues = new ArrayList<Leveldto>();
                        if (!groupFilteredCompanies.isEmpty()) {
                            try {
                                for (Leveldto leveldto : custVlues) {
                                    if (groupFilteredCompanies.contains(leveldto.getRelationshipLevelValue().trim())) {
                                        filteredValues.add(leveldto);
                                    }
                                }
                            } catch (Exception ex) { 
                                
                                LOGGER.error(ex + " level ValueChangeListener ");
                            }

                        }
                        availableCustomerContainer.addAll(filteredValues);
                    } else {
                        availableCustomerContainer.addAll(custVlues);
                    }

                }
            }
            availableCustomer.setContainerDataSource(availableCustomerContainer);
            availableCustomer.setVisibleColumns(new Object[]{"displayValue"});
            availableCustomer.setColumnHeaders(new String[]{levelName});
            availableCustomer.setFilterBarVisible(true);
            availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
            availableCustomer.setStyleName("filtertable");
        } catch (Exception ex) { 
            LOGGER.error(ex + " filterCustomerOnGroupSelect ");
        }
    }

    /**
     * Manual trigger and processing of customer group select button logic Code
     * based on customer group select button logic Any change made there, should
     * be made here accordingly
     *
     * @param customerGrpSid
     */
    public void triggerCustGrpOnView(String customerGrpSid, final int hierarchyId, final boolean filter) {

        List<String> companySids = null;
        List<String> customerSidsFromDetails = null;
        DataSelectionLogic logic = new DataSelectionLogic();
        try {
            if (!StringUtils.isBlank(customerGrpSid) && !NULL.equalsIgnoreCase(customerGrpSid)) {
                companySids = DataSelectionUtil.getCustomerSidFromHierarchy(getCustomersFromHierarchy());
                if (companySids != null) {
                    List<String> finalCompanySids = new ArrayList<String>(companySids);
                    customerSidsFromDetails = logic.getCustomerGroupDetails(Integer.parseInt(customerGrpSid));
                    finalCompanySids.retainAll(customerSidsFromDetails);
                    groupFilteredCompanies = new ArrayList<String>(finalCompanySids);
                }
                if (filter) {
                    filterCustomerOnGroupSelect();
                }
            }
        } catch (Exception ex) { 
            LOGGER.error(ex + " at triggerCustGrpOnView");
        }
    }

    /**
     * Manual trigger and processing of product group select button logic Code
     * based on product group select button logic Any change made there, should
     * be made here accordingly
     *
     * @param productGrpSid
     */
    public void triggerProdGrpOnView(String productGrpSid, final String hierarchyId, final boolean filter) {
        try {
            if (!StringUtils.isBlank(productGrpSid) && !NULL.equalsIgnoreCase(productGrpSid)) {
                List<String> itemSids = null;
                List<String> itemSidsFromDetails = null;
                DataSelectionLogic logic = new DataSelectionLogic();
                itemSids = DataSelectionUtil.getItemSidFromHierarchy(getItemSidFromHierarchy());
                if (itemSids != null) {
                    List<String> finalItemSids = new ArrayList<String>(itemSids);
                    itemSidsFromDetails = logic.getItemGroupDetails(Integer.parseInt(productGrpSid));
                    finalItemSids.retainAll(itemSidsFromDetails);
                    groupFilteredItems = new ArrayList<String>(finalItemSids);
                }
                if (filter && productlevelDdlb.getValue() != null && !String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(productlevelDdlb.getValue()))) {
                    loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()), false);
                }
            }
        } catch (Exception ex) { 
            LOGGER.error(ex + " at triggerProdGrpOnView");
        }
    }

    private List<Leveldto> getItemSidFromHierarchy() {
        List<Leveldto> innerLevelValues = null;
        String dedLevel = StringUtils.EMPTY;
        String dedValue = StringUtils.EMPTY;
        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
            dedLevel = "Deduction Program Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "REBATE_PROGRAM_TYPE" : "Deduction Category".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "RS_CATEGORY" : "Deduction Schedule Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? Constant.RS_TYPE : StringUtils.EMPTY;
            dedValue = String.valueOf(deductionValue.getValue());
        }

        try {

            if ((productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) && (innerProdLevels != null)) {

                String relationshipSid = String.valueOf(productRelation.getValue());
                DataSelectionLogic logic = new DataSelectionLogic();
                Leveldto ndcLevel = null;
                for (Leveldto dto : innerProdLevels) {
                    if (Constant.NDC.equalsIgnoreCase(dto.getLevel()) || "Item".equalsIgnoreCase(dto.getLevel())) {
                        ndcLevel = dto;
                        break;
                    }
                }
                if (ndcLevel != null) {

                    innerLevelValues = logic.loadInnerLevel(ndcLevel.getLevel(), productHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds()), true, ndcLevel.getFieldName(),
                            relationshipSid, productDescriptionMap, INDICATOR_LEVEL_NDC.getConstant(), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, ndcLevel.getLevelNo(), dedValue, dedLevel);
                }
            }
        } catch (Exception ex) { 
            LOGGER.error(ex + " in getItemSidFromHierarchy");
        }
        return innerLevelValues;
    }

    private List<Leveldto> getCustomersFromHierarchy() {
        List<Leveldto> innerLevelValues = null;
        try {

            if ((customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) && (innerCustLevels != null)) {
                String relationshipSid = String.valueOf(customerRelation.getValue());
                DataSelectionLogic logic = new DataSelectionLogic();
                Leveldto customerLevelDto = null;
                for (Leveldto dto : innerCustLevels) {
                    if (Constant.CUSTOMER_SMALL.equalsIgnoreCase(dto.getLevel()) || Constant.COMPANY_SMALL.equalsIgnoreCase(dto.getLevel()) || Constant.TRADING_PARTNER.equalsIgnoreCase(dto.getLevel())) {
                        customerLevelDto = dto;
                        break;
                    }
                }
                if (customerLevelDto != null) {

                    innerLevelValues = logic.loadInnerLevel(customerLevelDto.getLevel(), customerHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()),
                            false, customerLevelDto.getFieldName(), relationshipSid, customerDescriptionMap, INDICATOR_LEVEL_CUSTOMER.getConstant(), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, customerLevelDto.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY);
                }
            }
        } catch (Exception ex) { 
            LOGGER.error(ex + " in getCustomersFromHierarchy");
        }
        return innerLevelValues;
    }

    public List<Leveldto> getCompanySidFromHierarchy(final boolean loadFromSelected, final boolean dsTabflag) {
        List<Leveldto> innerLevelValues = null;
        String dedLevel = StringUtils.EMPTY;
        String dedValue = StringUtils.EMPTY;
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                dedLevel = "Deduction Program Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "REBATE_PROGRAM_TYPE" : "Deduction Category".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "RS_CATEGORY" : "Deduction Schedule Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? Constant.RS_TYPE : StringUtils.EMPTY;
                dedValue = String.valueOf(deductionValue.getValue());
            }

            if (dsTabflag) {
                int hierarchyId = 0;
                if (productHierarchyDto == null) {
                    hierarchyId = UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid());
                } else {
                    hierarchyId = productHierarchyDto.getHierarchyId();
                }
                if (innerProdLevels == null || innerProdLevels.isEmpty() || productHierarchyDto == null) {
                    innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
                }

            }
            if ((productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) && (innerProdLevels != null)) {
                String relationshipSid = String.valueOf(productRelation.getValue());
                Leveldto companyLevel = null;
                for (Leveldto dto : innerProdLevels) {
                    if (Constant.COMPANY_SMALL.equalsIgnoreCase(dto.getLevel()) || "GL Comp".contains(dto.getLevel()) || "GL Company".contains(dto.getLevel())) {
                        companyLevel = dto;
                        break;
                    }
                }
                List<Integer> selectedLevelSids = null;
                if (loadFromSelected) {
                    selectedLevelSids = DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds());
                }

                if (companyLevel != null) {

                    innerLevelValues = logic.loadInnerLevel(companyLevel.getLevel(), productHierarchyDto.getHierarchyId(),
                            selectedLevelSids, false, companyLevel.getFieldName(), relationshipSid, productDescriptionMap, StringUtils.EMPTY, screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, companyLevel.getLevelNo(), dedValue, dedLevel);
                }
            }

        } catch (Exception ex) { 
            LOGGER.error(ex + " in getCompanySidFromHierarchy");
        }
        return innerLevelValues;
    }

    public static void loadRelationDdlb(final int hierarchyDefinitionSid, final RelationshipDdlbDto selectedRelationshipDdlbDto, final ComboBox relationship) {
        LOGGER.info("Logging - loadRelationDdlb hierarchyDefinitionSid " + hierarchyDefinitionSid);
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            List<RelationshipDdlbDto> relationshipSidList = logic.getRelationshipSid(hierarchyDefinitionSid);
            relationship.removeAllItems();
            for (RelationshipDdlbDto relationshipDto : relationshipSidList) {
                relationship.addItem(relationshipDto.getRelationshipBuilderSid());
                relationship.setItemCaption(relationshipDto.getRelationshipBuilderSid(), relationshipDto.getRelationshipName());
            }
            if (selectedRelationshipDdlbDto != null) {
                relationship.select(selectedRelationshipDdlbDto.getRelationshipBuilderSid());
            }
            relationship.setPageLength(7);
            relationship.setImmediate(true);
            relationship.setNullSelectionAllowed(true);
            relationship.setInputPrompt(SELECT_ONE);
        } catch (Exception ex) { 
            LOGGER.error(ex);
        }
    }

    @Override
    protected void productLevelDdlbValueChange(String selectedLevel, boolean flag) {
        loadFilteredProductSelection(selectedLevel, false);
    }

    @Override
    protected void customerHierarchyLookUp() {
        final HierarchyLookup customerHierarchyLookupWindow = new HierarchyLookup(INDICATOR_CUSTOMER_HIERARCHY.getConstant(), WINDOW_CUSTOMER_HIERARCHY_LOOKUP.getConstant(), customerHierarchy, customerHierarchyDto);
        UI.getCurrent().addWindow(customerHierarchyLookupWindow);
        customerHierarchyLookupWindow.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customerHierarchyLookupWindow.getHierarchyDto() != null) {
                    final HierarchyLookupDTO lookupDto = customerHierarchyLookupWindow.getHierarchyDto();
                    customerHierarchyDto = lookupDto;
                    DataSelectionLogic logic = new DataSelectionLogic();
                    innerCustLevels = logic.loadCustomerForecastLevel(lookupDto.getHierarchyId(), lookupDto.getHierarchyName());
                    loadRelationDdlb(customerHierarchyDto.getHierarchyId(), null, customerRelation);
                    customerForecastLevelContainer.removeAllItems();
                    for (int i = 1; i <= innerCustLevels.size(); i++) {
                        String levelName = innerCustLevels.get(i - 1).getLevel();
                        customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
                    }
                    resetCustomerLevel();
                    resetSecondCustomerLevel();
                    availableCustomer.removeAllItems();
                    availableCustomerContainer.removeAllItems();
                    selectedCustomer.removeAllItems();
                    selectedCustomerContainer.removeAllItems();
                    customerGroup.setValue(StringUtils.EMPTY);
                    dataSelectionDTO.setCustomerGrpSid(null);
                    groupFilteredCompanies = null;
                    customerLevel.setContainerDataSource(customerForecastLevelContainer);
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                        level.setContainerDataSource(customerForecastLevelContainer);
                    }
                    setCustomerForecastLevelNullSelection();
                    setCustomerRelationNullSelection();
                    dataSelectionDTO.setCustomerHierSid(String.valueOf(customerHierarchyDto.getHierarchyId()));
                }
            }
        }
        );
    }

    @Override
    protected void productHierarchyLookUp() {
        final HierarchyLookup productHierarchyLookupWindow = new HierarchyLookup(INDICATOR_PRODUCT_HIERARCHY.getConstant(), WINDOW_PRODUCT_HIERARCHY_LOOKUP.getConstant(), productHierarchy, productHierarchyDto);
        UI.getCurrent().addWindow(productHierarchyLookupWindow);
        productHierarchyLookupWindow.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (productHierarchyLookupWindow.getHierarchyDto() != null) {
                    final HierarchyLookupDTO lookupDto = productHierarchyLookupWindow.getHierarchyDto();
                    DataSelectionLogic logic = new DataSelectionLogic();
                    productHierarchyDto = lookupDto;
                    innerProdLevels = logic.loadCustomerForecastLevel(lookupDto.getHierarchyId(), lookupDto.getHierarchyName());
                    productForecastLevelContainer.removeAllItems();
                    for (int i = 1; i <= innerProdLevels.size(); i++) {
                        String levelName = innerProdLevels.get(i - 1).getLevel();
                        productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
                    }
                    companiesInProdHier = new ArrayList<String>();
                    loadRelationDdlb(productHierarchyDto.getHierarchyId(), null, productRelation);
                    loadCompanyLogic();
                    resetProductLevel();
                    resetSecondProductLevel();
                    setProductForecastLevelNullSelection();
                    setProductRelationNullSelection();
                    availableProduct.removeAllItems();
                    availableProductContainer.removeAllItems();
                    selectedProduct.removeAllItems();
                    selectedProductContainer.removeAllItems();
                    dataSelectionDTO.setProdGrpSid(null);
                    productGroup.setValue(StringUtils.EMPTY);
                    groupFilteredItems = null;
                    productLevel.setContainerDataSource(productForecastLevelContainer);
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                        productlevelDdlb.setContainerDataSource(productForecastLevelContainer);
                    }
                }
            }
        }
        );
    }

    @Override
    protected void modeOptionChange(boolean isAddMode) {
        try {
//            if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
            if (isAddMode) {
                DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), screenName);
            } else {
                DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_SEARCH.getConstant(), screenName);
            }
//            }
        } catch (Exception ex) { 
            LOGGER.error(ex);
        }
    }

    @Override
    protected void customerLevelValueChange(Property.ValueChangeEvent event, boolean flag) {
        LOGGER.info("Logging - customerLevelValueChange");
        customerInnerLevelContainer.removeAllItems();
        if (event.getProperty().getValue() != null && !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
            String selectedLevel = String.valueOf(event.getProperty().getValue());
            setSelectedCustomerLevel(selectedLevel);
            DataSelectionLogic logic = new DataSelectionLogic();
            logic.loadCustomerForecastLevel(customerHierarchyDto.getHierarchyId(), customerHierarchyDto.getHierarchyName());
            String val[] = selectedLevel.split(" ");
            int forecastLevel = Integer.parseInt(val[1]);
            customerInnerLevelContainer.removeAllItems();
            selectedCustomer.removeAllItems();
            selectedCustomerContainer.removeAllItems();
            customerBeanList.removeAll(customerBeanList);
            for (int i = 1; i <= forecastLevel; i++) {
                String levelName = innerCustLevels.get(i - 1).getLevel();
                customerInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
            }
            level.setContainerDataSource(customerInnerLevelContainer);
            setCustomerLevelNullSelection();
        } else if (event.getProperty().getValue() == null || (event.getProperty().getValue() != null && SELECT_ONE.equals(String.valueOf(event.getProperty().getValue())))) {
            customerInnerLevelContainer.removeAllItems();
            availableCustomer.removeAllItems();
            availableCustomerContainer.removeAllItems();
            selectedCustomer.removeAllItems();
            selectedCustomerContainer.removeAllItems();
            setCustomerLevelNullSelection();
            customerBeanList.removeAll(customerBeanList);
        }
    }

    @Override
    protected void levelValueChangeListener(Object value) {
        LOGGER.info("customer inner Level - ValueChangeListener  " + value);
        availableCustomerContainer.removeAllItems();
        String levelName = "Level";
        int levelNo = 0;
        String tempConditionCheck;
        try {
            int forecastLevel = 0;
            boolean isNdc = false;
            List<Leveldto> custVlues = null;
            if (value != null && customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) {
                String selectedLevel = String.valueOf(value);
                if (customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) {
                    String dedLevel = StringUtils.EMPTY;
                    String dedValue = StringUtils.EMPTY;
                    String relationshipSid = String.valueOf(customerRelation.getValue());
                    DataSelectionLogic logic = new DataSelectionLogic();
                    String val[] = selectedLevel.split(" ");
                    forecastLevel = Integer.parseInt(val[1]);
                    Leveldto tempDto = (Leveldto) innerCustLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        levelName = tempDto.getLevel();
                        levelNo = tempDto.getLevelNo();
                    }

                    if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS)) {
                        tempConditionCheck = "NDC 11";
                    } else {
                        tempConditionCheck = Constant.NDC;
                    }

                    if (tempDto.getLevel() != null && tempConditionCheck.equalsIgnoreCase(tempDto.getLevel()) && Constant.ITEM_MASTER.equals(tempDto.getTableName())) {
                        isNdc = true;
                    } else {
                        isNdc = false;
                    }
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                        dedLevel = "Deduction Program Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "REBATE_PROGRAM_TYPE" : "Deduction Category".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? "RS_CATEGORY" : "Deduction Schedule Type".equalsIgnoreCase(String.valueOf(deductionLevel.getValue())) ? Constant.RS_TYPE : StringUtils.EMPTY;
                        dedValue = String.valueOf(deductionValue.getValue());
                    }

                    ExtTreeContainer<Leveldto> dumbyCustomerContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);
                    custVlues = logic.loadInnerLevel(tempDto.getLevel(), customerHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(dumbyCustomerContainer.getItemIds()), isNdc, tempDto.getFieldName(), relationshipSid, customerDescriptionMap,
                            DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, levelNo, dedValue, dedLevel);
                    if (groupFilteredCompanies != null && Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(tempDto.getTableName()))
                            && (Constant.TRADING_PARTNER.equals(levelName) || Constant.COMPANY_SMALL.equals(levelName))) {
                        List<Leveldto> filteredValues = new ArrayList<Leveldto>();
                        if (!groupFilteredCompanies.isEmpty()) {
                            try {
                                for (Leveldto leveldto : custVlues) {
                                    if (groupFilteredCompanies.contains(leveldto.getRelationshipLevelValue().trim())) {
                                        filteredValues.add(leveldto);
                                    }
                                }
                            } catch (Exception ex) { 
                                LOGGER.error(ex + " level ValueChangeListener ");
                            }
                        }
                        availableCustomerContainer.addAll(filteredValues);
                    } else {
                        availableCustomerContainer.addAll(custVlues);
                    }

                }
            }
            availableCustomer.setContainerDataSource(availableCustomerContainer);
            availableCustomer.setVisibleColumns(new Object[]{"displayValue"});
            availableCustomer.setColumnHeaders(new String[]{levelName});
            availableCustomer.setFilterBarVisible(true);
            availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
            availableCustomer.setStyleName("filtertable");
        } catch (Exception ex) { 
            LOGGER.error(ex + " level ValueChangeListener ");
        }
    }

    @Override
    protected void productLevelValueChange(Object value, boolean flag) {
        LOGGER.info("Logging - productLevelValueChange");
        productInnerLevelContainer.removeAllItems();
        if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
            String selectedLevel = String.valueOf(value);
            setSelectedProductLevel(selectedLevel);
            DataSelectionLogic logic = new DataSelectionLogic();
            logic.loadCustomerForecastLevel(productHierarchyDto.getHierarchyId(), productHierarchyDto.getHierarchyName());
            String val[] = selectedLevel.split(" ");
            int forecastLevel = Integer.parseInt(val[1]);
            productInnerLevelContainer.removeAllItems();
            selectedProduct.removeAllItems();
            selectedProductContainer.removeAllItems();
            productBeanList.removeAll(productBeanList);
            for (int i = 1; i <= forecastLevel; i++) {
                String levelName = innerProdLevels.get(i - 1).getLevel();
                productInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
            }
            productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
            setProductLevelNullSelection();
        } else if (value == null || (value != null && SELECT_ONE.equals(String.valueOf(value)))) {
            productInnerLevelContainer.removeAllItems();
            availableProduct.removeAllItems();
            availableProductContainer.removeAllItems();
            selectedProduct.removeAllItems();
            selectedProductContainer.removeAllItems();
            productBeanList.removeAll(productBeanList);
            setProductLevelNullSelection();
        }
    }

    @Override
    protected void customerRelationValueChange(Object value) {
        if (value != null) {
            try {
                DataSelectionLogic logic = new DataSelectionLogic();
                availableCustomer.removeAllItems();
                availableCustomerContainer.removeAllItems();
                selectedCustomer.removeAllItems();
                selectedCustomerContainer.removeAllItems();
                setCustomerForecastLevelNullSelection();
                setCustomerLevelNullSelection();
                setRelationshipBuilderSids(String.valueOf(customerRelation.getValue()));
                logic.insertToCcpMap(relationshipBuilderSids, screenName);
                customerDescriptionMap = logic.getLevelValueMap(String.valueOf(customerRelation.getValue()));
                if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName) && innerCustLevels.size()>0) {
                    customerForecastLevelContainer.removeAllItems();
                    for (int i = 1; i <= innerCustLevels.size(); i++) {
                        String levelName = innerCustLevels.get(i - 1).getLevel();
                        customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
                    }
                    level.setContainerDataSource(customerForecastLevelContainer);
                }
            } catch (Exception ex) { 
                LOGGER.error(ex + " in customerRelation value change");
            }
        } else if (value == null || (value != null && SELECT_ONE.equals(String.valueOf(value)))) {
            availableCustomer.removeAllItems();
            availableCustomerContainer.removeAllItems();
            selectedCustomer.removeAllItems();
            selectedCustomerContainer.removeAllItems();
            customerInnerLevelContainer.removeAllItems();
            setCustomerForecastLevelNullSelection();
            setCustomerLevelNullSelection();
            customerDescriptionMap = null;
        }
        customerGroup.setValue(StringUtils.EMPTY);
        selectedCustomerGroupDTO = new GroupDTO();
        dataSelectionDTO.setCustomerGrpSid(null);
        groupFilteredCompanies = null;
    }

    @Override
    protected void productRelationValueChange(Object value) {
        LOGGER.info("productRelation - ValueChangeListener " + value);
        if (value != null) {
            try {
                DataSelectionLogic logic = new DataSelectionLogic();
                selectedProduct.removeAllItems();
                selectedProductContainer.removeAllItems();
                availableProduct.removeAllItems();
                availableProductContainer.removeAllItems();
                setProductForecastLevelNullSelection();
                setProductLevelNullSelection();
                setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
                logic.insertToCcpMap(relationshipBuilderSids, screenName);
                productDescriptionMap = logic.getLevelValueMap(String.valueOf(productRelation.getValue()));
                loadCompanyLogic();
                if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName) && innerProdLevels.size()>0) {
                    productForecastLevelContainer.removeAllItems();
                    for (int i = 1; i <= innerProdLevels.size(); i++) {
                        String levelName = innerProdLevels.get(i - 1).getLevel();
                        productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
                    }
                    productlevelDdlb.setContainerDataSource(productForecastLevelContainer);
                }
                
            } catch (Exception ex) { 
                LOGGER.error(ex + " in productRelation value change");
            }
        } else if (value == null || (value != null && SELECT_ONE.equals(String.valueOf(value)))) {
            selectedProduct.removeAllItems();
            selectedProductContainer.removeAllItems();
            availableProduct.removeAllItems();
            availableProductContainer.removeAllItems();
            productInnerLevelContainer.removeAllItems();
            setProductForecastLevelNullSelection();
            setProductLevelNullSelection();
            productDescriptionMap = null;
        }
        dataSelectionDTO.setProdGrpSid(null);
        productGroup.setValue(StringUtils.EMPTY);
        selectedProductGroupDTO = new GroupDTO();
        groupFilteredItems = null;
    }

    @Override
    protected void generateButtonLogic() {
        LOGGER.info("generateBtn click listener started " + screenName);
        try {
            setPrivateViewName(privateView.getValue());
            setPublicViewName(publicView.getValue());
            String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
            List<String> roleList = new ArrayList<String>();
            ProcessInstance processInstance = DSCalculationLogic.startWorkflow();
            User userModel = UserLocalServiceUtil.getUser(Long.parseLong(userId));
            boolean workflowFlag = DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processInstance.getId());
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                NonMandatedLogic nmLogic = new NonMandatedLogic();
                DataSelectionLogic dsLogic = new DataSelectionLogic();

                try {
                    if (selectedCustomer.size() <= 0 || selectedProduct.size() <= 0 || fromPeriod.getValue() == null || StringUtils.isBlank(projectionName.getValue()) || Constants.CommonConstants.NULL.getConstant().equalsIgnoreCase(projectionName.getValue())
                            || company.getValue() == null || SELECT_ONE.equals(company.getValue())) {
                        AbstractNotificationUtils.getErrorNotification("Selection Criteria",
                                "Not all required fields have been populated. Please try again.");
                        return;
                    }
                    if (workflowFlag) {
                        bindDataselectionDtoToSave();
                        int projectionIdValue = nmLogic.saveProjection(dataSelectionDTO, screenName);
                        VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
                        projectionId.setValue(String.valueOf(projectionIdValue));
                        dataSelectionDTO.setProjectionId(projectionIdValue);
                        List<Leveldto> customerList = selectedCustomerContainer.getItemIds();
                        List<Leveldto> productList = selectedProductContainer.getItemIds();
                        List<String> customerListEndSids = DataSelectionUtil.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
                        List<String> productListEndSids = DataSelectionUtil.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
                        setRelationshipBuilderSids(String.valueOf(customerRelation.getValue()));
                        setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
                        dsLogic.saveCustomerHierarchyLogic(customerList, customerListEndSids, projectionIdValue, null, Constant.SAVE);
                        dsLogic.saveProductHierarchyLogic(productList, productListEndSids, projectionIdValue, null, Constant.SAVE);

                        dsLogic.saveCcp(getCustomerHierarchyEndLevels(selectedCustomerContainer), getProductHierarchyEndLevelsHierNo(selectedProductContainer), "customer", String.valueOf(projectionIdValue));
                        if (projectionIdValue != 0) {
                            Long processInstanceId = processInstance.getId();
                            try {
                                TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, projectionIdValue, processInstanceId);
                                processInstanceId = taskSummary.getProcessInstanceId();
                            } catch (Exception e) { 
                                LOGGER.error(e);
                            }
                            SessionUtil sessionUtil = new SessionUtil();
                            SessionDTO session = sessionUtil.createSession();
                            session.setProjectionId(projectionIdValue);
                            session.setAction(Constants.CommonConstants.ACTION_GENERATE.getConstant());
                            session.setCustomerDescription(customerDescriptionMap);
                            session.setProductDescription(productDescriptionMap);
                            session.setProcessId(processInstanceId);
                            relationLevelValues = new HashMap<String, String>();
                            relationLevelValues = null;
                            relationLevelValues = dataLogic.getLevelValueMap(session.getCustomerRelationId());
                            relationLevelValues.putAll(dataLogic.getLevelValueMap(session.getProductRelationId()));
                            ForecastEditWindow editWindow = new ForecastEditWindow(projectionName.getValue(), session, resultTable, screenName, this);
                            UI.getCurrent().addWindow(editWindow);
                        }
                        resetOne();
                        resetTwo();
                        LOGGER.info("generateBtn click listener ends ");
                    } else {
                        StringBuffer notiMsg = new StringBuffer("You dont have permission to create a projection.");
                        if (roleList != null && !roleList.isEmpty()) {
                            notiMsg.append("\n Only " + roleList + " can create a projection.");
                        }
                        NotificationUtils.getAlertNotification("Permission Denied", notiMsg.toString());

                    }
                } catch (Exception e) { 
                    LOGGER.error(e + " generateBtn click listener ");
                }
                UI.getCurrent().setFocusedComponent(UI.getCurrent());
            } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                if (workflowFlag) {
                    generateBtn(processInstance, userModel);
                } else {
                    StringBuffer notiMsg = new StringBuffer("You dont have permission to create a projection.");
                    if (roleList != null && !roleList.isEmpty()) {
                        notiMsg.append("\n Only " + roleList + " can create a projection.");
                    }
                    NotificationUtils.getAlertNotification("Permission Denied", notiMsg.toString());

                }
            } else if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                generateLogicForReturns();

            } else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                if (workflowFlag) {
                    generateLogicForARP(processInstance, userModel);
                } else {
                    StringBuffer notiMsg = new StringBuffer("You dont have permission to create a projection.");
                    if (roleList != null && !roleList.isEmpty()) {
                        notiMsg.append("\n Only " + roleList + " can create a projection.");
                    }
                    NotificationUtils.getAlertNotification("Permission Denied", notiMsg.toString());
                }
            }
        } catch (PortalException ex) {
            LOGGER.error(ex);

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    public void generateBtn(ProcessInstance processInstance, User userModel) {
        String marketType = StringUtils.EMPTY;
        int countMarkType = 0;
        try {
            LOGGER.info("generateBtn click listener started ");

            if (selectedCustomer.size() <= 0 || selectedProduct.size() <= 0 || fromPeriod.getValue() == null || StringUtils.isBlank(projectionName.getValue()) || Constant.NULL.equalsIgnoreCase(projectionName.getValue())) {
                AbstractNotificationUtils.getErrorNotification("Selection Criteria",
                        "Not all required fields have been populated. Please try again.");
                return;
            }
            DataSelectionLogic dsLogic = new DataSelectionLogic();
            NonMandatedLogic nmLogic = new NonMandatedLogic();
            List<Leveldto> customerList = selectedCustomerContainer.getItemIds();
            List<Leveldto> productList = selectedProductContainer.getItemIds();
            for (Leveldto dto : customerList) {
                if (dto.getLevel().equals("Market Type")) {
                    countMarkType += 1;
                }
            }

            bindDataselectionDtoToSave();
            if (!StringUtils.EMPTY.equals(dataSelectionDTO.getCustRelationshipBuilderSid()) && !DASH.equals(dataSelectionDTO.getCustRelationshipBuilderSid())) {
                marketType = dsLogic.getGenerateMarketValue(Integer.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
            }

            if (countMarkType > 1) {
                AbstractNotificationUtils.getErrorNotification("More than one Market Type Selected",
                        "The projection can be created for only one Market Type.  Please select only one Market Type and try again.");
                return;
            }
            int projectionIdValue = nmLogic.saveProjection(dataSelectionDTO, screenName);
            VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
            projectionName.setValue(String.valueOf(projectionName.getValue()));
            dataSelectionDTO.setProjectionId(projectionIdValue);
            List<String> customerListEndSids = DataSelectionUtil.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));

            List<String> productListEndSids = DataSelectionUtil.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));

            dsLogic.saveCustomerHierarchyLogic(customerList, customerListEndSids, projectionIdValue, null, Constant.SAVE);
            dsLogic.saveProductHierarchyLogic(productList, productListEndSids, projectionIdValue, null, Constant.SAVE);
            relationshipBuilderSids.clear();
            setRelationshipBuilderSids(String.valueOf(customerRelation.getValue()));
            setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
            dsLogic.insertToCcpMap(relationshipBuilderSids, screenName);
            dsLogic.saveCcp(getCustomerHierarchyEndLevels(selectedCustomerContainer), getProductHierarchyEndLevelsHierNo(selectedProductContainer), "customer", String.valueOf(projectionIdValue));
            if (projectionIdValue != 0) {
                Long processInstanceId = processInstance.getId();
                try {
                    TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, projectionIdValue, processInstanceId);
                    processInstanceId = taskSummary.getProcessInstanceId();
                } catch (Exception e) { 
                    LOGGER.error(e);
                }
                SessionUtil sessionUtil = new SessionUtil();
                final SessionDTO session = sessionUtil.createSession();
                session.setProjectionId(projectionIdValue);
                session.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
                session.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
                session.setAction(Constant.ADD_FULL_SMALL);
                session.setFromDate(dataSelectionDTO.getFromDate());
                session.setToDate(dataSelectionDTO.getToDate());
                session.setFromPeriod(String.valueOf(fromPeriod.getValue()));
                session.setToPeriod(String.valueOf(toPeriod.getValue()));
                session.setCustomerHierarchyId(Integer.valueOf(dataSelectionDTO.getCustomerHierSid()));
                session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
                session.setCustomerRelationId(Integer.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
                session.setProductRelationId(Integer.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
                String definedOrUDValue = StringUtils.EMPTY;
                String definedValue = dsLogic.getDefinedValue(dataSelectionDTO.getCustomerHierSid());
                if ("User Defined".equalsIgnoreCase(definedValue)) {
                    definedOrUDValue = marketType;
                } else {
                    definedOrUDValue = dsLogic.getHelperValue(StringUtils.EMPTY + projectionIdValue);
                }
                session.setMarketTypeValue(definedOrUDValue);
                session.setCustomerHierarchyId(Integer.valueOf(dataSelectionDTO.getCustomerHierSid()));
                session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
                session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
                session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
                session.setCustomerDescription(customerDescriptionMap);
                session.setProductDescription(productDescriptionMap);
                session.setProcessId(processInstanceId);
                relationLevelValues = new HashMap<String, String>();
                relationLevelValues = null;
                relationLevelValues = dataLogic.getLevelValueMap(session.getCustomerRelationId());
                relationLevelValues.putAll(dataLogic.getLevelValueMap(session.getProductRelationId()));
                ForecastEditWindow editWindow = new ForecastEditWindow(projectionName.getValue(), session, resultTable, screenName, this);
                UI.getCurrent().addWindow(editWindow);
            }
            resetOne();
            resetTwo();

            LOGGER.info("generateBtn click listener ends ");
        } catch (Exception e) { 
            LOGGER.error(e);
        }
        UI.getCurrent().setFocusedComponent(UI.getCurrent());
    }

    @Override
    protected void configureTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Object object, Object object0, String constant, String screenName) {
        try {
//            if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), screenName);
//            }
        } catch (Exception ex) { 

            LOGGER.error(ex);
        }
    }

    @Override
    protected void loadPublicView() {
        final PrivatePublicView publicViewLookup = new PrivatePublicView(INDICATOR_PUBLIC_VIEW.getConstant(),
                publicView, PUBLIC_VIEW.getConstant(), screenName);
        UI.getCurrent().addWindow(publicViewLookup);
        publicViewLookup.setImmediate(true);
        publicViewLookup.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (publicViewLookup.getViewDTO() != null) {
                    ViewDTO viewDTO = publicViewLookup.getViewDTO();
                    setViewDTO(viewDTO);
                    publicView.setValue(viewDTO.getViewName().trim());
                    try {
                        loadView(viewDTO);
                    } catch (Exception ex) { 
                        LOGGER.error(ex + " publicView close");
                    }
                }

            }
        });
    }

    @Override
    protected void loadPrivateView() {
        final PrivatePublicView privateViewLookup = new PrivatePublicView(INDICATOR_PRIVATE_VIEW.getConstant(),
                privateView, PRIVATE_VIEW.getConstant(), screenName);
        UI.getCurrent().addWindow(privateViewLookup);
        privateViewLookup.setImmediate(true);
        privateViewLookup.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (privateViewLookup.getViewDTO() != null) {
                    ViewDTO viewDTO = privateViewLookup.getViewDTO();
                    setViewDTO(viewDTO);
                    privateView.setValue(viewDTO.getViewName().trim());
                    try {
                        loadView(viewDTO);
                    } catch (Exception ex) { 
                        LOGGER.error(ex + " privateView close");
                    }
                }

            }
        });
    }

    @Override
    protected void loadProductGroup() {
        final CustomerProductGroupLookup productGroupLookupWindow = new CustomerProductGroupLookup(INDICATOR_PRODUCT_GROUP.getConstant(),
                WINDOW_PRODUCT_GROUP_LOOKUP.getConstant(), productGroup, DataSelectionUtil.getItemSidFromHierarchy(getItemSidFromHierarchy()), screenName);
        UI.getCurrent().addWindow(productGroupLookupWindow);
        productGroupLookupWindow.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (productGroupLookupWindow.getSelectedProdHierarchy() != null) {
                    selectedProductGroupDTO = productGroupLookupWindow.getSelectedProdHierarchy();
                    groupFilteredItems = productGroupLookupWindow.getFilteredSids();
                    if (productlevelDdlb.getValue() != null && !String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(productlevelDdlb.getValue()))) {
                        loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()), false);
                    }
                }
            }
        });
    }

    @Override
    protected void loadCustomerGroup() {
        final CustomerProductGroupLookup customerGroupLookupWindow = new CustomerProductGroupLookup(INDICATOR_CUSTOMER_GROUP.getConstant(),
                WINDOW_CUSTOMER_GROUP_LOOKUP.getConstant(), customerGroup, DataSelectionUtil.getCustomerSidFromHierarchy(getCustomersFromHierarchy()), screenName);
        UI.getCurrent().addWindow(customerGroupLookupWindow);
        customerGroupLookupWindow.addCloseListener(new Window.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customerGroupLookupWindow.getSelectedCustHierarchy() != null) {
                    selectedCustomerGroupDTO = customerGroupLookupWindow.getSelectedCustHierarchy();
                    groupFilteredCompanies = customerGroupLookupWindow.getFilteredSids();
                    filterCustomerOnGroupSelect();
                }
            }
        });
    }

    @Override
    protected void saveViewButtonlogic() {
        LOGGER.info("Entering saveViewBtn method");
        if ((StringUtils.isEmpty(projectionName.getValue()) || Constant.NULL.equals(projectionName.getValue()))
                && (StringUtils.isEmpty(description.getValue()) || Constant.NULL.equals(description.getValue()))) {
            AbstractNotificationUtils.getErrorNotification(Constants.MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(),
                    "No search criteria were found. Please enter search criteria and try again.");
        } else {
            try {
                bindDataselectionDtoToSave();
                final List<Leveldto> selectedCustomersList = new ArrayList<Leveldto>();
                final List<Leveldto> selectedProductsList = new ArrayList<Leveldto>();
                for (int i = 0; i < selectedCustomerContainer.size(); i++) {
                    final Leveldto cpDto = (Leveldto) selectedCustomerContainer.getIdByIndex(i);
                    selectedCustomersList.add(cpDto);
                }
                for (int i = 0; i < selectedProductContainer.size(); i++) {
                    final Leveldto cpDto = (Leveldto) selectedProductContainer.getIdByIndex(i);
                    selectedProductsList.add(cpDto);
                }
                if (privateView.getValue() != null && privateView.getValue() != StringUtils.EMPTY) {
                    dataSelectionDTO.setViewType("private");
                } else if (publicView.getValue() != null) {
                    dataSelectionDTO.setViewType("public");
                } else {
                    dataSelectionDTO.setViewType(StringUtils.EMPTY);
                }
                dataSelectionDTO.setDeductionLevel(String.valueOf(deductionLevel.getValue()));
                dataSelectionDTO.setDeductionValue(deductionValue.getValue()==null ||deductionValue.getValue()==""  ? "" : String.valueOf(((HelperDTO)deductionValue.getValue()).getId()));
                dataSelectionDTO.setDeductionValueId(deductionValue.getValue()==null ||deductionValue.getValue()=="" ? 0 : ((HelperDTO)deductionValue.getValue()).getId());

                List<String> customerListEndSids = DataSelectionUtil.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
                List<String> productListEndSids = DataSelectionUtil.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
                final SaveViewPopup saveViewPopup = new SaveViewPopup(SAVE_VIEW.getConstant(), dataSelectionDTO, selectedCustomersList,
                        selectedProductsList, getCustomerHierarchyEndLevels(selectedCustomerContainer), getProductHierarchyEndLevelsHierNo(selectedProductContainer), viewDTO, customerListEndSids, productListEndSids, screenName);
                getUI().getCurrent().addWindow(saveViewPopup);
            } catch (Exception e) { 
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @Override
    protected void resetButtonLogic() {
        discount.select(discountDdlbDefault);
    }

    @Override
    protected void resetTwo() {
        resultsContainer.removeAllItems();
        resultTable.setContainerDataSource(resultsContainer);
        tableLogic.fireSetData(dataSelectionDTO, true);
        resultTable.setVisibleColumns(UIUtil.DATASELECTION_COLUMNS);
        resultTable.setColumnHeaders(UIUtil.DATASELECTION_HEADERS);
    }

    private void generateLogicForReturns() {
        try {
            LOGGER.info("generateBtn click listener started ");
            if (selectedProduct.size() <= 0 || fromPeriod.getValue() == null || StringUtils.isBlank(projectionName.getValue()) || Constant.NULL.equalsIgnoreCase(projectionName.getValue()) || company.getValue() == null || SELECT_ONE.equals(company.getValue())) {
                AbstractNotificationUtils.getErrorNotification("Selection Criteria",
                        "Not all required fields have been populated. Please try again.");
                return;
            }
            DataSelectionLogic dsLogic = new DataSelectionLogic();
            NonMandatedLogic nmLogic = new NonMandatedLogic();
            List<Leveldto> productList = selectedProductContainer.getItemIds();
            bindDataselectionDtoToSave();
            int projectionIdValue = nmLogic.saveProjection(dataSelectionDTO, screenName);
            VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
            projectionName.setValue(String.valueOf(projectionName.getValue()));
            dataSelectionDTO.setProjectionId(projectionIdValue);
            List<String> productListEndSids = DataSelectionUtil.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
            dsLogic.saveProductHierarchyLogic(productList, productListEndSids, projectionIdValue, null, Constant.SAVE);
            relationshipBuilderSids.clear();
            setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
            dsLogic.insertToReturnDetails(projectionIdValue);
            if (projectionIdValue != 0) {
                SessionUtil sessionUtil = new SessionUtil();
                final SessionDTO session = sessionUtil.createSession();
                session.setProjectionId(projectionIdValue);
                session.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
                session.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
                session.setAction(Constant.ADD_FULL_SMALL);

                session.setFromDate(dataSelectionDTO.getFromDate());
                session.setToDate(dataSelectionDTO.getToDate());
                session.setFromPeriod(String.valueOf(fromPeriod.getValue()));
                session.setToPeriod(String.valueOf(toPeriod.getValue()));
                session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
                session.setProductRelationId(Integer.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
                session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
                session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
                session.setProductDescription(productDescriptionMap);
                session.setCustomerHierarchyId(0);
                session.setReturnsDetailsMap(dsLogic.getReturnDetails(productDescriptionMap, session, true));

                dsLogic.callSalesInsertProcedure(session.getProjectionId(), session.getUserId(), session.getSessionId(), SalesUtils.RETURNS_SALES_INSERT_PRO_NAME);
                ForecastEditWindow editWindow = new ForecastEditWindow(projectionName.getValue(), session, resultTable, screenName, this);
                UI.getCurrent().addWindow(editWindow);
            }
            resetOne();
            resetTwo();
            LOGGER.info("generateBtn click listener ends ");
        } catch (Exception e) { 
            LOGGER.error(e);
        }
        UI.getCurrent().setFocusedComponent(UI.getCurrent());
    }

    /**
     * Used to set the Visible Header for DataSelection ListView
     *
     */
    private void setTableHeader() {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
            resultTable.setVisibleColumns(TableHeaderColumnsUtil.DATASELECTION_COLUMNS_RETURNS);
            resultTable.setColumnHeaders(TableHeaderColumnsUtil.DATASELECTION_HEADERS_RETURNS);
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
            resultTable.setVisibleColumns(TableHeaderColumnsUtil.DATASELECTION_COLUMNS_ACCRUAL);
            resultTable.setColumnHeaders(TableHeaderColumnsUtil.DATASELECTION_HEADERS_ACCRUAL);
        } else {
            resultTable.setVisibleColumns(TableHeaderColumnsUtil.DATASELECTION_COLUMNS);
            resultTable.setColumnHeaders(TableHeaderColumnsUtil.DATASELECTION_HEADERS);
        }
    }

    private void generateLogicForARP(ProcessInstance processInstance, User userModel) {
        DSLogic accdsLogic = new DSLogic();
        NonMandatedLogic nmLogic = new NonMandatedLogic();
        DataSelectionLogic dsLogic = new DataSelectionLogic();
        try {
            bindDataselectionDtoToSave();
            if (selectedCustomer.size() <= 0 || selectedProduct.size() <= 0 || StringUtils.isBlank(projectionName.getValue()) || Constants.CommonConstants.NULL.getConstant().equalsIgnoreCase(projectionName.getValue())
                    || company.getValue() == null || SELECT_ONE.equals(company.getValue()) || deductionLevel.getValue() == null || deductionValue.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification("Selection Criteria",
                        "Not all required fields have been populated. Please try again.");
                return;
            }

            int projectionIdValue = accdsLogic.saveProjection(dataSelectionDTO, screenName);

            AccrualDataSelectionDTO dtoValue = new AccrualDataSelectionDTO();
            dtoValue.setDeductionType(String.valueOf(deductionLevel.getValue()));
            dtoValue.setDeductionValue(String.valueOf(deductionValue.getValue()));
            dtoValue.setProjectionId(StringUtils.EMPTY + projectionIdValue);
            dtoValue.setScreenName(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION);
            accdsLogic.updateRebateValue(dtoValue);
            projectionId.setValue(String.valueOf(projectionIdValue));
            dataSelectionDTO.setProjectionId(projectionIdValue);
            dataSelectionDTO.setDeductionLevel(String.valueOf(deductionLevel.getValue()));
            dataSelectionDTO.setDeductionValue(String.valueOf(deductionValue.getValue()));
            List<Leveldto> customerList = selectedCustomerContainer.getItemIds();
            List<Leveldto> productList = selectedProductContainer.getItemIds();
            List<String> customerListEndSids = DataSelectionUtil.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
            List<String> productListEndSids = DataSelectionUtil.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
            setRelationshipBuilderSids(String.valueOf(customerRelation.getValue()));
            setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
            dsLogic.saveCustomerHierarchyLogic(customerList, customerListEndSids, projectionIdValue, null, Constant.SAVE);
            dsLogic.saveProductHierarchyLogic(productList, productListEndSids, projectionIdValue, null, Constant.SAVE);
            dsLogic.insertToCcpMap(relationshipBuilderSids, screenName);
            accdsLogic.saveCcp(getCustomerHierarchyEndLevels(selectedCustomerContainer), getProductHierarchyEndLevelsHierNo(selectedProductContainer), "customer", String.valueOf(projectionIdValue));
            if (projectionIdValue != 0) {
                Long processInstanceId = processInstance.getId();

                try {
                    TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, projectionIdValue, processInstanceId);
                    processInstanceId = taskSummary.getProcessInstanceId();

                } catch (Exception e) { 
                    LOGGER.error(e);
                }
                SessionUtil sessionUtil = new SessionUtil();
                final SessionDTO session = sessionUtil.createSession();
                session.setProjectionId(projectionIdValue);
                session.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
                session.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
                session.setAction(Constant.ADD_FULL_SMALL);
                session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
                session.setProductRelationId(Integer.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
                session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
                session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
                session.setCustomerDescription(customerDescriptionMap);
                session.setProductDescription(productDescriptionMap);
                session.setCustomerHierarchyId(0);

                session.setDeductionLevel(String.valueOf(deductionLevel.getValue()));
                session.setDeductionValue(String.valueOf(deductionValue.getValue()));

                session.setProcessId(processInstanceId);
                AccrualRateProjectionView arpView = new AccrualRateProjectionView(StringUtils.EMPTY + session.getProjectionId(), session, screenName, this);
                getUI().getNavigator().addView(AccrualRateProjectionView.ARP_VIEW, arpView);
                getUI().getNavigator().navigateTo(AccrualRateProjectionView.ARP_VIEW);
            }

        } catch (Exception e) { 
            LOGGER.error(e.getMessage());
        }

    }
    
    public void closeBtn() {
        try {
            resetOne();
            deductionLevel.setValue(null);
            deductionValue.setValue(null);
            modeOption.select("Add");
            dismantleCustomerSelection = true;
            dismantleProductSelection = true;
            resetButtonLogic();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }



    public void loadDiscountLevel() {
        try {
            deductionLevel.setImmediate(true);
            deductionLevel.setNullSelectionAllowed(true);
            deductionLevel.setNullSelectionItemId(UIUtil.SELECT_ONE);
            deductionLevel.addItem(UIUtil.SELECT_ONE);
            deductionLevel.addItem("Deduction Category");
            deductionLevel.addItem("Deduction Program Type");
            deductionLevel.addItem("Deduction Schedule Type");
            deductionLevel.select(UIUtil.SELECT_ONE);

            deductionLevel.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {

                        if ("Deduction Category".equalsIgnoreCase(String.valueOf(deductionLevel))) {

                            commonUtils.loadComboBox(deductionValue, "RS_CATEGORY", false);
                        } else if ("Deduction Program Type".equalsIgnoreCase(String.valueOf(deductionLevel))) {

                            commonUtils.loadComboBox(deductionValue, "REBATE_PROGRAM_TYPE", false);
                        } else if ("Deduction Schedule Type".equalsIgnoreCase(String.valueOf(deductionLevel))) {

                            commonUtils.loadComboBox(deductionValue, Constant.RS_TYPE, false);
                        }

                    } catch (Exception e) { 

                    }
                }
            });
//            fromPeriod.addItem("Jan - 2016");
////            fromPeriod.setReadOnly(true);
//            toPeriod.addItem("Dec - 2018");
//            toPeriod.setReadOnly(true);

        } catch (Exception e) { 
            LOGGER.error(e.getMessage());
        }

    }

}
