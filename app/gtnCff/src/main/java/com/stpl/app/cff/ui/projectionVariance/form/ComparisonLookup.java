package com.stpl.app.cff.ui.projectionVariance.form;

import com.stpl.app.cff.abstractCff.AbstractComparisonLookup;
import com.stpl.app.cff.ui.projectionVariance.dto.ComparisonLookupDTO;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.WindowMessagesName.CONFIRMATION;
import com.stpl.app.cff.util.ConstantsUtil;
import static com.stpl.app.cff.util.ConstantsUtil.SELECT_ONE;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

/**
 * The Class CustomerTreeLookup.
 */
public class ComparisonLookup extends AbstractComparisonLookup {

    private Boolean recordSelectedFlag = false;
    private int currentProjId;
    List<ComparisonLookupDTO> selectedList;
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ComparisonLookup.class);
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
    public ComparisonLookup(final CustomTextField comparisonLookup,final int currentProjId, List<ComparisonLookupDTO> selectedList) {
        super(comparisonLookup);
        this.currentProjId= currentProjId;
        this.selectedList = selectedList;
        this.screenName = screenName;
        configureFields();
    }
    
    public void configureFields() {
        try{
        fromDate.setDateFormat("MM/dd/yyyy");
        toDate.setDateFormat("MM/dd/yyyy");
        selectedResultsBean.addAll(selectedList);
        addBtn.setEnabled(false);
        loadAvailableResults();
        loadSelectedResults();
        workFlowStatus.setNullSelectionAllowed(false);
        workFlowStatus.setValue(SELECT_ONE);
        workFlowStatus.focus();
        }catch(Exception ex){
            LOGGER.error(ex);
        }
    }

    /**
     * Search btn logic.
     *
     * @param event the event
     */
    protected void searchBtnLogic() {
        LOGGER.debug("Inside searchBtnLogic");
        // Implement
        ComparisonLookupDTO comparisonLookup = new ComparisonLookupDTO();
        try {
            String workFlowState = String.valueOf(workFlowStatus.getValue());
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
            
            if (workFlowStatus.getValue() != null && workFlowState!=null && !"null".equalsIgnoreCase(workFlowState) &&!"0".equalsIgnoreCase(workFlowState) && !org.apache.commons.lang.StringUtils.EMPTY.equalsIgnoreCase(workFlowState)) {
                if (workFlowState.equals(Constants.SUBMITTED)) {
                    workFlowState = "Pending";
                }
                String notSearchProjId = "'" + currentProjId + "'";
                for (int j = 0; j < selectedResultsBean.size(); j++) {
                    notSearchProjId += ",'" + selectedResultsBean.getIdByIndex(j).getProjectionId() + "'";
                }
                comparisonLookup.setWorkflowStatus(workFlowState);
                comparisonLookup.setCurrentProjId(notSearchProjId);
                comparisonLookup.setCreatedDateFrom(fromDate.getValue());
                comparisonLookup.setCreatedDateTo(toDate.getValue());
                tableLogic.fireSetData(comparisonLookup, false,screenName);
                if (resultsTable.size()==0) {
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
        LOGGER.debug("Ending searchBtnLogic");
    }

    /**
     * Reset btn logic.
     *
     * @param event the event
     */
    protected void resetBtnLogic() {
        LOGGER.debug("Inside resetBtnLogic");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                 final HelperDTO defaultValue = new HelperDTO(0,ConstantsUtil.SELECT_ONE);
                workFlowStatus.setValue(null);
                 workFlowStatus.select(defaultValue);
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
                //Implement
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage(CONFIRMATION.getConstant(), "Are you sure you want to reset?");
        LOGGER.debug("Ending resetBtnLogic");
    }

    /**
     * Adds the btn logic.
     *
     * @param event the event
     */
    protected void addBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("Inside addBtnLogic");
        // Implement
        if (recordSelectedFlag) {
            addItemsButtonClick(event);
            recordSelectedFlag = false;
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a projection to add.", ButtonId.OK);
        }
        LOGGER.debug("Ending addBtnLogic");

    }

    /**
     * Projection reset btn logic.
     *
     * @param event the event
     */
    protected void projectionResetBtnLogic() {
        LOGGER.debug("Inside projectionResetBtnLogic");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                //Implement
                projectionTable.removeAllItems();
                projectionTable.getContainerDataSource().removeAllItems();

            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage(CONFIRMATION.getConstant(), "Are you sure you want to reset the contents of the ‘Projections’ list view?");
        LOGGER.debug("Ending projectionResetBtnLogic");
    }

    /**
     * Close btn logic.
     *
     * @param event the event
     */
    protected void closeBtnLogic() {
        LOGGER.debug("Inside closeBtnLogic");
        if (projectionTable.getItemIds().isEmpty()) {
            comparison.setReadOnly(false);
            comparison.setValue(SELECT_ONE);
            comparison.setData(null);
            comparison.setImmediate(true);
            comparison.setReadOnly(true);
            selectedList.clear();
        }
        LOGGER.debug("Ending closeBtnLogic");
    }

    /**
     * Removes the btn logic.
     *
     * @param event the event
     */
    protected void removeBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("Inside removeBtnLogic");
        // Implement
        if (recordSelectedFlag) {
            removeItemsButtonClick(event);
            recordSelectedFlag = false;
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a projection to remove. ", ButtonId.OK);
        }
        LOGGER.debug("Ending removeBtnLogic");
    }

    private void loadAvailableResults() {
        LOGGER.debug("Inside loadAvailableResults");
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
        LOGGER.debug("Ending loadAvailableResults");
    }

    private void loadSelectedResults() {
        LOGGER.debug("Inside loadSelectedResults");
        projectionTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });
        LOGGER.debug("Ending loadSelectedResults");
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
        final java.util.Set<ComparisonLookupDTO> itemMasterDetailsList = (java.util.Set<ComparisonLookupDTO>) resultsTable.getValue();
        boolean flag = false;
        List<ComparisonLookupDTO> addedItem = new ArrayList<ComparisonLookupDTO>();
        if ((itemMasterDetailsList.size()) <= (NumericConstants.FIVE - selectedResultsBean.getItemIds().size())) {
            for (final Iterator<ComparisonLookupDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
                final ComparisonLookupDTO item = iterator.next();
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

            for (ComparisonLookupDTO item : addedItem) {
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
        final java.util.Set<ComparisonLookupDTO> itemMasterDetailsList = (java.util.Set<ComparisonLookupDTO>) projectionTable.getValue();
        resultsBean.addAll(itemMasterDetailsList);
        for (final Iterator<ComparisonLookupDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
            final ComparisonLookupDTO item = iterator.next();
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
