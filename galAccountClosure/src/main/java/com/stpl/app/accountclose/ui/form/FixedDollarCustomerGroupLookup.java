/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dto.FixedDollarGroupDTO;
import com.stpl.app.accountclose.logic.BaseRateCalculationLogic;
import com.stpl.app.accountclose.logic.FixedDollarCalculationLogic;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import com.stpl.app.accountclose.utils.ErrorCodeUtil;
import com.stpl.app.accountclose.utils.ErrorCodes;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.accountclose.utils.ValidationUtil;
import com.stpl.app.model.CompanyMaster;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class FixedDollarCustomerGroupLookup extends Window {

    private String indicator;
    private TextField groupLookup;
    private String sidQuery;
    private String groupSid = StringUtils.EMPTY;
    List<String> companySids;
    private static final long serialVersionUID = 1L;
    /**
     * The customer group name.
     */
    @UiField("customerGroupName")
    private TextField customerGroupName;
    /**
     * The customer group.
     */
    @UiField("customerGroup")
    private TextField customerGroupNo;
    /**
     * The search btn.
     */
    @UiField("searchBtnCGL")
    private Button searchBtn;
    /**
     * The reset btn.
     */
    @UiField("resetBtnCGL")
    private Button resetBtn;
    /**
     * The select btn.
     */
    @UiField("selectBtnCGL")
    private Button selectBtn;
    /**
     * The cancel btn.
     */
    @UiField("cancelBtnCGL")
    private Button cancelBtn;
    /**
     * The result reset btn.
     */
    @UiField("resultResetBtnCGL")
    private Button resultResetBtn;
    @UiField("tblpanel")
    private VerticalLayout tblpanel;
    /**
     * The result container.
     */
    private BeanItemContainer<FixedDollarGroupDTO> resultContainer = new BeanItemContainer<FixedDollarGroupDTO>(FixedDollarGroupDTO.class);
    private FixedDollarGroupDTO selectedCustHierarchy;
    List<CompanyMaster> filteredCompanies;
    private static final Logger LOGGER = org.jboss.logging.Logger.getLogger(CustomerGroupLookUp.class);
    private ExtPagedFilterTable resultTable = new ExtPagedFilterTable("Results");
    TextField pageTextField;
    ComboBox pageLength;

    /**
     *
     * @param indicator
     * @param windowName
     * @param groupLookup
     * @param sidQuery
     */
    public FixedDollarCustomerGroupLookup(final String indicator, final String windowName,
            final TextField groupLookup, final String sidQuery, final String groupSid) {
        super("Customer Group Lookup");
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");

        this.indicator = indicator;
        this.groupLookup = groupLookup;
        this.sidQuery = sidQuery;
        init();
    }

    public void init() {
        setClosable(true);
        setModal(true);
        setWidth(60f, Sizeable.Unit.PERCENTAGE);
        setHeight(40f, Sizeable.Unit.PERCENTAGE);
        setContent(Clara.create(getClass().getResourceAsStream("/fixedDollarCustomerGroupLookup.xml"), this));
        configureFields();
    }

    private ExtFilterTable addCustomerTable() {
        resultTable.setContainerDataSource(resultContainer);
        resultTable.setVisibleColumns(HeaderUtils.CUS_VISIBLE_COLUMN);
        resultTable.setColumnHeaders(HeaderUtils.CUS_COLUMN_HEADER);
        resultTable.setSelectable(true);
        resultTable.setImmediate(true);

        return resultTable;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        tblpanel.addComponent(resultTable);
        HorizontalLayout layout = resultTable.createControls(resultTable);
        pageTextField = (TextField) ((HorizontalLayout) layout.getComponent(1)).getComponent(3);
        pageLength = (ComboBox) ((HorizontalLayout) layout.getComponent(0)).getComponent(1);
        layout.setStyleName("responsivePagedTable");
        tblpanel.addComponent(layout);
        resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTable.setWidth("100%");
        resultTable.setHeight("100%");
        resultTable.setFilterBarVisible(true);
        resultTable.setImmediate(true);
        resultTable.setPageLength(5);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.addStyleName("filtertable");
        resultTable.addStyleName("table-header-normal");
        resultTable.setSelectable(true);
        resultTable.setMultiSelect(false);
        resultTable.setEditableSink(Boolean.TRUE);
        resultTable.sinkItemPerPageWithPageLength(Boolean.FALSE);
        resultTable.setContainerDataSource(resultContainer);
        resultTable.setVisibleColumns(HeaderUtils.CUS_VISIBLE_COLUMN);
        resultTable.setColumnHeaders(HeaderUtils.CUS_COLUMN_HEADER);
          customerGroupName.setData("maxlengthvalidationhundred,maxlengthvalidationcustomergroupname,null,null");
          customerGroupName.addValidator(new StringLengthValidator(ValidationUtil.getMessage("maxlengthvalidationcustomergroupname"), 0, 100, true));
          customerGroupNo.addValidator(new StringLengthValidator(ValidationUtil.getMessage("maxlengthvalidationcustomergroupno"), 0, 100, true));
          searchBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                btnSearchLogic();
            }
        });
        selectBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                btnLookupSelectLogic();
            }
        });

        resetBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    public void noMethod() {
                      
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
                        customerGroupName.setValue(StringUtils.EMPTY);
                        customerGroupNo.setValue(StringUtils.EMPTY);
                    }
                }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset?");

            }
        });
        resultResetBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    public void noMethod() {
                        
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
                        resultContainer.removeAllItems();
                    }
                }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset?");
            }
        });

    }

    /**
     * Cancel Button Logic
     *
     * @param event
     */
    @UiHandler("cancelBtnCGL")
    public void cancelBtnCGL(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, "Confirmation", " Are you sure you want to cancel? ", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("OK")) {
                    try {
                        close();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        }, ButtonId.OK, ButtonId.CANCEL);
    }

    protected void btnSearchLogic() {
        LOGGER.info("btnSearchLogic called");

        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        try {

            if ((StringUtils.EMPTY.equals(customerGroupName.getValue()) || "null".equals(customerGroupName.getValue()))
                    && (StringUtils.EMPTY.equals(customerGroupNo.getValue()) || "null".equals(customerGroupNo.getValue()))) {

                resultContainer.removeAllItems();

                if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
                    AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1010"), CommonUtil.getMessage("FDA_MSG_1014"));
                } else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
                    AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1010"),  CommonUtil.getMessage("FDA_MSG_1013"));
                }
            } else {
                List<FixedDollarGroupDTO> resultList = new ArrayList<FixedDollarGroupDTO>();
                if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
                    if (!StringUtils.EMPTY.equals(sidQuery)) {
                        getCompanies(sidQuery);
                    }
                    resultList = logic.getCustomerGroup(customerGroupName.getValue(), customerGroupNo.getValue(), companySids);

                    LOGGER.info("Customer Group Result List Value=" + resultList.size());
                }
                if (!resultList.isEmpty()) {
                    resultContainer.removeAllItems();
                    resultContainer.addAll(resultList);
                    pageLength.setValue(10);
                    pageLength.setValue(5);
                    CommonUIUtils.getMessageNotification("Search Completed");
                } else {
                    resultContainer.removeAllItems();
                    if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
                        AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1010"), CommonUtil.getMessage("FDA_MSG_1011"));
                    } else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
                        AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1010"), CommonUtil.getMessage("FDA_MSG_1013"));
                    }
                }
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("btnSearchLogic ends");
    }

    /**
     * Get Companies Logic
     *
     * @param query
     * @throws Exception
     */
    private void getCompanies(final String query) throws Exception {
        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        companySids = logic.executeQuery(query);
    }

    /**
     * Select button logic.
     *
     */
    @UiHandler("selectBtnCGL")
    protected void btnLookupSelectLogic() {
        if (resultTable != null && resultTable.getValue() != null) {
            BaseRateCalculationLogic logic = new BaseRateCalculationLogic();
            List<String> itemSidsFromDetails;
            if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
                setSelectedCustHierarchy((FixedDollarGroupDTO) resultTable.getValue());

                try {
                    if (companySids != null) {
                        List<String> finalCompanySids = new ArrayList<String>(companySids);
                        itemSidsFromDetails = logic.getCustomerGroupDetails(Integer.parseInt(selectedCustHierarchy.getCustomerGroupSid()));
                        finalCompanySids.retainAll(itemSidsFromDetails);
                        setFilteredCompanies(logic.getCompanyFromSids(finalCompanySids));
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                groupLookup.setValue(String.valueOf(selectedCustHierarchy.getCustomerGroupName()));
                groupSid = String.valueOf(selectedCustHierarchy.getCustomerGroupSid());
                setGroupSid(groupSid);
            }
            close();

        } else {
            MessageBox.showPlain(Icon.QUESTION, "Notification", " Please select a Customer/Group from the Results section.", new MessageBoxListener() {
                public void buttonClicked(ButtonId buttonId) {
                    if (buttonId.name().equals("OK")) {
                        try {
                            close();
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }
            }, ButtonId.OK, ButtonId.CANCEL);
        }

    }

    public TextField getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(TextField customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public TextField getCustomerGroupNo() {
        return customerGroupNo;
    }

    public void setCustomerGroupNo(TextField customerGroupNo) {
        this.customerGroupNo = customerGroupNo;
    }

    public FixedDollarGroupDTO getSelectedCustHierarchy() {
        return selectedCustHierarchy;
    }

    public void setSelectedCustHierarchy(FixedDollarGroupDTO selectedCustHierarchy) {
        this.selectedCustHierarchy = selectedCustHierarchy;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public TextField getGroupLookup() {
        return groupLookup;
    }

    public void setGroupLookup(TextField groupLookup) {
        this.groupLookup = groupLookup;
    }

    public String getSidQuery() {
        return sidQuery;
    }

    public void setSidQuery(String sidQuery) {
        this.sidQuery = sidQuery;
    }

    public List<String> getCompanySids() {
        return companySids;
    }

    public void setCompanySids(List<String> companySids) {
        this.companySids = companySids;
    }

    public List<CompanyMaster> getFilteredCompanies() {
        return filteredCompanies;
    }

    public void setFilteredCompanies(List<CompanyMaster> filteredCompanies) {
        this.filteredCompanies = filteredCompanies;
    }

    public String getGroupSid() {
        return groupSid;
    }

    public void setGroupSid(String groupSid) {
        this.groupSid = groupSid;
    }
    
            }
