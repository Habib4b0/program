/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.gtnforecasting.discountProjection.logic.tableLogic.NMDiscountTableLoadLogic;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.discountProjection.form.AltSummeryDiscount;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.salesprojection.form.AlternateSummery;
import com.stpl.app.gtnforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.TabNameUtil;
import com.stpl.ifs.ui.util.NumericConstants;
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
    private Button nextBtn;
    
    @UiField("previousBtn")
    private Button previousBtn;
    
    @UiField("populateBtn")
    private Button populateBtn;

    /**
     * The tab sheet.
     */
    @UiField("tabSheet")
    private TabSheet tabSheet;

    private int tabPosition = 0;
    private int lastPosition = 0;
    private int currentPosition = 0;
    private boolean prevflag = false;
    private CustomerSelection customerSelection;
    private ItemSelection itemSelection;
    private AltHistorySelection altHistorySelection;
    private Allocation allocation;
    private AlternateSummery summary;
    private AltSummeryDiscount discountSummary;

    private final SessionDTO session;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AlternateHistory.class);
    private ProjectionSelectionDTO projectionSelection = new ProjectionSelectionDTO();
    private final NMDiscountTableLoadLogic tableLogic;
    private int rsModelSid = 0;
    private String rsName = StringUtils.EMPTY;
    private String ccpList = StringUtils.EMPTY;
    private final AlternateHistoryLogic logic = new AlternateHistoryLogic();
    private List<String> variableList=new ArrayList<>();

    public AlternateHistory(SessionDTO session,List<String> variableList) {
        this.session = session;
        this.projectionSelection = null;
        this.tableLogic = null;
        this.variableList=variableList;
        this.ccpList = session.getActualccp();         
        configInConstructor();

    }

    public void configInConstructor() {
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
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

    public AlternateHistory(SessionDTO session, ProjectionSelectionDTO projectionSelection, NMDiscountTableLoadLogic tableLogic, String ccpList, int rsModelSid, String rsName) {
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

    private void init()  {
        this.customerSelection = new CustomerSelection(session);
        this.itemSelection = new ItemSelection(session);
        this.altHistorySelection = new AltHistorySelection(session,this);
        this.allocation = new Allocation(session);

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
        tabSheet.addTab((Component) altHistorySelection, "Alt History Selection", null, NumericConstants.TWO);
        tabSheet.addTab((Component) allocation, Constant.ALLOCATION, null, NumericConstants.THREE);
        if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
            tabSheet.addTab(discountSummary, Constant.SUMMARY, null, NumericConstants.FOUR);
        } else {
            tabSheet.addTab(summary, Constant.SUMMARY, null, NumericConstants.FOUR);
        }
        attachTabChangeListener();
    }

    @UiHandler("previousBtn")
    public void previousTab(Button.ClickEvent event) {
        prevflag = true;
        if (tabPosition == NumericConstants.TWO || tabPosition == NumericConstants.THREE || tabPosition == NumericConstants.FOUR) {
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
                        if (lastPosition == 0 && lastPosition != tabPosition) {
                            boolean flag = logic.checkSelectedCustomers(session);
                            if (!flag) {
                                AbstractNotificationUtils.getErrorNotification(Constant.ERROR,
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
                            boolean flag = logic.checkSelectedItems(session);
                            if (!flag && tabPosition != 0) {
                                AbstractNotificationUtils.getErrorNotification(Constant.ERROR,
                                        "Please select at least one Item record and move it to the ‘Selected Items’ list view. Then try again.");
                                event.getTabSheet().setSelectedTab(lastPosition);
                                resetTab(tabPosition, lastPosition);
                                return;
                            }else{
                                logic.deleteUnselectedCustomersOrItems(session,true);
                            }
                        }
                        if (lastPosition == NumericConstants.TWO && tabPosition > NumericConstants.TWO) {
                            boolean flag = altHistorySelection.getSelectedItems();
                            List list = altHistorySelection.getselectedPeriod();
                            allocation.setAllocatedPeriods(list);
                            if (Constant.ALLOCATION.equals(tabName) && altHistorySelection.getAllocationCheck()) {
                                logic.resetTablesOnAllocation(session);
                                altHistorySelection.setAllocationCheck(false);                                
                            }
                            if (!flag || list.isEmpty()) {
                                event.getTabSheet().setSelectedTab(lastPosition);
                                resetTab(tabPosition, lastPosition);
                                return;
                            }
                        }
                        if (Constant.ALLOCATION.equals(tabName)) {
                            String ccpId =altHistorySelection.getCcpIds();
                            if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
                                Object[] orderedArg = {session.getCcps(), logic.getProjDetaSid(ccpList, session.getProjectionId()), rsModelSid, session.getUserId(), session.getSessionId()};

                                logic.call(orderedArg, "PRC_DISC_ALTERNATE_HIST_INSERT");
                                logic.insertforDiscountTotal(session , ccpId);

                            } else {                           
                                logic.altHistInsert(session, ccpList,ccpId);
                                logic.insertforSalesTotal(session, ccpId);
                            }

                            allocation.getContent(session);
                        }

                        lastPosition = tabPosition;
                        if (lastPosition > NumericConstants.THREE) {
                            populateBtn.setVisible(true);

                        }
                        if (tabPosition == 0 || tabPosition == 1) {
                            previousBtn.setVisible(false);
                        } else {
                            previousBtn.setVisible(true);
                        }
                        if (tabPosition == NumericConstants.FOUR) {
                            nextBtn.setVisible(false);
                        } else {
                            nextBtn.setVisible(true);
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex);
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
            case NumericConstants.TWO:
                tabSheet.addTab((Component) altHistorySelection, "Alt History Selection", null, NumericConstants.TWO);
                tabSheet.setSelectedTab(lastPosition);
                break;    
            case NumericConstants.THREE:
                tabSheet.addTab((Component) allocation, Constant.ALLOCATION, null, NumericConstants.THREE);
                tabSheet.setSelectedTab(lastPosition);
                break;    
            case NumericConstants.FOUR: 
                if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
                    tabSheet.addTab(discountSummary, Constant.SUMMARY, null, NumericConstants.FOUR);
                } else {
                    tabSheet.addTab(summary, Constant.SUMMARY, null, NumericConstants.FOUR);
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
                AbstractNotificationUtils.getErrorNotification(Constant.ERROR,
                        "Please select a Customer record and move it to the ‘Selected Customers’ list view. Then try again.");
                return;
            }else{
                logic.deleteUnselectedCustomersOrItems(session,false);
            }
        }
        if (tabPosition == 1) {
            boolean flag = logic.checkSelectedItems(session);
            if (!flag) {
                AbstractNotificationUtils.getErrorNotification(Constant.ERROR,
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
            @Override
            public void noMethod() {
                // do nothing
                LOGGER.debug("Inside Overriden method: do nothing");
            }

            @Override
            public void yesMethod() {
                try {

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
                    LOGGER.debug("Inside Overriden method: do nothing");
                }

                @Override
                public void yesMethod() {

                    Object[] orderedArg = {String.valueOf(session.getProjectionId()), tableLogic.getFrequency(), session.getUserId(), session.getSessionId()};
                    logic.call(orderedArg, "PRC_DISC_ALTERNATE_HIST");
                    logic.executeDelete(session, false);
                    tableLogic.setDiscountVariablesForLogic(session, projectionSelection, tableLogic.getStartAndEndPeriods(), true,
                            tableLogic.getDiscountList(), tableLogic.getLevelNo(), true, tableLogic.getRightDto(), tableLogic.getHierarchyIndicator(), 
                            tableLogic.getUserGroup(), tableLogic.getCurrentHierarchy(), tableLogic.isIsCustomHierarchy(), tableLogic.getCustomId(), 
                            tableLogic.getRelationshipBuilderSid());
                    logic.removeTempTable(session);
                    close();

                }
            }.getConfirmationMessage("Populate Confirmation", "Are you sure you want to complete the Alternate History Allocation process? \n The Historic Payment values that have been calculated for each CCP combination here will be loaded and saved to the system if you proceed..");
        } else {

            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    LOGGER.debug("Inside Overriden method: do nothing");
                }

                @Override
                public void yesMethod() {
                    
                    Object[] orderedArg = {session.getProjectionId(), session.getFrequency(), session.getStartDate(), session.getEndDate(), session.getUserId(), session.getSessionId()};
                    logic.call(orderedArg, "PRC_SALES_ALTERNATE_HISTORY");
                    logic.executeDelete(session, true);
                    
                    close();
                }
            }.getConfirmationMessage("Populate Confirmation", "Are you sure you want to complete the Alternate History Allocation process? \n The Historic Unit values that have been calculated for each CCP combination here will be loaded and saved to the system if you proceed..");
        }
    }

    public void changeTab() {
        if (tabPosition == NumericConstants.THREE) {
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
