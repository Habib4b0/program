package com.stpl.app.security.busineessRoleMgmt.ui.form;

import com.stpl.app.security.busineessRoleMgmt.dto.BusinessroleMasterDTO;
import com.stpl.app.security.busineessRoleMgmt.logic.BusinessRoleMgmtLogic;
import com.stpl.app.security.busineessRoleMgmt.ui.layout.DeleteButtonLayout;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchResults extends CustomComponent {

	private static final long serialVersionUID = 1L;
        
        private static final Logger LOGGER = LoggerFactory.getLogger(SearchResults.class);

        private final ErrorLabel errorMsg = new ErrorLabel();
        private final Label space = new Label("&nbsp;", ContentMode.HTML);
	
	private final ErrorfulFieldGroup binder;
	private final BeanItemContainer<BusinessroleMasterDTO> searchResultbeans;
	private BusinessRoleMgmtLogic businessRoleMgmtLogic = new BusinessRoleMgmtLogic();
	private final Table table;

	public SearchResults(ErrorfulFieldGroup binder,
			BeanItemContainer<BusinessroleMasterDTO> searchResultbeans,
			Table table) {
		this.binder = binder;
		this.searchResultbeans = searchResultbeans;
		this.table = table;
		binder.bindMemberFields(this);
		init();
	}

	private void init() {
		space.setHeight("20");
		addToContent();
	}

	private void addToContent() {
		final VerticalLayout content = new VerticalLayout();
		content.addComponentAsFirst(space);
		content.addComponent(space);
		content.addComponent(addToTable());
		content.addComponent(space);
		content.addComponent(new DeleteButtonLayout(binder,searchResultbeans,table));
		content.addComponent(space);
		content.addComponent(new AddUpdateForm(binder,searchResultbeans,table));
		setCompositionRoot(content);
	}

	private Table addToTable() {
		table.markAsDirty();
		table.setContainerDataSource(searchResultbeans);
		table.setVisibleColumns("businessroleName", "businessroleDesc",
				"hierarchyLevel", "createdDate", "modifiedDate");
		table.setColumnHeaders("Role Name", "Role Description", "Hierarchy",
				"Created", "Last Updated");

		table.setPageLength(NumericConstants.TEN);
		table.setImmediate(true);
		table.setSelectable(true);
		table.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                   
            return;

            }
    });
		// Handle selection changes
		table.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = -7031867080753509788L;

                        @Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					try {
						binder.commit();
						
						BusinessroleMasterDTO selectedItem = searchResultbeans
								.getItem(event.getProperty().getValue())
								.getBean();
						
						binder.setItemDataSource(new BeanItem<BusinessroleMasterDTO>(
								selectedItem));
						
					} catch (CommitException e) {
                                             LOGGER.error(e.getMessage());
					}
				} else {
					binder.setItemDataSource(new BeanItem<BusinessroleMasterDTO>(new BusinessroleMasterDTO()));
					
				}
				binder.getField("businessroleMasterSid").setReadOnly(true);
			}
		});
		return table;
	}

	public ErrorLabel getErrorMsg() {
		return errorMsg;
	}

	public BusinessRoleMgmtLogic getBusinessRoleMgmtLogic() {
		return businessRoleMgmtLogic;
	}

	public void setBusinessRoleMgmtLogic(BusinessRoleMgmtLogic businessRoleMgmtLogic) {
		this.businessRoleMgmtLogic = businessRoleMgmtLogic;
	}

}
