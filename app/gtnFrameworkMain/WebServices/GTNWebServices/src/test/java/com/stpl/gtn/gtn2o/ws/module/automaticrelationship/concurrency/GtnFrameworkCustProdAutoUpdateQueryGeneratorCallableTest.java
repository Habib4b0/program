package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang.SerializationUtils;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class
 * <code>GtnFrameworkCustProdAutoUpdateQueryGeneratorCallableTest</code>
 * contains tests for the class
 * <code>{@link GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFrameworkCustProdAutoUpdateQueryGeneratorCallableTest {

    @InjectMocks
    @Autowired
    GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable instance;

    @Mock
    private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean = Mockito.mock(GtnFrameworkEntityMasterBean.class);

    /**
     * Run the GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable()
     * constructor test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnFrameworkCustProdAutoUpdateQueryGeneratorCallable_1()
            throws Exception {

        GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable result = new GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable();

        assertNotNull(result);
        assertEquals(0, result.getIndex());
        assertEquals(0, result.getCustomertUpdatedVersionNo());
        assertEquals(null, result.getRelationBean());
    }

    /**
     * Run the String call() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test(expected = java.lang.NullPointerException.class)
    public void testCall_1()
            throws Exception {
        GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);

        String result = fixture.call();

        assertNotNull(result);
    }

    /**
     * Run the int getCustomertUpdatedVersionNo() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetCustomertUpdatedVersionNo_1()
            throws Exception {
        GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);

        int result = fixture.getCustomertUpdatedVersionNo();

        assertEquals(1, result);
    }

    /**
     * Run the int getIndex() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetIndex_1()
            throws Exception {
        GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);

        int result = fixture.getIndex();

        assertEquals(1, result);
    }

    /**
     * Run the List<Object> getInputListForSelectClause() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testMain()
            throws Exception {
        instance.setRelationBean(new GtnWsRelationshipBuilderBean());
        instance.setCustomertUpdatedVersionNo(1);
        instance.setIndex(0);
        GtnFrameworkSelectColumnRelationBean keyBean = Mockito.mock(GtnFrameworkSelectColumnRelationBean.class);
        doReturn("join_col").when(keyBean).getJoinColumnTable();
        doReturn("where_col").when(keyBean).getWhereClauseColumn();
        when(gtnFrameworkEntityMasterBean.getKeyRelationBeanUsingTableIdAndColumnName(Mockito.anyString(), Mockito.anyString())).thenReturn(keyBean);
        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        HierarchyLevelDefinitionBean bean=new HierarchyLevelDefinitionBean();
          bean.setLevelValueReference(GtnFrameworkWebserviceConstant.USER_DEFINED);
        hierarchyLevelDefinitionList.add(bean);
        
        instance.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);

        ExecutorService service = Executors.newFixedThreadPool(1);
        System.out.println("" + service.submit(instance));
//        System.out.println("" + service.submit(instance));

    }
     @Test
    public void testGetInputListForSelectClause_1()
            throws Exception {
        instance.setRelationBean(new GtnWsRelationshipBuilderBean());
        instance.setCustomertUpdatedVersionNo(1);
        instance.setIndex(0);
        GtnFrameworkSelectColumnRelationBean keyBean = Mockito.mock(GtnFrameworkSelectColumnRelationBean.class);
        doReturn("join_col").when(keyBean).getJoinColumnTable();
        doReturn("where_col").when(keyBean).getWhereClauseColumn();
        when(gtnFrameworkEntityMasterBean.getKeyRelationBeanUsingTableIdAndColumnName(Mockito.anyString(), Mockito.anyString())).thenReturn(keyBean);
        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();
        HierarchyLevelDefinitionBean bean=new HierarchyLevelDefinitionBean();
        hierarchyLevelDefinitionList.add(bean);
        
        instance.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
        instance.getInputListForSelectClause();
//        System.out.println("" + service.submit(instance));

    }

    /**
     * Run the GtnWsRelationshipBuilderBean getRelationBean() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetRelationBean_1()
            throws Exception {
        GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);

        GtnWsRelationshipBuilderBean result = fixture.getRelationBean();

        assertNotNull(result);
        assertEquals(null, result.getStartDate());
        assertEquals(null, result.getModifiedDate());
        assertEquals(0, result.getRelationshipBuilderSid());
        assertEquals(null, result.getRelationshipDescription());
        assertEquals(null, result.getRelationshipName());
        assertEquals(null, result.getDeductionRelation());
        assertEquals(null, result.getHierarchycategory());
        assertEquals(0, result.getHierarchyDefinitionSid());
        assertEquals(null, result.getHierarchyVersion());
        assertEquals(null, result.getCreatedDate());
        assertEquals(0, result.getModifiedBy());
        assertEquals(null, result.getBuildType());
        assertEquals(0, result.getVersionNo());
        assertEquals(0, result.getCreatedBy());
    }

    /**
     * Run the void setCustomertUpdatedVersionNo(int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetCustomertUpdatedVersionNo_1()
            throws Exception {
        GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);
        int customertUpdatedVersionNo = 1;

        fixture.setCustomertUpdatedVersionNo(customertUpdatedVersionNo);

    }

    /**
     * Run the void
     * setHierarchyLevelDefinitionList(List<HierarchyLevelDefinitionBean>)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetHierarchyLevelDefinitionList_1()
            throws Exception {
        GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);
        List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = new LinkedList();

        fixture.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);

    }

    /**
     * Run the void setIndex(int) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetIndex_1()
            throws Exception {
        GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);
        int index = 1;

        fixture.setIndex(index);

    }

    /**
     * Run the void setRelationBean(GtnWsRelationshipBuilderBean) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetRelationBean_1()
            throws Exception {
        GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable fixture = new GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable();
        fixture.setRelationBean(new GtnWsRelationshipBuilderBean());
        fixture.setCustomertUpdatedVersionNo(1);
        fixture.setIndex(1);
        GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();

        fixture.setRelationBean(relationBean);

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
        new org.junit.runner.JUnitCore().run(GtnFrameworkCustProdAutoUpdateQueryGeneratorCallableTest.class);
    }
}
