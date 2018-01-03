package com.stpl.app.security.userGrpBsnsRoleMgmt.ui.form;

import java.util.Date;

import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.userGrpBsnsRoleMgmt.ui.layout.ActionButtonLayout;
import com.stpl.app.security.businessRoleModuleMaster.util.UIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.shared.ui.datefield.Resolution;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.VerticalLayout;

public class SearchResults extends CustomComponent {
	private static final long serialVersionUID = 1L;
	final Label space = new Label("&nbsp;", ContentMode.HTML);
	BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;
	BusinessRoleModuleSearchLogic businessRoleModuleLogic = new BusinessRoleModuleSearchLogic();
	Table table;

	public SearchResults(
			BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans,
			Table table) {
		this.searchResultbeans = searchResultbeans;
		this.table = table;
		init();
	}

	private void init() {
		space.setHeight("20");

		addToContent();
	}

	private void addToContent() {
		final VerticalLayout content = new VerticalLayout();
		content.addComponentAsFirst(space);
		content.addComponent(space);
		content.addComponent(addToTable());
		content.addComponent(space);
		content.addComponent(new ActionButtonLayout(searchResultbeans));
		setCompositionRoot(content);
	}

	@SuppressWarnings("serial")
	private Table addToTable() {
		table.markAsDirty();
		table.setContainerDataSource(searchResultbeans);
		table.setVisibleColumns(UIUtils.getInstance().businessRoleModuleMasterCol);
		table.setTableFieldFactory(new DefaultFieldFactory() {
		    @Override
		    public Field<?> createField(Container container, Object itemId,
		            Object propertyId, Component uiContext) {
		        // Create fields by their class
		        Class<?> cls = container.getType(propertyId);

		        // Create a DateField with year resolution for dates
		        if (cls.equals(Date.class)) {
		            DateField df = new DateField();
		            df.setResolution(Resolution.YEAR);
		            df.setReadOnly(true);
		            return df;
		        }
		        
		        // Create a CheckBox for Boolean fields
		        if (cls.equals(Boolean.class))
		            return new CheckBox();
		        @SuppressWarnings("rawtypes")
				Field field = super.createField(container, itemId, propertyId,
                        uiContext);
		        field.setReadOnly(true);
		        // Otherwise use the default field factory 
		        return field;
		    }
		});
		table.setEditable(true);
		table.setPageLength(NumericConstants.SEVEN);
		table.setImmediate(true);
		table.setSelectable(true);
		
		return table;
	}
}
