/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.ui.lazyload;

import com.stpl.app.adminconsole.relationshipbuilder.logic.RelationBuilderLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;


/**
 * The Class HierarchyNameContainer.
 *
 * @author Karthikeyans
 */
public class HierarchyNameContainer implements DAO<HelperDTO> {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HierarchyNameContainer.class);

    /**
     * The manufacture id.
     */
    private HelperDTO manufactureId;

    /**
     * Gets the manufacture id.
     *
     * @return the manufacture id
     */
    public HelperDTO getManufactureId() {
        return manufactureId;
    }

    /**
     * Sets the manufacture id.
     *
     * @param manufactureId the new manufacture id
     */
    public void setManufactureId(final HelperDTO manufactureId) {
        this.manufactureId = manufactureId;
    }

    /**
     * Method used for get Count.
     *
     * @param manufactureId the manufacture id
     */
    public HierarchyNameContainer(final HelperDTO manufactureId) {
        this.manufactureId = manufactureId;
    }

    /**
     * Count.
     *
     * @param searchCriteria the search criteria
     * @return the int
     */
    public int count(final SearchCriteria searchCriteria) {
        try {
            LOGGER.debug("Entering HierarchyNameContainer Count method :");
            return RelationBuilderLogic.getLazyHierarchyNameCount(searchCriteria.getFilter());
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {

            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return 0;
    }

    /**
     * Method used for get the results.
     *
     * @param searchCriteria the search criteria
     * @param startIndex the start index
     * @param offset the offset
     * @param list the list
     * @return the list
     */
    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering HierarchyNameContainer find method :");
            return RelationBuilderLogic.getLazyHierarchyNameResults(startIndex, startIndex + offset, searchCriteria.getFilter());
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return null;
    }
}
