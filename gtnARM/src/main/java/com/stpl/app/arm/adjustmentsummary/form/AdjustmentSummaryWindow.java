/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentsummary.form;

import com.stpl.app.arm.dataselection.ui.form.DataSelectionTab;
import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.stpl.app.arm.dataselection.view.DataSelectionView;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.UI;
import java.util.Map;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentSummaryWindow extends Window {

    @UiField("abstractTabSheet")
    private TabSheet tabSheet;

    @UiField("nextBtn")
    private Button nextBtn;

    @UiField("closeBtn")
    private Button closeBtn;

    @UiField("previousBtn")
    private Button previousBtn;

    private DataSelectionTab dataSelection;
    private AdjustmentSummary adjustmentsummary;
    private AdjustmentDetail adjustmentDetail;
    int tabPosition = 0;
    AdjustmentReserveDTO binderDto = new AdjustmentReserveDTO();
    /**
     * binder used to bind the fields from the page
     */
    SummarySelection selection = new SummarySelection();
    private static final Logger LOGGER = Logger.getLogger(AdjustmentSummaryWindow.class);
    DataSelectionDTO dataSelectionDTO;
    SessionDTO sessionDTO;

    public AdjustmentSummaryWindow(SummarySelection selection, DataSelectionDTO dataSelectionDTO, SessionDTO sessionDTO) throws PortalException, SystemException {
        this.selection = selection;
        this.sessionDTO = sessionDTO;
        this.dataSelectionDTO = dataSelectionDTO;
        configureWindow();
        initializeTabs();
    }

    private void initializeTabs() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Adjustment Summary" + "," + "Landing screen");
        if (functionHM.get("nextBtn") != null && !((AppPermission) functionHM.get("nextBtn")).isFunctionFlag()) {
            nextBtn.setVisible(false);
        } else {
            nextBtn.setVisible(true);
        }
        if (functionHM.get("closeBtn") != null && !((AppPermission) functionHM.get("closeBtn")).isFunctionFlag()) {
            closeBtn.setVisible(false);
        } else {
            closeBtn.setVisible(true);
        }

        if (functionHM.get("previousBtn") != null && !((AppPermission) functionHM.get("previousBtn")).isFunctionFlag()) {
            previousBtn.setVisible(false);
        } else {
            previousBtn.setVisible(true);
        }
        if (functionHM.get("nextBtn") != null && !((AppPermission) functionHM.get("nextBtn")).isFunctionFlag()) {
            nextBtn.setVisible(false);
        } else {
            nextBtn.setVisible(true);
        }
        if (functionHM.get("previousBtn") != null && !((AppPermission) functionHM.get("previousBtn")).isFunctionFlag()) {
            previousBtn.setVisible(false);
        } else {
            previousBtn.setVisible(true);
        }
        if (functionHM.get("closeBtn") != null && !((AppPermission) functionHM.get("closeBtn")).isFunctionFlag()) {
            closeBtn.setVisible(false);
        } else {
            closeBtn.setVisible(true);
        }
        try {
            tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            tabSheet.setImmediate(true);
            dataSelection = new DataSelectionTab(dataSelectionDTO, new SessionDTO());
            selection.setDataSelectionDTO(dataSelectionDTO);
            selection.setProjectionMasterSid(selection.getDataSelectionDTO().getProjectionId());
            try {
                adjustmentsummary = new AdjustmentSummary(selection, sessionDTO);
                adjustmentDetail = new AdjustmentDetail(selection, sessionDTO);
            } catch (Exception e) {
                LOGGER.error(e);
            }

            tabSheet.addTab(dataSelection, ARMConstants.getDataSelection());
            TabSheet.Tab tab = tabSheet.addTab(adjustmentsummary, ARMConstants.getAdjustmentSummary());
            tab.setDefaultFocusComponent(adjustmentsummary.getDefaultFocusComponent());
            tabSheet.addTab(adjustmentDetail, "Adjustment Detail");
            if (tabSheet.getTab(0).getCaption().equals(ARMConstants.getDataSelection())) {
                nextBtn.setVisible(true);
                previousBtn.setVisible(false);
            } else if (tabSheet.getTab(1).getCaption().equals(ARMConstants.getAdjustmentSummary())) {
                nextBtn.setVisible(true);
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
                            nextBtn.setVisible(true);
                            previousBtn.setVisible(true);
                        } else if (tabPosition == NumericConstants.TWO) {
                            adjustmentDetail.loadReserveAccount();
                            previousBtn.setVisible(true);
                            nextBtn.setVisible(false);
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
            notifier.getConfirmationMessage(ARMMessages.getCloseMessageName_001(), ARMMessages.getCloseMessage_Summary());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void configureWindow() {
        setContent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/bottomButtonSummary.xml"), this));
        center();
        setWidth(NumericConstants.FLOAT_HUNDRED, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_EIGHTY, Sizeable.Unit.PERCENTAGE);
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
}
