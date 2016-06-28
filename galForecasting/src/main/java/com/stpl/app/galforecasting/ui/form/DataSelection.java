
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui.form;

import com.stpl.app.galforecasting.dto.CompanyDdlbDto;
import com.stpl.app.galforecasting.dto.RelationshipDdlbDto;
import com.stpl.app.galforecasting.lazyload.CompanyDdlbCriteria;
import com.stpl.app.galforecasting.lazyload.CompanyDdlbDao;
import com.stpl.app.galforecasting.logic.DataSelectionLogic;
import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.form.lookups.CustomerProductGroupLookup;
import com.stpl.app.galforecasting.ui.form.lookups.HierarchyLookup;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.NULL;
import static com.stpl.app.galforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.galforecasting.utils.DataSelectionUtil;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import static com.stpl.app.utils.Constants.IndicatorConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.form.ForecastDataSelection;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.LazyContainer;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_SEARCH;
import com.vaadin.server.VaadinSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
/**
 *
 * @author sooriya.lakshmanan
 */
public class DataSelection extends ForecastDataSelection {

    final StplSecurity stplSecurity = new StplSecurity();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
    Map<String, AppPermission> functionPsHM;

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelection.class);
    private final CustomFieldGroup dataSelectionBinder;
    private DataSelectionDTO dataSelectionDTO;
    private SessionDTO session;
    private ForecastForm form;
    private boolean firstTimeLoad = true;
    private List<String> companySidsForLazyCompany = null;
    private boolean dismantelCustomerSelection = true;
    private boolean dismantelProductSelection = true;
    final DataSelectionForm dataSelectionForm;
    private boolean updateOnTabChange = false;
    private boolean reloadAfterUpdate = false;
    private boolean valid = true;
    private LazyContainer discountDdlbLazyContainer;
    CompanyDdlbDto discountDdlbDefault = new CompanyDdlbDto(0, SELECT_ONE, true);
    CompanyDdlbDto discountDTO = null;
    List<Integer> customerBeanList = new ArrayList<Integer>();
    List<Integer> productBeanList = new ArrayList<Integer>();
    static List<Integer> productBeanLisTemp = new ArrayList<Integer>();
    List<String> productHierarchyNos = new ArrayList<String>();
    DataSelectionLogic dsLogic = new DataSelectionLogic();

    public DataSelection(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, SessionDTO session,
            ForecastForm form, String screenName, final DataSelectionForm dataSelectionForm) throws Exception {
        super(dataSelectionBinder, screenName, false);
        this.session = session;
        this.dataSelectionBinder = dataSelectionBinder;
        this.dataSelectionDTO = dataSelectionDTO;
        this.form = form;
        this.screenName = screenName;
        this.dataSelectionForm = dataSelectionForm;
        customerDescriptionMap = session.getCustomerDescription();
        productDescriptionMap = session.getProductDescription();
        configureTimeDdlb();
        configureOnLoading(dataSelectionDTO.getProjectionId(), dataSelectionDTO);
        configureFields();
        initializeCompanyDdlb();
        configureForView();
        if (ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            configureOnViewMode();
        }
    }

    public DataSelection(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, SessionDTO session, String screenName,
            final DataSelectionForm dataSelectionForm) throws Exception {
        super(dataSelectionBinder, screenName, false);
        this.session = session;
        this.dataSelectionBinder = dataSelectionBinder;
        this.dataSelectionDTO = dataSelectionDTO;
        this.screenName = screenName;
        this.dataSelectionForm = dataSelectionForm;
        customerDescriptionMap = session.getCustomerDescription();
        productDescriptionMap = session.getProductDescription();
        deductionLevel.addItem(session.getDeductionLevel());
        deductionLevel.select(session.getDeductionLevel());
        deductionLevel.setImmediate(true);
        deductionValue.addItem(session.getDeductionValue());
        deductionValue.select(session.getDeductionValue());
        deductionValue.setImmediate(true);
        configureTimeDdlb();
        configureOnLoading(dataSelectionDTO.getProjectionId(), dataSelectionDTO);
        configureFields();
        initializeCompanyDdlb();
        configureForView();
        configureOnViewMode();

    }

    private void configureTimeDdlb() throws Exception {
        String from = DataSelectionUtil.getTimePeriod(dataSelectionDTO.getFromDate());
        String to = DataSelectionUtil.getTimePeriod(dataSelectionDTO.getToDate());
        fromPeriod.select(from);
        toPeriod.select(to);
        toPeriod.setReadOnly(true);
        fromPeriod.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                setUpdateOnTabChange(Boolean.TRUE);
                session.setFromPeriod(String.valueOf(fromPeriod.getValue()));
                session.setFromDate(dataSelectionDTO.getFromDate());
                session.setFromDateChanged(Boolean.TRUE);
                productBeanLisTemp.removeAll(productBeanLisTemp);
                productHierarchyNos.removeAll(productHierarchyNos);
                for (Leveldto dto : selectedProductContainer.getItemIds()) {

                    productBeanLisTemp.add(dto.getRelationshipLevelSid());
                    productHierarchyNos.add(dto.getHierarchyNo());

                }
                setProductBeanLisTemp(productBeanLisTemp);
            }
        });
        toPeriod.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                session.setSaveFlag(true);
                session.setToPeriod(dataSelectionDTO.getToPeriod());
                session.setToDate(dataSelectionDTO.getToDate());
            }
        });
    }

    private void configureForView() {
        resultsTablePanel.setVisible(false);
        buttonLay.setVisible(false);
        finalBtn.setVisible(false);
        modeOption.setEnabled(false);
        discount.setEnabled(false);
        if (dataSelectionForm != null) {
            publicView.setValue(dataSelectionForm.getPublicViewName() != null ? dataSelectionForm.getPublicViewName() : StringUtils.EMPTY);
            privateView.setValue(dataSelectionForm.getPrivateViewName() != null ? dataSelectionForm.getPrivateViewName() : StringUtils.EMPTY);
        }
        privateView.setEnabled(false);
        publicView.setEnabled(false);
        discount.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                LOGGER.info("Discount Value Change");
                level.select(Constant.SELECT_ONE);
                availableCustomerContainer.removeAllItems();
                availableCustomer.removeAllItems();
                selectedCustomerContainer.removeAllItems();
                selectedCustomer.removeAllItems();
            }
        });

        customerLevel.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                if (!firstTimeLoad) {
                    if (!selectedCustomerContainer.getItemIds().isEmpty() && customerLevelListenerFlag) {
                        final String customerLevelValue = getSelectedCustomerLevel();
                        new AbstractNotificationUtils() {

                            public void noMethod() {
                                // do nothing
                                if (!StringUtils.isBlank(customerLevelValue)) {
                                    customerLevelListenerFlag = false;
                                    customerLevel.select(customerLevelValue);
                                    customerLevelListenerFlag = true;
                                }
                            }

                            @Override
                            /**
                             * The method is triggered when Yes button of
                             * themessage box is pressed .
                             *
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */

                            public void yesMethod() {
                                customerLevelValueChange(event);
                                setUpdateOnTabChange(true);
                            }
                        }.getConfirmationMessage("Confirm Change", "You have selected a new Forecast Level. Are you sure you want to proceed? You will lose the current Customer/Product hierarchies if you continue.");
                    } else {
                        if (customerLevelListenerFlag) {
                            customerLevelValueChange(event);
                        }
                    }
                } else {
                    customerLevelValueChange(event);
                }
            }
        });

        productLevel.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                if (!firstTimeLoad) {
                    if (!selectedProductContainer.getItemIds().isEmpty() && productLevelListenerFlag) {
                        final String productLevelValue = getSelectedProductLevel();
                        new AbstractNotificationUtils() {
                            public void noMethod() {
                                // do nothing
                                if (!StringUtils.isBlank(productLevelValue)) {
                                    productLevelListenerFlag = false;
                                    productLevel.select(productLevelValue);
                                    productLevelListenerFlag = true;
                                }
                            }

                            @Override
                            /**
                             * The method is triggered when Yes button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            public void yesMethod() {
                                productLevelValueChange(event);
                                setUpdateOnTabChange(true);
                            }
                        }.getConfirmationMessage("Confirm Change", "You have selected a new Forecast Level. Are you sure you want to proceed? You will lose the current Customer/Product hierarchies if you continue.");
                    } else {
                        if (productLevelListenerFlag) {
                            productLevelValueChange(event);
                        }
                    }
                } else {
                    productLevelValueChange(event);
                }
            }
        });
        projectionName.setValue(dataSelectionDTO.getProjectionName());
        description.setValue(dataSelectionDTO.getDescription());
    }

    private void customerLevelValueChange(Property.ValueChangeEvent event) {
        customerInnerLevelContainer.removeAllItems();
        if (event.getProperty().getValue() != null && !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
            String selectedLevel = String.valueOf(event.getProperty().getValue());
            String val[] = selectedLevel.split(" ");
            int forecastLevel = Integer.parseInt(val[1]);
            setSelectedCustomerLevel(selectedLevel);
            session.setCustomerLevelNumber(String.valueOf(forecastLevel));
            customerInnerLevelContainer.removeAllItems();
            if (!firstTimeLoad) {
                selectedCustomer.removeAllItems();
                selectedCustomerContainer.removeAllItems();
            }
            customerBeanList.removeAll(customerBeanList);
            for (int i = 1; i <= forecastLevel; i++) {
                String levelName = innerCustLevels.get(i - 1).getLevel();
                customerInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
            }
            level.setContainerDataSource(customerInnerLevelContainer);
            setCustomerLevelNullSelection();
        } else if (event.getProperty().getValue() == null || (event.getProperty().getValue() != null && SELECT_ONE.equals(String.valueOf(event.getProperty().getValue())))) {
            customerInnerLevelContainer.removeAllItems();
            customerInnerLevelContainer.addItem(SELECT_ONE);
            availableCustomer.removeAllItems();
            availableCustomerContainer.removeAllItems();
            selectedCustomer.removeAllItems();
            selectedCustomerContainer.removeAllItems();
            customerBeanList.removeAll(customerBeanList);
            setCustomerLevelNullSelection();
        }
    }

    private void productLevelValueChange(Property.ValueChangeEvent event) {
        productInnerLevelContainer.removeAllItems();
        if (event.getProperty().getValue() != null && !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
            String selectedLevel = String.valueOf(event.getProperty().getValue());
            DataSelectionLogic logic = new DataSelectionLogic();
            setSelectedProductLevel(selectedLevel);
            int hierarchyId = 0;
            String hierarchyName = StringUtils.EMPTY;
            if (productHierarchyDto != null && !StringUtils.isBlank(productHierarchyDto.getHierarchyName())) {
                hierarchyName = productHierarchyDto.getHierarchyName();
            }
            if (productHierarchyDto == null) {
                hierarchyId = UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid());
            } else {
                hierarchyId = productHierarchyDto.getHierarchyId();
            }
            logic.loadCustomerForecastLevel(hierarchyId, hierarchyName);
            String val[] = selectedLevel.split(" ");
            int forecastLevel = Integer.parseInt(val[1]);
            session.setProductLevelNumber(String.valueOf(forecastLevel));
            productInnerLevelContainer.removeAllItems();
            productBeanList.removeAll(productBeanList);
            if (!firstTimeLoad) {
                selectedProduct.removeAllItems();
                selectedProductContainer.removeAllItems();
            }
            for (int i = 1; i <= forecastLevel; i++) {
                String levelName = innerProdLevels.get(i - 1).getLevel();
                productInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
            }
            productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
            setProductLevelNullSelection();
        } else if (event.getProperty().getValue() == null || (event.getProperty().getValue() != null && SELECT_ONE.equals(String.valueOf(event.getProperty().getValue())))) {
            productInnerLevelContainer.removeAllItems();
            productInnerLevelContainer.addItem(SELECT_ONE);
            availableProduct.removeAllItems();
            availableProductContainer.removeAllItems();
            selectedProduct.removeAllItems();
            selectedProductContainer.removeAllItems();
            productBeanList.removeAll(productBeanList);
            setProductLevelNullSelection();
        }
    }

    public void configureOnLoading(int projectionId, DataSelectionDTO dataSelectionDTO) throws Exception {
        session.setFromPeriod(dataSelectionDTO.getFromPeriod());
        session.setToPeriod(dataSelectionDTO.getToPeriod());
        session.setFromDate(dataSelectionDTO.getFromDate());
        session.setToDate(dataSelectionDTO.getToDate());
        session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
        if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
            configureStartAndEndPeriods();
        } else {
            productLevel.setVisible(false);
            customerLevel.setVisible(false);
            productForecastLevelLabel.setVisible(false);
            customerForecastLevelLabel.setVisible(false);
        }
        initializeProductHierarchy(projectionId, String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
        setFirstTimeLoad(true);
        initializeFromDto();
        setFirstTimeLoad(false);
        if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
            session.setCustomerHierarchyId(Integer.valueOf(dataSelectionDTO.getCustomerHierSid()));
            initializeCustomerHierarchy(projectionId, String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
        }
    }

    public void configureOnTabLoad(int projectionId, DataSelectionDTO dataSelectionDTO) {
        try {
            customerDescriptionMap = session.getCustomerDescription();
            productDescriptionMap = session.getProductDescription();
            setFirstTimeLoad(true);
            initializeFromDto();
            setFirstTimeLoad(false);
            if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                initializeCustomerHierarchy(projectionId, String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
            }
            initializeProductHierarchy(projectionId, String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
        } catch (Exception e) { 
            LOGGER.error(e);
        }
    }

    private void configureStartAndEndPeriods() {
        CommonUtils.loadHistory(MONTHLY.getConstant(), MONTHS.getConstant(), session);
        CommonUtils.loadHistory(QUARTERLY.getConstant(), QUARTERS.getConstant(), session);
        CommonUtils.loadHistory(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant(), session);
        CommonUtils.loadHistory(ANNUALLY.getConstant(), YEAR.getConstant(), session);
        CommonUtils.getProjectionNumber(MONTHLY.getConstant(), session);
        CommonUtils.getProjectionNumber(QUARTERLY.getConstant(), session);
        CommonUtils.getProjectionNumber(SEMI_ANNUALLY.getConstant(), session);
        CommonUtils.getProjectionNumber(ANNUALLY.getConstant(), session);
    }

    public void setFirstTimeLoad(boolean firstTimeLoad) {
        this.firstTimeLoad = firstTimeLoad;
    }

    private void initializeCompanyDdlb() {
        List<String> companySids = DataSelectionUtil.getCompanySidFromHierarchy(getCompanySidFromHierarchy(false, true), screenName);
        try {
            setCompanySidsForLazyCompany(companySids);
            loadCompanyDdlb(companySids);
        } catch (Exception ex) { 

            LOGGER.error(ex);
        }
    }

    private void loadCompanyDdlb(final List<String> companySids) {
        try {

            DataSelectionLogic logic = new DataSelectionLogic();
            List<CompanyDdlbDto> companyList = logic.getCompanies(companySids);
            CompanyDdlbDto selectedCompanyDdlbDto = null;
            if (dataSelectionDTO.getCompanySid() != null && !StringUtils.isBlank(dataSelectionDTO.getCompanySid())
                    && !Constants.CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getCompanySid()) && !DASH.equals(dataSelectionDTO.getCompanySid())) {
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
                    if (Constant.COMPANY_SMALL.equalsIgnoreCase(dto.getLevel()) || "GL Comp".contains(dto.getLevel()) || "GL Company".contains(dto.getLevel())) {
                        companyLevel = dto;
                        break;
                    }
                }
                List<Integer> selectedLevelSids = null;
                if (loadFromSelected) {
                    selectedLevelSids = DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds());
                }

                if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
                    discountDTO = (CompanyDdlbDto) discount.getValue();
                }

                if (companyLevel != null) {

                    innerLevelValues = logic.loadInnerLevel(companyLevel.getLevel(), productHierarchyDto.getHierarchyId(),
                            selectedLevelSids, false, companyLevel.getFieldName(), relationshipSid, productDescriptionMap, StringUtils.EMPTY, screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, companyLevel.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY);
                }
            }
        } catch (Exception ex) { 

            LOGGER.error(ex + " in getCompanySidFromHierarchy");
        }
        return innerLevelValues;
    }

    /**
     * Load customer hierarchy.
     *
     * @param projectionId
     * @throws java.lang.Exception
     */
    private void initializeCustomerHierarchy(final int projectionId, final String customerLevel) throws Exception {
        LOGGER.info("Initializing Customer Hierarchy...");
        List<Leveldto> initialCustomerHierarchy = getInitialHierarchy(projectionId, "customer", customerLevel, session.getCustomerDescription());
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

    /**
     * Load customer hierarchy.
     *
     * @param projectionId
     * @throws java.lang.Exception
     */
    private void initializeProductHierarchy(final int projectionId, final String productLevel) throws Exception {
        DataSelectionLogic logic = new DataSelectionLogic();
        int forecastLevel = 0;
        forecastLevel = UiUtils.parseStringToInteger(productLevel);
        List<Leveldto> reslistOne;
        reslistOne = logic.getRelationShipValues(projectionId, "product", productLevel, productDescriptionMap);
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

    public void setCompanySidsForLazyCompany(List<String> companySidsForLazyCompany) {
        this.companySidsForLazyCompany = companySidsForLazyCompany;
    }

    private void loadCustomerLevel(final String hierarchyId) {
        DataSelectionLogic logic = new DataSelectionLogic();
        innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY);
        int levelNo = UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierarchyLevel());
        String selectedLevelName = innerCustLevels.get(levelNo - 1).getLevel();
        customerForecastLevelContainer.removeAllItems();
        for (int i = 1; i <= innerCustLevels.size(); i++) {
            String levelName = innerCustLevels.get(i - 1).getLevel();
            customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
        }
        session.setLowerMostCustomerLevelNo(innerCustLevels.size());
        customerLevel.setContainerDataSource(customerForecastLevelContainer);
        customerLevel.setNullSelectionAllowed(true);
        customerLevel.select(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
        setSelectedCustomerLevel(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
    }

    private void initializeFromDto() {
        try {
            if (dataSelectionDTO != null) {
                session.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
                session.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));

                if (dataSelectionDTO.getCustomerHierSid() != null && !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))
                        && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))) {

                    RelationshipDdlbDto selectedRelationshipDdlbDto = null;
                    if (!StringUtils.isBlank(dataSelectionDTO.getCustRelationshipBuilderSid()) && !Constants.CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getCustRelationshipBuilderSid())) {
                        selectedRelationshipDdlbDto = new RelationshipDdlbDto();
                        selectedRelationshipDdlbDto.setRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
                        session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
                    }
                    DataSelectionForm.loadRelationDdlb(UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid()), selectedRelationshipDdlbDto, customerRelation);
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
                        session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
                    }
                    DataSelectionForm.loadRelationDdlb(UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid()), selectedRelationshipDdlbDto, productRelation);
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
                    triggerProdGrpOnView(dataSelectionDTO.getProdGrpSid(), Integer.parseInt(dataSelectionDTO.getProdHierSid()), true);
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

                if (!String.valueOf(dataSelectionDTO.getDiscount()).equals(Constant.NULL)) {
                    CompanyDdlbDto selectedDiscountDTO = new CompanyDdlbDto(dataSelectionDTO.getDiscountSid(), dataSelectionDTO.getDiscount(), true);
                    loadDiscountDdlb(dataSelectionDTO.getDiscountSid(), selectedDiscountDTO);
                }
            }

        } catch (Exception ex) { 
            LOGGER.error(ex + " in initializeFromDto ");
        }
    }

    /**
     * Manual trigger and processing of customer group select button logic Code
     * based on customer group select button logic Any change made there, should
     * be made here accordingly
     *
     * @param customerGrpSid
     */
    private void triggerCustGrpOnView(String customerGrpSid, final int hierarchyId, final boolean filter) {

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

    private void loadProductLevel(final String hierarchyId) {
        DataSelectionLogic logic = new DataSelectionLogic();
        innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY);
        int levelNo = UiUtils.parseStringToInteger(dataSelectionDTO.getProductHierarchyLevel());
        String selectedLevelName = innerProdLevels.get(levelNo - 1).getLevel();
        productForecastLevelContainer.removeAllItems();
        for (int i = 1; i <= innerProdLevels.size(); i++) {
            String levelName = innerProdLevels.get(i - 1).getLevel();
            productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
        }
        session.setLowerMostProductLevelNo(innerProdLevels.size());
        productLevel.setContainerDataSource(productForecastLevelContainer);
        productLevel.setNullSelectionAllowed(true);
        productLevel.select(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
        setSelectedProductLevel(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
    }

    /**
     * Manual trigger and processing of product group select button logic Code
     * based on product group select button logic Any change made there, should
     * be made here accordingly
     *
     * @param productGrpSid
     */
    private void triggerProdGrpOnView(String productGrpSid, final int hierarchyId, final boolean filter) {
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

    private void loadInnerCustomerLevel(int forecastLevel, int innerLevel, int hierarchyId) {
        DataSelectionLogic logic = new DataSelectionLogic();
        String selectedLevelName = StringUtils.EMPTY;
        customerInnerLevelContainer.removeAllItems();
        innerCustLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
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
        DataSelectionLogic logic = new DataSelectionLogic();
        String selectedLevelName = StringUtils.EMPTY;
        productInnerLevelContainer.removeAllItems();
        innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
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

    public boolean isDataSelectionValid() {
        if (!screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {

            if (getSelectedCustomers() != null && !getSelectedCustomers().isEmpty() && getSelectedProducts() != null && !getSelectedProducts().isEmpty()
                    && (company.getValue() != null && !Constant.SELECT_ONE.equals(company.getValue()))) {
                setValid(Boolean.TRUE);
            } else {
                setValid(Boolean.FALSE);
            }
        } else {
            isReturnsDataSelectionValid();
        }
        return isValid();
    }

    public boolean isReturnsDataSelectionValid() {

        if (getSelectedProducts() != null && !getSelectedProducts().isEmpty()
                && (company.getValue() != null && !Constant.SELECT_ONE.equals(company.getValue()))) {
            setValid(Boolean.TRUE);
        } else {
            setValid(Boolean.FALSE);
        }
        return isValid();
    }

    public boolean isUpdateOnTabChange() {
        return updateOnTabChange;
    }

    public void setUpdateOnTabChange(boolean updateOnTabChange) {
        session.setSaveFlag(updateOnTabChange);
        this.updateOnTabChange = updateOnTabChange;
    }

    public boolean isReloadAfterUpdate() {
        return reloadAfterUpdate;
    }

    public void setReloadAfterUpdate(boolean reloadAfterUpdate) {
        this.reloadAfterUpdate = reloadAfterUpdate;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<Leveldto> getSelectedCustomers() {
        return selectedCustomerContainer.getItemIds();
    }

    public List<Leveldto> getSelectedProducts() {
        return selectedProductContainer.getItemIds();
    }

    public int getTabNumber() {
        return Constant.ZERO;
    }

    public void updateBasicsProjectionMaster() throws PortalException, SystemException, Exception {
        NonMandatedLogic logic = new NonMandatedLogic();
        dataSelectionDTO = bindDataselectionDtoToSave();
        logic.updateBasicsProjectionMaster(dataSelectionDTO, session.getProjectionId(), true);
    }

    public void updateDataSelection() throws SystemException, Exception {
        LOGGER.info("updateDataSelection starts");
        dataSelectionDTO = bindDataselectionDtoToSave();
        NonMandatedLogic logic = new NonMandatedLogic();
        logic.updateProjection(dataSelectionDTO, session.getProjectionId(), true, screenName);
        session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
        session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
        session.setCustomerDescription(customerDescriptionMap);
        session.setProductDescription(productDescriptionMap);
        LOGGER.info("updateDataSelection ends");
    }

    public DataSelectionDTO bindDataselectionDtoToSave() {
        LOGGER.info("bindDataselectionDtoToSave starts");
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
            dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((customerLevel.getValue() == null ? StringUtils.EMPTY : customerLevel.getValue().toString()))));
            dataSelectionDTO.setProductHierarchyLevel(String.valueOf(UiUtils.parseStringToInteger((productLevel.getValue() == null ? StringUtils.EMPTY : productLevel.getValue().toString()))));
            dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(UiUtils.parseStringToInteger((level.getValue() == null ? StringUtils.EMPTY : level.getValue().toString()))));
            dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(UiUtils.parseStringToInteger((productlevelDdlb.getValue() == null ? StringUtils.EMPTY : productlevelDdlb.getValue().toString()))));
            dataSelectionDTO.setProjectionName(projectionName.getValue());
            dataSelectionDTO.setDescription(description.getValue());

        } catch (ParseException ex) {
            LOGGER.error(ex + " in binding for save, can't parse dates");
        }
        LOGGER.info("bindDataselectionDtoToSave ends");
        return dataSelectionDTO;
    }

    public void updateDataSelectionProjectionTables(final String propertyName) throws SystemException, Exception {
        DataSelectionLogic dsLogic = new DataSelectionLogic();
        List<Leveldto> initialCustomerHierarchy = getInitialHierarchy(session.getProjectionId(), "customer", dataSelectionDTO.getCustomerHierarchyLevel(), customerDescriptionMap);
        List<Leveldto> initialProductHierarchy = getInitialHierarchy(session.getProjectionId(), "product", dataSelectionDTO.getProductHierarchyLevel(), productDescriptionMap);
        List<String> customerRemovedLevels = DataSelectionUtil.getRemovedLevelsHno(initialCustomerHierarchy, getSelectedCustomers());
        List<String> productRemovedLevels = DataSelectionUtil.getRemovedLevelsHno(initialProductHierarchy, getSelectedProducts());
        List<Integer> projectionDetailsSid = new ArrayList<Integer>();
        if (!customerRemovedLevels.isEmpty()) {
            projectionDetailsSid.addAll((List<Integer>) HelperTableLocalServiceUtil.executeSelectQuery(SQlUtil.getQuery("getProjectionDetailsQuery").replace("@HIERARCHY_TABLE", "PROJECTION_CUST_HIERARCHY")
                    .replace("@PROJECTION_SID", String.valueOf(session.getProjectionId()))
                    .replace("@HIERARCHYNO", CommonUtils.CollectionToString(customerRemovedLevels, true))));
        }
        if (!productRemovedLevels.isEmpty()) {
            projectionDetailsSid.addAll(((List<Integer>) HelperTableLocalServiceUtil.executeSelectQuery(SQlUtil.getQuery("getProjectionDetailsQuery").replace("@HIERARCHY_TABLE", "PROJECTION_PROD_HIERARCHY")
                    .replace("@PROJECTION_SID", String.valueOf(session.getProjectionId()))
                    .replace("@HIERARCHYNO", CommonUtils.CollectionToString(productRemovedLevels, true)))));
        }
        if (customerRemovedLevels != null && !customerRemovedLevels.isEmpty()) {
            dsLogic.deleteTempOnUpdate("PROJECTION_CUST_HIERARCHY", session.getProjectionId(), UiUtils.stringListToString(customerRemovedLevels));
        }
        if (productRemovedLevels != null && !productRemovedLevels.isEmpty()) {
            dsLogic.deleteTempOnUpdate("PROJECTION_PROD_HIERARCHY", session.getProjectionId(), UiUtils.stringListToString(productRemovedLevels));
        }

        setRelationshipBuilderSids(String.valueOf(customerRelation.getValue()));
        setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
        dsLogic.updateCustomerHierarchyLogic(getSelectedCustomers(), DataSelectionUtil.getEndLevelHierNo(dataSelectionForm.getCustomerHierarchyEndLevels(selectedCustomerContainer)), session.getProjectionId(),
                DataSelectionUtil.getRemovedLevelsRsid(initialCustomerHierarchy, getSelectedCustomers()), DataSelectionUtil.getNewLevelRsid(initialCustomerHierarchy, getSelectedCustomers()));
        dsLogic.updateProductHierarchyLogic(getSelectedProducts(), DataSelectionUtil.getEndLevelHierNo(dataSelectionForm.getProductHierarchyEndLevels(selectedProductContainer)), session.getProjectionId(),
                DataSelectionUtil.getRemovedLevelsRsid(initialProductHierarchy, getSelectedProducts()), DataSelectionUtil.getNewLevelRsid(initialProductHierarchy, getSelectedProducts()));
        dsLogic.insertToCcpMap(relationshipBuilderSids, StringUtils.EMPTY);
        dsLogic.updateCcpLogic(dataSelectionForm.getCustomerHierarchyEndLevels(selectedCustomerContainer), dataSelectionForm.getProductHierarchyEndLevelsHierNo(selectedProductContainer), "customer", session.getProjectionId());

        if (!projectionDetailsSid.isEmpty()) {
            deleteProjectionDetailstable(CommonUtils.CollectionToString(projectionDetailsSid, true), propertyName);
        }
    }

    private List<Leveldto> getInitialHierarchy(final int projectionId, String indicator, final String level, final Map<String, String> descriptionMap) {
        DataSelectionLogic logic = new DataSelectionLogic();
        List<Leveldto> initialHierarchy = logic.getRelationShipValues(projectionId, indicator, level, descriptionMap);
        return initialHierarchy;
    }

    private void setRelationshipBuilderSids(String rbSid) {

        relationshipBuilderSids.add(rbSid);
    }

    @Override
    protected void productLevelDdlbValueChange(String selectedLevel, boolean flag) {
        loadFilteredProductSelection(selectedLevel, false);
    }

    private List<Leveldto> getItemSidFromHierarchy() {
        List<Leveldto> innerLevelValues = null;
        try {
            int hierarchyId = 0;
            DataSelectionLogic logic = new DataSelectionLogic();
            if (productHierarchyDto == null) {
                hierarchyId = UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid());
            } else {
                hierarchyId = productHierarchyDto.getHierarchyId();
            }
            if (innerProdLevels == null || innerProdLevels.isEmpty() || productHierarchyDto == null) {
                innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
            }
            if ((productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) && (innerProdLevels != null)) {

                String relationshipSid = String.valueOf(productRelation.getValue());
                Leveldto ndcLevel = null;
                for (Leveldto dto : innerProdLevels) {
                    if (Constant.NDC.equalsIgnoreCase(dto.getLevel()) || "Item".equalsIgnoreCase(dto.getLevel())) {
                        ndcLevel = dto;
                        break;
                    }
                }
                if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
                    discountDTO = (CompanyDdlbDto) discount.getValue();
                }
                if (ndcLevel != null) {
                    innerLevelValues = logic.loadInnerLevel(ndcLevel.getLevel(), productHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds()), true, ndcLevel.getFieldName(), relationshipSid,
                            productDescriptionMap, INDICATOR_LEVEL_NDC.getConstant(), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, ndcLevel.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY);
                }
            }
        } catch (Exception ex) { 
            LOGGER.error(ex + " in getItemSidFromHierarchy");
        }
        return innerLevelValues;
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
                    dataSelectionForm.loadRelationDdlb(customerHierarchyDto.getHierarchyId(), null, customerRelation);
                    innerCustLevels = logic.loadCustomerForecastLevel(lookupDto.getHierarchyId(), lookupDto.getHierarchyName());
                    customerForecastLevelContainer.removeAllItems();
                    for (int i = 1; i <= innerCustLevels.size(); i++) {
                        String levelName = innerCustLevels.get(i - 1).getLevel();
                        customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
                    }
                    resetCustomerLevel();
                    resetSecondCustomerLevel();
                    setCustomerRelationNullSelection();
                    availableCustomer.removeAllItems();
                    availableCustomerContainer.removeAllItems();
                    selectedCustomer.removeAllItems();
                    selectedCustomerContainer.removeAllItems();
                    customerGroup.setValue(StringUtils.EMPTY);
                    dataSelectionDTO.setCustomerGrpSid(null);

                    groupFilteredCompanies = null;
                    customerLevel.setContainerDataSource(customerForecastLevelContainer);
                    setCustomerForecastLevelNullSelection();
                    setUpdateOnTabChange(Boolean.TRUE);
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
                    dataSelectionForm.loadRelationDdlb(productHierarchyDto.getHierarchyId(), null, productRelation);
                    innerProdLevels = logic.loadCustomerForecastLevel(lookupDto.getHierarchyId(), lookupDto.getHierarchyName());
                    productForecastLevelContainer.removeAllItems();
                    for (int i = 1; i <= innerProdLevels.size(); i++) {
                        String levelName = innerProdLevels.get(i - 1).getLevel();
                        productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
                    }
                    loadCompanyLogic();
                    resetProductLevel();
                    resetSecondProductLevel();
                    setProductRelationNullSelection();
                    availableProduct.removeAllItems();
                    availableProductContainer.removeAllItems();
                    selectedProduct.removeAllItems();
                    selectedProductContainer.removeAllItems();
                    dataSelectionDTO.setProdGrpSid(null);
                    dataSelectionDTO.setCompanySid(null);
                    groupFilteredItems = null;
                    productLevel.setContainerDataSource(productForecastLevelContainer);
                    setProductForecastLevelNullSelection();
                    setUpdateOnTabChange(Boolean.TRUE);
                }
            }
        }
        );
    }

    private void loadCompanyLogic() {
        try {
            List<String> companySids = DataSelectionUtil.getCompanySidFromHierarchy(getCompanySidFromHierarchy(false, true), screenName);
            setCompanySidsForLazyCompany(companySids);
            loadCompanyDdlb(companySids);
        } catch (Exception ex) { 
            LOGGER.error(ex + " loadCompanyLogic");
        }
    }

    @Override
    protected void modeOptionChange(boolean isAddMode) {
        try {
//            if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
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

    }

    @Override
    protected void levelValueChangeListener(Object value) {
        availableCustomerContainer.removeAllItems();
        String levelName = "Level";
        try {
            int forecastLevel = 0;
            List<Leveldto> custVlues = null;
            boolean isNdc = false;
            if (value != null && !Constants.CommonConstants.NULL.getConstant().equals(value) && !SELECT_ONE.equals(value) && customerRelation.getValue() != null) {
                String selectedLevel = String.valueOf(value);

                if (customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) {
                    String relationshipSid = String.valueOf(customerRelation.getValue());
                    int hierarchyId = 0;
                    if (customerHierarchyDto == null) {
                        hierarchyId = UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid());
                    } else {
                        hierarchyId = customerHierarchyDto.getHierarchyId();
                    }
                    DataSelectionLogic logic = new DataSelectionLogic();
                    String val[] = selectedLevel.split(" ");
                    forecastLevel = Integer.parseInt(val[1]);
                    if (innerCustLevels.isEmpty() || customerHierarchyDto == null) {
                        innerCustLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
                    }
                    Leveldto tempDto = (Leveldto) innerCustLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        levelName = tempDto.getLevel();
                    }
                    if (tempDto.getLevel() != null && Constant.NDC.equalsIgnoreCase(tempDto.getLevel()) && Constant.ITEM_MASTER.equals(tempDto.getTableName())) {
                        isNdc = true;
                    } else {
                        isNdc = false;
                    }
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
                        discountDTO = (CompanyDdlbDto) discount.getValue();
                    }
                    ExtTreeContainer<Leveldto> dumbyCustomerContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);

                    custVlues = logic.loadInnerLevel(tempDto.getLevel(), hierarchyId,
                            DataSelectionUtil.getSelectedRelationshipLevelSids(dumbyCustomerContainer.getItemIds()), isNdc, tempDto.getFieldName(), relationshipSid, customerDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY);
                    if (groupFilteredCompanies != null && Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(tempDto.getTableName()))
                            && (Constant.TRADING_PARTNER.equals(levelName) || Constant.COMPANY_SMALL.equals(levelName) || Constant.CUSTOMER_SMALL.equals(levelName))) {
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
    }

    @Override
    protected void customerRelationValueChange(Object value) {
        if (value != null) {
            try {
                DataSelectionLogic logic = new DataSelectionLogic();
                if (!firstTimeLoad) {
                    selectedCustomer.removeAllItems();
                    selectedCustomerContainer.removeAllItems();
                }
                availableCustomer.removeAllItems();
                availableCustomerContainer.removeAllItems();
                setCustomerForecastLevelNullSelection();
                setCustomerLevelNullSelection();
                if (!isFirstTimeLoad()) {
                    customerDescriptionMap = logic.getLevelValueMap(String.valueOf(customerRelation.getValue()));
                }
            } catch (Exception ex) { 
                LOGGER.error(ex + " in customerRelation value change");
            }
        } else if (value == null || (value != null && SELECT_ONE.equals(String.valueOf(value)))) {
            customerInnerLevelContainer.removeAllItems();
            availableCustomer.removeAllItems();
            availableCustomerContainer.removeAllItems();
            selectedCustomer.removeAllItems();
            selectedCustomerContainer.removeAllItems();
            setCustomerForecastLevelNullSelection();
            setCustomerLevelNullSelection();
            if (!isFirstTimeLoad()) {
                customerDescriptionMap = null;
            }
        }
        if (!isFirstTimeLoad()) {
            customerGroup.setValue(StringUtils.EMPTY);
            selectedCustomerGroupDTO = new GroupDTO();
            dataSelectionDTO.setCustomerGrpSid(null);
        }
        dataSelectionDTO.setCustomerDescription(customerDescriptionMap);
    }

    public boolean isFirstTimeLoad() {
        return firstTimeLoad;
    }

    @Override
    protected void productRelationValueChange(Object value) {
        if (value != null) {
            try {
                DataSelectionLogic logic = new DataSelectionLogic();
                if (!firstTimeLoad) {
                    selectedProduct.removeAllItems();
                    selectedProductContainer.removeAllItems();
                }
                availableProduct.removeAllItems();
                availableProductContainer.removeAllItems();
                setProductForecastLevelNullSelection();
                setProductLevelNullSelection();
                if (!firstTimeLoad) {
                    productDescriptionMap = logic.getLevelValueMap(String.valueOf(productRelation.getValue()));
                    loadCompanyLogic();
                }
            } catch (Exception ex) { 
                LOGGER.error(ex + " in productRelation value change");
            }

        } else if (value == null || (value != null && SELECT_ONE.equals(String.valueOf(value)))) {
            availableProduct.removeAllItems();
            availableProductContainer.removeAllItems();
            selectedProduct.removeAllItems();
            selectedProductContainer.removeAllItems();
            productInnerLevelContainer.removeAllItems();
            setProductForecastLevelNullSelection();
            setProductLevelNullSelection();
            if (!isFirstTimeLoad()) {
                productDescriptionMap = null;
            }
        }
        if (!isFirstTimeLoad()) {
            dataSelectionDTO.setProdGrpSid(null);
            productGroup.setValue(StringUtils.EMPTY);
            selectedProductGroupDTO = new GroupDTO();
        }
        dataSelectionDTO.setProductDescription(productDescriptionMap);

    }

    @Override
    protected void generateButtonLogic() {

    }

    @Override
    protected void searchButtonLogic() {

    }

    @Override
    protected void editButtonLogic() {

    }

    @Override
    protected void viewButtonLogic() {

    }

    @Override
    protected void deleteButtonLogic() {

    }

    @Override
    protected void configureTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Object object, Object object0, String constant, String screenName) {
        try {
            DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), screenName);
        } catch (Exception ex) { 
            LOGGER.error(ex);
        }
    }

    @Override
    protected void loadPublicView() {

    }

    @Override
    protected void loadPrivateView() {

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

                    if ((tempDto.getLevel() != null && (Constant.NDC.equalsIgnoreCase(tempDto.getLevel()) || "Item".equalsIgnoreCase(tempDto.getLevel()) || Constant.PRODUCT.equalsIgnoreCase(tempDto.getLevel()))) && Constant.ITEM_MASTER.equals(tempDto.getTableName())) {

                        isNdc = true;
                    } else {
                        isNdc = false;
                    }
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
                        discountDTO = (CompanyDdlbDto) discount.getValue();
                    }
                    ExtTreeContainer<Leveldto> dumbyProductContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);
                    List<Integer> dumbyProductList = new ArrayList<Integer>();

                    innerLevelValues = logic.loadInnerLevel(tempDto.getLevel(), productHierarchyDto.getHierarchyId(),
                            dumbyProductList, isNdc, tempDto.getFieldName(), relationshipSid, productDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY);
                    List<Leveldto> groupFilteredValues = new ArrayList<Leveldto>();
                    List<Leveldto> companyFilteredValues = new ArrayList<Leveldto>();
                    List<Leveldto> brandFilteredValues = new ArrayList<Leveldto>();
                    List<Leveldto> tempLevelValues;
                    List<Leveldto> filteredValues = new ArrayList<Leveldto>(innerLevelValues);
                    productFilterContainer.removeAllItems();
                    productFilterContainer.addAll(innerLevelValues);

                    if (!tempDto.isFromItem() && (Constant.NDC.equalsIgnoreCase(tempDto.getLevel()) || "Items".equalsIgnoreCase(tempDto.getLevel()) ||Constant.PRODUCT.equalsIgnoreCase(tempDto.getLevel()))) {
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
                            logic.getEndLevelRelationshipLevels(DataSelectionUtil.getEndLevelHierNo(dataSelectionForm.getCustomerHierarchyEndLevels(selectedCustomerContainer)), relationshipSid, ccList, availableHierNo);
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

    private List<Leveldto> getCustomersFromHierarchy() {
        List<Leveldto> innerLevelValues = null;
        try {
            int hierarchyId = 0;
            if (customerHierarchyDto == null) {
                hierarchyId = UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid());
            } else {
                hierarchyId = customerHierarchyDto.getHierarchyId();
            }
            DataSelectionLogic logic = new DataSelectionLogic();
            if (innerCustLevels == null || innerCustLevels.isEmpty() || customerHierarchyDto == null) {
                innerCustLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
            }
            if ((customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) && (innerCustLevels != null)) {

                String relationshipSid = String.valueOf(customerRelation.getValue());
                Leveldto customerLevelDto = null;
                for (Leveldto dto : innerCustLevels) {
                    if (Constant.CUSTOMER_SMALL.equalsIgnoreCase(dto.getLevel()) || Constant.COMPANY_SMALL.equalsIgnoreCase(dto.getLevel()) || Constant.TRADING_PARTNER.equalsIgnoreCase(dto.getLevel())) {
                        customerLevelDto = dto;
                        break;
                    }
                }
                if (customerLevelDto != null) {
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
                        discountDTO = (CompanyDdlbDto) discount.getValue();
                    }
                    innerLevelValues = logic.loadInnerLevel(customerLevelDto.getLevel(), customerHierarchyDto.getHierarchyId(),
                            DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()), false, customerLevelDto.getFieldName(), relationshipSid,
                            customerDescriptionMap, INDICATOR_LEVEL_CUSTOMER.getConstant(), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, customerLevelDto.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY);
                }
            }
        } catch (Exception ex) { 
            LOGGER.error(ex + " in getCustomersFromHierarchy");
        }
        return innerLevelValues;
    }

    private void filterCustomerOnGroupSelect() {
        availableCustomerContainer.removeAllItems();
        String levelName = "Level";
        try {
            int forecastLevel = 0;
            List<Leveldto> custVlues = null;
            boolean isNdc = false;
            if (level.getValue() != null && !Constants.CommonConstants.NULL.getConstant().equals(level.getValue()) && !SELECT_ONE.equals(level.getValue()) && customerRelation.getValue() != null) {
                String selectedLevel = String.valueOf(level.getValue());

                if (customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) {
                    String relationshipSid = String.valueOf(customerRelation.getValue());
                    int hierarchyId = 0;
                    if (customerHierarchyDto == null) {
                        hierarchyId = UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid());
                    } else {
                        hierarchyId = customerHierarchyDto.getHierarchyId();
                    }
                    DataSelectionLogic logic = new DataSelectionLogic();
                    String val[] = selectedLevel.split(" ");
                    forecastLevel = Integer.parseInt(val[1]);
                    if (innerCustLevels.isEmpty() || customerHierarchyDto == null) {
                        innerCustLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
                    }
                    Leveldto tempDto = (Leveldto) innerCustLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        levelName = tempDto.getLevel();
                    }
                    if (tempDto.getLevel() != null && (Constant.NDC.equalsIgnoreCase(tempDto.getLevel()) || "Item".equalsIgnoreCase(tempDto.getLevel())) && Constant.ITEM_MASTER.equals(tempDto.getTableName())) {
                        isNdc = true;
                    } else {
                        isNdc = false;
                    }
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
                        discountDTO = (CompanyDdlbDto) discount.getValue();
                    }
                    custVlues = logic.loadInnerLevel(tempDto.getLevel(), hierarchyId,
                            DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()), isNdc, tempDto.getFieldName(), relationshipSid, customerDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY);

                    if (groupFilteredCompanies != null && Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(tempDto.getTableName())) && (Constant.TRADING_PARTNER.equals(levelName) || Constant.COMPANY_SMALL.equals(levelName))) {
                        List<Leveldto> filteredValues = new ArrayList<Leveldto>();
                        if (!groupFilteredCompanies.isEmpty()) {
                            try {
                                for (Leveldto leveldto : custVlues) {
                                    if (groupFilteredCompanies.contains(leveldto.getRelationshipLevelValue().trim())) {
                                        filteredValues.add(leveldto);
                                    }
                                }
                            } catch (Exception ex) { 
                                LOGGER.error(ex);
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

    @Override
    protected void saveViewButtonlogic() {

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
                setUpdateOnTabChange(Boolean.TRUE);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
                        "No Level was selected to move. Please try again.");
            }
            productBeanLisTemp.removeAll(productBeanLisTemp);
            productHierarchyNos.removeAll(productHierarchyNos);
            for (Leveldto dto : selectedProductContainer.getItemIds()) {
                productHierarchyNos.add(dto.getHierarchyNo());
                productBeanLisTemp.add(dto.getRelationshipLevelSid());

            }
            setProductBeanLisTemp(productBeanLisTemp);
        } catch (Exception e) { 
        }
    }

    @Override
    protected void moveAllButtonLogic() {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            if (availableCustomerContainer.size() > 0) {
                int forecastLevel = 0;
                if (customerLevel.getValue() != null) {
                    forecastLevel = UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
                }
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
                productBeanLisTemp.removeAll(productBeanLisTemp);
                productHierarchyNos.removeAll(productHierarchyNos);
                for (Leveldto dto : selectedProductContainer.getItemIds()) {
                    productHierarchyNos.add(dto.getHierarchyNo());
                    productBeanLisTemp.add(dto.getRelationshipLevelSid());

                }
                setProductBeanLisTemp(productBeanLisTemp);
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

                    if (productLevel.getValue() != null && UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue())) == 1) {

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
                setUpdateOnTabChange(Boolean.TRUE);
                DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
                productBeanLisTemp.removeAll(productBeanLisTemp);
                productHierarchyNos.removeAll(productHierarchyNos);
                for (Leveldto dto : selectedProductContainer.getItemIds()) {
                    productHierarchyNos.add(dto.getHierarchyNo());
                    productBeanLisTemp.add(dto.getRelationshipLevelSid());

                }
                setProductBeanLisTemp(productBeanLisTemp);

            }
        } catch (Exception e) { 
            LOGGER.error(e);
        }
    }

    public static List<Integer> getProductBeanLisTemp() {
        return productBeanLisTemp;
    }

    public static void setProductBeanLisTemp(List<Integer> productBeanLisTemp) {
        DataSelection.productBeanLisTemp = productBeanLisTemp;
    }

    @Override
    protected void moveRigthButtonLogic() {
        
        if (selectedCustomer.getValue() != null) {
            Object selectedItem = selectedCustomer.getValue();
            String levelInString = Constant.DASH;
            if (level.getValue() != null && !String.valueOf(level.getValue()).equalsIgnoreCase(String.valueOf(SELECT_ONE))) {
                levelInString = String.valueOf(level.getValue());
            }
            int currentLevel = UiUtils.parseStringToInteger(levelInString);

            DataSelectionUtil.removeItemsRecursively(selectedItem, selectedCustomer, availableCustomer, selectedCustomerContainer, availableCustomerContainer, currentLevel);
            selectedCustomerContainer.removeItem(DataSelectionUtil.getBeanFromId(selectedItem));
            selectedCustomer.removeItem(selectedItem);
            customerBeanList.remove(DataSelectionUtil.getBeanFromId(selectedItem).getRelationshipLevelSid());
            customerBeanList.removeAll(customerBeanList);
            List<Leveldto> selectedValueItem = selectedCustomerContainer.getItemIds();
            for (Leveldto dto : selectedValueItem) {
                customerBeanList.add(dto.getRelationshipLevelSid());
            }
            setUpdateOnTabChange(Boolean.TRUE);
            if (dismantelCustomerSelection) {

                triggerCustGrpOnView(dataSelectionDTO.getCustomerGrpSid(), Integer.parseInt(dataSelectionDTO.getCustomerHierSid()), false);
                dismantelCustomerSelection = false;
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
                    "No Level was selected to move. Please try again. ");
        }
        productBeanLisTemp.removeAll(productBeanLisTemp);
        productHierarchyNos.removeAll(productHierarchyNos);
        for (Leveldto dto : selectedProductContainer.getItemIds()) {
            productHierarchyNos.add(dto.getHierarchyNo());
            productBeanLisTemp.add(dto.getRelationshipLevelSid());

        }
        setProductBeanLisTemp(productBeanLisTemp);
    }

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
                    triggerProdGrpOnView(dataSelectionDTO.getProdGrpSid(), Integer.parseInt(dataSelectionDTO.getProdHierSid()), false);
                    dismantleProductSelection = false;
                }
                setUpdateOnTabChange(Boolean.TRUE);
                if (dismantelProductSelection) {
                    triggerProdGrpOnView(dataSelectionDTO.getProdGrpSid(), Integer.valueOf(dataSelectionDTO.getProdHierSid()), false);
                    dismantelProductSelection = false;
                }
                productBeanList.removeAll(productBeanList);
                productHierarchyNos.removeAll(productHierarchyNos);
                List<Leveldto> productListValue = selectedProductContainer.getItemIds();
                for (Leveldto dto : productListValue) {
                    productHierarchyNos.add(dto.getHierarchyNo());
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
                setUpdateOnTabChange(Boolean.TRUE);
                DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
                productBeanLisTemp.removeAll(productBeanLisTemp);
                productHierarchyNos.removeAll(productHierarchyNos);
                for (Leveldto dto : selectedProductContainer.getItemIds()) {
                    productHierarchyNos.add(dto.getHierarchyNo());
                    productBeanLisTemp.add(dto.getRelationshipLevelSid());

                }
                setProductBeanLisTemp(productBeanLisTemp);
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

    }

    @Override
    protected void companyValueChangeLogic(Object value) throws Exception {
        int hierarchyId = 0;
        boolean isNdc = false;
        DataSelectionLogic logic = new DataSelectionLogic();
        if (productHierarchyDto == null) {
            hierarchyId = UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid());
        } else {
            hierarchyId = productHierarchyDto.getHierarchyId();
        }
        if (innerProdLevels.isEmpty() || productHierarchyDto == null) {
            innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY);
        }
        if (company.getValue() != null && innerProdLevels != null && productlevelDdlb.getValue() != null
                && !String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(productlevelDdlb.getValue())) && productRelation.getValue() != null) {
            int forecastLevel = 0;
            forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()));
            Leveldto tempDto = (Leveldto) innerProdLevels.get(forecastLevel - 1);
            List<Leveldto> companyFilteredValues = new ArrayList<Leveldto>();
            List<Leveldto> tempLevelValues;
            List<Leveldto> innerLevelValues = null;

            String relationshipSid = String.valueOf(productRelation.getValue());

            if (tempDto.getLevel() != null && (Constant.NDC.equalsIgnoreCase(tempDto.getLevel()) || "Item".equalsIgnoreCase(tempDto.getLevel())) && Constant.ITEM_MASTER.equals(tempDto.getTableName())) {
                isNdc = true;
            } else {
                isNdc = false;
            }
            if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
                discountDTO = (CompanyDdlbDto) discount.getValue();
            }
            innerLevelValues = logic.loadInnerLevel(tempDto.getLevel(), productHierarchyDto.getHierarchyId(),
                    DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds()), isNdc, tempDto.getFieldName(), relationshipSid, productDescriptionMap, DataSelectionUtil.identifyLevel(tempDto), screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY);
            List<Leveldto> filteredValues = new ArrayList<Leveldto>(innerLevelValues);
            if (company.getValue() != null && !String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(company.getValue()))
                    && Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(tempDto.getTableName()))) {
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
            session.setSaveFlag(updateOnTabChange);
        } else {
            loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()), true);
        }
    }

    @Override
    protected void resetButtonLogic() {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private void configureFields() {

        productlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String selectedLevel = String.valueOf(event.getProperty().getValue());
                loadFilteredProductSelection(selectedLevel, false);
            }

        });

    }

    void configureOnViewMode() {
        horizontalLayout.setEnabled(false);
        selectedCustomer.setEditable(false);
        selectedCustomer.setSelectable(false);
        all.setEnabled(false);
        moveRightBtn.setEnabled(false);
        moveLeftBtn.setEnabled(false);
        level.setEnabled(false);
        customerGroup.setEnabled(false);
        customerLevel.setEnabled(false);
        customerRelation.setEnabled(false);
        customerHierarchy.setEnabled(false);
        company.setEnabled(false);
        productHierarchy.setEnabled(false);
        productRelation.setEnabled(false);
        productGroup.setEnabled(false);
        productlevelDdlb.setEnabled(false);
        availableProduct.setEnabled(false);
        allProductBtn.setEnabled(false);
        moveLeftProduct.setEnabled(false);
        moveRightProduct.setEnabled(false);
        selectedProduct.setSelectable(false);
//        panel3.setEnabled(false);
    }

    private void loadDiscountDdlb(int discountSid, CompanyDdlbDto selectedDiscountDdlbDto) {
        LOGGER.info("Entering loadDiscountDDLB method");
        discountDdlbLazyContainer = new LazyContainer(CompanyDdlbDto.class, new CompanyDdlbDao(Constant.DISCOUNT_SMALL, discountDdlbDefault, selectedDiscountDdlbDto), new CompanyDdlbCriteria());
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

    @Override
    protected void resetTwo() {

    }

    public String getProjectionName() {
        return projectionName.getValue();
    }

    public void updateDataSelectionSelectedProducts(List<Integer> selectedProductIds) throws SystemException, Exception {
        LOGGER.info("updateDataSelectionSelectedProducts starts");
        StringBuilder prodUpdateQuery = new StringBuilder();
        prodUpdateQuery.append(" Delete FROM PROJECTION_PROD_HIERARCHY where PROJECTION_MASTER_SID=" + session.getProjectionId() + "; ");
        HelperTableLocalServiceUtil.executeUpdateQuery(prodUpdateQuery.toString());
        LOGGER.info("updateDataSelectionSelectedProducts ends");
    }

    public void updateProjectionProdHierarchy() throws Exception {

        List<Leveldto> productList = selectedProductContainer.getItemIds();
        List<String> productListEndSids = DataSelectionUtil.getEndLevelHierNo(dataSelectionForm.getProductHierarchyEndLevels(selectedProductContainer));
        dsLogic.saveProductHierarchyLogic(productList, productListEndSids, session.getProjectionId(), null, "save");
    }

    /**
     * Used to delete the projection Details table for removed hierarchy
     *
     * @param CollectionToString
     */
    private void deleteProjectionDetailstable(final String CollectionToString, final String propertyName) {
        ResourceBundle tableName = ResourceBundle.getBundle("properties.Constants");
        StringBuilder queryBuilder = new StringBuilder();
        String[] tables = tableName.getString(propertyName).split(",");
        for (String tableNames : tables) {
            queryBuilder.append(SQlUtil.getQuery("deleteTemplate").replace("@TABLE_NAME", tableNames).replace("@PD_SID", CollectionToString));
        }
        queryBuilder.append("DELETE FROM PROJECTION_DETAILS where PROJECTION_DETAILS_SID in (" + CollectionToString + ")");
        HelperTableLocalServiceUtil.executeUpdateQuery(queryBuilder.toString());
    }

}
