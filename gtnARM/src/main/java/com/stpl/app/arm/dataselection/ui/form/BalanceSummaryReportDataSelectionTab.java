/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.form;

import com.stpl.app.arm.AbstractForms.AbstractDataSelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.DeductionLevelDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import static com.stpl.app.arm.dataselection.ui.form.DataSelection.getBeanFromId;
import com.stpl.app.arm.dataselection.ui.lookups.CalculationProfileLookUp;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.data.Property;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.paged.logic.HierarchyString;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;

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
    private final List<String> hierarchyKeys = new ArrayList<>();
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
//        if (sessionDTO.isWorkFlow()) {
//            configureDataSelection();
//            setViewDetails();
//            setReadOnlyMode();
//        } else {
        configureDataSelection();
        loadValuesInDataSelection();
        setReadOnlyMode();
//        }
    }

    @Override
    public void loadCustRelationAndLevel(int hierSid, CustomTextField.ClickEvent event) {

    }

    @Override
    public void loadProdRelationAndLevel(int hierSid, int glComp, CustomTextField.ClickEvent event) {

    }

    public void loadAvailableCustProd(int hierSid, int relSid, Property.ValueChangeEvent event) {

    }

    @Override
    public void loadAvailableCustomers() {

    }

    @Override
    public void loadAvailableProducts() {

    }

    @Override
    public void customerHierarchyCloseListener() {

    }

    @Override
    public void productHierarchyCloseListener() {

    }

    @Override
    public void moveLeftCustomersButtonLogic() {

    }

    @Override
    public void moveRightCustomersButtonLogic() {

    }

    @Override
    public void customerRelationValueChange(Object value) {

    }

    @Override
    public void productRelationValueChange(Object value) {

    }

    @Override
    public void moveLeftProductsButtonLogic() {

    }

    @Override
    public void moveRightProductsButtonLogic() {

    }

    @Override
    public void moveAllProductsButtonLogic() {

    }

    @Override
    public void moveAllCustomersButtonLogic() {
    }

    @Override
    public void generateButtonLogicForScreens() {
    }

    @Override
    public void loadPrivateViewLook() {
    }

    @Override
    public void loadPublicViewLook() {
    }

    @Override
    public void loadAvailableDeductions() {
    }

    @Override
    public void moveLeftToRightDeductions() {
    }

    @Override
    public void privateLookupCloseListener() {
    }

    @Override
    public void publicLookupCloseListener() {
    }

    @Override
    public void moveAllDeductions() {
    }

    @Override
    public void moveRightToLeftDeductions() {
    }

    @Override
    public void saveViewLogic() {
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
        selectedCustomer.setVisibleColumns(new Object[]{"displayValue"});
        selectedCustomer.setColumnHeaders(new String[]{"Selected Customers"});
        selectedDeduction.setVisibleColumns("levelValue");
        selectedDeduction.setColumnHeader("levelValue", "Selected Deductions");
        selectedProduct.setVisibleColumns("displayValue");
        selectedProduct.setColumnHeader("displayValue", "Selected Products");
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
        businessUnit.select(dataSelectionDTO.getBu_companyMasterSid());
       
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
        calculationProfile.setValue(dataSelectionDTO.getCalculationProfileName());
        /* if (ARMConstants.getPipelineAccrual().equalsIgnoreCase(selection.getAdjustmentType()) || ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(selection.getAdjustmentType())
         || ARMConstants.getTransaction6().equalsIgnoreCase(selection.getAdjustmentType())
         || ARMConstants.getTransaction7().equalsIgnoreCase(selection.getAdjustmentType())) {
         toPeriod.removeAllItems();
         configurePeriodDropDown(toPeriod);
         toPeriod.setEnabled(false);
         }*/
        view.setValue(dataSelectionDTO.getViewName());
        loadFromAndTo(dataSelectionDTO);
        calculationProfile.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                loadCalculationProfileLookUp();
            }
        });
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
        customer_MoveAllBtn.setEnabled(false);
        customer_moveLeftBtn.setEnabled(false);
        customer_moveRightBtn.setEnabled(false);
        product_moveAllBtn.setEnabled(false);
        product_moveLeftProduct.setEnabled(false);
        product_moveRightProduct.setEnabled(false);
        deduction_moveAllBtn.setEnabled(false);
        deduction_moveLeftBtn.setEnabled(false);
        deduction_moveRightBtn.setEnabled(false);
        generateBtn.setVisible(false);
        resetBtn.setVisible(false);
        saveViewBtn.setVisible(false);
        deleteViewBtn.setVisible(false);
    }

    private void setViewDetails() {
        try {
            deductionViewFlag = true;
            custLevelListenerFlag = false;
            prodLevelListenerFlag = false;
            deductionLevel.select(new HelperDTO(dataSelectionDTO.getDeductionLevel(), HelperListUtil.getInstance().getIdDescMap().get(dataSelectionDTO.getDeductionLevel())));
            description.setValue(dataSelectionDTO.getProjectionDescription());
            String adjType = dataSelectionDTO.getAdjustmentType().replace("~", " ");
            HelperDTO adjHeper = new HelperDTO(dataSelectionDTO.getAdjustmentId(), adjType);
            adjustmentType.setItemCaption(adjHeper, dataSelectionDTO.getSelectedAdjType());
            adjustmentType.select(adjHeper);
            rsContractSidList.clear();
            rsContractSidList.addAll(logic.getRsContractSids(dataSelectionDTO.getProjectionId()));
            hierarchyKeys.clear();
            Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, rsContractSidList, hierarchyKeys);
            selectedDeductionContainer.removeAllItems();
            setDeductionTree(levelKeys);
            customerHierarchy.setValue(dataSelectionDTO.getCustomerHierarchyName());
            productHierarchy.setValue(dataSelectionDTO.getProductHierarchyName());
            logic.loadCustomerRelation(customerRelation, dataSelectionDTO.getCustomerHierarchySid());
            logic.loadProductRelation(productRelation, dataSelectionDTO.getProductHierarchySid(), dataSelectionDTO.getCompanyMasterSid());
            logic.loadCustoProdLevels(customerLevel, dataSelectionDTO.getCustomerHierarchySid());
            logic.loadCustoProdLevels(productLevel, dataSelectionDTO.getProductHierarchySid());
            loadCustomerLevel();
            loadProductLevel();
            customerRelation.select(dataSelectionDTO.getCustRelationshipBuilderSid());
            productRelation.select(dataSelectionDTO.getProdRelationshipBuilderSid());
            customerLevel.select(dataSelectionDTO.getCustomerHierarchyLevel());
            productLevel.select(dataSelectionDTO.getProductHierarchyLevel());
            CommonLogic.loadCompanyAndBusinessUnit(company, "getCompanyQueryForDS");
            CommonLogic.loadCompanyAndBusinessUnit(businessUnit, "getBusinessQueryForDS");
            company.select(dataSelectionDTO.getCompanyMasterSid());
            businessUnit.select(dataSelectionDTO.getBu_companyMasterSid());
            customerDescriptionMap = logic.getLevelValueMap(String.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
            productDescriptionMap = logic.getLevelValueMap(String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
            initializeCustomerHierarchy(dataSelectionDTO.getProjectionId(), Integer.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
            initializeProductHierarchy(dataSelectionDTO.getProjectionId(), Integer.valueOf(dataSelectionDTO.getProductHierarchyLevel()));

            custLevelListenerFlag = true;
            prodLevelListenerFlag = true;
            loadFromAndTo(dataSelectionDTO);
            dataSelectionDTO.setFromPeriodMonth(fromPeriod.getItemCaption(fromPeriod.getValue()));

            // GAL-7834
            if (ARMConstants.getPipelineAccrual().equalsIgnoreCase(adjType) || ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(adjType)
                    || ARMConstants.getTransaction6().equalsIgnoreCase(adjType)
                    || ARMConstants.getTransaction7().equalsIgnoreCase(adjType)) {
                toPeriod.select(0);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
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
                        String parentarr[] = selection.getParentNode().split("~");
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
        selectedCustomer.setVisibleColumns(new Object[]{"displayValue"});
        selectedCustomer.setColumnHeaders(new String[]{"Customer Hierarchy Group Builder"});
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
                        String parentarr[] = selection.getParentNode().split("~");
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
        selectedProduct.setVisibleColumns(new Object[]{"displayValue"});
        selectedProduct.setColumnHeaders(new String[]{"Product Hierarchy Group Builder"});
        for (LevelDTO ddo : selectedProductContainer.getItemIds()) {
            selectedProduct.setCollapsed(ddo, false);
        }
    }

    private void setDeductionTree(Map<String, DeductionLevelDTO> levelKeys) {
        List<HierarchyString> strkeys = HierarchyString.getHierarchyStringList(hierarchyKeys, true);
        for (HierarchyString hKey : strkeys) {
            String key = hKey.getString();
            DeductionLevelDTO value = levelKeys.get(key);
            String parentKey = key.substring(0, key.lastIndexOf('.'));
            if (parentKey.lastIndexOf('.') >= 0) {
                parentKey = parentKey.substring(0, parentKey.lastIndexOf('.') + 1);
            }
            selectedDeductionContainer.addItem(value);
            DeductionLevelDTO parent = levelKeys.get(parentKey);

            if (parent != null) {
                selectedDeductionContainer.setParent(value, parent);
            }
            if (StringUtils.countMatches(key, ".") == NumericConstants.NINE) {
                selectedDeductionContainer.setChildrenAllowed(value, false);
            }
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
            int glCompId = Integer.valueOf(company.getValue().toString());
            int bUnitCompSid = Integer.valueOf(businessUnit.getValue().toString());
            fromPeriod.removeAllItems();
            toPeriod.removeAllItems();
            configurePeriodDropDown(fromPeriod);
            configurePeriodDropDown(toPeriod);
            DataSelectionLogic.getDates(-2, glCompId, bUnitCompSid, fromPeriod, toPeriod,true);
            fromPeriod.select(selection.getFromPeriod());
            toPeriod.select(selection.getToPeriod());
            periodView = DataSelectionLogic.getPeriodViewForAdjustmentType(buscinessProcess, glCompId, bUnitCompSid);
            if (ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView)) {
                toPeriod.removeAllItems();
                configurePeriodDropDown(toPeriod);
                DataSelectionLogic.getDates(-2, glCompId, bUnitCompSid, toPeriod);
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

    public void setAdjustmentOptionsPanelForBalanceSummaryReport() {
        addCompanyBusinessUnit();
        addCalculationProfileLookUp();
        loadSummaryType();
    }

    public void addCalculationProfileLookUp() {
        VerticalLayout calculationRootVLayout = new VerticalLayout();

        HorizontalLayout calculationProfileLayout = new HorizontalLayout();
        calculationProfileLabel.addStyleName("labelresultalign");
        calculationProfileLabel.setWidth("150px");
        calculationProfileLayout.addComponent(calculationProfileLabel);
        calculationProfileLayout.addComponent(calculationProfile);
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
        CommonLogic.setComboBoxItemIDAndCaption(summaryTypeDdlb, "LoadBalanceSummaryType", Collections.EMPTY_LIST);
    }

    public CustomTextField getCalculationProfile() {
        return calculationProfile;
    }
    public void loadCalculationProfileLookUp() {
        calculationProfileLookUp = new CalculationProfileLookUp(ARMUtils.PRIVATE, screenName);
        getUI().addWindow(calculationProfileLookUp);

        calculationProfileLookUp.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (calculationProfileLookUp.isSelected()) {
                    calculationProfile.setValue(calculationProfileLookUp.getCalculationProfileDTO().getProfileName());
                    dataSelectionDTO.setCalculationProfileMasterSid(calculationProfileLookUp.getCalculationProfileDTO().getCalculationProfileId());
                    dataSelectionDTO.setCalculationProfileName(calculationProfileLookUp.getCalculationProfileDTO().getProfileName());
                }
            }
        });
    }
}
