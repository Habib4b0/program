/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.ifp.ui.form;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.dto.ItemFamilyplanMasterDTO;
import com.stpl.app.global.ifp.dto.TempItemDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.ifp.ui.lazyload.IFPItemContainer;
import com.stpl.app.global.ifp.ui.lazyload.IFPItemCriteria;
import com.stpl.app.global.ifp.ui.lazyload.SelectedViewContainer;
import com.stpl.app.global.ifp.ui.lazyload.TempSelectedContainer;
import com.stpl.app.global.ifp.ui.lazyload.TempSelectedCriteria;
import com.stpl.app.global.ifp.util.IfpUtils;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.IFPFilterGenerator;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.ResponsiveUtils;

import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author sooriya.lakshmanan
 */
public class IFPItemAddition extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(IFPItemAddition.class);
    final ErrorfulFieldGroup binder;
    /**
     * the used id
     */
    private String userId;

    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();

    final CommonUtils commonUtils = new CommonUtils();

    /**
     * The ifp logic.
     */
    private final IFPLogic ifpLogic;
    
    @UiField("addLayout")
    private VerticalLayout addLayout;
    @UiField("viewLayout")
    private VerticalLayout viewLayout;
    @UiField("hlayout2")
    private HorizontalLayout hlayout2;
    @UiField("search")
    private Button searchBtn;

    @UiField("searchFields")
    private ComboBox searchFields;

    @UiField("valueList")
    private ComboBox valueList;

    @UiField("searchValue")
    private TextField searchValue;

    @UiField("cssLayout")
    CssLayout cssLayout;
    /**
     * The available table.
     */
    @UiField("availableTable")
    private CustomePagedFilterTable availableTable;
    /**
     * The selected table.
     */
    @UiField("selectedTable")
    private CustomePagedFilterTable selectedTable;
    /**
     * The View results Table
     */
    @UiField("viewResultsTable")
    private CustomePagedFilterTable viewResultsTable;

    
   
    @UiField("btnMove")
    private Button btnMove;
    @UiField("btnRemove")
    private Button btnRemove;
    @UiField("btnMoveAll")
    private Button btnMoveAll;
    @UiField("btnRemoveAll")
    private Button btnRemoveAll;
    @UiField("availableTableLayout")
    private VerticalLayout availableTableLayout;
    @UiField("selectedTableLayout")
    private VerticalLayout selectedTableLayout;
   
    private String tempSearchField;

    private String tempSearchValue;

    private LazyBeanItemContainer lazySelectedContainer;
    private LazyBeanItemContainer lazyAvailableContainer;

    private final BeanItemContainer<ItemMasterDTO> emptyContainer = new BeanItemContainer<ItemMasterDTO>(ItemMasterDTO.class);

    /**
     * The available item result bean.
     */
    private final BeanItemContainer<ItemMasterDTO> availableItemResultBean = new BeanItemContainer<ItemMasterDTO>(ItemMasterDTO.class);

    private final ItemFamilyplanMasterDTO ifpMaster;
    
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    String mode;
    
    private CommonUtil commonUtil = CommonUtil.getInstance();
    SessionDTO sessionDTO;
    
    public IFPItemAddition(final ErrorfulFieldGroup binder, ItemFamilyplanMasterDTO ifpMaster, final String mode, final SessionDTO sessionDTO) {
        this.ifpMaster = ifpMaster;
        this.binder = binder;
        this.mode = mode;
        this.sessionDTO = sessionDTO;
        ifpLogic = new IFPLogic(this.sessionDTO);
        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/item_family_plan/itemaddition.xml"), this));
            binder.bindMemberFields(this);
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            if (String.valueOf(mode).equals("View")) {
                addResultsTable();
                addLayout.removeAllComponents();
                addLayout.setVisible(false);
            }
            else
            {
            viewLayout.setVisible(false);
            init();
            configureFields();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void init() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEM_ADDITION,false);
        configurePermission(fieldIfpHM);
        final Map<String, AppPermission> functionIfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEM_ADDITION);
        if (functionIfpHM.get(FunctionNameUtil.SEARCH) != null && ((AppPermission) functionIfpHM.get(FunctionNameUtil.SEARCH)).isFunctionFlag()) {
            addFindBtn();
	}else{
            searchBtn.setVisible(false);
        }        
        addAvailableTable();
        addSelectedTable();
        add();
        remove();
        addAll();
        removeAll();
    }
    
    private void configurePermission(final Map<String, AppPermission> fieldIfpHM) {
        LOGGER.debug("Entering configurePermission");
        try {
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN,ConstantsUtils.ITEM_ADDITION);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldIfpHM, mode);
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

    private void configureFields() {
        final IfpUtils ifpUtils = new IfpUtils();

        searchFields.focus();
        searchFields.setNullSelectionAllowed(true);
        searchFields.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        ifpUtils.searchFields(searchFields);
        searchFields.select(ConstantsUtils.SELECT_ONE);
        searchFields.setDescription((String) searchFields.getValue());
        searchFields.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for mass field logic and its listener
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                try {

                    if (searchFields.getValue() != null) {
                        final String value = String.valueOf(searchFields.getValue());
                        if ("Form".equals(value)) {

                            searchValue.setVisible(false);
                            valueList.setVisible(true);
                            valueList.removeAllItems();
                            commonUtil.loadComboBox(valueList,"FORM", false);

                        } else if ("Item Status".equals(value)) {

                            searchValue.setVisible(false);
                            valueList.setVisible(true);
                            valueList.removeAllItems();
                            commonUtil.loadComboBox(valueList,"STATUS", false);
                        } else if ("Strength".equals(value)) {

                            searchValue.setVisible(false);
                            valueList.setVisible(true);
                            valueList.removeAllItems();
                            commonUtil.loadComboBox(valueList,"STRENGTH", false);
                        } else if ("Therapeutic Class".equals(value)) {

                            searchValue.setVisible(false);
                            valueList.setVisible(true);
                            valueList.removeAllItems();
                            commonUtil.loadComboBox(valueList,"THERAPEUTIC_CLASS", false);
                        } else if ("UOM".equals(value)) {

                            searchValue.setVisible(false);
                            valueList.setVisible(true);
                            valueList.removeAllItems();
                            commonUtil.loadComboBox(valueList,"UOM", false);
                        } else {
                            searchValue.setValue(StringUtils.EMPTY);
                            searchValue.setVisible(true);
                            valueList.setVisible(false);
                        }
                    } else {
                        searchValue.setVisible(true);
                        valueList.setVisible(false);
                    }
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(IFPItemAddition.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        searchValue.setValidationVisible(true);

        valueList.setImmediate(true);
        valueList.setVisible(false);
        valueList.setImmediate(true);
        valueList.setNullSelectionAllowed(true);
        valueList.addItem(Constants.ZERO);
        valueList.setItemCaption(Constants.ZERO, Constants.SELECT_ONE);
        valueList.setNullSelectionItemId(Constants.ZERO);
        valueList.select(Constants.ZERO);

    }

    /**
     * Adds the find btn.
     *
     * @return the button
     */
    public Button addFindBtn() {

        searchBtn.setImmediate(true);
        searchBtn.setWidth("85");
        searchBtn.setErrorHandler(new ErrorHandler() {

            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty method
            }
        });
        /**
         * Find button listener when the button triggers
         */
        searchBtn.addClickListener(new Button.ClickListener() {

            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    binder.getErrorDisplay().clearError();
                    int value = 0;
                    LOGGER.debug("Entering inside IFPTabsheetForm SEARCH  method ");
                    if (valueList.getValue() != null) {
                            value = ((HelperDTO)valueList.getValue()).getId();
                        }
                    if (searchFields.getValue() == null
                            || (searchValue.isVisible() && (searchValue.getValue() == null || StringUtils.isEmpty(searchValue.getValue())))
                            || (valueList.isVisible() && value == 0)) {

                        if (searchFields.getValue() == null && valueList.isVisible() && value == 0) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please enter Search criteria", new MessageBoxListener() {
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
                        } else if (searchFields.getValue() == null) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Select Search Field", new MessageBoxListener() {
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
                        } else if (valueList.isVisible() && value == 0) {
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please select a value to search", new MessageBoxListener() {
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
                            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please enter a value to search", new MessageBoxListener() {
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

                    } else {

                        tempSearchField = searchFields.getValue().toString();
                        if (searchValue.isVisible()) {
                            tempSearchValue = searchValue.getValue().trim();
                        } else if (valueList.isVisible()) {
                            tempSearchValue = String.valueOf(value);
                        }
                        loadAvailableTable();
                        if (availableTable.size() > Constants.ZERO) {
                            CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                        } else {
                            CommonUIUtils.successNotification(ConstantsUtils.NO_RESULTS_COMPLETED);
                        }

                    }
                    LOGGER.debug("Ending IFPTabsheetForm SEARCH method ");
                } catch (Exception exception) {
                    LOGGER.error(exception);
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

    public void loadAvailableTable() {
        
        try {
            lazyAvailableContainer = new LazyBeanItemContainer(ItemMasterDTO.class, new IFPItemContainer(tempSearchField, tempSearchValue, sessionDTO), new IFPItemCriteria());
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEM_ADDITION,false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN, ConstantsUtils.ITEM_ADDITION);
            Object[] obj = IfpUtils.AVAILABLE_ITEM_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
            availableTable.setContainerDataSource(lazyAvailableContainer);
            
            availableTable.setVisibleColumns(tableResultCustom.getObjResult());
            availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(IFPItemAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(IFPItemAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(IFPItemAddition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resetAvailableTable(){
         try {
            BeanItemContainer<ItemMasterDTO> emptyContainer = new BeanItemContainer<ItemMasterDTO>(ItemMasterDTO.class);
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEM_ADDITION,false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN, ConstantsUtils.ITEM_ADDITION);
            Object[] obj = IfpUtils.AVAILABLE_ITEM_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM,mode);
            
            availableTable.setContainerDataSource(emptyContainer);
            availableTable.setVisibleColumns(tableResultCustom.getObjResult());
            availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(IFPItemAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(IFPItemAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(IFPItemAddition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds the ">" Button.
     *
     * @return the button
     */
    public void add() {
        btnMove.setDescription(ConstantsUtils.MOVE_RIGHT);
        btnMove.setImmediate(true);
        btnMove.setWidth(ConstantsUtils.WIDTH);
        btnMove.setErrorHandler(new ErrorHandler() {

            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty method
            }
        });
       btnMove.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    binder.getErrorDisplay().clearError();
                    final Set<ItemMasterDTO> itemMasterDetailsList = (Set<ItemMasterDTO>) availableTable.getValue();
                    if (availableTable.size() <= 0) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "There are no items to move", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;

                    } else if (itemMasterDetailsList.isEmpty()) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please select an item to add", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                    }
                    for (ItemMasterDTO item : itemMasterDetailsList) {
                        if (item != null) {
                            ifpLogic.addToTempTable(item, ifpMaster.getItemFamilyplanSystemId());                           
                        }
                    }
                    loadAvailableTable();
                    loadSelectedTable();
                    LOGGER.debug("Ending  MOVE_RIGHT Method ");
                } catch (Exception e) {
                     
                    LOGGER.error(e);
                }
            }
        });
    }

    /**
     * Add ">" Button and its properties.
     *
     * @return the button
     */
    public void remove() {
        btnRemove.setDescription("Move selected items to left");

        btnRemove.setWidth(ConstantsUtils.WIDTH);
        btnRemove.setErrorHandler(new ErrorHandler() {

            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty method
            }
        });
        btnRemove.addClickListener(new Button.ClickListener() {

            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug("Entering inside  MOVE_LEFT  method ");
                    binder.getErrorDisplay().clearError();
                    final Set<TempItemDTO> itemMasterDetailsList = (Set<TempItemDTO>) selectedTable.getValue();
                    if (selectedTable.size() <= 0) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "There are no items to remove", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                    } else if (itemMasterDetailsList.isEmpty()) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please select an item to remove", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                    }
                    for (TempItemDTO dto : itemMasterDetailsList) {
                        if (itemMasterDetailsList != null) {
                            ifpLogic.removeFromTempTable(dto);
                        }
                    }
                    loadAvailableTable();
                    loadSelectedTable();
                    LOGGER.debug("Ending  MOVE_LEFT Method ");
                } catch (Exception e) {                     
                    LOGGER.error(e);
                }
            }
        });

    }

    /**
     * Add the all button ">>" which will move all the data s from available to
     * selected.
     *
     * @return the button
     */
    public void addAll() {
        btnMoveAll.setDescription(ConstantsUtils.MOVE_ALL_RIGHT_DES);

        btnMoveAll.setImmediate(true);
        btnMoveAll.setWidth(ConstantsUtils.WIDTH);
        btnMoveAll.setErrorHandler(new ErrorHandler() {

            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // empty method
            }
        });
        btnMoveAll.addClickListener(new Button.ClickListener() {

            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering inside IFPTabsheetForm MOVE_ALL_RIGHT  method ");

                try {
                    addAllCompanyButtonClick(event);
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
                } catch (PortalException portException) {
                    LOGGER.error(portException);
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

                LOGGER.debug("Ending IFPTabsheetForm MOVE_ALL_RIGHT  method ");
            }
        });
    }

    /**
     * Adds the all company button click - It will add All company values.
     *
     * @param event the event
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     */
    private void addAllCompanyButtonClick(final Button.ClickEvent event) throws SystemException, PortalException {
        ifpLogic.addAllToTempTable(tempSearchField, tempSearchValue);
        loadAvailableTable();
        loadSelectedTable();

    }

    /**
     * Removes the all the values from selected.
     *
     * @return the button
     */
    public void removeAll() {
        btnRemoveAll.setDescription(ConstantsUtils.MOVE_ALL_LEFT_DES);

        btnRemoveAll.setWidth(ConstantsUtils.WIDTH);
        btnRemoveAll.setErrorHandler(new ErrorHandler() {

            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // Added for error handling
            }
        });
        btnRemoveAll.addClickListener(new Button.ClickListener() {

            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering inside  MOVE_ALL_LEFT  method ");

                try {
                    removeAllCompanyButtonClick(event);
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
                } catch (PortalException portException) {
                    LOGGER.error(portException);
                }
                LOGGER.debug("Ending  MOVE_ALL_LEFT  method ");
            }
        });
    }

    public void removeAllCompanyButtonClick(final Button.ClickEvent event) throws SystemException, PortalException {
        ifpLogic.removeAllFromTempTable(Boolean.FALSE);
        loadAvailableTable();
        loadSelectedTable();
    }

    public void loadSelectedTable() {
   
        try {
            lazySelectedContainer = new LazyBeanItemContainer(TempItemDTO.class, new TempSelectedContainer(sessionDTO), new TempSelectedCriteria());
            
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEM_ADDITION,false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN, ConstantsUtils.ITEM_ADDITION);
            Object[] obj = IfpUtils.SELECTED_ITEM_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
            if(tableResultCustom.getObjResult().length > 0){
            selectedTable.setContainerDataSource(lazySelectedContainer);
            selectedTable.setVisibleColumns(tableResultCustom.getObjResult());
            selectedTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
       }else{
                hlayout2.setVisible(false);
            }
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
        LOGGER.error(ex);
        }
    }

    /**
     * Adds the available table.
     *
     * @return the table
     */
    public void addAvailableTable() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+ConstantsUtils.COMMA+ConstantsUtils.ITEM_ADDITION,false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN, ConstantsUtils.ITEM_ADDITION);
            Object[] obj = IfpUtils.AVAILABLE_ITEM_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
            
            availableTable.setContainerDataSource(availableItemResultBean);
            if(tableResultCustom.getObjResult().length > 0){
            availableTable.setVisibleColumns(tableResultCustom.getObjResult());
            availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            }else{
                hlayout2.setVisible(false);
            }
            availableTable.markAsDirty();
            availableTable.setPageLength(NumericConstants.SIX);
            availableTable.setNullSelectionAllowed(true);
            availableTable.sinkItemPerPageWithPageLength(false);
            availableTable.setImmediate(true);
            availableTable.setSelectable(true);
            availableTable.setMultiSelect(true);
            availableTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
            availableTable.setSizeFull();
            availableTable.setFilterBarVisible(true);
            availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableTable.setValidationVisible(false);
            availableTable.addStyleName(ConstantsUtils.FILTER_BAR);
            availableTable.setFilterGenerator(new IFPFilterGenerator());
            availableTable.setWidth("380px");
            availableTable.setFilterBarVisible(true);
            availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableTable.setFilterGenerator(new IFPFilterGenerator());
            HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(availableTable.createControls(), true);
            availableTableLayout.addComponent(layout);
            availableTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                
                /**
                 * Called when a item has been clicked.
                 */
                @SuppressWarnings("PMD")
                public void itemClick(final ItemClickEvent event) {
                }
            });
            availableTable.setErrorHandler(new ErrorHandler() {
                
                /**
                 * Invoked when an error occurs.
                 */
                public void error(final com.vaadin.server.ErrorEvent event) {
                    // empty method
                }
            });
        } catch (PortalException ex) {
                 LOGGER.error(ex);
        } catch (SystemException ex) {
                 LOGGER.error(ex);
        } catch (Exception ex) {
                 LOGGER.error(ex);
        }

    }

    /**
     * Adds the selected table.
     *
     * @return the table
     * @throws Exception the exception
     */
    public void addSelectedTable() {
        selectedTable.markAsDirty();
        loadSelectedTable();
        selectedTable.setPageLength(NumericConstants.SIX);
        selectedTable.setImmediate(true);
        selectedTable.setNullSelectionAllowed(true);
        selectedTable.sinkItemPerPageWithPageLength(false);
        selectedTable.setSelectable(true);
        selectedTable.setMultiSelect(true);
        selectedTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
        selectedTable.setSizeFull();
        selectedTable.sinkItemPerPageWithPageLength(false);
        selectedTable.setFilterBarVisible(true);
        selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedTable.setValidationVisible(false);
        selectedTable.addStyleName(ConstantsUtils.FILTER_BAR);
        selectedTable.setFilterGenerator(new IFPFilterGenerator());
        selectedTable.setWidth("380px");
        selectedTable.setFilterBarVisible(true);
        selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedTable.setFilterGenerator(new IFPFilterGenerator());
        HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(selectedTable.createControls(), true);
        selectedTableLayout.addComponent(layout);
        selectedTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

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
    }

    public void resetItemAdditionTab() throws SystemException, PortalException {
        removeAllCompanyButtonClick(null);
        availableTable.setContainerDataSource(emptyContainer);
        availableTable.setVisibleColumns(IfpUtils.AVAILABLE_ITEM_COL);
        availableTable.setColumnHeaders(IfpUtils.AVAILABLE_ITEM_COL_HEADER);
    }

    private void addResultsTable() {

            viewResultsTable.markAsDirty();
            LazyBeanItemContainer lazyResultsContainer = new LazyBeanItemContainer(TempItemDTO.class, new SelectedViewContainer(sessionDTO), new TempSelectedCriteria());
            viewResultsTable.setContainerDataSource(lazyResultsContainer);
            viewResultsTable.setVisibleColumns(IfpUtils.SELECTED_ITEM_COL);
            viewResultsTable.setColumnHeaders(IfpUtils.SELECTED_ITEM_COL_HEADER);
            viewResultsTable.setPageLength(NumericConstants.ELEVEN);
            viewResultsTable.setImmediate(true);
            viewResultsTable.setSelectable(true);
            viewResultsTable.setSizeFull();
            viewResultsTable.setWidth("99%");
            
            viewResultsTable.setErrorHandler(new ErrorHandler() {
                
                /**
                 * Invoked when an error occurs.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    // empty method
                }
            });

    }

	public CustomePagedFilterTable getAvailableTable() {
		return availableTable;
	}

	public CustomePagedFilterTable getSelectedTable() {
		return selectedTable;
	}
}
