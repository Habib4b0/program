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
	private final ErrorLabel errorMsg = new ErrorLabel();
	private BusinessRoleModuleSearchLogic salesLogic = new BusinessRoleModuleSearchLogic();
	private final Label space = new Label("&nbsp;", ContentMode.HTML);
	private final TextField salesId = new TextField("Sales Id");
	private final TextField organizationKey = new TextField("Organization Key");
	private final TextField itemId = new TextField("Item Id");
	private final TextField itemNo = new TextField("Item No");
	private final TextField itemCodeQualifier = new TextField("Item Code Qualifier");
	private final TextField parentItemId = new TextField("Parent Item Id");
	private final TextField itemParentNo = new TextField("Item Parent No");
	private final TextField itemUom = new TextField("Item Uom");
	private final TextField docType = new TextField("Doc Type");
	private final TextField orderNumber = new TextField("Order Number");
	private final TextField invoiceNumber = new TextField("Invoice Number");
	private final TextField invoiceLineNumber = new TextField("Invoice Line Number");
	private final PopupDateField invoiceDate = new PopupDateField("Invoice Date");
	private final TextField orderType = new TextField("Order Type");
	private final TextField orderSubtype = new TextField("Order Subtype");
	private final TextField analysisCode = new TextField("Analysis Code");
	private final TextField priceAdjustmentName = new TextField("Price Adjustment Name");
	private final TextField recordSequence = new TextField("Record Sequence");
	private final TextField price = new TextField("Price");
	private final TextField quantity = new TextField("Quantity");
	private final TextField lotNo = new TextField("Lot Number");
	private final TextField amount = new TextField("Amount");
	private final TextField contractId = new TextField("Contract ID");
	private final TextField contractNo = new TextField("Contract No");
	private final TextField accountType = new TextField("Account Type");
	private final TextField customerSubtype = new TextField("Customer Subtype");
	private final TextField wholesaleOwnerId = new TextField("Wholesale Owner Id");
	private final TextField accountNo = new TextField("Account No");
	private final TextField accountName = new TextField("Account Name");
	private final TextField identifierCodeQualifier = new TextField(
			"Identifier Code Qualifier");
	private final TextField customerCompanyCode = new TextField("Customer Company Code");
	private final TextField isActive = new TextField("Is Active");
	private final TextField companyId = new TextField("Company Id");
	private final TextField divisionId = new TextField("Division Id");
	private final TextField marketId = new TextField("Market Id");
	private final TextField brandId = new TextField("brandId");

	private BusinessRoleModuleMasterDTO salesMasterDTO;
	private ErrorfulFieldGroup binder;

	public ViewForm(BusinessRoleModuleMasterDTO salesMasterDTO, ErrorfulFieldGroup binder) {
		this.setSalesMasterDTO(salesMasterDTO);
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

	public BusinessRoleModuleSearchLogic getSalesLogic() {
		return salesLogic;
	}

	public void setSalesLogic(BusinessRoleModuleSearchLogic salesLogic) {
		this.salesLogic = salesLogic;
	}

	public BusinessRoleModuleMasterDTO getSalesMasterDTO() {
		return salesMasterDTO;
	}

	public void setSalesMasterDTO(BusinessRoleModuleMasterDTO salesMasterDTO) {
		this.salesMasterDTO = salesMasterDTO;
	}
}
