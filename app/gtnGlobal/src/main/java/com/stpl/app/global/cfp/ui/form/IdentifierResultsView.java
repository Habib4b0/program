package com.stpl.app.global.cfp.ui.form;


import org.jboss.logging.Logger;

import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class IdentifierResultsView.
 */
public class IdentifierResultsView extends CustomComponent {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(IdentifierResultsView.class);
    
    /** The space. */
    private final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    
    /** The identifier form bean. */
    private final CompanyCrtIdentifierDTO identifierFormBean = new CompanyCrtIdentifierDTO();
    
    /** The binder. */
    private final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
            new BeanItem<>(identifierFormBean));
    
    /** The identifier results bean. */
    private BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean = new BeanItemContainer<>(
            CompanyCrtIdentifierDTO.class);

    /**
	 * @return the identifierResultsBean
	 */
	public BeanItemContainer<CompanyCrtIdentifierDTO> getIdentifierResultsBean() {
		return identifierResultsBean;
	}

	/**
	 * @param identifierResultsBean the identifierResultsBean to set
	 */
	public void setIdentifierResultsBean(
			final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean) {
		this.identifierResultsBean = identifierResultsBean;
	}

	/**
	 * @return the space
	 */
	public Label getSpace() {
		return space;
	}

	/**
	 * @return the identifierFormBean
	 */
	public CompanyCrtIdentifierDTO getIdentifierFormBean() {
		return identifierFormBean;
	}

	/**
	 * @return the binder
	 */
	public ErrorfulFieldGroup getBinder() {
		return binder;
	}

	/**
     * The Constructor.
     *
     * @param identifierResultsBean the identifier results bean
     */
    public IdentifierResultsView(
            final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean) {
    	super();
        this.identifierResultsBean = identifierResultsBean;
        init();

    }

    /**
     * Initial method when the Constructor get calls
     */
    private void init() {
        LOGGER.debug("Entering IdentifierResultsView init");
        space.setHeight(ConstantsUtils.HEIGHT);
        binder.bindMemberFields(this);
        addToContent();
        LOGGER.debug("Ending IdentifierResultsView init");

    }

    /**
     * Add content to layout
     */
    private void addToContent() {
        LOGGER.debug("Entering IdentifierResultsView addToContent");
        final VerticalLayout content = new VerticalLayout();
		content.addComponentAsFirst(space);
		content.addComponent(space);
		content.addComponent(space);
		content.addComponent(space);
		content.addComponent(space);
		content.addComponent(addToTable());
		content.addComponent(space);
		setCompositionRoot(content);
        LOGGER.debug("Ending IdentifierResultsView addToContent");
    }

    /**
     * returns the configured table
     *
     * @return the table
     */
    private Table addToTable() {
        LOGGER.debug("Entering IdentifierResultsView addToTable");
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
		        //called when a item is clicked.
		    }
		});
        LOGGER.debug("Ending IdentifierResultsView addToTable");
        return table;
    }   
}
