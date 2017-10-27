/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.forecastabstract.lookups.AbstractViewLookup;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.RETURNS;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;

import static com.stpl.app.utils.Constants.ButtonConstants.*;

import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import static com.stpl.app.utils.Constants.LogicConstants.*;
import com.stpl.app.utils.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.ExtCustomTable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class PrivatePublicView.
 *
 * @author soundarrajan
 */
public class PrivatePublicView extends AbstractViewLookup {

    /**
     * Indicator to indicate private or public view lookup.
     */
    private String indicator;

    /**
     * To refer the textfield that opens this lookup.
     */
    /**
     * The view name textfield for searching the view.
     */
    private TextField viewName;

    /**
     * Result Table.
     */
    private ExtFilterTable results;

    /**
     * The search button.
     */
    private Button btnSearch;

    /**
     * The reset button.
     */
    private Button btnReset;

    /**
     * The select button.
     */
    private Button btnSelect;

    /**
     * The close button.
     */
    private Button btnClose;

    /**
     * Default container to results table.
     */
    private BeanItemContainer<ViewDTO> viewContainer;

    private ViewDTO viewDTO;

    private String screenName;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PrivatePublicView.class);

    /**
     * Constructor for PrivatePublicView.
     *
     * @param indicator to indicate whether the view is private or public
     * @param lookup To refer the textfield that opens this lookup
     * @param windowName the window name for lookup
     */
    public PrivatePublicView(final String indicator,final String windowName, final String screenName) {
        super(windowName);
        LOGGER.debug("Entering PrivatePublicView");
        this.indicator = indicator;
        this.screenName = screenName;
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        initializeComponents();
        buildLookup();
        LOGGER.debug("End of PrivatePublicView");
    }

    /**
     * Initializes all the components.
     */
    private void initializeComponents() {
        LOGGER.debug("Entering initializeComponents");
        setViewName(UiUtils.addTextField());
        btnSearch = new Button(BTN_SEARCH.getConstant());
        btnReset = new Button(BTN_RESET.getConstant());
        btnSelect = new Button(BTN_SELECT.getConstant());
        btnClose = new Button(BTN_CLOSE.getConstant());
        results = new ExtFilterTable();
        viewContainer = new BeanItemContainer<>(ViewDTO.class);
        LOGGER.debug("End of initializeComponents");
    }

    /**
     * Builds the view lookup.
     */
    private void buildLookup() {
        LOGGER.debug("Entering buildLookup");
        setContent(buildViewLookup(viewName, btnSearch, btnReset, btnSelect, btnClose, results));
        configureField();
        LOGGER.debug("End of buildLookup");
    }

    /**
     * Configure the components here.
     */
    @Override
    protected void configureField() {
        LOGGER.debug("Entering configureField");
        //Configure the table
        results.setContainerDataSource(viewContainer);
        if (screenName.equals(RETURNS.getConstant())) {
            results.setVisibleColumns(TableHeaderColumnsUtil.getInstance().viewLookupColumnReturns);
            results.setColumnHeaders(TableHeaderColumnsUtil.getInstance().viewLookupHeadersReturns);
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
            results.setVisibleColumns(TableHeaderColumnsUtil.getInstance().viewLookupColumnsArp);
            results.setColumnHeaders(TableHeaderColumnsUtil.getInstance().viewLookupHeadersARP);
        } else {
            results.setVisibleColumns(TableHeaderColumnsUtil.getInstance().viewLookupColumns);
            results.setColumnHeaders(TableHeaderColumnsUtil.getInstance().viewLookupHeaders);
        }
        results.setSortEnabled(true);
        results.setSelectable(true);
        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setStyleName(Constant.FILTER_TABLE);
        results.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                 if (event.getProperty().getValue() != null) {
                    btnSelect.setEnabled(true);
                } else {
                    btnSelect.setEnabled(false);
                }
            }
        });
        btnReset.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {

                NotificationUtils notificationUtils = new NotificationUtils() {
                    @Override
                    public void yesMethod() {
                        viewName.setValue(StringUtils.EMPTY);
                    }

                    @Override
                    public void noMethod() {
                        return;
                    }
                };
                notificationUtils.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

            }
        });
        btnSelect.setEnabled(false);
        viewName.focus();
        results.setHeight(NumericConstants.FIVE_HUNDRED, Unit.PIXELS);
        results.addStyleName("custom-table-header-alignment");
        if (screenName.equals("Returns")) {
            results.setColumnAlignments(ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT);
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
            results.setColumnAlignments(ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT);
        } else {
            results.setColumnAlignments(ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT);
        }
        LOGGER.debug("End of configureField");
        viewName.setImmediate(true);
        viewName.setConverter(new TextFieldConverter());
        results.setConverter("createdDateSearch", new DateToStringConverter());
        results.setConverter("modifiedDateSearch", new DateToStringConverter());
        results.refreshRowCache();
    }

    /**
     * Overriding Search button logic.
     */
    @Override
    public void btnSearchLogic() {
        LOGGER.debug("Entering btnSearchLogic method");
        try {
            if (StringUtils.EMPTY.equals(viewName.getValue()) || Constant.NULL.equals(viewName.getValue()) || StringUtils.isBlank(viewName.getValue())) {
                viewContainer.removeAllItems();
                AbstractNotificationUtils.getErrorNotification("Invalid Search", "There are no Views that match the search criteria.  Please try again.");
                viewName.focus();
            } else {
                String moduleName = null;
                if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                    moduleName = "Non Mandated";
                } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                    moduleName = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED;
                } else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                    moduleName = CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION;
                } else {
                    moduleName = "Returns";
                }
                List<ViewDTO> list = null;
                if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
                    list = NonMandatedLogic.searhViewARP(viewName.getValue(), moduleName, VIEW_TYPE.getViewType(indicator));
                } else {
                    list = NonMandatedLogic.searhView(viewName.getValue(), moduleName, VIEW_TYPE.getViewType(indicator));
                }

                if (list.isEmpty()) {
                    viewContainer.removeAllItems();
                    btnSelect.setEnabled(false);
                    AbstractNotificationUtils.getErrorNotification("Invalid Search", "There are no Views that match the search criteria.  Please try again.");
                } else {
                    viewContainer.removeAllItems();
                    viewContainer.addAll(list);
                    btnSelect.setEnabled(false);

                }
            }
        } catch (SystemException se) {
            LOGGER.error(se);
        } catch (PortalException pe) {
            LOGGER.error(pe);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("End of btnSearchLogic method");
    }

    /**
     * Overriding Select button logic.
     */
    @Override
    public void btnSelectLogic() {
        LOGGER.debug("Entering btnSelectLogic method");
        if (results.getValue() == null) {
            if (viewContainer.size() > 0 || results.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification("No View Selected",
                        "There is no view selected. Please select a saved view and try again.");
            } else {
                CommonUIUtils.getMessageNotification("Search for View");
            }
        } else {
            final ViewDTO itemId = (ViewDTO) results.getValue();
            setViewDTO(itemId);
            close();
        }
        LOGGER.debug("End of btnSelectLogic method");
    }

    /**
     * Overriding Close button logic.
     */
    @Override
    public void btnCloseLogic() {
        close();
    }

    /**
     * Gets the view name.
     *
     * @return the view name
     */
    public TextField getViewName() {
        return viewName;
    }

    /**
     * Sets the view name.
     *
     * @param viewName the view name
     */
    public void setViewName(final TextField viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the btn search.
     *
     * @return the btn search
     */
    public Button getBtnSearch() {
        return btnSearch;
    }

    /**
     * Sets the btn search.
     *
     * @param btnSearch the btn search
     */
    public void setBtnSearch(final Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    /**
     * Gets the btn reset.
     *
     * @return the btn reset
     */
    public Button getBtnReset() {
        return btnReset;
    }

    /**
     * Sets the btn reset.
     *
     * @param btnReset the btn reset
     */
    public void setBtnReset(final Button btnReset) {
        this.btnReset = btnReset;
    }

    /**
     * Gets the btn select.
     *
     * @return the btn select
     */
    public Button getBtnSelect() {
        return btnSelect;
    }

    /**
     * Sets the btn select.
     *
     * @param btnSelect the btn select
     */
    public void setBtnSelect(final Button btnSelect) {
        this.btnSelect = btnSelect;
    }

    /**
     * Gets the btn close.
     *
     * @return the btn close
     */
    public Button getBtnClose() {
        return btnClose;
    }

    /**
     * Sets the btn close.
     *
     * @param btnClose the btn close
     */
    public void setBtnClose(final Button btnClose) {
        this.btnClose = btnClose;
    }

    public ViewDTO getViewDTO() {
        return viewDTO;
    }

    public void setViewDTO(ViewDTO viewDTO) {
        this.viewDTO = viewDTO;
    }

}
