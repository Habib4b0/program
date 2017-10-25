/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.logic.tablelogic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.netsalesformula.dto.DeductionDto;
import com.stpl.app.global.netsalesformula.logic.DeductionsLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author karthikraja.k
 */
public class DeductionTableLogic extends PageTableLogic {

    /**
     * LOGGER
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DeductionTableLogic.class);
    private BeanItemContainer<DeductionDto> deductionBean;
    private ErrorfulFieldGroup binder;
    DeductionsLogic dedLogic;
    SessionDTO sessionDTO;
    boolean isResultsEmpty = true;
    boolean isContract;
    boolean isSelected;

    @Override
    public int getCount() {
              List list = null;

        try {
             if (!isSelected && binder!=null) {
                list = dedLogic.getCountAndResultsForRS(binder, 0, 0, this.getSortByColumns(), this.getFilters(), true);
            } else if(binder!=null){
                list = dedLogic.tempTableCount(this.getFilters(), this.getSortByColumns(),isContract,true, 0, 0);
            }

        } catch (SystemException ex) {
            LOGGER.debug("System Exception" + ex);
        } catch (Exception ex) {
            LOGGER.debug("Exception" + ex);
        }
        if (list != null) {
            if (Integer.valueOf(String.valueOf(list.get(0))) > 0) {
                isResultsEmpty = false;
            } else {
                isResultsEmpty = true;
            }
        } else {
            isResultsEmpty = true;
        }

        int count = list == null ? 0 : Integer.valueOf(String.valueOf(list.get(0)));
        return count;
        

    }

    @Override
    public List loadData(int start, int offset) {

        List<DeductionDto> list = new ArrayList<>();
        LOGGER.debug("Inside Load Data");
        try {
            if (isSelected) {
                return dedLogic.tempTableCount(this.getFilters(), this.getSortByColumns(),isContract, false, start, offset);
            } else {
                if (deductionBean.size() > 0) {
                    deductionBean.removeAllItems();
                }
                list = dedLogic.getCountAndResultsForRS(binder, start, offset, this.getSortByColumns(),this.getFilters(), false);
            }
        } catch (SystemException ex) {
            LOGGER.debug("System Exception" + ex);
        } catch (Exception ex) {
            LOGGER.debug("Exception" + ex);
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        DeductionDto dto = (DeductionDto) object;
        ((BeanItemContainer<DeductionDto>) container).addBean(dto);
        return dto;
    }

    public void setSearchData(final BeanItemContainer<DeductionDto> deductionBean, final ErrorfulFieldGroup binder, final SessionDTO sessionDTO,boolean isContract,boolean isSelected) {
      LOGGER.debug("------------Inside Search Data----------");
        clearAll();
        this.deductionBean = deductionBean;
        this.binder = binder;
        this.sessionDTO = sessionDTO;
        this.isContract=isContract;
        this.isSelected=isSelected;
        dedLogic = new DeductionsLogic(this.sessionDTO);

        setRequiredCount(true);

    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }
}
