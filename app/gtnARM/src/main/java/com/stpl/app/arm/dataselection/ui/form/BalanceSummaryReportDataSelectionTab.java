/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.form;

import static com.stpl.app.arm.dataselection.ui.form.DataSelection.getBeanFromId;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.arm.abstractforms.AbstractDataSelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.DeductionLevelDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.data.Property;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryReportDataSelectionTab extends AbstractDataSelection {

    @UiField("adjustmentTypeLabel")
    protected Label adjustmentTypeLabel;
    @UiField("descriptionLabel")
    protected Label descriptionLabel;
    DataSelectionDTO dataSelectionDTO;
    DataSelectionLogic logic = new DataSelectionLogic();
    private static final Logger LOGGER = Logger.getLogger(BalanceSummaryReportDataSelectionTab.class);
    Set<Integer> rsContractSidList = new HashSet<>();
    String screenName = StringUtils.EMPTY;
    SessionDTO sessionDTO;
    String periodView = StringUtils.EMPTY;

    public BalanceSummaryReportDataSelectionTab() {
    }

    public BalanceSummaryReportDataSelectionTab(DataSelectionDTO selection, SessionDTO sessionDTO) {
        super();
        this.dataSelectionDTO = selection;
        this.sessionDTO = sessionDTO;
        init();
    }

    public void init() {
        panel1.setCaption("Selection Criteria");
        configureDataSelection();
        loadValuesInDataSelection();
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
        LOGGER.debug("Inside loadAvailableCustProd Method" + hierSid + relSid + event);
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
        selectedDeduction.setContainerDataSource(dataSelectionDTO.getSelectedDeductionContainer());
        selectedCustomer.setContainerDataSource(dataSelectionDTO.getSelectedCustomerContainer());
        selectedProduct.setContainerDataSource(dataSelectionDTO.getSelectedProductContainer());
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
        logic.loadCustomerRelation(customerRelation, dataSelectionDTO.getCustomerHierarchySid());
        logic.loadProductRelation(productRelation, dataSelectionDTO.getProductHierarchySid(), dataSelectionDTO.getCompanyMasterSid());
        logic.loadCustoProdLevels(customerLevel, dataSelectionDTO.getCustomerHierarchySid());
        logic.loadCustoProdLevels(productLevel, dataSelectionDTO.getProductHierarchySid());
        customerHierarchy.setValue(dataSelectionDTO.getCustomerHierarchyName());
        customerRelation.select(dataSelectionDTO.getCustRelationshipBuilderSid());
        customerLevel.select(dataSelectionDTO.getCustomerHierarchyLevel());
        productHierarchy.setValue(dataSelectionDTO.getProductHierarchyName());
        productRelation.select(dataSelectionDTO.getProdRelationshipBuilderSid());
        productLevel.select(dataSelectionDTO.getProductHierarchyLevel());
        deductionLevel.select(HelperListUtil.getInstance().getIdHelperDTOMap().get(dataSelectionDTO.getDeductionLevel()));
        company.select(dataSelectionDTO.getCompanyMasterSid());
        businessUnit.select(dataSelectionDTO.getBucompanyMasterSid());

        Collection<?> adjItems = (Collection<?>) dataSelectionDTO.getSummaryType();
        Object adjItemToAdd = null;
        for (Object adjItem : adjItems) {
            if (adjItem instanceof HelperDTO) {
                HelperDTO hlpd = (HelperDTO) adjItem;
                if (dataSelectionDTO.getAdjustmentId() == hlpd.getId()) {
                    adjItemToAdd = adjItem;
                }
            }
        }
        summaryTypeDdlb.select(adjItemToAdd);

        view.setValue(dataSelectionDTO.getViewName());
        loadFromAndTo(dataSelectionDTO);
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

    public void loadCustomerLevel() {
        try {
            innerCustLevels = logic.loadCustomerForecastLevel(dataSelectionDTO.getCustomerHierarchySid(), dataSelectionDTO.getCustomerHierarchyName());
        } catch (Exception ex) {
            LOGGER.error(ex + " in loadCustomerLevel");
        }

    }

    public void loadProductLevel() {
        try {
            innerProdLevels = logic.loadCustomerForecastLevel(dataSelectionDTO.getProductHierarchySid(), dataSelectionDTO.getProductHierarchyName());
        } catch (Exception ex) {
            LOGGER.error(ex + " loadProductLevel");
        }
    }

    /**
     * Load customer hierarchy.
     *
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void initializeCustomerHierarchy(final int projectionId, int customerLevel) {
        DataSelectionLogic logic = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = logic.getRelationShipValues(projectionId, "customer", customerLevel, customerDescriptionMap);
        for (LevelDTO selection : reslistOne) {
            if (selection.getLevelNo() == 1) {
                selectedCustomerContainer.removeAllItems();
                selectedCustomerContainer.addItem(selection);
                selectedCustomerContainer.setChildrenAllowed(selection, true);
            } else {
                for (Object tempselection : selectedCustomerContainer.getItemIds()) {
                    if (selection.getParentNode().contains("~")) {
                        String[] parentarr = selection.getParentNode().split("~");
                        String parentName = parentarr[1];
                        if (getBeanFromId(tempselection).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
                            selectedCustomerContainer.addBean(selection);
                            selectedCustomerContainer.setChildrenAllowed(selection, true);
                            selectedCustomerContainer.setParent(selection, tempselection);
                            break;
                        }
                    } else {
                        selectedCustomerContainer.addBean(selection);
                        selectedCustomerContainer.setChildrenAllowed(selection, true);
                        selectedCustomerContainer.setParent(selection, tempselection);
                        break;
                    }
                }
            }
        }
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
        DataSelectionLogic logic = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = logic.getRelationShipValues(projectionId, "product", productLevel, productDescriptionMap);
        for (LevelDTO selection : reslistOne) {
            if (selection.getLevelNo() == 1) {
                selectedProductContainer.removeAllItems();
                selectedProductContainer.addItem(selection);
                selectedProductContainer.setChildrenAllowed(selection, true);
            } else {
                for (Object tempselection : selectedProductContainer.getItemIds()) {
                    if (selection.getParentNode().contains("~")) {
                        String[] parentarr = selection.getParentNode().split("~");
                        String parentName = parentarr[1];
                        if (getBeanFromId(tempselection).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
                            selectedProductContainer.addBean(selection);
                            if (productLevel != selection.getLevelNo()) {
                                selectedProductContainer.setChildrenAllowed(selection, true);
                            } else {
                                selectedProductContainer.setChildrenAllowed(selection, false);
                            }
                            selectedProductContainer.setParent(selection, tempselection);
                            break;
                        }
                    } else {
                        selectedProductContainer.addBean(selection);
                        if (productLevel != selection.getLevelNo()) {
                            selectedProductContainer.setChildrenAllowed(selection, true);
                        } else {
                            selectedProductContainer.setChildrenAllowed(selection, false);
                        }
                        selectedProductContainer.setParent(selection, tempselection);
                        break;
                    }
                }
            }
        }
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
            DataSelectionLogic.getDates(val, glCompId, bUnitCompSid, fromPeriod, toPeriod, true,selection);
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
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
