/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.abstractCff;

import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import static com.stpl.app.cff.util.Constants.LabelConstants.PRIVATE;
import static com.stpl.app.cff.util.Constants.LabelConstants.PUBLIC;
import static com.stpl.app.cff.util.Constants.LabelConstants.VIEW_NAME;
import com.stpl.app.cff.util.UiUtils;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author mohamed.hameed
 */
public abstract class AbstractSaveViewPopup extends AbstractLookup {
	private TextField viewName;

	private OptionGroup viewType;

	private Button viewAddButton;

	private Button viewUpdateButton;

	/**
	 * Save view popup constructor
	 *
	 * @param windowName
	 *            Window name for the popup
	 */
	public AbstractSaveViewPopup(String windowName) {
		super(windowName);
	}

	public VerticalLayout buildPopupScreen(TextField viewName, OptionGroup viewType) {
		this.viewName = viewName;
		this.viewType = viewType;
		initializeComponents();
		return build();
	}

	private VerticalLayout build() {
		VerticalLayout verticalLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		HorizontalLayout viewLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		HorizontalLayout buttonLayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		verticalLayout.addComponent(viewType);
		viewLayout.addComponent(UiUtils.makeLabel(VIEW_NAME.getConstant()));
		viewLayout.addComponent(viewName);
		buttonLayout.addComponent(getCancelButton());
		buttonLayout.addComponent(viewAddButton);
		buttonLayout.addComponent(viewUpdateButton);
		viewLayout.setMargin(false);
		buttonLayout.setMargin(false);
		verticalLayout.addComponent(viewLayout);
		verticalLayout.addComponent(buttonLayout);
		return verticalLayout;
	}

	/**
	 * Initializes and configures the components
	 */
	private void initializeComponents() {
		initializePrivatePublic();
		setViewAddButton(getAddButton());
		setViewUpdateButton(getUpdateButton());
	}

	/**
	 * Initializes and configures privatePublic option group
	 */
	private void initializePrivatePublic() {
		viewType.addItem(PRIVATE.getConstant());
		viewType.addItem(PUBLIC.getConstant());
		viewType.select(PRIVATE.getConstant());
		viewType.setStyleName(ConstantsUtils.HORIZONTAL);
	}

	/**
	 * This method need not be implemented as no search needs to be performed
	 */
	@Override
	protected void btnLookupSearchLogic() {
		// DO NOTHING. THIS METHOD NEED NOT BE IMPLEMENTED
	}

	/**
	 * This method need not be implemented as no import needs to be performed
	 */
	@Override
	protected void btnImportLogic() {
		// DO NOTHING. THIS METHOD NEED NOT BE IMPLEMENTED
	}

	/**
	 * This method need not be implemented as no select needs to be performed
	 */
	@Override
	protected void btnLookupSelectLogic() {
		// DO NOTHING. THIS METHOD NEED NOT BE IMPLEMENTED
	}

	/**
	 * This method need not be implemented as no result table needs to be added
	 */
	@Override
	protected void configureResultTable(final ExtFilterTable results, final String indicator) {
		// DO NOTHING. THIS METHOD NEED NOT BE IMPLEMENTED
	}

	/**
	 * This method need not be implemented as no result table needs to be added
	 */
	@Override
	protected void btnLookupAnotherSearchLogic() {
		// DO NOTHING. THIS METHOD NEED NOT BE IMPLEMENTED
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

	public Button getViewAddButton() {
		return viewAddButton;
	}

	public void setViewAddButton(Button viewAddButton) {
		this.viewAddButton = viewAddButton;
	}

	public Button getViewUpdateButton() {
		return viewUpdateButton;
	}

	public void setViewUpdateButton(Button viewUpdateButton) {
		this.viewUpdateButton = viewUpdateButton;
	}

}
