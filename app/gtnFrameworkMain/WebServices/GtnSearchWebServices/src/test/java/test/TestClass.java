/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import com.stpl.gtn.gtn2o.ws.search.sqlservice.GtnSearchwebServiceSqlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author anandh.karuppusamy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/Sample-test.xml"})
public class TestClass {
    
    @Autowired
    private GtnSearchwebServiceSqlService gtnSearchwebServiceSqlService;
    @Test
    public void sampleTest()
    {
      
        System.out.println(gtnSearchwebServiceSqlService.getQuery("privatePublic")); 
        
    }
}
