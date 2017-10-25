/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import com.stpl.app.gtnforecasting.dto.RelationshipDdlbDto;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.portal.kernel.exception.PortalException;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author soundarrajan.l
 */
public class RelationshipDdlbDao implements DAO<RelationshipDdlbDto> {

    RelationshipDdlbDto defaultRelationshipDdlbDto;

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(RelationshipDdlbDao.class);

    DataSelectionLogic logic = new DataSelectionLogic();
    RelationshipDdlbDto selectedRelationshipDdlbDto;
    

    int hierarchyDefinitionSid;

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
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count + 1;
    }

    @Override
    public List<RelationshipDdlbDto> find(SearchCriteria criteria, int startIndex, int offset, List<OrderByColumn> columns) {
        List<RelationshipDdlbDto> resultList = new ArrayList<RelationshipDdlbDto>();
        try {
            resultList = logic.getRelationshipSidLazy(startIndex, startIndex + offset, defaultRelationshipDdlbDto, criteria.getFilter(), hierarchyDefinitionSid, selectedRelationshipDdlbDto);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);

        }
        return resultList;
    }

}
