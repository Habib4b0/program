package com.stpl.app.security.businessRoleModuleMaster.ui.form;

import com.stpl.app.security.businessRoleModuleMaster.dto.BusinessRoleModuleMasterDTO;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;

public class ViewForm extends CustomComponent implements View {
	private static final long serialVersionUID = 8893447767363695369L;
	public static final String NAME = "view";
	final ErrorLabel errorMsg = new ErrorLabel();
	BusinessRoleModuleSearchLogic salesLogic = new BusinessRoleModuleSearchLogic();
	final Label space = new Label("&nbsp;", ContentMode.HTML);
	final TextField salesId = new TextField("Sales Id");
	final TextField organizationKey = new TextField("Organization Key");
	final TextField itemId = new TextField("Item Id");
	final TextField itemNo = new TextField("Item No");
	final TextField itemCodeQualifier = new TextField("Item Code Qualifier");
	final TextField parentItemId = new TextField("Parent Item Id");
	final TextField itemParentNo = new TextField("Item Parent No");
	final TextField itemUom = new TextField("Item Uom");
	final TextField docType = new TextField("Doc Type");
	final TextField orderNumber = new TextField("Order Number");
	final TextField invoiceNumber = new TextField("Invoice Number");
	final TextField invoiceLineNumber = new TextField("Invoice Line Number");
	final PopupDateField invoiceDate = new PopupDateField("Invoice Date");
	final TextField orderType = new TextField("Order Type");
	final TextField orderSubtype = new TextField("Order Subtype");
	final TextField analysisCode = new TextField("Analysis Code");
	final TextField priceAdjustmentName = new TextField("Price Adjustment Name");
	final TextField recordSequence = new TextField("Record Sequence");
	final TextField price = new TextField("Price");
	final TextField quantity = new TextField("Quantity");
	final TextField lotNo = new TextField("Lot Number");
	final TextField amount = new TextField("Amount");
	final TextField contractId = new TextField("Contract ID");
	final TextField contractNo = new TextField("Contract No");
	final TextField accountType = new TextField("Account Type");
	final TextField customerSubtype = new TextField("Customer Subtype");
	final TextField wholesaleOwnerId = new TextField("Wholesale Owner Id");
	final TextField accountNo = new TextField("Account No");
	final TextField accountName = new TextField("Account Name");
	final TextField identifierCodeQualifier = new TextField(
			"Identifier Code Qualifier");
	final TextField customerCompanyCode = new TextField("Customer Company Code");
	final TextField isActive = new TextField("Is Active");
	final TextField companyId = new TextField("Company Id");
	final TextField divisionId = new TextField("Division Id");
	final TextField marketId = new TextField("Market Id");
	final TextField brandId = new TextField("brandId");

	BusinessRoleModuleMasterDTO salesMasterDTO;
	ErrorfulFieldGroup binder;

	public ViewForm(BusinessRoleModuleMasterDTO salesMasterDTO, ErrorfulFieldGroup binder) {
		this.salesMasterDTO = salesMasterDTO;
		this.binder = binder;
		init();

	}

	private void init() {
		space.setHeight("20");
		binder.bindMemberFields(this);
		binder.setReadOnly(true);
		addToContent();
	}

	private void addToContent() {
		final FormLayout content = new FormLayout();
		content.addComponentAsFirst(space);
		content.addComponent(errorMsg);
		content.addComponent(space);
		content.addComponent(addToGrid());
		content.addComponent(space);
		setCompositionRoot(content);
	}

	private GridLayout addToGrid() {
		GridLayout grid = new GridLayout(NumericConstants.THREE, NumericConstants.TWELVE);
		grid.setWidth(null);
		grid.setSpacing(true);
		grid.addComponent(salesId);
		grid.addComponent(organizationKey);
		grid.addComponent(itemId);
		grid.addComponent(itemNo);
		grid.addComponent(itemCodeQualifier);
		grid.addComponent(parentItemId);
		grid.addComponent(itemParentNo);
		grid.addComponent(itemUom);
		grid.addComponent(docType);
		grid.addComponent(orderNumber);
		grid.addComponent(invoiceNumber);
		grid.addComponent(invoiceLineNumber);
		grid.addComponent(invoiceDate);
		grid.addComponent(orderType);
		grid.addComponent(orderSubtype);
		grid.addComponent(analysisCode);
		grid.addComponent(priceAdjustmentName);
		grid.addComponent(recordSequence);
		grid.addComponent(price);
		grid.addComponent(quantity);
		grid.addComponent(lotNo);
		grid.addComponent(amount);
		grid.addComponent(contractId);
		grid.addComponent(contractNo);
		grid.addComponent(accountType);
		grid.addComponent(customerSubtype);
		grid.addComponent(wholesaleOwnerId);
		grid.addComponent(accountNo);
		grid.addComponent(accountName);
		grid.addComponent(identifierCodeQualifier);
		grid.addComponent(customerCompanyCode);
		grid.addComponent(isActive);
		grid.addComponent(companyId);
		grid.addComponent(divisionId);
		grid.addComponent(marketId);
		grid.addComponent(brandId);
		return grid;
	}

	public void enter(ViewChangeEvent event) {
            return;
	}
}
