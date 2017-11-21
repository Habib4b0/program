/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastabstract.lookups;

import com.stpl.app.gtnforecasting.logic.GroupSearchLogic;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.ErrorCodeUtil;
import com.stpl.app.gtnforecasting.utils.ErrorCodes;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_RESET;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER_GROUP_NAME;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER_GROUP_NO;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT_GROUP_NAME;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT_GROUP_NO;
import static com.stpl.app.utils.Constants.LabelConstants.RESULTS;
import static com.stpl.app.utils.Constants.LabelConstants.SEARCH_CRITERIA;
import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 *
 * @author soundarrajan
 */
public abstract class AbstractGroupLookup extends AbstractLookup {

	/**
	 * To indicate customer or product lookup
	 */
	private String indicator;
	/**
	 * To label groupName TextField Differs based on indicator.
	 */
	private String groupNameLabel;

	/**
	 * To label groupNo TextField Differs based on indicator.
	 */
	private String groupNoLabel;

	/**
	 * Table headers for result table
	 */
	private String[] resultTableHeaders;

	/**
	 * Visible columns for result table
	 */
	private Object[] resultTableColumns = null;

	/**
	 * Button for search
	 */
	private Button searchBtn;

	/**
	 * The logger.
	 */
	private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger
			.getLogger(AbstractGroupLookup.class);
	/**
	 * Button for Reset
	 */
	private Button resetBtn;

	private GroupSearchLogic tableLogic;

	public String screenName;

	/**
	 * Constructor for AbstractLookup
	 *
	 * @param windowName
	 *            Window name of the lookup
	 * @param indicator
	 *            Indicator to indicate customer or product lookup
	 * @param moduleIndicator
	 *            To indicate Non Mandated or Mandated or Channels
	 */
	public AbstractGroupLookup(final String indicator, final String windowName, final String screenName) {
		super(windowName);
		this.indicator = indicator;
		this.screenName = screenName;
		customerOrProduct();
	}

	/**
	 * Builds the lookup
	 *
	 * @param groupName
	 *            Group name textfield for search
	 * @param groupNo
	 *            Group no textfield for search
	 * @param results
	 *            results table
	 * @return Layout containing all the components
	 */
	public VerticalLayout buildGroupLookup(final TextField groupName, final TextField groupNo,
			final ExtFilterTable results, final Button searchBtn, final Button resetBtn) {
		try {
			VerticalLayout mainLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
			this.searchBtn = searchBtn;
			this.resetBtn = resetBtn;
			mainLayout.addComponent(addSearchCriteria(groupName, groupNo));
			mainLayout.addComponent(getSearchCriteriaButtons());
			mainLayout.addComponent(addResultSection(results, false));
			mainLayout.addComponent(addFooterButtonsTypeOne(results));
			configureResultTable(results, StringUtils.EMPTY);
			return mainLayout;
		} catch (Exception ex) {
			LOGGER.error(ex);
			return null;
		}
	}

	public VerticalLayout buildGroupLookup(final TextField groupName, final TextField groupNo,
			final ExtPagedTable results, final Button searchBtn, final Button resetBtn,
			final GroupSearchLogic tableLogic) {
		try {
			VerticalLayout mainLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
			this.searchBtn = searchBtn;
			this.resetBtn = resetBtn;
			this.tableLogic = tableLogic;
			mainLayout.addComponent(addSearchCriteria(groupName, groupNo));
			mainLayout.addComponent(getSearchCriteriaButtons());
			mainLayout.addComponent(addResultSection(results, true));
			mainLayout.addComponent(addFooterButtonsTypeOne(results));
			configureResultTable(results, StringUtils.EMPTY);
			return mainLayout;
		} catch (Exception ex) {
			LOGGER.error(ex);
			return null;
		}
	}

	private HorizontalLayout getSearchCriteriaButtons() {
		HorizontalLayout buttonLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		buttonLayout.addComponent(searchBtn);
		buttonLayout.addComponent(resetBtn);
		buttonLayout.setMargin(false);
		searchBtn.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					btnSearchLogic();
				} catch (PortalException | SystemException ex) {
					LOGGER.error(ex);
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1000),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5039));
				}
			}
		});

		resetBtn.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnResetLogic();
			}
		});

		return buttonLayout;
	}

	/**
	 * Initializes screen based on customer or product
	 */
	private void customerOrProduct() {
		try {
			if (screenName.equals("AccrualRateProjection")) {
				if (INDICATOR_CUSTOMER_GROUP.getConstant().equalsIgnoreCase(indicator)) {
					groupNameLabel = CUSTOMER_GROUP_NAME.getConstant();
					groupNoLabel = CUSTOMER_GROUP_NO.getConstant();
					resultTableColumns = TableHeaderColumnsUtil.getInstance().accrualCustomerLookupColumns;
					resultTableHeaders = TableHeaderColumnsUtil.getInstance().accrualCustomerLookupHeaders;

				} else if (INDICATOR_PRODUCT_GROUP.getConstant().equalsIgnoreCase(indicator)) {
					groupNameLabel = PRODUCT_GROUP_NAME.getConstant();
					groupNoLabel = PRODUCT_GROUP_NO.getConstant();
					resultTableColumns = TableHeaderColumnsUtil.getInstance().accrualProductLookupColumns;
					resultTableHeaders = TableHeaderColumnsUtil.getInstance().accrualProductLookUpHeaders;
				}

			} else {
				if (INDICATOR_CUSTOMER_GROUP.getConstant().equalsIgnoreCase(indicator)) {
					groupNameLabel = CUSTOMER_GROUP_NAME.getConstant();
					groupNoLabel = CUSTOMER_GROUP_NO.getConstant();
					resultTableColumns = TableHeaderColumnsUtil.getInstance().customerGroupLookupColumns;
					resultTableHeaders = TableHeaderColumnsUtil.getInstance().customerGroupLookupHeaders;
				} else if (INDICATOR_PRODUCT_GROUP.getConstant().equalsIgnoreCase(indicator)) {
					groupNameLabel = PRODUCT_GROUP_NAME.getConstant();
					groupNoLabel = PRODUCT_GROUP_NO.getConstant();
					resultTableColumns = TableHeaderColumnsUtil.getInstance().productGroupLookupColumns;
					resultTableHeaders = TableHeaderColumnsUtil.getInstance().productGroupLookupHeaders;
				}
			}
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	/**
	 * Adds the search criteria
	 *
	 * @return Panel with the search criteria
	 */
	private Panel addSearchCriteria(final TextField groupName, final TextField groupNo) {
		Panel searchCriteria = UiUtils.addCommonPanel(SEARCH_CRITERIA.getConstant());
		HorizontalLayout hLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		GridLayout criteriaLayout = new GridLayout(NumericConstants.FOUR, NumericConstants.ONE);
		Label groupNamelabel = UiUtils.makeLabel(groupNameLabel);
		Label groupNolabel = UiUtils.makeLabel(groupNoLabel);
		if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
			groupNamelabel.setWidth(NumericConstants.ONE_SEVEN_ZERO, Sizeable.Unit.PIXELS);
			groupNolabel.setWidth(NumericConstants.ONE_SEVEN_ZERO, Sizeable.Unit.PIXELS);
		} else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
			groupNamelabel.setWidth(NumericConstants.ONE_SIX_ZERO, Sizeable.Unit.PIXELS);
			groupNolabel.setWidth(NumericConstants.ONE_SIX_ZERO, Sizeable.Unit.PIXELS);
		}
		criteriaLayout.setMargin(false);
		criteriaLayout.setSpacing(false);
		criteriaLayout.addComponent(groupNamelabel);
		criteriaLayout.addComponent(groupName);
		criteriaLayout.addComponent(groupNolabel);
		criteriaLayout.addComponent(groupNo);
		hLayout.addComponent(criteriaLayout);
		hLayout.setMargin(true);
		hLayout.setSpacing(false);
		searchCriteria.setContent(hLayout);
		return searchCriteria;
	}

	/**
	 * Adds the result table
	 *
	 * @param results
	 *            The search result table
	 * @return Panel containing the result table
	 */
	protected Panel addResultSection(final ExtFilterTable results, final boolean createControls) {
		Panel resultsPanel = UiUtils.addCommonPanel(RESULTS.getConstant());
		VerticalLayout resultLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		resultLayout.setSizeFull();
		resultLayout.addComponent(results);
		List<Integer> pageLength = new ArrayList<>();
		pageLength.add(NumericConstants.TEN);
		pageLength.add(NumericConstants.FIFTEEN);
		pageLength.add(NumericConstants.TWENTY);
		pageLength.add(NumericConstants.TWENTY_FIVE);
		pageLength.add(NumericConstants.FIFTY);
		pageLength.add(NumericConstants.HUNDRED);
		tableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);

		if (createControls) {
			resultLayout.addComponent(tableLogic.createControls());
		}
		resultsPanel.setContent(resultLayout);
		configureResultTable(results, StringUtils.EMPTY);
		return resultsPanel;
	}

	/**
	 * Configures the result table
	 *
	 * @param results
	 *            The search result table for group lookup
	 * @param indicator
	 *            to indicate different sections or module. Pass "" if not
	 *            needed
	 */
	@Override
	protected void configureResultTable(final ExtFilterTable results, final String indicator) {

	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnImportLogic() {

	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnUpdateLogic() {

	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnAddLogic() {

	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnLookupAnotherSearchLogic() {

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

	/**
	 * Override this to customize search logic in the extending classes
	 */
	protected abstract void btnSearchLogic() throws PortalException, SystemException;

	/**
	 * Override this to customize reset logic in the extending classes
	 */
	protected abstract void btnResetLogic();

	@Override
	protected void btnLookupSearchLogic() {

	}

	@Override
	protected void configureResultTable(ExtPagedTable results, String indicator) {
		results.setVisibleColumns(resultTableColumns);
		results.setColumnHeaders(resultTableHeaders);
	}

	@Override
	protected Button getResetResultsButton(final ExtFilterTable results) {
		Button reset = new Button(BTN_RESET.getConstant());
		reset.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				NotificationUtils notificationUtils = new NotificationUtils() {
					@Override
					public void yesMethod() {
						tableLogic.fireSetData(null, true, true);
					}

					@Override
					public void noMethod() {
						return;
					}
				};
				notificationUtils.getConfirmationMessage("Confirm Reset",
						"Are you sure you want to reset the page to default values?");

			}
		});
		return reset;
	}
}
