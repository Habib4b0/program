package com.stpl.app.global.ifp.ui.form;

import org.jboss.logging.Logger;
import com.stpl.app.global.item.dto.ItemIrtIdentifierDTO;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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

// TODO: Auto-generated Javadoc
/**
 * The Class IdenQualifierEditList.
 */
public final class IdenQualifierEditList extends Window implements View {

    /**
     * The logger.
     */
    private static final  Logger LOGGER = Logger.getLogger(IdenQualifierEditList.class);
    /**
     * The space.
     */
    private  final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    /**
     * The itemlogic.
     */
    private  final ItemSearchLogic itemlogic = new ItemSearchLogic();
    /**
     * The qualifier table.
     */
    private  final Table qualifierTable = new Table();
    /**
     * The item qualifier.
     */
    private   final BeanItemContainer<ItemIrtIdentifierDTO> itemQualifier = new BeanItemContainer<>(
            ItemIrtIdentifierDTO.class);
    /**
     * The identifier dto.
     */
    private  final ItemIrtIdentifierDTO identifierDTO = new ItemIrtIdentifierDTO();

    /**
     * The item identifier.
     */
    private   final TextField itemIdentifier = new TextField();
    /**
     * The item irt qualifier name.
     */
    private  final TextField itemIrtQualifierName = new TextField();
    /**
     * The item irt qualifier object.
     */
    private  final TextField itemIrtQualifierId = new TextField();

    /**
     * The created date.
     */
    private  final PopupDateField createdDate = new PopupDateField(ConstantsUtils.CREATED_DATE);
  
    /**
     * The modified date.
     */
    private  final PopupDateField modifiedDate = new PopupDateField(ConstantsUtils.MODIFIED_DATE);
    /**
     * The btn save.
     */
    private  final Button btnSave = new Button(ConstantsUtils.SAVE);
    /**
     * The binder.
     */
    private  final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
            new BeanItem<>(identifierDTO));
   
    private ItemIrtIdentifierDTO selectedItemiden = new ItemIrtIdentifierDTO();
    
    private final UIUtils uIUtils = UIUtils.getInstance();
    /**
     * Instantiates a new iden qualifier edit list.
     */
    public IdenQualifierEditList() throws SystemException, PortalException {
        super("Qualifier List");
        itemQualifier.addAll(itemlogic.getItemQualifierForEditList());
        binder.bindMemberFields(this);
        init();
    }

    /**
     * Initial method when the Constructor get calls.
     *
     * @throws Exception the exception
     */
    public  void init() {
        
        center();
        setClosable(true);
        setModal(true);
        setSizeFull();
        setContent(addToContent());
        configureFields();
         
    }

    /**
     * Configure all the fields.
     */
    public void configureFields() {
        
            createdDate.setDescription(ConstantsUtils.DATE_DES);
            modifiedDate.setDescription(ConstantsUtils.DATE_DES);
        itemIdentifier.setImmediate(true);
        itemIrtQualifierName.setImmediate(true);
        itemIrtQualifierId.setImmediate(true);
         
    }

    /**
     * Method to Adds all the contents.
     *
     * @return the vertical layout
     * @throws Exception the exception
     */
    public VerticalLayout addToContent() {
       
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
         
        return content;
    }

    /**
     * Gets the buttons.
     *
     * @return the buttons
     * @throws Exception the exception
     */
    public GridLayout getButtons() {
        final GridLayout layout = new GridLayout(NumericConstants.THREE, 1);
    
        layout.setSpacing(true);
        layout.setWidth(null);
        layout.addComponent(saveButton());
        layout.addComponent(deleteButton());
        layout.addComponent(resetButton());
         
        return layout;
    }

    /**
     *  Method to Adds the to table.
     *
     * @return the table
     * @throws Exception the exception
     */
    private Table addToTable() {
    
        qualifierTable.setContainerDataSource(itemQualifier);
        qualifierTable.setVisibleColumns(uIUtils.qualifierItem);
        qualifierTable.setPageLength(NumericConstants.SEVEN);
        qualifierTable.setImmediate(true);
        qualifierTable.setSelectable(true);
        qualifierTable.setSizeFull();


        qualifierTable.addItemClickListener(new ItemClickListener() {
            /**
             * Called when a item has been clicked.
             */ @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                try {
                    binder.commit();
                selectedItemiden = itemQualifier.getItem(
                        event.getItemId()).getBean();
                binder.setItemDataSource(new BeanItem<>(selectedItemiden));
                btnSave.setCaption(ConstantsUtils.UPDATE);
                 } catch(FieldGroup.CommitException ex){
                            LOGGER.error(ex);
                 } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1014), new MessageBoxListener() {  
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

        return qualifierTable;
    }

    /**
     * Method to Adds the form contents.
     *
     * @return the grid layout
     * @throws Exception the exception
     */
    public GridLayout addToForm() {
        final GridLayout grid = new GridLayout(NumericConstants.FOUR, 1);
        
        grid.setWidth(null);
        grid.setSpacing(true);
        grid.addComponent(new Label("Item Identifier"));
        grid.addComponent(itemIdentifier);
        grid.addComponent(new Label("Item Qualifier"));
        grid.addComponent(itemIrtQualifierName);
          
        return grid;

    }

    /**
     * Method to add Save button in the layout and its properties.
     *
     * @return the button
     * @throws Exception the exception
     */
    public Button saveButton() {
        btnSave.setWidth("60");
        btnSave.setImmediate(true);

        btnSave.addClickListener(new ClickListener() {
            /**
             * Called when a  Button has been clicked.
             */ @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering IdenQualifierEditList saveButton");
                qualifierTable.commit();
                try {
                    binder.commit();

                    final ItemIrtIdentifierDTO iden = new ItemIrtIdentifierDTO();
                    
                    if (!ConstantsUtils.SAVE.equals(btnSave.getCaption()) && itemlogic.checkDifferentQualifier(selectedItemiden.getItemIrtQualifierId(), String.valueOf(binder.getField("identifierCodeQualifier").getValue()),false)) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Identifier Code Qualifier cannot be edited.", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {

                                // Do Nothingreturn
                                return;
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                    }

                    iden.setItemIrtQualifierId(Integer.valueOf(binder.getField("itemIrtQualifierId").getValue().toString()));
                    iden.setItemIrtQualifierName(binder.getField("itemIrtQualifierName").getValue().toString());
                    iden.setItemIrtQualifierName(binder.getField("itemIdentifier").getValue().toString());
                    int val =0;
                    if(ConstantsUtils.SAVE.equals(btnSave.getCaption())){
                        val = selectedItemiden.getIrtIdentifierSystemId();
                    }
                    qualifierTable.addItem(binder.getItemDataSource());
                    itemQualifier.removeAllItems();
                    itemQualifier.addAll(itemlogic.saveIrtQualifer(binder,val));
                    binder.discard();
                    binder.setItemDataSource(new BeanItem<>(new ItemIrtIdentifierDTO()));
                    btnSave.setCaption(ConstantsUtils.SAVE);
                 } catch (FieldGroup.CommitException sysException) {
                        LOGGER.error(sysException);
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
                } catch (Exception exception) {
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
                    LOGGER.error(exception);
                }
                LOGGER.debug("Ending IdenQualifierEditList saveButton");
            }
        });
        return btnSave;
    }

    /**
     *Method to add Delete button in the layout and its properties.
     *
     * @return the button
     */
    public Button deleteButton() {

        final Button buttonSave = new Button(ConstantsUtils.DELETE);
       
        buttonSave.setWidth("60");
        buttonSave.addClickListener(new ClickListener() {
            /**
             * Called when a {@link Button} has been clicked.
             */ @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering IdenQualifierEditList delete operation");
                try {
                    binder.commit();
                itemQualifier.removeAllItems();
                LOGGER.debug("deleteIrtQualifer started  ");
                itemQualifier.addAll(itemlogic.deleteIrtQualifer(Integer.valueOf(binder.getField("itemIrtQualifierId").getValue().toString())));
                binder.discard();
                binder.setItemDataSource(new BeanItem<>(new ItemIrtIdentifierDTO()));
                LOGGER.debug("Ending IdenQualifierEditList delete operation");
                } catch(FieldGroup.CommitException ex){
                    LOGGER.error(ex);
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
                } catch (Exception exception) {
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
                    LOGGER.error(exception);
                }
            }
        });
          

        return buttonSave;

    }

    /**
     * Method to add Reset button in the layout and its properties.
     *
     * @return the button
     * @throws Exception the exception
     */
    public Button resetButton() {
        final Button btnReset = new Button(ConstantsUtils.RESET);
       
        btnReset.setWidth("60");
        btnReset.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */ @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering IdenQualifierEditList reset Button");
                try {
                    binder.discard();
                    binder.setItemDataSource(new BeanItem<>(new ItemIrtIdentifierDTO()));
                    btnSave.setCaption(ConstantsUtils.SAVE);
               } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1006), new MessageBoxListener() {  
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
                LOGGER.debug("Entering IdenQualifierEditList reset Button");
            }
        });
         
        return btnReset;

    }

     /**
      * Method which will get call from form while page loading.
      *
      * @param event the event
      */
    public void enter(final ViewChangeEvent event) {
        // TODO Auto-generated method stub
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
	 * Gets the itemlogic.
	 *
	 * @return the itemlogic
	 */
	public ItemSearchLogic getItemlogic() {
		return itemlogic;
	}

	/**
	 * Gets the qualifier table.
	 *
	 * @return the qualifier table
	 */
	public Table getQualifierTable() {
		return qualifierTable;
	}

	/**
	 * Gets the item qualifier.
	 *
	 * @return the item qualifier
	 */
	public BeanItemContainer<ItemIrtIdentifierDTO> getItemQualifier() {
		return itemQualifier;
	}

	/**
	 * Gets the identifier dto.
	 *
	 * @return the identifier dto
	 */
	public ItemIrtIdentifierDTO getIdentifierDTO() {
		return identifierDTO;
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
	 * Gets the item irt qualifier name.
	 *
	 * @return the item irt qualifier name
	 */
	public TextField getItemIrtQualifierName() {
		return itemIrtQualifierName;
	}

	/**
	 * Gets the item irt qualifier id.
	 *
	 * @return the item irt qualifier id
	 */
	public TextField getItemIrtQualifierId() {
		return itemIrtQualifierId;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public PopupDateField getCreatedDate() {
		return createdDate;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public PopupDateField getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Gets the btn save.
	 *
	 * @return the btn save
	 */
	public Button getBtnSave() {
		return btnSave;
	}

	/**
	 * Gets the binder.
	 *
	 * @return the binder
	 */
	public ErrorfulFieldGroup getBinder() {
		return binder;
	}
    

}
