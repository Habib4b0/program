/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.abstractforms.AbstractDataSelection;
import com.stpl.app.arm.balancesummaryreport.ui.BalanceSummaryReportWindow;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.DeductionLevelDTO;
import com.stpl.app.arm.dataselection.dto.HierarchyLookupDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.dto.ViewDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.dataselection.ui.lookups.HierarchyLookup;
import com.stpl.app.arm.dataselection.ui.lookups.SaveViewLookUp;
import com.stpl.app.arm.dataselection.ui.lookups.ViewSearchLookUp;
import com.stpl.app.arm.dataselection.view.DataSelectionView;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.DataSelectionQueryUtils;
import com.stpl.app.arm.utils.DataSelectionUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.ConstantsUtils;

import com.stpl.app.utils.VariableConstants;
import com.stpl.gtn.gtn2o.ws.arm.dataselection.bean.GtnARMHierarchyInputBean;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.logic.HierarchyString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryReportDataSelection extends AbstractDataSelection {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceSummaryReportDataSelection.class);
    private DataSelectionLogic logic = new DataSelectionLogic();
    private DataSelectionDTO bsrDataSelectionDTO = new DataSelectionDTO();
    private final List<Integer> bsrCustomerSidList = new ArrayList<>();
    protected SummarySelection bsrSelection = new SummarySelection();

    private String screenName;
    @UiField("adjustmentTypeLabel")
    protected Label bsrAdjustmentTypeLabel;
    @UiField("descriptionLabel")
    protected Label bsrDescriptionLabel;
    private SessionDTO bsrSessionDTO;
    private final Set<Integer> bsrRsContractSids = new HashSet<>();
    private final List<String> bsrHierarchyKeys = new ArrayList<>();

    //Used for CCP_HIERARCHY_INSERT query formation
    private String topLevelName = StringUtils.EMPTY;
    private String periodView = StringUtils.EMPTY;
    private List<Integer> bsrCustomerHierarchyLevelDefnList = new ArrayList<>();
    private Map<Integer, Integer> bsrCustomerVersionMap = new HashMap<>();
    private Map<Integer, Integer> bsrProductVersionMap = new HashMap<>();
    private List<Integer> bsrProductHierarchyLevelDefnList = new ArrayList<>();

    public BalanceSummaryReportDataSelection(String screenName, SessionDTO sessionDTO) {
        super();
        this.bsrSessionDTO = sessionDTO;
        this.screenName = screenName;
        configureFields();
        securityForAllScreens();
    }

    @UiHandler("companyDDLB")
    public void companyDdlbVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();

        if (val == null) {
            bsrDataSelectionDTO.setCompanyMasterSid(0);
        } else {
            bsrDataSelectionDTO.setCompanyMasterSid((int) val);
        }
        loadFromAndTo(bsrDataSelectionDTO);
        loadAvailableProducts();
    }

    @UiHandler("businessUnit")
    public void businessDdlbVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();
        if (val == null) {
            bsrDataSelectionDTO.setBucompanyMasterSid(0);
        } else {
            bsrDataSelectionDTO.setBucompanyMasterSid((int) val);
        }
        loadFromAndTo(bsrDataSelectionDTO);
        loadAvailableProducts();
    }

    @Override
    public void loadCustRelationAndLevel(int custHierSid, CustomTextField.ClickEvent event) {
        bsrCustomerVersionMap = logic.loadCustomerRelation(customerRelation, custHierSid);
        bsrCustomerHierarchyLevelDefnList = logic.loadCustoProdLevels(customerLevel, custHierSid);
        customerBeanList.clear();
    }

    @Override
    public void loadProdRelationAndLevel(int prodHierSid, int glComp, CustomTextField.ClickEvent event) {
        bsrProductVersionMap = logic.loadProductRelation(productRelation, prodHierSid, glComp);
        bsrProductHierarchyLevelDefnList = logic.loadCustoProdLevels(productLevel, prodHierSid);
        productBeanList.clear();
    }

    @Override
    public void loadAvailableCustomers() {
        availableCustomer.removeAllItems();
        availableCustomerContainer.removeAllItems();
        String bsrLevelName = "Level";
        Object customerValue = customerLevel.getItemCaption(customerLevel.getValue());
        try {
            int forecastLevel = 0;
            List<LevelDTO> custVlues = null;
            if (customerValue != null && customerRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(customerRelation.getValue())) {
                String selectedLevel = String.valueOf(customerValue);
                if (customerRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(customerRelation.getValue()) && !innerCustLevels.isEmpty()) {

                    String relationshipSid = String.valueOf(customerRelation.getValue());
                    int relSid = relationshipSid.isEmpty() || "null".equals(relationshipSid) ? 0 : Integer.valueOf(relationshipSid);
                    DataSelectionLogic logicVal = new DataSelectionLogic();
                    String[] val = selectedLevel.split(" ");
                    forecastLevel = Integer.parseInt(val[1]);
                    LevelDTO tempDto = innerCustLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        bsrLevelName = tempDto.getLevel();
                        tempDto.getLevelNo();
                    }
                    List<LevelDTO> customerHierarchyLevelDefinitionList = logicVal.getHierarchyLevelDefinition(customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getVersionNo());;
                    LevelDTO selectedHierarchyLevelDto = customerHierarchyLevelDefinitionList.get(forecastLevel - 1);
                    custVlues = logicVal.loadCustomerInnerLevel(createInputBean(customerHierarchyLookup.getHierarchyDto(), relSid,
                            bsrCustomerVersionMap.get(relSid), tempDto.getLevelNo(), bsrCustomerHierarchyLevelDefnList.get(tempDto.getLevelNo() - 1), false, bsrRsContractSids), customerDescriptionMap, selectedHierarchyLevelDto);
                    availableCustomerContainer.addAll(custVlues);
                }

            }
            availableCustomer.setContainerDataSource(availableCustomerContainer);
            Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE};
            availableCustomer.setVisibleColumns(obj);
            String[] str = new String[]{bsrLevelName};
            availableCustomer.setColumnHeaders(str);
            availableCustomer.setSelectable(true);
            availableCustomer.setFilterBarVisible(true);
            availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
            availableCustomer.setStyleName("filtertable");
        } catch (Exception ex) {
            LOGGER.error(" Customer level ValueChangeListener ", ex);
        }
    }

    @Override
    public void customerHierarchyCloseListener() {
        try {
            customerHierarchyLookup.addCloseListener((Window.CloseEvent e) -> {
                LOGGER.debug("Inside Close Event");
                customerValueChange();
                innerCustLevels = logic.loadCustomerForecastLevel(customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getHierarchyName());
            });
        } catch (Exception e) {
            LOGGER.error("Error in customerHierarchyCloseListener :", e);
        }
    }

    @Override
    public void productHierarchyCloseListener() {
        productHierarchyLookup.addCloseListener((Window.CloseEvent e) -> {
            productValueChange(bsrDataSelectionDTO.getCompanyMasterSid());
        });
    }

    @Override
    public void productValueChange(int glComp) {
        super.productValueChange(glComp);
        innerProdLevels = logic.loadCustomerForecastLevel(productHierarchyLookup.getHierarchyDto().getHierarchyId(), productHierarchyLookup.getHierarchyDto().getHierarchyName());
    }

    @Override
    public void customerRelationValueChange(Object value) {
        if (custLevelListenerFlag) {
            if (value != null) {
                try {
                    availableCustomer.removeAllItems();
                    availableCustomerContainer.removeAllItems();
                    selectedCustomer.removeAllItems();
                    selectedCustomerContainer.removeAllItems();
                    customerLevel.select(null);
                    int relationshipSid = (Integer) customerRelation.getValue();
                    customerDescriptionMap = new DataSelectionQueryUtils().loadLevelValuesMap(relationshipSid, bsrCustomerVersionMap.get(relationshipSid), customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getVersionNo());
                    customerBeanList.clear();
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            } else {
                availableCustomer.removeAllItems();
                availableCustomerContainer.removeAllItems();
                selectedCustomer.removeAllItems();
                selectedCustomerContainer.removeAllItems();
                customerLevel.select(null);
                customerDescriptionMap = null;
            }
        }
    }

    @Override
    public void productRelationValueChange(Object value) {
        if (prodLevelListenerFlag) {
            if (value != null) {
                try {
                    availableProduct.removeAllItems();
                    availableProductContainer.removeAllItems();
                    selectedProduct.removeAllItems();
                    selectedProductContainer.removeAllItems();
                    productLevel.select(null);
                    int relationshipSid = (Integer) productRelation.getValue();
                    productDescriptionMap = new DataSelectionQueryUtils().loadLevelValuesMap(relationshipSid,
                            bsrProductVersionMap.get(relationshipSid), productHierarchyLookup.getHierarchyDto().getHierarchyId(),
                            productHierarchyLookup.getHierarchyDto().getVersionNo());
                    productBeanList.clear();
                } catch (Exception ex) {
                    LOGGER.error("customerRelation value change", ex);
                }
            } else {
                availableProduct.removeAllItems();
                availableProductContainer.removeAllItems();
                selectedProduct.removeAllItems();
                selectedProductContainer.removeAllItems();
                productLevel.select(null);
                productDescriptionMap = null;
            }
        }
    }

    public void loadCustomerLevel() {
        try {
            innerCustLevels = logic.loadCustomerForecastLevel(bsrDataSelectionDTO.getCustomerHierarchySid(), bsrDataSelectionDTO.getCustomerHierarchyName());
        } catch (Exception ex) {
            LOGGER.error("loadCustomerLevel", ex);
        }

    }

    public void loadProductLevel() {
        try {
            innerProdLevels = logic.loadCustomerForecastLevel(bsrDataSelectionDTO.getProductHierarchySid(), bsrDataSelectionDTO.getProductHierarchyName());
        } catch (Exception ex) {
            LOGGER.error(" loadProductLevel", ex);
        }
    }

    @Override
    public void loadAvailableProducts() {
        try {
            availableProductContainer.removeAllItems();
            availableProduct.removeAllItems();
            int bsrProdforecastLevel = 0;
            boolean isNdc = false;
            List<LevelDTO> bsrInnerLevelValues = null;
            String bsrLevelName = "Level";
            String bsrSelectedLevel = productLevel.getValue() == null ? StringUtils.EMPTY : productLevel.getValue().toString();
            if ((bsrSelectedLevel != null && !bsrSelectedLevel.equalsIgnoreCase(StringUtils.EMPTY) && !ARMUtils.NULL.equals(bsrSelectedLevel) && !GlobalConstants.getSelectOne().equals(bsrSelectedLevel)
                    && productRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(productRelation.getValue())) && (productRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(productRelation.getValue()) && !innerProdLevels.isEmpty())) {

                int prodRelationshipSid = (Integer) productRelation.getValue();
                DataSelectionLogic logicVal = new DataSelectionLogic();
                bsrProdforecastLevel = Integer.parseInt(bsrSelectedLevel);
                LevelDTO tempDto = innerProdLevels.get(bsrProdforecastLevel - 1);
                if (tempDto.getLevel() != null) {
                    bsrLevelName = tempDto.getLevel();
                }

                if ((tempDto.getLevel() != null && (ARMUtils.NDC.equalsIgnoreCase(tempDto.getLevel()) || "Item".equalsIgnoreCase(tempDto.getLevel()) || ARMUtils.PRODUCT.equalsIgnoreCase(tempDto.getLevel()))) && ARMUtils.ITEM_MASTER.equals(tempDto.getTableName())) {

                    isNdc = true;
                } else {
                    isNdc = false;
                }
                bsrCustomerSidList.clear();
                getChildIds(selectedCustomerContainer, bsrCustomerSidList);
                int custhierarchyId = customerHierarchyLookup.getHierarchyDto().getHierarchyId();
                List<LevelDTO> selectedCustomerContractList = getSelectedCustomerContractList();
                GtnARMHierarchyInputBean bean = createInputBean(productHierarchyLookup.getHierarchyDto(), prodRelationshipSid,
                        bsrProductVersionMap.get(prodRelationshipSid), tempDto.getLevelNo(),
                        bsrProductHierarchyLevelDefnList.get(tempDto.getLevelNo() - 1), isNdc, bsrRsContractSids);
                GtnARMHierarchyInputBean inputBean = loadCustomersInInputbean(bean, bsrCustomerVersionMap.get((Integer) customerRelation.getValue()),
                        selectedCustomerContractList, custhierarchyId, customerHierarchyLookup.getHierarchyDto().getVersionNo());
                bsrInnerLevelValues = logicVal.loadProductInnerLevel(inputBean, productDescriptionMap);
                availableProductContainer.addAll(bsrInnerLevelValues);
                availableProduct.setContainerDataSource(availableProductContainer);
                if (isNdc) {
                    Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE, "form", "strength"};
                    availableProduct.setVisibleColumns(obj);
                    String[] value = new String[]{ARMUtils.NDC, "Form", "Strength"};
                    availableProduct.setColumnHeaders(value);
                } else {
                    Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE};
                    availableProduct.setVisibleColumns(obj);
                    String[] value = new String[]{bsrLevelName};
                    availableProduct.setColumnHeaders(value);
                }
                availableProduct.setFilterBarVisible(true);
                availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
                availableProduct.setStyleName("filtertable");
            }
        } catch (NullPointerException e) {
            LOGGER.error(" business Unit Ddlb might not have been selected", e);
        } catch (Exception ex) {
            LOGGER.error("Error in loadAvailableProducts :", ex);
        }
    }

    public void getChildIds(ExtTreeContainer<LevelDTO> container, List<Integer> ids) {
        Collection<?> bsrParentCustids = container.rootItemIds();
        if (bsrParentCustids != null && !bsrParentCustids.isEmpty()) {
            getChildIds(container, ids, bsrParentCustids, null);
        }
    }

    public void getChildIds(ExtTreeContainer<LevelDTO> bsrContainer, List<Integer> bsrRelSids, Collection<?> bsrParentCustids, Object parent) {
        if (bsrParentCustids != null && !bsrParentCustids.isEmpty()) {
            Iterator<?> x = bsrParentCustids.iterator();
            while (x.hasNext()) {
                Object y = x.next();
                if (bsrContainer.hasChildren(y)) {
                    getChildIds(bsrContainer, bsrRelSids, bsrContainer.getChildren(y), y);
                } else {
                    LevelDTO dto = (LevelDTO) y;
                    bsrRelSids.add(dto.getRelationshipLevelSid());
                }
            }
        } else if (parent != null) {
            LevelDTO dto = (LevelDTO) parent;
            bsrRelSids.add(dto.getRelationshipLevelSid());
        }
    }

    @Override
    public void moveLeftCustomersButtonLogic() {
        try {
            DataSelectionLogic bsrDsLogic = new DataSelectionLogic();
            if (availableCustomer.getValue() != null) {
                int bsrForecastLevel = Integer.parseInt(customerLevel.getValue().toString());
                if (customerLevel.getValue() != null) {
                    bsrForecastLevel = CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
                }

                Object bsrItem = availableCustomer.getValue();
                if (selectedCustomerContainer.size() > 0) {
                    if (selectedCustomer.getValue() != null) {

                        String bsrHierarchyNo = DataSelectionUtils.getBeanFromId(bsrItem).getHierarchyNo();
                        if (bsrHierarchyNo.length() > 0 && bsrHierarchyNo.charAt(bsrHierarchyNo.length() - 1) == '.') {
                            bsrHierarchyNo = bsrHierarchyNo.substring(0, bsrHierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtils.getBeanFromId(bsrItem).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<>();
                        List<LevelDTO> bsrNewParentLevels = null;
                        List<LevelDTO> bsrNewChildLevels = null;
                        hierarchyNos.add(bsrHierarchyNo + ".");
                        int pos = 0;
                        while (bsrHierarchyNo.contains(ARMUtils.DOT)) {
                            pos = bsrHierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                bsrHierarchyNo = bsrHierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(bsrHierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        List<String> bsrSelectedHierarchyNos = new ArrayList<>();
                        for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                bsrSelectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> uncommonValues = DataSelectionUtils.storeUncommonValues(hierarchyNos, bsrSelectedHierarchyNos);
                        List<String> removeValues = new ArrayList<>();
                        for (String uncommonHierValue : uncommonValues) {
                            if (bsrSelectedHierarchyNos.contains(uncommonHierValue)) {
                                removeValues.add(uncommonHierValue);
                            }
                        }
                        if (!removeValues.isEmpty()) {
                            uncommonValues.removeAll(removeValues);
                        }
                        if (!uncommonValues.isEmpty()) {
                            bsrNewParentLevels = bsrDsLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                        }

                        bsrNewChildLevels = bsrDsLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                        if (bsrNewParentLevels != null) {
                            int pos2 = 0;
                            String parentHierarchyNo;
                            LevelDTO bsrParent = null;
                            for (LevelDTO bsrNewLevel : bsrNewParentLevels) {
                                String bsrTempHNo = bsrNewLevel.getHierarchyNo();
                                if (bsrTempHNo.length() > 0 && bsrTempHNo.charAt(bsrTempHNo.length() - 1) == '.') {
                                    bsrTempHNo = bsrTempHNo.substring(0, bsrTempHNo.length() - 1);
                                }
                                pos2 = bsrTempHNo.lastIndexOf(ARMUtils.DOT);
                                if (pos2 != -1) {
                                    parentHierarchyNo = bsrTempHNo.substring(0, pos2) + ".";
                                } else {
                                    parentHierarchyNo = bsrTempHNo + ".";
                                }
                                if (customerBeanList.isEmpty() || !customerBeanList.contains(bsrNewLevel.getRelationshipLevelSid())) {
                                    customerBeanList.add(bsrNewLevel.getRelationshipLevelSid());
                                    selectedCustomerContainer.addBean(bsrNewLevel);
                                    if (bsrForecastLevel != bsrNewLevel.getLevelNo()) {
                                        selectedCustomerContainer.setChildrenAllowed(bsrNewLevel, true);
                                    } else {
                                        selectedCustomerContainer.setChildrenAllowed(bsrNewLevel, false);
                                    }
                                }

                                if (!StringUtils.isBlank(parentHierarchyNo)) {
                                    for (LevelDTO bsrSelectedLevel : selectedCustomerContainer.getItemIds()) {
                                        if (parentHierarchyNo.equals(String.valueOf(bsrSelectedLevel.getHierarchyNo()))) {
                                            bsrParent = bsrSelectedLevel;
                                            break;
                                        }
                                    }
                                }
                                selectedCustomerContainer.setParent(bsrNewLevel, bsrParent);
                                bsrParent = bsrNewLevel;
                            }
                            if (!bsrNewChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                LevelDTO childsParent = null;
                                for (LevelDTO bsrNewLevels : bsrNewChildLevels) {
                                    String bsrtempHierNo = bsrNewLevels.getHierarchyNo();
                                    if (bsrtempHierNo.length() > 0 && bsrtempHierNo.charAt(bsrtempHierNo.length() - 1) == '.') {
                                        bsrtempHierNo = bsrtempHierNo.substring(0, bsrtempHierNo.length() - 1);
                                    }
                                    pos3 = bsrtempHierNo.lastIndexOf(ARMUtils.DOT);
                                    if (pos3 != -1) {
                                        childHierarchyNo = bsrtempHierNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = bsrtempHierNo + ".";
                                    }
                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(bsrNewLevels.getRelationshipLevelSid())) {
                                        customerBeanList.add(bsrNewLevels.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(bsrNewLevels);
                                        if (bsrForecastLevel != bsrNewLevels.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(bsrNewLevels, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(bsrNewLevels, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (LevelDTO bsrseleLevel : selectedCustomerContainer.getItemIds()) {
                                            if (childHierarchyNo.equals(String.valueOf(bsrseleLevel.getHierarchyNo()))) {
                                                childsParent = bsrseleLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedCustomerContainer.setParent(bsrNewLevels, childsParent);
                                }
                            }

                        }

                    } else {
                        String bsrSelhierarchyNo = DataSelectionUtils.getBeanFromId(bsrItem).getHierarchyNo();
                        if (bsrSelhierarchyNo.length() > 0 && bsrSelhierarchyNo.charAt(bsrSelhierarchyNo.length() - 1) == '.') {
                            bsrSelhierarchyNo = bsrSelhierarchyNo.substring(0, bsrSelhierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtils.getBeanFromId(bsrItem).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<>();
                        List<LevelDTO> newParentLevels = null;
                        List<LevelDTO> newChildLevelsBsr = null;
                        hierarchyNos.add(bsrSelhierarchyNo + ".");
                        int pos = 0;
                        String selectedParentHierarchyNoBsr = StringUtils.EMPTY;
                        LevelDTO selectedParent = null;
                        while (bsrSelhierarchyNo.contains(ARMUtils.DOT)) {
                            pos = bsrSelhierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                bsrSelhierarchyNo = bsrSelhierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(bsrSelhierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        List<String> bsrselectedHierarchyNos = new ArrayList<>();
                        for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                bsrselectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> uncommonValuesBsr = DataSelectionUtils.storeUncommonValues(hierarchyNos, bsrselectedHierarchyNos);
                        List<String> removeValuesBsr = new ArrayList<>();
                        for (String uncommonHierValue : uncommonValuesBsr) {
                            if (bsrselectedHierarchyNos.contains(uncommonHierValue)) {
                                removeValuesBsr.add(uncommonHierValue);
                            }
                        }
                        if (!removeValuesBsr.isEmpty()) {
                            uncommonValuesBsr.removeAll(removeValuesBsr);
                        }
                        if (!uncommonValuesBsr.isEmpty()) {
                            String tempHNo = uncommonValuesBsr.get(0);
                            if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                            }
                            int pos2 = 0;
                            pos2 = tempHNo.lastIndexOf(ARMUtils.DOT);
                            if (pos2 != -1) {
                                selectedParentHierarchyNoBsr = tempHNo.substring(0, pos2) + ".";
                            } else {
                                selectedParentHierarchyNoBsr = tempHNo + ".";
                            }
                        }
                        if (!StringUtils.isBlank(selectedParentHierarchyNoBsr)) {
                            for (LevelDTO selectedLevelBsr : selectedCustomerContainer.getItemIds()) {
                                if (selectedParentHierarchyNoBsr.equals(String.valueOf(selectedLevelBsr.getHierarchyNo()))) {
                                    selectedParent = selectedLevelBsr;
                                    break;
                                }
                            }
                        }
                        if (!uncommonValuesBsr.isEmpty()) {
                            newParentLevels = bsrDsLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValuesBsr), customerDescriptionMap, customerRelation.getValue().toString());
                        }

                        newChildLevelsBsr = bsrDsLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                        if (newParentLevels != null) {
                            for (LevelDTO newLevel : newParentLevels) {
                                if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                    customerBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedCustomerContainer.addBean(newLevel);
                                    if (bsrForecastLevel != newLevel.getLevelNo()) {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                    }
                                    selectedCustomerContainer.setParent(newLevel, selectedParent);
                                    selectedParent = newLevel;
                                }
                            }
                            if (!newChildLevelsBsr.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNoBsr;
                                LevelDTO childsParent = null;
                                for (LevelDTO newLevel : newChildLevelsBsr) {
                                    String tempHNoBsr = newLevel.getHierarchyNo();
                                    if (tempHNoBsr.length() > 0 && tempHNoBsr.charAt(tempHNoBsr.length() - 1) == '.') {
                                        tempHNoBsr = tempHNoBsr.substring(0, tempHNoBsr.length() - 1);
                                    }
                                    pos3 = tempHNoBsr.lastIndexOf(ARMUtils.DOT);
                                    if (pos3 != -1) {
                                        childHierarchyNoBsr = tempHNoBsr.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNoBsr = tempHNoBsr + ".";
                                    }

                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        customerBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(newLevel);
                                        if (bsrForecastLevel != newLevel.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(childHierarchyNoBsr)) {
                                        for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                            if (childHierarchyNoBsr.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
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
                } else if (availableCustomer.getValue() != null && (DataSelectionUtils.getBeanFromId(availableCustomer.getValue()).getLevelNo() > 1)) {

                    String hierarchyBsrNo = DataSelectionUtils.getBeanFromId(bsrItem).getHierarchyNo();
                    if (hierarchyBsrNo.length() > 0 && hierarchyBsrNo.charAt(hierarchyBsrNo.length() - 1) == '.') {
                        hierarchyBsrNo = hierarchyBsrNo.substring(0, hierarchyBsrNo.length() - 1);
                    }
                    String currentHierarchyNo = DataSelectionUtils.getBeanFromId(bsrItem).getHierarchyNo();
                    List<String> hierarchyNosListBsr = new ArrayList<>();
                    List<LevelDTO> newParentLevels = null;
                    List<LevelDTO> newChildLevels = null;
                    hierarchyNosListBsr.add(hierarchyBsrNo + ".");
                    int pos = 0;
                    String selectedParentBsrHierarchyNo = StringUtils.EMPTY;
                    LevelDTO selectedParent2 = null;
                    while (hierarchyBsrNo.contains(ARMUtils.DOT)) {
                        pos = hierarchyBsrNo.lastIndexOf(ARMUtils.DOT);
                        if (pos != -1) {
                            hierarchyBsrNo = hierarchyBsrNo.substring(0, pos);
                        }
                        hierarchyNosListBsr.add(hierarchyBsrNo + ".");
                    }
                    Collections.reverse(hierarchyNosListBsr);
                    if (!StringUtils.isBlank(selectedParentBsrHierarchyNo)) {
                        for (LevelDTO selectedBsrLevel : selectedCustomerContainer.getItemIds()) {
                            if (selectedParentBsrHierarchyNo.equals(String.valueOf(selectedBsrLevel.getHierarchyNo()))) {
                                selectedParent2 = selectedBsrLevel;
                                break;
                            }
                        }
                    }
                    newParentLevels = bsrDsLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(hierarchyNosListBsr), customerDescriptionMap, customerRelation.getValue().toString());

                    newChildLevels = bsrDsLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                    if (newParentLevels != null) {
                        for (LevelDTO newLevel : newParentLevels) {
                            if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {

                                customerBeanList.add(newLevel.getRelationshipLevelSid());
                                selectedCustomerContainer.addBean(newLevel);
                                if (bsrForecastLevel != newLevel.getLevelNo()) {
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
                            String childHierarchyNoBsr;
                            LevelDTO bsrChildsParent = null;
                            for (LevelDTO newLevel : newChildLevels) {
                                String tempHNo = newLevel.getHierarchyNo();
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                pos3 = tempHNo.lastIndexOf(ARMUtils.DOT);
                                if (pos3 != -1) {
                                    childHierarchyNoBsr = tempHNo.substring(0, pos3) + ".";
                                } else {
                                    childHierarchyNoBsr = tempHNo + ".";
                                }
                                if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                    customerBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedCustomerContainer.addBean(newLevel);
                                    if (bsrForecastLevel != newLevel.getLevelNo()) {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                    }
                                }
                                if (!StringUtils.isBlank(childHierarchyNoBsr)) {
                                    for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                        if (childHierarchyNoBsr.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                            bsrChildsParent = selectedLevel;
                                            break;
                                        }
                                    }
                                }
                                selectedCustomerContainer.setParent(newLevel, bsrChildsParent);
                            }
                        }

                    }
                } else {
                    LevelDTO selectedParent = DataSelectionUtils.getBeanFromId(bsrItem);

                    String hierarchyNoBSr = DataSelectionUtils.getBeanFromId(bsrItem).getHierarchyNo();
                    if (hierarchyNoBSr.length() > 0 && hierarchyNoBSr.charAt(hierarchyNoBSr.length() - 1) == '.') {
                        hierarchyNoBSr = hierarchyNoBSr.substring(0, hierarchyNoBSr.length() - 1);
                    }
                    String currentHierarchyNo = DataSelectionUtils.getBeanFromId(bsrItem).getHierarchyNo();
                    List<LevelDTO> newChildLevels = null;
                    int pos = 0;
                    if (hierarchyNoBSr.contains(ARMUtils.DOT)) {
                        while (hierarchyNoBSr.contains(ARMUtils.DOT)) {
                            pos = hierarchyNoBSr.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                hierarchyNoBSr = hierarchyNoBSr.substring(0, pos) + ".";
                            }
                        }
                    }
                    if (customerBeanList.isEmpty() || !customerBeanList.contains(DataSelectionUtils.getBeanFromId(bsrItem).getRelationshipLevelSid())) {
                        customerBeanList.add(DataSelectionUtils.getBeanFromId(bsrItem).getRelationshipLevelSid());
                        selectedCustomerContainer.addBean(selectedParent);
                        if (bsrForecastLevel != selectedParent.getLevelNo()) {
                            selectedCustomerContainer.setChildrenAllowed(selectedParent, true);
                        } else {
                            selectedCustomerContainer.setChildrenAllowed(selectedParent, false);
                        }
                    }

                    newChildLevels = bsrDsLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);

                    if ((newChildLevels != null) && !newChildLevels.isEmpty()) {
                        int pos3 = 0;
                        String childHierarchyNoBSr;
                        LevelDTO childsParent = null;
                        for (LevelDTO newLevel : newChildLevels) {
                            String tempHNo = newLevel.getHierarchyNo();
                            if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                            }
                            pos3 = tempHNo.lastIndexOf(ARMUtils.DOT);
                            if (pos3 != -1) {
                                childHierarchyNoBSr = tempHNo.substring(0, pos3) + ".";
                            } else {
                                childHierarchyNoBSr = tempHNo + ".";
                            }
                            if (customerBeanList.isEmpty()) {
                                customerBeanList.add(newLevel.getRelationshipLevelSid());
                                selectedCustomerContainer.addBean(newLevel);
                                if (bsrForecastLevel != selectedParent.getLevelNo()) {
                                    selectedCustomerContainer.setChildrenAllowed(newLevel, true);
                                } else {
                                    selectedCustomerContainer.setChildrenAllowed(newLevel, false);
                                }
                            }

                            if (!StringUtils.isBlank(childHierarchyNoBSr)) {
                                for (LevelDTO selectedLevel4 : selectedCustomerContainer.getItemIds()) {
                                    if (childHierarchyNoBSr.equals(String.valueOf(selectedLevel4.getHierarchyNo()))) {
                                        childsParent = selectedLevel4;
                                        break;
                                    }
                                }
                            }
                            selectedCustomerContainer.setParent(newLevel, childsParent);
                        }
                    }

                }

            } else {
                AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
                        "No Level was selected to move. Please try again.");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void moveRightCustomersButtonLogic() {
        try {
            if (selectedCustomer.getValue() != null) {
                Object selectedCustomerItem = selectedCustomer.getValue();
                String customerLevelInString = ARMUtils.ZERO_STRING;
                if (customerLevel.getValue() != null && !String.valueOf(customerLevel.getValue()).equalsIgnoreCase(String.valueOf(GlobalConstants.getSelectOne()))) {
                    customerLevelInString = String.valueOf(customerLevel.getValue());
                }
                int customerCurrentLevel = CommonLogic.parseStringToInteger(customerLevelInString);
                DataSelectionUtils.removeItemsRecursively(selectedCustomerItem, selectedCustomer, availableCustomer, selectedCustomerContainer, availableCustomerContainer, customerCurrentLevel);
                selectedCustomerContainer.removeItem(DataSelectionUtils.getBeanFromId(selectedCustomerItem));
                selectedCustomer.removeItem(selectedCustomerItem);
                customerBeanList.clear();
                List<LevelDTO> selectedValueItem = selectedCustomerContainer.getItemIds();
                for (LevelDTO cusotmerDto : selectedValueItem) {
                    customerBeanList.add(cusotmerDto.getRelationshipLevelSid());
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
                        "No Level was selected to move. Please try again. ");
            }
        } catch (Exception ex) {
            LOGGER.error("Error in moveRightCustomersButtonLogic :", ex);
        }
    }

    @Override
    public void moveLeftProductsButtonLogic() {
        try {
            DataSelectionLogic productlogicVal = new DataSelectionLogic();
            int bsrProductforecastLevel = 0;

            if (productLevel.getValue() != null) {
                bsrProductforecastLevel = CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()));
            }

            if (availableProduct.getValue() != null) {
                Object item = availableProduct.getValue();
                if (selectedProductContainer.size() > 0) {
                    if (selectedProduct.getValue() != null) {

                        String bsrProductsHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        if (bsrProductsHierarchyNo.length() > 0 && bsrProductsHierarchyNo.charAt(bsrProductsHierarchyNo.length() - 1) == '.') {
                            bsrProductsHierarchyNo = bsrProductsHierarchyNo.substring(0, bsrProductsHierarchyNo.length() - 1);
                        }
                        String bsrProductCurrentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        List<String> bsrProductHierarchyNos = new ArrayList<>();
                        List<LevelDTO> bsrProductNewParentLevels = null;
                        List<LevelDTO> bsrProductNewChildLevels = null;
                        bsrProductHierarchyNos.add(bsrProductsHierarchyNo + ".");
                        int pos = 0;
                        while (bsrProductsHierarchyNo.contains(ARMUtils.DOT)) {
                            pos = bsrProductsHierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                bsrProductsHierarchyNo = bsrProductsHierarchyNo.substring(0, pos);
                            }
                            bsrProductHierarchyNos.add(bsrProductsHierarchyNo + ".");
                        }
                        Collections.reverse(bsrProductHierarchyNos);
                        List<String> bsrProductSelectedHierarchyNos = new ArrayList<>();
                        for (LevelDTO bsrProductSelectedLevel : selectedProductContainer.getItemIds()) {
                            if (!StringUtils.isBlank(bsrProductSelectedLevel.getHierarchyNo())) {
                                bsrProductSelectedHierarchyNos.add(bsrProductSelectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> bsrProductUncommonValues = DataSelectionUtils.storeUncommonValues(bsrProductHierarchyNos, bsrProductSelectedHierarchyNos);
                        List<String> bsrProductRemoveValues = new ArrayList<>();
                        for (String uncommonHierValue : bsrProductUncommonValues) {
                            if (bsrProductSelectedHierarchyNos.contains(uncommonHierValue)) {
                                bsrProductRemoveValues.add(uncommonHierValue);
                            }
                        }
                        if (!bsrProductRemoveValues.isEmpty()) {
                            bsrProductUncommonValues.removeAll(bsrProductRemoveValues);
                        }
                        if (!bsrProductUncommonValues.isEmpty()) {
                            bsrProductNewParentLevels = productlogicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(bsrProductUncommonValues), productDescriptionMap, productRelation.getValue().toString());
                        }
                        bsrProductNewChildLevels = productlogicVal.getChildLevelsWithHierarchyNo(bsrProductCurrentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
                        if (bsrProductNewParentLevels != null) {
                            int pos2 = 0;
                            String parentHierarchyNo;
                            LevelDTO parent = null;
                            for (LevelDTO bsrProductNewLevel : bsrProductNewParentLevels) {
                                String bsrProducttempHNo = bsrProductNewLevel.getHierarchyNo();
                                if (bsrProducttempHNo.length() > 0 && bsrProducttempHNo.charAt(bsrProducttempHNo.length() - 1) == '.') {
                                    bsrProducttempHNo = bsrProducttempHNo.substring(0, bsrProducttempHNo.length() - 1);
                                }
                                pos2 = bsrProducttempHNo.lastIndexOf(ARMUtils.DOT);
                                if (pos2 != -1) {
                                    parentHierarchyNo = bsrProducttempHNo.substring(0, pos2) + ".";
                                } else {
                                    parentHierarchyNo = bsrProducttempHNo + ".";
                                }
                                if (productBeanList.isEmpty() || !productBeanList.contains(bsrProductNewLevel.getRelationshipLevelSid())) {
                                    productBeanList.add(bsrProductNewLevel.getRelationshipLevelSid());
                                    selectedProductContainer.addBean(bsrProductNewLevel);
                                    if (bsrProductforecastLevel != bsrProductNewLevel.getLevelNo()) {
                                        selectedProductContainer.setChildrenAllowed(bsrProductNewLevel, true);
                                    } else {
                                        selectedProductContainer.setChildrenAllowed(bsrProductNewLevel, false);
                                    }
                                }

                                if (!StringUtils.isBlank(parentHierarchyNo)) {
                                    for (LevelDTO bsrProductsselectedLevel : selectedProductContainer.getItemIds()) {
                                        if (parentHierarchyNo.equals(String.valueOf(bsrProductsselectedLevel.getHierarchyNo()))) {
                                            parent = bsrProductsselectedLevel;
                                            break;
                                        }
                                    }
                                }
                                selectedProductContainer.setParent(bsrProductNewLevel, parent);
                                parent = bsrProductNewLevel;
                            }
                            if (!bsrProductNewChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String bsrchildHierarchyNoProduct;
                                LevelDTO childsParent = null;
                                for (LevelDTO bsrProductNewLevel2 : bsrProductNewChildLevels) {
                                    String bsrProducttempHNo2 = bsrProductNewLevel2.getHierarchyNo();
                                    if (bsrProducttempHNo2.length() > 0 && bsrProducttempHNo2.charAt(bsrProducttempHNo2.length() - 1) == '.') {
                                        bsrProducttempHNo2 = bsrProducttempHNo2.substring(0, bsrProducttempHNo2.length() - 1);
                                    }
                                    pos3 = bsrProducttempHNo2.lastIndexOf(ARMUtils.DOT);
                                    if (pos3 != -1) {
                                        bsrchildHierarchyNoProduct = bsrProducttempHNo2.substring(0, pos3) + ".";
                                    } else {
                                        bsrchildHierarchyNoProduct = bsrProducttempHNo2 + ".";
                                    }
                                    if (productBeanList.isEmpty() || !productBeanList.contains(bsrProductNewLevel2.getRelationshipLevelSid())) {
                                        productBeanList.add(bsrProductNewLevel2.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(bsrProductNewLevel2);
                                        if (bsrProductforecastLevel != bsrProductNewLevel2.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(bsrProductNewLevel2, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(bsrProductNewLevel2, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(bsrchildHierarchyNoProduct)) {
                                        for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                            if (bsrchildHierarchyNoProduct.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                childsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedProductContainer.setParent(bsrProductNewLevel2, childsParent);
                                }
                            }

                        }

                    } else {

                        String newChildLevelsHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        if (newChildLevelsHierarchyNo.length() > 0 && newChildLevelsHierarchyNo.charAt(newChildLevelsHierarchyNo.length() - 1) == '.') {
                            newChildLevelsHierarchyNo = newChildLevelsHierarchyNo.substring(0, newChildLevelsHierarchyNo.length() - 1);
                        }
                        String childLevelscurrentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        List<String> childLevelshierarchyNos = new ArrayList<>();
                        List<LevelDTO> newParentLevals = null;
                        List<LevelDTO> newChildLevals = null;
                        childLevelshierarchyNos.add(newChildLevelsHierarchyNo + ".");
                        int pos = 0;
                        String selectedParentHierarchyNo = StringUtils.EMPTY;
                        LevelDTO selectedParent = null;
                        while (newChildLevelsHierarchyNo.contains(ARMUtils.DOT)) {
                            pos = newChildLevelsHierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                newChildLevelsHierarchyNo = newChildLevelsHierarchyNo.substring(0, pos);
                            }
                            childLevelshierarchyNos.add(newChildLevelsHierarchyNo + ".");
                        }
                        Collections.reverse(childLevelshierarchyNos);
                        List<String> selectedHierarchyNos = new ArrayList<>();
                        for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> childProductUncomonValues = DataSelectionUtils.storeUncommonValues(childLevelshierarchyNos, selectedHierarchyNos);
                        List<String> childProductRemoveValues = new ArrayList<>();
                        for (String uncommonHierValue : childProductUncomonValues) {
                            if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                childProductRemoveValues.add(uncommonHierValue);
                            }
                        }
                        if (!childProductRemoveValues.isEmpty()) {
                            childProductUncomonValues.removeAll(childProductRemoveValues);
                        }
                        if (!childProductUncomonValues.isEmpty()) {
                            String childProducttempHNo = childProductUncomonValues.get(0);
                            if (childProducttempHNo.length() > 0 && childProducttempHNo.charAt(childProducttempHNo.length() - 1) == '.') {
                                childProducttempHNo = childProducttempHNo.substring(0, childProducttempHNo.length() - 1);
                            }
                            int pos2 = 0;
                            pos2 = childProducttempHNo.lastIndexOf(ARMUtils.DOT);
                            if (pos2 != -1) {
                                selectedParentHierarchyNo = childProducttempHNo.substring(0, pos2) + ".";
                            } else {
                                selectedParentHierarchyNo = childProducttempHNo + ".";
                            }
                        }
                        if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                            for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                    selectedParent = selectedLevel;
                                    break;
                                }
                            }
                        }
                        if (!childProductUncomonValues.isEmpty()) {
                            newParentLevals = productlogicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(childProductUncomonValues), productDescriptionMap, productRelation.getValue().toString());
                        }
                        newChildLevals = productlogicVal.getChildLevelsWithHierarchyNo(childLevelscurrentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
                        if (newParentLevals != null) {
                            for (LevelDTO newLevel : newParentLevals) {
                                if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                    productBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedProductContainer.addBean(newLevel);
                                    if (bsrProductforecastLevel != newLevel.getLevelNo()) {
                                        selectedProductContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedProductContainer.setChildrenAllowed(newLevel, false);
                                    }
                                    selectedProductContainer.setParent(newLevel, selectedParent);
                                    selectedParent = newLevel;
                                }
                            }
                            if (!newChildLevals.isEmpty()) {
                                int pos3 = 0;
                                String newChildLevalsChildHierarchyNo;
                                LevelDTO newChildLevalsParent = null;
                                for (LevelDTO newLevel : newChildLevals) {
                                    String newHierlevelstempHNo = newLevel.getHierarchyNo();
                                    if (newHierlevelstempHNo.length() > 0 && newHierlevelstempHNo.charAt(newHierlevelstempHNo.length() - 1) == '.') {
                                        newHierlevelstempHNo = newHierlevelstempHNo.substring(0, newHierlevelstempHNo.length() - 1);
                                    }
                                    pos3 = newHierlevelstempHNo.lastIndexOf(ARMUtils.DOT);
                                    if (pos3 != -1) {
                                        newChildLevalsChildHierarchyNo = newHierlevelstempHNo.substring(0, pos3) + ".";
                                    } else {
                                        newChildLevalsChildHierarchyNo = newHierlevelstempHNo + ".";
                                    }
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (bsrProductforecastLevel != newLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }

                                    if (!StringUtils.isBlank(newChildLevalsChildHierarchyNo)) {
                                        for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                            if (newChildLevalsChildHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                newChildLevalsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }

                                    selectedProductContainer.setParent(newLevel, newChildLevalsParent);
                                }
                            }

                        }

                    }
                } else if (availableProduct.getValue() != null && (DataSelectionUtils.getBeanFromId(availableProduct.getValue()).getLevelNo() > 1)) {

                    String existingProducthierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                    if (existingProducthierarchyNo.length() > 0 && existingProducthierarchyNo.charAt(existingProducthierarchyNo.length() - 1) == '.') {
                        existingProducthierarchyNo = existingProducthierarchyNo.substring(0, existingProducthierarchyNo.length() - 1);
                    }
                    String existingProductCurrentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                    List<String> existingProductHierarchyNos = new ArrayList<>();
                    List<LevelDTO> existingProductNewParentLevels = null;
                    List<LevelDTO> existingProductNewChildLevels = null;
                    existingProductHierarchyNos.add(existingProducthierarchyNo + ".");
                    int pos = 0;
                    String selectedParentHierarchyNo = StringUtils.EMPTY;
                    LevelDTO selectedParent2 = null;
                    while (existingProducthierarchyNo.contains(ARMUtils.DOT)) {
                        pos = existingProducthierarchyNo.lastIndexOf(ARMUtils.DOT);
                        if (pos != -1) {
                            existingProducthierarchyNo = existingProducthierarchyNo.substring(0, pos);
                        }
                        existingProductHierarchyNos.add(existingProducthierarchyNo + ".");
                    }
                    Collections.reverse(existingProductHierarchyNos);
                    if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                        for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                            if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                selectedParent2 = selectedLevel;
                                break;
                            }
                        }
                    }
                    existingProductNewParentLevels = productlogicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(existingProductHierarchyNos), productDescriptionMap, productRelation.getValue().toString());
                    existingProductNewChildLevels = productlogicVal.getChildLevelsWithHierarchyNo(existingProductCurrentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
                    if (existingProductNewParentLevels != null) {
                        for (LevelDTO existingProductNewLevel : existingProductNewParentLevels) {
                            if (productBeanList.isEmpty() || !productBeanList.contains(existingProductNewLevel.getRelationshipLevelSid())) {
                                productBeanList.add(existingProductNewLevel.getRelationshipLevelSid());
                                selectedProductContainer.addBean(existingProductNewLevel);
                                if (bsrProductforecastLevel != existingProductNewLevel.getLevelNo()) {
                                    selectedProductContainer.setChildrenAllowed(existingProductNewLevel, true);
                                } else {
                                    selectedProductContainer.setChildrenAllowed(existingProductNewLevel, false);
                                }
                                selectedProductContainer.setParent(existingProductNewLevel, selectedParent2);
                                selectedParent2 = existingProductNewLevel;
                            }
                        }
                        if (!existingProductNewChildLevels.isEmpty()) {
                            int pos3 = 0;
                            String existingProductsChildHierarchyNo;
                            LevelDTO childsParent = null;
                            for (LevelDTO existingProductsNewLevel : existingProductNewChildLevels) {
                                String existingProductstempHNo = existingProductsNewLevel.getHierarchyNo();
                                if (existingProductstempHNo.length() > 0 && existingProductstempHNo.charAt(existingProductstempHNo.length() - 1) == '.') {
                                    existingProductstempHNo = existingProductstempHNo.substring(0, existingProductstempHNo.length() - 1);
                                }
                                pos3 = existingProductstempHNo.lastIndexOf(ARMUtils.DOT);
                                if (pos3 != -1) {
                                    existingProductsChildHierarchyNo = existingProductstempHNo.substring(0, pos3) + ".";
                                } else {
                                    existingProductsChildHierarchyNo = existingProductstempHNo + ".";
                                }
                                if (productBeanList.isEmpty() || !productBeanList.contains(existingProductsNewLevel.getRelationshipLevelSid())) {
                                    productBeanList.add(existingProductsNewLevel.getRelationshipLevelSid());
                                    selectedProductContainer.addBean(existingProductsNewLevel);
                                    if (bsrProductforecastLevel != existingProductsNewLevel.getLevelNo()) {
                                        selectedProductContainer.setChildrenAllowed(existingProductsNewLevel, true);
                                    } else {
                                        selectedProductContainer.setChildrenAllowed(existingProductsNewLevel, false);
                                    }
                                }

                                if (!StringUtils.isBlank(existingProductsChildHierarchyNo)) {
                                    for (LevelDTO existingProductsselectedLevel : selectedProductContainer.getItemIds()) {
                                        if (existingProductsChildHierarchyNo.equals(String.valueOf(existingProductsselectedLevel.getHierarchyNo()))) {
                                            childsParent = existingProductsselectedLevel;
                                            break;
                                        }
                                    }
                                }
                                selectedProductContainer.setParent(existingProductsNewLevel, childsParent);
                            }
                        }

                    }
                } else {
                    LevelDTO newProductSelectedParent = DataSelectionUtils.getBeanFromId(item);

                    String newProdutsHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                    if (newProdutsHierarchyNo.length() > 0 && newProdutsHierarchyNo.charAt(newProdutsHierarchyNo.length() - 1) == '.') {
                        newProdutsHierarchyNo = newProdutsHierarchyNo.substring(0, newProdutsHierarchyNo.length() - 1);
                    }
                    String newProdutsCurrentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                    List<LevelDTO> newChildLevels = null;
                    int pos = 0;
                    if (newProdutsHierarchyNo.contains(ARMUtils.DOT)) {
                        while (newProdutsHierarchyNo.contains(ARMUtils.DOT)) {
                            pos = newProdutsHierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                newProdutsHierarchyNo = newProdutsHierarchyNo.substring(0, pos) + ".";
                            }
                        }
                    }
                    if (productBeanList.isEmpty() || !productBeanList.contains(DataSelectionUtils.getBeanFromId(item).getRelationshipLevelSid())) {
                        productBeanList.add(DataSelectionUtils.getBeanFromId(item).getRelationshipLevelSid());
                        selectedProductContainer.addBean(newProductSelectedParent);
                        if (bsrProductforecastLevel != newProductSelectedParent.getLevelNo()) {
                            selectedProductContainer.setChildrenAllowed(newProductSelectedParent, true);
                        } else {
                            selectedProductContainer.setChildrenAllowed(newProductSelectedParent, false);
                        }
                    }
                    newChildLevels = productlogicVal.getChildLevelsWithHierarchyNo(newProdutsCurrentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
                    if ((newChildLevels != null) && !newChildLevels.isEmpty()) {
                        int pos3 = 0;
                        String newProdutsChildHierarchyNo;
                        LevelDTO newSelectedProductchildsParent = null;
                        for (LevelDTO newLevel : newChildLevels) {
                            String newProdutstempHNo = newLevel.getHierarchyNo();
                            if (newProdutstempHNo.length() > 0 && newProdutstempHNo.charAt(newProdutstempHNo.length() - 1) == '.') {
                                newProdutstempHNo = newProdutstempHNo.substring(0, newProdutstempHNo.length() - 1);
                            }
                            pos3 = newProdutstempHNo.lastIndexOf(ARMUtils.DOT);
                            if (pos3 != -1) {
                                newProdutsChildHierarchyNo = newProdutstempHNo.substring(0, pos3) + ".";
                            } else {
                                newProdutsChildHierarchyNo = newProdutstempHNo + ".";
                            }
                            if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                productBeanList.add(newLevel.getRelationshipLevelSid());
                                selectedProductContainer.addBean(newLevel);
                                if (bsrProductforecastLevel != newLevel.getLevelNo()) {
                                    selectedProductContainer.setChildrenAllowed(newLevel, true);
                                } else {
                                    selectedProductContainer.setChildrenAllowed(newLevel, false);
                                }
                            }
                            if (!StringUtils.isBlank(newProdutsChildHierarchyNo)) {
                                for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                    if (newProdutsChildHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                        newSelectedProductchildsParent = selectedLevel;
                                        break;
                                    }
                                }
                            }
                            selectedProductContainer.setParent(newLevel, newSelectedProductchildsParent);
                        }
                    }
                }
                DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
                        "No Level was selected to move. Please try again.");
            }
        } catch (Exception e) {
            LOGGER.error("Error in moveLeftProductsButtonLogic :", e);
        }
    }

    @Override
    public void moveRightProductsButtonLogic() {
        try {
            if (selectedProduct.getValue() != null) {
                List<LevelDTO> rightProductlistValue;
                Object selectedItem = selectedProduct.getValue();
                LevelDTO selectedProductLevel = DataSelectionUtils.getBeanFromId(selectedItem);
                String rightLevel = ARMUtils.ZERO_STRING;
                if (productLevel.getValue() != null && !String.valueOf(productLevel.getValue()).equalsIgnoreCase(String.valueOf(GlobalConstants.getSelectOne()))) {
                    rightLevel = String.valueOf(productLevel.getValue());
                }
                int currentLevel = CommonLogic.parseStringToInteger(rightLevel);
                if ((currentLevel != 0 && selectedProductLevel.getLevelNo() == currentLevel) && ARMUtils.NDC.equalsIgnoreCase(selectedProductLevel.getLevel())) {
                    rightProductlistValue = DataSelectionUtils.getFSValue(selectedProductLevel.getRelationshipLevelValue(), selectedProductLevel.getFieldName());
                    selectedProductLevel.setForm(StringUtils.EMPTY + rightProductlistValue.get(0).getForm());
                    selectedProductLevel.setStrength(StringUtils.EMPTY + rightProductlistValue.get(0).getStrength());
                }

                DataSelectionUtils.removeItemsRecursively(selectedItem, selectedProduct, availableProduct, selectedProductContainer, availableProductContainer, currentLevel);
                selectedProductContainer.removeItem(selectedProductLevel);
                selectedProduct.removeItem(selectedItem);
                productBeanList.clear();
                List<LevelDTO> removedProductListValue = selectedProductContainer.getItemIds();
                for (LevelDTO levels : removedProductListValue) {
                    productBeanList.add(levels.getRelationshipLevelSid());
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
                        "No Level was selected to move. Please try again. ");
            }
        } catch (Exception ex) {
            LOGGER.error("moveRightProduct", ex);
        }

    }

    @Override
    public void moveAllProductsButtonLogic() {
        try {
            DataSelectionLogic logicVal = new DataSelectionLogic();
            int forecastLevelallProd = 0;
            if (productLevel.getValue() != null) {
                forecastLevelallProd = CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
            }

            if (availableProductContainer.size() > 0) {
                List<LevelDTO> iteams = new ArrayList<>(availableProductContainer.getItemIds());
                Object selectedItem = null;
                if (selectedProductContainer.size() > 0) {
                    if (selectedProduct.getValue() != null) {
                        selectedItem = selectedProduct.getValue();
                        for (LevelDTO item : iteams) {
                            if (DataSelectionUtils.getBeanFromId(item).getLevelNo() > DataSelectionUtils.getBeanFromId(selectedItem).getLevelNo()) {

                                String hierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                                if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                    hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                                }
                                String currentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                                List<String> hierarchyNos = new ArrayList<>();
                                List<LevelDTO> newParentLevels = null;
                                List<LevelDTO> newChildLevels = null;
                                hierarchyNos.add(hierarchyNo + ".");
                                int pos = 0;
                                while (hierarchyNo.contains(ARMUtils.DOT)) {
                                    pos = hierarchyNo.lastIndexOf(ARMUtils.DOT);
                                    if (pos != -1) {
                                        hierarchyNo = hierarchyNo.substring(0, pos);
                                    }
                                    hierarchyNos.add(hierarchyNo + ".");
                                }
                                Collections.reverse(hierarchyNos);
                                List<String> selectedHierarchyNos = new ArrayList<>();
                                for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                    if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                        selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                    }
                                }
                                List<String> uncommonValues = DataSelectionUtils.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                                List<String> removeValues = new ArrayList<>();
                                for (String uncommonHierValue : uncommonValues) {
                                    if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                        removeValues.add(uncommonHierValue);
                                    }
                                }
                                if (!removeValues.isEmpty()) {
                                    uncommonValues.removeAll(removeValues);
                                }
                                newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap, productRelation.getValue().toString());
                                newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                                if (newParentLevels != null) {
                                    int pos2 = 0;
                                    String parentHierarchyNo;
                                    LevelDTO parent = null;
                                    for (LevelDTO newLevel : newParentLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos2 = tempHNo.lastIndexOf(ARMUtils.DOT);
                                        if (pos2 != -1) {
                                            parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                        } else {
                                            parentHierarchyNo = tempHNo + ".";
                                        }
                                        if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            productBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedProductContainer.addBean(newLevel);
                                            if (forecastLevelallProd != newLevel.getLevelNo()) {
                                                selectedProductContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedProductContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(parentHierarchyNo)) {
                                            for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
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
                                        LevelDTO childsParent = null;
                                        for (LevelDTO newLevel : newChildLevels) {
                                            String tempHNo = newLevel.getHierarchyNo();
                                            if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                                tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                            }
                                            pos3 = tempHNo.lastIndexOf(ARMUtils.DOT);
                                            if (pos3 != -1) {
                                                childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                            } else {
                                                childHierarchyNo = tempHNo + ".";
                                            }
                                            if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                                productBeanList.add(newLevel.getRelationshipLevelSid());
                                                selectedProductContainer.addBean(newLevel);
                                                if (forecastLevelallProd != newLevel.getLevelNo()) {
                                                    selectedProductContainer.setChildrenAllowed(newLevel, true);
                                                } else {
                                                    selectedProductContainer.setChildrenAllowed(newLevel, false);
                                                }
                                            }
                                            if (!StringUtils.isBlank(childHierarchyNo)) {
                                                for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
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

                        for (LevelDTO item : iteams) {
                            String hierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                            if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                            }
                            String currentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                            List<String> hierarchyNos = new ArrayList<>();
                            List<LevelDTO> newParentLevels = null;
                            List<LevelDTO> newChildLevels = null;
                            hierarchyNos.add(hierarchyNo + ".");
                            int pos = 0;
                            String selectedParentHierarchyNo = StringUtils.EMPTY;
                            LevelDTO selectedParent = null;
                            while (hierarchyNo.contains(ARMUtils.DOT)) {
                                pos = hierarchyNo.lastIndexOf(ARMUtils.DOT);
                                if (pos != -1) {
                                    hierarchyNo = hierarchyNo.substring(0, pos);
                                }
                                hierarchyNos.add(hierarchyNo + ".");
                            }
                            Collections.reverse(hierarchyNos);
                            List<String> selectedHierarchyNos = new ArrayList<>();
                            for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                    selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                }
                            }
                            List<String> uncommonValues = DataSelectionUtils.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                            List<String> removeValues = new ArrayList<>();
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
                                pos2 = tempHNo.lastIndexOf(ARMUtils.DOT);
                                if (pos2 != -1) {
                                    selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                } else {
                                    selectedParentHierarchyNo = tempHNo + ".";
                                }

                            }
                            if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                                for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                    if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                        selectedParent = selectedLevel;
                                        break;
                                    }
                                }
                            }
                            if (!uncommonValues.isEmpty()) {
                                newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap, productRelation.getValue().toString());
                            }
                            newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                            if (newParentLevels != null) {
                                for (LevelDTO newLevel : newParentLevels) {
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (forecastLevelallProd != newLevel.getLevelNo()) {
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
                                    LevelDTO childsParent = null;
                                    for (LevelDTO newLevel : newChildLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos3 = tempHNo.lastIndexOf(ARMUtils.DOT);
                                        if (pos3 != -1) {
                                            childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                        } else {
                                            childHierarchyNo = tempHNo + ".";
                                        }
                                        if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                            productBeanList.add(newLevel.getRelationshipLevelSid());
                                            selectedProductContainer.addBean(newLevel);
                                            if (forecastLevelallProd != newLevel.getLevelNo()) {
                                                selectedProductContainer.setChildrenAllowed(newLevel, true);
                                            } else {
                                                selectedProductContainer.setChildrenAllowed(newLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(childHierarchyNo)) {
                                            for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
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
                    for (LevelDTO item : iteams) {
                        String hierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<>();
                        List<LevelDTO> newParentLevels = null;
                        List<LevelDTO> newChildLevels = null;
                        hierarchyNos.add(hierarchyNo + ".");
                        int pos = 0;
                        while (hierarchyNo.contains(ARMUtils.DOT)) {
                            pos = hierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                hierarchyNo = hierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(hierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        List<String> selectedHierarchyNos = new ArrayList<>();
                        for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> uncommonValues = DataSelectionUtils.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                        List<String> removeValues = new ArrayList<>();
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
                                LOGGER.debug("TempHNo  {}", tempHNo);
                            }

                        }
                        if (!uncommonValues.isEmpty()) {
                            newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap, productRelation.getValue().toString());
                        }
                        newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                        if (newParentLevels != null) {
                            int pos2 = 0;
                            String parentHierarchyNo;
                            LevelDTO parent = null;
                            for (LevelDTO newLevel : newParentLevels) {
                                String tempHNo = newLevel.getHierarchyNo();
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                pos2 = tempHNo.lastIndexOf(ARMUtils.DOT);

                                if (pos2 != -1) {
                                    parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                } else {
                                    parentHierarchyNo = tempHNo + ".";
                                }
                                if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                    productBeanList.add(newLevel.getRelationshipLevelSid());
                                    selectedProductContainer.addBean(newLevel);
                                    if (forecastLevelallProd != newLevel.getLevelNo()) {
                                        selectedProductContainer.setChildrenAllowed(newLevel, true);
                                    } else {
                                        selectedProductContainer.setChildrenAllowed(newLevel, false);
                                    }
                                }
                                if (!StringUtils.isBlank(parentHierarchyNo)) {
                                    for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
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
                                LevelDTO childsParent = null;
                                for (LevelDTO newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(ARMUtils.DOT);
                                    if (pos3 != -1) {
                                        childHierarchyNo = tempHNo.substring(0, pos3) + ".";
                                    } else {
                                        childHierarchyNo = tempHNo + ".";
                                    }
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (forecastLevelallProd != newLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(newLevel, false);
                                        }
                                    }
                                    if (!StringUtils.isBlank(childHierarchyNo)) {
                                        for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
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
            }
        } catch (Exception e) {
            LOGGER.error("Error in movemoveLeftProductsButtonLogic", e);
        }
    }

    @Override
    public void moveAllCustomersButtonLogic() {
        try {
            DataSelectionLogic logicBtn = new DataSelectionLogic();
            int forecastLevel = 0;
            if (customerLevel.getValue() != null) {
                forecastLevel = CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue()));
            }
            if (availableCustomerContainer.size() > 0) {
                List<LevelDTO> iteams = new ArrayList<>(availableCustomerContainer.getItemIds());
                Object selectedItem = null;
                if (selectedCustomerContainer.size() > 0) {
                    if (selectedCustomer.getValue() != null) {
                        selectedItem = selectedCustomer.getValue();
                        for (LevelDTO item : iteams) {
                            if (DataSelectionUtils.getBeanFromId(item).getLevelNo() > DataSelectionUtils.getBeanFromId(selectedItem).getLevelNo()) {

                                String hierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                                if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                    hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                                }
                                String currentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                                List<String> hierarchyNos = new ArrayList<>();
                                List<LevelDTO> newParentLevels = null;
                                List<LevelDTO> newChildLevels = null;
                                hierarchyNos.add(hierarchyNo + ".");
                                int pos = 0;
                                while (hierarchyNo.contains(ARMUtils.DOT)) {
                                    pos = hierarchyNo.lastIndexOf(ARMUtils.DOT);
                                    if (pos != -1) {
                                        hierarchyNo = hierarchyNo.substring(0, pos);
                                    }
                                    hierarchyNos.add(hierarchyNo + ".");
                                }
                                Collections.reverse(hierarchyNos);
                                List<String> selectedHierarchyNos = new ArrayList<>();
                                for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                    if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                        selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                    }
                                }
                                List<String> uncommonValues = DataSelectionUtils.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                                List<String> removeValues = new ArrayList<>();
                                for (String uncommonHierValue : uncommonValues) {
                                    if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                        removeValues.add(uncommonHierValue);
                                    }
                                }
                                if (!removeValues.isEmpty()) {
                                    uncommonValues.removeAll(removeValues);
                                }
                                if (!uncommonValues.isEmpty()) {
                                    newParentLevels = logicBtn.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                                }
                                newChildLevels = logicBtn.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                                if (newParentLevels != null) {
                                    int pos2 = 0;
                                    String parentHierarchyNo;
                                    LevelDTO parent = null;
                                    for (LevelDTO newLevel : newParentLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos2 = tempHNo.lastIndexOf(ARMUtils.DOT);

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
                                            for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
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
                                        LevelDTO childsParent = null;
                                        for (LevelDTO newLevel : newChildLevels) {
                                            String tempHNo = newLevel.getHierarchyNo();
                                            if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                                tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                            }
                                            pos3 = tempHNo.lastIndexOf(ARMUtils.DOT);
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
                                                for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
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

                        for (LevelDTO item : iteams) {
                            String hierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                            if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                                hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                            }
                            String currentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                            List<String> hierarchyNos = new ArrayList<>();
                            List<LevelDTO> newParentLevels = null;
                            List<LevelDTO> newChildLevels = null;
                            hierarchyNos.add(hierarchyNo + ".");
                            int pos = 0;
                            String selectedParentHierarchyNo = StringUtils.EMPTY;
                            LevelDTO selectedParent = null;
                            while (hierarchyNo.contains(ARMUtils.DOT)) {
                                pos = hierarchyNo.lastIndexOf(ARMUtils.DOT);
                                if (pos != -1) {
                                    hierarchyNo = hierarchyNo.substring(0, pos);
                                }
                                hierarchyNos.add(hierarchyNo + ".");
                            }
                            Collections.reverse(hierarchyNos);
                            List<String> selectedHierarchyNos = new ArrayList<>();
                            for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                    selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                }
                            }
                            List<String> uncommonValues = DataSelectionUtils.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                            List<String> removeValues = new ArrayList<>();
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
                                pos2 = tempHNo.lastIndexOf(ARMUtils.DOT);
                                if (pos2 != -1) {
                                    selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                                } else {
                                    selectedParentHierarchyNo = tempHNo + ".";
                                }

                            }
                            if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                                for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                    if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                        selectedParent = selectedLevel;
                                        break;
                                    }
                                }
                            }
                            if (!uncommonValues.isEmpty()) {
                                newParentLevels = logicBtn.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                            }
                            newChildLevels = logicBtn.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                            if (newParentLevels != null) {
                                for (LevelDTO newLevel : newParentLevels) {
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
                                    LevelDTO childsParent = null;
                                    for (LevelDTO newLevel : newChildLevels) {
                                        String tempHNo = newLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos3 = tempHNo.lastIndexOf(ARMUtils.DOT);
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
                                            for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
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
                } else if (customerLevel.getValue() != null && CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())) == 1) {

                    for (LevelDTO item : iteams) {
                        selectedCustomerContainer.removeAllItems();
                        selectedCustomer.removeAllItems();
                        LevelDTO selectedParent = DataSelectionUtils.getBeanFromId(item);

                        String hierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        List<LevelDTO> newChildLevels = null;
                        int pos = 0;
                        if (hierarchyNo.contains(ARMUtils.DOT)) {
                            while (hierarchyNo.contains(ARMUtils.DOT)) {
                                pos = hierarchyNo.lastIndexOf(ARMUtils.DOT);
                                if (pos != -1) {
                                    hierarchyNo = hierarchyNo.substring(0, pos) + ".";
                                }
                            }
                        }
                        if (customerBeanList.isEmpty() || !customerBeanList.contains(DataSelectionUtils.getBeanFromId(item).getRelationshipLevelSid())) {
                            customerBeanList.add(DataSelectionUtils.getBeanFromId(item).getRelationshipLevelSid());
                            selectedCustomerContainer.addBean(selectedParent);
                            if (forecastLevel != selectedParent.getLevelNo()) {
                                selectedCustomerContainer.setChildrenAllowed(selectedParent, true);
                            } else {
                                selectedCustomerContainer.setChildrenAllowed(selectedParent, false);
                            }
                        }
                        newChildLevels = logicBtn.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                        if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
                            int pos3 = 0;
                            String childHierarchyNo;
                            LevelDTO childsParent = null;
                            for (LevelDTO newLevel : newChildLevels) {
                                String tempHNo = newLevel.getHierarchyNo();
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                pos3 = tempHNo.lastIndexOf(ARMUtils.DOT);
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
                                    for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
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

                    for (LevelDTO item : iteams) {
                        String hierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
                            hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        List<String> hierarchyNos = new ArrayList<>();
                        List<LevelDTO> newParentLevels = null;
                        List<LevelDTO> newChildLevels = null;
                        hierarchyNos.add(hierarchyNo + ".");
                        int pos = 0;
                        String selectedParentHierarchyNo = StringUtils.EMPTY;
                        LevelDTO selectedParent = null;
                        while (hierarchyNo.contains(ARMUtils.DOT)) {
                            pos = hierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                hierarchyNo = hierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(hierarchyNo + ".");
                        }
                        Collections.reverse(hierarchyNos);
                        List<String> selectedHierarchyNos = new ArrayList<>();
                        for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }

                        List<String> uncommonValues = DataSelectionUtils.storeUncommonValues(hierarchyNos, selectedHierarchyNos);
                        List<String> removeValues = new ArrayList<>();
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
                            pos2 = tempHNo.lastIndexOf(ARMUtils.DOT);
                            if (pos2 != -1) {
                                selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
                            } else {
                                selectedParentHierarchyNo = tempHNo + ".";
                            }

                        }
                        if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                            for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                    selectedParent = selectedLevel;
                                    break;
                                }
                            }
                        }
                        newParentLevels = logicBtn.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                        newChildLevels = logicBtn.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                        if (newParentLevels != null) {
                            for (LevelDTO newLevel : newParentLevels) {
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
                                LevelDTO childsParent = null;
                                for (LevelDTO newLevel : newChildLevels) {
                                    String tempHNo = newLevel.getHierarchyNo();
                                    if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                        tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                    }
                                    pos3 = tempHNo.lastIndexOf(ARMUtils.DOT);
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
                                        for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
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
        } catch (Exception e) {
            LOGGER.error("Error in moveAllCustomersButtonLogic :", e);
        }
    }

    private boolean bindDataSelectionValues(boolean isSaveView) {
        try {
            String msgHeader;
            String msg;

            if (isSaveView) {
                msgHeader = "No Search Criteria";
                msg = "No data selection criteria were found. Please enter data selection criteria and try again. ";
            } else {
                msgHeader = ARMMessages.getGenerateMessageID002();
                msg = "Not all required fields have been populated. Please try again.";
            }
            boolean value = false;
            if (customerHierarchyLookup == null || productHierarchyLookup == null
                    || customerLevel.getValue() == null || productLevel.getValue() == null || deductionLevel.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(msgHeader, msg);
            } else if ((summaryTypeDdlb.getValue() == null || businessUnit.getValue() == null || company.getValue() == null
                    || CommonLogic.checkInt(customerHierarchyLookup.getHierarchyDto().getHierarchyId())
                    || CommonLogic.checkInt(productHierarchyLookup.getHierarchyDto().getHierarchyId())
                    || CommonLogic.checkInt(Integer.valueOf(company.getValue().toString()))
                    || CommonLogic.checkInt(Integer.valueOf(businessUnit.getValue().toString()))
                    || CommonLogic.checkInt(((HelperDTO) deductionLevel.getValue()).getId())
                    || CommonLogic.checkInt(Integer.valueOf(customerRelation.getValue().toString()))
                    || CommonLogic.checkInt(Integer.valueOf(productRelation.getValue().toString()))
                    || bsrRsContractSids.isEmpty() || selectedCustomerContainer.size() == 0 || selectedProductContainer.size() == 0
                    || selectedDeductionContainer.size() == 0)) {
                AbstractNotificationUtils.getErrorNotification(msgHeader, msg);
            } else {
                boolean mandatory = false;
                if (fromPeriod.getValue() == null || "0".equalsIgnoreCase(String.valueOf(fromPeriod.getValue()))) {
                    mandatory = true;
                }
                if ((toPeriod.getValue() == null || "0".equalsIgnoreCase(String.valueOf(toPeriod.getValue())))) {
                    mandatory = true;
                }
                if (mandatory) {
                    AbstractNotificationUtils.getErrorNotification(msgHeader, msg);
                    return false;
                }

                int customerLevl = 0;
                int productLevelVal = 0;
                String[] levelArrCus = customerLevel.getItemCaption(customerLevel.getValue()).split(
                        ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE);
                String[] levelArrProd = productLevel.getItemCaption(productLevel.getValue()).split(
                        ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE);
                String[] levelNoArrCus = levelArrCus[0].split(ARMUtils.SPACE);
                String[] levelNoArrProd = levelArrProd[0].split(ARMUtils.SPACE);
                customerLevl = Integer.valueOf(levelNoArrCus[1]);
                productLevelVal = Integer.valueOf(levelNoArrProd[1]);
                String userId = (String) VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID);
                bsrDataSelectionDTO.setProjectionName(StringUtils.EMPTY);
                bsrDataSelectionDTO.setProjectionDescription(StringUtils.EMPTY);
                bsrDataSelectionDTO.setCreatedBy(CommonLogic.parseStringToInteger(userId));
                if (bsrDataSelectionDTO.getViewCreatedBy() == 0) {
                    bsrDataSelectionDTO.setViewCreatedBy(bsrDataSelectionDTO.getCreatedBy());
                }
                bsrDataSelectionDTO.setCreatedDate(new Date());
                bsrDataSelectionDTO.setCustomerHierarchySid(customerHierarchyLookup.getHierarchyDto().getHierarchyId());
                bsrDataSelectionDTO.setProductHierarchySid(productHierarchyLookup.getHierarchyDto().getHierarchyId());
                bsrDataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerLevl));
                bsrDataSelectionDTO.setProductHierarchyLevel(String.valueOf(productLevelVal));
                bsrDataSelectionDTO.setCustomerHierarchyVersionNo(customerHierarchyLookup.getHierarchyDto().getVersionNo());
                bsrDataSelectionDTO.setProductHierarchyVersionNo(productHierarchyLookup.getHierarchyDto().getVersionNo());
                bsrDataSelectionDTO.setCompanyMasterSid(Integer.valueOf(company.getValue().toString()));
                bsrDataSelectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));   //Obtain from Admin Console
                bsrDataSelectionDTO.setFromPeriodMonth(fromPeriod.getItemCaption(fromPeriod.getValue()));
                bsrDataSelectionDTO.setFromDate(CommonLogic.parseDate(String.valueOf(fromPeriod.getValue())));
                bsrDataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
                bsrDataSelectionDTO.setToDate(CommonLogic.parseDate(String.valueOf(toPeriod.getValue())));
                bsrDataSelectionDTO.setSaveFlag(false);
                bsrDataSelectionDTO.setCustRelationshipBuilderSid(Integer.valueOf(customerRelation.getValue().toString()));
                bsrDataSelectionDTO.setProdRelationshipBuilderSid(Integer.valueOf(productRelation.getValue().toString()));
                bsrDataSelectionDTO.setCustomerRelationshipVersionNo(bsrCustomerVersionMap.get(Integer.valueOf(customerRelation.getValue().toString())));
                bsrDataSelectionDTO.setProductRelationshipVersionNo(bsrProductVersionMap.get(Integer.valueOf(productRelation.getValue().toString())));
                bsrDataSelectionDTO.setForecastingType("Balance Summary Report");
                bsrDataSelectionDTO.setAdjustmentType(screenName);
                bsrDataSelectionDTO.setAdjustmentCaption(summaryTypeDdlb.getItemCaption(summaryTypeDdlb.getValue()));
                bsrDataSelectionDTO.setRsContractSidList(new ArrayList<>(bsrRsContractSids));
                bsrDataSelectionDTO.setDeductionLevel(((HelperDTO) deductionLevel.getValue()).getId());
                bsrDataSelectionDTO.setSelectedCustomerContainer(selectedCustomerContainer);
                bsrDataSelectionDTO.setSelectedDeductionContainer(selectedDeductionContainer);
                bsrDataSelectionDTO.setSelectedProductContainer(selectedProductContainer);
                bsrDataSelectionDTO.setCustomerHierarchyName(customerHierarchy.getValue());
                bsrDataSelectionDTO.setProductHierarchyName(productHierarchy.getValue());
                bsrDataSelectionDTO.setBucompanyMasterSid((int) businessUnit.getValue());
                bsrDataSelectionDTO.setSummaryType(summaryTypeDdlb.getItemIds());
                value = true;
            }
            return value;
        } catch (Exception ex) {
            LOGGER.error("Error in bindDataSelectionValues :", ex);
            return false;
        }
    }

    private final CustomNotification notifier = new CustomNotification();

    class CustomNotification extends AbstractNotificationUtils {

        private String buttonName;

        public CustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "deleteView":
                        if ((bsrDataSelectionDTO.getProjectionId() != 0) && (logic.deleteViewLogic(bsrDataSelectionDTO.getProjectionId()))) {
                            UI.getCurrent().getNavigator().navigateTo(DataSelectionView.NAME);
                        }
                        break;
                    case "save":
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    @Override
    public void generateButtonLogicForScreens() {
        try {
            LOGGER.debug(" generateButtonLogicForScreens ");
//F
            if (saveDataSelectionValues()) {
                bsrSessionDTO.setWorkFlow(false);
                bsrSelection.setSessionDTO(bsrSessionDTO);
                BalanceSummaryReportWindow form = new BalanceSummaryReportWindow(bsrSelection, bsrDataSelectionDTO, bsrSessionDTO);
                getUI().addWindow(form);

            }
        } catch (IllegalArgumentException | NullPointerException ex) {
            LOGGER.error("Error in generateButtonLogicForScreens :", ex);
        }
    }

    @Override
    public void loadPrivateViewLook() {
        LOGGER.debug("Inside loadPrivateViewLook Method");

    }

    @Override
    public void loadPublicViewLook() {
        LOGGER.debug("Inside loadPublicViewLook Method");
    }

    public void loadViewLookUp() {
        viewLookUp = new ViewSearchLookUp(screenName);
        getUI().addWindow(viewLookUp);
        privateLookupCloseListener();
    }

    @Override
    public void loadAvailableDeductions() {
        availableDeductionContainer.removeAllItems();
        HelperDTO deductionLevelval = (HelperDTO) deductionLevel.getValue();
        if (deductionLevelval != null) {
            List<DeductionLevelDTO> list = logic.loadAvailableDeductions(deductionLevelval);
            availableDeductionContainer.addAll(list);
        }
    }

    @Override
    public void privateLookupCloseListener() {
        viewLookUp.addCloseListener(closeListener);
    }

    @Override
    public void publicLookupCloseListener() {
        LOGGER.debug("inside publicLookupCloseListener");
    }
    private Window.CloseListener closeListener = new Window.CloseListener() {

        @Override
        public void windowClose(Window.CloseEvent e) {
            try {
                ViewSearchLookUp lookUP = (ViewSearchLookUp) e.getWindow();
                if (lookUP.isSelected()) {
                    customerHierarchyLookup = new HierarchyLookup();
                    productHierarchyLookup = new HierarchyLookup();
                    bsrDataSelectionDTO.setViewType(lookUP.getCaption());
                    bsrDataSelectionDTO.setViewFlag(Boolean.TRUE);
                    view.setValue(lookUP.getViewDTO().getViewName());
                    bsrDataSelectionDTO.setViewName(lookUP.getViewDTO().getViewName());
                    HierarchyLookupDTO customerHierarchyDto = new HierarchyLookupDTO();
                    HierarchyLookupDTO productHierarchyDto = new HierarchyLookupDTO();
                    customerHierarchyDto.setHierarchyId(lookUP.getViewDTO().getCustomerHierarchySid().isEmpty() ? 0 : Integer.valueOf(lookUP.getViewDTO().getCustomerHierarchySid()));
                    customerHierarchyDto.setVersionNo(lookUP.getViewDTO().getCustomerHierVers());
                    customerHierarchyDto.setHighestLevel(lookUP.getViewDTO().getCustomerHierHL());
                    customerHierarchyLookup.setHierarchyDto(customerHierarchyDto);
                    productHierarchyDto.setHierarchyId(lookUP.getViewDTO().getProductHierarchySid().isEmpty() ? 0 : (Integer.valueOf(lookUP.getViewDTO().getProductHierarchySid())));
                    productHierarchyDto.setVersionNo(lookUP.getViewDTO().getProductHierVers());
                    productHierarchyDto.setHighestLevel(lookUP.getViewDTO().getProductHierHL());
                    productHierarchyLookup.setHierarchyDto(productHierarchyDto);
                    BeanUtils.copyProperties(bsrDataSelectionDTO, lookUP.getViewDTO());
                    setViewDetails(bsrDataSelectionDTO, lookUP.getViewDTO());
                    deleteViewBtn.setEnabled(true);
                }
            } catch (Exception ex) {
                LOGGER.error("Error in closeListener :", ex);
            }
        }

        private void setViewDetails(DataSelectionDTO dto, ViewDTO viewDTO) {
            try {
                deductionViewFlag = true;
                Collection<?> adjItems = summaryTypeDdlb.getItemIds();
                Object adjItemToAdd = null;
                for (Object adjItem : adjItems) {
                    if (adjItem instanceof HelperDTO) {
                        HelperDTO hlpd = (HelperDTO) adjItem;
                        if (dto.getAdjustmentId() == hlpd.getId()) {
                            adjItemToAdd = adjItem;
                        }
                    }
                }
                CommonLogic.loadCompanyAndBusinessUnit(company, "getCompanyQueryForDS");
                CommonLogic.loadCompanyAndBusinessUnit(businessUnit, "getBusinessQueryForDS");
                loadSummaryType();
                company.select(dto.getCompanyMasterSid() == 0 ? null : dto.getCompanyMasterSid());
                businessUnit.select(viewDTO.getBuCompanyName().isEmpty() ? null : dto.getBucompanyMasterSid());
                summaryTypeDdlb.select(adjItemToAdd);
                Date fromPeriodDate = viewDTO.getFromPeriod();
                fromPeriod.select(fromPeriodDate != null ? getPeriod(fromPeriodDate) : 0);
                Date toPeriodDate = viewDTO.getFromPeriod();
                toPeriod.select(toPeriodDate != null ? getPeriod(toPeriodDate) : 0);
                deductionLevel.select(dto.getDeductionLevel() == 0 ? null : new HelperDTO(dto.getDeductionLevel(), HelperListUtil.getInstance().getIdDescMap().get(dto.getDeductionLevel())));
                bsrRsContractSids.clear();
                bsrRsContractSids.addAll(logic.getRsContractSids(dto.getProjectionId()));
                bsrHierarchyKeys.clear();
                Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, bsrRsContractSids, bsrHierarchyKeys);
                selectedDeductionContainer.removeAllItems();
                setDeductionTree(levelKeys);
                customerHierarchy.setValue(dto.getCustomerHierarchyName());
                bsrCustomerVersionMap = logic.loadCustomerRelation(customerRelation, dto.getCustomerHierarchySid());
                customerRelation.select(dto.getCustRelationshipBuilderSid());
                loadCustomerLevel();
                logic.loadCustoProdLevels(customerLevel, dto.getCustomerHierarchySid());

                customerDescriptionMap = new DataSelectionQueryUtils().loadLevelValuesMap(dto.getCustRelationshipBuilderSid(), bsrCustomerVersionMap.get(dto.getCustRelationshipBuilderSid()), customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getVersionNo());
                customerLevel.select(dto.getCustomerHierarchyLevel());
                initializeCustomerHierarchy(dto.getProjectionId(), dto.getCustomerHierarchyLevel().isEmpty() ? 0 : Integer.valueOf(dto.getCustomerHierarchyLevel()));

                productHierarchy.setValue(dto.getProductHierarchyName());
                bsrProductVersionMap = logic.loadProductRelation(productRelation, dto.getProductHierarchySid(), dto.getCompanyMasterSid());
                productRelation.select(dto.getProdRelationshipBuilderSid());
                loadProductLevel();
                logic.loadCustoProdLevels(productLevel, dto.getProductHierarchySid());
                productLevel.select(dto.getProductHierarchyLevel());
                productDescriptionMap = new DataSelectionQueryUtils().loadLevelValuesMap(dto.getProdRelationshipBuilderSid(),
                        bsrProductVersionMap.get(dto.getProdRelationshipBuilderSid()), productHierarchyLookup.getHierarchyDto().getHierarchyId(),
                        productHierarchyLookup.getHierarchyDto().getVersionNo());
                initializeProductHierarchy(dto.getProjectionId(), dto.getProductHierarchyLevel().isEmpty() ? 0 : Integer.valueOf(dto.getProductHierarchyLevel()));
            } catch (Property.ReadOnlyException | NumberFormatException e) {
                LOGGER.error("Error in setViewDetails :", e);
            }
        }

    };

    @Override
    public void moveLeftToRightDeductions() {
        try {
            if (availableDeduction.getValue() != null) {
                String param = ((HelperDTO) deductionLevel.getValue()).getDescription();
                DeductionLevelDTO dto = (DeductionLevelDTO) availableDeduction.getValue();
                Set<Integer> ids = selectedLevelIds.get(param);
                if (ids == null) {
                    ids = new HashSet<>();
                    selectedLevelIds.put(param, ids);
                }
                ids.add(dto.getLevelSid());
                bsrHierarchyKeys.clear();
                Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, bsrRsContractSids, bsrHierarchyKeys);
                selectedDeductionContainer.removeAllItems();
                setDeductionTree(levelKeys);
                customerLevel.select(null);
                productLevel.select(null);

            } else {
                AbstractNotificationUtils.getErrorNotification("Error",
                        "No Level was selected to move. Please select and try again.");
            }
        } catch (Exception ex) {
            LOGGER.error("Error in moveLeftToRightDeductions :", ex);
        }

    }

    private void setDeductionTree(Map<String, DeductionLevelDTO> levelKeys) {
        List<HierarchyString> strkeys = HierarchyString.getHierarchyStringList(bsrHierarchyKeys, true);
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

    public void setLevels(LevelDTO parentList, List<DeductionLevelDTO> list, int levelNo) {
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

    @Override
    public void moveAllDeductions() {
        selectedDeduction.removeAllItems();
        selectedDeductionContainer.removeAllItems();
        String param = ((HelperDTO) deductionLevel.getValue()).getDescription();
        List<DeductionLevelDTO> listDto = availableDeductionContainer.getItemIds();
        Set<Integer> ids = selectedLevelIds.get(param);
        for (DeductionLevelDTO dto : listDto) {
            if (ids == null) {
                ids = new HashSet<>();
                selectedLevelIds.put(param, ids);
            }
            ids.add(dto.getLevelSid());
        }
        bsrHierarchyKeys.clear();
        Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, bsrRsContractSids, bsrHierarchyKeys);
        selectedDeductionContainer.removeAllItems();
        setDeductionTree(levelKeys);
        selectedLevelIds.clear();
        customerLevel.select(null);
        productLevel.select(null);
    }

    public void configureFields() {
        summaryTypeDdlb.focus();
        adjustmentType.setVisible(false);
        description.setVisible(false);
        bsrAdjustmentTypeLabel.setVisible(false);
        bsrDescriptionLabel.setVisible(false);
        panel1.setCaption("Selection Criteria");
        view.addStyleName("searchText");
        fromPeriod.addValueChangeListener(fromPeriodListener);
        toPeriod.addValueChangeListener(toPeriodListener);
        deleteViewBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                String userID;
                String useridString;
                String tableUserId;
                try {
                    userID = (String) VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID);
                    useridString = DataSelectionLogic.getUserFLName(userID);
                    tableUserId = bsrDataSelectionDTO.getCreatedByString();
                    if (tableUserId.equals(useridString)) {
                        notifier.setButtonName("deleteView");
                        notifier.getConfirmationMessage(ARMMessages.getDeleteConfirmationMessage(), ARMMessages.getDeleteMessage_exclusion());
                    } else {
                        notifier.getErrorNotification(ARMMessages.getDeleteErrorMessage(), ARMMessages.getDeleteErrorMessage_exclusion());
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
        view.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                loadViewLookUp();
            }
        });

        summaryTypeDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                Object val = event.getProperty().getValue();
                if (val != null) {
                    HelperDTO dto = (HelperDTO) val;
                    String screenNameSum = dto.getDescription();
                    bsrDataSelectionDTO.setAdjustmentType(screenNameSum);
                    bsrDataSelectionDTO.setAdjustmentCaption(summaryTypeDdlb.getItemCaption(summaryTypeDdlb.getValue()));
                    bsrDataSelectionDTO.setAdjustmentId(dto.getId());
                    loadFromAndTo(bsrDataSelectionDTO);
                }

            }
        });

    }

    public boolean saveDataSelectionValues() {
        try {
            if (bindDataSelectionValues(false)) {
                int projectionIdValue = 0;
                String sessionId = new SimpleDateFormat("hhmmssms").format(new Date());
                bsrSessionDTO.setSessionId(Integer.valueOf(sessionId));
                projectionIdValue = logic.saveProjection(bsrDataSelectionDTO);
                VaadinSession.getCurrent().setAttribute(ARMUtils.PROJECTION_ID, projectionIdValue);
                bsrDataSelectionDTO.setProjectionId(projectionIdValue);
                List<LevelDTO> customerList = selectedCustomerContainer.getItemIds();
                List<LevelDTO> productList = selectedProductContainer.getItemIds();
                List<String> customerListEndSids = DataSelectionUtils.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
                List<String> productListEndSids = DataSelectionUtils.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
                logic.saveCustomerHierarchyLogic(customerList, customerListEndSids, projectionIdValue, null, ARMUtils.SAVE);
                logic.saveProductHierarchyLogic(productList, productListEndSids, projectionIdValue, null, ARMUtils.SAVE);
                logic.saveDeductionLogic(new HashSet(bsrDataSelectionDTO.getRsContractSidList()), projectionIdValue);

                bsrSessionDTO.setUserId(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID))));
                bsrSessionDTO.setCurrentTableNames(QueryUtils.createTempTables("ARM_CCP_HIERARCHY", bsrSessionDTO.getProjectionId(), bsrSessionDTO.getUserId().toString(), bsrSessionDTO.getSessionId().toString()));
                getCustTopLevelName();

                (new QueryUtils()).ccpHierarchyInsert(bsrSessionDTO.getCurrentTableNames(), bsrDataSelectionDTO, selectedCustomerContainer.getItemIds(), selectedProductContainer.getItemIds(), topLevelName, Boolean.FALSE);
                logic.saveCcp(bsrSessionDTO.getCurrentTableNames().get("ST_CCP_HIERARCHY"), String.valueOf(projectionIdValue));
                logic.saveAdjustmentMaster(bsrDataSelectionDTO);

                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            LOGGER.error("Error in saveDataSelectionValues :", ex);
            return false;
        }
    }

    public List<LevelDTO> getCustomerHierarchyEndLevels(final ExtTreeContainer<LevelDTO> selectedCustomerContainer) {
        List<LevelDTO> customerHierarchyEndLevels = new ArrayList<>();
        for (Object item : selectedCustomerContainer.getItemIds()) {
            if (!selectedCustomerContainer.hasChildren(item)) {
                customerHierarchyEndLevels.add(DataSelectionUtils.getBeanFromId(item));
            }
        }
        return customerHierarchyEndLevels;
    }

    public List<LevelDTO> getProductHierarchyEndLevels(final ExtTreeContainer<LevelDTO> selectedProductContainer) {
        List<LevelDTO> productHierarchyEndLevels = new ArrayList<>();
        for (Object item : selectedProductContainer.getItemIds()) {
            if (!selectedProductContainer.hasChildren(item)) {
                productHierarchyEndLevels.add(DataSelectionUtils.getBeanFromId(item));
            }
        }
        return productHierarchyEndLevels;
    }

    @Override
    public void moveRightToLeftDeductions() {
        if (selectedDeduction.getValue() != null) {
            DeductionLevelDTO selectedLevel = (DeductionLevelDTO) selectedDeduction.getValue();
            Set<String> ids = selectedLevel.getRsContractSids();
            for (String removeSelectedCon_Sid : ids) {
                bsrRsContractSids.remove(Integer.valueOf(removeSelectedCon_Sid));
            }
            if (!bsrRsContractSids.isEmpty()) {
                bsrHierarchyKeys.clear();
                Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, bsrRsContractSids, bsrHierarchyKeys);
                selectedDeductionContainer.removeAllItems();
                setDeductionTree(levelKeys);
            } else {
                selectedDeductionContainer.removeAllItems();
                selectedDeduction.removeAllItems();
            }
            customerLevel.select(null);
            productLevel.select(null);
        } else {
            AbstractNotificationUtils.getErrorNotification("Error",
                    "No Level was selected to move. Please select and try again.");
        }
    }

    @Override
    public void saveViewLogic() {
        try {
            if (bindDataSelectionSaveViewValues()) {
                List<LevelDTO> customerList = selectedCustomerContainer.getItemIds();
                List<LevelDTO> productList = selectedProductContainer.getItemIds();
                List<String> customerListEndSids = DataSelectionUtils.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
                List<String> productListEndSids = DataSelectionUtils.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
                bsrDataSelectionDTO.setCustomerList(customerList);
                bsrDataSelectionDTO.setProductList(productList);
                bsrDataSelectionDTO.setCustomerEndLevelList(customerListEndSids);
                bsrDataSelectionDTO.setProductEndLevelList(productListEndSids);
                bsrDataSelectionDTO.setAddUpdateFlag(StringUtils.isEmpty(view.getValue()));
                bsrDataSelectionDTO.setScreenname("BSR");
                SaveViewLookUp saveViewLookUp = new SaveViewLookUp(bsrDataSelectionDTO);
                getUI().addWindow(saveViewLookUp);
            }
        } catch (Exception ex) {
            LOGGER.error("Error in saveViewLogic :", ex);
        }
    }

    /**
     * Load customer hierarchy.
     *
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void initializeCustomerHierarchy(final int projectionId, int customerLevel) {
        DataSelectionLogic logicCust = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = logicCust.getRelationShipValues(projectionId, "customer", customerLevel, customerDescriptionMap);
        for (LevelDTO dto : reslistOne) {
            if (dto.getLevelNo() == 1) {
                selectedCustomerContainer.removeAllItems();
                selectedCustomerContainer.addItem(dto);
                selectedCustomerContainer.setChildrenAllowed(dto, true);
            } else {
                for (Object tempdto : selectedCustomerContainer.getItemIds()) {
                    if (dto.getParentNode().contains("~")) {
                        String[] parentarr = dto.getParentNode().split("~");
                        String parentName = parentarr[1];
                        if (getBeanFromId(tempdto).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
                            selectedCustomerContainer.addBean(dto);
                            if (customerLevel != dto.getLevelNo()) {
                                selectedCustomerContainer.setChildrenAllowed(dto, true);
                            } else {
                                selectedCustomerContainer.setChildrenAllowed(dto, false);
                            }
                            selectedCustomerContainer.setParent(dto, tempdto);
                            break;
                        }
                    } else {
                        selectedCustomerContainer.addBean(dto);
                        if (customerLevel != dto.getLevelNo()) {
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
        Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE};
        selectedCustomer.setVisibleColumns(obj);
        String[] value = new String[]{"Customer Hierarchy Group Builder"};
        selectedCustomer.setColumnHeaders(value);
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
        DataSelectionLogic logicVal = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = logicVal.getRelationShipValues(projectionId, "product", productLevel, productDescriptionMap);
        for (LevelDTO dto : reslistOne) {
            if (dto.getLevelNo() == 1) {
                selectedProductContainer.removeAllItems();
                selectedProductContainer.addItem(dto);
                selectedProductContainer.setChildrenAllowed(dto, true);
            } else {
                for (Object tempdto : selectedProductContainer.getItemIds()) {
                    if (dto.getParentNode().contains("~")) {
                        String[] parentarr = dto.getParentNode().split("~");
                        String parentName = parentarr[1];
                        if (getBeanFromId(tempdto).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
                            selectedProductContainer.addBean(dto);
                            if (productLevel != dto.getLevelNo()) {
                                selectedProductContainer.setChildrenAllowed(dto, true);
                            } else {
                                selectedProductContainer.setChildrenAllowed(dto, false);
                            }
                            selectedProductContainer.setParent(dto, tempdto);
                            break;
                        }
                    } else {
                        selectedProductContainer.addBean(dto);
                        if (productLevel != dto.getLevelNo()) {
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
        Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE};
        selectedProduct.setVisibleColumns(obj);
        String[] value = new String[]{"Product Hierarchy Group Builder"};
        selectedProduct.setColumnHeaders(value);
        for (LevelDTO ddo : selectedProductContainer.getItemIds()) {
            selectedProduct.setCollapsed(ddo, false);
        }
    }

    /**
     * Gets the bean from id.
     *
     * @param obj the id
     * @return the bean from id
     */
    public static LevelDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof LevelDTO) {
            targetItem = new BeanItem<>((LevelDTO) obj);
        }
        if (targetItem == null) {
            return new LevelDTO();
        } else {
            return (LevelDTO) targetItem.getBean();

        }
    }

    private void loadFromAndTo(DataSelectionDTO selection) {
        if (company.getValue() != null && businessUnit.getValue() != null && summaryTypeDdlb.getValue() != null) {
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
            periodView = DataSelectionLogic.getPeriodViewForAdjustmentType(0, glCompId, bUnitCompSid);
            if (ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView)) {
                toPeriod.removeAllItems();
                configurePeriodDropDown(toPeriod);
                DataSelectionLogic.getDates(val, glCompId, bUnitCompSid, toPeriod);
                toPeriod.select(String.valueOf(fromPeriod.getValue()));
                toPeriod.setEnabled(false);
            } else {
                toPeriod.setEnabled(true);
            }
            bsrDataSelectionDTO.setCheckFlag(false);
        } else {
            fromPeriod.removeAllItems();
            toPeriod.removeAllItems();
        }

    }

    @UiHandler("descriptionText")
    public void descriptionDdlbVlaueChange(Property.ValueChangeEvent event) {
        description.addValidator(new StringLengthValidator(" Description Should be less than 200 characters", 0, 200, true));
    }

    /**
     * Used to check which level is top in selected customer hierarchy either
     * customer or contract It is used for CCP_HIERARCHY_INSERT query formation
     *
     * @param levelName
     */
    private void levelCheck(String levelName) {
        if (StringUtils.isBlank(topLevelName) && ("Customer".equals(levelName) || "Trading Partner".equals(levelName) || "TradingPartner".equals(levelName) || "Contract".equals(levelName))) {
            topLevelName = levelName;
        }
    }

    /**
     * Method to get and identify the level name of customer hierarchy
     */
    private void getCustTopLevelName() {
        for (int i = 1; i <= innerCustLevels.size(); i++) {
            String levelName = innerCustLevels.get(i - 1).getLevel();
            levelCheck(levelName);
        }
    }

    @Override
    public void setAdjustmentOptionsPanelForBalanceSummaryReport() {
        addCompanyBusinessUnit();
        loadSummaryType();
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
    public void resetFields() {
        deductionLevel.select(null);
        availableDeduction.removeAllItems();
        availableDeductionContainer.removeAllItems();
        selectedDeduction.removeAllItems();
        selectedDeductionContainer.removeAllItems();
        customerHierarchy.setValue(StringUtils.EMPTY);
        customerHierarchyLookup = null;
        customerRelation.removeAllItems();
        configureDropDowns(customerRelation);
        customerLevel.removeAllItems();
        configureDropDowns(customerLevel);
        availableCustomer.removeAllItems();
        availableCustomerContainer.removeAllItems();
        selectedCustomerContainer.removeAllItems();
        selectedCustomer.removeAllItems();
        productHierarchy.setValue(StringUtils.EMPTY);
        productHierarchyLookup = null;
        productRelation.removeAllItems();
        configureDropDowns(productRelation);
        productLevel.removeAllItems();
        configureDropDowns(productLevel);
        availableProduct.removeAllItems();
        availableProductContainer.removeAllItems();
        selectedProductContainer.removeAllItems();
        selectedProduct.removeAllItems();
        availableCustomer.resetFilters();
        availableProduct.resetFilters();
        availableDeduction.resetFilters();
        company.select(null);
        businessUnit.select(null);
        configureDropDowns(fromPeriod);
        configureDropDowns(toPeriod);
        fromPeriod.setValue(0);
        toPeriod.setValue(0);
        view.setValue(StringUtils.EMPTY);
        summaryTypeDdlb.select(null);
        deleteViewBtn.setEnabled(false);
    }

    public void securityForAllScreens() {

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(com.stpl.app.utils.ConstantsUtils.USER_ID));
        if (com.stpl.app.utils.ConstantsUtils.BALANCE_SUMMARY_REPORT.equalsIgnoreCase(screenName)) {
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, ARMUtils.BALANCE_SUMMARY_REPORT + ConstantsUtils.COMMA + com.stpl.app.utils.ConstantsUtils.LANDING_SCREEN);
            if (functionCfpHM.get("generateBtn") != null && !(functionCfpHM.get("generateBtn")).isFunctionFlag()) {
                generateBtn.setVisible(false);
            }
            if (functionCfpHM.get("resetBtn") != null && !(functionCfpHM.get("resetBtn")).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (functionCfpHM.get("saveViewBtn") != null && !(functionCfpHM.get("saveViewBtn")).isFunctionFlag()) {
                saveViewBtn.setVisible(false);
            }
            if (functionCfpHM.get("deleteViewBtn") != null && !(functionCfpHM.get("deleteViewBtn")).isFunctionFlag()) {
                deleteViewBtn.setVisible(false);
            }

        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.logic);
        hash = 97 * hash + Objects.hashCode(this.bsrDataSelectionDTO);
        hash = 97 * hash + Objects.hashCode(this.bsrCustomerSidList);
        hash = 97 * hash + Objects.hashCode(this.bsrSelection);
        hash = 97 * hash + Objects.hashCode(this.screenName);
        hash = 97 * hash + Objects.hashCode(this.bsrAdjustmentTypeLabel);
        hash = 97 * hash + Objects.hashCode(this.bsrDescriptionLabel);
        hash = 97 * hash + Objects.hashCode(this.bsrSessionDTO);
        hash = 97 * hash + Objects.hashCode(this.bsrRsContractSids);
        hash = 97 * hash + Objects.hashCode(this.bsrHierarchyKeys);
        hash = 97 * hash + Objects.hashCode(this.topLevelName);
        hash = 97 * hash + Objects.hashCode(this.periodView);
        hash = 97 * hash + Objects.hashCode(this.notifier);
        hash = 97 * hash + Objects.hashCode(this.closeListener);
        hash = 97 * hash + Objects.hashCode(this.fromPeriodListener);
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
        final BalanceSummaryReportDataSelection other = (BalanceSummaryReportDataSelection) obj;
        if (!Objects.equals(this.screenName, other.screenName)) {
            return false;
        }
        if (!Objects.equals(this.topLevelName, other.topLevelName)) {
            return false;
        }
        if (!Objects.equals(this.periodView, other.periodView)) {
            return false;
        }
        if (!Objects.equals(this.logic, other.logic)) {
            return false;
        }
        if (!Objects.equals(this.bsrDataSelectionDTO, other.bsrDataSelectionDTO)) {
            return false;
        }
        return getEqualsCheck(other);
    }

    public boolean getEqualsCheck(final BalanceSummaryReportDataSelection other) {
        if (!Objects.equals(this.bsrCustomerSidList, other.bsrCustomerSidList)) {
            return false;
        }
        if (!Objects.equals(this.bsrSelection, other.bsrSelection)) {
            return false;
        }
        if (!Objects.equals(this.bsrAdjustmentTypeLabel, other.bsrAdjustmentTypeLabel)) {
            return false;
        }
        if (!Objects.equals(this.bsrDescriptionLabel, other.bsrDescriptionLabel)) {
            return false;
        }
        if (!Objects.equals(this.bsrSessionDTO, other.bsrSessionDTO)) {
            return false;
        }
        if (!Objects.equals(this.bsrRsContractSids, other.bsrRsContractSids)) {
            return false;
        }
        if (!Objects.equals(this.notifier, other.notifier)) {
            return false;
        }
        if (!Objects.equals(this.closeListener, other.closeListener)) {
            return false;
        }
        if (!Objects.equals(this.fromPeriodListener, other.fromPeriodListener)) {
            return false;
        }
        return Objects.equals(this.toPeriodListener, other.toPeriodListener);
    }

    private boolean bindDataSelectionSaveViewValues() {
        try {
            String msgHeader = "No Search Criteria";
            String msg = "No data selection criteria were found. Please enter data selection criteria and try again. ";

            boolean value = false;
            if ((StringUtils.isEmpty(company.getValue() == null ? "" : company.getValue().toString()) || CommonLogic.checkInt(Integer.valueOf(company.getValue().toString())))
                    && (customerHierarchyLookup == null || CommonLogic.checkInt(customerHierarchyLookup.getHierarchyDto().getHierarchyId()))
                    && (productHierarchyLookup == null || CommonLogic.checkInt(productHierarchyLookup.getHierarchyDto().getHierarchyId()))
                    && (businessUnit.getValue() == null || CommonLogic.checkInt(Integer.valueOf(businessUnit.getValue().toString())))
                    && (deductionLevel.getValue() == null || CommonLogic.checkInt(((HelperDTO) deductionLevel.getValue()).getId()))
                    && (adjustmentType.getValue() == null || CommonLogic.checkInt(((HelperDTO) adjustmentType.getValue()).getId()))
                    && (summaryTypeDdlb.getValue() == null)) {
                AbstractNotificationUtils.getErrorNotification(msgHeader, msg);
                return false;
            }

            int customerLevl = 0;
            int productLevelVal = 0;
            String[] levelArrCus = customerLevel.getValue() != null ? customerLevel.getItemCaption(customerLevel.getValue()).split(
                    ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE) : new String[0];
            String[] levelArrProd = productLevel.getValue() != null ? productLevel.getItemCaption(productLevel.getValue()).split(
                    ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE) : new String[0];
            String[] levelNoArrCus = levelArrCus.length > 0 ? levelArrCus[0].split(ARMUtils.SPACE) : new String[0];
            String[] levelNoArrProd = levelArrProd.length > 0 ? levelArrProd[0].split(ARMUtils.SPACE) : new String[0];
            customerLevl = levelNoArrCus.length > 0 ? Integer.valueOf(levelNoArrCus[1]) : 0;
            productLevelVal = levelNoArrProd.length > 0 ? Integer.valueOf(levelNoArrProd[1]) : 0;
            String userId = (String) VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID);
            bsrDataSelectionDTO.setProjectionName(StringUtils.EMPTY);
            bsrDataSelectionDTO.setCreatedBy(CommonLogic.parseStringToInteger(userId));
            if (bsrDataSelectionDTO.getViewCreatedBy() == 0) {
                bsrDataSelectionDTO.setViewCreatedBy(bsrDataSelectionDTO.getCreatedBy());
            }
            bsrDataSelectionDTO.setCreatedDate(new Date());
            bsrDataSelectionDTO.setCustomerHierarchySid(customerHierarchyLookup == null ? NumericConstants.ZERO : customerHierarchyLookup.getHierarchyDto().getHierarchyId());
            bsrDataSelectionDTO.setProductHierarchySid(productHierarchyLookup == null ? NumericConstants.ZERO : productHierarchyLookup.getHierarchyDto().getHierarchyId());
            bsrDataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerLevl));
            bsrDataSelectionDTO.setProductHierarchyLevel(String.valueOf(productLevelVal));
            bsrDataSelectionDTO.setCustomerHierarchyVersionNo(customerHierarchyLookup == null ? NumericConstants.ZERO : customerHierarchyLookup.getHierarchyDto().getVersionNo());
            bsrDataSelectionDTO.setProductHierarchyVersionNo(productHierarchyLookup == null ? NumericConstants.ZERO : productHierarchyLookup.getHierarchyDto().getVersionNo());
            bsrDataSelectionDTO.setCompanyMasterSid(company.getValue() != null ? Integer.valueOf(String.valueOf(company.getValue())) : NumericConstants.ZERO);
            bsrDataSelectionDTO.setFromPeriod(fromPeriod.getValue() == null || "0".equals(String.valueOf(fromPeriod.getValue())) ? StringUtils.EMPTY : String.valueOf(fromPeriod.getValue()));   //Obtain from Admin Console
            bsrDataSelectionDTO.setFromPeriodMonth(fromPeriod.getValue() != null || "0".equals(String.valueOf(fromPeriod.getValue())) ? fromPeriod.getItemCaption(fromPeriod.getValue()) : StringUtils.EMPTY);
            bsrDataSelectionDTO.setFromDate("0".equals(String.valueOf(fromPeriod.getValue())) ? null : CommonLogic.parseDate(String.valueOf(fromPeriod.getValue())));
            bsrDataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
            bsrDataSelectionDTO.setToDate("0".equals(String.valueOf(toPeriod.getValue())) ? null : CommonLogic.parseDate(String.valueOf(toPeriod.getValue())));
            bsrDataSelectionDTO.setSaveFlag(false);
            bsrDataSelectionDTO.setCustRelationshipBuilderSid(customerRelation.getValue() != null ? Integer.valueOf(String.valueOf(customerRelation.getValue())) : NumericConstants.ZERO);
            bsrDataSelectionDTO.setProdRelationshipBuilderSid(productRelation.getValue() != null ? Integer.valueOf(String.valueOf(productRelation.getValue())) : NumericConstants.ZERO);
            bsrDataSelectionDTO.setForecastingType(VariableConstants.ARM);
            bsrDataSelectionDTO.setForecastingType("Balance Summary Report");
            bsrDataSelectionDTO.setAdjustmentType(screenName);
            bsrDataSelectionDTO.setRsContractSidList(new ArrayList<>(bsrRsContractSids));
            bsrDataSelectionDTO.setDeductionLevel(deductionLevel.getValue() != null ? ((HelperDTO) deductionLevel.getValue()).getId() : NumericConstants.ZERO);
            bsrDataSelectionDTO.setSelectedCustomerContainer(selectedCustomerContainer);
            bsrDataSelectionDTO.setSelectedDeductionContainer(selectedDeductionContainer);
            bsrDataSelectionDTO.setSelectedProductContainer(selectedProductContainer);
            bsrDataSelectionDTO.setCustomerHierarchyName(customerHierarchy.getValue() != null ? customerHierarchy.getValue() : StringUtils.EMPTY);
            bsrDataSelectionDTO.setProductHierarchyName(productHierarchy.getValue() != null ? productHierarchy.getValue() : StringUtils.EMPTY);
            bsrDataSelectionDTO.setBucompanyMasterSid(businessUnit.getValue() != null ? (int) businessUnit.getValue() : NumericConstants.ZERO);
            bsrDataSelectionDTO.setDefaultCompanyMasterSid(logic.getCompanyId());
            bsrDataSelectionDTO.setAdjustmentCaption(summaryTypeDdlb.getItemCaption(summaryTypeDdlb.getValue()));
            bsrDataSelectionDTO.setSummaryType(summaryTypeDdlb.getItemIds());
            value = true;
            return value;
        } catch (NumberFormatException | ParseException ex) {
            LOGGER.error("Error in bindDataSelectionValues :", ex);
            return false;
        }
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getPeriod(Date date) {
        SimpleDateFormat getMonth = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return getMonth.format(calendar.getTime());

    }

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
                    loadAvailableProducts();
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
    private Property.ValueChangeListener toPeriodListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (bsrDataSelectionDTO != null) {
                if (bsrDataSelectionDTO.isViewFlag()) {
                    bsrDataSelectionDTO.setCheckFlag(false);
                } else {
                    if (((toPeriod.getValue() != null) && toPeriod.isEnabled()) && fromPeriod.getValue() != null) {
                        bsrDataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
                    }
                }

            }
        }
    };

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
