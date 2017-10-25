package com.stpl.app.global.rebateschedule.ui.form;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.rebateschedule.dto.IFPDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.ItemDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.RSFilterGenerate;
import com.stpl.app.global.rebateschedule.lazyload.RebateScheduleContainer;
import com.stpl.app.global.rebateschedule.lazyload.RebateScheduleCriteria;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.global.rebateschedule.util.RsUtils;
import com.stpl.app.model.IfpModel;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

public class RSItemAdditionTabForm extends CustomComponent implements View {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RSItemAdditionTabForm.class);
    /**
     * The ifp number.
     */
    @UiField("ifpNo")
    private TextField ifpNo;
    /**
     * Previous column
     */
    @UiField("prevColumn")
    private Button prevColumn;
    /**
     * Next column
     */
    @UiField("nextColumn")
    private Button nextColumn;

    /**
     * The ifp name.
     */
    @UiField("ifpName")
    private TextField ifpName;
    /**
     * The ifp type.
     */
    @UiField("ifpType")
    private ComboBox ifpType;

    @UiField("ifpStartDate")
    private PopupDateField ifpStartDate;
    /**
     * The ifp end date.
     */
    @UiField("ifpEndDate")
    private PopupDateField ifpEndDate;
    /**
     * The item number.
     */
    @UiField("itemNo")
    private TextField itemID;
    /**
     * The item name.
     */
    @UiField("itemName")
    private TextField itemName;
    @UiField("searchBtn")
    Button searchBtn;
    @UiField("searchField")
    private ComboBox searchFields;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("availableTable")
    private ExtFilterTable availableTable;
    @UiField("selectedTable")
    private ExtFilterTable selectedTable;
    @UiField("resultsTable")
    private Table resultsTable;
    
    /**
     * The ifp type.
     */
    @UiField("ifpCategory")
    private CustomComboBox ifpCategory;
    
    /**
     * The ifp type.
     */
    @UiField("ifpStatus")
    private CustomComboBox ifpStatus;
    
    @UiField("searchType")
    private OptionGroup searchType;
    
    private final BeanItemContainer<IfpModel> itemFamilyplanResultsBean;
    private final ErrorfulFieldGroup binder;
    @UiField("hlayout2")
    HorizontalLayout hlayout2;
    @UiField("hlayout")
    HorizontalLayout hlayout;
    @UiField("hlayout3")
    HorizontalLayout hlayout3;
    @UiField("hlayout4")
    HorizontalLayout hlayout4;
    @UiField("tablePanel")
    private Panel tablePanel;
    @UiField("cssLayout")
    CssLayout cssLayout;
    private final BeanItemContainer<IFPDetailsDTO> selectedItemResultBean;
    BeanItemContainer<ItemDetailsDTO> itemResultsBean;    
    private final RebateScheduleLogic rebateLogic;
    private final Label selectedIfp;    
    private final String mode;
    private final boolean isViewMode;
    final StplSecurity stplSecurity = new StplSecurity();
    private String userId;
    private final IFPLogic ifpLogic = new IFPLogic();
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonUtil commonUtil = CommonUtil.getInstance();
    List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_SCHEDULE, "Item Addition");
    Object[] obj = CommonUIUtils.IFP_COLUMNS_IN_RS;
    private RSRebateSetupTabForm rsRebateSetupTabForm;
     CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    SessionDTO sessionDTO;
    
    public RSItemAdditionTabForm(ErrorfulFieldGroup binder, BeanItemContainer<IfpModel> itemFamilyplanResultsBean, final BeanItemContainer<IFPDetailsDTO> selectedItemResultBean,final BeanItemContainer<ItemDetailsDTO> itemResultsBean,
            final RebateScheduleLogic rebateLogic, final Label selectedIfp, String mode,RSRebateSetupTabForm rsRebateSetupTabForm, final SessionDTO sessionDTO) {
        this.itemFamilyplanResultsBean = itemFamilyplanResultsBean;
        this.binder = binder;
        this.selectedItemResultBean = selectedItemResultBean;
        this.rebateLogic = rebateLogic;
        this.selectedIfp = selectedIfp;        
        this.mode = mode;
        this.itemResultsBean = itemResultsBean;  
        this.sessionDTO = sessionDTO;  
        this.rsRebateSetupTabForm = rsRebateSetupTabForm;
        userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        this.isViewMode = ConstantsUtils.VIEW.equals(mode);
        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/rebateschedule/rsitemadditiontabform.xml"), this));
            getBinder();
            addToContent();
            configureFields();

            final Map<String, AppPermission> functionRsHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Item Addition");
            final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Item Addition",false);


            addResponsiveness(fieldRsHM);
            if (functionRsHM.get(FunctionNameUtil.SEARCH) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.SEARCH)).isFunctionFlag()) {
                configureSearchButton();
            }else{
                searchBtn.setVisible(false);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);
        binder.setBuffered(true);
        return binder;
    }

    private void addToContent() throws PortalException, SystemException {
        getIfpTable();

    }

    private void getIfpTable() throws PortalException, SystemException {
        LOGGER.debug("Entering getTables");
        if (isViewMode) {
            configureResultsTableForView();
        } else {
            addAvailableTable();
            addSelectedTable();
        }

        hlayout2.addStyleName("dataTransferGridForPS");
        hlayout2.addStyleName("responsiveTabGrid");

    }

    public ExtFilterTable addAvailableTable() throws PortalException, SystemException {
        final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Item Addition",false);
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldRsHM, mode);
        if (tableResultCustom.getObjResult().length == 0) {
            availableTable.setVisible(false);
            prevColumn.setVisible(false);
            nextColumn.setVisible(false);
        }
        availableTable.markAsDirty();
        availableTable.setContainerDataSource(itemFamilyplanResultsBean);
        availableTable.setVisibleColumns(tableResultCustom.getObjResult());
        availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        availableTable.setPageLength(NumericConstants.EIGHT);
        availableTable.setImmediate(true);
        availableTable.setSelectable(true);
        availableTable.setFilterBarVisible(true);
        availableTable.addStyleName(ConstantsUtils.FILTER_BAR);
        availableTable.setFilterGenerator(new RSFilterGenerate());
        availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableTable.setWidth("380px");
        availableTable.addItemClickListener(new ItemClickListener() {

            public void itemClick(ItemClickEvent event) {

            }
        });

        availableTable.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to Error Handling for Selected Table
             */
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });

        return availableTable;
    }

    public ExtFilterTable addSelectedTable() throws PortalException, SystemException {
        final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Item Addition",false);
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_SCHEDULE, "Item Addition");
        Object[] obj = CommonUIUtils.IFP_COLUMNS_IN_RS;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldRsHM, mode);
 if (tableResultCustom.getObjResult().length == 0) {
            selectedTable.setVisible(false);
             prevColumn.setVisible(false);
            nextColumn.setVisible(false);
        }
        selectedTable.markAsDirty();
        selectedTable.setContainerDataSource(selectedItemResultBean);
        selectedTable.setVisibleColumns(tableResultCustom.getObjResult());
        selectedTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        selectedTable.setPageLength(NumericConstants.EIGHT);
        selectedTable.setImmediate(true);

        if (!isViewMode) {
            selectedTable.setSelectable(true);
            selectedTable.setFilterBarVisible(true);
            selectedTable.setFilterBarVisible(true);
            selectedTable.setWidth("380px");
        } else {
            selectedTable.setSizeFull();
        }

        selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedTable.setValidationVisible(false);
        selectedTable.addStyleName(ConstantsUtils.FILTER_BAR);

        selectedTable.setFilterGenerator(new RSFilterGenerate());

        selectedTable.addItemClickListener(new ItemClickListener() {

            /**
             * Called when a item has been clicked.
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                // empty method
            }
        });
        selectedTable.setErrorHandler(new ErrorHandler() {

            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty method
            }
        });

        return selectedTable;
    }

    /**
     * Method to add Search button and its listener.
     *
     * @return the button
     */
    public Button configureSearchButton() {
        searchBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for find button logic
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        searchBtn.addClickListener(new ClickListener() {
            /**
             * Method used for find button logic
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering Rebate Schedule Search operation in Add");
                    LOGGER.debug("addFindBtn ifpNumber=" + ifpNo.getValue() + " ifpName=" + ifpName.getValue() + " ifpType=" + ifpType.getValue());
                    binder.getErrorDisplay().clearError();
                    if (searchFields.getValue() == null) {
                            binder.getErrorDisplay().setError("Please Select a Search Field to Search");
                            return;
                    }

                    if (ifpNo.getValue().isEmpty() && ifpName.getValue().isEmpty() && ifpType.getValue() == null && ifpStartDate.getValue() == null && ifpEndDate.getValue() == null && itemName.getValue().isEmpty() && itemID.getValue().isEmpty() && ifpStatus.getValue() == null && ifpCategory.getValue() == null ) {
                        binder.getErrorDisplay().setError("Please Select or Enter the Value to search");
                    } else {
                        availableTable.clearFilters();
                        String tempIFPNo = ifpNo.getValue();
                        String tempIFPName = ifpName.getValue();
                        Object tempIFPType = ((HelperDTO)ifpType.getValue()) != null ? ((HelperDTO)ifpType.getValue()).getId() : 0 ;
                        Date tempIFPStartDate = ifpStartDate.getValue();
                        Date tempIFPEndDate = ifpEndDate.getValue() ;
                        String tempItemNo = itemID.getValue() != null ? itemID.getValue() : StringUtils.EMPTY;
                        String tempItemName = itemName.getValue() != null ? itemName.getValue() : StringUtils.EMPTY;
                        Object tempIFPCategory = ((HelperDTO)ifpCategory.getValue()) != null ? ((HelperDTO)ifpCategory.getValue()).getId() : 0;
                        Object tempIFPStatus = ((HelperDTO)ifpStatus.getValue()) != null ? ((HelperDTO)ifpStatus.getValue()).getId() : 0;
                                                
                        LazyBeanItemContainer searchResults = new LazyBeanItemContainer(IFPDetailsDTO.class, new RebateScheduleContainer(tempIFPNo, tempIFPName, tempIFPType,tempIFPStartDate ,tempIFPEndDate , tempItemName,tempItemNo ,tempIFPCategory,tempIFPStatus), new RebateScheduleCriteria());
                        availableTable.setContainerDataSource(searchResults);
                        if (searchResults.size() > (Constants.ZERO)) {
                            CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                        } else {
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No Records Found", "There were no records found that match the search criteria entered.", new MessageBoxListener() {  
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
                        availableTable.setVisibleColumns(CommonUIUtils.IFP_COLUMNS_IN_RS);
                        availableTable.setColumnHeaders(CommonUIUtils.IFP_HEADER_IN_RS);
                    }
                    LOGGER.debug("Ending Rebate Schedule Search operation in Add");
                } catch (Exception exception) {

                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {  
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
                    LOGGER.error(exception);
                }
            }
        });

        return searchBtn;
    }

    public void configureAddRemoveButons() {
        addBtn.setCaption(">");
        addBtn.setDescription(ConstantsUtils.MOVE_RIGHT);
        addBtn.setImmediate(true);
        addBtn.setWidth(ConstantsUtils.WIDTH);
        addBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                LOGGER.debug("Entering AddFormForPS Move Right operation");
                    final Object itemId = availableTable.getValue();
                    binder.getErrorDisplay().clearError();

                    if (availableTable.size() > 0) {
                        if (itemId == null) {
                            binder.getErrorDisplay().setError("Please select the IFP to Add");
                        } else {
                            if (selectedItemResultBean.size() < Constants.ONE) {
                                final IFPDetailsDTO item = (IFPDetailsDTO) itemId;

                                selectedItemResultBean.addItem(item);
                                selectedIfp.setValue(String.valueOf(item.getItemFamilyplanSystemId()));
                                String ifpSysId = String.valueOf(item.getItemFamilyplanSystemId());
                                final String idValue = String.valueOf(sessionDTO.getSystemId());
                                if (item.getItemFamilyplanSystemId() != 0) {
                                    rebateLogic.addItemDetailsFromIfp(ifpSysId, idValue);
                                }
                            }
                        }
                    } else {
                        binder.getErrorDisplay().setError("There are no IFP to move");
                    }
                LOGGER.debug("Ending AddFormForPS Move Right operation");
            }

        });

        removeBtn.setCaption("<");
        removeBtn.setDescription("Move selected items to left");
        removeBtn.setWidth(ConstantsUtils.WIDTH);
        removeBtn.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                
                final Object itemId = selectedTable.getValue();
                binder.getErrorDisplay().clearError();                
                if (selectedTable.size() > 0) {
                    if (itemId == null) {
                        binder.getErrorDisplay().setError("Please select the IFP to Remove");
                    } else {
                        selectedItemResultBean.removeAllItems();
                        rebateLogic.removeRsItems();                        
                        itemResultsBean.removeAllItems();   
                        rsRebateSetupTabForm.clearRebateSetupData();
                        selectedIfp.setValue(StringUtils.EMPTY);
                    }

                }
            }
        });        

    }

    private void configureFields() {
        //If View button is Clicked
        if (isViewMode) {
            hlayout.setVisible(false);
            hlayout2.setVisible(false);
            availableTable.setVisible(false);
            hlayout3.setVisible(false);
            selectedTable.setVisible(false);
            tablePanel.setVisible(true);
            tablePanel.setSizeFull();
            hlayout4.setVisible(false);

            LOGGER.debug("In configureFields lodding-ifpType Dblb List Type addNaviButtonForLandingSearchWithDeclarativeUI");
        }
        configureAddRemoveButons();

        searchType.setImmediate(true);
        searchType.addItem("Item");
        searchType.addItem("IFP");
        searchType.setValue("IFP");
        searchType.addStyleName("horizontal");
        
        // In 2nd Tab
        ifpNo.setImmediate(true);
        ifpNo.setValidationVisible(true);
        ifpNo.setDescription(ifpNo.getValue());
        ifpNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for ifpNumber in Value Change Listener
             */
            public void valueChange(final ValueChangeEvent event) {
                ifpNo.setDescription(ifpNo.getValue());
            }
        });

        ifpName.setImmediate(true);
        ifpName.setValidationVisible(true);
        ifpName.setDescription(ifpName.getValue());
        ifpName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for lodding-ifpType Dblb ListType
             */
            public void valueChange(final ValueChangeEvent event) {
                ifpName.setDescription(ifpName.getValue());
            }
        });

        itemID.setImmediate(true);
        itemID.setValidationVisible(true);
        itemID.setDescription(itemID.getValue());
        itemID.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for ifpNumber in Value Change Listener
             */
            public void valueChange(final ValueChangeEvent event) {
                itemID.setDescription(itemID.getValue());
            }
        });

        itemName.setImmediate(true);
        itemName.setValidationVisible(true);
        itemName.setDescription(itemName.getValue());
        itemName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for lodding-ifpType Dblb ListType
             */
            public void valueChange(final ValueChangeEvent event) {
                itemName.setDescription(itemName.getValue());
            }
        });
        LOGGER.debug("In configureFields lodding-ifpType Dblb ListType=" + GeneralCommonUtils.IFP_TYPE);

        commonUtil.loadComboBox(ifpType, GeneralCommonUtils.IFP_TYPE, false);
        commonUtil.loadComboBox(ifpCategory, "IFP_CATEGORY", false);
        commonUtil.loadComboBox(ifpStatus, "STATUS", false);
        ifpNo.setVisible(true);
        ifpName.setVisible(false);
        ifpType.setVisible(false);
        ifpStartDate.setVisible(false);
        ifpEndDate.setVisible(false);
        itemName.setVisible(false);
        itemID.setVisible(false);
        ifpCategory.setVisible(false);
        ifpStatus.setVisible(false);

        ifpEndDate.setDescription(ConstantsUtils.DATE_DES);
        ifpStartDate.setDescription(ConstantsUtils.DATE_DES);
        ifpStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        ifpStartDate.setImmediate(true);
        ifpStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for convert 2 Digit To 4 Digit Year Format
             */
            public void valueChange(final ValueChangeEvent event) {
                ifpStartDate.setValue(GeneralCommonUtils.convert2DigitTo4DigitYearFormat(ifpStartDate.getValue()));
            }
        });
        ifpEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        ifpEndDate.setImmediate(true);
        ifpEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to convert 2 Digit To 4 Digit Year Format
             */
            public void valueChange(final ValueChangeEvent event) {
                ifpEndDate.setValue(GeneralCommonUtils.convert2DigitTo4DigitYearFormat(ifpEndDate.getValue()));
            }
        });

        com.stpl.app.global.item.util.CommonUtils.getComboBox(searchFields);
        searchFields.setContainerDataSource(RsUtils.searchFields());
        searchFields.select(ConstantsUtils.SELECT_ONE);
        searchFields.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used to search field logic and its listener.
             */
            public void valueChange(final ValueChangeEvent event) {

                if (event != null) {

                    String value = String.valueOf(event.getProperty().getValue());                    

                    if ("IFP No".equals(value)) {
                        ifpNo.setVisible(true);
                        ifpName.setVisible(false);
                        ifpName.setValue(StringUtils.EMPTY);
                        ifpType.setVisible(false);
                        ifpType.select(null);
                        ifpStartDate.setVisible(false);
                        ifpStartDate.setValue(null);
                        ifpEndDate.setVisible(false);
                        ifpEndDate.setValue(null);
                        itemName.setVisible(false);
                        itemName.setValue(StringUtils.EMPTY);
                        itemID.setVisible(false);
                        itemID.setValue(StringUtils.EMPTY);
                        ifpCategory.setVisible(false);
                        ifpCategory.select(null);
                        ifpStatus.setVisible(false);
                        ifpStatus.select(null);
                    } else if ("IFP Name".equals(value)) {
                        ifpNo.setVisible(false);
                        ifpNo.setValue(StringUtils.EMPTY);
                        ifpName.setVisible(true);
                        ifpType.setVisible(false);
                        ifpType.select(null);
                        ifpStartDate.setVisible(false);
                        ifpStartDate.setValue(null);
                        ifpEndDate.setVisible(false);
                        ifpEndDate.setValue(null);
                        itemName.setVisible(false);
                        itemName.setValue(StringUtils.EMPTY);
                        itemID.setVisible(false);
                        itemID.setValue(StringUtils.EMPTY);
                        ifpCategory.setVisible(false);
                        ifpCategory.select(null);
                        ifpStatus.setVisible(false);
                        ifpStatus.select(null);
                    } else if ("IFP Type".equals(value)) {
                        ifpNo.setVisible(false);
                        ifpNo.setValue(StringUtils.EMPTY);
                        ifpName.setVisible(false);
                        ifpName.setValue(StringUtils.EMPTY);
                        ifpType.setVisible(true);
                        ifpStartDate.setVisible(false);
                        ifpStartDate.setValue(null);
                        ifpEndDate.setVisible(false);
                        ifpEndDate.setValue(null);
                        itemName.setVisible(false);
                        itemName.setValue(StringUtils.EMPTY);
                        itemID.setVisible(false);
                        itemID.setValue(StringUtils.EMPTY);
                        ifpCategory.setVisible(false);
                        ifpCategory.select(null);
                        ifpStatus.setVisible(false);
                        ifpStatus.select(null);
                    } else if ("IFP Start Date".equals(value)) {
                        ifpNo.setVisible(false);
                        ifpNo.setValue(StringUtils.EMPTY);
                        ifpName.setVisible(false);
                        ifpName.setValue(StringUtils.EMPTY);
                        ifpType.setVisible(false);
                        ifpType.select(null);
                        ifpStartDate.setVisible(true);
                        ifpEndDate.setVisible(false);
                        ifpEndDate.setValue(null);
                        itemName.setVisible(false);
                        itemName.setValue(StringUtils.EMPTY);
                        itemID.setVisible(false);
                        itemID.setValue(StringUtils.EMPTY);
                        ifpCategory.setVisible(false);
                        ifpCategory.select(null);
                        ifpStatus.setVisible(false);
                        ifpStatus.select(null);
                    } else if ("IFP End Date".equals(value)) {
                        ifpNo.setVisible(false);
                        ifpNo.setValue(StringUtils.EMPTY);
                        ifpName.setVisible(false);
                        ifpName.setValue(StringUtils.EMPTY);
                        ifpType.setVisible(false);
                        ifpType.select(null);
                        ifpStartDate.setVisible(false);
                        ifpStartDate.setValue(null);
                        ifpEndDate.setVisible(true);
                        itemName.setVisible(false);
                        itemName.setValue(StringUtils.EMPTY);
                        itemID.setVisible(false);
                        itemID.setValue(StringUtils.EMPTY);
                        ifpCategory.setVisible(false);
                        ifpCategory.select(null);
                        ifpStatus.setVisible(false);
                        ifpStatus.select(null);
                    } else if ("IFP ID".equals(value)) {
                        ifpNo.setVisible(false);
                        ifpNo.setValue(StringUtils.EMPTY);
                        ifpName.setVisible(false);
                        ifpName.setValue(StringUtils.EMPTY);
                        ifpType.setVisible(false);
                        ifpType.select(null);
                        ifpStartDate.setVisible(false);
                        ifpStartDate.setValue(null);
                        ifpEndDate.setVisible(false);
                        ifpEndDate.setValue(null);
                        itemName.setVisible(false);
                        itemName.setValue(StringUtils.EMPTY);
                        itemID.setVisible(true);
                        ifpCategory.setVisible(false);
                        ifpCategory.select(null);
                        ifpStatus.setVisible(false);
                        ifpStatus.select(null);
                    } else if ("Item Name".equals(value)) {
                        ifpNo.setVisible(false);
                        ifpNo.setValue(StringUtils.EMPTY);
                        ifpName.setVisible(false);
                        ifpName.setValue(StringUtils.EMPTY);
                        ifpType.setVisible(false);
                        ifpType.select(null);
                        ifpStartDate.setVisible(false);
                        ifpStartDate.setValue(null);
                        ifpEndDate.setVisible(false);
                        ifpEndDate.setValue(null);
                        itemName.setVisible(true);
                        itemID.setVisible(false);
                        itemID.setValue(StringUtils.EMPTY);
                        ifpCategory.setVisible(false);
                        ifpCategory.select(null);
                        ifpStatus.setVisible(false);
                        ifpStatus.select(null);
                    } else if ("IFP Category".equals(value)) {
                        ifpNo.setVisible(false);
                        ifpNo.setValue(StringUtils.EMPTY);
                        ifpName.setVisible(false);
                        ifpName.setValue(StringUtils.EMPTY);
                        ifpType.setVisible(false);
                        ifpType.select(null);
                        ifpStartDate.setVisible(false);
                        ifpStartDate.setValue(null);
                        ifpEndDate.setVisible(false);
                        ifpEndDate.setValue(null);
                        itemName.setVisible(false);
                        itemID.setVisible(false);
                        itemID.setValue(StringUtils.EMPTY);
                        ifpCategory.setVisible(true);
                        ifpCategory.select(null);
                        ifpStatus.setVisible(false);
                        ifpStatus.select(null);
                    } else if ("IFP Status".equals(value)) {
                        ifpNo.setVisible(false);
                        ifpNo.setValue(StringUtils.EMPTY);
                        ifpName.setVisible(false);
                        ifpName.setValue(StringUtils.EMPTY);
                        ifpType.setVisible(false);
                        ifpType.select(null);
                        ifpStartDate.setVisible(false);
                        ifpStartDate.setValue(null);
                        ifpEndDate.setVisible(false);
                        ifpEndDate.setValue(null);
                        itemName.setVisible(false);
                        itemID.setVisible(false);
                        itemID.setValue(StringUtils.EMPTY);
                        ifpCategory.setVisible(false);
                        ifpCategory.select(null);
                        ifpStatus.setVisible(true);
                        ifpStatus.select(null);
                    }

                }

            }
        });
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

    public void focusIfpNo() {
        ifpNo.focus();
    }

    public ExtFilterTable getAvailableTable() {
        return availableTable;
    }

    public ExtFilterTable getSelectedTable() {
        return selectedTable;
    }
    /**
     * Table to view results
     */
    public void configureResultsTableForView() {
        try {
            resultsTable.setContainerDataSource(selectedItemResultBean);
            final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Item Addition",false);

            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldRsHM, mode);
            if (tableResultCustom.getObjResult().length == 0) {
                resultsTable.setVisible(false);
                prevColumn.setVisible(false);
                nextColumn.setVisible(false);
            }
            resultsTable.setVisibleColumns(tableResultCustom.getObjResult());
            resultsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            resultsTable.setColumnCollapsingAllowed(true);
            resultsTable.setPageLength(NumericConstants.EIGHT);
            resultsTable.setImmediate(true);
            resultsTable.setSizeFull();
            resultsTable.setSelectable(false);
            resultsTable.setSizeFull();
            resultsTable.setEditable(false);
            ResponsiveUtils.addButtonListeners(resultsTable, prevColumn, nextColumn);
        } catch (Exception e) {
           LOGGER.error(e);
        }
    }

    private void addResponsiveness(final Map<String, AppPermission> fieldRsHM) {
        LOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_SCHEDULE, "Item Addition");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldRsHM, mode);
            
        } catch (Exception ex) {
            LOGGER.error(ex);            
        }
        LOGGER.debug("Ending configurePermission");

    }    
     
    public void resetItemAdditionSearchCiteria() {
                
        searchFields.select(ConstantsUtils.SELECT_ONE);
        ifpNo.setValue(StringUtils.EMPTY);        
        ifpName.setValue(StringUtils.EMPTY);        
        itemID.setValue(StringUtils.EMPTY);        
        itemName.setValue(StringUtils.EMPTY);        
        ifpType.setValue(null);        
        ifpStartDate.setValue(null);        
        ifpEndDate.setValue(null);
        ifpStatus.setValue(null);
        ifpCategory.setValue(null);
        selectedItemResultBean.removeAllItems();            
                
    }
    
    
    public void resetForItemDetails() {
        final IFPDetailsDTO item = (IFPDetailsDTO) selectedItemResultBean.lastItemId();
        selectedIfp.setValue(String.valueOf(item.getItemFamilyplanSystemId()));
        String ifpSysId = String.valueOf(item.getItemFamilyplanSystemId());
        final String idValue = String.valueOf(sessionDTO.getSystemId());        
        if (item.getItemFamilyplanSystemId() != 0) {
            rebateLogic.addItemDetailsFromIfp(ifpSysId, idValue);
        }
    }
    
    
}
