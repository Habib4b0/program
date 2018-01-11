package com.stpl.app.security.udc.ui.form;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import com.stpl.app.security.common.SessionDTO;
import com.stpl.app.security.dao.UdcLogicDAO;
import com.stpl.app.security.dao.impl.UdcLogicDAOImpl;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.security.security.StplSecurity;
import com.stpl.app.security.udc.dto.BrandContainer;
import com.stpl.app.security.udc.dto.BrandCriteria;
import com.stpl.app.security.udc.dto.BrandMasterDTO;
import com.stpl.app.security.udc.dto.HelperForm;
import com.stpl.app.security.udc.logic.UdcLogic;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.NotificationUtils;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.TableResultCustom;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.security.service.SecurityImpl;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Map;
import org.asi.ui.addons.lazycontainer.LazyBeanItemContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdcHelperForm extends CustomComponent implements View {

    /**
     *
     */
    private static final long serialVersionUID = 7820110023085927101L;
    private UdcLogic udcLogic = new UdcLogic();
    final ErrorLabel errorMsg = new ErrorLabel();
    final Label space = new Label("&nbsp;", ContentMode.HTML);
    final NativeSelect category = new NativeSelect();
    final TextField description = new TextField();
    final TextField brandId = new TextField();
    final TextField brandName = new TextField();
    final TextField displayBrand = new TextField();
    final TextField aliasName = new TextField();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    ErrorfulFieldGroup binder;
    ErrorfulFieldGroup brandBinder;
    private static final Object[] HELPER_COLUMNS = new Object[]{"description", "listName"};
    private static final String[] HELPER_HEADERS = new String[]{"Description", CommonUtils.CASECATEGORY};
    public static final String CATEGORY_LABEL = "category";
    public static final String BRAND_ID_LABEL = "brandId";
    public static final String BRAND_NAME_LABEL = "brandName";
    public static final String DIS_BRAND_LABEL = "displayBrand";
    public static final String LANDING_SCREEN = "Landing screen";
    public static final String UDC_CONFIGURATION = "UDC Configuration";
    private static final Object[] BRAND_HELPER_COLUMNS = new Object[]{BRAND_ID_LABEL, BRAND_NAME_LABEL, DIS_BRAND_LABEL, CATEGORY_LABEL};
    private static final String[] BRAND_HELPER_HEADERS = new String[]{"Brand ID", "Brand Name", "Display Brand", CommonUtils.CASECATEGORY};
    
    private static final Object[] FILE_TYPE_HELPER_COLUMNS = new Object[]{"description", "listName", "aliasName"};
    private static final String[] FILE_TYPE_HELPER_HEADERS = new String[]{"Description", CommonUtils.CASECATEGORY ,CommonUtils.ALIAS_NAME};
    Button btnDelete = new Button("Delete");
    Button btnSave1 = new Button("Add");

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UdcHelperForm.class.getName());

    BeanItemContainer<HelperForm> searchResultbeans;
    Table table = new Table();
    LazyBeanItemContainer searchResults;
    final Label valueLabel = new Label("Value");
    final Label aliasNameLabel = new Label("AliasName");
    final Label brandIdLabel = new Label("Brand ID");
    final Label brandNameLabel = new Label("Brand Name");
    final Label displayBrandLabel = new Label("Display Brand");
    int masterSid = 0;
    UdcLogicDAO dao = new UdcLogicDAOImpl();
    private final SessionDTO sessionDTO;

    public UdcHelperForm(SessionDTO sessionDTO, BeanItemContainer<HelperForm> searchResultbeans, Table table) {
        this.searchResultbeans = searchResultbeans;
        this.sessionDTO = sessionDTO;
        this.table = table;
        init();
    }

    private void init() {
        space.setHeight("20");
        addToContent();
        configurePermission();
        configureFields();
        getBinder();
        getBrandBinder();
    }

    private void addToContent() {
        LOGGER.debug("inside UDC addToContent");
        final VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.addComponentAsFirst(space);
        content.addComponent(errorMsg);
        content.addComponent(space);
        content.addComponent(space);
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setMargin(true);
        vLayout.setStyleName("bootstrap-search-criteria");
        vLayout.addComponent(addGrid());
        vLayout.addComponent(deleteButton());
        Panel categoryPanel = new Panel("Category Options");
        categoryPanel.setContent(vLayout);
        content.addComponent(categoryPanel);
        content.addComponent(space);
        content.addComponent(space);
        content.addComponent(space);

        content.addComponent(space);
        Panel resultsPanel = new Panel("Results");
        resultsPanel.setContent(addToTable());
        content.addComponent(resultsPanel);

        setCompositionRoot(content);
    }

    private ErrorfulFieldGroup getBinder() {

        binder = new ErrorfulFieldGroup(
                new BeanItem<HelperForm>(new HelperForm()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    private ErrorfulFieldGroup getBrandBinder() {

        brandBinder = new ErrorfulFieldGroup(
                new BeanItem<BrandMasterDTO>(new BrandMasterDTO()));
        brandBinder.setBuffered(true);
        brandBinder.bindMemberFields(this);
        brandBinder.setErrorDisplay(errorMsg);
        return brandBinder;
    }
    GridLayout gridLayout = new GridLayout(NumericConstants.SEVEN, NumericConstants.FOUR);

    private GridLayout addGrid() {

        gridLayout.setSpacing(true);
        gridLayout.addComponent(new Label(CommonUtils.CASECATEGORY));
        gridLayout.addComponent(category);
        return gridLayout;
    }

    protected Table addToTable() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UDC_CONFIGURATION + ConstantsUtils.COMMA + LANDING_SCREEN, false);
            List<Object> resultList = getFieldsForSecurity(UDC_CONFIGURATION, LANDING_SCREEN);
            Object[] obj = HELPER_COLUMNS;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, "Add");
            if (tableResultCustom.getObjResult().length == 0) {
                table.setVisible(false);
            }
            table.markAsDirty();
            table.setContainerDataSource(searchResultbeans);
            table.setVisibleColumns(tableResultCustom.getObjResult());
            table.setColumnHeaders(tableResultCustom.getObjResultHeader());
            table.setPageLength(NumericConstants.TWENTY);
            table.setImmediate(true);
            table.setSelectable(true);
            table.setComponentError(null);
            table.setValidationVisible(false);

            table.setErrorHandler(new ErrorHandler() {

                /**
                 *
                 */
                private static final long serialVersionUID = 1L;

                public void error(com.vaadin.server.ErrorEvent event) {
                    return;
                }
            });

            table.setWidth("100%");
            table.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method is called when results value is changed
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final Property.ValueChangeEvent event) {
                    LOGGER.debug("In addResultsTable results.addValueChangeListener started");
                    resultsItemClick(event.getProperty().getValue());
                    LOGGER.debug("In addResultsTable results.addValueChangeListener Ended");
                }
            });

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return table;
    }

    /**
     * Results item click.
     *
     * @param obj the id
     */
    protected void resultsItemClick(final Object obj) {
        LOGGER.debug("resultsItemClick Method Started");
        if (obj == null) {
            masterSid = 0;
        } else {

            BeanItem<?> targetItem;
            if (obj instanceof BeanItem<?>) {
                LOGGER.debug("Object is Bean Item");
            } else if (obj instanceof BrandMasterDTO) {
                targetItem = new BeanItem<BrandMasterDTO>((BrandMasterDTO) obj);
                masterSid = ((BrandMasterDTO) targetItem.getBean()).getBrandMasterSid();
            } else if (obj instanceof HelperForm) {
                targetItem = new BeanItem<HelperForm>((HelperForm) obj);
                masterSid = ((HelperForm) targetItem.getBean()).getHelperTableSid();
            } else {
                masterSid = 0;
            }
        }
        LOGGER.debug("resultsItemClick Method Ended");
    }

    public void configureFields() {

        category.setNullSelectionAllowed(true);
        category.setNullSelectionItemId("- Select One -");
        category.setValidationVisible(true);
        category.setImmediate(true);
        category.setRequired(true);
        category.setRequiredError("Category should be selected");
        category.setContainerDataSource(udcLogic.getListNames());
        category.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {

                if (event.getProperty().getValue() != null) {

                    if (event.getProperty().getValue().equals(CommonUtils.BRAND)) {
                        gridLayout.removeAllComponents();
                        gridLayout.addComponent(new Label(CommonUtils.CASECATEGORY), 0, 0);
                        gridLayout.addComponent(category, NumericConstants.ONE, 0);
                        gridLayout.addComponent(brandIdLabel, NumericConstants.TWO, 0);
                        gridLayout.addComponent(brandId, NumericConstants.THREE, 0);
                        gridLayout.addComponent(new Label(""), NumericConstants.FOUR, 0);

                        gridLayout.addComponent(new Label(""), 0, NumericConstants.ONE);
                        gridLayout.addComponent(new Label(""), NumericConstants.ONE, NumericConstants.ONE);
                        gridLayout.addComponent(brandNameLabel, NumericConstants.TWO, NumericConstants.ONE);
                        gridLayout.addComponent(brandName, NumericConstants.THREE, NumericConstants.ONE);
                        gridLayout.addComponent(new Label(""), NumericConstants.FOUR, NumericConstants.ONE);

                        gridLayout.addComponent(new Label(""), 0, NumericConstants.TWO);
                        gridLayout.addComponent(new Label(""), NumericConstants.ONE, NumericConstants.TWO);
                        gridLayout.addComponent(displayBrandLabel, NumericConstants.TWO, NumericConstants.TWO);
                        gridLayout.addComponent(displayBrand, NumericConstants.THREE, NumericConstants.TWO);
                        gridLayout.addComponent(addButton(), NumericConstants.FOUR, NumericConstants.TWO);

                        table.removeAllItems();
                        searchResults = new LazyBeanItemContainer(BrandMasterDTO.class, new BrandContainer(category.getValue()), new BrandCriteria());
                        table.setContainerDataSource(searchResults);
                        table.setVisibleColumns(BRAND_HELPER_COLUMNS);
                        table.setColumnHeaders(BRAND_HELPER_HEADERS);

                    }else if (event.getProperty().getValue().equals(CommonUtils.FILE_TYPE)) {
                        gridLayout.removeAllComponents();
                        gridLayout.addComponent(new Label(CommonUtils.CASECATEGORY), 0, 0);
                        gridLayout.addComponent(category, NumericConstants.ONE, 0);

                        gridLayout.addComponent(valueLabel, NumericConstants.TWO, 0);
                        gridLayout.addComponent(description, NumericConstants.THREE, 0);
                        gridLayout.addComponent(aliasNameLabel, NumericConstants.FOUR, 0);
                        gridLayout.addComponent(aliasName, NumericConstants.FIVE, 0);
                        gridLayout.addComponent(addButton(), NumericConstants.SIX, 0);
//                                       
                        table.removeAllItems();
                        List<HelperForm> helperResult = udcLogic.getFileTypeDescription(category.getValue().toString());
                        searchResultbeans.addAll(helperResult);
                        table.setContainerDataSource(searchResultbeans);
                        table.setVisibleColumns(FILE_TYPE_HELPER_COLUMNS);
                        table.setColumnHeaders(FILE_TYPE_HELPER_HEADERS);

                    } else {
                        gridLayout.removeAllComponents();
                        gridLayout.addComponent(new Label(CommonUtils.CASECATEGORY), 0, 0);
                        gridLayout.addComponent(category, NumericConstants.ONE, 0);

                        gridLayout.addComponent(valueLabel, NumericConstants.TWO, 0);
                        gridLayout.addComponent(description, NumericConstants.THREE, 0);
                        gridLayout.addComponent(addButton(), NumericConstants.FOUR, 0);
//                                       
                        table.removeAllItems();
                        List<HelperForm> helperResult = udcLogic.getDescrition(category.getValue().toString());
                        searchResultbeans.addAll(helperResult);
                        table.setContainerDataSource(searchResultbeans);
                        table.setVisibleColumns(HELPER_COLUMNS);
                        table.setColumnHeaders(HELPER_HEADERS);
                    }
                    
                    description.setValue(StringUtils.EMPTY);
                    aliasName.setValue(StringUtils.EMPTY);
                    brandId.setValue(StringUtils.EMPTY);
                    brandName.setValue(StringUtils.EMPTY);
                    displayBrand.setValue(StringUtils.EMPTY);

                } else {

                }
            }

        });
        btnSave1.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                try {

                    binder.getFields();
                    binder.commit();
                    brandBinder.getFields();
                    brandBinder.commit();
                    String success = "";
                    try {
                        if (StringUtils.EMPTY.equals(description.getValue().trim()) && StringUtils.EMPTY.equals(brandId.getValue().trim()) && StringUtils.EMPTY.equals(brandName.getValue().trim())
                                && StringUtils.EMPTY.equals(displayBrand.getValue().trim())) {
                            NotificationUtils.getErrorNotification("Error", "Space should not be allowed");
                            description.setValue(StringUtils.EMPTY);
                            return;
                        }
                        if (category.getValue().equals(CommonUtils.BRAND)) {
                            success = udcLogic.saveBrandMaster(brandBinder, masterSid);

                        }else if (category.getValue().equals(CommonUtils.FILE_TYPE)) {
                            success = udcLogic.SaveFileTypeHelperTable(binder);

                        }else {
                            success = udcLogic.SaveHelperTable(binder);
                        }
                        if (success.equals("success")) {

                            if (category.getValue().equals(CommonUtils.BRAND)) {
                                table.removeAllItems();
                                searchResults.removeAllItems();
                                searchResults = new LazyBeanItemContainer(BrandMasterDTO.class, new BrandContainer(category.getValue()), new BrandCriteria());
                                table.setContainerDataSource(searchResults);
                                table.setVisibleColumns(BRAND_HELPER_COLUMNS);
                                table.setColumnHeaders(BRAND_HELPER_HEADERS);
                            }else if (category.getValue().equals(CommonUtils.FILE_TYPE)) {
                                searchResultbeans.removeAllItems();

                                List<HelperForm> helperResult = udcLogic.getFileTypeDescription(category.getValue().toString());
                                searchResultbeans.addAll(helperResult);
                            }else {
                                searchResultbeans.removeAllItems();

                                List<HelperForm> helperResult = udcLogic.getDescrition(category.getValue().toString());
                                searchResultbeans.addAll(helperResult);
                            }
                            Notification notif = new Notification("Category "
                                    + category.getValue().toString() + " Saved successfully",
                                    Notification.Type.HUMANIZED_MESSAGE);

                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(CommonUtils.MYSTYLE);
                            notif.show(Page.getCurrent());
                        }
                        if (success.equals("fail")) {
                            Notification notif = new Notification("Category "
                                    + category.getValue().toString() + " Save Failed ",
                                    Notification.Type.HUMANIZED_MESSAGE);

                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(CommonUtils.MYSTYLE);
                            notif.show(Page.getCurrent());

                        }
                    description.setValue(StringUtils.EMPTY);
                    aliasName.setValue(StringUtils.EMPTY);
                    brandId.setValue(StringUtils.EMPTY);
                    brandName.setValue(StringUtils.EMPTY);
                    displayBrand.setValue(StringUtils.EMPTY);
                        
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());

                    }

                } catch (CommitException e) {
                    LOGGER.error(e.getMessage());

                }
            }
        });

        description.setImmediate(true);
        description.setValidationVisible(true);
        description.addValidator(new RegexpValidator(ValidationUtils.alphaNumericCharsUDC, ValidationUtils.alphaNumericCharsMessage));
        description
                .addValidator(new StringLengthValidator(
                        " Value Should be less than 50 characters", 0,
                        NumericConstants.FIFTY, true));

    }

    private Button addButton() {
        // Commit button

        btnSave1.setWidth("75");        
        return btnSave1;
    }

    private Button deleteButton() {

        btnDelete.setWidth("75");
        btnDelete.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            public void error(com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        btnDelete.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {

                binder.getFields();

                try {

                    if (category.getValue().equals(CommonUtils.BRAND)) {

                        if (masterSid != 0) {
                            BrandMaster brandMaster = dao.getBrandMaster(masterSid);
                            if (brandMaster.getRecordLockStatus()) {
                                NotificationUtils.getWarningNotification("Delete", "You are not having permission to delete this record");
                            } else {
                                brandMaster.setInboundStatus("D");
                                udcLogic.deleteBrandTableByCode(brandMaster);
                                searchResults.removeAllItems();
                                searchResults = new LazyBeanItemContainer(BrandMasterDTO.class, new BrandContainer(category.getValue()), new BrandCriteria());
                                table.setContainerDataSource(searchResults);
                                table.setVisibleColumns(BRAND_HELPER_COLUMNS);
                                table.setColumnHeaders(BRAND_HELPER_HEADERS);
                                Notification notif = new Notification(" Category Value " + "BRAND" + " Deleted successfully", Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.MIDDLE_CENTER);
                                notif.setStyleName(CommonUtils.MYSTYLE);
                                notif.show(Page.getCurrent());
                            }
                        } else if (masterSid == 0) {
                            NotificationUtils.getErrorNotification("Error", "Please select Brand to delete");
                        } else {
                            Notification notif = new Notification("Deleted Failed. Category Value " + "BRAND" + " is currently used in Master Records", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(CommonUtils.MYSTYLE);
                            notif.show(Page.getCurrent());
                        }
                    } else if (category.getValue().equals(CommonUtils.FILE_TYPE)) {
                        HelperTable check = HelperTableLocalServiceUtil.getHelperTable(masterSid);
                        HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(masterSid);
                        if (check.getRefCount() == 0 && masterSid != 0) {
                            helperTable = udcLogic.deleteHelperTableByCode(masterSid);

                            searchResultbeans.removeAllItems();

                            List<HelperForm> helperResult = udcLogic.getFileTypeDescription(String.valueOf(category.getValue()));
                            searchResultbeans.addAll(helperResult);
                            Notification notif = new Notification(" Category Value " + helperTable.getListName() + " Deleted successfully", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(CommonUtils.MYSTYLE);
                            notif.show(Page.getCurrent());
                        } else if (masterSid == 0) {
                            Notification notif = new Notification("Deleted Failed", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(CommonUtils.MYSTYLE);
                            notif.show(Page.getCurrent());
                        } else {
                            Notification notif = new Notification("Deleted Failed. Category Value " + helperTable.getListName() + "is currently used in Master Records", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(CommonUtils.MYSTYLE);
                            notif.show(Page.getCurrent());
                        }
                    } else {
                        HelperTable check = HelperTableLocalServiceUtil.getHelperTable(masterSid);
                        HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(masterSid);
                        if (check.getRefCount() == 0 && masterSid != 0) {
                            helperTable = udcLogic.deleteHelperTableByCode(masterSid);

                            searchResultbeans.removeAllItems();

                            List<HelperForm> helperResult = udcLogic.getDescrition(String.valueOf(category.getValue()));
                            searchResultbeans.addAll(helperResult);
                            Notification notif = new Notification(" Category Value " + helperTable.getListName() + " Deleted successfully", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(CommonUtils.MYSTYLE);
                            notif.show(Page.getCurrent());
                        } else if (masterSid == 0) {
                            Notification notif = new Notification("Deleted Failed", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(CommonUtils.MYSTYLE);
                            notif.show(Page.getCurrent());
                        } else {
                            Notification notif = new Notification("Deleted Failed. Category Value " + helperTable.getListName() + "is currently used in Master Records", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName(CommonUtils.MYSTYLE);
                            notif.show(Page.getCurrent());
                        }
                    }

                } catch (Exception e) {
                    LOGGER.error(e.getMessage());

                }

            }
        });
        return btnDelete;
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UDC_CONFIGURATION + "," + LANDING_SCREEN);
            if (functionHM.get("add") != null && !((AppPermission) functionHM.get("add")).isFunctionFlag()) {
                btnSave1.setVisible(false);
            } else {
                btnSave1.setVisible(true);
            }
            if (functionHM.get("delete") != null && !((AppPermission) functionHM.get("delete")).isFunctionFlag()) {
                btnDelete.setVisible(false);
            } else {
                btnDelete.setVisible(true);
            }
            if (functionHM.get(CATEGORY_LABEL) != null && !((AppPermission) functionHM.get(CATEGORY_LABEL)).isFunctionFlag()) {
                category.setVisible(false);
            } else {
                category.setVisible(true);
            }

        } catch (PortalException ex) {
            LOGGER.error(ex.getMessage());
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    /**
     * Search forecast results to load table .
     *
     * @param forecastDTO the forecast dto
     * @return object of list or count
     */
    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<Object>();
        try {
            resultList = new SecurityImpl().fetchFieldsForSecurity(moduleName, tabName, null, null, null);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

}
