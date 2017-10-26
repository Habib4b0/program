package com.stpl.app.global.cfp.ui.form;

import com.stpl.app.global.cfp.dto.CFPCriteria;
import com.stpl.app.global.cfp.dto.CFPDTO;
import com.stpl.app.global.cfp.dto.CFPSearchDTO;
import com.stpl.app.global.cfp.dto.ParentCFPContainer;
import com.stpl.app.global.cfp.util.CFPTestGenerator;
import com.stpl.app.global.cfp.util.UIUtils;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.ui.CustomGridLayout;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ResponsiveUtils;
import static com.stpl.app.util.ResponsiveUtils.*;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Collection;
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
 * The Class ParentCFPIdLookup.
 */
public final class ParentCFPIdLookup extends Window {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -1923575668763571645L;
    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ParentCFPIdLookup.class);
    /**
     * The error msg.
     */
    @UiField("errorLB")
    private ErrorLabel errorMsg;
    /**
     * The company family plan id.
     */

    @UiField("companyFamilyPlanIdLB")
    private Label companyFamilyPlanIdLB;
    @UiField("companyFamilyPlanId")
    private TextField companyFamilyPlanId;
    /**
     * The company family plan name.
     */
    @UiField("companyFamilyPlanNameLB")
    private Label companyFamilyPlanNameLB;
    @UiField("companyFamilyPlanName")
    private TextField companyFamilyPlanName;
    /**
     * The company family plan status.
     */
    @UiField("companyFamilyPlanStatusLB")
    private Label companyFamilyPlanStatusLB;
    @UiField("companyFamilyPlanStatus")
    private ComboBox companyFamilyPlanStatus;
    /**
     * The company family plan no.
     */
    @UiField("companyFamilyPlanNoLB")
    private Label companyFamilyPlanNoLB;
    @UiField("companyFamilyPlanNo")
    private TextField companyFamilyPlanNo;
    /**
     * The company family plan type.
     */
    @UiField("companyFamilyPlanTypeLB")
    private Label companyFamilyPlanTypeLB;
    @UiField("companyFamilyPlanType")
    private ComboBox companyFamilyPlanType;

    /**
     * The parent company family plan name.
     */
    private TextField parentCompanyFamilyPlanName = new TextField();
    /**
     * The parent company family plan id.
     */
    private TextField parentCompanyFamilyPlanId = new TextField();
    /**
     * The company family plan system id.
     */
    private int companyFamilyPlanSystemId;

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

    @UiField("prevColumn")
    private Button prevColumn;
    @UiField("nextColumn")
    private Button nextColumn;

    /**
     * The binder.
     */
    ErrorfulFieldGroup binder;
    /**
     * The result table.
     */
    @UiField("resultTable")
    private ExtFilterTable resultTable;
    /**
     * The search results.
     */
    private LazyBeanItemContainer searchResults;
    /**
     * The search.
     */
    @UiField("search")
    private Button search;
    /**
     * The reset.
     */
    @UiField("reset")
    private Button reset;
    /**
	 * The select button.
	 */
     @UiField("selectBtn")
    Button selectBtn;
     /**
	 * The close button.
	 */
    @UiField("closeBtn")
    Button closeBtn;
    /**
     * A dummy BeanItemContainer to avoid load issue in empty lazy bean
     * container.
     */
    private BeanItemContainer<CFPDTO> dummySearchResulbeans = new BeanItemContainer<CFPDTO>(CFPDTO.class);

    /**
     * The parent company family plan name.
     */
    private TextField parentFlagSID = new TextField();
    private Integer parentSysId=0;
    private String parentcfpId = StringUtils.EMPTY;
    private String parentcfpName = StringUtils.EMPTY;
    
    private CommonUtil commonUtil = CommonUtil.getInstance();
    SessionDTO sessionDTO;
    
    @UiField("cssLayout")
    CssLayout cssLayout;

    /**
     * The Constructor.
     *
     * @param parentCompanyFamilyPlanId the parent company family plan id
     * @param parentCompanyFamilyPlanName the parent company family plan name
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public ParentCFPIdLookup(final TextField parentCompanyFamilyPlanId, final TextField parentCompanyFamilyPlanName, final int companyFamilyPlanSystemId, final TextField parentFlagSID, final SessionDTO sessionDTO) throws SystemException {
        super();
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        setContent(Clara.create(getClass().getResourceAsStream("/declarativeui/company_family_plan/parentcfpidlookup.xml"), this));
        this.parentCompanyFamilyPlanId = parentCompanyFamilyPlanId;
        this.parentCompanyFamilyPlanName = parentCompanyFamilyPlanName;
        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
        this.parentFlagSID = parentFlagSID;
        this.sessionDTO = sessionDTO;
        setResizable(false);
        init();
    }

    /**
     * Initial method when the Constructor get calls.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void init() throws SystemException {
        LOGGER.debug("Entering ParentCFPIdLookup init");
        center();
        setClosable(true);
        setModal(true);
        setHeight(NumericConstants.FLOAT_EIGHTY, Unit.PERCENTAGE);
        setWidth(NumericConstants.FLOAT_EIGHTY, Unit.PERCENTAGE);
        addToContent();

        configureFields();
        
        getBinder();
        validateFields();
        
        LOGGER.debug("Ending ParentCFPIdLookup init");
    }

    /**
     * Adds content to panel.
     *
     * @return the panel
     */
    private void addToContent() {
        LOGGER.debug("Entering ParentCFPIdLookup addToContent");
        addToGrid1();
        addNaviButtonForLandingSearchWithDeclarativeUI(resultTable, prevColumn, nextColumn);
        addTabel();
        LOGGER.debug("Ending ParentCFPIdLookup addToContent");

    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {
        LOGGER.debug("Entering ParentCFPIdLookup getBinder");
        binder = new ErrorfulFieldGroup(
                new BeanItem<CFPDTO>(new CFPDTO()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        LOGGER.debug("Ending ParentCFPIdLookup getBinder");
        this.binder = binder;
        return binder;
    }

    public CssLayout getResponsiveButtons() {
        final CssLayout layout = new CssLayout();
        layout.setSizeFull();
        layout.addComponent(search);
        layout.addComponent(reset);
        return layout;
    }

    /**
     * Adds component to grid.
     *
     * @return the grid layout
     */
    protected GridLayout addToGrid() {
        LOGGER.debug("Entering ParentCFPIdLookup addToGrid");
        final CustomGridLayout gridLayout = new CustomGridLayout(NumericConstants.SIX, NumericConstants.THREE);

        return gridLayout;
    }

    private void addToGrid1() {
        LOGGER.debug("Entering addToGrid1");
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyFamilyPlanIdLB, false), companyFamilyPlanId, true);
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyFamilyPlanNoLB, false), companyFamilyPlanNo, true);
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyFamilyPlanNameLB, false), companyFamilyPlanName, true);
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyFamilyPlanTypeLB, false), companyFamilyPlanType, true);
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyFamilyPlanStatusLB, false), companyFamilyPlanStatus, true);
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyIdLB, false), companyId, true);
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyNoLB, false), companyNo, true);
        ResponsiveUtils.addComponentInCsssLayout(cssLayout, ResponsiveUtils.makeLabel(companyNameLB, false), companyName, true);

        LOGGER.debug("Ending addToGrid1");
    }

    /**
     * returns table.
     *
     * @return the table
     */
    private void addTabel() {
        LOGGER.debug("Entering ParentCFPIdLookup addTabel");
        resultTable.setPageLength(NumericConstants.TEN);
        resultTable.setImmediate(true);
        resultTable.setSelectable(true);
        resultTable.setWidth(NumericConstants.NINETY_EIGHT, Unit.PERCENTAGE);
        resultTable.setContainerDataSource(dummySearchResulbeans);
        resultTable.setVisibleColumns(UIUtils.CFP_SEARCH);
        resultTable.setColumnHeaders(UIUtils.CFP_SEARCH_HEADER);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(new CFPTestGenerator());
        selectBtn.setEnabled(false);
        resultTable.addItemClickListener(new ItemClickListener() {
            /**
             * Called when a item has been clicked.
             */
            @SuppressWarnings("PMD")
            @UiHandler("selectBtn")
            public void itemClick(final ItemClickEvent event) {
                final CFPSearchDTO cfpSearchDto = (CFPSearchDTO) searchResults.getItem(event.getItemId()).getBean();
                selectBtn.setEnabled(true);
                if ((String.valueOf(companyFamilyPlanSystemId)).equals(parentCompanyFamilyPlanId.getValue().toString())) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Select", "Please select different CFP as parent", new MessageBoxListener() {
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
                } else {
                    parentSysId=Integer.valueOf(cfpSearchDto.getCompanyFamilyPlanSystemId());
                    parentcfpId = String.valueOf(cfpSearchDto.getCompanyFamilyPlanId());
                    parentcfpName = cfpSearchDto.getCompanyFamilyPlanName();
                }
            }

        });
        setDefaultTableColumns();
        LOGGER.debug("Ending ParentCFPIdLookup addTabel");
    }

    /**
     * to Configure fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    protected void configureFields() throws SystemException {
        LOGGER.debug("Entering ParentCFPIdLookup configureFields");
        companyFamilyPlanId.setData("maxlengthvalidation,maxlengthvalidationcompanyfamilyplanid,null,null");
        companyFamilyPlanNo.setData("maxlengthvalidationfifty,maxlengthvalidationcompanyfamilyplanno,null,null");
        companyFamilyPlanName.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyfamilyplanname,null,null");
        companyFamilyPlanName.setImmediate(true);
        companyFamilyPlanName.setValidationVisible(true);
        companyFamilyPlanNo.setImmediate(true);
        companyFamilyPlanNo.setValidationVisible(true);

        companyFamilyPlanId.setImmediate(true);
        companyFamilyPlanId.setValidationVisible(true);

        commonUtil.loadComboBox(companyFamilyPlanStatus, UIUtils.CFP_STATUS, false);
        commonUtil.loadComboBox(companyFamilyPlanType, UIUtils.CFP_TYPE, false);

        companyFamilyPlanId.focus();
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        search.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering inside  SEARCH  method ");

                    if (binder.getField("companyFamilyPlanId").getValue().toString().trim().isEmpty()
                            && binder.getField("companyFamilyPlanNo").getValue().toString().trim().isEmpty()
                            && binder.getField("companyFamilyPlanName").getValue().toString().trim().isEmpty()
                            && binder.getField("companyNo").getValue().toString().trim().isEmpty()
                            && binder.getField("companyId").getValue().toString().trim().isEmpty()
                            && binder.getField("companyName").getValue().toString().trim().isEmpty()
                            && (binder.getField("companyFamilyPlanStatus").getValue() == null || ((HelperDTO) binder.getField("companyFamilyPlanStatus").getValue()).getId() == 0)
                            && (binder.getField("companyFamilyPlanType").getValue() == null || ((HelperDTO) binder.getField("companyFamilyPlanType").getValue()).getId() == 0)) {
                        MessageBox.showPlain(Icon.INFO, "Search Criteria", "Please enter Search Criteria", new MessageBoxListener() {
                            /**
                             * After clicking button, function will be executed.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {

                            }
                        }, ButtonId.OK);
                    } else {

                        binder.getFields();
                        binder.commit();
                        searchResults = new LazyBeanItemContainer(
                                CFPDTO.class, new ParentCFPContainer(binder,sessionDTO),
                                new CFPCriteria());

                        resultTable.setContainerDataSource(searchResults);
                        if (searchResults.size() > Constants.ZERO) {
                            CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                        } else {
                            CommonUIUtils.successNotification(ConstantsUtils.NO_RESULTS_COMPLETED);
                        }

                    }
                    resultTable.setVisibleColumns(UIUtils.CFP_SEARCH);
                    resultTable.setColumnHeaders(UIUtils.CFP_SEARCH_HEADER);
                    resultTable.setFilterBarVisible(true);
                    resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    resultTable.setFilterGenerator(new CFPTestGenerator());
                    resultTable.addStyleName(ConstantsUtils.FILTER_BAR);
                    LOGGER.debug("Ending  SEARCH  method ");

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

        reset.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                LOGGER.debug("Entering inside  RESET  method ");
                                binder.setItemDataSource(new BeanItem<CFPDTO>(new CFPDTO()));
                                if (searchResults != null) {
                                    searchResults.removeAllItems();
                                }
                                binder.getErrorDisplay().clearError();
                                final BeanItemContainer<CFPDTO> searchResultbeans = new BeanItemContainer<CFPDTO>(CFPDTO.class);
                                resultTable.setContainerDataSource(searchResultbeans);

                                resultTable.setVisibleColumns(UIUtils.CFP_SEARCH);
                                resultTable.setColumnHeaders(UIUtils.CFP_SEARCH_HEADER);
                                resultTable.setFilterBarVisible(true);
                                resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                                resultTable.setFilterGenerator(new CFPTestGenerator());
                                binder.getErrorDisplay().clearError();
                                selectBtn.setEnabled(false);

                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });
        companyName.setData("maxlengthvalidation,maxlengthvalidationcompanyid,null,null");
        companyId.setData("maxlengthvalidationfifty,maxlengthvalidationcompanyno,null,null");
        companyNo.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyname,null,null");
        companyName.setImmediate(true);
        companyName.setValidationVisible(true);
        companyNo.setImmediate(true);
        companyNo.setValidationVisible(true);
        companyNo.setId("companyNo");
        companyId.setImmediate(true);
        companyId.setId("companyId");
        companyId.setValidationVisible(true);

        LOGGER.debug("Ending ParentCFPIdLookup configureFields");
    }

    /**
     * Gets the dummy search result bean.
     *
     * @return the dummy search result bean
     */
    public BeanItemContainer<CFPDTO> getDummySearchResulbeans() {
        return dummySearchResulbeans;
    }

    /**
     * Sets the dummy search result bean.
     *
     * @param dummySearchResulbeans - the dummy search result bean to be set
     */
    public void setDummySearchResulbeans(final BeanItemContainer<CFPDTO> dummySearchResulbeans) {
        this.dummySearchResulbeans = dummySearchResulbeans;
    }

    private void setDefaultTableColumns() {

        resultTable.setColumnCollapsingAllowed(true);
        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
        try {

            if (browserWidth > NumericConstants.THREE_SIX_ZERO) {
                for (Object propertyId : resultTable.getVisibleColumns()) {
                    resultTable.setColumnCollapsed(propertyId, false);
                }
            } else if (browserWidth < NumericConstants.THREE_SIX_ZERO) {
                Object[] visibleColumns = resultTable.getVisibleColumns();
                for (Object propertyId : visibleColumns) {
                    resultTable.setColumnCollapsed(propertyId, false);
                }
                resultTable.setColumnCollapsed(visibleColumns[1], true);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void validateFields() {
        Collection<Field<?>> collection = binder.getFields();
        CommonUtil commmonUtil = CommonUtil.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
                
            }
        }
    }
    
    
   @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event){
        parentcfpId = StringUtils.EMPTY;
        parentcfpName = StringUtils.EMPTY;
        parentCompanyFamilyPlanId.setValue(parentcfpId);
        parentCompanyFamilyPlanName.setValue(parentcfpName);
        parentFlagSID.setValue(parentSysId.toString());
        close();
    } 
    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event){
        parentCompanyFamilyPlanId.setReadOnly(false);
        parentCompanyFamilyPlanName.setReadOnly(false);
        parentCompanyFamilyPlanId.setValue(parentcfpId);
        parentCompanyFamilyPlanName.setValue(parentcfpName);
        parentFlagSID.setValue(parentSysId.toString());
        close();
    } 
    
    
    
}
