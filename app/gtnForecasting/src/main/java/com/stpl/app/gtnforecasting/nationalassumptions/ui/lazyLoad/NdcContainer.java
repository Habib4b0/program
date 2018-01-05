/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.ui.lazyLoad;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.NationalAssumptionLogic;
import com.stpl.ifs.util.HelperDTO;
import java.util.Collections;
import java.util.List;
import org.asi.ui.addons.lazycontainer.DAO;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.asi.ui.addons.lazycontainer.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinodhini
 */
public class NdcContainer implements DAO<HelperDTO> {

    private HelperDTO brand;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NdcContainer.class);
    private boolean itemFlag;
     private HelperDTO medicaidNdc9;
    
    /**
     * NdcContainer.
     */
    public NdcContainer() {
    
    }

    /**
     * NdcContainer.
     * @param brand
     * @param itemFlag
     */
    public NdcContainer(final HelperDTO brand,boolean itemFlag) {
        this.brand = brand;
         this.itemFlag=itemFlag;
    }

    public NdcContainer(final HelperDTO brand,boolean itemFlag, HelperDTO medicaidNdc9) {
        this.brand = brand;
         this.itemFlag=itemFlag;
         this.medicaidNdc9=medicaidNdc9;
    }
    @Override
    public int count(final SearchCriteria searchCriteria) {
        try {
            LOGGER.debug("Entering NdcContainer Count method :");
            return NationalAssumptionLogic.getLazyNdcCount(searchCriteria.getFilter(), brand,itemFlag) + 1;
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return 0;
    }

    /**
     * Method used for get the results.
     * @param searchCriteria
     * @param startIndex
     * @param offset
     * @param list
     * @return 
     */
    @Override
    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering NdcContainer find method :");
            return NationalAssumptionLogic.getLazyNdcResults(startIndex, startIndex + offset, searchCriteria.getFilter(), brand,itemFlag,medicaidNdc9);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return Collections.emptyList();
    }

}
