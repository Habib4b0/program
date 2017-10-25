/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.global.company.dto.TradeClassTableGenerator;
import com.stpl.app.global.company.ui.view.CompanyAddView;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shyam.duraipandian
 */
public class CompanyTradeClassForm extends StplCustomComponent {

    /**
     * The CsLayout Layout.
     */
    @UiField("cssLayout")
    CssLayout cssLayout;
    /**
     * The Horizontal Layout.
     */
    @UiField("hLayout")
    HorizontalLayout hlayout;
  
    /**
     * The Error Msg.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The trade class1.
     */
    @UiField("tradeClass1")
    ComboBox tradeClass1;
    /**
     * The trade class s date.
     */
    @UiField("tradeClassSDate")
    PopupDateField tradeClassSDate;
    /**
     * The trade class e date.
     */
    @UiField("tradeClassEndDate")
    PopupDateField tradeClassEDate;
    @UiField("excelExport")
    private Button excelExport;
    //Label
    /**
     * The trade class1.
     */
    @UiField("labelTradeClass1")
    Label labelTradeClass1 = ResponsiveUtils.makeLabel("Trade Class", true);
    /**
     * The trade class s date.
     */
    @UiField("labelTradeClassSDate")
    Label labelTradeClassSDate = ResponsiveUtils.makeLabel("Trade Class Start Date", true);
    /**
     * The trade class e date.
     */
    @UiField("labelTradeClassEDate")
    Label labelTradeClassEDate;
    /**
     * The trade class results bean.
     */
    BeanItemContainer<CompanyMasterDTO> tradeClassResultsBean;
    @UiField("resultTable")
    ExtFilterTable resultTable;
    /**
     * The attach button
     */
    @UiField("attachButton")
    Button attachButton;
    /**
     * The attach button
     */
    @UiField("removeButton")
    Button removeButton;
     /**
     * The vertical Layout
     */
    @UiField("panel")
    Panel panel;
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();  
    /** The HelperList */
    HelperListUtil helperListUtil=HelperListUtil.getInstance();
    /** The Company Master DTO */
    CompanyMasterDTO companyMasterDTO = new CompanyMasterDTO();
    /** The binder */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<CompanyMasterDTO>(companyMasterDTO));
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanyAddView.class);
    private Object selectedId;
    private final Map<Integer, Boolean> reloadVerticalLayoutTabFourMap = new HashMap<Integer, Boolean>();
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    IFPLogic ifpLogic = new IFPLogic();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    SessionDTO sessionDTO;

    /**
     * Gets the selected id.
     *
     * @return the selected id
     */
    public Object getSelectedId() {
        return selectedId;
    }

    /**
     * Sets the selected id.
     *
     * @param selectedId the new selected id
     */
    public void setSelectedId(final Object selectedId) {
        this.selectedId = selectedId;
    }
/**
 * Used to add the component
 * 
 * @param binder
 * @param tradeClassResultsBean
 * @return 
 */
    public Component getContent(ErrorfulFieldGroup binder, final BeanItemContainer<CompanyMasterDTO> tradeClassResultsBean, SessionDTO sessionDTO) {
        VerticalLayout vLayout = new VerticalLayout();
        try {
            this.binder = binder;
            this.tradeClassResultsBean = tradeClassResultsBean;
            this.sessionDTO=sessionDTO;
            
            vLayout.addComponent(Clara.create(getClass().getResourceAsStream("/clara/companyMaster/CompanyTradeClassForm.xml"), this));

            addResponsiveVerticalTabFourLayout(vLayout);
            getBinder();
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER+ConstantsUtils.COMMA+TabNameUtil.TRADE_CLASS,false);
            final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_MASTER+ConstantsUtils.COMMA+TabNameUtil.TRADE_CLASS);
            configurePermission(fieldCompanyHM, functionHM);
            configureFields();

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return vLayout;
    }
    
    private void configurePermission(final Map<String, AppPermission> fieldCompanyHM, final Map<String, AppPermission> functionHM) {
        LOGGER.debug("Entering configurePermission");
        try {
        String mode = sessionDTO.getMode();
        
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_MASTER,TabNameUtil.TRADE_CLASS);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldCompanyHM, mode);
        if (functionHM.get(FiledNameUtils.ATTACH_TRADECLASS) != null && ((AppPermission) functionHM.get(FiledNameUtils.ATTACH_TRADECLASS)).isFunctionFlag()) {
            attachButton();
        }else{
            attachButton.setVisible(false);
        }
        if (functionHM.get(FiledNameUtils.REMOVE_TRADECLASS) != null && ((AppPermission) functionHM.get(FiledNameUtils.REMOVE_TRADECLASS)).isFunctionFlag()) {
            removeButton();
        }else{
            removeButton.setVisible(false);
        }
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    public void disableTable() {
        resultTable.setSelectable(false);
        resultTable.setEditable(false);
        removeButton.setVisible(false);
        panel.setVisible(false);
    }

    public void configureFields() throws PortalException, SystemException {
        LOGGER.debug("configureFields");

        tradeClassSDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        tradeClassEDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        tradeClassSDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    try {
                        tradeClassSDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(tradeClassSDate.getValue()));
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
        });
        tradeClassEDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    try {
                        tradeClassEDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(tradeClassEDate.getValue()));
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
           commonUtil.loadComboBox(tradeClass1, UIUtils.TRADE_CLASS, false);
        tradeClass1.setWidth("207px");
        resultTable.removeAllItems();
        resultTable.markAsDirty();
        resultTable.addStyleName("table-header-normal");
        
        final StplSecurity stplSecurity = new StplSecurity();
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER+ConstantsUtils.COMMA+"Company Trade Class Header",false);

        String mode = sessionDTO.getMode();
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_MASTER, "Company Trade Class Header");
        Object[] obj = UIUtils.TRADE_CLASS_COLUMNS;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
            if(tableResultCustom.getObjResult().length == 0){
              resultTable.setVisible(false);
            }
        resultTable.setContainerDataSource(tradeClassResultsBean);
        resultTable.setVisibleColumns(tableResultCustom.getObjResult());
        resultTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        
        resultTable.setPageLength(NumericConstants.THREE);
        resultTable.setImmediate(true);
        resultTable.setSelectable(true);
        resultTable.setSizeFull();
        resultTable.setTableFieldFactory(new TradeClassTableGenerator());
        resultTable.setEditable(true);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * serialVersionUID
             */
            private static final long serialVersionUID = 1L;

            /**
             * item click
             */
            public void itemClick(final ItemClickEvent event) {
                selectedId = event.getItemId();

            }
        });
    }

    public void attachButton() {

        attachButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    boolean flag = false;
                    binder.getErrorDisplay().clearError();
                    final CompanyMasterDTO identForm = new CompanyMasterDTO();
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + "<br>");
                    if (tradeClass1.getValue() == null || ConstantsUtils.ZERO.equals(tradeClass1.getValue())) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Trade Class");
                        flag = true;
                    }
                    if (tradeClassSDate.getValue() == null) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.COMMA);
                        }
                        errorMessage.append("Start Date");
                        flag = true;
                    } else {
                        identForm.setTradeClassSDate((Date) tradeClassSDate.getValue());
                    }
                    if (flag) {
                        binder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    } else {

                        binder.getFields();

                        identForm.setTradeClass1((HelperDTO) (tradeClass1.getValue()));
                        identForm.setTradeClass(String.valueOf(tradeClass1.getItemCaption(tradeClass1.getValue())));
                        if (tradeClassEDate.getValue() != null) {
                            identForm.setTradeClassEDate((Date) tradeClassEDate.getValue());
                        }

                        if (identForm.getTradeClassEDate() != null) {
                            if (identForm.getTradeClassSDate() == null) {

                                binder.getErrorDisplay().setError("Trade class Start Date is Mandatory in Company Trade Class Tab");
                                return;
                            } else {
                                final Date tradeStartDate = identForm.getTradeClassSDate();
                                final Date tradeEndDate = identForm.getTradeClassEDate();
                                final int differedtradeDate = tradeStartDate.compareTo(tradeEndDate);
                                if (differedtradeDate == Constants.ZERO) {
                                    binder.getErrorDisplay().setError("Trade Class Start Date should not be equal to Trade Class  End Date in Company Trade Class Tab");
                                    return;
                                } else if (differedtradeDate > Constants.ZERO) {
                                    binder.getErrorDisplay().setError("Trade Class Start Date should be less than Trade Class  End Date in Company Trade Class Tab");
                                    return;
                                }
                            }

                        }

                        List<CompanyMasterDTO> tradeClassList = tradeClassResultsBean.getItemIds();
                        if (tradeClassList != null) {
                            List<Integer> tradeClassNoList = new ArrayList<Integer>();
                            List<Date> tradeClassStartDateList = new ArrayList<Date>();
                            for (CompanyMasterDTO tradeClass : tradeClassList) {
                                tradeClassNoList.add(tradeClass.getTradeClass1().getId());
                                tradeClassStartDateList.add(tradeClass.getTradeClassSDate());
                            }
                            if (tradeClassNoList.contains(identForm.getTradeClass1())) {
                                binder.getErrorDisplay().setError("Please select different Trade class. Selected Trade Class already exists");
                                return;
                            }
                            if (tradeClassStartDateList.contains(identForm.getTradeClassSDate())) {
                                binder.getErrorDisplay().setError("Please enter different Start Date. Selected Start Date already exists");
                                return;
                            }
                        }

                        identForm.setCreatedDate(new Date());
                        String createdBy = StplSecurity.userMap.get(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                        identForm.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);

                        tradeClassResultsBean.addBean(identForm);
                        tradeClass1.select(null);
                        tradeClassSDate.setValue(null);
                        tradeClassEDate.setValue(null);
                    }

                } catch (Exception e) {
                    LOGGER.error(e);
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
        });

    }

    public void removeButton() {
        removeButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                List<CompanyMasterDTO> list = tradeClassResultsBean.getItemIds();
                Object selectValue = resultTable.getValue();
                boolean flag = resultTable.isSelected(selectValue);
                binder.getErrorDisplay().clearError();
                if (list == null || list.isEmpty()) {
                    binder.getErrorDisplay().setError(ValidationUtils.TRADE_CLASS_VALID);
                } else if (!flag) {
                    binder.getErrorDisplay().setError("Please select a Trade Class from the list view to Remove.");
                } else {
                    MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to remove the trade class from the company " + " ?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            try {

                                if (buttonId.name().equals("YES")) {
                                    tradeClassResultsBean.removeItem(selectedId);
                                    selectedId = null;
                                    Object itemClick = resultTable.getValue();
                                    resultTable.unselect(itemClick);
                                }
                            } catch (Exception e) {
                                LOGGER.error(e);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                }
            }
        });

    }

    @UiHandler("excelExport")
    public void excelExportButtonClick(final Button.ClickEvent event) {
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

    protected void excelExportLogic() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        final long recordCount = resultTable.getContainerDataSource().size();
        ExcelExportforBB.createWorkSheet(resultTable.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.TRADE_CLASS_EXP);
        LOGGER.debug("Ending createWorkSheet");
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {
        CompanyMasterDTO dto;
        final List<CompanyMasterDTO> searchList;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String date;

        searchList = tradeClassResultsBean.getItemIds();
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);

            printWriter.print(ConstantsUtils.QUOTE + dto.getTradeClass() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            if (dto.getTradeClassSDate() != null) {
                printWriter.print(ConstantsUtils.QUOTE + format.format(dto.getTradeClassSDate()) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if (dto.getTradeClassEDate() != null) {
                printWriter.print(ConstantsUtils.QUOTE + format.format(dto.getTradeClassEDate()) + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if (dto.getCreatedDate() != null) {
                date = format.format(dto.getCreatedDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            } else {
                printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }

            printWriter.print(ConstantsUtils.QUOTE + dto.getCreatedBy() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getModifiedBy() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            if (dto.getModifiedDate() != null) {
                date = format.format(dto.getModifiedDate());
                printWriter.println(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE);
            } else {
                printWriter.println(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE);
            }

        }


    }

    public void addResponsiveVerticalTabFourLayout(final VerticalLayout verticalLayout) {
        reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
        reloadVerticalLayoutTabFourMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
        reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
        reloadVerticalLayoutTabFourMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadVerticalLayoutTabFourMap.put(NumericConstants.EIGHT_HUNDRED, true);
        reloadVerticalLayoutTabFourMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadVerticalLayoutTabFourMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
        reloadVerticalLayoutTabFourMap.put(0, true);

        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {

                        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                        if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX && reloadVerticalLayoutTabFourMap.get(NumericConstants.ONE_FIVE_ONE_SIX)) {
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_FIVE_ONE_SIX, false);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(0, true);

                            getCollapsibleColumnsDefault(resultTable);
                        } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.THOUSAND_THREE_HUNDRED && reloadVerticalLayoutTabFourMap.get(NumericConstants.THOUSAND_THREE_HUNDRED)) {
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, false);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(0, true);
                            getCollapsibleColumnsDefault(resultTable);
                        } else if (browserWidth < NumericConstants.THOUSAND_THREE_HUNDRED && browserWidth > NumericConstants.ONE_ZERO_TWO_FOUR && reloadVerticalLayoutTabFourMap.get(NumericConstants.ONE_ZERO_TWO_FOUR)) {
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, false);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(0, true);
                            getCollapsibleColumnsDefault(resultTable);
                        } else if (browserWidth < NumericConstants.ONE_ZERO_TWO_FOUR && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadVerticalLayoutTabFourMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(0, true);
                        } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.EIGHT_HUNDRED && reloadVerticalLayoutTabFourMap.get(NumericConstants.EIGHT_HUNDRED)) {
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.EIGHT_HUNDRED, false);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(0, true);

                        } else if (browserWidth <= NumericConstants.EIGHT_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadVerticalLayoutTabFourMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(0, true);
                        } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_EIGHT_ZERO && reloadVerticalLayoutTabFourMap.get(NumericConstants.THREE_EIGHT_ZERO)) {
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THREE_EIGHT_ZERO, false);
                            reloadVerticalLayoutTabFourMap.put(0, true);
                            getCollapsibleOneColumn(resultTable);
                        } else if (browserWidth < NumericConstants.THREE_EIGHT_ZERO && reloadVerticalLayoutTabFourMap.get(0)) {
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_FIVE_ONE_SIX, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THOUSAND_THREE_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.ONE_ZERO_TWO_FOUR, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.EIGHT_HUNDRED, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(NumericConstants.THREE_EIGHT_ZERO, true);
                            reloadVerticalLayoutTabFourMap.put(0, false);
                            getCollapsibleOneColumn(resultTable);
                        }

                    }
                });
    }

    private static Object[] getCollapsibleOneColumn(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new Object[list.size()]);

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
}
