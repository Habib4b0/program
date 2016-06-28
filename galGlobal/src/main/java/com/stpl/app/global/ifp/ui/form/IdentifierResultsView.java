package com.stpl.app.global.ifp.ui.form;

import org.jboss.logging.Logger;

import com.stpl.app.global.item.dto.ItemIrtIdentifierDTO;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class IdentifierResultsView.
 */
public class IdentifierResultsView extends CustomComponent {

    /**
     * The space.
     */
    private  final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    /**
     * The error msg.
     */
    private  final ErrorLabel errorMsg = new ErrorLabel();

    /**
     * The identifier form bean.
     */
    private  final ItemIrtIdentifierDTO identifierFormBean = new ItemIrtIdentifierDTO();
    /**
     * The binder.
     */
    private  final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
            new BeanItem<ItemIrtIdentifierDTO>(identifierFormBean));
    
    /** Constants LOGGER. */
     private static final Logger LOGGER = Logger.getLogger(IdentifierResults.class);
    /**
     * The identifier results bean.
     */
    private BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean = new BeanItemContainer<ItemIrtIdentifierDTO>(
            ItemIrtIdentifierDTO.class);

	/**
     * The table.
     */
    private   final Table table = new Table();

    
    /**
     * Instantiates a new identifier results view.
     *
     * @param identifierResultsBean the identifier results bean
     */
    public IdentifierResultsView(
            final BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean) {
        super();
    	this.identifierResultsBean = identifierResultsBean;
        init();
    }
    
    /**
     * Initial method when the Constructor get calls.
     */
    private void init() {
    	LOGGER.info("Entering init method");
        space.setHeight(ConstantsUtils.HEIGHT);
        binder.bindMemberFields(this);
        binder.setReadOnly(true);
        addToContent();
  

    }

    /**
     * Adds the to content.
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
        setCompositionRoot(content);
         
    }

    /**
     * Adds the table and its properties.
     *
     * @return the table
     * @throws Exception the exception
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
             */ @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                //empty method
            }
        });

        return table;
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
	    * @param identifierResultsBean the identifierResultsBean to set
	    */
   	public void setIdentifierResultsBean(
   			final BeanItemContainer<ItemIrtIdentifierDTO> identifierResultsBean) {
   		this.identifierResultsBean = identifierResultsBean;
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
}
