/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.model.CompanyMaster;
import com.stpl.app.gcm.promotetptocontract.dto.ContractHolderDTO;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.TableHeaderColumnsUtil;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class TPContractHolderLookUp extends Window {

    private final String indicator;
    private final TextField groupLookup;
    List<String> companySids;
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = org.jboss.logging.Logger.getLogger(TPContractHolderLookUp.class);
    @UiField("resultsTableCHL")
    private ExtFilterTable resultTable;
    @UiField("cancelBtnCHL")
    private Button cancelBtn;
    @UiField("searchBtnCHL")
    private Button searchBtn;
    /**
     * The reset btn.
     */
    @UiField("resetBtnCHL")
    private Button resetBtn;
    /**
     * The select btn.
     */
    @UiField("selectBtnCHL")
    private Button selectBtn;
    /**
     * The result reset btn.
     */
    @UiField("contractHolderId")
    private TextField contractHolderId;
    @UiField("contractHolderNo")
    private TextField contractHolderNo;
    @UiField("contractHolderName")
    private TextField contractHolderName;
    @UiField("contractHolderStatus")
    private ComboBox contractHolderStatus;
    @UiField("contractHolderType")
    private ComboBox contractHolderType;
    private final BeanItemContainer<ContractHolderDTO> resultContainer = new BeanItemContainer<>(ContractHolderDTO.class);
    private ContractHolderDTO selectedChHolderHierarchy;
    List<CompanyMaster> filteredCompanies;
    PromoteTPLogic logic = new PromoteTPLogic();
    HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    public static final String CONTRACT_HOLDER = "Contract Holder";

    public List<CompanyMaster> getFilteredCompanies() {
        return filteredCompanies;
    }

    public void setFilteredCompanies(List<CompanyMaster> filteredCompanies) {
        this.filteredCompanies = filteredCompanies;
    }

    public ContractHolderDTO getSelectedChHolderHierarchy() {
        return selectedChHolderHierarchy;
    }

    public void setSelectedChHolderHierarchy(ContractHolderDTO selectedChHolderHierarchy) {
        this.selectedChHolderHierarchy = selectedChHolderHierarchy;
    }

    public TPContractHolderLookUp(final String indicator, final TextField groupLookup) {
        super("Contract Holder Lookup");
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);

        this.indicator = indicator;
        this.groupLookup = groupLookup;
        init();
    }

    public void init() {
        setClosable(true);
        setModal(true); 
        setContent(Clara.create(getClass().getResourceAsStream("/contractHolderLookUp.xml"), this));
        addContractTable();
        configureFields();
    }

    private ExtFilterTable addContractTable() {
        resultTable.setContainerDataSource(resultContainer);
        resultTable.setVisibleColumns(TableHeaderColumnsUtil.getInstance().chHolderVisibleColumn);
        resultTable.setColumnHeaders(TableHeaderColumnsUtil.getInstance().chHolderColumnHeader);
        resultTable.setSelectable(true);
        resultTable.setImmediate(true);
        resultTable.setHeight("330px");

        return resultTable;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.addStyleName("filterbar");
        resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTable.setImmediate(true);
        resultTable.setSelectable(true);

        contractHolderStatus.addItem(Constants.SELECT_ONE);
        contractHolderStatus.addItem(Constants.IndicatorConstants.ACTIVE);
        contractHolderStatus.addItem(Constants.IndicatorConstants.IN_ACTIVE);
        contractHolderStatus.setNullSelectionAllowed(true);
        contractHolderStatus.setNullSelectionItemId(Constants.SELECT_ONE);

        contractHolderType.addItem(ddlbDefaultValue);
        contractHolderType.setNullSelectionAllowed(true);
        contractHolderType.setNullSelectionItemId(ddlbDefaultValue);

        loadContractHolderType(contractHolderType);
        
        selectBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnLookupSelectLogic();
            }
        });

        resetBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        // do nothing
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
                        resultTable.resetFilters();

                        contractHolderId.setValue(StringUtils.EMPTY);
                        contractHolderNo.setValue(StringUtils.EMPTY);
                        contractHolderName.setValue(StringUtils.EMPTY);
                        contractHolderStatus.setValue(Constants.SELECT_ONE);
                        contractHolderType.setValue(ddlbDefaultValue);
                        loadContractHolderType(contractHolderType);                        
                    }
                }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the page to default/previous values?");

            }
        });
        contractHolderId.focus();
    }

    /**
     * Cancel Button Logic
     *
     * @param event
     */
    @UiHandler("cancelBtnCHL")
    public void cancelBtnCHL(Button.ClickEvent event) {
        close();
    }

    @UiHandler("searchBtnCHL")
    public void searchButtonLogic(Button.ClickEvent event) {
        LOGGER.debug("btnSearchLogic called");
        try {
            if ((!StringUtils.EMPTY.equals(contractHolderId.getValue()) || !Constants.NULL.equals(String.valueOf(contractHolderId.getValue())))
                    && (!StringUtils.EMPTY.equals(contractHolderNo.getValue()) || !Constants.NULL.equals(String.valueOf(contractHolderNo.getValue())))
                    && (!StringUtils.EMPTY.equals(contractHolderName.getValue()) || !Constants.NULL.equals(String.valueOf(contractHolderName.getValue())))
                    && (!StringUtils.EMPTY.equals(String.valueOf(contractHolderStatus.getValue())) || !Constants.NULL.equals(String.valueOf(contractHolderStatus.getValue())))
                    && (!StringUtils.EMPTY.equals(String.valueOf(contractHolderType.getValue())) || !Constants.NULL.equals(String.valueOf(contractHolderType.getValue())))) {

                resultContainer.removeAllItems();
                List<ContractHolderDTO> resultList = new ArrayList<>();
                if (CONTRACT_HOLDER.equals(indicator)) {
                    resultList = logic.getContractHolder(contractHolderId.getValue(), contractHolderNo.getValue(), contractHolderName.getValue(),
                            String.valueOf(contractHolderStatus.getValue()), String.valueOf(contractHolderType.getValue()));

                    LOGGER.debug("Contract Holder Result List Value=" + resultList.size());
                }
                if (!resultList.isEmpty()) {
                    resultContainer.removeAllItems();
                    resultContainer.addAll(resultList);
                    CommonUIUtils.getMessageNotification("Search Completed");
                } else {
                    resultContainer.removeAllItems();
                    if (CONTRACT_HOLDER.equals(indicator)) {
                        AbstractNotificationUtils.getErrorNotification("No Records Found", "There are no Contract Holder that match the search criteria.");
                    }
                }
            }

        } catch (Exception ex) {
           LOGGER.error(ex);
        }
        LOGGER.debug("btnSearchLogic ends");
    }

    private void loadContractHolderType(ComboBox contractHolderType) {
        logic.loadDdlb("getCompanyTypeForContact",contractHolderType);
    }

    @UiHandler("selectBtnCHL")
    protected void btnLookupSelectLogic() {
        if (resultTable != null && resultTable.getValue() != null) {
            if (CONTRACT_HOLDER.equals(indicator)) {
                setSelectedChHolderHierarchy((ContractHolderDTO) resultTable.getValue());
                groupLookup.setValue(String.valueOf(selectedChHolderHierarchy.getContractHolderName()));
                groupLookup.setData(String.valueOf(selectedChHolderHierarchy.getContractHolderId()));
            }
            close();
        } else {
            AbstractNotificationUtils.getErrorNotification("Confirm Selection",
                    "Please select a record.");
        }
    }
}
