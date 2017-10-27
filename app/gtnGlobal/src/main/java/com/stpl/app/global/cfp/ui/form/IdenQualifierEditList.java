package com.stpl.app.global.cfp.ui.form;

import org.jboss.logging.Logger;

import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.ui.StplR2Exception;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 * The Class IdenQualifierEditList.
 */
public final class IdenQualifierEditList extends Window {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(IdenQualifierEditList.class);
    
    /** The space. */
    private final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    
    /** The companylogic. */
    private final CompanySearchLogic companylogic = new CompanySearchLogic();
    
    /** The qualifier table. */
    private final Table qualifierTable = new Table();
    
    /** The company qualifier. */
    private final BeanItemContainer<CompanyCrtIdentifierDTO> companyQualifier = new BeanItemContainer<>(
            CompanyCrtIdentifierDTO.class);
    
    /** The identifier dto. */
    private final CompanyCrtIdentifierDTO identifierDTO = new CompanyCrtIdentifierDTO();
    
    /** The company identifier. */
    private final TextField companyIdentifier = new TextField("Qualifier");
    
    /** The company crt qualifier name. */
    private final TextField companyCrtQualifierName = new TextField("Company Qualifier Name");
    
    /** The company crt qualifier id. */
    private final TextField companyCrtQualifierId = new TextField("Company Qualifier Id");
    
    /** The created date. */
    private final PopupDateField createdDate = new PopupDateField(ConstantsUtils.CREATED_DATE);
    
    /** The modified date. */
    private final PopupDateField modifiedDate = new PopupDateField(ConstantsUtils.MODIFIED_DATE);
    
    /** The btn save. */
    private final Button btnSave = new Button(ConstantsUtils.SAVE);
    
    /** The binder. */
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
            new BeanItem<>(identifierDTO));

    /**
	 * @return the space
	 */
	public Label getSpace() {
		return space;
	}

	/**
	 * @return the companylogic
	 */
	public CompanySearchLogic getCompanylogic() {
		return companylogic;
	}

	/**
	 * @return the qualifierTable
	 */
	public Table getQualifierTable() {
		return qualifierTable;
	}

	/**
	 * @return the companyQualifier
	 */
	public BeanItemContainer<CompanyCrtIdentifierDTO> getCompanyQualifier() {
		return companyQualifier;
	}

	/**
	 * @return the identifierDTO
	 */
	public CompanyCrtIdentifierDTO getIdentifierDTO() {
		return identifierDTO;
	}

	/**
	 * @return the companyIdentifier
	 */
	public TextField getCompanyIdentifier() {
		return companyIdentifier;
	}

	/**
	 * @return the companyCrtQualifierName
	 */
	public TextField getCompanyCrtQualifierName() {
		return companyCrtQualifierName;
	}

	/**
	 * @return the companyCrtQualifierId
	 */
	public TextField getCompanyCrtQualifierId() {
		return companyCrtQualifierId;
	}

	/**
	 * @return the createdDate
	 */
	public PopupDateField getCreatedDate() {
		return createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public PopupDateField getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @return the btnSave
	 */
	public Button getBtnSave() {
		return btnSave;
	}

	/**
	 * @return the binder
	 */
	public ErrorfulFieldGroup getBinder() {
		return binder;
	}

	/**
     * The Constructor.
     */
    public IdenQualifierEditList() throws PortalException,SystemException {
        super("Qualifier List");
        companyQualifier.addAll(companylogic.getCompanyQualifierForEditList());
        binder.bindMemberFields(this);

        init();
    }

    /**
     * Initial method when the Constructor get calls.
     *
     * @throws StplR2Exception the stpl r2 exception
     */
    public void init() {
        LOGGER.debug("Entering IdenQualifierEditList init");
        center();
        setClosable(true);
        setModal(true);
        setWidth("1200px");
        setHeight("600px");
        setContent(addToContent());
        configureFields();
        LOGGER.debug("Ending IdenQualifierEditList init");
    }

    /**
     * Configure fields.
     *
     * @throws StplR2Exception the stpl r2 exception
     */
    public void configureFields() {
        
        LOGGER.debug("Entering IdenQualifierEditList configureFields");
        createdDate.setDescription(ConstantsUtils.DATE_DES);
        modifiedDate.setDescription(ConstantsUtils.DATE_DES);
        companyIdentifier.setImmediate(true);
		companyCrtQualifierName.setImmediate(true);
		companyCrtQualifierId.setImmediate(true);
        LOGGER.debug("Ending IdenQualifierEditList configureFields");
    }

    /**
     * Adds the to content.
     *
     * @return the vertical layout
     * @throws StplR2Exception the stpl r2 exception
     */
    public VerticalLayout addToContent() {
        LOGGER.debug("Entering IdenQualifierEditList addToContent");
        final VerticalLayout content = new VerticalLayout();
        content.addComponentAsFirst(space);
		content.addComponent(addToTable());
		content.addComponent(space);
		content.addComponent(space);
		content.addComponent(addToForm());
		content.addComponent(space);
		content.addComponent(space);
		content.addComponent(getButtons());
		content.addComponent(space);
        LOGGER.debug("Ending IdenQualifierEditList addToContent");
        return content;
    }

    /**
     * Gets the buttons.
     *
     * @return the buttons
     * @throws StplR2Exception the stpl r2 exception
     */
    public GridLayout getButtons() {
        LOGGER.debug("Entering IdenQualifierEditList getButtons");
        final GridLayout layout = new GridLayout(NumericConstants.TWO, 1);
        layout.setSpacing(true);
		layout.setWidth(null);
		layout.addComponent(saveButton());
		layout.addComponent(deleteButton());
		layout.addComponent(resetButton());
        LOGGER.debug("Ending IdenQualifierEditList getButtons");
        return layout;
    }

    /**
     * Adds the to table.
     *
     * @return the table
     * @throws StplR2Exception the stpl r2 exception
     */
    private Table addToTable() {
        LOGGER.debug("Entering IdenQualifierEditList addToTable");
        qualifierTable.setContainerDataSource(companyQualifier);
		qualifierTable.setVisibleColumns(UIUtils.getInstance().qualifierCompany);
		qualifierTable.setPageLength(NumericConstants.SEVEN);
		qualifierTable.setImmediate(true);
		qualifierTable.setSelectable(true);
		qualifierTable.addItemClickListener(new ItemClickListener() {
		    /**
		     * Called when a item has been clicked.
		     */	 @SuppressWarnings("PMD")		
		    public void itemClick(final ItemClickEvent event) {
                        LOGGER.debug("Entering IdenQualifierEditList table itemClick");		       
		            try {
						binder.commit();
					} catch (CommitException e) {
						LOGGER.error(e);
					}
		        
		        final CompanyCrtIdentifierDTO companyDTO = companyQualifier.getItem(event.getItemId()).getBean();
		        binder.setItemDataSource(new BeanItem<>(companyDTO));
		        btnSave.setCaption(ConstantsUtils.UPDATE);
                        LOGGER.debug("Ending IdenQualifierEditList table itemClick");		       
		    }
		});
        LOGGER.debug("Ending IdenQualifierEditList addToTable");
        return qualifierTable;
    }

    /**
     * Adds the to form.
     *
     * @return the grid layout
     * @throws StplR2Exception the stpl r2 exception
     */
    public GridLayout addToForm() {
        LOGGER.debug("Entering IdenQualifierEditList addToForm");		       
        final GridLayout grid = new GridLayout(NumericConstants.TWO, 1);
        grid.setWidth(null);
		grid.setSpacing(true);
		grid.addComponent(companyIdentifier);
		grid.addComponent(companyCrtQualifierName);
        LOGGER.debug("Ending IdenQualifierEditList addToForm");		       
        return grid;
    }

    /**
     * Save button.
     *
     * @return the button
     */
    public Button saveButton() {
        LOGGER.debug("Entering IdenQualifierEditList saveButton");		       
        btnSave.setWidth(ConstantsUtils.BTN_WIDTH);
		btnSave.setImmediate(true);
		btnSave.addClickListener(new ClickListener() {
		    /**
		     * Called when a Button has been clicked.
		     */	 @SuppressWarnings("PMD")		
		    public void buttonClick(final ClickEvent event) {
		        qualifierTable.commit();
		        try {
		            LOGGER.debug("Entering inside IdenQualifierEditList  SAVE  method ");
		            binder.commit();
		            final CompanyCrtIdentifierDTO companyDTO = new CompanyCrtIdentifierDTO();

		            companyDTO.setCompanyCrtQualifierId(Integer.valueOf(binder.getField("companyCrtQualifierId").getValue().toString()));
		            companyDTO.setCompanyCrtQualifierName(binder.getField("companyCrtQualifierName").getValue().toString());
		            companyDTO.setCompanyCrtQualifierName(binder.getField("companyIdentifier").getValue().toString());
		            qualifierTable.addItem(binder.getItemDataSource());
		            companyQualifier.removeAllItems();
		            companyQualifier.addAll(companylogic.saveCrtQualifer(binder));
		            binder.discard();
		            binder.setItemDataSource(new BeanItem<>(new CompanyCrtIdentifierDTO()));
		            btnSave.setCaption(ConstantsUtils.SAVE);
		               LOGGER.debug("Ending IdenQualifierEditList  SAVE  method ");
		        } catch(CommitException ex){
                            LOGGER.error(ex);
                        } catch (PortalException ex) {
                            LOGGER.error(ex);
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {
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
                           final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {
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
        LOGGER.debug("Ending IdenQualifierEditList saveButton");
        return btnSave;
    }

    /**
     * logic for delete button.
     *
     * @return the button
     */
    public Button deleteButton() {
        
        final Button buttonSave = new Button(ConstantsUtils.DELETE);
        buttonSave.setWidth(ConstantsUtils.BTN_WIDTH);
        buttonSave.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */   @SuppressWarnings("PMD")      	
            public void buttonClick(final ClickEvent event) {
            	try{
                 LOGGER.debug("Entering inside IdenQualifierEditList  DELETE  method ");
                    binder.commit();  
                companyQualifier.removeAllItems();
                companyQualifier.addAll(companylogic.deleteCrtQualifer(Integer.valueOf(binder.getField("companyCrtQualifierId").getValue().toString())));
                binder.discard();
                binder.setItemDataSource(new BeanItem<>(new CompanyCrtIdentifierDTO()));
                 LOGGER.debug("Ending IdenQualifierEditList  DELETE  method ");
            	} catch(CommitException ex){
                    LOGGER.error(ex);
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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
                } catch (NumberFormatException e) {
                    LOGGER.error(e);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {
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
        return buttonSave;
    }

    /**
     * logic for Reset button.
     *
     * @return the button
     * @throws StplR2Exception the stpl r2 exception
     */
    public Button resetButton() {
        final Button btnReset = new Button(ConstantsUtils.RESET);
        btnReset.setWidth(ConstantsUtils.BTN_WIDTH);
        btnReset.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */   @SuppressWarnings("PMD")      	
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering inside IdenQualifierEditList RESET  method ");
				binder.discard();
				binder.setItemDataSource(new BeanItem<>(new CompanyCrtIdentifierDTO()));
				btnSave.setCaption(ConstantsUtils.SAVE);
				 LOGGER.debug("Ending IdenQualifierEditList RESET  method ");


            }
        });
        return btnReset;
    }
}