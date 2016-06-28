package com.stpl.app.global.ifp.ui.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;

import com.stpl.app.global.item.dto.ItemPricingDTO;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

// TODO: Auto-generated Javadoc
/**
 * The Class PricingResults.
 */
public final class PricingResults extends CustomComponent {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(PricingResults.class);
    
    /** The space. */
    private  final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    
    /** The error msg. */
    private  final ErrorLabel errorMsg = new ErrorLabel();
    
    /** The item logic. */
    private  final ItemSearchLogic itemLogic = new ItemSearchLogic();
    
    /** The pricing table bean. */
    private  BeanItemContainer<ItemPricingDTO> pricingTableBean;
    
    /** The pricing form bean. */
    private  final ItemPricingDTO pricingFormBean = new ItemPricingDTO();
    
    /** The binder. */
    private  ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
            new BeanItem<ItemPricingDTO>(pricingFormBean));

    /** The identifier code qualifier name. */
    private  final NativeSelect identifierCodeQualifierName = new NativeSelect();
    
    /** The item price. */
    private  final TextField itemPrice = new TextField();
    
    /** The entity code. */
    private  final TextField entityCode = new TextField();
    
    /** The item uom. */
    private  final NativeSelect itemUom = new NativeSelect();
    
    /** The start date. */
    private  final PopupDateField startDate = new PopupDateField();
    
    /** The end date. */
    private  final PopupDateField endDate = new PopupDateField();
    
    /** The identifier status. */
    private  final NativeSelect identifierStatus = new NativeSelect();
    
    /**
     * Instantiates a new pricing results.
     *
     * @param pricingTableBean the pricing table bean
     */
    public PricingResults(final BeanItemContainer<ItemPricingDTO> pricingTableBean) throws PortalException, Exception {
        super();
    	this.pricingTableBean = pricingTableBean;
        init();
    }

    /**
     * Initial method when the Constructor get calls.
     */
    private void init() throws PortalException, Exception {
        space.setHeight("10px");
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        addToContent();
        configureFields();
    }

    /**
     * Method to add All the components in form.
     *
     * @throws Exception the exception
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
     * Adding all the fields in the grid.
     *
     * @return the grid layout
     * @throws Exception the exception
     */
    private GridLayout addToGrid() {
        final GridLayout grid = new GridLayout(6, 3);

        grid.setSpacing(true);

        grid.addComponent(new Label("Pricing Qualifier"));
        grid.addComponent(identifierCodeQualifierName);
        grid.addComponent(new Label("Item Price"));
        grid.addComponent(itemPrice);
        grid.addComponent(new Label("Entity Code"));
        grid.addComponent(entityCode);
        grid.addComponent(new Label("Item Uom"));
        grid.addComponent(itemUom);
        grid.addComponent(new Label("Start Date"));
        grid.addComponent(startDate);
        grid.addComponent(new Label("End Date"));
        grid.addComponent(endDate);
        grid.addComponent(new Label("Pricing Status"));
        grid.addComponent(identifierStatus);

        return grid;
    }

    /**
     * Adding pricing table.
     *
     * @return the table
     * @throws Exception the exception
     */
    private Table addToTable() {

        final Table pricingTable = new Table();
  
        pricingTable.setContainerDataSource(pricingTableBean);
        pricingTable.setVisibleColumns(UIUtils.PRICING_FORM_COL_ORDER);
        pricingTable.setColumnHeaders(UIUtils.PRICING_FORM_COL_ORDER_HEADER);
        pricingTable.setPageLength(7);
        pricingTable.setImmediate(true);
        pricingTable.setSelectable(true);
        pricingTable.setSizeFull();
        pricingTable.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */ @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                //empty method
            }
        });

        pricingTable.addItemClickListener(new ItemClickListener() {
             /**
             * Called when a item has been clicked.
             */
            public void itemClick(final ItemClickEvent event) {
                //empty method
            }
        });

       
        return pricingTable;
    }

    /**
     * Method to add Populate button and its properties.
     *
     * @return the button
     */
    public Button populateButton() {
        final Button btnPopulate = new Button(ConstantsUtils.POPULATE);
   
        btnPopulate.setWidth("85");
        btnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */ @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                //empty method
            }
        });
        btnPopulate.addClickListener(new ClickListener() {
            /**
             * Called when a button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering PricingResults button click");
                try {
                    binder.getFields();
                    binder.commit();

                  

                      final  ItemPricingDTO identForm = new ItemPricingDTO();
                        identForm.setIdentifierCodeQualifierName(String
                                .valueOf(binder.getField(
                                "identifierCodeQualifierName")
                                .getValue()));
                        identForm.setEntityCode(String.valueOf(binder.getField(
                                ConstantsUtils.ENTITY_CODE).getValue()));
                        identForm.setPricingCodeStatus((HelperDTO)binder.getField("pricingCodeStatus").getValue());
                            identForm.setStartDate(convertDate((Date) binder.getField(ConstantsUtils.START_DATE).getValue()));
                            identForm.setEndDate(convertDate((Date) binder.getField(ConstantsUtils.END_DATE).getValue()));
                        pricingTableBean.addBean(identForm);
                        binder.discard();
                        binder = new ErrorfulFieldGroup(
                                new BeanItem<ItemPricingDTO>(
                                new ItemPricingDTO()));

                   
                   
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
                LOGGER.info("Ending PricingResults button click");
            }
        });
         
        return btnPopulate;
    }

    /**
     * Convert date.
     *
     * @param start the start
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertDate(final Date start) throws ParseException {
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        return inputFormat.parse(inputFormat.format(start));
    }

    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void configureFields() throws SystemException, PortalException, Exception {
       
            startDate.setDescription(ConstantsUtils.DATE_DES);
            endDate.setDescription(ConstantsUtils.DATE_DES);
        final CommonUtils commonUtils = new CommonUtils();
        LOGGER.info("getNativeList method to retrive to Load identifierCodeQualifierName Nativeselect value ---- ");
        commonUtils.getNativeSelect(identifierCodeQualifierName,
                itemLogic.getItemQualifier());
        identifierCodeQualifierName.setImmediate(true);
        identifierCodeQualifierName.setNullSelectionAllowed(true);
        identifierCodeQualifierName.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        identifierCodeQualifierName.setDescription((String) identifierCodeQualifierName.getValue());
        identifierCodeQualifierName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */ @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                identifierCodeQualifierName.setDescription((String) identifierCodeQualifierName.getValue());
            }
        });

        commonUtils.getStatusSelect(identifierStatus);



        startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);

        new CommonUtils().getNativeSelect(itemUom, itemLogic.getItemType(UIUtils.ITEM_UOM));
        itemUom.setImmediate(true);
        itemUom.setNullSelectionAllowed(true);
        itemUom.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        itemUom.setRequired(true);
        itemUom.setRequiredError("ItemUom should be selected on Pricing tab");
        itemUom.setDescription((String) itemUom.getValue());
        itemUom.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                itemUom.setDescription((String) itemUom.getValue());
            }
        });

        
    }

	/**
	 * Gets the pricing table bean.
	 *
	 * @return the pricingTableBean
	 */
	public BeanItemContainer<ItemPricingDTO> getPricingTableBean() {
		return pricingTableBean;
	}

	/**
	 * Sets the pricing table bean.
	 *
	 * @param pricingTableBean the pricingTableBean to set
	 */
	public void setPricingTableBean(
			final BeanItemContainer<ItemPricingDTO> pricingTableBean) {
		this.pricingTableBean = pricingTableBean;
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
	 * Sets the binder.
	 *
	 * @param binder the new binder
	 */
	public void setBinder(final ErrorfulFieldGroup binder) {
		this.binder = binder;
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
	 * Gets the pricing form bean.
	 *
	 * @return the pricing form bean
	 */
	public ItemPricingDTO getPricingFormBean() {
		return pricingFormBean;
	}

	/**
	 * Gets the identifier code qualifier name.
	 *
	 * @return the identifier code qualifier name
	 */
	public NativeSelect getIdentifierCodeQualifierName() {
		return identifierCodeQualifierName;
	}

	/**
	 * Gets the item price.
	 *
	 * @return the item price
	 */
	public TextField getItemPrice() {
		return itemPrice;
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
	 * Gets the item uom.
	 *
	 * @return the item uom
	 */
	public NativeSelect getItemUom() {
		return itemUom;
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
