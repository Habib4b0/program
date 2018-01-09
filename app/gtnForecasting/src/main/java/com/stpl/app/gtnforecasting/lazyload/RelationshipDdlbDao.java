/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.RelationshipDdlbDto;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.addons.lazycontainer.DAO;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.asi.ui.addons.lazycontainer.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author soundarrajan.l
 */
public class RelationshipDdlbDao implements DAO<RelationshipDdlbDto> {

    private final RelationshipDdlbDto defaultRelationshipDdlbDto;

    private static final Logger LOGGER = LoggerFactory.getLogger(RelationshipDdlbDao.class);

    private final DataSelectionLogic logic = new DataSelectionLogic();
    private final RelationshipDdlbDto selectedRelationshipDdlbDto;
    

    private final int hierarchyDefinitionSid;

    public RelationshipDdlbDao(final int hierarchyDefinitionSid, final RelationshipDdlbDto defaultRelationshipDdlbDto, RelationshipDdlbDto selectedRelationshipDdlbDto) {
        this.hierarchyDefinitionSid = hierarchyDefinitionSid;
        this.defaultRelationshipDdlbDto = defaultRelationshipDdlbDto;
        this.selectedRelationshipDdlbDto = selectedRelationshipDdlbDto;
    }

    @Override
    public int count(SearchCriteria criteria) {
        int count = 0;
        try {
            count = logic.getRelationshipSidCount(criteria.getFilter(), hierarchyDefinitionSid);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return count + 1;
    }

    @Override
    public List<RelationshipDdlbDto> find(SearchCriteria criteria, int startIndex, int offset, List<OrderByColumn> columns) {
        List<RelationshipDdlbDto> resultList = new ArrayList<>();
        try {
            resultList = logic.getRelationshipSidLazy(startIndex, startIndex + offset, defaultRelationshipDdlbDto, criteria.getFilter(), hierarchyDefinitionSid, selectedRelationshipDdlbDto);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

}
