package com.stpl.app.security.businessRoleModuleMaster.ui.form;

import java.util.Date;

import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.businessRoleModuleMaster.util.UIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class SearchResults extends CustomComponent {
	private static final long serialVersionUID = 1L;
	private final Label space = new Label("&nbsp;", ContentMode.HTML);
	private BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;
	private BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult;
	private BusinessRoleModuleSearchLogic businessRoleModuleLogic = new BusinessRoleModuleSearchLogic();
	private Table table;
	private Table tableResult;
	private CheckBox add = new CheckBox("Add");
	private CheckBox view = new CheckBox("View");
	private CheckBox edit = new CheckBox("Edit");

	public SearchResults(
			BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans,
			BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult,
			Table table, Table tableResult) {
		this.searchResultbeans = searchResultbeans;
		this.searchFieldResult = searchFieldResult;
		this.table = table;
		this.tableResult = tableResult;
		init();
	}

	private void init() {
		space.setHeight("20");

		addToContent();
		configureFields();
	}

	private void addToContent() {
		final VerticalLayout content = new VerticalLayout();
		content.addComponentAsFirst(space);
		content.addComponent(space);
		content.addComponent(addToTable());
		content.addComponent(space);
		
		content.addComponent(addCheck());
		content.addComponent(space);
		content.addComponent(addToTableField());
		content.addComponent(space);
		setCompositionRoot(content);
	}

	public HorizontalLayout addCheck() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(space);
		layout.addComponent(new Label("Select All Option:"));
		layout.addComponent(add);
		layout.addComponent(space);
		layout.addComponent(view);
		layout.addComponent(space);
		layout.addComponent(edit);
		return layout;
	}

	@SuppressWarnings("serial")
	private Table addToTable() {
		table.markAsDirty();
		table.setContainerDataSource(searchResultbeans);
		table.setVisibleColumns(UIUtils.getInstance().businessRoleModuleMasterCol);
		// Set a custom field factory that overrides the default factory
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
				if (cls.equals(Boolean.class)) {
					CheckBox ch = new CheckBox();
					ch.setEnabled(true);
					return ch;
				}
				@SuppressWarnings("rawtypes")
				Field field = super.createField(container, itemId, propertyId,
						uiContext);
				field.setReadOnly(true);
				// Otherwise use the default field factory
				return field;
			}
		});
		table.setEditable(true);
		table.setPageLength(NumericConstants.FIVE);
		table.setImmediate(true);
		table.setSelectable(true);

		return table;
	}

	@SuppressWarnings("serial")
	private Table addToTableField() {
		tableResult.markAsDirty();
		tableResult.setContainerDataSource(searchFieldResult);
		tableResult
				.setVisibleColumns(UIUtils.getInstance().businessRoleModuleMasterFields);
		
		add.setValue(setAddFlag(searchFieldResult));
		// Set a custom field factory that overrides the default factory
		tableResult.setTableFieldFactory(new DefaultFieldFactory() {
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
				if (propertyId.equals("add")
						&&

						((Boolean) container.getItem(itemId)
								.getItemProperty("nullFlag").getValue())
								.booleanValue()
						&& ((Boolean) container.getItem(itemId)
								.getItemProperty("add").getValue())
								.booleanValue()) {
					if (cls.equals(Boolean.class)) {
						CheckBox ch = new CheckBox();
						ch.setEnabled(false);
						ch.setValue(true);
						return ch;
					}
				} else {
					if (cls.equals(Boolean.class)) {
						 CheckBox ch = new CheckBox();
						ch.setEnabled(true);
						
						return ch;

					}
				}
				@SuppressWarnings("rawtypes")
				Field field = super.createField(container, itemId, propertyId,
						uiContext);
				field.setReadOnly(true);
				// Otherwise use the default field factory
				return field;
			}
		});
		tableResult.setEditable(true);
		tableResult.setPageLength(NumericConstants.TEN);
		tableResult.setImmediate(true);
		tableResult.setSelectable(true);

		return tableResult;
	}

	private void configureFields() {
		add.setImmediate(true);
		add.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -6857112166321059475L;

			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub
				boolean value = add.getValue();
				
				if(value==true){
					int size=searchFieldResult.size();
					
					if(size!=0){
				for(int i=0;i<size;i++){
					SearchBusinessRoleModuleForm result;
					 result=searchFieldResult.getIdByIndex(i);
					 result.setAdd(true);
					 searchFieldResult.addBean(result);
				}
				addToTableField();
				}
					
					
					
				}
			}
        });
		
		
		
		
		view.setImmediate(true);
		view.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -6857112166321059475L;

			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub
					boolean value = view.getValue();
				
				if(value==true){
					int size=searchFieldResult.size();
					
					if(size!=0){
				for(int i=0;i<size;i++)	{
					SearchBusinessRoleModuleForm result;
					 result=searchFieldResult.getIdByIndex(i);
					 result.setView(true);
					 searchFieldResult.addBean(result);
				}
				addToTableField();
				}
				}
			}
        });
		
		edit.setImmediate(true);
		edit.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -6857112166321059475L;

			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub
				boolean value = edit.getValue();
				
				if(value==true){
					int size=searchFieldResult.size();
					
					if(size!=0){
				for(int i=0;i<size;i++){
					SearchBusinessRoleModuleForm result;
					 result=searchFieldResult.getIdByIndex(i);
					 result.setEdit(true);
					 searchFieldResult.addBean(result);
				}
				addToTableField();
				}
				}
				
			}
        });
		
		
		
	}
	
	public boolean setAddFlag(BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult){
		int size=searchFieldResult.size();
		if(size==0){
			return false;
		}
		for(int i=0;i<size;i++){
		
			 if(searchFieldResult.getIdByIndex(i).getAdd().equals(false)){
				 return false;
					 }
			
		}
		return true;
	}
	
	
}
