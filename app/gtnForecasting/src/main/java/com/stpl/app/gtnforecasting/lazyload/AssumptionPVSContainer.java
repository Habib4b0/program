package com.stpl.app.gtnforecasting.lazyload;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.freezetable.FreezePagedFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

import com.stpl.app.gtnforecasting.dto.AssumptionPVDTO;
import com.stpl.app.gtnforecasting.logic.AssumptionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;

public class AssumptionPVSContainer  implements DAO<AssumptionPVDTO> {

    private static final Logger LOGGER = org.jboss.logging.Logger.getLogger(AssumptionPVSContainer.class);
    private final FreezePagedFilterTable table;
    private final BeanItemContainer<AssumptionPVDTO> saveContainer;
    private final AssumptionLogic logic=new AssumptionLogic();
    private final SessionDTO session;
    private int count;
    private final ComboBox noOfRecs;
    
    public AssumptionPVSContainer(final FreezePagedFilterTable table, final BeanItemContainer<AssumptionPVDTO> saveContainer, final SessionDTO session, ComboBox noOfRecs){
        this.table=table;
        this.saveContainer=saveContainer;
        this.session= session;
        this.noOfRecs=noOfRecs;
    }

    @Override
    public int count(SearchCriteria sc) {
            count = logic.getLineCount(session);
        return count;
    }

    @Override
    public List<AssumptionPVDTO> find(SearchCriteria sc, int i, int i1, List<OrderByColumn> list) {
        List<AssumptionPVDTO> resultList = new ArrayList<>();
        final int currentPage=table.getLeftFreezeAsTable().getCurrentPage()-1;
        final int pageLength=Integer.parseInt(String.valueOf(noOfRecs.getValue()));
        int offset=i1;
        if(count<currentPage*pageLength+offset){
            offset=(currentPage*pageLength+offset)-count;
        }
        try {
        	if(saveContainer.size()>0){
                logic.savePVS(session,saveContainer);
                saveContainer.removeAllItems();
            }
            resultList = logic.getPVSResult(session, pageLength*currentPage,currentPage*pageLength+offset);
        } catch (SystemException | PortalException ex) {
           LOGGER.error(ex);
        }
        return resultList;
    }

}