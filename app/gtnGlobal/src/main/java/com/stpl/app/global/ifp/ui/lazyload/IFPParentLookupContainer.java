/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.ifp.ui.lazyload;

import com.stpl.app.global.ifp.dto.IFPContainer;
import com.stpl.app.global.ifp.dto.ItemFamilyplanSearchDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author shrihariharan
 */
public class IFPParentLookupContainer implements BeanDAO<ItemFamilyplanSearchDTO> {

	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(IFPContainer.class);

	/** The ifp logic. */
	private final IFPLogic ifpLogic = new IFPLogic();

	/** The binder. */
	private ErrorfulFieldGroup binder;

	/** The binder flag. */
	private boolean binderFlag;

	/**
	 * Instantiates a new ifp dao.
	 */
	public IFPParentLookupContainer() {
		// empty method
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
	 * Gets the binder flag.
	 *
	 * @return the binder flag
	 */
	public boolean isBinderFlag() {
		return binderFlag;
	}

	/**
	 * Sets the binder.
	 *
	 * @param binder
	 *            the new binder
	 */
	public void setBinder(final ErrorfulFieldGroup binder) {
		this.binder = binder;
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
	 * Instantiates a new ifp dao.
	 *
	 * @param binder
	 *            the binder
	 */
	public IFPParentLookupContainer(final ErrorfulFieldGroup binder) {
		this.binder = binder;
		binderFlag = true;
	}

	/**
	 * Method to get the no of count for Searching results.
	 *
	 * @param search
	 *            the search
	 * @return int
	 */
	public int count(final BeanSearchCriteria search) {
		int count = 0;

		try {
			if (binderFlag) {
				count = (int) ifpLogic.getLookUpSearchCount(binder, search);
			} else {
				count = ifpLogic.getTotalCount();
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
		return count;

	}

	/**
	 * To find the results based on counts.
	 *
	 * @param criteria
	 *            the criteria
	 * @param startIndex
	 *            the start index
	 * @param offset
	 *            the offset
	 * @param columns
	 *            the columns
	 * @return List of results
	 */
	public List<ItemFamilyplanSearchDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {

		List<ItemFamilyplanSearchDTO> salesList = new ArrayList();
		try {

			if (binderFlag) {
				salesList = ifpLogic.lookupSearchIFP(binder, startIndex, offset, columns, criteria);
                                return salesList;
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
		return salesList;
	}

	/**
	 * @return the ifpLogic
	 */
	public IFPLogic getIfpLogic() {
		return ifpLogic;
	}

}
