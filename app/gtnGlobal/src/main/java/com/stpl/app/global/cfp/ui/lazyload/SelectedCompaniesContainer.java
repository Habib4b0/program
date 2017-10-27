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
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Collections;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author KarthikeyanS
 */
public class SelectedCompaniesContainer implements BeanDAO<CompanyMasterDTO> {

    public boolean editListFlag;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SelectedCompaniesContainer.class);
    private CommonLazyUtilDTO dto;

    /**
     * Method used for get Count.
     */
    public SelectedCompaniesContainer(CommonLazyUtilDTO dto) {
        this.dto = dto;
    }

    public int count(final BeanSearchCriteria searchCriteria) {
        try {
              LOGGER.debug("Entering SelCompanyAddition count method :");
            if (dto == null) {
                return 0;
            } else {
                if (dto.getSearchFlag()) {
                   return CFPSearchLogic.getLazySelectedCompaniesCount(dto, searchCriteria);
                }
            }
        } catch (Exception ex) {
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
           LOGGER.debug("Ends SelCompanyAddition count method :");
        return 0;
    }

    /**
     * Method used for get the results.
     */
    public List<CompanyMasterDTO> find(final BeanSearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering SelCompanyAddition find method :");
            if (dto == null) {
                    return Collections.emptyList();
            } else {
                if (dto.getSearchFlag()) {
                return CFPSearchLogic.getSelectedCompaniesDeatils(dto, startIndex,offset,  list,searchCriteria,"search");
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return Collections.emptyList();
    }
}
