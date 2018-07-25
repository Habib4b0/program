/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config.popups.privatePublicView;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.config.abstractConfig.GTNFrameworkAbstractComponent;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkAdjustmentDetailsStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GtnFrameworkPrivatePublicViewLookup extends GTNFrameworkAbstractComponent {

    public GtnUIFrameworkViewConfig getPrivateView() {
        GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
        GtnUIFrameworkViewConfig privateView = componentConfig.getViewConfig("PrivateLookup", "V003", false);
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        privateView.setGtnComponentList(componentList);
        addComponentList(privateView, componentConfig);
        return privateView;
    }

    private void addComponentList(GtnUIFrameworkViewConfig searchView, GtnFrameworkComponentConfigProvider componentConfig) {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        searchView.setGtnComponentList(componentList);
        addSearchCriteriaPanel(componentList, componentConfig);
        addButtonLayout(componentList, componentConfig);
        addResultPanel(componentList, componentConfig);
        addFooterButtonLayout(componentList, componentConfig);
    }

    private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig itemMasterSearchPanel = componentConfig.getPanelConfig("viewPanel",
                false, null);
        itemMasterSearchPanel.setComponentName("View Search");
        itemMasterSearchPanel.setAuthorizationIncluded(true);
        itemMasterSearchPanel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchPanel.setComponentHight(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchPanel.setMargin(true);
        itemMasterSearchPanel.setSpacing(true);
        componentList.add(itemMasterSearchPanel);
        addFieldLayout(componentList, componentConfig);
    }

    private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterSearchLayout = componentConfig.getGtnCssLayoutConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PUBLIC_VIEW_LAYOUT, true, "viewPanel",
                GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
        itemMasterSearchLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchLayout.setComponentHight(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchLayout.setMargin(true);
        itemMasterSearchLayout.setSpacing(true);
        componentList.add(itemMasterSearchLayout);
        GtnUIFrameworkComponentConfig itemMasterSearchLayout1 = componentConfig.getGtnCssLayoutConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PUBLIC_VIEW_LAYOUT1, true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PUBLIC_VIEW_LAYOUT,
                GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
        itemMasterSearchLayout1.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchLayout1.setComponentHight(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchLayout1.setMargin(true);
        itemMasterSearchLayout1.setSpacing(true);
        componentList.add(itemMasterSearchLayout1);
        addFieldComponent(componentList, componentConfig);
    }

    private void addViewName(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "View Name";
        String objectComponentName = GtnFrameworkCommonConstants.VIEW_NAME;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PUBLIC_VIEW_LAYOUT1);
    }

    private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        addViewName(componentList, componentConfig);
    }

    private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig itemMasterButtonLayout = componentConfig
                .getCssLayoutConfig(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PRIVATE_PUBLIC_VIEW_BUTTONLAYOUT, false, null);
        itemMasterButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
        itemMasterButtonLayout.setMargin(true);
        itemMasterButtonLayout.setSpacing(true);
        componentList.add(itemMasterButtonLayout);

        addSearchButtonComponent(componentList, componentConfig);
        addResetButtonComponent(componentList, componentConfig);
    }

    private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnSearch01layout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PRIVATE_PUBLIC_VIEW_BUTTONLAYOUT);
        componentList.add(searchBtnLayout);

        GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnSearch01",
                true, searchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        searchButtonConfig.setComponentName("SEARCH");
        searchButtonConfig.setAuthorizationIncluded(true);
        componentList.add(searchButtonConfig);

        List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

        GtnUIFrameWorkActionConfig searchValidationActionConfig = componentConfig
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
        searchValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.VIEW_NAME));

        GtnUIFrameWorkActionConfig alertActionConfig = componentConfig
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);

        List<Object> alertParamsList = new ArrayList<>();
        alertParamsList.add("Search Criteria ");
        alertParamsList.add("Please enter Search Criteria");

        alertActionConfig.setActionParameterList(alertParamsList);
        searchValidationActionConfig.setActionParameterList(
                Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)));
        searchActionConfigList.add(searchValidationActionConfig);

        GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
        loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

        loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_VIEW_SEARCH_RESULT_TABLE);
        loadDataTableActionConfig.setFieldValues(Arrays.asList(
                GtnFrameworkCommonConstants.VIEW_NAME));
        searchActionConfigList.add(loadDataTableActionConfig);

        GtnUIFrameWorkActionConfig notificationActionConfig = componentConfig
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
        notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_VIEW_SEARCH_RESULT_TABLE);
        searchActionConfigList.add(notificationActionConfig);

        searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

    }

    private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnReset01layout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PRIVATE_PUBLIC_VIEW_BUTTONLAYOUT);
        componentList.add(searchBtnLayout);

        GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnReset01",
                true, searchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        searchButtonConfig.setComponentName("RESET");
        searchButtonConfig.setAuthorizationIncluded(true);
        componentList.add(searchButtonConfig);
    }

    public void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig searchResultConfig = componentConfig.getUIFrameworkComponentConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_VIEW_SEARCH_RESULT_TABLE, true, GtnFrameworkAdjustmentDetailsStringConstants.RESULT_VIEW_LAYOUT,
                GtnUIFrameworkComponentType.PAGEDTABLE);
        searchResultConfig.setAuthorizationIncluded(true);
        searchResultConfig.setComponentName("Results");
        searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

        componentList.add(searchResultConfig);

        GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
                GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
                GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
                "AdjustmentDetails", "privatePublicViewSearchQuery");
        searchResults.setSinkItemPerPageWithPageLength(false);
        searchResults.setTableColumnDataType(new Class[]{String.class, String.class,
            Date.class, String.class, Date.class, String.class});

        searchResults.setTableVisibleHeader(GtnFrameworkAdjustmentDetailsStringConstants.getPrivatePublicColumnHeaders());
        searchResults.setTableColumnMappingId(GtnFrameworkAdjustmentDetailsStringConstants.getPrivatePublicPropertyIds());
        searchResults.setExtraColumn(new Object[]{"viewMasterSId"});
        searchResults.setExtraColumnDataType(new Class[]{Integer.class});
        searchResults.setCustomFilterConfigMap(getCVCustomFilterConfig());
        searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ADJUSTMENT_DETAILS);
        searchResults.setDoubleClickEnable(true);
        searchResultConfig.setGtnPagedTableConfig(searchResults);
    }

    private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterResultPanelConfig = componentConfig.getPanelConfig("ResultPanel",
                false, null);
        itemMasterResultPanelConfig.setComponentName("Results");
        itemMasterResultPanelConfig.setAuthorizationIncluded(true);
        componentList.add(itemMasterResultPanelConfig);

        addResultLayout(componentList, componentConfig);
    }

    private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig resultTableLayout = componentConfig.getHorizontalLayoutConfig(GtnFrameworkAdjustmentDetailsStringConstants.RESULT_VIEW_LAYOUT,
                true, "ResultPanel");
        resultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(resultTableLayout);

        addPagedTableComponent(componentList, componentConfig);
    }

    private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCVCustomFilterConfig() {

        Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customViewFilterConfigMap = new HashMap<>();
        String[] propertyIds = GtnFrameworkAdjustmentDetailsStringConstants.getFilterPropertyIds();
        String[] listNameArray = GtnFrameworkAdjustmentDetailsStringConstants.getFilterDbHeaders();
        for (int i = 0; i < propertyIds.length; i++) {
            GtnUIFrameworkPagedTableCustomFilterConfig cvCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
            cvCustomFilterConfig.setPropertId(propertyIds[i]);
            cvCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
            GtnUIFrameworkComponentConfig cvCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
            cvCustomFilterComponentConfig.setComponentId("customFilterComboBox");
            cvCustomFilterComponentConfig.setComponentName("customFilterComboBox");
            cvCustomFilterComponentConfig.setGtnComboboxConfig(GtnFrameworkComponentConfigProvider
                    .getInstance().getComboBoxConfig(listNameArray[i],
                            GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
                            + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX));
            cvCustomFilterComponentConfig.getGtnComboboxConfig()
                    .setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
            cvCustomFilterConfig.setGtnComponentConfig(cvCustomFilterComponentConfig);
            customViewFilterConfigMap.put(cvCustomFilterConfig.getPropertId(), cvCustomFilterConfig);

        }
        return customViewFilterConfigMap;
    }

    private void addFooterButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig itemMasterButtonLayout = componentConfig
                .getCssLayoutConfig(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PRIVATE_PUBLIC_VIEW_FOOTER_BUTTONLAYOUT, false, null);
        itemMasterButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
        itemMasterButtonLayout.setMargin(true);
        itemMasterButtonLayout.setSpacing(true);
        componentList.add(itemMasterButtonLayout);

        addSelectButtonComponent(componentList, componentConfig);
        addCloseButtonComponent(componentList, componentConfig);
    }

    private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnSelect01layout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PRIVATE_PUBLIC_VIEW_FOOTER_BUTTONLAYOUT);
        componentList.add(searchBtnLayout);

        GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnSelect01",
                true, searchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        searchButtonConfig.setComponentName("SELECT");
        searchButtonConfig.setAuthorizationIncluded(true);
        componentList.add(searchButtonConfig);
        List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
        selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
        selectAction.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_VIEW_SEARCH_RESULT_TABLE);
        selectAction.addActionParameter(GtnFrameworkCommonConstants.PUBLIC_VIEWS);
        selectAction.addActionParameter(
                Arrays.asList(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_NAME, GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_NAME));
        selectAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.PUBLIC_VIEWS, GtnFrameworkCommonConstants.WORKFLOW_ID));
        GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
        closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
        closeAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_CFP_VIEW);
        selectActionConfigList.add(selectAction);
        selectActionConfigList.add(closeAction);
        searchButtonConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);
    }

    private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnClose01layout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_PRIVATE_PUBLIC_VIEW_FOOTER_BUTTONLAYOUT);
        componentList.add(searchBtnLayout);

        GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnClose01",
                true, searchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        searchButtonConfig.setComponentName("CLOSE");
        searchButtonConfig.setAuthorizationIncluded(true);
        componentList.add(searchButtonConfig);
        List<GtnUIFrameWorkActionConfig> closeActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig resetActionConfig = componentConfig.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
        List<Object> paramsList = new ArrayList<>();
        Map<String, Object> resetMap = new HashMap<>(1);
        resetMap.put(GtnFrameworkCommonConstants.PUBLIC_VIEWS, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetActionConfig.addActionParameter(resetMap);
        resetActionConfig.addActionParameter(paramsList);
        GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
        closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
        closeAction.addActionParameter("V003");
        closeActionConfigList.add(closeAction);
        closeActionConfigList.add(resetActionConfig);
        searchButtonConfig.setGtnUIFrameWorkActionConfigList(closeActionConfigList);
    }
}
