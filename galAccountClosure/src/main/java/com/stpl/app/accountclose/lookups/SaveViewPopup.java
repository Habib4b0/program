/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.lookups;

import com.stpl.app.accountclose.dto.LevelDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.ViewDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.GTNbalanceLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.ViewLogic;
import com.stpl.app.accountclose.gtnbalancereport.utils.Constants;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import static com.stpl.app.accountclose.utils.Constants.LabelConstants.PRIVATE;
import static com.stpl.app.accountclose.utils.Constants.LabelConstants.PUBLIC;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.Button;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class SaveViewPopup extends Window {

    @UiField("viewOption")
    private OptionGroup viewType;
    @UiField("viewName")
    private TextField viewName;
    @UiField("cancelBtn")
    private Button cancelBtn;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("updateBtn")
    private Button updateBtn;
    private static final Logger LOGGER = Logger.getLogger(SaveViewPopup.class);
    ViewDTO viewDTO;
    ViewLogic viewLogic = new ViewLogic();
    DataSelectionDTO dataSelectionDTO;
    /**
     * The viewName logic.
     */
    final ErrorLabel errorMsg = new ErrorLabel();

    public SaveViewPopup(DataSelectionDTO dataSelectionDTO, ViewDTO viewDTO) {
        super("Save View");
        this.dataSelectionDTO = dataSelectionDTO;
        this.viewDTO = viewDTO;
        center();
        setClosable(true);
        setModal(true);
        setContent(Clara.create(getClass().getResourceAsStream("/saveViewPopup.xml"), this));
        configureFields();
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
    }

    private void configureFields() {
        viewType.addItem(PRIVATE.getConstant());
        viewType.addItem(PUBLIC.getConstant());
        viewType.setValue(PRIVATE.getConstant());

        cancelBtn.setImmediate(true);
        addBtn.setImmediate(true);
        updateBtn.setImmediate(true);
        if (viewDTO != null && "Public".equals(viewDTO.getViewType())) {
            viewType.select(PUBLIC.getConstant());
        } else {
            viewType.select(PRIVATE.getConstant());
        }
        configureAddUpdate();
    }

    @UiHandler("addBtn")
    public void addBtnviewLogic(Button.ClickEvent event) {
        try {
            if (StringUtils.isBlank(viewName.getValue())) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else {
                if (isDuplicateView(viewName.getValue(), String.valueOf(viewType.getValue()))) {
                    if (PRIVATE.getConstant().equals(viewType.getValue())) {
                        AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                                "The Private View name you have attempted to save is a duplicate of an existing view name. "
                                + "\nPlease enter a different view name");
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                                "The Public View name you have attempted to save is a duplicate of an existing view name. "
                                + "\nPlease enter a different view name");
                    }
                } else {
                    close();
                    saveView(Constants.SAVE);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("cancelBtn")
    public void cancelButtonLogic(Button.ClickEvent event) {
        close();
    }

    @UiHandler("updateBtn")
    public void updateBtnButtonLogic(Button.ClickEvent event) {
        try {
            if (StringUtils.isBlank(viewName.getValue())) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else {
                if (isDuplicateView(viewName.getValue(), String.valueOf(viewType.getValue()))) {
                    if (PRIVATE.getConstant().equals(viewType.getValue())) {
                        AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                                "The Private View name you have attempted to save is a duplicate of an existing view name. "
                                + "\nPlease enter a different view name");
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                                "The Public View name you have attempted to save is a duplicate of an existing view name. "
                                + "\nPlease enter a different view name");
                    }
                }else{
                    close();
                    saveView("Update");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Checks if is duplicate viewName.
     *
     * @param viewName the viewName name
     * @return true, if checks if is duplicate viewName
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private boolean isDuplicateView(final String viewName, final String viewType) throws SystemException, Exception {
        LOGGER.info("Entering isDuplicateView method with viewName " + viewName + " viewType is " + viewType);
        return viewLogic.isDuplicateView(viewName, viewType);
    }

    private void configureAddUpdate() {
        if (viewDTO == null || viewDTO.getViewMasterId() == 0) {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
        } else {
            viewName.setValue(viewDTO.getViewName());
            addBtn.setEnabled(false);
            updateBtn.setEnabled(true);
        }
    }

    /**
     * Save viewName.
     *
     * @param selectedCustomersList the selected customers list
     * @param selectedProductsList the selected products list
     * @param actionFlag the action flag
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void saveView(final String actionFlag) throws SystemException, PortalException, Exception {
            LOGGER.info("Entering saveView method");
            GTNbalanceLogic logic = new GTNbalanceLogic();
            int accountClosureId = 0;
            int viewIdValue = 0;
            if ("Update".equals(actionFlag)) {
                dataSelectionDTO.setAccountClosureSid(String.valueOf(viewDTO.getAccountClosureId()));
               
            }
                accountClosureId = logic.saveOrUpdateGtnReport(dataSelectionDTO,actionFlag);
         
            viewDTO.setViewName(viewName.getValue());
            viewDTO.setViewType(String.valueOf(viewType.getValue()));
            if (accountClosureId != 0) {
                viewDTO.setAccountClosureId(accountClosureId);
                viewIdValue = viewLogic.saveForecastViewMaster(viewDTO);
                if (viewIdValue != 0) {
                    if ("save".equals(actionFlag)) {
                        if (Constants.PRIVATE.equals(viewType.getValue())) {
                            AbstractNotificationUtils.getInfoNotification("View Added Successfully",
                                    "You have successfully added private view " + viewName.getValue());
                        } else {
                            AbstractNotificationUtils.getInfoNotification("View Added Successfully",
                                    "You have successfully added public view " + viewName.getValue());
                        }
                    } else {
                        if (Constants.PRIVATE.equals(viewType.getValue())) {
                            AbstractNotificationUtils.getInfoNotification("View Updated Successfully",
                                    "You have successfully Updated private view " + viewName.getValue());
                        } else {
                            AbstractNotificationUtils.getInfoNotification("View Updated Successfully",
                                    "You have successfully Updated public view " + viewName.getValue());
                        }
                    }
                }
            }
        LOGGER.info("End of saveView");
    }

}
