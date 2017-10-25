/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionvariance.form.lookup;

import com.stpl.app.gtnforecasting.abstractforecast.ForecastPVComparisonLookup;
import static com.stpl.app.gtnforecasting.abstractforecast.ForecastPVComparisonLookup.COMPARISON_RESULTS_COLUMNS;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import static com.stpl.app.utils.Constants.WindowMessagesName.CONFIRMATION;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.drools.core.util.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ComparisonLookup.
 *
 * @author soundarrajan
 */
public class NMComparisonLookup extends ForecastPVComparisonLookup {

    private Boolean recordSelectedFlag = false;
    private int currentProjId;
    List<ComparisonLookupDTO> selectedList;
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(NMComparisonLookup.class);
    /**
     * Screen Name
     */
    String screenName = StringUtils.EMPTY;

    /**
     * Constructor for ComparisonLookup.
     *
     * @param windowName Name of the window
     * @param moduleIndicator Indicates the module. NonMandated or Mandated or
     * Channels
     * @param comparisonLookup Textfield which opens this lookup
     */
    public NMComparisonLookup(final String windowName, final String moduleIndicator, final CustomTextField comparisonLookup, final int currentProjId, List<ComparisonLookupDTO> selectedList, final String screenName) {
        super(screenName, comparisonLookup);
        this.currentProjId = currentProjId;
        this.selectedList = selectedList;
        this.screenName = screenName;
        configureFields();
    }

    public void configureFields() {
        try {
            fromDate.setDateFormat("dd/M/yyyy");
            toDate.setDateFormat("dd/M/yyyy");
            selectedResultsBean.addAll(selectedList);
            addBtn.setEnabled(false);
            loadAvailableResults();
            loadSelectedResults();
            workFlowStatus.focus();
            workFlowStatus.setNullSelectionAllowed(true);
            workFlowStatus.setNullSelectionItemId(SELECT_ONE);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Search btn logic.
     *
     * @param event the event
     */
    protected void searchBtnLogic() {
        LOGGER.info("Inside searchBtnLogic");

        com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO comparisonLookup = new com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO();
        try {
            String workFlowState = String.valueOf(workFlowStatus.getValue());
            String toDatValue = null;
            String marketTypeValue = String.valueOf(marketType.getValue());
            String brandValue = String.valueOf(brand.getValue());
            String projecName = String.valueOf(projectionName.getValue());
            String projDesc = String.valueOf(description.getValue());
            String customerVal = String.valueOf(customer.getValue());
            String ndcNameValue = String.valueOf(ndcName.getValue());
            String ndcNoValue = String.valueOf(ndc.getValue());
            String contractVal = String.valueOf(contract.getValue());

            comparisonLookup.setMarketType(marketTypeValue);
            comparisonLookup.setBrand(brandValue);
            comparisonLookup.setProjectionName(projecName);
            comparisonLookup.setProjectionDescription(projDesc);
            comparisonLookup.setCustomer(customerVal);
            comparisonLookup.setNdcName(ndcNameValue);
            comparisonLookup.setNdcNo(ndcNoValue);
            comparisonLookup.setContract(contractVal);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            if (toDate.getValue() != null) {
                toDatValue = format.format(toDate.getValue());
            }

            if (workFlowStatus.getValue() != null && !Constant.NULL.equalsIgnoreCase(workFlowState) && !org.apache.commons.lang.StringUtils.EMPTY.equalsIgnoreCase(workFlowState)) {
                if (workFlowState.equals(Constant.SUBMITTED)) {
                    workFlowState = WorkflowConstants.getPendingStatus();
                }
                String notSearchProjId = "'" + currentProjId + "'";
                for (int j = 0; j < selectedResultsBean.size(); j++) {
                    notSearchProjId += ",'" + selectedResultsBean.getIdByIndex(j).getProjectionId() + "'";
                }
                comparisonLookup.setWorkflowStatus(workFlowState);
                comparisonLookup.setCurrentProjId(notSearchProjId);
                comparisonLookup.setCreatedDateFrom(fromDate.getValue());
                comparisonLookup.setCreatedDateTo(toDatValue);

                tableLogic.fireSetData(comparisonLookup, false, screenName);
                if (resultsTable.size() == 0) {
                    MessageBox.showPlain(Icon.INFO, "Error", "No results could be found that match the entered search criteria.", ButtonId.OK);
                    addBtn.setEnabled(false);
                    addBtn.setImmediate(true);
                } else {
                    addBtn.setEnabled(true);
                    addBtn.setImmediate(true);
                    CommonUIUtils.getMessageNotification("Search Completed");

                }
            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "Please select a Workflow Status", ButtonId.OK);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending searchBtnLogic");
    }

    /**
     * Reset btn logic.
     *
     * @param event the event
     */
    protected void resetBtnLogic() {
        LOGGER.info("Inside resetBtnLogic");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                workFlowStatus.setValue(null);
                marketType.setValue(org.apache.commons.lang.StringUtils.EMPTY);
                brand.setValue(org.apache.commons.lang.StringUtils.EMPTY);
                projectionName.setValue(org.apache.commons.lang.StringUtils.EMPTY);
                customer.setValue(org.apache.commons.lang.StringUtils.EMPTY);
                ndc.setValue(org.apache.commons.lang.StringUtils.EMPTY);
                ndcName.setValue(org.apache.commons.lang.StringUtils.EMPTY);
                description.setValue(org.apache.commons.lang.StringUtils.EMPTY);
                contract.setValue(org.apache.commons.lang.StringUtils.EMPTY);
                fromDate.setValue(null);
                toDate.setValue(null);

            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage(CONFIRMATION.getConstant(), "Are you sure you want to reset?");
        LOGGER.info("Ending resetBtnLogic");
    }

    /**
     * Adds the btn logic.
     *
     * @param event the event
     */
    protected void addBtnLogic(Button.ClickEvent event) {
        LOGGER.info("Inside addBtnLogic");

        if (recordSelectedFlag) {
            addItemsButtonClick(event);
            recordSelectedFlag = false;
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a projection to add.", ButtonId.OK);
        }
        LOGGER.info("Ending addBtnLogic");

    }

    /**
     * Projection reset btn logic.
     *
     * @param event the event
     */
    protected void projectionResetBtnLogic() {
        LOGGER.info("Inside projectionResetBtnLogic");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {

                projectionTable.removeAllItems();
                projectionTable.getContainerDataSource().removeAllItems();

            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage(CONFIRMATION.getConstant(), "Are you sure you want to reset the contents of the ‘Projections’ list view?");
        LOGGER.info("Ending projectionResetBtnLogic");
    }

    /**
     * Close btn logic.
     *
     * @param event the event
     */
    protected void closeBtnLogic() {
        LOGGER.info("Inside closeBtnLogic");
        if (projectionTable.getItemIds().isEmpty()) {
            comparison.setReadOnly(false);
            comparison.setValue(SELECT_ONE);
            comparison.setData(null);
            comparison.setImmediate(true);
            comparison.setReadOnly(true);
            selectedList.clear();
        }
        LOGGER.info("Ending closeBtnLogic");
    }

    /**
     * Removes the btn logic.
     *
     * @param event the event
     */
    protected void removeBtnLogic(Button.ClickEvent event) {
        LOGGER.info("Inside removeBtnLogic");
        if (recordSelectedFlag) {
            removeItemsButtonClick(event);
            recordSelectedFlag = false;
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a projection to remove. ", ButtonId.OK);
        }
        LOGGER.info("Ending removeBtnLogic");
    }

    private void loadAvailableResults() {
        LOGGER.info("Inside loadAvailableResults");
        Object[] objColumn = COMPARISON_RESULTS_COLUMNS;
        resultsTable.setConverter("createdDateFrom", new DateToStringConverter());
        projectionTable.setConverter("createdDateFrom", new DateToStringConverter());
        for (Object objColumn1 : objColumn) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
            }
        }
        resultsTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });
        LOGGER.info("Ending loadAvailableResults");
    }

    private void loadSelectedResults() {
        LOGGER.info("Inside loadSelectedResults");
        projectionTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });

        LOGGER.info("Ending loadSelectedResults");
    }

    /**
     * Results item click.
     *
     * @param obj the id
     * @throws PortalException the portal exception
     */
    protected void resultsItemClick(final Object obj) {
        if (obj == null) {
            recordSelectedFlag = false;
        } else {
            recordSelectedFlag = true;
        }
    }

    /**
     * Adds the items button click.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void addItemsButtonClick(final Button.ClickEvent event) {
        final java.util.Set<com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO> itemMasterDetailsList = (java.util.Set<com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO>) resultsTable.getValue();
        boolean flag = false;
        List<com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO> addedItem = new ArrayList<com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO>();
        if ((itemMasterDetailsList.size()) <= (NumericConstants.FIVE - selectedResultsBean.getItemIds().size())) {
            for (final Iterator<com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
                final com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO item = iterator.next();
                flag = false;
                if (selectedResultsBean.size() > 0) {
                    for (int j = 0; j < selectedResultsBean.size(); j++) {
                        if (selectedResultsBean.getIdByIndex(j).getProjectionId() == item.getProjectionId()) {
                            flag = false;
                            break;
                        } else {
                            flag = true;
                        }
                    }
                    if (flag) {
                        selectedResultsBean.addItem(item);

                        selectedList.add(item);
                        addedItem.add(item);
                    }
                } else {
                    selectedResultsBean.addItem(item);

                    selectedList.add(item);
                    addedItem.add(item);
                }

            }

            for (com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO item : addedItem) {
                if (resultsBean.containsId(item)) {
                    resultsBean.removeItem(item);
                }
            }
            projectionTable.setValue(null);
            resultsTable.setValue(null);
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Cannot Add more than Five items.  Please select five records or below and try again.", ButtonId.OK);
        }
        if (resultsBean.size() > 0) {
            addBtn.setEnabled(true);
            addBtn.setImmediate(true);
        } else {
            addBtn.setEnabled(false);
            addBtn.setImmediate(true);
        }
    }

    /**
     * Removes the items button click.
     *
     * @param event the event
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void removeItemsButtonClick(final Button.ClickEvent event) {
        final Object itemId = projectionTable.getValue();
        selectedResultsBean.removeItem(itemId);
        projectionTable.addItem(itemId);
        final java.util.Set<com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO> itemMasterDetailsList = (java.util.Set<com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO>) projectionTable.getValue();
        resultsBean.addAll(itemMasterDetailsList);
        for (final Iterator<com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
            final com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO item = iterator.next();
            selectedResultsBean.removeItem(item);
            selectedList.remove(item);
        }
        if (resultsBean.size() > 0) {
            addBtn.setEnabled(true);
            addBtn.setImmediate(true);
        } else {
            addBtn.setEnabled(false);
            addBtn.setImmediate(true);
        }
    }

    public ExtFilterTable getSelectedProjection() {
        return projectionTable;
    }
}
