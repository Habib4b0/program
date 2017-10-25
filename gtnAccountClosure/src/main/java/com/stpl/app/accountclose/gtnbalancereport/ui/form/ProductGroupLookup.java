/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.stpl.app.accountclose.dto.GroupDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.ProductLookupResultTable;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.HeaderUtils;
import com.stpl.app.accountclose.utils.ResponsiveUtils;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
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
 * @author alok.v
 */
public class ProductGroupLookup extends Window {

    private String indicator;
    private TextField groupLookup;
    private String sidQuery;
    List<String> companySids;
    private static final long serialVersionUID = 1L;
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
    @UiField("searchBtnPGL")
    public Button searchBtnPGL;
    /**
     * The reset btn.
     */
    @UiField("resetBtnPGL")
    private Button resetBtn;
    /**
     * The result layout.
     */
    @UiField("resultsLayout")
    private VerticalLayout resultsLayout;
    /**
     * The select btn.
     */
    @UiField("selectBtnPGL")
    private Button selectBtn;
    /**
     * The cancel btn.
     */
    @UiField("cancelBtnPGL")
    private Button cancelBtn;
    /**
     * The result reset btn.
     */
    @UiField("resultResetBtnPGL")
    private Button resultResetBtn;
    /**
     * The result container.
     */
    private BeanItemContainer<GroupDTO> resultContainer = new BeanItemContainer<GroupDTO>(GroupDTO.class);
    private GroupDTO selectedCustHierarchy;
    ProductLookupResultTable resultTableLogic = new ProductLookupResultTable();
    public ExtPagedTable resultTable = new ExtPagedTable(resultTableLogic);
    GroupDTO groupDTO = new GroupDTO();
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");
    public static final Logger LOGGER = Logger.getLogger(ProductGroupLookup.class);

    /**
     *
     * @param indicator
     * @param windowName
     * @param groupLookup
     * @param sidQuery
     */
    public ProductGroupLookup(final String indicator, final String windowName, final TextField groupLookup, final String sidQuery) {
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
        setContent(Clara.create(getClass().getResourceAsStream("/productGroupLookUp.xml"), this));
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

        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setVisibleColumns(HeaderUtils.PRODUCT_LOOKUP_VISIBLE_COLUMN);
        resultTable.setColumnHeaders(HeaderUtils.PRODUCT_LOOKUP_COLUMN_HEADER);
        resultTable.setSelectable(true);
        resultTable.setImmediate(true);
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
                        productGroupName.setValue(StringUtils.EMPTY);
                        productGroupNo.setValue(StringUtils.EMPTY);
                    }
                }.getConfirmationMessage(confirmationMessage.getString("MSG_ID_001"), confirmationMessage.getString("BR_MSG_ID_038"));

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
                }.getConfirmationMessage(confirmationMessage.getString("MSG_ID_001"), confirmationMessage.getString("BR_MSG_ID_038"));
            }
        });

    }

    @UiHandler("searchBtnPGL")
    public void btnSearchLogic(Button.ClickEvent event) {
        try {
            if ((StringUtils.isNotBlank((productGroupName.getValue().trim()))) || ((StringUtils.isNotBlank((productGroupNo.getValue().trim()))))) {
                resultContainer.removeAllItems();
                GroupDTO groupDTO = new GroupDTO();
                groupDTO.setProductGroupName(productGroupName.getValue().trim());
                groupDTO.setProductGroupNo(productGroupNo.getValue().trim());
                groupDTO.setIndicator(indicator);
                if (!resultTableLogic.loadSetData(groupDTO, false)) {
                    AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_028"), confirmationMessage.getString("BR_MSG_ID_039"));
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

    @UiHandler("cancelBtnPGL")
    public void cancelBtnLogic(Button.ClickEvent event) {
        close();
    }
}
