/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad;

import com.stpl.app.gtnforecasting.nationalassumptions.logic.NationalAssumptionLogic;
import com.stpl.ifs.util.HelperDTO;
import java.util.Collections;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author Nadhiya
 */
public class NdcFilterContainer implements DAO<HelperDTO>{
    private HelperDTO brandMasterSid;
    private boolean itemFlag;
    private HelperDTO therapeutic;
    private boolean isFilter;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NdcFilterContainer.class);

    /**
     * Method used for get Count.
     */
    public NdcFilterContainer() {
        //Empty Constructor.
    }

    /**
     * Method used for get Count.
     * 
     * @param brandMasterSid
     * @param itemFlag
     * @param therapeutic
     * @param isFilter 
     */
    public NdcFilterContainer(final HelperDTO brandMasterSid, boolean itemFlag, HelperDTO therapeutic,boolean isFilter) {
        this.brandMasterSid = brandMasterSid;
        this.itemFlag = itemFlag;
        this.therapeutic = therapeutic;
        this.isFilter = isFilter;
    }

    @Override
    public int count(final SearchCriteria searchCriteria) {
        try {
            LOGGER.debug("Entering Count method :");
            return NationalAssumptionLogic.getLazyNdcFilterCount(searchCriteria.getFilter(),brandMasterSid,itemFlag,therapeutic) + 1;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return 0;
    }

    /**
     * Method used for get the results.
     */
    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering find method :");
            return NationalAssumptionLogic.getLazyNdcFilterResults(startIndex, startIndex + offset, searchCriteria.getFilter(), itemFlag,brandMasterSid,therapeutic,isFilter);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return Collections.emptyList();
    }
    
}
