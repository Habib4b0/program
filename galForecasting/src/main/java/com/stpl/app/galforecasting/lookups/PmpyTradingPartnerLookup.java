/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.lookups;

import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.lookups.dto.PmpyTPFilterGenerator;
import com.stpl.app.galforecasting.lookups.dto.PmpyTradingPartnerDTO;
import com.stpl.app.galforecasting.lookups.logic.tablelogic.PmpyTradingPartnerTableLogic;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import elemental.events.KeyboardEvent;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class PmpyTradingPartnerLookup.
 *
 * @author vinodhini
 */
public class PmpyTradingPartnerLookup extends Window {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PmpyTradingPartnerLookup.class);

    /**
     * The trading partner no.
     */
    @UiField("tradingPartnerNo")
    private TextField tradingPartnerNo;

    /**
     * The trading partner name.
     */
    @UiField("tradingPartnerName")
    private TextField tradingPartnerName;

    /**
     * The reset tp.
     */
    @UiField("resetTP")
    private Button resetTP;

    /**
     * The search tp.
     */
    @UiField("searchTP")
    private Button searchTP;

    /**
     * The Constant EMPTY.
     */
    private static final String EMPTY = StringUtils.EMPTY;

    /**
     * The select.
     */
    @UiField("select")
    private Button select;

    /**
     * The reset.
     */
    @UiField("reset")
    private Button reset;

    /**
     * The close.
     */
    @UiField("close")
    private Button close;
    // add to CommonUIUtils.NonMandated later
    /**
     * The Constant TRADING_PARTNER_COLUMNS.
     */
    public static final Object[] TRADING_PARTNER_COLUMNS = new Object[]{"tradingPartnerNo", "tradingPartnerName"};

    /**
     * The Constant TRADING_PARTNER_HEADER.
     */
    public static final String[] TRADING_PARTNER_HEADER = new String[]{"Trading Partner #", "Trading Partner Name"};

    /**
     * The trading partner.
     */
    private TextField tradingPartner;

    /**
     * The non mandated logic.
     */
    private NonMandatedLogic nonMandatedLogic = new NonMandatedLogic();

    /**
     * The search binder.
     */
    private CustomFieldGroup searchBinder = new CustomFieldGroup(new BeanItem<PmpyTradingPartnerDTO>(new PmpyTradingPartnerDTO()));

    /**
     * The error msg.
     */
    private ErrorLabel errorMsg = new ErrorLabel();

    public static final String ALPHA_NUMERIC = "([0-9|a-z|A-Z|\\ |\\*])*";

    private Object contractHolder;

    @UiField("tableLayout")
    VerticalLayout tableLayout;

    private HorizontalLayout controlLayout = new HorizontalLayout();

    PmpyTradingPartnerTableLogic tableLogic = new PmpyTradingPartnerTableLogic();
    private ExtPagedTable resultsTableTP = new ExtPagedTable(tableLogic);
    private BeanItemContainer<PmpyTradingPartnerDTO> resultBean = new BeanItemContainer<>(PmpyTradingPartnerDTO.class);

    /**
     * Gets the trading partner no.
     *
     * @return the tradingPartnerNo
     */
    public TextField getTradingPartnerNo() {
        return tradingPartnerNo;
    }

    /**
     * Sets the trading partner no.
     *
     * @param tradingPartnerNo the tradingPartnerNo to set
     */
    public void setTradingPartnerNo(final TextField tradingPartnerNo) {
        this.tradingPartnerNo = tradingPartnerNo;
    }

    /**
     * Gets the trading partner name.
     *
     * @return the tradingPartnerName
     */
    public TextField getTradingPartnerName() {
        return tradingPartnerName;
    }

    /**
     * Sets the trading partner name.
     *
     * @param tradingPartnerName the tradingPartnerName to set
     */
    public void setTradingPartnerName(final TextField tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    /**
     * Gets the reset tp.
     *
     * @return the resetTP
     */
    public Button getResetTP() {
        return resetTP;
    }

    /**
     * Sets the reset tp.
     *
     * @param resetTP the resetTP to set
     */
    public void setResetTP(final Button resetTP) {
        this.resetTP = resetTP;
    }

    /**
     * Gets the search tp.
     *
     * @return the searchTP
     */
    public Button getSearchTP() {
        return searchTP;
    }

    /**
     * Sets the search tp.
     *
     * @param searchTP the searchTP to set
     */
    public void setSearchTP(final Button searchTP) {
        this.searchTP = searchTP;
    }

    /**
     * Gets the select.
     *
     * @return the select
     */
    public Button getSelect() {
        return select;
    }

    /**
     * Sets the select.
     *
     * @param select the select to set
     */
    public void setSelect(final Button select) {
        this.select = select;
    }

    /**
     * Gets the reset.
     *
     * @return the reset
     */
    public Button getReset() {
        return reset;
    }

    /**
     * Sets the reset.
     *
     * @param reset the reset to set
     */
    public void setReset(final Button reset) {
        this.reset = reset;
    }

    /**
     * Gets the close.
     *
     * @return the close
     */
    public Button getClose() {
        return close;
    }

    /**
     * Sets the close.
     *
     * @param close the close to set
     */
    public void setClose(final Button close) {
        this.close = close;
    }

    /**
     * Gets the trading partner.
     *
     * @return the tradingPartner
     */
    public TextField getTradingPartner() {
        return tradingPartner;
    }

    /**
     * Sets the trading partner.
     *
     * @param tradingPartner the tradingPartner to set
     */
    public void setTradingPartner(final TextField tradingPartner) {
        this.tradingPartner = tradingPartner;
    }

    /**
     * Gets the non mandated logic.
     *
     * @return the nonMandatedLogic
     */
    public NonMandatedLogic getNonMandatedLogic() {
        return nonMandatedLogic;
    }

    /**
     * Sets the non mandated logic.
     *
     * @param nonMandatedLogic the nonMandatedLogic to set
     */
    public void setNonMandatedLogic(final NonMandatedLogic nonMandatedLogic) {
        this.nonMandatedLogic = nonMandatedLogic;
    }

    /**
     * Gets the search binder.
     *
     * @return the searchBinder
     */
    public CustomFieldGroup getSearchBinder() {
        return searchBinder;
    }

    /**
     * Sets the search binder.
     *
     * @param searchBinder the searchBinder to set
     */
    public void setSearchBinder(final CustomFieldGroup searchBinder) {
        this.searchBinder = searchBinder;
    }

    /**
     * Gets the error msg.
     *
     * @return the errorMsg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets the error msg.
     *
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(final ErrorLabel errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Instantiates a new PMPY trading partner lookup.
     *
     * @param tradingPartner the trading partner
     * @param contractHolder
     */
    public PmpyTradingPartnerLookup(final TextField tradingPartner, Object contractHolder) {
        super("Trading Partner Lookup");
        this.tradingPartner = tradingPartner;
        this.contractHolder = contractHolder;
        LOGGER.info("Entering PmpyTradingPartnerLookup");
        init();
        getBinder();
        LOGGER.info("End of PmpyTradingPartnerLookup");

    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {
        final PmpyTradingPartnerDTO bean = new PmpyTradingPartnerDTO();

        LOGGER.info("Entering getBinder method");
        searchBinder = new CustomFieldGroup(new BeanItem<PmpyTradingPartnerDTO>(bean));
        searchBinder.setBuffered(true);
        searchBinder.bindMemberFields(this);
        searchBinder.setErrorDisplay(errorMsg);
        LOGGER.info("End of getBinder method");

        return searchBinder;
    }

    /**
     * Instantiates a new PMPY trading partner lookup.
     */
    public PmpyTradingPartnerLookup() {
        super("Trading Partner Lookup");

        LOGGER.info("Entering PMPYTradingPartnerLookup");
        init();
        getBinder();
        LOGGER.info("End of PMPYTradingPartnerLookup");

    }

    /**
     * Inits the.
     */
    public final void init() {

        LOGGER.info("Entering init method");
        center();
        setClosable(true);
        setModal(true);
        setWidth("580px");
        setHeight("690px");
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setContent(Clara.create(getClass().getResourceAsStream("/nonmandated/pmpyTradingPartner.xml"), this));
        configureTable();
        configureFields();
        LOGGER.info("End of init method");

    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        LOGGER.info("Entering configureFields method");
        setCloseShortcut(KeyboardEvent.KeyCode.ESC);
        tradingPartnerNo.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                if (!String.valueOf(event.getText()).matches(ALPHA_NUMERIC)) {
                    AbstractNotificationUtils.getErrorNotification("Field Error", "Special Characters are not allowed");
                    tradingPartnerNo.setValue(StringUtils.EMPTY);
                    return;
                }
            }

        });

        tradingPartnerName.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                if (!String.valueOf(event.getText()).matches(ALPHA_NUMERIC)) {
                    AbstractNotificationUtils.getErrorNotification("Field Error", "Special Characters are not allowed");
                    tradingPartnerName.setValue(StringUtils.EMPTY);
                    return;
                }
            }

        });
        tradingPartnerName.setMaxLength(36);
        tradingPartnerNo.setMaxLength(36);
        resultsTableTP.addStyleName(Constant.TABLE_HEADER_STYLE);
        resetTP.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", "Are you sure you want to reset the page to default values?", new MessageBoxListener() {
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(Constant.YES)) {
                            tradingPartnerName.setValue(EMPTY);
                            tradingPartnerNo.setValue(EMPTY);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });

        searchTP.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    searchLogic();
                } catch (SystemException e) {
                    LOGGER.error(errorMsg);
                } catch (CommitException e) {
                    LOGGER.error(e.getMessage());
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }

            }
        });
        LOGGER.info("End of configureFields method");
    }

    private void configureTable() {

        tableLayout.addComponent(resultsTableTP);
        getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);
        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(100);
        tableLogic.sinkItemPerPageWithPageLength(false);
        setTableDefaultConfig();
        resultsTableTP.setSelectable(true);
        resultsTableTP.markAsDirty();
        resultsTableTP.setComponentError(null);
        resultsTableTP.setFilterBarVisible(true);
        resultsTableTP.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTableTP.setFilterGenerator(new PmpyTPFilterGenerator());
        resultsTableTP.setValidationVisible(false);
        resultsTableTP.addStyleName(Constant.FILTERBAR);

    }

    /**
     * Search logic.
     *
     * @throws CommitException , SystemException
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void searchLogic() throws CommitException, SystemException, Exception {
        LOGGER.info("Entering searchLogic method");
        if (StringUtils.isEmpty(tradingPartnerNo.getValue().toString()) && StringUtils.isEmpty(tradingPartnerName.getValue().toString())) {
            AbstractNotificationUtils.getErrorNotification("No Search Criteria", "Please enter a value to search for.");
        } else {
            String tpNo = tradingPartnerNo.getValue().toString();
            String tpName = tradingPartnerName.getValue().toString();
            searchBinder.commit();
            tableLogic.configureSearchData(tpNo, tpName, contractHolder);
            resultsTableTP.setFilterDecorator(new ExtDemoFilterDecorator());
            resultsTableTP.setFilterGenerator(new PmpyTPFilterGenerator());
            resultsTableTP.setImmediate(true);

            resultsTableTP.addStyleName("TableCheckBox");
            resultsTableTP.setSelectable(true);
            if (tableLogic.isResultsEmpty()) {
                AbstractNotificationUtils.getInfoNotification("No Results", "There were no results found that match the entered search criteria. \nPlease try again.");

            } else {
                CommonUIUtils.getMessageNotification("Search Completed");
            }
            resultsTableTP.markAsDirtyRecursive();
        }
        LOGGER.info("End of searchLogic method");
    }

    public void getResponsiveControls(HorizontalLayout tempLayout, HorizontalLayout controlBar) {

        controlBar.setStyleName(Constant.RESPONSIVE_PAGED_TABLE);
        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < 8; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);

    }

    @UiHandler("reset")
    public void reset(final Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", "Are you sure you want to reset the page to default values?", new MessageBoxListener() {
            /**
             * Called when the button is clicked .
             *
             */

            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    tableLogic.clearAll();
                    tableLogic.setReset(true);
                    tableLogic.setRequiredCount(true);
                    tableLogic.setCurrentPage(1);
                    resultsTableTP.setFilterDecorator(new ExtDemoFilterDecorator());
                    setTableDefaultConfig();
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    /**
     *
     * @param resultTable
     * @param key
     */
    public void setTableDefaultConfig() {
        resultsTableTP.setVisibleColumns(TRADING_PARTNER_COLUMNS);
        resultsTableTP.setColumnHeaders(TRADING_PARTNER_HEADER);
        resultsTableTP.markAsDirtyRecursive();
        resultsTableTP.setImmediate(true);
        resultsTableTP.setWidth("510px");
        resultsTableTP.setHeight("250px");
    }

    /**
     * Called when select button is clicked .
     *
     */
    @UiHandler("select")
    public void select(final Button.ClickEvent event) {

        if (resultsTableTP.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Trading Partner selected", "Please select a Trading Partner from the list view.");
        } else {
            final PmpyTradingPartnerDTO dto = (PmpyTradingPartnerDTO) resultsTableTP.getValue();
            close();
            tradingPartner.setReadOnly(false);
            tradingPartner.setValue(String.valueOf(dto.getTradingPartnerName()));
            tradingPartner.setData(dto.getCompanySysId());
            tradingPartner.setReadOnly(true);

        }
    }

    /**
     * Called when the close button is clicked .
     *
     */
    @UiHandler("close")
    public void close(final Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Close Confirmation", "Are you sure you want to close the trading partner lookup" + " ?", new MessageBoxListener() {
            /**
             * Called when the button is clicked .
             *
             */

            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    close();
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

}
