/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import java.util.HashMap;
import java.util.Map;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author alok.v
 */
public class BaseRateForm extends CustomComponent implements View {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(BaseRateForm.class);
    /**
     * The Constant NAME.
     */
    public static final String NAME = "BaseRateForm";
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
    @UiField("approveBtn")
    Button approveBtn;
    /**
     * The reset btn.
     */
    @UiField("rejectBtn")
    Button rejectBtn;
    /**
     * The previous btn.
     */
    @UiField("cancelBtn")
    Button cancelBtn;
    /**
     * The next btn.
     */
    @UiField("saveBtn")
    Button saveBtn;
    /**
     * The close.
     */
    @UiField("nextTabBtn")
    Button nextTabBtn;
    /**
     * The refresh btn.
     */
    @UiField("submitBtn")
    Button submitBtn;
    /**
     * The submit.
     */
    @UiField("closeBtn")
    Button closeBtn;
    /**
     * Position of the tab.
     */
    int tabPosition = 0;
    private BaseRateDTO baseRateDTO;
    SessionDTO session;
    private CustomFieldGroup baseRateBinder;
    private BaseRateIndex baseRate;
    private BaseRateSummary baseRateSummary;
    private AdditionalInformation additionalInformation;

    /**
     * Instantiates a new channels form.
     *
     * @param dataSelectionDTO
     * @param session
     */
    public BaseRateForm(final BaseRateDTO baseRateDTO, final SessionDTO session, CustomFieldGroup custom) throws Exception {
        this.baseRateDTO = baseRateDTO;
        this.session = session;
        session.setSessionId("1");
        session.setProjectionId(614);
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/baseRateForm.xml"), this));
        init();
        addTab();
        tabSheet.setSelectedTab(1);
    }

    private void init() throws Exception {
        session.setSessionId("1");
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
        tabSheet.addTab(baseRate, "Base Rate Calculation", null, 0);
        tabSheet.addTab(baseRateSummary, "Base Rate Summary", null, 1);
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

    protected void buttonEnableLogic(int tabPosition, int i) {
        
        if (tabPosition != 0) {
            approveBtn.setVisible(true);
        } else if (tabPosition == 0) {
            rejectBtn.setVisible(false);
            approveBtn.setVisible(false);
        }
        if (tabPosition == 1) {
            rejectBtn.setVisible(false);
        } else {
            rejectBtn.setVisible(false);
            closeBtn.setVisible(true);
            submitBtn.setVisible(true);
        }

        if (tabPosition == 7) {
            rejectBtn.setVisible(true);
        } else {
            rejectBtn.setVisible(false);
        }

        if (tabPosition == i) {
            nextTabBtn.setVisible(false);
        } else {
            nextTabBtn.setVisible(true);
        }
        if (tabPosition <= i) {
            approveBtn.setEnabled(true);
        }

    }

    /**
     * On tab change.
     *
     * @param tabPosition the tab position
     */
    protected void onTabChange(int tabPosition) {
        if (tabPosition != 1) {
            if (!tabLazyLoadMap.get(tabPosition)) {
                tabLazyLoadMap.put(tabPosition, Boolean.TRUE);
                lazyLoadTab(tabPosition);
            }
        }
    }

    /**
     * Lazy load tab.
     *
     * @param tabPosition the tab position
     */
    protected void lazyLoadTab(int tabPosition) {
        if (tabPosition == 0) {
            tabSheet.replaceComponent(baseRate, baseRate.getContent());

        } 
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        tabSheet.setSelectedTab(1);
    }
                }
