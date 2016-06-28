/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.itemgroup.util.UISecurityUtil;
import com.stpl.app.adminconsole.relationshipbuilder.dto.RelationshipBuilderDTO;
import com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic;
import com.stpl.app.adminconsole.relationshipbuilder.ui.lazyload.HierarchyNameContainer;
import com.stpl.app.adminconsole.relationshipbuilder.ui.lazyload.HierarchyNameCriteria;
import com.stpl.app.adminconsole.relationshipbuilder.ui.view.RelationshipBuilderView;
import com.stpl.app.adminconsole.relationshipbuilder.util.CommonUtils;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.FunctionNameUtil;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.CustomGridLayout;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class RelationshipBuilderIndex.
 *
 * @author nisanthan
 */
public final class RelationshipBuilderIndex extends CustomComponent implements View {

    /**
     * The space.
     */
    private Label space = new Label("&nbsp;", ContentMode.HTML);
    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantsUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RelationshipBuilderIndex.class);
    /**
     * The error msg.
     */
    private ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The relationship builder binder.
     */
    private CustomFieldGroup relationshipBuilderBinder;
    /**
     * The relationship builder dto.
     */
    private RelationshipBuilderDTO relationshipBuilderDTO;
    /**
     * The relationship name.
     */
    private TextField relationshipName = new TextField();
    /**
     * The relationship desc.
     */
    private TextField relationshipDesc = new TextField();
    /**
     * The hierarchy name ddlb.
     */
    private ComboBox hierarchyNameDdlb = new ComboBox();
    /**
     * The hierarchy.
     */
    private TextField hierarchy = new TextField();
    /**
     * The start date from.
     */
    private PopupDateField startDateFrom = new PopupDateField();
    /**
     * The start date to.
     */
    private PopupDateField startDateTo = new PopupDateField();
    /**
     * The creation date from.
     */
    private PopupDateField creationDateFrom = new PopupDateField();
    /**
     * The creation date to.
     */
    private PopupDateField creationDateTo = new PopupDateField();
    /**
     * The relationship type.
     */
    private OptionGroup relationshipType = new OptionGroup();
    /**
     * The btn search.
     */
    private Button btnSearch = new Button("SEARCH");
    /**
     * The Audit Search.
     */
    private Button auditSearch = new Button("AUDIT SEARCH");
    /**
     * The result table.
     */
    private final ExtPagedFilterTable resultTable = new ExtPagedFilterTable();
    /**
     * The btn reset.
     */
    private Button btnReset = new Button("RESET");
    /**
     * The btn add.
     */
    private Button btnAdd = new Button("ADD");
    /**
     * The btn edit.
     */
    private Button btnEdit = new Button("EDIT");
    /**
     * The btn view.
     */
    private Button btnView = new Button("VIEW");
    /**
     * The btn delete.
     */
    private Button btnDelete = new Button("DELETE");
    /**
     * The btn copy.
     */
    private Button btnCopy = new Button("COPY");

    /**
     * The rb system id.
     */
    private TextField rbSystemId = new TextField();
    /**
     * The logic.
     */
    private RelationBuilderLogic logic = new RelationBuilderLogic();
    /**
     * The results bean.
     */
    private BeanItemContainer<RelationshipBuilderDTO> resultsBean = new BeanItemContainer<RelationshipBuilderDTO>(RelationshipBuilderDTO.class);
    /**
     * The search results excel export.
     */
    private Button searchResultsExcelExport = new Button();
    /**
     * The version no.
     */
    private int version;
    /**
     * The hierarchyVersion.
     */
    private int hierarchyVersion;
    /**
     * The search criteria.
     */
    private String searchCriteria = ConstantsUtils.EMPTY;
    /**
     * Relationship Name
     */
    private String relationName;

    /**
     * Gets the hierarchy version.
     *
     * @return the hierarchy version
     */
    public int getHierarchyVersion() {
        return hierarchyVersion;
    }

    /**
     * Sets the hierarchy version.
     *
     * @param hierarchyVersion the new hierarchy version
     */
    public void setHierarchyVersion(final int hierarchyVersion) {
        this.hierarchyVersion = hierarchyVersion;
    }

    /**
     * Instantiates a new relationship builder index.
     *
     * @param relationshipBuilderBinder the relationship builder binder
     * @param relationshipBuilderDTO the relationship builder dto
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public RelationshipBuilderIndex(final CustomFieldGroup relationshipBuilderBinder, final RelationshipBuilderDTO relationshipBuilderDTO) throws SystemException, Exception {
        super();
        LOGGER.info("Entering RelationshipBuilderIndex constructor");
        this.relationshipBuilderBinder = relationshipBuilderBinder;
        this.relationshipBuilderDTO = relationshipBuilderDTO;
        init();
        addResponsiveSearchTableCollapse(resultTable);
        LOGGER.info("RelationshipBuilderIndex constructor Ended ");
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(final int version) {
        this.version = version;
    }

    /**
     * Gets the space.
     *
     * @return the space
     */
    public Label getSpace() {
        return space;
    }

    /**
     * Sets the space.
     *
     * @param space the space to set
     */
    public void setSpace(final Label space) {
        this.space = space;
    }

    /**
     * Gets the error msg.
     *
     * @return the errorMsg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets the error msg.
     *
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(final ErrorLabel errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Gets the relationship builder binder.
     *
     * @return the relationshipBuilderBinder
     */
    public CustomFieldGroup getRelationshipBuilderBinder() {
        return relationshipBuilderBinder;
    }

    /**
     * Sets the relationship builder binder.
     *
     * @param relationshipBuilderBinder the relationshipBuilderBinder to set
     */
    public void setRelationshipBuilderBinder(final CustomFieldGroup relationshipBuilderBinder) {
        this.relationshipBuilderBinder = relationshipBuilderBinder;
    }

    /**
     * Gets the relationship builder dto.
     *
     * @return the relationshipBuilderDTO
     */
    public RelationshipBuilderDTO getRelationshipBuilderDTO() {
        return relationshipBuilderDTO;
    }

    /**
     * Sets the relationship builder dto.
     *
     * @param relationshipBuilderDTO the relationshipBuilderDTO to set
     */
    public void setRelationshipBuilderDTO(final RelationshipBuilderDTO relationshipBuilderDTO) {
        this.relationshipBuilderDTO = relationshipBuilderDTO;
    }

    /**
     * Gets the relationship name.
     *
     * @return the relationshipName
     */
    public TextField getRelationshipName() {
        return relationshipName;
    }

    /**
     * Sets the relationship name.
     *
     * @param relationshipName the relationshipName to set
     */
    public void setRelationshipName(final TextField relationshipName) {
        this.relationshipName = relationshipName;
    }

    /**
     * Gets the relationship desc.
     *
     * @return the relationshipDesc
     */
    public TextField getRelationshipDesc() {
        return relationshipDesc;
    }

    /**
     * Sets the relationship desc.
     *
     * @param relationshipDesc the relationshipDesc to set
     */
    public void setRelationshipDesc(final TextField relationshipDesc) {
        this.relationshipDesc = relationshipDesc;
    }

    /**
     * Gets the hierarchy name ddlb.
     *
     * @return the hierarchyNameDdlb
     */
    public ComboBox getHierarchyNameDdlb() {
        return hierarchyNameDdlb;
    }

    /**
     * Sets the hierarchy name ddlb.
     *
     * @param hierarchyNameDdlb the hierarchyNameDdlb to set
     */
    public void setHierarchyNameDdlb(final ComboBox hierarchyNameDdlb) {
        this.hierarchyNameDdlb = hierarchyNameDdlb;
    }

    /**
     * Gets the hierarchy.
     *
     * @return the hierarchy
     */
    public TextField getHierarchy() {
        return hierarchy;
    }

    /**
     * Sets the hierarchy.
     *
     * @param hierarchy the hierarchy to set
     */
    public void setHierarchy(final TextField hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * Gets the start date from.
     *
     * @return the startDateFrom
     */
    public PopupDateField getStartDateFrom() {
        return startDateFrom;
    }

    /**
     * Sets the start date from.
     *
     * @param startDateFrom the startDateFrom to set
     */
    public void setStartDateFrom(final PopupDateField startDateFrom) {
        this.startDateFrom = startDateFrom;
    }

    /**
     * Gets the start date to.
     *
     * @return the startDateTo
     */
    public PopupDateField getStartDateTo() {
        return startDateTo;
    }

    /**
     * Sets the start date to.
     *
     * @param startDateTo the startDateTo to set
     */
    public void setStartDateTo(final PopupDateField startDateTo) {
        this.startDateTo = startDateTo;
    }

    /**
     * Gets the creation date from.
     *
     * @return the creationDateFrom
     */
    public PopupDateField getCreationDateFrom() {
        return creationDateFrom;
    }

    /**
     * Sets the creation date from.
     *
     * @param creationDateFrom the creationDateFrom to set
     */
    public void setCreationDateFrom(final PopupDateField creationDateFrom) {
        this.creationDateFrom = creationDateFrom;
    }

    /**
     * Gets the creation date to.
     *
     * @return the creationDateTo
     */
    public PopupDateField getCreationDateTo() {
        return creationDateTo;
    }

    /**
     * Sets the creation date to.
     *
     * @param creationDateTo the creationDateTo to set
     */
    public void setCreationDateTo(final PopupDateField creationDateTo) {
        this.creationDateTo = creationDateTo;
    }

    /**
     * Gets the relationship type.
     *
     * @return the relationshipType
     */
    public OptionGroup getRelationshipType() {
        return relationshipType;
    }

    /**
     * Sets the relationship type.
     *
     * @param relationshipType the relationshipType to set
     */
    public void setRelationshipType(final OptionGroup relationshipType) {
        this.relationshipType = relationshipType;
    }

    /**
     * Gets the btn search.
     *
     * @return the btnSearch
     */
    public Button getBtnSearch() {
        return btnSearch;
    }

    /**
     * Sets the btn search.
     *
     * @param btnSearch the btnSearch to set
     */
    public void setBtnSearch(final Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    /**
     * Gets the audit search.
     *
     * @return the auditSearch
     */
    public Button getAuditSearch() {
        return auditSearch;
    }

    /**
     * Sets the audit search.
     *
     * @param auditSearch the auditSearch to set
     */
    public void setAuditSearch(final Button auditSearch) {
        this.auditSearch = auditSearch;
    }

    /**
     * Gets the result table.
     *
     * @return the resultTable
     */
    public ExtPagedFilterTable getResultTable() {
        return resultTable;
    }

    /**
     * Gets the btn reset.
     *
     * @return the btnReset
     */
    public Button getBtnReset() {
        return btnReset;
    }

    /**
     * Sets the btn reset.
     *
     * @param btnReset the btnReset to set
     */
    public void setBtnReset(final Button btnReset) {
        this.btnReset = btnReset;
    }

    /**
     * Gets the btn add.
     *
     * @return the btnAdd
     */
    public Button getBtnAdd() {
        return btnAdd;
    }

    /**
     * Sets the btn add.
     *
     * @param btnAdd the btnAdd to set
     */
    public void setBtnAdd(final Button btnAdd) {
        this.btnAdd = btnAdd;
    }

    /**
     * Gets the btn edit.
     *
     * @return the btnEdit
     */
    public Button getBtnEdit() {
        return btnEdit;
    }

    /**
     * Sets the btn edit.
     *
     * @param btnEdit the btnEdit to set
     */
    public void setBtnEdit(final Button btnEdit) {
        this.btnEdit = btnEdit;
    }

    /**
     * Gets the btn view.
     *
     * @return the btnView
     */
    public Button getBtnView() {
        return btnView;
    }

    /**
     * Sets the btn view.
     *
     * @param btnView the btnView to set
     */
    public void setBtnView(final Button btnView) {
        this.btnView = btnView;
    }

    /**
     * Gets the btn delete.
     *
     * @return the btnDelete
     */
    public Button getBtnDelete() {
        return btnDelete;
    }

    /**
     * Sets the btn delete.
     *
     * @param btnDelete the btnDelete to set
     */
    public void setBtnDelete(final Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    /**
     * Gets the rb system id.
     *
     * @return the rbSystemId
     */
    public TextField getRbSystemId() {
        return rbSystemId;
    }

    /**
     * Sets the rb system id.
     *
     * @param rbSystemId the rbSystemId to set
     */
    public void setRbSystemId(final TextField rbSystemId) {
        this.rbSystemId = rbSystemId;
    }

    /**
     * Gets the logic.
     *
     * @return the logic
     */
    public RelationBuilderLogic getLogic() {
        return logic;
    }

    /**
     * Sets the logic.
     *
     * @param logic the logic to set
     */
    public void setLogic(final RelationBuilderLogic logic) {
        this.logic = logic;
    }

    /**
     * Gets the results bean.
     *
     * @return the resultsBean
     */
    public BeanItemContainer<RelationshipBuilderDTO> getResultsBean() {
        return resultsBean;
    }

    /**
     * Sets the results bean.
     *
     * @param resultsBean the resultsBean to set
     */
    public void setResultsBean(final BeanItemContainer<RelationshipBuilderDTO> resultsBean) {
        this.resultsBean = resultsBean;
    }

    /**
     * Gets the search results excel export.
     *
     * @return the searchResultsExcelExport
     */
    public Button getSearchResultsExcelExport() {
        return searchResultsExcelExport;
    }

    /**
     * Sets the search results excel export.
     *
     * @param searchResultsExcelExport the searchResultsExcelExport to set
     */
    public void setSearchResultsExcelExport(final Button searchResultsExcelExport) {
        this.searchResultsExcelExport = searchResultsExcelExport;
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void init() throws SystemException, Exception {
        LOGGER.info("Entering init Method");
        space.setHeight("20");
        setCompositionRoot(addToContent());
        securityForButtons();

        LOGGER.info(" init Method Ended");
    }

    /**
     * Adds the to content.
     *
     * @return the component
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private Component addToContent() throws SystemException, Exception {
        LOGGER.info("Entering addToContent Method");
        final VerticalLayout content = new VerticalLayout();
        space.setHeight("20");

        content.addComponent(errorMsg);

        content.setSpacing(true);
        content.setMargin(true);

        final VerticalLayout layout = new VerticalLayout();
        final Panel searchpanel = new Panel("Relationship Search Criteria");
        final Panel tablePanel = new Panel("Results");
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addComponent(relationshipSearchSection());

        searchpanel.setContent(layout);
        content.addComponent(searchpanel);

        content.addComponent(addSearchButtons());

        content.addComponent(ResponsiveUtils.addNaviButton(resultTable));
        tablePanel.setContent(relationshipResultTable());
        content.addComponent(tablePanel);
        content.addComponent(ResponsiveUtils.getResponsiveControls(resultTable.createControls()));

        content.addComponent(relationshipBuilderButtons());
        getBinder();
        configureFields();
        LOGGER.info("addToContent Method Returns Layout");
        return content;
    }

    /**
     * Adds the search buttons.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout addSearchButtons() {
        final HorizontalLayout searchButtons = new HorizontalLayout();
        searchButtons.setSpacing(true);
        searchButtons.addComponent(btnSearch);
        searchButtons.addComponent(auditSearch);
        return searchButtons;
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {
        LOGGER.info("Entering getBinder Method");
        relationshipBuilderBinder.bindMemberFields(this);
        relationshipBuilderBinder.setItemDataSource(new BeanItem<RelationshipBuilderDTO>(relationshipBuilderDTO));
        relationshipBuilderBinder.setBuffered(true);
        relationshipBuilderBinder.setErrorDisplay(errorMsg);
        LOGGER.info(" getBinder Method returns relationshipBuilderBinder");
        return relationshipBuilderBinder;
    }

    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void configureFields() throws SystemException, Exception {
        LOGGER.info("Entering configureFields Method");
        startDateFrom.setDescription(ConstantsUtils.DATE_DES);
        startDateTo.setDescription(ConstantsUtils.DATE_DES);
        creationDateFrom.setDescription(ConstantsUtils.DATE_DES);
        creationDateTo.setDescription(ConstantsUtils.DATE_DES);

        relationshipName.focus();
        searchResultsExcelExport.setDescription(ConstantsUtils.EXCEL_EXPORT);
        relationshipName.setImmediate(true);

        relationshipName.setValidationVisible(true);
        relationshipName.addValidator(new StringLengthValidator("Relationship Name length should be less than 100 characters", 0, 100, true));
        relationshipName.setImmediate(true);
        relationshipDesc.setImmediate(true);
        relationshipDesc.setValidationVisible(true);
        relationshipDesc.addValidator(new StringLengthValidator("Relationship Description length should be less than 100 characters", 0, 100, true));
        relationshipName.focus();
        relationshipName.setTabIndex(1);
        relationshipDesc.setTabIndex(2);
        relationshipType.setTabIndex(3);
        hierarchyNameDdlb.setTabIndex(4);
        startDateFrom.setTabIndex(5);
        startDateTo.setTabIndex(6);
        creationDateFrom.setTabIndex(7);
        creationDateTo.setTabIndex(8);
        btnSearch.setTabIndex(9);
        btnReset.setTabIndex(10);
        btnAdd.setTabIndex(11);
        btnEdit.setTabIndex(12);
        btnView.setTabIndex(13);
        btnDelete.setTabIndex(14);
        btnCopy.setTabIndex(15);

        btnReset.setEnabled(true);
        btnEdit.setEnabled(true);
        btnView.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCopy.setEnabled(true);

        hierarchyNameDdlb.setPageLength(7);
        hierarchyNameDdlb.setImmediate(true);
        hierarchyNameDdlb.setNullSelectionAllowed(true);
        hierarchyNameDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);
        hierarchyNameDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        hierarchyNameDdlb.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        hierarchyNameDdlb.markAsDirty();

        /**
         * value change listener for hierarchy name ddlb
         */
        hierarchyNameDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Called on Property Value Change
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.info("In configureFields hierarchyNameDdlb.addValueChangeListener started");
                hierarchyOnChangeEvent();
                LOGGER.info("In configureFields hierarchyNameDdlb.addValueChangeListener Ended");

            }
        });

        relationshipType.addItem("Primary");
        relationshipType.addItem("Secondary");
        relationshipType.select("Primary");
        relationshipType.addStyleName("horizontal");
        LOGGER.info("In configureFields loadHierarchy started");
        loadHierarchy();
        LOGGER.info("In configureFields loadHierarchy Ended");
        startDateFrom.setDateFormat(ConstantsUtils.DATE_FORMAT);
        creationDateFrom.setDateFormat(ConstantsUtils.DATE_FORMAT);
        startDateTo.setDateFormat(ConstantsUtils.DATE_FORMAT);
        creationDateTo.setDateFormat(ConstantsUtils.DATE_FORMAT);
        startDateFrom.setImmediate(true);
        startDateTo.setImmediate(true);
        creationDateFrom.setImmediate(true);
        creationDateTo.setImmediate(true);
        /**
         * value change listener for starte date from field
         */
        startDateFrom.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    LOGGER.info("In configureFields startDateFrom.addValueChangeListener started");
                    startDateFrom.setValue(CommonUtils.convert2DigitTo4DigitYearFormat(startDateFrom.getValue()));
                    LOGGER.info("In configureFields startDateFrom.addValueChangeListener Ended");
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    LOGGER.error(ex);
                }
            }
        });
        /**
         * value change listener for start date to field
         */
        startDateTo.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    LOGGER.info("In configureFields startDateTo.addValueChangeListener started");
                    startDateTo.setValue(CommonUtils.convert2DigitTo4DigitYearFormat(startDateTo.getValue()));
                    LOGGER.info("In configureFields startDateTo.addValueChangeListener Ended");
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    LOGGER.error(ex);
                }
            }
        });
        startDateTo.addValidator(new DateValidator("Start Date From should be before Start Date To"));
        /**
         * value change listener for creation date from field
         */
        creationDateFrom.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    LOGGER.info("In configureFields creationDateFrom.addValueChangeListener started");
                    creationDateFrom.setValue(CommonUtils.convert2DigitTo4DigitYearFormat(creationDateFrom.getValue()));
                    LOGGER.info("In configureFields creationDateFrom.addValueChangeListener Ended");
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    LOGGER.error(ex);
                }
            }
        });
        /**
         * value change listener foe creation date
         */
        creationDateTo.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    LOGGER.info("In configureFields creationDateTo.addValueChangeListener started");
                    creationDateTo.setValue(CommonUtils.convert2DigitTo4DigitYearFormat(creationDateTo.getValue()));
                    LOGGER.info("In configureFields creationDateTo.addValueChangeListener Ended");
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    LOGGER.error(ex);
                }
            }
        });
        creationDateTo.addValidator(new CreationDateValidator("Creation Date From should be before Creation Date To"));
        addListeners();
        /**
         * click listener for reset button
         */
        btnReset.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields btnReset.addClickListener started");
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values?", new MessageBoxListener() {
                    /**
                     * Click Listener for Message Box
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            resetButtonClickLogic();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                LOGGER.info("In configureFields btnReset.addClickListener Ended");
            }
        });
        /**
         * click listener for delete button
         */
        btnDelete.addClickListener(new ClickListener() {
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In configureFields btnDelete.addClickListener started");
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.VERSIONNO, version);
                deleteButtonClickLogic(event);
                LOGGER.info("In configureFields btnDelete.addClickListener Ended");
            }
        });

        /**
         * click listener for copy button
         */
        btnCopy.addClickListener(new ClickListener() {
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In configureFields btnCopy.addClickListener started");

                if (rbSystemId.getValue() == null || StringUtils.EMPTY.equals(rbSystemId.getValue()) || ConstantsUtils.ZERO.equals(rbSystemId.getValue())) {
                    MessageBox.showPlain(Icon.ERROR, "No Relationship Selected", "No Relationship has been selected. Please select a Relationship and try again.", ButtonId.OK);
                } else {
                    try {
                        final int systemId = Integer.valueOf(rbSystemId.getValue());
                        final CopyPopup copyPopup = new CopyPopup(systemId);
                        UI.getCurrent().addWindow(copyPopup);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
                LOGGER.info("In configureFields btnCopy.addClickListener Ended");
            }
        });

        searchResultsExcelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        searchResultsExcelExport.setStyleName("link");
        /**
         * click listener for search reults excel
         */
        searchResultsExcelExport.addClickListener(new ClickListener() {
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In configureFields searchResultsExcelExport.addClickListener started");
                if (resultTable.size() > ConstantsUtils.ZERO_NUM) {
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(resultTable), "RelationShip Builder Search Results", "RelationShip Builder", "RelationShip Builder Search Results.xls", false);
                    excel.export();
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.info("In configureFields searchResultsExcelExport.addClickListener Ended");
            }
        });
        LOGGER.info("configureFields Method ends Here");
    }

    /**
     * Adds the listeners.
     */
    private void addListeners() {
        /**
         * click listener for add button
         */
        btnAdd.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields btnAdd.addClickListener navigateTo RelationshipBuilderView");
                VaadinSession.getCurrent().setAttribute("relationshipSystemId", 0);
                VaadinSession.getCurrent().setAttribute("selectedHierarchySessionId", 0);
                VaadinSession.getCurrent().setAttribute("fromViewPage", "Add");
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.VERSIONNO, version);
                getUI().getNavigator().navigateTo(RelationshipBuilderView.NAME);
                LOGGER.info("In configureFields btnAdd.addClickListener Ended");
            }
        });
        /**
         * click listener for edit button
         */
        btnEdit.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields btnEdit.addValueChangeListener started");
                if (rbSystemId.getValue() == null || StringUtils.EMPTY.equals(rbSystemId.getValue()) || ConstantsUtils.ZERO.equals(rbSystemId.getValue())) {
                    MessageBox.showPlain(Icon.ERROR, "No Relationship Selected", "No Relationship has been selected. Please select a Relationship and try again.", ButtonId.OK);
                } else {
                    try {
                        final int systemId = Integer.valueOf(rbSystemId.getValue());
                        final int latestVersion = logic.getExistingVersion(systemId);

                        if (latestVersion == version) {
                            VaadinSession.getCurrent().setAttribute("relationshipSystemId", Integer.parseInt(rbSystemId.getValue().toString().replaceAll(",", ConstantsUtils.EMPTY)));
                            VaadinSession.getCurrent().setAttribute("fromViewPage", "Edit");
                            VaadinSession.getCurrent().setAttribute(ConstantsUtils.VERSIONNO, version);
                            VaadinSession.getCurrent().setAttribute("hierarchyVersion", hierarchyVersion);
                            getUI().getNavigator().navigateTo(RelationshipBuilderView.NAME);
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Error", "User Can Only Edit latest Version.");
                        }
                    } catch (SystemException ex) {
                        LOGGER.error(ex);
                    } catch (PortalException ex) {
                        LOGGER.error(ex);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
                LOGGER.info("In configureFields btnEdit.addValueChangeListener Ended");
            }
        });
        /**
         * click listener for view button
         */
        btnView.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields btnView.addClickListener started");
                if (rbSystemId.getValue() == null || StringUtils.EMPTY.equals(rbSystemId.getValue()) || ConstantsUtils.ZERO.equals(rbSystemId.getValue())) {
                    MessageBox.showPlain(Icon.ERROR, "No Relationship Selected", "No Relationship has been selected. Please select a Relationship and try again.", ButtonId.OK);
                } else {

                    VaadinSession.getCurrent().setAttribute("relationshipSystemId", Integer.parseInt(rbSystemId.getValue().replaceAll(",", ConstantsUtils.EMPTY)));
                    VaadinSession.getCurrent().setAttribute("fromViewPage", "Yes");

                    VaadinSession.getCurrent().setAttribute("hierarchyVersion", hierarchyVersion);
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.VERSIONNO, version);
                    getUI().getNavigator().navigateTo(RelationshipBuilderView.NAME);

                }
                LOGGER.info("In configureFields btnView.addClickListener Ended");
            }
        });
        /**
         * click listener for search button
         */
        btnSearch.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields btnSearch.addClickListener started");
                if (StringUtils.isBlank(relationshipName.getValue()) && StringUtils.isBlank(relationshipDesc.getValue()) && (hierarchyNameDdlb.getValue() == null || String.valueOf(hierarchyNameDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                        && creationDateFrom.getValue() == null && creationDateTo.getValue() == null && startDateTo.getValue() == null && startDateFrom.getValue() == null && relationshipType.getValue() == null) {

                    MessageBox.showPlain(Icon.ERROR, "No Search Criteria", "No search criteria were found. Please enter search criteria and try again", ButtonId.OK);

                } else {
                    searchCriteria = "search";
                    resultTable.removeAllItems();
                    btnDelete.setEnabled(true);
                    btnCopy.setEnabled(true);
                    searchButtonClickLogic(event, ConstantsUtils.SEARCH);
                }
                LOGGER.info("In configureFields btnSearch.addClickListener Ended");
            }
        });
        auditSearch.addClickListener(new ClickListener() {
            /**
             * Method is called when search button is clicked
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In configureFields auditsearch.addClickListener started");

                if (StringUtils.isBlank(String.valueOf(relationshipName.getValue())) && StringUtils.isBlank(String.valueOf(relationshipDesc.getValue())) && (hierarchyNameDdlb.getValue() == null || String.valueOf(hierarchyNameDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))) {
                    AbstractNotificationUtils.getErrorNotification("Search Error", "No search criteria have been entered.  Please enter search criteria and try again.");
                    return;
                }
                rbSystemId = new TextField();
                resultTable.removeAllItems();
                btnDelete.setEnabled(false);
                btnCopy.setEnabled(false);
                searchCriteria = "auditSearch";
                searchButtonClickLogic(event, ConstantsUtils.AUDIT_SEARCH);
                LOGGER.info("In configureFields auditsearch.addClickListener Ended");
            }
        });
    }

    private void securityForButtons() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(com.stpl.app.util.ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionRbHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.RELATIONSHIP_BUILDER + "," + "Index Screen");
        if (functionRbHM.get(FunctionNameUtil.DELETE) != null && ((AppPermission) functionRbHM.get(FunctionNameUtil.DELETE)).isFunctionFlag()) {

            btnDelete.setVisible(true);
        } else {
            btnDelete.setVisible(false);
        }

        if (functionRbHM.get(FunctionNameUtil.ADD) != null && ((AppPermission) functionRbHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {

            btnAdd.setVisible(true);
        } else {
            btnAdd.setVisible(false);
        }
        if (functionRbHM.get(FunctionNameUtil.EDIT) != null && ((AppPermission) functionRbHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {

            btnEdit.setVisible(true);
        } else {
            btnEdit.setVisible(false);
        }
        if (functionRbHM.get(FunctionNameUtil.VIEW) != null && ((AppPermission) functionRbHM.get(FunctionNameUtil.VIEW)).isFunctionFlag()) {

            btnView.setVisible(true);
        } else {
            btnView.setVisible(false);
        }

    }

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings("rawtypes")
    private class DateValidator extends AbstractValidator {

        /**
         * Instantiates a new date validator.
         */
        public DateValidator() {
            super(ConstantsUtils.EMPTY);
        }

        /**
         * Instantiates a new date validator.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * (non-Javadoc).
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            LOGGER.info("validate Method started");
            if (startDateFrom.getValue() != null && startDateTo.getValue() != null) {
                if (startDateFrom.getValue().after(startDateTo.getValue())) {
                    throw new Validator.InvalidValueException("Start Date To should be greater than Start Date From");
                } else if (startDateFrom.getValue().equals(startDateTo.getValue())) {
                    throw new Validator.InvalidValueException("Start Date From and Start Date To should not be equal");
                }
            }
            LOGGER.info("validate Method Ended");
        }

        /**
         * (non-Javadoc).
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.info("isValidValue Method started");
            boolean flag;
            if (startDateFrom.getValue() == null || startDateTo.getValue() == null) {
                flag = true;
            } else {
                flag = startDateFrom.getValue().compareTo(startDateTo.getValue()) <= 0;
            }
            LOGGER.info("isValidValue Method returns true");
            return flag;
        }

        /**
         * (non-Javadoc).
         *
         * @return the type
         * @see com.vaadin.data.validator.AbstractValidator#getType()
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * Adds the space.
     *
     * @return the custom grid layout
     */
    private static CustomGridLayout addSpace() {
        LOGGER.info("addSpace Method started");
        final CustomGridLayout gridLayout = new CustomGridLayout(1, 1);
        gridLayout.addComponent(new Label(ConstantsUtils.EMPTY));
        LOGGER.info("addSpace Method returns Layout");
        return gridLayout;
    }

    private HorizontalLayout relationshipSearchSection() {
        LOGGER.info("relationshipSearchSection Method started");

        final HorizontalLayout hlayout1 = new HorizontalLayout();

        hlayout1.setStyleName(ConstantsUtils.RESPONSIVE_GRID);
        final CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(new Label("Relationship Name:"));
        cssLayout.addComponent(relationshipName);
        cssLayout.addComponent(new Label("Hierarchy Name:"));
        cssLayout.addComponent(hierarchyNameDdlb);
        cssLayout.addComponent(new Label("Relationship Description:"));
        cssLayout.addComponent(relationshipDesc);
        cssLayout.addComponent(new Label("Start Date From:"));

        cssLayout.addComponent(startDateFrom);
        cssLayout.addComponent(new Label("Start Date To:"));
        cssLayout.addComponent(startDateTo);
        cssLayout.addComponent(new Label("Relationship Type:"));
        cssLayout.addComponent(relationshipType);

        cssLayout.addComponent(new Label("Creation Date From:"));

        cssLayout.addComponent(creationDateFrom);
        cssLayout.addComponent(new Label("Creation Date To:"));
        cssLayout.addComponent(creationDateTo);
        hlayout1.addComponent(cssLayout);

        LOGGER.info("relationshipSearchSection Method returns panel");
        return hlayout1;
    }

    /**
     * Relationship result table.
     *
     * @return the filter table
     */
    public ExtFilterTable relationshipResultTable() {
        LOGGER.info("relationshipResultTable Method started");
        resultTable.markAsDirty();
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setContainerDataSource(resultsBean);
        resultTable.setVisibleColumns(CommonUIUtil.AC_SEARCH_RESULT_COLUMNS);

        resultTable.setColumnHeaders(CommonUIUtil.AC_SEARCH_RESULT_HEADER);
        resultTable.setPageLength(5);
        resultTable.setSizeFull();
        resultTable.setImmediate(true);
        resultTable.setSelectable(true);
        resultTable.sinkItemPerPageWithPageLength(false);
        setDefaultTableWidth(resultTable, resultsBean);

        resultTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Called on Property Value Change
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.info("In relationshipResultTable resultTable.addValueChangeListener started");
                resultsItemClick(event.getProperty().getValue());
                LOGGER.info("In relationshipResultTable resultTable.addValueChangeListener Ended");
            }
        });
        /**
         * error handler for result table
         */
        resultTable.setErrorHandler(new ErrorHandler() {
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });

        final Object[] obj = new Object[]{"startDate", "createdDate", "modifiedDate"};
        LOGGER.info("relationshipResultTable Method returns Filtertable");
        return resultTable;
    }

    /**
     * Adds the smallgrid from.
     *
     * @param from the from
     * @return the grid layout
     */
    private GridLayout addSmallgridFrom(final PopupDateField from) {
        LOGGER.info("addSmallgridFrom Method started");
        final CustomGridLayout gridLayout = new CustomGridLayout(2, 1);
        gridLayout.setSpacing(true);
        gridLayout.addComponent(new Label("From"));
        gridLayout.addComponent(from);
        gridLayout.setSpacing(true);
        LOGGER.info("addSmallgridFrom Method returns layout");
        return gridLayout;
    }

    /**
     * Adds the small togrid from.
     *
     * @param toDate the to date
     * @return the grid layout
     */
    private GridLayout addSmallTogridFrom(final PopupDateField toDate) {
        LOGGER.info("addSmallTogridFrom Method started");
        final CustomGridLayout gridLayout = new CustomGridLayout(2, 1);
        gridLayout.setSpacing(true);
        gridLayout.addComponent(new Label("To&nbsp&nbsp&nbsp&nbsp&nbsp", ContentMode.HTML));
        gridLayout.addComponent(toDate);
        gridLayout.setSpacing(true);
        LOGGER.info("addSmallTogridFrom Method returns Layout");
        return gridLayout;
    }

    /**
     * Relationship builder buttons.
     *
     * @return the grid layout
     */
    private HorizontalLayout relationshipBuilderButtons() {
        LOGGER.info("relationshipBuilderButtons Method started");

        final HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setStyleName("responsiveTabGrid");

        hLayout.setSpacing(true);
        hLayout.addComponent(btnReset);
        hLayout.addComponent(btnAdd);
        hLayout.addComponent(btnEdit);
        hLayout.addComponent(btnView);
        hLayout.addComponent(btnDelete);
        hLayout.addComponent(btnCopy);
        hLayout.addComponent(searchResultsExcelExport);
        LOGGER.info("relationshipBuilderButtons Method returns Layout");
        return hLayout;
    }

    /**
     * Load hierarchy.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void loadHierarchy() throws SystemException, Exception {
        LOGGER.info("loadHierarchy Method started");
        final LazyContainer hierarchyNameContainer = new LazyContainer(HelperDTO.class, new HierarchyNameContainer(null), new HierarchyNameCriteria());
        hierarchyNameContainer.setMinFilterLength(0);
        hierarchyNameDdlb.setContainerDataSource(hierarchyNameContainer);
        hierarchyNameDdlb.select(new HelperDTO(ConstantsUtils.SELECT_ONE));
        LOGGER.info("loadHierarchy Method ended");

    }

    /**
     * Hierarchy on change event.
     *
     */
    protected void hierarchyOnChangeEvent() {
        LOGGER.info("hierarchyOnChangeEvent Method started");
        if (hierarchyNameDdlb.getValue() == null || String.valueOf(hierarchyNameDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE)) {
            hierarchy.setValue(null);
        } else {
            final Object companyValue = ((HelperDTO) hierarchyNameDdlb.getValue()).getId();
            hierarchy.setValue(companyValue.toString());
            final int selectedHierarchySysId = Integer.valueOf(companyValue.toString());
            VaadinSession.getCurrent().setAttribute("selectedHierarchySessionId", selectedHierarchySysId);
        }
        LOGGER.info("hierarchyOnChangeEvent Method Ended");
    }

    /**
     * Search button click logic.
     *
     * @param event the event
     * @param flag the flag
     */
    protected void searchButtonClickLogic(final Button.ClickEvent event, final String flag) {
        LOGGER.info("searchButtonClickLogic Method started");
        try {
            relationshipBuilderBinder.commit();
            resultsBean.removeAllItems();
            final List<RelationshipBuilderDTO> searchResults = new ArrayList<RelationshipBuilderDTO>();
            if (searchResults.size() > ConstantsUtils.ZERO_NUM) {
                enableActionButton();
                resultsBean.addAll(searchResults);
                resultTable.firePagedChangedEvent();
                resultTable.setPageLength(resultTable.getPageLength() + 1);
                resultTable.setPageLength(resultTable.getPageLength() - 1);
                CommonUIUtils.getMessageNotification("Search Completed");

            } else {
                MessageBox.showPlain(Icon.ERROR, "No Matching Records", "There were no records matching the search Criteria. Please try Again", ButtonId.OK);
            }
        } catch (CustomFieldGroup.CommitException ex) {

            LOGGER.error(ex);
        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
            LOGGER.error(ex);
        }
        LOGGER.info("searchButtonClickLogic Method Ended");
    }

    /**
     * Reset button click logic.
     */
    protected void resetButtonClickLogic() {
        LOGGER.info("resetButtonClickLogic Method started");
        relationshipBuilderBinder.setItemDataSource(new BeanItem<RelationshipBuilderDTO>(new RelationshipBuilderDTO()));
        relationshipBuilderBinder.getErrorDisplay().clearError();
        rbSystemId.setValue("0");
        resultsBean.removeAllItems();
        resultTable.firePagedChangedEvent();
        relationshipName.setValue(ConstantsUtils.EMPTY);
        relationshipType.select("Primary");
        relationshipDesc.setValue(ConstantsUtils.EMPTY);
        hierarchyNameDdlb.setValue(new HelperDTO(ConstantsUtils.SELECT_ONE));
        startDateFrom.setValue(null);
        creationDateFrom.setValue(null);
        startDateTo.setValue(null);
        creationDateTo.setValue(null);
        searchCriteria = ConstantsUtils.EMPTY;
        LOGGER.info("resetButtonClickLogic Method Ended");
    }

    /**
     * Results item click.
     *
     * @param selectedItem the selected item
     */
    protected void resultsItemClick(final Object selectedItem) {
        LOGGER.info("resultsItemClick Method started");
        if (selectedItem == null) {
            rbSystemId.setValue("0");
            btnDelete.setEnabled(false);
            btnCopy.setEnabled(false);
            hierarchyVersion = 0;
            relationName = StringUtils.EMPTY;
        } else {
            enableActionButton();
            BeanItem<?> targetItem;
            if (selectedItem instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) selectedItem;
            } else if (selectedItem instanceof RelationshipBuilderDTO) {
                targetItem = new BeanItem<RelationshipBuilderDTO>((RelationshipBuilderDTO) selectedItem);
            } else {
                targetItem = (BeanItem<?>) selectedItem;
            }
            final int rbSysId = ((RelationshipBuilderDTO) targetItem.getBean()).getRbSystemId();
            rbSystemId.setValue(String.valueOf(rbSysId));
            version = ((RelationshipBuilderDTO) targetItem.getBean()).getVersionNo();
            hierarchyVersion = ((RelationshipBuilderDTO) targetItem.getBean()).getHierarchyVersionNo();
            relationName = ((RelationshipBuilderDTO) targetItem.getBean()).getResultRSName();
        }
        LOGGER.info("resultsItemClick Method ended");
    }

    /**
     * Delete button click logic.
     *
     * @param event the event
     */
    protected void deleteButtonClickLogic(final Button.ClickEvent event) {
        LOGGER.info("deleteButtonClickLogic Method started");
        if (rbSystemId.getValue() == null || StringUtils.EMPTY.equals(rbSystemId.getValue()) || ConstantsUtils.ZERO.equals(rbSystemId.getValue())) {
            MessageBox.showPlain(Icon.ERROR, "No Relationship Selected", "No Relationship has been selected. Please select a Relationship and try again.", ButtonId.OK);
        } else {
            MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to delete record " + relationName + "?", new MessageBoxListener() {
                /**
                 * Click Listener for Message Box Buttons
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    if (buttonId.name().equals("YES")) {
                        try {
                            final String deletedRbName = logic.deleteRelationship(Integer.parseInt(rbSystemId.getValue().replaceAll(",", ConstantsUtils.EMPTY)));
                            if (deletedRbName != null && !ConstantsUtils.EMPTY.equals(deletedRbName)) {
                                final Notification notif = new Notification(" " + deletedRbName + "' has been deleted Successfully", Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.MIDDLE_CENTER);

                                notif.setDelayMsec(2000);
                                notif.show(Page.getCurrent());

                                resultsBean.removeAllItems();
                                final List<RelationshipBuilderDTO> relationshipsInfo = new ArrayList<RelationshipBuilderDTO>();
                                resultsBean.addAll(relationshipsInfo);
                            }
                        } catch (SystemException ex) {
                            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                            LOGGER.error(ex);
                        } catch (PortalException ex) {
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                            LOGGER.error(ex);
                        } catch (Exception ex) {
                            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                            LOGGER.error(ex);
                        }
                    }
                }
            }, ButtonId.YES, ButtonId.NO);
        }
        LOGGER.info("deleteButtonClickLogic Method Ended");
    }

    /**
     * (non-Javadoc).
     *
     * @param event the event
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(final ViewChangeEvent event) {

    }

    /**
     * Adds the responsive search table collapse.
     *
     * @param table the table
     */
    public void addResponsiveSearchTableCollapse(final ExtFilterTable table) {

        final Map<Integer, Boolean> reloadMap = new HashMap<Integer, Boolean>();
        reloadMap.put(1516, true);
        reloadMap.put(978, true);
        reloadMap.put(600, true);
        reloadMap.put(480, true);
        reloadMap.put(320, true);
        reloadMap.put(0, true);

        try {

            table.setColumnCollapsingAllowed(true);
            Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
                @Override
                /**
                 *
                 */
                public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                    final int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                    if (browserWidth > 1516 && reloadMap.get(1516)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > 1516);
                        }
                        reloadMap.put(1516, false);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 1516 && browserWidth > 978 && reloadMap.get(978)) {
                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 1516);
                        }

                        reloadMap.put(1516, true);
                        reloadMap.put(978, false);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 978 && browserWidth > 600 && reloadMap.get(600)) {
                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }

                        if (table != null && table.getItemIds().isEmpty()) {
                            for (final Object propertyId : getCollapsibleColumns978Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        } else {
                            for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        }

                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, false);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 600 && browserWidth > 480 && reloadMap.get(480)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, false);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 480 && browserWidth > 320 && reloadMap.get(320)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 480);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, false);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 320 && reloadMap.get(0)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 320);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, false);

                    }

                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Gets the collapsible columns600 px.
     *
     * @param table the table
     * @return the collapsible columns600 px
     */
    private static String[] getCollapsibleColumns600Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns480 px.
     *
     * @param table the table
     * @return the collapsible columns480 px
     */
    private static String[] getCollapsibleColumns480Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns978 px.
     *
     * @param table the table
     * @return the collapsible columns978 px
     */
    private static String[] getCollapsibleColumns978Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns default1515 px.
     *
     * @param table the table
     * @return the collapsible columns default1515 px
     */
    private static String[] getCollapsibleColumnsDefault1515Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns default.
     *
     * @param table the table
     * @return the collapsible columns default
     */
    private static String[] getCollapsibleColumnsDefault(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));

        for (int i = 0; i < 10 && !list.isEmpty(); i++) {
            list.remove(propertyIds[i]);
        }

        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * setDefaultTableWidth
     *
     * @param table
     * @param container
     */
    public void setDefaultTableWidth(final ExtFilterTable table, final BeanItemContainer<RelationshipBuilderDTO> container) {

        try {
            table.setColumnCollapsingAllowed(true);
            int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > 1516) {

                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }

            } else if (browserWidth < 1516 && browserWidth > 978) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 978 && browserWidth > 600) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }

                if (container != null && container.getItemIds().isEmpty()) {
                    for (Object propertyId : getCollapsibleColumns978Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                } else {
                    for (Object propertyId : getCollapsibleColumns600Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                }
            } else if (browserWidth < 600 && browserWidth > 480) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 480 && browserWidth > 320) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 320) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * getSixColumns
     *
     * @param table
     * @return object
     */
    private static Object[] getSixColumns(ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0; i < 6; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    /**
     * Enable action button.
     */
    private void enableActionButton() {
        if (!ConstantsUtils.EMPTY.equalsIgnoreCase(searchCriteria) && ConstantsUtils.SEARCH.equalsIgnoreCase(searchCriteria)) {
            btnDelete.setEnabled(true);

            btnView.setEnabled(true);
            btnEdit.setEnabled(true);
            btnCopy.setEnabled(true);
        } else if (!ConstantsUtils.EMPTY.equalsIgnoreCase(searchCriteria) && ConstantsUtils.AUDIT_SEARCH.equalsIgnoreCase(searchCriteria)) {
            btnDelete.setEnabled(false);
            btnView.setEnabled(true);
            btnEdit.setEnabled(false);
            btnCopy.setEnabled(false);
        }
    }

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings("rawtypes")
    private class CreationDateValidator extends AbstractValidator {

        /**
         * Instantiates a new date validator.
         */
        public CreationDateValidator() {
            super(ConstantsUtils.EMPTY);
        }

        /**
         * Instantiates a new date validator.
         *
         * @param errorMessage the error message
         */
        public CreationDateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * (non-Javadoc).
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            LOGGER.info("validate Method started");

            if (creationDateFrom.getValue() != null && creationDateTo.getValue() != null) {
                if (creationDateFrom.getValue().after(creationDateTo.getValue())) {
                    throw new Validator.InvalidValueException("Creation Date To should be greater than Creation Date From");
                } else if (creationDateFrom.getValue().equals(creationDateTo.getValue())) {
                    throw new Validator.InvalidValueException("Creation Date From and Creation Date To should not be equal");
                }
            }
            LOGGER.info("validate Method Ended");
        }

        /**
         * (non-Javadoc).
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.info("isValidValue Method started");
            boolean flag;
            if (creationDateFrom.getValue() == null || creationDateTo.getValue() == null) {
                flag = true;
            } else {
                flag = creationDateFrom.getValue().compareTo(creationDateTo.getValue()) <= 0;
            }
            LOGGER.info("isValidValue Method returns true");
            return flag;
        }

        /**
         * (non-Javadoc).
         *
         * @return the type
         * @see com.vaadin.data.validator.AbstractValidator#getType()
         */
        @Override
        public Class getType() {
            return null;
        }
    }
}
