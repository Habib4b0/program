package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.serviceimpl;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnFrameworkCustProdAutoUpdateServiceImplTest</code> contains
 * tests for the class
 * <code>{@link GtnFrameworkCustProdAutoUpdateServiceImpl}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFrameworkCustProdAutoUpdateServiceImplTest {

    @Mock
    @Autowired
    private GtnFrameworkAutomaticRelationUpdateService automaticService;
    @InjectMocks
    @Autowired
    GtnFrameworkCustProdAutoUpdateServiceImpl fixture;

    /**
     * Run the GtnFrameworkCustProdAutoUpdateServiceImpl() constructor test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnFrameworkCustProdAutoUpdateServiceImpl_1()
            throws Exception {

        assertNotNull(fixture);
        assertNotNull(fixture.getService());
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
    public void testCheckForAutoUpdate_1()
            throws Exception {

        GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        HierarchyLevelDefinitionBean bean = new HierarchyLevelDefinitionBean();
        bean.setLevelNo(2);
        hierarchyLevelDefinitionList.add(bean);
        
        boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

        assertEquals(false, result);
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
    public void testCheckForAutoUpdate_2()
            throws Exception {

        GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        HierarchyLevelDefinitionBean bean = new HierarchyLevelDefinitionBean();
      
        bean.setLevelNo(0);
        hierarchyLevelDefinitionList.add(bean);

        boolean result = fixture.checkForAutoUpdate(relationBean, hierarchyLevelDefinitionList);

        assertEquals(false, result);
    }

   

    @Test
    public void testDoAutomaticUpdate_1()
            throws Exception {
       GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        HierarchyLevelDefinitionBean bean = new HierarchyLevelDefinitionBean();
        bean.setLevelValueReference(GtnFrameworkWebserviceConstant.USER_DEFINED);
        bean.setLevelNo(2);
        hierarchyLevelDefinitionList.add(bean);
        when(automaticService.insertRelationTillFirstLevelAndGetVersionNo(0, relationBean)).thenReturn(2);
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
        assertNotNull(result);
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
        // add additional set up code here
        MockitoAnnotations.initMocks(this);
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
        // Add additional tear down code here
    }

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     *
     *
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(GtnFrameworkCustProdAutoUpdateServiceImplTest.class);
    }
}
