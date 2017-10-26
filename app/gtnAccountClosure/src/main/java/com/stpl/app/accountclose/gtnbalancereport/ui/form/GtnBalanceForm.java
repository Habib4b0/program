/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.HistoryTabDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.GTNbalanceLogic;
import com.stpl.app.accountclose.gtnbalancereport.ui.layout.GtnBalanceEditWindow;
import com.stpl.app.accountclose.gtnbalancereport.ui.layout.GtnBalanceViewWindow;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.form.FixedDollarAdditionalInformation;
import com.stpl.app.accountclose.ui.form.NotesTabForm;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class GtnBalanceForm extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(GtnBalanceForm.class);
    /**
     * The Constant NAME.
     */
    public static final String NAME = "GtnBalanceForm";
    /**
     * The tab flag.
     */
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
     * The previous btn.
     */
    @UiField("saveBtn")
    Button saveBtn;
    
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
    @UiField("adjustBtn")
    Button adjustBtn;
    @UiField("reconcileBtn")
    Button reconcileBtn;
    @UiField("closeBtn")
    Button closeBtn;
    /**
     * Position of the tab.
     */
    int tabPosition;
    /**
     * Balance Report.
     */
    private BalanceReport balanceReport;
    /**
     * The History .
     */
    private HistoryTab historyTab;
    /**
     * The Additional Information.
     */
    private NotesTabForm additionalInformation;
    
    private CustomFieldGroup dataSelectionBinder;
    GtnBalanceEditWindow editWindow;
    GtnBalanceViewWindow viewWindow;
    ExtFilterTable resultTable;
    DataSelectionDTO dsdto;
    private final Map<String, Boolean> pushMap = new HashMap<String, Boolean>();
    public int tempTabPosition = 0;
    boolean dsFlag = true;
    int lastPosition;
    SessionDTO sessionDTO;
    FixedDollarDTO fixedDollarDTO = new FixedDollarDTO();
    private CustomFieldGroup fixedDollarBinder = new CustomFieldGroup(new BeanItem<FixedDollarDTO>(fixedDollarDTO));
    
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");
    public GtnBalanceForm(GtnBalanceEditWindow editWindow,SessionDTO sessionDTO) throws SystemException, Exception {
        this.editWindow = editWindow;
        this.sessionDTO=sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/gtnBalanceForm.xml"), this));
        init();
        addTab();
        configuringTabs();
        configureForView();

    }
    public GtnBalanceForm(SessionDTO sessionDTO, DataSelectionDTO dsdto, GtnBalanceViewWindow viewWindow) {
        try {
            this.viewWindow = viewWindow;
            this.sessionDTO = sessionDTO;
            this.dsdto = dsdto;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/gtnBalanceForm.xml"), this));
            init();
            addTab();
            configuringTabs();
            configureForView();
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }
   

    private void init() {
        try {
            balanceReport = new BalanceReport(sessionDTO, new BalanceReportDTO());
            historyTab = new HistoryTab(sessionDTO, new HistoryTabDTO());
            additionalInformation = new NotesTabForm(fixedDollarBinder, "GTN_BALANCE");
        } catch (Exception e) {
            LOGGER.error(e);
        }
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
        tabSheet.addTab(balanceReport, "Balance Report", null, 0);
        tabSheet.addTab(historyTab, "History", null, 1);
        tabSheet.addTab(additionalInformation, "Additional Information", null, 2);
        attachTabChangeListener();

    }

    private void attachTabChangeListener() {
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                buttonEnableLogic(tabPosition, tabSheet.getComponentCount() - 1);
                try {
                    onTabChange(tabPosition);
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
            previousBtn.setVisible(true);
            adjustBtn.setEnabled(false);
            reconcileBtn.setEnabled(false);
        } else if (tabPosition == 0) {
            previousBtn.setVisible(false);
            if (!"VIEW".equalsIgnoreCase(sessionDTO.getAction())) {
                adjustBtn.setEnabled(true);
                reconcileBtn.setEnabled(true);
            }
            
        }
        if (tabPosition == i) {
            nextBtn.setVisible(false);
        } else {
            nextBtn.setVisible(true);
        }
    }

    /**
     * On tab change.
     *
     * @param tabPosition the tab position
     */
    protected void onTabChange(int tabPosition) throws PortalException, SystemException {
         if (tabPosition != 0) {
            if (!tabLazyLoadMap.get(tabPosition)) {
                detachListeners(tabSheet);
                tabLazyLoadMap.put(tabPosition, Boolean.TRUE);
                lazyLoadTab(tabPosition);
                attachTabChangeListener();
            }
        }
    }

    /**
     * Lazy load tab.
     *
     * @param tabPosition the tab position
     */
    protected void lazyLoadTab(int tabPosition) {
        if (tabPosition == 1) {
            tabSheet.replaceComponent(historyTab, historyTab);
            historyTab.setSelectionCriteria();
        } else if (tabPosition == 2) {
            tabSheet.replaceComponent(additionalInformation, additionalInformation);
        }
    }

    /**
     * Btn save logic.
     *
     * @param event the event
     */
    @UiHandler("saveBtn")
    public void btnSaveLogic(Button.ClickEvent event) {

        
        MessageBox.showPlain(Icon.QUESTION,confirmationMessage.getString("BR_MSG_ID_040"),confirmationMessage.getString("MSG_ID_022"), new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {

                        saveProjection();
                        CommonUIUtils.getMessageNotification(confirmationMessage.getString("BR_MSG_ID_041"));
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

            }
        }, ButtonId.YES, ButtonId.NO);

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
            if (i == 0) {
                tabLazyLoadMap.put(i, Boolean.TRUE);
            } else {
                tabLazyLoadMap.put(i, Boolean.FALSE);
            }
        }
        tabFlag = true;
    }

   

    

    private void saveProjection() throws SystemException, Exception {
        
        if (tabLazyLoadMap.get(0)) {
            balanceReport.saveSelection();
        }
        if (tabLazyLoadMap.get(1)) {
            if(sessionDTO.isSaveFlag()){
            }else{
              balanceReport.saveSelection(); 
            }
        }
        new GTNbalanceLogic().saveTempToMain(sessionDTO);
        GTNbalanceLogic.updateSaveFlag(sessionDTO.getAcctCloserMasterId());
    }

    

    
    private void configureForView() {
        if ("VIEW".equalsIgnoreCase(sessionDTO.getAction())) {
            saveBtn.setEnabled(false);
            adjustBtn.setEnabled(false);
            reconcileBtn.setEnabled(false);
        }
        previousBtn.setVisible(false);
    }

   

    public void detachListeners(TabSheet tabsheet) {
        List<TabSheet.SelectedTabChangeListener> listeners = new ArrayList<TabSheet.SelectedTabChangeListener>();

        listeners = (List<TabSheet.SelectedTabChangeListener>) tabsheet
                .getListeners(TabSheet.SelectedTabChangeEvent.class);

        for (TabSheet.SelectedTabChangeListener object : listeners) {
            tabsheet.removeSelectedTabChangeListener(object);

        }

    }
    @UiHandler("closeBtn")
    public void btnCloseLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, confirmationMessage.getString("BR_MSG_ID_042"),confirmationMessage.getString("MSG_ID_023"), new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        if ("VIEW".equals(sessionDTO.getAction())) {
                            if (viewWindow != null) {
                                viewWindow.close();
                            }
                        } else {
                            if (editWindow != null) {
                                editWindow.close();
                            }
                        }
                        new GTNbalanceLogic().deleteTempBySession(sessionDTO);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

            }
        }, ButtonId.YES, ButtonId.NO);

    }
    public void configureOnEnter(final int projectionId, final DataSelectionDTO dataSelectionDTO) {
        tabSheet.setSelectedTab(0);
    }
    @UiHandler("adjustBtn")
    public void adjustBtnLogic(Button.ClickEvent event) {
        if(false){
         MessageBox.showPlain(Icon.QUESTION, confirmationMessage.getString("BR_MSG_ID_027"),confirmationMessage.getString("BR_MSG_ID_043"));
          MessageBox.showPlain(Icon.QUESTION, confirmationMessage.getString("BR_MSG_ID_042"),confirmationMessage.getString("BR_MSG_ID_044"));
        }
       final GtnFixedDollarLookup gtnLookup = new GtnFixedDollarLookup(sessionDTO); 
        UI.getCurrent().addWindow(gtnLookup);
    }
    @UiHandler("reconcileBtn")
    public void reconcileBtnLogic(Button.ClickEvent event) {
        if(false){
       MessageBox.showPlain(Icon.QUESTION, confirmationMessage.getString("BR_MSG_ID_027"),confirmationMessage.getString("BR_MSG_ID_045")); 
       MessageBox.showPlain(Icon.QUESTION, confirmationMessage.getString("BR_MSG_ID_042"),confirmationMessage.getString("BR_MSG_ID_046")); 
        }
        final GtnFixedDollarLookup gtnLookup = new GtnFixedDollarLookup(sessionDTO); 
        UI.getCurrent().addWindow(gtnLookup);
    }
}
