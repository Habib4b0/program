
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ADD_TRADING_PARTNER;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.CONTRACT_SELECTION;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_SUMMARY;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Mahesh J
 */
public class AddTPForm extends CustomWindow {

    SummaryTab summary;
    TabSheet tabSheet = new TabSheet();
    int tabPosition;
    SessionDTO session;
    UpdatedContractSelection updatedContractSelection;

    public AddTPForm(SessionDTO session) {
        this.session = session;
        this.updatedContractSelection = new UpdatedContractSelection(session, this);
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
        setCaption(ADD_TRADING_PARTNER.getConstant());
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
        tabSheet.addTab(updatedContractSelection, CONTRACT_SELECTION.getConstant(), null, 0);
        tabSheet.addTab(summary, TAB_SUMMARY.getConstant(), null, 1);
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                int tabPosition = event.getTabSheet().getTabPosition(tab);
                String tabName = tabSheet.getTab(tabPosition).getCaption();

                if (TAB_SUMMARY.getConstant().equals(tabName) && updatedContractSelection.isSummaryRefreshed()) {
                    summary.refreshCurrentTPDetails(StringUtils.EMPTY);
                    updatedContractSelection.setSummaryRefreshed(false);
                } else if (summary.isCurrentContractRefresh() && CONTRACT_SELECTION.getConstant().equals(tabName)) {
                    updatedContractSelection.refreshContractSelectionTable();
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
        logic.insertDataIntoTempTable(session.getUserId(), session.getSessionId(), session.getCompanyMasterSids(), StringUtils.EMPTY, true);
    }
}
