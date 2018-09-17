/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.form;

import com.stpl.app.arm.abstractforms.AbstractDataSelection;
import com.stpl.app.arm.adjustmentsummary.form.AdjustmentSummaryWindow;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.DeductionLevelDTO;
import com.stpl.app.arm.dataselection.dto.HierarchyLookupDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.dto.PeriodCheckValidator;
import com.stpl.app.arm.dataselection.dto.ViewDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import static com.stpl.app.arm.dataselection.logic.DataSelectionLogic.getPeriod;
import com.stpl.app.arm.dataselection.ui.lookups.HierarchyLookup;
import com.stpl.app.arm.dataselection.ui.lookups.PrivatePublicLookUp;
import com.stpl.app.arm.dataselection.ui.lookups.SaveViewLookUp;
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
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author
 */
public class DataSelection extends AbstractDataSelection {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSelection.class);
    private DataSelectionLogic logic = new DataSelectionLogic();
    private DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();
    private List<Integer> customerSidList = new ArrayList<>();
    protected SummarySelection selection = new SummarySelection();

    private String screenName;
    @UiField("adjustmentTypeLabel")
    protected Label adjustmentTypeLabel;
    @UiField("descriptionLabel")
    protected Label descriptionLabel;
    private SessionDTO sessionDTO;
    private final Set<Integer> rsContractSids = new HashSet<>();
    private final List<String> hierarchyKeys = new ArrayList<>();

    //Used for CCP_HIERARCHY_INSERT query formation
    private String topLevelName = StringUtils.EMPTY;
    private String periodView = StringUtils.EMPTY;
    private List<Integer> customerHierarchyLevelDefnList = new ArrayList<>();
    private Map<Integer, Integer> customerVersionMap = new HashMap<>();
    private Map<Integer, Integer> productVersionMap = new HashMap<>();
    private List<Integer> productHierarchyLevelDefnList = new ArrayList<>();

    public DataSelection(String screenName, SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        this.screenName = screenName;
        securityForAllScreens();
        configureFields();
    }

    @UiHandler("companyDDLB")
    public void companyDdlbVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();

        if (val == null) {
            dataSelectionDTO.setCompanyMasterSid(0);
        } else {
            dataSelectionDTO.setCompanyMasterSid((int) val);
        }
        dataSelectionDTO.setCheckFlag(true);
        loadFromAndTo(dataSelectionDTO);
        loadAvailableProducts();

    }

    @UiHandler("businessUnit")
    public void businessDdlbVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();
        if (val == null) {
            dataSelectionDTO.setBucompanyMasterSid(0);
        } else {
            dataSelectionDTO.setBucompanyMasterSid((int) val);
        }
        dataSelectionDTO.setCheckFlag(true);
        loadFromAndTo(dataSelectionDTO);
        loadAvailableProducts();

    }

    @UiHandler("adjustmentType")
    public void adjustmentTypeVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();
        if (val != null) {
            HelperDTO dto = (HelperDTO) val;
            screenName = dto.getDescription();
            dataSelectionDTO.setAdjustmentType(screenName);
            dataSelectionDTO.setAdjustmentCaption(adjustmentType.getItemCaption(adjustmentType.getValue()));
            dataSelectionDTO.setAdjustmentId(dto.getId());
            if (ARMConstants.getPipelineAccrual().equalsIgnoreCase(screenName) || ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(screenName)
                    || ARMConstants.getTransaction6().equalsIgnoreCase(screenName) || ARMConstants.getTransaction7().equalsIgnoreCase(screenName)
                    || ARMConstants.getTransaction8().equalsIgnoreCase(screenName)) {
                toPeriod.select(0);
                toPeriod.setEnabled(true);
                toPeriod.removeAllItems();
                configurePeriodDropDown(toPeriod);
                toPeriod.setEnabled(false);
            } else {
                toPeriod.setEnabled(true);
            }
        } else {
            screenName = StringUtils.EMPTY;
            dataSelectionDTO.setAdjustmentId(0);
            dataSelectionDTO.setAdjustmentType(StringUtils.EMPTY);
        }
        dataSelectionDTO.setCheckFlag(true);
        loadFromAndTo(dataSelectionDTO);

    }

    @Override
    public void loadCustRelationAndLevel(int custHierSid, CustomTextField.ClickEvent event) {
        customerVersionMap = logic.loadCustomerRelation(customerRelation, custHierSid);
        customerHierarchyLevelDefnList = logic.loadCustoProdLevels(customerLevel, custHierSid);
        customerBeanList.clear();
    }

    @Override
    public void loadProdRelationAndLevel(int prodHierSid, int glComp, CustomTextField.ClickEvent event) {
        productVersionMap = logic.loadProductRelation(productRelation, prodHierSid, glComp);
        productHierarchyLevelDefnList = logic.loadCustoProdLevels(productLevel, prodHierSid);
        productBeanList.clear();
    }

    @Override
    public void loadAvailableCustomers() {
        availableCustomer.removeAllItems();
        availableCustomerContainer.removeAllItems();
        String levelName = "Level";
        Object value = customerLevel.getItemCaption(customerLevel.getValue());
        try {
            int forecastLevel = 0;
            List<LevelDTO> custVlues = null;
            if (value != null && customerRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(customerRelation.getValue())) {
                String selectedLevel = String.valueOf(value);
                if (customerRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(customerRelation.getValue()) && !innerCustLevels.isEmpty()) {
                    int relationshipSid = (Integer) customerRelation.getValue();
                    DataSelectionLogic dslogic = new DataSelectionLogic();
                    String[] val = selectedLevel.split(" ");
                    forecastLevel = Integer.parseInt(val[1]);
                    LevelDTO tempDto = innerCustLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        levelName = tempDto.getLevel();
                        tempDto.getLevelNo();
                    }
                    List<LevelDTO> customerHierarchyLevelDefinitionList = dslogic.getHierarchyLevelDefinition(customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getVersionNo());;
                    LevelDTO selectedHierarchyLevelDto = customerHierarchyLevelDefinitionList.get(forecastLevel - 1);
                    custVlues = dslogic.loadCustomerInnerLevel(createInputBean(customerHierarchyLookup.getHierarchyDto(), relationshipSid,
                            customerVersionMap.get(relationshipSid), tempDto.getLevelNo(), customerHierarchyLevelDefnList.get(tempDto.getLevelNo() - 1), false, rsContractSids), customerDescriptionMap, selectedHierarchyLevelDto);
                    availableCustomerContainer.addAll(custVlues);
                }

            }
            availableCustomer.setContainerDataSource(availableCustomerContainer);
            availableCustomer.setVisibleColumns(new Object[]{CommonConstant.DISPLAY_VALUE});
            availableCustomer.setColumnHeaders(new String[]{levelName});
            availableCustomer.setSelectable(true);
            availableCustomer.setFilterBarVisible(true);
            availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
            availableCustomer.setStyleName("filtertable");
        } catch (Exception ex) {
            LOGGER.error("Customer level ValueChangeListener ", ex);
        }
    }

    @Override
    public void customerHierarchyCloseListener() {
        try {
            customerHierarchyLookup.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    customerValueChange();
                    innerCustLevels = logic.loadCustomerForecastLevel(customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getHierarchyName());

                }
            });
        } catch (Exception e) {
            LOGGER.error("Error in customerHierarchyCloseListener :", e);
        }
    }

    @Override
    public void productHierarchyCloseListener() {
        productHierarchyLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                productValueChange(dataSelectionDTO.getCompanyMasterSid());
            }
        });
    }

    @Override
    public void productValueChange(int glCompId) {
        super.productValueChange(glCompId);
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
                    customerDescriptionMap = new DataSelectionQueryUtils().loadLevelValuesMap(relationshipSid, customerVersionMap.get(relationshipSid), customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getVersionNo());
                    customerBeanList.clear();
                } catch (Exception ex) {
                    LOGGER.error("customerRelation value change", ex);
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
                            productVersionMap.get(relationshipSid), productHierarchyLookup.getHierarchyDto().getHierarchyId(),
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
            innerCustLevels = logic.loadCustomerForecastLevel(dataSelectionDTO.getCustomerHierarchySid(), dataSelectionDTO.getCustomerHierarchyName());
        } catch (Exception ex) {
            LOGGER.error("loadCustomerLevel", ex);
        }

    }

    public void loadProductLevel() {
        try {
            innerProdLevels = logic.loadCustomerForecastLevel(dataSelectionDTO.getProductHierarchySid(), dataSelectionDTO.getProductHierarchyName());
        } catch (Exception ex) {
            LOGGER.error("loadProductLevel", ex);
        }
    }

    @Override
    public void loadAvailableProducts() {
        try {
            availableProductContainer.removeAllItems();
            availableProduct.removeAllItems();
            int prodforecastLevel = 0;
            boolean isNdc = false;
            List<LevelDTO> innerLevelValues = null;
            String levelName = "Level";
            String selectedLevel = productLevel.getValue() == null ? StringUtils.EMPTY : productLevel.getValue().toString();
            if ((selectedLevel != null && !selectedLevel.equalsIgnoreCase(StringUtils.EMPTY) && !ARMUtils.NULL.equals(selectedLevel) && !GlobalConstants.getSelectOne().equals(selectedLevel)
                    && productRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(productRelation.getValue())) && (productRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(productRelation.getValue()) && !innerProdLevels.isEmpty())) {

                int prodRelationshipSid = (Integer) productRelation.getValue();
                DataSelectionLogic loadAvailableDsLogic = new DataSelectionLogic();
                prodforecastLevel = Integer.parseInt(selectedLevel);
                LevelDTO tempDto = innerProdLevels.get(prodforecastLevel - 1);
                if (tempDto.getLevel() != null) {
                    levelName = tempDto.getLevel();
                }
                customerSidList.clear();
                getChildIds(selectedCustomerContainer, customerSidList);
                int custhierarchyId = customerHierarchyLookup != null ? customerHierarchyLookup.getHierarchyDto().getHierarchyId() : 0;
                isNdc = (tempDto.getLevel().equalsIgnoreCase("Package")
                        || tempDto.getLevel().equalsIgnoreCase("NDC-11"));
                List<LevelDTO> selectedCustomerContractList = getSelectedCustomerContractList();
                GtnARMHierarchyInputBean bean = createInputBean(productHierarchyLookup.getHierarchyDto(), prodRelationshipSid,
                        productVersionMap.get(prodRelationshipSid), tempDto.getLevelNo(),
                        productHierarchyLevelDefnList.get(tempDto.getLevelNo() - 1), isNdc, rsContractSids);
                GtnARMHierarchyInputBean inputBean = loadCustomersInInputbean(bean, customerVersionMap.get((Integer) customerRelation.getValue()),
                        selectedCustomerContractList, custhierarchyId, customerHierarchyLookup != null ? customerHierarchyLookup.getHierarchyDto().getVersionNo() : 0);
                innerLevelValues = loadAvailableDsLogic.loadProductInnerLevel(inputBean, productDescriptionMap);
                availableProductContainer.addAll(innerLevelValues);
                availableProduct.setContainerDataSource(availableProductContainer);
                if (isNdc) {
                    availableProduct.setVisibleColumns(new Object[]{CommonConstant.DISPLAY_VALUE, "form", "strength"});
                    availableProduct.setColumnHeaders(new String[]{ARMUtils.NDC, "Form", "Strength"});
                } else {
                    availableProduct.setVisibleColumns(new Object[]{CommonConstant.DISPLAY_VALUE});
                    availableProduct.setColumnHeaders(new String[]{levelName});
                }
                availableProduct.setFilterBarVisible(true);
                availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
                availableProduct.setStyleName("filtertable");
            }
        } catch (NullPointerException e) {
            LOGGER.error(" Company Ddlb might not have been selected", e);
        } catch (Exception ex) {
            LOGGER.error("Error  :", ex);
        }
    }

    public void getChildIds(ExtTreeContainer<LevelDTO> container, List<Integer> ids) {
        Collection<?> parentCustids = container.rootItemIds();
        if (parentCustids != null && !parentCustids.isEmpty()) {
            getChildIds(container, ids, parentCustids, null);
        }
    }

    public void getChildIds(ExtTreeContainer<LevelDTO> container, List<Integer> ids, Collection<?> parentCustids, Object parent) {
        if (parentCustids != null && !parentCustids.isEmpty()) {
            Iterator<?> x = parentCustids.iterator();
            while (x.hasNext()) {
                Object y = x.next();
                if (container.hasChildren(y)) {
                    getChildIds(container, ids, container.getChildren(y), y);
                } else {
                    LevelDTO dto = (LevelDTO) y;
                    ids.add(dto.getRelationshipLevelSid());
                }
            }
        } else if (parent != null) {
            LevelDTO dto = (LevelDTO) parent;
            ids.add(dto.getRelationshipLevelSid());
        }
    }

    @Override
    public void moveLeftCustomersButtonLogic() {
        try {
            DataSelectionLogic moveLeftDsLogic = new DataSelectionLogic();
            if (availableCustomer.getValue() != null) {
                int forecastLevel = ARMUtils.getIntegerValue(customerLevel.getValue().toString());
                if (customerLevel.getValue() != null) {
                    forecastLevel = CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
                }

                Object item = availableCustomer.getValue();
                if (selectedCustomerContainer.size() > 0) {
                    if (selectedCustomer.getValue() != null) {

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
                            newParentLevels = moveLeftDsLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                        }

                        newChildLevels = moveLeftDsLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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

                    } else {
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
                            newParentLevels = moveLeftDsLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                        }

                        newChildLevels = moveLeftDsLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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
                } else if (availableCustomer.getValue() != null && (DataSelectionUtils.getBeanFromId(availableCustomer.getValue()).getLevelNo() > 1)) {

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
                    LevelDTO selectedParent2 = null;
                    while (hierarchyNo.contains(ARMUtils.DOT)) {
                        pos = hierarchyNo.lastIndexOf(ARMUtils.DOT);
                        if (pos != -1) {
                            hierarchyNo = hierarchyNo.substring(0, pos);
                        }
                        hierarchyNos.add(hierarchyNo + ".");
                    }
                    Collections.reverse(hierarchyNos);
                    if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                        for (LevelDTO selectedLevel : selectedCustomerContainer.getItemIds()) {
                            if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                selectedParent2 = selectedLevel;
                                break;
                            }
                        }
                    }
                    newParentLevels = moveLeftDsLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(hierarchyNos), customerDescriptionMap, customerRelation.getValue().toString());

                    newChildLevels = moveLeftDsLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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
                                selectedCustomerContainer.setParent(newLevel, selectedParent2);
                                selectedParent2 = newLevel;
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
                } else {
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

                    newChildLevels = moveLeftDsLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);

                    if ((newChildLevels != null) && !newChildLevels.isEmpty()) {
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
                            if (customerBeanList.isEmpty()) {
                                customerBeanList.add(newLevel.getRelationshipLevelSid());
                                selectedCustomerContainer.addBean(newLevel);
                                if (forecastLevel != selectedParent.getLevelNo()) {
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
                AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
                        "No Level was selected to move. Please try again.");
            }
        } catch (Exception e) {
            LOGGER.error("Error in moveLeftCustomersButtonLogic :", e);
        }
    }

    @Override
    public void moveRightCustomersButtonLogic() {
        try {
            if (selectedCustomer.getValue() != null) {
                Object selectedItem = selectedCustomer.getValue();
                String levelInString = ARMUtils.ZERO_STRING;
                if (customerLevel.getValue() != null && !String.valueOf(customerLevel.getValue()).equalsIgnoreCase(String.valueOf(GlobalConstants.getSelectOne()))) {
                    levelInString = String.valueOf(customerLevel.getValue());
                }
                int currentLevel = CommonLogic.parseStringToInteger(levelInString);
                DataSelectionUtils.removeItemsRecursively(selectedItem, selectedCustomer, availableCustomer, selectedCustomerContainer, availableCustomerContainer, currentLevel);
                selectedCustomerContainer.removeItem(DataSelectionUtils.getBeanFromId(selectedItem));
                selectedCustomer.removeItem(selectedItem);
                customerBeanList.clear();
                List<LevelDTO> selectedValueItem = selectedCustomerContainer.getItemIds();
                for (LevelDTO dto : selectedValueItem) {
                    customerBeanList.add(dto.getRelationshipLevelSid());
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
            DataSelectionLogic moveLeftProdLogic = new DataSelectionLogic();
            int forecastLevel = 0;

            if (productLevel.getValue() != null) {
                forecastLevel = CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()));
            }

            if (availableProduct.getValue() != null) {
                Object item = availableProduct.getValue();
                if (selectedProductContainer.size() > 0) {
                    if (selectedProduct.getValue() != null) {

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
                            newParentLevels = moveLeftProdLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap, productRelation.getValue().toString());
                        }
                        newChildLevels = moveLeftProdLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
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
                                    if (forecastLevel != newLevel.getLevelNo()) {
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
                                        if (forecastLevel != newLevel.getLevelNo()) {
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

                    } else {

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
                            newParentLevels = moveLeftProdLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap, productRelation.getValue().toString());
                        }
                        newChildLevels = moveLeftProdLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
                        if (newParentLevels != null) {
                            for (LevelDTO newLevel : newParentLevels) {
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
                                        if (forecastLevel != newLevel.getLevelNo()) {
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
                } else if (availableProduct.getValue() != null && (DataSelectionUtils.getBeanFromId(availableProduct.getValue()).getLevelNo() > 1)) {

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
                    LevelDTO selectedParent2 = null;
                    while (hierarchyNo.contains(ARMUtils.DOT)) {
                        pos = hierarchyNo.lastIndexOf(ARMUtils.DOT);
                        if (pos != -1) {
                            hierarchyNo = hierarchyNo.substring(0, pos);
                        }
                        hierarchyNos.add(hierarchyNo + ".");
                    }
                    Collections.reverse(hierarchyNos);
                    if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
                        for (LevelDTO selectedLevel : selectedProductContainer.getItemIds()) {
                            if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
                                selectedParent2 = selectedLevel;
                                break;
                            }
                        }
                    }
                    newParentLevels = moveLeftProdLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(hierarchyNos), productDescriptionMap, productRelation.getValue().toString());
                    newChildLevels = moveLeftProdLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
                    if (newParentLevels != null) {
                        for (LevelDTO newLevel : newParentLevels) {
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
                                    if (forecastLevel != newLevel.getLevelNo()) {
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
                } else {
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
                    if (productBeanList.isEmpty() || !productBeanList.contains(DataSelectionUtils.getBeanFromId(item).getRelationshipLevelSid())) {
                        productBeanList.add(DataSelectionUtils.getBeanFromId(item).getRelationshipLevelSid());
                        selectedProductContainer.addBean(selectedParent);
                        if (forecastLevel != selectedParent.getLevelNo()) {
                            selectedProductContainer.setChildrenAllowed(selectedParent, true);
                        } else {
                            selectedProductContainer.setChildrenAllowed(selectedParent, false);
                        }
                    }
                    newChildLevels = moveLeftProdLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
                    if ((newChildLevels != null) && !newChildLevels.isEmpty()) {
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
                                if (forecastLevel != newLevel.getLevelNo()) {
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
                List<LevelDTO> listValue;
                Object selectedItem = selectedProduct.getValue();
                LevelDTO selectedLevel = DataSelectionUtils.getBeanFromId(selectedItem);
                String levelInString = ARMUtils.ZERO_STRING;
                if (productLevel.getValue() != null && !String.valueOf(productLevel.getValue()).equalsIgnoreCase(String.valueOf(GlobalConstants.getSelectOne()))) {
                    levelInString = String.valueOf(productLevel.getValue());
                }
                int currentLevel = CommonLogic.parseStringToInteger(levelInString);
                if ((currentLevel != 0 && selectedLevel.getLevelNo() == currentLevel) && ARMUtils.NDC.equalsIgnoreCase(selectedLevel.getLevel())) {
                    listValue = DataSelectionUtils.getFSValue(selectedLevel.getRelationshipLevelValue(), selectedLevel.getFieldName());
                    selectedLevel.setForm(StringUtils.EMPTY + listValue.get(0).getForm());
                    selectedLevel.setStrength(StringUtils.EMPTY + listValue.get(0).getStrength());
                }

                DataSelectionUtils.removeItemsRecursively(selectedItem, selectedProduct, availableProduct, selectedProductContainer, availableProductContainer, currentLevel);
                selectedProductContainer.removeItem(selectedLevel);
                selectedProduct.removeItem(selectedItem);
                productBeanList.clear();
                List<LevelDTO> productListValue = selectedProductContainer.getItemIds();
                for (LevelDTO dto : productListValue) {
                    productBeanList.add(dto.getRelationshipLevelSid());
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
            DataSelectionLogic moveAllProdLogic = new DataSelectionLogic();
            int forecastLevel = 0;
            if (productLevel.getValue() != null) {
                forecastLevel = CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
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
                                newParentLevels = moveAllProdLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap, productRelation.getValue().toString());
                                newChildLevels = moveAllProdLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                                            if (forecastLevel != newLevel.getLevelNo()) {
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
                                                if (forecastLevel != newLevel.getLevelNo()) {
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
                                newParentLevels = moveAllProdLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap, productRelation.getValue().toString());
                            }
                            newChildLevels = moveAllProdLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
                            if (newParentLevels != null) {
                                for (LevelDTO newLevel : newParentLevels) {
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
                                            if (forecastLevel != newLevel.getLevelNo()) {
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
                                LOGGER.debug("tempHN0 {}", tempHNo);
                            }

                        }
                        if (!uncommonValues.isEmpty()) {
                            newParentLevels = moveAllProdLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap, productRelation.getValue().toString());
                        }
                        newChildLevels = moveAllProdLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                                    if (forecastLevel != newLevel.getLevelNo()) {
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
                                        if (forecastLevel != newLevel.getLevelNo()) {
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
            LOGGER.error("Error in moveAllProductsButtonLogic :", e);
        }
    }

    @Override
    public void moveAllCustomersButtonLogic() {
        try {
            DataSelectionLogic moveAllLogic = new DataSelectionLogic();
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
                                    newParentLevels = logic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                                }
                                newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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
                                newParentLevels = moveAllLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                            }
                            newChildLevels = moveAllLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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
                        newChildLevels = moveAllLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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
                        newParentLevels = moveAllLogic.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap, customerRelation.getValue().toString());
                        newChildLevels = moveAllLogic.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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
            if (customerHierarchyLookup == null || productHierarchyLookup == null || (description.isVisible() && StringUtils.isBlank(String.valueOf(description.getValue())))
                    || customerLevel.getValue() == null || productLevel.getValue() == null || deductionLevel.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(msgHeader, msg);
            } else if (ARMConstants.getAdjustmentSummary().equals(screenName) && (StringUtils.isEmpty(company.getValue() == null ? "" : company.getValue().toString())
                    || CommonLogic.checkInt(customerHierarchyLookup.getHierarchyDto().getHierarchyId())
                    || CommonLogic.checkInt(productHierarchyLookup.getHierarchyDto().getHierarchyId())
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(company.getValue().toString()))
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(businessUnit.getValue().toString()))
                    || CommonLogic.checkInt(((HelperDTO) deductionLevel.getValue()).getId())
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(customerRelation.getValue().toString()))
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(productRelation.getValue().toString()))
                    || rsContractSids.isEmpty() || selectedCustomerContainer.size() == 0 || selectedProductContainer.size() == 0
                    || selectedDeductionContainer.size() == 0)) {
                AbstractNotificationUtils.getErrorNotification(msgHeader, msg);
            } else if (!ARMConstants.getAdjustmentSummary().equals(screenName) && (adjustmentType.getValue() == null || businessUnit.getValue() == null || StringUtils.isEmpty(company.getValue() == null ? "" : company.getValue().toString())
                    || CommonLogic.checkInt(customerHierarchyLookup.getHierarchyDto().getHierarchyId())
                    || CommonLogic.checkInt(productHierarchyLookup.getHierarchyDto().getHierarchyId())
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(company.getValue().toString()))
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(businessUnit.getValue().toString()))
                    || CommonLogic.checkInt(((HelperDTO) deductionLevel.getValue()).getId())
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(customerRelation.getValue().toString()))
                    || CommonLogic.checkInt(ARMUtils.getIntegerValue(productRelation.getValue().toString()))
                    || rsContractSids.isEmpty() || selectedCustomerContainer.size() == 0 || selectedProductContainer.size() == 0
                    || selectedDeductionContainer.size() == 0)) {
                AbstractNotificationUtils.getErrorNotification(msgHeader, msg);
            } else {
                boolean mandatory = false;
                if (fromPeriod.getValue() == null || "0".equalsIgnoreCase(String.valueOf(fromPeriod.getValue()))) {
                    mandatory = true;
                }
                if ((!ARMConstants.getPipelineAccrual().equalsIgnoreCase(screenName)
                        && !ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(screenName) && !ARMConstants.getTransaction6().equalsIgnoreCase(screenName)
                        && !ARMConstants.getTransaction7().equalsIgnoreCase(screenName) && !ARMConstants.getTransaction8().equalsIgnoreCase(screenName)) && (toPeriod.getValue() == null || "0".equalsIgnoreCase(String.valueOf(toPeriod.getValue())))) {
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
                dataSelectionDTO.setProjectionName(StringUtils.EMPTY);
                dataSelectionDTO.setProjectionDescription(description.getValue());
                dataSelectionDTO.setCreatedBy(CommonLogic.parseStringToInteger(userId));
                if (dataSelectionDTO.getViewCreatedBy() == 0) {
                    dataSelectionDTO.setViewCreatedBy(dataSelectionDTO.getCreatedBy());
                }
                dataSelectionDTO.setCreatedDate(new Date());
                dataSelectionDTO.setCustomerHierarchySid(customerHierarchyLookup.getHierarchyDto().getHierarchyId());
                dataSelectionDTO.setProductHierarchySid(productHierarchyLookup.getHierarchyDto().getHierarchyId());
                dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerLevl));
                dataSelectionDTO.setProductHierarchyLevel(String.valueOf(productLevelVal));
                dataSelectionDTO.setCustomerHierarchyVersionNo(customerHierarchyLookup.getHierarchyDto().getVersionNo());
                dataSelectionDTO.setProductHierarchyVersionNo(productHierarchyLookup.getHierarchyDto().getVersionNo());
                dataSelectionDTO.setCompanyMasterSid(ARMUtils.getIntegerValue(company.getValue().toString()));
                dataSelectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));   //Obtain from Admin Console
                dataSelectionDTO.setFromPeriodMonth(fromPeriod.getItemCaption(fromPeriod.getValue()));
                dataSelectionDTO.setFromDate(CommonLogic.parseDate(String.valueOf(fromPeriod.getValue())));
                if (ARMConstants.getPipelineAccrual().equalsIgnoreCase(screenName) || ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(screenName)
                        || ARMConstants.getTransaction6().equalsIgnoreCase(screenName)
                        || ARMConstants.getTransaction7().equalsIgnoreCase(screenName)
                        || ARMConstants.getTransaction8().equalsIgnoreCase(screenName)) {
                    dataSelectionDTO.setToPeriod(dataSelectionDTO.getFromPeriod());
                } else {
                    dataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
                    dataSelectionDTO.setToDate(CommonLogic.parseDate(String.valueOf(toPeriod.getValue())));
                }
                dataSelectionDTO.setSaveFlag(false);
                dataSelectionDTO.setCustRelationshipBuilderSid(ARMUtils.getIntegerValue(customerRelation.getValue().toString()));
                dataSelectionDTO.setCustomerRelationshipVersionNo(customerVersionMap.get(ARMUtils.getIntegerValue(customerRelation.getValue().toString())));
                dataSelectionDTO.setProdRelationshipBuilderSid(ARMUtils.getIntegerValue(productRelation.getValue().toString()));
                dataSelectionDTO.setProductRelationshipVersionNo(productVersionMap.get(ARMUtils.getIntegerValue(productRelation.getValue().toString())));
                dataSelectionDTO.setForecastingType(VariableConstants.ARM);
                if (ARMConstants.getAdjustmentSummary().equals(screenName)) {
                    dataSelectionDTO.setForecastingType(ARMConstants.getAdjustmentSummary());
                }
                dataSelectionDTO.setAdjustmentType(screenName);
                dataSelectionDTO.setAdjustmentCaption(adjustmentType.getItemCaption(adjustmentType.getValue()));
                dataSelectionDTO.setRsContractSidList(new ArrayList<>(rsContractSids));
                dataSelectionDTO.setDeductionLevel(((HelperDTO) deductionLevel.getValue()).getId());
                dataSelectionDTO.setSelectedCustomerContainer(selectedCustomerContainer);
                dataSelectionDTO.setSelectedDeductionContainer(selectedDeductionContainer);
                dataSelectionDTO.setSelectedProductContainer(selectedProductContainer);
                dataSelectionDTO.setCustomerHierarchyName(customerHierarchy.getValue());
                dataSelectionDTO.setProductHierarchyName(productHierarchy.getValue());
                dataSelectionDTO.setBucompanyMasterSid((int) businessUnit.getValue());
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

        public CustomNotification() {
            LOGGER.debug("Empty Constructor");
        }

        private String dsButtonName;

        @Override
        public void noMethod() {
            LOGGER.debug("inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", dsButtonName);
            if (null != dsButtonName) {
                switch (dsButtonName) {
                    case "deleteView":
                        if ((dataSelectionDTO.getProjectionId() != 0) && (logic.deleteViewLogic(dataSelectionDTO.getProjectionId()))) {
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
            this.dsButtonName = buttonName;
        }

    }

    @Override
    public void generateButtonLogicForScreens() {
        LOGGER.debug(" generateButtonLogicForScreens {}", lengthValidation());
        if (lengthValidation() && saveDataSelectionValues()) {
            if (ARMConstants.getAdjustmentSummary().equals(screenName)) {
                sessionDTO.setWorkFlow(false);
                selection.setSessionDTO(sessionDTO);
                AdjustmentSummaryWindow form = new AdjustmentSummaryWindow(selection, dataSelectionDTO, sessionDTO);
                getUI().addWindow(form);
            } else {
                BussinessProcessForm form = new BussinessProcessForm(screenName, dataSelectionDTO, sessionDTO);
                getUI().addWindow(form);
            }
        }
    }

    @Override
    public void loadPrivateViewLook() {
        privateLookUp = new PrivatePublicLookUp(ARMUtils.PRIVATE, screenName);
        getUI().addWindow(privateLookUp);
        privateLookupCloseListener();
    }

    @Override
    public void loadPublicViewLook() {
        publicLookUp = new PrivatePublicLookUp(ARMUtils.PUBLIC, screenName);
        publicLookupCloseListener();
        getUI().addWindow(publicLookUp);
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
        privateLookUp.addCloseListener(closeListener);
    }

    @Override
    public void publicLookupCloseListener() {
        publicLookUp.addCloseListener(closeListener);
    }
    private Window.CloseListener closeListener = new Window.CloseListener() {

        @Override
        public void windowClose(Window.CloseEvent e) {
            try {
                PrivatePublicLookUp lookUP = (PrivatePublicLookUp) e.getWindow();
                if (lookUP.isSelected()) {
                    customerHierarchyLookup = new HierarchyLookup();
                    productHierarchyLookup = new HierarchyLookup();
                    dataSelectionDTO.setViewType(lookUP.getCaption());
                    dataSelectionDTO.setViewFlag(true);
                    if (lookUP.getCaption().equalsIgnoreCase(ARMUtils.PRIVATE_VIEW)) {
                        privateView.setValue(lookUP.getViewDTO().getViewName());
                        dataSelectionDTO.setViewName(lookUP.getViewDTO().getViewName());
                        publicView.setValue(StringUtils.EMPTY);
                    } else if (lookUP.getCaption().equalsIgnoreCase(ARMUtils.PUBLIC_VIEW)) {
                        publicView.setValue(lookUP.getViewDTO().getViewName());
                        dataSelectionDTO.setViewName(lookUP.getViewDTO().getViewName());
                        privateView.setValue(StringUtils.EMPTY);
                    }
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
                    BeanUtils.copyProperties(dataSelectionDTO, lookUP.getViewDTO());
                    setViewDetails(dataSelectionDTO, lookUP.getViewDTO());
                    deleteViewBtn.setEnabled(true);
                }
            } catch (Exception ex) {
                LOGGER.error("Error in closeListener :", ex);
            }
        }

        private void setViewDetails(DataSelectionDTO dto, ViewDTO viewDTO) {
            try {
                deductionViewFlag = true;
                Collection<?> adjItems = adjustmentType.getItemIds();
                Object adjItemToAdd = null;
                for (Object adjItem : adjItems) {
                    if (adjItem instanceof HelperDTO) {
                        HelperDTO hlpd = (HelperDTO) adjItem;
                        if (dto.getAdjustmentId() == hlpd.getId()) {
                            adjItemToAdd = adjItem;
                        }
                    }
                }
                adjustmentType.select(adjItemToAdd);
                description.setValue(dto.getProjectionDescription());
                CommonLogic.loadCompanyAndBusinessUnit(company, "getCompanyQueryForDS");
                CommonLogic.loadCompanyAndBusinessUnit(businessUnit, "getBusinessQueryForDS");
                company.select(dto.getCompanyMasterSid() == 0 ? null : dto.getCompanyMasterSid());
                businessUnit.select(viewDTO.getBuCompanyName().isEmpty() ? null : dto.getBucompanyMasterSid());
                fromPeriod.select(viewDTO.getFromPeriod() == null ? 0 : getPeriod(viewDTO.getFromPeriod()));
                if (!ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView) && !periodView.isEmpty()) {
                    toPeriod.select(viewDTO.getToPeriod() == null ? 0 : getPeriod(viewDTO.getToPeriod()));
                }
                deductionLevel.select(dto.getDeductionLevel() == 0 ? null : new HelperDTO(dto.getDeductionLevel(), HelperListUtil.getInstance().getIdDescMap().get(dto.getDeductionLevel())));
                rsContractSids.clear();
                rsContractSids.addAll(logic.getRsContractSids(dto.getProjectionId()));
                hierarchyKeys.clear();
                selectedDeductionContainer.removeAllItems();
                selectedDeduction.removeAllItems();
                Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, rsContractSids, hierarchyKeys);
                setDeductionTree(levelKeys, hierarchyKeys);
                customerHierarchy.setValue(dto.getCustomerHierarchyName());

                customerVersionMap = logic.loadCustomerRelation(customerRelation, dto.getCustomerHierarchySid());
                customerRelation.select(dto.getCustRelationshipBuilderSid());
                loadCustomerLevel();
                customerHierarchyLevelDefnList = logic.loadCustoProdLevels(customerLevel, dto.getCustomerHierarchySid());
                int relationshipSid = dto.getCustRelationshipBuilderSid();
                customerDescriptionMap = new DataSelectionQueryUtils().loadLevelValuesMap(relationshipSid, customerVersionMap.get(relationshipSid), customerHierarchyLookup.getHierarchyDto().getHierarchyId(), customerHierarchyLookup.getHierarchyDto().getVersionNo());
                customerLevel.select(dto.getCustomerHierarchyLevel());
                initializeCustomerHierarchy(dto.getProjectionId(), dto.getCustomerHierarchyLevel().isEmpty() ? 0 : Integer.valueOf(dto.getCustomerHierarchyLevel()));
                availableCustomerContainer.removeAllItems();
                availableCustomer.removeAllItems();
                productHierarchy.setValue(dto.getProductHierarchyName());
                productVersionMap = logic.loadProductRelation(productRelation, dto.getProductHierarchySid(), dto.getCompanyMasterSid());
                productRelation.select(dto.getProdRelationshipBuilderSid());
                loadProductLevel();
                productHierarchyLevelDefnList = logic.loadCustoProdLevels(productLevel, dto.getProductHierarchySid());
                productLevel.select(dto.getProductHierarchyLevel());
                productDescriptionMap = new DataSelectionQueryUtils().loadLevelValuesMap(dto.getProdRelationshipBuilderSid(),
                        productVersionMap.get(dto.getProdRelationshipBuilderSid()), productHierarchyLookup.getHierarchyDto().getHierarchyId(),
                        productHierarchyLookup.getHierarchyDto().getVersionNo());
                initializeProductHierarchy(dto.getProjectionId(), dto.getProductHierarchyLevel().isEmpty() ? 0 : Integer.valueOf(dto.getProductHierarchyLevel()));
                availableProductContainer.removeAllItems();
                availableProduct.removeAllItems();

            } catch (Exception e) {
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
                hierarchyKeys.clear();
                Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, rsContractSids, hierarchyKeys);
                selectedDeductionContainer.removeAllItems();
                setDeductionTree(levelKeys, hierarchyKeys);
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

    public void setLevels(LevelDTO parentList, List<DeductionLevelDTO> list, int levelNo) {
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
        hierarchyKeys.clear();
        Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, rsContractSids, hierarchyKeys);
        selectedDeductionContainer.removeAllItems();
        setDeductionTree(levelKeys, hierarchyKeys);
        selectedLevelIds.clear();
        customerLevel.select(null);
        productLevel.select(null);
    }

    public void configureFields() {
        panel1.setCaption("Adjustment Options");
        if (ARMConstants.getAdjustmentSummary().equals(screenName)) {
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
        deleteViewBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                String userID;
                String useridString;
                String tableUserId;
                try {
                    userID = (String) VaadinSession.getCurrent().getAttribute(ARMUtils.USER_ID);
                    useridString = DataSelectionLogic.getUserFLName(userID);
                    tableUserId = dataSelectionDTO.getCreatedByString();
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
        toPeriod.addValidator(new PeriodCheckValidator(fromPeriod, toPeriod, "To Period Cannot be Before the From Period"));
    }

    public boolean saveDataSelectionValues() {
        try {
            if (bindDataSelectionValues(false)) {
                int projectionIdValue = 0;
                String sessionId = new SimpleDateFormat("hhmmssms").format(new Date());
                sessionDTO.setSessionId(Integer.valueOf(sessionId));
                projectionIdValue = logic.saveProjection(dataSelectionDTO);
                VaadinSession.getCurrent().setAttribute(ARMUtils.PROJECTION_ID, projectionIdValue);
                dataSelectionDTO.setProjectionId(projectionIdValue);
                List<LevelDTO> customerList = selectedCustomerContainer.getItemIds();
                List<LevelDTO> productList = selectedProductContainer.getItemIds();
                List<String> customerListEndSids = DataSelectionUtils.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
                List<String> productListEndSids = DataSelectionUtils.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
                logic.saveCustomerHierarchyLogic(customerList, customerListEndSids, projectionIdValue, null, ARMUtils.SAVE);
                logic.saveProductHierarchyLogic(productList, productListEndSids, projectionIdValue, null, ARMUtils.SAVE);
                logic.saveDeductionLogic(new HashSet(dataSelectionDTO.getRsContractSidList()), projectionIdValue);

                sessionDTO.setUserId(ARMUtils.getIntegerValue(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID))));
                sessionDTO.setCurrentTableNames(QueryUtils.createTempTables("ARM_CCP_HIERARCHY", sessionDTO.getProjectionId(), sessionDTO.getUserId().toString(), sessionDTO.getSessionId().toString()));
                getCustTopLevelName();
                logic.ccpHierarchyInsert(sessionDTO.getCurrentTableNames(), selectedCustomerContainer.getItemIds(), selectedProductContainer.getItemIds(), dataSelectionDTO);
                logic.saveCcp(sessionDTO.getCurrentTableNames().get("ST_CCP_HIERARCHY"), String.valueOf(projectionIdValue));
                logic.saveAdjustmentMaster(dataSelectionDTO);
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
                rsContractSids.remove(Integer.valueOf(removeSelectedCon_Sid));
            }
            if (!rsContractSids.isEmpty()) {
                hierarchyKeys.clear();
                Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, rsContractSids, hierarchyKeys);
                selectedDeductionContainer.removeAllItems();
                setDeductionTree(levelKeys, hierarchyKeys);
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
            if (lengthValidation() && bindDataSelectionSaveViewValues()) {
                List<LevelDTO> customerList = selectedCustomerContainer.getItemIds();
                List<LevelDTO> productList = selectedProductContainer.getItemIds();
                List<String> customerListEndSids = DataSelectionUtils.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
                List<String> productListEndSids = DataSelectionUtils.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
                dataSelectionDTO.setCustomerList(customerList);
                dataSelectionDTO.setProductList(productList);
                dataSelectionDTO.setCustomerEndLevelList(customerListEndSids);
                dataSelectionDTO.setProductEndLevelList(productListEndSids);
                dataSelectionDTO.setAddUpdateFlag(StringUtils.isEmpty(privateView.getValue()) && (StringUtils.isEmpty(publicView.getValue())));
                dataSelectionDTO.setScreenname("FixedDollar");
                SaveViewLookUp saveViewLookUp = new SaveViewLookUp(dataSelectionDTO);
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
        DataSelectionLogic initCustDataSelection = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = initCustDataSelection.getRelationShipValues(projectionId, "customer", customerLevel, customerDescriptionMap);
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
        DataSelectionLogic dataSelectionDsLogic = new DataSelectionLogic();

        List<LevelDTO> reslistOne;
        reslistOne = dataSelectionDsLogic.getRelationShipValues(projectionId, "product", productLevel, productDescriptionMap);
        createHierarchyBasedOnHierarchyNo(selectedProductContainer, reslistOne, productLevel);
        selectedProduct.setContainerDataSource(selectedProductContainer);
        selectedProduct.setVisibleColumns(new Object[]{CommonConstant.DISPLAY_VALUE});
        selectedProduct.setColumnHeaders(new String[]{"Product Hierarchy Group Builder"});
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
        if ((ARMConstants.getAdjustmentSummary().equals(screenName) || adjustmentType.getValue() != null) && company.getValue() != null && businessUnit.getValue() != null) {
            int buscinessProcess = -1;
            if (!ARMConstants.getAdjustmentSummary().equals(screenName)) {
                buscinessProcess = ((HelperDTO) adjustmentType.getValue()).getId();
            }
            int glCompId = ARMUtils.getIntegerValue(company.getValue().toString());
            int bUnitCompSid = ARMUtils.getIntegerValue(businessUnit.getValue().toString());
            fromPeriod.removeAllItems();
            toPeriod.removeAllItems();
            configurePeriodDropDown(fromPeriod);
            configurePeriodDropDown(toPeriod);
            DataSelectionLogic.getDates(buscinessProcess, glCompId, bUnitCompSid, fromPeriod, toPeriod, true, selection);
            fromPeriod.select(selection.getStartDate());
            toPeriod.select(selection.getEndDate());
            if (ARMConstants.getPipelineAccrual().equalsIgnoreCase(selection.getAdjustmentType())
                    || ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(selection.getAdjustmentType()) || ARMConstants.getTransaction6().equalsIgnoreCase(selection.getAdjustmentType())
                    || ARMConstants.getTransaction7().equalsIgnoreCase(selection.getAdjustmentType())
                    || ARMConstants.getTransaction8().equalsIgnoreCase(selection.getAdjustmentType())) {
                toPeriod.removeAllItems();
                configurePeriodDropDown(toPeriod);
                toPeriod.setEnabled(false);
                periodView = ARMConstants.getSingelPeriodView();
            } else { // Added for GALUAT-711
                periodView = DataSelectionLogic.getPeriodViewForAdjustmentType(buscinessProcess, glCompId, bUnitCompSid);
                if (ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView)) {
                    toPeriod.removeAllItems();
                    configurePeriodDropDown(toPeriod);
                    DataSelectionLogic.getDates(buscinessProcess, glCompId, bUnitCompSid, toPeriod);
                    toPeriod.select(String.valueOf(fromPeriod.getValue()));
                    toPeriod.setEnabled(false);
                } else {
                    toPeriod.setEnabled(true);
                }
                selection.setCheckFlag(false);
            } //Ends here
        } else {
            fromPeriod.removeAllItems();
            toPeriod.removeAllItems();
        }

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

    @UiHandler("fromDate")
    public void fromPeriodDdlbVlaueChange(Property.ValueChangeEvent event) throws ParseException { // Added for GALUAT-711
        if ((ARMConstants.getAdjustmentSummary().equals(screenName) || adjustmentType.getValue() != null) && company.getValue() != null && businessUnit.getValue() != null) {
            if (ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView)) {
                toPeriod.select(String.valueOf(fromPeriod.getValue()));
                toPeriod.setEnabled(false);
            }
            loadAvailableProducts();
        }
        if (ARMConstants.getTransaction8().equalsIgnoreCase(screenName) && !(fromPeriod.getValue() == null || "0".equalsIgnoreCase(String.valueOf(fromPeriod.getValue()))) && logic.getVersionCount(fromPeriod.getItemCaption(fromPeriod.getValue()).replace(" ", "-"))) {
            fromPeriod.setValue(0);
            AbstractNotificationUtils.getErrorNotification(ARMUtils.SELECTION_CRITERIA,
                    ARMUtils.DATA_SELECTION_MESSAGE);
        }
        if (((toPeriod.getValue() != null) && toPeriod.isEnabled()) && dataSelectionDTO != null && fromPeriod.getValue() != null) {
            toPeriod.removeAllItems();
            configurePeriodDropDown(toPeriod);
            if (dataSelectionDTO.isCheckFlag()) {
                initialFromandToLoad();
            } else {
                valueChangeMethod();
            }
        }

    } //Ends here

    @UiHandler("toDate")
    public void toPeriodDdlbVlaueChange(Property.ValueChangeEvent event) {
        if (dataSelectionDTO != null) {
            if (dataSelectionDTO.isViewFlag()) {
                dataSelectionDTO.setCheckFlag(false);
            } else {
                if (((toPeriod.getValue() != null) && toPeriod.isEnabled()) && fromPeriod.getValue() != null) {
                    dataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
                }
            }

        }

    }

    public boolean lengthValidation() {
        return description.getValue().length() <= 200;
    }

    public void securityForAllScreens() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(com.stpl.app.utils.ConstantsUtils.USER_ID));

        final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, ARMUtils.FIXED_DOLLAR_ADJUSTMENT + ARMUtils.COMMA_CHAR + com.stpl.app.utils.ConstantsUtils.LANDING_SCREEN);
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.logic);
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
        final DataSelection other = (DataSelection) obj;
        return Objects.equals(this.logic, other.logic);
    }

    private boolean bindDataSelectionSaveViewValues() {
        try {
            String msgHeader = "No Search Criteria";
            String msg = "No data selection criteria were found. Please enter data selection criteria and try again. ";

            boolean value = false;
            if ((StringUtils.isEmpty(company.getValue() == null ? "" : company.getValue().toString()) || CommonLogic.checkInt(ARMUtils.getIntegerValue(company.getValue().toString())))
                    && (customerHierarchyLookup == null || CommonLogic.checkInt(customerHierarchyLookup.getHierarchyDto().getHierarchyId()))
                    && (productHierarchyLookup == null || CommonLogic.checkInt(productHierarchyLookup.getHierarchyDto().getHierarchyId()))
                    && (description.isVisible() && StringUtils.isBlank(String.valueOf(description.getValue())))
                    && (businessUnit.getValue() == null || CommonLogic.checkInt(ARMUtils.getIntegerValue(businessUnit.getValue().toString())))
                    && (deductionLevel.getValue() == null || CommonLogic.checkInt(((HelperDTO) deductionLevel.getValue()).getId()))
                    && (adjustmentType.getValue() == null || CommonLogic.checkInt(((HelperDTO) adjustmentType.getValue()).getId()))) {
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
            dataSelectionDTO.setProjectionName(StringUtils.EMPTY);
            dataSelectionDTO.setProjectionDescription(description.getValue());
            dataSelectionDTO.setCreatedBy(CommonLogic.parseStringToInteger(userId));
            if (dataSelectionDTO.getViewCreatedBy() == 0) {
                dataSelectionDTO.setViewCreatedBy(dataSelectionDTO.getCreatedBy());
            }
            dataSelectionDTO.setCreatedDate(new Date());
            dataSelectionDTO.setCustomerHierarchySid(customerHierarchyLookup == null ? NumericConstants.ZERO : customerHierarchyLookup.getHierarchyDto().getHierarchyId());
            dataSelectionDTO.setProductHierarchySid(productHierarchyLookup == null ? NumericConstants.ZERO : productHierarchyLookup.getHierarchyDto().getHierarchyId());
            dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerLevl));
            dataSelectionDTO.setProductHierarchyLevel(String.valueOf(productLevelVal));
            dataSelectionDTO.setCustomerHierarchyVersionNo(customerHierarchyLookup == null ? NumericConstants.ZERO : customerHierarchyLookup.getHierarchyDto().getVersionNo());
            dataSelectionDTO.setProductHierarchyVersionNo(productHierarchyLookup == null ? NumericConstants.ZERO : productHierarchyLookup.getHierarchyDto().getVersionNo());
            dataSelectionDTO.setCompanyMasterSid(company.getValue() != null ? ARMUtils.getIntegerValue(String.valueOf(company.getValue())) : NumericConstants.ZERO);
            dataSelectionDTO.setFromPeriod(fromPeriod.getValue() == null || "0".equals(String.valueOf(fromPeriod.getValue())) ? StringUtils.EMPTY : String.valueOf(fromPeriod.getValue()));   //Obtain from Admin Console
            dataSelectionDTO.setFromPeriodMonth(fromPeriod.getValue() != null || "0".equals(String.valueOf(fromPeriod.getValue())) ? fromPeriod.getItemCaption(fromPeriod.getValue()) : StringUtils.EMPTY);
            dataSelectionDTO.setFromDate(fromPeriod.getValue() == null || "0".equals(String.valueOf(fromPeriod.getValue())) ? CommonLogic.parseDate(StringUtils.EMPTY) : CommonLogic.parseDate(String.valueOf(fromPeriod.getValue())));
            if (ARMConstants.getPipelineAccrual().equalsIgnoreCase(screenName) || ARMConstants.getPipelineInventoryTrueUp().equalsIgnoreCase(screenName)
                    || ARMConstants.getTransaction6().equalsIgnoreCase(screenName)
                    || ARMConstants.getTransaction7().equalsIgnoreCase(screenName)
                    || ARMConstants.getTransaction8().equalsIgnoreCase(screenName)) {
                dataSelectionDTO.setToPeriod(dataSelectionDTO.getFromPeriod());
            } else {
                dataSelectionDTO.setFromPeriod(toPeriod.getValue() == null || "0".equals(String.valueOf(toPeriod.getValue())) ? StringUtils.EMPTY : String.valueOf(toPeriod.getValue()));   //Obtain from Admin Console
                dataSelectionDTO.setToDate(CommonLogic.parseDate(String.valueOf(toPeriod.getValue())));
            }
            dataSelectionDTO.setSaveFlag(false);
            dataSelectionDTO.setCustRelationshipBuilderSid(customerRelation.getValue() != null ? ARMUtils.getIntegerValue(String.valueOf(customerRelation.getValue())) : NumericConstants.ZERO);
            dataSelectionDTO.setProdRelationshipBuilderSid(productRelation.getValue() != null ? ARMUtils.getIntegerValue(String.valueOf(productRelation.getValue())) : NumericConstants.ZERO);
            dataSelectionDTO.setCustomerRelationshipVersionNo(customerVersionMap.get(ARMUtils.getIntegerValue(customerRelation.getValue().toString())));
            dataSelectionDTO.setProductRelationshipVersionNo(productVersionMap.get(ARMUtils.getIntegerValue(productRelation.getValue().toString())));
            dataSelectionDTO.setForecastingType(VariableConstants.ARM);
            if (ARMConstants.getAdjustmentSummary().equals(screenName)) {
                dataSelectionDTO.setForecastingType(ARMConstants.getAdjustmentSummary());
            }
            dataSelectionDTO.setAdjustmentType(screenName);
            dataSelectionDTO.setAdjustmentCaption(adjustmentType.getValue() != null ? adjustmentType.getItemCaption(adjustmentType.getValue()) : StringUtils.EMPTY);
            dataSelectionDTO.setRsContractSidList(new ArrayList<>(rsContractSids));
            dataSelectionDTO.setDeductionLevel(deductionLevel.getValue() != null ? ((HelperDTO) deductionLevel.getValue()).getId() : NumericConstants.ZERO);
            dataSelectionDTO.setSelectedCustomerContainer(selectedCustomerContainer);
            dataSelectionDTO.setSelectedDeductionContainer(selectedDeductionContainer);
            dataSelectionDTO.setSelectedProductContainer(selectedProductContainer);
            dataSelectionDTO.setCustomerHierarchyName(customerHierarchy.getValue() != null ? customerHierarchy.getValue() : StringUtils.EMPTY);
            dataSelectionDTO.setProductHierarchyName(productHierarchy.getValue() != null ? productHierarchy.getValue() : StringUtils.EMPTY);
            dataSelectionDTO.setBucompanyMasterSid(businessUnit.getValue() != null ? (int) businessUnit.getValue() : NumericConstants.ZERO);
            dataSelectionDTO.setDefaultCompanyMasterSid(logic.getCompanyId());
            value = true;
            return value;
        } catch (Exception ex) {
            LOGGER.error("Error in bindDataSelectionValues :", ex);
            return false;
        }
    }

    private void initialFromandToLoad() {
        Date fromDate;
        fromDate = dataSelectionDTO.getStartDate();
        if (fromPeriod.getValue().equals(NumericConstants.ZERO)) {

            DataSelectionLogic.getPeriods(fromDate, dataSelectionDTO.getEndDate(), toPeriod);
            toPeriod.select(0);

        } else {
            DataSelectionLogic.getPeriods(fromDate, dataSelectionDTO.getEndDate(), toPeriod);
            toPeriod.select(DataSelectionLogic.getPeriod(dataSelectionDTO.getDefaultToDate()));
        }
        dataSelectionDTO.setCheckFlag(false);

    }

    private void valueChangeMethod() throws ParseException {
        Date fromDate;
        if (fromPeriod.getValue().equals(NumericConstants.ZERO)) {
            fromDate = dataSelectionDTO.getStartDate();
            DataSelectionLogic.getPeriods(fromDate, dataSelectionDTO.getEndDate(), toPeriod);
            toPeriod.select(0);

        } else {
            fromDate = ARMUtils.getInstance().getDbDate().parse(String.valueOf(fromPeriod.getValue()));
            DataSelectionLogic.getPeriods(fromDate, dataSelectionDTO.getEndDate(), toPeriod);
            toPeriod.select(DataSelectionLogic.getPeriod(dataSelectionDTO.getEndDate()));
        }
    }

    @Override
    public void clearListView() {
        rsContractSids.clear();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
