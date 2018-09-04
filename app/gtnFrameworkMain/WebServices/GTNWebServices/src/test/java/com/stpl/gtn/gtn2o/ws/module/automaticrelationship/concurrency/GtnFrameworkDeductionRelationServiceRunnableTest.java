package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnFrameworkDeductionRelationServiceRunnableTest</code>
 * contains tests for the class
 * <code>{@link GtnFrameworkDeductionRelationServiceRunnable}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnFrameworkDeductionRelationServiceRunnableTest {

    @Autowired
    GtnFrameworkDeductionRelationServiceRunnable instance;

    /**
     * Run the GtnFrameworkDeductionRelationServiceRunnable() constructor test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnFrameworkDeductionRelationServiceRunnable_1()
            throws Exception {

        GtnFrameworkDeductionRelationServiceRunnable result = new GtnFrameworkDeductionRelationServiceRunnable();

        assertNotNull(result);
    }

    /**
     * Run the String getQueryReplaced(List<String>,String) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetQueryReplaced_1()
            throws Exception {
        GtnFrameworkDeductionRelationServiceRunnable fixture = new GtnFrameworkDeductionRelationServiceRunnable();
        List<String> input = new LinkedList();
        String queryName = "";

        String result = fixture.getQueryReplaced(input, queryName);

        assertEquals("", result);
    }

    /**
     * Run the String getQueryReplaced(List<String>,String) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetQueryReplaced_2()
            throws Exception {
        GtnFrameworkDeductionRelationServiceRunnable fixture = new GtnFrameworkDeductionRelationServiceRunnable();
        List<String> input = new LinkedList();
        String queryName = "";

        String result = fixture.getQueryReplaced(input, queryName);

        assertEquals("", result);
    }

    /**
     * Run the String getQueryReplaced(List<String>,String) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetQueryReplaced_3()
            throws Exception {
        GtnFrameworkDeductionRelationServiceRunnable fixture = new GtnFrameworkDeductionRelationServiceRunnable();
        List<String> input = null;
        String queryName = "";

        String result = fixture.getQueryReplaced(input, queryName);

        assertEquals("", result);
    }

    /**
     * Run the void saveRelationship(GtnWsRelationshipBuilderBean) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveRelationship_1()
            throws Exception {
        try {
            GtnWsRelationshipBuilderBean relationBuilderBean = new GtnWsRelationshipBuilderBean();
            GtnFrameworkDeductionRelationServiceRunnable ins = Mockito.spy(instance);
            ins.saveRelationship(relationBuilderBean);

            //case 2
            doReturn(null).when(ins).getResultSet(Mockito.anyList());
            ins.saveRelationship(relationBuilderBean);
        } catch (Exception e) {

        }

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
        new org.junit.runner.JUnitCore().run(GtnFrameworkDeductionRelationServiceRunnableTest.class);
    }
}
