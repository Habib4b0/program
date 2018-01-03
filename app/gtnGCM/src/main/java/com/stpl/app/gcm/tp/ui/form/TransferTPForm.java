/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import com.stpl.app.gcm.util.Message;
import com.stpl.app.gcm.util.MessageUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Lokeshwari
 */
public class TransferTPForm extends CustomWindow {

   private TabSheet tabSheet = new TabSheet();

   private UpdatedContractSelection currentContractSelectionTab;
   private UpdatedContractSelection transferContractSelectionTab;
   private SummaryTab summaryTab;
   private CustomerSelection customerSelectionTab;
   private final SessionDTO session;

    private final ContractSelectionLogic logic = new ContractSelectionLogic();
    private String lastTabName = StringUtils.EMPTY;
    private int tabPosition;
    private int tabCount = 0;
    private boolean automaticTabChangedFlag = false;
    private boolean salesCopyFlag = true;
    private boolean salesRemoveFlag = false;

    public TransferTPForm(SessionDTO session) {
        this.session = session;
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(Constants.ZERO);
        setPositionY(Constants.ZERO);
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-fe");
        setClosable(true);
        setCaption(session.getModuleName());
        if (session.getModuleName().equals(TRANSFER_TRADING_PARTNER.getConstant())) {
            insertDataIntoTempTable();
        }
        addContent();
    }

    public void enter() {
        return;
    }

    /**
     * Adds the content to the screen.
     *
     * @throws Exception
     * @throws SystemException
     */
    private void addContent() {
        initializeTabs();
        configureFields();
        buildScreen();
    }

    private void configureFields() {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                try {
                    final Tab tab = (Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                    tabPosition = event.getTabSheet().getTabPosition(tab);
                    String tabName = tabSheet.getTab(tabPosition).getCaption();

                    if (automaticTabChangedFlag) {
                        return;
                    }

                    if (customerSelectionTab!=null && tabPosition > tabSheet.getTabPosition(tabSheet.getTab(customerSelectionTab)) && !customerSelectionTab.tabChangeCheck()) {
                        automaticTabChangedFlag = true;
                        tabSheet.setSelectedTab(customerSelectionTab);
                        automaticTabChangedFlag = false;
                        forceTabRefresh();
                        return;
                    }

                    if(lastTabName.equals(TAB_CUSTOMER_SELECTION.getConstant()) && customerSelectionTab.tabChangeCheck()){
                        customerSelectionTab.tabChangeLogic();
                    }

                    if (currentContractSelectionTab!=null 
                            && tabPosition > tabSheet.getTabPosition(tabSheet.getTab(currentContractSelectionTab))
                            && !logic.isAnyDataSubmitted(session.getUserId(), session.getSessionId(),session.getModuleName(), TAB_CURRENT_CONTRACT.getConstant())) {
                        automaticTabChangedFlag = true;
                        AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.NO_COMPANY_SUBMIT), MessageUtil.getErrorCode(Message.SUBMIT_TRANSFER_FROM));
                        tabSheet.setSelectedTab(currentContractSelectionTab);
                        forceTabRefresh();
                        automaticTabChangedFlag = false;
                        return;
                    }

                    if (transferContractSelectionTab != null
                            && tabPosition > tabSheet.getTabPosition(tabSheet.getTab(transferContractSelectionTab))
                            && !logic.isAnyDataSubmitted(session.getUserId(), session.getSessionId(), session.getModuleName(), TAB_TRANSFER_CONTRACT.getConstant())) {
                        automaticTabChangedFlag = true;
                        AbstractNotificationUtils.getErrorNotification(MessageUtil.getErrorCode(Message.NO_COMPANY_SUBMIT), MessageUtil.getErrorCode(Message.SUBMIT_TRANSFER_TO));
                        tabSheet.setSelectedTab(transferContractSelectionTab);
                        forceTabRefresh();
                        automaticTabChangedFlag = false;
                        return;
                    }
                    allTableRefreshOnTabChange(tabName);

                } catch (Exception e) {
                   LOGGER.error(e);
                }
            }

        });
    }

    private void forceTabRefresh() {
        tabSheet.removeTab(tabSheet.getTab(tabCount));
        tabSheet.addTab(summaryTab, TAB_SUMMARY.getConstant(), null, tabCount);
    }

    /**
     * To initialize and configure the tabLazyLoadMap for lazy tab loading.
     */
    protected void initializeTabs() {
        currentContractSelectionTab = new UpdatedContractSelection(session, this, false);
        transferContractSelectionTab = new UpdatedContractSelection(session, this, true);
        summaryTab = new SummaryTab(session, this);

        if (session.getModuleName().equals(PROJECTION_DETAILS_TRANSFER.getConstant())) {
            customerSelectionTab = new CustomerSelection(session, this);
            tabSheet.addTab(customerSelectionTab, TAB_CUSTOMER_SELECTION.getConstant(), null, tabCount++);
        }
        tabSheet.addTab(currentContractSelectionTab, TAB_CURRENT_CONTRACT.getConstant(), null, tabCount++);
        tabSheet.addTab(transferContractSelectionTab, TAB_TRANSFER_CONTRACT.getConstant(), null, tabCount++);
        tabSheet.addTab(summaryTab, TAB_SUMMARY.getConstant(), null, tabCount);
        
        lastTabName = tabSheet.getTab(0).getCaption();
    }

    public void buildScreen() {
        final VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponent(tabSheet);
        setContent(vLayout);

    }

    public TabSheet getTabSheet() {
        return tabSheet;
    }

    public void setTabSheet(TabSheet tabSheet) {
        this.tabSheet = tabSheet;
    }

    public int getTabPosition() {
        return tabPosition;
    }

    public void setTabPosition(int tabPosition) {
        this.tabPosition = tabPosition;
    }

    private void insertDataIntoTempTable() {
        ContractSelectionLogic logic = new ContractSelectionLogic();
        logic.insertDataIntoTempTable(session.getUserId(), session.getSessionId(), session.getCompanyMasterSids(), TAB_CURRENT_CONTRACT.getConstant(), false);
        logic.insertDataIntoTempTable(session.getUserId(), session.getSessionId(), session.getCompanyMasterSids(), TAB_TRANSFER_CONTRACT.getConstant(), true);
    }

    public boolean isAutomaticTabChangedFlag() {
        return automaticTabChangedFlag;
    }

    public void setAutomaticTabChangedFlag(boolean automaticTabChangedFlag) {
        this.automaticTabChangedFlag = automaticTabChangedFlag;
    }

    public void allTableRefreshOnTabChange(String tabName) {
        if (TAB_SUMMARY.getConstant().equals(tabName)) {
            if (currentContractSelectionTab.isSummaryRefreshed()) {
                summaryTab.refreshCurrentTPDetails(TAB_CURRENT_CONTRACT.getConstant());
                currentContractSelectionTab.setSummaryRefreshed(false);
            }
            if (transferContractSelectionTab.isSummaryRefreshed()) {
                summaryTab.refreshTransferTPDetails();
                transferContractSelectionTab.setSummaryRefreshed(false);
            }
        } else if ((summaryTab.isCurrentContractRefresh() || (customerSelectionTab != null && customerSelectionTab.isTransferFlag())) && TAB_CURRENT_CONTRACT.getConstant().equals(tabName)) {
            currentContractSelectionTab.refreshContractSelectionTable();
            summaryTab.setCurrentContractRefresh(false);
            if (customerSelectionTab != null) {
                customerSelectionTab.setTransferFlag(false);
            }
        } else if ((summaryTab.isTransferContractRefresh() || (customerSelectionTab != null && customerSelectionTab.isTransferFlag())) && TAB_TRANSFER_CONTRACT.getConstant().equals(tabName)) {
            transferContractSelectionTab.refreshContractSelectionTable();
            summaryTab.setTransferContractRefresh(false);
            if (customerSelectionTab != null) {
                customerSelectionTab.setTransferFlag(false);
            }
        }
        lastTabName = tabName;
    }

    public boolean isSalesCopyFlag() {
        return salesCopyFlag;
    }

    public void setSalesCopyFlag(boolean salesCopyFlag) {
        this.salesCopyFlag = salesCopyFlag;
    }

    public boolean isSalesRemoveFlag() {
        return salesRemoveFlag;
    }

    public void setSalesRemoveFlag(boolean salesRemoveFlag) {
        this.salesRemoveFlag = salesRemoveFlag;
    }

}
