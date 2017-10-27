
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dto.FixedDollarGroupDTO;
import com.stpl.app.accountclose.logic.FixedDollarCalculationLogic;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.accountclose.utils.ValidationUtil;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ItemMaster;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
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
public class FixedDollarProductGroupLookUp extends Window {

    private String indicator;
    private TextField groupLookup;
    private String sidQuery;
    private String groupSid = StringUtils.EMPTY;
    List<String> companySids;
    private static final long serialVersionUID = 1L;
    CommonUtil commonutil=CommonUtil.getInstance();
    /**
     * The customer group name.
     */
    @UiField("productGroupName")
    private TextField productGroupName;
    /**
     * The customer group.
     */
    @UiField("productGroup")
    private TextField productGroupNo;
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
    List<String> itemSids;
    List<ItemMaster> filteredItems;
    List<CompanyMaster> filteredCompanies;
    private FixedDollarGroupDTO selectedProdHierarchy;
    private static final Logger LOGGER = org.jboss.logging.Logger.getLogger(FixedDollarProductGroupLookUp.class);
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
    public FixedDollarProductGroupLookUp(final String indicator, final String windowName,
            final TextField groupLookup, final String sidQuery, final String groupSid) {
        super("Product Group Lookup");
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
        setContent(Clara.create(getClass().getResourceAsStream("/fixedDollarProductGroupLookUp.xml"), this));
        configureFields();
    }

    

    /**
     * Configure fields.
     */
    private void configureFields() {
        productGroupName.addValidator(new StringLengthValidator(ValidationUtil.getMessage("maxlengthvalidationproductgroupname"), 0, 100, true));
        productGroupNo.addValidator(new StringLengthValidator(ValidationUtil.getMessage("maxlengthvalidationproductgroupno"), 0, 100, true));
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
        resultTable.setVisibleColumns(HeaderUtils.PROD_VISIBLE_COLUMN);
        resultTable.setColumnHeaders(HeaderUtils.PROD_COLUMN_HEADER);
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
                        productGroupName.setValue(StringUtils.EMPTY);
                        productGroupNo.setValue(StringUtils.EMPTY);
                    }
                }.getConfirmationMessage(commonutil.getHeaderMessage(), commonutil.getResetMessage());

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
                }.getConfirmationMessage(commonutil.getHeaderMessage(), commonutil.getResetMessage());
            }
        });

    }

    @UiHandler("cancelBtnCGL")
    public void cancelBtnPGL(Button.ClickEvent event) {
      
        MessageBox.showPlain(Icon.QUESTION,commonutil.getcancelBtnCGLHeader(),commonutil.getcancelBtnCGLMessage(), new MessageBoxListener() {
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
        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        try {

            if ((StringUtils.EMPTY.equals(productGroupName.getValue().trim()) || "null".equals(productGroupName.getValue().trim()))
                    && (StringUtils.EMPTY.equals(productGroupNo.getValue().trim()) || "null".equals(productGroupNo.getValue().trim()))) {

                resultContainer.removeAllItems();

                if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
                    AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1010"), CommonUtil.getMessage("FDA_MSG_1011"));
                } else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
                    AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1010"), CommonUtil.getMessage("FDA_MSG_1012"));
                }
            } else {
                List<FixedDollarGroupDTO> resultList = new ArrayList<FixedDollarGroupDTO>();
                if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
                    if (!StringUtils.EMPTY.equals(sidQuery)) {
                        getCompanies(sidQuery);
                    }
                    resultList = logic.getCustomerGroup(productGroupName.getValue(), productGroupNo.getValue(), companySids);
                } else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
                    if (!StringUtils.EMPTY.equals(sidQuery)) {
                        getItems(sidQuery);
                    }
                    resultList = logic.getProductGroup(productGroupName.getValue().trim(), productGroupNo.getValue().trim(), itemSids);
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
                        AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1010"), CommonUtil.getMessage("FDA_MSG_1012"));
                    } else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
                        AbstractNotificationUtils.getErrorNotification(CommonUtil.getMessage("FDA_MSG_1010"), CommonUtil.getMessage("FDA_MSG_1013"));
                    }
                }
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Method to get Companies
     *
     * @param query
     * @throws Exception
     */
    private void getCompanies(final String query) throws Exception {
        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        companySids = logic.executeQuery(query);
    }

    /**
     * Method to get Items
     *
     * @param query
     * @throws Exception
     */
    private void getItems(final String query) throws Exception {
        FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
        itemSids = logic.executeQuery(query);
    }

    /**
     * Select Button Logic
     *
     */
    protected void btnLookupSelectLogic() {
        if (resultTable != null && resultTable.getValue() != null) {
            FixedDollarCalculationLogic logic = new FixedDollarCalculationLogic();
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
            } else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
                setSelectedProdHierarchy((FixedDollarGroupDTO) resultTable.getValue());
                try {
                    if (itemSids != null) {
                        List<String> finalItemSids = new ArrayList<String>(itemSids);
                        itemSidsFromDetails = logic.getItemGroupDetails(Integer.parseInt(selectedProdHierarchy.getProductGroupSid()));
                        finalItemSids.retainAll(itemSidsFromDetails);
                        setFilteredItems(logic.getItemsFromSids(finalItemSids));
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                groupLookup.setValue(String.valueOf(selectedProdHierarchy.getProductGroupName()));
                groupSid = String.valueOf(selectedProdHierarchy.getProductGroupSid());
                setGroupSid(groupSid);
            }
            close();

        } else {
            MessageBox.showPlain(Icon.QUESTION, commonutil.getNotificationMessage(),commonutil.getSelectBtnMessage(), new MessageBoxListener() {
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

    public TextField getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(TextField productGroupName) {
        this.productGroupName = productGroupName;
    }

    public TextField getProductGroupNo() {
        return productGroupNo;
    }

    public void setProductGroupNo(TextField productGroupNo) {
        this.productGroupNo = productGroupNo;
    }

    public BeanItemContainer<FixedDollarGroupDTO> getResultContainer() {
        return resultContainer;
    }

    public void setResultContainer(BeanItemContainer<FixedDollarGroupDTO> resultContainer) {
        this.resultContainer = resultContainer;
    }

    public List<String> getItemSids() {
        return itemSids;
    }

    public void setItemSids(List<String> itemSids) {
        this.itemSids = itemSids;
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

    public FixedDollarGroupDTO getSelectedProdHierarchy() {
        return selectedProdHierarchy;
    }

    public void setSelectedProdHierarchy(FixedDollarGroupDTO selectedProdHierarchy) {
        this.selectedProdHierarchy = selectedProdHierarchy;
    }

    public List<ItemMaster> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<ItemMaster> filteredItems) {
        this.filteredItems = filteredItems;
    }

    public String getGroupSid() {
        return groupSid;
    }

    public void setGroupSid(String groupSid) {
        this.groupSid = groupSid;
    }

}
