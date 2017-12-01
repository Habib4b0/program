package com.stpl.app.gtnforecasting.projectionvariance.form.lookup;

import com.stpl.app.gtnforecasting.abstractforecast.ForecastPVComparisonLookup;
import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonFilterGenerator;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.gtnforecasting.projectionvariance.logic.tablelogic.ProjectionVarianceTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.NUM_VALIDATION;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import com.stpl.app.utils.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.validator.RegexpValidator;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.drools.core.util.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class CustomerTreeLookup.
 */
public class MComparisonLookup extends ForecastPVComparisonLookup {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(MComparisonLookup.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The results container.
     */

    final private SessionDTO sessionDTO;

    Object removeItemId = new Object();
    boolean removeFlag = false;
    ProjectionVarianceTableLogic pvTableLogic;
    /**
     * Screen name
     */
    String screenName = StringUtils.EMPTY;
      /** To reduce unwanted DB hits **/
    Map<MultiKey,List> contractTypeList;
    private PVSelectionDTO pvSelectionDTO;
    private final HeaderUtils headerUtils = new HeaderUtils();

    /**
     * Instantiates a new Comparison lookUp.
     */
    public MComparisonLookup(CustomTextField comparison, SessionDTO sessionDTO, final String screenName, Map<MultiKey,List> contractTypeList,PVSelectionDTO pvSelectionDTO,ProjectionVarianceTableLogic pvTableLogic) {
        super(screenName, comparison);
        LOGGER.debug("ComparisonLookUp constructor started");
        this.sessionDTO = sessionDTO;
        this.screenName = screenName;
        this.contractTypeList=contractTypeList;
        this.pvSelectionDTO = pvSelectionDTO;
        this.pvTableLogic = pvTableLogic;
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setModal(true);
        setClosable(true);
        setWidth(NumericConstants.FLOAT_NINETY, Sizeable.Unit.PERCENTAGE);
        setWidth(NumericConstants.FLOAT_SEVENTY, Sizeable.Unit.PERCENTAGE);
        init();
        LOGGER.debug("ComparisonLookUp constructor ends");
    }

    /**
     * Inits the.
     */
    public void init() {
        LOGGER.debug("ComparisonLookUp init method started");
        configureFields();
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterGenerator(new ComparisonFilterGenerator(pvSelectionDTO, pvTableLogic, false,contractTypeList));
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        LOGGER.debug("ComparisonLookUp init method ends");
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        resultsTable.setSortEnabled(true);
        ndc.setImmediate(true);
        ndc.addValidator(new RegexpValidator(NUM_VALIDATION, "Please Enter Only Numeric"));
        fromDate.setDateFormat(DATE_FORMAT.getConstant());
        toDate.setDateFormat(DATE_FORMAT.getConstant());
        fromDate.addStyleName("dateField-align-center");
        toDate.addStyleName("dateField-align-center");
        projectionTable.setConverter("createdDateFrom", new DateToStringConverter());
        resultsTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });
        projectionTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });

        workFlowStatus.focus();
        workFlowStatus.setNullSelectionAllowed(true);
        workFlowStatus.setNullSelectionItemId(SELECT_ONE);

    }

    /**
     * Search btn.
     *
     * @param event the event
     */
    protected void searchBtnLogic() {
        if ((SELECT_ONE).equals(String.valueOf(workFlowStatus.getValue())) || workFlowStatus.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Search Error ", "Please select a Workflow Status. ");
        } else {
            searchButtonLogic();
        }
    }

    /**
     * Close btn.
     *
     * @param event the event
     */
    public void closeBtnLogic() {
        return;
    }

    /**
     * Projection reset btn.
     *
     * @param event the event
     */
    public void projectionResetBtnLogic() {
        new AbstractNotificationUtils() {
            public void noMethod() {
               return;
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                selectedResultsBean.removeAllItems();
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(), "Are you sure you want to reset the page to default/previous values?");
    }

    /**
     * Adds the btn.
     *
     * @param event the event
     */
    public void addBtnLogic(Button.ClickEvent event) {
        try {
            final java.util.Set<ComparisonLookupDTO> selectedItemId = (java.util.Set<ComparisonLookupDTO>) resultsTable.getValue();
            removeFlag = false;
            if (!selectedItemId.isEmpty()) {
                for (final Iterator<ComparisonLookupDTO> iterator = selectedItemId.iterator(); iterator.hasNext();) {
                    final ComparisonLookupDTO item = iterator.next();
                    String projectionId = String.valueOf(item.getProjectionId());
                    if (selectedResultsBean.size() == 0) {
                        selectedResultsBean.addBean(item);
                        resultsTable.setValue(null);
                        projectionTable.setValue(null);
                    } else {
                        List<ComparisonLookupDTO> itemIds = selectedResultsBean.getItemIds();
                        Map<String, Object> projectionMap = new HashMap<>();

                        for (ComparisonLookupDTO lookUpDTO : itemIds) {
                            projectionMap.put(String.valueOf(lookUpDTO.getProjectionId()), lookUpDTO);
                        }
                        if (selectedResultsBean.size() < NumericConstants.FIVE) {
                            if (!projectionMap.containsKey(projectionId)) {
                                selectedResultsBean.addBean(item);
                                resultsTable.setValue(null);
                                projectionTable.setValue(null);
                            } else {
                                AbstractNotificationUtils.getErrorNotification("Projection already exists", "Projection already selected. \n Please select another projection");
                            }
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Error", "Cannot Add more than Five items.  Please select five records or below and try again.");

                        }
                    }
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("No Value Selected", "Please select a projection to add. ");
            }
        } catch (Exception ex) {
              LOGGER.error(ex);
        }
    }

    /**
     * Removes the btn.
     *
     * @param event the event
     */
    public void removeBtnLogic(Button.ClickEvent event) {
        if (removeFlag) {
            removeItemsButtonClick();
            removeFlag = false;
        } else {
            AbstractNotificationUtils.getErrorNotification("No Value Selected", "Please select a Projection to remove. ");
        }
    }

    /**
     * Removes the items button click.
     *
     * @param event the event
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    protected void removeItemsButtonClick() {
        final Object itemId = projectionTable.getValue();
        selectedResultsBean.removeItem(itemId);
        resultsTable.addItem(itemId);
        projectionTable.addItem(itemId);
        final java.util.Set<ComparisonLookupDTO> itemMasterDetailsList = (java.util.Set<ComparisonLookupDTO>) projectionTable.getValue();
        resultsBean.addAll(itemMasterDetailsList);
        for (final Iterator<ComparisonLookupDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
            final ComparisonLookupDTO item = iterator.next();
            selectedResultsBean.removeItem(item);
        }
        if (resultsBean.size() > 0) {
            addBtn.setEnabled(true);        
        } else {
            addBtn.setEnabled(false);
        }
    }

    /**
     * Search button logic.
     */
    public void searchButtonLogic() {
        try {
            LOGGER.debug("Inside searchButtonLogic");
            boolean flag = true;
            ComparisonLookupDTO lookUpDTO = new ComparisonLookupDTO();

            if (Constant.SUBMITTED.equalsIgnoreCase(String.valueOf(workFlowStatus.getValue()))) {
                lookUpDTO.setWorkflowStatus(WorkflowConstants.getPendingStatus());
            } else {
                lookUpDTO.setWorkflowStatus(String.valueOf(workFlowStatus.getValue()));
            }
            lookUpDTO.setMarketType(marketType.getValue());
            lookUpDTO.setBrand(brand.getValue());
            lookUpDTO.setProjectionName(projectionName.getValue());
            lookUpDTO.setCustomer(customer.getValue());
            lookUpDTO.setNdcNo(ndc.getValue());
            lookUpDTO.setNdcName(ndcName.getValue());
            lookUpDTO.setContract(contract.getValue());
            lookUpDTO.setProjectionDescription(description.getValue());

            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            if (fromDate.getValue() != null) {
                lookUpDTO.setCreatedDateFrom(fromDate.getValue());
            }
            if (toDate.getValue() != null) {
                lookUpDTO.setCreatedDateTo(format.format(toDate.getValue()));
            }

            String notNeededProjectionIds = String.valueOf(sessionDTO.getProjectionId());
            if (selectedResultsBean.size() != 0) {
                List<ComparisonLookupDTO> list = selectedResultsBean.getItemIds();
                for (ComparisonLookupDTO tempLookUpDTO : list) {
                    notNeededProjectionIds += ",";
                    notNeededProjectionIds += tempLookUpDTO.getProjectionId();
                }

            }


            tableLogic.setContainerDataSource(resultsBean);
            resultsTable.setVisibleColumns(headerUtils.comparisonColumns);
            resultsTable.setColumnHeaders(headerUtils.comparisonHeader);

            if (lookUpDTO.getCreatedDateFrom() != null && lookUpDTO.getCreatedDateTo() != null && lookUpDTO.getCreatedDateFrom().equals(lookUpDTO.getCreatedDateTo())) {
                AbstractNotificationUtils.getErrorNotification("Date Range Error", "Start date and End date should not be equal");
                flag = false;
            }
                if ((flag) && (!tableLogic.fireSetData(lookUpDTO, sessionDTO, notNeededProjectionIds, false, screenName))) {

                    AbstractNotificationUtils.getErrorNotification("No Matching Records",
                            "There were no records matching the search criteria.  Please try again.");
                }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending searchButtonLogic");
    }

    /**
     * Gets the bean from id.
     *
     * @param obj the obj
     * @return the bean from id
     */
    public ComparisonLookupDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof ComparisonLookupDTO) {
            targetItem = new BeanItem<>(
                    (ComparisonLookupDTO) obj);
        }
        return (ComparisonLookupDTO) targetItem.getBean();
    }

    /**
     * Results item click.
     *
     * @param obj the id
     * @throws PortalException the portal exception
     */
    protected void resultsItemClick(final Object obj) {
        if (obj == null) {
            removeFlag = false;
        } else {
            removeFlag = true;
        }
    }
}
