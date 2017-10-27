package com.stpl.app.global.cfp.ui.form;

import com.stpl.app.global.common.util.HelperListUtil;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 * The Class IdentifierResults.
 */
public final class IdentifierResults extends CustomComponent {

	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(IdentifierResults.class);

	/** The space. */
	private final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);

	/** The company logic. */
	private final CompanySearchLogic companyLogic = new CompanySearchLogic();

	/** The identifier form bean. */
	private final CompanyCrtIdentifierDTO identifierFormBean = new CompanyCrtIdentifierDTO();

	/** The binder. */
	private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(identifierFormBean));

	/** The identifier results bean. */
	private BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean = new BeanItemContainer<>(CompanyCrtIdentifierDTO.class);

	/** The company crt qualifier name. */
	private ComboBox companyCrtQualifierName = new ComboBox();

	/** The company identifier. */
	private final TextField companyIdentifier = new TextField();

	/** The entity code. */
	private final TextField entityCode = new TextField();

	/** The start date. */
	private final PopupDateField startDate = new PopupDateField();

	/** The end date. */
	private final PopupDateField endDate = new PopupDateField();

	/** The identifier status. */
	private final ComboBox identifierStatus = new ComboBox();
        HelperListUtil helperListUtil=HelperListUtil.getInstance();

	/**
	 * @return the binder
	 */
	public ErrorfulFieldGroup getBinder() {
		return binder;
	}

	/**
	 * @param binder
	 *            the binder to set
	 */
	public void setBinder(final ErrorfulFieldGroup binder) {
		this.binder = binder;
	}

	/**
	 * @return the identifierResultsBean
	 */
	public BeanItemContainer<CompanyCrtIdentifierDTO> getIdentifierResultsBean() {
		return identifierResultsBean;
	}

	/**
	 * @param identifierResultsBean
	 *            the identifierResultsBean to set
	 */
	public void setIdentifierResultsBean(final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean) {
		this.identifierResultsBean = identifierResultsBean;
	}

	/**
	 * @return the companyCrtQualifierName
	 */
	public ComboBox getCompanyCrtQualifierName() {
		return companyCrtQualifierName;
	}

	/**
	 * @param companyCrtQualifierName
	 *            the companyCrtQualifierName to set
	 */
	public void setCompanyCrtQualifierName(final ComboBox companyCrtQualifierName) {
		this.companyCrtQualifierName = companyCrtQualifierName;
	}

	/**
	 * @return the space
	 */
	public Label getSpace() {
		return space;
	}

	/**
	 * @return the companyLogic
	 */
	public CompanySearchLogic getCompanyLogic() {
		return companyLogic;
	}

	/**
	 * @return the identifierFormBean
	 */
	public CompanyCrtIdentifierDTO getIdentifierFormBean() {
		return identifierFormBean;
	}

	/**
	 * @return the companyIdentifier
	 */
	public TextField getCompanyIdentifier() {
		return companyIdentifier;
	}

	/**
	 * @return the entityCode
	 */
	public TextField getEntityCode() {
		return entityCode;
	}

	/**
	 * @return the startDate
	 */
	public PopupDateField getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public PopupDateField getEndDate() {
		return endDate;
	}

	/**
	 * @return the identifierStatus
	 */
	public ComboBox getIdentifierStatus() {
		return identifierStatus;
	}

	/**
	 * The Constructor.
	 *
	 * @param identifierResultsBean
	 *            the identifier results bean
	 */
	public IdentifierResults(final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean) {
		super();
		this.identifierResultsBean = identifierResultsBean;
		init();
	}

	/**
	 * Initial method when the Constructor get calls
	 */
	private void init() {
		try {
			LOGGER.debug("Entering IdentifierResults init");
			space.setHeight(ConstantsUtils.HEIGHT);
			binder.bindMemberFields(this);
			addToContent();
			configureFields();
			LOGGER.debug("Ending IdentifierResults init");
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
                } catch (PortalException ex) {
			LOGGER.error(ex);
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
		} catch (Exception ex) {
			LOGGER.error(ex);
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
		}
	}

	/**
	 * Adds the to content.
	 */
	private void addToContent() {
		LOGGER.debug("Entering IdentifierResults addToContent");
		final VerticalLayout content = new VerticalLayout();
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
		LOGGER.debug("Ending IdentifierResults addToContent");
	}

	/**
	 * Adds the to grid.
	 *
	 * @return the grid layout
	 */
	private GridLayout addToGrid() {
		LOGGER.debug("Entering IdentifierResults addToGrid");
		final GridLayout grid = new GridLayout(6, NumericConstants.TWO);
		grid.setWidth(null);
		grid.setSpacing(true);
		grid.addComponent(new Label("Company Qualifier"));
		grid.addComponent(companyCrtQualifierName);
		grid.addComponent(new Label("Identifier"));
		grid.addComponent(companyIdentifier);
		grid.addComponent(new Label("Start Date"));
		grid.addComponent(startDate);
		grid.addComponent(new Label("End Date"));
		grid.addComponent(endDate);
		grid.addComponent(new Label("Entity Code"));
		grid.addComponent(entityCode);
		grid.addComponent(new Label("Identifier Status"));
		grid.addComponent(identifierStatus);
		LOGGER.debug("Ending IdentifierResults addToGrid");
		return grid;
	}

	/**
	 * Adds the to table.
	 *
	 * @return the table
	 */
	private Table addToTable() {
		LOGGER.debug("Entering IdentifierResults addToTable");
		final Table table = new Table();
		table.setContainerDataSource(identifierResultsBean);
		table.setVisibleColumns(UIUtils.getInstance().idenFormColOrder);
		table.setColumnHeaders(UIUtils.getInstance().idenFormHeader);
		table.setPageLength(NumericConstants.SEVEN);
		table.setImmediate(true);
		table.setSelectable(true);
		table.addItemClickListener(new ItemClickListener() {
			/**
			 * Called when a item has been clicked.
			 */
			@SuppressWarnings("PMD")
			public void itemClick(final ItemClickEvent event) {
				// empty method
			}
		});
		LOGGER.debug("Ending IdentifierResults addToTable");
		return table;
	}

	/**
	 * Populate button.
	 *
	 * @return the button
	 */
	public Button populateButton() {
		final Button btnPopulate = new Button(ConstantsUtils.POPULATE);
		btnPopulate.setWidth(ConstantsUtils.BTN_WIDTH);
		btnPopulate.addClickListener(new ClickListener() {
			/**
			 * Called when a Button has been clicked.
			 */
			@SuppressWarnings("PMD")
			public void buttonClick(final ClickEvent event) {
				try {
					LOGGER.debug("Entering inside IdentifierResults POPULATE  method ");
					binder.commit();
					final CompanyCrtIdentifierDTO identForm = new CompanyCrtIdentifierDTO();
					identForm.setCompanyCrtQualifierName(String.valueOf(binder.getField(ConstantsUtils.COMPANY_QUALIFIER_NAME).getValue()));
					identForm.setCompanyIdentifier(String.valueOf(binder.getField("companyIdentifier").getValue()));
					identForm.setEntityCode(String.valueOf(binder.getField(ConstantsUtils.ENTITY_CODE).getValue()));
					identForm.setIdentifierStatus(helperListUtil.getIdHelperDTOMap().get(String.valueOf(binder.getField("identifierStatus").getValue())));
					if (binder.getField(ConstantsUtils.START_DATE).getValue() != null) {
						identForm.setStartDate((Date) binder.getField(ConstantsUtils.START_DATE).getValue());
					}
					if (binder.getField(ConstantsUtils.END_DATE).getValue() != null) {
						identForm.setEndDate((Date) binder.getField(ConstantsUtils.END_DATE).getValue());
					}
					identifierResultsBean.addBean(identForm);
					LOGGER.debug("Ending IdentifierResults POPULATE  method ");
				} catch (CommitException e) {
					LOGGER.error(e);
				}
			}
		});
		return btnPopulate;
	}

	/**
	 * to Configure fields.
	 * 
	 * @throws Exception
	 * @throws SystemException
	 * @throws PortalException
	 */
	public void configureFields() throws PortalException, SystemException {
		LOGGER.debug("Entering IdentifierResults configureFields");
		startDate.setDescription(ConstantsUtils.DATE_DES);
		endDate.setDescription(ConstantsUtils.DATE_DES);
		companyCrtQualifierName = new CommonUtils().getNativeSelectForIdentifier(companyCrtQualifierName, companyLogic.getCompanyQualifier());
		companyCrtQualifierName.setImmediate(true);
		companyCrtQualifierName.setNullSelectionAllowed(true);
		companyCrtQualifierName.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
		companyCrtQualifierName.setDescription((String) companyCrtQualifierName.getValue());
		companyCrtQualifierName.addValueChangeListener(new Property.ValueChangeListener() {

			/**
			 * This method listens to data source value changes and passes the
			 * changes forwards.
			 */
			@SuppressWarnings("PMD")
			public void valueChange(final ValueChangeEvent event) {

				companyCrtQualifierName.setDescription((String) companyCrtQualifierName.getValue());

			}
		});

		new CommonUtils().getStatusSelect(identifierStatus);
		companyIdentifier.setImmediate(true);
		companyIdentifier.setValidationVisible(true);
		companyIdentifier.setRequiredError("Company Identifier should be present");
		companyIdentifier.setDescription((String) companyIdentifier.getValue());
		companyIdentifier.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * This method listens to data source value changes and passes the
			 * changes forwards.
			 */
			public void valueChange(final ValueChangeEvent event) {

				companyIdentifier.setDescription((String) companyIdentifier.getValue());

			}
		});

		entityCode.setImmediate(true);
		entityCode.setRequired(true);
		entityCode.setValidationVisible(true);
		entityCode.setRequiredError("Entity Code should be present");
		entityCode.setDescription((String) entityCode.getValue());
		entityCode.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * This method listens to data source value changes and passes the
			 * changes forwards.
			 */
			public void valueChange(final ValueChangeEvent event) {

				entityCode.setDescription((String) entityCode.getValue());

			}
		});

		startDate.setRequired(true);
		startDate.setValidationVisible(true);
		startDate.setRequiredError("Start Date should be present");
		startDate.setImmediate(true);
		startDate.addValidator(new DateValidator("Start date should be before End date"));
		startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
		startDate.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * This method listens to data source value changes and passes the
			 * changes forwards.
			 */
			public void valueChange(final ValueChangeEvent event) {
                            startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));				
			}
		});
		endDate.setRequired(true);
		endDate.setValidationVisible(true);
		endDate.setRequiredError("End Date should be present");
		endDate.addValidator(new DateValidator("Start date should be before item end date"));
		endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
		endDate.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * This method listens to data source value changes and passes the
			 * changes forwards.
			 */
			public void valueChange(final ValueChangeEvent event) {
                            endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
			}
		});
		identifierStatus.setRequired(true);
		identifierStatus.setValidationVisible(true);
		identifierStatus.setRequiredError("Status  should be present");
		identifierStatus.setDescription((String) identifierStatus.getValue());
		identifierStatus.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * This method listens to data source value changes and passes the
			 * changes forwards.
			 */
			public void valueChange(final ValueChangeEvent event) {

				identifierStatus.setDescription((String) identifierStatus.getValue());

			}
		});

		companyCrtQualifierName.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * This method listens to data source value changes and passes the
			 * changes forwards.
			 */
			public void valueChange(final ValueChangeEvent event) {
				try {
					final String value = companyCrtQualifierName.getValue().toString();
					if ("EditList".equals(value)) {

						final IdenQualifierEditList sub = new IdenQualifierEditList();

						UI.getCurrent().addWindow(sub);

						new CommonUtils().getNativeSelectForIdentifier(companyCrtQualifierName, companyLogic.getCompanyQualifier());

						companyCrtQualifierName.markAsDirty();
					}
				} catch (PortalException ex) {
					LOGGER.error(ex);
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
                                } catch (Exception e) {
					LOGGER.error(e);
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
				}

			}
		});
		companyCrtQualifierName.setImmediate(true);
		companyCrtQualifierName.setDescription((String) companyCrtQualifierName.getValue());
		companyCrtQualifierName.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * This method listens to data source value changes and passes the
			 * changes forwards.
			 */
			public void valueChange(final ValueChangeEvent event) {

				companyCrtQualifierName.setDescription((String) companyCrtQualifierName.getValue());

			}
		});
		LOGGER.debug("Ending IdentifierResults configureFields");
	}

	/**
	 * The Class DateValidator.
	 */
	@SuppressWarnings("rawtypes")
	public class DateValidator extends AbstractValidator {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/**
		 * The Constructor.
		 */
		public DateValidator() {
			super(StringUtils.EMPTY);
		}

		/**
		 * The Constructor.
		 *
		 * @param errorMessage
		 *            the error message
		 */
		public DateValidator(final String errorMessage) {
			super(errorMessage);
		}

		/**
		 * to validate the given object
		 */
		@Override
		public void validate(final Object value) {
			if (startDate.getValue() != null && endDate.getValue() != null) {
				if (startDate.getValue().after(endDate.getValue())) {
					throw new InvalidValueException("Start date is less than End date");
				} else if (startDate.getValue().equals(endDate.getValue())) {
					throw new InvalidValueException("Start date and End date are equal");
				}
			}

		}

		/**
		 * checks the given object is valid or not
		 */
		@Override
		protected boolean isValidValue(final Object value) {

			if (startDate.getValue() != null && endDate.getValue() != null) {
				return startDate.getValue().compareTo(startDate.getValue()) <= 0;
			}

			return true;
		}

		/**
		 * Returns null.
		 */
		public Class getType() {
			return null;
		}
	}
}
