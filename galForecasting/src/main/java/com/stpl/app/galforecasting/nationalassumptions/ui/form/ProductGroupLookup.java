/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.ui.form;

import com.stpl.app.galforecasting.nationalassumptions.dto.ProductGroupLookUpDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.ProductGroupLogic;
import com.stpl.app.galforecasting.nationalassumptions.logic.ProductGroupTableLogic;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUiUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.USER_ID;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.PRODUCT_GROUP_LOOKUP;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class ProductGroupLookup.
 *
 * @author gopinath
 */
public class ProductGroupLookup extends Window {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -2352237704469629008L;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ProductGroupLookup.class);

    /**
     * The product group name.
     */
    @UiField("productGroupName")
    TextField productGroupName;

    /**
     * The product group.
     */
    @UiField("productGroup")
    TextField productGroup;

    /**
     * The result table.
     */
    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;
    ProductGroupTableLogic tableLogic = new ProductGroupTableLogic();
    ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    /**
     * The table bean.
     */
    BeanItemContainer<ProductGroupLookUpDTO> tableBean = new BeanItemContainer<ProductGroupLookUpDTO>(ProductGroupLookUpDTO.class);

    /**
     * The select.
     */
    @UiField("select")
    Button select;

    /**
     * The cancel.
     */
    @UiField("cancel")
    Button cancel;

    /**
     * The reset table.
     */
    @UiField("resetTable")
    Button resetTable;

    /**
     * The search.
     */
    @UiField("search")
    Button search;

    /**
     * The reset.
     */
    @UiField("reset")
    Button reset;

    /**
     * The logic.
     */
    ProductGroupLogic productLogic = new ProductGroupLogic();
    boolean isCancel = false;

    /**
     * Instantiates a new product group lookup.
     */
    public ProductGroupLookup() {
        super("Product Group Lookup");
        LOGGER.info("Constructor Method Started");
        init();
        LOGGER.info("Constructor Method ended");

    }

    /**
     * Inits the.
     */
    private void init() {
        LOGGER.info("Init Method Started");
        setClosable(true);
        setModal(true);
        setWidth("950px");
        setHeight("790px");
        setContent(Clara.create(getClass().getResourceAsStream("/nationalassumption/ProductGroupLookUp.xml"), this));
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        configureFields();
        LOGGER.info("Init Method Ended");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        LOGGER.info("ConfigureFields Methood Started");

        productGroupName.focus();
        resultTable.markAsDirty();
        resultsTableLayout.addComponent(resultTable);
        resultsTableLayout.addComponent(tableLogic.createControls());
        tableLogic.setContainerDataSource(tableBean);
        resultTable.setVisibleColumns(CommonUiUtils.PRODUCTGROUPCOLUMN);
        resultTable.setColumnHeaders(CommonUiUtils.PRODUCTGROUPHEADER);
        resultTable.setSelectable(true);
        resultTable.setSortEnabled(true);
        resultTable.setImmediate(true);
        resultTable.setPageLength(10);
        resultTable.setSizeFull();
        resultTable.setHeight("367px");
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.addStyleName(Constant.FILTER_TABLE);
        resultTable.addStyleName("table-header-center");

        search.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                searchButtonOnClick();
            }
        });
        select.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                selectButtonOnClick(resultTable.getValue());
            }
        });
        cancel.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                    }

                    @Override
                    public void yesMethod() {
                        cancelButtonOnClick();
                    }
                }.getConfirmationMessage("Cancel Confirmation", "Are you sure you want to close the lookup?");
            }
        });
        resetTable.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                    }

                    @Override
                    public void yesMethod() {
                        resetTableButtonOnClick();
                    }
                }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
            }
        });
        reset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                    }

                    @Override
                    public void yesMethod() {
                        resetButtonOnClick();
                    }
                }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
            }
        });
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID.getConstant()));
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS + "," + PRODUCT_GROUP_LOOKUP);
            if (tabItemHM.get("search") != null && tabItemHM.get("search").isFunctionFlag()) {
                search.setVisible(true);
            } else {
                search.setVisible(false);
            }
            if (tabItemHM.get("reset") != null && tabItemHM.get("reset").isFunctionFlag()) {
                reset.setVisible(true);
            } else {
                reset.setVisible(false);
            }
            if (tabItemHM.get("select") != null && tabItemHM.get("select").isFunctionFlag()) {
                select.setVisible(true);
            } else {
                select.setVisible(false);
            }
            if (tabItemHM.get("cancel") != null && tabItemHM.get("cancel").isFunctionFlag()) {
                cancel.setVisible(true);
            } else {
                cancel.setVisible(false);
            }
            if (tabItemHM.get("resetTable") != null && tabItemHM.get("resetTable").isFunctionFlag()) {
                resetTable.setVisible(true);
            } else {
                resetTable.setVisible(false);
            }
        } catch (Exception system) {
            LOGGER.error(system.getMessage());
        }

        LOGGER.info("ConfigureFields Methood Ended");
    }

    /**
     * To generate the search results.
     *
     * @param event the event
     */
    @UiHandler("search")
    private void searchButtonOnClick() {
        LOGGER.info("searchButtonOnClick Methood Started");
        try {
            String productGroupNoValue = productGroup.getValue();
            String productGroupNameValue = productGroupName.getValue();

            if (!tableLogic.fireSetData(productGroupNoValue, productGroupNameValue, false)) {
                AbstractNotificationUtils
                        .getErrorNotification("No Search Results", "There are no Product Groups that match the entered search criteria. Please try again.");

            } else {
                CommonUIUtils.getMessageNotification("Search Completed");

            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("searchButtonOnClick Methood Started");
    }

    /**
     * To generate the select results.
     *
     * @param event the event
     */
    @UiHandler("select")
    private void selectButtonOnClick(Object selectedRecord) {
        LOGGER.info("selectButtonOnClick Methood Started");
        if (selectedRecord == null) {
            AbstractNotificationUtils.getErrorNotification("Select a product group", "Please select one product group.");
        } else {
            isCancel = false;
            close();
        }
        LOGGER.info("selectButtonOnClick Methood ends");
    }

    /**
     * To cancel the search results.
     *
     * @param event the event
     */
    @UiHandler("cancel")
    private void cancelButtonOnClick() {
        LOGGER.info("cancelButtonOnClick Method Started");

        isCancel = true;
        close();
        LOGGER.info("cancelButtonOnClick Method Started");
    }

    /**
     * To resetTable the search results.
     *
     * @param event the event
     */
    @UiHandler("resetTable")
    private void resetTableButtonOnClick() {
        LOGGER.info("resetTableButtonOnClick Method Started");
        productGroupName.setValue(StringUtils.EMPTY);
        productGroup.setValue(StringUtils.EMPTY);
        resultTable.removeAllItems();
        LOGGER.info("resetTableButtonOnClick Method Started");

    }

    /**
     * To reset results value.
     *
     * @param event the event
     */
    @UiHandler("reset")
    private void resetButtonOnClick() {
        LOGGER.info("resetButtonOnClick Method Started");
        productGroupName.setValue(StringUtils.EMPTY);
        productGroup.setValue(StringUtils.EMPTY);
        LOGGER.info("resetButtonOnClick Method Started");
    }

    public Object getResultTable() {
        if (isCancel) {
            return null;
        } else {
            return resultTable.getValue();
        }
    }

}
