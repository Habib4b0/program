/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.ui.form;

import com.stpl.app.galforecasting.additionalinformation.form.AdditionalInformationForm;
import com.stpl.app.galforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.CommonLogic;
import com.stpl.app.galforecasting.nationalassumptions.ui.view.DataSelectionView;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.USER_ID;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.FunctionNameUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import java.util.HashMap;
import java.util.Map;
import org.jboss.logging.Logger;
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

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NationalAssumptionsForm.class);

    /**
     * The tabSheet.
     */
    @UiField("tabSheet")
    TabSheet tabSheet;

    /**
     * The tabSheet map.
     */
    Map<Integer, Boolean> tabSheetMap = new HashMap<Integer, Boolean>();
    /**
     * The button layout.
     */
    @UiField("buttonLayout")
    HorizontalLayout buttonLayout;

    /**
     * The previous btn.
     */
    @UiField("previousBtn")
    Button previousBtn;

    /**
     * The next btn.
     */
    @UiField("nextBtn")
    Button nextBtn;

    /**
     * The close btn.
     */
    @UiField("closeBtn")
    Button closeBtn;

    /**
     * The save btn.
     */
    @UiField("saveBtn")
    Button saveBtn;

    /**
     * The tab position.
     */
    public int tabPosition = 0;

    /**
     * The national assumptions.
     */
    private NationalAssumptions nationalAssumptions;
    /**
     * The medicaid ura.
     */
    private MedicaidURA medicaidURA;

    /**
     * The fcp results.
     */
    private FcpResults fcpResults;

    /**
     * The phs results.
     */
    private PhsResults phsResults;

    /**
     * The additional information.
     */
    private AdditionalInformationForm additionalInformation;

    /**
     * The data selection.
     */
    private DataSelection dataSelection;
    /**
     * The tabsheet map.
     */
    Map<Integer, Boolean> tabsheetMap = new HashMap<Integer, Boolean>();

    CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem(DataSelectionDTO.class));

    /**
     * The mode.
     */
    OptionGroup mode;

    /**
     * The data selection index.
     */
    private DataSelectionIndex dataSelectionIndex;

    /**
     * The dto value.
     */
    DataSelectionDTO dtoValue;

    int lastPosition;

    public String getmode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    boolean naTabChange = true;

    boolean dsFlag = true;

    /**
     * The tab position.
     */
    public int tempTabPosition = 0;
    FieldGroup binder;

    TabSheet.SelectedTabChangeListener tabSheetListner;

    /**
     * Instantiates a new national assumptions form.
     *
     * @param dtoValue the dto value
     * @param mode the mode
     */
    public NationalAssumptionsForm(DataSelectionDTO dtoValue, OptionGroup mode) throws Exception {
        LOGGER.info("NationalAssumptionsForm Constructor initiated ");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/NationalAssumptionsForm.xml"), this));
        this.dtoValue = dtoValue;
        this.mode = mode;
        this.dataSelection = new DataSelection(dtoValue, mode, dataSelectionBinder);
        this.nationalAssumptions = new NationalAssumptions();
        this.medicaidURA = new MedicaidURA(this);
        this.fcpResults = new FcpResults(this);
        this.phsResults = new PhsResults(this);
        this.additionalInformation = new AdditionalInformationForm("National Assumption", "National Assumption", dtoValue.getProjectionId(), String.valueOf(mode.getValue()));
        if (Constant.EDIT_SMALL.equalsIgnoreCase(getmode) || Constant.VIEW.equalsIgnoreCase(getmode)) {
            CommonLogic logic = new CommonLogic();
            logic.deleteTempBySession();
            callTempTableActivities();
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
        this.dataSelectionIndex = new DataSelectionIndex(dtoValue, dataSelectionBinder, mode);
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
        tabSheet.addTab(nationalAssumptions, "National Assumptions", null, 1);
        tabSheet.addTab(medicaidURA, "Medicaid URA Results", null, 2);
        tabSheet.addTab(fcpResults, "FCP Results", null, 3);
        tabSheet.addTab(phsResults, "PHS Results", null, 4);
        tabSheet.addTab(additionalInformation, "Additional Information", null, 5);
        additionalInformation.setDescription("Additional Information");

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
        tabSheet.setImmediate(true);
        entry();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID.getConstant()));
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + "All Tabs");
            if (tabItemHM.get("saveBtn") != null && tabItemHM.get("saveBtn").isFunctionFlag()) {
                saveBtn.setVisible(true);
            } else {
                saveBtn.setVisible(false);
            }
        } catch (PortalException portal) {
            LOGGER.error(portal.getMessage());
        } catch (SystemException system) {
            LOGGER.error(system.getMessage());
        }
    }

    /**
     * For to set the default screen in tab sheet.
     *
     */
    public void entry() {
        tabSheet.setSelectedTab(1);
    }

    /**
     * method to load Tab sheet.
     *
     * @param position the position
     * @throws Exception the exception
     */
    public void loadTabsheet(int position) throws Exception {
        LOGGER.info("Entering loadTabsheet method ");
        if (position == 0) {
            tabSheet.replaceComponent(dataSelection, dataSelection);
            tabSheet.setDescription("Data Selection");
        }
        if (position == 1) {
            tabSheet.replaceComponent(nationalAssumptions, nationalAssumptions);
        } else if (position == 2) {
            tabSheet.replaceComponent(medicaidURA, medicaidURA);
        } else if (position == 3) {
            tabSheet.replaceComponent(fcpResults, fcpResults);
        } else if (position == 4) {
            tabSheet.replaceComponent(phsResults, phsResults);
        } else if (position == 5) {
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
            if (lastPosition == 0 && !"View".equalsIgnoreCase(getmode)) {
                if (dataSelection.isChanged() && dsFlag) {
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
                            tabSheet.addTab(nationalAssumptions, "National Assumptions", null, 1);
                            dsFlag = true;
                            /*Relaod the Default Value(Previously stored in DB) for Available & Selected Table in Data Selection Tab*/
                             dataSelection.setValues();
                        }
                    }.getConfirmationMessage("Update confirmation", "You have added/removed NDC's from the current Projection. Are you sure you want to continue?");
            }
            }
            if (tabPosition > 0) {
                buttonLayout.addComponent(previousBtn, 1);
                buttonLayout.addComponent(nextBtn, 2);
                nextBtn.setEnabled(true);
            }
            if (tabPosition == 5) {
                buttonLayout.removeComponent(nextBtn);
                buttonLayout.addComponent(previousBtn, 2);
                previousBtn.setEnabled(true);
            }
            if (tabPosition < 5 && tabPosition != 0) {
                buttonLayout.addComponent(previousBtn, 1);
                buttonLayout.addComponent(nextBtn, 2);
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
            if (tabPosition == 2) {

                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
                medicaidURA.historyDdlb.focus();
            }

            if (tabPosition == 3) {
                fcpResults.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
            }

            if (tabPosition == 4) {

                phsResults.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
            }
            additionalInformation.setUploderValue();
            lastPosition = tabPosition;
        } catch (Exception ex) {
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

            if (tabPosition != 5) {
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
                        }.getConfirmationMessage("Update confirmation", "You have added/removed NDC's from the current Projection. Are you sure you want to continue?");
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
            if (tabPosition == 2) {
                medicaidURA.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
            }

            if (tabPosition == 3) {
                fcpResults.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
            }

            if (tabPosition == 4) {
                phsResults.historyDdlb.focus();
                if (naTabChange) {
                    naSaveProcedure();
                    naTabChange = false;
                }
            }
            UI.getCurrent().setFocusedComponent(UI.getCurrent());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Save btn.
     *
     * @param event the event
     */
    @UiHandler("saveBtn")
    public void saveBtn(Button.ClickEvent event) throws PortalException, Exception {
        final String projName = dataSelection.getProjName();
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
                }
            }.getConfirmationMessage("Update confirmation", "You have added/removed NDC's from the current Projection. Are you sure you want to continue?");
        }

        if (!updateflag) {
            new AbstractNotificationUtils() {
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

            }.getConfirmationMessage("Save Confirmation", "Save record " + projName + "?");
        }

        UI.getCurrent().setFocusedComponent(UI.getCurrent());
    }

    private void callTempTableActivities() throws SystemException, Exception {
        CommonLogic logic = new CommonLogic();
        logic.clearTemp();
        logic.tempInsert();
    }

    void closeBtnLogic(String closeMsg) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                CommonLogic logic = new CommonLogic();
                try {
                    logic.deleteTempBySession();
                    logic.clearTemp();
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

    private void saveProjection() throws Exception {
        final String projName = dataSelection.getProjName();
        final int projectionId = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));
        nationalAssumptions.saveNationalAssumptions(true);
        medicaidURA.saveMedicaidSelections();
        fcpResults.saveFcpSelections();
        phsResults.savePhsSelections();
        additionalInformation.saveNotesInformation(0, "National Assumption");
        CommonLogic logic = new CommonLogic();
        logic.saveTempToMain();
        logic.saveBaseYeaToItemMaster();
        logic.saveTempNDCToMain();
        nationalAssumptions.saveDeletedPrice();
        nationalAssumptions.saveNationalAssumptions(false);
        CommonUIUtils.getMessageNotification(projectionId + "," + projName + " has been successfully saved");
        saveBtn.setCaption("UPDATE");
    }

    public void naSaveProcedure() throws Exception {
        nationalAssumptions.saveNationalAssumptions(true);
        nationalAssumptions.ndcProcedure();
        nationalAssumptions.callNaProcedure();

    }

    private void setTabSecurity() throws Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));

        Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessTabPermission(userId, "National Assumptions");
        if (functionPsHM.get(FunctionNameUtil.NM_DATA_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_DATA_TAB)).isTabFlag()) {
            tabSheet.getTab(0).setVisible(Boolean.FALSE);

        }
        if (functionPsHM.get(FunctionNameUtil.NA_NATIONAL_ASUMPTIONS_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.NA_NATIONAL_ASUMPTIONS_TAB)).isTabFlag()) {
            tabSheet.getTab(1).setVisible(Boolean.FALSE);
            tabSheet.getTab(2).setVisible(Boolean.FALSE);
            tabSheet.getTab(3).setVisible(Boolean.FALSE);
            tabSheet.getTab(4).setVisible(Boolean.FALSE);
        }
        if (functionPsHM.get(FunctionNameUtil.MEDICAID_URA_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.MEDICAID_URA_TAB)).isTabFlag()) {
            tabSheet.getTab(2).setVisible(Boolean.FALSE);
        }
        if (functionPsHM.get(FunctionNameUtil.FCP_RESULTS_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.FCP_RESULTS_TAB)).isTabFlag()) {
            tabSheet.getTab(3).setVisible(Boolean.FALSE);
        }
        if (functionPsHM.get(FunctionNameUtil.PHS_RESULTS_TAB) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.PHS_RESULTS_TAB)).isTabFlag()) {
            tabSheet.getTab(4).setVisible(Boolean.FALSE);
        }

    }

    public void tabOrder(int index) {
        saveBtn.setTabIndex(index + 1);
        previousBtn.setTabIndex(index + 2);
        nextBtn.setTabIndex(index + 3);
        closeBtn.setTabIndex(index + 4);
    }
}
