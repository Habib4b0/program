/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui.form.lookups;

import com.stpl.app.forecastabstract.lookups.AbstractSaveViewPopup;
import com.stpl.app.galforecasting.accrualrateprojection.dto.AccrualDataSelectionDTO;
import com.stpl.app.galforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.galforecasting.dto.SaveViewDTO;
import com.stpl.app.galforecasting.logic.DataSelectionLogic;
import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.logic.ViewLogic;
import com.stpl.app.galforecasting.queryUtils.PPAQuerys;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveViewPopup.
 *
 * @author soundarrajan
 */
public class SaveViewPopup extends AbstractSaveViewPopup {

    /**
     * The available customers.
     */
    public BeanItemContainer<Leveldto> availableCustomers;

    /**
     * The selected customers.
     */
    public BeanItemContainer<Leveldto> selectedCustomers;

    /**
     * The available products.
     */
    public BeanItemContainer<Leveldto> availableProducts;

    /**
     * The selected products.
     */
    public BeanItemContainer<Leveldto> selectedProducts;
    /**
     * The save view dto.
     */
    private final SaveViewDTO saveViewDTO = new SaveViewDTO();
    /**
     * The view binder.
     */
    private final CustomFieldGroup viewBinder = new CustomFieldGroup(new BeanItem<SaveViewDTO>(saveViewDTO));

    final ErrorLabel errorMsg = new ErrorLabel();
    private TextField viewId = new TextField();

    private TextField viewName = new TextField();

    private OptionGroup viewType = new OptionGroup();

    /**
     * The view logic.
     */
    public ViewLogic viewLogic = new ViewLogic();

    /**
     * The data selection binder.
     */
    private CustomFieldGroup dataSelectionBinder;

    /**
     * The logic.
     */
    public NonMandatedLogic logic = new NonMandatedLogic();

    List<String> customerListEndSids;
    List<String> productListEndSids;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SaveViewPopup.class);

    private DataSelectionDTO dataselectionDtoToSave;

    private List<Leveldto> selectedProductsList;
    private List<Leveldto> selectedCustomersList;
    private int projectionId = 0;
    List<Leveldto> customerHierarchyEndLevels;
    String productHierarchyEndLevelsHierNos;
    ViewDTO viewDTO;
    String screenName = StringUtils.EMPTY;
    String updateOrSave;

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
        LOGGER.info("Entering saveViewPopup");
        this.dataSelectionBinder = dataSelectionBinder;
        this.availableCustomers = availableCustomers;
        this.selectedCustomers = selectedCustomers;
        this.availableProducts = availableProducts;
        this.selectedProducts = selectedProducts;
        this.customerListEndSids = customerListEndSids;
        this.productListEndSids = productListEndSids;
        buildPopup();
        LOGGER.info("End of SaveViewPopup");
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
            List<Leveldto> selectedProductsList, List<Leveldto> customerHierarchyEndLevels, String productHierarchyEndLevelsHierNos, ViewDTO viewDTO, List<String> customerListEndSids, List<String> productListEndSids, final String screenName) {
        super(windowName);
        LOGGER.info("Entering saveViewPopup");
        this.dataselectionDtoToSave = dataselectionDtoToSave;
        this.selectedCustomersList = selectedCustomersList;
        this.selectedProductsList = selectedProductsList;
        this.customerHierarchyEndLevels = customerHierarchyEndLevels;
        this.productHierarchyEndLevelsHierNos = productHierarchyEndLevelsHierNos;
        this.viewDTO = viewDTO;
        this.customerListEndSids = customerListEndSids;
        this.productListEndSids = productListEndSids;
        this.screenName = screenName;
        buildPopup();
        LOGGER.info("End of SaveViewPopup");
    }

    /**
     * Builds the popup.
     */
    private void buildPopup() {
        LOGGER.info("Entering buildPopup method");
        setContent(buildPopupScreen(viewName, viewType));
        getViewBinder();
        configureAddUpdate();
        viewName.setImmediate(true);
        viewName.setConverter(new TextFieldConverter());
        viewType.focus();
        LOGGER.info("End of buildPopup");
    }

    /**
     *
     * Saves or updates the current view.
     */
    @Override
    protected void btnAddLogic() {
        LOGGER.info("Entering btnAddLogic method");
        try {
            if ((StringUtils.isBlank(viewName.getValue()) || Constant.NULL.equals(String.valueOf(viewName.getValue())))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else {
                if (isDuplicateView(viewName.getValue())) {
                    if (Constant.PRIVATE.equals(viewType.getValue())) {
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
                    saveView(selectedCustomersList, selectedProductsList, Constant.SAVE);
                }
            }
        } catch (SystemException sysException) {
            LOGGER.error(sysException);
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
        LOGGER.info("End of btnAddLogic");
    }

    /**
     * Checks if is duplicate view.
     *
     * @param viewName the view name
     * @return true, if checks if is duplicate view
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private boolean isDuplicateView(final String viewName) throws SystemException, Exception {
        LOGGER.info("Entering isDuplicateView method with viewName " + viewName);
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
            final String actionFlag) throws SystemException, PortalException, Exception {
        try {

            LOGGER.info("Entering saveView method");
            DataSelectionLogic dsLogic = new DataSelectionLogic();
            DSLogic accLogic = new DSLogic();
            int projectionIdValue = 0;
            int viewIdValue = 0;
            if (Constant.UPDATE_SMALL.equalsIgnoreCase(actionFlag)) {
                if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
                    projectionIdValue = accLogic.updateProjection(dataselectionDtoToSave, projectionId, false);
                    AccrualDataSelectionDTO dtoValue = new AccrualDataSelectionDTO();
                    dtoValue.setDeductionType(dataselectionDtoToSave.getDeductionLevel());
                    dtoValue.setDeductionValue(dataselectionDtoToSave.getDeductionValue());
                    dtoValue.setDeductionValueId(dataselectionDtoToSave.getDeductionValueId());
                    dtoValue.setProjectionId(StringUtils.EMPTY + projectionIdValue);
                    dtoValue.setScreenName("Save-View");
                    dtoValue.setActionFlag(Constant.UPDATE_SMALL);
                    accLogic.updateRebateValue(dtoValue);
                } else {
                    projectionIdValue = logic.updateProjection(dataselectionDtoToSave, projectionId, false, screenName);
                }

                dsLogic.updateCustomerHierarchyLogic(selectedCustomersList, customerListEndSids, projectionIdValue);
                dsLogic.updateProductHierarchyLogic(selectedProductsList, productListEndSids, projectionIdValue);
                dsLogic.updateCcpLogicView(customerHierarchyEndLevels, productHierarchyEndLevelsHierNos, "customer", projectionIdValue);

            } else {

                if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
                    projectionIdValue = accLogic.saveProjection(dataselectionDtoToSave, screenName);
                    AccrualDataSelectionDTO dtoValue = new AccrualDataSelectionDTO();
                    dtoValue.setDeductionType(dataselectionDtoToSave.getDeductionLevel());
                    dtoValue.setDeductionValue(dataselectionDtoToSave.getDeductionValue());
                    dtoValue.setDeductionValueId(dataselectionDtoToSave.getDeductionValueId());
                    dtoValue.setProjectionId(StringUtils.EMPTY + projectionIdValue);
                    dtoValue.setScreenName("Save-View");
                    dtoValue.setActionFlag(StringUtils.EMPTY);
                    accLogic.updateRebateValue(dtoValue);
                } else {
                    projectionIdValue = logic.saveProjection(dataselectionDtoToSave, screenName);
                }

                dsLogic.saveCustomerHierarchyLogic(selectedCustomersList, customerListEndSids, projectionIdValue, null, Constant.SAVE);
                dsLogic.saveProductHierarchyLogic(selectedProductsList, productListEndSids, projectionIdValue, null, Constant.SAVE);
                dsLogic.saveCcp(customerHierarchyEndLevels, productHierarchyEndLevelsHierNos, "customer", String.valueOf(projectionIdValue));

            }
            viewBinder.commit();

            if (projectionIdValue != 0) {

                viewIdValue = viewLogic.saveForecastViewMaster(saveViewDTO, projectionIdValue);

                viewId.setValue(String.valueOf(viewIdValue));
                setProjectionId(projectionIdValue);

                if (viewIdValue != 0) {
                    if (!Constant.FAIL.equals(String.valueOf(saveViewDTO.getViewId()))) {
                        if (Constant.SAVE.equals(actionFlag)) {
                            if (Constant.PRIVATE.equals(viewType.getValue())) {
                                AbstractNotificationUtils.getInfoNotification("View Added Successfully",
                                        "You have successfully added private view " + viewName.getValue());
                            } else {
                                AbstractNotificationUtils.getInfoNotification("View Added Successfully",
                                        "You have successfully added public view " + viewName.getValue());
                            }
                        } else {
                            if (Constant.PRIVATE.equals(viewType.getValue())) {
                                AbstractNotificationUtils.getInfoNotification("View Updated Successfully",
                                        "You have successfully Updated private view " + viewName.getValue());
                            } else {
                                AbstractNotificationUtils.getInfoNotification("View Updated Successfully",
                                        "You have successfully Updated public view " + viewName.getValue());
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in saveView ");
        }
        LOGGER.info("End of saveView");
    }

    @Override
    protected void btnUpdateLogic() {
        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);

        int non_creatoralert = 0;
        if (!dataselectionDtoToSave.getViewType().equals(StringUtils.EMPTY)) {
            if (dataselectionDtoToSave.getViewType().equals("public")) {
                if (!userId.equals(viewDTO.getCreatedUserid())) {
                    non_creatoralert++;
                }
            }

        }

        try {
            if ((StringUtils.isBlank(viewName.getValue()) || Constant.NULL.equals(String.valueOf(viewName.getValue())))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (non_creatoralert != 0) {
                AbstractNotificationUtils.getErrorNotification("Cannot update public view", "You cannot update Public View (" + viewDTO.getViewName() + ") because it was created by another user.You can choose to save a new profile under a different profile name");

            } else if (!viewName.getValue().toString().equals(viewDTO.getViewName())) {
                AbstractNotificationUtils.getErrorNotification("Cannot update view name", "View  name can't be Changed");
            } else {
                if (isDuplicateView(viewName.getValue())) {

                    close();
                    if (updateView()) {
                        saveView(selectedCustomersList, selectedProductsList, Constant.UPDATE_SMALL);
                    } else {
                        saveView(selectedCustomersList, selectedProductsList, Constant.SAVE);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " in save view -  btnUpdateLogic ");
        }
    }

    private boolean updateView() {
        try {
            viewBinder.commit();
        } catch (FieldGroup.CommitException ex) {
            java.util.logging.Logger.getLogger(SaveViewPopup.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (viewName.getValue() != null && !String.valueOf(viewName.getValue()).equals(StringUtils.EMPTY)) {
            ForecastingViewMaster updatedViewMaster = viewLogic.updateView(saveViewDTO, String.valueOf(viewName.getValue()), String.valueOf(viewType.getValue()));
            if (updatedViewMaster == null) {

                return false;
            }
            viewId.setValue(String.valueOf(updatedViewMaster.getViewId()));
            projectionId = Integer.parseInt(String.valueOf(updatedViewMaster.getProjectionId()));
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
        if (viewDTO == null || StringUtils.isBlank(viewDTO.getViewId()) || DASH.equals(viewDTO.getViewId())) {
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

    private void deleteViewRecord(String viewName, String viewType) {
        List input = new ArrayList();
        if (viewType.equals("Public")) {
            input.add("Private");
        } else {
            input.add("Public");
        }
        input.add(viewName);
        PPAQuerys.PPAUpdate(input, "Delete View Master");
    }

    @Override
    protected void configureResultTable(ExtPagedTable results, String indicator) {

    }

}
