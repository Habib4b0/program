/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.cfp.ui.lazyload;

import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.util.CommonLazyUtilDTO;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author karthikeyans
 */
public class AvailableCompaniesContainer implements BeanDAO<CompanyMasterDTO> {

    public boolean editListFlag;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AvailableCompaniesContainer.class);

    final ExtFilterTable  availableTable;

    /**
     * Method used for get Count.
     */
    public AvailableCompaniesContainer(final ExtFilterTable  availableTable) {
     this.availableTable=availableTable;
         
    }

    public int count(final BeanSearchCriteria searchCriteria) {
        LOGGER.debug("Entering inside available count  Method ");
        try {
            if (availableTable.getData() == null) {
                return 0;
            } else {
                CommonLazyUtilDTO dto = (CommonLazyUtilDTO) availableTable.getData();
                if (dto.getSearchFields() != null && dto.getSearchValue() != null) {
                    int count=CFPSearchLogic.getLazyAvailableCompaniesCount(dto,searchCriteria);
                    return count;
                }
            }
            LOGGER.debug("Ending available count  Method ");
        } catch (PortalException ex) {
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
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        }
        
        return 0;
    }

    /**
     * Method used for get the results.
     */
    public List<CompanyMasterDTO> find(final BeanSearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering inside available find  Method ");
            if (availableTable.getData() == null) {
                return Collections.emptyList();
            } else {
                CommonLazyUtilDTO dto = (CommonLazyUtilDTO) availableTable.getData();
                if (dto.getSearchFields() != null && dto.getSearchValue() != null) {
                return CFPSearchLogic.getLazyAvailableCompaniesResults(dto,startIndex, offset, list,searchCriteria);
                }
            }
            LOGGER.debug("Ending available find  Method ");

        } catch (PortalException ex) {
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
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        }
        catch (Exception ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        }
        
        return Collections.emptyList();
    }
}
