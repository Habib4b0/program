package com.stpl.app.global.ifp.ui.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;

import com.stpl.app.global.item.dto.ItemPricingDTO;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;


// TODO: Auto-generated Javadoc
/**
 * The Class PricingResultsView.
 */
public class PricingResultsView extends CustomComponent {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(PricingResultsView.class);
    
    /** The space. */
    private  final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    
    /** The error msg. */
    private  final ErrorLabel errorMsg = new ErrorLabel();

    
    /** The pricing table bean. */
    private   BeanItemContainer<ItemPricingDTO> pricingTableBean;
    
    /** The pricing form bean. */
    private  final ItemPricingDTO pricingFormBean = new ItemPricingDTO();
    
    /** The binder. */
    private  ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(pricingFormBean));
    
    private final UIUtils uiUtils = UIUtils.getInstance();
    
     /**
     * Instantiates a new pricing results view.
     *
     * @param pricingTableBean the pricing table bean
     */
    public PricingResultsView(final BeanItemContainer<ItemPricingDTO> pricingTableBean) {
        super();
    	this.pricingTableBean = pricingTableBean;
        init();
    }

   /**
    * Initial method when the Constructor get calls.
    */
    private void init() {
        space.setHeight(ConstantsUtils.HEIGHT);
        binder.bindMemberFields(this);
        binder.setReadOnly(true);
        addToContent();     
    }

    /**
     * Methods to add All the contents in form.
     *
     * @throws Exception the exception
     */
    private void addToContent() {
            final VerticalLayout content = new VerticalLayout();
        content.addComponentAsFirst(space);
        content.addComponent(errorMsg);
        content.addComponent(space);
        content.addComponent(space);
        content.addComponent(space);
        content.addComponent(space);
        content.addComponent(addToTable());
        content.addComponent(space);
      
        setCompositionRoot(content);
        
    }

    /**
     *  
     * Adding the pricing table.
     *
     * @return the table
     * @throws Exception the exception
     */
    private Table addToTable() {


        final Table pricingTable = new Table();
      
        pricingTable.setContainerDataSource(pricingTableBean);
        pricingTable.setVisibleColumns(uiUtils.pricingFormColOrder);
        pricingTable.setColumnHeaders(uiUtils.pricingFormColOrderHeader);
        pricingTable.setPageLength(NumericConstants.SEVEN);
        pricingTable.setImmediate(true);
        pricingTable.setSelectable(true);
        pricingTable.setSizeFull();


        pricingTable.addItemClickListener(new ItemClickListener() {
            /**
             * Called when a item has been clicked.
             */ @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                //empty method
            }
        });

        return pricingTable;
    }

    /**
     * Method to load Populate btn and its properties.
     *
     * @return the button
     * @throws Exception the exception
     */
    public Button populateButton() {
        final Button btnPopulate = new Button(ConstantsUtils.POPULATE);
     
        btnPopulate.setWidth("60");
        btnPopulate.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */ @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering PricingResultsView button click");
               try{
                    binder.getFields();
                    binder.commit();

                 

                        final ItemPricingDTO identForm = new ItemPricingDTO();
                        identForm.setIdentifierCodeQualifierName(String.valueOf(binder
                                .getField("identifierCodeQualifierName").getValue()));
                        identForm.setEntityCode(String.valueOf(binder.getField(
                                ConstantsUtils.ENTITY_CODE).getValue()));
                        identForm.setPricingCodeStatus((HelperDTO)binder.getField("pricingCodeStatus").getValue());
                    

                            identForm.setStartDate(convertDate((Date) binder.getField(ConstantsUtils.START_DATE)
                                    .getValue()));


                            identForm.setEndDate(convertDate((Date) binder.getField(ConstantsUtils.END_DATE)
                                    .getValue()));


                      

                        pricingTableBean.addBean(identForm);
                        binder.discard();
                        binder = new ErrorfulFieldGroup(
                                new BeanItem<>(new ItemPricingDTO()));

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
                LOGGER.debug("Ending PricingResultsView button click");
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
			final	BeanItemContainer<ItemPricingDTO> pricingTableBean) {
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
	 * Gets the pricing form bean.
	 *
	 * @return the pricing form bean
	 */
	public ItemPricingDTO getPricingFormBean() {
		return pricingFormBean;
	}

}
