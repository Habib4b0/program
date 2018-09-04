package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.serviceimpl;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnFrameworkProductAutoUpdateServiceImplTest</code> contains
 * tests for the class
 * <code>{@link GtnFrameworkProductAutoUpdateServiceImpl}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFrameworkProductAutoUpdateServiceImplTest {

    /**
     * Run the GtnFrameworkProductAutoUpdateServiceImpl() constructor test.
     *
     * @throws Exception
     *
     *
     */
         @Mock
    @Autowired
    private org.hibernate.SessionFactory sessionFactory;
     @InjectMocks
    @Autowired
    GtnFrameworkProductAutoUpdateServiceImpl fixture;
         @Mock
    @Autowired
    private GtnFrameworkAutomaticRelationUpdateService automaticService;


    @Test
    public void testGtnFrameworkProductAutoUpdateServiceImpl_1()
            throws Exception {

        GtnFrameworkProductAutoUpdateServiceImpl result = new GtnFrameworkProductAutoUpdateServiceImpl();

        assertNotNull(result);
        assertEquals(null, result.getService());
    }

   


    /**
     * Run the boolean
     * checkForAutoUpdate(GtnWsRelationshipBuilderBean,List<HierarchyLevelDefinitionBean>)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckForAutoUpdate_7()
            throws Exception {
        
        GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
        relationBean.setVersionNo(1);
        List<HierarchyLevelDefinitionBean> productHierarchyLevelDefinitionList = new LinkedList();

                List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        HierarchyLevelDefinitionBean bean = new HierarchyLevelDefinitionBean();
        bean.setLevelNo(0);
        hierarchyLevelDefinitionList.add(bean);
        RelationshipBuilder productrelationshipBuilder=new RelationshipBuilder();
        Session session=Mockito.mock(Session.class);
        doReturn(productrelationshipBuilder).when(session).get(RelationshipBuilder.class,
					relationBean.getDeductionRelation());
        doReturn(session).when(sessionFactory).openSession();
        
 

        boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

        assertEquals(false, result);
    }

	/**
	 * Run the void doAutomaticUpdate(List<HierarchyLevelDefinitionBean>,GtnWsRelationshipBuilderBean) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testDoAutomaticUpdate_1()
		throws Exception {
		
	        GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
        relationBean.setVersionNo(1);
        List<HierarchyLevelDefinitionBean> productHierarchyLevelDefinitionList = new LinkedList();

                List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        HierarchyLevelDefinitionBean bean = new HierarchyLevelDefinitionBean();
        bean.setLevelValueReference(GtnFrameworkWebserviceConstant.USER_DEFINED);
        bean.setLevelNo(2);
        hierarchyLevelDefinitionList.add(bean);
        RelationshipBuilder productrelationshipBuilder=new RelationshipBuilder();
        Session session=Mockito.mock(Session.class);
        doReturn(productrelationshipBuilder).when(session).get(RelationshipBuilder.class,
					relationBean.getDeductionRelation());
        doReturn(session).when(sessionFactory).openSession();
        doReturn(1).when(automaticService).updateRelationShipVersionNo(relationBean);

		fixture.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);

	}

    /**
     * Run the GtnFrameworkAutomaticRelationUpdateService getService() method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetService_1()
            throws Exception {
        

        GtnFrameworkAutomaticRelationUpdateService result = fixture.getService();

        assertNotNull( result);
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
     *
     *
     */
    @Before
    public void setUp()
            throws Exception {
        MockitoAnnotations.initMocks(this);
        // add additional set up code here
    }

    /**
     * Perform post-test clean-up.
     *
     * @throws Exception if the clean-up fails for some reason
     *
     *
     */
    @After
    public void tearDown()
            throws Exception {

    }

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     *
     *
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(GtnFrameworkProductAutoUpdateServiceImplTest.class);
    }
}
