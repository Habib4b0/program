package com.stpl.app.security.businessRoleModuleMaster.ui.form;

import java.util.List;

import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.dto.SearchDTO;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.businessRoleModuleMaster.ui.layout.ActionButtonLayout;
import com.stpl.app.security.businessRoleModuleMaster.ui.layout.SearchButtonLayout;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import com.stpl.app.security.businessRoleModuleMaster.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.HelperUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.shared.ui.datefield.Resolution;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Date;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchForm.class);

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
        con.addItem(CommonUtils.SELECT_ONE);

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
        businessRoleName.select(CommonUtils.SELECT_ONE);
        moduleName.addItem(CommonUtils.SELECT_ONE);
        moduleName.select(CommonUtils.SELECT_ONE);
        subModuleName.addItem(CommonUtils.SELECT_ONE);
        subModuleName.select(CommonUtils.SELECT_ONE);
        businessRoleName.setValidationVisible(true);
        businessRoleName.setRequired(true);
        businessRoleName.setImmediate(true);
        businessRoleName.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {

                    try {
                        if (String.valueOf(event.getProperty().getValue()).equals(CommonUtils.SELECT_ONE)) {
                            moduleName.setContainerDataSource(con);
                            moduleName.select(CommonUtils.SELECT_ONE);
                        } else {
                            moduleName.setContainerDataSource(getModuleNames());
                            moduleName.select(CommonUtils.SELECT_ONE);
                        }
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
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

                    if (String.valueOf(event.getProperty().getValue()).equals(CommonUtils.SELECT_ONE)) {
                        subModuleName.setContainerDataSource(con);
                        subModuleName.select(CommonUtils.SELECT_ONE);
                    } else {
                        subModuleName.setContainerDataSource(getSubModules(event.getProperty().getValue().toString()));
                        subModuleName.select(CommonUtils.SELECT_ONE);
                    }
                }
            }

        });
        add.setValue(Boolean.FALSE);
        edit.setValue(Boolean.FALSE);
        view.setValue(Boolean.FALSE);
        add.setImmediate(true);
        add.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent vce) {
                 boolean value = add.getValue();
                if (value == true) {
                    int size = searchFieldResult.size();

                    if (size != 0) {
                        for (int i = 0; i < size; i++) {
                            SearchBusinessRoleModuleForm result;
                            result = searchFieldResult.getIdByIndex(i);
                            result.setAdd(true);
                        }
                        addToTableField(false);
                    }

                }
            }
        });

        view.setImmediate(true);
        view.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent vce) {
                boolean value = view.getValue();

                if (value == true) {
                    int size = searchFieldResult.size();

                    if (size != 0) {
                        for (int i = 0; i < size; i++) {
                            SearchBusinessRoleModuleForm result;
                            result = searchFieldResult.getIdByIndex(i);
                            result.setView(true);
                        }
                        addToTableField(false);
                    }
                }
            }
        });

        edit.setImmediate(true);
        edit.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent vce) {
                 boolean value = edit.getValue();

                if (value == true) {
                    int size = searchFieldResult.size();

                    if (size != 0) {
                        for (int i = 0; i < size; i++) {
                            SearchBusinessRoleModuleForm result;
                            result = searchFieldResult.getIdByIndex(i);
                            result.setEdit(value);
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
            helperLst.add(CommonUtils.SELECT_ONE);
            helperLst.addAll(businessRoleModuleLogic.getModuleNames());

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return new IndexedContainer(helperLst);
    }

    private Container getSubModules(String moduleName) {
        List<String> helperLst = new ArrayList<String>();
        helperLst.add(CommonUtils.SELECT_ONE);
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
        table.setVisibleColumns(UIUtils.getInstance().businessRoleModuleMasterCol);
        table.setColumnHeaders(UIUtils.getInstance().businessRoleModuleMasterFunctionHeader);
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
        table.setPageLength(NumericConstants.TEN);
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
        tableResult
                .setVisibleColumns(UIUtils.getInstance().businessRoleModuleMasterFields);
        tableResult
                .setColumnHeaders(UIUtils.getInstance().businessRoleModuleMasterFieldsHeader);
        if(first){
        add.setValue(setAddFlag(searchFieldResult));
        tableResult.setEditable(true);
        
        tableResult.setTableFieldFactory(new DefaultFieldFactory() {
            @Override
            public Field<?> createField(final Container container, final Object itemId,
                    final Object propertyId, Component uiContext) {
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
                        && ((Boolean) container.getItem(itemId)
                        .getItemProperty("nullFlag").getValue())
                        .booleanValue()
                        && ((Boolean) container.getItem(itemId)
                        .getItemProperty("add").getValue())
                        .booleanValue() && cls.equals(Boolean.class)) {
                    final ExtCustomCheckBox ch = new ExtCustomCheckBox();
                    ch.setImmediate(true);
                    ch.setEnabled(false);
                    ch.setValue(true);
                    return ch;
                } else if (cls.equals(Boolean.class)) {
                    final ExtCustomCheckBox ch = new ExtCustomCheckBox();
                    ch.setEnabled(true);
                    ch.setImmediate(true);
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
        
        tableResult.setPageLength(NumericConstants.TEN);
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
