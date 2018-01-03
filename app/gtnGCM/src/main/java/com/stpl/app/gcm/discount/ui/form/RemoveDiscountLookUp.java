/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import static com.stpl.app.gcm.discount.ui.form.Summary.LOGGER;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mohamed.hameed
 */
public class RemoveDiscountLookUp extends Window {

    private TabSheet mainTab = new TabSheet();
    private final RemoveDiscount removeDiscount = new RemoveDiscount();
    private final Summary summary = new Summary();
    private final ContractsDetailsDto dto = new ContractsDetailsDto();
    public List<RemoveDiscountDto> selecteditemList;
    private final Button closeBtn = new Button("CLOSE");
    private final Button nextBtn = new Button("NEXT");
    private final Button previousBtn = new Button("PREVIOUS");
    private final Button removeBtn = new Button("REMOVE DISCOUNT");
    private int tabPosition;
    public TreeTable dashboardTreeTable = new TreeTable();
    private int userId;
    private int sessionId;
    private final StplSecurity stplSecurity = new StplSecurity();

    public RemoveDiscountLookUp() {
    }

    public RemoveDiscountLookUp(final List<RemoveDiscountDto> selecteditemList, int userID, int sessionID) {
        this.selecteditemList = selecteditemList;
        this.userId = userID;
        this.sessionId = sessionID;
        mainTab = new TabSheet();
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        center();
        setCaption("Remove Discount");
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        VerticalLayout baseLayout = new VerticalLayout();
        mainTab.addStyleName(ValoTheme.TABSHEET_FRAMED);
        mainTab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        mainTab.setImmediate(true);
        mainTab.addTab(removeDiscount.getContent(selecteditemList, mainTab, summary, removeDiscount, userId, sessionId), "Discount Selection", null, 0);
        mainTab.addTab(summary.getContent(selecteditemList, dto, mainTab, removeDiscount), "Summary", null, 1);
        baseLayout.addComponent(mainTab);
        baseLayout.addComponent(getActionButton());
        setResizable(true);
        setModal(true);
        setClosable(true);
        addStyleName("valo-theme-customwindow");
        setContent(baseLayout);
        configureFields();
        configureSecurityPermissions();
    }

    public void windowClose() {
        close();
    }

    /**
     * Get action button
     *
     * @return
     */
    HorizontalLayout getActionButton() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(previousBtn);
        horizontalLayout.addComponent(closeBtn);
        horizontalLayout.addComponent(nextBtn);
        horizontalLayout.addComponent(removeBtn);

        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);
        return horizontalLayout;
    }

    public void configureFields() {
        removeBtn.setVisible(false);
        nextBtn.setImmediate(true);
        nextBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                mainTab.setSelectedTab(1);
            }
        });
        previousBtn.setImmediate(true);
        previousBtn.setVisible(false);
        previousBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                mainTab.setSelectedTab(0);
            }
        });
        closeBtn.setImmediate(true);
        closeBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        try {
                            close();
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }

                    }

                    @Override
                    public void noMethod() {
                        return;
                    }
                }.getConfirmationMessage("Confirmation", "Are you sure you want to close the window? ");

            }
        });
        removeBtn.setImmediate(true);
        removeBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        // do nothing
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
                        try {
                            String msgContent = "The selected Rebate has been Removed successfully";
                            List<String> tempTransferList = summary.removeRebate();
                            if (tempTransferList != null && !tempTransferList.isEmpty()) {
                                msgContent = msgContent + "'\n new Projection created with forecasting type -" + tempTransferList.get(0)
                                        + " \n and Projection Name - " + tempTransferList.get(1) + " ";
                            }

                            if (tempTransferList.size() > 0 && !tempTransferList.isEmpty()) {
                                MessageBox.showPlain(Icon.INFO, "Updated Successfully", msgContent, new MessageBoxListener() {
                                    /**
                                     * The method is triggered when a button of
                                     * the message box is pressed.
                                     */
                                    @SuppressWarnings("PMD")
                                    @Override
                                    public void buttonClicked(final ButtonId buttonId) {
                                        close();
                                        final Notification notif = new Notification("Discount removed Successfully", Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setPosition(Position.BOTTOM_CENTER);
                                        notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
                                        notif.setStyleName(ConstantsUtils.MY_STYLE);
                                        notif.show(Page.getCurrent());
                                    }
                                }, ButtonId.OK);
                            }
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }.getConfirmationMessage("Alert", "Are you sure you want to remove the discount?");

            }
        });

        mainTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                enableActionButton(tabPosition);
            }
        });
    }

    public void enableActionButton(int tabPosition) {
        if (tabPosition == 0) {
            previousBtn.setVisible(false);
            nextBtn.setVisible(true);
            removeBtn.setVisible(false);

        } else {
            previousBtn.setVisible(true);
            nextBtn.setVisible(false);
            removeBtn.setVisible(true);

        }
    }

    private void configureSecurityPermissions() {
        try {
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(userId), "GCM-Customer Management", "Remove Discount", "SumaryTab");
            closeBtn.setVisible(CommonLogic.isButtonVisibleAccess("closeBtn", functionHM));
            nextBtn.setVisible(CommonLogic.isButtonVisibleAccess("nextBtn", functionHM));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
