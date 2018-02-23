/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.additionalinformation.form.AdditionalInformationForm;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.CommonLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.view.DataSelectionView;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.OptionGroup;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class NationalAssumptionsForm.
 *
 * @author Vinodhini
 */
public class NationalAssumptionsForm extends CustomComponent {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 8263036364877609281L;
    private final String viewMode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NationalAssumptionsForm.class);

    /**
     * The tabSheet.
     */
    @UiField("tabSheet")
    private TabSheet tabSheet;

    /**
     * The button layout.
     */
    @UiField("buttonLayout")
    private HorizontalLayout buttonLayout;

    /**
     * The previous btn.
     */
    @UiField("previousBtn")
    private Button previousBtn;

    /**
     * The next btn.
     */
    @UiField("nextBtn")
    private Button nextBtn;

    /**
     * The close btn.
     */
    @UiField("closeBtn")
    private Button closeBtn;

    /**
     * The save btn.
     */
    @UiField("saveBtn")
    private Button saveBtn;

    /**
     * The tab position.
     */
    private int tabPosition = 0;

    /**
     * The national assumptions.
     */
    private final NationalAssumptions nationalAssumptions;
    /**
     * The medicaid ura.
     */
    private final MedicaidURA medicaidURA;

    /**
     * The fcp results.
     */
    private final FcpResults fcpResults;

    /**
     * The phs results.
     */
    private final PhsResults phsResults;

    /**
     * The additional information.
     */
    private final AdditionalInformationForm additionalInformation;

    /**
     * The data selection.
     */
    private final DataSelection dataSelection;
    /**
     * The tabsheet map.
     */
    private final Map<Integer, Boolean> tabsheetMap = new HashMap<>();

    private final CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem(DataSelectionDTO.class));

    private int lastPosition;

    private final String getmode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    private boolean naTabChange = true;

    private boolean dsFlag = true;

    /**
     * The tab position.
     */
    private int tempTabPosition = 0;
    private final SessionDTO sessionDTO;
    private final ExecutorService service = ThreadPool.getInstance().getService();
    
    private boolean isDataSelectionupdateforFcp;
    private boolean isDataSelectionupdatedforMedicaid;
    private boolean isDataSelectionupdatedPhs;
    
    
    public NationalAssumptionsForm(DataSelectionDTO dtoValue, OptionGroup mode,SessionDTO sessionDTO) throws SystemException, PortalException  {
        LOGGER.info("NationalAssumptionsForm Constructor initiated ");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/NationalAssumptionsForm.xml"), this));
        this.sessionDTO= sessionDTO;
        if (!sessionDTO.getCurrentTableNames().isEmpty()) {
            
            com.stpl.app.gtnforecasting.logic.CommonLogic.dropDynamicTables(sessionDTO.getUserId(), sessionDTO.getSessionId());
            sessionDTO.resetCurrentTableNames();
        }
        if (!Constant.VIEW.equalsIgnoreCase(getmode)) {
            QueryUtils.createTempTables(sessionDTO);
        }
        this.dataSelection = new DataSelection(dataSelectionBinder,sessionDTO);
        this.nationalAssumptions = new NationalAssumptions(sessionDTO);
        this.medicaidURA = new MedicaidURA(this,sessionDTO);
        this.fcpResults = new FcpResults(this,sessionDTO);
        this.phsResults = new PhsResults(this,sessionDTO);
        this.additionalInformation = new AdditionalInformationForm(Constant.NATIONAL_ASSUMPTION_SCREEN_NAME, sessionDTO.getProjectionId(), String.valueOf(mode.getValue()));
        
        if (Constant.EDIT_SMALL.equalsIgnoreCase(getmode) || Constant.VIEW.equalsIgnoreCase(getmode)) {
            CommonLogic logic = new CommonLogic();
            logic.deleteTempBySession(sessionDTO);
            callTempTableActivities();
        }
        if(!Constant.VIEW.equalsIgnoreCase(getmode)){
        callFileInsertProcedure();
        }
        init();
        setTabSecurity();
        LOGGER.info("NationalAssumptionsForm Constructor ends ");
    }

    /**
     * This method is used to initialize the components and to configure the
     * fields.
     */
    private void init() {
        LOGGER.info("NationalAssumptionsForm init() starts ");
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        addTab();
        configureFields();
        if (Constant.EDIT_SMALL.equalsIgnoreCase(getmode)) {
            saveBtn.setCaption("UPDATE");
        }
        if (Constant.EDIT_SMALL.equalsIgnoreCase(getmode) || Constant.VIEW.equalsIgnoreCase(getmode)) {
            if (Constant.VIEW.equalsIgnoreCase(getmode)) {
                saveBtn.setEnabled(false);
            }
            nationalAssumptions.saveNationalAssumptions(false);
        }
        LOGGER.info("NationalAssumptionsForm init() ends ");
    }

    /**
     * Used to configure the TabSheet for all classes in FFS.
     *
     * @return the tab sheet
     */
    protected TabSheet addTab() {
        LOGGER.info("NationalAssumptionsForm addTab() starts ");

        tabSheet.markAsDirty();
        tabSheet.markAsDirtyRecursive();
        tabSheet.addTab(dataSelection, "Data Selection", null, 0);
        tabSheet.addTab(nationalAssumptions, Constant.NATIONAL_ASSUMPTIONS_SCREEN, null, 1);
        tabSheet.addTab(medicaidURA, "Medicaid URA Results", null, NumericConstants.TWO);
        tabSheet.addTab(fcpResults, "FCP Results", null, NumericConstants.THREE);
        tabSheet.addTab(phsResults, "PHS Results", null, NumericConstants.FOUR);
        tabSheet.addTab(additionalInformation, "Additional Information", null, NumericConstants.FIVE);

        int tabCount = tabSheet.getComponentCount();
        tabsheetMap.clear();
        for (int i = 0; i < tabCount; i++) {
            if (i == 1) {
                tabsheetMap.put(i, Boolean.TRUE);
            } else {
                tabsheetMap.put(i, Boolean.FALSE);
            }
        }
        LOGGER.info("NationalAssumptionsForm addTab() ends ");
        return tabSheet;
    }

    /**
     * This method is used to configure the fields for validations and to load
     * default values.
     */
    protected void configureFields() {
        previousBtn.setEnabled(true);
        nextBtn.setEnabled(true);
        saveBtn.setEnabled(true);
        closeBtn.setEnabled(true);
        entry();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId =  sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + "All Tabs");
            if (tabItemHM.get("saveBtn") != null && tabItemHM.get("saveBtn").isFunctionFlag()) {
                saveBtn.setVisible(true);
            } else {
                saveBtn.setVisible(false);
            }
        } catch (PortalException | SystemException portal) {
            LOGGER.error(StringUtils.EMPTY,portal);
        } 
    }

    /**
     * For to set the default screen in tab sheet.
     *
     */
    public void entry() {
        tabSheet.setSelectedTab(1);
    }

   
    public void loadTabsheet(int position)  {
        LOGGER.info("Entering loadTabsheet method ");
        if (position == 0) {
            tabSheet.replaceComponent(dataSelection, dataSelection);
            tabSheet.setDescription("Data Selection");
        }
        if (position == 1) {
            tabSheet.replaceComponent(nationalAssumptions, nationalAssumptions);
        } else if (position == NumericConstants.TWO) {
            tabSheet.replaceComponent(medicaidURA, medicaidURA);
        } else if (position == NumericConstants.THREE) {
            tabSheet.replaceComponent(fcpResults, fcpResults);
        } else if (position == NumericConstants.FOUR) {
            tabSheet.replaceComponent(phsResults, phsResults);
        } else if (position == NumericConstants.FIVE) {
            tabSheet.replaceComponent(additionalInformation, additionalInformation);
        }
        LOGGER.info("End of loadTabsheet method ");
    }

    /**
     * Selected tab change.
     *
     * @param event the event
     */
    @UiHandler("tabSheet")
    public void selectedTabChange(SelectedTabChangeEvent event) {
        try {
            Tab tab = event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
            tabPosition = event.getTabSheet().getTabPosition(tab);
            if ((lastPosition == 0 && !"View".equalsIgnoreCase(getmode)) && (dataSelection.isChanged() && dsFlag)) {
                    dsFlag = false;
                    tempTabPosition = tabPosition;
                    tabSheet.setSelectedTab(0);
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            try {
                                    dataSelection.updateDataSelection();
                                    tabSheet.setSelectedTab(tempTabPosition);
                                    dsFlag = true;
                            } catch (Exception ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }

                        @Override
                        public void noMethod() {
                            Tab tabToReset = tabSheet.getTab(1);
                            tabSheet.removeTab(tabToReset);
                            tabSheet.addTab(nationalAssumptions, Constant.NATIONAL_ASSUMPTIONS_SCREEN, null, 1);
                            dsFlag = true;
                            /*Relaod the Default Value(Previously stored in DB) for Available & Selected Table in Data Selection Tab*/
                             dataSelection.setValues();
                        }
                    }.getConfirmationMessage(Constant.UPDATE_CONFIRMATION_ALERT, Constant.ADDEDREMOVED_NDCS_ALERT);
                 naTabChange = true;
                 isDataSelectionupdateforFcp= true;
                 isDataSelectionupdatedforMedicaid= true;
                 isDataSelectionupdatedPhs = true;
            }
            if (tabPosition > 0) {
                buttonLayout.addComponent(previousBtn, 1);
                buttonLayout.addComponent(nextBtn, NumericConstants.TWO);
                nextBtn.setEnabled(true);
            }
            if (tabPosition == NumericConstants.FIVE) {
                buttonLayout.removeComponent(nextBtn);
                buttonLayout.addComponent(previousBtn, NumericConstants.TWO);
                previousBtn.setEnabled(true);
            }
            if (tabPosition < NumericConstants.FIVE && tabPosition != 0) {
                buttonLayout.addComponent(previousBtn, 1);
                buttonLayout.addComponent(nextBtn, NumericConstants.TWO);
                previousBtn.setEnabled(true);
            }
            if (tabPosition == 0 && lastPosition!=0 ) {
                buttonLayout.removeComponent(previousBtn);
                buttonLayout.addComponent(nextBtn, 1);
                nextBtn.setEnabled(true);
                dataSelection.setValues();
                dataSelection.disableFields(getmode);
            }
            if (tabPosition == 1) {
                naTabChange = true;
                nationalAssumptions.priceTypeDdlb.focus();
            }
            if (tabPosition == NumericConstants.TWO) {

                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
                if (isDataSelectionupdatedforMedicaid) {
                    medicaidURA.generateLogic();
                   isDataSelectionupdatedforMedicaid=false;
                }            
                medicaidURA.historyDdlb.focus();
            }

            if (tabPosition == NumericConstants.THREE) {
                fcpResults.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
                if (isDataSelectionupdateforFcp) {
                    fcpResults.generateLogic();
                    isDataSelectionupdateforFcp=false;
                }
            }

            if (tabPosition == NumericConstants.FOUR) {

                phsResults.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
                if (isDataSelectionupdatedPhs) {
                    phsResults.generateLogic();
                    isDataSelectionupdatedPhs=false;
                }
            }
            additionalInformation.setUploderValue();
            lastPosition = tabPosition;
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
    /**
     * Close btn.
     *
     * @param event the event
     */
    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        String closeMsg = "Are you sure you want to close the National Assumptions screen? All unsaved data will be lost.";
        if (Constant.VIEW.equalsIgnoreCase(getmode)) {
            closeMsg = "Are you sure you want to close the National Assumptions screen?";
        }
        closeBtnLogic(closeMsg);
    }

    /**
     * Previous btn.
     *
     * @param event the event
     */
    @UiHandler("previousBtn")
    public void previousBtn(Button.ClickEvent event) {
        try {
            if (tabPosition != 0) {
                int i = 1;
                while ((lastPosition - i) > 0 && !tabSheet.getTab(lastPosition - i).isVisible()) {
                    i++;
                }
                tabSheet.setSelectedTab(lastPosition - i);
            }
            UI.getCurrent().setFocusedComponent(UI.getCurrent());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Next btn.
     *
     * @param event the event
     */
    @UiHandler("nextBtn")
    public void nextBtn(Button.ClickEvent event) {
        try {

            if (tabPosition != NumericConstants.FIVE) {
                if (lastPosition == 0 && !"View".equalsIgnoreCase(getmode)) {
                    if (dataSelection.isChanged()) {
                        new AbstractNotificationUtils() {
                            @Override
                            public void yesMethod() {
                                try {
                                    dataSelection.updateDataSelection();
                                    int i = 1;
                                    while ((lastPosition + i) < tabSheet.getComponentCount() && !tabSheet.getTab(lastPosition + i).isVisible()) {
                                        i++;
                                    }
                                    tabSheet.setSelectedTab(lastPosition + i);
                                } catch (Exception ex) {
                                    LOGGER.error(ex.getMessage());
                                }
                            }

                            @Override
                            public void noMethod() {
                                tabSheet.setSelectedTab(0);
                            }
                        }.getConfirmationMessage(Constant.UPDATE_CONFIRMATION_ALERT, Constant.ADDEDREMOVED_NDCS_ALERT);
                        naTabChange = true;
                        isDataSelectionupdateforFcp = true;
                        isDataSelectionupdatedforMedicaid = true;
                        isDataSelectionupdatedPhs = true;
                    } else {
                        tabSheet.setSelectedTab(tabPosition + 1);
                    }
                } else {
                    tabSheet.setSelectedTab(tabPosition + 1);
                }

            }
            if (tabPosition == 1) {
                naTabChange = true;
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
            }
            if (tabPosition == NumericConstants.TWO) {
                medicaidURA.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
                if (isDataSelectionupdatedforMedicaid) {
                    medicaidURA.generateLogic();
                    isDataSelectionupdatedforMedicaid = false;
                }
            }

            if (tabPosition == NumericConstants.THREE) {
                fcpResults.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
                if (isDataSelectionupdateforFcp) {
                    fcpResults.generateLogic();
                    isDataSelectionupdateforFcp = false;
                }
            }

            if (tabPosition == NumericConstants.FOUR) {
                phsResults.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
                if (isDataSelectionupdatedPhs) {
                    phsResults.generateLogic();
                    isDataSelectionupdatedPhs = false;
                }
            }
            UI.getCurrent().setFocusedComponent(UI.getCurrent());
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Save btn.
     *
     * @param event the event
     */
    @UiHandler("saveBtn")
    public void saveBtn(Button.ClickEvent event)  {
        final String projName = sessionDTO.getProjectionName();
        boolean updateflag = dataSelection.isChanged();
        if (updateflag) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        dataSelection.updateDataSelection();
                        saveProjection();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }

                @Override
                public void noMethod() {
                    //Default method
                }
            }.getConfirmationMessage(Constant.UPDATE_CONFIRMATION_ALERT, Constant.ADDEDREMOVED_NDCS_ALERT);
        }

        if (!updateflag) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    // do nothing
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    try {
                        saveProjection();
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }

            }.getConfirmationMessage("Save Confirmation", "Are you sure you want to save the projection?");
        }

        UI.getCurrent().setFocusedComponent(UI.getCurrent());
    }

    private void callTempTableActivities()  {
        CommonLogic logic = new CommonLogic();
        logic.clearTemp(sessionDTO);
        if(!Constant.VIEW.equalsIgnoreCase(viewMode)){
        logic.tempInsert(sessionDTO);
        }
    }

    void closeBtnLogic(String closeMsg) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                CommonLogic logic = new CommonLogic();
                try {
                    logic.deleteTempBySession(sessionDTO);
                    logic.clearTemp(sessionDTO);
                    com.stpl.app.gtnforecasting.logic.CommonLogic.dropDynamicTables(sessionDTO.getUserId(),sessionDTO.getSessionId());
                    sessionDTO.resetCurrentTableNames();
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
                getUI().getNavigator().navigateTo(DataSelectionView.NAME);
            }

            @Override
            public void noMethod() {
                // TODO Auto-generated method stub
            }
        }.getConfirmationMessage("Close Confirmation",
                closeMsg);

    }

    private void saveProjection()  {
        try{
        final String projName = sessionDTO.getProjectionName();
        final int projectionId = sessionDTO.getProjectionId();
        nationalAssumptions.saveNationalAssumptions(true);
        medicaidURA.saveMedicaidSelections();
        fcpResults.saveFcpSelections();
        phsResults.savePhsSelections();
        additionalInformation.saveNotesInformation(projectionId, Constant.NATIONAL_ASSUMPTION_SCREEN_NAME);
        CommonLogic logic = new CommonLogic();
        logic.saveTempToMain(sessionDTO);
        logic.saveBaseYeaToItemMaster(sessionDTO);
        logic.saveTempNDCToMain(sessionDTO);
        nationalAssumptions.saveDeletedPrice();
        nationalAssumptions.saveNationalAssumptions(false);
        CommonUIUtils.getMessageNotification(projectionId + "," + projName + " has been successfully saved");
        saveBtn.setCaption("UPDATE");
    }
        catch(PortalException e){
            LOGGER.error(e.getMessage());
        }
    }

    public void naSaveProcedure() throws NamingException, SQLException  {
         if(!Constant.VIEW.equalsIgnoreCase(viewMode)){
        nationalAssumptions.saveNationalAssumptions(true);
        nationalAssumptions.callCumulativeCalculation();
        nationalAssumptions.ndcProcedure();
        nationalAssumptions.callNaProcedure();
        }

    }

    private void setTabSecurity() throws PortalException, SystemException  {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId =  sessionDTO.getUserId();

        Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessTabPermission(userId, Constant.NATIONAL_ASSUMPTIONS_SCREEN);
        if (functionPsHM.get(FunctionNameUtil.NM_DATA_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_DATA_TAB)).isTabFlag()) {
            tabSheet.getTab(0).setVisible(Boolean.FALSE);

        }
        if (functionPsHM.get(FunctionNameUtil.NA_NATIONAL_ASUMPTIONS_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NA_NATIONAL_ASUMPTIONS_TAB)).isTabFlag()) {
            tabSheet.getTab(1).setVisible(Boolean.FALSE);
            tabSheet.getTab(NumericConstants.TWO).setVisible(Boolean.FALSE);
            tabSheet.getTab(NumericConstants.THREE).setVisible(Boolean.FALSE);
            tabSheet.getTab(NumericConstants.FOUR).setVisible(Boolean.FALSE);
        }
        if (functionPsHM.get(FunctionNameUtil.MEDICAID_URA_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.MEDICAID_URA_TAB)).isTabFlag()) {
            tabSheet.getTab(NumericConstants.TWO).setVisible(Boolean.FALSE);
        }
        if (functionPsHM.get(FunctionNameUtil.FCP_RESULTS_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.FCP_RESULTS_TAB)).isTabFlag()) {
            tabSheet.getTab(NumericConstants.THREE).setVisible(Boolean.FALSE);
        }
        if (functionPsHM.get(FunctionNameUtil.PHS_RESULTS_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.PHS_RESULTS_TAB)).isTabFlag()) {
            tabSheet.getTab(NumericConstants.FOUR).setVisible(Boolean.FALSE);
        }

    }

    public void tabOrder(int index) {
        saveBtn.setTabIndex(index + 1);
        previousBtn.setTabIndex(index + NumericConstants.TWO);
        nextBtn.setTabIndex(index + NumericConstants.THREE);
        closeBtn.setTabIndex(index + NumericConstants.FOUR);
    }
        private void callFileInsertProcedure() {
            LOGGER.info("sessionDTO.getProjectionId() = {}" , sessionDTO.getProjectionId());
            LOGGER.info("sessionDTO.getUserId() = {} " , sessionDTO.getUserId());
            LOGGER.info("sessionDTO.getSessionId() = {} " , sessionDTO.getSessionId());
            sessionDTO.addFutureMap(Constant.NA_FILE_INSERT, new Future[]{service.submit(CommonUtil.getInstance().createRunnable(Constant.PROCEDURE_CALL, SalesUtils.PRC_NA_WAC_DATA, sessionDTO.getProjectionId(), sessionDTO.getUserId(), sessionDTO.getSessionId(),NATIONAL_ASSUMPTIONS.getConstant()))
        });
}

}
