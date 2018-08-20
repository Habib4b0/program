/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.GtnFrameworkContractDashboardMainConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.GtnFrameworkContractDashboardMainConfigTest;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnFrameworkContractDashboardInfomationTabConfigTest {
    
    public GtnFrameworkContractDashboardInfomationTabConfigTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTabConfig method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testGetTabConfig() {
        System.out.println("getTabConfig");
        String mainNamspacePrefix = "";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        GtnUIFrameworkTabConfig result = instance.getTabConfig(mainNamspacePrefix);
        assertFalse(result.getTabCaption().isEmpty());
    }

    /**
     * Test of addContractHeaderDetailDecissionLayout method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractHeaderDetailDecissionLayout() {
        String methodName = "addContractHeaderDetailDecissionLayout";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderDetailFieldPanel method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractHeaderDetailFieldPanel() {
        String methodName = "addContractHeaderDetailFieldPanel";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecissionLevel method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddDecissionLevel() {
        String methodName = "addDecissionLevel";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecissionView method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddDecissionView() {
        String methodName = "addDecissionView";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHeaderFieldComponent method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddHeaderFieldComponent() {
        String methodName = "addHeaderFieldComponent";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractId method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractId() {
        String methodName = "addContractId";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractNo method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractNo() {
        String methodName = "addContractNo";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractName method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractName() {
        String methodName = "addContractName";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractType method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractType() {
        String methodName = "addContractType";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractStatus method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractStatus() {
        String methodName = "addContractStatus";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDocumentType method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddDocumentType() {
        String methodName = "addDocumentType";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractStartDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractStartDate() {
        String methodName = "addContractStartDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractEndDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractEndDate() {
        String methodName = "addContractEndDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDocumentClass method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddDocumentClass() {
        String methodName = "addDocumentClass";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyName method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddCompanyName() {
        String methodName = "addCompanyName";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addTradeClass method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddTradeClass() {
        String methodName = "addTradeClass";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addTerm method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddTerm() {
        String methodName = "addTerm";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addTradingPartner method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddTradingPartner() {
        String methodName = "addTradingPartner";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addReNegotiationStartDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddReNegotiationStartDate() {
        String methodName = "addReNegotiationStartDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addReNegotiationEndDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddReNegotiationEndDate() {
        String methodName = "addReNegotiationEndDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceProtectionStartDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddPriceProtectionStartDate() {
        String methodName = "addPriceProtectionStartDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceProtectionEndDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddPriceProtectionEndDate() {
        String methodName = "addPriceProtectionEndDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addManufacturerNo method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddManufacturerNo() {
        String methodName = "addManufacturerNo";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractEligibleDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddContractEligibleDate() {
        String methodName = "addContractEligibleDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDeatilsFieldComponent method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddDeatilsFieldComponent() {
        String methodName = "addDeatilsFieldComponent";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInsideOwner method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddInsideOwner() {
        String methodName = "addInsideOwner";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInsidePhone method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddInsidePhone() {
        String methodName = "addInsidePhone";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInsideAuthor method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddInsideAuthor() {
        String methodName = "addInsideAuthor";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInsideAdditional method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddInsideAdditional() {
        String methodName = "addInsideAdditional";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInsideAdditionalName method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddInsideAdditionalName() {
        String methodName = "addInsideAdditionalName";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInsideAdditionalPhone method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddInsideAdditionalPhone() {
        String methodName = "addInsideAdditionalPhone";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addOutsideOwner method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddOutsideOwner() {
        String methodName = "addOutsideOwner";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addOutsidePhone method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddOutsidePhone() {
        String methodName = "addOutsidePhone";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addOutsideAuthor method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddOutsideAuthor() {
        String methodName = "addOutsideAuthor";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addOutsideAdditional method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddOutsideAdditional() {
        String methodName = "addOutsideAdditional";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addOutsideAdditionalName method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddOutsideAdditionalName() {
        String methodName = "addOutsideAdditionalName";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addOutsideAdditionalPhone method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddOutsideAdditionalPhone() {
        String methodName = "addOutsideAdditionalPhone";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addAdvanceNoticeDays method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddAdvanceNoticeDays() {
        String methodName = "addAdvanceNoticeDays";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addAffiliatedContractInfo method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddAffiliatedContractInfo() {
        String methodName = "addAffiliatedContractInfo";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addShippingTerms method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddShippingTerms() {
        String methodName = "addShippingTerms";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProposalStartDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddProposalStartDate() {
        String methodName = "addProposalStartDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProposalEndDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddProposalEndDate() {
        String methodName = "addProposalEndDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addOriginalStartDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddOriginalStartDate() {
        String methodName = "addOriginalStartDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addOriginalEndDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddOriginalEndDate() {
        String methodName = "addOriginalEndDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addAwardStatus method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddAwardStatus() {
        String methodName = "addAwardStatus";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addLastUpdatedDate method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddLastUpdatedDate() {
        String methodName = "addLastUpdatedDate";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceEscalationClause method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddPriceEscalationClause() {
        String methodName = "addPriceEscalationClause";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addExemptFromLowPrice method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddExemptFromLowPrice() {
        String methodName = "addExemptFromLowPrice";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceResetIndicator method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddPriceResetIndicator() {
        String methodName = "addPriceResetIndicator";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCancellationClause method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddCancellationClause() {
        String methodName = "addCancellationClause";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMostFavoredNation method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddMostFavoredNation() {
        String methodName = "addMostFavoredNation";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCategory method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddCategory() {
        String methodName = "addCategory";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCurrency method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddCurrency() {
        String methodName = "addCurrency";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMinimumOrder method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddMinimumOrder() {
        String methodName = "addMinimumOrder";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPaymentTerms method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testAddPaymentTerms() {
        String methodName = "addPaymentTerms";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of contractTabHistoryDataTableComponent method, of class GtnFrameworkContractDashboardInfomationTabConfig.
     */
    @Test
    public void testContractTabHistoryDataTableComponent() {
        String methodName = "contractTabHistoryDataTableComponent";
        GtnFrameworkContractDashboardInfomationTabConfig instance = new GtnFrameworkContractDashboardInfomationTabConfig();
        testComponentConfig(methodName, instance);
    }
    
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkContractDashboardInfomationTabConfig gtnFrameworkContractDashboardInfomationTabConfig) {
        System.out.println(gtnFrameworkContractMainConfigMethodName);
        Method arr[] = gtnFrameworkContractDashboardInfomationTabConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnFrameworkContractMainConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractDashboardInfomationTabConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkContractDashboardInfomationTabConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        try {
            Class[] classArr = method.getParameterTypes();
            Object[] obj = new Object[method.getParameterTypes().length];
            obj[0] = componentList;
            for (int i = 1; i < obj.length; i++) {
                switch (classArr[i].getName()) {
                    case "java.lang.String":
                        obj[i] = "VALUE";
                        break;
                    case "java.util.List":
                        obj[i] = new ArrayList<>();
                        break;
                    default:
                        break;
                }
            }
            method.setAccessible(true);
            method.invoke(gtnFrameworkContractDashboardInfomationTabConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkContractDashboardInfomationTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
