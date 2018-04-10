/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastabstract.lookups;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.ErrorCodeUtil;
import com.stpl.app.gtnforecasting.utils.ErrorCodes;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_ADD;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_CANCEL;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_CLOSE;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_IMPORT;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_REMOVE;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_RESET;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SEARCH;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SELECT;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SUBMIT;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_UPDATE;
import com.stpl.app.utils.UiUtils;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author soundarrajan
 */
public abstract class AbstractLookup extends Window {

	/**
	 * Button for search
	 */
	private Button searchBtn;

	/**
	 * Button for Reset
	 */
	private Button resetBtn;

	/**
	 * Button for Reset
	 */
	private Button cancelBtn;

	/**
	 * Button for Reset
	 */
	private Button resetResultBtn;
	/**
	 * Button for select
	 */
	private Button selectBtn;

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractLookup.class);

	/**
	 * Constructor for AbstractLookup
	 *
	 * @param windowName
	 *            Window name of the lookup
	 */
	public AbstractLookup(final String windowName) {
		super(windowName);
		setClosable(true);
		setModal(true);
		center();
		initializeComponents();

		addStyleName(Constant.BOOTSTRAP_UI);
		addStyleName(Constant.BOOTSTRAP);
		addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
	}

	/**
	 * Initializes the components
	 */
	private void initializeComponents() {

	}

	/**
	 * Creates a layout with search and reset buttons for search criteria
	 *
	 * @param components
	 *            varags which contains a collection of components whose values
	 *            are to be reseted
	 * @return
	 */
	protected HorizontalLayout addSearchCriteriaButtons(final Component... components) {
		setSearchBtn(getSearchButton());
		setResetBtn(getResetCriteriaButton(components));
		HorizontalLayout abstractLookupButtonLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		abstractLookupButtonLayout.addComponent(searchBtn);
		abstractLookupButtonLayout.addComponent(resetBtn);
		abstractLookupButtonLayout.setMargin(false);
		return abstractLookupButtonLayout;
	}

	/**
	 * Adds the select, reset and cancel buttons Type 1 - Returns Select, Reset
	 * and Cancel buttons
	 *
	 * @param results
	 *            the results table
	 * @return Panel containing the select, reset and cancel buttons
	 */
	public HorizontalLayout addFooterButtonsTypeOne(final ExtFilterTable results) {
		setSelectBtn(getSelectButton());
		setResetResultBtn(getResetResultsButton(results));
		setCancelBtn(getCancelButton());
		HorizontalLayout abstractLookupFooterButtons = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		abstractLookupFooterButtons.addComponent(selectBtn);
		abstractLookupFooterButtons.addComponent(cancelBtn);
		abstractLookupFooterButtons.addComponent(resetResultBtn);
		abstractLookupFooterButtons.setMargin(false);
		return abstractLookupFooterButtons;
	}

	/**
	 * Adds the select, reset and cancel buttons Type 1 - Returns Select, Reset
	 * and Cancel buttons
	 *
	 * @param results
	 *            the results table
	 * @return Panel containing the select, reset and cancel buttons
	 */
	public HorizontalLayout addFooterButtonsTypeOne(final ExtFilterTable results, Button selectBtn) {
		setResetResultBtn(getResetResultsButton(results));
		setCancelBtn(getCancelButton());
		HorizontalLayout footerButtons = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		footerButtons.addComponent(selectBtn);
		footerButtons.addComponent(cancelBtn);
		footerButtons.addComponent(resetResultBtn);
		footerButtons.setMargin(false);
		selectBtn.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					btnLookupSelectLogic();
				} catch (PortalException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1000),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5025));
				} catch (SystemException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5025));
				}
			}
		});
		return footerButtons;
	}

	/**
	 * Creates a new Search button
	 *
	 * added to the screen
	 *
	 * @return Search button with its listener
	 */
	protected Button getSearchButton() {
		Button searchButton = new Button(BTN_SEARCH.getConstant());
		searchButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnLookupSearchLogic();
			}
		});
		return searchButton;
	}

	/**
	 * Creates a new Select button
	 *
	 * @return Select button with its listener
	 */
	protected Button getSelectButton() {
		Button selectButton = new Button(BTN_SELECT.getConstant());
		selectButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					btnLookupSelectLogic();
				} catch (PortalException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1000),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5040));
				} catch (SystemException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5040));
				}
			}
		});
		return selectButton;
	}

	/**
	 * Creates a new Reset button which resets the search criteria
	 *
	 * @param components
	 *            varags which contains a collection of components whose values
	 *            are to be reseted
	 * @return A Reset button and it's listener implementation
	 */
	protected Button getResetCriteriaButton(final Component... components) {
		Button reset = new Button(BTN_RESET.getConstant());
		reset.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				UiUtils.componentResetLogic(components);
			}
		});
		return reset;
	}

	/**
	 * Creates a button to reset the results table
	 *
	 * @param results
	 *            The table to be reseted
	 * @return The reset button
	 */
	protected Button getResetResultsButton(final ExtFilterTable results) {
		Button resultReset = new Button(BTN_RESET.getConstant());
		resultReset.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				NotificationUtils notificationUtils = new NotificationUtils() {
					@Override
					public void yesMethod() {
						UiUtils.componentResetLogic(results);
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
		return resultReset;
	}

	/**
	 * Creates a cancel button which cancels and closes the window
	 *
	 * @return The cancel button
	 */
	protected Button getCancelButton() {
		Button cancelBtn = new Button(BTN_CANCEL.getConstant());
		cancelBtn.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				close();
			}
		});
		return cancelBtn;
	}

	/**
	 * Creates a close button which closes the window
	 *
	 * @return The close button
	 */
	protected Button getCloseButton() {
		Button closeBtn = new Button(BTN_CLOSE.getConstant());
		closeBtn.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnCloseLogic();

			}

		});
		return closeBtn;
	}

	/**
	 * Creates a import button which imports data from window
	 *
	 * @return The import button
	 */
	protected Button getUpdateButton() {
		Button updateButton = new Button(BTN_UPDATE.getConstant());
		updateButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					btnUpdateLogic();
				} catch (SystemException | FieldGroup.CommitException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5019));
				} catch (PortalException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1000),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5019));
				}
			}
		});
		return updateButton;
	}

	/**
	 * Creates a import button which imports data from window
	 *
	 * @return The import button
	 */
	protected Button getAddButton() {
		Button addButton = new Button(BTN_ADD.getConstant());
		addButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					btnAddLogic();
				} catch (SystemException | FieldGroup.CommitException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5018));
				} catch (PortalException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1000),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5018));
				}
			}
		});
		return addButton;
	}

	/**
	 * Creates a import button which imports data from window
	 *
	 * @return The import button
	 */
	protected Button getImportButton() {
		Button lookupImportButton = new Button(BTN_IMPORT.getConstant());
		lookupImportButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					btnImportLogic();
				} catch (SystemException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5023));
				} catch (SQLException | PortalException | NamingException ex) {
					LOGGER.error(ErrorCodeUtil.getErrorMessage(ex));
					AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1000),
							ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_5023));
				}
			}
		});
		return lookupImportButton;
	}

	/**
	 * Adds a submit button
	 *
	 * @return Submit button
	 */
	public Button getSubmitBtn() {
		Button submitBtn = new Button(BTN_SUBMIT.getConstant());
		submitBtn.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnSubmitLogic();
			}
		});
		return submitBtn;
	}

	/**
	 * Override this to customize submit logic in the extending classes
	 */
	protected abstract void btnSubmitLogic();

	/**
	 * Adds a submit button
	 *
	 * @return Submit button
	 */
	public Button getRemoveBtn() {
		Button removeBtn = new Button(BTN_REMOVE.getConstant());
		removeBtn.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				btnRemoveLogic();
			}
		});
		return removeBtn;
	}

	/**
	 * Override this to customize Remove logic in the extending classes
	 */
	protected abstract void btnRemoveLogic();

	/**
	 * Override this to customize search logic in the extending classes
	 */
	protected abstract void btnLookupSearchLogic();

	/**
	 * Override this to customize second search logic in the extending classes
	 */
	protected abstract void btnLookupAnotherSearchLogic();

	/**
	 * Override this to customize Update logic in the extending classes
	 */
	protected abstract void btnUpdateLogic() throws SystemException, PortalException, FieldGroup.CommitException;

	/**
	 * Override this to customize Add logic in the extending classes
	 */
	protected abstract void btnAddLogic() throws SystemException, PortalException, FieldGroup.CommitException;

	/**
	 * Override this to customize import logic in the extending classes
	 */
	protected abstract void btnImportLogic() throws SystemException, SQLException, NamingException, PortalException;

	/**
	 * Override this to customize select logic in the extending classes
	 */
	protected abstract void btnLookupSelectLogic() throws PortalException, SystemException;

	/**
	 * Configures the result table
	 *
	 * @param results
	 *            The search result table for group lookup
	 */
	protected abstract void configureResultTable(final ExtFilterTable results, final String indicator);

	protected abstract void configureResultTable(final ExtPagedTable results, final String indicator);

	/**
	 * To reset the search criteria. Override this method to implement custom
	 * logic
	 *
	 * @param grpName
	 * @param grpNo
	 */
	public void btnLookupResetLogic(final TextField grpName, final TextField grpNo) {
		if (grpName != null) {
			grpName.setValue(StringUtils.EMPTY);
		}
		if (grpNo != null) {
			grpNo.setValue(StringUtils.EMPTY);
		}
	}

	/*
	 * Getters to get the button components. Use this if you want to get the
	 * buttons to the extending classes.
	 */
	public Button getSearchBtn() {
		return searchBtn;
	}

	public Button getResetBtn() {
		return resetBtn;
	}

	public Button getCancelBtn() {
		return cancelBtn;
	}

	public Button getResetResultBtn() {
		return resetResultBtn;
	}

	public Button getSelectBtn() {
		return selectBtn;
	}

	public void setSearchBtn(Button searchBtn) {
		this.searchBtn = searchBtn;
	}

	public void setResetBtn(Button resetBtn) {
		this.resetBtn = resetBtn;
	}

	public void setCancelBtn(Button cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public void setResetResultBtn(Button resetResultBtn) {
		this.resetResultBtn = resetResultBtn;
	}

	public void setSelectBtn(Button selectBtn) {
		this.selectBtn = selectBtn;
	}

	protected abstract void btnCloseLogic();
}
