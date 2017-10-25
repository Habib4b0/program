/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.lookups;

import com.stpl.app.accountclose.gtnbalancereport.dto.ViewDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.DataSelectionLogic;
import com.stpl.app.accountclose.gtnbalancereport.utils.HeaderUtils;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;



/**
 *
 * @author santanukumar
 */
public class PrivatePublicView extends Window {

    /**
     * Indicator to indicate private or public view lookup.
     */
    private String indicator;
    /**
     * To refer the textfield that opens this lookup.
     */
    private TextField lookup;
    /**
     * The view name textfield for searching the view.
     */
    @UiField("viewName")
    private TextField viewName;
    /**
     * Result Table.
     */
    @UiField("resultTable")
    private ExtFilterTable results;
    /**
     * The search button.
     */
    @UiField("searchBtn")
    private Button searchBtn;
    /**
     * The reset button.
     */
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * The select button.
     */
    @UiField("selectBtn")
    private Button selectBtn;
    /**
     * The close button.
     */
    @UiField("closeBtn")
    private Button closeBtn;
    /**
     * Default container to results table.
     */
    private BeanItemContainer<ViewDTO> viewContainer = new BeanItemContainer<ViewDTO>(ViewDTO.class);
    private static final Logger LOGGER = Logger.getLogger(PrivatePublicView.class);
    private ViewDTO viewDTO;
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");
    /**
     * Constructor for PrivatePublicView.
     *
     * @param indicator to indicate whether the view is private or public
     * @param lookup To refer the textfield that opens this lookup
     * @param windowName the window name for lookup
     */
    public PrivatePublicView(final String indicator, final TextField lookup, final String windowName) {
        super(windowName);

        this.indicator = indicator;
        this.lookup = lookup;
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        center();
        setClosable(true);
        setModal(true);
        setWidth(75f, Sizeable.Unit.PERCENTAGE);
        setHeight(40f, Sizeable.Unit.PERCENTAGE);
        setContent(Clara.create(getClass().getResourceAsStream("/privatePublicView.xml"), this));
        configureField();
    }

    /**
     * Configure the components here.
     */
    protected void configureField() {
        results.setContainerDataSource(viewContainer);
        results.setVisibleColumns(HeaderUtils.VIEW_LOOKUP_COLUMNS);
        results.setColumnHeaders(HeaderUtils.VIEW_LOOKUP_HEADERS);
        results.setSelectable(true);
        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setStyleName("filtertable");
        viewName.focus();
        results.setPageLength(10);
        results.setHeight(400, Sizeable.Unit.PIXELS);
        results.addStyleName("custom-table-header-alignment");
        results.setColumnAlignments(ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER,
                ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT,ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT,
                ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.CENTER, ExtCustomTable.Align.LEFT);
        LOGGER.info("End of configureField");
        viewName.setImmediate(true);
    }

    /**
     * Select btn logic.
     *
     * @param event the event
     */
    @UiHandler("selectBtn")
    public void selectBtnLogic(Button.ClickEvent event) {
        LOGGER.info("Entering selectBtn method");
        if (results.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_033"),
                   confirmationMessage.getString("BR_MSG_ID_024") );
        } else {
            AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    viewName.setValue(StringUtils.EMPTY);
                    final ViewDTO itemId = (ViewDTO) results.getValue();
                    setViewDTO(itemId);
                    close();
                }
                @Override
                public void noMethod() {
                }
            };
            notificationUtils.getOkCancelMessage(confirmationMessage.getString("BR_MSG_ID_034"),confirmationMessage.getString("BR_MSG_ID_035"));

        }
        LOGGER.info("End of selectBtn method");
    }

    /**
     * Close btn logic.
     *
     * @param event the event
     */
    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
        close();
    }

    /**
     * Search btn logic.
     *
     * @param event the event
     */
    @UiHandler("searchBtn")
    public void searchBtnLogic(Button.ClickEvent event) {
        LOGGER.info("Start of searchBtnLogic method");
        if(StringUtils.isNotBlank(viewName.getValue())){
            try {
                viewContainer.removeAllItems();
                List<ViewDTO> resultList=new DataSelectionLogic().getSearchViews(indicator,viewName.getValue());
                if(!resultList.isEmpty()){
                     viewContainer.addAll(resultList);
                      Notification.show("Search Completed");
                }else{
                    AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_026"),
                        confirmationMessage.getString("BR_MSG_ID_020"));
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }else{
            MessageBox.showPlain(Icon.INFO, confirmationMessage.getString("BR_MSG_ID_026"),confirmationMessage.getString("BR_MSG_ID_036"), ButtonId.OK);
        }
        LOGGER.info("End of searchBtnLogic method");
    }

    /**
     * Reset btn logic.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                viewName.setValue(StringUtils.EMPTY);
            }

            @Override
            public void noMethod() {
            }
        };
        notificationUtils.getConfirmationMessage(confirmationMessage.getString("BR_MSG_ID_037"), confirmationMessage.getString("BR_MSG_ID_030"));
    }

    public ViewDTO getViewDTO() {
        return viewDTO;
    }

    public void setViewDTO(ViewDTO viewDTO) {
        this.viewDTO = viewDTO;
    }
}
