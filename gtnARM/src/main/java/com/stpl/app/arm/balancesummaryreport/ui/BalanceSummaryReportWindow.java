/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.balancesummaryreport.logic.BSummaryDemandLogic;
import com.stpl.app.arm.balancesummaryreport.logic.BSummaryPipelineLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.ui.form.BalanceSummaryReportDataSelectionTab;
import com.stpl.app.arm.dataselection.view.DataSelectionView;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantUtil;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Map;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.Raju
 */
public class BalanceSummaryReportWindow extends Window {

    @UiField("abstractTabSheet")
    private TabSheet tabSheet;

    @UiField("nextBtn")
    private Button nextBtn;

    @UiField("closeBtn")
    private Button closeBtn;

    @UiField("previousBtn")
    private Button previousBtn;

    private BalanceSummaryReportDataSelectionTab bsrDataSelection;
    private AbstractBSummaryReportSummary bsrSummary;
    int tabPosition = 0;
    AdjustmentReserveDTO binderDto = new AdjustmentReserveDTO();
    /**
     * binder used to bind the fields from the page
     */
    SummarySelection selection = new SummarySelection();
    private static final Logger LOGGER = Logger.getLogger(BalanceSummaryReportWindow.class);
    DataSelectionDTO dataSelectionDTO;
    SessionDTO sessionDTO;

    public BalanceSummaryReportWindow(SummarySelection selection, DataSelectionDTO dataSelectionDTO, SessionDTO sessionDTO) {
        super("Balance Summary");
        this.selection = selection;
        this.dataSelectionDTO = dataSelectionDTO;
        this.sessionDTO = sessionDTO;
        configureWindow();
        configurePermission();
        initializeTabs();
    }

    private void initializeTabs() {
        try {
            tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            tabSheet.setImmediate(true);
            bsrDataSelection = new BalanceSummaryReportDataSelectionTab(dataSelectionDTO, new SessionDTO());
            selection.setDataSelectionDTO(dataSelectionDTO);
            selection.setBalanceSummaryDataSelectionTab(bsrDataSelection);
            selection.setProjectionMasterSid(selection.getDataSelectionDTO().getProjectionId());
            bsrSummary = getSummaryObject(dataSelectionDTO.getAdjustmentCaption());

            tabSheet.addTab(bsrDataSelection, "Data Selection");
            Tab sumTab = tabSheet.addTab(bsrSummary, "Summary");
            sumTab.setDefaultFocusComponent(bsrSummary.getDefaultFocusComponent());
//            tab.setDefaultFocusComponent(bsrSummary.getDefaultFocusComponent());
            if (tabSheet.getTab(0).getCaption().equals("Data Selection")) {
                nextBtn.setVisible(true);
                previousBtn.setVisible(false);
            } else if (tabSheet.getTab(1).getCaption().equals("Summary")) {
                nextBtn.setVisible(false);
                previousBtn.setVisible(true);
            } else {
                previousBtn.setVisible(true);
                nextBtn.setVisible(false);
            }
            tabSheet.addListener(new TabSheet.SelectedTabChangeListener() {
                @Override
                public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                    try {
                        final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                        tabPosition = event.getTabSheet().getTabPosition(tab);
                        if (tabPosition == 0) {
                            nextBtn.setVisible(true);
                            previousBtn.setVisible(false);
                        } else if (tabPosition == 1) {
                            nextBtn.setVisible(false);
                            previousBtn.setVisible(true);
                            bsrSummary.setCalculationProfile();
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    private final CustomNotification notifier = new CustomNotification();

    private AbstractBSummaryReportSummary getSummaryObject(String summaryTypes) {

        if (summaryTypes.equals("Demand")) {
            BSummaryDemandLogic demandLogic = new BSummaryDemandLogic();
            BSummaryDemandSummary summary = new BSummaryDemandSummary(selection, demandLogic);
            return summary;
        }
        BSummaryPipelineLogic pipelineLogic = new BSummaryPipelineLogic();
        BSummaryPipelineSummary pipelineSummary = new BSummaryPipelineSummary(selection, pipelineLogic);
        return pipelineSummary;

    }

    class CustomNotification extends AbstractNotificationUtils {

        String buttonName;

        @Override
        public void noMethod() {
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "close":
                        close();
                        UI.getCurrent().getNavigator().navigateTo(DataSelectionView.NAME); // For GAL-6087
                        CommonLogic.dropDynamicTables(String.valueOf(sessionDTO.getUserId()), String.valueOf(sessionDTO.getSessionId()));
                        break;
                    case "save":
                        break;
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    @UiHandler("closeBtn")
    public void closeButtonClick(Button.ClickEvent event) {
        try {
            notifier.setButtonName("close");
            String closeMsg;
            if (ARMConstants.getDemand().equals(dataSelectionDTO.getAdjustmentCaption())) {
                closeMsg = ARMMessages.getCloseMessageID014();
            } else {
                closeMsg = ARMMessages.getCloseMessageID013();
            }
            notifier.getConfirmationMessage(ARMMessages.getCloseMessageName_001(), closeMsg);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureWindow() {
        setContent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/bottomButtonSummary.xml"), this));
        center();
        setWidth(100f, Sizeable.Unit.PERCENTAGE);
        setHeight(80f, Sizeable.Unit.PERCENTAGE);
        setPositionX(ARMUtils.ZERO);
        setPositionY(ARMUtils.ZERO);
        addStyleName(ARMUtils.BOOTSTRAP_UI);
        addStyleName(ARMUtils.BOOTSTRAP);
        addStyleName(ARMUtils.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setResizable(false);
        setClosable(false);
        nextBtn.focus();
    }

    @UiHandler("nextBtn")
    public void nextButtonListener(Button.ClickEvent event) {
        int position = tabSheet.getTabPosition(tabSheet.getTab(tabSheet.getSelectedTab()));
        tabSheet.setSelectedTab(position + 1);
    }

    @UiHandler("previousBtn")
    public void previousButtonListener(Button.ClickEvent event) {
        int position = tabSheet.getTabPosition(tabSheet.getTab(tabSheet.getSelectedTab()));
        tabSheet.setSelectedTab(position - 1);
    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent()
                    .getAttribute(ConstantsUtils.USER_ID));

            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, ARMUtils.BALANCE_SUMMARY_REPORT + ConstantUtil.COMMA + ConstantsUtils.SUMMARY);
            if (functionCfpHM.get("previousBtn") != null && !((AppPermission) functionCfpHM.get("previousBtn")).isFunctionFlag()) {
                previousBtn.setVisible(false);

            }
            if (functionCfpHM.get("closeBtn") != null && !((AppPermission) functionCfpHM.get("closeBtn")).isFunctionFlag()) {
                closeBtn.setVisible(false);

            }
            if (functionCfpHM.get("nextBtn") != null && !((AppPermission) functionCfpHM.get("nextBtn")).isFunctionFlag()) {
                nextBtn.setVisible(false);

            }
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }
}
