/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.logic.tablelogic;

import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualRateProjectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualRateSelectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.AccrualRateProjectionLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 * Table Logic class that is used across all the tabs in Accrual Rate
 * Projection. Contains the logic to retrieve the data form database.
 *
 * @author sibi
 */
public class AccrualRateProjectionTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(AccrualRateProjectionTableLogic.class);

    private final AccrualRateProjectionLogic searchLogic = AccrualRateProjectionLogic.getInstance();

    private AccrualRateSelectionDTO accrualRateSelectionDTO;
    

    private String tabName = StringUtils.EMPTY;
    
    List<String> selectedVariables;
    
    private boolean isViewMode;

    @Override
    public int getCount() {
        LOGGER.debug("Inside Count: "+tabName);
        int count = 0;
        switch (tabName) {
            case AccrualRateUtils.SALES:
                count = searchLogic.getProductsCount(accrualRateSelectionDTO, isViewMode);
                break;
            case AccrualRateUtils.RATES:
                count = searchLogic.getProductsCount(accrualRateSelectionDTO, isViewMode);
                break;
            case AccrualRateUtils.DETAILS:
                List variableList = accrualRateSelectionDTO.getVariableList();
                count = variableList.contains(AccrualRateUtils.DetailsVariables.CHECK_ALL.getConstant()) ? variableList.size() - 1 : variableList.size();
                break;
            default:
                LOGGER.warn("Count - Tab Name is not valid: " + tabName);
                break;
        }
        LOGGER.debug("Count: " + count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        LOGGER.debug("Inside Load Data");
        List list = new ArrayList();
        try {
            switch (tabName) {
                case AccrualRateUtils.SALES:
                    list = searchLogic.getDataForSales(accrualRateSelectionDTO, selectedVariables, start, offset, false, isViewMode);
                    break;
                case AccrualRateUtils.RATES:
                    list = searchLogic.getDataForRates(accrualRateSelectionDTO, start, offset, false, isViewMode);
                    break;
                case AccrualRateUtils.DETAILS:
                    list = searchLogic.getDataForDetails(accrualRateSelectionDTO, start, offset, false, isViewMode);
                    break;
                default:
                    LOGGER.warn("Load Data - Tab Name is not valid: " + tabName);
                    break;
            }

            LOGGER.debug("Records returned: " + list == null ? null : list.size());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AccrualRateProjectionDTO dto = (AccrualRateProjectionDTO) object;
        ((ExtContainer<AccrualRateProjectionDTO>) container).addBean(dto);        
        return dto;
    }

    public void setRequiredData(final String tabName, final AccrualRateSelectionDTO accrualRateSelectionDTO,final List<String> selectedVariables,final boolean isViewMode) {        
        this.tabName = tabName;
        this.accrualRateSelectionDTO = accrualRateSelectionDTO;
        this.selectedVariables =selectedVariables;
        this.isViewMode = isViewMode;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }
}
