 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.copycontract.dto.CopyComponentDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import static com.stpl.app.gcm.copycontract.ui.form.Newcomponent.getSelectNull;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.common.HelperListUtil;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.discount.ui.layout.CopyContractWindow;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.ComponentLookUp;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.gcm.tp.dao.TradingPartnerDAO;
import com.stpl.app.gcm.tp.dao.impl.TradingPartnerDAOImpl;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.UI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.customwindow.MinimizeTray;
import org.jboss.logging.Logger;

/**
 *
 * @author kasiammal.m
 */
public class CopyContractform extends CustomComponent implements View {
private static final Logger LOGGER = Logger.getLogger(CopyContractform.class);
    CopyContractWindow editWindow;
    ExtFilterTable resultTable;
    @UiField("main")
    public VerticalLayout layout;
    public TabSheet tabsheet = new TabSheet();
    @UiField("typeNC")
    public ComboBox aliastypecc;
    @UiField("CLOSE")
    public Button CLOSE;
    @UiField("CREATE")
    public Button CREATE;
    @UiField("startdate")
    public PopupDateField startdate;
    @UiField("aliasStartDate")
    public PopupDateField aliasStartDate;
    @UiField("aliasEndDate")
    public PopupDateField aliasEndDate;
    @UiField("enddate")
    public PopupDateField enddate;
    @UiField("markettype")
    public ComboBox markettype;
    Map<Integer, Boolean> tabLazyLoadMap = new HashMap<Integer, Boolean>();
    private final CopyContractLogic CopyContractLogic = new CopyContractLogic();
    public static final SimpleDateFormat DBDate = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    boolean tabFlag = false;
    int tabPosition = 0;
    private Newcomponent Newcomponent;
    private Exixtingcomponent existingcomponent;
    private Copycomponents Copycomponent;
    List<ContractSelectionDTO> selectedList;
    CustomTreeContainer<CopyComponentDTO> dashBoardContainer = new CustomTreeContainer<CopyComponentDTO>(CopyComponentDTO.class);
    @UiField("populate")
    public Button populate;
    @UiField("contractid")
    public TextField contractid;
    @UiField("contractname")
    public TextField contractname;
    @UiField("contractno")
    public TextField contractno;
    TreeTable copyContractDashBoardTable = new TreeTable();
    TreeTable existingContractDashBoardTable = new TreeTable();
    TreeTable newcontractDashBoardTable = new TreeTable();
    ExtFilterTable multiContractTable = new ExtFilterTable();
    @UiField("contracthHolder")
    public CustomTextField contracthHolder;
    @UiField("contractStatus")
    public ComboBox contractStatus;
    @UiField("aliasNumber")
    public TextField aliasNumber;
    @UiField("multiContractLayout")
    public HorizontalLayout multiContractLayout;
    @UiField("horizontalLayoutNC1")
    public HorizontalLayout horizontalLayoutNC1;
    String count = StringUtils.EMPTY;
    @UiField("multiContractButtons")
    public HorizontalLayout multiContractButtons;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("removeBtn")
    public Button removeBtn;
    @UiField("populateBtn")
    public Button populateBtn;
    private final BeanItemContainer<CopyComponentDTO> multiContractContainer = new BeanItemContainer<CopyComponentDTO>(CopyComponentDTO.class);
    QueryUtils queryUtils = new QueryUtils();
    CommonLogic commonLogic=new CommonLogic();
    CommonUtil commonUtil=CommonUtil.getInstance();
    UiUtils UIUtils=new UiUtils();
     TradingPartnerDAO ccDao = new TradingPartnerDAOImpl();
    public CopyContractform(CopyContractWindow editWindow, List<ContractSelectionDTO> selectedList, String Count) throws SystemException, Exception {
        this.editWindow = editWindow;
        this.resultTable = resultTable;
        this.selectedList = selectedList;
        this.count = Count;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/CopyContractform.xml"), this));
        if (Integer.valueOf(Count) > 1) {
            multiContractLayout.addComponent(multiContractTable);
        }
       
        configurefields();
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private void configurefields() throws SystemException {
        try {
            contractid.focus();
            startdate.setDateFormat(Constants.MM_DD_YYYY);
            enddate.setDateFormat(Constants.MM_DD_YYYY);
            startdate.setStyleName(Constants.DATE_FIEILD_CENTER);
            enddate.setStyleName(Constants.DATE_FIEILD_CENTER);
            aliasStartDate.setStyleName(Constants.DATE_FIEILD_CENTER);
            aliasEndDate.setStyleName(Constants.DATE_FIEILD_CENTER);
            aliasStartDate.setDateFormat(Constants.MM_DD_YYYY);
            aliasEndDate.setDateFormat(Constants.MM_DD_YYYY);
            contractStatus.setVisible(true);
            commonUtil.loadComboBox(contractStatus, UIUtils.STATUS, false);
            contractStatus.setValidationVisible(true);
            commonUtil.loadComboBox(markettype, UIUtils.CONTRACT_TYPE, false);
            markettype.setValidationVisible(true);
            commonUtil.loadComboBox(aliastypecc, UIUtils.CONTRACT_ALIAS_TYPE, false);
            aliastypecc.setValidationVisible(true);
            Copycomponent = new Copycomponents(selectedList, copyContractDashBoardTable,dashBoardContainer);
            Newcomponent = new Newcomponent(newcontractDashBoardTable,dashBoardContainer);
            existingcomponent = new Exixtingcomponent(existingContractDashBoardTable,dashBoardContainer);
            tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            tabsheet.setImmediate(true);
            tabsheet.markAsDirty();
            tabsheet.markAsDirtyRecursive();
            tabsheet.addTab(Newcomponent, "New", null, 0);
            tabsheet.addTab(existingcomponent, "Existing", null, 1);
            tabsheet.addTab(Copycomponent, "Copy Component", null, 2);
            tabsheet.setSelectedTab(0);
            layout.addComponent(tabsheet);
            loadTable();
            attachTabChangeListener();
            editWindow.addCloseListener(new Window.CloseListener() {

                @Override
                public void windowClose(Window.CloseEvent e) {
                    closeEditTray(editWindow);
                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @UiHandler("CLOSE")
    public void btnCloseLogic(Button.ClickEvent event) {
        if (editWindow != null) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    // do nothing
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    try {
                        editWindow.close();
                        closeEditTray(editWindow);
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                }
                }
            }.getConfirmationMessage("Close", "Are you sure you want to close the popup?");
        }
    }
    
    @UiHandler("populate")
    public void populateLogic(Button.ClickEvent event) throws ParseException, SystemException {

        String cId = contractid.getValue();
        String cName = contractname.getValue();
        String cNo = contractno.getValue();
        Date SDATE = startdate.getValue();
        Date eDate = enddate.getValue();
        String mType = String.valueOf(markettype.getValue());
        String cStatus = String.valueOf(contractStatus.getValue());
        String cHolder = String.valueOf(contracthHolder.getValue());
         String contHolder1=String.valueOf(contracthHolder.getData());
        List listcId = null;
        List listcNo = null;
        if (!StringUtils.EMPTY.equals(cId)) {
            String query = "select upper(CONTRACT_ID) from CONTRACT_MASTER where CONTRACT_ID='" + cId.toUpperCase() + "'";
            listcId = CompanyMasterLocalServiceUtil.executeQuery(query);
        }
        if (!StringUtils.EMPTY.equals(cNo)) {
            String query = "select upper(CONTRACT_NO) from CONTRACT_MASTER where CONTRACT_NO='" + cNo.toUpperCase() + "'";
            listcNo = CompanyMasterLocalServiceUtil.executeQuery(query);
        }

        Date AliasSDATE = aliasStartDate.getValue();
        String AliasNumber = aliasNumber.getValue();
        Date AliasEDATE = aliasEndDate.getValue();
        if (StringUtils.EMPTY.equals(cId) || StringUtils.EMPTY.equals(cName) || StringUtils.EMPTY.equals(cNo) || SDATE == null || mType.equals(Constants.NULL) ||  Constants.NULL.equals(contHolder1)) {
            AbstractNotificationUtils.getErrorNotification("Populate", "Please complete all mandatory Contract Header Details.");
        } else if (cId.length() > 38) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract ID length should be less than 38 characters.");
        } else if (!cId.matches(Constants.ALPHA_NUM_VALIDATION)) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract ID  can contain only digits,alphabets.");
        } else if (cName.length() > 38) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract Name should be less than 38 characters.");
        } else if (!cName.matches("([0-9|a-z|A-Z|\\_|\\*|\\:|\\.|\\,|\\'|\\;|\\-|\\ |\\/])*")) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract Name allows Special Characters like *,:,.,(,),',;,-,/,_");
        } else if (cNo.length() > 38) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract No length should be less than 38 characters.");
        } else if (!cNo.matches("([0-9|a-z|A-Z|\\_|\\*|\\:|\\.|\\,|\\'|\\;|\\-|\\ |\\/])*")) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract No allows Special Characters like *,:,.,(,),',;,-,/,_");
        } else if (enddate.getValue() != null && startdate.getValue().after(enddate.getValue())) {
            //throw new Validator.InvalidValueException("Contract End date should be after Contract Start Date");
            AbstractNotificationUtils.getErrorNotification("Error", "Contract End date should be after Contract Start Date.");
        } else if (enddate.getValue() != null && startdate.getValue().getTime() == enddate.getValue().getTime()) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract Start date and Contract End date are equal.");
        } else if (AliasNumber.length() > 50) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract Alias No should be less than 50 characters.");
        } else if (!AliasNumber.matches("([0-9|a-z|A-Z|\\_|\\*|\\:|\\.|\\,|\\'|\\;|\\-|\\ |\\/])*")) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract Alias No allows Special Characters like *,:,.,(,),',;,-,/,_");
        } else if (aliasEndDate.getValue() != null && aliasStartDate.getValue().after(aliasEndDate.getValue())) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract Alias End date should be after Contract Alias Start Date.");
        } else if (aliasEndDate.getValue() != null && aliasStartDate.getValue().getTime() == aliasEndDate.getValue().getTime()) {
            AbstractNotificationUtils.getErrorNotification("Error", "Contract Alias Start date and Contract Alias End date are equal.");
        } else if (listcId != null && listcId.size() > 0) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter different Contract ID since the Contract ID  already exists");
        } else if (listcNo != null && listcNo.size() > 0) {
            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter different Contract No since the Contract No  already exists");
        } else {

//            if (tabPosition == 2) {
               // if (contractDashBoardTable.getItemIds().isEmpty()) {
                   String contHolder=String.valueOf(contracthHolder.getData());

                   final Object rootId = copyContractDashBoardTable.addItem();
                    copyContractDashBoardTable.getContainerProperty(rootId, "category").setValue("Contract Header");
                    copyContractDashBoardTable.getContainerProperty(rootId, "dashboardId").setValue(cId);
                    copyContractDashBoardTable.getContainerProperty(rootId, "dashboardNumber").setValue(cNo);
                    copyContractDashBoardTable.getContainerProperty(rootId, "dashboardName").setValue(cName);
                    copyContractDashBoardTable.getContainerProperty(rootId, "levelNo").setValue("0");
                    copyContractDashBoardTable.getContainerProperty(rootId, "marketType").setValue(HelperListUtil.getInstance().getHelperDTObyID(Integer.valueOf(markettype.getValue().toString())));
                    copyContractDashBoardTable.getContainerProperty(rootId, "contractHolder").setValue(contHolder);
                    copyContractDashBoardTable.getContainerProperty(rootId, "startDate").setValue(SDATE);
                    copyContractDashBoardTable.getContainerProperty(rootId, "status").setValue(HelperListUtil.getInstance().getHelperDTObyID(Integer.valueOf(contractStatus.getValue().toString())));
                    copyContractDashBoardTable.getContainerProperty(rootId, "endDate").setValue(eDate);
                    copyContractDashBoardTable.getContainerProperty(rootId, "aliasType").setValue(aliastypecc.getValue()==null?new HelperDTO(0, StringUtils.EMPTY):HelperListUtil.getInstance().getHelperDTObyID(Integer.valueOf(aliastypecc.getValue().toString())));
                    copyContractDashBoardTable.getContainerProperty(rootId, "aliasstartdate").setValue(AliasSDATE);
                    copyContractDashBoardTable.getContainerProperty(rootId, "aliasNumber").setValue(AliasNumber);
                    copyContractDashBoardTable.getContainerProperty(rootId, "aliasenddate").setValue(AliasEDATE);
                    copyContractDashBoardTable.getContainerProperty(rootId, Constants.ADDBY).setValue("0");
                    copyContractDashBoardTable.addItem(rootId);
                    resetFields();
            }
            }

    @UiHandler("CREATE")
    public void btnCreateLogic(Button.ClickEvent event) throws SystemException, PortalException {
        int levelcount = 0;
        if (tabPosition == 1) {

            Collection<?> treeItem = existingContractDashBoardTable.getItemIds();
            for (Object item : treeItem) {
                String level = String.valueOf(existingContractDashBoardTable.getContainerProperty(item, Constants.LEVELNO).getValue());
                if (level.equals(Constants.ZEROSTRING)) {
                    levelcount = 1;
                }
                if (level.equals("1")) {
                    levelcount = 2;
                }
                if (level.equals("2")) {
                    levelcount = 3;
                }
                if (level.equals("3")) {
                    levelcount = 4;
                }
                if (level.equals("4")) {
                    levelcount = 5;
                }
            }
        } else if (tabPosition == 0) {
            Collection<?> treeItem = newcontractDashBoardTable.getItemIds();
            for (Object item : treeItem) {
                String level = String.valueOf(newcontractDashBoardTable.getContainerProperty(item, Constants.LEVELNO).getValue());
                if (level.equals(Constants.ZEROSTRING)) {
                    levelcount = 1;
                }
                if (level.equals("1")) {
                    levelcount = 2;
                }
                if (level.equals("2")) {
                    levelcount = 3;
                }
                if (level.equals("3")) {
                    levelcount = 4;
                }
                if (level.equals("4")) {
                    levelcount = 5;
                }
            }
        } else if (tabPosition == 2) {
            Collection<?> treeItem = copyContractDashBoardTable.getItemIds();
            for (Object item : treeItem) {
                String level = String.valueOf(copyContractDashBoardTable.getContainerProperty(item, Constants.LEVELNO).getValue());
                if (level.equals(Constants.ZEROSTRING)) {
                    levelcount = 1;
                }
                if (level.equals("1")) {
                    levelcount = 2;
                }
                if (level.equals("2")) {
                    levelcount = 3;
                }
                if (level.equals("3")) {
                    levelcount = 4;
                }
                if (level.equals("4")) {
                    levelcount = 5;
                }
            }
        }

            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    // do nothing
                }


            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                try {
                    createContract();
                    resetFields();
                } catch (Exception ex) {
                }
            }
        }.getConfirmationMessage("Create", "Are you sure you want to save the new contract?");
    }
    
    private void createContract() throws SystemException, PortalException, ParseException {
        Object[] newItemId = newcontractDashBoardTable.getItemIds().toArray();
        Object[] extItemId = existingContractDashBoardTable.getItemIds().toArray();
        Object[] copyItemId = copyContractDashBoardTable.getItemIds().toArray();
        int i = 0;
        Object[] mainList;
        if (newItemId.length > extItemId.length && newItemId.length > copyItemId.length) {
            mainList = newItemId;
        } else if (extItemId.length > newItemId.length && extItemId.length > copyItemId.length) {
            mainList = extItemId;
        } else {
            mainList = copyItemId;
        }
        for (Object c : mainList) {
            String addBy = String.valueOf(newcontractDashBoardTable.getContainerProperty(c, String.valueOf(Constants.ADDBY)).getValue());
            switch (addBy) {
                case "0":
                    Newcomponent.savecontract(mainList[i]);
                    break;
                case "1":
                    Newcomponent.savecontract(mainList[i]);
                    break;
                case "2":
                    existingcomponent.savecontract(mainList[i]);
                    break;
                case "3":
                    Copycomponent.savecontract(mainList[i]);
                    break;
            }
            i++;
        }
        AbstractNotificationUtils.getAlertNotification("Info", "Contract Saved Successfully.");
    }

    private void attachTabChangeListener() {
        tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                try {
                    onTabChange(tabPosition);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });

    }

    protected void onTabChange(int tabPosition) throws PortalException, SystemException {
        tabsheet.setSelectedTab(tabPosition);
    }

    private void resetFields() {
        contractid.setValue(Constants.EMPTY);
        contractname.setValue(Constants.EMPTY);
        contractno.setValue(Constants.EMPTY);
        startdate.setValue(null);
        enddate.setValue(null);
        markettype.setValue(null);
        contractStatus.setValue(null);
        contracthHolder.setValue(Constants.EMPTY);
        contracthHolder.setData(Constants.EMPTY);
        aliasNumber.setValue(Constants.EMPTY);
        aliastypecc.setValue(null);
        aliasStartDate.setValue(null);
        aliasEndDate.setValue(null);
    }

    @UiHandler("contracthHolder")
    public void contractHolderLookup(CustomTextField.ClickEvent event) {
        try {
            ComponentLookUp chLookup = new ComponentLookUp("Contract Holder", "Contract Holder Lookup", contracthHolder);
            chLookup.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    if (contracthHolder.getData() != null) {
                        ComponentLookUpDTO object = (ComponentLookUpDTO) contracthHolder.getData();
                        contracthHolder.setData(object.getMasterSid());
                        contracthHolder.setValue(object.getComponentName());
                    }
                }
            });
            getUI().addWindow(chLookup);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void loadMultiContainer(int no) {
        List<CopyComponentDTO> list = new ArrayList<CopyComponentDTO>();
        for (int i = 0; i < no; i++) {
            CopyComponentDTO dto = new CopyComponentDTO();
            dto.setCheck(false);
            dto.setContractId(Constants.EMPTY);
            dto.setContractNo(Constants.EMPTY);
            dto.setContractName(Constants.EMPTY);
            dto.setMarketType(CommonUtil.ddlbDefaultValue);
            dto.setStatus(CommonUtil.ddlbDefaultValue);
            dto.setStartDate(null);
            dto.setEndDate(null);
            dto.setContractHolder(Constants.EMPTY);
            dto.setContractHolderName(Constants.EMPTY);
            dto.setAliasType(CommonUtil.ddlbDefaultValue);
            dto.setAliasNumber(Constants.EMPTY);
            dto.setAliasstartdate(null);
            dto.setAliasenddate(null);
            dto.setCheck(false);
            list.add(dto);
        }

              multiContractTable.setTableFieldFactory(new TableFieldFactory() {
                public Field<?> createField(Container container, final Object itemId, Object propertyId, Component uiContext) {
                    Field field;
                    if (String.valueOf(Constants.CHECK).equals(propertyId)) {
                        field = new CheckBox();
                        return field;
                    } if (Constants.START_DATE.equals(propertyId) || "aliasstartdate".equals(propertyId)|| "aliasenddate".equals(propertyId)) {
                        PopupDateField field1 = new PopupDateField();
                        field1.setDateFormat(Constants.MM_DD_YYYY);
                        field1.setStyleName(Constants.DATE_FIEILD_CENTER);
                       return field1;
                    }
                    if (Constants.END_DATE.equals(propertyId)) {
                        PopupDateField field1 = new PopupDateField();
                        field1.setDateFormat(Constants.MM_DD_YYYY);
                        field1.setStyleName(Constants.DATE_FIEILD_CENTER);
                       return field1;

                    }  if ("status".equals(propertyId) ) {
                        ComboBox status = new ComboBox();
                   
                        try {
                            commonUtil.loadComboBoxForGCM(status, UIUtils.STATUS, false);
                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }
                        return status;
                    }if (Constants.MARKET_TYPE.equals(propertyId) ) {
                        ComboBox marketType = new ComboBox();
                        getSelectNull(marketType);
                        try {
                        commonUtil.loadComboBoxForGCM(marketType, UIUtils.CONTRACT_TYPE, false);
                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }
                        return marketType;
                    }
                          if (propertyId.equals(Constants.CONTRACT_HOLDER)) {
                            final CustomTextField cHolder = new CustomTextField();
                            cHolder.addStyleName("searchicon");
                            cHolder.setWidth("100%");
                            cHolder.addClickListener(new CustomTextField.ClickListener() {
                                public void click(CustomTextField.ClickEvent event) {
                                    ComponentLookUp lookup = new ComponentLookUp("Contract Holder", "Contract Holder Lookup", cHolder);
                                    lookup.addCloseListener(new Window.CloseListener() {
                                        public void windowClose(Window.CloseEvent e) {
                                            if (cHolder.getData() != null) {
                                             ComponentLookUpDTO dto=(ComponentLookUpDTO)cHolder.getData();
                                             multiContractContainer.getContainerProperty(itemId,Constants.CONTRACT_HOLDER ).setValue(dto.getComponentNo());
                                              multiContractContainer.getContainerProperty(itemId,"contractHolderName" ).setValue(dto.getComponentName());
                                              multiContractContainer.getContainerProperty(itemId,"hiddenId" ).setValue(dto.getMasterSid());
                                            }
                                        }
                                    });
                                    UI.getCurrent().addWindow(lookup);
                                }
                            });
                            return cHolder;
                        }
                        if ("aliasType".equals(propertyId) ) {
                        ComboBox aliasType = new ComboBox();
                        getSelectNull(aliasType);
                        try {
                            commonUtil.loadComboBoxForGCM(aliasType, UIUtils.CONTRACT_ALIAS_TYPE, false);
                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }
                            return aliasType;
                }
                if ("contractId".equals(propertyId)) {
                    final TextField contractid = new TextField();
                    contractid.addBlurListener(new FieldEvents.BlurListener() {
                        public void blur(FieldEvents.BlurEvent event) {
                            String newValue = String.valueOf(contractid.getValue());
                            specValidation(newValue, contractid);
                        }
                    });

                        return contractid;
                    }if (Constants.CONTRACT_NO.equals(propertyId) ) {
                        final TextField contractNo = new TextField();
                        contractNo.addBlurListener(new FieldEvents.BlurListener() {
                        public void blur(FieldEvents.BlurEvent event) {
                            String newValue = String.valueOf(contractNo.getValue());
                             specValidation(newValue,contractNo);
                        }
                    });
                        return contractNo;
                    }if (Constants.CONTRACT_NAME.equals(propertyId) ) {
                        final TextField contractName = new TextField();
                              contractName.addBlurListener(new FieldEvents.BlurListener() {
                        public void blur(FieldEvents.BlurEvent event) {
                            String newValue = String.valueOf(contractName.getValue());
                             specValidation(newValue,contractName);
                        }
                    });
                        return contractName;
                    }if ("aliasNumber".equals(propertyId) ) {
                        final TextField aliasNumber = new TextField();
                        aliasNumber.setWidth("120px");
                         aliasNumber.addBlurListener(new FieldEvents.BlurListener() {
                        public void blur(FieldEvents.BlurEvent event) {
                            String newValue = String.valueOf(aliasNumber.getValue());
                             specValidation(newValue,aliasNumber);
                        }
                    });
                        return aliasNumber;
                    }
                    else {
                        field = null;
                    }
                    return field;
                }
            });
        
        multiContractContainer.addAll(list);
         
    }


    private void loadTable() {
        if (!count.equals("1")) {
            multiContractTable.setEditable(true);
            multiContractTable.setPageLength(5);
            multiContractTable.setWidth("1800px");
            int i = Integer.valueOf(count);
            loadMultiContainer(i);
            multiContractTable.setContainerDataSource(multiContractContainer);
            multiContractTable.setVisibleColumns("check", "contractId", Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, "status", Constants.START_DATE, Constants.END_DATE, Constants.CONTRACT_HOLDER, "contractHolderName", "aliasType", "aliasNumber", "aliasstartdate", "aliasenddate");
            multiContractTable.setColumnHeaders(StringUtils.EMPTY, "Contract ID", "Contract No", "Contract Name", "Market Type", "Status", "Start Date", "End Date", "Contract Holder", "Contract Holder Name", "Alias Type", "Alias Number", "Alias Start Date", "Alias End Date");
            multiContractTable.setVisible(true);
            multiContractButtons.setVisible(true);
            horizontalLayoutNC1.setVisible(false);
             multiContractTable.setColumnCheckBox(HeaderUtil.CONTRACT_SEARCH_COLUMN[0], Boolean.TRUE);
                 multiContractTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                for (CopyComponentDTO temp : multiContractContainer.getItemIds()) {
                    multiContractContainer.getItem(temp).getItemProperty(event.getPropertyId()).setValue(event.isChecked());
                }
            }
        });

            
            
            Object[] visibleColumns = multiContractTable.getVisibleColumns();
            for (Object column : visibleColumns) {
                if(String.valueOf(column).equals(Constants.CHECK)){
                    multiContractTable.setColumnWidth(column, 80);
                }else{
                    multiContractTable.setColumnWidth(column, 230);
                }
                
            }
        } else {
            horizontalLayoutNC1.setVisible(true);
            multiContractButtons.setVisible(false);
            multiContractTable.setVisible(false);
        }
    }

    


    private void specValidation(String value, TextField field) {

        if (value.length() > 38) {
            AbstractNotificationUtils.getErrorNotification("Error", "Length should be less than 38 characters.");
            field.setValue(Constants.EMPTY);
        } else if (!value.matches(Constants.ALPHA_NUM_VALIDATION)) {
            AbstractNotificationUtils.getErrorNotification("Error", "Field can contain only digits,alphabets.");
            field.setValue(Constants.EMPTY);
        }
    }

    @UiHandler("removeBtn")
    public void removeBtnLogic(Button.ClickEvent event) {
    
        removeLogic();
    }

    
    @UiHandler("populateBtn")
    public void populateBtnLogic(Button.ClickEvent event) {
        final Collection<?> list = multiContractTable.getItemIds();
        List<String> idList = new ArrayList<String>();
        List<String> noList = new ArrayList<String>();
        Boolean checkFlag = false;
        if (list != null) {
            for (Object tmp : list) {
                Boolean test = (Boolean) multiContractContainer.getContainerProperty(tmp, Constants.CHECK).getValue();
                if(test){
                   checkFlag=true;
                    CopyComponentDTO dto=(CopyComponentDTO)tmp;
                if(!dto.getContractId().equals(StringUtils.EMPTY) && !dto.getContractNo().equals(StringUtils.EMPTY) && !dto.getContractName().equals(StringUtils.EMPTY) && !dto.getMarketType().equals(Constants.ZEROSTRING) && dto.getStartDate()!=null && dto.getEndDate()!=null && !dto.getStatus().equals(Constants.ZEROSTRING) && !dto.getContractHolder().equals("") && !dto.getContractHolderName().equals(StringUtils.EMPTY))    
                   {
                        idList.add(dto.getContractId());
                        noList.add(dto.getContractNo());

                        Date sDate = dto.getStartDate();
                        Date eDate = dto.getEndDate();
                        Boolean flag = dateValidation(sDate, eDate, "Contract");
                        if (!flag) {
                            return;
                        }
                       if (dto.getAliasstartdate() != null && !dateValidation(dto.getAliasstartdate(), dto.getAliasenddate(), "Alias")) {
                            return;
                       } else if(dto.getAliasstartdate() == null && dto.getAliasenddate() != null) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR," Alias End date should be after Alias Start Date.");
                            return;
                        }
                    } else {
                        if (!dto.getContractId().equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter all the value for Contract Id " + dto.getContractId() + " .");
                            return;
                        } else {
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please enter all the value in the table");
                            return;
                        }
                    }
                }
            }

            if (!checkFlag) {
                AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please highlight a row to populate.");
                return;
            }
            if (idList != null) {
                String ids = commonLogic.idString(idList);
                String query = "select upper(CONTRACT_ID) from CONTRACT_MASTER where CONTRACT_ID in (" + ids + ")";
                List listId = CompanyMasterLocalServiceUtil.executeQuery(query);
                if (listId != null && listId.size() > 0) {
                    Object obj = listId.get(0);
                    String duplicateId = String.valueOf(obj);
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Contract ID " + duplicateId + " already exists. Enter different Contract ID");
                    return;
                }
            }
            if (noList != null) {
                String ids = commonLogic.idString(noList);
                String query = "select upper(CONTRACT_NO) from CONTRACT_MASTER where CONTRACT_NO in (" + ids + ")";
                List listNo = CompanyMasterLocalServiceUtil.executeQuery(query);
                if (listNo != null && listNo.size() > 0) {
                    Object obj = listNo.get(0);
                    String duplicateId = String.valueOf(obj);
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Contract NO " + duplicateId + " already exists. Enter different Contract NO");
                    return;
                }
            }

            addMultiContractDashboard(list);
        }
    }
    
     @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                try {
                    resetLogic();
                } catch (Exception ex) {

                }
            }
        }.getConfirmationMessage("Reset", "Are you sure you want to reset the page to default/previous values?");

    }
    
    
    private Boolean dateValidation(Date sDate,Date eDate,String field)
    {
        Boolean flag=true;
        if (eDate!= null && sDate.after(eDate)) {

               AbstractNotificationUtils.getErrorNotification("Error", field+" End date should be after "+  field+" Start Date.");
                return false;
         } else if (eDate != null && sDate.getTime() == eDate.getTime()) {
                  AbstractNotificationUtils.getErrorNotification("Error", field+ "Start date and "+ field+" End date are equal.");
                 return false;
         }
        return flag;
    }
    
    private void removeLogic() {
        Collection<?> list = multiContractTable.getItemIds();
        if (list != null) {
           Boolean checkFlag=false;
            final List<Object> idList = new ArrayList<Object>();
            for (final Iterator<?> iterator = multiContractTable.getItemIds().iterator(); iterator.hasNext();) {
                Object item = iterator.next();
                Boolean checked = (Boolean) multiContractContainer.getContainerProperty(item, Constants.CHECK).getValue();
                if (checked) {
                  checkFlag=true;
                    idList.add(item);
                }
            }
             if(!checkFlag)
           {
                 AbstractNotificationUtils.getErrorNotification("Error", "Please select a row to remove.");
                           return;
           }
               new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                try {
                   
                   if (idList.size() > 0) {
                for (Object obj : idList) {
                    if (multiContractTable.removeItem(obj));
                }
            } 
                } catch (Exception ex) {
                    
                }
            }
        }.getConfirmationMessage("Reset", "Are you sure you want to remove the checked row?");
             
            
        }
    }
    
    private void resetLogic()
    {
        int i=multiContractContainer.size();
        multiContractContainer.removeAllItems();
         multiContractTable.resetFilters();
       loadMultiContainer(i);
        multiContractTable.setColumnCheckBox(HeaderUtil.CONTRACT_SEARCH_COLUMN[0], Boolean.TRUE);
    }
    
    private void addMultiContractDashboard( Collection<?> list)
            {

           for(Object item:list){
                Boolean checked = (Boolean) multiContractContainer.getContainerProperty(item, Constants.CHECK).getValue();
                if(checked){
                 CopyComponentDTO dto=(CopyComponentDTO)item;
                    final Object rootId = newcontractDashBoardTable.addItem();
                    newcontractDashBoardTable.getContainerProperty(rootId, "category").setValue("Contract Header");
                    newcontractDashBoardTable.getContainerProperty(rootId, "dashboardId").setValue(dto.getContractId());
                    newcontractDashBoardTable.getContainerProperty(rootId, "dashboardNumber").setValue(dto.getContractNo());
                    newcontractDashBoardTable.getContainerProperty(rootId, "dashboardName").setValue(dto.getContractName());
                    newcontractDashBoardTable.getContainerProperty(rootId, "levelNo").setValue("0");
                    newcontractDashBoardTable.getContainerProperty(rootId, "marketType").setValue(dto.getMarketType());
                    newcontractDashBoardTable.getContainerProperty(rootId, "contractHolder").setValue(dto.getHiddenId());
                    newcontractDashBoardTable.getContainerProperty(rootId, "startDate").setValue(dto.getStartDate());
                    newcontractDashBoardTable.getContainerProperty(rootId, "endDate").setValue(dto.getEndDate());
                    newcontractDashBoardTable.getContainerProperty(rootId, "status").setValue(dto.getStatus());
                    newcontractDashBoardTable.getContainerProperty(rootId, "aliasType").setValue(dto.getAliasType()==null?new HelperDTO(0,StringUtils.EMPTY):dto.getAliasType());
                    newcontractDashBoardTable.getContainerProperty(rootId, "aliasstartdate").setValue(dto.getAliasstartdate());
                    newcontractDashBoardTable.getContainerProperty(rootId, "aliasNumber").setValue(String.valueOf(dto.getAliasNumber()));
                    newcontractDashBoardTable.getContainerProperty(rootId, "aliasenddate").setValue(dto.getAliasenddate());
                    newcontractDashBoardTable.getContainerProperty(rootId, Constants.ADDBY).setValue("0");
                    newcontractDashBoardTable.addItem(rootId);  
                    
                }
           }
           multiContractContainer.removeAllItems();
           loadTable();
            }
        /**
     * Used to close the Edit tray
     * 
     * @param editWindow 
     */
    private void closeEditTray(CustomWindow editWindow) {
        MinimizeTray tray = editWindow.getMinimizeTray();
        if (tray.getWindowItems().size() == 1) {
            tray.close();
        }
    }
    
}
