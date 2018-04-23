package com.stpl.gtn.gtn2o.ws.service;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.CustomerHierarchyLookupBean;

@Service
public class GtnWsReportWebsevice {

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public GtnWsReportWebsevice() {
		super();
	}

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public List<Object[]> loadHierarchyResults() throws GtnFrameworkGeneralException {

		Object[] params = { "Customer Hierarchy", "%", "Primary" };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		String searchQuery = "SELECT distinct c.HIERARCHY_NAME,a.LEVEL_NAME, b.LEVEL_NO , c.CREATED_DATE, c.MODIFIED_DATE, c.VERSION_NO,c.HIERARCHY_DEFINITION_SID from HIERARCHY_LEVEL_DEFINITION a , HIERARCHY_LEVEL_DEFINITION b ,HIERARCHY_DEFINITION c where a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID and a.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID AND c.HIERARCHY_CATEGORY in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.LIST_NAME = 'HIERARCHY_CATEGORY' and ht.DESCRIPTION like ?)  AND b.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID  AND a.LEVEL_NO in (SELECT MAX(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = a.HIERARCHY_DEFINITION_SID)  AND b.LEVEL_NO in  (SELECT MIN(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID)  AND c.HIERARCHY_NAME like ?  AND c.HIERARCHY_TYPE in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.DESCRIPTION like ?) ";
		gtnSqlQueryEngine.setSessionFactory(getSessionFactory());
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(searchQuery, params,
				paramsType);
		for (Object[] objects : resultList) {
			System.out.println("**********" + objects);
		}
		return resultList;

	}

	public List<Object[]> loadHierarchyResults(CustomerHierarchyLookupBean hierarchyBean) throws GtnFrameworkGeneralException {
		Object[] params = { hierarchyBean.getHierarchyIndicator(), hierarchyBean.getHierarchyName(),
				hierarchyBean.getHierarchyType() };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		String searchQuery = "SELECT distinct c.HIERARCHY_DEFINITION_SID,c.HIERARCHY_NAME,a.LEVEL_NAME, b.LEVEL_NO , c.CREATED_DATE, c.MODIFIED_DATE, c.VERSION_NO from HIERARCHY_LEVEL_DEFINITION a , HIERARCHY_LEVEL_DEFINITION b ,HIERARCHY_DEFINITION c where a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID and a.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID AND c.HIERARCHY_CATEGORY in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.LIST_NAME = 'HIERARCHY_CATEGORY' and ht.DESCRIPTION like ?)  AND b.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID  AND a.LEVEL_NO in (SELECT MAX(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = a.HIERARCHY_DEFINITION_SID)  AND b.LEVEL_NO in  (SELECT MIN(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID)  AND c.HIERARCHY_NAME like ?  AND c.HIERARCHY_TYPE in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.DESCRIPTION like ?) ";
		gtnSqlQueryEngine.setSessionFactory(getSessionFactory());
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(searchQuery, params,
				paramsType);
		for (Object[] objects : resultList) {
			System.out.println("**********" + objects);
		}
		return resultList;
	}

	public List<Object[]> loadProductHierarchyResults(CustomerHierarchyLookupBean hierarchyBean) throws GtnFrameworkGeneralException {
		Object[] params = { hierarchyBean.getHierarchyIndicator(), hierarchyBean.getHierarchyName(),
				hierarchyBean.getHierarchyType() };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		String searchQuery = "SELECT distinct c.HIERARCHY_DEFINITION_SID,c.HIERARCHY_NAME,a.LEVEL_NAME, b.LEVEL_NO , c.CREATED_DATE, c.MODIFIED_DATE, c.VERSION_NO from HIERARCHY_LEVEL_DEFINITION a , HIERARCHY_LEVEL_DEFINITION b ,HIERARCHY_DEFINITION c where a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID and a.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID AND c.HIERARCHY_CATEGORY in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.LIST_NAME = 'HIERARCHY_CATEGORY' and ht.DESCRIPTION like ?)  AND b.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID  AND a.LEVEL_NO in (SELECT MAX(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = a.HIERARCHY_DEFINITION_SID)  AND b.LEVEL_NO in  (SELECT MIN(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID)  AND c.HIERARCHY_NAME like ?  AND c.HIERARCHY_TYPE in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.DESCRIPTION like ?) ";
		gtnSqlQueryEngine.setSessionFactory(getSessionFactory());
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(searchQuery, params,
				paramsType);
		for (Object[] objects : resultList) {
			System.out.println("**********" + objects);
		}
		return resultList;
	}

	public List<Object[]> loadProductHierarchyResults() throws GtnFrameworkGeneralException {
		Object[] params = { "Customer Hierarchy", "%", "Primary" };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		String searchQuery = "SELECT distinct c.HIERARCHY_NAME,a.LEVEL_NAME, b.LEVEL_NO , c.CREATED_DATE, c.MODIFIED_DATE, c.VERSION_NO,c.HIERARCHY_DEFINITION_SID from HIERARCHY_LEVEL_DEFINITION a , HIERARCHY_LEVEL_DEFINITION b ,HIERARCHY_DEFINITION c where a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID and a.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID AND c.HIERARCHY_CATEGORY in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.LIST_NAME = 'HIERARCHY_CATEGORY' and ht.DESCRIPTION like ?)  AND b.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID  AND a.LEVEL_NO in (SELECT MAX(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = a.HIERARCHY_DEFINITION_SID)  AND b.LEVEL_NO in  (SELECT MIN(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID)  AND c.HIERARCHY_NAME like ?  AND c.HIERARCHY_TYPE in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.DESCRIPTION like ?) ";
		gtnSqlQueryEngine.setSessionFactory(getSessionFactory());
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(searchQuery, params,
				paramsType);
		for (Object[] objects : resultList) {
			System.out.println("**********" + objects);
		}
		return resultList;
	}

	public List<Object[]> loadRelationshipValues(CustomerHierarchyLookupBean lookupBean) throws GtnFrameworkGeneralException {

		/*Session session = sessionFactory.openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		RelationshipBuilder relationshipBuilder = new RelationshipBuilder();

		Criteria criteria = session.createCriteria(RelationshipBuilder.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property(String.valueOf(relationshipBuilder.getRelationshipBuilderSid())));
		projectionList.add(Projections.property(relationshipBuilder.getRelationshipName()));
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.eq("hierarchyDefinition", 3));
		List<RelationshipBuilder> resultList = criteria.list();
		for (RelationshipBuilder relationshipBuilder2 : resultList) {
			System.out.println("**********" + relationshipBuilder2);
		}*/

		Object[] params = { lookupBean.getHierarchyDefSid() };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER };
		String relationshipLoadQuery = "SELECT DISTINCT RELATIONSHIP_BUILDER_SID,RELATIONSHIP_NAME from dbo.RELATIONSHIP_BUILDER WHERE HIERARCHY_DEFINITION_SID = ?";
		gtnSqlQueryEngine.setSessionFactory(getSessionFactory());
		List<Object[]> relationshipList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(relationshipLoadQuery,
				params, paramsType);
		for (Object[] objects : relationshipList) {
			System.out.println("Relatioship is" + objects[1]);
		}
		return relationshipList;
	}

}
