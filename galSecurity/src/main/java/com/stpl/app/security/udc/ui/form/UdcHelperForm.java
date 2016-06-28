package com.stpl.app.security.udc.ui.form;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;

import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.impl.BrandMasterImpl;
import com.stpl.app.model.impl.HelperTableImpl;
import com.stpl.app.security.dao.UdcLogicDAO;
import com.stpl.app.security.dao.impl.UdcLogicDAOImpl;
import com.stpl.app.security.udc.dto.BrandContainer;
import com.stpl.app.security.udc.dto.BrandCriteria;
import com.stpl.app.security.udc.dto.BrandMasterDTO;
import com.stpl.app.security.udc.dto.HelperForm;
import com.stpl.app.security.udc.logic.UdcLogic;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.NotificationUtils;
import com.stpl.app.util.ValidationUtils;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UdcHelperForm extends CustomComponent implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7820110023085927101L;
	private UdcLogic udcLogic = new UdcLogic();
	final ErrorLabel errorMsg = new ErrorLabel();
	final Label space = new Label("&nbsp;", ContentMode.HTML);
	final NativeSelect category = new NativeSelect();
	final TextField description = new TextField();
        final TextField brandId = new TextField();
        final TextField brandName = new TextField();
        final TextField displayBrand = new TextField();
	
	ErrorfulFieldGroup binder;
        ErrorfulFieldGroup brandBinder;
	private static final Object[] HELPER_COLUMNS=new Object[]{"description","listName"};
	private static final String[] HELPER_HEADERS=new String[]{"Description","Category"};
        
        private static final Object[] BRAND_HELPER_COLUMNS=new Object[]{"brandId","brandName","displayBrand", "category"};
	private static final String[] BRAND_HELPER_HEADERS=new String[]{"Brand ID","Brand Name","Display Brand", "Category"};
        
	
	Button btnDelete = new Button("Delete");
        
	private static final Logger LOGGER = LogManager
			.getLogger(UdcHelperForm.class.getName());
	
	BeanItemContainer<HelperForm> searchResultbeans;
	Table table = new Table();
        LazyBeanItemContainer searchResults;
        final Label valueLabel = new Label("Value");
        final Label brandIdLabel = new Label("Brand ID");
        final Label brandNameLabel = new Label("Brand Name");
        final Label displayBrandLabel = new Label("Display Brand");
        int masterSid = 0;
        UdcLogicDAO dao = new UdcLogicDAOImpl();

	public UdcHelperForm(BeanItemContainer<HelperForm> searchResultbeans, Table table) {
		this.searchResultbeans = searchResultbeans;
		this.table = table;
		init();
	}

	private void init() {
		space.setHeight("20");
		addToContent();
	
		configureFields();
		getBinder();
                getBrandBinder();
	}

	private void addToContent() {
            LOGGER.info("inside UDC addToContent");
		final VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.addComponentAsFirst(space);
		content.addComponent(errorMsg);
		content.addComponent(space);
		content.addComponent(space);
                VerticalLayout vLayout=new VerticalLayout(); 
                vLayout.setMargin(true);
                vLayout.setStyleName("bootstrap-search-criteria");
                vLayout.addComponent(addGrid());
                vLayout.addComponent(deleteButton());
                Panel categoryPanel = new Panel("Category Options");
                categoryPanel.setContent(vLayout);
		content.addComponent(categoryPanel);
		content.addComponent(space);
		content.addComponent(space);
		content.addComponent(space);
	
		content.addComponent(space);
                Panel resultsPanel = new Panel("Results");
                resultsPanel.setContent(addToTable());
		content.addComponent(resultsPanel);
		
		
		setCompositionRoot(content);
	}

	private ErrorfulFieldGroup getBinder() {
		
		binder = new ErrorfulFieldGroup(
				new BeanItem<HelperForm>(new HelperForm()));
		binder.setBuffered(true);
		binder.bindMemberFields(this);
		binder.setErrorDisplay(errorMsg);
		return binder;
	}
        
      private ErrorfulFieldGroup getBrandBinder() {

        brandBinder = new ErrorfulFieldGroup(
                new BeanItem<BrandMasterDTO>(new BrandMasterDTO()));
        brandBinder.setBuffered(true);
        brandBinder.bindMemberFields(this);
        brandBinder.setErrorDisplay(errorMsg);
        return brandBinder;
    }
  GridLayout gridLayout = new GridLayout(5, 4);
    private GridLayout addGrid() {
      
 
        gridLayout.setSpacing(true);
        gridLayout.addComponent(new Label("Category"));
        gridLayout.addComponent(category);
        return gridLayout;
    }

	
    protected Table addToTable() {

        table.markAsDirty();
        table.setContainerDataSource(searchResultbeans);
        table.setVisibleColumns(HELPER_COLUMNS);
        table.setColumnHeaders(HELPER_HEADERS);
        table.setPageLength(20);
        table.setImmediate(true);
        table.setSelectable(true);
        table.setComponentError(null);
        table.setValidationVisible(false);

        table.setErrorHandler(new ErrorHandler() {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public void error(com.vaadin.server.ErrorEvent event) {

            }
        });

        table.setWidth("100%");
        table.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method is called when results value is changed
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.info("In addResultsTable results.addValueChangeListener started");
                resultsItemClick(event.getProperty().getValue());
                LOGGER.info("In addResultsTable results.addValueChangeListener Ended");
            }
        });

        return table;
    }



 /**
     * Results item click.
     *
     * @param obj the id
     */
    protected void resultsItemClick(final Object obj) {
        LOGGER.info("resultsItemClick Method Started");
        if (obj == null) {
            masterSid=0;
        } else {
         
            BeanItem<?> targetItem;
            if (obj instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) obj;
            } else if (obj instanceof BrandMasterDTO) {
                targetItem = new BeanItem<BrandMasterDTO>((BrandMasterDTO) obj);
                 masterSid = ((BrandMasterDTO) targetItem.getBean()).getBrandMasterSid();
            } else if(obj instanceof HelperForm){
                 targetItem = new BeanItem<HelperForm>((HelperForm) obj);
                 masterSid = ((HelperForm) targetItem.getBean()).getHelperTableSid();
            }else{
                targetItem = null;
                masterSid = 0;
            }
        }
        LOGGER.info("resultsItemClick Method Ended");
    }



	public void configureFields() {
		
		category.setNullSelectionAllowed(true);
		category.setNullSelectionItemId("- Select One -");
		category.setValidationVisible(true);
		category.setImmediate(true);
		category.setRequired(true);
	category.setRequiredError("Category should be selected");
        category.setContainerDataSource(udcLogic.getListNames());
		category.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			public void valueChange(ValueChangeEvent event) {
				
				
				if (event.getProperty().getValue() != null) {
                                    
                                    if(event.getProperty().getValue().equals("BRAND")) {
                                        gridLayout.removeAllComponents();
                                        gridLayout.addComponent(new Label("Category"), 0, 0);
                                        gridLayout.addComponent(category, 1, 0);
                                        gridLayout.addComponent(brandIdLabel,2,0);
                                        gridLayout.addComponent(brandId,3,0);
                                        gridLayout.addComponent(new Label(""),4,0);
                                        
                                        
                                        gridLayout.addComponent(new Label(""),0,1);
                                        gridLayout.addComponent(new Label(""),1,1);
                                        gridLayout.addComponent(brandNameLabel,2,1);
                                        gridLayout.addComponent(brandName,3,1);
                                        gridLayout.addComponent(new Label(""),4,1);
                                        
                                        gridLayout.addComponent(new Label(""),0,2);
                                        gridLayout.addComponent(new Label(""),1,2);
                                        gridLayout.addComponent(displayBrandLabel,2,2);
                                        gridLayout.addComponent(displayBrand,3,2);
                                        gridLayout.addComponent(addButton(), 4,2);
                                        
                                      
                                        table.removeAllItems();
                                        searchResults = new LazyBeanItemContainer(BrandMasterDTO.class, new BrandContainer(category.getValue()), new BrandCriteria());
                                        table.setContainerDataSource(searchResults);
                                        table.setVisibleColumns(BRAND_HELPER_COLUMNS);
                                        table.setColumnHeaders(BRAND_HELPER_HEADERS);
                                        
                                        
                                    } else {
                                        gridLayout.removeAllComponents();
                                        gridLayout.addComponent(new Label("Category"), 0, 0);
                                        gridLayout.addComponent(category, 1, 0);
                                        
                                        gridLayout.addComponent(valueLabel,2,0);
                                        gridLayout.addComponent(description,3,0);
                                        gridLayout.addComponent(addButton(), 4, 0);
//                                       
					table.removeAllItems();
					List<HelperForm> helperResult = udcLogic.getDescrition(category.getValue().toString());
					searchResultbeans.addAll(helperResult);
					table.setContainerDataSource(searchResultbeans);
					table.setVisibleColumns(HELPER_COLUMNS);
					table.setColumnHeaders(HELPER_HEADERS);
                                    }
					
				} else {
					
				}
			}

		});
	
		

		description.setImmediate(true);
		description.setValidationVisible(true);               
                description.addValidator(new RegexpValidator(ValidationUtils.alphaNumericChars, ValidationUtils.alphaNumericCharsMessage));
		description
		.addValidator(new StringLengthValidator(
				" Value Should be less than 50 characters", 0,
				50, true));
		
	}

	private Button addButton() {
		// Commit button
		Button btnSave1 = new Button("Add");
		btnSave1.setWidth("75");
		btnSave1.addClickListener(new ClickListener() {
                    
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				try {
					
					binder.getFields();
					binder.commit();
                                        brandBinder.getFields();
                                        brandBinder.commit();
                                        String success = "";
					try {
					        if(StringUtils.EMPTY.equals(description.getValue().trim()) && StringUtils.EMPTY.equals(brandId.getValue().trim()) && StringUtils.EMPTY.equals(brandName.getValue().trim())
                                                        && StringUtils.EMPTY.equals(displayBrand.getValue().trim())) {
                                                       NotificationUtils.getErrorNotification("Error", "Space should not be allowed");
                                                       description.setValue(StringUtils.EMPTY);
                                                       return;
                                                }
                                                if(category.getValue().equals("BRAND")) {
                                                    success = udcLogic.saveBrandMaster(brandBinder,masterSid);
                                                    
                                                    
                                                } else {
                                                    success = udcLogic.SaveHelperTable(binder);
                                                }
						if (success.equals("success")) {
                                                    
                                                    if (category.getValue().equals("BRAND")) {
                                                        table.removeAllItems();
                                                        searchResults.removeAllItems();
                                                        searchResults = new LazyBeanItemContainer(BrandMasterDTO.class, new BrandContainer(category.getValue()), new BrandCriteria());
                                                        table.setContainerDataSource(searchResults);
                                                        table.setVisibleColumns(BRAND_HELPER_COLUMNS);
                                                        table.setColumnHeaders(BRAND_HELPER_HEADERS);
                                                        } else {
							searchResultbeans.removeAllItems();
				
							List<HelperForm> helperResult = udcLogic.getDescrition(category.getValue().toString());
							searchResultbeans.addAll(helperResult);
                                                        }
                                                        Notification notif = new Notification("Category "
									+ category.getValue().toString() + " Saved successfully",
									Notification.Type.HUMANIZED_MESSAGE);

							notif.setPosition(Position.MIDDLE_CENTER);
							notif.setStyleName("mystyle");
							notif.show(Page.getCurrent());
//							category.setContainerDataSource(udcLogic.getListNames());
//							description.setValue("");
							
						}
						if (success.equals("fail")) {
							Notification notif = new Notification("Category "
									+ category.getValue().toString() + " Save Failed ",
									Notification.Type.HUMANIZED_MESSAGE);

//							searchResultbeans.removeAllItems();
							notif.setPosition(Position.MIDDLE_CENTER);
							notif.setStyleName("mystyle");
							notif.show(Page.getCurrent());

						}
					/*	if(category.getValue().toString()=="CategoryName")
						{
							category.setContainerDataSource(udcLogic.getListNames());
						}*/
			                                 } catch (Exception e) {
                                        LOGGER.error(e);

                                    }

                                } catch (CommitException e) {


				}
			}
		});
		return btnSave1;
	}
        
    private Button deleteButton() {

        btnDelete.setWidth("75");
        btnDelete.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            public void error(com.vaadin.server.ErrorEvent event) {

            }
        });
        btnDelete.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {

                binder.getFields();

                try {

                    if (category.getValue().equals("BRAND")) {

                        if (masterSid != 0) {
                            BrandMaster brandTable = BrandMasterLocalServiceUtil.getBrandMaster(masterSid);
                            BrandMaster brandMaster = dao.getBrandMaster(masterSid);
                            if (brandMaster.getRecordLockStatus()) {
                                NotificationUtils.getWarningNotification("Delete", "You are not having permission to delete this record");
                            } else {
                                brandMaster.setInboundStatus("D");
                                brandTable = udcLogic.deleteBrandTableByCode(brandMaster);
                                searchResults.removeAllItems();
                                searchResults = new LazyBeanItemContainer(BrandMasterDTO.class, new BrandContainer(category.getValue()), new BrandCriteria());
                                table.setContainerDataSource(searchResults);
                                table.setVisibleColumns(BRAND_HELPER_COLUMNS);
                                table.setColumnHeaders(BRAND_HELPER_HEADERS);
                                Notification notif = new Notification(" Category Value " + "BRAND" + " Deleted successfully", Notification.Type.HUMANIZED_MESSAGE);
                                notif.setPosition(Position.MIDDLE_CENTER);
                                notif.setStyleName("mystyle");
                                notif.show(Page.getCurrent());
                            }
                        } else if (masterSid == 0) {
                            NotificationUtils.getErrorNotification("Error", "Please select Brand to delete");
                        } else {
                            Notification notif = new Notification("Deleted Failed. Category Value " + "BRAND" + " is currently used in Master Records", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName("mystyle");
                            notif.show(Page.getCurrent());
                        }
                    } else {
                        HelperTable check = HelperTableLocalServiceUtil.getHelperTable(masterSid);
                        HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(masterSid);
                        if (check.getRefCount() == 0 && masterSid != 0) {
                            helperTable = udcLogic.deleteHelperTableByCode(masterSid);

                            searchResultbeans.removeAllItems();

                            List<HelperForm> helperResult = udcLogic.getDescrition(helperTable.getListName());
                            searchResultbeans.addAll(helperResult);
                            Notification notif = new Notification(" Category Value " + helperTable.getListName() + " Deleted successfully", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName("mystyle");
                            notif.show(Page.getCurrent());
                        } else if (masterSid == 0) {
                            Notification notif = new Notification("Deleted Failed", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName("mystyle");
                            notif.show(Page.getCurrent());
                        } else {
                            Notification notif = new Notification("Deleted Failed. Category Value " + helperTable.getListName() + "is currently used in Master Records", Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.MIDDLE_CENTER);
                            notif.setStyleName("mystyle");
                            notif.show(Page.getCurrent());
                        }
                    }

                } catch (Exception e) {
                                       LOGGER.error(e);

                }

            }
        });
        return btnDelete;
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
	
}
