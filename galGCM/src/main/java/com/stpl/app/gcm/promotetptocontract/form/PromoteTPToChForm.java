/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_CURRENT_CONTRACT;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class PromoteTPToChForm extends CustomComponent implements View {

    private PromoteTpToChDto promoteTpToChDto;
    public static final SimpleDateFormat DBDate = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    final ErrorLabel errorMsg = new ErrorLabel();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(PromoteTPToChForm.class);
    /**
     * The data selection binder.
     */
    public CustomFieldGroup promoteTpBinder;
    boolean tabFlag = false;
    /**
     * The tab sheet.
     */
    @UiField("tabSheet")
    TabSheet tabSheet;
    /**
     * The tab lazy load map.
     */
    Map<Integer, Boolean> tabLazyLoadMap = new HashMap<Integer, Boolean>();
    /**
     * The close.
     */
    @UiField("nextBtn")
    Button nextBtn;
    @UiField("closeBtn")
    Button closeBtn;
    @UiField("previousBtn")
    Button previousBtn;
    @UiField("transferBtn")
    Button transferBtn;
    /**
     * Position of the tab.
     */
    int tabPosition = 0;
    private CurrentContractSelection currentContractSelection;
    private TransferContract transferContract;
    private Summary summary;
    ExtFilterTable resultTable;
    SessionDTO session;
    PromoteTpToChWindow promoteWindow;

    /**
     *
     * @param screenIndicator
     * @param baseRateDTO
     * @param custom
     * @throws Exception
     */
    public PromoteTPToChForm(CustomFieldGroup promoteTpToChBinder, PromoteTpToChDto promoteTpToChDto, SessionDTO session, PromoteTpToChWindow editWindow, final ExtFilterTable resultTable) throws SystemException, Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/PromoteTPToChForm.xml"), this));
        this.promoteTpToChDto = promoteTpToChDto;
        this.promoteTpBinder = promoteTpToChBinder;
        this.resultTable = resultTable;
        this.promoteWindow = editWindow;
        this.session = session;
        configureFields();
        init();
        addTab();
        tabSheet.setSelectedTab(0);
        insertDataToTempTable();
    }

    private void init() throws Exception {
        this.currentContractSelection = new CurrentContractSelection(session, resultTable, tabPosition, tabSheet);
        this.transferContract = new TransferContract(session, resultTable);
        this.summary = new Summary(session, resultTable);
    }

    private void addTab() {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        tabSheet.markAsDirty();
        tabSheet.markAsDirtyRecursive();
        tabSheet.addTab(currentContractSelection, "Current Contract", null, 0);
        tabSheet.addTab(transferContract, "Transfer Contract", null, 1);
        tabSheet.addTab(summary, "Summary", null, 2);

        attachTabChangeListener();
    }

    private void attachTabChangeListener() {
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                buttonEnableLogic(tabPosition, tabSheet.getComponentCount() - 1);
                String tabName = tabSheet.getTab(tabPosition).getCaption();
                try {
                    if ("Transfer Contract".equals(tabName)) {
                        transferContract.loadContractCompResultsTable();
                    }
                    if ("Summary".equals(tabName)) {
                        summary.loadCurrentTPDetails();
                        summary.transferTPDetails();
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                   
                }
            }
        });
    }

    /**
     * Button Enable and Disable Logic
     *
     * @param tabPosition
     * @param i
     */
    protected void buttonEnableLogic(int tabPosition, int i) {
        if (tabPosition == 0) {
            previousBtn.setVisible(false);
            transferBtn.setVisible(false);
        } else {
            previousBtn.setVisible(true);
        }
        if (tabPosition == 2) {
            nextBtn.setVisible(false);
            transferBtn.setVisible(true);
        } else {
            nextBtn.setVisible(true);
            transferBtn.setVisible(false);
        }
    }

   
    /**
     * Configures the components.
     */
    protected void configureFields() {
        try {
            previousBtn.setVisible(false);
            transferBtn.setVisible(false);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     *
     * @param event
     */
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        tabSheet.setSelectedTab(0);
    }

    public Component getContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/baseRateForm.xml"), this));
        configureFields();
        Panel panel = new Panel();
        panel.setContent(layout);
        return panel;
    }

    /**
     * Next Button Click Logic
     *
     * @param event
     */
    @UiHandler("nextBtn")
    public void nextTab(Button.ClickEvent event) {
        btnNextLogic();
    }

    @UiHandler("transferBtn")
    public void transferBtnLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Transfer Company Confirmation", " Are you sure you want to Promote the Selected Company?\n ", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        String sessionId = String.valueOf(session.getSessionId());
                        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
                        CommonLogic logic = new CommonLogic();
                        List<String> masterids = new ArrayList<String>();
                        masterids.add(session.getCompanyMasterSid());
                        List<Object[]> projCOntractIds= CommonLogic.getProjectionIdForPromoteCustomer(session.getSessionId(), session.getUserId());
                        int  selectedProjectionId = Integer.parseInt(String.valueOf(projCOntractIds.get(0)[0]));
                        session.setContractMasterSid(String.valueOf(session.getContractSystemId()));
                        List<String> existingProjection = logic.copyProjection(selectedProjectionId, false, null, null, null);
                        List<String> projectionWithNewContract = logic.generateNewProjection(userId, sessionId, selectedProjectionId, masterids, true, false);
                        String copiedToProjId = String.valueOf(projectionWithNewContract.get(2));
                        Integer copiedFromProjId = Integer.valueOf(String.valueOf(existingProjection.get(2)));
                        CommonLogic.insertInputsBeforeTranfer(selectedProjectionId, copiedFromProjId, Integer.valueOf(copiedToProjId), Integer.valueOf(copiedToProjId), session.getContMasteSid(), Integer.valueOf(session.getContractMasterSid()), session.getCompanyMasterSid(), DBDate.format(new Date()), DBDate.format(new Date()), true, sessionId);
                        CommonLogic.callPromoteProcedure(sessionId);
                        String query="select PROJECTION_NAME from PROJECTION_MASTER where PROJECTION_MASTER_SID in ("+copiedFromProjId+","+copiedToProjId+")";
                        List list=HelperTableLocalServiceUtil.executeSelectQuery(query);
                        String projName=StringUtils.EMPTY;
                        String copiedProjection=StringUtils.EMPTY;
                       if(list!=null && list.size()>0){
                           projName=String.valueOf(list.get(0));
                          copiedProjection=String.valueOf(list.get(1));
                         AbstractNotificationUtils.getAlertNotification("Info","New Projection has been created with Name of:"+projName+","+copiedProjection);
                     }
                    } catch (Exception ex) {
                       LOGGER.error(ex.getMessage());
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);

       LOGGER.info("Selected TP Promoted Successfully !");
    }

    /**
     * Next Button Click Logic
     *
     */
    protected void btnNextLogic() {
       if (tabPosition == 1) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        transferContract.saveContract();
                        tabSheet.setSelectedTab(tabPosition + 1);
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }

                @Override
                public void noMethod() {
                }
            }.getConfirmationMessage("Save", "Do You Want to Save Contract?");



        }
        if (tabPosition == 0) {
            ExtPagedTable table = currentContractSelection.getCurrentTradingPartnerTable2();
            transferContract.loadTransferComponenttable(table);
            tabSheet.setSelectedTab(tabPosition + 1);
        }
       
       UI.getCurrent().setFocusedComponent(UI.getCurrent());
    }

    @UiHandler("previousBtn")
    public void previousTab(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    btnPrevLogic();
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Update confirmation", "Customer values have changed. All other tabs will be updated \n and unsaved data will be lost. Continue?");
    }

    protected void btnPrevLogic() {
        tabSheet.setSelectedTab(tabPosition - 1);

        UI.getCurrent().setFocusedComponent(UI.getCurrent());
    }

    @UiHandler("closeBtn")
    public void closeButtonLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Close Confirmation", " Are you sure you want to close out? No values will be saved.", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        promoteWindow.close();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    private void insertDataToTempTable() {
        ContractSelectionLogic logic = new ContractSelectionLogic();
        List<String> companyMasterSid = new ArrayList<String>();
        companyMasterSid.add(session.getCompanyMasterSid());
        logic.insertDataIntoTempTable(session.getUserId(), session.getSessionId(), companyMasterSid, TAB_CURRENT_CONTRACT.getConstant(), false);
    }

    public boolean callCcpInsertProcedure(String sessionId) {
        LOGGER.info("calling CcpInsertProcedure");
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        String sessionValue = session.getSessionId();
        int sessionIdValue = Integer.valueOf(sessionValue);

        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            } else {
                LOGGER.info("Failed to lookup datasource.");
            }
            if (connection != null) {

                LOGGER.info("Got Connection " + connection.toString() + ", ");
                statement = connection.prepareCall("{call PRC_CCP_POPULATION('" + sessionIdValue + "')}");
                statement.execute();
            }
        } catch (Exception ex) {

            LOGGER.error(ex.getMessage());
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.info("exiting CcpInsertProcedure");
        return true;
    }
}
