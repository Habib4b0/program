package com.stpl.app.security.businessRoleModuleMaster.ui.form;

import java.util.List;

import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.dto.SearchDTO;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.businessRoleModuleMaster.ui.layout.ActionButtonLayout;
import com.stpl.app.security.businessRoleModuleMaster.ui.layout.SearchButtonLayout;
import static com.stpl.app.security.businessRoleModuleMaster.ui.view.BusinessRoleModuleMasterSearchView.userId;
import com.stpl.app.security.businessRoleModuleMaster.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.HelperUtils;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Date;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

public class SearchForm extends CustomComponent {

    private static final long serialVersionUID = 8893447767363695369L;

    final ErrorLabel errorMsg = new ErrorLabel();
    IndexedContainer con = new IndexedContainer();
    BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;
    BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult;
    BusinessRoleModuleSearchLogic businessRoleModuleLogic = new BusinessRoleModuleSearchLogic();
    CheckBox add = new CheckBox("Add");
    CheckBox view = new CheckBox("View");
    CheckBox edit = new CheckBox("Edit");
    @UiField("functionResult")
    private HorizontalLayout functionResult;
    @UiField("fieldResult")
    private HorizontalLayout fieldResult;
    @UiField("check")
    private HorizontalLayout check;
    @UiField("saveBtn")
    private HorizontalLayout saveBtn;
    @UiField("mainVL")
    private VerticalLayout mainVL;
    @UiField("button")
    private HorizontalLayout button;
    @UiField("businessRoleName")
    private ComboBox businessRoleName;
    @UiField("subModuleName")
    private ComboBox subModuleName;
    @UiField("moduleName")
    private ComboBox moduleName;
    @UiField("cssLayout")
    private CssLayout cssLayout;

    private ExtFilterTable tableResult;

    private ExtFilterTable table;

    private String userId;

    public SearchForm(BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans, BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult, ExtFilterTable table, ExtFilterTable tableResult, String userId) {
        super();

        this.searchResultbeans = searchResultbeans;
        this.searchFieldResult = searchFieldResult;
        this.table = table;
        this.tableResult = tableResult;
        this.userId = userId;

        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/searchForm.xml"), this));
        addStyleName("bootstrap");
        init();
    }

    private void init() {

        addToContent();
        configureFields();
    }

    private void addToContent() {
        con.addItem("-Select One-");

        button.addComponent(new SearchButtonLayout(getBinder(), searchResultbeans, searchFieldResult, table, tableResult, subModuleName, moduleName, businessRoleName,add,edit,view));
        functionResult.addComponent(addToTable());
        check.addComponent(addCheck());
        fieldResult.addComponent(addToTableField(true));
        saveBtn.addComponent(new ActionButtonLayout(searchResultbeans,
                searchFieldResult, subModuleName, moduleName, businessRoleName, userId,add,edit,view));

    }

    private ErrorfulFieldGroup getBinder() {
        SearchDTO bean = new SearchDTO();
        ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
                new BeanItem<SearchDTO>(bean));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    private void configureFields() {

        new HelperUtils().getNativeSelect(businessRoleName, businessRoleModuleLogic.getBusinessRoleNames());
        businessRoleName.select("-Select One-");
        moduleName.addItem("-Select One-");
        moduleName.select("-Select One-");
        subModuleName.addItem("-Select One-");
        subModuleName.select("-Select One-");
        businessRoleName.setValidationVisible(true);
        businessRoleName.setRequired(true);
        businessRoleName.setImmediate(true);
        businessRoleName.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {

                    try {
                        if (String.valueOf(event.getProperty().getValue()).equals("-Select One-")) {
                            moduleName.setContainerDataSource(con);
                            moduleName.select("-Select One-");
                        } else {
                            moduleName.setContainerDataSource(getModuleNames());
                            moduleName.select("-Select One-");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        subModuleName.setRequired(true);
        subModuleName.setValidationVisible(true);
        subModuleName.setImmediate(true);
        moduleName.setValidationVisible(true);
        moduleName.setImmediate(true);
        moduleName.setRequired(true);
        moduleName.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {

                    if (String.valueOf(event.getProperty().getValue()).equals("-Select One-")) {
                        subModuleName.setContainerDataSource(con);
                        subModuleName.select("-Select One-");
                    } else {
                        subModuleName.setContainerDataSource(getSubModules(event.getProperty().getValue().toString()));
                        subModuleName.select("-Select One-");
                    }
                }
            }

        });
        add.setValue(Boolean.FALSE);
        edit.setValue(Boolean.FALSE);
        view.setValue(Boolean.FALSE);
        add.setImmediate(true);
        add.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -6857112166321059475L;

            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                // TODO Auto-generated method stub
                boolean value = add.getValue();
                if (value == true) {
                    int size = searchFieldResult.size();

                    if (size != 0) {
                        for (int i = 0; i < size; i++) {
                            SearchBusinessRoleModuleForm result;
                            result = searchFieldResult.getIdByIndex(i);
                            result.setAdd(true);
//                            searchFieldResult.addBean(result);
                        }
                        addToTableField(false);
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

                if (value == true) {
                    int size = searchFieldResult.size();

                    if (size != 0) {
                        for (int i = 0; i < size; i++) {
                            SearchBusinessRoleModuleForm result;
                            result = searchFieldResult.getIdByIndex(i);
                            result.setView(true);
//                            searchFieldResult.addBean(result);
                        }
                        addToTableField(false);
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

                if (value == true) {
                    int size = searchFieldResult.size();

                    if (size != 0) {
                        for (int i = 0; i < size; i++) {
                            SearchBusinessRoleModuleForm result;
                            result = searchFieldResult.getIdByIndex(i);
                            result.setEdit(value);
//                            searchFieldResult.addBean(result);
                        }
                        addToTableField(false);
                    }
                }

            }
        });
    }

    private Container getModuleNames() {

        List<String> helperLst = new ArrayList<String>();
        try {
            helperLst.add("-Select One-");
            helperLst.addAll(businessRoleModuleLogic.getModuleNames());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new IndexedContainer(helperLst);
    }

    private Container getSubModules(String moduleName) {
        List<String> helperLst = new ArrayList<String>();
        helperLst.add("-Select One-");
        helperLst.addAll(businessRoleModuleLogic.getSubModules(moduleName));
        return new IndexedContainer(helperLst);
    }

    public HorizontalLayout addCheck() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(new Label("Select All Option:"));
        layout.addComponent(add);
        layout.addComponent(view);
        layout.addComponent(edit);
        return layout;
    }

    @SuppressWarnings("serial")
    private ExtFilterTable addToTable() {

        table.setContainerDataSource(searchResultbeans);
        table.setVisibleColumns(UIUtils.BUSINESS_ROLE_MODULE_MASTER_COL);
        table.setColumnHeaders(UIUtils.BUSINESS_ROLE_MODULE_MASTER_FUNCTION_HEADER);
        table.setImmediate(true);
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
        table.setPageLength(10);
        table.setImmediate(true);
        table.setSelectable(true);
        table.setSizeFull();
        return table;
    }

    @SuppressWarnings("serial")
    private ExtFilterTable addToTableField(boolean first) {
        tableResult.markAsDirty();
        searchFieldResult.removeAllContainerFilters();
        
        tableResult.setContainerDataSource(searchFieldResult);
//        searchFieldResult.removeAllContainerFilters();
        tableResult
                .setVisibleColumns(UIUtils.BUSINESS_ROLE_MODULE_MASTER_FIELDS);
        tableResult
                .setColumnHeaders(UIUtils.BUSINESS_ROLE_MODULE_MASTER_FIELDS_HEADER);
        if(first){
        add.setValue(setAddFlag(searchFieldResult));
        tableResult.setEditable(true);
        
        tableResult.setTableFieldFactory(new DefaultFieldFactory() {
            @Override
            public Field<?> createField(final Container container, final Object itemId,
                    final Object propertyId, Component uiContext) {
                // Create fields by their class
                Class<?> cls = container.getType(propertyId);
                final SearchBusinessRoleModuleForm dto=(SearchBusinessRoleModuleForm)itemId;
                // Create a DateField with year resolution for dates
                if (cls.equals(Date.class)) {
                    DateField df = new DateField();
                    df.setResolution(Resolution.YEAR);
                    df.setReadOnly(true);
                    return df;
                }

                // Create a CheckBox for Boolean fields
                if (propertyId.equals("add")
                        && ((Boolean) container.getItem(itemId)
                        .getItemProperty("nullFlag").getValue())
                        .booleanValue()
                        && ((Boolean) container.getItem(itemId)
                        .getItemProperty("add").getValue())
                        .booleanValue() && cls.equals(Boolean.class)) {
                    final ExtCustomCheckBox ch = new ExtCustomCheckBox();
                    ch.setImmediate(true);
                    // ch.setEnabled(true);
                    ch.setEnabled(false);
                    ch.setValue(true);
//                    ch.addClickListener(new ExtCustomCheckBox.ClickListener() {
//
//                        @Override
//                        public void click(ExtCustomCheckBox.ClickEvent event) {
//                            
//                            container.getItem(itemId).getItemProperty(propertyId).setValue(ch.getValue());
//                        }
//                    });
                    return ch;
                } else if (cls.equals(Boolean.class)) {
                    final ExtCustomCheckBox ch = new ExtCustomCheckBox();
                    ch.setEnabled(true);
                    ch.setImmediate(true);
//                    Property pr=container.getContainerProperty(itemId, propertyId);
//                    ch.setData(pr);                    
//                    ch.addClickListener(new ExtCustomCheckBox.ClickListener() {
//
//                        @Override
//                        public void click(ExtCustomCheckBox.ClickEvent event) { 
//                            
//                         
//                            dto.setAdd(ch.getValue());

//                            
////                            ((Property)ch.getData()).setValue(ch.getValue());
//                            
//                            
////                            tableResult.markAsDirty();
////                            if (searchFieldResult != null) {
////                                if (searchFieldResult.getContainerProperty(itemId, propertyId) != null) {
////                                        if (ch.getValue() != null) {
////                                            searchFieldResult.getContainerProperty(itemId, propertyId).setValue(ch.getValue());
//////                                            searchFieldResult.getContainerProperty(itemId, propertyId).setValue(ch.getValue());
//////                                            container.getItem(itemId).getItemProperty(propertyId).setValue(ch.getValue());
////                                        } else {
////                                           
////                                        }
////                                } else {
////                                   
////                                }
////                            } else {
////                                
////                            }
//
//                        }
//                    });

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
        
        tableResult.setPageLength(10);
        tableResult.setImmediate(true);
        tableResult.setSelectable(true);
        tableResult.setSizeFull();
        }
        return tableResult;
    }

    public boolean setAddFlag(BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult) {
        int size = searchFieldResult.size();
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {

            if (searchFieldResult.getIdByIndex(i).getAdd().equals(false)) {
                return false;
            }

        }
        return true;
    }

}
