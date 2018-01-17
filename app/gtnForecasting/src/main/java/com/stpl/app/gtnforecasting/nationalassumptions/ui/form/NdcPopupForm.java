package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.NewNdcDTO;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.WindowMessagesName.*;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.vaadin.ui.Button;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author nadhiya
 */
public class NdcPopupForm extends Window {

    /**
     * The close.
     */
    @UiField("close")
    private Button close;

    /**
     * The add.
     */
    @UiField(Constant.ADD_FULL_SMALL)
    private Button add;

    /**
     * The reset table.
     */
    @UiField("resetTable")
    private Button resetTable;
    @UiField("tabSheet")
    private TabSheet tabSheet;

    /**
     * The tab position.
     */
    private int tabPosition = 0;
    /**
     * The tabsheet map.
     */
    private final Map<Integer, Boolean> tabsheetMap;

    private MedicaidNdcPopUp medicaidNdcPopUp;
    private FederalNdcPopup federalNdcPopup;
    private NewNdcDTO newNdcDto = new NewNdcDTO();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NdcPopupForm.class);
    private SessionDTO sessionDTO;

    public NdcPopupForm() {
        super("New NDC Setup");
        this.tabsheetMap = new HashMap<>();
    }

    public NdcPopupForm(NewNdcDTO newNdcDto, Map<Integer, Object> medicaidMap, Map<Integer, Object> federalMap,SessionDTO sessionDTO) throws SystemException {
        super("New NDC Setup");
        this.tabsheetMap = new HashMap<>();
        this.newNdcDto = newNdcDto;
        this.sessionDTO=sessionDTO;
        this.medicaidNdcPopUp = new MedicaidNdcPopUp(newNdcDto, medicaidMap,sessionDTO);
        this.federalNdcPopup = new FederalNdcPopup(newNdcDto, federalMap,sessionDTO);
        init();
    }

    private void init() {
        center();
        setClosable(true);
        setModal(true);
        setContent(Clara.create(getClass().getResourceAsStream("/nationalassumption/NdcPopupForm.xml"), this));
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        addTab();
        configurefields();

    }

    @UiHandler(Constant.ADD_FULL_SMALL)
    public void add(Button.ClickEvent event) throws SystemException, PortalException {
        if (addLogic()) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        medicaidNdcPopUp.saveAll();
                        federalNdcPopup.saveAll();
                        close();
                    } catch (PortalException | SystemException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                }

                @Override
                public void noMethod() {
                    // TODO Auto-generated method stub
                }
            }.getConfirmationMessage("Add Confirmation",
                    "There are still more NDC’s to add. Are you sure you want to add the NDC’s in the New NDC’s list view now?");
        } else if (StringUtils.isNotBlank(newNdcDto.getIndicator())) {
            close();
        }
    }

    /**
     * Close.
     *
     * @param event the event
     */
    @UiHandler("close")
    public void close(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                close();
            }

            @Override
            public void noMethod() {
                // TODO Auto-generated method stub
            }
        }.getConfirmationMessage("Close Confirmation",
                " Are you sure you want to close the New NDC Setup Popup?");
    }

    /**
     * Reset table.
     *
     * @param event the event
     */
    @UiHandler("resetTable")
    public void resetTable(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    if (tabPosition == 0) {
                        medicaidNdcPopUp.resetTableLogic();
                    } else {
                        federalNdcPopup.resetTableLogic();
                    }

                } catch (SystemException ex) {
                    LOGGER.error(ex.getMessage());
                }

            }

            @Override
            public void noMethod() {
                // TODO Auto-generated method stub
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(),
                " Are you sure you want to reset the selected NDC? You will have to recreate it.");

    }

    /**
     * Used to configure the TabSheet for all classes in FFS.
     *
     * @return the tab sheet
     */
    protected TabSheet addTab() {
        LOGGER.debug("NdcPopupForm addTab() starts ");

        tabSheet.markAsDirty();
        tabSheet.markAsDirtyRecursive();
        tabSheet.addTab(medicaidNdcPopUp, "Medicaid FFS", null, 0);
        tabSheet.addTab(federalNdcPopup, "Federal", null, 1);

        int tabCount = tabSheet.getComponentCount();
        tabsheetMap.clear();
        for (int i = 0; i < tabCount; i++) {
            if (i == 1) {
                tabsheetMap.put(i, Boolean.TRUE);
            } else {
                tabsheetMap.put(i, Boolean.FALSE);
            }
        }
        LOGGER.debug("NdcPopupForm addTab() ends ");
        return tabSheet;
    }

    private void configurefields() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId =  sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + "New NDC PopUp");
            if (tabItemHM.get(Constant.ADD_FULL_SMALL) != null && tabItemHM.get(Constant.ADD_FULL_SMALL).isFunctionFlag()) {
                add.setVisible(true);
            } else {
                add.setVisible(false);
            }
            if (tabItemHM.get("resetTable") != null && tabItemHM.get("resetTable").isFunctionFlag()) {
                resetTable.setVisible(true);
            } else {
                resetTable.setVisible(false);
            }
            if (tabItemHM.get("close") != null && tabItemHM.get("close").isFunctionFlag()) {
                close.setVisible(true);
            } else {
                close.setVisible(false);
            }

        } catch (PortalException | SystemException portal) {
            LOGGER.error(StringUtils.EMPTY,portal);
        }
    }

    @UiHandler("tabSheet")
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {

        TabSheet.Tab tab = event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
        tabPosition = event.getTabSheet().getTabPosition(tab);
    }

    public void deleteNewNdc() {
        medicaidNdcPopUp.deleteMedicaidNdc();
        federalNdcPopup.deleteFederalNdc();
    }

    public boolean addLogic() throws SystemException, PortalException  {
        boolean addMessage = false;
        int mediCount = medicaidNdcPopUp.addLogic();
        int fedCount = federalNdcPopup.addLogic();
        federalNdcPopup.addLogic();
        if (mediCount > 0 || fedCount > 0) {
            addMessage = true;
        }
        return addMessage;
    }
}
