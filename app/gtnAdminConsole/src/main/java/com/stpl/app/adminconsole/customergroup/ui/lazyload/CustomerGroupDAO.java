/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.ui.lazyload;

import com.stpl.app.adminconsole.customergroup.dto.CustomerGroupDTO;
import com.stpl.app.adminconsole.customergroup.logic.CustomerGroupLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author mohamed
 */
public class CustomerGroupDAO implements DAO<CustomerGroupDTO> {

    String version;
    CustomFieldGroup customerGroupForm;
    int count;
    private final CustomerGroupLogic logic = new CustomerGroupLogic();
    private static final Logger LOGGER = Logger.getLogger(CustomerGroupDAO.class);

    public CustomerGroupDAO(final CustomFieldGroup customerGroupForm, final String version) {
        this.version = version;
        this.customerGroupForm = customerGroupForm;
    }

    public int count(SearchCriteria criteria) {
        try {
            if (customerGroupForm != null) {
                customerGroupForm.commit();
                count = logic.getSearchResultsCount(customerGroupForm, version);
                return count;
            }
        } catch (Exception e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            LOGGER.error(e);
        }
        return 0;
    }

    public List<CustomerGroupDTO> find(SearchCriteria criteria, int startIndex, int offset, List<OrderByColumn> columns) {
        try {
            if (customerGroupForm != null) {
                customerGroupForm.commit();
                List<CustomerGroupDTO> resultList = new ArrayList<CustomerGroupDTO>();

                return resultList;
            }
        } catch (Exception e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            LOGGER.error(e);
        }
        return new ArrayList<CustomerGroupDTO>();

    }
}
