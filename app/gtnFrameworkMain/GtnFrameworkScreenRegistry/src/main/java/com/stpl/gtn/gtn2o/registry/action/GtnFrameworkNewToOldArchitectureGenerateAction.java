package com.stpl.gtn.gtn2o.registry.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.TreeGrid;
import com.stpl.app.gtnforecasting.ui.ForecastUI;

public class GtnFrameworkNewToOldArchitectureGenerateAction
        implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

    GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkNewToOldArchitectureGenerateAction.class);

    @SuppressWarnings("unchecked")
    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        try {
            logger.info("doAction----------------------------------------------");
            final String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
            String uniqueId = UUID.randomUUID().toString().replaceAll("-", "_").substring(0, 12);
            List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();

            String fromPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_from").getStringCaptionFromV8ComboBox();
            String toPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_to").getStringCaptionFromV8ComboBox();
            String projectionName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_projectionName").getV8StringFromField();
            String description = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_projectionDescription").getV8StringFromField();

            GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent("forecastLandingScreen_customerHierarchy", componentId).getComponentData().getCustomData();

            String relationshipVersionNo = String.valueOf(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial_Forecasting_customerRelationshipVersion", componentId).getCaptionFromV8ComboBox());
            String hierarchyVersionNo = recordBean.getPropertyValueByIndex(6).toString();

            GtnWsRecordBean recordBeanProduct = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent("Commercial Forecasting_prodhierarchyName", componentId).getComponentData()
                    .getCustomData();
            String productRelationVersionNo = String.valueOf((GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent("Commercial_Forecasting_productRelationshipVersion", componentId).getCaptionFromV8ComboBox()));
            String productHierarchyVersionNo = recordBeanProduct.getPropertyValueByIndex(6).toString();

            String businessUnit = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_businessUnit").getCaptionFromV8ComboBox();

            String customerForecastLevel = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial_Forecasting_customerSelectionForecastLevel").getCaptionFromV8ComboBox();

            String customerLevel = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial_Forecasting_customerSelectionLevel").getCaptionFromV8ComboBox();

            List<Object> parametersForDataSelection = new ArrayList<>();
            parametersForDataSelection.add(fromPeriod);
            parametersForDataSelection.add(toPeriod);
            parametersForDataSelection.add(projectionName);
            parametersForDataSelection.add(description);
            parametersForDataSelection.add(relationshipVersionNo);
            parametersForDataSelection.add(hierarchyVersionNo);
            parametersForDataSelection.add(productRelationVersionNo);
            parametersForDataSelection.add(productHierarchyVersionNo);
            parametersForDataSelection.add(businessUnit);
            parametersForDataSelection.add(customerForecastLevel);
            parametersForDataSelection.add(customerLevel);

            List<GtnWsRecordBean> selectedCustomerList = null;
            List<GtnWsRecordBean> selectedProductList = null;

            selectedCustomerList = getSelectedList(actionParamList.get(1).toString(), componentId);
            selectedProductList = getSelectedList(actionParamList.get(2).toString(), componentId);

            GtnFrameworkForecastInputBean dto = getForecastDataSelectionDto(
                    gtnUIFrameWorkActionConfig.getActionParameterList(), selectedCustomerList, selectedProductList,
                    componentId);

            ForecastUI ui = new ForecastUI();
            ui.getContentForecasting(userId, uniqueId, parametersForDataSelection, dto);

        } catch (Exception ex) {
            logger.error("Error", ex);
        }
    }

    private List<GtnWsRecordBean> getSelectedList(String tableComponentId, String componentId)
            throws GtnFrameworkValidationFailedException {
        GtnUIFrameworkComponentData selectedTableComponentData = GtnUIFrameworkGlobalUI
                .getVaadinComponentData(tableComponentId, componentId);
        GtnFrameworkV8DualListBoxBean selectedDualListBoxBean = (GtnFrameworkV8DualListBoxBean) selectedTableComponentData
                .getCustomData();
        TreeGrid<GtnWsRecordBean> selectedRightTable = selectedDualListBoxBean.getRightTable();
        selectedRightTable.expand(selectedRightTable.getTreeData().getRootItems());
        List<GtnWsRecordBean> selectedValues = selectedRightTable.getTreeData().getRootItems();
        if (selectedValues == null || selectedValues.isEmpty()) {
            throw new GtnFrameworkValidationFailedException("Selected Table is Empty");
        }
        List<GtnWsRecordBean> selectedRecordList = new ArrayList<>(10);
        for (GtnWsRecordBean gtnWsRecordBean : selectedValues) {

            selectedRecordList.add(gtnWsRecordBean);
            addSelectedValues(selectedRightTable, gtnWsRecordBean, selectedRecordList);
        }
        return selectedRecordList;
    }

    private void addSelectedValues(TreeGrid<GtnWsRecordBean> rightTable, GtnWsRecordBean selectedvalues,
            List<GtnWsRecordBean> selectedList) {
        for (GtnWsRecordBean gtnWsRecordBean : rightTable.getTreeData().getChildren(selectedvalues)) {
            selectedList.add(gtnWsRecordBean);
            addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
        }
    }

    private GtnFrameworkForecastInputBean getForecastDataSelectionDto(List<Object> actionParamList,
            List<GtnWsRecordBean> selectedCustomerList, List<GtnWsRecordBean> selectedProductList, String componentId)
            throws Exception {

        GtnFrameworkForecastInputBean dto = new GtnFrameworkForecastInputBean();
        Date forecastEligibleDate = null;

        String hierarchyComponentId = actionParamList.get(3).toString();
        String relationshipComponentId = actionParamList.get(4).toString();
        GtnWsRecordBean customerRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(hierarchyComponentId).getComponentData().getCustomData();
        GtnWsRecordBean productRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(8).toString()).getComponentData().getCustomData();

        LocalDate date = (LocalDate) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(7).toString())
                .getFieldValue();
        if (date != null) {
            forecastEligibleDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        dto.setForecastEligibleDate(forecastEligibleDate);
        generateButtonMandatoryCheck(dto, actionParamList, customerRecordBean, productRecordBean, relationshipComponentId);
        String privateView = String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(22).toString(), componentId).getV8PopupFieldValue());
        if (privateView != "") {
            dto.setPrivateViewName(privateView);
        }
        String publicViewName = String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(23).toString(), componentId).getV8PopupFieldValue());
        if (publicViewName != "") {
            dto.setPublicViewName(publicViewName);
        }
        dto.setCustomerHierarchyBean(customerRecordBean);
        dto.setProductHierarchyBean(productRecordBean);
        dto.setSelectedCustomerList(selectedCustomerList);
        dto.setSelectedProductList(selectedProductList);

        dto.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
        String uniqueId = UUID.randomUUID().toString().replaceAll("-", "_").substring(0, 12);
        dto.setSessionId(uniqueId);
        dto.setUniqueId(uniqueId);

        return dto;
    }

    private void generateButtonMandatoryCheck(GtnFrameworkForecastInputBean dto, List<Object> actionParamList,
            GtnWsRecordBean customerRecordBean, GtnWsRecordBean productRecordBean, String relationshipComponentId)
            throws NumberFormatException, GtnFrameworkValidationFailedException {
        dto.setCompany(checkDDLBValues(Integer.parseInt(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(13).toString()).getCaptionFromV8ComboBox())));
        dto.setBusinessUnit(checkDDLBValues(Integer.parseInt(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(14).toString()).getCaptionFromV8ComboBox())));
        dto.setFromPeriod(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(15).toString()).getStringCaptionFromV8ComboBox());
        dto.setToPeriod(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(17).toString()).getStringCaptionFromV8ComboBox());
        dto.setCustomerHierarchySid(
                checkDDLBValues(Integer.valueOf(String.valueOf(customerRecordBean.getPropertyValueByIndex(7)))));
        dto.setCustomerHierarchyVersion(
                checkDDLBValues(Integer.parseInt(String.valueOf(customerRecordBean.getPropertyValueByIndex(6)))));
        dto.setCustomerRelationSid(checkDDLBValues(Integer.parseInt(String.valueOf(
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(relationshipComponentId).getCaptionFromV8ComboBox()))));
        dto.setCustomerRelationVersionNo(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(5).toString()).getCaptionFromV8ComboBox()))));
        dto.setCustomerHierarchyLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(6).toString()).getCaptionFromV8ComboBox()))));
        dto.setProductHierarchySid(
                checkDDLBValues(Integer.valueOf(String.valueOf(productRecordBean.getPropertyValueByIndex(7)))));
        dto.setProductHierarchyVersion(
                checkDDLBValues(Integer.parseInt(String.valueOf(productRecordBean.getPropertyValueByIndex(7)))));
        dto.setProductRelationSid(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(9).toString()).getCaptionFromV8ComboBox()))));
        dto.setProductRelationVersionNo(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(11).toString()).getCaptionFromV8ComboBox()))));
        dto.setProductHierarchyLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(10).toString()).getCaptionFromV8ComboBox()))));
        dto.setCustomerRelationLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(21).toString()).getCaptionFromV8ComboBox()))));
        dto.setProductRelationLevel(checkDDLBValues(Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(16).toString()).getCaptionFromV8ComboBox()))));
        dto.setProjectionName(String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(18).toString()).getV8StringFromField()));
        dto.setProjectionDescription(String.valueOf(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(19).toString()).getV8StringFromField()));
        dto.setCustomerHierarchyName(String.valueOf(customerRecordBean.getPropertyValueByIndex(0)));
        dto.setProductHierarchyName(String.valueOf(productRecordBean.getPropertyValueByIndex(0)));
        dto.setDeductionLevel(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(24).toString()).getCaptionFromV8ComboBox());
        dto.setDeductionValue(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParamList.get(24).toString()).getStringCaptionFromV8ComboBox());
        dto.setFrequency("");
                
    }

    private int checkDDLBValues(int value) throws GtnFrameworkValidationFailedException {
        if (value == 0) {
            throw new GtnFrameworkValidationFailedException("Generate Validation Exception");
        }
        return value;
    }

}
