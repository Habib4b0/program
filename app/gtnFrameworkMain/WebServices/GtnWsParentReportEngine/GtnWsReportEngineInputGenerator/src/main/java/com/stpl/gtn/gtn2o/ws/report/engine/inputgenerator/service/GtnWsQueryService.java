package com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.service;

import java.util.ArrayList;
import java.util.List;

public class GtnWsQueryService {

    public String getCCPQuery() {
        return "IF Object_id('tempdb..#COMP_CONT') IS NOT NULL \n"
                + "  DROP TABLE #COMP_CONT \n"
                + "\n"
                + "CREATE TABLE #COMP_CONT \n"
                + "  ( \n"
                + "     COMPANY_MASTER_SID  INT, \n"
                + "     CONTRACT_MASTER_SID INT, \n"
                + "     HIERARCHY_NO        VARCHAR(50) \n"
                + "  ) \n"
                + "\n"
                + "INSERT INTO #COMP_CONT \n"
                + "            (COMPANY_MASTER_SID, \n"
                + "             CONTRACT_MASTER_SID, \n"
                + "             HIERARCHY_NO) \n"
                + "             \n"
                + "SELECT DISTINCT COMPANY_MASTER.COMPANY_MASTER_SID          AS \n"
                + "                company_mastercompany_master_sid, \n"
                + "                CONTRACT_MASTER.CONTRACT_MASTER_SID        AS \n"
                + "                contract_mastercontract_master_sid, \n"
                + "                RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO AS \n"
                + "                relationship_level_definitionhierarchy_no                \n"
                + "                \n"
                + "FROM   CONTRACT_MASTER AS CONTRACT_MASTER \n"
                + "       JOIN CFP_CONTRACT CFP_CONTRACT \n"
                + "         ON CONTRACT_MASTER.CONTRACT_MASTER_SID = \n"
                + "            CFP_CONTRACT.CONTRACT_MASTER_SID \n"
                + "       JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS \n"
                + "         ON CFP_CONTRACT.CFP_CONTRACT_SID = \n"
                + "            CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID \n"
                + "       JOIN COMPANY_MASTER COMPANY_MASTER \n"
                + "         ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = \n"
                + "            COMPANY_MASTER.COMPANY_MASTER_SID \n"
                + "       INNER JOIN CONTRACT_MASTER RESTRICTION_JOINCONTRACT_MASTER \n"
                + "               ON CONTRACT_MASTER.PROCESS_STATUS = \n"
                + "                  RESTRICTION_JOINCONTRACT_MASTER.PROCESS_STATUS \n"
                + "       JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION \n"
                + "         ON RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE \n"
                + "                 CONCAT(RELATIONSHIP_BUILDER_SID, '-', '%', '.', \n"
                + "                 CONTRACT_MASTER.CONTRACT_TYPE, \n"
                + "                 '.', \n"
                + "            CONTRACT_MASTER.CONTRACT_MASTER_SID, '.', \n"
                + "                 COMPANY_MASTER.COMPANY_MASTER_SID, '.' \n"
                + "                 ) \n"
                + "            AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = 4 \n"
                + "            AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = 390 \n"
                + "WHERE  CONTRACT_MASTER.CONTRACT_TYPE IN( '222' ) \n"
                + "       AND CONTRACT_MASTER.CONTRACT_MASTER_SID IN( '44', '1091', '1093', '22', \n"
                + "                                                   '1092', '23', '1083', '24', \n"
                + "                                                   '1082', '1094', '25', '1079', \n"
                + "                                                   '62', '54', '60', '61' ) \n"
                + "       AND COMPANY_MASTER.COMPANY_MASTER_SID IN( \n"
                + "           '58322', '58323', '58325', '74820', \n"
                + "           '10110', '58321', '74822', '58320', \n"
                + "           '74821', '86', '74823', '87', \n"
                + "           '84', '10116', '58318', '96', \n"
                + "           '92', '74614', '91', '90', \n"
                + "           '58319', '11144', '88', '89', '11147' \n"
                + "                                               ) \n"
                + "\n"
                + "IF Object_id('TEMPDB..#SELECTED_PROD_HIERARCHY_NO') IS NOT NULL \n"
                + "  DROP TABLE #SELECTED_PROD_HIERARCHY_NO \n"
                + "\n"
                + "IF Object_id('tempdb..#ITEM') IS NOT NULL \n"
                + "  DROP TABLE #ITEM \n"
                + "\n"
                + "CREATE TABLE #ITEM \n"
                + "  ( \n"
                + "     ITEM_MASTER_SID INT, \n"
                + "     HIERARCHY_NO    VARCHAR(50) \n"
                + "  ) \n"
                + "\n"
                + "INSERT INTO #ITEM \n"
                + "            (ITEM_MASTER_SID, \n"
                + "             HIERARCHY_NO) \n"
                + "SELECT DISTINCT ITEM_MASTER.ITEM_MASTER_SID                AS \n"
                + "                item_masteritem_master_sid, \n"
                + "                RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO AS \n"
                + "                relationship_level_definitionhierarchy_no \n"
                + "FROM   ITEM_MASTER AS ITEM_MASTER \n"
                + "       JOIN BRAND_MASTER BRAND_MASTER \n"
                + "         ON ITEM_MASTER.BRAND_MASTER_SID = BRAND_MASTER.BRAND_MASTER_SID \n"
                + "       JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER \n"
                + "         ON GL_COST_CENTER_MASTER.NDC8 = ITEM_MASTER.NDC8 \n"
                + "       JOIN COMPANY_MASTER COMPANY_MASTER \n"
                + "         ON COMPANY_MASTER.COMPANY_NO = GL_COST_CENTER_MASTER.COMPANY_CODE \n"
                + "       JOIN RELATIONSHIP_LEVEL_DEFINITION RELATIONSHIP_LEVEL_DEFINITION \n"
                + "         ON RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO LIKE \n"
                + "                 CONCAT(RELATIONSHIP_BUILDER_SID, '-', \n"
                + "                 COMPANY_MASTER.COMPANY_MASTER_SID, \n"
                + "                 '.', \n"
                + "       BRAND_MASTER.BRAND_MASTER_SID, \n"
                + "       '.' \n"
                + "       , ITEM_MASTER.ITEM_MASTER_SID, '.') \n"
                + "       AND RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO = 3 \n"
                + "       AND RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID = 403 \n"
                + "WHERE  COMPANY_MASTER.COMPANY_MASTER_SID IN( '94' ) \n"
                + "       AND BRAND_MASTER.BRAND_MASTER_SID IN( '97' ) \n"
                + "       AND ITEM_MASTER.ITEM_MASTER_SID IN( '143', '134', '144', '126', \n"
                + "                                           '135', '145', '127', '132', \n"
                + "                                           '146', '128', '133', '138', \n"
                + "                                           '139', '136', '137', '176', \n"
                + "                                           '129', '1224', '140', '131', \n"
                + "                                           '130', '142', '141' ) \n"
                + "\n"
                + "--INSERT INTO ST_CCP_HIERARCHY_10922_050416471_180110 \n"
                + "--            (CCP_DETAILS_SID, \n"
                + "--             CUST_HIERARCHY_NO, \n"
                + "--             PROD_HIERARCHY_NO) \n"
                + "SELECT DISTINCT CD.CCP_DETAILS_SID, \n"
                + "                CC.HIERARCHY_NO, \n"
                + "                I.HIERARCHY_NO \n"
                + "FROM   #COMP_CONT CC \n"
                + "       JOIN CCP_DETAILS CD \n"
                + "         ON CC.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID \n"
                + "            AND CC.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID \n"
                + "       JOIN #ITEM I \n"
                + "         ON I.ITEM_MASTER_SID = CD.ITEM_MASTER_SID \n";
    }

    public List<Object[]> getCPPList() {
        List result = new ArrayList<>();
        result.add(new Object[]{"10515", "390-116.222.22.84.", "403-94.97.126."});
        result.add(new Object[]{"10516", "390-116.222.22.84.", "403-94.97.137."});
        result.add(new Object[]{"10517", "390-116.222.22.84.", "403-94.97.139."});
        result.add(new Object[]{"10518", "390-116.222.22.84.", "403-94.97.140."});
        result.add(new Object[]{"10519", "390-116.222.23.86.", "403-94.97.141."});
        result.add(new Object[]{"10520", "390-116.222.23.86.", "403-94.97.142."});
        result.add(new Object[]{"10523", "390-116.222.24.88.", "403-94.97.127."});
        result.add(new Object[]{"10524", "390-116.222.24.88.", "403-94.97.128."});
        result.add(new Object[]{"10525", "390-116.222.24.88.", "403-94.97.143."});
        result.add(new Object[]{"10526", "390-116.222.24.88.", "403-94.97.144."});
        result.add(new Object[]{"10527", "390-116.222.24.88.", "403-94.97.145."});
        result.add(new Object[]{"10528", "390-116.222.24.89.", "403-94.97.127."});
        result.add(new Object[]{"10529", "390-116.222.24.89.", "403-94.97.128."});
        result.add(new Object[]{"10530", "390-116.222.24.89.", "403-94.97.143."});
        result.add(new Object[]{"10531", "390-116.222.24.89.", "403-94.97.144."});
        result.add(new Object[]{"10532", "390-116.222.24.89.", "403-94.97.145."});
        result.add(new Object[]{"10533", "390-116.222.24.90.", "403-94.97.127."});
        result.add(new Object[]{"10534", "390-116.222.24.90.", "403-94.97.128."});
        result.add(new Object[]{"10535", "390-116.222.24.90.", "403-94.97.143."});
        result.add(new Object[]{"10536", "390-116.222.24.90.", "403-94.97.144."});
        result.add(new Object[]{"10537", "390-116.222.24.90.", "403-94.97.145."});
        result.add(new Object[]{"10538", "390-116.222.25.91.", "403-94.97.129."});
        result.add(new Object[]{"10539", "390-116.222.25.91.", "403-94.97.130."});
        result.add(new Object[]{"10540", "390-116.222.25.91.", "403-94.97.131."});
        result.add(new Object[]{"10541", "390-116.222.25.91.", "403-94.97.132."});
        result.add(new Object[]{"10542", "390-116.222.25.92.", "403-94.97.129."});
        result.add(new Object[]{"10543", "390-116.222.25.92.", "403-94.97.130."});
        result.add(new Object[]{"10544", "390-116.222.25.92.", "403-94.97.131."});
        result.add(new Object[]{"10545", "390-116.222.25.92.", "403-94.97.132."});
        result.add(new Object[]{"10564", "390-116.222.44.84.", "403-94.97.126."});
        result.add(new Object[]{"10565", "390-116.222.44.86.", "403-94.97.126."});
        result.add(new Object[]{"10566", "390-116.222.44.87.", "403-94.97.126."});
        result.add(new Object[]{"10588", "390-116.222.60.84.", "403-94.97.126."});
        result.add(new Object[]{"10589", "390-116.222.61.84.", "403-94.97.127."});
        result.add(new Object[]{"10597", "390-116.222.1083.84.", "403-94.97.126."});
        result.add(new Object[]{"10674", "390-116.222.61.58318.", "403-94.97.127."});
        result.add(new Object[]{"10678", "390-116.222.61.58319.", "403-94.97.127."});
        result.add(new Object[]{"10682", "390-116.222.61.58320.", "403-94.97.127."});
        result.add(new Object[]{"10686", "390-116.222.61.58322.", "403-94.97.127."});
        result.add(new Object[]{"10690", "390-116.222.61.58323.", "403-94.97.127."});
        result.add(new Object[]{"10694", "390-116.222.61.58325.", "403-94.97.127."});
        result.add(new Object[]{"10710", "390-116.222.23.86.", "403-94.97.127."});
        result.add(new Object[]{"10737", "390-116.222.23.86.", "403-94.97.126."});
        result.add(new Object[]{"10745", "390-116.222.23.86.", "403-94.97.128."});
        return result;
    }

    public String getCPPListWithRsQuery() {
        return " select * from ST_CCP_HIERARCHY_LEELA_KRISH_143_180207;";
    }

    public List<Object[]> getCPPListWithRS() {
        List result = new ArrayList<>();
//        result.add(new Object[]{"10515", "390-116.222.22.84.", "403-94.97.126.", "147"});
//        result.add(new Object[]{"10515", "390-116.222.22.84.", "403-94.97.126.", "150"});
//        result.add(new Object[]{"10516", "390-116.222.22.84.", "403-94.97.137.", "150"});
//        result.add(new Object[]{"10517", "390-116.222.22.84.", "403-94.97.139.", "151"});
//        result.add(new Object[]{"10517", "390-116.222.22.84.", "403-94.97.139.", "158"});
//        result.add(new Object[]{"10518", "390-116.222.22.84.", "403-94.97.140.", "157"});
//        result.add(new Object[]{"10519", "390-116.222.23.86.", "403-94.97.141.", "180"});
//        result.add(new Object[]{"10519", "390-116.222.23.86.", "403-94.97.141.", "181"});
//        result.add(new Object[]{"10597", "390-116.222.1083.84.", "403-94.97.126.", "180"});
//        result.add(new Object[]{"10674", "390-116.222.61.58318.", "403-94.97.127.", "157"});

        result.add(new Object[]{"10515", "390-116.222.22.84.", "403-94.97.126.", "157"});
        result.add(new Object[]{"10516", "390-116.222.22.84.", "403-94.97.137.", "235"});
//        result.add(new Object[]{"10517", "390-116.222.22.84.", "403-94.97.139.", "151"});
//        result.add(new Object[]{"10517", "390-116.222.22.84.", "403-94.97.139.", "158"});
//        result.add(new Object[]{"10518", "390-116.222.22.84.", "403-94.97.140.", "157"});
//        result.add(new Object[]{"10519", "390-116.222.23.86.", "403-94.97.141.", "180"});
//        result.add(new Object[]{"10520", "390-116.222.23.86.", "403-94.97.142.", "181"});
//        result.add(new Object[]{"10523", "390-116.222.24.88.", "403-94.97.127.", "182"});
//        result.add(new Object[]{"10524", "390-116.222.24.88.", "403-94.97.128.", "183"});
//        result.add(new Object[]{"10525", "390-116.222.24.88.", "403-94.97.143.", "184"});
//        result.add(new Object[]{"10526", "390-116.222.24.88.", "403-94.97.144.", "147"});
//        result.add(new Object[]{"10527", "390-116.222.24.88.", "403-94.97.145.", "150"});
//        result.add(new Object[]{"10528", "390-116.222.24.89.", "403-94.97.127.", "151"});
//        result.add(new Object[]{"10529", "390-116.222.24.89.", "403-94.97.128.", "158"});
//        result.add(new Object[]{"10530", "390-116.222.24.89.", "403-94.97.143.", "157"});
//        result.add(new Object[]{"10531", "390-116.222.24.89.", "403-94.97.144.", "180"});
//        result.add(new Object[]{"10532", "390-116.222.24.89.", "403-94.97.145.", "181"});
//        result.add(new Object[]{"10533", "390-116.222.24.90.", "403-94.97.127.", "182"});
//        result.add(new Object[]{"10534", "390-116.222.24.90.", "403-94.97.128.", "183"});
//        result.add(new Object[]{"10535", "390-116.222.24.90.", "403-94.97.143.", "184"});
//        result.add(new Object[]{"10536", "390-116.222.24.90.", "403-94.97.144.", "147"});
//        result.add(new Object[]{"10537", "390-116.222.24.90.", "403-94.97.145.", "150"});
//        result.add(new Object[]{"10538", "390-116.222.25.91.", "403-94.97.129.", "151"});
//        result.add(new Object[]{"10539", "390-116.222.25.91.", "403-94.97.130.", "158"});
//        result.add(new Object[]{"10540", "390-116.222.25.91.", "403-94.97.131.", "157"});
//        result.add(new Object[]{"10541", "390-116.222.25.91.", "403-94.97.132.", "180"});
//        result.add(new Object[]{"10542", "390-116.222.25.92.", "403-94.97.129.", "181"});
//        result.add(new Object[]{"10543", "390-116.222.25.92.", "403-94.97.130.", "182"});
//        result.add(new Object[]{"10544", "390-116.222.25.92.", "403-94.97.131.", "183"});
//        result.add(new Object[]{"10545", "390-116.222.25.92.", "403-94.97.132.", "184"});
//        result.add(new Object[]{"10564", "390-116.222.44.84.", "403-94.97.126.", "157"});
//        result.add(new Object[]{"10565", "390-116.222.44.86.", "403-94.97.126.", "180"});
//        result.add(new Object[]{"10566", "390-116.222.44.87.", "403-94.97.126.", "181"});
//        result.add(new Object[]{"10588", "390-116.222.60.84.", "403-94.97.126.", "182"});
//        result.add(new Object[]{"10589", "390-116.222.61.84.", "403-94.97.127.", "183"});
//        result.add(new Object[]{"10597", "390-116.222.1083.84.", "403-94.97.126.", "180"});
//        result.add(new Object[]{"10674", "390-116.222.61.58318.", "403-94.97.127.", "157"});
//        result.add(new Object[]{"10678", "390-116.222.61.58319.", "403-94.97.127.", "180"});
//        result.add(new Object[]{"10682", "390-116.222.61.58320.", "403-94.97.127.", "181"});
//        result.add(new Object[]{"10686", "390-116.222.61.58322.", "403-94.97.127.", "182"});
//        result.add(new Object[]{"10690", "390-116.222.61.58323.", "403-94.97.127.", "183"});
//        result.add(new Object[]{"10694", "390-116.222.61.58325.", "403-94.97.127.", "184"});
//        result.add(new Object[]{"10710", "390-116.222.23.86.", "403-94.97.127.", "157"});
//        result.add(new Object[]{"10737", "390-116.222.23.86.", "403-94.97.126.", "180"});
//        result.add(new Object[]{"10745", "390-116.222.23.86.", "403-94.97.128.", "181"});
        return result;
    }

    public List<Object[]> getDiscountCCPList() {
        List result = new ArrayList<>();
        result.add(new Object[]{"10515", "147"});
        result.add(new Object[]{"10516", "150"});
        result.add(new Object[]{"10517", "151"});
        result.add(new Object[]{"10518", "157"});
        result.add(new Object[]{"10519", "180"});
        result.add(new Object[]{"10520", "181"});
        result.add(new Object[]{"10523", "182"});
        result.add(new Object[]{"10524", "183"});
        result.add(new Object[]{"10525", "184"});
        return result;
    }

    public List<Object[]> getDiscountSubList() {
        List result = new ArrayList<>();
        result.add(new Object[]{"147", "627", "630", "RS_12"});
        result.add(new Object[]{"150", "627", "630", "RS_13"});
        result.add(new Object[]{"151", "627", "630", "RS_14"});
        result.add(new Object[]{"157", "627", "630", "RS_2"});
        result.add(new Object[]{"180", "627", "630", "RS_13"});
        result.add(new Object[]{"181", "627", "630", "RS_1"});
        result.add(new Object[]{"182", "627", "630", "RS_1"});
        result.add(new Object[]{"183", "627", "630", "RS_1"});
        return result;
    }

    public List<Object[]> getSelectedCust() {
//        return "IF Object_id('TEMPDB..#SELECTED_HIERARCHY_NO') IS NOT NULL\n"
//                + "                DROP TABLE #SELECTED_HIERARCHY_NO\n"
//                + "                CREATE TABLE #SELECTED_HIERARCHY_NO (HIERARCHY_NO varchar(50))\n"
//                + "\n"
//                + "                INSERT INTO #SELECTED_HIERARCHY_NO\n"
//                + "                (HIERARCHY_NO)\n"
//                + "                SELECT DISTINCT HIERARCHY_NO\n"
//                + "                FROM  (VALUES ('390-116.'),('390-116.222.'),('390-116.222.44.'),('390-116.222.60.'),('390-116.222.25.'),('390-116.222.24.'),('390-116.222.23.'),('390-116.222.22.'),('390-116.222.61.')\n"
//                + ",('390-116.222.1083.'),('390-116.222.61.58318.'),('390-116.222.61.58319.'),('390-116.222.61.58320.'),('390-116.222.25.92.'),('390-116.222.25.91.'),('390-116.222.24.90.')\n"
//                + ",('390-116.222.24.89.'),('390-116.222.24.88.'),('390-116.222.44.87.'),('390-116.222.23.86.'),('390-116.222.44.86.'),('390-116.222.1083.84.'),('390-116.222.22.84.')\n"
//                + ",('390-116.222.44.84.'),('390-116.222.60.84.'),('390-116.222.61.84.'),('390-116.222.61.58322.'),('390-116.222.61.58325.'),('390-116.222.61.58323.'))A(HIERARCHY_NO)\n"
//                + "                SELECT\n"
//                + "                   distinct  LEVEL_NO,\n"
//                + "                     LEVEL_NAME,\n"
//                + "                     RLD.HIERARCHY_NO,\n"
//                + "                     RELATIONSHIP_LEVEL_VALUES\n"
//                + "                FROM\n"
//                + "                    RELATIONSHIP_LEVEL_DEFINITION RLD\n"
//                + "                JOIN #SELECTED_HIERARCHY_NO SH\n"
//                + "                    ON RLD.HIERARCHY_NO LIKE SH.HIERARCHY_NO\n"
//                + "                WHERE\n"
//                + "                    RLD.RELATIONSHIP_BUILDER_SID = 390 AND\n"
//                + "                    RLD.VERSION_NO = 4 AND\n"
//                + "                    RLD.LEVEL_NO >= 2\n"
//                + "                ORDER BY\n"
//                + "                    RLD.LEVEL_NO, RLD.HIERARCHY_NO;";
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[]{"2", "Market Type", "390-116.222.", "222"});
        result.add(new Object[]{"3", "Contract", "390-116.222.1083.", "1083"});
        result.add(new Object[]{"3", "Contract", "390-116.222.22.", "22"});
        result.add(new Object[]{"3", "Contract", "390-116.222.23.", "23"});
        result.add(new Object[]{"3", "Contract", "390-116.222.24.", "24"});
        result.add(new Object[]{"3", "Contract", "390-116.222.25.", "25"});
        result.add(new Object[]{"3", "Contract", "390-116.222.44.", "44"});
        result.add(new Object[]{"3", "Contract", "390-116.222.60.", "60"});
        result.add(new Object[]{"3", "Contract", "390-116.222.61.", "61"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.1083.84.", "84"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.22.84.", "84"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.23.86.", "86"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.24.88.", "88"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.24.89.", "89"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.24.90.", "90"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.25.91.", "91"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.25.92.", "92"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.44.84.", "84"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.44.86.", "86"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.44.87.", "87"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.60.84.", "84"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.61.58318.", "58318"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.61.58319.", "58319"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.61.58320.", "58320"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.61.58322.", "58322"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.61.58323.", "58323"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.61.58325.", "58325"});
        result.add(new Object[]{"4", "Trading Partner", "390-116.222.61.84.", "84"});
        return result;
    }

    public List<Object[]> getSelectedProd() {
//        return " IF Object_id('TEMPDB..#SELECTED_HIERARCHY_NO') IS NOT NULL\n"
//                + "                DROP TABLE #SELECTED_HIERARCHY_NO\n"
//                + "                CREATE TABLE #SELECTED_HIERARCHY_NO (HIERARCHY_NO varchar(50))\n"
//                + "\n"
//                + "                INSERT INTO #SELECTED_HIERARCHY_NO\n"
//                + "                (HIERARCHY_NO)\n"
//                + "                SELECT DISTINCT HIERARCHY_NO\n"
//                + "                FROM  (VALUES ('403-94.'),('403-94.97.'),('403-94.97.145.'),('403-94.97.144.'),('403-94.97.143.'),('403-94.97.142.'),('403-94.97.141.'),('403-94.97.140.'),('403-94.97.139.'),\n"
//                + "('403-94.97.137.'),('403-94.97.132.'),('403-94.97.131.'),('403-94.97.130.'),('403-94.97.129.'),('403-94.97.128.'),('403-94.97.127.'),('403-94.97.126.'))A(HIERARCHY_NO)\n"
//                + "                SELECT\n"
//                + "                   distinct  LEVEL_NO,\n"
//                + "                     LEVEL_NAME,\n"
//                + "                     RLD.HIERARCHY_NO,\n"
//                + "                     RELATIONSHIP_LEVEL_VALUES\n"
//                + "                FROM\n"
//                + "                    RELATIONSHIP_LEVEL_DEFINITION RLD\n"
//                + "                JOIN #SELECTED_HIERARCHY_NO SH\n"
//                + "                    ON RLD.HIERARCHY_NO LIKE SH.HIERARCHY_NO\n"
//                + "                WHERE\n"
//                + "                    RLD.RELATIONSHIP_BUILDER_SID = 403 AND\n"
//                + "                    RLD.VERSION_NO = 3 AND\n"
//                + "                    RLD.LEVEL_NO >= 2\n"
//                + "                ORDER BY\n"
//                + "                    RLD.LEVEL_NO, RLD.HIERARCHY_NO;";
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[]{"2", "Brand", "403-94.97.", "97"});
        result.add(new Object[]{"3", "NDC", "403-94.97.126.", "126"});
        result.add(new Object[]{"3", "NDC", "403-94.97.127.", "127"});
        result.add(new Object[]{"3", "NDC", "403-94.97.128.", "128"});
        result.add(new Object[]{"3", "NDC", "403-94.97.129.", "129"});
        result.add(new Object[]{"3", "NDC", "403-94.97.130.", "130"});
        result.add(new Object[]{"3", "NDC", "403-94.97.131.", "131"});
        result.add(new Object[]{"3", "NDC", "403-94.97.132.", "132"});
        result.add(new Object[]{"3", "NDC", "403-94.97.137.", "137"});
        result.add(new Object[]{"3", "NDC", "403-94.97.139.", "139"});
        result.add(new Object[]{"3", "NDC", "403-94.97.140.", "140"});
        result.add(new Object[]{"3", "NDC", "403-94.97.141.", "141"});
        result.add(new Object[]{"3", "NDC", "403-94.97.142.", "142"});
        result.add(new Object[]{"3", "NDC", "403-94.97.143.", "143"});
        result.add(new Object[]{"3", "NDC", "403-94.97.144.", "144"});
        result.add(new Object[]{"3", "NDC", "403-94.97.145.", "145"});
        return result;
    }

    public String getCustomerMap() {
        return ";WITH CTE AS( \n"
                + "             \n"
                + "                SELECT\n"
                + "                    RLD.HIERARCHY_NO,\n"
                + "                    TEMP.LEVEL_VALUES VALUE,\n"
                + "                    LEVEL_NAME,\n"
                + "                    LEVEL_NO,\n"
                + "                    RELATIONSHIP_LEVEL_VALUES\n"
                + "                     ,NULL AS COLUMN_0 ,NULL AS COLUMN_1,\n"
                + "                    0 AS SID\n"
                + "                FROM\n"
                + "                    RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "                JOIN ST_CCP_HIERARCHY_10922_011855842_180108 CH ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO+'%'\n"
                + "                JOIN HIERARCHY_LEVEL_VALUES TEMP ON\n"
                + "                                TEMP.HIERARCHY_LEVEL_VALUES_SID = RLD.RELATIONSHIP_LEVEL_VALUES\n"
                + "                                AND RLD.RELATIONSHIP_BUILDER_SID = 390\n"
                + "                                AND RLD.LEVEL_NO = 1\n"
                + "                                AND RLD.VERSION_NO = 4\n"
                + "                                \n"
                + "            \n"
                + "		 UNION ALL  \n"
                + "             \n"
                + "                SELECT\n"
                + "                    RLD.HIERARCHY_NO,\n"
                + "                    TEMP.DESCRIPTION VALUE,\n"
                + "                    LEVEL_NAME,\n"
                + "                    LEVEL_NO,\n"
                + "                    RELATIONSHIP_LEVEL_VALUES\n"
                + "                     ,NULL AS COLUMN_0 ,NULL AS COLUMN_1,\n"
                + "                    1 AS SID\n"
                + "                FROM\n"
                + "                    RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "                JOIN ST_CCP_HIERARCHY_10922_011855842_180108 CH ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO+'%'\n"
                + "                JOIN HELPER_TABLE TEMP ON\n"
                + "                                TEMP.HELPER_TABLE_SID = RLD.RELATIONSHIP_LEVEL_VALUES\n"
                + "                                AND RLD.RELATIONSHIP_BUILDER_SID = 390\n"
                + "                                AND RLD.LEVEL_NO = 2\n"
                + "                                AND RLD.VERSION_NO = 4\n"
                + "                                \n"
                + "            \n"
                + "		 UNION ALL  \n"
                + "             \n"
                + "                SELECT\n"
                + "                    RLD.HIERARCHY_NO,\n"
                + "                    TEMP.CONTRACT_NAME VALUE,\n"
                + "                    LEVEL_NAME,\n"
                + "                    LEVEL_NO,\n"
                + "                    RELATIONSHIP_LEVEL_VALUES\n"
                + "                    , TEMP.CONTRACT_NAME AS COLUMN_16, TEMP.CONTRACT_NO AS COLUMN_17,\n"
                + "                    2 AS SID\n"
                + "                FROM\n"
                + "                    RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "                JOIN ST_CCP_HIERARCHY_10922_011855842_180108 CH ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO+'%'\n"
                + "                JOIN CONTRACT_MASTER TEMP ON\n"
                + "                                TEMP.CONTRACT_MASTER_SID = RLD.RELATIONSHIP_LEVEL_VALUES\n"
                + "                                AND RLD.RELATIONSHIP_BUILDER_SID = 390\n"
                + "                                AND RLD.LEVEL_NO = 3\n"
                + "                                AND RLD.VERSION_NO = 4\n"
                + "                                \n"
                + "            \n"
                + "		 UNION ALL  \n"
                + "             \n"
                + "                SELECT\n"
                + "                    RLD.HIERARCHY_NO,\n"
                + "                    TEMP.COMPANY_NAME VALUE,\n"
                + "                    LEVEL_NAME,\n"
                + "                    LEVEL_NO,\n"
                + "                    RELATIONSHIP_LEVEL_VALUES\n"
                + "                    , TEMP.COMPANY_NAME AS COLUMN_3, TEMP.COMPANY_NO AS COLUMN_4,\n"
                + "                    3 AS SID\n"
                + "                FROM\n"
                + "                    RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "                JOIN ST_CCP_HIERARCHY_10922_011855842_180108 CH ON CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO+'%'\n"
                + "                JOIN COMPANY_MASTER TEMP ON\n"
                + "                                TEMP.COMPANY_MASTER_SID = RLD.RELATIONSHIP_LEVEL_VALUES\n"
                + "                                AND RLD.RELATIONSHIP_BUILDER_SID = 390\n"
                + "                                AND RLD.LEVEL_NO = 4\n"
                + "                                AND RLD.VERSION_NO = 4\n"
                + "                                \n"
                + "            \n"
                + "		) SELECT Distinct * FROM CTE ORDER BY SID,VALUE DESC";
    }

    public String getProductMap() {
        return ";WITH CTE AS( \n"
                + "             \n"
                + "                SELECT\n"
                + "                    RLD.HIERARCHY_NO,\n"
                + "                    TEMP.COMPANY_NAME VALUE,\n"
                + "                    LEVEL_NAME,\n"
                + "                    LEVEL_NO,\n"
                + "                    RELATIONSHIP_LEVEL_VALUES\n"
                + "                    , TEMP.COMPANY_NAME AS COLUMN_3, TEMP.COMPANY_NO AS COLUMN_4,\n"
                + "                    0 AS SID\n"
                + "                FROM\n"
                + "                    RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "                JOIN ST_CCP_HIERARCHY_10922_011855842_180108 CH ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO+'%'\n"
                + "                JOIN COMPANY_MASTER TEMP ON\n"
                + "                                TEMP.COMPANY_MASTER_SID = RLD.RELATIONSHIP_LEVEL_VALUES\n"
                + "                                AND RLD.RELATIONSHIP_BUILDER_SID = 403\n"
                + "                                AND RLD.LEVEL_NO = 1\n"
                + "                                AND RLD.VERSION_NO = 3\n"
                + "                                \n"
                + "            \n"
                + "		 UNION ALL  \n"
                + "             \n"
                + "                SELECT\n"
                + "                    RLD.HIERARCHY_NO,\n"
                + "                    TEMP.BRAND_NAME VALUE,\n"
                + "                    LEVEL_NAME,\n"
                + "                    LEVEL_NO,\n"
                + "                    RELATIONSHIP_LEVEL_VALUES\n"
                + "                     ,NULL AS COLUMN_0 ,NULL AS COLUMN_1,\n"
                + "                    1 AS SID\n"
                + "                FROM\n"
                + "                    RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "                JOIN ST_CCP_HIERARCHY_10922_011855842_180108 CH ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO+'%'\n"
                + "                JOIN BRAND_MASTER TEMP ON\n"
                + "                                TEMP.BRAND_MASTER_SID = RLD.RELATIONSHIP_LEVEL_VALUES\n"
                + "                                AND RLD.RELATIONSHIP_BUILDER_SID = 403\n"
                + "                                AND RLD.LEVEL_NO = 2\n"
                + "                                AND RLD.VERSION_NO = 3\n"
                + "                                \n"
                + "            \n"
                + "		 UNION ALL  \n"
                + "             \n"
                + "                SELECT\n"
                + "                    RLD.HIERARCHY_NO,\n"
                + "                    TEMP.ITEM_NAME VALUE,\n"
                + "                    LEVEL_NAME,\n"
                + "                    LEVEL_NO,\n"
                + "                    RELATIONSHIP_LEVEL_VALUES\n"
                + "                    , TEMP.ITEM_NAME AS COLUMN_10, TEMP.ITEM_NO AS COLUMN_11,\n"
                + "                    2 AS SID\n"
                + "                FROM\n"
                + "                    RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "                JOIN ST_CCP_HIERARCHY_10922_011855842_180108 CH ON CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO+'%'\n"
                + "                JOIN ITEM_MASTER TEMP ON\n"
                + "                                TEMP.ITEM_MASTER_SID = RLD.RELATIONSHIP_LEVEL_VALUES\n"
                + "                                AND RLD.RELATIONSHIP_BUILDER_SID = 403\n"
                + "                                AND RLD.LEVEL_NO = 3\n"
                + "                                AND RLD.VERSION_NO = 3\n"
                + "                                \n"
                + "            \n"
                + "		) SELECT * FROM CTE ORDER BY SID,VALUE DESC";
    }

    public String getDeductionQuery() {
        return "select RS_CONTRACT_SID, RS_CATEGORY, RS_TYPE, REBATE_PROGRAM_TYPE, U.UDC1,U.UDC2, U.UDC3, U.UDC4, U.UDC5, U.UDC6, RS.RS_ID\n"
                + "    from dbo.RS_CONTRACT RS\n"
                + "    JOIN dbo.UDCS U ON U.MASTER_SID = RS.RS_CONTRACT_SID AND U.MASTER_TYPE = 'RS_CONTRACT'\n"
                + "    where RS_CONTRACT_SID in (147,150,151,157,180,181,182,183,184);";
    }

    public List<Object[]> getDeductionQueryList() {
        List<Object[]> results = new ArrayList<>();
        results.add(new Object[]{"147", "628", "627", "630", "12363", "12358", "12359", "12360", "12361", "12362", "RS_12"});
        results.add(new Object[]{"150", "628", "627", "630", "12363", "12358", "12359", "12360", "12361", "12362", "RS_13"});
        results.add(new Object[]{"151", "628", "627", "630", "12363", "12358", "12359", "12360", "12361", "12362", "RS_14"});
        results.add(new Object[]{"157", "628", "627", "630", "12363", "12358", "12359", "12360", "12361", "12362", "RS_2"});
        results.add(new Object[]{"158", "6281", "6271", "6301", "12363", "12358", "12359", "12360", "12361", "12363", "RS_2"});
        results.add(new Object[]{"180", "6281", "6271", "6301", "12363", "12358", "12359", "12360", "12361", "12363", "RS_13"});
        results.add(new Object[]{"181", "6281", "6271", "6301", "12363", "12358", "12359", "12360", "12361", "12363", "RS_1"});
        results.add(new Object[]{"182", "6281", "6271", "6301", "12363", "12358", "12359", "12360", "12361", "12363", "RS_1"});
        results.add(new Object[]{"183", "6281", "6271", "6301", "12363", "12358", "12359", "12360", "12361", "12363", "RS_1"});
        results.add(new Object[]{"184", "628", "627", "630", "12363", "12358", "12359", "12360", "12361", "12363", "RS_1"});
        results.add(new Object[]{"235", "628", "627", "6301", "12363", "12358", "12359", "12360", "12361", "12362", "RS_12"});
        return results;
    }

    /**
     * 0 - Level No 1 - Indicator 2 - Level Name 3 - Custom view Level NO
     *
     * @return
     */
    public List<Object[]> getCustomViewLevel(int i) {
        List<Object[]> custom = new ArrayList<>();
        Object[] obj = null;
        Object[] obj1 = null;
        Object[] obj2 = null;
        Object[] obj3 = null;
        switch (i) {
            case 0:
                obj = new Object[]{3, "C", "Contract", 1};
                obj1 = new Object[]{2, "P", "Brand", 2};
                obj2 = new Object[]{4, "C", "TP", 3};
                obj3 = new Object[]{3, "P", "Item", 4};
                custom.add(obj);
                custom.add(obj1);
                custom.add(obj2);
                custom.add(obj3);
                break;
            case 1:
                obj = new Object[]{3, "C", "Contract", 1};
                obj1 = new Object[]{4, "C", "TP", 2};
                obj2 = new Object[]{2, "P", "Brand", 3};
                obj3 = new Object[]{3, "P", "Item", 4};
                custom.add(obj);
                custom.add(obj1);
                custom.add(obj2);
                custom.add(obj3);
                break;
            case 2:
                obj = new Object[]{4, "C", "TP", 1};
                obj1 = new Object[]{3, "C", "Contract", 2};
                custom.add(obj);
                custom.add(obj1);
                break;
            case 3:
                obj2 = new Object[]{2, "P", "Brand", 1};
                obj3 = new Object[]{3, "P", "Item", 2};
                custom.add(obj2);
                custom.add(obj3);
                break;
            case 4:
                obj = new Object[]{2, "P", "Brand", 1};
                obj1 = new Object[]{3, "P", "Item", 2};
                obj2 = new Object[]{3, "C", "Contract", 3};
                obj3 = new Object[]{4, "C", "TP", 4};

                custom.add(obj);
                custom.add(obj1);
                custom.add(obj2);
                custom.add(obj3);
                break;
            case 5:
                obj = new Object[]{3, "C", "Contract", 1};
                obj1 = new Object[]{2, "P", "Brand", 2};
                obj2 = new Object[]{3, "P", "Item", 3};
                obj3 = new Object[]{4, "C", "TP", 4};
                custom.add(obj);
                custom.add(obj1);
                custom.add(obj2);
                custom.add(obj3);
                break;
            default:
                break;
        }
        return custom;
    }

    /**
     * 0 - Level No 1 - Indicator 2 - Level Name 3 - Custom view Level NO
     *
     * @return
     */
    public List<Object[]> getCustomViewWithDiscountLevel(int i) {
        List<Object[]> custom = new ArrayList<>();
        Object[] obj = null;
        switch (i) {
            case 0:
                obj = new Object[]{3, "D", "Program Type", 1};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 2};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 3};
                custom.add(obj);
                break;
            case 1:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 2};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 3};
                custom.add(obj);
                break;
            case 2:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 2};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 3};
                custom.add(obj);
                break;
            case 3:
                obj = new Object[]{3, "D", "Program Type", 1};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 2};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 3};
                custom.add(obj);
                break;
            case 4:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 2};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 3};
                custom.add(obj);
                break;
            case 5:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 2};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 3};
                custom.add(obj);
                break;
            case 6:
                obj = new Object[]{3, "D", "Program Type", 1};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 2};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 3};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 4};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 5};
                custom.add(obj);
                break;
            case 7:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 2};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 3};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 4};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 5};
                custom.add(obj);
                break;
            case 8:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 2};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 3};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 4};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 5};
                custom.add(obj);
                break;
            case 9:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 2};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 3};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 4};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 5};
                custom.add(obj);
                break;

            case 10:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 2};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 3};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 4};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 5};
                custom.add(obj);
                break;
            case 11:
                obj = new Object[]{3, "D", "Program Type", 5};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 2};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 3};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 4};
                custom.add(obj);
                break;
            case 12:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 2};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 3};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 4};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 5};
                custom.add(obj);
                break;
            case 13:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 2};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 3};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 4};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 5};
                custom.add(obj);
                break;
            case 14:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 2};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 3};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 5};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 4};
                custom.add(obj);
                break;
            case 15:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 2};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 3};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 4};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 5};
                custom.add(obj);
                break;
            case 16:
                obj = new Object[]{3, "D", "Program Type", 1};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 2};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 3};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 4};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 5};
                custom.add(obj);
                break;
            case 17:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 2};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 3};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 4};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 5};
                custom.add(obj);
                break;
            case 18:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 2};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 3};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 4};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 5};
                custom.add(obj);
                break;
            case 19:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 2};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 3};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 4};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 5};
                custom.add(obj);
                break;
            case 20:
                obj = new Object[]{3, "C", "Contract", 1};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 2};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 3};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 4};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 5};
                custom.add(obj);
                break;
            case 21:
                obj = new Object[]{3, "D", "Program Type", 1};
                custom.add(obj);
                obj = new Object[]{2, "P", "Brand", 2};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 3};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 4};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 5};
                custom.add(obj);
                break;
            case 22:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 2};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 3};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 4};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 5};
                custom.add(obj);
                break;
            case 23:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 2};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 2};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 4};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 5};
                custom.add(obj);
                break;
            case 24:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 2};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 3};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 4};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 5};
                custom.add(obj);
                break;
            case 25:
                obj = new Object[]{2, "P", "Brand", 1};
                custom.add(obj);
                obj = new Object[]{3, "C", "Contract", 2};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 3};
                custom.add(obj);
                obj = new Object[]{4, "C", "TP", 4};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 5};
                custom.add(obj);
                break;

            case 26:
                obj = new Object[]{1, "D", "Schedule Category", 1};
                custom.add(obj);
                obj = new Object[]{2, "D", "Schedule Type", 2};
                custom.add(obj);
                obj = new Object[]{3, "D", "Program Type", 3};
                custom.add(obj);
                obj = new Object[]{4, "D", "UDC 1", 4};
                custom.add(obj);
                obj = new Object[]{5, "D", "UDC 2", 5};
                custom.add(obj);
                obj = new Object[]{6, "D", "UDC 3", 6};
                custom.add(obj);
                obj = new Object[]{7, "D", "UDC 4", 7};
                custom.add(obj);
                obj = new Object[]{8, "D", "UDC 5", 8};
                custom.add(obj);
                obj = new Object[]{9, "D", "UDC 6", 9};
                custom.add(obj);
                obj = new Object[]{0, "D", "Schedule ID", 10};
                custom.add(obj);
                break;

            case 27:
                obj = new Object[]{3, "D", "Program Type", 1};
                custom.add(obj);
                obj = new Object[]{4, "D", "UDC 1", 2};
                custom.add(obj);
                obj = new Object[]{3, "P", "Item", 3};
                custom.add(obj);

            default:
                break;
        }
        return custom;
    }
}
