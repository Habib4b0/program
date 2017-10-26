/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.gtnforecasting.dto.ContractBrandDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastSalesProjection;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.AlternateLookupSource;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author sibi
 */
public class ContractBrandLookup extends Window {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ContractBrandLookup.class);
    @UiField("closeBtn")
    public Button closeBtn;

    @UiField("contractResultsTable")
    public ExtFilterTable contractResultsTable;

    @UiField("brandResultsTable")
    public ExtFilterTable brandResultsTable;

    @UiField("searchContract")
    public Button searchContractBtn;

    @UiField("resetContract")
    public Button resetContractBtn;

    @UiField("searchBrand")
    public Button searchBrandBtn;

    @UiField("resetBrand")
    public Button resetBrandBtn;

    @UiField("importBtn")
    public Button importBtn;

    @UiField("customerDdlb")
    public ComboBox customerDdlb;

    @UiField("contractNo")
    public TextField contractNo;

    @UiField("contractName")
    public TextField contractName;

    @UiField("brandSearch")
    public TextField brandName;

    @UiField("customerId")
    public TextField customerId;

    @UiField("contractHolderName")
    public TextField contractHolder;

    @UiField("customerDdlbLabel")
    public Label customerDdlbLabel;

    @UiField("contractNoLb")
    public Label contractNoLb;

    @UiField("contractNameLb")
    public Label contractNameLb;

    @UiField("contractHolderNameLb")
    public Label contractHolderNameLb;

    @UiField("customerIdLb")
    public Label customerIdLb;

    /**
     * Container for contractResults table.
     */
    private BeanItemContainer<ContractBrandDTO> contractContainer;

    /**
     * Container for contractResults table.
     */
    private BeanItemContainer<ContractBrandDTO> brandContainer;

    /**
     * Container for contractResults table.
     */
    private BeanItemContainer<ContractBrandDTO> nmContractContainer;

    /**
     * Container for contractResults table.
     */

    SalesLogic logic = new SalesLogic();

    ForecastSalesProjection forecastSalesProjection;

    private CustomFieldGroup searchBinder;

    private final ErrorLabel errorMsg = new ErrorLabel();    

    private ContractBrandDTO contractBrandDTO = new ContractBrandDTO();    

    final private String lookupType;

    private final SessionDTO sessionDTO;

    private String hierarchyNo = StringUtils.EMPTY;
    
    private SessionDTO session;
    String screenName;    

    /**
     * Instantiates a new contract brand lookup.
     *
     * @param windowName
     * @param sessionDTO
     * @param type
     * @param hierarchyNo
     */
    public ContractBrandLookup(final String windowName, final SessionDTO sessionDTO, final String type, final String hierarchyNo, String screenName) {
        super(StringUtils.EMPTY);
        LOGGER.debug("ContractBrandLookup Constructor initiated ");
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        center();
        setModal(true);
        setWidth(NumericConstants.FLOAT_SEVENTY_FIVE, Sizeable.Unit.PERCENTAGE);
        this.lookupType = type;
        this.sessionDTO = sessionDTO;
        this.hierarchyNo = hierarchyNo;
        this.screenName = screenName;
        setContent(Clara.create(getClass().getResourceAsStream("/mandated/ContractBrandLookup.xml"), this));
        getBinder();
        configureFields();
        configureBasedOnScreenName();
        LOGGER.debug("PmpyCalculator Constructor ends ");

    }

    /**
     * Getting information From Binder for NM Fields
     *
     * @return searchBinder
     */
    private CustomFieldGroup getBinder() {
        LOGGER.debug("Entering getBinder");
        final ContractBrandDTO bean = new ContractBrandDTO();

        searchBinder = new CustomFieldGroup(new BeanItem<ContractBrandDTO>(bean));
        searchBinder.setBuffered(true);
        searchBinder.bindMemberFields(this);
        searchBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("Ends getBinder");
        return searchBinder;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        LOGGER.debug("Entering Configure Fields");
        try {
            if (lookupType.equals(Constant.BRAND)) {
                brandName.focus();
            }
            contractContainer = new BeanItemContainer<ContractBrandDTO>(ContractBrandDTO.class);
            brandContainer = new BeanItemContainer<ContractBrandDTO>(ContractBrandDTO.class);
            nmContractContainer = new BeanItemContainer<ContractBrandDTO>(ContractBrandDTO.class);
            brandResultsTable.setSelectable(true);
            brandResultsTable.setFilterBarVisible(true);
            brandResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            brandResultsTable.setStyleName(Constant.FILTER_TABLE);
            brandResultsTable.setContainerDataSource(brandContainer);
            brandResultsTable.setVisibleColumns(CommonUtils.HISTORY_LOOKUP_BRAND_COLUMNS);
            brandResultsTable.setColumnHeaders(CommonUtils.HISTORY_LOOKUP_BRAND_HEADERS);
            contractResultsTable.setPageLength(NumericConstants.FIVE);
            brandResultsTable.setPageLength(NumericConstants.FIVE);
            contractResultsTable.setSizeFull();
            brandResultsTable.setSizeFull();

            if (Constant.CONTRACT_SMALL.equalsIgnoreCase(lookupType)) {
                LOGGER.debug("Entering the Configuration For Contract");
                brandName.setEnabled(false);
                searchBrandBtn.setEnabled(false);
                resetBrandBtn.setEnabled(false);
                brandResultsTable.setEnabled(false);
                LOGGER.debug("Ending the Configuration For Contract");
            } else {
                LOGGER.debug("Entering the Configuration For Contract");
                searchContractBtn.setEnabled(false);
                resetContractBtn.setEnabled(false);
                customerDdlb.setEnabled(false);
                contractName.setEnabled(false);
                contractNo.setEnabled(false);
                contractResultsTable.setEnabled(false);
                LOGGER.debug("Ending the Configuration For Contract");
            }

            loadCustomerDDLB(customerDdlb);

            searchContractBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {

                    try {
                        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                            mandatedContractSearchLogic();
                        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                            nonmandatedContractSearchLogic();
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }

                }
            });

            searchBrandBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    try {
                        brandSearchLogic();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });

            importBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    try {
                        importButtonLogic();
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            });

            brandResultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

                public void itemClick(ItemClickEvent event) {
                    contractBrandDTO = (ContractBrandDTO) getBeanFromId(event.getItem());
                }
            });

            contractResultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

                public void itemClick(ItemClickEvent event) {
                    contractBrandDTO = (ContractBrandDTO) getBeanFromId(event.getItem());
                }
            });
            customerId.setValue(StringUtils.EMPTY);
            contractHolder.setValue(StringUtils.EMPTY);
            contractName.setValue(StringUtils.EMPTY);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } 
        LOGGER.debug("Entering Configure Fields");
    }

    /**
     * Close Button Listener
     *
     * @param event
     */
    @UiHandler("closeBtn")
    public void editCustomertree(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
               
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                close();
            }
        }.getConfirmationMessage("Confirm Close", "Closing the popup will not submit an alternate Contract and/or Brand back to the Sales Projection screen.  Are you sure you want to continue?");

    }

    /**
     * Customize Customer search logic.
     */
    protected void nonmandatedContractSearchLogic() {
        try {
            searchBinder.commit();
        } catch (FieldGroup.CommitException ex) {
            java.util.logging.Logger.getLogger(AlternateHistoryLookup.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (StringUtils.isEmpty(getContractHolder().getValue()) && StringUtils.isEmpty(getCustomerId().getValue())) {
            com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils.getErrorNotification("No Search Value Entered", "There is no search value entered. Please enter a search value and try again.");

        } else {
            try {

                nmContractContainer.removeAllItems();
                final AlternateLookupSource alternate = logic.searchAlternateCustomerAndBrand(searchBinder, Constant.TP, false);
                final List<ContractBrandDTO> tpResult = alternate.getContractcustomersList();
                if (tpResult.isEmpty()) {
                    com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils.getInfoNotification("No Results", "There were no results found that match the entered search criteria. \nPlease try again.");

                } else {
                    nmContractContainer.addAll(tpResult);
                    CommonUIUtils.getMessageNotification("Search Completed");
                }

            } catch (SystemException sysException) {

                LOGGER.error(sysException);

            } catch (Exception exception) {

                LOGGER.error(exception);

            }

        }
    }

    /**
     * Search Logic For Mandated Forecasting
     *
     * @throws Exception
     */
    public void mandatedContractSearchLogic()  {
        try{
        LOGGER.debug("Entering the Mandated Contract Search Logic");
        contractBrandDTO.setCustomer(String.valueOf(customerDdlb.getValue()));
        contractBrandDTO.setContractNumber(contractNo.getValue());
        contractBrandDTO.setContractName(contractName.getValue());

        if (StringUtils.isEmpty(contractName.getValue()) && StringUtils.isEmpty(contractNo.getValue()) && StringUtils.isEmpty(String.valueOf(customerDdlb.getValue()))) {

            AbstractNotificationUtils.getErrorNotification("No Search Value Entered", "There is no search value entered. Please enter a search value and try again.");

        } else if (StringUtils.isEmpty(contractName.getValue()) && StringUtils.isEmpty(contractNo.getValue())) {

            AbstractNotificationUtils.getErrorNotification("No Search Value Entered", "There is no search value entered. Please enter a search value and try again.");

        } else {

            if (contractContainer.size() != 0) {
                contractContainer.removeAllItems();
            }
            final List alternate = logic.searchAlternateContract(contractBrandDTO);
            if (alternate.isEmpty()) {
                AbstractNotificationUtils.getInfoNotification("No Results", "There were no results found that match the entered search criteria. \nPlease try again.");
            } else {

                contractContainer.addAll(alternate);
                CommonUIUtils.getMessageNotification("Search Completed");
            }
        }
        LOGGER.debug("Ending the Mandated Contract Search Logic");
        }catch(PortalException e){
        LOGGER.error(e);
        }catch(SystemException e){
        LOGGER.error(e);
        }
    }

    /**
     * Loading Customer DDLB when the Alternate History is launched from Mandated.
     *
     * @param customerDDLB
     * @throws SystemException
     * @throws Exception
     */
    public void loadCustomerDDLB(ComboBox customerDDLB) throws SystemException {
        try{
        customerDDLB.addItem(Constant.SELECT_ONE);
        customerDDLB.setNullSelectionAllowed(true);
        customerDDLB.setNullSelectionItemId(Constant.SELECT_ONE);
        List<Object[]> list = logic.loadAlternateCustomer(sessionDTO.getMarketType());
        for (Object[] obj : list) {
            customerDDLB.addItem(obj[0]);
            customerDDLB.setItemCaption(obj[0], String.valueOf(obj[1]));
        }
        }catch(Exception e){
        LOGGER.error(e);
        }
    }

    /**
     * Search Logic for Brand
     *
     * @throws Exception
     */
    public void brandSearchLogic() throws PortalException, SystemException {
        LOGGER.debug("Entering the Brand Search Logic");
        String tempbrandName = brandName.getValue();
        if ("*".equals(tempbrandName.trim())) {
            tempbrandName = Constant.PERCENT;
        }
        if (StringUtils.isEmpty(tempbrandName)) {
            AbstractNotificationUtils.getErrorNotification("No Search Value Entered", "There is no search value entered. Please enter a search value and try again.");
        } else {
            List<ContractBrandDTO> brandResult = logic.loadAlternateBrand(tempbrandName);

            if (brandResult.isEmpty()) {

                AbstractNotificationUtils.getInfoNotification("No Results", "There were no results found that match the entered search criteria. \nPlease try again.");

            } else {
                if (brandContainer.size() != 0) {
                    brandContainer.removeAllItems();
                }
                brandContainer.addAll(brandResult);
                CommonUIUtils.getMessageNotification("Search Completed");
            }
        }
        LOGGER.debug("Ending the Brand Search Logic");
    }

    /**
     * Import Button Logic
     */
    public void importButtonLogic() throws SQLException, SystemException {
        LOGGER.debug("Entering Import Logic Button Listener");
        ContractBrandDTO item;
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            if (Constant.CONTRACT.equalsIgnoreCase(lookupType)) {
                item = (ContractBrandDTO) contractResultsTable.getValue();
                if (item != null) {
                    contractBrandDTO.setBrandSid(null);
                    contractBrandDTO.setContractHierarchyNo(hierarchyNo);
                    contractBrandDTO.setBrandHierarchyNo(null);
                } else {
                    AbstractNotificationUtils.getErrorNotification("No result selected", "There are no selected results. Please select a record and try again.");
                }

            } else {
                item = (ContractBrandDTO) brandResultsTable.getValue();
                if (item != null) {
                    contractBrandDTO.setContractSid(0);
                    contractBrandDTO.setBrandHierarchyNo(hierarchyNo);
                    contractBrandDTO.setContractHierarchyNo(null);
                } else {
                    AbstractNotificationUtils.getErrorNotification("No result selected", "There are no selected results. Please select a record and try again.");
                }

            }
            if (item != null) {
                logic.callAlternateHistoryProcedure(sessionDTO, contractBrandDTO);                
                close();
            }
            contractBrandDTO = item;
        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            Object selected = null;
            if (lookupType.equals(Constant.CONTRACT)) {
                selected = contractResultsTable.getValue();
            }
            if (selected != null) {
                ContractBrandDTO contrtactDto = null;
                Object[] inputs = new Object[NumericConstants.SEVEN];
                inputs[0] = 0;
                inputs[1] = 0;
                inputs[NumericConstants.TWO] = 0;
                inputs[NumericConstants.THREE] = 0;
                if (contractResultsTable.getValue() != null) {
                    contrtactDto = getBeanFromId(contractResultsTable.getValue());
                }
                if (contrtactDto != null) {
                    inputs[NumericConstants.TWO] = contrtactDto.getContractSid();

                }
                inputs[NumericConstants.FOUR] = session.getProjectionId();
                inputs[NumericConstants.FIVE] = session.getSessionId();
                inputs[NumericConstants.SIX] = session.getUserId();
                logic.callAlternateHistoryProcedure(inputs);
                close();
                contractBrandDTO = contrtactDto;
            } else {
                com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils.getErrorNotification("Error", "There are no selected results.\n Please select a record and try again.");
            }
        }

    }

    /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public ContractBrandDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof ContractBrandDTO) {
            targetItem = new BeanItem<ContractBrandDTO>(
                    (ContractBrandDTO) obj);
        }

        return (ContractBrandDTO) targetItem.getBean();
    }
 

    /**
     * Configuration Of Fields Based on the Screen Name
     */
    private void configureBasedOnScreenName() {
        LOGGER.debug("Entering Configure Based On ScreenName");
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            customerDdlb.setVisible(false);
            contractNo.setVisible(false);
            contractName.setVisible(false);
            customerDdlbLabel.setVisible(false);
            contractNoLb.setVisible(false);
            contractNameLb.setVisible(false);
            contractResultsTable.setSelectable(true);
            contractResultsTable.setFilterBarVisible(true);
            contractResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            contractResultsTable.setStyleName(Constant.FILTER_TABLE);
            contractResultsTable.setContainerDataSource(nmContractContainer);
            contractResultsTable.setVisibleColumns(CommonUtils.HISTORY_LOOKUP_CONTRACT_COLUMNS_NONMANDATED);
            contractResultsTable.setColumnHeaders(CommonUtils.HISTORY_LOOKUP_CONTRACT_HEADERS_NONMANDATED);
        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            contractHolder.setVisible(false);
            customerId.setVisible(false);
            contractHolderNameLb.setVisible(false);
            customerIdLb.setVisible(false);
            contractResultsTable.setSelectable(true);
            contractResultsTable.setFilterBarVisible(true);
            contractResultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            contractResultsTable.setStyleName(Constant.FILTER_TABLE);
            contractResultsTable.setContainerDataSource(contractContainer);
            contractResultsTable.setVisibleColumns(CommonUtils.HISTORY_LOOKUP_CONTRACT_COLUMNS_MANDATED);
            contractResultsTable.setColumnHeaders(CommonUtils.HISTORY_LOOKUP_CONTRACT_HEADERS_MANDATED);
        }
        LOGGER.debug("Ending Configure Based On ScreenName");
    }

    public TextField getCustomerId() {
        return customerId;
    }

    public void setCustomerId(TextField customerId) {
        this.customerId = customerId;
    }

    public TextField getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(TextField contractHolder) {
        this.contractHolder = contractHolder;
    }

    public ContractBrandDTO getContractBrandDTO() {
        return contractBrandDTO;
    }

    public void setContractBrandDTO(ContractBrandDTO contractBrandDTO) {
        this.contractBrandDTO = contractBrandDTO;
    }    
    
}
