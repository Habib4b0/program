/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.form;

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
    private Object[] ndcVisibleColumns = new Object[]{CommonConstant.DISPLAY_VALUE, "form", "strength"};
    private String[] ndcHeaders = new String[]{ARMUtils.NDC, "Form", "Strength"};

    public BalanceSummaryReportDataSelection(String screenName, SessionDTO sessionDTO) {
        super();
        this.bsrSessionDTO = sessionDTO;
        this.screenName = screenName;
        configureReportFields();
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
                    int relSid = relationshipSid.isEmpty() || "null".equals(relationshipSid) ? 0 : Integer.parseInt(relationshipSid);
                    DataSelectionLogic logicVal = new DataSelectionLogic();
                    String[] val = selectedLevel.split(ARMUtils.SPACE.toString());
                    forecastLevel = Integer.parseInt(val[1]);
                    LevelDTO tempDto = innerCustLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        bsrLevelName = tempDto.getLevel();
                        tempDto.getLevelNo();
                    }
                    List<LevelDTO> customerHierarchyLevelDefinitionList = logicVal.getHierarchyLevelDefinition(customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getVersionNo());
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
                    Object[] obj = ndcVisibleColumns;
                    availableProduct.setVisibleColumns(obj);
                    String[] value = ndcHeaders;
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
                        hierarchyNos.add(bsrHierarchyNo + ARMUtils.DOT);
                        int pos = 0;
                        while (bsrHierarchyNo.contains(ARMUtils.DOT)) {
                            pos = bsrHierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                bsrHierarchyNo = bsrHierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(bsrHierarchyNo + ARMUtils.DOT);
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
                                    parentHierarchyNo = bsrTempHNo.substring(0, pos2) + ARMUtils.DOT;
                                } else {
                                    parentHierarchyNo = bsrTempHNo + ARMUtils.DOT;
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
                                        childHierarchyNo = bsrtempHierNo.substring(0, pos3) + ARMUtils.DOT;
                                    } else {
                                        childHierarchyNo = bsrtempHierNo + ARMUtils.DOT;
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
                        hierarchyNos.add(bsrSelhierarchyNo + ARMUtils.DOT);
                        int pos = 0;
                        String selectedParentHierarchyNoBsr = StringUtils.EMPTY;
                        LevelDTO selectedParent = null;
                        while (bsrSelhierarchyNo.contains(ARMUtils.DOT)) {
                            pos = bsrSelhierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                bsrSelhierarchyNo = bsrSelhierarchyNo.substring(0, pos);
                            }
                            hierarchyNos.add(bsrSelhierarchyNo + ARMUtils.DOT);
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
                                selectedParentHierarchyNoBsr = tempHNo.substring(0, pos2) + ARMUtils.DOT;
                            } else {
                                selectedParentHierarchyNoBsr = tempHNo + ARMUtils.DOT;
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
                                        childHierarchyNoBsr = tempHNoBsr.substring(0, pos3) + ARMUtils.DOT;
                                    } else {
                                        childHierarchyNoBsr = tempHNoBsr + ARMUtils.DOT;
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
                    hierarchyNosListBsr.add(hierarchyBsrNo + ARMUtils.DOT);
                    int pos = 0;
                    String selectedParentBsrHierarchyNo = StringUtils.EMPTY;
                    LevelDTO selectedParent2 = null;
                    while (hierarchyBsrNo.contains(ARMUtils.DOT)) {
                        pos = hierarchyBsrNo.lastIndexOf(ARMUtils.DOT);
                        if (pos != -1) {
                            hierarchyBsrNo = hierarchyBsrNo.substring(0, pos);
                        }
                        hierarchyNosListBsr.add(hierarchyBsrNo + ARMUtils.DOT);
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
                                    childHierarchyNoBsr = tempHNo.substring(0, pos3) + ARMUtils.DOT;
                                } else {
                                    childHierarchyNoBsr = tempHNo + ARMUtils.DOT;
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
                                hierarchyNoBSr = hierarchyNoBSr.substring(0, pos) + ARMUtils.DOT;
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
                                childHierarchyNoBSr = tempHNo.substring(0, pos3) + ARMUtils.DOT;
                            } else {
                                childHierarchyNoBSr = tempHNo + ARMUtils.DOT;
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
                        bsrProductHierarchyNos.add(bsrProductsHierarchyNo + ARMUtils.DOT);
                        int pos = 0;
                        while (bsrProductsHierarchyNo.contains(ARMUtils.DOT)) {
                            pos = bsrProductsHierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                bsrProductsHierarchyNo = bsrProductsHierarchyNo.substring(0, pos);
                            }
                            bsrProductHierarchyNos.add(bsrProductsHierarchyNo + ARMUtils.DOT);
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
                                    parentHierarchyNo = bsrProducttempHNo.substring(0, pos2) + ARMUtils.DOT;
                                } else {
                                    parentHierarchyNo = bsrProducttempHNo + ARMUtils.DOT;
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
                                        bsrchildHierarchyNoProduct = bsrProducttempHNo2.substring(0, pos3) + ARMUtils.DOT;
                                    } else {
                                        bsrchildHierarchyNoProduct = bsrProducttempHNo2 + ARMUtils.DOT;
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
                        childLevelshierarchyNos.add(newChildLevelsHierarchyNo + ARMUtils.DOT);
                        int pos = 0;
                        String selectedParentHierarchyNo = StringUtils.EMPTY;
                        LevelDTO selectedParent = null;
                        while (newChildLevelsHierarchyNo.contains(ARMUtils.DOT)) {
                            pos = newChildLevelsHierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                newChildLevelsHierarchyNo = newChildLevelsHierarchyNo.substring(0, pos);
                            }
                            childLevelshierarchyNos.add(newChildLevelsHierarchyNo + ARMUtils.DOT);
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
                                selectedParentHierarchyNo = childProducttempHNo.substring(0, pos2) + ARMUtils.DOT;
                            } else {
                                selectedParentHierarchyNo = childProducttempHNo + ARMUtils.DOT;
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
                                        newChildLevalsChildHierarchyNo = newHierlevelstempHNo.substring(0, pos3) + ARMUtils.DOT;
                                    } else {
                                        newChildLevalsChildHierarchyNo = newHierlevelstempHNo + ARMUtils.DOT;
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
                    existingProductHierarchyNos.add(existingProducthierarchyNo + ARMUtils.DOT);
                    int pos = 0;
                    String selectedParentHierarchyNo = StringUtils.EMPTY;
                    LevelDTO selectedParent2 = null;
                    while (existingProducthierarchyNo.contains(ARMUtils.DOT)) {
                        pos = existingProducthierarchyNo.lastIndexOf(ARMUtils.DOT);
                        if (pos != -1) {
                            existingProducthierarchyNo = existingProducthierarchyNo.substring(0, pos);
                        }
                        existingProductHierarchyNos.add(existingProducthierarchyNo + ARMUtils.DOT);
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
                                    existingProductsChildHierarchyNo = existingProductstempHNo.substring(0, pos3) + ARMUtils.DOT;
                                } else {
                                    existingProductsChildHierarchyNo = existingProductstempHNo + ARMUtils.DOT;
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
                                newProdutsHierarchyNo = newProdutsHierarchyNo.substring(0, pos) + ARMUtils.DOT;
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
                                newProdutsChildHierarchyNo = newProdutstempHNo.substring(0, pos3) + ARMUtils.DOT;
                            } else {
                                newProdutsChildHierarchyNo = newProdutstempHNo + ARMUtils.DOT;
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
            int bsrForecastLevelallProd = 0;
            if (productLevel.getValue() != null) {
                bsrForecastLevelallProd = CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
            }

            if (availableProductContainer.size() > 0) {
                List<LevelDTO> items = new ArrayList<>(availableProductContainer.getItemIds());
                Object existingProductsSelectedItem = null;
                if (selectedProductContainer.size() > 0) {
                    if (selectedProduct.getValue() != null) {
                        existingProductsSelectedItem = selectedProduct.getValue();
                        for (LevelDTO item : items) {
                            if (DataSelectionUtils.getBeanFromId(item).getLevelNo() > DataSelectionUtils.getBeanFromId(existingProductsSelectedItem).getLevelNo()) {

                                String existingHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                                if (existingHierarchyNo.length() > 0 && existingHierarchyNo.charAt(existingHierarchyNo.length() - 1) == '.') {
                                    existingHierarchyNo = existingHierarchyNo.substring(0, existingHierarchyNo.length() - 1);
                                }
                                String existingCurrentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                                List<String> existinghierarchyNos = new ArrayList<>();
                                List<LevelDTO> newParentLevels = null;
                                List<LevelDTO> newProductChildLevelsforAllProducts = null;
                                existinghierarchyNos.add(existingHierarchyNo + ARMUtils.DOT);
                                int pos = 0;
                                while (existingHierarchyNo.contains(ARMUtils.DOT)) {
                                    pos = existingHierarchyNo.lastIndexOf(ARMUtils.DOT);
                                    if (pos != -1) {
                                        existingHierarchyNo = existingHierarchyNo.substring(0, pos);
                                    }
                                    existinghierarchyNos.add(existingHierarchyNo + ARMUtils.DOT);
                                }
                                Collections.reverse(existinghierarchyNos);
                                List<String> existingProductselectedHierarchyNos = new ArrayList<>();
                                for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                    if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                        existingProductselectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                    }
                                }
                                List<String> existingAllProductuncommonValues = DataSelectionUtils.storeUncommonValues(existinghierarchyNos, existingProductselectedHierarchyNos);
                                List<String> removeValues = new ArrayList<>();
                                for (String uncommonHierValue : existingAllProductuncommonValues) {
                                    if (existingProductselectedHierarchyNos.contains(uncommonHierValue)) {
                                        removeValues.add(uncommonHierValue);
                                    }
                                }
                                if (!removeValues.isEmpty()) {
                                    existingAllProductuncommonValues.removeAll(removeValues);
                                }
                                newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(existingAllProductuncommonValues), productDescriptionMap, productRelation.getValue().toString());
                                newProductChildLevelsforAllProducts = logicVal.getChildLevelsWithHierarchyNo(existingCurrentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                                if (newParentLevels != null) {
                                    int pos2 = 0;
                                    String parentHierarchyNoallProd;
                                    LevelDTO parentallProd = null;
                                    for (LevelDTO allProdNewLevel : newParentLevels) {
                                        String tempHNo = allProdNewLevel.getHierarchyNo();
                                        if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                            tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                        }
                                        pos2 = tempHNo.lastIndexOf(ARMUtils.DOT);
                                        if (pos2 != -1) {
                                            parentHierarchyNoallProd = tempHNo.substring(0, pos2) + ARMUtils.DOT;
                                        } else {
                                            parentHierarchyNoallProd = tempHNo + ARMUtils.DOT;
                                        }
                                        if (productBeanList.isEmpty() || !productBeanList.contains(allProdNewLevel.getRelationshipLevelSid())) {
                                            productBeanList.add(allProdNewLevel.getRelationshipLevelSid());
                                            selectedProductContainer.addBean(allProdNewLevel);
                                            if (bsrForecastLevelallProd != allProdNewLevel.getLevelNo()) {
                                                selectedProductContainer.setChildrenAllowed(allProdNewLevel, true);
                                            } else {
                                                selectedProductContainer.setChildrenAllowed(allProdNewLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(parentHierarchyNoallProd)) {
                                            for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                                if (parentHierarchyNoallProd.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    parentallProd = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedProductContainer.setParent(allProdNewLevel, parentallProd);
                                        parentallProd = allProdNewLevel;
                                    }
                                    if (!newProductChildLevelsforAllProducts.isEmpty()) {
                                        int pos3 = 0;
                                        String newProductChildHierarchyNoBsr;
                                        LevelDTO childsParent = null;
                                        for (LevelDTO newLevelAllProduct : newProductChildLevelsforAllProducts) {
                                            String newProductTempHNoAllProduct = newLevelAllProduct.getHierarchyNo();
                                            if (newProductTempHNoAllProduct.length() > 0 && newProductTempHNoAllProduct.charAt(newProductTempHNoAllProduct.length() - 1) == '.') {
                                                newProductTempHNoAllProduct = newProductTempHNoAllProduct.substring(0, newProductTempHNoAllProduct.length() - 1);
                                            }
                                            pos3 = newProductTempHNoAllProduct.lastIndexOf(ARMUtils.DOT);
                                            if (pos3 != -1) {
                                                newProductChildHierarchyNoBsr = newProductTempHNoAllProduct.substring(0, pos3) + ARMUtils.DOT;
                                            } else {
                                                newProductChildHierarchyNoBsr = newProductTempHNoAllProduct + ARMUtils.DOT;
                                            }
                                            if (productBeanList.isEmpty() || !productBeanList.contains(newLevelAllProduct.getRelationshipLevelSid())) {
                                                productBeanList.add(newLevelAllProduct.getRelationshipLevelSid());
                                                selectedProductContainer.addBean(newLevelAllProduct);
                                                if (bsrForecastLevelallProd != newLevelAllProduct.getLevelNo()) {
                                                    selectedProductContainer.setChildrenAllowed(newLevelAllProduct, true);
                                                } else {
                                                    selectedProductContainer.setChildrenAllowed(newLevelAllProduct, false);
                                                }
                                            }
                                            if (!StringUtils.isBlank(newProductChildHierarchyNoBsr)) {
                                                for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                                    if (newProductChildHierarchyNoBsr.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                        childsParent = selectedLevel;
                                                        break;
                                                    }
                                                }
                                            }
                                            selectedProductContainer.setParent(newLevelAllProduct, childsParent);
                                        }
                                    }

                                }
                            }
                        }
                    } else {

                        for (LevelDTO item : items) {
                            String bsrNewItemHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                            if (bsrNewItemHierarchyNo.length() > 0 && bsrNewItemHierarchyNo.charAt(bsrNewItemHierarchyNo.length() - 1) == '.') {
                                bsrNewItemHierarchyNo = bsrNewItemHierarchyNo.substring(0, bsrNewItemHierarchyNo.length() - 1);
                            }
                            String currentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                            List<String> hierarchyNos = new ArrayList<>();
                            List<LevelDTO> bsrDSnewParentLevels = null;
                            List<LevelDTO> newChildLevelsforAllProductBsr = null;
                            hierarchyNos.add(bsrNewItemHierarchyNo + ARMUtils.DOT);
                            int pos = 0;
                            String selectedParentHierarchyNoforAllProdBsr = StringUtils.EMPTY;
                            LevelDTO selectedParent = null;
                            while (bsrNewItemHierarchyNo.contains(ARMUtils.DOT)) {
                                pos = bsrNewItemHierarchyNo.lastIndexOf(ARMUtils.DOT);
                                if (pos != -1) {
                                    bsrNewItemHierarchyNo = bsrNewItemHierarchyNo.substring(0, pos);
                                }
                                hierarchyNos.add(bsrNewItemHierarchyNo + ARMUtils.DOT);
                            }
                            Collections.reverse(hierarchyNos);
                            List<String> bsrNewItemsSelectedHierarchyNos = new ArrayList<>();
                            for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                    bsrNewItemsSelectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                }
                            }
                            List<String> existingProductUncommonValues = DataSelectionUtils.storeUncommonValues(hierarchyNos, bsrNewItemsSelectedHierarchyNos);
                            List<String> removedValues = new ArrayList<>();
                            for (String uncommonHierValue : existingProductUncommonValues) {
                                if (bsrNewItemsSelectedHierarchyNos.contains(uncommonHierValue)) {
                                    removedValues.add(uncommonHierValue);
                                }
                            }
                            if (!removedValues.isEmpty()) {
                                existingProductUncommonValues.removeAll(removedValues);
                            }
                            if (!existingProductUncommonValues.isEmpty()) {
                                String existingProducttempHNoAllProd = existingProductUncommonValues.get(0);
                                if (existingProducttempHNoAllProd.length() > 0 && existingProducttempHNoAllProd.charAt(existingProducttempHNoAllProd.length() - 1) == '.') {
                                    existingProducttempHNoAllProd = existingProducttempHNoAllProd.substring(0, existingProducttempHNoAllProd.length() - 1);
                                }
                                int pos2 = 0;
                                pos2 = existingProducttempHNoAllProd.lastIndexOf(ARMUtils.DOT);
                                if (pos2 != -1) {
                                    selectedParentHierarchyNoforAllProdBsr = existingProducttempHNoAllProd.substring(0, pos2) + ARMUtils.DOT;
                                } else {
                                    selectedParentHierarchyNoforAllProdBsr = existingProducttempHNoAllProd + ARMUtils.DOT;
                                }

                            }
                            if (!StringUtils.isBlank(selectedParentHierarchyNoforAllProdBsr)) {
                                for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                    if (selectedParentHierarchyNoforAllProdBsr.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                        selectedParent = selectedLevel;
                                        break;
                                    }
                                }
                            }
                            if (!existingProductUncommonValues.isEmpty()) {
                                bsrDSnewParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(existingProductUncommonValues), productDescriptionMap, productRelation.getValue().toString());
                            }
                            newChildLevelsforAllProductBsr = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                            if (bsrDSnewParentLevels != null) {
                                for (LevelDTO newLevel : bsrDSnewParentLevels) {
                                    if (productBeanList.isEmpty() || !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(newLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(newLevel);
                                        if (bsrForecastLevelallProd != newLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(newLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(newLevel, false);
                                        }
                                        selectedProductContainer.setParent(newLevel, selectedParent);
                                        selectedParent = newLevel;
                                    }
                                }
                                if (!newChildLevelsforAllProductBsr.isEmpty()) {
                                    int pos3 = 0;
                                    String childHierarchyNo;
                                    LevelDTO childsParent = null;
                                    for (LevelDTO bsrDsProductnewLevel : newChildLevelsforAllProductBsr) {
                                        String bsrDsAllProductstempHNo = bsrDsProductnewLevel.getHierarchyNo();
                                        if (bsrDsAllProductstempHNo.length() > 0 && bsrDsAllProductstempHNo.charAt(bsrDsAllProductstempHNo.length() - 1) == '.') {
                                            bsrDsAllProductstempHNo = bsrDsAllProductstempHNo.substring(0, bsrDsAllProductstempHNo.length() - 1);
                                        }
                                        pos3 = bsrDsAllProductstempHNo.lastIndexOf(ARMUtils.DOT);
                                        if (pos3 != -1) {
                                            childHierarchyNo = bsrDsAllProductstempHNo.substring(0, pos3) + ARMUtils.DOT;
                                        } else {
                                            childHierarchyNo = bsrDsAllProductstempHNo + ARMUtils.DOT;
                                        }
                                        if (productBeanList.isEmpty() || !productBeanList.contains(bsrDsProductnewLevel.getRelationshipLevelSid())) {
                                            productBeanList.add(bsrDsProductnewLevel.getRelationshipLevelSid());
                                            selectedProductContainer.addBean(bsrDsProductnewLevel);
                                            if (bsrForecastLevelallProd != bsrDsProductnewLevel.getLevelNo()) {
                                                selectedProductContainer.setChildrenAllowed(bsrDsProductnewLevel, true);
                                            } else {
                                                selectedProductContainer.setChildrenAllowed(bsrDsProductnewLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(childHierarchyNo)) {
                                            for (LevelDTO bsrDsAllProcudtsSelectedLevel : selectedProductContainer.getItemIds()) {
                                                if (childHierarchyNo.equals(String.valueOf(bsrDsAllProcudtsSelectedLevel.getHierarchyNo()))) {
                                                    childsParent = bsrDsAllProcudtsSelectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedProductContainer.setParent(bsrDsProductnewLevel, childsParent);
                                    }
                                }

                            }

                        }
                    }
                } else {
                    for (LevelDTO item : items) {
                        String bsrDsNewProductshierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        if (bsrDsNewProductshierarchyNo.length() > 0 && bsrDsNewProductshierarchyNo.charAt(bsrDsNewProductshierarchyNo.length() - 1) == '.') {
                            bsrDsNewProductshierarchyNo = bsrDsNewProductshierarchyNo.substring(0, bsrDsNewProductshierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        List<String> bsrAllNewProducthierarchyNos = new ArrayList<>();
                        List<LevelDTO> newParentLevels = null;
                        List<LevelDTO> newChildLevels = null;
                        bsrAllNewProducthierarchyNos.add(bsrDsNewProductshierarchyNo + ARMUtils.DOT);
                        int pos = 0;
                        while (bsrDsNewProductshierarchyNo.contains(ARMUtils.DOT)) {
                            pos = bsrDsNewProductshierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                bsrDsNewProductshierarchyNo = bsrDsNewProductshierarchyNo.substring(0, pos);
                            }
                            bsrAllNewProducthierarchyNos.add(bsrDsNewProductshierarchyNo + ARMUtils.DOT);
                        }
                        Collections.reverse(bsrAllNewProducthierarchyNos);
                        List<String> selectedHierarchyNos = new ArrayList<>();
                        for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }
                        List<String> bsrAllProductNewuncommonValues = DataSelectionUtils.storeUncommonValues(bsrAllNewProducthierarchyNos, selectedHierarchyNos);
                        List<String> bsrAllProductsNewremoveValues = new ArrayList<>();
                        for (String uncommonHierValue : bsrAllProductNewuncommonValues) {
                            if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                bsrAllProductsNewremoveValues.add(uncommonHierValue);
                            }
                        }
                        if (!bsrAllProductsNewremoveValues.isEmpty()) {
                            bsrAllProductNewuncommonValues.removeAll(bsrAllProductsNewremoveValues);
                        }
                        if (!bsrAllProductNewuncommonValues.isEmpty()) {
                            String moveAllProductstempHNo = bsrAllProductNewuncommonValues.get(0);
                            if (moveAllProductstempHNo.length() > 0 && moveAllProductstempHNo.charAt(moveAllProductstempHNo.length() - 1) == '.') {
                                moveAllProductstempHNo = moveAllProductstempHNo.substring(0, moveAllProductstempHNo.length() - 1);
                                LOGGER.debug("TempHNo  {}", moveAllProductstempHNo);
                            }

                        }
                        if (!bsrAllProductNewuncommonValues.isEmpty()) {
                            newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(bsrAllProductNewuncommonValues), productDescriptionMap, productRelation.getValue().toString());
                        }
                        newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                        if (newParentLevels != null) {
                            int pos2 = 0;
                            String parentHierarchyNo;
                            LevelDTO parent = null;
                            for (LevelDTO moveNewAllProductsnewLevel : newParentLevels) {
                                String tempHNo = moveNewAllProductsnewLevel.getHierarchyNo();
                                if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
                                    tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
                                }
                                pos2 = tempHNo.lastIndexOf(ARMUtils.DOT);

                                if (pos2 != -1) {
                                    parentHierarchyNo = tempHNo.substring(0, pos2) + ARMUtils.DOT;
                                } else {
                                    parentHierarchyNo = tempHNo + ARMUtils.DOT;
                                }
                                if (productBeanList.isEmpty() || !productBeanList.contains(moveNewAllProductsnewLevel.getRelationshipLevelSid())) {
                                    productBeanList.add(moveNewAllProductsnewLevel.getRelationshipLevelSid());
                                    selectedProductContainer.addBean(moveNewAllProductsnewLevel);
                                    if (bsrForecastLevelallProd != moveNewAllProductsnewLevel.getLevelNo()) {
                                        selectedProductContainer.setChildrenAllowed(moveNewAllProductsnewLevel, true);
                                    } else {
                                        selectedProductContainer.setChildrenAllowed(moveNewAllProductsnewLevel, false);
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
                                selectedProductContainer.setParent(moveNewAllProductsnewLevel, parent);
                                parent = moveNewAllProductsnewLevel;
                            }

                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String getChildHierarchyNo;
                                LevelDTO getChildsParent = null;
                                for (LevelDTO getnewLevel : newChildLevels) {
                                    String gettempHNo = getnewLevel.getHierarchyNo();
                                    if (gettempHNo.length() > 0 && gettempHNo.charAt(gettempHNo.length() - 1) == '.') {
                                        gettempHNo = gettempHNo.substring(0, gettempHNo.length() - 1);
                                    }
                                    pos3 = gettempHNo.lastIndexOf(ARMUtils.DOT);
                                    if (pos3 != -1) {
                                        getChildHierarchyNo = gettempHNo.substring(0, pos3) + ARMUtils.DOT;
                                    } else {
                                        getChildHierarchyNo = gettempHNo + ARMUtils.DOT;
                                    }
                                    if (productBeanList.isEmpty() || !productBeanList.contains(getnewLevel.getRelationshipLevelSid())) {
                                        productBeanList.add(getnewLevel.getRelationshipLevelSid());
                                        selectedProductContainer.addBean(getnewLevel);
                                        if (bsrForecastLevelallProd != getnewLevel.getLevelNo()) {
                                            selectedProductContainer.setChildrenAllowed(getnewLevel, true);
                                        } else {
                                            selectedProductContainer.setChildrenAllowed(getnewLevel, false);
                                        }
                                    }
                                    if (!StringUtils.isBlank(getChildHierarchyNo)) {
                                        for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                                            if (getChildHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                getChildsParent = selectedLevel;
                                                break;
                                            }
                                        }
                                    }
                                    selectedProductContainer.setParent(getnewLevel, getChildsParent);
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
            int moveAllCustomersForecastLevel = 0;
            if (customerLevel.getValue() != null) {
                moveAllCustomersForecastLevel = CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue()));
            }
            if (availableCustomerContainer.size() > 0) {
                List<LevelDTO> customers = new ArrayList<>(availableCustomerContainer.getItemIds());
                Object selectedItem = null;
                if (selectedCustomerContainer.size() > 0) {
                    if (selectedCustomer.getValue() != null) {
                        selectedItem = selectedCustomer.getValue();
                        for (LevelDTO allCustomers : customers) {
                            if (DataSelectionUtils.getBeanFromId(allCustomers).getLevelNo() > DataSelectionUtils.getBeanFromId(selectedItem).getLevelNo()) {

                                String allCustomershierarchyNo = DataSelectionUtils.getBeanFromId(allCustomers).getHierarchyNo();
                                if (allCustomershierarchyNo.length() > 0 && allCustomershierarchyNo.charAt(allCustomershierarchyNo.length() - 1) == '.') {
                                    allCustomershierarchyNo = allCustomershierarchyNo.substring(0, allCustomershierarchyNo.length() - 1);
                                }
                                String currentHierarchyNo = DataSelectionUtils.getBeanFromId(allCustomers).getHierarchyNo();
                                List<String> hierarchyNos = new ArrayList<>();
                                List<LevelDTO> newParentLevels = null;
                                List<LevelDTO> newChildLevels = null;
                                hierarchyNos.add(allCustomershierarchyNo + ARMUtils.DOT);
                                int pos = 0;
                                while (allCustomershierarchyNo.contains(ARMUtils.DOT)) {
                                    pos = allCustomershierarchyNo.lastIndexOf(ARMUtils.DOT);
                                    if (pos != -1) {
                                        allCustomershierarchyNo = allCustomershierarchyNo.substring(0, pos);
                                    }
                                    hierarchyNos.add(allCustomershierarchyNo + ARMUtils.DOT);
                                }
                                Collections.reverse(hierarchyNos);
                                List<String> allCustomersselectedHierarchyNos = new ArrayList<>();
                                for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                    if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                        allCustomersselectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                    }
                                }
                                List<String> allCustomersuncommonValues = DataSelectionUtils.storeUncommonValues(hierarchyNos, allCustomersselectedHierarchyNos);
                                List<String> removeValues = new ArrayList<>();
                                for (String uncommonHierValue : allCustomersuncommonValues) {
                                    if (allCustomersselectedHierarchyNos.contains(uncommonHierValue)) {
                                        removeValues.add(uncommonHierValue);
                                    }
                                }
                                if (!removeValues.isEmpty()) {
                                    allCustomersuncommonValues.removeAll(removeValues);
                                }
                                if (!allCustomersuncommonValues.isEmpty()) {
                                    newParentLevels = logicBtn.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(allCustomersuncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                                }
                                newChildLevels = logicBtn.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                                if (newParentLevels != null) {
                                    int pos2 = 0;
                                    String allCustomersparentHierarchyNo;
                                    LevelDTO parent = null;
                                    for (LevelDTO allCustomersnewLevel : newParentLevels) {
                                        String allCustomerstempHNo = allCustomersnewLevel.getHierarchyNo();
                                        if (allCustomerstempHNo.length() > 0 && allCustomerstempHNo.charAt(allCustomerstempHNo.length() - 1) == '.') {
                                            allCustomerstempHNo = allCustomerstempHNo.substring(0, allCustomerstempHNo.length() - 1);
                                        }
                                        pos2 = allCustomerstempHNo.lastIndexOf(ARMUtils.DOT);

                                        if (pos2 != -1) {
                                            allCustomersparentHierarchyNo = allCustomerstempHNo.substring(0, pos2) + ARMUtils.DOT;
                                        } else {
                                            allCustomersparentHierarchyNo = allCustomerstempHNo + ARMUtils.DOT;
                                        }
                                        if (customerBeanList.isEmpty() || !customerBeanList.contains(allCustomersnewLevel.getRelationshipLevelSid())) {
                                            customerBeanList.add(allCustomersnewLevel.getRelationshipLevelSid());
                                            selectedCustomerContainer.addBean(allCustomersnewLevel);
                                            if (moveAllCustomersForecastLevel != allCustomersnewLevel.getLevelNo()) {
                                                selectedCustomerContainer.setChildrenAllowed(allCustomersnewLevel, true);
                                            } else {
                                                selectedCustomerContainer.setChildrenAllowed(allCustomersnewLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(allCustomersparentHierarchyNo)) {
                                            for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                                if (allCustomersparentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    parent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedCustomerContainer.setParent(allCustomersnewLevel, parent);
                                        parent = allCustomersnewLevel;
                                    }
                                    if (!newChildLevels.isEmpty()) {
                                        int pos3 = 0;
                                        String childHierarchyNo;
                                        LevelDTO childsParent = null;
                                        for (LevelDTO allCustomersnewLevel : newChildLevels) {
                                            String allCustomerstempHNo = allCustomersnewLevel.getHierarchyNo();
                                            if (allCustomerstempHNo.length() > 0 && allCustomerstempHNo.charAt(allCustomerstempHNo.length() - 1) == '.') {
                                                allCustomerstempHNo = allCustomerstempHNo.substring(0, allCustomerstempHNo.length() - 1);
                                            }
                                            pos3 = allCustomerstempHNo.lastIndexOf(ARMUtils.DOT);
                                            if (pos3 != -1) {
                                                childHierarchyNo = allCustomerstempHNo.substring(0, pos3) + ARMUtils.DOT;
                                            } else {
                                                childHierarchyNo = allCustomerstempHNo + ARMUtils.DOT;
                                            }
                                            if (customerBeanList.isEmpty() || !customerBeanList.contains(allCustomersnewLevel.getRelationshipLevelSid())) {
                                                customerBeanList.add(allCustomersnewLevel.getRelationshipLevelSid());
                                                selectedCustomerContainer.addBean(allCustomersnewLevel);
                                                if (moveAllCustomersForecastLevel != allCustomersnewLevel.getLevelNo()) {
                                                    selectedCustomerContainer.setChildrenAllowed(allCustomersnewLevel, true);
                                                } else {
                                                    selectedCustomerContainer.setChildrenAllowed(allCustomersnewLevel, false);
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
                                            selectedCustomerContainer.setParent(allCustomersnewLevel, childsParent);
                                        }
                                    }

                                }
                            }
                        }
                    } else {

                        for (LevelDTO emptyCustomersitem : customers) {
                            String initHierarchyNo = DataSelectionUtils.getBeanFromId(emptyCustomersitem).getHierarchyNo();
                            if (initHierarchyNo.length() > 0 && initHierarchyNo.charAt(initHierarchyNo.length() - 1) == '.') {
                                initHierarchyNo = initHierarchyNo.substring(0, initHierarchyNo.length() - 1);
                            }
                            String startCurrentHierarchyNo = DataSelectionUtils.getBeanFromId(emptyCustomersitem).getHierarchyNo();
                            List<String> initHierarchyNos = new ArrayList<>();
                            List<LevelDTO> initNewParentLevels = null;
                            List<LevelDTO> newChildLevels = null;
                            initHierarchyNos.add(initHierarchyNo + ARMUtils.DOT);
                            int pos = 0;
                            String initSelectedParentHierarchyNo = StringUtils.EMPTY;
                            LevelDTO selectedParent = null;
                            while (initHierarchyNo.contains(ARMUtils.DOT)) {
                                pos = initHierarchyNo.lastIndexOf(ARMUtils.DOT);
                                if (pos != -1) {
                                    initHierarchyNo = initHierarchyNo.substring(0, pos);
                                }
                                initHierarchyNos.add(initHierarchyNo + ARMUtils.DOT);
                            }
                            Collections.reverse(initHierarchyNos);
                            List<String> selectedHierarchyNos = new ArrayList<>();
                            for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                    selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                                }
                            }
                            List<String> initUncommonValues = DataSelectionUtils.storeUncommonValues(initHierarchyNos, selectedHierarchyNos);
                            List<String> removeValues = new ArrayList<>();
                            for (String uncommonHierValue : initUncommonValues) {
                                if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                    removeValues.add(uncommonHierValue);
                                }
                            }
                            if (!removeValues.isEmpty()) {
                                initUncommonValues.removeAll(removeValues);
                            }
                            if (!initUncommonValues.isEmpty()) {
                                String initTtempHNo = initUncommonValues.get(0);
                                if (initTtempHNo.length() > 0 && initTtempHNo.charAt(initTtempHNo.length() - 1) == '.') {
                                    initTtempHNo = initTtempHNo.substring(0, initTtempHNo.length() - 1);
                                }
                                int pos2 = 0;
                                pos2 = initTtempHNo.lastIndexOf(ARMUtils.DOT);
                                if (pos2 != -1) {
                                    initSelectedParentHierarchyNo = initTtempHNo.substring(0, pos2) + ARMUtils.DOT;
                                } else {
                                    initSelectedParentHierarchyNo = initTtempHNo + ARMUtils.DOT;
                                }

                            }
                            if (!StringUtils.isBlank(initSelectedParentHierarchyNo)) {
                                for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                    if (initSelectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                        selectedParent = selectedLevel;
                                        break;
                                    }
                                }
                            }
                            if (!initUncommonValues.isEmpty()) {
                                initNewParentLevels = logicBtn.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(initUncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                            }
                            newChildLevels = logicBtn.getChildLevelsWithHierarchyNo(startCurrentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                            if (initNewParentLevels != null) {
                                for (LevelDTO initCustomersnewLevel : initNewParentLevels) {
                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(initCustomersnewLevel.getRelationshipLevelSid())) {
                                        customerBeanList.add(initCustomersnewLevel.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(initCustomersnewLevel);
                                        if (moveAllCustomersForecastLevel != initCustomersnewLevel.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(initCustomersnewLevel, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(initCustomersnewLevel, false);
                                        }
                                        selectedCustomerContainer.setParent(initCustomersnewLevel, selectedParent);
                                        selectedParent = initCustomersnewLevel;
                                    }
                                }
                                if (!newChildLevels.isEmpty()) {
                                    int pos3 = 0;
                                    String initCustomerschildHierarchyNo;
                                    LevelDTO childsParent = null;
                                    for (LevelDTO innitCustomersnewLevel : newChildLevels) {
                                        String innitCustomerstempHNo = innitCustomersnewLevel.getHierarchyNo();
                                        if (innitCustomerstempHNo.length() > 0 && innitCustomerstempHNo.charAt(innitCustomerstempHNo.length() - 1) == '.') {
                                            innitCustomerstempHNo = innitCustomerstempHNo.substring(0, innitCustomerstempHNo.length() - 1);
                                        }
                                        pos3 = innitCustomerstempHNo.lastIndexOf(ARMUtils.DOT);
                                        if (pos3 != -1) {
                                            initCustomerschildHierarchyNo = innitCustomerstempHNo.substring(0, pos3) + ARMUtils.DOT;
                                        } else {
                                            initCustomerschildHierarchyNo = innitCustomerstempHNo + ARMUtils.DOT;
                                        }
                                        if (customerBeanList.isEmpty() || !customerBeanList.contains(innitCustomersnewLevel.getRelationshipLevelSid())) {
                                            customerBeanList.add(innitCustomersnewLevel.getRelationshipLevelSid());
                                            selectedCustomerContainer.addBean(innitCustomersnewLevel);
                                            if (moveAllCustomersForecastLevel != innitCustomersnewLevel.getLevelNo()) {
                                                selectedCustomerContainer.setChildrenAllowed(innitCustomersnewLevel, true);
                                            } else {
                                                selectedCustomerContainer.setChildrenAllowed(innitCustomersnewLevel, false);
                                            }
                                        }

                                        if (!StringUtils.isBlank(initCustomerschildHierarchyNo)) {
                                            for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                                if (initCustomerschildHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                                    childsParent = selectedLevel;
                                                    break;
                                                }
                                            }
                                        }
                                        selectedCustomerContainer.setParent(innitCustomersnewLevel, childsParent);
                                    }
                                }

                            }

                        }
                    }
                } else if (customerLevel.getValue() != null && CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())) == 1) {

                    for (LevelDTO availableItems : customers) {
                        selectedCustomerContainer.removeAllItems();
                        selectedCustomer.removeAllItems();
                        LevelDTO getselectedParent = DataSelectionUtils.getBeanFromId(availableItems);

                        String customerLevelHierarchyNo = DataSelectionUtils.getBeanFromId(availableItems).getHierarchyNo();
                        if (customerLevelHierarchyNo.length() > 0 && customerLevelHierarchyNo.charAt(customerLevelHierarchyNo.length() - 1) == '.') {
                            customerLevelHierarchyNo = customerLevelHierarchyNo.substring(0, customerLevelHierarchyNo.length() - 1);
                        }
                        String currentHierarchyNo = DataSelectionUtils.getBeanFromId(availableItems).getHierarchyNo();
                        List<LevelDTO> newChildLevels = null;
                        int pos = 0;
                        if (customerLevelHierarchyNo.contains(ARMUtils.DOT)) {
                            while (customerLevelHierarchyNo.contains(ARMUtils.DOT)) {
                                pos = customerLevelHierarchyNo.lastIndexOf(ARMUtils.DOT);
                                if (pos != -1) {
                                    customerLevelHierarchyNo = customerLevelHierarchyNo.substring(0, pos) + ARMUtils.DOT;
                                }
                            }
                        }
                        if (customerBeanList.isEmpty() || !customerBeanList.contains(DataSelectionUtils.getBeanFromId(availableItems).getRelationshipLevelSid())) {
                            customerBeanList.add(DataSelectionUtils.getBeanFromId(availableItems).getRelationshipLevelSid());
                            selectedCustomerContainer.addBean(getselectedParent);
                            if (moveAllCustomersForecastLevel != getselectedParent.getLevelNo()) {
                                selectedCustomerContainer.setChildrenAllowed(getselectedParent, true);
                            } else {
                                selectedCustomerContainer.setChildrenAllowed(getselectedParent, false);
                            }
                        }
                        newChildLevels = logicBtn.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                        if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
                            int pos3 = 0;
                            String childHierarchyNo;
                            LevelDTO existingCustomerschildsParent = null;
                            for (LevelDTO existingCustomersnewLevel : newChildLevels) {
                                String existingCustomerstempHNo = existingCustomersnewLevel.getHierarchyNo();
                                if (existingCustomerstempHNo.length() > 0 && existingCustomerstempHNo.charAt(existingCustomerstempHNo.length() - 1) == '.') {
                                    existingCustomerstempHNo = existingCustomerstempHNo.substring(0, existingCustomerstempHNo.length() - 1);
                                }
                                pos3 = existingCustomerstempHNo.lastIndexOf(ARMUtils.DOT);
                                if (pos3 != -1) {
                                    childHierarchyNo = existingCustomerstempHNo.substring(0, pos3) + ARMUtils.DOT;
                                } else {
                                    childHierarchyNo = existingCustomerstempHNo + ARMUtils.DOT;
                                }
                                if (customerBeanList.isEmpty() || !customerBeanList.contains(existingCustomersnewLevel.getRelationshipLevelSid())) {
                                    customerBeanList.add(existingCustomersnewLevel.getRelationshipLevelSid());
                                    selectedCustomerContainer.addBean(existingCustomersnewLevel);
                                    if (moveAllCustomersForecastLevel != existingCustomersnewLevel.getLevelNo()) {
                                        selectedCustomerContainer.setChildrenAllowed(existingCustomersnewLevel, true);
                                    } else {
                                        selectedCustomerContainer.setChildrenAllowed(existingCustomersnewLevel, false);
                                    }
                                }
                                if (!StringUtils.isBlank(childHierarchyNo)) {
                                    for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                                        if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                            existingCustomerschildsParent = selectedLevel;
                                            break;
                                        }
                                    }
                                }
                                selectedCustomerContainer.setParent(existingCustomersnewLevel, existingCustomerschildsParent);
                            }
                        }
                    }
                } else {

                    for (LevelDTO item : customers) {
                        String customerhierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        if (customerhierarchyNo.length() > 0 && customerhierarchyNo.charAt(customerhierarchyNo.length() - 1) == '.') {
                            customerhierarchyNo = customerhierarchyNo.substring(0, customerhierarchyNo.length() - 1);
                        }
                        String currentCustomerHierarchyNo = DataSelectionUtils.getBeanFromId(item).getHierarchyNo();
                        List<String> customerHierarchyNos = new ArrayList<>();
                        List<LevelDTO> newParentLevels = null;
                        List<LevelDTO> newChildLevels = null;
                        customerHierarchyNos.add(customerhierarchyNo + ARMUtils.DOT);
                        int pos = 0;
                        String selectedParentHierarchyNo = StringUtils.EMPTY;
                        LevelDTO selectedParent = null;
                        while (customerhierarchyNo.contains(ARMUtils.DOT)) {
                            pos = customerhierarchyNo.lastIndexOf(ARMUtils.DOT);
                            if (pos != -1) {
                                customerhierarchyNo = customerhierarchyNo.substring(0, pos);
                            }
                            customerHierarchyNos.add(customerhierarchyNo + ARMUtils.DOT);
                        }
                        Collections.reverse(customerHierarchyNos);
                        List<String> selectedHierarchyNos = new ArrayList<>();
                        for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                            if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
                                selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
                            }
                        }

                        List<String> customerUncommonValues = DataSelectionUtils.storeUncommonValues(customerHierarchyNos, selectedHierarchyNos);
                        List<String> customerremoveValues = new ArrayList<>();
                        for (String uncommonHierValue : customerUncommonValues) {
                            if (selectedHierarchyNos.contains(uncommonHierValue)) {
                                customerremoveValues.add(uncommonHierValue);
                            }
                        }
                        if (!customerremoveValues.isEmpty()) {
                            customerUncommonValues.removeAll(customerremoveValues);
                        }
                        if (!customerUncommonValues.isEmpty()) {
                            String customersstempHNo = customerUncommonValues.get(0);
                            if (customersstempHNo.length() > 0 && customersstempHNo.charAt(customersstempHNo.length() - 1) == '.') {
                                customersstempHNo = customersstempHNo.substring(0, customersstempHNo.length() - 1);
                            }
                            int pos2 = 0;
                            pos2 = customersstempHNo.lastIndexOf(ARMUtils.DOT);
                            if (pos2 != -1) {
                                selectedParentHierarchyNo = customersstempHNo.substring(0, pos2) + ARMUtils.DOT;
                            } else {
                                selectedParentHierarchyNo = customersstempHNo + ARMUtils.DOT;
                            }

                        }
                        if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                            for (LevelDTO getselectedLevel : selectedCustomerContainer.getItemIds()) {
                                if (selectedParentHierarchyNo.equals(String.valueOf(getselectedLevel.getHierarchyNo()))) {
                                    selectedParent = getselectedLevel;
                                    break;
                                }
                            }
                        }
                        newParentLevels = logicBtn.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(customerUncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                        newChildLevels = logicBtn.getChildLevelsWithHierarchyNo(currentCustomerHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
                        if (newParentLevels != null) {
                            for (LevelDTO getnewLevel : newParentLevels) {
                                if (customerBeanList.isEmpty() || !customerBeanList.contains(getnewLevel.getRelationshipLevelSid())) {
                                    customerBeanList.add(getnewLevel.getRelationshipLevelSid());
                                    selectedCustomerContainer.addBean(getnewLevel);
                                    if (moveAllCustomersForecastLevel != getnewLevel.getLevelNo()) {
                                        selectedCustomerContainer.setChildrenAllowed(getnewLevel, true);
                                    } else {
                                        selectedCustomerContainer.setChildrenAllowed(getnewLevel, false);
                                    }
                                    selectedCustomerContainer.setParent(getnewLevel, selectedParent);
                                    selectedParent = getnewLevel;
                                }
                            }
                            if (!newChildLevels.isEmpty()) {
                                int pos3 = 0;
                                String childHierarchyNo;
                                LevelDTO childsParent = null;
                                for (LevelDTO getnewLevel : newChildLevels) {
                                    String gettempHNoCustomers = getnewLevel.getHierarchyNo();
                                    if (gettempHNoCustomers.length() > 0 && gettempHNoCustomers.charAt(gettempHNoCustomers.length() - 1) == '.') {
                                        gettempHNoCustomers = gettempHNoCustomers.substring(0, gettempHNoCustomers.length() - 1);
                                    }
                                    pos3 = gettempHNoCustomers.lastIndexOf(ARMUtils.DOT);
                                    if (pos3 != -1) {
                                        childHierarchyNo = gettempHNoCustomers.substring(0, pos3) + ARMUtils.DOT;
                                    } else {
                                        childHierarchyNo = gettempHNoCustomers + ARMUtils.DOT;
                                    }
                                    if (customerBeanList.isEmpty() || !customerBeanList.contains(getnewLevel.getRelationshipLevelSid())) {
                                        customerBeanList.add(getnewLevel.getRelationshipLevelSid());
                                        selectedCustomerContainer.addBean(getnewLevel);
                                        if (moveAllCustomersForecastLevel != getnewLevel.getLevelNo()) {
                                            selectedCustomerContainer.setChildrenAllowed(getnewLevel, true);
                                        } else {
                                            selectedCustomerContainer.setChildrenAllowed(getnewLevel, false);
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
                                    selectedCustomerContainer.setParent(getnewLevel, childsParent);
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
            String bsrGenerateMsgHeader;
            String bsrGenerateMsg;

            if (isSaveView) {
                bsrGenerateMsgHeader = "No Search Criteria";
                bsrGenerateMsg = "No data selection criteria were found. Please enter data selection criteria and try again. ";
            } else {
                bsrGenerateMsgHeader = ARMMessages.getGenerateMessageID002();
                bsrGenerateMsg = "Not all required fields have been populated. Please try again.";
            }
            boolean value = false;
            if (customerHierarchyLookup == null || productHierarchyLookup == null
                    || customerLevel.getValue() == null || productLevel.getValue() == null || deductionLevel.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(bsrGenerateMsgHeader, bsrGenerateMsg);
            } else if ((summaryTypeDdlb.getValue() == null || businessUnit.getValue() == null || company.getValue() == null
                    || CommonLogic.checkInt(customerHierarchyLookup.getHierarchyDto().getHierarchyId())
                    || CommonLogic.checkInt(productHierarchyLookup.getHierarchyDto().getHierarchyId())
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(company.getValue().toString()))
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(businessUnit.getValue().toString()))
                    || CommonLogic.checkInt(((HelperDTO) deductionLevel.getValue()).getId())
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(customerRelation.getValue().toString()))
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(productRelation.getValue().toString()))
                    || bsrRsContractSids.isEmpty() || selectedCustomerContainer.size() == 0 || selectedProductContainer.size() == 0
                    || selectedDeductionContainer.size() == 0)) {
                AbstractNotificationUtils.getErrorNotification(bsrGenerateMsgHeader, bsrGenerateMsg);
            } else {
                boolean mandatoryCheck = false;
                if (fromPeriod.getValue() == null || "0".equalsIgnoreCase(String.valueOf(fromPeriod.getValue()))) {
                    mandatoryCheck = true;
                }
                if ((toPeriod.getValue() == null || "0".equalsIgnoreCase(String.valueOf(toPeriod.getValue())))) {
                    mandatoryCheck = true;
                }
                if (mandatoryCheck) {
                    AbstractNotificationUtils.getErrorNotification(bsrGenerateMsgHeader, bsrGenerateMsg);
                    return false;
                }

                int customerLevl = 0;
                int productLevelVal = 0;
                String[] bsrLevelArrCus = customerLevel.getItemCaption(customerLevel.getValue()).split(
                        ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE);
                String[] bsrLevelArrProd = productLevel.getItemCaption(productLevel.getValue()).split(
                        ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE);
                String[] levelNoArrCus = bsrLevelArrCus[0].split(ARMUtils.SPACE.toString());
                String[] levelNoArrProd = bsrLevelArrProd[0].split(ARMUtils.SPACE.toString());
                customerLevl = Integer.parseInt(levelNoArrCus[1]);
                productLevelVal = Integer.parseInt(levelNoArrProd[1]);
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
                bsrDataSelectionDTO.setCompanyMasterSid(ARMUtils.getIntegerValue(company.getValue().toString()));
                bsrDataSelectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));   //Obtain from Admin Console
                bsrDataSelectionDTO.setFromPeriodMonth(fromPeriod.getItemCaption(fromPeriod.getValue()));
                bsrDataSelectionDTO.setFromDate(CommonLogic.parseDate(String.valueOf(fromPeriod.getValue())));
                bsrDataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
                bsrDataSelectionDTO.setToDate(CommonLogic.parseDate(String.valueOf(toPeriod.getValue())));
                bsrDataSelectionDTO.setSaveFlag(false);
                bsrDataSelectionDTO.setCustRelationshipBuilderSid(ARMUtils.getIntegerValue(customerRelation.getValue().toString()));
                bsrDataSelectionDTO.setProdRelationshipBuilderSid(ARMUtils.getIntegerValue(productRelation.getValue().toString()));
                bsrDataSelectionDTO.setCustomerRelationshipVersionNo(bsrCustomerVersionMap.get(ARMUtils.getIntegerValue(customerRelation.getValue().toString())));
                bsrDataSelectionDTO.setProductRelationshipVersionNo(bsrProductVersionMap.get(ARMUtils.getIntegerValue(productRelation.getValue().toString())));
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

    private final BSRDataSelectionCustomNotification notifier = new BSRDataSelectionCustomNotification();

    class BSRDataSelectionCustomNotification extends AbstractNotificationUtils {

        private String bsrButtonName;

        public BSRDataSelectionCustomNotification() {
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
            LOGGER.debug("buttonName :{}", bsrButtonName);
            if (null != bsrButtonName) {
                switch (bsrButtonName) {
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
            this.bsrButtonName = buttonName;
        }

    }

    @Override
    public void generateButtonLogicForScreens() {
        try {
            LOGGER.debug(" generateButtonLogicForScreens ");

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
                ViewSearchLookUp searchLookUp = (ViewSearchLookUp) e.getWindow();
                if (searchLookUp.isSelected()) {
                    customerHierarchyLookup = new HierarchyLookup();
                    productHierarchyLookup = new HierarchyLookup();
                    bsrDataSelectionDTO.setViewType(searchLookUp.getCaption());
                    bsrDataSelectionDTO.setViewFlag(true);
                    view.setValue(searchLookUp.getViewDTO().getViewName());
                    bsrDataSelectionDTO.setViewName(searchLookUp.getViewDTO().getViewName());
                    HierarchyLookupDTO customerHierarchyDto = new HierarchyLookupDTO();
                    HierarchyLookupDTO productHierarchyDto = new HierarchyLookupDTO();
                    customerHierarchyDto.setHierarchyId(searchLookUp.getViewDTO().getCustomerHierarchySid().isEmpty() ? 0 : Integer.parseInt(searchLookUp.getViewDTO().getCustomerHierarchySid()));
                    customerHierarchyDto.setVersionNo(searchLookUp.getViewDTO().getCustomerHierVers());
                    customerHierarchyDto.setHighestLevel(searchLookUp.getViewDTO().getCustomerHierHL());
                    customerHierarchyLookup.setHierarchyDto(customerHierarchyDto);
                    productHierarchyDto.setHierarchyId(searchLookUp.getViewDTO().getProductHierarchySid().isEmpty() ? 0 : (Integer.parseInt(searchLookUp.getViewDTO().getProductHierarchySid())));
                    productHierarchyDto.setVersionNo(searchLookUp.getViewDTO().getProductHierVers());
                    productHierarchyDto.setHighestLevel(searchLookUp.getViewDTO().getProductHierHL());
                    productHierarchyLookup.setHierarchyDto(productHierarchyDto);
                    BeanUtils.copyProperties(bsrDataSelectionDTO, searchLookUp.getViewDTO());
                    setViewDetails(bsrDataSelectionDTO, searchLookUp.getViewDTO());
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
                            break;
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
                bsrCustomerHierarchyLevelDefnList = logic.loadCustoProdLevels(customerLevel, dto.getCustomerHierarchySid());

                customerDescriptionMap = new DataSelectionQueryUtils().loadLevelValuesMap(dto.getCustRelationshipBuilderSid(), bsrCustomerVersionMap.get(dto.getCustRelationshipBuilderSid()), customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getVersionNo());
                customerLevel.select(dto.getCustomerHierarchyLevel());
                initializeCustomerHierarchy(dto.getProjectionId(), dto.getCustomerHierarchyLevel().isEmpty() ? 0 : Integer.parseInt(dto.getCustomerHierarchyLevel()));
                availableCustomerContainer.removeAllItems();
                availableCustomer.removeAllItems();

                productHierarchy.setValue(dto.getProductHierarchyName());
                bsrProductVersionMap = logic.loadProductRelation(productRelation, dto.getProductHierarchySid(), dto.getCompanyMasterSid());
                productRelation.select(dto.getProdRelationshipBuilderSid());
                loadProductLevel();
                bsrProductHierarchyLevelDefnList = logic.loadCustoProdLevels(productLevel, dto.getProductHierarchySid());
                productLevel.select(dto.getProductHierarchyLevel());
                productDescriptionMap = new DataSelectionQueryUtils().loadLevelValuesMap(dto.getProdRelationshipBuilderSid(),
                        bsrProductVersionMap.get(dto.getProdRelationshipBuilderSid()), productHierarchyLookup.getHierarchyDto().getHierarchyId(),
                        productHierarchyLookup.getHierarchyDto().getVersionNo());
                initializeProductHierarchy(dto.getProjectionId(), dto.getProductHierarchyLevel().isEmpty() ? 0 : Integer.parseInt(dto.getProductHierarchyLevel()));
                availableProductContainer.removeAllItems();
                availableProduct.removeAllItems();
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
            String bsrHierkey = hKey.getString();
            DeductionLevelDTO value = levelKeys.get(bsrHierkey);
            String bsrParentKey = bsrHierkey.substring(0, bsrHierkey.lastIndexOf('.'));
            if (bsrParentKey.lastIndexOf('.') >= 0) {
                bsrParentKey = bsrParentKey.substring(0, bsrParentKey.lastIndexOf('.') + 1);
            }
            selectedDeductionContainer.addItem(value);
            DeductionLevelDTO parent = levelKeys.get(bsrParentKey);

            if (parent != null) {
                selectedDeductionContainer.setParent(value, parent);
            }
            if (StringUtils.countMatches(bsrHierkey, ARMUtils.DOT) == NumericConstants.NINE) {
                selectedDeductionContainer.setChildrenAllowed(value, false);
            }
        }
    }

    public void setLevels(LevelDTO parentList, List<DeductionLevelDTO> list, int levelNo) {
        for (DeductionLevelDTO levelDTO : list) {
            if (levelNo != 8) {
                selectedDeductionContainer.addBean(levelDTO);
                selectedDeductionContainer.setParent(levelDTO, parentList);
                selectedDeductionContainer.setChildrenAllowed(levelDTO, true);
            } else {
                selectedDeductionContainer.addBean(levelDTO);
                selectedDeductionContainer.setParent(levelDTO, parentList);
                selectedDeductionContainer.setChildrenAllowed(levelDTO, false);
            }
        }
    }

    @Override
    public void moveAllDeductions() {
        selectedDeduction.removeAllItems();
        selectedDeductionContainer.removeAllItems();
        String parameters = ((HelperDTO) deductionLevel.getValue()).getDescription();
        List<DeductionLevelDTO> listDto = availableDeductionContainer.getItemIds();
        Set<Integer> ids = selectedLevelIds.get(parameters);
        for (DeductionLevelDTO dto : listDto) {
            if (ids == null) {
                ids = new HashSet<>();
                selectedLevelIds.put(parameters, ids);
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

    private void configureReportFields() {
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

                bsrSessionDTO.setUserId(ARMUtils.getIntegerValue(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID))));
                bsrSessionDTO.setCurrentTableNames(QueryUtils.createTempTables("ARM_CCP_HIERARCHY", bsrSessionDTO.getProjectionId(), bsrSessionDTO.getUserId().toString(), bsrSessionDTO.getSessionId().toString()));
                getCustTopLevelName();
                logic.ccpHierarchyInsert(bsrSessionDTO.getCurrentTableNames(), selectedCustomerContainer.getItemIds(), selectedProductContainer.getItemIds(), bsrDataSelectionDTO);
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
        for (Object customer : selectedCustomerContainer.getItemIds()) {
            if (!selectedCustomerContainer.hasChildren(customer)) {
                customerHierarchyEndLevels.add(DataSelectionUtils.getBeanFromId(customer));
            }
        }
        return customerHierarchyEndLevels;
    }

    public List<LevelDTO> getProductHierarchyEndLevels(final ExtTreeContainer<LevelDTO> selectedProductContainer) {
        List<LevelDTO> productHierarchyEndLevels = new ArrayList<>();
        for (Object product : selectedProductContainer.getItemIds()) {
            if (!selectedProductContainer.hasChildren(product)) {
                productHierarchyEndLevels.add(DataSelectionUtils.getBeanFromId(product));
            }
        }
        return productHierarchyEndLevels;
    }

    @Override
    public void moveRightToLeftDeductions() {
        if (selectedDeduction.getValue() != null) {
            DeductionLevelDTO selectedDeductionLevelDTO = (DeductionLevelDTO) selectedDeduction.getValue();
            Set<String> ids = selectedDeductionLevelDTO.getRsContractSids();
            for (String bsrremoveSelectedCon_Sid : ids) {
                bsrRsContractSids.remove(Integer.valueOf(bsrremoveSelectedCon_Sid));
            }
            if (!bsrRsContractSids.isEmpty()) {
                bsrHierarchyKeys.clear();
                Map<String, DeductionLevelDTO> bsrLevelKeys = logic.getDeductionTree(selectedLevelIds, bsrRsContractSids, bsrHierarchyKeys);
                selectedDeductionContainer.removeAllItems();
                setDeductionTree(bsrLevelKeys);
            } else {
                selectedDeductionContainer.removeAllItems();
                selectedDeduction.removeAllItems();
            }
            customerLevel.select(null);
            productLevel.select(null);
            LOGGER.debug("End of moveRightToLeftDeductions");
        } else {
            AbstractNotificationUtils.getErrorNotification("Error", "No Level was selected to move. Please select and try again.");
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
        createHierarchyBasedOnHierarchyNo(selectedCustomerContainer, reslistOne, customerLevel);
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
        createHierarchyBasedOnHierarchyNo(selectedProductContainer, reslistOne, productLevel);
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
     * @param bsrObj the id
     * @return the bean from id
     */
    public static LevelDTO getBeanFromId(Object bsrObj) {

        BeanItem<?> targetItem = null;
        if (bsrObj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) bsrObj;
        } else if (bsrObj instanceof LevelDTO) {
            targetItem = new BeanItem<>((LevelDTO) bsrObj);
        }
        if (targetItem == null) {
            return new LevelDTO();
        } else {
            return (LevelDTO) targetItem.getBean();

        }
    }

    private void loadFromAndTo(DataSelectionDTO bsrSelection) {
        if (company.getValue() != null && businessUnit.getValue() != null && summaryTypeDdlb.getValue() != null) {
            int glCompanyId = Integer.parseInt(company.getValue().toString());
            int bUnitCompMasterSid = Integer.parseInt(businessUnit.getValue().toString());
            int value = -(((HelperDTO) (summaryTypeDdlb.getValue())).getId());
            fromPeriod.removeAllItems();
            toPeriod.removeAllItems();
            configurePeriodDropDown(fromPeriod);
            configurePeriodDropDown(toPeriod);
            DataSelectionLogic.getDates(value, glCompanyId, bUnitCompMasterSid, fromPeriod, toPeriod, true, bsrSelection);
            fromPeriod.select(bsrSelection.getFromPeriod());
            toPeriod.select(bsrSelection.getToPeriod());
            periodView = DataSelectionLogic.getPeriodViewForAdjustmentType(0, glCompanyId, bUnitCompMasterSid);
            if (ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView)) {
                toPeriod.removeAllItems();
                configurePeriodDropDown(toPeriod);
                DataSelectionLogic.getDates(value, glCompanyId, bUnitCompMasterSid, toPeriod);
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
        GridLayout companyLayout = new GridLayout(4, 2);
        companyLayout.addComponent(summaryTypeLabel);
        companyLayout.addComponent(summaryTypeDdlb);

        companyLayout.addComponent(companyLabel);
        companyLayout.addComponent(company);

        companyLayout.addComponent(viewLabel);
        companyLayout.addComponent(view);

        companyLayout.addComponent(businessUnitLabel);
        companyLayout.addComponent(businessUnit);
        companyLayout.setSpacing(true);
        companyLayout.setMargin(false);

        summaryTypeDdlb.setWidth("217px");
        selectionVerticalLayout.addComponent(companyLayout);
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

    private void securityForAllScreens() {

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(com.stpl.app.utils.ConstantsUtils.USER_ID));
        if (com.stpl.app.utils.ConstantsUtils.BALANCE_SUMMARY_REPORT.equalsIgnoreCase(screenName)) {
            final Map<String, AppPermission> bsrfunction = stplSecurity.getBusinessFunctionPermission(userId, ARMUtils.BALANCE_SUMMARY_REPORT + ConstantsUtils.COMMA + com.stpl.app.utils.ConstantsUtils.LANDING_SCREEN);
            if (bsrfunction.get("generateBtn") != null && !(bsrfunction.get("generateBtn")).isFunctionFlag()) {
                generateBtn.setVisible(false);
            }
            if (bsrfunction.get("resetBtn") != null && !(bsrfunction.get("resetBtn")).isFunctionFlag()) {
                resetBtn.setVisible(false);
            }
            if (bsrfunction.get("saveViewBtn") != null && !(bsrfunction.get("saveViewBtn")).isFunctionFlag()) {
                saveViewBtn.setVisible(false);
            }
            if (bsrfunction.get("deleteViewBtn") != null && !(bsrfunction.get("deleteViewBtn")).isFunctionFlag()) {
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
            if ((StringUtils.isEmpty(company.getValue() == null ? "" : company.getValue().toString()) || CommonLogic.checkInt(ARMUtils.getIntegerValue(company.getValue().toString())))
                    && (customerHierarchyLookup == null || CommonLogic.checkInt(customerHierarchyLookup.getHierarchyDto().getHierarchyId()))
                    && (productHierarchyLookup == null || CommonLogic.checkInt(productHierarchyLookup.getHierarchyDto().getHierarchyId()))
                    && (businessUnit.getValue() == null || CommonLogic.checkInt(ARMUtils.getIntegerValue(businessUnit.getValue().toString())))
                    && (deductionLevel.getValue() == null || CommonLogic.checkInt(((HelperDTO) deductionLevel.getValue()).getId()))
                    && (adjustmentType.getValue() == null || CommonLogic.checkInt(((HelperDTO) adjustmentType.getValue()).getId()))
                    && (summaryTypeDdlb.getValue() == null)) {
                AbstractNotificationUtils.getErrorNotification(msgHeader, msg);
                return false;
            }

            int bsrCustomerLevl = 0;
            int bsrProductLevelVal = 0;
            String[] bsrLevelArrCus = customerLevel.getValue() != null ? customerLevel.getItemCaption(customerLevel.getValue()).split(
                    ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE) : new String[0];
            String[] bsrLevelArrProd = productLevel.getValue() != null ? productLevel.getItemCaption(productLevel.getValue()).split(
                    ARMUtils.SPACE + ARMUtils.HIPHEN + ARMUtils.SPACE) : new String[0];
            String[] levelNoArrCus = bsrLevelArrCus.length > 0 ? bsrLevelArrCus[0].split(ARMUtils.SPACE.toString()) : new String[0];
            String[] levelNoArrProd = bsrLevelArrProd.length > 0 ? bsrLevelArrProd[0].split(ARMUtils.SPACE.toString()) : new String[0];
            bsrCustomerLevl = levelNoArrCus.length > 0 ? Integer.parseInt(levelNoArrCus[1]) : 0;
            bsrProductLevelVal = levelNoArrProd.length > 0 ? Integer.parseInt(levelNoArrProd[1]) : 0;
            String userId = (String) VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID);
            bsrDataSelectionDTO.setProjectionName(StringUtils.EMPTY);
            bsrDataSelectionDTO.setCreatedBy(CommonLogic.parseStringToInteger(userId));
            if (bsrDataSelectionDTO.getViewCreatedBy() == 0) {
                bsrDataSelectionDTO.setViewCreatedBy(bsrDataSelectionDTO.getCreatedBy());
            }
            bsrDataSelectionDTO.setCreatedDate(new Date());
            bsrDataSelectionDTO.setCustomerHierarchySid(customerHierarchyLookup == null ? NumericConstants.ZERO : customerHierarchyLookup.getHierarchyDto().getHierarchyId());
            bsrDataSelectionDTO.setProductHierarchySid(productHierarchyLookup == null ? NumericConstants.ZERO : productHierarchyLookup.getHierarchyDto().getHierarchyId());
            bsrDataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(bsrCustomerLevl));
            bsrDataSelectionDTO.setProductHierarchyLevel(String.valueOf(bsrProductLevelVal));
            bsrDataSelectionDTO.setCustomerHierarchyVersionNo(customerHierarchyLookup == null ? NumericConstants.ZERO : customerHierarchyLookup.getHierarchyDto().getVersionNo());
            bsrDataSelectionDTO.setProductHierarchyVersionNo(productHierarchyLookup == null ? NumericConstants.ZERO : productHierarchyLookup.getHierarchyDto().getVersionNo());
            bsrDataSelectionDTO.setCompanyMasterSid(company.getValue() != null ? ARMUtils.getIntegerValue(String.valueOf(company.getValue())) : NumericConstants.ZERO);
            bsrDataSelectionDTO.setFromPeriod(fromPeriod.getValue() == null || "0".equals(String.valueOf(fromPeriod.getValue())) ? StringUtils.EMPTY : String.valueOf(fromPeriod.getValue()));   //Obtain from Admin Console
            bsrDataSelectionDTO.setFromPeriodMonth(fromPeriod.getValue() != null || "0".equals(String.valueOf(fromPeriod.getValue())) ? fromPeriod.getItemCaption(fromPeriod.getValue()) : StringUtils.EMPTY);
            bsrDataSelectionDTO.setFromDate("0".equals(String.valueOf(fromPeriod.getValue())) ? null : CommonLogic.parseDate(String.valueOf(fromPeriod.getValue())));
            bsrDataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
            bsrDataSelectionDTO.setToDate("0".equals(String.valueOf(toPeriod.getValue())) ? null : CommonLogic.parseDate(String.valueOf(toPeriod.getValue())));
            bsrDataSelectionDTO.setSaveFlag(false);
            bsrDataSelectionDTO.setCustRelationshipBuilderSid(customerRelation.getValue() != null ? ARMUtils.getIntegerValue(String.valueOf(customerRelation.getValue())) : NumericConstants.ZERO);
            bsrDataSelectionDTO.setProdRelationshipBuilderSid(productRelation.getValue() != null ? ARMUtils.getIntegerValue(String.valueOf(productRelation.getValue())) : NumericConstants.ZERO);
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
