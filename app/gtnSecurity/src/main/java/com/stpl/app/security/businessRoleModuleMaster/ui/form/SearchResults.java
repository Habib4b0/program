package com.stpl.app.security.businessRoleModuleMaster.ui.form;

import java.util.Date;

import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.businessRoleModuleMaster.util.UIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.shared.ui.datefield.Resolution;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.VerticalLayout;

public class SearchResults extends CustomComponent {
	private static final long serialVersionUID = 1L;
        private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
	private final Label space = new Label("&nbsp;", ContentMode.HTML);
	private final BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;
	private final BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult;
	private BusinessRoleModuleSearchLogic businessRoleModuleLogic = new BusinessRoleModuleSearchLogic();
	private final Table table;
	private final Table tableResult;
	private final CheckBox add = new CheckBox("Add");
	private final CheckBox view = new CheckBox("View");
	private final CheckBox edit = new CheckBox("Edit");

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
						ch.setValue(BOOLEAN_CONSTANT.getTrueFlag());
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
                    @Override
                    public void valueChange(Property.ValueChangeEvent vce) {
				boolean value = add.getValue();
				
				if(value==true){
					int size=searchFieldResult.size();
					
					if(size!=0){
				for(int i=0;i<size;i++){
					SearchBusinessRoleModuleForm result;
					 result=searchFieldResult.getIdByIndex(i);
					 result.setAdd(BOOLEAN_CONSTANT.getTrueFlag());
					 searchFieldResult.addBean(result);
				}
				addToTableField();
				}
					
					
					
				}
			}
        });
		
		
		
		
		view.setImmediate(true);
		view.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent vce) {
					boolean value = view.getValue();
				
				if(value==true){
					int size=searchFieldResult.size();
					
					if(size!=0){
				for(int i=0;i<size;i++)	{
					SearchBusinessRoleModuleForm result;
					 result=searchFieldResult.getIdByIndex(i);
					 result.setView(BOOLEAN_CONSTANT.getTrueFlag());
					 searchFieldResult.addBean(result);
				}
				addToTableField();
				}
				}
			}
        });
		
		edit.setImmediate(true);
		edit.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent vce) {
				boolean value = edit.getValue();
				
				if(value==true){
					int size=searchFieldResult.size();
					
					if(size!=0){
				for(int i=0;i<size;i++){
					SearchBusinessRoleModuleForm result;
					 result=searchFieldResult.getIdByIndex(i);
					 result.setEdit(BOOLEAN_CONSTANT.getTrueFlag());
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
		
			 if(searchFieldResult.getIdByIndex(i).getAdd().equals(BOOLEAN_CONSTANT.getFalseFlag())){
				 return false;
					 }
			
		}
		return true;
	}

	public BusinessRoleModuleSearchLogic getBusinessRoleModuleLogic() {
		return businessRoleModuleLogic;
	}

	public void setBusinessRoleModuleLogic(BusinessRoleModuleSearchLogic businessRoleModuleLogic) {
		this.businessRoleModuleLogic = businessRoleModuleLogic;
	}
	
	
}
