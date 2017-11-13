package com.stpl.gtn.gtn2o.ui.framework.type;

public enum GtnUIFrameworkComponentName {
	TEXTBOX("Text Field"),

	COMBOBOX("ComboBox Field"),

	PAGEDTABLE("Paged Table"),

	TABSHEET("Tab Sheet"),

	OPTIONGROUP("Option Group Field"),

	BUTTON("Button"),

	PANEL("Panel"),

	LAYOUT("Layout"),

	CHECKEDCOMBOBOX("Check Combobox Field"),

	DATATABLE("Table"),

	DATEFIELD("Date Field"),

	NOTES_TAB("Notes Tab"),

	PAGEDTREETABLE("Paged Freeze Tree Table"),

	TEXTAREA("Text Area Field"),

	DUALLISTBOX("Dual List Box"),

	POPUPTEXTFIELD("Popup Text Field"),

	CHECKBOX("Check Box"),

	TREE("Tree"),

	LABEL("Label"),

	HORIZONTAL_SPLIT_PANEL("Horizontal Split Panel"),

	VERTICAL_SPLIT_PANEL("Vertical Split Panel"),

	EXCEL_BUTTON("Excel Button"),

	PAGED_TREE_TABLE("Paged Tree Table"),

	TREE_TABLE("Tree Table"),

	ERROR_BANNER("Error Banner"),

	CALENDAR_FIELD("Calendar Field");

	private String componentName;

	private GtnUIFrameworkComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentName() {
		return componentName;
	}
}
