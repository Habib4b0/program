/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.ifp.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.dto.IFPItemDTO;
import com.stpl.app.global.ifp.dto.TableGenerator;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.ifp.ui.lazyload.DetailsViewContainer;
import com.stpl.app.global.ifp.ui.lazyload.TempItemContainer;
import com.stpl.app.global.ifp.ui.lazyload.TempItemCriteria;
import com.stpl.app.global.ifp.util.FieldNameUtils;
import com.stpl.app.global.ifp.util.IfpUtils;
import com.stpl.app.global.ifp.util.UIUtils;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.ui.IFPFilterGenerator;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.ResponsiveUtils;

import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author sooriya.lakshmanan
 */
public class IFPItems extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(IFPItems.class);
    
    final ErrorfulFieldGroup binder;
    /**
     * the used id
     */
    private String userId;
    /**
     * the session id
     */
    private String sessionId;
    /**
     * the temp table records created date
     */
    private String tempCreatedDate;
    /**
     * The Mode
     */
    private String mode;
    /**
     * The mass check.
     */
    @UiField("hLayout")
    private VerticalLayout hLayout;
    
    @UiField("hLayout2")
    private HorizontalLayout hLayout2;
    
    @UiField("viewLayout")
    private VerticalLayout viewLayout;
   
    @UiField("resultPanel")
    private Panel resultPanel;

    @UiField("massCheck")
    private OptionGroup massCheck;
    /**
     * The mass field.
     */
    @UiField("massField")
    private ComboBox massField;
    /**
     * The mass value.
     */
    @UiField("massValue")
    private ComboBox massValue;
    /**
     * The mass date.
     */
    @UiField("massDate")
    private PopupDateField massDate;
    /**
     * The btn populate.
     */
    @UiField("btnPopulate")
    private Button btnPopulate;
    /**
     * The btn all populate.
     */
    @UiField("btnAllPopulate")
    private Button btnAllPopulate;
    /**
     * this is a label for the ddlb values
     */
    @UiField("valueforddlb")
    private Label valueforddlb;    

    @UiField("pageCreater")
    HorizontalLayout pageCreater;
    
    @UiField("cssLayout")
    CssLayout cssLayout;

    /**
     * The company details table.
     */
    @UiField("itemDetailsTable")
    private CustomePagedFilterTable itemDetailsTable;
    
    @UiField("viewResultsTable")
    private CustomePagedFilterTable viewResultsTable;
    
    @UiField("excel")
    private Button excel;
    private LazyBeanItemContainer itemDetlsLzyContnr;
    /**
     * Record checkbox
     */
    @UiField("record")
    private OptionGroup record;
    /**
     * itemContainer
     */
    TempItemContainer itemContainer ;
    /**
     * viewItemContainer
     */
    DetailsViewContainer viewItemContainer ;

    /**
     * The item details results bean.
     */
    private BeanItemContainer<IFPItemDTO> itemDetailsResultsBean;

    

    /**
     * The ifp item list.
     */
    private final List<IFPItemDTO> ifpItemList = new ArrayList<IFPItemDTO>();

    /**
     * The field mass.
     */
    private String fieldMass = StringUtils.EMPTY;

    /**
     * The ifp logic.
     */
    private final IFPLogic ifpLogic;

    TempItemCriteria tempItemCriteria = new TempItemCriteria();

    private BeanItemContainer<IFPItemDTO> saveContainer = new BeanItemContainer<IFPItemDTO>(IFPItemDTO.class);

    private final Map<Integer, Boolean> reloadMap = new HashMap<Integer, Boolean>();
CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    
    private CommonUtil commonUtil = CommonUtil.getInstance();
    SessionDTO sessionDTO;
    /**
     * The map.
     */
    private final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    private final BeanItemContainer<IFPItemDTO> emptyCont = new BeanItemContainer<IFPItemDTO>(IFPItemDTO.class);
    
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png"); 
    
    public IFPItems(final ErrorfulFieldGroup binder, final SessionDTO sessionDTO)  {
        this.binder = binder;
        this.sessionDTO = sessionDTO;
        ifpLogic = new IFPLogic(this.sessionDTO);
       
        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/item_family_plan/items.xml"), this));
            binder.bindMemberFields(this);
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            tempCreatedDate = String.valueOf(this.sessionDTO.getSessionDate());
            mode = String.valueOf(this.sessionDTO.getMode());

            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEMS,false);
            final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEMS);
            init();
            configurePermission(fieldIfpHM,functionHM);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    
    private void configurePermission(final Map<String, AppPermission> fieldIfpHM , final Map<String, AppPermission> functionHM) {
        LOGGER.debug("Entering configurePermission");
        try {
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN,ConstantsUtils.ITEMS);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldIfpHM, ConstantsUtils.VIEW_BTN.equals(mode)?"view":mode);
        if (functionHM.get(FieldNameUtils.POPULATE) != null && !((AppPermission) functionHM.get(FieldNameUtils.POPULATE)).isFunctionFlag()) {
            btnPopulate.setVisible(false);
        }
        
        if (functionHM.get(FieldNameUtils.POPULATE_ALL) != null && !((AppPermission) functionHM.get(FieldNameUtils.POPULATE_ALL)).isFunctionFlag()) {
            btnAllPopulate.setVisible(false);
        }
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

    private void init() throws SystemException {
        
        record.addItems("Current", "History", "Future");
        record.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String value = String.valueOf(record.getValue());
                if (mode.equals(ConstantsUtils.VIEW_BTN)) {
                    viewItemContainer.setRecord(value);
                    viewResultsTable.setCurrentPage(1);
                } else {
                    itemContainer.setRecord(value);
                    itemDetailsTable.setCurrentPage(1);
                }
            }
        });
        
        excel.setIcon(excelExportImage);
        excel.setDescription("Export to excel");
        excel.setIconAlternateText("Excel export");
        excel.setHtmlContentAllowed(true);
        
        if (mode.equals(ConstantsUtils.VIEW_BTN)) {

            hLayout.setVisible(false);
                    viewLayout.addComponent(hLayout2, 0);
                    viewLayout.addComponent(pageCreater,2);
            addResultsTable();

        } else {
            if(mode.equals("Add")){
                record.setReadOnly(true);
            }
            viewLayout.setVisible(false);
            addItemDetailsTable();
            configureFields();
            addBtnPopulate();
            addAllPopulate();
        }
    }

    /**
     * Adds the btn populate.
     *
     * @return the button
     */
    public void addBtnPopulate() {

        btnPopulate.setReadOnly(true);
        btnPopulate.setErrorHandler(new ErrorHandler() {

            /**
             * Method used to populate button logic and its listener.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // parses the error.
            }
        });

        btnPopulate.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering inside IFPTabsheetForm POPULATE  method ");
                binder.getErrorDisplay().clearError();
                try {
                    boolean massUpdate;
                    if (massField.getValue() == null) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select Field", new MessageBoxListener() {
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
                        massUpdate = false;
                    } else {
                        final String value = String.valueOf(massField.getValue());
                        if (FieldNameUtils.IFPSTATUS.equals(value) && ((com.stpl.ifs.util.HelperDTO) massValue.getValue()).getId() != 0) {
                            massUpdate = true;
                        } else if ((ConstantsUtils.IFP_STARTDATE.equals(value) || ConstantsUtils.IFP_ENDDATE.equals(value)) && massDate.getValue() != null) {
                            massUpdate = true;
                        } else {
                            massUpdate = false;
                        }
                        if (FieldNameUtils.IFPSTATUS.equals(value) && ((com.stpl.ifs.util.HelperDTO)massValue.getValue()).getId() == 0) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select Status", new MessageBoxListener() {
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
                        if (ConstantsUtils.IFP_STARTDATE.equals(value) && massDate.getValue() == null) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select Start Date", new MessageBoxListener() {
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
                        if (ConstantsUtils.IFP_ENDDATE.equals(value) && massDate.getValue() == null) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select End Date", new MessageBoxListener() {
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

                    if (massUpdate) {
                        LOGGER.debug("Entering Mass update operation");
                        final String populateField;
                        final String populateValue;
                        final SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
                        fieldMass = map.get(massField.getValue()).toString();
                        if (fieldMass.equals(ConstantsUtils.IFP_START_DATE)) {
                            populateField = "IFP_DETAILS_START_DATE";
                            populateValue = fmt.format(massDate.getValue());
                        } else if (fieldMass.equals(ConstantsUtils.IFP_END_DATE)) {
                            populateField = "IFP_DETAILS_END_DATE";
                            populateValue = fmt.format(massDate.getValue());

                        } else if (fieldMass.equals(ConstantsUtils.IFP_STATUS)) {
                            populateField = "IFP_DETAILS_ATTACHED_STATUS";
                            populateValue = String.valueOf(CommonUtils.getHelperTableSId(String.valueOf(massValue.getValue()), UIUtils.STATUS));
                        } else {
                            populateField = StringUtils.EMPTY;
                            populateValue = StringUtils.EMPTY;
                        }

                        saveRecordsInTempTable();
                        userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                        sessionId = String.valueOf(sessionDTO.getUiSessionId());
                        tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
                        Object list = ImtdIfpDetailsLocalServiceUtil.validateTempIFPDeatils(userId, sessionId, tempCreatedDate, "checkRecord", null, null, null, null);

                        if (((Integer) list) == ConstantsUtils.ZERO_INT) {

                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select a row to populate", new MessageBoxListener() {
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
                            
                            return;
                        }
                        if (massField.getValue() != null && ((massValue.getValue()!=null && ((com.stpl.ifs.util.HelperDTO)massValue.getValue()).getId() != 0) || massDate.getValue() != null)) {
                            ifpLogic.populateLogic(populateField, populateValue, false);
                            itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                        }
                        LOGGER.debug("Ending Mass update operation");
                    }
                    LOGGER.debug("Ending IFPTabsheetForm POPULATE  method ");
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }catch(Exception ex){
                    LOGGER.error(ex);
                }
            }
        });
    }

    /**
     * Adds the all populate button.
     *
     * @return the button
     */
    public void addAllPopulate() {

        btnAllPopulate.setReadOnly(true);
        btnAllPopulate.setErrorHandler(new ErrorHandler() {

            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // parses the error.
            }
        });

        btnAllPopulate.addClickListener(new Button.ClickListener() {

            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug("Entering inside IFPTabsheetForm POPULATE_ALL  method ");
                    binder.getErrorDisplay().clearError();
                    boolean massUpdate;
                    if (massField.getValue() == null) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select Field", new MessageBoxListener() {
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
                        massUpdate = false;
                    } else {
                        final String value = String.valueOf(massField.getValue());
                        if (FieldNameUtils.IFPSTATUS.equals(value) && massValue.getValue() != null) {
                            massUpdate = true;
                        } else if ((ConstantsUtils.IFP_STARTDATE.equals(value) || ConstantsUtils.IFP_ENDDATE.equals(value)) && massDate.getValue() != null) {
                            massUpdate = true;
                        } else {
                            massUpdate = false;
                        }
                        if (FieldNameUtils.IFPSTATUS.equals(value) && ((com.stpl.ifs.util.HelperDTO)massValue.getValue()).getId() == 0) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select Status", new MessageBoxListener() {
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
                            return;
                        }
                        if (ConstantsUtils.IFP_STARTDATE.equals(value) && massDate.getValue() == null) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select Start Date", new MessageBoxListener() {
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
                            return;
                        }
                        if (ConstantsUtils.IFP_ENDDATE.equals(value) && massDate.getValue() == null) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select End Date", new MessageBoxListener() {
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
                            return;
                        }
                    }

                    if (massUpdate) {
                        LOGGER.debug("Entering Mass update operation");
                        final String populateField;
                        final String populateValue;
                        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        fieldMass = map.get(massField.getValue()).toString();

                        if (fieldMass.equals(ConstantsUtils.IFP_START_DATE)) {
                            populateField = "IFP_DETAILS_START_DATE";
                            populateValue = fmt.format(massDate.getValue());
                        } else if (fieldMass.equals(ConstantsUtils.IFP_END_DATE)) {
                            populateField = "IFP_DETAILS_END_DATE";
                            populateValue = fmt.format(massDate.getValue());

                        } else if (fieldMass.equals(ConstantsUtils.IFP_STATUS)) {
                            populateField = "IFP_DETAILS_ATTACHED_STATUS";
                            populateValue = String.valueOf(CommonUtils.getHelperTableSId(String.valueOf(massValue.getValue()), UIUtils.STATUS));
                        } else {
                            populateField = StringUtils.EMPTY;
                            populateValue = StringUtils.EMPTY;
                        }
                        List<IFPItemDTO> list = saveContainer.getItemIds();
                        for (IFPItemDTO ob : list) {
                            if (ob.getCheckbox()) {
                            }
                        }
                        IFPLogic.saveToTemp(saveContainer.getItemIds());
                        saveContainer.removeAllItems();
                        ifpLogic.populateLogic(populateField, populateValue, true);
                        itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                        LOGGER.debug("Ending Mass update operation");
                    }
                    LOGGER.debug("Ending IFPTabsheetForm POPULATE_ALL  method ");
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

   

    /**
     * Adds the item details table.
     *
     * @return the table
     */
    public void addItemDetailsTable() {
        try {
            LOGGER.debug("Entering addItemDetailsTable");
            itemDetailsTable.markAsDirty();
            itemDetailsTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
            itemDetailsTable.addStyleName("table-header-normal");
            itemDetailsTable.addStyleName(ConstantsUtils.FILTER_BAR);
            itemDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            itemContainer = new TempItemContainer(binder, itemDetailsTable, saveContainer,sessionDTO);
            itemDetlsLzyContnr = new LazyBeanItemContainer(IFPItemDTO.class, itemContainer, tempItemCriteria);
            
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEMS,false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN, ConstantsUtils.ITEMS);
            Object[] obj = IfpUtils.ITEM_DETAILS_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
            itemDetailsTable.setContainerDataSource(itemDetlsLzyContnr);
             if(tableResultCustom.getObjResult().length > 0){
            itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
             }else{
                 resultPanel.setVisible(false);
                 pageCreater.setVisible(false);
             }
            itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
            itemDetailsTable.setFilterBarVisible(true);
            itemDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            itemDetailsTable.setTableFieldFactory(new TableGenerator(saveContainer));
            itemDetailsTable.setEditable(true);
            itemDetailsTable.setPageLength(NumericConstants.SEVEN);
            itemDetailsTable.sinkItemPerPageWithPageLength(false);
            itemDetailsTable.setImmediate(true);
            itemDetailsTable.setSelectable(true);
            itemDetailsTable.setSizeFull();
            itemDetailsTable.setFilterGenerator(new IFPFilterGenerator());
            itemDetailsTable.setFilterFieldVisible(ConstantsUtils.CHECK_BOX, false);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            } catch (Exception ex) {
                   LOGGER.error(ex);
            }
            
            ResponsiveUtils.getResponsiveControls(itemDetailsTable.createControls(), pageCreater);
            pageCreater.addComponent(excel);
            itemDetailsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                
                /**
                 * Called when a item has been clicked.
                 */
                @SuppressWarnings("PMD")
                public void itemClick(final ItemClickEvent event) {
                    // empty method
                }
            });
            
            itemDetailsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                
                @Override
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    if (ConstantsUtils.CHECK_BOX.equals(event.getPropertyId().toString())) {
                        if (event.isChecked()) {
                            try {
                                for (IFPItemDTO dto : saveContainer.getItemIds()) {
                                    dto.setCheckbox(Boolean.TRUE);
                                }
                                ifpLogic.checkAllLogic(1);
                                saveContainer.removeAllItems();
                                // this setCurrentPage is used to refresh the lazy conatiner
                                itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                                itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, true);
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        } else {
                            try {
                                for (IFPItemDTO dto : saveContainer.getItemIds()) {
                                    dto.setCheckbox(Boolean.FALSE);
                                }
                                ifpLogic.checkAllLogic(0);
                                saveContainer.removeAllItems();
                                // this setCurrentPage is used to refresh the lazy conatiner
                                itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                                itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                }
            });
            
            itemDetailsTable.setErrorHandler(new ErrorHandler() {
                
                /**
                 * Invoked when an error occurs.
                 */
                public void error(final com.vaadin.server.ErrorEvent event) {
                    // empty method
                }
            });
            LOGGER.debug("Ending addItemDetailsTable");
    }
    /*
    For Refreshing TableData
    */
    void ItemsPerPageRefreshing() {
        itemDetailsTable.setItemsPerPage(NumericConstants.TEN);
        itemDetailsTable.setItemsPerPage(NumericConstants.SEVEN);
    }

    void configureInTabChange() {
        massCheck.focus();

        itemDetlsLzyContnr.removeAllItems();
        itemDetlsLzyContnr = new LazyBeanItemContainer(IFPItemDTO.class, new TempItemContainer(binder, itemDetailsTable, saveContainer, sessionDTO), tempItemCriteria);
        itemDetailsTable.addStyleName("table-header-normal");
        itemDetailsTable.setFilterBarVisible(true);
        itemDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        itemDetailsTable.setValidationVisible(false);
        itemDetailsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        itemDetailsTable.setFilterGenerator(new IFPFilterGenerator());
        itemDetailsTable.setFilterFieldVisible(ConstantsUtils.CHECK_BOX, false);
        saveContainer.removeAllItems();

    }

    private void configureFields() throws SystemException {
        massDate.setDescription(ConstantsUtils.DATE_DES);

        map.put(ConstantsUtils.IFP_STARTDATE, ConstantsUtils.IFP_START_DATE);
        map.put("IFP End Date", ConstantsUtils.IFP_END_DATE);
        map.put(FieldNameUtils.IFPSTATUS, ConstantsUtils.IFP_STATUS);

        massCheck.addItem(ConstantsUtils.ENABLE);
        massCheck.addItem(ConstantsUtils.DISABLE);
        massCheck.select(ConstantsUtils.DISABLE);
        massCheck.setImmediate(true);
        massCheck.setStyleName("horizontal");
        massCheck.setMultiSelect(false);
        massField.setEnabled(false);
        btnPopulate.setEnabled(false);
        btnAllPopulate.setEnabled(false);
        valueforddlb.setPrimaryStyleName("labelsize");
        massCheck.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {

                if (ConstantsUtils.DISABLE.equals(massCheck.getValue())) {

                    massField.setEnabled(false);
                    massValue.setValue(StringUtils.EMPTY);
                    massValue.setVisible(false);
                    massDate.setVisible(false);
                    btnPopulate.setEnabled(false);
                    btnPopulate.setReadOnly(true);
                    btnAllPopulate.setEnabled(false);

                    markAsDirty();

                } else if (ConstantsUtils.ENABLE.equals(massCheck.getValue())) {
                    valueforddlb.setEnabled(true);
                    massField.setEnabled(true);
                    btnPopulate.setEnabled(true);
                    btnAllPopulate.setEnabled(true);
                    markAsDirty();
                }

            }
        });

        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        massField.addItem(ConstantsUtils.SELECT_ONE);
        massField.addItem(ConstantsUtils.IFP_STARTDATE);
        massField.addItem("IFP End Date");
        massField.addItem(FieldNameUtils.IFPSTATUS);
        massField.select(ConstantsUtils.SELECT_ONE);
        massField.setDescription((String) massField.getValue());
        massField.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                massField.setDescription((String) massField.getValue());
            }
        });

        massField.setImmediate(true);
        massValue.setVisible(false);
        commonUtil.loadComboBox(massValue, UIUtils.STATUS, false);

        massDate.setImmediate(true);
        massDate.setVisible(false);
        massDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        massDate.setValidationVisible(true);
        massDate.setId("MassDate");
        massDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    massDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(massDate.getValue()));
                } catch (Exception ex) {
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
                }

            }
        });

        btnPopulate.setEnabled(false);
        btnAllPopulate.setEnabled(false);
        massField.setEnabled(false);
        btnAllPopulate.setImmediate(false);

        massField.addValueChangeListener(new Property.ValueChangeListener() {

            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                final String value = String.valueOf(massField.getValue());
                if (FieldNameUtils.IFPSTATUS.equals(value)) {
                    massValue.setVisible(true);
                    massValue.select(new HelperDTO( 0,ConstantsUtils.SELECT_ONE));
                    massDate.setVisible(false);
                    btnPopulate.setReadOnly(false);
                } else if (ConstantsUtils.IFP_STARTDATE.equals(value) || ConstantsUtils.IFP_ENDDATE.equals(value)) {
                    massValue.setVisible(false);
                    massDate.setVisible(true);
                    massDate.setValue(null);
                    btnPopulate.setReadOnly(false);

                } else {
                    massValue.setVisible(false);
                    massDate.setVisible(false);
                }

            }
        });

        /**
         * massCheck listener
         */
        massCheck.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(final Property.ValueChangeEvent event) {

                if (ConstantsUtils.DISABLE.equals(massCheck.getValue())) {
                    massField.setValue(null);
                    massField.setEnabled(false);
                    massValue.setValue(StringUtils.EMPTY);
                    massValue.setVisible(false);
                    massDate.setVisible(false);
                    btnPopulate.setEnabled(false);
                    btnPopulate.setReadOnly(true);
                    btnAllPopulate.setEnabled(false);
                    if (itemDetailsResultsBean != null) {
                        final List<IFPItemDTO> ifpItemDTOList = itemDetailsResultsBean.getItemIds();

                        for (int i = 0; i < ifpItemDTOList.size(); i++) {
                            final IFPItemDTO dto = ifpItemDTOList.get(i);

                            if (dto.getCheckbox()) {

                                dto.setCheckbox(false);
                            }

                            ifpItemList.add(dto);

                        }
                        if (!ifpItemList.isEmpty()) {
                            itemDetailsResultsBean.removeAllItems();
                            itemDetailsResultsBean.addAll(ifpItemList);

                        }
                    }
                    markAsDirty();

                } else if (ConstantsUtils.ENABLE.equals(massCheck.getValue())) {

                    massField.setEnabled(true);
                    btnPopulate.setEnabled(true);
                    btnAllPopulate.setEnabled(true);

                    markAsDirty();
                }
                massCheck.focus();

            }
        });
    }

    private static Object[] getSixColumns(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0; i < NumericConstants.SIX; i++) {
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

    private static Object[] getCollapsibleColumns480Px(CustomePagedFilterTable table) {
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

    private static String[] getCollapsibleColumns600Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumnsDefault1515Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static Object[] getCollapsibleColumns978Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    public void saveRecordsInTempTable() throws SystemException, PortalException {
        if (saveContainer.getItemIds().size() > 0) {
            IFPLogic.saveToTemp(saveContainer.getItemIds());
            saveContainer.removeAllItems();
        }
    }
    
    public void updateRecordsInTempTable() throws SystemException, PortalException {
        if (saveContainer.getItemIds().size() > 0) {
            IFPLogic.saveToTempEdit(saveContainer.getItemIds());
            saveContainer.removeAllItems();
        }
    }

    public void resetItemsTab() {
        try {
            massCheck.setValue(ConstantsUtils.DISABLE);
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEMS,false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN, ConstantsUtils.ITEMS);
            Object[] obj = IfpUtils.ITEM_DETAILS_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
            itemDetailsTable.setContainerDataSource(emptyCont);
                if(tableResultCustom.getObjResult().length > 0){
            itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                }else{
               resultPanel.setVisible(false);
                 pageCreater.setVisible(false);      
                }
        } catch (PortalException ex) {
                  LOGGER.error(ex);
        } catch (SystemException ex) {
                  LOGGER.error(ex);
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
    }

    public void addResponsiveSearchTableCollapse(final CustomePagedFilterTable table) {
        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
        reloadMap.put(0, true);

        try {

            table.setColumnCollapsingAllowed(true);

            Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
                @Override
                public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                    int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                    if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX && reloadMap.get(NumericConstants.ONE_FIVE_ONE_SIX)) {
                        tempItemCriteria.setCustomDirty(false);

                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getSixColumns(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > NumericConstants.ONE_FIVE_ONE_SIX);
                        }
                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, false);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                        tempItemCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                        tempItemCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.ONE_FIVE_ONE_SIX);
                        }

                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                        tempItemCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED && reloadMap.get(NumericConstants.SIX_HUNDRED)) {
                        tempItemCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }

                        if (itemDetlsLzyContnr != null && itemDetlsLzyContnr.getItemIds().isEmpty()) {
                            for (Object propertyId : getCollapsibleColumns978Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        } else {
                            for (Object propertyId : getCollapsibleColumns600Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        }

                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, false);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                        tempItemCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                        tempItemCriteria.setCustomDirty(false);       // --> Disables reloading the container
                        for (Object propertyId : table.getVisibleColumns()) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns600Px(table)) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                        tempItemCriteria.setCustomDirty(true);            // --> Enables reloading the container
                    } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadMap.get(NumericConstants.THREE_TWO_ZERO)) {
                        tempItemCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.FOUR_EIGHT_ZERO);
                        }
                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, false);
                        reloadMap.put(0, true);
                        tempItemCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadMap.get(0)) {
                        tempItemCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.THREE_TWO_ZERO);
                        }
                        reloadMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, false);
                        tempItemCriteria.setCustomDirty(true);
                    }

                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    public void loadItemDetailsTable() {
        try {
            itemDetlsLzyContnr = new LazyBeanItemContainer(IFPItemDTO.class, new TempItemContainer(binder, itemDetailsTable, saveContainer, sessionDTO), new TempItemCriteria());
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEMS,false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN, ConstantsUtils.ITEMS);
            Object[] obj = IfpUtils.ITEM_DETAILS_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
            itemDetailsTable.setContainerDataSource(itemDetlsLzyContnr);
                if(tableResultCustom.getObjResult().length > 0){
            itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                }else{
               resultPanel.setVisible(false);
                 pageCreater.setVisible(false);      
                }
        } catch (PortalException ex) {
          LOGGER.error(ex);
        } catch (SystemException ex) {
           LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    
    public void loadDetailsTable(){
        itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
    }

    public OptionGroup getMassCheck() {
        return massCheck;
    }

    private void addResultsTable() {

        try {
            viewResultsTable.markAsDirty();
            
            
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getBusinessFieldPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEMS);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN, ConstantsUtils.ITEMS);
            Object[] obj = IfpUtils.ITEM_DETAILS_COL_VIEW;            
            viewItemContainer = new DetailsViewContainer(binder, viewResultsTable,sessionDTO);
            itemDetlsLzyContnr = new LazyBeanItemContainer(IFPItemDTO.class, viewItemContainer, tempItemCriteria);            
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM,"view");
            viewResultsTable.setContainerDataSource(itemDetlsLzyContnr);  
            viewResultsTable.setVisibleColumns(tableResultCustom.getObjResult());
            viewResultsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            viewResultsTable.setPageLength(NumericConstants.ELEVEN);
            viewResultsTable.setSelectable(false);
            viewResultsTable.setSizeFull();
            viewResultsTable.setWidth("99%");
            viewResultsTable.setImmediate(true);
            ResponsiveUtils.getResponsiveControls(viewResultsTable.createControls(), pageCreater);
            pageCreater.addComponent(excel);
            viewResultsTable.setErrorHandler(new ErrorHandler() {
                
                /**
                 * Invoked when an error occurs.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    // empty method
        }
            });
        } catch (PortalException ex) {
            LOGGER.error(ex);
            java.util.logging.Logger.getLogger(IFPItems.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        }
    
    
        
    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excel")
    public void excelButtonLogic(Button.ClickEvent event) {
        try {
            excelExportLogic();
        } catch (Exception e) {
           LOGGER.error(e);
        }     
        
    }
    
    
    public void excelExportLogic() throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LOGGER.debug("Entering excelExportLogic");
        saveRecordsInTempTable();
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
        }
    
    private void createWorkSheet() throws PortalException, SystemException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        int recordCount =0;
        if(mode.equals(ConstantsUtils.VIEW_BTN))        {
            recordCount = ifpLogic.ifpViewCount(String.valueOf(record.getValue()),false);
            ExcelExportforBB.createWorkSheet(viewResultsTable.getColumnHeaders(), recordCount, this, getUI(), ConstantsUtils.ITEMS);
        }else{
            recordCount = ifpLogic.getResultTableCount(null);
            ExcelExportforBB.createWorkSheet(itemDetailsTable.getColumnHeaders(), recordCount, this, getUI(), ConstantsUtils.ITEMS);
        }
        LOGGER.debug("Ending createWorkSheet");
        }
    
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws PortalException, SystemException {
        LOGGER.debug("Entering createWorkSheetContent");
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT, Locale.getDefault());
        if (itemDetailsTable.size() > 0) {
            IFPItemDTO resultList;
            final List<OrderByColumn> columns = new ArrayList<OrderByColumn>();
            List<IFPItemDTO> items = null;
            if (mode.equals(ConstantsUtils.VIEW_BTN)) {
                items = ifpLogic.getViewTableResult(start, end, binder, columns,String.valueOf(record.getValue()));
            } else {
                List<Object[]> returnList = ifpLogic.getResultTableResult(start, end, binder, columns, null,false,String.valueOf(record.getValue()));
                items = ifpLogic.getCoustomizedResult(returnList, binder, sessionDTO);
            }
            for (int rowCount = 0; rowCount < items.size(); rowCount++) {

                resultList = (IFPItemDTO) items.get(rowCount);
                if (!mode.equals(ConstantsUtils.VIEW_BTN)) {
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getItemNo()!=null){
                    printWriter.print(ConstantsUtils.QUOTE + resultList.getItemNo() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getItemName()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getItemName() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getItemDesc()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getItemDesc() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getItemFamilyplanStatus().getId()!=0 && resultList.getItemFamilyplanStatus()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getItemFamilyplanStatus().getDescription() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getItemFamilyplanStartDate()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  dateFormat.format(resultList.getItemFamilyplanStartDate()) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getItemFamilyplanEndDate()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  dateFormat.format(resultList.getItemFamilyplanEndDate()) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getItemStatus()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getItemStatus() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getForm()!=null){
                    printWriter.print(ConstantsUtils.QUOTE + resultList.getForm() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getStrength()!=null){
                   printWriter.print(ConstantsUtils.QUOTE +  resultList.getStrength() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA); 
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getTherapeuticClass()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getTherapeuticClass() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getBrand()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getBrand() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getAttachedDate()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  dateFormat.format(resultList.getAttachedDate()) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getModifiedDate()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  dateFormat.format(resultList.getModifiedDate()) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getModifiedBy()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getModifiedBy() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCreatedDate()!=null ){
                    printWriter.print(ConstantsUtils.QUOTE + dateFormat.format(resultList.getCreatedDate()) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCreatedBy()!=null){
                    printWriter.println(ConstantsUtils.QUOTE + resultList.getCreatedBy() + ConstantsUtils.QUOTE );
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
            }
            LOGGER.debug("Ending createWorkSheetContent");
        }
    }
}
