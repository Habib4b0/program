/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.cfp.ui.form;

import com.stpl.app.global.cfp.dto.CFPCompanyDTO;
import com.stpl.app.global.cfp.dto.CFPTableGenerator;
import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.cfp.ui.lazyload.CompanyDetailsContainer;
import com.stpl.app.global.cfp.ui.lazyload.CompanyDetailsCriteria;
import com.stpl.app.global.cfp.util.CFPTestGenerator;
import com.stpl.app.global.cfp.util.CommonUtils;
import com.stpl.app.global.cfp.util.UIUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplR2Exception;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonLazyUtilDTO;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
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
public class CFPCompanies extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(CFPCompanies.class);
    /**
     * The ErrorfulFieldGroup
     */
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
     * the Mode
     */
    private String mode;
    /**
     * the MassUpdate layout
     */
    @UiField("cssLayout")
    private CssLayout cssLayout;
    
    @UiField("massUpdateLayout")
    private HorizontalLayout massUpdateLayout;
    /**
     * The mass check.
     */
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
    @UiField("pageCreater")
    HorizontalLayout pageCreater;
    @UiField("excel")
    public Button excel;
    @UiField("companyDetailsTableLayout")
    private VerticalLayout companyDetailsTableLayout;
    /**
     * Record checkbox
     */
    @UiField("record")
    private OptionGroup record;
    @UiField("valueforddlb")
    private Label valueforddlb; 
    
    public BeanItemContainer<CFPCompanyDTO> companyDetailsResultSaveBean = new BeanItemContainer<>(CFPCompanyDTO.class);
    /**
     * The cfp logic.
     */
    private final CFPSearchLogic cfpLogic;
    /**
     * The company details table.
     */
    @UiField("companyDetailsTable")
    private CustomePagedFilterTable companyDetailsTable;
    CFPCompanyDTO cfpMaster;
    CompanyDetailsCriteria companyDetailsCriteria = new CompanyDetailsCriteria();
    SessionDTO sessionDTO;
   
    /**
     * The company details lazy bean is used to load the companies from temp
     * table in companies tab
     */
    private LazyBeanItemContainer companyDetailsResultLazyBean;
    private final Map<Integer, Boolean> reloadMap = new HashMap<>();
    private final IFPLogic ifpLogic = new IFPLogic();

    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png"); 
    
    /**
     * CompanyDetailsContainer constructor
     */
    CompanyDetailsContainer detailContanier ;
    
    CompanyDetailsContainer detailviewContanier ;
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();

    public CFPCompanies(final ErrorfulFieldGroup binder, CFPCompanyDTO cfpMaster, final SessionDTO sessionDTO) {
        this.binder = binder;
        this.cfpMaster = cfpMaster;
        this.sessionDTO=sessionDTO;
        cfpLogic = new CFPSearchLogic(this.sessionDTO);
        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/company_family_plan/companies.xml"), this));
            binder.bindMemberFields(this);
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            sessionId = sessionDTO.getUiSessionId();
            tempCreatedDate = sessionDTO.getSessionDate();
            mode = sessionDTO.getMode();

            init();
            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> fieldCfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.COMPANIES,false);
            configureFieldPermission(fieldCfpHM);
            configureButtonPermission();

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void init() throws PortalException, SystemException {
        record.addItems(ConstantsUtils.CURRENT, ConstantsUtils.HISTORY, "Future");
        record.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                String value = String.valueOf(record.getValue());
                if (mode.equals("View")) {
                    detailviewContanier.setRecord(value);
                } else {
                    detailContanier.setRecord(value);
                }
                companyDetailsTable.setCurrentPage(1);
            }
        });

        excel.setIcon(excelExportImage);
        excel.setDescription("Export to excel");
        excel.setIconAlternateText("Excel export");
        excel.setHtmlContentAllowed(true);
        
        try {
            addItemDetailsTable();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CFPCompanies.class.getName()).log(Level.SEVERE, null, ex);
        }

        companyDetailsTable.setColumnCollapsingAllowed(false);
        
        if (mode.equals(ConstantsUtils.VIEW_BTN)) {
            massUpdateLayout.setVisible(false);
            massUpdateLayout.removeAllComponents();
        } else {
            configureFields();
            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.COMPANIES);
            if (functionCfpHM.get(FunctionNameUtil.POPULATE_CFP) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.POPULATE_CFP)).isFunctionFlag()) {
                addBtnPopulate();
            } else {
                btnPopulate.setVisible(false);
            }

            if (functionCfpHM.get(FunctionNameUtil.POPULATE_ALL_CFP) != null && ((AppPermission) functionCfpHM.get(FunctionNameUtil.POPULATE_ALL_CFP)).isFunctionFlag()) {
                addAllPopulate();
            } else {
                btnAllPopulate.setVisible(false);
            }
           
        }
    }

    

  private void configureFieldPermission(final Map<String, AppPermission> fieldCfpHM) {
        LOGGER.debug("Entering configurePermission");
        try {
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN,TabNameUtil.CFP_COMPANIES);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldCfpHM, mode);
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

  /**
   * Configure fields
   */
    private void configureFields() {
        try {
            massDate.setDescription(ConstantsUtils.DATE_DES);

            massCheck.addItem(ConstantsUtils.ENABLE);
            massCheck.addItem(ConstantsUtils.DISABLE);
            massCheck.setValue(ConstantsUtils.DISABLE);
            massCheck.select(ConstantsUtils.DISABLE);
            massCheck.setImmediate(true);
            massCheck.setStyleName("horizontal");
            massCheck.setMultiSelect(false);

            massField.addItem(ConstantsUtils.SELECT_ONE);
            CommonUtils.getSeletNull(massField);
            massField.addItem(Constants.CFP_START_DATE);
            massField.addItem(Constants.CFP_END_DATE);
            massField.addItem(Constants.CFP_STATUS);
            massField.select(ConstantsUtils.SELECT_ONE);
            massField.setDescription((String) massField.getValue());
            valueforddlb.setPrimaryStyleName("labelsize");
            valueforddlb.setVisible(false);

            massField.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    massField.setDescription(String.valueOf(massField.getValue()) == null? StringUtils.EMPTY : String.valueOf(massField.getValue()));

                }
            });

            massField.setImmediate(true);
            massValue.setVisible(false);
            massValue.setImmediate(true);
            massValue.setNullSelectionAllowed(true);
            massValue.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
            massValue.setDescription((String) massValue.getValue());
            massValue.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {

                    massValue.setDescription((String) massValue.getValue());

                }
            });

            new CommonUtils().getNativeSelect(massValue, cfpLogic.getDropDownList(UIUtils.CFP_STATUS));

            massDate.setImmediate(true);
            massDate.setVisible(false);
            massDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            massDate.addStyleName(ConstantsUtils.DATE_FIEILD_CENTER);
            massDate.setId("MassDate");
            massDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                        massDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(massDate.getValue()));
                }
            });

            btnPopulate.setEnabled(false);
            btnAllPopulate.setEnabled(false);
            massField.setEnabled(false);
            btnAllPopulate.setImmediate(false);

            massField.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {

                    final String value = String.valueOf(massField.getValue());

                    if (Constants.CFP_STATUS.equals(value)) {
                        valueforddlb.setVisible(true);

                        massValue.setVisible(true);
                        massDate.setVisible(false);
                        btnPopulate.setReadOnly(false);
                    } else if (Constants.CFP_START_DATE.equals(value) || Constants.CFP_END_DATE.equals(value)) {
                        valueforddlb.setVisible(true);

                        massValue.setVisible(false);
                        massDate.setVisible(true);
                        massDate.setValue(null);
                        btnPopulate.setReadOnly(false);
                        btnAllPopulate.setReadOnly(false);

                    }

                }
            });

            massCheck.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {

                    if ((ConstantsUtils.DISABLE).equals(massCheck.getValue())) {

                        massField.setEnabled(false);
                        massValue.setValue(StringUtils.EMPTY);
                        massValue.setVisible(false);
                        massDate.setVisible(false);
                        btnPopulate.setEnabled(false);
                        btnPopulate.setReadOnly(true);
                        btnAllPopulate.setEnabled(false);

                        markAsDirty();

                    } else if ((ConstantsUtils.ENABLE).equals(massCheck.getValue())) {
                        valueforddlb.setEnabled(true);
                        massField.setEnabled(true);
                        btnPopulate.setEnabled(true);
                        btnAllPopulate.setEnabled(true);
                        markAsDirty();
                    }

                }
            });

        }catch (SystemException se) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(se);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
              
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
        /**
         * The method is triggered when a button of the message box is
         * pressed .
         *
         * @param buttonId The buttonId of the pressed button.
         */
        // Do Nothing
         catch (Exception ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.WARN, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
              
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
     * Adds the populate button .
     *
     * @return the button
     * @throws StplR2Exception the stpl r2 exception
     */
    public void addBtnPopulate() {
        LOGGER.debug("Entering addBtnPopulate");
        btnPopulate.setReadOnly(true);
        btnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.debug(ConstantsUtils.ERROR_IN_SEARCH);
            }
        });

        btnPopulate.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug("Entering into POPULATE method");
                    binder.getErrorDisplay().clearError();

                    if (massField.getValue() == null) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please Select the Field to populate", new MessageBoxListener() {
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
                    } else {
                        if (massField.getValue().toString().equals(Constants.CFP_STATUS) && massValue.getValue() == null) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please Select a value to populate", new MessageBoxListener() {
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
                        if ((massField.getValue().toString().equals(Constants.CFP_START_DATE) || massField.getValue().toString().equals(Constants.CFP_END_DATE))
                                && massDate.getValue() == null) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please Select a date to populate", new MessageBoxListener() {
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
                    //    before populate, need to save the records in temp table
                    saveRecordsInTempTable();
                    // this is check atleast company is selected in third tab
                    List<Object> companyIdList = cfpLogic.validateNull(userId, sessionId, tempCreatedDate, "checkRecord");
                    if (((Integer) companyIdList.get(0)) == ConstantsUtils.ZERO_INT) {

                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "At least one company needs to be selected", new MessageBoxListener() {
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

                    if (massField.getValue() != null && (massValue.getValue() != null || massDate.getValue() != null)) {
                        cfpLogic.populateCheckedRecords(userId, sessionId, tempCreatedDate, massField.getValue(), massValue.getValue(), massDate.getValue());
                        companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
                        LOGGER.debug("Ending POPULATE method");
                    }
                } catch (PortalException ex) {
                    LOGGER.error(ex);
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
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        LOGGER.debug("Ending addBtnPopulate");
    }

    /**
     * add all populate button.
     *
     * @return the button
     * @throws StplR2Exception the stpl r2 exception
     */
    public Button addAllPopulate() {
        btnAllPopulate.setReadOnly(true);
        btnAllPopulate.setErrorHandler(new ErrorHandler() {
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.debug(ConstantsUtils.ERROR_IN_SEARCH);
            }
        });

        btnAllPopulate.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug("Enteing inside POPULATE_ALL method");

                    binder.getErrorDisplay().clearError();
                    if (massField.getValue() == null) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please Select the Field to populate", new MessageBoxListener() {
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
                    } else { 
                        if (massField.getValue().toString().equals(Constants.CFP_STATUS) && massValue.getValue()== null) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please Select a value to populate", new MessageBoxListener() {
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
                        if ((massField.getValue().toString().equals(Constants.CFP_START_DATE) || massField.getValue().toString().equals(Constants.CFP_END_DATE))
                                && massDate.getValue() == null) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please Select a date to populate", new MessageBoxListener() {
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

                    if (massField.getValue() != null && (massValue.getValue() != null || massDate.getValue() != null)) {
                        //before populate, need to save the records in temp table
                        if (companyDetailsResultSaveBean.size() > 0) {
                            CFPSearchLogic.saveToTempTable(companyDetailsResultSaveBean.getItemIds());
                            companyDetailsResultSaveBean.removeAllItems();
                        }
                        if (massField.getValue() != null && (massValue.getValue() != null || massDate.getValue() != null)) {
                            cfpLogic.populateAll(userId, sessionId, tempCreatedDate, massField.getValue(), massValue.getValue(), massDate.getValue(), "populate");
                            // this setCurrentPage is used to refresh the companyDetailsResultLazyBean lazy conatiner 
                            companyDetailsCriteria.setCustomDirty(false);
                            companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
                            companyDetailsCriteria.setCustomDirty(true);
                        }
                    }

                    LOGGER.debug("Ending  POPULATE_ALL method");
                } catch (PortalException ex) {
                    LOGGER.error(ex);
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
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        return btnAllPopulate;
    }

    /**
     * Adds the item details table.
     *
     * @return the table
     */
    public void addItemDetailsTable() {
        try {
            LOGGER.debug("Entering addItemDetailsTable");
             final StplSecurity stplSecurity = new StplSecurity();
                userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                final Map<String, AppPermission> fieldCfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.COMPANIES,false);
                
                companyDetailsTable.markAsDirty();
            if (mode.equals(ConstantsUtils.VIEW_BTN)) {              
                detailviewContanier = new CompanyDetailsContainer(companyDetailsTable, Boolean.TRUE,
                        String.valueOf(cfpMaster.getCompanyFamilyPlanSystemId()), new String[]{cfpMaster.getCompanyFamilyPlanNo(), cfpMaster.getCompanyFamilyPlanName()}, ConstantsUtils.COMPANIES);
                companyDetailsResultLazyBean = new LazyBeanItemContainer(CFPCompanyDTO.class, detailviewContanier, companyDetailsCriteria);
                companyDetailsTable.setContainerDataSource(companyDetailsResultLazyBean);
                companyDetailsTable.setVisible(true);
                companyDetailsTable.setVisibleColumns(UIUtils.ITEM_COL_VIEW);
                companyDetailsTable.setColumnHeaders(UIUtils.ITEM_HEADER_VIEW);
                companyDetailsTable.setPageLength(NumericConstants.TEN);
                companyDetailsTable.sinkItemPerPageWithPageLength(false);
                companyDetailsTable.setImmediate(true);
                companyDetailsTable.setSizeFull();
                companyDetailsTable.setFilterBarVisible(false);
                companyDetailsTable.setReadOnly(true);
            } else {
                List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN, TabNameUtil.CFP_COMPANIES);
                Object[] obj = UIUtils.ITEM_DETAILS_COL;
                TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldCfpHM, mode);
                if (tableResultCustom.getObjResult().length == 0) {
                    companyDetailsTable.setVisible(false);
                    
                }else{
                companyDetailsTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
                companyDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
                detailContanier = new CompanyDetailsContainer(companyDetailsTable, companyDetailsResultSaveBean);
                companyDetailsResultLazyBean = new LazyBeanItemContainer(CFPCompanyDTO.class, detailContanier, companyDetailsCriteria);
                companyDetailsTable.setContainerDataSource(companyDetailsResultLazyBean);
                Object [] visibleColumns = tableResultCustom.getObjResult();
                for(int i=0;i < visibleColumns.length; i++){
                    if("companyFamilyPlanStatusValue".equals(visibleColumns[i])){
                        visibleColumns[i] = "companyFamilyPlanStatus";
                    }
                }
                companyDetailsTable.setVisibleColumns(visibleColumns); 
                companyDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                companyDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
                companyDetailsTable.setSelectable(true);
                companyDetailsTable.setPageLength(NumericConstants.SEVEN);
                companyDetailsTable.sinkItemPerPageWithPageLength(false);
                companyDetailsTable.setImmediate(true);
                companyDetailsTable.setSizeFull();
                companyDetailsTable.setTableFieldFactory(new CFPTableGenerator(companyDetailsResultSaveBean,sessionDTO,companyDetailsTable));
                companyDetailsTable.setEditable(true);
                companyDetailsTable.setFilterBarVisible(true);
                companyDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
                companyDetailsTable.setFilterGenerator(new CFPTestGenerator());
                companyDetailsTable.setFilterFieldVisible(companyDetailsTable.getVisibleColumns()[0], false);
                setDefaultTableWidth(companyDetailsTable);

                }
            }
            companyDetailsTableLayout.addComponent(companyDetailsTable);
            } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(CFPCompanies.class.getName()).log(Level.SEVERE, null, ex);
            LOGGER.error(ex);                    
            } catch (SystemException ex) {
                LOGGER.error(ex);
                java.util.logging.Logger.getLogger(CFPCompanies.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResponsiveUtils.getResponsiveControls(companyDetailsTable.createControls(), pageCreater);
            pageCreater.addComponent(excel);
            companyDetailsTable.setErrorHandler(new ErrorHandler() {
                /**
                 * Invoked when an error occurs.
                 */
                public void error(final com.vaadin.server.ErrorEvent event) {
                    LOGGER.error("Error in details Table"+event.getThrowable());
                }
            });
            if (!mode.equals(ConstantsUtils.VIEW_BTN)) {
                companyDetailsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                    @Override
                    public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                        if (ConstantsUtils.CHECK_BOX.equals(event.getPropertyId().toString())) {
                            if (event.isChecked()) {
                                try {
                                    for (CFPCompanyDTO dto : companyDetailsResultSaveBean.getItemIds()) {
                                        dto.setCheckbox(Boolean.TRUE);
                                    }
                                    cfpLogic.populateAll(userId, sessionId, tempCreatedDate, null, null, null, "check");
                                    companyDetailsResultSaveBean.removeAllItems();
                                    // this setCurrentPage is used to refresh the lazy conatiner
                                    companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
                                    companyDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, true);
                                } catch (PortalException ex) {
                                    java.util.logging.Logger.getLogger(CFPAddForm.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SystemException ex) {
                                    java.util.logging.Logger.getLogger(CFPAddForm.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                try {
                                    for (CFPCompanyDTO dto : companyDetailsResultSaveBean.getItemIds()) {
                                        dto.setCheckbox(Boolean.FALSE);
                                    }
                                    cfpLogic.populateAll(userId, sessionId, tempCreatedDate, null, null, null, "uncheck");
                                    companyDetailsResultSaveBean.removeAllItems();
                                    // this setCurrentPage is used to refresh the lazy conatiner
                                    companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
                                    companyDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
                                } catch (PortalException ex) {
                                    java.util.logging.Logger.getLogger(CFPAddForm.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SystemException ex) {
                                    java.util.logging.Logger.getLogger(CFPAddForm.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                });
            }
            LOGGER.debug("Ending addItemDetailsTable");
        
    }
    
    public void setDefaultTableWidth(final CustomePagedFilterTable table) {

        try {
            table.setColumnCollapsingAllowed(true);
            int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.NINE_SEVEN_EIGHT) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }

                if (companyDetailsResultLazyBean != null && companyDetailsResultLazyBean.getItemIds().isEmpty()) {
                    for (Object propertyId : getCollapsibleColumns978Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                } else {
                    for (Object propertyId : getCollapsibleColumns600Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < NumericConstants.THREE_TWO_ZERO) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private static String[] getCollapsibleColumns600Px(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumnsDefault1515Px(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static Object[] getSixColumns(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
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
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
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

    private static Object[] getCollapsibleColumns978Px(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(propertyIds));
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
                        companyDetailsCriteria.setCustomDirty(false);

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
                        companyDetailsCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {
                        companyDetailsCriteria.setCustomDirty(false);
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
                        companyDetailsCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED && reloadMap.get(NumericConstants.SIX_HUNDRED)) {
                        companyDetailsCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }

                        if (companyDetailsResultLazyBean != null && companyDetailsResultLazyBean.getItemIds().isEmpty()) {
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
                        companyDetailsCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {
                        // --> Disables reloading the container
                        companyDetailsCriteria.setCustomDirty(false);
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
                        // --> Enables reloading the container
                        companyDetailsCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadMap.get(NumericConstants.THREE_TWO_ZERO)) {
                        companyDetailsCriteria.setCustomDirty(false);
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
                        companyDetailsCriteria.setCustomDirty(true);
                    } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadMap.get(0)) {
                        companyDetailsCriteria.setCustomDirty(false);
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
                        companyDetailsCriteria.setCustomDirty(true);
                    }

                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    void configureInTabChange(CommonLazyUtilDTO lazyUtilDTO) {
        
        companyDetailsResultLazyBean = new LazyBeanItemContainer(CFPCompanyDTO.class, detailContanier, companyDetailsCriteria);
        companyDetailsTable.setData(lazyUtilDTO);
        if (!mode.equals(ConstantsUtils.VIEW_BTN)) {
            companyDetailsTable.setFilterBarVisible(true);
            companyDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            companyDetailsTable.setTableFieldFactory(new CFPTableGenerator(companyDetailsResultSaveBean,sessionDTO,companyDetailsTable));
            companyDetailsTable.setFilterGenerator(new CFPTestGenerator());
            companyDetailsTable.setFilterFieldVisible(companyDetailsTable.getVisibleColumns()[0], false);
            companyDetailsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        }
        companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
        companyDetailsResultSaveBean.removeAllItems();
        binder.getField("massCheck").focus();

        massCheck.setValue(ConstantsUtils.DISABLE);
        massCheck.select(ConstantsUtils.DISABLE);
    }
    
    public void loadDetailsTable(){
        companyDetailsTable.setCurrentPage(companyDetailsTable.getCurrentPage());
    }
    
    public void saveRecordsInTempTable() throws SystemException, PortalException {
        if (companyDetailsResultSaveBean.size() > 0) {
            CFPSearchLogic.saveToTempTable(companyDetailsResultSaveBean.getItemIds());
            companyDetailsResultSaveBean.removeAllItems();
        }
    }

    void resetCompanyDetailsTable() {
        BeanItemContainer<CFPCompanyDTO> emptyContainer = new BeanItemContainer<>(CFPCompanyDTO.class);
        companyDetailsTable.setContainerDataSource(emptyContainer);
        companyDetailsTable.setVisibleColumns(UIUtils.ITEM_DETAILS_COL);
        companyDetailsTable.setColumnHeaders(UIUtils.ITEM_COL_HEADER);
    }

    private void configureButtonPermission() {
        return;
          
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
    
    private void createWorkSheet() throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        int recordCount =0;
        if (mode.equals(ConstantsUtils.VIEW_BTN)) {
            recordCount = CFPSearchLogic.getLazyTempCfpDetailsCount(String.valueOf(cfpMaster.getCompanyFamilyPlanSystemId()),null,String.valueOf(record.getValue()));
        }else{
            CommonLazyUtilDTO dto = (CommonLazyUtilDTO) companyDetailsTable.getData();
            recordCount = CFPSearchLogic.getLazyTempCfpDetailsCount(dto,null);
        }
        ExcelExportforBB.createWorkSheet(!mode.equals(ConstantsUtils.VIEW_BTN) ? UIUtils.ITEM_COL_HEADER : Arrays.copyOfRange(UIUtils.ITEM_COL_HEADER, 1, UIUtils.ITEM_COL_HEADER.length), recordCount, this, getUI(), ConstantsUtils.COMPANIES);
        LOGGER.debug("Ending createWorkSheet");
        }
    
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws PortalException, SystemException {
        LOGGER.debug("Entering createWorkSheetContent");
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT, Locale.getDefault());
        if (companyDetailsTable.size() > 0) {
            CFPCompanyDTO resultList;
            final List<OrderByColumn> columns = new ArrayList<>();
            List<CFPCompanyDTO> companies = null;
            if (mode.equals(ConstantsUtils.VIEW_BTN)) {
                companies = CFPSearchLogic.getLazyTempCfpDetailsResults(start, end, String.valueOf(cfpMaster.getCompanyFamilyPlanSystemId()), new String[]{cfpMaster.getCompanyFamilyPlanNo(), cfpMaster.getCompanyFamilyPlanName()}, columns, null,String.valueOf(record.getValue()));
            } else {
                CommonLazyUtilDTO dto = (CommonLazyUtilDTO) companyDetailsTable.getData();
                List<Object[]> list1 = CFPSearchLogic.searchCompanyHelperTableSort(start, end, dto, columns, null,String.valueOf(record.getValue()),false);
                List<CFPCompanyDTO> finalList = new ArrayList<>();

                companies = CFPSearchLogic.getCustomizedTempCFPCompanyDTO(list1, finalList, dto);
            }
            for (int rowCount = 0; rowCount < companies.size(); rowCount++) {

                resultList = (CFPCompanyDTO) companies.get(rowCount);
                if (!mode.equals(ConstantsUtils.VIEW_BTN)) {
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCompanyId()!=null){
                    printWriter.print(ConstantsUtils.QUOTE + resultList.getCompanyId() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCompanyNo()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getCompanyNo() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCompanyName()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getCompanyName() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCompanyFamilyPlanStatus().getId()!=0 && resultList.getCompanyFamilyPlanStatus()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getCompanyFamilyPlanStatus() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCompanyFamilyPlanStartDate()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  dateFormat.format(resultList.getCompanyFamilyPlanStartDate()) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCompanyFamilyPlanEndDate()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  dateFormat.format(resultList.getCompanyFamilyPlanEndDate()) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCompanyStatusValue()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getCompanyStatusValue() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCompanyTypeValue()!=null){
                    printWriter.print(ConstantsUtils.QUOTE + resultList.getCompanyTypeValue() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getTradeClass()!=null){
                   printWriter.print(ConstantsUtils.QUOTE +  resultList.getTradeClass() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA); 
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCompanyCategoryValue()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getCompanyCategory() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getTradingPartnerContractNo()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getTradingPartnerContractNo() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getAttachedDate()!=null){
                    printWriter.print(ConstantsUtils.QUOTE +  resultList.getAttachedDate() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
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
                if(resultList.getCreatedDate()!=null){
                    printWriter.print(ConstantsUtils.QUOTE + dateFormat.format(resultList.getCreatedDate()) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA );
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
                if(resultList.getCreatedBy()!=null ){
                    printWriter.println(ConstantsUtils.QUOTE + resultList.getCreatedBy() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }else{
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }
            }
            LOGGER.debug("Ending createWorkSheetContent");
        }
    }

    public ComboBox getMassField() {
        return massField;
    }

    public void setMassField(ComboBox massField) {
        this.massField = massField;
    }
}
