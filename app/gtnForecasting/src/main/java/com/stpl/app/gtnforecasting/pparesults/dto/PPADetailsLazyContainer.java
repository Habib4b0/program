/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.pparesults.dto;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.gtnforecasting.pparesults.logic.PPAProjectionResultsLogic;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author pvinoth
 */
public class PPADetailsLazyContainer implements DAO<HelperDTO> {

    private HelperDTO dto;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PPADetailsLazyContainer.class);

    PPAProjectionResultsLogic logic = new PPAProjectionResultsLogic();

    String ddlbType = StringUtils.EMPTY;

    PPADetailsDTO ppaDetailsDTO;

    private HelperDTO defaultDTO = new HelperDTO(0, Constant.SELECT_ONE);

    int projectionId;

    int count;

    public PPADetailsLazyContainer(final HelperDTO dto, final String ddlbType, final PPADetailsDTO ppaDetailsDTO, final int projectionId) {
        this.dto = dto;
        this.ddlbType = ddlbType;
        this.ppaDetailsDTO = ppaDetailsDTO;
        this.projectionId = projectionId;
    }

    @Override
    public int count(SearchCriteria sc) {
        count = 0;
        try {
            if (Constant.CONTRACT.equalsIgnoreCase(ddlbType) || ppaDetailsDTO.getSelectedContract() != 0
                    || ppaDetailsDTO.getSelectedCustomer() != 0 || ppaDetailsDTO.getSelectedBrand() != 0) {
                count = logic.getPPADetailsDDLBCount(sc.getFilter(), ddlbType, projectionId, ppaDetailsDTO);
            }

        } catch (Exception ex) {
           LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List<HelperDTO> find(SearchCriteria sc, final int startIndex, final int offset, List<OrderByColumn> list) {
        List<HelperDTO> returnList = new ArrayList<>();
        try {
            if (count != 0) {
                returnList = logic.getPPADetailsDDLBResult(startIndex, startIndex + offset, sc.getFilter(), dto, ddlbType, projectionId, ppaDetailsDTO);
            }
            if (returnList.isEmpty()) {
                returnList.add(defaultDTO);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return returnList;
    }

}
