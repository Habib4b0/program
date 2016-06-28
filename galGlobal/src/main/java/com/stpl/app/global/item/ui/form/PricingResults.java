package com.stpl.app.global.item.ui.form;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.dto.IdentifierPricingGenerater;
import com.stpl.app.global.item.dto.ItemPricingDTO;
import com.stpl.app.global.item.dto.ItemPricingTableGenerator;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.ui.lazyload.PriceTypeContainer;
import com.stpl.app.global.item.ui.lazyload.PriceTypeCriteria;
import com.stpl.app.global.item.util.FieldNameUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.RsModelLocalServiceUtil;
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
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
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
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.jsoup.helper.StringUtil;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

// TODO: Auto-generated Javadoc
/**
 * The Class PricingResults.
 */
public final class PricingResults extends StplCustomComponent {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PricingResults.class);
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
    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<Integer, Boolean>();
    private final ItemSearchLogic itemLogic = new ItemSearchLogic();
    /**
     * The pricing table bean.
     */
    private BeanItemContainer<ItemPricingDTO> pricingTableBean;
    
    private BeanItemContainer<ItemPricingDTO> tempPricing = new BeanItemContainer<ItemPricingDTO>(ItemPricingDTO.class);
    /**
     * The pricing form bean.
     */
    private final ItemPricingDTO pricingFormBean = new ItemPricingDTO();
    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<ItemPricingDTO>(pricingFormBean));
     CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
     IFPLogic ifpLogic = new IFPLogic(); 
     CommonUIUtils commonUiUtil = new CommonUIUtils();

      @UiField("BtnLayout")
    private HorizontalLayout BtnLayout;
      /**
     * The attachBtnLayout Layout.
     */
    @UiField("attachBtnLayout")
    private HorizontalLayout attachBtnLayout;

    @UiField("cssLayout")
    CssLayout cssLayout;
    @UiField("hLayout")
    HorizontalLayout hlayout;
    /**
     * The item pricing qualifier name.
     */
    @UiField("itemPricingQualifierNameLB")
    private Label itemPricingQualifierNameLB;
    @UiField("itemPricingQualifierNameDDLB")
    private ComboBox itemPricingQualifierNameDDLB;
    /**
     * The item price.
     */
    @UiField("itemPriceLB")
    private Label itemPriceLB;
    @UiField("itemPrice")
    private TextField itemPrice;
    /**
     * The pricing code status.
     */
    @UiField("pricingCodeStatusLB")
    private Label pricingCodeStatusLB;
    @UiField("pricingCodeStatus")
    private ComboBox pricingCodeStatus;
    /**
     * The item uom.
     */
    @UiField("itemUomLB")
    private Label itemUomLB;
    @UiField("itemUom")
    private ComboBox itemUom;
    /**
     * The start date.
     */
    @UiField("startDateLB")
    private Label startDateLB;
    @UiField("startDate")
    private PopupDateField startDate;
    /**
     * The end date.
     */
    @UiField("endDateLB")
    private Label endDateLB;
    @UiField("endDate")
    private PopupDateField endDate;
    /**
     * The entity code.
     */
    @UiField("entityCodeLB")
    private Label entityCodeLB;
    @UiField("entityCode")
    private CustomTextField entityCode;
    /**
     * The entity code.
     */
    @UiField("entityCodeNameLB")
    private Label entityCodeNameLB;
    @UiField("entityCodeName")
    private CustomTextField entityCodeName;
    private final TextField entityCodeSid = new TextField();
    @UiField("btnPopulate")
    private Button btnPopulate;
    /**
     * The pricing table.
     */
    @UiField("pricingTable")
    private ExtFilterTable pricingTable;
    @UiField("tablePanel")
    private Panel tablePanel;
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("priceInfoLayout")
    private Panel priceInfoLayout;
    private final String mode;
    private final boolean isAddMode;
    private final boolean isEditMode;
    private final boolean isViewMode;

    /**
     * Popup Window.
     */
    private IdenQualifierEditList sub = new IdenQualifierEditList(true);
    @UiField("excelExport")
    private Button excelExport;
    ParentCompanyNo lookUp = null;
    /**
     *
     */
    private final HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    final ItemPricingDTO identForm = new ItemPricingDTO();
    final List removedItemPriceList;

    /**
     * Gets the pricing table bean.
     *
     * @return the pricing table bean
     */
    public BeanItemContainer<ItemPricingDTO> getPricingTableBean() {
        return pricingTableBean;
    }
    /**
     * Purposely for Error Display
     */
    private ErrorfulFieldGroup systemBinder;
     /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();
     private static HelperListUtil helperListUtil=HelperListUtil.getInstance();
     
      SessionDTO sessionDTO;
    /**
     * The Constructor.
     * 
     * @param systemBinder
     * @param pricingTableBean
     * @param mode
     * @throws SystemException
     * @throws PortalException
     * @throws Exception 
     */
    public PricingResults(final ErrorfulFieldGroup systemBinder,
            final BeanItemContainer<ItemPricingDTO> pricingTableBean,final List<ItemPricingDTO> removedItemPriceList, String mode, SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();
        this.systemBinder = systemBinder;
        this.pricingTableBean = pricingTableBean;
        this.mode = mode;
        this.removedItemPriceList = removedItemPriceList;
        this.isAddMode = ConstantsUtils.ADD.equals(mode);
        this.isEditMode = ConstantsUtils.EDIT.equals(mode);
        this.isViewMode = ConstantsUtils.VIEW.equals(mode);
        this.sessionDTO=sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/itemmaster/pricing.xml"), this));
        init();
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void init() throws SystemException, PortalException, Exception {
        space.setHeight(ConstantsUtils.HEIGHT);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        binder.setItemDataSource(new BeanItem<ItemPricingDTO>(pricingFormBean));
        binder.bindMemberFields(this);

      
        
if( isEditMode || isViewMode)  {
              addToContent();
              configureFields();
              getBinder();

        } else {
            getBinder();
            addToContent();
            configureFields();
               

     
        }   
  if (isViewMode) {
            LOGGER.info("removing panel for view mode :");
            priceInfoLayout.setVisible(false);
            btnPopulate.setVisible(false);
            removeBtn.setVisible(false);
  }
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldItemHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_MASTER+ConstantsUtils.COMMA+"Pricing",false);
         final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId,UISecurityUtil.ITEM_MASTER);
        addResponsiveGrid(fieldItemHM,functionHM);
        
    }

    /**
     * Adds the to content.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void addToContent() throws SystemException, PortalException, Exception {

        
        addToTable();
        addResponsiveVerticalTabThreeLayout();
    }

    private void addResponsiveGrid(final Map<String, AppPermission> fieldCompanyHM,final Map<String, AppPermission> functionHM) {
          LOGGER.info("Entering getFirstTab1");
        try {
            
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_MASTER,"Pricing");
           commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldCompanyHM, mode);
            if (functionHM.get(FieldNameUtils.ATTACH_PRICING) != null && ((AppPermission) functionHM.get(FieldNameUtils.ATTACH_PRICING)).isFunctionFlag()) {
                populateButton();
            }else{
            attachBtnLayout.removeComponent(btnPopulate);
        
            }
            if (functionHM.get(FieldNameUtils.REMOVE_PRICING) != null && ((AppPermission) functionHM.get(FieldNameUtils.REMOVE_PRICING)).isFunctionFlag()) {
                removeButton();
            }else{
            BtnLayout.removeComponent(removeBtn);
            }


        }catch(Exception ex){
            LOGGER.error(ex);
        }

    }

    /**
     * Adds the to table.
     *
     * @return the table
     * @throws Exception the exception
     */
    public ExtFilterTable addToTable() throws Exception {

        final StplSecurity stplSecurity = new StplSecurity();
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_MASTER+ConstantsUtils.COMMA+"Pricing Header",false);
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_MASTER, "Pricing Header");
        Object[] obj = UIUtils.PRICING_FORM_COL_ORDER;
        LOGGER.info("mode--------------------"+mode);
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
        pricingTable.markAsDirty();
        List<ItemPricingDTO> dtos = pricingTableBean.getItemIds();
        for (ItemPricingDTO dto : dtos) {
            if (dto.getIdentifierCodeQualifierName() != null) {
                dto.setItemPricingQualifierName(dto
                        .getIdentifierCodeQualifierName());
            }

            if (dto.getStartDate() != null) {
                dto.setPricingStartDate(dto
                        .getStartDate());
            }
            if (dto.getEndDate() != null) {
                dto.setPricingEndDate(dto.getEndDate());
            }

        }
        if (tableResultCustom.getObjResult().length > 0) {
            pricingTable.setContainerDataSource(pricingTableBean);
            pricingTable.setVisibleColumns(tableResultCustom.getObjResult());
            pricingTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            pricingTable.setPageLength(3);
        } else {
            tablePanel.setVisible(false);
            BtnLayout.setVisible(false);
        }
        pricingTable.setImmediate(true);
        pricingTable.setSizeFull();
        pricingTable.setComponentError(null);
        pricingTable.addStyleName(ConstantsUtils.FILTER_BAR);
        if (!isViewMode) {
            pricingTable.setEditable(true);
            pricingTable.setFilterBarVisible(true);
            pricingTable.setTableFieldFactory(new ItemPricingTableGenerator());
            pricingTable.setSelectable(true);
        }
        pricingTable.setFilterDecorator(new ExtDemoFilterDecorator());
        pricingTable.setValidationVisible(false);
        pricingTable.setFilterGenerator(new IdentifierPricingGenerater());
        pricingTable.setErrorHandler(new ErrorHandler() {
            /**
             * Error event handling
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                /**
                 * Empty error event
                 */
            }
        });

        pricingTable.addItemClickListener(new ItemClickListener() {
            /**
             * Item click event
             */
            public void itemClick(final ItemClickEvent event) {
                /**
                 * Empty item click event
                 */
            }
        });

        return pricingTable;
    }

    /**
     * Populate button.
     *
     * @return the button
     * @throws Exception the exception
     */
    public void populateButton() throws Exception {

        btnPopulate.setImmediate(true);
        btnPopulate.setWidth("85");
        btnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Error event handling.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                /**
                 * Empty error event
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
                LOGGER.info("Entering PricingResult Attach operation");
                try {
                    binder.getFields();
                    binder.commit();
                    final ItemPricingDTO identForm = new ItemPricingDTO();
                    final String qualifier = String.valueOf(binder.getField(ConstantsUtils.ITEM_PRICING_QUALIFIER_NAME).getValue());
                    String itemPrice = String.valueOf(binder.getField(ConstantsUtils.ITEM_PRICE).getValue());
                     String entityCode = String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE).getValue());
                    final String strPrice;
                    final String source = "GTN";
                    
                    final DecimalFormat decimalFormat = new DecimalFormat("###,###,###.00");

                    boolean flag = false;
                    binder.getErrorDisplay().clearError();
                    systemBinder.getErrorDisplay().clearError();
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                    if (StringUtils.isEmpty(qualifier) || ConstantsUtils.SELECT_ONE.equals(qualifier) || ConstantsUtils.NULL.equals(qualifier)) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Pricing Qualifier Name");
                        flag = true;
                    } else {
                        identForm.setIdentifierCodeQualifierName(qualifier);
                        HelperDTO helperDTO = (HelperDTO) itemPricingQualifierNameDDLB.getValue();
                        int qualifierId = helperDTO.getId();
                        identForm.setItemPricingQualifierId(qualifierId);
                    }
                    if (!StringUtil.isBlank(itemPrice)) {
                        try {
                            if (itemPrice.contains(ConstantsUtils.DOLLAR) || itemPrice.contains(ConstantsUtils.COMMA)) {
                                itemPrice = itemPrice.replace(ConstantsUtils.DOLLAR, StringUtils.EMPTY);
                                itemPrice = itemPrice.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
                            }
                            if (Double.isNaN(Double.valueOf(itemPrice))) {
                                systemBinder.getErrorDisplay().setError("Item Price should be numeric.");
                                return;
                            }
                        } catch (Exception ex) {
                            LOGGER.error("Exception in populateButton - buttonClick - itemPrice -" + ex);
                            systemBinder.getErrorDisplay().setError("Item Price should be numeric.");
                            return;
                        }
                    }

                    if (StringUtil.isBlank(itemPrice) || Double.valueOf(itemPrice) == 0) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Item Price");
                        flag = true;
                    }
                     if (binder.getField(ConstantsUtils.PRICING_CODE_STATUS).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.PRICING_CODE_STATUS).getValue()).getId() == 0) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Pricing Status");
                        flag = true;
                    } else {
                        identForm.setPricingCodeStatus((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.PRICING_CODE_STATUS).getValue());
                    }
                    if (binder.getField(ConstantsUtils.START_DATE).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Start Date");
                        flag = true;
                    }
                    if (!StringUtil.isBlank(itemPrice)) {
                        if (qualifier.equals("AMP") || qualifier.equals("BP")) {
                            final DecimalFormat decfor = new DecimalFormat("###,###,###.0000");
                            strPrice = ConstantsUtils.DOLLAR + decfor.format(Double.valueOf(itemPrice));
                            identForm.setItemPrice(strPrice);
                        } else {
                            strPrice = ConstantsUtils.DOLLAR + decimalFormat.format(Double.valueOf(itemPrice));
                            identForm.setItemPrice(strPrice);
                        }
                    }
                    identForm.setEntityCode(String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE).getValue()));
                    identForm.setEntityCodeSid(String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE_SID).getValue()));
                    if (binder.getField(ConstantsUtils.ITEM_UOM).getValue() == null || ((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.ITEM_UOM).getValue()).getId() == 0) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Item Uom");
                        flag = true;
                    } else {
                        identForm.setItemUom((com.stpl.ifs.util.HelperDTO) binder.getField(ConstantsUtils.ITEM_UOM).getValue());
                    }

                    if (flag) {
                        systemBinder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }
                    if (startDate.getValue() != null && endDate.getValue() != null && startDate.getValue().after(endDate.getValue())) {
                        systemBinder.getErrorDisplay().setError("End date should be greater than Start date");
                        return;
                    } else if (startDate.getValue() != null && endDate.getValue() != null && startDate.getValue().getTime() == endDate.getValue().getTime()) {
                        systemBinder.getErrorDisplay().setError("Start date and End date should not be  equal");
                        return;
                    }
                    if (binder.getField(ConstantsUtils.START_DATE).getValue() != null) {
                        identForm.setStartDate((Date) binder.getField(ConstantsUtils.START_DATE).getValue());
                    }
                    if (binder.getField(ConstantsUtils.END_DATE).getValue() != null) {
                        identForm.setEndDate((Date) binder.getField(ConstantsUtils.END_DATE).getValue());
                    }
                    String createdBy = StplSecurity.userMap.get(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                    identForm.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);
                    identForm.setCreatedDate(new Date());
                    identForm.setSource(source);
                    if (identForm.getIdentifierCodeQualifierName() != null) {
                        identForm.setItemPricingQualifierName(identForm
                                .getIdentifierCodeQualifierName());
                    }
                    if (identForm.getStartDate() != null) {
                        identForm.setPricingStartDate(identForm
                                .getStartDate());
                    }
                    if (identForm.getEndDate() != null) {
                        identForm.setPricingEndDate(identForm.getEndDate());
                    }
                    String query = StringUtils.EMPTY;
                    if (isEditMode) {
                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                        query = "select COUNT(ITEM_PRICING_SID) from ITEM_PRICING where ITEM_MASTER_SID = " + sessionDTO.getSystemId() + " and ITEM_PRICING_QUALIFIER_SID = " + identForm.getItemPricingQualifierId() + " "
                                + "and ITEM_UOM = " + pricingFormBean.getItemUom().getId() + " and PRICING_CODE_STATUS = " + pricingFormBean.getPricingCodeStatus().getId() + " and START_DATE = '" + format1.format(startDate.getValue()) + "' and \"SOURCE\" = 'GTN'";
                        List<Object> list = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
                        if (!list.isEmpty()) {
                            Object ob = list.get(0);
                            int i = Integer.valueOf(String.valueOf(ob));
                            if (i != 0) {
                                systemBinder.getErrorDisplay().setError("Item Pricing combination already exists for this Item");
                                return;
                            }
                        }
                        if (!validateBean(tempPricing, identForm)) {
                            systemBinder.getErrorDisplay().setError("Item Pricing combination already exists for this Item");
                            return;
                        }
                        tempPricing.addBean(identForm);
                    } else {

                        if (!validateBean(pricingTableBean, identForm)) {
                            systemBinder.getErrorDisplay().setError("Item Pricing combination already exists for this Item");
                            return;
                        }
                    }
                   
                    pricingTableBean.addBean(identForm);
                    binder.setItemDataSource(new BeanItem<>(new ItemPricingDTO()));
                    itemPricingQualifierNameDDLB.select(dto);
                    resetBtnLogic();
                } catch (CommitException e) {
                    boolean flag = false;
                    final String qualifier = String.valueOf(binder.getField(ConstantsUtils.ITEM_PRICING_QUALIFIER_NAME).getValue());
                    String itemPrice = String.valueOf(binder.getField(ConstantsUtils.ITEM_PRICE).getValue());
                    String strPrice = StringUtils.EMPTY;
                    final DecimalFormat decimalFormat = new DecimalFormat("###,###,###.00");
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                    if (e.getCause().getMessage().equals("Date format not recognized")) {
                        return;
                    }
                    if (StringUtils.isEmpty(qualifier) || ConstantsUtils.SELECT_ONE.equals(qualifier) || ConstantsUtils.NULL.equals(qualifier)) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Pricing Qualifier Name");
                        flag = true;
                    } else {
                        identForm.setIdentifierCodeQualifierName(qualifier);
                    }
                    if (!StringUtil.isBlank(itemPrice)) {
                        try {
                            if (e.getCause().getMessage().equals("Could not convert value to Double")) {
                                binder.getErrorDisplay().clearError();
                                systemBinder.getErrorDisplay().setError("Item Price should be numeric.");
                                return;
                            }
                        } catch (Exception ex) {
                            LOGGER.error("Exception in populateButton - buttonClick - itemPrice -" + ex);
                            systemBinder.getErrorDisplay().setError("Item Price should be numeric.");
                            return;
                        }
                    }
                    if (e.getCause().getMessage().equals("Please enter valid value.")) {
                        binder.getErrorDisplay().clearError();
                        systemBinder.getErrorDisplay().setError("Please enter valid value.");
                        return;
                    }
                    if (itemPrice.contains(ConstantsUtils.DOLLAR) || itemPrice.contains(ConstantsUtils.COMMA)) {
                        itemPrice = itemPrice.replace(ConstantsUtils.DOLLAR, StringUtils.EMPTY);
                        itemPrice = itemPrice.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
                    }

                    if (StringUtil.isBlank(itemPrice) || Double.valueOf(itemPrice) == 0) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Item Price");
                        flag = true;
                    }
                  if(binder.getField(ConstantsUtils.PRICING_CODE_STATUS).getValue()==null || ((com.stpl.ifs.util.HelperDTO)binder.getField(ConstantsUtils.PRICING_CODE_STATUS).getValue()).getId()==0){ 
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Pricing Status");
                        flag = true;
                    } else {
                          identForm.setPricingCodeStatus( (com.stpl.ifs.util.HelperDTO)binder.getField(ConstantsUtils.PRICING_CODE_STATUS).getValue());  
                    }

                    if (binder.getField(ConstantsUtils.START_DATE).getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Start Date");
                        flag = true;
                    }
                    if (qualifier.equals("AMP") || qualifier.equals("BP")) {
                        final DecimalFormat decfor = new DecimalFormat("###,###,###.0000");
                        strPrice = ConstantsUtils.DOLLAR + decfor.format(Double.valueOf(itemPrice));
                        identForm.setItemPrice(strPrice);
                    } else {
                        strPrice = ConstantsUtils.DOLLAR + decimalFormat.format(Double.valueOf(itemPrice));
                        identForm.setItemPrice(strPrice);
                    }
                    identForm.setEntityCode(String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE).getValue()));
                   if(binder.getField(ConstantsUtils.ITEM_UOM).getValue()==null || ((com.stpl.ifs.util.HelperDTO)binder.getField(ConstantsUtils.ITEM_UOM).getValue()).getId()==0){ 
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Item Uom");
                        flag = true;
                    } else {
                      identForm.setItemUom(  (com.stpl.ifs.util.HelperDTO)binder.getField(ConstantsUtils.ITEM_UOM).getValue());  
                    }

                    if (flag) {
                        systemBinder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(ex);
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
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {   
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
                    LOGGER.error(exception);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {   
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
                LOGGER.info("Ending PricingResult Attach operation");
            }
        });
    }

    /**
     * Convert date.
     *
     * @param startDate the start date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertDate(final Date startDate) throws java.text.ParseException {

        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

        return inputFormat.parse(inputFormat.format(startDate));

    }

    /**
     * Removes the button.
     *
     * @return the button
     */
    public void removeButton() {

        removeBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        removeBtn.setImmediate(true);
        removeBtn.addClickListener(new ClickListener() {
            /**
             * After clicking the remove button, function will be executed.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering PricingResult Remove operation");
                try {
                    systemBinder.getErrorDisplay().clearError();
                    binder.getErrorDisplay().clearError();
                    final Object itemId = pricingTable.getValue();
                    if (itemId == null) {
                        systemBinder.getErrorDisplay().setError("Please select a price from the list view to remove.");
                    } else {
                        MessageBox.showPlain(Icon.QUESTION, "Remove Confirmation", " Are you sure you want to Remove the Price?", new MessageBoxListener() {
                            /**
                             * After clicking button, function will be executed.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    removedItemPriceList.add(pricingTableBean.getItem(itemId));
                                    pricingTableBean.removeItem(itemId);
                                    pricingTable.unselect(itemId);
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);

                    }
                    pricingTable.setValue(null);
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1013), new MessageBoxListener() {   
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
                LOGGER.info("Ending PricingResult Remove operation");
            }
        });

    }

    public void setDefaultFocus() {
        itemPricingQualifierNameDDLB.focus();
    }
    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void configureFields() throws SystemException, Exception {
       
        
        startDate.setDescription(ConstantsUtils.DATE_DES);
        endDate.setDescription(ConstantsUtils.DATE_DES);

        itemPricingQualifierNameDDLB.setImmediate(true);
        final LazyContainer tempContainer = new LazyContainer(HelperDTO.class, new PriceTypeContainer(true), new PriceTypeCriteria());
        tempContainer.setMinFilterLength(0);
        itemPricingQualifierNameDDLB.setPageLength(7);

        itemPricingQualifierNameDDLB.setContainerDataSource(tempContainer);
        itemPricingQualifierNameDDLB.setInputPrompt(ConstantsUtils.SELECT_ONE);
        itemPricingQualifierNameDDLB.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        itemPricingQualifierNameDDLB.markAsDirty();
        itemPricingQualifierNameDDLB.setNullSelectionAllowed(true);
        itemPricingQualifierNameDDLB.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        itemPricingQualifierNameDDLB.select(dto);
        itemPricingQualifierNameDDLB.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in itemPricingQualifierName, function
             * will be executed.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {

                final String value = String.valueOf(itemPricingQualifierNameDDLB.getValue());
                if (value.equals(ConstantsUtils.EDITLIST)) {
                    UI.getCurrent().addWindow(sub);
                }
            }
        });

        sub.addCloseListener(new Window.CloseListener() {
            /**
             * window listener
             */
            public void windowClose(final CloseEvent event) {
                try {
                    final LazyContainer tempContainer = new LazyContainer(HelperDTO.class, new PriceTypeContainer(true), new PriceTypeCriteria());
                    tempContainer.setMinFilterLength(0);
                    itemPricingQualifierNameDDLB.setContainerDataSource(tempContainer);
                    itemPricingQualifierNameDDLB.markAsDirty();
                    itemPricingQualifierNameDDLB.select(dto);

                } catch (Exception exception) {
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
            }
        });
        itemPricingQualifierNameDDLB.setDescription(itemPricingQualifierNameDDLB.getValue().toString());
        itemPricingQualifierNameDDLB.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in itemPricingQualifierName, function
             * will be executed.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {

                if (itemPricingQualifierNameDDLB.getValue() != null) {
                    itemPricingQualifierNameDDLB.setDescription(itemPricingQualifierNameDDLB.getValue().toString());
                }

            }
        });

        itemPrice.setImmediate(true);
        itemPrice.setNullRepresentation(StringUtils.EMPTY);
        itemPrice.setValidationVisible(true);
        /*
         * itemPrice.addValidator(new RegexpValidator(ValidationUtils.Price,
         * ValidationUtils.price_Message));
         */
        itemPrice.setDescription((String) itemPrice.getValue());
        itemPrice.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in itemPrice, function will be executed.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {

                itemPrice.setDescription((String) itemPrice.getValue());

            }
        });

          commonUtil.loadComboBox(pricingCodeStatus, UIUtils.STATUS, false);

        entityCodeName.setReadOnly(true);
        entityCodeName.setImmediate(true);
        entityCodeSid.setVisible(false);
        entityCode.setImmediate(true);
        entityCode.setReadOnly(true);
        entityCode.setStyleName(ConstantsUtils.SEARCH_SYLENAME);
        entityCode.setDescription((String) entityCode.getValue());
        entityCode.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value in entityCode, function will be
             * executed.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {

                entityCode.setDescription((String) entityCode.getValue());

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
                    if(lookUp == null){
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
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

        startDate.setValidationVisible(true);
        startDate.setImmediate(true);
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value, function will be executed.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                    startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
            }
        });
        startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);

        endDate.setImmediate(true);
        endDate.setValidationVisible(true);
        endDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * After changing the value, function will be executed.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                    endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
            }
        });
     

       commonUtil.loadComboBox(itemUom, UIUtils.UOM, false);
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
                    LOGGER.info("Entering EXCEL Export Button Click :::: ");
                    binder.getFields();
                    excelExportLogic();
                    LOGGER.info(" Ends  EXCEL Export Button Click ::::  ");

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

    protected void excelExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.info("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheet");
        final long recordCount = pricingTable.getContainerDataSource().size();
        ExcelExportforBB.createWorkSheet(pricingTable.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.PRICING);
        LOGGER.info("Ending createWorkSheet");
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
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        ItemPricingDTO dto;
        final List<OrderByColumn> columns = new ArrayList<OrderByColumn>();
        final List<ItemPricingDTO> searchList = pricingTableBean.getItemIds();
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT);
        String date;
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);

            printWriter.print(ConstantsUtils.QUOTE + dto.getIdentifierCodeQualifierName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getItemPrice() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            String status = dto.getPricingCodeStatus().getId() != 0 ? dto.getPricingCodeStatus().getDescription() : StringUtils.EMPTY;
            printWriter.print(ConstantsUtils.QUOTE + status + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            String uom = dto.getItemUom().getId() != 0 ? dto.getItemUom().getDescription() : StringUtils.EMPTY;

            printWriter.print(ConstantsUtils.QUOTE + uom + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

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

            printWriter.print(ConstantsUtils.QUOTE + dto.getSource() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getModifiedBy() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            if (StringUtils.EMPTY.equals(dto.getModifiedDate()) || dto.getModifiedDate() == null) {

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
        itemPricingQualifierNameDDLB.setValue(dto);
        pricingCodeStatus.setValue(0);
        itemPrice.setValue(StringUtils.EMPTY);
        entityCode.setReadOnly(false);
        entityCode.setValue(StringUtils.EMPTY);
        entityCodeName.setReadOnly(false);
        entityCodeName.setValue(StringUtils.EMPTY);
        entityCodeName.setReadOnly(true);
        entityCodeSid.setValue(StringUtils.EMPTY);
        entityCode.setReadOnly(true);
        startDate.setValue(null);
        endDate.setValue(null);
        itemUom.setValue(0);
    }

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings(ConstantsUtils.RAWTYPES)
    private class DateValidator extends AbstractValidator {

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validating the value.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) throws InvalidValueException {
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
         * @return true, if is valid value
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


    private void setDefaultResolution(ExtFilterTable table) {
        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
        if (browserWidth > 1516) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < 1516 && browserWidth > 1300) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < 1300 && browserWidth > 1024) {
            getCollapsibleColumnsDefault(table);
        } else if (browserWidth < 1024 && browserWidth > 978) {
            getCollapsibleColumns978Px(table);
        } else if (browserWidth < 978 && browserWidth > 800) {
            if (table.getItemIds().isEmpty()) {
                getCollapsibleColumns978Px(table);
            } else {
                getCollapsibleColumnsTwoData(table);
            }
        } else if (browserWidth < 800 && browserWidth > 480) {
            if (table.getItemIds().isEmpty()) {
                getCollapsibleColumns978Px(table);
            } else {
                getCollapsibleColumnsTwoData(table);
            }
        } else if (browserWidth < 480 && browserWidth > 380) {
            getCollapsibleColumns480Px(table);
        } else if (browserWidth < 380) {
            if (defaultColumnsVisible(table)) {
                for (Object propertyId : getCollapsibleOneColumn(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        }

    }

    private static Object[] getCollapsibleColumns480Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumns978Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsTwoData(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static boolean defaultColumnsVisible(ExtFilterTable table) {
        boolean result = true;
        for (Object propertyId : getCollapsibleOneColumn(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    private static Object[] getCollapsibleOneColumn(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsDefault(ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
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

        reloadVerticalLayoutTabThreeMap.put(1516, true);
        reloadVerticalLayoutTabThreeMap.put(1300, true);
        reloadVerticalLayoutTabThreeMap.put(1024, true);
        reloadVerticalLayoutTabThreeMap.put(978, true);
        reloadVerticalLayoutTabThreeMap.put(800, true);
        reloadVerticalLayoutTabThreeMap.put(480, true);
        reloadVerticalLayoutTabThreeMap.put(320, true);
        reloadVerticalLayoutTabThreeMap.put(0, true);

        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(
                    final Page.BrowserWindowResizeEvent event) {

                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                if (browserWidth > 1516 && reloadVerticalLayoutTabThreeMap.get(1516)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, false);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(pricingTable);
                } else if (browserWidth < 1516 && browserWidth > 1300 && reloadVerticalLayoutTabThreeMap.get(1300)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, false);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(pricingTable);
                } else if (browserWidth < 1300 && browserWidth > 1024 && reloadVerticalLayoutTabThreeMap.get(1024)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, false);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < 1024 && browserWidth > 978 && reloadVerticalLayoutTabThreeMap.get(978)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, false);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < 978 && browserWidth > 800 && reloadVerticalLayoutTabThreeMap.get(800)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, false);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    if (pricingTable.getItemIds().isEmpty()) {
                    } else {
                    }
                } else if (browserWidth <= 800 && browserWidth > 480 && reloadVerticalLayoutTabThreeMap.get(480)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, false);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < 480 && browserWidth > 320 && reloadVerticalLayoutTabThreeMap.get(320)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, false);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                } else if (browserWidth < 320 && reloadVerticalLayoutTabThreeMap.get(0)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, false);
                }

            }
        });
    }
    private ErrorfulFieldGroup getBinder() throws Exception {

        binder.bindMemberFields(this);
        return binder;
    }
    
    private boolean validateBean(BeanItemContainer<ItemPricingDTO> tempPricing,final ItemPricingDTO identForm) throws Exception {
        for (int i = 0; i < tempPricing.size(); i++) {
            if (tempPricing.getIdByIndex(i).getItemUom() == (identForm.getItemUom())
                    && tempPricing.getIdByIndex(i).getStartDate().equals(identForm.getStartDate())
                    && tempPricing.getIdByIndex(i).getPricingCodeStatus() == (identForm.getPricingCodeStatus())
                    && tempPricing.getIdByIndex(i).getItemPricingQualifierId() == (identForm.getItemPricingQualifierId())) {
                final ItemPricingQualifier qual = itemLogic.getItemPricingQualifierByCodeQualifierName(identForm.getIdentifierCodeQualifierName());
                final ItemPricingQualifier qualBean = itemLogic.getItemPricingQualifierByCodeQualifierName(tempPricing.getIdByIndex(i).getIdentifierCodeQualifierName());
                if (qual.getItemPricingQualifierSid() == qualBean.getItemPricingQualifierSid()) {
                    if (tempPricing.getIdByIndex(i).getPricingCodeStatus() == (identForm.getPricingCodeStatus())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public void clearFilters() {
        pricingTable.clearFilters();
    }

}
