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
import com.stpl.app.arm.utils.DataSelectionUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
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
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.logic.HierarchyString;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryReportDataSelection extends AbstractDataSelection {

    private static final Logger LOGGER = Logger.getLogger(BalanceSummaryReportDataSelection.class);
    DataSelectionLogic logic = new DataSelectionLogic();
    DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();
    DataSelection dataSelection;
    Map<Integer, List<LevelDTO>> levelMap = new HashMap<>();
    List<Integer> customerSidList = new ArrayList<>();
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
    String tempCCPTableName = StringUtils.EMPTY;
    String periodView = StringUtils.EMPTY;

    public BalanceSummaryReportDataSelection(String screenName, SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        this.screenName = screenName;
        configureFields();
        securityForAllScreens();
    }

    @UiHandler("companyDDLB")
    public void companyDdlbVlaueChange(Property.ValueChangeEvent event) {
        Object val = event.getProperty().getValue();

        if (val == null) {
            dataSelectionDTO.setCompanyMasterSid(0);
        } else {
            dataSelectionDTO.setCompanyMasterSid((int) val);
        }
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
        loadFromAndTo(dataSelectionDTO);
        loadAvailableProducts();
    }

    @Override
    public void loadCustRelationAndLevel(int custHierSid, CustomTextField.ClickEvent event) {
        logic.loadCustomerRelation(customerRelation, custHierSid);
        logic.loadCustoProdLevels(customerLevel, custHierSid);
        customerBeanList.removeAll(customerBeanList);
    }

    @Override
    public void loadProdRelationAndLevel(int prodHierSid, int glComp, CustomTextField.ClickEvent event) {
        logic.loadProductRelation(productRelation, prodHierSid, glComp);
        logic.loadCustoProdLevels(productLevel, prodHierSid);
        productBeanList.removeAll(productBeanList);
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

                    String relationshipSid = String.valueOf(customerRelation.getValue());
                    DataSelectionLogic logicVal = new DataSelectionLogic();
                    String[] val = selectedLevel.split(" ");
                    forecastLevel = Integer.parseInt(val[1]);
                    LevelDTO tempDto = innerCustLevels.get(forecastLevel - 1);
                    if (tempDto.getLevel() != null) {
                        levelName = tempDto.getLevel();
                        tempDto.getLevelNo();
                    }

                    custVlues = logicVal.loadCustomerInnerLevel(relationshipSid, tempDto.getLevelNo(), customerHierarchyLookup.getHierarchyDto().getHierarchyId(), new ArrayList<>(rsContractSids),
                            customerDescriptionMap, tempCCPTableName);
                    availableCustomerContainer.addAll(custVlues);
                }

            }
            availableCustomer.setContainerDataSource(availableCustomerContainer);
            Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE};
            availableCustomer.setVisibleColumns(obj);
            String[] str = new String[]{levelName};
            availableCustomer.setColumnHeaders(str);
            availableCustomer.setSelectable(true);
            availableCustomer.setFilterBarVisible(true);
            availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
            availableCustomer.setStyleName("filtertable");
        } catch (Exception ex) {
            LOGGER.error(ex + " Customer level ValueChangeListener ");
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
            LOGGER.error("Error in customerHierarchyCloseListener :"+e);
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
    public void productValueChange(int glComp) {
        super.productValueChange(glComp);
        innerProdLevels = logic.loadCustomerForecastLevel(productHierarchyLookup.getHierarchyDto().getHierarchyId(), productHierarchyLookup.getHierarchyDto().getHierarchyName());
    }

    @Override
    public void customerRelationValueChange(Object value) {
        if (custLevelListenerFlag) {
            if (value != null) {
                try {
                    final DataSelectionLogic logicVal = new DataSelectionLogic();
                    availableCustomer.removeAllItems();
                    availableCustomerContainer.removeAllItems();
                    selectedCustomer.removeAllItems();
                    selectedCustomerContainer.removeAllItems();
                    customerLevel.select(null);
                    customerDescriptionMap = logicVal.getLevelValueMap(String.valueOf(customerRelation.getValue()));
                    customerBeanList.removeAll(customerBeanList);
                } catch (Exception ex) {
                    LOGGER.error(ex + " in customerRelation value change");
                }
            } else if (value == null || (value != null && GlobalConstants.getSelectOne().equals(String.valueOf(value)))) {
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
                    DataSelectionLogic logicVal = new DataSelectionLogic();
                    availableProduct.removeAllItems();
                    availableProductContainer.removeAllItems();
                    selectedProduct.removeAllItems();
                    selectedProductContainer.removeAllItems();
                    productLevel.select(null);
                    productDescriptionMap = logicVal.getLevelValueMap(String.valueOf(productRelation.getValue()));
                    productBeanList.removeAll(productBeanList);
                } catch (Exception ex) {
                    LOGGER.error(ex + " in customerRelation value change");
                }
            } else if (value == null || (value != null && GlobalConstants.getSelectOne().equals(String.valueOf(value)))) {
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

    @Override
    public void loadAvailableProducts() {
        try {
            availableProductContainer.removeAllItems();
            availableProduct.removeAllItems();
            int custforecastLevel = 0;
            int prodforecastLevel = 0;
            boolean isNdc = false;
            List<LevelDTO> innerLevelValues = null;
            String levelName = "Level";
            String custSelectedLevel;
            LevelDTO custTempDto;
            if (customerLevel.getValue() != null) {
                Object value = customerLevel.getItemCaption(customerLevel.getValue());
                custSelectedLevel = String.valueOf(value);
                String[] val = custSelectedLevel.split(" ");
                custforecastLevel = Integer.parseInt(val[1]);
                custTempDto = innerCustLevels.get(custforecastLevel - 1);
            } else {
                custTempDto = null;
            }
            String selectedLevel = productLevel.getValue() == null ? StringUtils.EMPTY : productLevel.getValue().toString();
            if ((selectedLevel != null && !selectedLevel.equalsIgnoreCase(StringUtils.EMPTY) && !ARMUtils.NULL.equals(selectedLevel) && !GlobalConstants.getSelectOne().equals(selectedLevel)
                    && productRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(productRelation.getValue())) && (productRelation.getValue() != null && !GlobalConstants.getSelectOne().equals(productRelation.getValue()) && !innerProdLevels.isEmpty())) {

                String prodRelationshipSid = String.valueOf(productRelation.getValue());
                String custRelSid = String.valueOf(customerRelation.getValue());
                DataSelectionLogic logicVal = new DataSelectionLogic();
                prodforecastLevel = Integer.parseInt(selectedLevel);
                LevelDTO tempDto = innerProdLevels.get(prodforecastLevel - 1);
                if (tempDto.getLevel() != null) {
                    levelName = tempDto.getLevel();
                }

                if ((tempDto.getLevel() != null && (ARMUtils.NDC.equalsIgnoreCase(tempDto.getLevel()) || "Item".equalsIgnoreCase(tempDto.getLevel()) || ARMUtils.PRODUCT.equalsIgnoreCase(tempDto.getLevel()))) && ARMUtils.ITEM_MASTER.equals(tempDto.getTableName())) {

                    isNdc = true;
                } else {
                    isNdc = false;
                }

                int businessUnitVal = 0;
                try {
                    businessUnitVal = Integer.valueOf(businessUnit.getValue().toString());
                } catch (NullPointerException e) {
                    LOGGER.error(e + " business Unit Ddlb might not have been selected");
                } catch (Exception e) {
                    LOGGER.error("Error in businessUnit :"+e);
                }
                int glCompId = 0;
                try {
                    glCompId = Integer.valueOf(company.getValue().toString());
                } catch (NullPointerException e) {
                    LOGGER.error(e + " Company Ddlb might not have been selected");
                } catch (Exception e) {
                    LOGGER.error("Error in glCompid :"+e);
                }
                customerSidList.clear();
                getChildIds(selectedCustomerContainer, customerSidList);

                innerLevelValues = logicVal.loadProductInnerLevel(prodRelationshipSid, tempDto.getLevelNo(), productHierarchyLookup.getHierarchyDto().getHierarchyId(), new ArrayList<>(rsContractSids),
                        customerSidList, businessUnitVal, glCompId, isNdc, custRelSid, custTempDto != null ? custTempDto.getLevelNo() : 0, customerHierarchyLookup.getHierarchyDto().getHierarchyId(), productDescriptionMap);
                availableProductContainer.addAll(innerLevelValues);
                availableProduct.setContainerDataSource(availableProductContainer);
                if (isNdc) {
                    Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE, "form", "strength"};
                    availableProduct.setVisibleColumns(obj);
                    String[] value = new String[]{ARMUtils.NDC, "Form", "Strength"};
                    availableProduct.setColumnHeaders(value);
                } else {
                    Object[] obj = new Object[]{CommonConstant.DISPLAY_VALUE};
                    availableProduct.setVisibleColumns(obj);
                    String[] value = new String[]{levelName};
                    availableProduct.setColumnHeaders(value);
                }
                availableProduct.setFilterBarVisible(true);
                availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
                availableProduct.setStyleName("filtertable");
            }
        } catch (Exception ex) {
            LOGGER.error("Error in loadAvailableProducts :"+ex);
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
            DataSelectionLogic logicVal = new DataSelectionLogic();
            if (availableCustomer.getValue() != null) {
                int forecastLevel = Integer.parseInt(customerLevel.getValue().toString());
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
                            newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap);
                        }

                        newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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
                            newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap);
                        }

                        newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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
                    newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(hierarchyNos), customerDescriptionMap);

                    newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);
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

                    newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(customerLevel.getValue())), customerDescriptionMap);

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
                            if (customerBeanList.isEmpty() || !customerBeanList.contains(newLevel.getRelationShipBuilderId())) {
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
            LOGGER.error(e);
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
                customerBeanList.removeAll(customerBeanList);
                List<LevelDTO> selectedValueItem = selectedCustomerContainer.getItemIds();
                for (LevelDTO dto : selectedValueItem) {
                    customerBeanList.add(dto.getRelationshipLevelSid());
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
                        "No Level was selected to move. Please try again. ");
            }
        } catch (Exception ex) {
            LOGGER.error("Error in moveRightCustomersButtonLogic :"+ex);
        }
    }

    @Override
    public void moveLeftProductsButtonLogic() {
        try {
            DataSelectionLogic logicVal = new DataSelectionLogic();
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
                            newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap);
                        }
                        newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
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
                            newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap);
                        }
                        newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
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
                    newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(hierarchyNos), productDescriptionMap);
                    newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
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
                    newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue())), productDescriptionMap);
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
            LOGGER.error("Error in moveLeftProductsButtonLogic :"+e);
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
                productBeanList.removeAll(productBeanList);
                List<LevelDTO> productListValue = selectedProductContainer.getItemIds();
                for (LevelDTO dto : productListValue) {
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
    public void moveAllProductsButtonLogic() {
        try {
            DataSelectionLogic logicVal = new DataSelectionLogic();
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
                                newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap);
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
                                newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap);
                            }
                            newChildLevels = logicVal.getChildLevelsWithHierarchyNo(currentHierarchyNo, CommonLogic.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]), productDescriptionMap);
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
                                LOGGER.debug("TempHNo  " + tempHNo);
                            }

                        }
                        if (!uncommonValues.isEmpty()) {
                            newParentLevels = logicVal.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), productDescriptionMap);
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
            LOGGER.error("Error in movemoveLeftProductsButtonLogic"+e);
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
                                    newParentLevels = logicBtn.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap);
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
                                newParentLevels = logicBtn.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap);
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
                        newParentLevels = logicBtn.getParentLevelsWithHierarchyNo(CommonLogic.stringListToString(uncommonValues), customerDescriptionMap);
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
            LOGGER.error("Error in moveAllCustomersButtonLogic :"+e);
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
                    || rsContractSids.isEmpty() || selectedCustomerContainer.size() == 0 || selectedProductContainer.size() == 0
                    || selectedDeductionContainer.size() == 0)) {
//                    || calculationProfile.getValue().isEmpty()
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
                dataSelectionDTO.setProjectionName(StringUtils.EMPTY);
                dataSelectionDTO.setProjectionDescription(StringUtils.EMPTY);
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
                dataSelectionDTO.setCompanyMasterSid(Integer.valueOf(company.getValue().toString()));
                dataSelectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));   //Obtain from Admin Console
                dataSelectionDTO.setFromPeriodMonth(fromPeriod.getItemCaption(fromPeriod.getValue()));
                dataSelectionDTO.setFromDate(CommonLogic.parseDate(String.valueOf(fromPeriod.getValue())));
                dataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
                dataSelectionDTO.setToDate(CommonLogic.parseDate(String.valueOf(toPeriod.getValue())));
                dataSelectionDTO.setSaveFlag(false);
                dataSelectionDTO.setCustRelationshipBuilderSid(Integer.valueOf(customerRelation.getValue().toString()));
                dataSelectionDTO.setProdRelationshipBuilderSid(Integer.valueOf(productRelation.getValue().toString()));
                dataSelectionDTO.setForecastingType("Balance Summary Report");
                dataSelectionDTO.setAdjustmentType(screenName);
                dataSelectionDTO.setAdjustmentCaption(summaryTypeDdlb.getItemCaption(summaryTypeDdlb.getValue()));
                dataSelectionDTO.setRsContractSidList(new ArrayList<>(rsContractSids));
                dataSelectionDTO.setDeductionLevel(((HelperDTO) deductionLevel.getValue()).getId());
                dataSelectionDTO.setSelectedCustomerContainer(selectedCustomerContainer);
                dataSelectionDTO.setSelectedDeductionContainer(selectedDeductionContainer);
                dataSelectionDTO.setSelectedProductContainer(selectedProductContainer);
                dataSelectionDTO.setCustomerHierarchyName(customerHierarchy.getValue());
                dataSelectionDTO.setProductHierarchyName(productHierarchy.getValue());
                dataSelectionDTO.setBucompanyMasterSid((int) businessUnit.getValue());
                dataSelectionDTO.setSummaryType(summaryTypeDdlb.getItemIds());
                value = true;
            }
            return value;
        } catch (Exception ex) {
            LOGGER.error("Error in bindDataSelectionValues :"+ex);
            return false;
        }
    }

    private final CustomNotification notifier = new CustomNotification();

    class CustomNotification extends AbstractNotificationUtils {

        String buttonName;

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName) {
                switch (buttonName) {
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
            this.buttonName = buttonName;
        }

    }

    @Override
    public void generateButtonLogicForScreens() {
        try {
            LOGGER.debug(" generateButtonLogicForScreens ");
//F
            if (saveDataSelectionValues()) {
                sessionDTO.setWorkFlow(false);
                selection.setSessionDTO(sessionDTO);
                BalanceSummaryReportWindow form = new BalanceSummaryReportWindow(selection, dataSelectionDTO, sessionDTO);
                getUI().addWindow(form);

            }
        } catch (IllegalArgumentException | NullPointerException ex) {
            LOGGER.error("Error in generateButtonLogicForScreens :"+ex);
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
    Window.CloseListener closeListener = new Window.CloseListener() {

        @Override
        public void windowClose(Window.CloseEvent e) {
            try {
                ViewSearchLookUp lookUP = (ViewSearchLookUp) e.getWindow();
                if (lookUP.isSelected()) {
                    customerHierarchyLookup = new HierarchyLookup();
                    productHierarchyLookup = new HierarchyLookup();
                    dataSelectionDTO.setViewType(lookUP.getCaption());
                    dataSelectionDTO.setViewFlag(Boolean.TRUE);
                    view.setValue(lookUP.getViewDTO().getViewName());
                    dataSelectionDTO.setViewName(lookUP.getViewDTO().getViewName());
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
                LOGGER.error("Error in closeListener :" + ex);
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
                setDeductionTree(levelKeys);
                customerLevel.select(null);
                productLevel.select(null);

            } else {
                AbstractNotificationUtils.getErrorNotification("Error",
                        "No Level was selected to move. Please select and try again.");
            }
        } catch (Exception ex) {
            LOGGER.error("Error in moveLeftToRightDeductions :"+ex);
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
        hierarchyKeys.clear();
        Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, rsContractSids, hierarchyKeys);
        selectedDeductionContainer.removeAllItems();
        setDeductionTree(levelKeys);
        selectedLevelIds.clear();
        customerLevel.select(null);
        productLevel.select(null);
    }

    public void configureFields() {
        adjustmentType.setVisible(false);
        description.setVisible(false);
        adjustmentTypeLabel.setVisible(false);
        descriptionLabel.setVisible(false);
        panel1.setCaption("Selection Criteria");
        view.addStyleName("searchText");
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
                } catch (PortalException | SystemException ex) {
                    java.util.logging.Logger.getLogger(DataSelection.class.getName()).log(Level.SEVERE, null, ex);
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
                    dataSelectionDTO.setAdjustmentType(screenNameSum);
                    dataSelectionDTO.setAdjustmentCaption(summaryTypeDdlb.getItemCaption(summaryTypeDdlb.getValue()));
                    dataSelectionDTO.setAdjustmentId(dto.getId());
                    loadFromAndTo(dataSelectionDTO);
                }

            }
        });

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

                sessionDTO.setUserId(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID))));
                sessionDTO.setCurrentTableNames(QueryUtils.createTempTables("CCP_HIERARCHY", sessionDTO.getProjectionId(), sessionDTO.getUserId().toString(), sessionDTO.getSessionId().toString()));
                getCustTopLevelName();

                (new QueryUtils()).ccpHierarchyInsert(sessionDTO.getCurrentTableNames(), dataSelectionDTO, selectedCustomerContainer.getItemIds(), selectedProductContainer.getItemIds(), topLevelName, Boolean.FALSE);
                logic.saveCcp(sessionDTO.getCurrentTableNames().get("ST_CCP_HIERARCHY"), String.valueOf(projectionIdValue));
                logic.saveAdjustmentMaster(dataSelectionDTO);

                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            LOGGER.error("Error in saveDataSelectionValues :"+ex);
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
            if (lengthValidation() && bindDataSelectionSaveViewValues()) {
                List<LevelDTO> customerList = selectedCustomerContainer.getItemIds();
                List<LevelDTO> productList = selectedProductContainer.getItemIds();
                List<String> customerListEndSids = DataSelectionUtils.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
                List<String> productListEndSids = DataSelectionUtils.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
                dataSelectionDTO.setCustomerList(customerList);
                dataSelectionDTO.setProductList(productList);
                dataSelectionDTO.setCustomerEndLevelList(customerListEndSids);
                dataSelectionDTO.setProductEndLevelList(productListEndSids);
                dataSelectionDTO.setAddUpdateFlag(StringUtils.isEmpty(view.getValue()));
                dataSelectionDTO.setScreenname("BSR");
                SaveViewLookUp saveViewLookUp = new SaveViewLookUp(dataSelectionDTO);
                getUI().addWindow(saveViewLookUp);
            }
        } catch (Exception ex) {
            LOGGER.error("Error in saveViewLogic :"+ex);
        }
    }
    
    public boolean lengthValidation() {
        return description.getValue().length() <= 200;
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

        return (LevelDTO) targetItem.getBean();
    }

    private void setViewDetails(DataSelectionDTO dto,ViewDTO viewDTO) {
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
            fromPeriod.select(getPeriod(viewDTO.getFromPeriod()));
            toPeriod.select(getPeriod(viewDTO.getToPeriod()));
            summaryTypeDdlb.select(adjItemToAdd);
            deductionLevel.select(dto.getDeductionLevel() == 0 ? null : new HelperDTO(dto.getDeductionLevel(), HelperListUtil.getInstance().getIdDescMap().get(dto.getDeductionLevel())));
            rsContractSids.clear();
            rsContractSids.addAll(logic.getRsContractSids(dto.getProjectionId()));
            hierarchyKeys.clear();
            Map<String, DeductionLevelDTO> levelKeys = logic.getDeductionTree(selectedLevelIds, rsContractSids, hierarchyKeys);
            selectedDeductionContainer.removeAllItems();
            setDeductionTree(levelKeys);
            customerHierarchy.setValue(dto.getCustomerHierarchyName());
            
            logic.loadCustomerRelation(customerRelation, dto.getCustomerHierarchySid());
            customerRelation.select(dto.getCustRelationshipBuilderSid());
            loadCustomerLevel();
            logic.loadCustoProdLevels(customerLevel, dto.getCustomerHierarchySid());

            customerDescriptionMap = logic.getLevelValueMap(String.valueOf(dto.getCustRelationshipBuilderSid()));
            customerLevel.select(dto.getCustomerHierarchyLevel());
            initializeCustomerHierarchy(dto.getProjectionId(), dto.getCustomerHierarchyLevel().isEmpty() ? 0 : Integer.valueOf(dto.getCustomerHierarchyLevel()));
            
            productHierarchy.setValue(dto.getProductHierarchyName());
            logic.loadProductRelation(productRelation, dto.getProductHierarchySid(), dto.getCompanyMasterSid());
            productRelation.select(dto.getProdRelationshipBuilderSid());
            loadProductLevel();
            logic.loadCustoProdLevels(productLevel, dto.getProductHierarchySid());
            productLevel.select(dto.getProductHierarchyLevel());
            productDescriptionMap = logic.getLevelValueMap(String.valueOf(dto.getProdRelationshipBuilderSid()));
            initializeProductHierarchy(dto.getProjectionId(), dto.getProductHierarchyLevel().isEmpty() ? 0 : Integer.valueOf(dto.getProductHierarchyLevel()));
        } catch (Property.ReadOnlyException | NumberFormatException e) {
            LOGGER.error("Error in setViewDetails :"+e);
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
            DataSelectionLogic.getDates(val, glCompId, bUnitCompSid, fromPeriod, toPeriod, true,selection);
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

    @UiHandler("fromDate")
    public void fromPeriodDdlbVlaueChange(Property.ValueChangeEvent event) { // Added for GALUAT-711
        if ((summaryTypeDdlb.getValue() != null) && company.getValue() != null && businessUnit.getValue() != null) {
            if (ARMConstants.getSingelPeriodView().equalsIgnoreCase(periodView)) {
                toPeriod.select(String.valueOf(fromPeriod.getValue()));
                toPeriod.setEnabled(false);
            }
            loadAvailableProducts();
        }
    } //Ends here

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
            try {
                final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, ARMUtils.BALANCE_SUMMARY_REPORT + ConstantUtil.COMMA + com.stpl.app.utils.ConstantsUtils.LANDING_SCREEN);
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
            } catch (PortalException | SystemException ex) {
                LOGGER.error("Error in securityForAllScreens :"+ex);
            } 

        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    private boolean bindDataSelectionSaveViewValues() {
        try {
            String msgHeader = "No Search Criteria";
            String msg = "No data selection criteria were found. Please enter data selection criteria and try again. ";

            boolean value = false;
            if ((StringUtils.isEmpty(company.getValue() == null ? "" : company.getValue().toString()) || CommonLogic.checkInt(Integer.valueOf(company.getValue().toString())))
                    && (customerHierarchyLookup == null || CommonLogic.checkInt(customerHierarchyLookup.getHierarchyDto().getHierarchyId()))
                    && (productHierarchyLookup == null || CommonLogic.checkInt(productHierarchyLookup.getHierarchyDto().getHierarchyId()))
                    && (description.isVisible() && StringUtils.isBlank(String.valueOf(description.getValue())))
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
            dataSelectionDTO.setProjectionName(StringUtils.EMPTY);
            dataSelectionDTO.setProjectionDescription(description.getValue());
            dataSelectionDTO.setCreatedBy(CommonLogic.parseStringToInteger(userId));
            if (dataSelectionDTO.getViewCreatedBy() == 0) {
                dataSelectionDTO.setViewCreatedBy(dataSelectionDTO.getCreatedBy());
            }
            dataSelectionDTO.setCreatedDate(new Date());
            dataSelectionDTO.setCustomerHierarchySid(customerHierarchyLookup == null ? NumericConstants.ZERO : customerHierarchyLookup.getHierarchyDto().getHierarchyId());
            dataSelectionDTO.setProductHierarchySid(productHierarchyLookup == null ? NumericConstants.ZERO : productHierarchyLookup.getHierarchyDto().getHierarchyId());
            dataSelectionDTO.setCustomerHierarchyLevel("" + customerLevl);
            dataSelectionDTO.setProductHierarchyLevel("" + productLevelVal);
            dataSelectionDTO.setCustomerHierarchyVersionNo(customerHierarchyLookup == null ? NumericConstants.ZERO : customerHierarchyLookup.getHierarchyDto().getVersionNo());
            dataSelectionDTO.setProductHierarchyVersionNo(productHierarchyLookup == null ? NumericConstants.ZERO : productHierarchyLookup.getHierarchyDto().getVersionNo());
            dataSelectionDTO.setCompanyMasterSid(company.getValue() != null ? Integer.valueOf(String.valueOf(company.getValue())) : NumericConstants.ZERO);
            dataSelectionDTO.setFromPeriod(fromPeriod.getValue() == null || "0".equals(String.valueOf(fromPeriod.getValue())) ? StringUtils.EMPTY : String.valueOf(fromPeriod.getValue()));   //Obtain from Admin Console
            dataSelectionDTO.setFromPeriodMonth(fromPeriod.getValue() != null || "0".equals(String.valueOf(fromPeriod.getValue())) ? fromPeriod.getItemCaption(fromPeriod.getValue()) : StringUtils.EMPTY);
            dataSelectionDTO.setFromDate(CommonLogic.parseDate(String.valueOf(fromPeriod.getValue())));
            dataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
            LOGGER.info("-----------------" + String.valueOf(toPeriod.getValue()));
            dataSelectionDTO.setToDate("0".equals(String.valueOf(toPeriod.getValue())) ? null : CommonLogic.parseDate(String.valueOf(toPeriod.getValue())));
            dataSelectionDTO.setSaveFlag(false);
            dataSelectionDTO.setCustRelationshipBuilderSid(customerRelation.getValue() != null ? Integer.valueOf(String.valueOf(customerRelation.getValue())) : NumericConstants.ZERO);
            dataSelectionDTO.setProdRelationshipBuilderSid(productRelation.getValue() != null ? Integer.valueOf(String.valueOf(productRelation.getValue())) : NumericConstants.ZERO);
            dataSelectionDTO.setForecastingType(VariableConstants.ARM);
            dataSelectionDTO.setForecastingType("Balance Summary Report");
            dataSelectionDTO.setAdjustmentType(screenName);
            dataSelectionDTO.setRsContractSidList(new ArrayList<>(rsContractSids));
            dataSelectionDTO.setDeductionLevel(deductionLevel.getValue() != null ? ((HelperDTO) deductionLevel.getValue()).getId() : NumericConstants.ZERO);
            dataSelectionDTO.setSelectedCustomerContainer(selectedCustomerContainer);
            dataSelectionDTO.setSelectedDeductionContainer(selectedDeductionContainer);
            dataSelectionDTO.setSelectedProductContainer(selectedProductContainer);
            dataSelectionDTO.setCustomerHierarchyName(customerHierarchy.getValue() != null ? customerHierarchy.getValue() : StringUtils.EMPTY);
            dataSelectionDTO.setProductHierarchyName(productHierarchy.getValue() != null ? productHierarchy.getValue() : StringUtils.EMPTY);
            dataSelectionDTO.setBucompanyMasterSid(businessUnit.getValue() != null ? (int) businessUnit.getValue() : NumericConstants.ZERO);
            dataSelectionDTO.setDefaultCompanyMasterSid(logic.getCompanyId());
            dataSelectionDTO.setAdjustmentCaption(summaryTypeDdlb.getItemCaption(summaryTypeDdlb.getValue()));
            dataSelectionDTO.setSummaryType(summaryTypeDdlb.getItemIds());
            value = true;
            return value;
        } catch (NumberFormatException | ParseException ex) {
            LOGGER.error("Error in bindDataSelectionValues :" + ex);
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
}
