/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui.form.lookups;

import com.stpl.app.galforecasting.discountProjection.logic.tableLogic.NMDiscountTableLoadLogic;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.discountProjection.form.AltSummeryDiscount;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.salesprojection.form.AlternateSummery;
import com.stpl.app.galforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.TabNameUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nandhakumar
 */
public class AlternateHistory extends CustomWindow {

    /**
     * The close.
     */
    @UiField("nextBtn")
    Button nextBtn;
    @UiField("closeBtn")
    Button closeBtn;
    @UiField("previousBtn")
    Button previousBtn;
    @UiField("populateBtn")
    Button populateBtn;

    /**
     * The tab sheet.
     */
    @UiField("tabSheet")
    TabSheet tabSheet;

    int tabPosition = 0;
    int lastPosition = 0;
    int currentPosition = 0;
    boolean prevflag = false;
    private CustomerSelection customerSelection;
    private ItemSelection itemSelection;
    private AltHistorySelection altHistorySelection;
    private Allocation allocation;
    private AlternateSummery summary;
    private AltSummeryDiscount discountSummary;

    SessionDTO session;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AlternateHistory.class);
    ProjectionSelectionDTO projectionSelection = new ProjectionSelectionDTO();
    NMDiscountTableLoadLogic tableLogic;
    int rsModelSid = 0;
    String rsName = StringUtils.EMPTY;
    String ccpList = StringUtils.EMPTY;
    AlternateHistoryLogic logic = new AlternateHistoryLogic();
    List<String> variableList=new ArrayList<>();

    /**
     *
     * @param screenIndicator
     * @param baseRateDTO
     * @param custom
     * @throws Exception
     */
    public AlternateHistory(SessionDTO session,List<String> variableList) throws SystemException, Exception {
        this.session = session;
        this.projectionSelection = null;
        this.tableLogic = null;
        this.variableList=variableList;
        this.ccpList = session.getActualccp(); 
        configInConstructor();

    }

    public void configInConstructor() throws SystemException, Exception {
        center();
        setWidth(100, Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        setMinimizeToTray();
        addStyleName("valo-theme-customwindow");
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setContent(Clara.create(getClass().getResourceAsStream("/alternateHistory.xml"), this));
        deleteOldDataOnLoad();
        configureFields();
        init();
        addTab();
        tabSheet.setSelectedTab(0);
    }

    public AlternateHistory(SessionDTO session, ProjectionSelectionDTO projectionSelection, NMDiscountTableLoadLogic tableLogic, String ccpList, int rsModelSid, String rsName) throws SystemException, Exception {
        this.session = session;
        this.projectionSelection = projectionSelection;
        this.tableLogic = tableLogic;
        this.ccpList = ccpList;
        this.rsModelSid = rsModelSid;
        this.rsName = rsName;
        configInConstructor();
    }

    /**
     * Configures the components.
     */
    protected void configureFields() {
        try {
            populateBtn.setVisible(false);
            previousBtn.setVisible(false);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void init() throws Exception {
        this.customerSelection = new CustomerSelection(session);
        this.itemSelection = new ItemSelection(session);
        this.altHistorySelection = new AltHistorySelection(session,this);
        this.allocation = new Allocation(session, logic.getProjDetaSid(ccpList, session.getProjectionId()));

        if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
            this.discountSummary = new AltSummeryDiscount(session, CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED, projectionSelection, tableLogic, rsName);
        } else {
        this.summary = new AlternateSummery(session, CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED,variableList);
        }

    }

    private void addTab() {
        populateBtn.setVisible(false);
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        tabSheet.markAsDirty();
        tabSheet.markAsDirtyRecursive();
        tabSheet.addTab((Component) customerSelection, "Customer Selection", null, 0);
        tabSheet.addTab((Component) itemSelection, "Item Selection", null, 1);
        tabSheet.addTab((Component) altHistorySelection, "Alt History Selection", null, 2);
        tabSheet.addTab((Component) allocation, "Allocation", null, 3);
        if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
            tabSheet.addTab(discountSummary, "Summary", null, 4);
        } else {
            tabSheet.addTab(summary, "Summary", null, 4);
        }
        attachTabChangeListener();
    }

    @UiHandler("previousBtn")
    public void previousTab(Button.ClickEvent event) {
        prevflag = true;
        if (tabPosition == 2 || tabPosition == 3 || tabPosition == 4) {
            currentPosition = tabPosition - 1;
            tabSheet.setSelectedTab(currentPosition);
            tabPosition = currentPosition;
        }

        if (currentPosition == 0 || currentPosition == 1) {
            previousBtn.setVisible(false);
        } else {
            previousBtn.setVisible(true);
            nextBtn.setVisible(true);
        }
    }

    private void attachTabChangeListener() {
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                
                if (!prevflag) {

                    final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                    tabPosition = event.getTabSheet().getTabPosition(tab);

                    String tabName = event.getTabSheet().getTab(tabPosition).getCaption();

                    try {
//                   
                        if (lastPosition == 0 && lastPosition != tabPosition) {
//                       
                            boolean flag = logic.checkSelectedCustomers(session);
                            if (!flag) {
                                AbstractNotificationUtils.getErrorNotification("Error",
                                        "Please select a Customer record and move it to the ‘Selected Customers’ list view. Then try again.");
                                event.getTabSheet().setSelectedTab(lastPosition);
                                resetTab(tabPosition, lastPosition);
                                return;
                            }else{
                                 logic.deleteUnselectedCustomersOrItems(session,false);
                                 CommonLogic.getIdentifierType(itemSelection.itemIdentifierType,session);
                            }
                        }
                        if (lastPosition == 1 && lastPosition != tabPosition) {
//                      
                            boolean flag = logic.checkSelectedItems(session);
                            if (!flag && tabPosition != 0) {
                                AbstractNotificationUtils.getErrorNotification("Error",
                                        "Please select at least one Item record and move it to the ‘Selected Items’ list view. Then try again.");
                                event.getTabSheet().setSelectedTab(lastPosition);
                                resetTab(tabPosition, lastPosition);
                                return;
                            }else{
                                logic.deleteUnselectedCustomersOrItems(session,true);
                            }
                        }
                        if (lastPosition == 2 && tabPosition > 2) {
//                       
                            boolean flag = altHistorySelection.getSelectedItems();
                            List list = altHistorySelection.getselectedPeriod();
                            allocation.setAllocatedPeriods(list);
                            if ("Allocation".equals(tabName) && altHistorySelection.getAllocationCheck()) {
                                logic.resetTablesOnAllocation(session);
                                altHistorySelection.setAllocationCheck(false);                                
                            }
                            if (!flag || list.isEmpty()) {
                                event.getTabSheet().setSelectedTab(lastPosition);
                                resetTab(tabPosition, lastPosition);
                                return;
                            }
                        }
                        if ("Allocation".equals(tabName)) {
                            String ccpId =altHistorySelection.getCcpIds();
                            if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
                                Object[] orderedArg = {session.getCcps(), logic.getProjDetaSid(ccpList, session.getProjectionId()), rsModelSid, session.getUserId(), session.getSessionId()};

                                logic.call(orderedArg, session, "PRC_DISC_ALTERNATE_HIST_INSERT");
                                logic.insertforDiscountTotal(session.getUserId(), session.getSessionId(), ccpId);

                            } else {
                                String projectionDetailsId = logic.getProjDetaSid(session.getActualccp(), session.getProjectionId());
                               
                                logic.altHistInsert(session, projectionDetailsId,ccpId);
                                logic.insertforSalesTotal(session.getUserId(), session.getSessionId(), ccpId);
                            }

                            allocation.getContent(session);
                        }

                        lastPosition = tabPosition;
                        if (lastPosition > 3) {
                            populateBtn.setVisible(true);

                        }
                        if (tabPosition == 0 || tabPosition == 1) {
                            previousBtn.setVisible(false);
                        } else {
                            previousBtn.setVisible(true);
                        }
                        if (tabPosition == 4) {
                            nextBtn.setVisible(false);
                        } else {
                            nextBtn.setVisible(true);
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage());
                    }
                } else {
                    prevflag = false;
                }
            }
        });

    }
    
    /**
     * Reset the tab to last position if validation fails
     * @param tabPosition
     * @param lastPosition 
     */
    private void resetTab(int tabPosition, int lastPosition) {
        Tab tabToReset = tabSheet.getTab(tabPosition);
        tabSheet.removeTab(tabToReset);
        switch(tabPosition){
            case 0:
                tabSheet.addTab((Component) customerSelection, "Customer Selection", null, 0);
                tabSheet.setSelectedTab(lastPosition);
                break;
            case 1:
                tabSheet.addTab((Component) itemSelection, "Item Selection", null, 1);
                tabSheet.setSelectedTab(lastPosition);
                break;
            case 2:
                tabSheet.addTab((Component) altHistorySelection, "Alt History Selection", null, 2);
                tabSheet.setSelectedTab(lastPosition);
                break;    
            case 3:
                tabSheet.addTab((Component) allocation, "Allocation", null, 3);
                tabSheet.setSelectedTab(lastPosition);
                break;    
            case 4: 
                if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
                    tabSheet.addTab(discountSummary, "Summary", null, 4);
                } else {
                    tabSheet.addTab(summary, "Summary", null, 4);
                }
                tabSheet.setSelectedTab(lastPosition);
                break; 
        }
    }

    /**
     * Next Button Click Logic
     *
     * @param event
     */
    @UiHandler("nextBtn")
    public void nextTab(Button.ClickEvent event) {

        if (tabPosition == 0) {
            boolean flag = logic.checkSelectedCustomers(session);
            if (!flag) {
                AbstractNotificationUtils.getErrorNotification("Error",
                        "Please select a Customer record and move it to the ‘Selected Customers’ list view. Then try again.");
                return;
            }else{
                logic.deleteUnselectedCustomersOrItems(session,false);
            }
        }
        if (tabPosition == 1) {
            boolean flag = logic.checkSelectedItems(session);
            if (!flag) {
                AbstractNotificationUtils.getErrorNotification("Error",
                        "Please select at least one Item record and move it to the ‘Selected Items’ list view. Then try again.");
                return;
            }else{
                logic.deleteUnselectedCustomersOrItems(session,true);
            }
        }

        tabSheet.setSelectedTab(tabPosition + 1);
    }

    /**
     * previous Button Click Logic
     *
     * @param event
     */
    /**
     * Close Button Click Logic
     *
     * @param event
     */
    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            public void yesMethod() {
                try {
                    /* Below line is Used to remove all temp table data */
//                    AlternateHistoryLogic alternateHistoryLogic = new AlternateHistoryLogic();
//                    alternateHistoryLogic.removeTempTable(session);
//                    if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
//                        logic.executeDelete(session.getSessionId(), false);
//                    } else {
//                        logic.executeDelete(session.getSessionId(), true);
//                    }

                    /* Below line is Used close window */
                    close();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to Exit ?");
    }

    @UiHandler("populateBtn")
    public void populateBtnClickLogic(Button.ClickEvent event) {

        if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {

                }

                @Override
                public void yesMethod() {

                    Object[] orderedArg = {String.valueOf(session.getProjectionId()), tableLogic.getFrequency(), session.getUserId(), session.getSessionId()};
                    logic.call(orderedArg, session, "PRC_DISC_ALTERNATE_HIST");
                    logic.executeDelete(session, false);
                    tableLogic.setDiscountVariablesForLogic(session, projectionSelection, tableLogic.getStartAndEndPeriods(), true,
                            tableLogic.getDiscountList(), tableLogic.getLevelNo(), true, tableLogic.getRightDto(), tableLogic.getHierarchyIndicator(), tableLogic.getUserGroup(), tableLogic.getCurrentHierarchy(), tableLogic.isIsCustomHierarchy(), tableLogic.getCustomId(), tableLogic.getRelationshipBuilderSid());
                    logic.removeTempTable(session);
                    close();

                }
            }.getConfirmationMessage("Populate Confirmation", "Are you sure you want to complete the Alternate History Allocation process? \n The Historic Payment values that have been calculated for each CCP combination here will be loaded and saved to the system if you proceed..");
        } else {

            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {

                }

                @Override
                public void yesMethod() {
                    
                    Object[] orderedArg = {session.getProjectionId(), session.getFrequency(), session.getStartDate(), session.getEndDate(), session.getUserId(), session.getSessionId()};
                    logic.call(orderedArg, session, "PRC_SALES_ALTERNATE_HISTORY");
                    logic.executeDelete(session, true);
                    
                    close();
                }
            }.getConfirmationMessage("Populate Confirmation", "Are you sure you want to complete the Alternate History Allocation process? \n The Historic Unit values that have been calculated for each CCP combination here will be loaded and saved to the system if you proceed..");
        }
    }

    public void changeTab() {
        if (tabPosition == 3) {
            boolean flag = altHistorySelection.getSelectedItems();
            List list = altHistorySelection.getselectedPeriod();
            allocation.setAllocatedPeriods(list);
            if (!flag || list.isEmpty()) {
                return;
            }
        }
        tabSheet.setSelectedTab(tabPosition + 1);
    }
    
    private void deleteOldDataOnLoad() {
        logic.executeDelete(session, !session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION));
    }
    
}
