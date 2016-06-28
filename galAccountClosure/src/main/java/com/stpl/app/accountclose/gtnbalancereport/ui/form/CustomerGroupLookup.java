/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.stpl.app.accountclose.dto.GroupDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.CustomerLookupResultTableLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.ProductLookupResultTable;
import com.stpl.app.accountclose.logic.BaseRateCalculationLogic;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.accountclose.utils.ResponsiveUtils;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanu
 */
public class CustomerGroupLookup extends Window {

    private String indicator;
    private TextField groupLookup;
    private String sidQuery;
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
    public Button searchBtn;
    /**
     * The reset btn.
     */
    @UiField("resetBtnCGL")
    private Button resetBtn;
    /**
     * The result table.
     */
    @UiField("resultsLayout")
    private VerticalLayout resultsLayout;
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
    /**
     * The result container.
     */
    private BeanItemContainer<GroupDTO> resultContainer = new BeanItemContainer<GroupDTO>(GroupDTO.class);
    CustomerLookupResultTableLogic resultTableLogic = new CustomerLookupResultTableLogic();
    public ExtPagedTable resultTable = new ExtPagedTable(resultTableLogic);
    GroupDTO groupDTO = new GroupDTO();
     private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");
    public static final Logger LOGGER = Logger.getLogger(CustomerGroupLookup.class);

    /**
     *
     * @param indicator
     * @param windowName
     * @param groupLookup
     * @param sidQuery
     */
    public CustomerGroupLookup(final String indicator, final String windowName, final TextField groupLookup, final String sidQuery) {
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
        setContent(Clara.create(getClass().getResourceAsStream("/customerGroupLookUp.xml"), this));

        configureFields();
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        addResultsTable();

        resultTableLogic.setContainerDataSource(resultContainer);
        resultTableLogic.setPageLength(5);
        resultTableLogic.sinkItemPerPageWithPageLength(false);

        resultTable.setVisibleColumns(HeaderUtils.CUSTOMER_LOOKUP_VISIBLE_COLUMN);
        resultTable.setColumnHeaders(HeaderUtils.CUSTOMER_LOOKUP_COLUMN_HEADER);
        resultTable.setImmediate(true);
        resultTable.setSelectable(true);
        resultTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }
        });
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        searchBtn.setImmediate(true);
        selectBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                if (resultTable.getValue() == null) {
                    AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_031"),
                           confirmationMessage.getString("BR_MSG_ID_032"));
                } else {
                    final GroupDTO itemId = (GroupDTO) resultTable.getValue();
                    setGroupDTO(itemId);
                    close();
                }
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
                }.getConfirmationMessage(confirmationMessage.getString("MSG_ID_001"),confirmationMessage.getString("BR_MSG_ID_030"));


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
                }.getConfirmationMessage(confirmationMessage.getString("MSG_ID_001"), confirmationMessage.getString("BR_MSG_ID_030"));
            }
        });

    }

    @UiHandler("searchBtnCGL")
    public void btnSearchLogic(Button.ClickEvent event) {
        try {
            if ((StringUtils.isNotBlank((customerGroupName.getValue().trim()))) || ((StringUtils.isNotBlank((customerGroupNo.getValue().trim()))))) {

                resultContainer.removeAllItems();
                GroupDTO groupDTO = new GroupDTO();
                groupDTO.setCustomerGroupName(customerGroupName.getValue().trim());
                groupDTO.setCustomerGroupNo(customerGroupNo.getValue().trim());
                groupDTO.setIndicator(indicator);
                if (!resultTableLogic.loadSetData(groupDTO, false)) {
                    AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_028"), confirmationMessage.getString("BR_MSG_ID_029"));
                } else {
                    Notification.show("Search Completed");
                }
            } else {
                MessageBox.showPlain(Icon.INFO, confirmationMessage.getString("BR_MSG_ID_026"), confirmationMessage.getString("MSG_ID_019"), ButtonId.OK);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void addResultsTable() {
        resultsLayout.removeAllComponents();
        resultsLayout.addComponent(resultTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(resultTableLogic.createControls());
        resultsLayout.addComponent(controls);
    }

    public GroupDTO getGroupDTO() {
        return groupDTO;
    }

    public void setGroupDTO(GroupDTO groupDTO) {
        this.groupDTO = groupDTO;
    }

    @UiHandler("cancelBtnCGL")
    public void cancelButtonLogic(Button.ClickEvent event) {
        close();
    }
}
