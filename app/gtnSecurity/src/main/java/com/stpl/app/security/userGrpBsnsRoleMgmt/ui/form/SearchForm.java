package com.stpl.app.security.userGrpBsnsRoleMgmt.ui.form;

import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.stpl.app.security.userGrpBsnsRoleMgmt.dto.UserGrpBsnsRoleDTO;
import com.stpl.app.security.userGrpBsnsRoleMgmt.logic.UserGrpBsnsRoleLogic;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.TwinColSelect;
import com.vaadin.v7.ui.VerticalLayout;
import org.jboss.logging.Logger;

public class SearchForm extends CustomComponent {
    private static final long serialVersionUID = 8893447767363695369L;
    private static final Logger LOGGER = Logger
            .getLogger(SearchForm.class.getName());
    final ErrorLabel errorMsg = new ErrorLabel();
    final Label space = new Label("&nbsp;", ContentMode.HTML);
    final NativeSelect userGroup1 = new NativeSelect();
    final TwinColSelect ugTobrSelect = new TwinColSelect();
    UserGrpBsnsRoleLogic businessRoleModuleLogic = new UserGrpBsnsRoleLogic();
    UserGrpBsnsRoleDTO usrDTO = new UserGrpBsnsRoleDTO();
    ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<UserGrpBsnsRoleDTO>(usrDTO));
	
	public SearchForm() {
		init();
	}

	private void init() {
		space.setHeight("20");
		addToContent();
		binder.setBuffered(true);
		binder.bindMemberFields(this);
		binder.setErrorDisplay(errorMsg);
		configureFields();
	}

	   private void addToContent() {
        final VerticalLayout content = new VerticalLayout();
        final HorizontalLayout content1 = new HorizontalLayout();
        content.addComponentAsFirst(space);
        content.addComponent(errorMsg);
        content.addComponent(space);
        content.addComponent(addToGrid());
        content.addComponent(space);
        content1.addComponent(SearchButton());
        content1.addComponent(ResetButton());
        content.addComponent(content1);
        setCompositionRoot(content);
		
	}

	
	private GridLayout addToGrid() {
		GridLayout grid = new GridLayout(1, 1);
		grid.setWidth(null);
		grid.setSpacing(true);
		grid.addComponent(new Label("user Group"));
		grid.addComponent(userGroup1);
		grid.addComponent(ugTobrSelect);
		return grid;
	}

	   private void configureFields() {
        ugTobrSelect.setNullSelectionAllowed(false);
        ugTobrSelect.setRequired(true);
        ugTobrSelect.setImmediate(true);
        ugTobrSelect.setCaption("Select Business Roles");
        ugTobrSelect.setSizeFull();
        ugTobrSelect.setWidth("400px");
        userGroup1.setNullSelectionAllowed(true);
        userGroup1.setRequired(true);
        userGroup1.setNullSelectionItemId("Select Sub Module");
        userGroup1.setValidationVisible(true);
        userGroup1.setImmediate(true);
        userGroup1.setContainerDataSource(getUserGroupValue());

        userGroup1.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    if (!"".equals(event.getProperty().getValue())) {
                        usrDTO.setSelUserGroupId(event.getProperty().getValue().toString());
                        usrDTO = businessRoleModuleLogic.getBsnsRoles(usrDTO);

                        ugTobrSelect.setContainerDataSource(new IndexedContainer(usrDTO.getBusinessRole()));
                        ugTobrSelect.setValue(usrDTO.getSelectedBusinessRole());
                    } else {
                        Notification notif = new Notification("Select User Group", Notification.Type.ERROR_MESSAGE);

                        notif.setPosition(Position.MIDDLE_CENTER);
                        notif.setStyleName(CommonUtils.MYSTYLE);
                        notif.show(Page.getCurrent());
                    }

                } else {
                    ugTobrSelect.removeAllItems();
                }
            }
        });



    }
	
	private Container getUserGroupValue(){
		usrDTO =  businessRoleModuleLogic.getUserGroups();
		List<String> helperLst = usrDTO.getUserGroup();
		return new IndexedContainer(helperLst);
	}
	
	private Button SearchButton() {
		// Commit button
		Button btnSearch = new Button("Save");
		btnSearch.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            public void error(com.vaadin.server.ErrorEvent event) {
                return;

            }
    });
		btnSearch.setWidth("75");
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			
			public void buttonClick(ClickEvent event) {
				try {
					binder.getFields();
					binder.commit();
					
					Collection selectedList = (Collection) ugTobrSelect.getValue();
					List list = new ArrayList();
					list.addAll(selectedList);
					if(list.size()>0){
					usrDTO.setSelectedBusinessRole(list);
					boolean check = businessRoleModuleLogic.saveLogic(usrDTO);
					
					if(check){
						Notification notif = new Notification("Data Saved Successfully",Notification.Type.HUMANIZED_MESSAGE);

	                     notif.setPosition(Position.MIDDLE_CENTER);
	                     notif.setStyleName(CommonUtils.MYSTYLE);
	                     notif.show(Page.getCurrent());
					}
					else{
						Notification notif = new Notification("Error occured while saving the details",Notification.Type.HUMANIZED_MESSAGE);

	                     notif.setPosition(Position.MIDDLE_CENTER);
	                     notif.setStyleName(CommonUtils.MYSTYLE);
	                     notif.show(Page.getCurrent());
					}
					}
					else{
						Notification notif = new Notification("Select business roles to save",Notification.Type.HUMANIZED_MESSAGE);

	                     notif.setPosition(Position.MIDDLE_CENTER);
	                     notif.setStyleName(CommonUtils.MYSTYLE);
	                     notif.show(Page.getCurrent());
					}
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}
		});
		return btnSearch;
	}

	private Button ResetButton() {
		Button btnReset = new Button("Reset");
		btnReset.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            public void error(com.vaadin.server.ErrorEvent event) {
                return;
            }
    });
		btnReset.setWidth("75");
		          btnReset.addClickListener(new ClickListener() {
                private static final long serialVersionUID = 1L;

                public void buttonClick(ClickEvent event) {
                    userGroup1.setContainerDataSource(getUserGroupValue());
                    ugTobrSelect.setContainerDataSource(new IndexedContainer());
                }
            });
		return btnReset;
	}

	
	
}
