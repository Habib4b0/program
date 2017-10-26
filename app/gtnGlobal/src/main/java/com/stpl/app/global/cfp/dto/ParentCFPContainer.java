package com.stpl.app.global.cfp.dto;

import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 * The Class CFPContainer.
 */
public class ParentCFPContainer implements BeanDAO<CFPSearchDTO> {

    /**
     * The c fp search logic.
     */
    private static CFPSearchLogic CFPSEARCHLOGIC;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    /**
     * The binder flag.
     */
    private boolean binderFlag;
    SessionDTO sessionDTO;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ParentCFPContainer.class);

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        return binder;
    }

    /**
     * Sets the binder.
     *
     * @param binder the binder
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Checks if is binder flag.
     *
     * @return true, if checks if is binder flag
     */
    public boolean isBinderFlag() {
        return binderFlag;
    }

    /**
     * Sets the binder flag.
     *
     * @param binderFlag the binder flag
     */
    public void setBinderFlag(final boolean binderFlag) {
        this.binderFlag = binderFlag;
    }

    /**
     * The Constructor.
     *
     * @param binder the binder
     */
    public ParentCFPContainer(final ErrorfulFieldGroup binder, final SessionDTO sessionDTO) {
        this.binder = binder;
        this.sessionDTO=sessionDTO;
        CFPSEARCHLOGIC = new CFPSearchLogic(this.sessionDTO);
        binderFlag = true;
    }

    /**
     * Returns the count.
     *
     * @param search the search
     * @return the int
     */
    public int count(final BeanSearchCriteria search) {

        try {
            int count;
            if (binderFlag) {
                Object listSize = ((List)CFPSEARCHLOGIC.getListForParentLookUp(binder,search,null,false,0,0,ConstantsUtils.COUNT)).get(0);
                count = Integer.valueOf(String.valueOf(listSize));

            } else {
                count = (int) CFPSEARCHLOGIC.getCFPTotalCount();
            }

            return count;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return Constants.ZERO;
    }

    /**
     * Returns the list.
     *
     * @param criteria the criteria
     * @param startIndex the start index
     * @param offset the offset
     * @param columns the columns
     */
    public List<CFPSearchDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
        List<CFPSearchDTO> salesList1;
        try {
            if (binderFlag) {
                salesList1 = CFPSEARCHLOGIC.searchCFPForParentLookUp(binder, startIndex, offset, columns,criteria);
                return salesList1;
            }
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        return new ArrayList<CFPSearchDTO>();
    }
    }
