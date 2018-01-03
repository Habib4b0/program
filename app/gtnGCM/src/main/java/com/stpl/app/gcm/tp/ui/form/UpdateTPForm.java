/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.CURRENT_CONTRACT;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_SUMMARY;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TRADING_PARTNER_UPDATE;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Maheshj
 */
public class UpdateTPForm extends CustomWindow {

    private final UpdatedContractSelection updateContract;
    private final SummaryTab summary;
    private TabSheet tabSheet = new TabSheet();
    private int tabPosition;
    private final SessionDTO session;

    public UpdateTPForm(SessionDTO session) {
        this.session = session;
        this.updateContract = new UpdatedContractSelection(session, this);
        this.summary = new SummaryTab(session, this);
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(Constants.ZERO);
        setPositionY(Constants.ZERO);
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-fe");
        setCaption(TRADING_PARTNER_UPDATE.getConstant());
        setClosable(true);
        insertDataIntoTempTable();
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
        buildScreen();
    }

    /**
     * To initialize and configure the tabLazyLoadMap for lazy tab loading.
     */
    protected void initializeTabs() {
        tabSheet = new TabSheet();
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        tabSheet.addTab(updateContract, CURRENT_CONTRACT.getConstant(), null, 0);
        tabSheet.addTab(summary, TAB_SUMMARY.getConstant(), null, 1);
        tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {

            @Override
            public void selectedTabChange(SelectedTabChangeEvent event) {
                final Tab tab = (Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                String tabName = tabSheet.getTab(tabPosition).getCaption();
                if (TAB_SUMMARY.getConstant().equals(tabName) && updateContract.isSummaryRefreshed()) {
                    summary.refreshCurrentTPDetails(StringUtils.EMPTY);
                    updateContract.setSummaryRefreshed(false);
                } else if (summary.isCurrentContractRefresh() && CURRENT_CONTRACT.getConstant().equals(tabName)) {
                    updateContract.refreshContractSelectionTable();
                    summary.setCurrentContractRefresh(false);
                }
            }
        });
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
        logic.insertDataIntoTempTable(session.getUserId(), session.getSessionId(), session.getCompanyMasterSids(), StringUtils.EMPTY, false);
    }

}
