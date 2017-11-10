package com.stpl.gtn.gtn2o.ui.framework.type;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.button.GtnUIFrameworkButtonComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.calendarfield.GtnUIFrameworkCalendarComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroup;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.label.GtnUIFrameworkLabelComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.label.error.GtnUIFrameworkErrorBannerComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.panel.GtnUIFrameworkPanelComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.prouptextfield.GtnUIFrameworkPopupTextField;
import com.stpl.gtn.gtn2o.ui.framework.component.splitpanel.GtnUIFrameworkHorizontalSplitPanelComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.splitpanel.GtnUIFrameworkVerticalSplitPanelComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.table.datatable.GtnUIFrameworkTableCompnent;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewPagedTreeTableComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.table.treetable.GtnUIFrameworkTreeTableComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabSheetComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.textarea.GtnUIFrameworkTextAreaComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.tree.GtnUIFrameworkTreeComponent;

public enum GtnUIFrameworkComponentType {

	TEXTBOX(new GtnUIFrameworkTextComponent()),

	COMBOBOX(new GtnUIFrameworkComboboxComponent()),

	PAGEDTABLE(new GtnUIFrameworkPagedTableComponent()),

	TABSHEET(new GtnUIFrameworkTabSheetComponent()),

	OPTIONGROUP(new GtnUIFrameworkOptionGroup()),

	BUTTON(new GtnUIFrameworkButtonComponent()),

	PANEL(new GtnUIFrameworkPanelComponent()),

	LAYOUT(new GtnUIFrameworkLayoutComponent()),

	CHECKEDCOMBOBOX(new GtnUIFrameworkCheckedComboboxComponent()),

	DATATABLE(new GtnUIFrameworkTableCompnent()),

	DATEFIELD(new GtnUIFrameworkDateComponent()),

	NOTES_TAB(new GtnUIFrameworkNotesTabComponent()),

	PAGEDTREETABLE(new GtnUIFrameworkPagedTreeTableComponent()),

	TEXTAREA(new GtnUIFrameworkTextAreaComponent()),

	DUALLISTBOX(new GtnUIFrameworkDualListBoxComponent()),

	POPUPTEXTFIELD(new GtnUIFrameworkPopupTextField()),

	CHECKBOX(new GtnUIFrameworkCheckBoxComponent()),

	TREE(new GtnUIFrameworkTreeComponent()),

	LABEL(new GtnUIFrameworkLabelComponent()),

	HORIZONTAL_SPLIT_PANEL(new GtnUIFrameworkHorizontalSplitPanelComponent()),

	VERTICAL_SPLIT_PANEL(new GtnUIFrameworkVerticalSplitPanelComponent()),

	EXCEL_BUTTON(new GtnUIFrameworkExcelButtonComponent()),

	PAGED_TREE_TABLE(new GtnUIFrameworkNewPagedTreeTableComponent()),

	TREE_TABLE(new GtnUIFrameworkTreeTableComponent()),

	ERROR_BANNER(new GtnUIFrameworkErrorBannerComponent()),

	CALENDAR_FIELD(new GtnUIFrameworkCalendarComponent());

	private GtnUIFrameworkComponent gtnComponent;

	private GtnUIFrameworkComponentType(GtnUIFrameworkComponent gtnComponent) {
		this.gtnComponent = gtnComponent;
	}

	public GtnUIFrameworkComponent getGtnComponent() {
		return gtnComponent;
	}

}
