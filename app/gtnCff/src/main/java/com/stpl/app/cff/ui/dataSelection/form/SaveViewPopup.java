/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.dataSelection.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.abstractCff.AbstractSaveViewPopup;
import com.stpl.app.cff.dto.SaveViewDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.logic.ViewLogic;
import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.parttwo.model.CffViewMaster;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mohamed.hameed
 */
public class SaveViewPopup extends AbstractSaveViewPopup {


    private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
    /**
     * The available customers.
     */
    protected BeanItemContainer<Leveldto> availableCustomers;
    /**
     * The selected customers.
     */
    protected BeanItemContainer<Leveldto> selectedCustomers;
    /**
     * The available products.
     */
    protected BeanItemContainer<Leveldto> availableProducts;
    /**
     * The selected products.
     */
    protected BeanItemContainer<Leveldto> selectedProducts;
    /**
     * The save view dto.
     */
    private final SaveViewDTO saveViewDTO = new SaveViewDTO();
    /**
     * The view binder.
     */
    private final CustomFieldGroup viewBinder = new CustomFieldGroup(new BeanItem<SaveViewDTO>(saveViewDTO));
    private final ErrorLabel errorMsg = new ErrorLabel();
    private final TextField viewId = new TextField();
    private final TextField viewName = new TextField();
    private final OptionGroup viewType = new OptionGroup();
    /**
     * The view logic.
     */
    protected final ViewLogic viewLogic = new ViewLogic();
    /**
     * The logic.
     */
    private final CFFLogic cffLogic = new CFFLogic();
    private final List<String> customerListEndSids;
    private final List<String> productListEndSids;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveViewPopup.class);
    private DataSelectionDTO dataselectionDtoToSave;
    private List<Leveldto> selectedProductsList;
    private List<Leveldto> selectedCustomersList;
    private int projectionId = 0;
    private List<Leveldto> customerHierarchyEndLevels;
    private String productHierarchyEndLevelsHierNos;
    private ViewDTO viewDTO;
    private SessionDTO sessionDTO;

    /**
     * Save view popup constructor.
     *
     * @param windowName Name of the popup
     * @param dataSelectionBinder the data selection binder
     * @param availableCustomers the available customers
     * @param selectedCustomers the selected customers
     * @param availableProducts the available products
     * @param selectedProducts the selected products
     */
    public SaveViewPopup(final String windowName, final CustomFieldGroup dataSelectionBinder, final BeanItemContainer<Leveldto> availableCustomers, final BeanItemContainer<Leveldto> selectedCustomers,
            final BeanItemContainer<Leveldto> availableProducts, final BeanItemContainer<Leveldto> selectedProducts, List<String> customerListEndSids, List<String> productListEndSids) {
        super(windowName);
        LOGGER.debug("Entering saveViewPopup");
        this.availableCustomers = availableCustomers;
        this.selectedCustomers = selectedCustomers;
        this.availableProducts = availableProducts;
        this.selectedProducts = selectedProducts;
        this.customerListEndSids = customerListEndSids == null ? customerListEndSids : new ArrayList<>(customerListEndSids);
        this.productListEndSids = productListEndSids == null ? productListEndSids : new ArrayList<>(productListEndSids);
        buildPopup();
        LOGGER.debug("End of SaveViewPopup= {}", dataSelectionBinder);
    }

    /**
     *
     * @param windowName
     * @param dataselectionDtoToSave
     * @param selectedProductsList
     * @param selectedCustomersList
     * @param customerHierarchyEndLevels
     * @param productHierarchyEndLevelsHierNos
     * @param viewDTO
     */
    public SaveViewPopup(String windowName, DataSelectionDTO dataselectionDtoToSave, List<Leveldto> selectedCustomersList,
            List<Leveldto> selectedProductsList, List<Leveldto> customerHierarchyEndLevels, String productHierarchyEndLevelsHierNos, ViewDTO viewDTO, List<String> customerListEndSids, 
            List<String> productListEndSids, SessionDTO sessionDTO) {
        super(windowName);
        LOGGER.debug("Entering saveViewPopup");
        this.dataselectionDtoToSave = dataselectionDtoToSave;
        this.selectedCustomersList = selectedCustomersList == null ? selectedCustomersList : new ArrayList<>(selectedCustomersList);
        this.selectedProductsList = selectedProductsList == null ? selectedProductsList : new ArrayList<>(selectedProductsList);
        this.customerHierarchyEndLevels = customerHierarchyEndLevels == null ? customerHierarchyEndLevels : new ArrayList<>(customerHierarchyEndLevels);
        this.productHierarchyEndLevelsHierNos = productHierarchyEndLevelsHierNos;
        this.viewDTO = viewDTO;
        this.customerListEndSids = customerListEndSids == null ? customerListEndSids : new ArrayList<>(customerListEndSids);
        this.productListEndSids = productListEndSids == null ? productListEndSids : new ArrayList<>(productListEndSids);
        this.sessionDTO = sessionDTO;
        buildPopup();
        LOGGER.debug("End of SaveViewPopup");
    }

    /**
     * Builds the popup.
     */
    private void buildPopup() {
        LOGGER.debug("Entering buildPopup method");
        setContent(buildPopupScreen(viewName, viewType));
        getViewBinder();
        configureAddUpdate();
        viewName.setImmediate(true);
        viewName.setConverter(new TextFieldConverter());
        viewType.focus();
        LOGGER.debug("End of buildPopup");
    }

    /**
     *
     * Saves or updates the current view.
     */
    @Override
    protected void btnAddLogic() {
        LOGGER.debug("Entering btnAddLogic method");
        try {
            if (StringUtils.isBlank(viewName.getValue()) || Constants.NULL.equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else {
                if (isDuplicateView(viewName.getValue())) {
                    if (Constants.PRIVATE.equals(viewType.getValue())) {
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
                    saveView(selectedCustomersList, selectedProductsList, Constants.SAVE, Constants.ZERO);
                }
            }
        } catch (SystemException sysException) {
            LOGGER.error(sysException.getMessage());
        } 
        LOGGER.debug("End of btnAddLogic");
    }

    /**
     * Checks if is duplicate view.
     *
     * @param viewName the view name
     * @return true, if checks if is duplicate view
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private boolean isDuplicateView(final String viewName) throws SystemException {
        LOGGER.debug("Entering isDuplicateView method with viewName= {} ", viewName);
        return viewLogic.isDuplicateView(viewName);
    }

    /**
     * Save view.
     *
     * @param selectedCustomersList the selected customers list
     * @param selectedProductsList the selected products list
     * @param actionFlag the action flag
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void saveView(final List<Leveldto> selectedCustomersList, final List<Leveldto> selectedProductsList,
            final String actionFlag, int projectionIdValue) {
        try {

            LOGGER.debug("Entering saveView method");
            DataSelectionLogic dsLogic = new DataSelectionLogic();
            int viewIdValue = 0;
            if ("Update".equalsIgnoreCase(actionFlag)) {
                projectionIdValue = cffLogic.saveCFFMaster(dataselectionDtoToSave, BOOLEAN_CONSTANT.getTrueFlag(), projectionIdValue,sessionDTO);
                dsLogic.updateCustomerHierarchyLogic(selectedCustomersList, customerListEndSids, projectionIdValue);
                dsLogic.updateProductHierarchyLogic(selectedProductsList, productListEndSids, projectionIdValue,dataselectionDtoToSave);
                dsLogic.updateCcpLogicView(customerHierarchyEndLevels, productHierarchyEndLevelsHierNos, "customer", projectionIdValue);
            } else {
                projectionIdValue = cffLogic.saveCFFMaster(dataselectionDtoToSave, BOOLEAN_CONSTANT.getFalseFlag(), projectionIdValue,sessionDTO);
                dsLogic.saveCustomerHierarchyLogic(selectedCustomersList, customerListEndSids, projectionIdValue, null, "save");
                dsLogic.saveProductHierarchyLogic(selectedProductsList, productListEndSids, projectionIdValue, null, "save",dataselectionDtoToSave);
                dsLogic.saveCcp(customerHierarchyEndLevels, productHierarchyEndLevelsHierNos, "customer", String.valueOf(projectionIdValue));

            }
            viewBinder.commit();
            if (projectionIdValue != 0) {
                viewIdValue = viewLogic.saveForecastViewMaster(saveViewDTO, projectionIdValue);
                viewId.setValue(String.valueOf(viewIdValue));
                setProjectionId(projectionIdValue);
                if (viewIdValue != 0 && !Constants.FAIL.equals(String.valueOf(saveViewDTO.getViewId()))) {
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
        } catch (PortalException | SystemException | Property.ReadOnlyException | FieldGroup.CommitException ex) {
            LOGGER.error(" in saveView= {} ", ex);
        }
        LOGGER.debug("End of saveView");
    }

    @Override
    protected void btnUpdateLogic() {
        int non_creatoralert = 0;
        if (!dataselectionDtoToSave.getViewType().equals("") && dataselectionDtoToSave.getViewType().equals("public") && !sessionDTO.getUserId().equals(viewDTO.getCreatedUserid())) {
                    non_creatoralert++;
        }

        try {
            if (StringUtils.isBlank(viewName.getValue()) || Constants.NULL.equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (non_creatoralert != 0) {
                AbstractNotificationUtils.getErrorNotification("Cannot update public view", "You cannot update Public View (" + viewDTO.getViewName() + ") because it was created by another user.You can choose to save a new profile under a different profile name");

            } else if (!viewName.getValue().toString().equals(viewDTO.getViewName())) {
                AbstractNotificationUtils.getErrorNotification("Cannot update view name", "View  name can't be Changed");
            } else {
                if (isDuplicateView(viewName.getValue())) {

                    close();
                    if (updateView()) {
                        saveView(selectedCustomersList, selectedProductsList, "Update", Integer.parseInt(viewDTO.getProjectionId()));
                    } else {
                        saveView(selectedCustomersList, selectedProductsList, "save", Constants.ZERO);
                    }
                }
            }
        } catch (SystemException | NumberFormatException e) {
            LOGGER.error(" in save view -  btnUpdateLogic= {}", e);
        }
    }

    private boolean updateView() {
        try {
            viewBinder.commit();
        } catch (FieldGroup.CommitException ex) {
            java.util.logging.Logger.getLogger(SaveViewPopup.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (viewName.getValue() != null && !String.valueOf(viewName.getValue()).equals(StringUtils.EMPTY)) {
            CffViewMaster updatedViewMaster = viewLogic.updateView(saveViewDTO, String.valueOf(viewName.getValue()), String.valueOf(viewType.getValue()));
            if (updatedViewMaster == null) {
                return false;
            }
            viewId.setValue(String.valueOf(updatedViewMaster.getCffViewMasterSid()));
            projectionId = Integer.parseInt(String.valueOf(updatedViewMaster.getCffMasterSid()));
            return true;
        }
        return false;
    }

    private CustomFieldGroup getViewBinder() {
        viewBinder.bindMemberFields(this);
        viewBinder.setItemDataSource(new BeanItem<SaveViewDTO>(saveViewDTO));
        viewBinder.setBuffered(true);
        viewBinder.setErrorDisplay(errorMsg);
        return viewBinder;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    private void configureAddUpdate() {
        if (viewDTO == null || StringUtils.isBlank(viewDTO.getViewId()) || "0".equals(viewDTO.getViewId())) {
            //Add new view
            getViewAddButton().setEnabled(true);
            getViewUpdateButton().setEnabled(false);
        } else {
            //Update view
            viewName.setValue(viewDTO.getViewName());
            getViewAddButton().setEnabled(false);
            getViewUpdateButton().setEnabled(true);
        }
    }

    @Override
    protected void btnCloseLogic() {
        close();
    }


    @Override
    protected void configureResultTable(ExtPagedTable results, String indicator) {
        return;
    }
    @Override
    protected void btnResetTableLogic() {
        return;
    }
}
