package com.stpl.gtn.gtn2o.ui.framework.type;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnFrameworkFieldFactoryPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkChangeCaptionAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkConfirmationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkConfirmedResetAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkCsvExcelExportAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkDeleteAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkEditAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkExcelExportAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkInfoAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkLoadCustomAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkLoadDataGridAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkLoadDataTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkLoadDataTreeTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkManageTableRecordTypeAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkNavigationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkNotificationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkPopUpAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkResetAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkSaveConfirmationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkSearchNoticationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkSetDefaultAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkSetModeAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkShowErrorBannerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkTableAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkWarningAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkWindowCloseAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkAddRecordAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkAttachAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkChangeTabAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkDataTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkDisableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkEnableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkHelperTableReloadAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkLoadNotesTabAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkLoadTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkLoadTreeTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkMassFieldEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkNextTabAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkPopupCloseAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkPreviousTabAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkRemoveAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkSetDataAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkTreeTableCollapseAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkTreeTableExpandAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkTreeTableLevelFilterAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkValueChangeAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkVisibleAction;
import com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.GtnUIFrameWorkDualListBoxLoadLeftTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.GtnUIFrameWorkDualListBoxLoadRightTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.GtnUIFrameWorkDualListBoxLoadRightTableBulkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.GtnUIFrameWorkDuallistBoxRightToLeftTableLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnFrameworkDateCompareValidationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameWorkValidationAction;

public enum GtnUIFrameworkActionType {

	ALERT_ACTION(new GtnUIFrameWorkAlertAction()),

	CONFIRMATION_ACTION(new GtnUIFrameWorkConfirmationAction()),

	LOAD_DATA_TABLE_ACTION(new GtnUIFrameWorkLoadDataTableAction()),

	NAVIGATION_ACTION(new GtnUIFrameWorkNavigationAction()),

	NOTIFICATION_ACTION(new GtnUIFrameWorkNotificationAction()),

	POPUP_ACTION(new GtnUIFrameWorkPopUpAction()),

	RESET_ACTION(new GtnUIFrameWorkResetAction()),

	VALIDATION_ACTION(new GtnUIFrameWorkValidationAction()),

	TREETABLE_ACTION(new GtnUIFrameWorkLoadDataTreeTableAction()),

	POPUP_SELECT_ACTION(new GtnUIFrameworkPopupSelectAction()),

	FIELDFACTORY_POPUP_SELECT_ACTION(new GtnFrameworkFieldFactoryPopupSelectAction()),

	POPUP_CLOSE_ACTION(new GtnUIFrameworkPopupCloseAction()),

	ATTACH_ACTION(new GtnUIFrameworkAttachAction()),

	REMOVE_ACTION(new GtnUIFrameworkRemoveAction()),

	CUSTOM_ACTION(new GtnUIFrameWorkLoadCustomAction()),

	EDIT_ACTION(new GtnUIFrameWorkEditAction()),

	ENABLE_ACTION(new GtnUIFrameworkEnableAction()),

	DISABLE_ACTION(new GtnUIFrameworkDisableAction()),

	VISIBLE_ACTION(new GtnUIFrameworkVisibleAction()),

	VALUE_CHANGE_ACTION(new GtnUIFrameworkValueChangeAction()),

	MASSFIELD_ENABLEDISABLE_ACTION(new GtnUIFrameworkMassFieldEnableDisableAction()),

	TABLE_ALERT_ACTION(new GtnUIFrameWorkTableAlertAction()),

	ADD_RECORD(new GtnUIFrameworkAddRecordAction()),

	CHANGE_CAPTION(new GtnUIFrameWorkChangeCaptionAction()),

	DELETE_ACTION(new GtnUIFrameWorkDeleteAction()),

	SET_DEFAULT_ACTION(new GtnUIFrameWorkSetDefaultAction()),

	MODE_CHANGE(new GtnUIFrameWorkSetModeAction()),

	SEARCH_COMPLETED_NOTIFICATION_ACTION(new GtnUIFrameWorkSearchNoticationAction()),

	CHANGE_TAB_ACTION(new GtnUIFrameworkChangeTabAction()),

	ENABLE_OR_DISABLE_ACTION(new GtnUIFrameworkEnableDisableAction()),

	SET_DATA_ACTION(new GtnUIFrameworkSetDataAction()),

	TREE_TEBLE_LEVEL_FILTER(new GtnUIFrameworkTreeTableLevelFilterAction()),

	TREE_TABLE_EXPAND_ACTION(new GtnUIFrameworkTreeTableExpandAction()),

	TREE_TABLE_COLLAPSE_ACTION(new GtnUIFrameworkTreeTableCollapseAction()),

	NEXT_TAB_ACTION(new GtnUIFrameworkNextTabAction()),

	PREVIOUS_TAB_ACTION(new GtnUIFrameworkPreviousTabAction()),

	WARNING_ACTION(new GtnUIFrameWorkWarningAction()),

	SAVE_CONFIRMATION_ACTION(new GtnUIFrameWorkSaveConfirmationAction()),

	INFO_ACTION(new GtnUIFrameWorkInfoAction()),

	WINDOW_CLOSE_ACTION(new GtnUIFrameWorkWindowCloseAction()),

	EXCEL_EXPORT_ACTION(new GtnUIFrameWorkExcelExportAction()),

	CONFIRMED_RESET_ACTION(new GtnUIFrameWorkConfirmedResetAction()),

	LOAD_TREE_TABLE_ACTION(new GtnUIFrameworkLoadTreeTableAction()),

	DUAL_LISTBOX_LEFT_TABLE_LOADACTION(new GtnUIFrameWorkDualListBoxLoadLeftTableAction()),

	DUAL_LISTBOX_RIGHT_TABLE_LOADACTION(new GtnUIFrameWorkDualListBoxLoadRightTableAction()),

	DUAL_LISTBOX_RIGHT_TABLE_BULK_LOADACTION(new GtnUIFrameWorkDualListBoxLoadRightTableBulkAction()),

	DUAL_LISTBOX_RIGHT_TO_LEFT_TABLE_LOADACTION(new GtnUIFrameWorkDuallistBoxRightToLeftTableLoadAction()),

	MANAGE_TABLE_RECORD_TYPE_ACTION(new GtnUIFrameWorkManageTableRecordTypeAction()),

	EXCEL_EXPORT_CSV_ACTION(new GtnUIFrameWorkCsvExcelExportAction()),

	SHOW_ERROR_BANNER_ACTION(new GtnUIFrameWorkShowErrorBannerAction()),

	RELOAD_HELPER_TABLE_ACTION(new GtnUIFrameworkHelperTableReloadAction()),

	DATE_COMPARE_VALIDATE_ACTION(new GtnFrameworkDateCompareValidationAction()),

	LOAD_NOTES_TAB(new GtnUIFrameworkLoadNotesTabAction()),

	LOAD_TABLE_ACTION(new GtnUIFrameworkLoadTableAction()),
        LOAD_DATA_GRID_ACTION(new GtnUIFrameWorkLoadDataGridAction()),
	DATA_TABLE_CHECKALL_ACTION(new GtnUIFrameworkDataTableCheckAllAction());

	private final GtnUIFrameWorkAction gtnUIFrameWorkAction;

	private GtnUIFrameworkActionType(GtnUIFrameWorkAction gtnUIFrameWorkAction) {
		this.gtnUIFrameWorkAction = gtnUIFrameWorkAction;
	}

	public GtnUIFrameWorkAction getGtnUIFrameWorkAction() {
		return gtnUIFrameWorkAction.createInstance();
	}

	public GtnUIFrameWorkAction getSingletonAction() {
		return gtnUIFrameWorkAction;
	}

}
