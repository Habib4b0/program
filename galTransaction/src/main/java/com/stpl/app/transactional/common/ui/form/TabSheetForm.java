/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.ui.form;

import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.transactional.common.ui.view.SearchView;
import com.stpl.app.util.ConstantUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author sooriya.lakshmanan
 */
public class TabSheetForm extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(TabSheetForm.class.getName());
    CommonLogic logic = new CommonLogic();
    @UiField("tabSheet")
    TabSheet tabSheet;
    @UiField("backBtn")
    private Button backBtn;
    int masterSid = 0;
    /**
     * Adding the tabs in the tabsheet
     *
     * @param masterSid
     * @throws Exception
     */
    public TabSheetForm(final int masterSid) throws Exception {
        this.masterSid = masterSid;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/ui/common/tabsheetform.xml"), this));
        addToTabbar();
        clickListeners();
    }

    /**
     * Adds the to tabbar.
     *
     * @return the tab sheet
     */
    private void addToTabbar() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering addToTabbar");

        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        List<Object> tabList = new ArrayList<>();
        String tableName = StringUtils.EMPTY;

        if (!ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase((String) VaadinSession.getCurrent().getAttribute(ConstantUtil.SCREEN_NAME))) {
            tabList = logic.getTabName((String) VaadinSession.getCurrent().getAttribute(ConstantUtil.SCREEN_NAME));
            tableName = (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.TABLE_NAME);
        } else {
            tabList = logic.getTabName((String) VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME));
            tableName = (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME);
        }
        for (int i = 0; i < tabList.size(); i++) {
            Object[] ob = (Object[]) tabList.get(i);

                Object[] obList = new Object[3];
                obList[0] = logic.convertToDetailsDto(logic.getDetailsTableByID(Integer.valueOf(ob[0].toString()), false), tableName, 0);
                obList[1] = logic.convertToDetailsDto(logic.getDetailsTableByID(Integer.valueOf(ob[0].toString()), true), tableName, 0);

                obList[2] = ob[1].toString();
                tabSheet.addTab(new ViewForm(obList, logic.getValuesById(masterSid, obList)), ob[1].toString(), null, i);
            }


        LOGGER.info("Ends addToTabbar with tabsheet");
    }

    private void clickListeners() {
        LOGGER.info("Entering buttonClick for backButton");

        backBtn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                try {
                     if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase((String) VaadinSession.getCurrent().getAttribute(ConstantUtil.SCREEN_NAME))) {
                        getUI().getWindows().iterator().next().close();
                    }
                    getUI().getNavigator().navigateTo(SearchView.NAME);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
        LOGGER.info("Ends buttonClick for backButton");
    }
}
