package com.stpl.app.global.ifp.ui.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.global.item.dto.ItemIrtIdentifierDTO;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

// TODO: Auto-generated Javadoc
/**
 * The Class IdentifierResults.
 */
public final class IdentifierResults extends StplCustomComponent {

	/**
	 * The logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(IdentifierResults.class);
	/**
	 * The space.
	 */
	private final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
	/**
	 * The error msg.
	 */
	private final ErrorLabel errorMsg = new ErrorLabel();
	/**
	 * The item logic.
	 */
	private final ItemSearchLogic itemLogic = new ItemSearchLogic();
	/**
	 * The identifier form bean.
	 */
	private final ItemIrtIdentifierDTO identifierFormBean = new ItemIrtIdentifierDTO();
	/**
	 * The binder.
	 */
	private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<ItemIrtIdentifierDTO>(identifierFormBean));
	/**
	 * The identifier results bean.
	 */
	private BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean = new BeanItemContainer<ItemIrtIdentifierDTO>(ItemIrtIdentifierDTO.class);
	/**
	 * The table.
	 */
	private final Table table = new Table();
	/**
	 * The item irt qualifier name.
	 */
	private NativeSelect itemIrtQualifierName = new NativeSelect();
	/**
	 * The item identifier.
	 */
	private final TextField itemIdentifier = new TextField();
	/**
	 * The entity code.
	 */
	private final TextField entityCode = new TextField();
	/**
	 * The start date.
	 */
	private final PopupDateField startDate = new PopupDateField();
	/**
	 * The end date.
	 */
	private final PopupDateField endDate = new PopupDateField();
	/**
	 * The identifier status.
	 */
	private final NativeSelect identifierStatus = new NativeSelect();

	/**
	 * Instantiates a new identifier results.
	 *
	 * @param identifierResultsBean
	 *            the identifier results bean
	 */
	public IdentifierResults(final BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean) throws SystemException, PortalException, Exception {
		super();
		this.identifierResultsBean = identifierResultsBean;
		init();
	}

	/**
	 * Initial method when the Constructor get calls.
	 */
	private void init() throws SystemException, PortalException, Exception {
		space.setHeight("10px");
		space.setHeight(ConstantsUtils.HEIGHT);
		binder.bindMemberFields(this);
		binder.setBuffered(true);
		binder.setErrorDisplay(errorMsg);
		addToContent();
		configureFields();
	}

	/**
	 * Method to Add all the content in a form.
	 *
	 * @throws Exception
	 *             the exception
	 */
	private void addToContent() {

		final VerticalLayout content = new VerticalLayout();
		content.addComponentAsFirst(space);
		content.addComponent(errorMsg);
		content.addComponentAsFirst(space);
		content.addComponent(addToGrid());
		content.addComponent(space);
		content.addComponent(space);
		content.addComponent(populateButton());
		content.addComponent(space);
		content.addComponent(space);
		content.addComponent(addToTable());
		content.addComponent(space);
		setCompositionRoot(content);

	}

	/**
	 * Adds all the components into grid.
	 *
	 * @return the grid layout
	 * @throws Exception
	 *             the exception
	 */
	private GridLayout addToGrid() {

		final GridLayout grid = new GridLayout(6, 2);

		grid.setSpacing(true);

		grid.addComponent(new Label("Qualifier Name"));
		grid.addComponent(itemIrtQualifierName);
		grid.addComponent(new Label("Identifier"));
		grid.addComponent(itemIdentifier);
		grid.addComponent(new Label("Start Date"));
		grid.addComponent(startDate);
		grid.addComponent(new Label("End Date"));
		grid.addComponent(endDate);
		grid.addComponent(new Label("Entity Code"));
		grid.addComponent(entityCode);
		grid.addComponent(new Label("Identifier Status"));
		grid.addComponent(identifierStatus);

		return grid;
	}

	/**
	 * Adding table properties.
	 *
	 * @return the table
	 * @throws Exception
	 *             the exception
	 */
	private Table addToTable() {

		table.setContainerDataSource(identifierResultsBean);
		table.setVisibleColumns(UIUtils.IDEN_FORM_COL_ORDER);
		table.setColumnHeaders(UIUtils.IDEN_FORM_COL_ORDER_HEADER);
		table.setPageLength(7);
		table.setImmediate(true);
		table.setSelectable(true);
		table.setSizeFull();
		table.addItemClickListener(new ItemClickListener() {
			/**
			 * Called when a item has been clicked.
			 */
			@SuppressWarnings("PMD")
			public void itemClick(final ItemClickEvent event) {
				// empty method
			}
		});
		table.setErrorHandler(new ErrorHandler() {

			/**
			 * Invoked when an error occurs.
			 */
			public void error(final com.vaadin.server.ErrorEvent event) {
				// empty method
			}
		});

		return table;
	}

	/**
	 * Method to load Populate button and its properties.
	 *
	 * @return the button
	 */
	public Button populateButton() {
		final Button btnPopulate = new Button(ConstantsUtils.POPULATE);

		btnPopulate.setImmediate(true);
		btnPopulate.setWidth("85");
		btnPopulate.setErrorHandler(new ErrorHandler() {

			/**
			 * Invoked when an error occurs.
			 */
			@SuppressWarnings("PMD")
			public void error(final com.vaadin.server.ErrorEvent event) {
				// parses the error.
			}
		});
		btnPopulate.addClickListener(new ClickListener() {

			/**
			 * Called when a button has been clicked.
			 */
			public void buttonClick(final ClickEvent event) {
				LOGGER.info("Entering IdentifierResults Populate Button");
				binder.getFields();

				try {
					binder.commit();

					final ItemIrtIdentifierDTO identForm = new ItemIrtIdentifierDTO();

					identForm.setItemIrtQualifierName(String.valueOf(binder.getField("itemIrtQualifierName").getValue()));
					identForm.setItemIdentifier(String.valueOf(binder.getField("itemIdentifier").getValue()));
					identForm.setEntityCode(String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE).getValue()));
					identForm.setIdentifierStatus((HelperDTO)binder.getField("identifierStatus").getValue());
					if (binder.getField(ConstantsUtils.START_DATE).getValue() != null) {
						identForm.setStartDate((Date) binder.getField(ConstantsUtils.START_DATE).getValue());
					}
					if (binder.getField(ConstantsUtils.END_DATE).getValue() != null) {
						identForm.setEndDate((Date) binder.getField(ConstantsUtils.END_DATE).getValue());
					}
					identifierResultsBean.addBean(identForm);
				} catch (FieldGroup.CommitException ex) {
					LOGGER.error(ex);
				} catch (Exception exception) {
                                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {  
                                            /**            
                                             * The method is triggered when a button of the message box is 
                                             * pressed .          
                                             *               
                                             * @param buttonId The buttonId of the pressed button.     
                                             */             
                                            @SuppressWarnings("PMD")          
                                            public void buttonClicked(final ButtonId buttonId) {      
                                                // Do Nothing              
                                            }         
                                        }, ButtonId.OK);      
                                        msg.getButton(ButtonId.OK).focus();
					LOGGER.error(exception);
				}
				LOGGER.info("Ending IdentifierResults Populate Button");
			}
		});

		return btnPopulate;
	}

	/**
	 * Configure fields.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void configureFields() throws SystemException, PortalException, Exception {

		LOGGER.info("In configureFields lodding-getNativeSelectForIdentifier");
		startDate.setDescription(ConstantsUtils.DATE_DES);
		endDate.setDescription(ConstantsUtils.DATE_DES);
		itemIrtQualifierName = new CommonUtils().getNativeSelect(itemIrtQualifierName, itemLogic.getItemQualifier());
		itemIrtQualifierName.setImmediate(true);
		itemIrtQualifierName.setNullSelectionAllowed(true);
		itemIrtQualifierName.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
		itemIrtQualifierName.setDescription((String) itemIrtQualifierName.getValue());
		itemIrtQualifierName.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * Notifies this listener that the Property's value has changed.
			 */
			@SuppressWarnings("PMD")
			public void valueChange(final ValueChangeEvent event) {
				itemIrtQualifierName.setDescription((String) itemIrtQualifierName.getValue());
			}
		});
		new CommonUtils().getStatusSelect(identifierStatus);

		startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
		startDate.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * Notifies this listener that the Property's value has changed.
			 */
			public void valueChange(final ValueChangeEvent event) {
                            startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));				
			}
		});
		endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
		endDate.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * Notifies this listener that the Property's value has changed.
			 */
			public void valueChange(final ValueChangeEvent event) {
                            endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));				
			}
		});
		itemIrtQualifierName.addValueChangeListener(new Property.ValueChangeListener() {

			/**
			 * Notifies this listener that the Property's value has changed.
			 */
			public void valueChange(final ValueChangeEvent event) {
				try {
					final String value = itemIrtQualifierName.getValue().toString();
					if ("EditList".equals(value)) {

						final IdenQualifierEditList sub = new IdenQualifierEditList();

						UI.getCurrent().addWindow(sub);
						LOGGER.info("In configureFields lodding-getNativeSelectForIdentifier");
						new CommonUtils().getNativeSelect(itemIrtQualifierName, itemLogic.getItemQualifier());
						itemIrtQualifierName.markAsDirty();

					}
				} catch (SystemException ex) {
                                    final  String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                    LOGGER.error(errorMsg);
                                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {   
                                            /**             
                                             * The method is triggered when a button of the message box is
                                             * pressed .            
                                             *              
                                             * @param buttonId The buttonId of the pressed button.      
                                             */             
                                            @SuppressWarnings("PMD")        
                                            public void buttonClicked(final ButtonId buttonId) { 
                                                // Do Nothing      
                                            }         
                                        }, ButtonId.OK);     
                                        msg.getButton(ButtonId.OK).focus();
                                } catch (PortalException portException) {
					LOGGER.error(portException);
					final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {   
                                            /**             
                                             * The method is triggered when a button of the message box is
                                             * pressed .            
                                             *              
                                             * @param buttonId The buttonId of the pressed button.      
                                             */             
                                            @SuppressWarnings("PMD")        
                                            public void buttonClicked(final ButtonId buttonId) { 
                                                // Do Nothing      
                                            }         
                                        }, ButtonId.OK);     
                                        msg.getButton(ButtonId.OK).focus();
				} catch (Exception exception) {
					final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {        
                                            /**           
                                             * The method is triggered when a button of the message box is              
                                             * pressed .            
                                             *            
                                             * @param buttonId The buttonId of the pressed button.    
                                             */        
                                            @SuppressWarnings("PMD")           
                                            public void buttonClicked(final ButtonId buttonId) {  
                                                // Do Nothing          
                                            }          
                                        }, ButtonId.OK);     
                                        msg.getButton(ButtonId.OK).focus();
					LOGGER.error(exception);
				}

			}
		});
		itemIrtQualifierName.setDescription((String) itemIrtQualifierName.getValue());
		itemIrtQualifierName.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * Notifies this listener that the Property's value has changed.
			 */
			public void valueChange(final ValueChangeEvent event) {
				itemIrtQualifierName.setDescription((String) itemIrtQualifierName.getValue());
			}
		});

	}

	/**
	 * The Class DateValidator.
	 */
	@SuppressWarnings("rawtypes")
	public class DateValidator extends AbstractValidator {

		/**
		 * Instantiates a new date validator.
		 */
		public DateValidator() {
			super(StringUtils.EMPTY);
		}

		/**
		 * Instantiates a new date validator.
		 *
		 * @param errorMessage
		 *            the error message
		 */
		public DateValidator(final String errorMessage) {
			super(errorMessage);
		}

		/**
		 * Validate the itemFamilyplanStartDate Values and throws error if it is
		 * fails.
		 *
		 * @param value
		 *            the value
		 * @throws InvalidValueException
		 *             the invalid value exception
		 */
		@Override
		public void validate(final Object value) throws Validator.InvalidValueException {
			if (startDate.getValue() != null && endDate.getValue() != null) {
				if (startDate.getValue().after(endDate.getValue())) {
					throw new Validator.InvalidValueException("Start date is less than End date");
				} else if (startDate.getValue().equals(endDate.getValue())) {
					throw new Validator.InvalidValueException("Start date and End date are equal");
				}
			}

		}

		/**
		 * Validate whether the date is valid by comparing with 0.
		 *
		 * @param value
		 *            the value
		 * @return Boolean - true / false
		 */
		@Override
		protected boolean isValidValue(final Object value) {

			if (startDate.getValue() != null && endDate.getValue() != null) {
				return startDate.getValue().compareTo(startDate.getValue()) <= 0;
			}

			return true;
		}

		/**
		 * . Get Null type
		 *
		 * @return null
		 */
		@Override
		public Class getType() {
			return null;
		}
	}

	/**
	 * Gets the identifier results bean.
	 *
	 * @return the identifierResultsBean
	 */
	public BeanItemContainer<ItemIrtIdentifierDTO> getIdentifierResultsBean() {
		return identifierResultsBean;
	}

	/**
	 * Sets the identifier results bean.
	 *
	 * @param identifierResultsBean
	 *            the identifierResultsBean to set
	 */
	public void setIdentifierResultsBean(final BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean) {
		this.identifierResultsBean = identifierResultsBean;
	}

	/**
	 * Gets the item irt qualifier name.
	 *
	 * @return the item irt qualifier name
	 */
	public NativeSelect getItemIrtQualifierName() {
		return itemIrtQualifierName;
	}

	/**
	 * Sets the item irt qualifier name.
	 *
	 * @param itemIrtQualifierName
	 *            the new item irt qualifier name
	 */
	public void setItemIrtQualifierName(final NativeSelect itemIrtQualifierName) {
		this.itemIrtQualifierName = itemIrtQualifierName;
	}

	/**
	 * Gets the space.
	 *
	 * @return the space
	 */
	public Label getSpace() {
		return space;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public ErrorLabel getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Gets the item logic.
	 *
	 * @return the item logic
	 */
	public ItemSearchLogic getItemLogic() {
		return itemLogic;
	}

	/**
	 * Gets the identifier form bean.
	 *
	 * @return the identifier form bean
	 */
	public ItemIrtIdentifierDTO getIdentifierFormBean() {
		return identifierFormBean;
	}

	/**
	 * Gets the binder.
	 *
	 * @return the binder
	 */
	public ErrorfulFieldGroup getBinder() {
		return binder;
	}

	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Gets the item identifier.
	 *
	 * @return the item identifier
	 */
	public TextField getItemIdentifier() {
		return itemIdentifier;
	}

	/**
	 * Gets the entity code.
	 *
	 * @return the entity code
	 */
	public TextField getEntityCode() {
		return entityCode;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public PopupDateField getStartDate() {
		return startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public PopupDateField getEndDate() {
		return endDate;
	}

	/**
	 * Gets the identifier status.
	 *
	 * @return the identifier status
	 */
	public NativeSelect getIdentifierStatus() {
		return identifierStatus;
	}

}
