/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.dataSelection.form;

import com.stpl.app.cff.ui.dataSelection.dto.CompanyDdlbDto;
import com.stpl.app.cff.abstractCff.AbstractDataSelection;
import com.stpl.app.cff.bpm.logic.DSCalculationLogic;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.ui.dataSelection.dto.RelationshipDdlbDto;
import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.vaadin.data.Property;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.*;
import static com.stpl.app.cff.util.Constants.LabelConstants.*;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.DataSelectionUtil;
import com.stpl.app.cff.util.NotificationUtils;
import com.stpl.app.cff.util.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TabSheet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 *
 * @author mohamed.hameed
 */
public class DataSelection extends AbstractDataSelection {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelection.class);
    CustomFieldGroup dataSelectionBinder;
    private List<String> companySidsForLazyCompany = null;
    /**
     * The data selection binder.
     */
    public static final String SELECT_ONE = "-Select One-";
    public static final String NULL = "null";
    String screenName = CommonUtils.MODULE_NAME;
    Map<String, String> customerDescriptionMap = null;
    Map<String, String> productDescriptionMap = null;
    private boolean dismantleCustomerSelection = true;
    private boolean dismantleProductSelection = true;
    private LazyContainer discountDdlbLazyContainer;
    CompanyDdlbDto discountDdlbDefault = new CompanyDdlbDto(0, SELECT_ONE, true);
    CompanyDdlbDto discountDTO = null;
    public static Map<String, String> relationLevelValues = new HashMap<String, String>();
    DataSelectionLogic dataLogic = new DataSelectionLogic();
    List<Integer> customerBeanList = new ArrayList<Integer>();
    List<Integer> productBeanList = new ArrayList<Integer>();
    String publicViewName;
    String privateViewName;
    CFFLogic cffLogic = new CFFLogic();
    private boolean firstTimeLoad = true;
    TabSheet tabSheet;
    SessionDTO sessionDTO;

    public DataSelection(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, TabSheet tabSheet, SessionDTO sessionDTO) throws Exception {
        super(dataSelectionBinder, CommonUtils.MODULE_NAME);
        this.dataSelectionBinder = dataSelectionBinder;
        this.dataSelectionDTO = dataSelectionDTO;
        this.tabSheet = tabSheet;
        this.sessionDTO = sessionDTO;
        customerDescriptionMap = sessionDTO.getCustomerDescription();
        productDescriptionMap = sessionDTO.getProductDescription();
        if (!(Constants.ADD.equals(sessionDTO.getAction()))) {
            LOGGER.info("Inside Edit of Tab loading");
            configureOnLoading(dataSelectionDTO.getProjectionId(), dataSelectionDTO, sessionDTO);
            configureOnTabLoad(sessionDTO.getProjectionId(), sessionDTO);
            initializeCompanyCombobox();
            readModeOnly();
        }
            
            }

    public void configureOnLoading(int projectionId, DataSelectionDTO dataSelectionDTO, SessionDTO session) throws Exception {
        try{
        sessionDTO.setFromPeriod(dataSelectionDTO.getFromPeriod());
        sessionDTO.setToPeriod(dataSelectionDTO.getToPeriod());
        sessionDTO.setFromDate(dataSelectionDTO.getFromDate());
        sessionDTO.setToDate(dataSelectionDTO.getToDate());
        sessionDTO.setProductHierarchyId(Integer.valueOf(session.getProductHierarchyId()));
        initializeProductHierarchy(projectionId, String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
        setFirstTimeLoad(true);
        initializeFromDto();
        setFirstTimeLoad(false);
        if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
            sessionDTO.setCustomerHierarchyId(Integer.valueOf(session.getCustomerHierarchyId()));
            initializeCustomerHierarchy(projectionId, String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
        }
       }catch(Exception e){
           e.printStackTrace();
       }
    }

    @Override
    protected void productLevelDdlbValueChange(String selectedLevel, boolean flag) {
        loadFilteredProductSelection(selectedLevel, false);
    }

    @Override
    protected void customerHierarchyLookUp() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
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
                        customerForecastLevelContainer.addItem("Level " + i + " - " + levelName);
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
                    // if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                        level.setContainerDataSource(customerForecastLevelContainer);
                   // }
                    setCustomerForecastLevelNullSelection();
                    setCustomerRelationNullSelection();
                    dataSelectionDTO.setCustomerHierSid(String.valueOf(customerHierarchyDto.getHierarchyId()));
                }
            }
        });
    }

    @Override
    protected void productHierarchyLookUp() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
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
                        productForecastLevelContainer.addItem("Level " + i + " - " + levelName);
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
                    productlevelDdlb.setContainerDataSource(productForecastLevelContainer);
                }
            }
        });
    }

    public void readModeOnly() {
        company.setEnabled(false);
        productRelation.setEnabled(false);
        customerHierarchy.setEnabled(false);
        customerRelation.setEnabled(false);
        customerLevel.setEnabled(false);
        customerGroup.setEnabled(false);
        level.setEnabled(false);
        productHierarchy.setEnabled(false);
        productRelation.setEnabled(false);
        productLevel.setEnabled(false);
        productGroup.setEnabled(false);
        productlevelDdlb.setEnabled(false);
        privateView.setEnabled(false);
        publicView.setEnabled(false);
        moveLeftBtn.setEnabled(false);
        moveLeftProduct.setEnabled(false);
        moveRightBtn.setEnabled(false);
        moveRightProduct.setEnabled(false);
        all.setEnabled(false);
        allProductBtn.setEnabled(false);
        generateBtn.setEnabled(false);
        resetBtn.setEnabled(false);
        saveViewBtn.setEnabled(false);
        deleteViewBtn.setEnabled(false);

    }

    @Override
    protected void customerLevelValueChange(Property.ValueChangeEvent event, boolean flag) {
        LOGGER.info("Logging - customerLevelValueChange");
        customerInnerLevelContainer.removeAllItems();
        if (event.getProperty().getValue() != null && !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
            String selectedLevel = String.valueOf(event.getProperty().getValue());
            System.out.println("==selected level=============>>>"+selectedLevel);
            setSelectedCustomerLevel(selectedLevel);
            DataSelectionLogic logic = new DataSelectionLogic();
            logic.loadCustomerForecastLevel(customerHierarchyDto.getHierarchyId(), customerHierarchyDto.getHierarchyName());
            String val[] = selectedLevel.split(" ");
            int forecastLevel = Integer.parseInt(val[1]);
            sessionDTO.setCustomerLevelNumber(String.valueOf(forecastLevel));
            customerInnerLevelContainer.removeAllItems();
            selectedCustomer.removeAllItems();
            selectedCustomerContainer.removeAllItems();
            customerBeanList.removeAll(customerBeanList);
            for (int i = 1; i <= forecastLevel; i++) {
                String levelName = innerCustLevels.get(i - 1).getLevel();
                customerInnerLevelContainer.addItem("Level " + i + " - " + levelName);
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
        LOGGER.info("customer inner Level - ValueChangeListener");
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
                        tempConditionCheck = "NDC";
                    }

                    if (tempDto.getLevel() != null && tempConditionCheck.equalsIgnoreCase(tempDto.getLevel()) && "ITEM_MASTER".equals(tempDto.getTableName())) {
                        isNdc = true;
                    } else {
                        isNdc = false;
                    }
                    ExtTreeContainer<Leveldto> dumbyCustomerContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);
                    custVlues = logic.loadInnerLevel(tempDto.getLevel(), customerHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(dumbyCustomerContainer.getItemIds()), isNdc, tempDto.getFieldName(), relationshipSid, customerDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, levelNo);
                    if (groupFilteredCompanies != null && "COMPANY_MASTER".equalsIgnoreCase(String.valueOf(tempDto.getTableName()))
                            && ("Trading Partner".equals(levelName) || "Company".equals(levelName))) {
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
        } catch (Exception ex) {
             ex.printStackTrace();
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
            sessionDTO.setProductLevelNumber(String.valueOf(forecastLevel));
            productInnerLevelContainer.removeAllItems();
            selectedProduct.removeAllItems();
            selectedProductContainer.removeAllItems();
            productBeanList.removeAll(productBeanList);
            for (int i = 1; i <= forecastLevel; i++) {
                String levelName = innerProdLevels.get(i - 1).getLevel();
                productInnerLevelContainer.addItem("Level " + i + " - " + levelName);
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
        try {
            String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID);
             List<String> roleList = new ArrayList<String>();
            ProcessInstance processInstance = DSCalculationLogic.startWorkflow();
            User userModel = UserLocalServiceUtil.getUser(Long.parseLong(userId));
            boolean workflowFlag = DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processInstance.getId());
            if (selectedCustomer.size() <= 0 || selectedProduct.size() <= 0
                    || company.getValue() == null || SELECT_ONE.equals(company.getValue())) {
                AbstractNotificationUtils.getErrorNotification("Selection Criteria",
                        "Not all required fields have been populated. Please try again.");
                return;
            }
              if (workflowFlag) {
            List<Leveldto> productList = selectedProductContainer.getItemIds();
            bindDataselectionDtoToSave();
            int projectionIdValue = cffLogic.saveCFFMaster(dataSelectionDTO, screenName, Boolean.FALSE, 0);
            VaadinSession.getCurrent().setAttribute("projectionId", projectionIdValue);
            dataSelectionDTO.setProjectionId(projectionIdValue);
            List<String> productListEndSids = DataSelectionUtil.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
            cffLogic.saveProductHierarchyLogic(productList, productListEndSids, projectionIdValue, null, "save");

            List<Leveldto> customerList = selectedCustomerContainer.getItemIds();
            List<String> customerListEndSids = DataSelectionUtil.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
            cffLogic.saveCustomerHierarchyLogic(customerList, customerListEndSids, projectionIdValue, null, "save");
            relationshipBuilderSids.clear();
            setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
            cffLogic.insertToCcpMap(relationshipBuilderSids, screenName);
            cffLogic.saveCcp(getCustomerHierarchyEndLevels(selectedCustomerContainer), getProductHierarchyEndLevelsHierNo(selectedProductContainer), "customer", String.valueOf(projectionIdValue));
            if (projectionIdValue != 0) {
                 Long processInstanceId = processInstance.getId();
                 try {
                                TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, projectionIdValue, processInstanceId);
                                processInstanceId = taskSummary.getProcessInstanceId();
                            } catch (Exception e) {
                                LOGGER.error(e.getMessage());
                            }
                
                
                sessionDTO.setProjectionId(projectionIdValue);
                sessionDTO.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
                sessionDTO.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
                sessionDTO.setAction(Constants.ADD);
                sessionDTO.setFromDate(dataSelectionDTO.getFromDate());
                sessionDTO.setToDate(dataSelectionDTO.getToDate());
                sessionDTO.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
                sessionDTO.setProductRelationId(Integer.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
                sessionDTO.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
                sessionDTO.setProductDescription(productDescriptionMap);
                sessionDTO.setCustomerHierarchyId(0);
                sessionDTO.setCustomerDescription(customerDescriptionMap);
                sessionDTO.setProcessId(processInstanceId);
                sessionDTO.setCustomerHierarchyId(Integer.valueOf(dataSelectionDTO.getCustomerHierSid()));
                sessionDTO.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
                sessionDTO.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());

            }
            tabSheet.setSelectedTab(1);
            sessionDTO.setIsGenerated(Boolean.TRUE);
              }else {
                        StringBuffer notiMsg = new StringBuffer("You dont have permission to create a projection.");
                        if (roleList != null || !roleList.isEmpty()) {
                            notiMsg.append("\n Only " + roleList + " can create a projection.");
                        }
                        NotificationUtils.getAlertNotification("Permission Denined", notiMsg.toString());

                    }

        } catch (Exception ex) {
            Logger.getLogger(DataSelection.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void loadProductGroup() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        final CustomerProductGroupLookup productGroupLookupWindow = new CustomerProductGroupLookup(INDICATOR_PRODUCT_GROUP.getConstant(),
                WINDOW_PRODUCT_GROUP_LOOKUP.getConstant(), productGroup, DataSelectionUtil.getItemSidFromHierarchy(getItemSidFromHierarchy()));
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
    protected void loadCustomerGroup() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        final CustomerProductGroupLookup customerGroupLookupWindow = new CustomerProductGroupLookup(INDICATOR_CUSTOMER_GROUP.getConstant(),
                WINDOW_CUSTOMER_GROUP_LOOKUP.getConstant(), customerGroup, DataSelectionUtil.getCustomerSidFromHierarchy(getCustomersFromHierarchy()));
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
            if (privateView.getValue() != null && privateView.getValue() != "") {
                dataSelectionDTO.setViewType("private");
            } else if (publicView.getValue() != null) {
                dataSelectionDTO.setViewType("public");
            } else {
                dataSelectionDTO.setViewType("");
            }
             final String userId = (String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID);
            List<String> customerListEndSids = DataSelectionUtil.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
            List<String> productListEndSids = DataSelectionUtil.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
            final SaveViewPopup saveViewPopup = new SaveViewPopup(SAVE_VIEW.getConstant(), dataSelectionDTO, selectedCustomersList,
                    selectedProductsList, getCustomerHierarchyEndLevels(selectedCustomerContainer), getProductHierarchyEndLevelsHierNo(selectedProductContainer), viewDTO, customerListEndSids, productListEndSids, screenName,sessionDTO);
            getUI().getCurrent().addWindow(saveViewPopup);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void moveLeftButtonLogic() {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            if (availableCustomer.getValue() != null) {
                int forecastLevel = 0;
                if (customerLevel.getValue() != null) {
                    forecastLevel = UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
                }
                Object item = availableCustomer.getValue();
                Object selectedItem = null;
                if (selectedCustomerContainer.size() > 0) {
                    if (selectedCustomer.getValue() != null) {
                        //When a value selected in selected customer tree
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
                            uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
                        }
                        if (!uncommonValues.isEmpty()) {
                            newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescriptionMap);
                        }
                        newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
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
                        //When no value selected in selected customer tree
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
                            uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
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
                        newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
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
                    // When selected customer tree table is empty
                    if (availableCustomer.getValue() != null && (DataSelectionUtil.getBeanFromId(availableCustomer.getValue()).getLevelNo() > 1)) {
                        //Not root level as checked with condition getLevelNo() > 1
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
                        newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
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
                        // Root 
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
                        newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
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
            if (customerLevel.getValue() != null) {
                forecastLevel = UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
            }
            if (availableCustomerContainer.size() > 0) {
                List<Leveldto> iteams = new ArrayList<Leveldto>(availableCustomerContainer.getItemIds());
                Object selectedItem = null;
                if (selectedCustomerContainer.size() > 0) {
                    if (selectedCustomer.getValue() != null) {
                        selectedItem = selectedCustomer.getValue();
                        for (Leveldto item : iteams) {
                            if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil.getBeanFromId(selectedItem).getLevelNo()) {
                                //Check whether available level is greater to selected level
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
                                    uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
                                }
                                if (!uncommonValues.isEmpty()) {
                                    newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescriptionMap);
                                }
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
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
                        // Adding without selecting a parent
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
                                uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
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
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
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
                    // Adding without selecting a parent with empty tree
                    if (level.getValue() != null && UiUtils.parseStringToInteger(String.valueOf(level.getValue())) == 1) {
                        //Root
                        for (Leveldto item : iteams) {
                            selectedCustomerContainer.removeAllItems();
                            selectedCustomer.removeAllItems();
                            Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);
                            // Root 
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

                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
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
                        // Not root, none selected in selected tree
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
                                uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
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
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]), customerDescriptionMap);
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
            if (productLevel.getValue() != null) {
                forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
            }

            if (availableProductContainer.size() > 0) {
                List<Leveldto> iteams = new ArrayList<Leveldto>(availableProductContainer.getItemIds());
                Object selectedItem = null;
                if (selectedProductContainer.size() > 0) {
                    if (selectedProduct.getValue() != null) {
                        selectedItem = selectedProduct.getValue();
                        for (Leveldto item : iteams) {
                            if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil.getBeanFromId(selectedItem).getLevelNo()) {
                                //Check whether available level is greater to selected level
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
                                    uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
                                }
                                newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), productDescriptionMap);
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                        // Adding without selecting a parent
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
                                uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
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
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                    // Adding without selecting a parent with empty tree
                    if (productLevel.getValue() != null && UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue())) == 1) {
                        //Root
                        for (Leveldto item : iteams) {
                            selectedProductContainer.removeAllItems();
                            selectedProduct.removeAllItems();
                            Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);
                            // Root 
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
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                        // Not root, none selected in selected tree
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
                                uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
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
                            newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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

    @Override
    protected void moveRigthButtonLogic() {
        try{
        
        if (selectedCustomer.getValue() != null) {
            Object selectedItem = selectedCustomer.getValue();
            String levelInString = "0";
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
        catch(Exception ex){
        }
    }

    @Override
    protected void moveRigthProductButtonLogic() {
        try {
            if (selectedProduct.getValue() != null) {
                List<Leveldto> listValue = new ArrayList<Leveldto>();
                Object selectedItem = selectedProduct.getValue();
                Leveldto selectedLevel = (Leveldto) DataSelectionUtil.getBeanFromId(selectedItem);
                String levelInString = "0";
                if (!String.valueOf(productlevelDdlb.getValue()).equalsIgnoreCase(String.valueOf(SELECT_ONE))) {
                    levelInString = String.valueOf(productlevelDdlb.getValue());
                }
                int currentLevel = UiUtils.parseStringToInteger(levelInString);
                if (currentLevel != 0 && selectedLevel.getLevelNo() == currentLevel) {
                    if ("NDC".equalsIgnoreCase(selectedLevel.getLevel())) {
                        listValue = DataSelectionUtil.getFSValue(selectedLevel.getRelationshipLevelValue(), selectedLevel.getFieldName());
                        selectedLevel.setForm("" + listValue.get(0).getForm());
                        selectedLevel.setStrength("" + listValue.get(0).getStrength());
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

    @Override
    protected void moveLeftProductButtonLogic() {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            int forecastLevel = 0;
            if (productLevel.getValue() != null) {
                forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
            }

            if (availableProduct.getValue() != null) {
                Object item = availableProduct.getValue();
                if (selectedProductContainer.size() > 0) {
                    if (selectedProduct.getValue() != null) {
                        //When a value selected in selected product tree
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
                            uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
                        }
                        if (!uncommonValues.isEmpty()) {
                            newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), productDescriptionMap);
                        }
                        newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                        //When no value selected in selected product tree
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
                            uncommonValues.removeAll(removeValues); // At this point, uncommonValues should contain only 1 value since only one value is selected to be moved in available table.
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
                        newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                    // When selected product tree table is empty
                    if (availableProduct.getValue() != null && (DataSelectionUtil.getBeanFromId(availableProduct.getValue()).getLevelNo() > 1)) {
                        //Not root level as checked with condition getLevelNo() > 1
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
                        newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                        // Root 
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
                        newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                        CFFLogic.deleteView(Integer.parseInt(dto.getViewId()));
                        CommonUIUtils.getMessageNotification(dto.getViewName() + " has been successfully deleted.");
                        deleteViewBtn.setEnabled(false);
                        resetOne();
                    }

                } catch (Exception exception) {
                    LOGGER.error(exception.getMessage());
                }
            }
        }.getConfirmationMessage(Constants.MessageConstants.CONFIRM_DELETION_TITLE.getConstant(), Constants.MessageConstants.CONFIRM_DELETION_BODY.getConstant().replace("?#", dto.getViewName()));
    }

    @Override
    protected void companyValueChangeLogic(Object value) throws Exception {
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
            if (tempDto.getLevel() != null && ("Item".equals(tempDto.getLevel()) || "NDC".equals(tempDto.getLevel())) && "ITEM_MASTER".equals(tempDto.getTableName())) {
                isNdc = true;
            } else {
                isNdc = false;
            }
            innerLevelValues = logic.loadInnerLevel(tempDto.getLevel(), productHierarchyDto.getHierarchyId(),
                    DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds()),
                    isNdc, tempDto.getFieldName(), relationshipSid, productDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo());
            List<Leveldto> filteredValues = new ArrayList<Leveldto>(innerLevelValues);
            if (company.getValue() != null && !String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(company.getValue()))
                    && "ITEM_MASTER".equalsIgnoreCase(String.valueOf(tempDto.getTableName())) && ("Item".equals(tempDto.getLevel()) || "NDC".equals(tempDto.getLevel()))) {
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

    @Override
    protected void resetButtonLogic() {
    }

    /**
     * Loads data selection after selecting a private/public view
     *
     * @param viewDTO DTO with view parameters
     */
    private void loadView(ViewDTO viewDTO) throws Exception {
        dismantleCustomerSelection = true;
        dismantleProductSelection = true;
        customerHierarchyDto = new HierarchyLookupDTO();
        productHierarchyDto = new HierarchyLookupDTO();
        viewId.setValue(viewDTO.getViewId());
        projectionId.setValue(viewDTO.getProjectionId());
        dataSelectionDTO.setProjectionId(UiUtils.parseStringToInteger(viewDTO.getProjectionId()));
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
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid()) && !"0".equals(viewDTO.getCompanyGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
            dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
        }
        customerGroup.setValue(viewDTO.getCustomerGroup());
        RelationshipDdlbDto selectedProductRelationshipDdlbDto = null;
        if (!StringUtils.isBlank(viewDTO.getProdRelationshipBuilderSid()) && !Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProdRelationshipBuilderSid())) {
            selectedProductRelationshipDdlbDto = new RelationshipDdlbDto();
            selectedProductRelationshipDdlbDto.setRelationshipBuilderSid(viewDTO.getProdRelationshipBuilderSid());
        }
        loadRelationDdlb(UiUtils.parseStringToInteger(viewDTO.getProductHierarchySid()), selectedProductRelationshipDdlbDto, productRelation);
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid()) && !"0".equals(viewDTO.getProductGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
            dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
        }
        productGroup.setValue(viewDTO.getProductGroup());
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCustomerHierarchySid()) && !"0".equals(viewDTO.getCustomerHierarchySid()) && !StringUtils.EMPTY.equals(viewDTO.getCustomerHierarchySid())) {

            loadCustomerLevel(viewDTO.getCustomerHierarchySid(), viewDTO.getCustomerLevel());
            relationLevelValues.putAll(dataLogic.getLevelValueMap(viewDTO.getCustRelationshipBuilderSid()));
            initializeCustomerHierarchy(UiUtils.parseStringToInteger(viewDTO.getProjectionId()), viewDTO.getCustomerLevel());
            loadInnerCustomerLevel(Integer.parseInt(viewDTO.getCustomerLevel()), UiUtils.parseStringToInteger(viewDTO.getCustomerInnerLevel()),
                    UiUtils.parseStringToInteger(viewDTO.getCustomerHierarchySid()));
            dataSelectionDTO.setCustomerHierSid(viewDTO.getCustomerHierarchySid());
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid()) && !"0".equals(viewDTO.getCompanyGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
            dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
            triggerCustGrpOnView(viewDTO.getCompanyGroupSid(), Integer.parseInt(viewDTO.getCustomerHierarchySid()), true);
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductHierarchySid()) && !"0".equals(viewDTO.getProductHierarchySid()) && !StringUtils.EMPTY.equals(viewDTO.getProductHierarchySid())) {
            loadProductLevel(viewDTO.getProductHierarchySid(), viewDTO.getProductLevel());
            relationLevelValues.putAll(dataLogic.getLevelValueMap(viewDTO.getProdRelationshipBuilderSid()));
            initializeProductHierarchy(UiUtils.parseStringToInteger(viewDTO.getProjectionId()), viewDTO.getProductLevel());
            loadInnerProductLevel(Integer.parseInt(viewDTO.getProductLevel()), UiUtils.parseStringToInteger(viewDTO.getProductInnerLevel()),
                    UiUtils.parseStringToInteger(viewDTO.getProductHierarchySid()));
            dataSelectionDTO.setProdHierSid(viewDTO.getProductHierarchySid());
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid()) && !"0".equals(viewDTO.getProductGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
            dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
            triggerProdGrpOnView(viewDTO.getProductGroupSid(), viewDTO.getProductHierarchySid(), false);
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyMasterSid()) && !"0".equals(viewDTO.getCompanyMasterSid()) && !StringUtils.EMPTY.equals(viewDTO.getCompanyMasterSid())) {
            dataSelectionDTO.setCompanySid(viewDTO.getCompanyMasterSid());
        }
        deleteViewBtn.setEnabled(true);
        initializeCompanyDdlb();
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid()) && !"0".equals(viewDTO.getCompanyGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
            dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
            selectedCustomerGroupDTO.setCustomerGroupSid(String.valueOf(viewDTO.getCompanyGroupSid()));
        }
        if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid()) && !"0".equals(viewDTO.getProductGroupSid()) && !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
            dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
            selectedProductGroupDTO.setProductGroupSid(String.valueOf(viewDTO.getProductGroupSid()));
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
            LOGGER.error(ex+ " - in initializeCompanyDdlb");
        }
    }

    private void initializeCompanyCombobox() {
        List<String> companySids = DataSelectionUtil.getCompanySidFromHierarchy(getCompanySidFromHierarchy(false, true), screenName);
        try {
            setCompanySidsForLazyCompany(companySids);
            loadCompanyDdlb(companySids);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void setCompanySidsForLazyCompany(List<String> companySidsForLazyCompany) {
        this.companySidsForLazyCompany = companySidsForLazyCompany;
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
            relationship.setInputPrompt("-Select One-");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Company Logic
     */
    private void loadCompanyLogic() {
        try {
            loadCompanyDdlb(DataSelectionUtil.getCompanySidFromHierarchy(getCompanySidFromHierarchy(false, false), screenName), null);
        } catch (Exception ex) {
            LOGGER.error(ex + " - in loadCompanyLogic in loading ddlb");
        }
    }

    /**
     * getCompanySidFromHierarchy
     *
     * @param loadFromSelected
     * @param dsTabflag
     * @return
     */
    public List<Leveldto> getCompanySidFromHierarchy(final boolean loadFromSelected, final boolean dsTabflag) {
        List<Leveldto> innerLevelValues = null;
        try {
            DataSelectionLogic logic = new DataSelectionLogic();

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
                    if ("Company".equalsIgnoreCase(dto.getLevel()) || "GL Comp".contains(dto.getLevel()) || "GL Company".contains(dto.getLevel())) {
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
                            selectedLevelSids, false, companyLevel.getFieldName(), relationshipSid, productDescriptionMap, StringUtils.EMPTY, screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, companyLevel.getLevelNo());
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex+ " in getCompanySidFromHierarchy");
        }
        return innerLevelValues;
    }

    /**
     * LoadCompanyDdlb
     *
     * @param companySids
     * @param selectedCompanyDdlbDto
     */
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

    private void setRelationshipBuilderSids(String rbSid) {
        // order is preserved
        relationshipBuilderSids.add(rbSid);
    }

    private void loadCompanyDdlb(final List<String> companySids) {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            List<CompanyDdlbDto> companyList = logic.getCompanies(companySids);
            CompanyDdlbDto selectedCompanyDdlbDto = null;
            if (dataSelectionDTO.getCompanySid() != null && !StringUtils.isBlank(dataSelectionDTO.getCompanySid())
                    && !Constants.CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getCompanySid()) && !"0".equals(dataSelectionDTO.getCompanySid())) {
                selectedCompanyDdlbDto = new CompanyDdlbDto();
                selectedCompanyDdlbDto.setCompanyMasterSid(UiUtils.parseStringToInteger(dataSelectionDTO.getCompanySid()));
            }
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
                    Leveldto tempDto = (Leveldto) innerProdLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        levelName = tempDto.getLevel();
                    }
                    if ((tempDto.getLevel() != null && ("NDC".equalsIgnoreCase(tempDto.getLevel()) || "Item".equalsIgnoreCase(tempDto.getLevel()) || "Product".equalsIgnoreCase(tempDto.getLevel()))) && "ITEM_MASTER".equals(tempDto.getTableName())) {
                        isNdc = true;
                    } else {
                        isNdc = false;
                    }
                    ExtTreeContainer<Leveldto> dumbyProductContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);
                    innerLevelValues = logic.loadInnerLevel(tempDto.getLevel(), productHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(dumbyProductContainer.getItemIds()), isNdc, tempDto.getFieldName(), relationshipSid, productDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo());
                    List<Leveldto> groupFilteredValues = new ArrayList<Leveldto>();
                    List<Leveldto> companyFilteredValues = new ArrayList<Leveldto>();
                    List<Leveldto> brandFilteredValues = new ArrayList<Leveldto>();
                    List<Leveldto> tempLevelValues;
                    List<Leveldto> filteredValues = new ArrayList<Leveldto>(innerLevelValues);
                    productFilterContainer.removeAllItems();
                    productFilterContainer.addAll(innerLevelValues);
                    if (!tempDto.isFromItem() && ("NDC".equalsIgnoreCase(tempDto.getLevel()) || "Items".equalsIgnoreCase(tempDto.getLevel()) || "Product".equalsIgnoreCase(tempDto.getLevel()))) {
                        List<String> itemSidsFromCcCombo = null;
                        List<String> itemSidsFromBrandType = null;
                        /* Contains the items from product hierarchy. For filtering using company ddlb */
                        List<String> filteredItemsFromBrandType = null;
                        if (selectedCustomerContainer != null && !selectedCustomerContainer.getItemIds().isEmpty()) {
                            Leveldto currentDto;
                            List<Leveldto> ccList = new ArrayList<Leveldto>();
                            for (Object item : selectedCustomerContainer.getItemIds()) {
                                currentDto = DataSelectionUtil.getBeanFromId(item);
                                if (currentDto != null && !StringUtils.isBlank(currentDto.getTableName())
                                        && ("CONTRACT_MASTER".equalsIgnoreCase(currentDto.getTableName()))
                                        && ("Contract".equalsIgnoreCase(currentDto.getLevel()))) {
                                    ccList.add(currentDto);
                                }
                                if (currentDto != null && !StringUtils.isBlank(currentDto.getTableName())
                                        && ("COMPANY_MASTER".equalsIgnoreCase(currentDto.getTableName())
                                        && ("Trading Partner".equalsIgnoreCase(currentDto.getLevel()) || "Company".equalsIgnoreCase(currentDto.getLevel())))) {
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
                        // Product group filter
                        if ("ITEM_MASTER".equalsIgnoreCase(String.valueOf(tempDto.getTableName())) && ("Item".equals(tempDto.getLevel()) || "NDC".equals(tempDto.getLevel()))) {
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
                        //Company DDLB filter 
                        if (company.getValue() != null && !SELECT_ONE.equals(company.getValue())
                                && "ITEM_MASTER".equalsIgnoreCase(String.valueOf(tempDto.getTableName())) && ("Item".equals(tempDto.getLevel()) || "NDC".equals(tempDto.getLevel()))) {
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
                availableProduct.setColumnHeaders(new String[]{"NDC", "Form", "Strength"});
            } else {
                availableProduct.setVisibleColumns(new Object[]{"displayValue"});
                availableProduct.setColumnHeaders(new String[]{levelName});
            }
        } catch (Exception ex) {
            LOGGER.error(ex + " - in loadFilteredProductSelection");
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

    private List<Leveldto> getItemSidFromHierarchy() {
        List<Leveldto> innerLevelValues = null;
        try {
            if ((productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) && (innerProdLevels != null)) {
                String relationshipSid = String.valueOf(productRelation.getValue());
                DataSelectionLogic logic = new DataSelectionLogic();
                Leveldto ndcLevel = null;
                for (Leveldto dto : innerProdLevels) {
                    if ("NDC".equalsIgnoreCase(dto.getLevel()) || "Item".equalsIgnoreCase(dto.getLevel())) {
                        ndcLevel = dto;
                        break;
                    }
                }
                if (ndcLevel != null) {
                    innerLevelValues = logic.loadInnerLevel(ndcLevel.getLevel(), productHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds()), true, ndcLevel.getFieldName(),
                            relationshipSid, productDescriptionMap, INDICATOR_LEVEL_NDC.getConstant(), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, ndcLevel.getLevelNo());
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex + " in getItemSidFromHierarchy");
        }
        return innerLevelValues;
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
                    if (tempDto.getLevel() != null && ("NDC".equalsIgnoreCase(tempDto.getLevel()) || "Item".equalsIgnoreCase(tempDto.getLevel())) && "ITEM_MASTER".equals(tempDto.getTableName())) {
                        isNdc = true;
                    } else {
                        isNdc = false;
                    }
                    custVlues = logic.loadInnerLevel(tempDto.getLevel(), customerHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()), isNdc,
                            tempDto.getFieldName(), relationshipSid, customerDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo());

                    if (groupFilteredCompanies != null && "COMPANY_MASTER".equalsIgnoreCase(String.valueOf(tempDto.getTableName()))
                            && ("Trading Partner".equals(levelName) || "Company".equals(levelName))) {
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
        } catch (Exception ex) {
            LOGGER.error(ex + " filterCustomerOnGroupSelect ");
        }
    }

    private List<Leveldto> getCustomersFromHierarchy() {
        List<Leveldto> innerLevelValues = null;
        try {
            if ((productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) && (innerCustLevels != null)) {
                String relationshipSid = String.valueOf(customerRelation.getValue());
                DataSelectionLogic logic = new DataSelectionLogic();
                Leveldto customerLevelDto = null;
                for (Leveldto dto : innerCustLevels) {
                    if ("Customer".equalsIgnoreCase(dto.getLevel()) || "Company".equalsIgnoreCase(dto.getLevel()) || "Trading Partner".equalsIgnoreCase(dto.getLevel())) {
                        customerLevelDto = dto;
                        break;
                    }
                }
                if (customerLevelDto != null) {
                    innerLevelValues = logic.loadInnerLevel(customerLevelDto.getLevel(), customerHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()),
                            false, customerLevelDto.getFieldName(), relationshipSid, customerDescriptionMap, INDICATOR_LEVEL_CUSTOMER.getConstant(), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, customerLevelDto.getLevelNo());
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex + " in getCustomersFromHierarchy");
        }
        return innerLevelValues;
    }

    public DataSelectionDTO bindDataselectionDtoToSave() {
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
            dataSelectionDTO.setCustomerHierSid(String.valueOf(customerHierarchyDto.getHierarchyId()));
        } else {
            dataSelectionDTO.setCustomerHierSid(String.valueOf(0));
        }
        if (productHierarchyDto != null && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(productHierarchyDto.getHierarchyId()))
                && !StringUtils.isEmpty(String.valueOf(productHierarchyDto.getHierarchyId())) && !StringUtils.isBlank(String.valueOf(productHierarchyDto.getHierarchyId()))) {
            dataSelectionDTO.setProdHierSid(String.valueOf(productHierarchyDto.getHierarchyId()));
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
        dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((level.getValue() == null ? StringUtils.EMPTY : level.getValue().toString()))));
        dataSelectionDTO.setProductHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((productlevelDdlb.getValue() == null ? StringUtils.EMPTY : productlevelDdlb.getValue().toString()))));

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
        
        if (discount.getValue() != null && !SELECT_ONE.equals(discount.getValue()) && StringUtils.isNotBlank(discount.getValue().toString())) {
            CompanyDdlbDto dto = (CompanyDdlbDto) discount.getValue();
            dataSelectionDTO.setDiscount(dto.getRsNo());
            dataSelectionDTO.setDiscountSid(dto.getRsModelSid());
        } else {
            dataSelectionDTO.setDiscount(SELECT_ONE);
            dataSelectionDTO.setDiscountSid(0);
        }

       // dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((customerLevel.getValue() == null ? StringUtils.EMPTY : customerLevel.getValue().toString()))));
      //  dataSelectionDTO.setProductHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((productLevel.getValue() == null ? StringUtils.EMPTY : productLevel.getValue().toString()))));

        dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(UiUtils.parseStringToInteger((level.getValue() == null ? StringUtils.EMPTY : level.getValue().toString()))));
        dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(UiUtils.parseStringToInteger((productlevelDdlb.getValue() == null ? StringUtils.EMPTY : productlevelDdlb.getValue().toString()))));
        return dataSelectionDTO;
    }

    public List<Leveldto> getProductHierarchyEndLevels(final ExtTreeContainer<Leveldto> selectedProductContainer) {
        LOGGER.info("--inside getProductHierarchyEndLevels------------------------->>>>>");
        List<Leveldto> productHierarchyEndLevels = new ArrayList<Leveldto>();
        for (Object item : selectedProductContainer.getItemIds()) {
            if (!selectedProductContainer.hasChildren(item)) {
                productHierarchyEndLevels.add(DataSelectionUtil.getBeanFromId(item));
            }
        }
        LOGGER.info("-- getProductHierarchyEndLevels size------------------------->>>>>" + productHierarchyEndLevels.size());
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

    private void initializeProductHierarchy(final int projectionId, final String productLevel) throws Exception {
        LOGGER.info("Intializind Product Hierarchy");
        DataSelectionLogic logic = new DataSelectionLogic();
        int forecastLevel = 0;
        System.out.println("==product level=========>>>"+productLevel);
        forecastLevel = UiUtils.parseStringToInteger(productLevel);
        System.out.println("==forecast level========"+forecastLevel);
        List<Leveldto> reslistOne;
        reslistOne = logic.getRelationShipValues(projectionId, "product", productLevel, productDescriptionMap);
        LOGGER.info("relist===========" + reslistOne.toString());
        for (Leveldto reslistOne1 : reslistOne) {
        }
        productBeanList.removeAll(productBeanList);
        for (Leveldto dto : reslistOne) {
            productBeanList.add(dto.getRelationshipLevelSid());
        }

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
                        if (getBeanFromId(tempdto).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
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

    public void setFirstTimeLoad(boolean firstTimeLoad) {
        this.firstTimeLoad = firstTimeLoad;
    }

    private void initializeFromDto() {
        try {

            if (dataSelectionDTO != null) {
                sessionDTO.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
                sessionDTO.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));

                if (dataSelectionDTO.getCustomerHierSid() != null && !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))
                        && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))) {

                    RelationshipDdlbDto selectedRelationshipDdlbDto = null;
                    if (!StringUtils.isBlank(dataSelectionDTO.getCustRelationshipBuilderSid()) && !Constants.CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getCustRelationshipBuilderSid())) {
                        selectedRelationshipDdlbDto = new RelationshipDdlbDto();
                        selectedRelationshipDdlbDto.setRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
                        sessionDTO.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
                    }
                    DataSelection.loadRelationDdlb(UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid()), selectedRelationshipDdlbDto, customerRelation);
                }
                if (dataSelectionDTO.getCustomerHierSid() != null && !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))
                        && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))) {
                    customerHierarchyDto = new HierarchyLookupDTO();
                    customerHierarchyDto.setHierarchyId(UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid()));
                    customerHierarchyDto.setHierarchyName(dataSelectionDTO.getCustomerHierarchy());
                    customerHierarchy.setValue(customerHierarchyDto.getHierarchyName());
                    loadCustomerLevel(String.valueOf(dataSelectionDTO.getCustomerHierSid()));
                    if (!StringUtils.isBlank(dataSelectionDTO.getCustomerHierarchyInnerLevel()) && !Constants.CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getCustomerHierarchyInnerLevel())) {
                        loadInnerCustomerLevel(UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierarchyLevel()), UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierarchyInnerLevel()),
                                UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid()));
                    }
                }
                if (dataSelectionDTO.getProdHierSid() != null && !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getProdHierSid()))
                        && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(dataSelectionDTO.getProdHierSid()))) {
                    RelationshipDdlbDto selectedRelationshipDdlbDto = null;
                    if (!StringUtils.isBlank(dataSelectionDTO.getProdRelationshipBuilderSid()) && !Constants.CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getProdRelationshipBuilderSid())) {
                        selectedRelationshipDdlbDto = new RelationshipDdlbDto();
                        selectedRelationshipDdlbDto.setRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
                        sessionDTO.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
                    }
                    DataSelection.loadRelationDdlb(UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid()), selectedRelationshipDdlbDto, productRelation);
                }
                if (dataSelectionDTO.getProdHierSid() != null && !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getProdHierSid()))
                        && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(dataSelectionDTO.getProdHierSid()))) {
                    productHierarchyDto = new HierarchyLookupDTO();
                    productHierarchyDto.setHierarchyId(UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid()));
                    productHierarchyDto.setHierarchyName(dataSelectionDTO.getProductHierarchy());
                    productHierarchy.setValue(productHierarchyDto.getHierarchyName());
                    loadProductLevel(String.valueOf(dataSelectionDTO.getProdHierSid()));
                    if (!StringUtils.isBlank(dataSelectionDTO.getProductHierarchyInnerLevel()) && !Constants.CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getProductHierarchyInnerLevel())) {
                        loadInnerProductLevel(UiUtils.parseStringToInteger(dataSelectionDTO.getProductHierarchyLevel()), UiUtils.parseStringToInteger(dataSelectionDTO.getProductHierarchyInnerLevel()),
                                UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid()));
                    }
                }

                if (dataSelectionDTO.getProdGrpSid() != null && !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getProdGrpSid()))
                        && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(dataSelectionDTO.getProdGrpSid()))) {
                    selectedProductGroupDTO = new GroupDTO();
                    selectedProductGroupDTO.setProductGroupSid(String.valueOf(dataSelectionDTO.getProdGrpSid()));
                    selectedProductGroupDTO.setProductGroupName(dataSelectionDTO.getProductGroup());
                    triggerProdGrpOnView(dataSelectionDTO.getProdGrpSid(), dataSelectionDTO.getProdHierSid(), true);
                }
                if (dataSelectionDTO.getCustomerGrpSid() != null && !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getCustomerGrpSid()))
                        && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(dataSelectionDTO.getCustomerGrpSid()))) {
                    selectedCustomerGroupDTO = new GroupDTO();
                    selectedCustomerGroupDTO.setCustomerGroupSid(String.valueOf(dataSelectionDTO.getCustomerGrpSid()));
                    selectedCustomerGroupDTO.setCustomerGroupName(dataSelectionDTO.getCustomerGroup());
                    triggerCustGrpOnView(dataSelectionDTO.getCustomerGrpSid(), Integer.parseInt(dataSelectionDTO.getCustomerHierSid()), true);
                }

                if (dataSelectionDTO.getSelectedCompanyName() == null || Constants.CommonConstants.NULL.getConstant().equalsIgnoreCase(dataSelectionDTO.getSelectedCompanyName())) {
                    company.setValue(StringUtils.EMPTY);
                } else {
                    company.setValue(dataSelectionDTO.getSelectedCompanyName());
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex + " in initializeFromDto ");
        }
    }

    private void initializeCustomerHierarchy(final int projectionId, final String customerLevel) throws Exception {
        LOGGER.info("Initializing Customer Hierarchy...");
        List<Leveldto> initialCustomerHierarchy = getInitialHierarchy(projectionId, "customer", customerLevel, customerDescriptionMap);
        int forecastLevel = 0;
        forecastLevel = UiUtils.parseStringToInteger(customerLevel);
        customerBeanList.removeAll(customerBeanList);
        for (Leveldto dto : initialCustomerHierarchy) {
            customerBeanList.add(dto.getRelationshipLevelSid());
        }

        for (Leveldto dto : initialCustomerHierarchy) {
            if (dto.getLevelNo() == 1) {
                selectedCustomerContainer.removeAllItems();
                selectedCustomerContainer.addItem(dto);
                selectedCustomerContainer.setChildrenAllowed(dto, true);
            } else {
                for (Object tempdto : selectedCustomerContainer.getItemIds()) {
                    if (dto.getParentNode().contains("~")) {
                        String parentarr[] = dto.getParentNode().split("~");
                        String parentName = parentarr[1];
                        if (getBeanFromId(tempdto).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
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

    private void loadDiscountDdlb(int discountSid, CompanyDdlbDto selectedDiscountDdlbDto) {
        LOGGER.info("Entering loadDiscountDDLB method");
        discount.setPageLength(7);
        discount.setContainerDataSource(discountDdlbLazyContainer);
        if (discountSid != 0) {
            discount.select(selectedDiscountDdlbDto);
        } else {
            discount.select(discountDdlbDefault);
        }
        discount.setNullSelectionItemId(discountDdlbDefault);
        discount.setNullSelectionAllowed(true);
        discount.setImmediate(true);
        discount.setItemCaptionPropertyId("rsNo");
        discountDdlbLazyContainer.setMinFilterLength(0);
        LOGGER.info("End of loadDiscountDDLB method");
    }

    private void loadCustomerLevel(final String hierarchyId) {
        DataSelectionLogic logic = new DataSelectionLogic();
        innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY);
        int levelNo = UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierarchyLevel());
        String selectedLevelName = innerCustLevels.get(levelNo - 1).getLevel();
        customerForecastLevelContainer.removeAllItems();
        for (int i = 1; i <= innerCustLevels.size(); i++) {
            String levelName = innerCustLevels.get(i - 1).getLevel();
            customerForecastLevelContainer.addItem("Level " + i + " - " + levelName);
        }
        sessionDTO.setLowerMostCustomerLevelNo(innerCustLevels.size());
        customerLevel.setContainerDataSource(customerForecastLevelContainer);
        customerLevel.setNullSelectionAllowed(true);
        customerLevel.select("Level " + (levelNo) + " - " + selectedLevelName);
        setSelectedCustomerLevel("Level " + (levelNo) + " - " + selectedLevelName);
    }

    private void loadInnerCustomerLevel(int forecastLevel, int innerLevel, int hierarchyId) {
        DataSelectionLogic logic = new DataSelectionLogic();
        String selectedLevelName = StringUtils.EMPTY;
        customerInnerLevelContainer.removeAllItems();
        innerCustLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
        for (int i = 1; i <= forecastLevel; i++) {
            String levelName = innerCustLevels.get(i - 1).getLevel();
            customerInnerLevelContainer.addItem("Level " + i + " - " + levelName);
            if (i == innerLevel) {
                selectedLevelName = levelName;
            }
        }
        level.setContainerDataSource(customerInnerLevelContainer);
        level.select("Level " + innerLevel + " - " + selectedLevelName);
    }

    private void loadProductLevel(final String hierarchyId) {
        DataSelectionLogic logic = new DataSelectionLogic();
        innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY);
        int levelNo = UiUtils.parseStringToInteger(dataSelectionDTO.getProductHierarchyLevel());
        String selectedLevelName = innerProdLevels.get(levelNo - 1).getLevel();
        productForecastLevelContainer.removeAllItems();
        for (int i = 1; i <= innerProdLevels.size(); i++) {
            String levelName = innerProdLevels.get(i - 1).getLevel();
            productForecastLevelContainer.addItem("Level " + i + " - " + levelName);
        }
        sessionDTO.setLowerMostProductLevelNo(innerProdLevels.size());
        productLevel.setContainerDataSource(productForecastLevelContainer);
        productLevel.setNullSelectionAllowed(true);
        productLevel.select("Level " + (levelNo) + " - " + selectedLevelName);
        setSelectedProductLevel("Level " + (levelNo) + " - " + selectedLevelName);
    }

    private void loadInnerProductLevel(int forecastLevel, int innerLevel, int hierarchyId) {
        DataSelectionLogic logic = new DataSelectionLogic();
        String selectedLevelName = StringUtils.EMPTY;
        productInnerLevelContainer.removeAllItems();
        innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
        for (int i = 1; i <= forecastLevel; i++) {
            String levelName = innerProdLevels.get(i - 1).getLevel();
            productInnerLevelContainer.addItem("Level " + i + " - " + levelName);
            if (i == innerLevel) {
                selectedLevelName = levelName;
            }
        }
        productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
        productlevelDdlb.select("Level " + innerLevel + " - " + selectedLevelName);
    }

    private List<Leveldto> getInitialHierarchy(final int projectionId, String indicator, final String level, final Map<String, String> descriptionMap) {
        DataSelectionLogic logic = new DataSelectionLogic();
        List<Leveldto> initialHierarchy = logic.getRelationShipValues(projectionId, indicator, level, descriptionMap);
        return initialHierarchy;
    }

    public void configureOnTabLoad(int projectionId, SessionDTO session) {
        try {
            customerDescriptionMap = sessionDTO.getCustomerDescription();
            productDescriptionMap = sessionDTO.getProductDescription();
            setFirstTimeLoad(true);
            initializeFromDto();
            setFirstTimeLoad(false);
            if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                initializeCustomerHierarchy(projectionId, String.valueOf(session.getCustomerLevelNumber()));
            }
            initializeProductHierarchy(projectionId, String.valueOf(session.getProductLevelNumber()));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
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
                customerForecastLevelContainer.addItem("Level " + i + " - " + levelName);
            }
            customerLevel.setContainerDataSource(customerForecastLevelContainer);
            customerLevel.select("Level " + (levelNo) + " - " + selectedLevelName);
            setSelectedCustomerLevel("Level " + (levelNo) + " - " + selectedLevelName);
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
                productForecastLevelContainer.addItem("Level " + i + " - " + levelName);
            }
            productLevel.setContainerDataSource(productForecastLevelContainer);
            productLevel.select("Level " + (levelNo) + " - " + selectedLevelName);
            setSelectedProductLevel("Level " + (levelNo) + " - " + selectedLevelName);
        } catch (Exception ex) {
            LOGGER.error(ex+ " loadProductLevel");
        }
    }
}
