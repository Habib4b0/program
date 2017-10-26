package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.company.dto.CompanyContainerParentNoLookUp;
import com.stpl.app.global.company.dto.CompanyCriteria;
import com.stpl.app.global.company.dto.CompanyFilterGenerator;
import com.stpl.app.global.company.dto.SearchCompanyForm;
import com.stpl.app.global.company.dto.SearchDTO;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.company.util.LabelUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.CustomGridLayout;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class ParentCompanyNo to get the parent companyNo window.
 */
public final class ParentCompanyNo extends Window {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(com.stpl.app.global.item.ui.form.ParentCompanyNo.class);
    /**
     * The error msg.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;
    private final Map<Integer, Boolean> reloadMap = new HashMap<Integer, Boolean>();
    CompanyCriteria searchCriteria = new CompanyCriteria();
    /**
     * The company no.
     */
    @UiField("companyNoLB")
    private Label companyNoLB;
    @UiField("companyNo")
    private TextField companyNo;
    /**
     * The company id.
     */
    @UiField("companyIdLB")
    private Label companyIdLB;
    @UiField("companyId")
    private TextField companyId;
    /**
     * The company name.
     */
    @UiField("companyNameLB")
    private Label companyNameLB;
    @UiField("companyName")
    private TextField companyName;
    /**
     * The company status.
     */
    @UiField("companyStatusLB")
    private Label companyStatusLB;
    @UiField("companyStatus")
    private ComboBox companyStatus;
    /**
     * The company type.
     */
    @UiField("companyTypeLB")
    private Label companyTypeLB;
    @UiField("companyType")
    private ComboBox companyType;
    @UiField("cssLayout")
    CssLayout cssLayout;
    /**
     * The parent company no.
     */
    @SuppressWarnings("PMD")
    /**
     * The parent company Id.
     */
    private TextField parentCompanyId = new TextField();
    /**
     * The parent company no.
     */
    private TextField parentCompanyNo = new TextField();
    /**
     * The Parent company name.
     */
    private TextField parentCompanyName = new TextField();
    CommonUtil commmonUtil=CommonUtil.getInstance();
    /**
     * The btn select.
     */
    @UiField("selectBtn")
    private Button btnSelect;
    /**
     * The btn search.
     */
    @UiField("searchBtn")
    private Button searchBtn;
    /**
     * The btn reset.
     */
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * The btn reset.
     */
    @UiField("selectBtn")
    private Button selectBtn;
    
    /**
     * The btn close.
     */
    @UiField("closeBtn")
    private Button closeBtn;

    /**
     * The table.
     */
    @UiField("table")
    private ExtFilterTable table;
    /**
     * The parent sys id.
     */
    private TextField parentSysId;
    /**
     * The parent company number.
     */
    public TextField parentCompanyNumber;
    final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<SearchDTO>(new SearchDTO()));
    SessionDTO sessionDTO;

    public ParentCompanyNo(final TextField parentCompanyNo) throws SystemException, PortalException {


        super("Entity Look Up");
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        this.parentCompanyNo = parentCompanyNo;
        this.parentSysId = new TextField();
        try {
            init();
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (PortalException ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (Exception ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }

    }

    /**
     * The Constructor to initialize the parent company numberS.
     *
     * @param parentCompanyNo the parent company no
     * @param parentSysId the parent sys id
     */
    public ParentCompanyNo(final TextField parentCompanyId, final TextField parentSysId, final TextField parentCompanyName, final SessionDTO sessionDTO) {


        super("Parent Company No");
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        this.parentCompanyId = parentCompanyId;
        this.parentSysId = parentSysId;
        this.parentCompanyName = parentCompanyName;
        this.sessionDTO=sessionDTO;
        try {
            setContent(Clara.create(getClass().getResourceAsStream("/clara/companyMaster/ParentCompanyLoopUp.xml"), this));
            init();
            getBinder();
            validateFields(binder);
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (PortalException ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (Exception ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }

    }

    /**
     * Initialize the the form.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void init() throws SystemException, PortalException {
        center();
        setClosable(true);
        setModal(true);
        setHeight("600px");
        addToContent();
        configureFields();
    }

    /**
     * Adds the contents to form.
     *
     * @return the vertical layout
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    private void addToContent() throws SystemException, PortalException {
        addToGrid2();
        addToTable();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private void getBinder() {
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
    }

    public void addResponsiveTableCollapse(final ExtFilterTable table) {
        reloadMap.put(NumericConstants.EIGHT_FIVE_ZERO, true);
        reloadMap.put(NumericConstants.FOUR_FIVE_ZERO, true);
        reloadMap.put(0, true);
        table.setColumnCollapsingAllowed(true);
        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {
                int browserWidth = event.getWidth();
                try {

                    if (browserWidth > NumericConstants.EIGHT_FIVE_ZERO && reloadMap.get(NumericConstants.EIGHT_FIVE_ZERO)) {
                        searchCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        reloadMap.put(NumericConstants.EIGHT_FIVE_ZERO, false);
                        reloadMap.put(NumericConstants.FOUR_FIVE_ZERO, true);
                        reloadMap.put(0, true);
                        searchCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.EIGHT_FIVE_ZERO && browserWidth > NumericConstants.FOUR_FIVE_ZERO && reloadMap.get(NumericConstants.FOUR_FIVE_ZERO)) {
                        searchCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns850Px(table)) {
                            table.setColumnCollapsed(propertyId, true);
                        }

                        reloadMap.put(NumericConstants.EIGHT_FIVE_ZERO, true);
                        reloadMap.put(NumericConstants.FOUR_FIVE_ZERO, false);
                        reloadMap.put(0, true);
                        searchCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.FOUR_FIVE_ZERO && reloadMap.get(0)) {
                        searchCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns450Px(table)) {
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(NumericConstants.EIGHT_FIVE_ZERO, true);
                        reloadMap.put(NumericConstants.FOUR_FIVE_ZERO, true);
                        reloadMap.put(0, false);
                        searchCriteria.setCustomDirty(true);
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

    }

    private String[] getCollapsibleColumns450Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private String[] getCollapsibleColumns850Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    public void addToTable() throws SystemException, PortalException {
        LOGGER.debug("Entering addToTable ");
        final BeanItemContainer<SearchCompanyForm> searchResultbeans = new BeanItemContainer<SearchCompanyForm>(SearchCompanyForm.class);
        table.markAsDirty();
        table.setContainerDataSource(searchResultbeans);
        table.setVisibleColumns(UIUtils.PARENT_COMPANY_NO_COLUMNS);
        table.setColumnHeaders(UIUtils.PARENT_COMPANY_NO_HEADERS);
        table.setPageLength(NumericConstants.SIX);
        table.setComponentError(null);
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setValidationVisible(false);
        table.addStyleName(ConstantsUtils.FILTER_BAR);
        table.setErrorHandler(new ErrorHandler() {
            /**
             * invoked when an error occurs.
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // parses the error.
            }
        });
        table.addStyleName(ConstantsUtils.FILTER_BAR);
        table.setFilterGenerator(new CompanyFilterGenerator());
        LOGGER.debug(" Ends addComponentInGridPosition  ");

    }

    /**
     * Adds the grid to form.
     *
     * @return the grid layout
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    protected GridLayout addToGrid() throws PortalException, SystemException {
        final CustomGridLayout grid = new CustomGridLayout(NumericConstants.SIX, NumericConstants.FOUR);
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER,false);
        grid.setWidth(null);
        grid.setSpacing(true);
        grid.setMargin(true);
        grid.addComponentInGrid(new Label(LabelUtils.COMPANY_ID), companyId,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_ID)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_ID)).isSearchFlag());
        grid.addComponentInGrid(new Label(LabelUtils.COMPANY_NO), companyNo,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_NO)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_NO)).isSearchFlag());
        grid.addComponentInGrid(new Label(LabelUtils.COMPANY_NAME), companyName, (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_NAME)) == null) ? false
                : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_NAME)).isSearchFlag());
        grid.addComponentInGrid(new Label(LabelUtils.COMPANY_STATUS), companyStatus, (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_STATUS)) == null) ? false
                : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_STATUS)).isSearchFlag());
        grid.addComponentInGrid(new Label(LabelUtils.COMPANY_TYPE), companyType, (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_TYPE)) == null) ? false
                : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_TYPE)).isSearchFlag());
        return grid;
    }

    protected void addToGrid2() throws SystemException, PortalException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER,false);
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyIdLB, false), companyId,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_ID)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_ID)).isSearchFlag());
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyNoLB, false), companyNo,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_NO)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_NO)).isSearchFlag());
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyNameLB, false), companyName,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_NAME)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_NAME)).isSearchFlag());
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyStatusLB, false), companyStatus,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_STATUS)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_STATUS)).isSearchFlag());
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyTypeLB, false), companyType,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_TYPE)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COMPANY_TYPE)).isSearchFlag());

    }

    /**
     * Configure the fields.
     *
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    protected void configureFields() throws PortalException, SystemException {
        LOGGER.debug("configureFields");
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        LOGGER.debug("configureFields");
        setResizable(false);
        selectBtn.setEnabled(false);
        closeBtn.setEnabled(true);
        table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
        table.setColumnCollapsingAllowed(true);
        companyName.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyname,null,null");
        companyName.setImmediate(true);
        companyName.setValidationVisible(true);

        companyNo.setData("maxlengthvalidationfifty,maxlengthvalidationcompanyno,null,null");
        companyNo.setImmediate(true);
        companyNo.setValidationVisible(true);

        companyId.setData("maxlengthvalidation,maxlengthvalidationcompanyid,null,null");
            
        companyId.setImmediate(true);
        companyId.setValidationVisible(true);
        companyId.focus();
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);

        CompanySearchLogic companyLogic = new CompanySearchLogic();
        new CommonUtils().getNativeSelect(companyStatus, companyLogic.getDropDownList(UIUtils.STATUS));
        companyStatus.setNullSelectionAllowed(true);
        companyStatus.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);

        new CommonUtils().getNativeSelect(companyType, companyLogic.getCompanyType(UIUtils.COMPANY_TYPE));
        companyType.setNullSelectionAllowed(true);
        companyType.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        btnSelect.setWidth(ConstantsUtils.BTN_WIDTH);
        btnSelect.addClickListener(new ClickListener() {
            /**
             * Logic for select button click Logic.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering inside  SELECT  method ");
                parentCompanyId.setReadOnly(false);
                parentCompanyNo.setReadOnly(false);
                parentCompanyName.setReadOnly(false);
                parentCompanyId.setValue(sessionDTO.getCompanyNo());
                parentCompanyNo.setValue(sessionDTO.getCompanyNo());
                parentCompanyName.setValue(sessionDTO.getCompanyName());
                parentCompanyNo.setReadOnly(true);
                parentCompanyId.setReadOnly(true);
                parentCompanyName.setReadOnly(true);
                parentSysId.setValue(sessionDTO.getParentSysId());
                sessionDTO.setParentLookUpId("0001");
                close();
                LOGGER.debug("Ending PaymentCompanyNo SELECT  method ");
            }            
        });
       closeBtn.setWidth(ConstantsUtils.BTN_WIDTH);
       closeBtn.addClickListener(new ClickListener() {
            /**
             * Logic for select button click Logic.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering inside  Close  method ");
                parentCompanyId.setReadOnly(false);
                parentCompanyNo.setReadOnly(false);
                parentCompanyName.setReadOnly(false);
                parentCompanyId.setValue(StringUtils.EMPTY);
                parentCompanyNo.setValue(StringUtils.EMPTY);
                parentCompanyName.setValue(StringUtils.EMPTY);
                parentCompanyNo.setReadOnly(true);
                parentCompanyId.setReadOnly(true);
                parentCompanyName.setReadOnly(true);
                parentSysId.setValue(sessionDTO.getParentSysId());
                sessionDTO.setParentLookUpId("0001");
                close();
                LOGGER.debug("Ending Close  method ");
            }            
        });
    }

    /**
     * Gets the parent company number.
     *
     * @return the parent company number
     */
    public TextField getParentCompanyNumber() {
        return parentCompanyNumber;
    }

    /**
     * Gets the parent sys id.
     *
     * @return the parent sys id
     */
    public TextField getParentSysId() {
        return parentSysId;
    }

    /**
     * Sets the parent sys id.
     *
     * @param parentSysId the new parent sys id
     */
    public void setParentSysId(final TextField parentSysId) {
        this.parentSysId = parentSysId;
    }

    @UiHandler("resetBtn")
    public void btnResetLogic(Button.ClickEvent event) {
          MessageBox.showPlain(Icon.QUESTION,"Confirmation", "Are you sure you want to reset the page to default/previous values?", new MessageBoxListener() {
                   /**
               * Called when a Button has been clicked .
               *
               */
              @SuppressWarnings("PMD")
              public void buttonClicked(final ButtonId buttonId) {
                  if (buttonId.name().equals(ConstantsUtils.YES)) {
                      try {
                          LOGGER.debug("Entering btnResetLogic - ParentCompanyNo Search operation");
                          List<Object> collapsedColumns = new ArrayList<Object>();
                          for (Object item : table.getVisibleColumns()) {
                              if (table.isColumnCollapsed(item)) {
                                  collapsedColumns.add(item);
                              }
                          }
                          table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
                          binder.setItemDataSource(new BeanItem<SearchDTO>(new SearchDTO()));
                          binder.getErrorDisplay().clearError();
                          final BeanItemContainer<SearchCompanyForm> searchResultbeans = new BeanItemContainer<SearchCompanyForm>(SearchCompanyForm.class);
                          table.setContainerDataSource(searchResultbeans);
                          table.setVisibleColumns(UIUtils.PARENT_COMPANY_NO_COLUMNS);
                          table.setColumnHeaders(UIUtils.PARENT_COMPANY_NO_HEADERS);
                          table.addStyleName(ConstantsUtils.FILTER_BAR);
                          table.setFilterDecorator(new ExtDemoFilterDecorator());
                          table.setFilterGenerator(new CompanyFilterGenerator());
                          btnSelect.setEnabled(false);

                          searchCriteria.setCustomDirty(false);
                          for (Object propertyId : collapsedColumns) {
                              table.setColumnCollapsed(propertyId, true);
                          }
                          searchCriteria.setCustomDirty(true);
                          LOGGER.debug("Ending btnResetLogic - ParentCompanyNo Search operation");

                            } catch (Exception e) {
                                LOGGER.error(e);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
       
    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
        LOGGER.debug("Entering btnSearchLogic - ParentCompanyNo Search operation");
        try {
            List<Object> collapsedColumns = new ArrayList<Object>();

            for (Object item : table.getVisibleColumns()) {
                if (table.isColumnCollapsed(item)) {
                    collapsedColumns.add(item);
                }
            }

            if (String.valueOf(binder.getField("companyId").getValue()).trim().isEmpty() && String.valueOf(binder.getField("companyNo").getValue()).trim().isEmpty() && String.valueOf(binder.getField("companyName").getValue()).trim().isEmpty()
                    && (binder.getField("companyStatus").getValue() == null || (Integer) binder.getField("companyStatus").getValue() == 0)
                    && (binder.getField("companyType").getValue() == null || (Integer) binder.getField("companyType").getValue() == 0)) {
                MessageBox.showPlain(Icon.INFO, "Search Criteria", "Please enter Search Criteria", new MessageBoxListener() {
                    /**
                     * After clicking button, function will be executed.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        
                    }
                }, ButtonId.OK);
            } else {
               
                    try {
                        binder.commit();
                 if (searchCriteria.getFilters() != null) {
                         searchCriteria.getFilters().clear();
                 }
                searchCriteria.setCustomDirty(true);
                final LazyBeanItemContainer searchResults = new LazyBeanItemContainer(
                        SearchCompanyForm.class, new CompanyContainerParentNoLookUp(binder),
                        searchCriteria);

                table.setContainerDataSource(searchResults);
                if (searchResults.size() > com.stpl.app.util.GeneralCommonUtils.ZERO) {
                    CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                } else {
                    CommonUIUtils.successNotification(ConstantsUtils.NO_RESULTS_COMPLETED);
                }
                table.setVisibleColumns(UIUtils.PARENT_COMPANY_NO_COLUMNS);
                table.setColumnHeaders(UIUtils.PARENT_COMPANY_NO_HEADERS);
                table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
                table.setSelectable(true);
                table.addStyleName(ConstantsUtils.FILTER_BAR);
                table.setFilterDecorator(new ExtDemoFilterDecorator());
                table.setFilterGenerator(new CompanyFilterGenerator());
                table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                    /**
                     * logic for item click event.
                     *
                     * @param event
                     */
                    @SuppressWarnings("PMD")
                    public void itemClick(final ItemClickEvent event) {
                        LOGGER.debug("Entering btnSearchLogic - ParentCompanyNo Search operation - itemClick");
                        try {
                          
                            itemSelectLogic(event, searchResults);
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                                /**
                                 * The method is triggered when a button of the message box is
                                 * pressed .
                                 *
                                 * @param buttonId The buttonId of the pressed button.
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    // Do Nothing
                                }
                            }, ButtonId.OK);
                            msg.getButton(ButtonId.OK).focus();
                        }
                        btnSelect.setEnabled(true);
                        LOGGER.debug("Ending btnSearchLogic - ParentCompanyNo Search operation - itemClick");

                    }
                });

             } catch (FieldGroup.CommitException ex) {
                        java.util.logging.Logger.getLogger(ParentCompanyNo.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            searchCriteria.setCustomDirty(false);
            for (Object propertyId : collapsedColumns) {
                table.setColumnCollapsed(propertyId, true);
            }
            searchCriteria.setCustomDirty(true);
            LOGGER.debug("Ending btnSearchLogic - ParentCompanyNo Search operation");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void itemSelectLogic(final ItemClickEvent event, final LazyBeanItemContainer searchResults) {
        LOGGER.debug("Entering itemSelectLogic");
        final SearchCompanyForm searchForm = (SearchCompanyForm) searchResults.getItem(event.getItemId()).getBean();
        LOGGER.debug("selected item--------->" + searchForm.getCompanyName());
        sessionDTO.setCompanyId(searchForm.getCompanyId());
        sessionDTO.setCompanyNo(searchForm.getCompanyNo());
        sessionDTO.setCompanyName(searchForm.getCompanyName());
        sessionDTO.setParentSysId(searchForm.getSystemId());
        btnSelect.setEnabled(true);
        LOGGER.debug("Ending itemSelectLogic");

    }
     public void validateFields(ErrorfulFieldGroup binder) {
        Collection collection = binder.getFields();
        
        for (Object field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
                
            }
        }
        
    }
}
