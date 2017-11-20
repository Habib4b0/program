/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.abstractCff;

import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import static com.stpl.app.cff.util.Constants.LabelConstants.CUSTOMER_GROUP_NAME;
import static com.stpl.app.cff.util.Constants.LabelConstants.CUSTOMER_GROUP_NO;
import static com.stpl.app.cff.util.Constants.LabelConstants.PRODUCT_GROUP_NAME;
import static com.stpl.app.cff.util.Constants.LabelConstants.PRODUCT_GROUP_NO;
import static com.stpl.app.cff.util.Constants.LabelConstants.RESULTS;
import static com.stpl.app.cff.util.Constants.LabelConstants.SEARCH_CRITERIA;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.app.cff.lazyLoad.GroupSearchLogic;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.ErrorCodes;
import com.stpl.app.cff.util.TableHeaderColumnsUtil;
import com.stpl.app.cff.util.UiUtils;
import com.stpl.app.serviceUtils.ErrorCodeUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author mohamed.hameed
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
	public AbstractGroupLookup(final String indicator, final String windowName) {
		super(windowName);
		this.indicator = indicator;
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
			mainLayout.addComponent(addFooterButtonsTypeOne());
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
			mainLayout.addComponent(addFooterButtonsTypeOne());
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
				} catch (PortalException ex) {
					LOGGER.error(ex);
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1000),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5039));
				} catch (SystemException ex) {
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
		if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
			groupNameLabel = CUSTOMER_GROUP_NAME.getConstant();
			groupNoLabel = CUSTOMER_GROUP_NO.getConstant();
			resultTableColumns = TableHeaderColumnsUtil.getInstance().customerGroupLookupColumns;
			resultTableHeaders = TableHeaderColumnsUtil.getInstance().customerGroupLookupHeaders;
		} else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
			groupNameLabel = PRODUCT_GROUP_NAME.getConstant();
			groupNoLabel = PRODUCT_GROUP_NO.getConstant();
			resultTableColumns = TableHeaderColumnsUtil.getInstance().productGroupLookupColumns;
			resultTableHeaders = TableHeaderColumnsUtil.getInstance().productGroupLookupHeaders;
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
		if (createControls && tableLogic != null) {
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
		// do nothing
	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnImportLogic() {
		// Import button is not in this screen. Leave this method blank.
	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnUpdateLogic() {
		// Update button is not in this screen. Leave this method blank.
	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnAddLogic() {
		// Add button is not in this screen. Leave this method blank.
	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnLookupAnotherSearchLogic() {
		// No need to implement this method
	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnSubmitLogic() {
		// No need to implement this method
	}

	/**
	 * No need to implement this method.
	 */
	@Override
	protected void btnRemoveLogic() {
		// No need to implement this method
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
		// Do nothing
	}

	@Override
	protected void configureResultTable(ExtPagedTable results, String indicator) {
		results.setVisibleColumns(resultTableColumns);
		results.setColumnHeaders(resultTableHeaders);
		for (Object result : results.getVisibleColumns()) {
			results.setColumnWidth(result, 300);
		}

	}

}
