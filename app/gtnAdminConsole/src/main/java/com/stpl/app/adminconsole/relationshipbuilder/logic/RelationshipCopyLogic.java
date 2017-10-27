package com.stpl.app.adminconsole.relationshipbuilder.logic;

import java.util.Date;
import java.util.List;

import com.stpl.app.adminconsole.dao.RelationBuilderLogicDAO;
import com.stpl.app.adminconsole.dao.impl.RelationBuilderLogicDAOImpl;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;

public class RelationshipCopyLogic {

    final public static RelationBuilderLogicDAO dao = new RelationBuilderLogicDAOImpl();

    public static void copyRelationship(int relationshipBuilderSysId, String newRelationshipName, String newRelationshipDescription) throws PortalException, SystemException {
        final int userId = Integer.valueOf((String) VaadinSession.getCurrent().getAttribute("userId"));
        RelationshipBuilder sourceRelation = dao.getRelationshipBuilder(relationshipBuilderSysId);
        RelationshipBuilder destRelation = RelationshipBuilderLocalServiceUtil.createRelationshipBuilder(0);
        destRelation.setRelationshipName(newRelationshipName);
        destRelation.setRelationshipDescription(newRelationshipDescription);
        destRelation.setRelationshipType(sourceRelation.getRelationshipType());
        destRelation.setHierarchyDefinitionSid(sourceRelation.getHierarchyDefinitionSid());
        destRelation.setHierarchyVersion(sourceRelation.getHierarchyVersion());
        destRelation.setStartDate(sourceRelation.getStartDate());
        destRelation.setCreatedBy(userId);
        destRelation.setCreatedDate(new Date());
        destRelation.setModifiedDate(new Date());
        destRelation.setVersionNo(1);
        destRelation.setBuildType(sourceRelation.getBuildType());
        destRelation = dao.addRelationshipBuilder(destRelation);
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);
        query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RELATIONSHIP_BUILDER_SYSTEM_ID, sourceRelation.getRelationshipBuilderSid()));
        final List<RelationshipLevelDefinition> sourceLevels = dao.getRelationshipBuilderWindow(query);
        for (int i = 0; i < sourceLevels.size(); i++) {
            RelationshipLevelDefinition sourceLevel = sourceLevels.get(i);
            RelationshipLevelDefinition destLevel = RelationshipLevelDefinitionLocalServiceUtil.createRelationshipLevelDefinition(0);
            destLevel.setRelationshipBuilderSid(destRelation.getRelationshipBuilderSid());
            destLevel.setHierarchyLevelDefinitionSid(sourceLevel.getHierarchyLevelDefinitionSid());
            destLevel.setRelationshipLevelValues(sourceLevel.getRelationshipLevelValues());
            destLevel.setLevelNo(sourceLevel.getLevelNo());
            destLevel.setLevelName(sourceLevel.getLevelName());
            destLevel.setParentNode(sourceLevel.getParentNode());
            destLevel.setHierarchyNo(sourceLevel.getHierarchyNo().replace(relationshipBuilderSysId + "-", destRelation.getRelationshipBuilderSid() + "-"));
            destLevel.setCreatedBy(userId);
            destLevel.setCreatedDate(new Date());
            destLevel.setModifiedDate(new Date());
            destLevel.setVersionNo(1);
            dao.addRelationshipLevelDefinition(destLevel);
        }
    }
}
