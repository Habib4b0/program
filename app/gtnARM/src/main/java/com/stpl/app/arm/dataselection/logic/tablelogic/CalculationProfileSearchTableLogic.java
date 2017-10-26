/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.logic.tablelogic;

import com.stpl.app.arm.dataselection.dto.CalculationProfileDTO;
import com.stpl.app.arm.dataselection.logic.CalculationProfileLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author
 */
public class CalculationProfileSearchTableLogic extends PageTableLogic {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(CalculationProfileSearchTableLogic.class);

    CalculationProfileLogic logic = new CalculationProfileLogic();
    boolean isGenerate = false;
    String profileName = StringUtils.EMPTY, profileDesc = StringUtils.EMPTY;
    String screenName = StringUtils.EMPTY;
    boolean isResultsEmpty;

    @Override
    public int getCount() {
        try {
            int count = 0;
            if (isGenerate) {
                count = logic.searhProfileCount(profileName, profileDesc, true, getFilters(), getSortByColumns());
            }
            isResultsEmpty = count == 0;
            return count;
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            List<Object[]> list = new ArrayList<>();
            List<CalculationProfileDTO> viewList = new ArrayList<>();
            if (isGenerate) {
                list = logic.searhProfile(profileName, profileDesc,  false, getFilters(), getSortByColumns(), start, offset);
                viewList = logic.getCustomizedProfiles(list);
            }
            return viewList;
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CalculationProfileDTO dto = (CalculationProfileDTO) object;
        ((BeanItemContainer<CalculationProfileDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(boolean isGenerate, String profileName, String profileDesc) {
        this.profileName = profileName;
        this.profileDesc = profileDesc;
        this.isGenerate = isGenerate;

        this.clearAll();
        this.getFilters().clear();
        this.setRequiredCount(true);
        setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

}
