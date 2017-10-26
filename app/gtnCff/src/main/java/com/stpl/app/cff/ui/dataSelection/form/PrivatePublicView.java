/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.dataSelection.form;

import com.stpl.app.cff.abstractCff.AbstractViewLookup;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.TextField;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import static com.stpl.app.cff.util.Constants.ButtonConstants.*;
import com.stpl.app.cff.util.NotificationUtils;
import com.stpl.app.cff.util.TableHeaderColumnsUtil;
import com.stpl.app.cff.util.UiUtils;
import com.stpl.ifs.ui.DateToStringConverter;
import static com.stpl.app.cff.util.Constants.LogicConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;

/**
 *
 * @author mohamed.hameed
 */
public class PrivatePublicView extends AbstractViewLookup {

    /**
     * Indicator to indicate private or public view lookup.
     */
    private String indicator;
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
    public PrivatePublicView(final String indicator, final CustomTextField lookup, final String windowName, final String screenName) {
        super(windowName);
        LOGGER.debug("Entering PrivatePublicView");
        this.indicator = indicator;
        this.screenName = screenName;
        addStyleName(Constants.bootstrap_ui);
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
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
        viewContainer = new BeanItemContainer<ViewDTO>(ViewDTO.class);
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
        if (screenName.equals(ConstantsUtils.RETURNS)) {
            results.setVisibleColumns(TableHeaderColumnsUtil.VIEW_LOOKUP_COLUMNS_RETURNS);
            results.setColumnHeaders(TableHeaderColumnsUtil.VIEW_LOOKUP_HEADERS_RETURNS);
        } else {
            results.setVisibleColumns(TableHeaderColumnsUtil.VIEW_LOOKUP_COLUMNS);
            results.setColumnHeaders(TableHeaderColumnsUtil.VIEW_LOOKUP_HEADERS);
        }
        results.setSortEnabled(true);
        results.setSelectable(true);
        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setStyleName(Constants.FILTER_TABLE);
        btnReset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                NotificationUtils notificationUtils = new NotificationUtils() {
                    @Override
                    public void yesMethod() {
                        viewName.setValue(StringUtils.EMPTY);
                    }

                    @Override
                    public void noMethod() {
                    }
                };
                notificationUtils.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

            }
        });
        btnSelect.setEnabled(false);
        viewName.focus();
        results.setHeight(NumericConstants.FIVE_HUNDRED, Unit.PIXELS);
        results.addStyleName("custom-table-header-alignment");
        if (screenName.equals(ConstantsUtils.RETURNS)) {
            results.setColumnAlignments(ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.LEFT);
        } else {
            results.setColumnAlignments(ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT,
                    ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.LEFT);
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
            if (StringUtils.EMPTY.equals(viewName.getValue()) || "null".equals(viewName.getValue()) || StringUtils.isBlank(viewName.getValue())) {
                viewContainer.removeAllItems();
                AbstractNotificationUtils.getErrorNotification("Invalid Search", "There are no Views that match the search criteria.  Please try again.");
                viewName.focus();
            } else {
                final List<ViewDTO> list = CFFLogic.searhView(viewName.getValue(),VIEW_TYPE.getViewType(indicator));
                if (list.isEmpty()) {
                    viewContainer.removeAllItems();
                    btnSelect.setEnabled(false);
                    AbstractNotificationUtils.getErrorNotification("Invalid Search", "There are no Views that match the search criteria.  Please try again.");
                } else {
                    viewContainer.removeAllItems();
                    viewContainer.addAll(list);
                    btnSelect.setEnabled(true);
                }
            }
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
