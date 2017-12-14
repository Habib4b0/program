/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.gtnforecasting.dto.PMPYTradingPartnerDTO;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.numberfilter.DemoFilterDecorator;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import elemental.events.KeyboardEvent;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class PMPYTradingPartnerLookup.
 *
 * @author lokeshwari
 */
public class PMPYTradingPartnerLookup extends Window {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PMPYTradingPartnerLookup.class);

    /**
     * The trading partner no.
     */
    private TextField tradingPartnerNo = new TextField();

    /**
     * The trading partner name.
     */
    private TextField tradingPartnerName = new TextField();

    /**
     * The reset tp.
     */
    private Button resetTP = new Button(Constant.RESET);

    /**
     * The search tp.
     */
    private Button searchTP = new Button(Constant.SEARCH);

    /**
     * The Constant EMPTY.
     */
    private static final String EMPTY = StringUtils.EMPTY;

    /**
     * The select.
     */
    private Button select = new Button(Constant.SELECT);

    /**
     * The reset.
     */
    private Button reset = new Button(Constant.RESET);

    /**
     * The close.
     */
    private Button close = new Button(Constant.CLOSE);
    // add to CommonUIUtils.NonMandated later
    /**
     * The Constant TRADING_PARTNER_COLUMNS.
     */
    private final Object[] tradingPartnerColumns = new Object[]{"tradingPartnerNo", "tradingPartnerName"};

    /**
     * The Constant TRADING_PARTNER_HEADER.
     */
    private final String[] tradingPartnerHeaders = new String[]{"Trading Partner #", "Trading Partner Name"};

    /**
     * The results table tp.
     */
    private ExtFilterTable resultsTableTP = new ExtFilterTable();

    /**
     * The trading partner.
     */
    private TextField tradingPartner;

    /**
     * The results tp bean.
     */
    private BeanItemContainer<PMPYTradingPartnerDTO> resultsTPBean = new BeanItemContainer<>(PMPYTradingPartnerDTO.class);

    /**
     * The non mandated logic.
     */
    private NonMandatedLogic nonMandatedLogic = new NonMandatedLogic();

    /**
     * The search binder.
     */
    private CustomFieldGroup searchBinder = new CustomFieldGroup(new BeanItem<>(new PMPYTradingPartnerDTO()));

    /**
     * The error msg.
     */
    private ErrorLabel errorMsg = new ErrorLabel();

    private static final String ALPHA_NUMERIC = "([0-9|a-z|A-Z|\\ |\\*])*";

    private Object contractHolder;

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
     * Gets the results table tp.
     *
     * @return the resultsTableTP
     */
    public ExtFilterTable getResultsTableTP() {
        return resultsTableTP;
    }

    /**
     * Sets the results table tp.
     *
     * @param resultsTableTP the resultsTableTP to set
     */
    public void setResultsTableTP(final ExtFilterTable resultsTableTP) {
        this.resultsTableTP = resultsTableTP;
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
     * Gets the results tp bean.
     *
     * @return the resultsTPBean
     */
    public BeanItemContainer<PMPYTradingPartnerDTO> getResultsTPBean() {
        return resultsTPBean;
    }

    /**
     * Sets the results tp bean.
     *
     * @param resultsTPBean the resultsTPBean to set
     */
    public void setResultsTPBean(final BeanItemContainer<PMPYTradingPartnerDTO> resultsTPBean) {
        this.resultsTPBean = resultsTPBean;
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

    public PMPYTradingPartnerLookup(final TextField tradingPartner, Object contractHolder) {
        super("Trading Partner Lookup");
        this.tradingPartner = tradingPartner;
        this.contractHolder = contractHolder;
        LOGGER.debug("Entering PMPYTradingPartnerLookup");
        init();
        getBinder();
        LOGGER.debug("End of PMPYTradingPartnerLookup");

    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {
        final PMPYTradingPartnerDTO bean = new PMPYTradingPartnerDTO();

        LOGGER.debug("Entering getBinder method");
        searchBinder = new CustomFieldGroup(new BeanItem<>(bean));
        searchBinder.setBuffered(true);
        searchBinder.bindMemberFields(this);
        searchBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("End of getBinder method");

        return searchBinder;
    }

    /**
     * Instantiates a new PMPY trading partner lookup.
     */
    public PMPYTradingPartnerLookup() {
        super("Trading Partner Lookup");

        LOGGER.debug("Entering PMPYTradingPartnerLookup");
        init();
        getBinder();
        LOGGER.debug("End of PMPYTradingPartnerLookup");

    }

    /**
     * Inits the.
     */
    public final void init() {

        LOGGER.debug("Entering init method");
        center();
        setClosable(true);
        setModal(true);
        setWidth("580px");
        setHeight(Constant.SIX_FIFTY_PX);

        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setContent(addToContent());
        configureFields();
        LOGGER.debug("End of init method");

    }

    /**
     * Adds the to content.
     *
     * @return the panel
     */
    public Panel addToContent() {
        final Panel panel = new Panel();

        LOGGER.debug("Entering addToContent method");
        final VerticalLayout vlayout = new VerticalLayout();
        vlayout.setStyleName("pmpy");
        vlayout.setSpacing(true);
        vlayout.setMargin(false);
        vlayout.addComponent(addContract());
        vlayout.addComponent(addFooterButton());
        panel.setContent(vlayout);
        panel.setSizeFull();
        LOGGER.debug("End of addToContent method");

        return panel;
    }

    /**
     * Adds the contract.
     *
     * @return the panel
     */
    private Panel addContract() {
        LOGGER.debug("Entering addContract method");
        final Panel panel = new Panel();
        final Panel panel1 = new Panel();
        final VerticalLayout contractContent = new VerticalLayout();
        contractContent.setStyleName("pmpycriteria");
        final GridLayout content = new GridLayout(NumericConstants.TWO, NumericConstants.TWO);
        content.setSpacing(true);
        content.setMargin(false);
        content.setStyleName("adjust-label");
        final VerticalLayout layout = new VerticalLayout();

        layout.setSpacing(true);
        layout.setMargin(true);
        content.setSizeFull();
        content.addComponent(new Label("Trading Partner #:"));
        content.addComponent(tradingPartnerNo);

        content.addComponent(new Label("Trading Partner Name:"));
        content.addComponent(tradingPartnerName);

        content.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        panel.setContent(content);
        layout.addComponent(panel);
        layout.addComponent(addHeaderButton());
        layout.addComponent(addResultsTable());

        panel1.setContent(layout);
        panel.setSizeFull();
        panel.setCaption("Search Criteria");
        LOGGER.debug("End of addContract method");

        return panel1;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        LOGGER.debug("Entering configureFields method");
        setCloseShortcut(KeyboardEvent.KeyCode.ESC);
        resultsTableTP.setFilterBarVisible(true);
        resultsTableTP.setFilterDecorator(new DemoFilterDecorator());
        tradingPartnerNo.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                if (!String.valueOf(event.getText()).matches(ALPHA_NUMERIC)) {
                    AbstractNotificationUtils.getErrorNotification("Field Error", "Special Characters are not allowed");
                    tradingPartnerNo.setValue(StringUtils.EMPTY);
                }
            }

        });

        tradingPartnerName.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                if (!String.valueOf(event.getText()).matches(ALPHA_NUMERIC)) {
                    AbstractNotificationUtils.getErrorNotification("Field Error", "Special Characters are not allowed");
                    tradingPartnerName.setValue(StringUtils.EMPTY);
                }
            }

        });
        tradingPartnerName.setMaxLength(NumericConstants.THIRTY_SIX);
        tradingPartnerNo.setMaxLength(NumericConstants.THIRTY_SIX);
        resultsTableTP.addStyleName(Constant.TABLE_HEADER_STYLE);
        resetTP.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", "Are you sure you want to reset the page to default values?", new MessageBoxListener() {
                    @Override
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
                    LOGGER.error(e);
                } catch (CommitException e) {
                    LOGGER.error(e);
                } catch (Exception e) {
                    LOGGER.error(e);
                }

            }
        });
        LOGGER.debug("End of configureFields method");
    }

    /**
     * Adds the footer button.
     *
     * @return the horizontal layout
     */
    public HorizontalLayout addFooterButton() {
        final HorizontalLayout layout5 = new HorizontalLayout();

        LOGGER.debug("Entering addFooterButton method");
        layout5.setSpacing(true);
        layout5.setMargin(false);
        layout5.addComponent(getSelectButton());
        layout5.addComponent(getResetButton());
        layout5.addComponent(getCloseButton());
        LOGGER.debug("End of addFooterButton method");

        return layout5;
    }

    /**
     * Gets the select button.
     *
     * @return the select button
     */
    private Button getSelectButton() {

        LOGGER.debug("Entering getSelectButton method");
        select.setWidth(Constant.WIDTH);
        select.setImmediate(true);
        select.addClickListener(new Button.ClickListener() {
            /**
             * Called when button is clicked .
             *
             */

            @Override
            public void buttonClick(final Button.ClickEvent event) {

                if (resultsTableTP.getValue() == null) {
                    AbstractNotificationUtils.getErrorNotification("No Trading Partner selected", "Please select a Trading Partner from the list view.");
                } else {
                    final PMPYTradingPartnerDTO dto = (PMPYTradingPartnerDTO) resultsTableTP.getValue();
                    close();

                    tradingPartner.setValue(String.valueOf(dto.getTradingPartnerName()));
                    tradingPartner.setData(dto.getCompanySysId());

                    
                }
            }
        });
        LOGGER.debug("End of getSelectButton method");

        return select;
    }

    /**
     * Gets the reset button.
     *
     * @return the reset button
     */
    private Button getResetButton() {

        LOGGER.debug("Entering getResetButton method");
        reset.addClickListener(new Button.ClickListener() {
            /**
             * Called when button is clicked .
             *
             */

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", "Are you sure you want to reset the page to default values?", new MessageBoxListener() {
                    /**
                     * Called when the button is clicked .
                     *
                     */

                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(Constant.YES)) {
                            resultsTableTP.removeAllItems();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
        LOGGER.debug("End of getResetButton method");

        return reset;
    }

    /**
     * Gets the close button.
     *
     * @return the close button
     */
    private Button getCloseButton() {

        LOGGER.debug("Entering getCloseButton method");
        close.addClickListener(new Button.ClickListener() {
            /**
             * Called when the button is clicked .
             *
             */

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Close Confirmation", "Are you sure you want to close the trading partner lookup" + " ?", new MessageBoxListener() {
                    /**
                     * Called when the button is clicked .
                     *
                     */

                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(Constant.YES)) {
                            close();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
        LOGGER.debug("End of getCloseButton method");

        return close;
    }

    /**
     * Adds the space.
     *
     * @return the custom grid layout
     */

    /**
     * Adds the header button.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout addHeaderButton() {
        final HorizontalLayout layout3 = new HorizontalLayout();

        LOGGER.debug("Entering addHeaderButton method");
        layout3.setSpacing(true);
        layout3.addComponent(searchTP);
        layout3.addComponent(resetTP);
        LOGGER.debug("End of addHeaderButton method");

        return layout3;
    }

    /**
     * Adds the results table.
     *
     * @return the table
     */
    private Panel addResultsTable() {

        LOGGER.debug("Entering addResultsTable method");
        Panel panel = new Panel(Constant.RESULTS);

        resultsTableTP.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        resultsTableTP.setSelectable(true);
        resultsTableTP.setContainerDataSource(resultsTPBean);
        resultsTableTP.setVisibleColumns(tradingPartnerColumns);
        resultsTableTP.setColumnHeaders(tradingPartnerHeaders);
        resultsTableTP.setPageLength(NumericConstants.SIX);
        panel.setContent(resultsTableTP);
        LOGGER.debug("End of addResultsTable method");

        return panel;
    }

    /**
     * Search logic.
     *
     * @throws CommitException , SystemException
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void searchLogic() throws CommitException, SystemException {
        LOGGER.debug("Entering searchLogic method");
        if (StringUtils.isEmpty(tradingPartnerNo.getValue()) && StringUtils.isEmpty(tradingPartnerName.getValue().toString())) {
            AbstractNotificationUtils.getErrorNotification("No Search Criteria", "Please enter a value to search for.");
        } else {
            String tpNo = tradingPartnerNo.getValue();
            String tpName = tradingPartnerName.getValue();
            searchBinder.commit();
            resultsTPBean.removeAllItems();
            final List<PMPYTradingPartnerDTO> tpResult = nonMandatedLogic.tradingPartnerLookUp(tpNo, tpName, contractHolder);
            if (tpResult.isEmpty()) {
                AbstractNotificationUtils.getInfoNotification("No Results", "There were no results found that match the entered search criteria. \nPlease try again.");

            } else {
                resultsTPBean.addAll(tpResult);
                CommonUIUtils.getMessageNotification("Search Completed");
            }
        }
        LOGGER.debug("End of searchLogic method");
    }
}
