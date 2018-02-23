/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dataassumptions.logic.tableLogic;

import com.stpl.app.gtnforecasting.dataassumptions.dto.DataAssumptionDTO;
import com.stpl.app.gtnforecasting.dataassumptions.logic.DataAssumptionsLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rohit.Vignesh
 */
public class DataAssumptionstableLogic extends PageTableLogic {

    private final DataAssumptionsLogic logic = new DataAssumptionsLogic();
    private boolean isFirstLoad = false;
    protected DataAssumptionDTO dataAssumptionDTO;
    protected SessionDTO sessionDTO;
    private static final Logger LOGGER = LoggerFactory.getLogger(DataAssumptionstableLogic.class);
    
    @Override
    public int getCount() {

        LOGGER.debug("getCount Started --->");
        int count = 0;
        try {
            if (isFirstLoad) {
                List list = logic.getDataAssumption(0, 0, true, this.getFilters(), this.getSortByColumns(), sessionDTO);
                if (list != null && !list.isEmpty()) {
                    count = Integer.parseInt(String.valueOf(list.get(0)));
                }
            }

        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("DataAssumptionstableLogic Count= {}" , count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {

        LOGGER.debug("loadData Started ");
        List<Object[]> returnList;
        List<DataAssumptionDTO> headerList = new ArrayList<>();

        if (isFirstLoad) {
            try {
                returnList = logic.getDataAssumption(start, offset, false, this.getFilters(), this.getSortByColumns(), sessionDTO);
                headerList = logic.getCustomizedData(returnList);

                LOGGER.debug("DataAssumptionstableLogic loadData= {} " , headerList.size());

            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return headerList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        DataAssumptionDTO dto = (DataAssumptionDTO) object;
        ((BeanItemContainer<DataAssumptionDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(DataAssumptionDTO dataAssumptionDTO, SessionDTO sessionDTO) {
        clearAll();
        this.dataAssumptionDTO = dataAssumptionDTO;
        this.sessionDTO = sessionDTO;
        setRequiredCount(true);
        this.getFilters().clear();
        isFirstLoad = true;
        setCurrentPage(1);
    }

}
