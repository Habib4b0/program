/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic;

import com.stpl.app.accountclose.gtnbalancereport.dto.ActualVarianceDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.ActualVarianceLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Santanukumar
 */
public class ActualVarianceTableLogic extends PageTableLogic{
    boolean generate=false;
    SessionDTO session;
    BalanceReportDTO balanceReportDTO;
    ActualVarianceLogic varianceLogic=new ActualVarianceLogic();
    public static final Logger LOGGER = Logger.getLogger(ActualVarianceTableLogic.class);
    @Override
    public int getCount() {
        int count=0;
        if (generate) {
             try {
                count=varianceLogic.getCount(balanceReportDTO,getFilters(),session);
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<ActualVarianceDTO> resultList=new ArrayList<ActualVarianceDTO>();
        try {
            resultList=varianceLogic.getResults(balanceReportDTO,getFilters(),session);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
         return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ActualVarianceDTO dto = (ActualVarianceDTO) object;
        ((BeanItemContainer<ActualVarianceDTO>) container).addBean(dto);
        return dto;
    }
    public void setData(SessionDTO session, BalanceReportDTO balanceReportDTO,boolean flag) {
        this.session=session;
        this.balanceReportDTO=balanceReportDTO;
        clearAll();
        setRequiredCount(true);
        generate= !flag;
        setCurrentPage(1);
    }
}
