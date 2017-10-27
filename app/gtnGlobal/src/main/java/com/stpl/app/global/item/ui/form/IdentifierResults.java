package com.stpl.app.global.item.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.dto.IdentifierPricingGenerater;
import com.stpl.app.global.item.dto.ItemIdentifierTableGenerator;
import com.stpl.app.global.item.dto.ItemIrtIdentifierDTO;
import com.stpl.app.global.item.dto.ItemMasterGenerate;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.ui.lazyload.ItemQualifierNameContainer;
import com.stpl.app.global.item.ui.lazyload.ItemQualifierNameCriteria;
import com.stpl.app.global.item.util.FieldNameUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.model.ItemQualifier;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

// TODO: Auto-generated Javadoc
/**
 * The Class IdentifierResults.
 */
public final class IdentifierResults extends StplCustomComponent {

    @UiField("cssLayout")
    CssLayout cssLayout;
    @UiField("hLayout")
    HorizontalLayout hlayout;
    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<>();
    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(IdentifierResults.class);
    /**
     * The space.
     */
    private final Label space = new Label("&nbsp;", ContentMode.HTML);
    /**
     * The error msg.
     */
    private final ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The item logic.
     */
    private final ItemSearchLogic itemLogic = new ItemSearchLogic();
    /**
     * The identifier form bean.
     */
    private final ItemIrtIdentifierDTO identifierFormBean = new ItemIrtIdentifierDTO();
    /**
     * The uniqueness binder.
     */
    private ErrorfulFieldGroup uniquenessBinder;
    ParentCompanyNo lookUp=null;
    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(identifierFormBean));
    /**
     * The identifier results bean.
     */
    private BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean;
    @UiField("table")
    private ExtFilterTable table;
    @UiField("tablePanel")
    private VerticalLayout tablePanel;
    @UiField("itemIrtQualifierNameLB")
    private Label itemIrtQualifierNameLB;
    @UiField("itemIrtQualifierNameDDLB")
    private ComboBox itemIrtQualifierNameDDLB;
    @UiField("itemIdentifierLB")
    private Label itemIdentifierLB;
    @UiField("itemIdentifier")
    private TextField itemIdentifier;
    @UiField("entityCodeLB")
    private Label entityCodeLB;
    @UiField("entityCode")
    private CustomTextField entityCode;
    private final TextField entityCodeSid = new TextField();
    @UiField("startDateLB")
    private Label startDateLB;
    @UiField("startDate")
    private PopupDateField startDate;
    @UiField("endDateLB")
    private Label endDateLB;
    @UiField("endDate")
    private PopupDateField endDate;
    @UiField("identifierStatusLB")
    private Label identifierStatusLB;
    @UiField("identifierStatus")
    private ComboBox identifierStatus;
    @UiField("removeBtn")
    private Button btnremove;
    @UiField("excelExport")
    private Button excelExport;
    @UiField("entityCodeNameLB")
    private Label entityCodeNameLB;
    @UiField("entityCodeName")
    private CustomTextField entityCodeName;
    @UiField("btnPopulate")
    private Button btnPopulate;
    @UiField("identifierInfoLayout")
    private Panel identifierInfoLayout;
    
    @UiField("BtnLayout")
    private HorizontalLayout BtnLayout;
      /**
     * The attachBtnLayout Layout.
     */
    @UiField("attachBtnLayout")
    private HorizontalLayout attachBtnLayout;
   
    IFPLogic ifpLogic = new IFPLogic(); 
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    
    /**
     * Popup Window.
     */
    private IdenQualifierEditList sub = new IdenQualifierEditList(false);
    /**
     *
     */
    private final HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private final String mode;
    private final boolean isEditMode;
    private final boolean isViewMode;
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    private final UIUtils uiUtils = UIUtils.getInstance();
        CommonUtil util = CommonUtil.getInstance();
  /**
   * The Constructor.
   * 
   * 
   * @param uniquenessBinder
   * @param identifierResultsBean
   * @param mode
   * @throws SystemException
   * @throws PortalException
   * @throws Exception 
   */
    public IdentifierResults(final ErrorfulFieldGroup uniquenessBinder,
            final BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean, String mode) throws SystemException, PortalException {
        super();
        this.uniquenessBinder = uniquenessBinder;
        this.identifierResultsBean = identifierResultsBean;
        this.mode = mode;
        this.isEditMode = ConstantsUtils.EDIT.equals(mode);
        this.isViewMode = ConstantsUtils.VIEW.equals(mode);
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/itemmaster/identifierresults.xml"), this));
        init();
        table.setImmediate(true);

        table.setFilterGenerator(new IdentifierPricingGenerater());

    }

    /**
     * For Initialization.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void init() throws SystemException, PortalException {
        space.setHeight(ConstantsUtils.HEIGHT);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
       
           if (isEditMode || isViewMode ) {
            addToContent();
            configureFields();
            getBinder();

        } else {
            getBinder();
            addToContent();
            configureFields();
             }
            if (isViewMode) {
            identifierInfoLayout.setVisible(false);
            btnPopulate.setVisible(false);
            btnremove.setVisible(false);
        }
                    addValidationForTextField(binder);
           
                           

    }

    /**
     * Adds the components to layout.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void addToContent() throws SystemException, PortalException {

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldItemHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_MASTER+ConstantsUtils.COMMA+ConstantsUtils.IDENTIFIER1,false);
         final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId,UISecurityUtil.ITEM_MASTER+ConstantsUtils.COMMA+ConstantsUtils.IDENTIFIER1);
        addResponsiveGrid(fieldItemHM,functionHM);
        addToTable();
        addResponsiveVerticalTabThreeLayout();
    }


    private void addResponsiveGrid(final Map<String, AppPermission> fieldItemHM,final Map<String, AppPermission> functionHM) {
        LOGGER.debug("Entering getFirstTab1");
        try {
        
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_MASTER,ConstantsUtils.IDENTIFIER1);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldItemHM, mode);
        if (functionHM.get(FieldNameUtils.ATTACH_IDENTIFIER) != null && ((AppPermission) functionHM.get(FieldNameUtils.ATTACH_IDENTIFIER)).isFunctionFlag()) {
            populateButton();
        }else{
            attachBtnLayout.removeComponent(btnPopulate);
        
        }
        if (functionHM.get(FieldNameUtils.REMOVE_IDENTIFIER) != null && ((AppPermission) functionHM.get(FieldNameUtils.REMOVE_IDENTIFIER)).isFunctionFlag()) {
            removeButton();
        }else{
            BtnLayout.removeComponent(btnremove);
        }
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending getFirstTab1");
    }

    /**
     * Adds the properties to table.
     *
     * @return the table
     * @throws Exception the exception
     */
    public ExtFilterTable addToTable() throws PortalException, SystemException {
         final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_MASTER+ConstantsUtils.COMMA+ ConstantsUtils.IDENTIFIER_HEADER,false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_MASTER, ConstantsUtils.IDENTIFIER_HEADER);
            Object[] obj = uiUtils.idenFormColOrder;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
          if(tableResultCustom.getObjResult().length > 0){
            table.markAsDirty();
            table.setContainerDataSource(identifierResultsBean);
            table.setVisibleColumns(tableResultCustom.getObjResult());
            table.setColumnHeaders(tableResultCustom.getObjResultHeader());
          }else{
              tablePanel.setVisible(false);
          }
        table.setPageLength(NumericConstants.THREE);
        table.setImmediate(true);
        table.setSizeFull();

        if (!isViewMode) {
            table.setSelectable(true);
            table.setEditable(true);
            table.setFilterBarVisible(true);
            table.setTableFieldFactory(new ItemIdentifierTableGenerator());
        }

        table.addStyleName(ConstantsUtils.FILTER_BAR);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setFilterGenerator(new IdentifierPricingGenerater());
        table.setValidationVisible(false);
        table.setImmediate(true);
        table.addItemClickListener(new ItemClickListener() {
            /**
             * For Item click event.
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                /**
                 * Empty item click event.
                 */
            }
        });
        table.setErrorHandler(new ErrorHandler() {
            /**
             * For Error Event
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                /**
                 * Empty error event.
                 */
            }
        });

        return table;
              
    }
/**
 *  To set default focus
 */
    public void setDefaultFocus() {
        itemIrtQualifierNameDDLB.focus();
    }

    /**
     * Logic for Populate button.
     *
     * @return the button
     * @throws Exception the exception
     */
    public void populateButton() {

        btnPopulate.setImmediate(true);
        btnPopulate.setWidth("85");
        btnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * For Error Event
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                /**
                 * Empty error event.
                 */
            }
        });
        btnPopulate.addClickListener(new ClickListener() {
            /**
             * After clicking the populate button, function will be executed.
             *
             * @param event
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering IdentifierResult Attach operation");
                    uniquenessBinder.getErrorDisplay().clearError();
                    binder.getErrorDisplay().clearError();
                    binder.getFields();
                    binder.commit();
                    boolean flag = false;
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                    if (binder.getField(ConstantsUtils.ITEM_IRT_QUALIFIER_NAME_DDLB).getValue().toString().equals(ConstantsUtils.SELECT_ONE)) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Item Qualifier Name");
                        flag = true;
                    }
                    if (StringUtils.isBlank(binder.getField(ConstantsUtils.ITEM_IDENTIFIER).getValue().toString())) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Item Identifier");
                        flag = true;
                    }
                       if(binder.getField(ConstantsUtils.IDENTIFIER_STATUS).getValue()==null || ((com.stpl.ifs.util.HelperDTO)binder.getField(ConstantsUtils.IDENTIFIER_STATUS).getValue()).getId()==0){ 
                          if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Identifier Status");
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.START_DATE).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Start Date");
                        flag = true;
                    }
                    if (flag) {
                        uniquenessBinder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }
                    if (startDate.getValue() != null && endDate.getValue() != null && startDate.getValue().after(endDate.getValue())) {
                        uniquenessBinder.getErrorDisplay().setError("End date should be greater than Start date");
                        return;
                    } else if (startDate.getValue() != null && endDate.getValue() != null && startDate.getValue().getTime() == endDate.getValue().getTime()) {
                        uniquenessBinder.getErrorDisplay().setError("Start date and End date should not be  equal");
                        return;
                    }
                    table.setFilterGenerator(new ItemMasterGenerate());
                    final ItemIrtIdentifierDTO identForm = new ItemIrtIdentifierDTO();
                    identForm.setItemIrtQualifierName(String.valueOf(binder.getField(ConstantsUtils.ITEM_IRT_QUALIFIER_NAME_DDLB).getValue()));
                    identForm.setItemIdentifier(String.valueOf(binder.getField(ConstantsUtils.ITEM_IDENTIFIER).getValue()));
                    identForm.setEntityCode(String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE).getValue()));
                    identForm.setEntityCodeName(String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE_NAME).getValue()));
                    identForm.setEntityCodeSid(String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE_SID).getValue()));
                    identForm.setIdentifierStatus( (com.stpl.ifs.util.HelperDTO)(binder.getField(ConstantsUtils.IDENTIFIER_STATUS).getValue()));
                    String createdBy = StplSecurity.userMap.get(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                    identForm.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);
                    identForm.setCreatedDate(new Date());
                    if (binder.getField(ConstantsUtils.START_DATE).getValue() != null) {
                        identForm.setStartDate((Date) binder.getField(ConstantsUtils.START_DATE).getValue());
                    }
                    if (binder.getField(ConstantsUtils.END_DATE).getValue() != null) {
                        identForm.setEndDate((Date) binder.getField(ConstantsUtils.END_DATE).getValue());
                    }
                    final ItemQualifier qualif = itemLogic.getItemIrtQualifierByName(identForm.getItemIrtQualifierName());
                    final DynamicQuery itemIdentifierDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemIdentifier.class);
                    itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_IDENTIFIER_VALUE, identForm.getItemIdentifier()));
                    itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.START_DATE, identForm.getStartDate()));
                    itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_QUALIFIER_SID, qualif.getItemQualifierSid()));
                    itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                    final List<ItemIdentifier> itemIdentiferList = itemLogic.getItemIrtIdentifierList(itemIdentifierDynamicQuery);
                    final String systemId = uniquenessBinder.getField(ConstantsUtils.ITEM_MASTER_SID) == null || uniquenessBinder.getField(ConstantsUtils.ITEM_MASTER_SID).getValue() == null
                            || uniquenessBinder.getField(ConstantsUtils.ITEM_MASTER_SID).getValue().equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(uniquenessBinder
                            .getField(ConstantsUtils.ITEM_MASTER_SID).getValue().toString());

                    if (StringUtils.EMPTY.equals(systemId)) {
                        if (itemIdentiferList.size() > 0) {

                            uniquenessBinder.getErrorDisplay().setError("Item Identifier is already exist for some other Item");
                            return;
                        }
                    } else {
                        final int primaryId = Integer.parseInt(systemId);
                        itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid", primaryId));
                        final List<ItemIdentifier> tempList = itemLogic.getItemIrtIdentifierList(itemIdentifierDynamicQuery);
                        if ((itemIdentiferList.size() - tempList.size()) > 0) {
                            uniquenessBinder.getErrorDisplay().setError("Item Identifier is already exist for some other Item");
                            return;
                        }
                    }
                    for (int i = 0; i < identifierResultsBean.size(); i++) {
                        if (identifierResultsBean.getIdByIndex(i).getItemIdentifier().equals(identForm.getItemIdentifier()) && identifierResultsBean.getIdByIndex(i).getStartDate().equals(identForm.getStartDate())) {
                                final ItemQualifier qual = itemLogic.getItemIrtQualifierByName(identForm.getItemIrtQualifierName());
                                final ItemQualifier qualBean = itemLogic.getItemIrtQualifierByName(identifierResultsBean.getIdByIndex(i).getItemIrtQualifierName());
                                if (qual.getItemQualifierSid() == qualBean.getItemQualifierSid()) {
                                    uniquenessBinder.getErrorDisplay().setError("Item Identifier already added for this Item");
                                    return;
                                }
                        }
                    }
                    identifierResultsBean.addBean(identForm);
                    binder.setItemDataSource(new BeanItem<>(new ItemIrtIdentifierDTO()));
                    itemIrtQualifierNameDDLB.select(dto);

                    resetBtnLogic();

                } catch (FieldGroup.CommitException commitException) {
                    LOGGER.error(commitException);
                    if (commitException.getCause().getMessage().equals(ValidationUtils.SPECIAL_CHAR_MSG)) {
                        binder.getErrorDisplay().clearError();
                        uniquenessBinder.getErrorDisplay().setError(ValidationUtils.SPECIAL_CHAR_MSG);
                        return;
                    }
                    if (commitException.getCause().getMessage().equals("Item Identifier should be less than 50 characters")) {
                        binder.getErrorDisplay().clearError();
                        uniquenessBinder.getErrorDisplay().setError("Item Identifier should be less than 50 characters");
                        return;
                    }
                    boolean flag = false;
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                    if (commitException.getCause().getMessage().equals("Date format not recognized")) {
                        return;
                    }
                    if (binder.getField(ConstantsUtils.ITEM_IRT_QUALIFIER_NAME_DDLB).getValue().toString().equals(ConstantsUtils.SELECT_ONE)) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Item Qualifier Name");
                        flag = true;
                    }
                    if (StringUtils.isBlank(binder.getField(ConstantsUtils.ITEM_IDENTIFIER).getValue().toString())) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Item Identifier");
                        flag = true;
                    }
                     if(binder.getField(ConstantsUtils.IDENTIFIER_STATUS).getValue()==null || ((com.stpl.ifs.util.HelperDTO)binder.getField(ConstantsUtils.IDENTIFIER_STATUS).getValue()).getId()==0){ 
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Identifier Status");
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.START_DATE).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Start Date");
                        flag = true;
                    }
                    if (flag) {
                        uniquenessBinder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                     final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {   
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
                    msgBox.getButton(ButtonId.OK).focus();
                }
                LOGGER.debug("Ending IdentifierResult Attach operation");
            }
        });

    }

    /**
     * Convert date to string.
     *
     * @param date the date
     * @return the string
     * @throws ParseException the parse exception
     */
    public String convertDateToString(final String date) throws ParseException {

        final DateFormat formatter = new SimpleDateFormat(ConstantsUtils.SIMPLE_DATE_FORMAT);
        final Date sDate = (Date) formatter.parse(date);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(sDate);
        return cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);

    }

    /**
     * Date to date convert.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date dateToDateConvert(final Date date) throws ParseException {
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

        return inputFormat.parse(inputFormat.format(date));

    }

    /**
     * Logic for Removes the button.
     *
     * @return the button
     */
    public void removeButton() {


        btnremove.setWidth(ConstantsUtils.BTN_WIDTH);
        btnremove.setImmediate(true);
        btnremove.addClickListener(new ClickListener() {
            /**
             * After clicking remove button, function will be executed.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering IdentifierResult Remove operation");
                try {
                    uniquenessBinder.getErrorDisplay().clearError();
                    binder.getErrorDisplay().clearError();
                    final Object itemId = table.getValue();
                    uniquenessBinder.getErrorDisplay().clearError();
                    binder.getErrorDisplay().clearError();
                    if (itemId == null) {
                        uniquenessBinder.getErrorDisplay().setError("Please select an identifier from the list view to remove.");
                    } else {
                        MessageBox.showPlain(Icon.QUESTION, "Remove Confirmation", " Are you sure you want to Remove the Identifier?", new MessageBoxListener() {
                            /**
                             * After clicking button, function will be executed.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    identifierResultsBean.removeItem(itemId);
                                    table.unselect(itemId);
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    }



                    table.setValue(null);
                } catch (Exception exception) {
                    final MessageBox msgBox = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1013), new MessageBoxListener() {   
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
                    msgBox.getButton(ButtonId.OK).focus();
                    LOGGER.error(exception);

                }
                LOGGER.debug("Ending IdentifierResult Remove operation");
            }
        });

    }

    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void configureFields() {
        LOGGER.debug("Enters configureFields() ");


        startDate.setDescription(ConstantsUtils.DATE_DES);
        endDate.setDescription(ConstantsUtils.DATE_DES);

        itemIrtQualifierNameDDLB.setErrorHandler(new ErrorHandler() {
            /**
             * For Error Event
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                /**
                 * Empty error event.
                 */
            }
        });
        itemIrtQualifierNameDDLB.setPageLength(NumericConstants.SEVEN);
        itemIrtQualifierNameDDLB.setImmediate(true);
        itemIrtQualifierNameDDLB.setNullSelectionAllowed(true);
        itemIrtQualifierNameDDLB.setInputPrompt(ConstantsUtils.SELECT_ONE);
        itemIrtQualifierNameDDLB.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        itemIrtQualifierNameDDLB.setContainerDataSource(getQualifierItems());
        itemIrtQualifierNameDDLB.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        itemIrtQualifierNameDDLB.markAsDirty();
        itemIrtQualifierNameDDLB.select(dto);

        itemIdentifier.setImmediate(true);
       itemIdentifier.setData("maxlengthvalidationfifty,maxlengthvalidationitemidentifier,alphaNumericCharsWithoutStar,specialcharvalidationitemidentifier");
        itemIdentifier.setValidationVisible(true);
        itemIdentifier.setDescription(itemIdentifier.getValue());
        itemIdentifier.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in itemIdentifier, function will be
             * executed.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {

                itemIdentifier.setDescription(itemIdentifier.getValue());

            }
        });

        entityCodeSid.setVisible(false);
        entityCode.setImmediate(true);
        entityCode.setStyleName("searchicon");
        entityCode.setDescription(entityCode.getValue());
        entityCode.setReadOnly(true);
        entityCodeName.setReadOnly(true);
        entityCodeName.setImmediate(true);
        entityCode.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in entityCode, function will be
             * executed.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {

                entityCode.setDescription(entityCode.getValue());

            }
        });

        entityCode.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Logic for focus event.
             *
             * @param event
             */
            public void click(final CustomTextField.ClickEvent event) {
                try {
                    if(lookUp==null){
                    LOGGER.debug("Inside Entity Code");
                    lookUp = new ParentCompanyNo(entityCodeName, entityCode, entityCodeSid);
                    UI.getCurrent().addWindow(lookUp);
                    }
                    lookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * window
                         */
                        public void windowClose(final Window.CloseEvent e) {
                            endDate.focus();
                              lookUp = null;
                        }
                    });
                   LOGGER.debug("Ends Entity Code");
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });


        startDate.setValidationVisible(true);
        startDate.setImmediate(true);
        startDate.setId("IdentifierSD");
        startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value, function will be executed.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                    startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
            }
        });
        endDate.setValidationVisible(true);
        endDate.setImmediate(true);
        endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        endDate.setId("IdentifierED");
        endDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value, function will be executed.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                    endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
            }
        });


          commonUtil.loadComboBox(identifierStatus, UIUtils.STATUS, false);
        
        itemIrtQualifierNameDDLB.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in itemIrtQualifierName, function will
             * be executed.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {

                itemIrtQualifierNameDDLB.setDescription(String.valueOf(itemIrtQualifierNameDDLB.getValue()));
                final String value;
                if (itemIrtQualifierNameDDLB.getValue() == null) {
                    value = StringUtils.EMPTY;
                } else {
                    value = String.valueOf(itemIrtQualifierNameDDLB.getValue());
                }
                if (value.equals(ConstantsUtils.EDITLIST)) {
                    UI.getCurrent().addWindow(sub);

                }


            }
        });

        sub.addCloseListener(new Window.CloseListener() {
            /**
             * Windows listener
             */
            public void windowClose(final CloseEvent event) {
                try {
                    itemIrtQualifierNameDDLB.setContainerDataSource(getQualifierItems());
                    itemIrtQualifierNameDDLB.markAsDirty();
                    itemIrtQualifierNameDDLB.select(dto);

                }catch (Exception exception) {
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
                    LOGGER.error(exception);
                }
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                // Do Nothing
                
            }
        });
        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
        excelExport.setDescription("Export to excel");
        excelExport.setIconAlternateText("Excel export");
        excelExport.setHtmlContentAllowed(true);
        excelExport.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // parses the error.
            }
        });
        excelExport.addClickListener(new ClickListener() {
            /**
             * calls excelExportLogic method on button click
             *
             * @param event - Mouse Click event
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering EXCEL Export Button Click :::: ");
                    binder.getFields();
          
                    excelExportLogic();
                    LOGGER.debug(" Ends  EXCEL Export Button Click ::::  ");

                } catch (SystemException sysException) {

                    final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
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
                } catch (PortalException portException) {

                    LOGGER.error(portException);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011), new MessageBoxListener() {   
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
                } catch (Exception exception) {

                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011), new MessageBoxListener() {   
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
    }

    protected void excelExportLogic() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        final long recordCount = table.getContainerDataSource().size();
        ExcelExportforBB.createWorkSheet(table.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.IDENTIFIER);
        LOGGER.debug("Ending createWorkSheet");
    }

    /**
     * Logic for creating worksheet content.
     *
     * @param start the start
     * @param end the end
     * @param printWriter the print writer
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        ItemIrtIdentifierDTO dto;
        final List<ItemIrtIdentifierDTO> searchList = identifierResultsBean.getItemIds();
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT, Locale.getDefault());
        String date;
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);

            printWriter.print(ConstantsUtils.QUOTE + dto.getItemIrtQualifierName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getItemIdentifier() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            String status = dto.getIdentifierStatus().getId() != 0 ? dto.getIdentifierStatus().getDescription() : StringUtils.EMPTY;

            printWriter.print(ConstantsUtils.QUOTE + status + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            if (dto.getStartDate() == null) {
                printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
            } else {
                date = dateFormat.format(dto.getStartDate());
                printWriter.print(date + ExcelExportUtil.COMMA);
            }

            if (dto.getEndDate() == null) {
                printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
            } else {
                date = dateFormat.format(dto.getEndDate());
                printWriter.print(date + ExcelExportUtil.COMMA);
            }

            printWriter.print(ConstantsUtils.QUOTE + dto.getEntityCode() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getEntityCodeName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getModifiedBy() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            if (dto.getModifiedDate() == null) {
                printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
            } else {
                date = dateFormat.format(dto.getModifiedDate());
                printWriter.print(date + ExcelExportUtil.COMMA);
            }

            printWriter.print(ConstantsUtils.QUOTE + dto.getCreatedBy() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            if (dto.getCreatedDate() == null) {
                printWriter.println(StringUtils.EMPTY);
            } else {
                date = dateFormat.format(dto.getCreatedDate());
                printWriter.println(date);
            }

        }

    }

    public void resetBtnLogic() {
        itemIrtQualifierNameDDLB.setValue(dto);
        identifierStatus.setValue(null);
        itemIdentifier.setValue(StringUtils.EMPTY);
        entityCode.setReadOnly(false);
        entityCode.setValue(StringUtils.EMPTY);
        entityCodeName.setReadOnly(false);
        entityCodeName.setValue(StringUtils.EMPTY);
        entityCodeSid.setValue(StringUtils.EMPTY);
        entityCode.setReadOnly(true);
        entityCodeName.setReadOnly(true);
        startDate.setValue(null);
        endDate.setValue(null);
    }

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings(ConstantsUtils.RAWTYPES)
    public class DateValidator extends AbstractValidator {

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * validating the value.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) {
            if (startDate.getValue() != null && endDate.getValue() != null) {
                if (startDate.getValue().after(endDate.getValue())) {
                    throw new InvalidValueException("End date should be greater than Start date");
                } else if (startDate.getValue().getTime() == endDate.getValue().getTime()) {
                    throw new InvalidValueException("Start date and End date should not be  equal");
                }
            }

        }

        /**
         * Checking value is valid or not.
         *
         * @param value the value
         * @return true, if checks if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (startDate.getValue() != null && endDate.getValue() != null) {
                return startDate.getValue().compareTo(startDate.getValue()) <= 0;
            }

            return true;
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
     * Gets the sub.
     *
     * @return the sub
     */
    public IdenQualifierEditList getSub() {
        return sub;
    }

    /**
     * Sets the sub.
     *
     * @param sub the new sub
     */
    public void setSub(final IdenQualifierEditList sub) {
        this.sub = sub;
    }

    /**
     * Gets the qualifier items.
     *
     * @return the qualifier items
     */
    private LazyContainer getQualifierItems() {

        final LazyContainer container = new LazyContainer(HelperDTO.class, new ItemQualifierNameContainer(true), new ItemQualifierNameCriteria());
        container.setMinFilterLength(0);
        return container;

    }

    private static Object[] getCollapsibleColumnsDefault(ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = 0, j = list.size(); i < j; i++) {
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

    public void addResponsiveVerticalTabThreeLayout() {

        reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
        reloadVerticalLayoutTabThreeMap.put(0, true);

        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(
                    final Page.BrowserWindowResizeEvent event) {

                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX && reloadVerticalLayoutTabThreeMap.get(NumericConstants.ONE_FIVE_ONE_SIX)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(table);
                } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.THOUSAND_THREE_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THOUSAND_THREE_HUNDRED)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(table);
                } else if (browserWidth < NumericConstants.THOUSAND_THREE_HUNDRED && browserWidth > NumericConstants.ONE_ZERO_TWO_FOUR && reloadVerticalLayoutTabThreeMap.get(NumericConstants.ONE_ZERO_TWO_FOUR)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < NumericConstants.ONE_ZERO_TWO_FOUR && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadVerticalLayoutTabThreeMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.EIGHT_HUNDRED && reloadVerticalLayoutTabThreeMap.get(NumericConstants.EIGHT_HUNDRED)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    if (table.getItemIds().isEmpty()) {
                    } else {
                    }
                } else if (browserWidth <= NumericConstants.EIGHT_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(NumericConstants.THREE_TWO_ZERO)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, false);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadVerticalLayoutTabThreeMap.get(0)) {
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.EIGHT_HUNDRED, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(NumericConstants.THREE_TWO_ZERO, true);
                    reloadVerticalLayoutTabThreeMap.put(0, false);
                }

            }
        });
    }
      private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);
        return binder;
    }
     private void addValidationForTextField(ErrorfulFieldGroup binder){
        Collection collection = binder.getFields();
        for(Object object:collection){
            if(object instanceof TextField){
                TextField filed = (TextField)object;
                util.textValidation(filed, filed.getData());
            }
        }
    }
}
