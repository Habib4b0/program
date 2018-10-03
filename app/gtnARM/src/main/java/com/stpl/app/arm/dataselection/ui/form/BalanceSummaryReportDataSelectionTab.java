/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.form;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.arm.abstractforms.AbstractDataSelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.DeductionLevelDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryReportDataSelectionTab extends AbstractDataSelection {

    @UiField("adjustmentTypeLabel")
    protected Label adjustmentTypeLabel;
    @UiField("descriptionLabel")
    protected Label descriptionLabel;
    private DataSelectionDTO bsrDataSelectionDTO;
    private DataSelectionLogic logic = new DataSelectionLogic();
    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceSummaryReportDataSelectionTab.class);
    private String periodView = StringUtils.EMPTY;
    private Boolean fromFlag = Boolean.FALSE;
    private TabSheet tabSheet;

    public Boolean getFromFlag() {
        return fromFlag;
    }

    public void setFromFlag(Boolean fromFlag) {
        this.fromFlag = fromFlag;
    }

    public BalanceSummaryReportDataSelectionTab() {
    }

    public BalanceSummaryReportDataSelectionTab(DataSelectionDTO selection, SessionDTO sessionDTO, TabSheet tabSheet) {
        super();
        this.bsrDataSelectionDTO = selection;
        this.tabSheet = tabSheet;
        reportTabInit();
    }

    private void reportTabInit() {
        panel1.setCaption("Selection Criteria");
        configureDataSelection();
        loadValuesInDataSelection();
        fromPeriod.addValueChangeListener(fromPeriodListener);
        toPeriod.addValueChangeListener(toPeriodListener);
        setReadOnlyMode();
    }

    @Override
    public void loadCustRelationAndLevel(int hierSid, CustomTextField.ClickEvent event) {
        LOGGER.debug("Inside loadCustRelationAndLevel Method");

    }

    @Override
    public void loadProdRelationAndLevel(int hierSid, int glComp, CustomTextField.ClickEvent event) {
        LOGGER.debug("Inside loadProdRelationAndLevel Method");
    }

    public void loadAvailableCustProd(int hierSid, int relSid, Property.ValueChangeEvent event) {
        LOGGER.debug("Inside loadAvailableCustProd Method {}", (hierSid + StringUtils.EMPTY + relSid + event));
    }

    @Override
    public void loadAvailableCustomers() {
        LOGGER.debug("Inside loadAvailableCustomers Method");
    }

    @Override
    public void loadAvailableProducts() {
        LOGGER.debug("Inside loadAvailableProducts Method");
    }

    @Override
    public void customerHierarchyCloseListener() {
        LOGGER.debug("Inside customerHierarchyCloseListener Method");
    }

    @Override
    public void productHierarchyCloseListener() {
        LOGGER.debug("Inside productHierarchyCloseListener Method");

    }

    @Override
    public void moveLeftCustomersButtonLogic() {
        LOGGER.debug("Inside loadCustRelationAndLevel Method");
    }

    @Override
    public void moveRightCustomersButtonLogic() {
        LOGGER.debug("Inside moveRightCustomersButtonLogic Method");
    }

    @Override
    public void customerRelationValueChange(Object value) {
        LOGGER.debug("Inside customerRelationValueChange Method");
    }

    @Override
    public void productRelationValueChange(Object value) {
        LOGGER.debug("Inside productRelationValueChange Method");
    }

    @Override
    public void moveLeftProductsButtonLogic() {
        LOGGER.debug("Inside moveLeftProductsButtonLogic Method");
    }

    @Override
    public void moveRightProductsButtonLogic() {
        LOGGER.debug("Inside moveRightProductsButtonLogic Method");
    }

    @Override
    public void moveAllProductsButtonLogic() {
        LOGGER.debug("Inside moveAllProductsButtonLogic Method");
    }

    @Override
    public void moveAllCustomersButtonLogic() {
        LOGGER.debug("Inside moveAllCustomersButtonLogic Method");
    }

    @Override
    public void generateButtonLogicForScreens() {
        LOGGER.debug("Inside generateButtonLogicForScreens Method");
    }

    @Override
    public void loadPrivateViewLook() {
        LOGGER.debug("Inside loadPrivateViewLook Method");
    }

    @Override
    public void loadPublicViewLook() {
        LOGGER.debug("Inside loadPublicViewLook Method");
    }

    @Override
    public void loadAvailableDeductions() {
        LOGGER.debug("Inside loadAvailableDeductions Method");
    }

    @Override
    public void moveLeftToRightDeductions() {
        LOGGER.debug("Inside moveLeftToRightDeductions Method");
    }

    @Override
    public void privateLookupCloseListener() {
        LOGGER.debug("Inside privateLookupCloseListener Method");
    }

    @Override
    public void publicLookupCloseListener() {
        LOGGER.debug("Inside publicLookupCloseListener Method");
    }

    @Override
    public void moveAllDeductions() {
        LOGGER.debug("Inside moveAllDeductions Method");
    }

    @Override
    public void moveRightToLeftDeductions() {
        LOGGER.debug("Inside moveRightToLeftDeductions Method");
    }

    @Override
    public void saveViewLogic() {
        LOGGER.debug("Inside saveViewLogic Method");
    }

    public void configureDataSelection() {
        buttonLay.setVisible(false);
        adjustmentType.setVisible(false);
        description.setVisible(false);
        adjustmentTypeLabel.setVisible(false);
        descriptionLabel.setVisible(false);
    }

    private void loadValuesInDataSelection() {
        selectedDeduction.setContainerDataSource(bsrDataSelectionDTO.getSelectedDeductionContainer());
        selectedCustomer.setContainerDataSource(bsrDataSelectionDTO.getSelectedCustomerContainer());
        selectedProduct.setContainerDataSource(bsrDataSelectionDTO.getSelectedProductContainer());
        Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE};
        selectedCustomer.setVisibleColumns(obj);
        String[] str = new String[]{"Selected Customers"};
        selectedCustomer.setColumnHeaders(str);
        selectedDeduction.setVisibleColumns("levelValue");
        selectedDeduction.setColumnHeader("levelValue", "Selected Deductions");
        selectedProduct.setVisibleColumns(CommonConstant.DISPLAY_VALUE);
        selectedProduct.setColumnHeader(CommonConstant.DISPLAY_VALUE, "Selected Products");
        CommonLogic.loadCompanyAndBusinessUnit(company, "getCompanyQueryForDS");
        CommonLogic.loadCompanyAndBusinessUnit(businessUnit, "getBusinessQueryForDS");
        loadSummaryType();
        logic.loadCustomerRelation(customerRelation, bsrDataSelectionDTO.getCustomerHierarchySid());
        logic.loadProductRelation(productRelation, bsrDataSelectionDTO.getProductHierarchySid(), bsrDataSelectionDTO.getCompanyMasterSid());
        logic.loadCustoProdLevels(customerLevel, bsrDataSelectionDTO.getCustomerHierarchySid());
        logic.loadCustoProdLevels(productLevel, bsrDataSelectionDTO.getProductHierarchySid());
        customerHierarchy.setValue(bsrDataSelectionDTO.getCustomerHierarchyName());
        customerRelation.select(bsrDataSelectionDTO.getCustRelationshipBuilderSid());
        customerLevel.select(bsrDataSelectionDTO.getCustomerHierarchyLevel());
        productHierarchy.setValue(bsrDataSelectionDTO.getProductHierarchyName());
        productRelation.select(bsrDataSelectionDTO.getProdRelationshipBuilderSid());
        productLevel.select(bsrDataSelectionDTO.getProductHierarchyLevel());
        deductionLevel.select(HelperListUtil.getInstance().getIdHelperDTOMap().get(bsrDataSelectionDTO.getDeductionLevel()));
        company.select(bsrDataSelectionDTO.getCompanyMasterSid());
        businessUnit.select(bsrDataSelectionDTO.getBucompanyMasterSid());

        Collection<?> adjItems = (Collection<?>) bsrDataSelectionDTO.getSummaryType();
        Object adjItemToAdd = null;
        for (Object adjItem : adjItems) {
            if (adjItem instanceof HelperDTO) {
                HelperDTO hlpd = (HelperDTO) adjItem;
                if (bsrDataSelectionDTO.getAdjustmentId() == hlpd.getId()) {
                    adjItemToAdd = adjItem;
                    break;
                }
            }
        }
        summaryTypeDdlb.select(adjItemToAdd);

        view.setValue(bsrDataSelectionDTO.getViewName());
        loadFromAndTo(bsrDataSelectionDTO);
    }

    public void setReadOnlyMode() {
        view.setEnabled(false);
        company.setReadOnly(true);
        businessUnit.setReadOnly(true);
        summaryTypeDdlb.setEnabled(false);
        customerHierarchy.setEnabled(false);
        customerRelation.setReadOnly(true);
        customerLevel.setReadOnly(true);
        productLevel.setReadOnly(true);
        availableCustomer.setReadOnly(true);
        selectedCustomer.setReadOnly(true);
        deductionLevel.setReadOnly(true);
        availableDeduction.setReadOnly(true);
        selectedDeduction.setReadOnly(true);
        productHierarchy.setEnabled(false);
        productRelation.setReadOnly(true);
        availableProduct.setReadOnly(true);
        customerMoveAllBtn.setEnabled(false);
        customerMoveLeftBtn.setEnabled(false);
        customerMoveRightBtn.setEnabled(false);
        productMoveAllBtn.setEnabled(false);
        productMoveLeftProduct.setEnabled(false);
        productMoveRightProduct.setEnabled(false);
        deductionMoveAllBtn.setEnabled(false);
        deductionMoveLeftBtn.setEnabled(false);
        deductionMoveRightBtn.setEnabled(false);
        generateBtn.setVisible(false);
        resetBtn.setVisible(false);
        saveViewBtn.setVisible(false);
        deleteViewBtn.setVisible(false);
    }

    public void bsrLoadCustomerLevel() {
        try {
            innerCustLevels = logic.loadCustomerForecastLevel(bsrDataSelectionDTO.getCustomerHierarchySid(), bsrDataSelectionDTO.getCustomerHierarchyName());
        } catch (Exception e) {
            LOGGER.error("loadCustomerLevel", e);
        }

    }

    public void bsrLoadProductLevel() {
        try {
            innerProdLevels = logic.loadCustomerForecastLevel(bsrDataSelectionDTO.getProductHierarchySid(), bsrDataSelectionDTO.getProductHierarchyName());
        } catch (Exception e) {
            LOGGER.error("loadProductLevel", e);
        }
    }

    /**
     * Load customer hierarchy.
     *
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void initializeCustomerHierarchy(final int projectionId, int customerLevel) {
        DataSelectionLogic dataSelectionLogic = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = dataSelectionLogic.getRelationShipValues(projectionId, "customer", customerLevel, customerDescriptionMap);
        createHierarchyBasedOnHierarchyNo(selectedCustomerContainer, reslistOne, customerLevel);
        selectedCustomer.setContainerDataSource(selectedCustomerContainer);
        Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE};
        selectedCustomer.setVisibleColumns(obj);
        String[] str = new String[]{"Customer Hierarchy Group Builder"};
        selectedCustomer.setColumnHeaders(str);
        for (LevelDTO ddo : selectedCustomerContainer.getItemIds()) {
            selectedCustomer.setCollapsed(ddo, false);
        }
    }

    /**
     * Load customer hierarchy.
     *
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void initializeProductHierarchy(final int projectionId, int productLevel) {
        DataSelectionLogic selectionLogic = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = selectionLogic.getRelationShipValues(projectionId, "product", productLevel, productDescriptionMap);
        createHierarchyBasedOnHierarchyNo(selectedProductContainer, reslistOne, productLevel);
        selectedProduct.setContainerDataSource(selectedProductContainer);
        Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE};
        selectedProduct.setVisibleColumns(obj);
        String[] str = new String[]{"Product Hierarchy Group Builder"};
        selectedProduct.setColumnHeaders(str);
        for (LevelDTO ddo : selectedProductContainer.getItemIds()) {
            selectedProduct.setCollapsed(ddo, false);
        }
    }

    public void setLevels(DeductionLevelDTO parentList, List<DeductionLevelDTO> list, int levelNo) {
        for (DeductionLevelDTO typeLevel : list) {
            if (levelNo != 8) {
                selectedDeductionContainer.addBean(typeLevel);
                selectedDeductionContainer.setParent(typeLevel, parentList);
                selectedDeductionContainer.setChildrenAllowed(typeLevel, true);
            } else {
                selectedDeductionContainer.addBean(typeLevel);
                selectedDeductionContainer.setParent(typeLevel, parentList);
                selectedDeductionContainer.setChildrenAllowed(typeLevel, false);
            }
        }
    }

    private void loadFromAndTo(DataSelectionDTO selection) {
        if ((summaryTypeDdlb.getValue() != null) && company.getValue() != null && businessUnit.getValue() != null) {
            int buscinessProcess = ((HelperDTO) summaryTypeDdlb.getValue()).getId();
            int glCompId = Integer.parseInt(company.getValue().toString());
            int bUnitCompSid = Integer.parseInt(businessUnit.getValue().toString());
            int val = -(((HelperDTO) (summaryTypeDdlb.getValue())).getId());
            fromPeriod.removeAllItems();
            toPeriod.removeAllItems();
            configurePeriodDropDown(fromPeriod);
            configurePeriodDropDown(toPeriod);
            DataSelectionLogic.getDates(val, glCompId, bUnitCompSid, fromPeriod, toPeriod, true, selection);
            fromPeriod.select(selection.getFromPeriod());
            toPeriod.select(selection.getToPeriod());
            periodView = DataSelectionLogic.getPeriodViewForAdjustmentType(buscinessProcess, glCompId, bUnitCompSid);
            if (ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView)) {
                toPeriod.removeAllItems();
                configurePeriodDropDown(toPeriod);
                DataSelectionLogic.getDates(val, glCompId, bUnitCompSid, toPeriod);
                toPeriod.select(String.valueOf(fromPeriod.getValue()));
                toPeriod.setEnabled(false);
            } else {
                toPeriod.setEnabled(true);
            }
//            } //Ends here
        } else {
            fromPeriod.removeAllItems();
            toPeriod.removeAllItems();
        }

    }

    @Override
    public void setAdjustmentOptionsPanelForBalanceSummaryReport() {
        addCompanyBusinessUnit();
        addCalculationProfileLookUp();
        loadSummaryType();
    }

    public void addCalculationProfileLookUp() {
        VerticalLayout calculationRootVLayout = new VerticalLayout();

        HorizontalLayout calculationProfileLayout = new HorizontalLayout();
        calculationProfileLayout.setSpacing(true);
        calculationProfileLayout.setSizeFull();
        VerticalLayout calculationVLayout = new VerticalLayout();
        Label lable = new Label();
        calculationVLayout.addComponent(lable);
        calculationVLayout.addComponent(calculationProfileLayout);
        calculationRootVLayout.addComponent(calculationVLayout);
        horizontalLayout.addComponent(calculationRootVLayout);
    }

    public void addCompanyBusinessUnit() {
        selectionVerticalLayout.removeAllComponents();
        GridLayout gridLayout = new GridLayout(4, 2);
        gridLayout.addComponent(summaryTypeLabel);
        gridLayout.addComponent(summaryTypeDdlb);

        gridLayout.addComponent(companyLabel);
        gridLayout.addComponent(company);

        gridLayout.addComponent(viewLabel);
        gridLayout.addComponent(view);

        gridLayout.addComponent(businessUnitLabel);
        gridLayout.addComponent(businessUnit);
        gridLayout.setSpacing(true);
        gridLayout.setMargin(false);

        summaryTypeDdlb.setWidth("217px");
        selectionVerticalLayout.addComponent(gridLayout);
    }

    public void loadSummaryType() {
        CommonLogic.setComboBoxItemIDAndCaption(summaryTypeDdlb, "LoadBalanceSummaryType", Collections.emptyList());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.adjustmentTypeLabel);
        hash = 59 * hash + Objects.hashCode(this.descriptionLabel);
        hash = 59 * hash + Objects.hashCode(this.bsrDataSelectionDTO);
        hash = 59 * hash + Objects.hashCode(this.logic);
        hash = 59 * hash + Objects.hashCode(this.periodView);
        hash = 59 * hash + Objects.hashCode(this.fromFlag);
        hash = 59 * hash + Objects.hashCode(this.tabSheet);
        hash = 59 * hash + Objects.hashCode(this.toPeriodListener);
        hash = 59 * hash + Objects.hashCode(this.fromPeriodListener);
        hash = 59 * hash + Objects.hashCode(this.notifier);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BalanceSummaryReportDataSelectionTab other = (BalanceSummaryReportDataSelectionTab) obj;
        if (!Objects.equals(this.periodView, other.periodView)) {
            return false;
        }
        if (!Objects.equals(this.adjustmentTypeLabel, other.adjustmentTypeLabel)) {
            return false;
        }
        if (!Objects.equals(this.descriptionLabel, other.descriptionLabel)) {
            return false;
        }
        if (!Objects.equals(this.bsrDataSelectionDTO, other.bsrDataSelectionDTO)) {
            return false;
        }
        if (!Objects.equals(this.logic, other.logic)) {
            return false;
        }
        if (!Objects.equals(this.fromFlag, other.fromFlag)) {
            return false;
        }
        if (!Objects.equals(this.tabSheet, other.tabSheet)) {
            return false;
        }
        if (!Objects.equals(this.toPeriodListener, other.toPeriodListener)) {
            return false;
        }
        if (!Objects.equals(this.fromPeriodListener, other.fromPeriodListener)) {
            return false;
        }
        return Objects.equals(this.notifier, other.notifier);
    }

    private Property.ValueChangeListener toPeriodListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (bsrDataSelectionDTO != null && ((toPeriod.getValue() != null) && toPeriod.isEnabled())
                    && fromPeriod.getValue() != null && !"0".equals(String.valueOf(toPeriod.getValue()))
                    && bsrDataSelectionDTO.isAlterFlag()) {
                notifier.getConfirmationMessage("Confirmation", "Time Period: From and/or Time Period: To values have changed.  Re-generate the Summary tab?");
            }
        }
    };

    private Property.ValueChangeListener fromPeriodListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                if ((summaryTypeDdlb.getValue() != null) && company.getValue() != null && businessUnit.getValue() != null) {
                    if (ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView)) {
                        toPeriod.select(String.valueOf(fromPeriod.getValue()));
                        toPeriod.setEnabled(false);
                    }

                    if (((toPeriod.getValue() != null) && toPeriod.isEnabled()) && bsrDataSelectionDTO != null && fromPeriod.getValue() != null) {
                        toPeriod.removeAllItems();
                        configurePeriodDropDown(toPeriod);
                        if (bsrDataSelectionDTO.isCheckFlag()) {
                            initialFromandToLoad();
                        } else {
                            valueChangeMethod();
                        }
                    }
                }
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage());
            }
        }

        private void initialFromandToLoad() {
            Date fromDate;
            fromDate = bsrDataSelectionDTO.getStartDate();
            if (fromPeriod.getValue().equals(NumericConstants.ZERO)) {
                DataSelectionLogic.getPeriods(fromDate, bsrDataSelectionDTO.getEndDate(), toPeriod);
                toPeriod.select(0);
            } else {
                DataSelectionLogic.getPeriods(fromDate, bsrDataSelectionDTO.getEndDate(), toPeriod);
                toPeriod.select(DataSelectionLogic.getPeriod(bsrDataSelectionDTO.getDefaultToDate()));
            }
            bsrDataSelectionDTO.setCheckFlag(false);

        }

        private void valueChangeMethod() throws ParseException {
            Date fromDate;
            if (fromPeriod.getValue().equals(NumericConstants.ZERO)) {
                fromDate = bsrDataSelectionDTO.getStartDate();
                DataSelectionLogic.getPeriods(fromDate, bsrDataSelectionDTO.getEndDate(), toPeriod);
                toPeriod.select(0);
            } else {
                fromDate = ARMUtils.getInstance().getDbDate().parse(String.valueOf(fromPeriod.getValue()));
                DataSelectionLogic.getPeriods(fromDate, bsrDataSelectionDTO.getEndDate(), toPeriod);
                toPeriod.select(DataSelectionLogic.getPeriod(bsrDataSelectionDTO.getEndDate()));
            }
        }
    };

    private final BSRReportDataSelectionCustomNotification notifier = new BSRReportDataSelectionCustomNotification();

    class BSRReportDataSelectionCustomNotification extends AbstractNotificationUtils {

        @Override
        public void noMethod() {
            LOGGER.debug("Entering No Method ");
            bsrDataSelectionDTO.setAlterFlag(false);
            toPeriod.select(bsrDataSelectionDTO.getToPeriod());
            bsrDataSelectionDTO.setAlterFlag(true);
        }

        @Override
        public void yesMethod() {
            try {
                bsrDataSelectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));
                bsrDataSelectionDTO.setFromDate(CommonLogic.parseDate(String.valueOf(fromPeriod.getValue())));
                bsrDataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
                bsrDataSelectionDTO.setToDate(CommonLogic.parseDate(String.valueOf(toPeriod.getValue())));
                logic.updateProjectionMaster(bsrDataSelectionDTO.getFromDate(), bsrDataSelectionDTO.getToDate(), bsrDataSelectionDTO.getProjectionId());
                setFromFlag(Boolean.TRUE);
                tabSheet.setSelectedTab(1);
                setFromFlag(Boolean.FALSE);
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage());
            }
        }

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
