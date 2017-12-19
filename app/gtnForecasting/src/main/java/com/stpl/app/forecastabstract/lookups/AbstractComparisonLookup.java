/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastabstract.lookups;

import static com.stpl.app.utils.Constants.ButtonConstants.BTN_ADD;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_REMOVE;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_RESET;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SUBMIT;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import static com.stpl.app.utils.Constants.LabelConstants.BRAND;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT;
import static com.stpl.app.utils.Constants.LabelConstants.CREATED_DATE;
import static com.stpl.app.utils.Constants.LabelConstants.FROM;
import static com.stpl.app.utils.Constants.LabelConstants.NDC_NAME;
import static com.stpl.app.utils.Constants.LabelConstants.NDC_NO;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTION_DESCRIPTION;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTION_NAME;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTION_SEARCH;
import static com.stpl.app.utils.Constants.LabelConstants.RESULTS;
import static com.stpl.app.utils.Constants.LabelConstants.TO;
import static com.stpl.app.utils.Constants.LabelConstants.WORKFLOW_STATUS;
import static com.stpl.app.utils.Constants.LevelConstants.LEVEL_CONTRACT_HOLDER;
import static com.stpl.app.utils.Constants.LevelConstants.LEVEL_MARKET_TYPE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.queryUtils.PVQueryUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 *
 * @author
 */
public abstract class AbstractComparisonLookup extends AbstractLookup {

	public static final String SIX_FIFTY_PX = "650px";
	public static final String THERAPEUTIC_CLASS = "Therapeutic Class";

	protected TextField comparisonLookup;
	private NativeSelect workflowStatus;
	private TextField marketType;
	private TextField brand;
	private TextField projectionName;
	private TextField contractHolder;
	private TextField ndcNo;
	private TextField projectionDescription;
	private TextField contract;
	private TextField ndcName;
	private PopupDateField createdDateFrom;
	private PopupDateField createdDateTo;
	private ExtFilterTable results;
	private ExtFilterTable selectedProjection;
	private int currentProjId;
	private List<ComparisonLookupDTO> selectedList;
	/**
	 * The record selected flag.
	 */
	private Boolean recordSelectedFlag = false;
	private final BeanItemContainer<ComparisonLookupDTO> resultsBean = new BeanItemContainer<>(ComparisonLookupDTO.class);
	private final BeanItemContainer<ComparisonLookupDTO> selectedResultsBean = new BeanItemContainer<>(
			ComparisonLookupDTO.class);
	/**
	 * The Constant COMPARISON_RESULTS_COLUMNS.
	 */
	public final Object[] comparisonResultsColumns = new Object[] { Constant.PROJECTION_NAME,
			Constant.PROJECTIONDESCRIPTION, Constant.MARKET_TYPE, "contractHolder", Constant.CONTRACT, Constant.BRAND,
			"createdDateFrom", "createdBy" };
	/**
	 * The Constant COMPARISON_RESULTS_HEADER.
	 */
	public final String[] comparisonResultsHeader = new String[] { "Projection Name", "Description", "Market Type",
			"Contract Holder", Constant.CONTRACT_SMALL, Constant.BRAND_CAPS, "Created Date", "Created By" };
	private static final Logger LOGGER = Logger.getLogger(AbstractComparisonLookup.class);
	Button addButton = new Button(BTN_ADD.getConstant());

	/**
	 * Constructor for AbstractComparisonLookup
	 *
	 * @param windowName
	 *            Window name of the lookup
	 * @param moduleIndicator
	 *            Indicates the module. NonMandated or Mandated or Channels
	 * @param comparisonLookup
	 *            Textfield which opens this lookup
	 */
	public AbstractComparisonLookup(final String windowName, final TextField comparisonLookup) {
		super(windowName);
		this.comparisonLookup = comparisonLookup;
		setWidth(NumericConstants.THOUSAND_FIVE_HUNDRED, Sizeable.Unit.PIXELS);
	}

	/**
	 * Builds the lookup
	 *
	 * @param results
	 *            Search results table
	 * @param selectedProjection
	 *            Selected projection table
	 * @return
	 */
	protected VerticalLayout buildComparisonLookup(final ExtFilterTable results,
			final ExtFilterTable selectedProjection, int currentProjId, final List<ComparisonLookupDTO> selectedList) {
		this.results = results;
		this.selectedProjection = selectedProjection;
		this.currentProjId = currentProjId;
		this.selectedList = selectedList;
		return addComparisonLookup();
	}

	/**
	 * Adds the components to the lookup
	 *
	 * @return layout containing all the components
	 */
	private VerticalLayout addComparisonLookup() {
		VerticalLayout comparisonLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		comparisonLayout.addComponent(addProjectionSearchSection());
		comparisonLayout.addComponent(addResultsSection());
		comparisonLayout.addComponent(addSelectedProjections());
		comparisonLayout.addComponent(addFooterButtons());

		configureTable();
		return comparisonLayout;
	}

	/**
	 * Adds the search criteria
	 *
	 * @return Layout containing Projection Search Section
	 */
	private VerticalLayout addProjectionSearchSection() {
		VerticalLayout searchSectionLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		searchSectionLayout.setMargin(false);
		searchSectionLayout.addComponent(addSearchCriteria());
		searchSectionLayout.addComponent(addSearchCriteriaButtons(workflowStatus, marketType, brand, projectionName,
				projectionDescription, contract, contractHolder, ndcName, ndcNo, createdDateFrom, createdDateTo));
		workflowStatus.focus();
		return searchSectionLayout;
	}

	/**
	 * Adds the search criteria contents
	 *
	 * @return Panel containing the search criteria
	 */
	private Panel addSearchCriteria() {
		Panel searchPanel = UiUtils.addCommonPanel(PROJECTION_SEARCH.getConstant());
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setMargin(false);
		mainLayout.setStyleName(Constant.WIDTH_AUTO);
		HorizontalLayout searchCriteria = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setSpacing(true);
		vLayout.addStyleName("comparisonlookup");
		GridLayout searchLayout = new GridLayout(NumericConstants.SIX, NumericConstants.THREE);
		searchLayout.setSpacing(true);

		Panel createdDatePanel = UiUtils.addCommonPanel(CREATED_DATE.getConstant());
		HorizontalLayout fromLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		HorizontalLayout toLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		VerticalLayout createdDateLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		initializeSearchCriteria();
		loadWorkStatus();

		searchLayout.addComponent(
				UiUtils.makeLabel(WORKFLOW_STATUS.getConstant(), NumericConstants.FLOAT_ONE_FIVE_FOUR, Unit.PIXELS));
		searchLayout.addComponent(workflowStatus);
		searchLayout.addComponent(
				UiUtils.makeLabel(LEVEL_MARKET_TYPE.getConstant(), NumericConstants.FLOAT_ONE_ONE_SIX, Unit.PIXELS));
		searchLayout.addComponent(marketType);
		searchLayout
				.addComponent(UiUtils.makeLabel(BRAND.getConstant(), NumericConstants.FLOAT_EIGHTY_SEVEN, Unit.PIXELS));
		searchLayout.addComponent(brand);
		searchLayout.addComponent(
				UiUtils.makeLabel(PROJECTION_NAME.getConstant(), NumericConstants.FLOAT_ONE_FIVE_FOUR, Unit.PIXELS));
		searchLayout.addComponent(projectionName);
		searchLayout.addComponent(UiUtils.makeLabel(LEVEL_CONTRACT_HOLDER.getConstant(),
				NumericConstants.FLOAT_ONE_ONE_SIX, Unit.PIXELS));
		searchLayout.addComponent(contractHolder);
		searchLayout.addComponent(
				UiUtils.makeLabel(NDC_NO.getConstant(), NumericConstants.FLOAT_EIGHTY_SEVEN, Unit.PIXELS));
		searchLayout.addComponent(ndcNo);
		searchLayout.addComponent(UiUtils.makeLabel(PROJECTION_DESCRIPTION.getConstant(),
				NumericConstants.FLOAT_ONE_SIX_FIVE, Unit.PIXELS));
		searchLayout.addComponent(projectionDescription);
		searchLayout.addComponent(
				UiUtils.makeLabel(CONTRACT.getConstant(), NumericConstants.FLOAT_ONE_ONE_SIX, Unit.PIXELS));
		searchLayout.addComponent(contract);
		searchLayout.addComponent(
				UiUtils.makeLabel(NDC_NAME.getConstant(), NumericConstants.FLOAT_EIGHTY_SEVEN, Unit.PIXELS));
		searchLayout.addComponent(ndcName);
		fromLayout.addComponent(UiUtils.makeLabel(FROM.getConstant(), NumericConstants.FLOAT_THIRTY_FIVE, Unit.PIXELS));
		fromLayout.addComponent(createdDateFrom);
		toLayout.addComponent(UiUtils.makeLabel(TO.getConstant(), NumericConstants.FLOAT_THIRTY_FIVE, Unit.PIXELS));
		toLayout.addComponent(createdDateTo);
		createdDateLayout.addComponent(fromLayout);
		createdDateLayout.addComponent(toLayout);
		createdDatePanel.setContent(createdDateLayout);
		vLayout.addComponent(searchLayout);
		searchCriteria.addComponent(vLayout);
		searchCriteria.addComponent(createdDatePanel);
		searchCriteria.setMargin(false);

		mainLayout.addComponent(searchCriteria);
		searchPanel.setContent(mainLayout);

		return searchPanel;
	}

	/**
	 * Adds the results table to the lookup
	 *
	 * @return A layout containing results table and ADD button
	 */
	private VerticalLayout addResultsSection() {
		VerticalLayout resultSectionLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		Panel resultsPanel = UiUtils.addCommonPanel(RESULTS.getConstant());

		resultsPanel.setContent(results);
		results.setContainerDataSource(resultsBean);
		Object[] objColumn = comparisonResultsColumns;
		for (Object objColumn1 : objColumn) {
			String value = objColumn1.toString();
			if (value.endsWith("Date")) {
				results.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
			}
		}
		results.setVisibleColumns(comparisonResultsColumns);
		results.setColumnHeaders(comparisonResultsHeader);
		results.setSelectable(true);
		results.setMultiSelect(true);
		results.setFilterBarVisible(true);
		results.setHeight("400px");
		results.setStyleName(Constant.FILTER_TABLE);
		results.setPageLength(NumericConstants.SEVEN);
		results.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * Method called when available results value is changed.
			 */
			@SuppressWarnings("PMD")
                        @Override
			public void valueChange(final Property.ValueChangeEvent event) {
				resultsItemClick(event.getProperty().getValue());
			}
		});
		addButton.setEnabled(false);
		resultSectionLayout.addComponent(resultsPanel);
		resultSectionLayout.addComponent(getAddButton());
		resultSectionLayout.setMargin(false);
		return resultSectionLayout;
	}

	/**
	 * Initializes components for search criteria
	 */
	private void initializeSearchCriteria() {
		setWorkflowStatus(UiUtils.addDefaultNativeSelect());
		setMarketType(UiUtils.addTextField());
		setBrand(UiUtils.addTextField());
		setProjectionName(UiUtils.addTextField());
		setProjectionDescription(UiUtils.addTextField());
		setContract(UiUtils.addTextField());
		setContractHolder(UiUtils.addTextField());
		setNdcName(UiUtils.addTextField());
		setNdcNo(UiUtils.addTextField());
		setCreatedDateFrom(UiUtils.addDate());
		setCreatedDateTo(UiUtils.addDate());
	}

	/**
	 * Adds the selected projections table
	 *
	 * @return Layout with selected projections table
	 */
	private VerticalLayout addSelectedProjections() {
		VerticalLayout selectedProjectionsLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		Panel selectedProjections = UiUtils.addCommonPanel(PROJECTIONS.getConstant());
		selectedProjections.setContent(selectedProjection);
		selectedProjection.setContainerDataSource(selectedResultsBean);
		selectedProjection.setVisibleColumns(comparisonResultsColumns);
		selectedProjection.setColumnHeaders(comparisonResultsHeader);
		selectedProjection.setSelectable(true);
		selectedProjection.setMultiSelect(true);
		selectedProjection.setFilterBarVisible(true);
		selectedProjection.setHeight("400px");
		selectedProjection.setStyleName(Constant.FILTER_TABLE);
		selectedProjection.setPageLength(NumericConstants.SEVEN);
		selectedProjection.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * Method called when available results value is changed.
			 */
			@SuppressWarnings("PMD")
                        @Override
			public void valueChange(final Property.ValueChangeEvent event) {
				resultsItemClick(event.getProperty().getValue());
			}
		});
		selectedProjectionsLayout.addComponent(selectedProjections);
		selectedProjectionsLayout.setMargin(false);
		return selectedProjectionsLayout;
	}

	/**
	 * Adds the footer button layout containing Submit, Close and Remove buttons
	 *
	 * @return the footer button layout
	 */
	private HorizontalLayout addFooterButtons() {
		HorizontalLayout footerLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		footerLayout.addComponent(getSubmitBtn());
		footerLayout.addComponent(getResetResultsButton(selectedProjection));
		footerLayout.addComponent(getCloseButton());
		footerLayout.addComponent(getRemoveBtn());
		footerLayout.setMargin(false);
		return footerLayout;
	}

	private void loadWorkStatus() {
		final IndexedContainer container = new IndexedContainer();
		container.addItem(Constant.SAVED);
		container.addItem(Constant.SUBMITTED);
		container.addItem(WorkflowConstants.getApprovedStatus());
		container.addItem(WorkflowConstants.getRejectedStatus());
		workflowStatus.setContainerDataSource(container);
		workflowStatus.setImmediate(true);
	}

	/**
	 * Configure the components here
	 */
	private void configureTable() {
		results.setSizeFull();
		selectedProjection.setSizeFull();
		selectedResultsBean.addAll(selectedList);
	}

	@Override
	protected void btnLookupSearchLogic() {
	}

	@Override
	protected void btnLookupAnotherSearchLogic() {

	}

	@Override
	protected void btnUpdateLogic() {

	}

	@Override
	protected void btnAddLogic() {
	}

	@Override
	protected void btnImportLogic() {

	}

	@Override
	protected void btnLookupSelectLogic() {
	}

	@Override
	protected void configureResultTable(ExtFilterTable results, String indicator) {
	}

	public TextField getComparisonLookup() {
		return comparisonLookup;
	}

	public void setComparisonLookup(TextField comparisonLookup) {
		this.comparisonLookup = comparisonLookup;
	}

	public NativeSelect getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(NativeSelect workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public TextField getMarketType() {
		return marketType;
	}

	public void setMarketType(TextField marketType) {
		this.marketType = marketType;
	}

	public TextField getBrand() {
		return brand;
	}

	public void setBrand(TextField brand) {
		this.brand = brand;
	}

	public TextField getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(TextField projectionName) {
		this.projectionName = projectionName;
	}

	public TextField getContractHolder() {
		return contractHolder;
	}

	public void setContractHolder(TextField contractHolder) {
		this.contractHolder = contractHolder;
	}

	public TextField getNdcNo() {
		return ndcNo;
	}

	public void setNdcNo(TextField ndcNo) {
		this.ndcNo = ndcNo;
	}

	public TextField getContract() {
		return contract;
	}

	public void setContract(TextField contract) {
		this.contract = contract;
	}

	public TextField getNdcName() {
		return ndcName;
	}

	public void setNdcName(TextField ndcName) {
		this.ndcName = ndcName;
	}

	public PopupDateField getCreatedDateFrom() {
		return createdDateFrom;
	}

	public void setCreatedDateFrom(PopupDateField createdDateFrom) {
		this.createdDateFrom = createdDateFrom;
	}

	public PopupDateField getCreatedDateTo() {
		return createdDateTo;
	}

	public void setCreatedDateTo(PopupDateField createdDateTo) {
		this.createdDateTo = createdDateTo;
	}

	public TextField getProjectionDescription() {
		return projectionDescription;
	}

	public void setProjectionDescription(TextField projectionDescription) {
		this.projectionDescription = projectionDescription;
	}

	public ExtFilterTable getResults() {
		return results;
	}

	public void setResults(ExtFilterTable results) {
		this.results = results;
	}

	public ExtFilterTable getSelectedProjection() {
		return selectedProjection;
	}

	public void setSelectedProjection(ExtFilterTable selectedProjection) {
		this.selectedProjection = selectedProjection;
	}

	@Override
	protected Button getSearchButton() {
		Button search = new Button("Search");

		search.addClickListener(new Button.ClickListener() {
                        @Override
			public void buttonClick(Button.ClickEvent event) {
				PVQueryUtils logic = new PVQueryUtils();
				NMProjectionVarianceLogic pvLogic = new NMProjectionVarianceLogic();
				try {
					resultsBean.removeAllItems();
					String workFlowState = String.valueOf(getWorkflowStatus().getValue());
					String fromDate = null;
					String toDate = null;
					String marketTypeValue = String.valueOf(getMarketType().getValue());
					String brandValue = String.valueOf(getBrand().getValue());
					String projecName = String.valueOf(getProjectionName().getValue());
					String projDesc = String.valueOf(projectionDescription.getValue());
					String chValue = String.valueOf(getContractHolder().getValue());
					String ndcNoValue = String.valueOf(getNdcName().getValue());
					String ndcNameValue = String.valueOf(getNdcNo().getValue());
					String contractVal = String.valueOf(getContract().getValue());

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
					if (getCreatedDateFrom().getValue() != null) {
						fromDate = format.format(getCreatedDateFrom().getValue());
					}
					if (getCreatedDateTo().getValue() != null) {
						toDate = format.format(getCreatedDateTo().getValue());
					}

					if (workflowStatus.getValue() != null && !Constant.NULL.equalsIgnoreCase(workFlowState)
							&& !StringUtils.EMPTY.equalsIgnoreCase(workFlowState)) {
						if (workFlowState.equals(Constant.SUBMITTED)) {
							workFlowState = WorkflowConstants.getPendingStatus();
						}
						List<String> projId = new ArrayList<>();
						projId.add(toDate);

						String notSearchProjId = "'" + currentProjId + "'";
						for (int j = 0; j < selectedResultsBean.size(); j++) {
							notSearchProjId += ",'" + selectedResultsBean.getIdByIndex(j).getProjectionId() + "'";
						}

						String resultString = logic.getComparisonSearch(workFlowState, marketTypeValue, brandValue,
								projecName, chValue, ndcNoValue, ndcNameValue, projDesc, contractVal, fromDate, toDate,
								notSearchProjId);

						List result = (List) CommonLogic.executeSelectQuery(resultString, null, null);
						List<ComparisonLookupDTO> searchResults = pvLogic.getCustomizedComparisonList(result);
						if (searchResults.isEmpty()) {
							MessageBox.showPlain(Icon.INFO, Constant.ERROR,
									"No results could be found that match the entered search criteria.", ButtonId.OK);
						} else {
							resultsBean.addAll(searchResults);
							addButton.setEnabled(true);
							addButton.setImmediate(true);
							CommonUIUtils.getMessageNotification("Search Completed");

						}
					} else {
						MessageBox.showPlain(Icon.INFO, Constant.ERROR, "Please select a Workflow Status", ButtonId.OK);
					}

				} catch (Exception e) {
					LOGGER.error(e);
				}

			}
		});

		return search;
	}

        @Override
	protected Button getResetCriteriaButton(final Component... components) {
		Button reset = new Button(BTN_RESET.getConstant());
		reset.addClickListener(new Button.ClickListener() {
                @Override
			public void buttonClick(Button.ClickEvent event) {
				MessageBox.showPlain(Icon.QUESTION, "Confirm Reset",
						"Are you sure you want to reset the page to default values?"

								+ "?",
						new MessageBoxListener() {
							/**
							 * Called when reset button is clicked
							 */
							@SuppressWarnings("PMD")
                        @Override
							public void buttonClicked(final ButtonId buttonId) {
								if (buttonId.name().equals(Constant.YES)) {
									UiUtils.componentResetLogic(workflowStatus, marketType, brand, projectionName,
											contractHolder, ndcNo, ndcName, projectionDescription, contract,
											createdDateFrom, createdDateTo);
								}
							}
						}, ButtonId.YES, ButtonId.NO);

			}
		});
		return reset;
	}

	/**
	 * Add button
	 *
	 * @return
	 */
        @Override
	protected Button getAddButton() {

		addButton.addClickListener(new Button.ClickListener() {
                @Override
			public void buttonClick(Button.ClickEvent event) {
				if (recordSelectedFlag) {
					addItemsButtonClick();
					recordSelectedFlag = false;
				} else {
					MessageBox.showPlain(Icon.INFO, Constant.ERROR, "Please select a projection to add.", ButtonId.OK);
				}

			}
		});
		return addButton;
	}

	/**
	 * Adds the items button click.
	 *
	 * @param event
	 *            the event
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	protected void addItemsButtonClick() {
		final java.util.Set<ComparisonLookupDTO> itemMasterDetailsList = (java.util.Set<ComparisonLookupDTO>) results
				.getValue();
		boolean flag = false;
		List<ComparisonLookupDTO> addedItem = new ArrayList<>();
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
			selectedProjection.setValue(null);
			results.setValue(null);
		} else {
			MessageBox.showPlain(Icon.INFO, Constant.ERROR,
					"Cannot Add more than Five items.  Please select five records or below and try again.",
					ButtonId.OK);
		}
		if (resultsBean.size() > 0) {
			addButton.setEnabled(true);
			addButton.setImmediate(true);
		} else {
			addButton.setEnabled(false);
			addButton.setImmediate(true);
		}

	}

	/**
	 * Adds a submit button
	 *
	 * @return Submit button
	 */
	@Override
	public Button getRemoveBtn() {
		Button btnRemove = new Button(BTN_REMOVE.getConstant());
		btnRemove.addClickListener(new Button.ClickListener() {
                        @Override
			public void buttonClick(Button.ClickEvent event) {
				if (recordSelectedFlag) {
					removeItemsButtonClick();
					recordSelectedFlag = false;
				} else {
					MessageBox.showPlain(Icon.INFO, Constant.ERROR, "Please select a projection to remove. ",
							ButtonId.OK);
				}

			}
		});
		return btnRemove;
	}

	/**
	 * Removes the items button click.
	 *
	 * @param event
	 *            the event
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	protected void removeItemsButtonClick() {

		final Object itemId = selectedProjection.getValue();
		selectedResultsBean.removeItem(itemId);
		results.addItem(itemId);
		final java.util.Set<ComparisonLookupDTO> itemMasterDetailsList = (java.util.Set<ComparisonLookupDTO>) selectedProjection
				.getValue();
		resultsBean.addAll(itemMasterDetailsList);

		for (final Iterator<ComparisonLookupDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
			final ComparisonLookupDTO item = iterator.next();
			selectedResultsBean.removeItem(item);
			selectedList.remove(item);
		}
		if (resultsBean.size() > 0) {
			addButton.setEnabled(true);
			addButton.setImmediate(true);
		} else {
			addButton.setEnabled(false);
			addButton.setImmediate(true);
		}

	}

	/**
	 * Creates a button to reset the results table
	 *
	 * @param results
	 *            The table to be reseted
	 * @return The reset button
	 */
	@Override
	protected Button getResetResultsButton(final ExtFilterTable results) {
		Button reset = new Button(BTN_RESET.getConstant());
		reset.addClickListener(new Button.ClickListener() {
                        @Override
			public void buttonClick(Button.ClickEvent event) {
				MessageBox.showPlain(Icon.QUESTION, "Confirm Reset",
						"Are you sure you want to reset the page to default values?"

								+ "?",
						new MessageBoxListener() {
							/**
							 * Called when reset button is clicked
							 */
							@SuppressWarnings("PMD")
                                @Override
							public void buttonClicked(final ButtonId buttonId) {
								if (buttonId.name().equals(Constant.YES)) {
									UiUtils.componentResetLogic(results);
								}
							}
						}, ButtonId.YES, ButtonId.NO);

			}
		});
		return reset;
	}

	/**
	 * Results item click.
	 *
	 * @param obj
	 *            the id
	 * @throws PortalException
	 *             the portal exception
	 */
	protected void resultsItemClick(final Object obj) {
		if (obj == null) {
			recordSelectedFlag = false;
		} else {
			recordSelectedFlag = true;
		}
	}

	/**
	 * Adds a submit button
	 *
	 * @return Submit button
	 */
        @Override
	public Button getSubmitBtn() {
		Button btnSubmit = new Button(BTN_SUBMIT.getConstant());
		btnSubmit.addClickListener(new Button.ClickListener() {
                @Override
			public void buttonClick(Button.ClickEvent event) {
				if (!selectedProjection.getItemIds().isEmpty()) {
					ComparisonLookupDTO dto = new ComparisonLookupDTO();
					List<ComparisonLookupDTO> selected = new ArrayList<>();
					for (Object item : selectedProjection.getItemIds()) {
						selected.add((ComparisonLookupDTO) item);
						dto.setSelected(selected);
					}
					if (selected.size() > 1) {
						comparisonLookup.setReadOnly(false);
						comparisonLookup.setValue(Constant.MULTIPLE);
						dto.setSelected(selected);
						comparisonLookup.setData(dto);
						comparisonLookup.setReadOnly(true);
					} else {
						comparisonLookup.setReadOnly(false);
						comparisonLookup.setValue(selected.get(0).getProjectionName());
						comparisonLookup.setData(dto);
						comparisonLookup.setReadOnly(true);
					}
					close();
				} else {
					MessageBox.showPlain(Icon.INFO, Constant.ERROR, "No Data is available to submit", ButtonId.OK);
				}
			}
		});
		return btnSubmit;
	}

	@Override
	protected void btnCloseLogic() {
		if (selectedProjection.getItemIds().isEmpty()) {
			comparisonLookup.setReadOnly(false);
			comparisonLookup.setValue(Constant.SELECT_ONE);
			comparisonLookup.setData(null);
			comparisonLookup.setImmediate(true);
			comparisonLookup.setReadOnly(true);
		}
		close();
	}
}
