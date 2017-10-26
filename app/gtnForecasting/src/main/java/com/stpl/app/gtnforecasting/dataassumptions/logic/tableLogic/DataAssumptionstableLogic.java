/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dataassumptions.logic.tableLogic;

import com.stpl.app.gtnforecasting.dataassumptions.dto.DataAssumptionDTO;
import com.stpl.app.gtnforecasting.dataassumptions.logic.DataAssumptionsLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Rohit.Vignesh
 */
public class DataAssumptionstableLogic extends PageTableLogic {

    private final DataAssumptionsLogic logic = new DataAssumptionsLogic();
    private boolean isFirstLoad = false;
    DataAssumptionDTO dataAssumptionDTO;
    SessionDTO sessionDTO;
    private static final Logger LOGGER = Logger.getLogger(DataAssumptionstableLogic.class);
    
    @Override
    public int getCount() {

        LOGGER.debug("getCount Started --->");
        int count = 0;
        try {
            if (isFirstLoad) {
                List list = logic.getDataAssumption(0, 0, true, this.getFilters(), this.getSortByColumns(), sessionDTO);
                if (list != null && !list.isEmpty()) {
                    count = Integer.valueOf(String.valueOf(list.get(0)));
                }
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("DataAssumptionstableLogic Count" + count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {

        LOGGER.debug("loadData Started ");
        List<Object[]> returnList = new ArrayList();
        List<DataAssumptionDTO> headerList = new ArrayList<>();

        if (isFirstLoad) {
            try {
                returnList = logic.getDataAssumption(start, offset, false, this.getFilters(), this.getSortByColumns(), sessionDTO);
                headerList = logic.getCustomizedData(returnList);

                LOGGER.debug("DataAssumptionstableLogic loadData " + headerList.size());

            } catch (Exception ex) {
                LOGGER.error(ex);
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
