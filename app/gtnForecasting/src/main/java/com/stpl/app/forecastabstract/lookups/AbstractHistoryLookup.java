/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastabstract.lookups;

import static com.stpl.app.utils.Constants.ButtonConstants.BTN_RESET;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SEARCH;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_MODULE_NON_MANDATED;
import static com.stpl.app.utils.Constants.LabelConstants.BRAND;
import static com.stpl.app.utils.Constants.LabelConstants.BRAND_SEARCH;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT_HOLDER_NAME;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT_NAME;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT_NO;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER_ID;
import static com.stpl.app.utils.Constants.LabelConstants.RESULTS;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author soundarrajan
 */
public abstract class AbstractHistoryLookup extends AbstractLookup {

	/**
	 * indicates whether the module is NonMandated or Mandated or Channels
	 */
	private final String moduleIndicator;

	/**
	 * Used to search brandSearch
	 */
	private TextField brandSearch;

	/**
	 * Used to search contract
	 */
	protected TextField contractName;

	/**
	 * Used to search contract
	 */
	private TextField contractNo;

	/**
	 * Used to search contract Holder Name
	 */
	private TextField contractHolder;

	/**
	 * Used to search customer Id
	 */
	private TextField customerId;

	/**
	 * Customer search button
	 */
	private Button btnCustomerSearch;

	/**
	 * Brand Search Button
	 */
	private Button btnBrandSearch;
	/**
	 * Customer Reset button
	 */
	private Button btnCustomerReset;

	/**
	 * Brand Reset Button
	 */
	private Button btnBrandReset;

	private final Label tempLable = new Label();

	private final Label tempLable1 = new Label();

	/**
	 * Constructor for AbstractHistoryLookup
	 *
	 * @param windowName
	 *            Window name of the lookup
	 * @param moduleIndicator
	 *            indicates whether the module is NonMandated or Mandated or
	 *            Channels
	 */
	public AbstractHistoryLookup(final String windowName, final String moduleIndicator) {
		super(windowName);
		setWidth(NumericConstants.EIGHT_FIVE_NINE, Sizeable.Unit.PIXELS);
		setHeight(NumericConstants.EIGHT_EIGHT_ONE, Sizeable.Unit.PIXELS);
		this.moduleIndicator = moduleIndicator;
		addStyleName(Constant.BOOTSTRAP_NM);
	}

	/**
	 * Builds the entire lookup
	 *
	 * @param contractResults
	 *            The results table of contract section
	 * @param brandResults
	 *            The results table of brandSearch section
	 * @param customerNativeSelect
	 *            Customer DDLB
	 * @return
	 */
	public Panel buildHistoryLookup(final ExtFilterTable contractResults, final ExtFilterTable brandResults,
			final NativeSelect customerNativeSelect) {
		initializeComponents();
		Panel mainPanel = UiUtils.addCommonPanel(StringUtils.EMPTY);
		VerticalLayout mainLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		mainLayout.addStyleName(Constant.WIDTH_AUTO);
		HorizontalLayout contractBrandLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		contractBrandLayout.addComponent(addContractSection(contractResults, customerNativeSelect));
		contractBrandLayout.addComponent(addBrandSection(brandResults));
		contractBrandLayout.setMargin(true);
		contractBrandLayout.setSpacing(true);
		mainLayout.addComponent(contractBrandLayout);
		mainLayout.addComponent(addFooterButton());
		mainPanel.setContent(mainLayout);
		return mainPanel;
	}

	/**
	 * Adds the Contract search section
	 *
	 * @param brandResults
	 *            The brandSearch search result table
	 * @return Panel containing the brandSearch section
	 */
	private Panel addContractSection(final ExtFilterTable contractResults, final NativeSelect customerNativeSelect) {
		Panel customerPanel = UiUtils.addCommonPanel(CONTRACT.getConstant());
		VerticalLayout layout = new VerticalLayout();

		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(addContractSearchCriteria(customerNativeSelect));
		layout.addComponent(
				addCustomerSearchButtons(contractName, contractNo, customerNativeSelect, customerId, contractHolder));
		layout.addComponent(addResultsSection(contractResults, CONTRACT.getConstant()));
		contractName.setValue(StringUtils.EMPTY);
		contractName.setImmediate(true);
		contractHolder.setImmediate(true);
		customerId.setImmediate(true);
		customerPanel.setContent(layout);
		return customerPanel;
	}

	/**
	 * Adds the brandSearch search section
	 *
	 * @param brandResults
	 *            The brandSearch search result table
	 * @return Panel containing the brandSearch section
	 */
	private Panel addBrandSection(final ExtFilterTable brandResults) {
		Panel brandPanel = UiUtils.addCommonPanel(BRAND.getConstant());
		VerticalLayout layout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		layout.setMargin(true);
		layout.addComponent(addBrandSearchCriteria());
		HorizontalLayout hloyout = new HorizontalLayout();
		hloyout.addComponent(tempLable);
		tempLable.setHeight("34px");
		tempLable.setHeight("34px");
		hloyout.addComponent(tempLable1);

		layout.addComponent(hloyout);
		layout.addComponent(addBrandSearchButtons(brandSearch));
		layout.addComponent(addResultsSection(brandResults, BRAND.getConstant()));
		brandPanel.setContent(layout);
		return brandPanel;
	}

	/**
	 * Initializes the components
	 */
	private void initializeComponents() {

		setBrandSearch(UiUtils.addTextField());
		setContractName(UiUtils.addTextField());
		setContractNo(UiUtils.addTextField());
		setCustomerId(UiUtils.addTextField());
		setContractHolder(UiUtils.addTextField());
	}

	/**
	 * Adds the search criteria for brandSearch
	 *
	 * @return Layout containing the search criteria layout
	 */
	private VerticalLayout addContractSearchCriteria(final NativeSelect customerNativeSelect) {
		int column = NumericConstants.TWO, row = NumericConstants.THREE;
		String textFieldLable1 = CONTRACT_NO.getConstant();
		String textFieldLable2 = CONTRACT_NAME.getConstant();
		contractNo.setWidth("151px");
		contractName.setWidth("151px");
		contractName.setValue(StringUtils.EMPTY);
		VerticalLayout criteriaLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		criteriaLayout.setMargin(false);
		if (moduleIndicator.equals(INDICATOR_MODULE_NON_MANDATED.getConstant())) {
			column = NumericConstants.TWO;
			row = NumericConstants.TWO;
			textFieldLable1 = CUSTOMER_ID.getConstant();
			textFieldLable2 = CONTRACT_HOLDER_NAME.getConstant();
		}
		GridLayout gridLayout = UiUtils.addCommonGridLayout(column, row);
		if (!moduleIndicator.equals(INDICATOR_MODULE_NON_MANDATED.getConstant())) {
			gridLayout.addComponent(UiUtils.makeLabel(CUSTOMER.getConstant()));
			gridLayout.addComponent(customerNativeSelect);
		}
		Label tempLabel = UiUtils.makeLabel(textFieldLable1);
		tempLabel.setWidth("157px");
		tempLabel.setStyleName(" ");
		gridLayout.addComponent(tempLabel);
		gridLayout.addComponent(
				moduleIndicator.equals(INDICATOR_MODULE_NON_MANDATED.getConstant()) ? customerId : contractNo);
		Label tempLabel1 = UiUtils.makeLabel(textFieldLable2);
		tempLabel1.setWidth("157px");
		gridLayout.addComponent(tempLabel1);
		gridLayout.addComponent(
				moduleIndicator.equals(INDICATOR_MODULE_NON_MANDATED.getConstant()) ? contractHolder : contractName);
		criteriaLayout.addComponent(gridLayout);
		gridLayout.setMargin(false);
		return criteriaLayout;
	}

	/**
	 * Adds the search criteria for brandSearch
	 *
	 * @return Layout containing the search criteria layout
	 */
	private HorizontalLayout addBrandSearchCriteria() {
		HorizontalLayout searchCriteriaLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		searchCriteriaLayout.addComponent(UiUtils.makeLabel(BRAND_SEARCH.getConstant()));
		searchCriteriaLayout.addComponent(brandSearch);
		searchCriteriaLayout.setMargin(false);
		return searchCriteriaLayout;
	}

	/**
	 * Adds the result section for contract section and brandSearch section
	 *
	 * @param results
	 *            The table to be added
	 * @param indicator
	 *            Indicates whether its contract section or brandSearch section
	 * @return Returns the results panel
	 */
	private Panel addResultsSection(final ExtFilterTable results, String indicator) {
		Panel resultsPanel = UiUtils.addCommonPanel(RESULTS.getConstant());
		HorizontalLayout resultLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		resultLayout.addComponent(results);
		configureResultTable(results, indicator);
		resultLayout.setMargin(false);
		resultsPanel.setContent(resultLayout);
		return resultsPanel;
	}

	/**
	 * Configures the result table
	 *
	 * @param results
	 *            The search result table for group lookup
	 * @param indicator
	 *            To indicate contract results or brandSearch results
	 */
	@Override
	protected void configureResultTable(final ExtFilterTable results, String indicator) {

		results.setFilterBarVisible(true);
		if (BRAND.getConstant().equals(indicator)) {
			results.setVisibleColumns(TableHeaderColumnsUtil.getHistoryLookupBrandColumns());
			results.setColumnHeaders(TableHeaderColumnsUtil.getHistoryLookupBrandHeaders());
			results.setWidth("300px");
			results.setHeight(Constant.FOUR_EIGHT_ONE_PX);

		} else if (CONTRACT.getConstant().equals(indicator)) {
			if (INDICATOR_MODULE_NON_MANDATED.getConstant().equals(moduleIndicator)) {
				results.setVisibleColumns(TableHeaderColumnsUtil.getHistoryLookupContractColumnsNm());
				results.setColumnHeaders(TableHeaderColumnsUtil.getHistoryLookupContractHeadersNm());
				results.setWidth("400px");
				results.setHeight(Constant.FOUR_EIGHT_ONE_PX);
			} else {
				results.setVisibleColumns(TableHeaderColumnsUtil.getHistoryLookupContractColumns());
				results.setColumnHeaders(TableHeaderColumnsUtil.getHistoryLookupContractHeaders());
				results.setWidth("400px");
				results.setHeight(Constant.FOUR_EIGHT_ONE_PX);
			}
		}
	}

	/**
	 * Adds Import and Close button to the screen
	 *
	 * @return HorizontalLayout containing Import and close buttons
	 */
	private HorizontalLayout addFooterButton() {
		HorizontalLayout hLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		hLayout.setMargin(false);
		hLayout.addComponent(getImportButton());
		hLayout.addComponent(getCloseButton());
		return hLayout;
	}

	/**
	 * Creates a layout with search and reset buttons for customer search
	 * criteria
	 *
	 * @param components
	 *            varags which contains a collection of components whose values
	 *            are to be reseted
	 * @return
	 */
	private HorizontalLayout addCustomerSearchButtons(final Component... components) {
		setBtnCustomerSearch(new Button(BTN_SEARCH.getConstant()));
		setBtnCustomerReset(new Button(BTN_RESET.getConstant()));

		HorizontalLayout buttonLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		buttonLayout.addComponent(btnCustomerSearch);
		buttonLayout.addComponent(btnCustomerReset);
		buttonLayout.setMargin(true);
		btnCustomerSearch.addClickListener(new Button.ClickListener() {

                        @Override
			public void buttonClick(Button.ClickEvent event) {
				btnCustomerSearchLogic();
			}
		});
		btnCustomerReset.addClickListener(new Button.ClickListener() {

                        @Override
			public void buttonClick(Button.ClickEvent event) {
				new AbstractNotificationUtils() {
                                @Override
					public void noMethod() {
						return;
					}

					@Override
					/**
					 * The method is triggered when Yes button of the message
					 * box is pressed .
					 *
					 * @param buttonId
					 *            The buttonId of the pressed button.
					 */
					public void yesMethod() {
						UiUtils.componentResetLogic(components);
					}
				}.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

			}
		});
		return buttonLayout;
	}

	/**
	 * Creates a layout with search and reset buttons for brandSearch search
	 * criteria
	 *
	 * @param components
	 *            varags which contains a collection of components whose values
	 *            are to be reseted
	 * @return
	 */
	private HorizontalLayout addBrandSearchButtons(final Component... components) {
		setBtnBrandSearch(new Button(BTN_SEARCH.getConstant()));
		setBtnBrandReset(new Button(BTN_RESET.getConstant()));

		HorizontalLayout buttonLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		buttonLayout.addComponent(btnBrandSearch);
		buttonLayout.addComponent(btnBrandReset);
		buttonLayout.setMargin(true);

		btnBrandSearch.addClickListener(new Button.ClickListener() {

                        @Override
			public void buttonClick(Button.ClickEvent event) {
				btnBrandSearchLogic();
			}
		});
		btnBrandReset.addClickListener(new Button.ClickListener() {

                        @Override
			public void buttonClick(Button.ClickEvent event) {

				new AbstractNotificationUtils() {
                                @Override
					public void noMethod() {
						return;
					}

					@Override
					/**
					 * The method is triggered when Yes button of the message
					 * box is pressed .
					 *
					 * @param buttonId
					 *            The buttonId of the pressed button.
					 */
					public void yesMethod() {
						UiUtils.componentResetLogic(components);
					}
				}.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");
			}
		});

		return buttonLayout;
	}

	/**
	 * Override this to customize Customer search logic in the extending classes
	 */
	protected abstract void btnCustomerSearchLogic();

	/**
	 * Override this to customize Brand search logic in the extending classes
	 */
	protected abstract void btnBrandSearchLogic();

	/**
	 * Don't implement this method
	 */
	@Override
	protected void btnLookupSearchLogic() {

	}

	/**
	 * Write search logic for brandSearch here
	 */
	@Override
	protected void btnLookupAnotherSearchLogic() {

	}

	@Override
	protected void btnLookupSelectLogic() {

	}

	@Override
	protected void btnUpdateLogic() {

	}

	@Override
	protected void btnAddLogic() {

	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnSubmitLogic() {

	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnRemoveLogic() {

	}

	/*
	 * Setters to initialize the components
	 */
	public void setBrandSearch(TextField brandSearch) {
		this.brandSearch = brandSearch;
	}

	public void setContractName(TextField contractName) {
		this.contractName = contractName;
	}

	public void setContractNo(TextField contractNo) {
		this.contractNo = contractNo;
	}

	public void setContractHolder(TextField contractHolder) {
		this.contractHolder = contractHolder;
	}

	public void setCustomerId(TextField customerId) {
		this.customerId = customerId;
	}

	public void setBtnCustomerSearch(Button btnCustomerSearch) {
		this.btnCustomerSearch = btnCustomerSearch;
	}

	public void setBtnBrandSearch(Button btnBrandSearch) {
		this.btnBrandSearch = btnBrandSearch;
	}

	public void setBtnCustomerReset(Button btnCustomerReset) {
		this.btnCustomerReset = btnCustomerReset;
	}

	public void setBtnBrandReset(Button btnBrandReset) {
		this.btnBrandReset = btnBrandReset;
	}

	public TextField getContractHolder() {
		return contractHolder;
	}

	public TextField getCustomerId() {
		return customerId;
	}

	public TextField getBrandSearch() {
		return brandSearch;
	}

	public Button getBtnCustomerSearch() {
		return btnCustomerSearch;
	}

	public Button getBtnBrandSearch() {
		return btnBrandSearch;
	}

	public Button getBtnCustomerReset() {
		return btnCustomerReset;
	}

	public Button getBtnBrandReset() {
		return btnBrandReset;
	}

}
