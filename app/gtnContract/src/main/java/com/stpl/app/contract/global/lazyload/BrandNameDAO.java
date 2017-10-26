package com.stpl.app.contract.global.lazyload;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import org.jboss.logging.Logger;

public class BrandNameDAO implements DAO<HelperDTO> {
        
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(BrandNameDAO.class);
	IfpLogic ifpLogic = new IfpLogic();
	
	@Override
	public int count(SearchCriteria arg0) {
		try {
			return ifpLogic.getLazyBrandNameCount(arg0.getFilter());
		} catch (PortalException e) {
			LOGGER.error(e);
		} catch (SystemException e) {
			LOGGER.error(e);
		}
		return 0;
	}

	@Override
	public List<HelperDTO> find(SearchCriteria arg0, int start, int offset,
			List<OrderByColumn> arg3) {
		List<HelperDTO> list = new ArrayList<HelperDTO>();
		try {
			return ifpLogic.getLazyBrandName(arg0.getFilter(),start,start+offset);
		} catch (PortalException e) {
			LOGGER.error(e);
		} catch (SystemException e) {
			LOGGER.error(e);
		}
		return list;
	}

}
