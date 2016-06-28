package com.stpl.app.security.busineessRoleMgmt.ui.form;
//
//import com.stpl.app.ui.errorhandling.ErrorLabel;
//import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
//import com.vaadin.navigator.View;
//import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
//import com.vaadin.shared.ui.label.ContentMode;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.Button.ClickEvent;
//import com.vaadin.ui.Button.ClickListener;
//import com.vaadin.ui.CustomComponent;
//import com.vaadin.ui.FormLayout;
//import com.vaadin.ui.GridLayout;
//import com.vaadin.ui.Label;
//import com.vaadin.ui.PopupDateField;
//import com.vaadin.ui.TabSheet;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.VerticalLayout;
//
//public class ViewForm extends CustomComponent implements View {
//	private static final long serialVersionUID = 8893447767363695369L;
//	public static final String NAME = "view";
//	final ErrorLabel errorMsg = new ErrorLabel();
//	ActualsSearchLogic actualsSearchLogic = new ActualsSearchLogic();
//	final Label space = new Label("&nbsp;", ContentMode.HTML);
//	// final GridLayout grid = new GridLayout(5, 2);
//
//	// TABBAR 1
//	final TextField actualsMasterId = new TextField("Actuals Master Id");
//	final TextField accountId = new TextField("Account Id");
//	final TextField itemId = new TextField("Item Id");
//	final TextField itemNo = new TextField("Item No");
//	final TextField itemIdentifierCodeQualifier = new TextField(
//			"Item Identifier Code Qualifier");
//	final TextField programStateCode = new TextField("Program State Code");
//	final TextField settlementNo = new TextField("Settlement No");
//	final PopupDateField accrualActualEndDate = new PopupDateField("Accrual Actual End Date");
//	final TextField actualId = new TextField("Actual Id");
//	final TextField divisionId = new TextField("Division Id");
//	final TextField organizationKey = new TextField("Organization Key");
//	final TextField quantityInclusion = new TextField("Quantity Inclusion");
//	final PopupDateField cashPaidDate = new PopupDateField("Cash Paid Date");
//	final TextField source = new TextField("Source");
//	final TextField analysisCode = new TextField("Analysis Code");
//	final PopupDateField accrualActualStartDate = new PopupDateField(
//			"Accrual Actual Start Date");
//	final TextField salesAmount = new TextField("Sales Amount");
//	final TextField quantity = new TextField("Quantity");
//	final TextField sentOut = new TextField("Sent Out");
//	final TextField accountNo = new TextField("Account No");
//	final TextField accountName = new TextField("Account Name");
//	final TextField actualsInterfaceId = new TextField("Actuals Interface Id");
//	final TextField provisionId = new TextField("Provision Id");
//	final TextField amount = new TextField("Amount");
//
//	// TABBAR 2
//	final TextField marketId = new TextField("Market Id");
//	final TextField isActive = new TextField("Is Active");
//	final TextField settlementMethodNo = new TextField("Settlement Method No");
//	final TextField recordLockStatus = new TextField("Record Lock Status");
//	final TextField priceAdjustmentName = new TextField("Price Adjustment Name");
//	final TextField recordSequence = new TextField("Record Sequence");
//	final TextField mandatedDiscountAmount = new TextField("Mandated Discount Amount");
//	final TextField parentcomDivmktBrandProdkey = new TextField("Parentcom Divmkt Brand Prodkey");
//	final TextField price = new TextField("Price");
//	final PopupDateField dispensedDate = new PopupDateField("Dispensed Date");
//	final TextField accrualProcessed = new TextField("Accrual Processed");
//	final PopupDateField uploadDate = new PopupDateField("Upload Date");
//	final TextField inboundStatus = new TextField("Inbound Status");
//	final TextField invoiceLineNo = new TextField("Invoice Line No");
//	final TextField contractId = new TextField("Contract Id");
//	final TextField acctIdentifierCodeQualifier = new TextField("Acct Identifier Code Qualifier");
//	final TextField brandId = new TextField("Brand Id");
//	final TextField comDivMktBrandProdKey = new TextField("Com DivMkt Brand ProdKey");
//	final TextField claimIndicator = new TextField("Claim Indicator");
//	final TextField provisionClaimIndicator = new TextField("Provision Claim Indicator");
//	final TextField createdBy = new TextField("Created By");
//	final PopupDateField createdDate = new PopupDateField("Created Date");
//	final TextField modifiedBy = new TextField("Modified By");
//	final PopupDateField modifiedDate = new PopupDateField("Modified Date");
//
//	ActualsMasterDTO actualsMasterDTO;
//	ErrorfulFieldGroup binder;
//
//	public ViewForm(ActualsMasterDTO actualsMasterDTO, ErrorfulFieldGroup binder) {
//		this.actualsMasterDTO = actualsMasterDTO;
//		this.binder = binder;
//		init();
//
//	}
//
//	private void init() {
//		space.setHeight("20");
//		binder.bindMemberFields(this);
//		binder.setReadOnly(true);
//		addToContent();
//	}
//
//	private void addToContent() {
//		final FormLayout content = new FormLayout();
//		content.addComponentAsFirst(space);
//		content.addComponent(errorMsg);
//		content.addComponent(space);
//		content.addComponent(addToTabbar());
//		content.addComponent(space);
//		content.addComponent(getBackButton());
//		content.addComponent(space);
//		setCompositionRoot(content);
//	}
//
//	public TabSheet addToTabbar() {
//		TabSheet tabsheet = new TabSheet();
//		tabsheet.setReadOnly(true);
//
//		VerticalLayout tab1 = new VerticalLayout();
//		tab1.setSizeFull();
//		tab1.setMargin(true);
//		tab1.addComponent(getFirstTab());
//		tabsheet.addTab(tab1, "Actuals Information");
//
//		VerticalLayout tab2 = new VerticalLayout();
//		tab2.setMargin(true);
//		tab2.setSizeFull();
//		tab2.addComponent(getSecondTab());
//		tabsheet.addTab(tab2, "Additional Information");
//
//		return tabsheet;
//	}
//
//	private GridLayout getFirstTab() {
//
//		GridLayout grid = new GridLayout(3, 9);
//		grid.setWidth(null);
//		grid.setSpacing(true);
//		grid.addComponent(actualsMasterId);
//		grid.addComponent(accountId);
//		grid.addComponent(itemId);
//		grid.addComponent(itemNo);
//		grid.addComponent(itemIdentifierCodeQualifier);
//		grid.addComponent(programStateCode);
//		grid.addComponent(settlementNo);
//		grid.addComponent(accrualActualEndDate);
//		grid.addComponent(actualId);
//		grid.addComponent(divisionId);
//		grid.addComponent(organizationKey);
//		grid.addComponent(quantityInclusion);
//		grid.addComponent(cashPaidDate);
//		grid.addComponent(source);
//		grid.addComponent(analysisCode);
//		grid.addComponent(accrualActualStartDate);
//		grid.addComponent(salesAmount);
//		grid.addComponent(quantity);
//		grid.addComponent(sentOut);
//		grid.addComponent(accountNo);
//		grid.addComponent(accountName);
//		grid.addComponent(actualsInterfaceId);
//		grid.addComponent(provisionId);
//		grid.addComponent(amount);
//
//		return grid;
//	}
//
//	private GridLayout getSecondTab() {
//
//		GridLayout grid = new GridLayout(3, 9);
//		grid.setWidth(null);
//		grid.setSpacing(true);
//		grid.addComponent(marketId);
//		grid.addComponent(isActive);
//		grid.addComponent(settlementMethodNo);
//		grid.addComponent(recordLockStatus);
//		grid.addComponent(priceAdjustmentName);
//		grid.addComponent(recordSequence);
//		grid.addComponent(mandatedDiscountAmount);
//		grid.addComponent(parentcomDivmktBrandProdkey);
//		grid.addComponent(price);
//		grid.addComponent(dispensedDate);
//		grid.addComponent(accrualProcessed);
//		grid.addComponent(uploadDate);
//		grid.addComponent(inboundStatus);
//		grid.addComponent(invoiceLineNo);
//		grid.addComponent(contractId);
//		grid.addComponent(acctIdentifierCodeQualifier);
//		grid.addComponent(brandId);
//		grid.addComponent(comDivMktBrandProdKey);
//		grid.addComponent(claimIndicator);
//		grid.addComponent(provisionClaimIndicator);
//		grid.addComponent(createdBy);
//		grid.addComponent(createdDate);
//		grid.addComponent(modifiedBy);
//		grid.addComponent(modifiedDate);
//		return grid;
//
//	}
//	
//	public Button getBackButton(){
//		Button backButton = new Button("Back");
//		backButton.setWidth("75");
//		backButton.addClickListener(new ClickListener() {
//			private static final long serialVersionUID = 1L;
//
//			public void buttonClick(ClickEvent event) {
//				getUI().getNavigator().navigateTo(ActualsSearchView.NAME);
//			}
//		});
//		return backButton;
//	}
//
//	public void enter(ViewChangeEvent event) {
//	}
//}
public class ViewForm{
}