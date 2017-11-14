package com.stpl.gtn.gtn2o.ui.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkForecastReturnDsSearchTableLogic;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkForecastReturnFromToPeriodAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkForecastReturnModeChangeAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkForecastReturnRefreshAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkForecastReturnsStartEndPeriodAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkResetRelationValuesAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameWorkForecastReturnSearchAlertAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameWorkFrequencyComboBoxLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkForecastReturnMassUpdateAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkForecastReturnDataSelectionGenerateAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkForecastReturnDeleteFileAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkForecastReturnEditAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkForecastReturnPublicPrivateViewSaveAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkForecastReturnSaveAndUpdateAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkForecastReturnSaveViewButtonAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkForecastReturnSubmitAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkForecastReturnViewAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkForecastReturnViewDeleteAction;
import com.stpl.gtn.gtn2o.ui.action.crud.GtnFrameworkReturnsDataSelectionFieldsSaveAction;
import com.stpl.gtn.gtn2o.ui.action.duallistbox.GtnFrameworkReturnsDualListBoxRightTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.duallistbox.GtnFrameworkReturnsDualListBoxRightTableRefreshAction;
import com.stpl.gtn.gtn2o.ui.action.duallistbox.GtnUIFrameWorkLoadDualListBoxAction;
import com.stpl.gtn.gtn2o.ui.action.duallistbox.GtnUIFrameworkReturnsDualListBoxConfigAction;
import com.stpl.gtn.gtn2o.ui.action.fieldfactory.GtnFrameworkReturnLeftFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.action.fieldfactory.GtnFrameworkReturnRightTableFieldFactory;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkConfigureRightTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPagedTreeTableFillCountDataAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetBulkDataAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkFSPagedTreeTableGetCountAction;
import com.stpl.gtn.gtn2o.ui.action.pagedtreetable.GtnFrameworkReturnCheckAllAction;
import com.stpl.gtn.gtn2o.ui.action.popupaction.GtnFrameworkPopupCaptionChangeAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkForecastButtonOneClickNextButtonCustomAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkForecastButtonOneClickTabChangeCustomAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkForecastButtonTwoClickNextButtonCustomAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkForecastButtonTwoClickTabChangeCustomAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkForecastReturnDataSelectionNextAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkForecastReturnDataSelectionTabAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkForecastReturnTabChangeCustomAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkReturnProjectionTabCalculateAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkReturnProjectionTabGenerateAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkReturnProjectionTabResetAction;
import com.stpl.gtn.gtn2o.ui.action.tabs.GtnFrameworkReturnProjectionTabResetButtonCustomAction;
import com.stpl.gtn.gtn2o.ui.action.validation.GtnFrameworkForecastReturnBusinessUnitValidationAction;
import com.stpl.gtn.gtn2o.ui.action.validation.GtnFrameworkReturnProjectionTabLRightTreeTableValidateAction;
import com.stpl.gtn.gtn2o.ui.action.validation.GtnFrameworkReturnProjectionTabLeftTreeTableValidateAction;
import com.stpl.gtn.gtn2o.ui.action.validation.GtnUIFrameWorkForecastReturnCloseValidationAction;
import com.stpl.gtn.gtn2o.ui.action.validation.GtnUIFrameworkMassUpdateFiledDdlbValidateAction;
import com.stpl.gtn.gtn2o.ui.action.validation.GtnUIFrameworkPopulateButtonValidateAction;
import com.stpl.gtn.gtn2o.ui.action.workflow.GtnFrameworkForecastWorkflowAction;
import com.stpl.gtn.gtn2o.ui.action.workflow.GtnFrameworkForecastWorkflowButtonAction;
import com.stpl.gtn.gtn2o.ui.action.workflow.GtnFrameworkForecastWorkflowTabLoadAction;
import com.stpl.gtn.gtn2o.ui.action.workflow.GtnFrameworkReturnsWorkflowInboxRefreshAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

public class GtnUIFrameworkForecastReturnDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkForecastReturnDataSelectionGenerateAction.class.getName(),
				new GtnFrameworkForecastReturnDataSelectionGenerateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnDeleteFileAction.class.getName(),
				new GtnFrameworkForecastReturnDeleteFileAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnEditAction.class.getName(),
				new GtnFrameworkForecastReturnEditAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkForecastReturnPublicPrivateViewSaveAction.class.getName(),
				new GtnFrameworkForecastReturnPublicPrivateViewSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnSaveAndUpdateAction.class.getName(),
				new GtnFrameworkForecastReturnSaveAndUpdateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnSaveViewButtonAction.class.getName(),
				new GtnFrameworkForecastReturnSaveViewButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnSubmitAction.class.getName(),
				new GtnFrameworkForecastReturnSubmitAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnViewAction.class.getName(),
				new GtnFrameworkForecastReturnViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnViewDeleteAction.class.getName(),
				new GtnFrameworkForecastReturnViewDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnsDataSelectionFieldsSaveAction.class.getName(),
				new GtnFrameworkReturnsDataSelectionFieldsSaveAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkReturnsDualListBoxRightTableLoadAction.class.getName(),
				new GtnFrameworkReturnsDualListBoxRightTableLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkReturnsDualListBoxRightTableRefreshAction.class.getName(),
				new GtnFrameworkReturnsDualListBoxRightTableRefreshAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkLoadDualListBoxAction.class.getName(),
				new GtnUIFrameWorkLoadDualListBoxAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkReturnsDualListBoxConfigAction.class.getName(),
				new GtnUIFrameworkReturnsDualListBoxConfigAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnLeftFieldFactoryAction.class.getName(),
				new GtnFrameworkReturnLeftFieldFactoryAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnRightTableFieldFactory.class.getName(),
				new GtnFrameworkReturnRightTableFieldFactory());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction.class.getName(),
				new GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkConfigureRightTableHeaderForPTTCompoAction.class.getName(),
				new GtnFrameworkConfigureRightTableHeaderForPTTCompoAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFSPagedTreeTableFillCountDataAction.class.getName(),
				new GtnFrameworkFSPagedTreeTableFillCountDataAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFSPagedTreeTableGetBulkDataAction.class.getName(),
				new GtnFrameworkFSPagedTreeTableGetBulkDataAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFSPagedTreeTableGetCountAction.class.getName(),
				new GtnFrameworkFSPagedTreeTableGetCountAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction.class.getName(),
				new GtnFrameworkFSPTTCompLeftHeaderFormHeaderAndConfigAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction.class.getName(),
				new GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnCheckAllAction.class.getName(),
				new GtnFrameworkReturnCheckAllAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPopupCaptionChangeAction.class.getName(),
				new GtnFrameworkPopupCaptionChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkForecastButtonOneClickNextButtonCustomAction.class.getName(),
				new GtnFrameworkForecastButtonOneClickNextButtonCustomAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkForecastButtonOneClickTabChangeCustomAction.class.getName(),
				new GtnFrameworkForecastButtonOneClickTabChangeCustomAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkForecastButtonTwoClickNextButtonCustomAction.class.getName(),
				new GtnFrameworkForecastButtonTwoClickNextButtonCustomAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkForecastButtonTwoClickTabChangeCustomAction.class.getName(),
				new GtnFrameworkForecastButtonTwoClickTabChangeCustomAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnDataSelectionNextAction.class.getName(),
				new GtnFrameworkForecastReturnDataSelectionNextAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnDataSelectionTabAction.class.getName(),
				new GtnFrameworkForecastReturnDataSelectionTabAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnTabChangeCustomAction.class.getName(),
				new GtnFrameworkForecastReturnTabChangeCustomAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnProjectionTabCalculateAction.class.getName(),
				new GtnFrameworkReturnProjectionTabCalculateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnProjectionTabGenerateAction.class.getName(),
				new GtnFrameworkReturnProjectionTabGenerateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnProjectionTabResetAction.class.getName(),
				new GtnFrameworkReturnProjectionTabResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkReturnProjectionTabResetButtonCustomAction.class.getName(),
				new GtnFrameworkReturnProjectionTabResetButtonCustomAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkForecastReturnBusinessUnitValidationAction.class.getName(),
				new GtnFrameworkForecastReturnBusinessUnitValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkReturnProjectionTabLeftTreeTableValidateAction.class.getName(),
				new GtnFrameworkReturnProjectionTabLeftTreeTableValidateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkReturnProjectionTabLRightTreeTableValidateAction.class.getName(),
				new GtnFrameworkReturnProjectionTabLRightTreeTableValidateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnUIFrameWorkForecastReturnCloseValidationAction.class.getName(),
				new GtnUIFrameWorkForecastReturnCloseValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkMassUpdateFiledDdlbValidateAction.class.getName(),
				new GtnUIFrameworkMassUpdateFiledDdlbValidateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPopulateButtonValidateAction.class.getName(),
				new GtnUIFrameworkPopulateButtonValidateAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastWorkflowAction.class.getName(),
				new GtnFrameworkForecastWorkflowAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastWorkflowButtonAction.class.getName(),
				new GtnFrameworkForecastWorkflowButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastWorkflowTabLoadAction.class.getName(),
				new GtnFrameworkForecastWorkflowTabLoadAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnDsSearchTableLogic.class.getName(),
				new GtnFrameworkForecastReturnDsSearchTableLogic());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnFromToPeriodAction.class.getName(),
				new GtnFrameworkForecastReturnFromToPeriodAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnModeChangeAction.class.getName(),
				new GtnFrameworkForecastReturnModeChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnRefreshAction.class.getName(),
				new GtnFrameworkForecastReturnRefreshAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkForecastReturnsStartEndPeriodAction.class.getName(),
				new GtnFrameworkForecastReturnsStartEndPeriodAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkResetRelationValuesAction.class.getName(),
				new GtnFrameworkResetRelationValuesAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkForecastReturnMassUpdateAction.class.getName(),
				new GtnUIFrameworkForecastReturnMassUpdateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkForecastReturnSearchAlertAction.class.getName(),
				new GtnUIFrameWorkForecastReturnSearchAlertAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkFrequencyComboBoxLoadAction.class.getName(),
				new GtnUIFrameWorkFrequencyComboBoxLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkReturnsWorkflowInboxRefreshAction.class.getName(),
				new GtnFrameworkReturnsWorkflowInboxRefreshAction());

	}

}
