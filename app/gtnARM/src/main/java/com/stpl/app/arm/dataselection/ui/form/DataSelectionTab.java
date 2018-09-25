/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.form;

import com.stpl.app.arm.abstractforms.AbstractDataSelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.DeductionLevelDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.DataSelectionQueryUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.Label;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Asha.Ravi
 */
public class DataSelectionTab extends AbstractDataSelection {

    @UiField("adjustmentTypeLabel")
    protected Label adjustmentTypeLabel;
    @UiField("descriptionLabel")
    protected Label descriptionLabel;
    private DataSelectionDTO selection;
    private DataSelectionLogic logic = new DataSelectionLogic();
    private static final Logger DS_LOGGER = LoggerFactory.getLogger(DataSelectionTab.class);
    private Set<Integer> rsContractSidList = new HashSet<>();
    private final List<String> hierarchyKeys = new ArrayList<>();
    private SessionDTO sessionDTO;

    public DataSelectionTab(DataSelectionDTO selection, SessionDTO sessionDTO) {
        super();
        this.selection = selection;
        this.sessionDTO = sessionDTO;
        init();
    }

    private void init() {
        panel1.setCaption("Adjustment Options");
        if (sessionDTO.isWorkFlow()) {
            configureDataSelection();
            setViewDetails();
            setReadOnlyMode();
        } else {
            configureDataSelection();
            loadValuesInDataSelection();
            setReadOnlyMode();
        }
    }

    @Override
    public void loadCustRelationAndLevel(int hierSid, CustomTextField.ClickEvent event) {
        DS_LOGGER.debug("Inside loadCustRelationAndLevel Method");

    }

    @Override
    public void loadProdRelationAndLevel(int hierSid, int glComp, CustomTextField.ClickEvent event) {
        DS_LOGGER.debug("Inside loadProdRelationAndLevel Method");
    }

    public void loadAvailableCustProd(int hierSid, int relSid, Property.ValueChangeEvent event) {
        DS_LOGGER.debug("Inside loadAvailableCustProd Method {}", (hierSid + StringUtils.EMPTY + relSid + event));
    }

    @Override
    public void loadAvailableCustomers() {
        DS_LOGGER.debug("Inside loadAvailableCustomers Method");
    }

    @Override
    public void loadAvailableProducts() {
        DS_LOGGER.debug("Inside loadAvailableProducts Method");
    }

    @Override
    public void customerHierarchyCloseListener() {
        DS_LOGGER.debug("Inside customerHierarchyCloseListener Method");
    }

    @Override
    public void productHierarchyCloseListener() {
        DS_LOGGER.debug("Inside productHierarchyCloseListener Method");
    }

    @Override
    public void moveLeftCustomersButtonLogic() {
        DS_LOGGER.debug("Inside moveLeftCustomersButtonLogic Method");
    }

    @Override
    public void moveRightCustomersButtonLogic() {
        DS_LOGGER.debug("Inside moveRightCustomersButtonLogic Method");
    }

    @Override
    public void customerRelationValueChange(Object value) {
        DS_LOGGER.debug("Inside customerRelationValueChange Method");
    }

    @Override
    public void productRelationValueChange(Object value) {
        DS_LOGGER.debug("Inside productRelationValueChange Method");
    }

    @Override
    public void moveLeftProductsButtonLogic() {
        DS_LOGGER.debug("Inside moveLeftProductsButtonLogic Method");
    }

    @Override
    public void moveRightProductsButtonLogic() {
        DS_LOGGER.debug("Inside moveRightProductsButtonLogic Method");
    }

    @Override
    public void moveAllProductsButtonLogic() {
        DS_LOGGER.debug("Inside moveAllProductsButtonLogic Method");
    }

    @Override
    public void moveAllCustomersButtonLogic() {
        DS_LOGGER.debug("Inside moveAllCustomersButtonLogic Method");
    }

    @Override
    public void generateButtonLogicForScreens() {
        DS_LOGGER.debug("Inside generateButtonLogicForScreens Method");
    }

    @Override
    public void loadPrivateViewLook() {
        DS_LOGGER.debug("Inside loadPrivateViewLook Method");
    }

    @Override
    public void loadPublicViewLook() {
        DS_LOGGER.debug("Inside loadPublicViewLook Method");
    }

    @Override
    public void loadAvailableDeductions() {
        DS_LOGGER.debug("Inside loadAvailableDeductions Method");
    }

    @Override
    public void moveLeftToRightDeductions() {
        DS_LOGGER.debug("Inside moveLeftToRightDeductions Method");
    }

    @Override
    public void privateLookupCloseListener() {
        DS_LOGGER.debug("Inside privateLookupCloseListener Method");
    }

    @Override
    public void publicLookupCloseListener() {
        DS_LOGGER.debug("Inside publicLookupCloseListener Method");
    }

    @Override
    public void moveAllDeductions() {
        DS_LOGGER.debug("Inside moveAllDeductions Method");
    }

    @Override
    public void moveRightToLeftDeductions() {
        DS_LOGGER.debug("Inside moveRightToLeftDeductions Method");
    }

    @Override
    public void saveViewLogic() {
        DS_LOGGER.debug("Inside saveViewLogic Method");
    }

    public void configureDataSelection() {
        buttonLay.setVisible(false);
        if (ARMConstants.getAdjustmentSummary().equals(selection.getAdjustmentType())) {
            adjustmentType.setVisible(false);
            description.setVisible(false);
            adjustmentTypeLabel.setVisible(false);
            descriptionLabel.setVisible(false);
        } else {
            adjustmentType.setVisible(true);
            description.setVisible(true);
            adjustmentTypeLabel.setVisible(true);
            descriptionLabel.setVisible(true);
        }
    }

    private void loadValuesInDataSelection() {
        selectedDeduction.setContainerDataSource(selection.getSelectedDeductionContainer());
        selectedCustomer.setContainerDataSource(selection.getSelectedCustomerContainer());
        selectedProduct.setContainerDataSource(selection.getSelectedProductContainer());
        selectedCustomer.setVisibleColumns(new Object[]{CommonConstant.DISPLAY_VALUE});
        selectedCustomer.setColumnHeaders(new String[]{"Selected Customers"});
        selectedDeduction.setVisibleColumns("levelValue");
        selectedDeduction.setColumnHeader("levelValue", "Selected Deductions");
        selectedProduct.setVisibleColumns(CommonConstant.DISPLAY_VALUE);
        selectedProduct.setColumnHeader(CommonConstant.DISPLAY_VALUE, "Selected Products");
        CommonLogic.loadCompanyAndBusinessUnit(company, "getCompanyQueryForDS");
        CommonLogic.loadCompanyAndBusinessUnit(businessUnit, "getBusinessQueryForDS");
        logic.loadCustomerRelation(customerRelation, selection.getCustomerHierarchySid());
        logic.loadProductRelation(productRelation, selection.getProductHierarchySid(), selection.getCompanyMasterSid());
        logic.loadCustoProdLevels(customerLevel, selection.getCustomerHierarchySid());
        logic.loadCustoProdLevels(productLevel, selection.getProductHierarchySid());
        customerHierarchy.setValue(selection.getCustomerHierarchyName());
        customerRelation.select(selection.getCustRelationshipBuilderSid());
        customerLevel.select(selection.getCustomerHierarchyLevel());
        productHierarchy.setValue(selection.getProductHierarchyName());
        productRelation.select(selection.getProdRelationshipBuilderSid());
        productLevel.select(selection.getProductHierarchyLevel());
        deductionLevel.select(HelperListUtil.getInstance().getIdHelperDTOMap().get(selection.getDeductionLevel()));
        company.select(selection.getCompanyMasterSid());
        businessUnit.select(selection.getBucompanyMasterSid());
        if (ARMConstants.getPipelineAccrual().equalsIgnoreCase(selection.getAdjustmentType()) || ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(selection.getAdjustmentType())
                || ARMConstants.getTransaction6().equalsIgnoreCase(selection.getAdjustmentType())
                || ARMConstants.getTransaction7().equalsIgnoreCase(selection.getAdjustmentType())) {
            toPeriod.removeAllItems();
            configurePeriodDropDown(toPeriod);
            toPeriod.setEnabled(false);
        }
        if (!ARMConstants.getAdjustmentSummary().equals(selection.getAdjustmentType())) {
            adjustmentType.select(new HelperDTO(selection.getAdjustmentId(), selection.getAdjustmentType()));
            description.setValue(selection.getProjectionDescription());
        }
        if (ARMUtils.PRIVATE.equalsIgnoreCase(selection.getViewType())) {
            privateView.setValue(selection.getViewName());
        } else if (ARMUtils.PUBLIC.equalsIgnoreCase(selection.getViewType())) {
            publicView.setValue(selection.getViewName());
        }
        loadFromAndTo(selection);
    }

    public void setReadOnlyMode() {
        boolean isReadOnly = true;
        boolean isEnabled = false;
        privateView.setEnabled(isEnabled);
        publicView.setEnabled(isEnabled);
        company.setReadOnly(isReadOnly);
        businessUnit.setReadOnly(isReadOnly);
        customerHierarchy.setEnabled(isEnabled);
        customerRelation.setReadOnly(isReadOnly);
        customerLevel.setReadOnly(isReadOnly);
        productLevel.setReadOnly(isReadOnly);
        availableCustomer.setReadOnly(isReadOnly);
        selectedCustomer.setReadOnly(isReadOnly);
        deductionLevel.setReadOnly(isReadOnly);
        availableDeduction.setReadOnly(isReadOnly);
        selectedDeduction.setReadOnly(isReadOnly);
        productHierarchy.setEnabled(isEnabled);
        productRelation.setReadOnly(isReadOnly);
        availableProduct.setReadOnly(isReadOnly);
        fromPeriod.setReadOnly(isReadOnly);
        toPeriod.setReadOnly(isReadOnly);
        adjustmentType.setReadOnly(isReadOnly);
        description.setReadOnly(isReadOnly);
        customerMoveAllBtn.setEnabled(isEnabled);
        customerMoveLeftBtn.setEnabled(isEnabled);
        customerMoveRightBtn.setEnabled(isEnabled);
        productMoveAllBtn.setEnabled(isEnabled);
        productMoveLeftProduct.setEnabled(isEnabled);
        productMoveRightProduct.setEnabled(isEnabled);
        deductionMoveAllBtn.setEnabled(isEnabled);
        deductionMoveLeftBtn.setEnabled(isEnabled);
        deductionMoveRightBtn.setEnabled(isEnabled);
        generateBtn.setVisible(isEnabled);
        resetBtn.setVisible(isEnabled);
        saveViewBtn.setVisible(isEnabled);
        deleteViewBtn.setVisible(isEnabled);
    }

    private void setViewDetails() {
        try {
            deductionViewFlag = true;
            custLevelListenerFlag = false;
            prodLevelListenerFlag = false;
            deductionLevel.select(new HelperDTO(selection.getDeductionLevel(), HelperListUtil.getInstance().getIdDescMap().get(selection.getDeductionLevel())));
            description.setValue(selection.getProjectionDescription());
            String adjType = selection.getAdjustmentType().replace("~", ARMUtils.SPACE.toString());
            HelperDTO adjHeper = new HelperDTO(selection.getAdjustmentId(), adjType);
            adjustmentType.setItemCaption(adjHeper, selection.getSelectedAdjType());
            adjustmentType.select(adjHeper);
            rsContractSidList.clear();
            rsContractSidList.addAll(logic.getRsContractSids(selection.getProjectionId()));
            hierarchyKeys.clear();
            Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, rsContractSidList, hierarchyKeys);
            selectedDeductionContainer.removeAllItems();
            setDeductionTree(levelKeys, hierarchyKeys);
            customerHierarchy.setValue(selection.getCustomerHierarchyName());
            productHierarchy.setValue(selection.getProductHierarchyName());
            logic.loadCustomerRelation(customerRelation, selection.getCustomerHierarchySid());
            logic.loadProductRelation(productRelation, selection.getProductHierarchySid(), selection.getCompanyMasterSid());
            logic.loadCustoProdLevels(customerLevel, selection.getCustomerHierarchySid());
            logic.loadCustoProdLevels(productLevel, selection.getProductHierarchySid());
            loadCustomerLevel();
            loadProductLevel();
            customerRelation.select(selection.getCustRelationshipBuilderSid());
            productRelation.select(selection.getProdRelationshipBuilderSid());
            customerLevel.select(selection.getCustomerHierarchyLevel());
            productLevel.select(selection.getProductHierarchyLevel());
            CommonLogic.loadCompanyAndBusinessUnit(company, "getCompanyQueryForDS");
            CommonLogic.loadCompanyAndBusinessUnit(businessUnit, "getBusinessQueryForDS");
            company.select(selection.getCompanyMasterSid());
            businessUnit.select(selection.getBucompanyMasterSid());
            custVersionMap = logic.loadCustomerRelation(customerRelation, selection.getCustomerHierarchySid());
            prodVersionMap = logic.loadCustomerRelation(customerRelation, selection.getProductHierarchySid());
            customerDescriptionMap = new DataSelectionQueryUtils()
                    .loadLevelValuesMap(selection.getCustRelationshipBuilderSid(),
                            custVersionMap.get(selection.getCustRelationshipBuilderSid()),
                            selection.getCustomerHierarchySid(),
                            selection.getCustomerHierarchyVersionNo());

            productDescriptionMap = new DataSelectionQueryUtils()
                    .loadLevelValuesMap(selection.getProdRelationshipBuilderSid(),
                            prodVersionMap.get(selection.getProdRelationshipBuilderSid()),
                            selection.getProductHierarchySid(),
                            selection.getProductHierarchyVersionNo());
            initializeCustomerHierarchy(selection.getProjectionId(), Integer.parseInt(selection.getCustomerHierarchyLevel()));
            initializeProductHierarchy(selection.getProjectionId(), Integer.parseInt(selection.getProductHierarchyLevel()));

            custLevelListenerFlag = true;
            prodLevelListenerFlag = true;
            loadFromAndTo(selection);
            selection.setFromPeriodMonth(fromPeriod.getItemCaption(fromPeriod.getValue()));

            // GAL-7834
            if (ARMConstants.getPipelineAccrual().equalsIgnoreCase(adjType) || ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(adjType)
                    || ARMConstants.getTransaction6().equalsIgnoreCase(adjType)
                    || ARMConstants.getTransaction7().equalsIgnoreCase(adjType)) {
                toPeriod.select(0);
            }
        } catch (Exception e) {
            DS_LOGGER.error("Error in setViewDetails :", e);
        }
    }

    public void loadCustomerLevel() {
        try {
            innerCustLevels = logic.loadCustomerForecastLevel(selection.getCustomerHierarchySid(), selection.getCustomerHierarchyName());
        } catch (Exception ex) {
            DS_LOGGER.error(" in loadCustomerLevel", ex);
        }

    }

    public void loadProductLevel() {
        try {
            innerProdLevels = logic.loadCustomerForecastLevel(selection.getProductHierarchySid(), selection.getProductHierarchyName());
        } catch (Exception ex) {
            DS_LOGGER.error(" loadProductLevel", ex);
        }
    }

    /**
     * Load customer hierarchy.
     *
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void initializeCustomerHierarchy(final int projectionId, int customerLevel) {
        DataSelectionLogic CustHierarchyLogic = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = CustHierarchyLogic.getRelationShipValues(projectionId, "customer", customerLevel, customerDescriptionMap);
        createHierarchyBasedOnHierarchyNo(selectedCustomerContainer, reslistOne, customerLevel);
        selectedCustomer.setContainerDataSource(selectedCustomerContainer);

        selectedCustomer.setVisibleColumns(new Object[]{CommonConstant.DISPLAY_VALUE});

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
        DataSelectionLogic initProdHierarchyDsLogic = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = initProdHierarchyDsLogic.getRelationShipValues(projectionId, "product", productLevel, productDescriptionMap);
        createHierarchyBasedOnHierarchyNo(selectedProductContainer, reslistOne, productLevel);
        selectedProduct.setContainerDataSource(selectedProductContainer);

        selectedProduct.setVisibleColumns(new Object[]{CommonConstant.DISPLAY_VALUE});

        selectedProduct.setColumnHeaders(new String[]{"Product Hierarchy Group Builder"});

        for (LevelDTO ddo : selectedProductContainer.getItemIds()) {
            selectedProduct.setCollapsed(ddo, false);
        }
    }

    public void setLevels(DeductionLevelDTO parentList, List<DeductionLevelDTO> list, int levelNo) {
        for (DeductionLevelDTO typeLevel : list) {
            if (levelNo != NumericConstants.EIGHT) {
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
        if ((ARMConstants.getAdjustmentSummary().equals(selection.getAdjustmentType()) || adjustmentType.getValue() != null) && company.getValue() != null && businessUnit.getValue() != null) {
            int buscinessProcess = -1;
            if (!ARMConstants.getAdjustmentSummary().equals(selection.getAdjustmentType())) {
                buscinessProcess = ((HelperDTO) adjustmentType.getValue()).getId();
            }
            int glCompId = ARMUtils.getIntegerValue(company.getValue().toString());
            int bUnitCompSid = ARMUtils.getIntegerValue(businessUnit.getValue().toString());
            fromPeriod.removeAllItems();
            toPeriod.removeAllItems();
            configurePeriodDropDown(fromPeriod);
            configurePeriodDropDown(toPeriod);
            DataSelectionLogic.getDates(buscinessProcess, glCompId, bUnitCompSid, fromPeriod, toPeriod, true, selection);
            fromPeriod.select(selection.getFromPeriod());
            toPeriod.select(selection.getToPeriod());
            if (ARMConstants.getPipelineAccrual().equalsIgnoreCase(selection.getAdjustmentType())
                    || ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(selection.getAdjustmentType()) || ARMConstants.getTransaction6().equalsIgnoreCase(selection.getAdjustmentType())
                    || ARMConstants.getTransaction7().equalsIgnoreCase(selection.getAdjustmentType())) {
                toPeriod.removeAllItems();
                configurePeriodDropDown(toPeriod);
                toPeriod.setEnabled(false);
            } else { // Added for GALUAT-711
                String periodView = DataSelectionLogic.getPeriodViewForAdjustmentType(buscinessProcess, glCompId, bUnitCompSid);
                if (ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView)) {
                    toPeriod.removeAllItems();
                    configurePeriodDropDown(toPeriod);
                    DataSelectionLogic.getDates(buscinessProcess, glCompId, bUnitCompSid, toPeriod);
                    toPeriod.select(String.valueOf(fromPeriod.getValue()));
                    toPeriod.setEnabled(false);
                } else {
                    toPeriod.setEnabled(true);
                }
            } //Ends here
        } else {
            fromPeriod.removeAllItems();
            toPeriod.removeAllItems();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
