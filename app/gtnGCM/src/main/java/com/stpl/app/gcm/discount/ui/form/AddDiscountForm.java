/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.discount.ui.layout.AddDiscountWindow;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class AddDiscountForm extends CustomComponent implements View {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AddDiscountForm.class);
    /**
     * The tab flag.
     */
    public boolean tabFlag = false;
    /**
     * The tab sheet.
     */
    @UiField("tabSheet")
    private TabSheet tabSheet;
    /**
     * The tab lazy load map.
     */
    private final Map<Integer, Boolean> tabLazyLoadMap = new HashMap<>();

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
     * Position of the tab.
     */
    private int tabPosition;
    /**
     * Balance Report.
     */
    private NewDiscountTab newDiscountTab;
    /**
     * The Existing Discount Tab .
     */
    private ExistingDiscountTab existingDiscountTab;
    private final AddDiscountWindow editWindow;
    private ExtFilterTable resultTable;
    public int tempTabPosition = 0;

    @UiField("addDiscountBtn")
    public Button addDiscountBtn;
    private final SessionDTO session;
    private final List<RemoveDiscountDto> removeList;
    private final StplSecurity stplSecurity = new StplSecurity();

    public AddDiscountForm(AddDiscountWindow editWindow, List<RemoveDiscountDto> removeList, SessionDTO session) {
        this.editWindow = editWindow;
        this.resultTable = resultTable;
        this.removeList = removeList == null ? removeList : new ArrayList<>(removeList);
        this.session = session;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/addDiscountForm.xml"), this));
        init();
        addTab();
        configuringTabs();
        configureSecurityPermissions();
    }

    private void init() {
        newDiscountTab = new NewDiscountTab(removeList, session);
        existingDiscountTab = new ExistingDiscountTab(removeList, session);
    }

    /**
     * Adds the tab.
     *
     * @return the component
     */
    private void addTab() {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        tabSheet.markAsDirty();
        tabSheet.markAsDirtyRecursive();
        tabSheet.addTab(newDiscountTab, "New Discount", null, 0);
        tabSheet.addTab(existingDiscountTab, "Existing Discount", null, 1);
        attachTabChangeListener();
        newDiscountTab.componentTypeddlb.focus();
    }

    private void attachTabChangeListener() {
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                buttonEnableLogic(tabPosition, tabSheet.getComponentCount() - 1);

                try {
                    onTabChange();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

    }

    /**
     * Button enable logic.
     *
     * @param tabPosition the tab position
     * @param i the i
     */
    protected void buttonEnableLogic(int tabPosition, int i) {

        if (tabPosition != 0) {
            existingDiscountTab.componentTypeDdlb.focus();
            previousBtn.setVisible(true);
        } else {
            newDiscountTab.componentTypeddlb.focus();
            previousBtn.setVisible(false);
        }
        if (tabPosition == i) {
            nextBtn.setVisible(false);
        } else {
            nextBtn.setVisible(true);
        }
        if (tabPosition <= i) {
            previousBtn.setEnabled(true);
        }

    }

    /**
     * On tab change.
     *
     * @param tabPosition the tab position
     */
    protected void onTabChange() throws PortalException, SystemException {
        return;
    }

    /**
     * Lazy load tab.
     *
     * @param tabPosition the tab position
     */
    protected void lazyLoadTab() {
        return;
    }

    /**
     * Btn previous logic.
     *
     * @param event the event
     */
    @UiHandler("previousBtn")
    public void btnPreviousLogic(Button.ClickEvent event) {
        if (tabPosition != 0) {
            tabSheet.setSelectedTab(tabPosition - 1);
        }
    }

    /**
     * Btn next logic.
     *
     * @param event the event
     */
    @UiHandler("nextBtn")
    public void btnNextLogic(Button.ClickEvent event) {

        tabSheet.setSelectedTab(tabPosition + 1);

        UI.getCurrent().setFocusedComponent(UI.getCurrent());

    }

    @UiHandler("closeBtn")

    public void btnCloseLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    editWindow.close();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Close", "Are you sure you want to close the popup ?");
    }

    /**
     * Enter.
     *
     * @param event the event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        tabSheet.setSelectedTab(0);
    }

    /**
     * Configuring tabs.
     */
    private void configuringTabs() {
        int tabCount = tabSheet.getComponentCount();
        tabLazyLoadMap.clear();
        for (int i = 0; i < tabCount; i++) {
            if (i == 0 || i == 1) {
                tabLazyLoadMap.put(i, Boolean.TRUE);
            } else {
                tabLazyLoadMap.put(i, Boolean.FALSE);
            }
        }
        tabFlag = true;
    }

    private void saveDiscount() {
        if (tabPosition == 0) {
            newDiscountTab.addDiscountSaveLogic();
        }
        if (tabPosition == 1) {
            existingDiscountTab.addDiscountSaveLogic();
        }

    }

    public void detachListeners(TabSheet tabsheet) {
        List<TabSheet.SelectedTabChangeListener> listeners;

        listeners = (List<TabSheet.SelectedTabChangeListener>) tabsheet
                .getListeners(TabSheet.SelectedTabChangeEvent.class);

        for (TabSheet.SelectedTabChangeListener object : listeners) {
            tabsheet.removeSelectedTabChangeListener(object);

        }

    }

    @UiHandler("addDiscountBtn")
    public void btnSaveLogic(Button.ClickEvent event) {
        try {
            saveDiscount();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(session.getUserId()), "GCM-Customer Management", "Add Discount", "ExistingDiscount Screen");
            addDiscountBtn.setVisible(CommonLogic.isButtonVisibleAccess("addDiscountBtn", functionHM));
            closeBtn.setVisible(CommonLogic.isButtonVisibleAccess("closeBtn", functionHM));
            previousBtn.setVisible(CommonLogic.isButtonVisibleAccess("previousBtn", functionHM));
            addDiscountBtn.setVisible(CommonLogic.isButtonVisibleAccess("addDiscountBtn", functionHM));
            nextBtn.setVisible(CommonLogic.isButtonVisibleAccess("nextBtn", functionHM));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
