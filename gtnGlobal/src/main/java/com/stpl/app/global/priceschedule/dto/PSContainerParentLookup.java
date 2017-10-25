/*
 * 
 */
package com.stpl.app.global.priceschedule.dto;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

// TODO: Auto-generated Javadoc
/**
 * This class is used to search Price schedule using lazy loading.
 *
 * @author manikanta
 */
public class PSContainerParentLookup implements BeanDAO<SearchPriceScheduleDTO> {

	/** DTO to hold the search params. */
	private SearchPriceScheduleDTO searchDTO;

	/** Logic variable to execute search. */
	private final static PSLogic PSLOGIC = new PSLogic();

	/** LOGGER Field. */
	private final static Logger LOGGER = Logger.getLogger(PSContainerParentLookup.class);

	/** Binder Field. */
	/** Flag to differentiate default and param search. */
	private boolean binderFlag;

	/** The binder. */
	private ErrorfulFieldGroup binder;

	/**
	 * Gets the search dto.
	 *
	 * @return the search dto
	 */
	public SearchPriceScheduleDTO getSearchDTO() {
		return searchDTO;
	}

	/**
	 * Sets the search dto.
	 *
	 * @param searchDTO
	 *            the new search dto
	 */
	public void setSearchDTO(final SearchPriceScheduleDTO searchDTO) {
		this.searchDTO = searchDTO;
	}

	/**
	 * Default Constructor.
	 */
	public PSContainerParentLookup() {
		// empty constructor
	}

	/**
	 * Constructor with search DTO.
	 *
	 * @param priceScheduleDTO
	 *            the price schedule dto
	 */
	public PSContainerParentLookup(final SearchPriceScheduleDTO priceScheduleDTO) {
		this.searchDTO = priceScheduleDTO;
	}

	/**
	 * Constructor with search binder.
	 *
	 * @param binder
	 *            the binder
	 */
	public PSContainerParentLookup(final ErrorfulFieldGroup binder) {
		this.binder = binder;
		binderFlag = true;
	}

	/**
	 * This method called when scrolling or to get latest search count.
	 *
	 * @param search
	 *            the search
	 * @return the int
	 */
	public int count(final BeanSearchCriteria search) {
		LOGGER.debug("Entering count " + search.getFilters());
		try {
			if (binderFlag) {
				LOGGER.debug("Entering getDynamicQueryCount");
				LOGGER.debug("End of count method");
				return (int) PSLOGIC.getSearchPsCountParentLookup(binder,search);
			} 
		}catch (Exception exception) {
               
			final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {     
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
		LOGGER.debug(" count method ends ");
		return 0;
	}

	/**
	 * This method called to get lazy records of specified size.
	 *
	 * @param criteria
	 *            the criteria
	 * @param startIndex
	 *            the start index
	 * @param offset
	 *            the offset
	 * @param columns
	 *            the columns
	 * @return the list
	 */
	public List<SearchPriceScheduleDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {

		try {

			if (binderFlag) {
				LOGGER.debug("Entering find");
				final List<SearchPriceScheduleDTO> salesList1 = PSLOGIC.getSearchPsListParentLookup(binder, startIndex, offset, columns,criteria);
				LOGGER.debug("return salesList1 " + ((salesList1 == null) ? salesList1 : salesList1.size()));
				return salesList1;
			}

		} catch (Exception exception) {
			final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {     
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

		return new ArrayList<SearchPriceScheduleDTO>();
	}

	/**
	 * Checks if is binder flag.
	 *
	 * @return true, if is binder flag
	 */
	public boolean isBinderFlag() {
		return binderFlag;
	}

	/**
	 * Sets the binder flag.
	 *
	 * @param binderFlag
	 *            the new binder flag
	 */
	public void setBinderFlag(final boolean binderFlag) {
		this.binderFlag = binderFlag;
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
}
