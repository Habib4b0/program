package com.stpl.app.global.company.dto;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.global.company.logic.CompanySearchLogic;
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
 * The Class CompanyContainer.
 */
public class CompanyContainer implements BeanDAO<SearchResultsDTO> {

	/**
	 * The Constant LOGGER to track CompanyDAO.
	 */
	private static final Logger LOGGER = Logger.getLogger(CompanyContainer.class);

	/**
	 * The binder.
	 */
	private ErrorfulFieldGroup binder;

	/**
	 * The binder flag.
	 */
	private boolean binderFlag;

	/**
	 * The Constructor.
	 */
	/**
	 * The company search logic.
	 */
	private final CompanySearchLogic companySearchLogic = new CompanySearchLogic();

	/**
	 * Gets the company search logic.
	 *
	 * @return the company search logic
	 */
	public CompanySearchLogic getCompanySearchLogic() {
		return companySearchLogic;
	}

	/**
	 * The Constructor.
	 */
	public CompanyContainer() {
		// Default Constructor
	}

	/**
	 * The Constructor.
	 *
	 * @param binder
	 *            the binder
	 */
	public CompanyContainer(final ErrorfulFieldGroup binder) {
		this.binder = binder;
		binderFlag = true;
	}

	/**
	 * To get the total count of selected records.
	 *
	 * @param search
	 *            the search
	 * @return the int
	 */
	public int count(final BeanSearchCriteria search) {
            
          LOGGER.debug("Enters count() P1: SearchCriteria");
		
                  
        int count = 0;
		try {
            if (binderFlag) {
                        
                count = (int) companySearchLogic.getSearchCount(binder, search);
            } else {
                
                count = companySearchLogic.getTotalCount();
		}
                        
		} catch (Exception e)
                {
                
                LOGGER.error(e);
	}
		return count;
	}

	/**
	 * Retrieve the records based on start and end index.
	 *
	 * @param criteria
	 *            the criteria
	 * @param startIndex
	 *            the start index
	 * @param offset
	 *            the offset
	 * @param columns
	 *            the columns
	 * @return the list< search company form>
	 */
	public List<SearchResultsDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
		if (binderFlag) {
			try {
				return companySearchLogic.searchCompany(binder, startIndex, startIndex + offset, columns, criteria);
			} catch (Exception ex) {
                                LOGGER.error(ex);
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
			}
		}
		
		return new ArrayList<>();
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
	 * @param binder
	 *            the binder
	 */
	public void setBinder(final ErrorfulFieldGroup binder) {
		this.binder = binder;
	}

	/**
	 * Checks if is binder flag.
	 *
	 * @return true, if checks if is binder flag
	 */
	public boolean isBinderFlag() {
		return binderFlag;
	}

	/**
	 * Sets the binder flag.
	 *
	 * @param binderFlag
	 *            the binder flag
	 */
	public void setBinderFlag(final boolean binderFlag) {
		this.binderFlag = binderFlag;
	}

}
