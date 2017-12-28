/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.model.CompanyMaster;
import com.stpl.app.gcm.promotetptocontract.dto.RebatePlanDTO;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.TableHeaderColumnsUtil;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class RebatePlanLookup extends Window {

    private final String indicator;
    private final CustomTextField groupLookup;
    List<String> companySids;
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = org.jboss.logging.Logger.getLogger(TPContractHolderLookUp.class);
    @UiField("resultsTableRPL")
    private ExtFilterTable rebateResultTable;
    @UiField("cancelBtnRPL")
    private Button cancelBtn;
    @UiField("searchBtnRPL")
    private Button searchBtn;
    /**
     * The reset btn.
     */
    @UiField("resetBtnRPL")
    private Button resetBtn;
    /**
     * The select btn.
     */
    @UiField("selectBtnRPL")
    private Button selectBtn;
    /**
     * The result reset btn.
     */
    @UiField("rebatePlanId")
    private TextField rebatePlanId;
    @UiField("rebatePlanNo")
    private TextField rebatePlanNo;
    @UiField("rebatePlanName")
    private TextField rebatePlanName;
    @UiField("rebatePlanStatus")
    private ComboBox rebatePlanStatus;
    @UiField("rebatePlanType")
    private ComboBox rebatePlanType;
    private final BeanItemContainer<RebatePlanDTO> resultContainer = new BeanItemContainer<>(RebatePlanDTO.class);
    private RebatePlanDTO selectedChHolderHierarchy;
    List<CompanyMaster> filteredCompanies;
    PromoteTPLogic logic = new PromoteTPLogic();
    HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    public static final String REBATE_PLAN = "Rebate Plan";

    public List<CompanyMaster> getFilteredCompanies() {
        return filteredCompanies;
    }

    public void setFilteredCompanies(List<CompanyMaster> filteredCompanies) {
        this.filteredCompanies = filteredCompanies;
    }

    public RebatePlanDTO getSelectedChHolderHierarchy() {
        return selectedChHolderHierarchy;
    }

    public void setSelectedChHolderHierarchy(RebatePlanDTO selectedChHolderHierarchy) {
        this.selectedChHolderHierarchy = selectedChHolderHierarchy;
    }

    public RebatePlanLookup(final String indicator, final CustomTextField groupLookup) {
        super("Rebate Plan Lookup");
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
        setWidth(NumericConstants.NINE_FIVE_ZERO, Sizeable.Unit.PIXELS);
        setHeight("750px");
        setContent(Clara.create(getClass().getResourceAsStream("/rebatePlanLookup.xml"), this));
        addContractTable();
        configureFields();
    }

    private ExtFilterTable addContractTable() {
        rebateResultTable.setContainerDataSource(resultContainer);
        rebateResultTable.setVisibleColumns(TableHeaderColumnsUtil.getInstance().rebatePlanVisibleColumn);
        rebateResultTable.setColumnHeaders(TableHeaderColumnsUtil.getInstance().rebatePlanColumnHeader);
        rebateResultTable.setSelectable(true);
        rebateResultTable.setImmediate(true);
        rebateResultTable.setWidth("1110px");
        rebateResultTable.setHeight("330px");
        return rebateResultTable;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        rebateResultTable.setFilterBarVisible(true);
        rebateResultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        rebateResultTable.setStyleName("filtertable");
        rebateResultTable.setImmediate(true);
        rebateResultTable.setSelectable(true);

        rebatePlanStatus.addItem(Constants.SELECT_ONE);
        rebatePlanStatus.addItem(Constants.IndicatorConstants.ACTIVE);
        rebatePlanStatus.addItem(Constants.IndicatorConstants.IN_ACTIVE);
        rebatePlanStatus.setNullSelectionAllowed(true);
        rebatePlanStatus.setNullSelectionItemId(Constants.SELECT_ONE);

        rebatePlanType.addItem(ddlbDefaultValue);
        rebatePlanType.setNullSelectionAllowed(true);
        rebatePlanType.setNullSelectionItemId(ddlbDefaultValue);

        loadRebatePlanType();

        searchBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnSearchLogic();
            }
        });
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
                        rebateResultTable.resetFilters();
                        rebatePlanId.setValue(StringUtils.EMPTY);
                        rebatePlanNo.setValue(StringUtils.EMPTY);
                        rebatePlanName.setValue(StringUtils.EMPTY);
                        rebatePlanStatus.setValue(Constants.SELECT_ONE);
                        rebatePlanType.setValue(ddlbDefaultValue);
                    }
                }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the page to default/previous values?");

            }
        });

    }

    /**
     * Cancel Button Logic
     *
     * @param event
     */
    @UiHandler("cancelBtnRPL")
    public void cancelBtnRPL(Button.ClickEvent event) {
        close();
    }

    protected void btnSearchLogic() {
        LOGGER.debug("btnSearchLogic called");
        try {
            if ((!StringUtils.EMPTY.equals(rebatePlanId.getValue()) || !Constants.NULL.equals(rebatePlanId.getValue()))
                    && (!StringUtils.EMPTY.equals(rebatePlanNo.getValue()) || !Constants.NULL.equals(rebatePlanNo.getValue()))
                    && (!StringUtils.EMPTY.equals(rebatePlanName.getValue()) || !Constants.NULL.equals(rebatePlanName.getValue()))
                    && (!StringUtils.EMPTY.equals(rebatePlanStatus.getValue()) || !Constants.NULL.equals(rebatePlanStatus.getValue()))
                    && (!StringUtils.EMPTY.equals(rebatePlanType.getValue()) || !Constants.NULL.equals(rebatePlanType.getValue()))) {

                resultContainer.removeAllItems();
                List<RebatePlanDTO> resultList = new ArrayList<>();
                if (REBATE_PLAN.equals(indicator)) {
                    resultList = logic.getRebatePlanDetails(rebatePlanId.getValue(), rebatePlanNo.getValue(), rebatePlanName.getValue(),
                            String.valueOf(rebatePlanType.getValue()), String.valueOf(rebatePlanType.getValue()));

                    LOGGER.debug("Rebate Plan Result List Value=" + resultList.size());
                }
                if (!resultList.isEmpty()) {
                    resultContainer.removeAllItems();
                    resultContainer.addAll(resultList);
                    CommonUIUtils.getMessageNotification("Search Completed");
                } else {
                    resultContainer.removeAllItems();
                    if (REBATE_PLAN.equals(indicator)) {
                        AbstractNotificationUtils.getErrorNotification("No Records Found", "There are no Rebate Plan that match the search criteria.");
                    }
                }
            }

        } catch (Exception ex) {
         LOGGER.error(ex);
        }
        LOGGER.debug("btnSearchLogic ends");

    }

    private void loadRebatePlanType() {
        logic.LazyLoadDdlb(rebatePlanType, "getRebatePlanTypeCount", "getRebatePlanType");
    }

    @UiHandler("selectBtnRPL")
    protected void btnLookupSelectLogic() {
        if (rebateResultTable != null && rebateResultTable.getValue() != null) {
            if (REBATE_PLAN.equals(indicator)) {
                setSelectedChHolderHierarchy((RebatePlanDTO) rebateResultTable.getValue());
                groupLookup.setValue(String.valueOf(selectedChHolderHierarchy.getRebatePlanName()));
                groupLookup.setData(String.valueOf(selectedChHolderHierarchy.getRebatePlanId()));
            }
            close();
        } else {
            AbstractNotificationUtils.getErrorNotification("Confirm Selection", "Please select a record.");
        }
    }
}
