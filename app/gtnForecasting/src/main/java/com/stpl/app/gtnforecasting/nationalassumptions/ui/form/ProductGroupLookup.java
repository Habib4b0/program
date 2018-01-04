/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProductGroupLookUpDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.ProductGroupTableLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PRODUCT_GROUP_LOOKUP;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductGroupLookup.class);

    /**
     * The product group name.
     */
    @UiField("productGroupName")
    private TextField productGroupName;

    /**
     * The product group.
     */
    @UiField("productGroup")
    private TextField productGroup;

    /**
     * The result table.
     */
    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;
    
    private final ProductGroupTableLogic tableLogic = new ProductGroupTableLogic();
    private final ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    /**
     * The table bean.
     */
    private final BeanItemContainer<ProductGroupLookUpDTO> tableBean = new BeanItemContainer<>(ProductGroupLookUpDTO.class);

    /**
     * The select.
     */
    @UiField("select")
    private Button select;

    /**
     * The cancel.
     */
    @UiField("cancel")
    private Button cancel;

    /**
     * The reset table.
     */
    @UiField("resetTable")
    private Button resetTable;

    /**
     * The search.
     */
    @UiField("search")
    private Button search;

    /**
     * The reset.
     */
    @UiField("reset")
    private Button reset;

    private boolean isCancel = false;
    private final SessionDTO sessionDTO;
    private final CommonUiUtils commonUiUtils = new CommonUiUtils();
    
    
    public ProductGroupLookup(SessionDTO sessionDTO) {
        super("Product Group Lookup");
        this.sessionDTO=sessionDTO;
        LOGGER.debug("Constructor Method Started");
        init();
        LOGGER.debug("Constructor Method ended");

    }

    /**
     * Inits the.
     */
    private void init() {
        LOGGER.debug("Init Method Started");
        setClosable(true);
        setModal(true);
        setWidth("950px");
        setHeight("790px");
        setContent(Clara.create(getClass().getResourceAsStream("/nationalassumption/ProductGroupLookUp.xml"), this));
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        configureFields();
        LOGGER.debug("Init Method Ended");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        LOGGER.debug("ConfigureFields Methood Started");

        productGroupName.focus();
        resultTable.markAsDirty();
        resultsTableLayout.addComponent(resultTable);
        resultsTableLayout.addComponent(tableLogic.createControls());
        tableLogic.setContainerDataSource(tableBean);
        resultTable.setVisibleColumns(CommonUiUtils.productGroupColumn);
        resultTable.setColumnHeaders(commonUiUtils.productGroupHeader);
        resultTable.setSelectable(true);
        resultTable.setSortEnabled(true);
        resultTable.setImmediate(true);
        resultTable.setPageLength(NumericConstants.TEN);
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
                        //Default method
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
                        //Default method
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
                        return;
                    }

                    @Override
                    public void yesMethod() {
                        resetButtonOnClick();
                    }
                }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
            }
        });
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId =  sessionDTO.getUserId();
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
        } catch (PortalException | SystemException system) {
            LOGGER.error(StringUtils.EMPTY,system);
        }

        LOGGER.debug("ConfigureFields Methood Ended");
    }

    /**
     * To generate the search results.
     *
     * @param event the event
     */
    @UiHandler("search")
    private void searchButtonOnClick() {
        LOGGER.debug("searchButtonOnClick Methood Started");
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
        LOGGER.debug("searchButtonOnClick Methood Started");
    }

    /**
     * To generate the select results.
     *
     * @param event the event
     */
    @UiHandler("select")
    private void selectButtonOnClick(Object selectedRecord) {
        LOGGER.debug("selectButtonOnClick Methood Started");
        if (selectedRecord == null) {
            AbstractNotificationUtils.getErrorNotification("Select a product group", "Please select one product group.");
        } else {
            isCancel = false;
            close();
        }
        LOGGER.debug("selectButtonOnClick Methood ends");
    }

    /**
     * To cancel the search results.
     *
     * @param event the event
     */
    @UiHandler("cancel")
    private void cancelButtonOnClick() {
        LOGGER.debug("cancelButtonOnClick Method Started");

        isCancel = true;
        close();
        LOGGER.debug("cancelButtonOnClick Method Started");
    }

    /**
     * To resetTable the search results.
     *
     * @param event the event
     */
    @UiHandler("resetTable")
    private void resetTableButtonOnClick() {
        LOGGER.debug("resetTableButtonOnClick Method Started");
        productGroupName.setValue(StringUtils.EMPTY);
        productGroup.setValue(StringUtils.EMPTY);
        resultTable.removeAllItems();
        LOGGER.debug("resetTableButtonOnClick Method Started");

    }

    /**
     * To reset results value.
     *
     * @param event the event
     */
    @UiHandler("reset")
    private void resetButtonOnClick() {
        LOGGER.debug("resetButtonOnClick Method Started");
        productGroupName.setValue(StringUtils.EMPTY);
        productGroup.setValue(StringUtils.EMPTY);
        LOGGER.debug("resetButtonOnClick Method Started");
    }

    public Object getResultTable() {
        if (isCancel) {
            return null;
        } else {
            return resultTable.getValue();
        }
    }

}
