package com.stpl.app.security.busineessRoleMgmt.ui.form;

import com.stpl.app.security.busineessRoleMgmt.dto.BusinessroleMasterDTO;
import com.stpl.app.security.busineessRoleMgmt.ui.layout.ButtonLayout;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;

public class AddUpdateForm extends CustomComponent {

	private static final long serialVersionUID = -5283229308220334335L;

	final ErrorLabel errorMsg = new ErrorLabel();
	final Label space = new Label("&nbsp;", ContentMode.HTML);

	final TextField businessroleMasterSid = new TextField();
	final TextField businessroleDesc = new TextField();
	final TextField businessroleName = new TextField();
	final TextField hierarchyLevel = new TextField();
	private ErrorfulFieldGroup binder;
	BeanItemContainer<BusinessroleMasterDTO> searchResultbeans;
	Table table;

	public AddUpdateForm(ErrorfulFieldGroup binder,
			BeanItemContainer<BusinessroleMasterDTO> searchResultbeans,
			Table table) {
		this.binder=binder;
		this.searchResultbeans = searchResultbeans;
		this.table = table;
		binder.bindMemberFields(this);
		init();
	}

	private void init() {
		space.setHeight("20");
		addToContent();
		configureFields();
	}

	private void addToContent() {
		final VerticalLayout content = new VerticalLayout();
		content.addComponentAsFirst(space);
		content.addComponent(space);
		content.addComponent(addToGrid());
		content.addComponent(space);
		content.addComponent(new ButtonLayout(binder,searchResultbeans,table));
		setCompositionRoot(content);
	}

	private GridLayout addToGrid() {
		GridLayout grid = new GridLayout(NumericConstants.FOUR, NumericConstants.TWO);
		grid.setWidth(null);
		grid.setSpacing(true);
		grid.addComponent(new Label("Role ID"));
		grid.addComponent(businessroleMasterSid);
		grid.addComponent(new Label("Role Description"));
		grid.addComponent(businessroleDesc);
		grid.addComponent(new Label("Role Name"));
		grid.addComponent(businessroleName);
		grid.addComponent(new Label("Hierarchy"));
		grid.addComponent(hierarchyLevel);
		return grid;
	}

	private void configureFields() {
		
		businessroleMasterSid.setReadOnly(true);
		
		businessroleDesc.addValidator(new RegexpValidator(
				"([0-9|a-z|A-Z|\\_|\\$|\\.|\\*|\\s])*",
				"value can contain only digits,alphabets or _ or . or $"));
		businessroleDesc.setImmediate(true);
		businessroleDesc.setValidationVisible(true);
		
		businessroleName.addValidator(new RegexpValidator(
				"([0-9|a-z|A-Z|\\_|\\$|\\.|\\*|\\s])*",
				"value can contain only digits,alphabets or _ or . or $"));
		businessroleName.setImmediate(true);
		businessroleName.setValidationVisible(true);
		
		hierarchyLevel.addValidator(new RegexpValidator(
				"([0-9])*",
				"value can contain only digits"));
		hierarchyLevel.setImmediate(true);
		hierarchyLevel.setValidationVisible(true);
		
	}

}
