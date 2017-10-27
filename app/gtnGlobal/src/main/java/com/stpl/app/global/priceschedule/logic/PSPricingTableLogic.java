/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.priceschedule.logic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.priceschedule.dto.PSDTO;
import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
import static com.stpl.app.global.priceschedule.logic.PSLogic.getCustomizedItemPriceDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author pvinoth
 */
public class PSPricingTableLogic extends PageTableLogic{
    
    private BeanItemContainer<PSIFPDTO> itemDetailsResultBean;
    private boolean isGenerate;    
    private ErrorfulFieldGroup binder;
    PSLogic psLogic;
    private String mode=StringUtils.EMPTY;
    PSDTO psMaster;
    SessionDTO sessionDTO;
    private static final Logger LOGGER = Logger.getLogger(PSPricingTableLogic.class);

    @Override
    public int getCount() {
        List<Object[]> list=null;
        
        if (isGenerate) {
            try {
                if (itemDetailsResultBean.size() > 0) {
                    psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
                    itemDetailsResultBean.removeAllItems();
                }
                list = psLogic.getItemPriceDetails(this.getFilters(), 0, 0, this.getSortByColumns(), mode, psMaster,true);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            } catch(PortalException ex){
                LOGGER.error(ex);
            }
        }
        return list!=null && !list.isEmpty() ? Integer.valueOf(String.valueOf(list.get(0))) : 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {            
            List<Object[]> list = psLogic.getItemPriceDetails(this.getFilters(), start, offset, this.getSortByColumns(), mode, psMaster,false);
            return getCustomizedItemPriceDTO(list, mode, psMaster);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
        }
        return Collections.emptyList();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        PSIFPDTO dto = (PSIFPDTO) object;
        ((BeanItemContainer<PSIFPDTO>) container).addBean(dto);
        return dto;
    }
    
    public void setSearchData(final BeanItemContainer<PSIFPDTO> itemDetailsResultBean, final ErrorfulFieldGroup binder,String mode, PSDTO psMaster, final SessionDTO sessionDTO){
        
        clearAll();
        this.itemDetailsResultBean = itemDetailsResultBean;  
        this.binder=binder;
        this.mode=mode;
        this.sessionDTO=sessionDTO;
        psLogic=new PSLogic(this.sessionDTO);
        this.psMaster=psMaster;
        setRequiredCount(true);
        isGenerate = true;
    }
}
