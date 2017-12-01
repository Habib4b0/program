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
import org.asi.ui.addons.lazycontainer.DAO;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.asi.ui.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author Vinodhini
 */
public class BrandContainer implements DAO<HelperDTO> {

    private HelperDTO brand;
    private HelperDTO preBrandValue;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(BrandContainer.class);

    /**
     * Method used for get Count.
     */
    public BrandContainer() {
      
    }

    /**
     * Method used for get Count.
     */
    public BrandContainer(final HelperDTO brand,final HelperDTO preBrandValue) {
        this.brand = brand;
        this.preBrandValue=preBrandValue;
    }

    public int count(final SearchCriteria searchCriteria) {
        try {
            LOGGER.debug("Entering BrandContainer Count method :");
            return NationalAssumptionLogic.getLazyBrandCount(searchCriteria.getFilter(), brand) + 1;
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
            LOGGER.debug("Entering BrandContainer find method :");
            return NationalAssumptionLogic.getLazyBrandResults(startIndex, startIndex + offset, searchCriteria.getFilter(), brand,preBrandValue);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return Collections.emptyList();
    }

}
