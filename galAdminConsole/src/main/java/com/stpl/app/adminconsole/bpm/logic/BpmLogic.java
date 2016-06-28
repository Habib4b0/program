package com.stpl.app.adminconsole.bpm.logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.drools.compiler.kie.builder.impl.InternalKieScanner;
import org.drools.compiler.kproject.ReleaseIdImpl;
import org.drools.workbench.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.workbench.models.guided.dtable.shared.model.DTCellValue52;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.eclipse.aether.artifact.Artifact;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.kie.scanner.MavenRepository;

import com.stpl.app.adminconsole.bpm.dto.VwHelperListDto;
import com.stpl.app.adminconsole.bpm.service.BPMManagerBean;
import com.stpl.app.adminconsole.bpm.util.BPMCommonUtils;
import com.stpl.app.adminconsole.dao.RelationBuilderLogicDAO;
import com.stpl.app.adminconsole.dao.impl.RelationBuilderLogicDAOImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.HistHierarchyDefinition;
import com.stpl.app.adminconsole.relationshipbuilder.dto.HierarchyLevelsDTO;
import com.stpl.app.adminconsole.relationshipbuilder.util.CommonUtils;
import com.stpl.app.service.HierarchyLevelDefinitionLocalServiceUtil;
import com.stpl.app.service.HistRelationshipBuilderLocalServiceUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.bpm.dto.HeirarchyDefinition;
import com.stpl.app.bpm.dto.HierarchyDefinitionLevelRuleDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import com.vaadin.server.Page;
import org.jboss.logging.Logger;

public class BpmLogic {

    private static Map<String, String> conditions = new HashMap<String, String>();
    private static Map<Integer, String> inclusionExclusionRules = new HashMap<Integer, String>();
    private static Map<String, VwHelperListDto> dependenciesMap = new HashMap<String, VwHelperListDto>();
    private static Set<String> tableNames = new HashSet<String>();
    private static Set<String> columnNames = new HashSet<String>();
    public static RelationBuilderLogicDAO dao = new RelationBuilderLogicDAOImpl();
    private static final String NULL = "null";
    private static final Logger LOGGER = Logger.getLogger(BpmLogic.class);

    public static Map<Integer, String> getInclusionExclusionRules() {
        return inclusionExclusionRules;
    }

    public static void setInclusionExclusionRules(Map<Integer, String> inclusionExclusionRules) {
        BpmLogic.inclusionExclusionRules = inclusionExclusionRules;
    }

    static {

        conditions.put("equal to", "=");
        conditions.put("starts with", "like");
        conditions.put("ends with", "like");
        conditions.put("greater than", ">");
        conditions.put("greater than or equal to", ">=");
        conditions.put("lesser than", "<");
        conditions.put("lesser than or equal to", "<=");
        conditions.put("in", "in");
        conditions.put("not in", "not in");
        conditions.put("contains", "like");
    }

    public static List<String> getHierarchies() {
        JarFile jar;
        List<String> hierarchies = new ArrayList<String>();
        try {
            jar = new JarFile(BPMManagerBean.getEngine().getArtifact().getFile());
            Enumeration<JarEntry> sf = jar.entries();
            while (sf.hasMoreElements()) {
                JarEntry temp = sf.nextElement();
                if (!temp.isDirectory()) {
                    if (temp.getName().endsWith(".drl")) {
                        String hierarcy = temp.getName().replace("%20", " ").replace(".drl", ConstantsUtils.EMPTY);
                        hierarchies.add(hierarcy);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return hierarchies;
    }

    public static List<HeirarchyDefinition> getHierarchyLevels(String hierarchyName) {
        JarFile jar;
        List<HeirarchyDefinition> hierarchies = new ArrayList<HeirarchyDefinition>();
        String fileName = hierarchyName.replace(" ", "%20") + ".drl";
        try {
            jar = new JarFile(BPMManagerBean.getEngine().getArtifact().getFile());
            Enumeration<JarEntry> sf = jar.entries();
            while (sf.hasMoreElements()) {
                JarEntry temp = sf.nextElement();
                if (!temp.isDirectory()) {
                    if (temp.getName().equals(fileName)) {
                        LOGGER.info("Inside Hierarchy");
                        InputStream is = jar.getInputStream(temp);
                        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
                        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
                        kbuilder.add(ResourceFactory.newInputStreamResource(is), ResourceType.DRL);
                        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
                        kbase.addKnowledgePackages(pkgs);
                        StatefulKnowledgeSession kiesession = kbase.newStatefulKnowledgeSession();
                        int totalLevels = kiesession.getKieBase().getKiePackage("defaultpkg").getRules().size();
                        for (int i = 0; i < totalLevels; i++) {
                            kiesession = kbase.newStatefulKnowledgeSession();
                            HeirarchyDefinition hd = new HeirarchyDefinition();
                            hd.setLevelNo(i + 1);
                            kiesession.insert(hd);
                            kiesession.fireAllRules();
                            hierarchies.add(hd);
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return hierarchies;
    }


    private static String buildQuery(HierarchyDefinitionLevelRuleDTO dto, String ruleType) {
        String query = ConstantsUtils.EMPTY;

        if ("Exclusion".equals(ruleType)) {
            query = " not ";
        }
        boolean startsWith = dto.getCondition1().equals("starts with") || dto.getCondition1().equals("contains") ? true : false;
        boolean endsWith = dto.getCondition1().equals("ends with") || dto.getCondition1().equals("contains") ? true : false;
        if ("in".equals(dto.getCondition1()) && StringUtils.isNotBlank(dto.getValue1())) {
            dto.setValue1("('" + dto.getValue1().replace(",", "','") + "')");
        }
        addTableName(dto.getDimension());
        addColumnName(dto.getAttribute());
        query += " ( " + BPMCommonUtils.REPLACE_STRING + dto.getAttribute() + " " + conditions.get(dto.getCondition1()) + " '" + (endsWith ? "%" :ConstantsUtils.EMPTY) + dto.getValue1() + (startsWith ? "%" : ConstantsUtils.EMPTY) + "' ) ";
        return query;
    }

    private static String buildQuery(List<HierarchyDefinitionLevelRuleDTO> dtoList, String ruleType, String condition) {
        String query = ConstantsUtils.EMPTY;
        int i = 1;
        for (HierarchyDefinitionLevelRuleDTO dto : dtoList) {
            if (i > 1 && !query.equals(ConstantsUtils.EMPTY)) {
                if ("OR".equals(condition)) {
                    query += " or ";
                } else {
                    query += " and ";
                }
            }
            String subQuery = buildQuery(dto, ruleType);
            if (query.equals(ConstantsUtils.EMPTY) && !subQuery.equals(ConstantsUtils.EMPTY)) {
                query += "(";
            }
            query += subQuery;
            i++;
        }
        if (StringUtils.isNotBlank(query)) {
            query += ")";
        }
        return query;
    }

    /*
     * public static Map<Integer, List<String>>
     * getInclusionExclusionRules(String hierarchyDefName) { Map<Integer,
     * List<String>> inclusionExclusionRules = new HashMap<Integer,
     * List<String>>(); Integer marketType=2; Integer contract =3; Integer
     * tradingPartner=4; List<String> rules = new ArrayList<String>(); String
     * inclusionRule = "(CONTRACT_TYPE = 873)"; String exclusionRule =
     * "  (CONTRACT_NO = '10001')"; rules.add(inclusionRule);
     * rules.add(exclusionRule); inclusionExclusionRules.put(marketType, rules);
     * rules = new ArrayList<String>(); inclusionRule = "(CONTRACT_TYPE = 873)";
     * exclusionRule = "  (CONTRACT_NO = '10001')"; rules.add(inclusionRule);
     * rules.add(exclusionRule); inclusionExclusionRules.put(contract, rules);
     * rules = new ArrayList<String>(); inclusionRule = " (COMPANY_TYPE = 25) ";
     * exclusionRule = " (COMPANY_CATEGORY is null) "; rules.add(inclusionRule);
     * rules.add(exclusionRule); inclusionExclusionRules.put(tradingPartner,
     * rules); return inclusionExclusionRules; }
     */
    public static List<String> getRuleList(String ruleGroup) {
        try {
            List<String> ruleList = new ArrayList<String>();
            KieServices ks = KieServices.Factory.get();
            ReleaseId releaseId = ks.newReleaseId(BPMCommonUtils.HR_GROUP, BPMCommonUtils.HR_ARTIFACT_ID, BPMCommonUtils.HR_VERSION);
            Artifact artifact = MavenRepository.getMavenRepository().resolveArtifact(releaseId);
            LOGGER.info("inside getRuleList method :");
            List<String> fileList = getFilePath(artifact);
            for (String resource : fileList) {
                getRuleListByFile(ruleList, resource, ruleGroup);
            }
            return ruleList;
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {

        }

        return null;
    }

    public static List<String> getFilePath(Artifact artifact) {
        List<String> fileList = new ArrayList<String>();
        JarFile jar = null;
        File file = null;
        InputStream is = null;
        try {
            file = artifact.getFile();
            jar = new JarFile(file);
            Enumeration<JarEntry> sf = jar.entries();
            while (sf.hasMoreElements()) {
                JarEntry temp = sf.nextElement();
                if (!temp.isDirectory()) {
                    if (temp.getName().endsWith(".gdst")) {
                        if (!temp.getName().startsWith("HierarchyDefinitionRules")) {
                            is = jar.getInputStream(temp);
                            fileList.add(IOUtils.toString(is));
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
        } finally {
            try {
                jar.close();

            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        return fileList;
    }

    public static List<String> getRuleListByFile(List<String> ruleList, final String resource, String columnName) {

        try {
            GuidedDecisionTable52 dcf = GuidedDTXMLPersistence.getInstance().unmarshal(resource);
            List<List<DTCellValue52>> rowList = dcf.getData();

            for (int k = 1; k <= rowList.size(); k++) {
                List<DTCellValue52> list = rowList.get(k - 1);
                boolean rgFlag = false;
                for (int i = 0; i < list.size(); i++) {
                    DTCellValue52 dtCellValue52 = list.get(i);
                    if (i == 2) {
                        if (columnName.equals(dtCellValue52.getStringValue())) {
                            rgFlag = true;
                        }
                    }
                    if (i == 4 && rgFlag) {
                        if (!ruleList.contains(dtCellValue52.getStringValue())) {
                            ruleList.add(dtCellValue52.getStringValue());
                        }
                    }
                }
            }
            LOGGER.info("Rule List value are--------" + ruleList);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return ruleList;
    }

    public static List<HierarchyLevelsDTO> getFilteredValues(int selectedHierarchySysId, int version, int levelNo, List<String> levelValues) {
        List<HierarchyLevelsDTO> filteredValues = new ArrayList<HierarchyLevelsDTO>();
        try {
            levelNo = levelNo + 1;
            String levelName = ConstantsUtils.EMPTY;
            int levelSystemId = 0;
            DynamicQuery dynQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
            dynQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", selectedHierarchySysId));
            dynQuery.add(RestrictionsFactoryUtil.le("levelNo", levelNo));
            dynQuery.addOrder(OrderFactoryUtil.desc("levelNo"));
            List<HierarchyLevelDefinition> hierarchyLevels = HierarchyLevelDefinitionLocalServiceUtil.dynamicQuery(dynQuery);
            String framedQuery = ConstantsUtils.EMPTY;
            String tableQuery = ConstantsUtils.EMPTY;
            String whereQuery = ConstantsUtils.EMPTY;
            Map<String, String> tableNameMap = new LinkedHashMap<String, String>();
            LOGGER.info("Received Hierarchy with ID: " + selectedHierarchySysId + " with levelNo: " + levelNo + " having " + levelValues);
            boolean flag = false;
            for (int i = 0; i < hierarchyLevels.size(); i++) {
                HierarchyLevelDefinition hd = hierarchyLevels.get(i);
                if (i == 0 && "User Defined".equals(hd.getLevelValueReference())) {
                    return null;
                } else {
                    if (i == 0) {
                        levelName = hd.getLevelName();
                        levelSystemId = hd.getHierarchyLevelDefinitionSid();
                    }
                    if (hd.getTableName() != null && StringUtils.isNotBlank(hd.getTableName())) {
                        String tablealias = tableNameMap.get(hd.getTableName());
                        String temp = ConstantsUtils.EMPTY;
                        if ("CONTRACT_MASTER".equals(hd.getTableName())) {
                            tablealias = "CM";
                            temp = "Contract";
                        } else if ("COMPANY_MASTER".equals(hd.getTableName()) && ("Trading Partner".equals(hd.getLevelName()) || "Customer".equals(hd.getLevelName()))) {
                            tablealias = "TP";
                            temp = "Trading Partner";
                        } else if ("COMPANY_MASTER".equals(hd.getTableName()) && "Contract Holder".equals(hd.getLevelName())) {
                            tablealias = "CH";
                            temp = "Contract Holder";
                        } else if ("ITEM_MASTER".equals(hd.getTableName())) {
                            tablealias = "IM";
                            temp = "Product";
                        } else if ("COMPANY_MASTER".equals(hd.getTableName())) {
                            tablealias = "CM";
                            temp = "GL Company";
                        } else {
                            tablealias = hd.getTableName().substring(0, 1) + ConstantsUtils.EMPTY + i;
                            temp = hd.getTableName();
                        }
                        if (i != 0 && tableNameMap.get(temp) == null) {
                            if (!flag) {
                                tableQuery += getJoinCondition(hd.getTableName(), tablealias, tableNameMap, levelName, temp);
                                flag = true;
                            }
                        }
                        if (i == 0) {
                            VwHelperListDto dto = dependenciesMap.get(hd.getTableName() + "|" + hd.getFieldName());
                            if (dto != null) {
                                framedQuery = "SELECT DISTINCT SUB." + dto.getReferenceColumnName() + ", SUB." + dto.getMappingColumnName() + " FROM " + hd.getTableName() + " " + tablealias + " ";
                                framedQuery += " JOIN " + dto.getReferenceTableName() + " SUB ON SUB." + dto.getMappingColumnName() + " = " + tablealias + "." + dto.getActualColumnName();
                                if (StringUtils.isNotBlank(dto.getListName()) && !"null".equals(dto.getListName())) {
                                    framedQuery += " AND SUB.LIST_NAME='" + dto.getListName() + "'";
                                }
                                whereQuery = " WHERE SUB." + dto.getReferenceColumnName() + " IS NOT NULL ";
                                Integer currentLevel = levelNo;
                                String ruleQuery = inclusionExclusionRules.get(currentLevel);
                                if (StringUtils.isNotBlank(ruleQuery)) {
                                    ruleQuery = buildFinalQuery(hd.getTableName(), hd.getFieldName(), ruleQuery, tablealias + ".", "SUB." + dto.getReferenceColumnName());
                                    whereQuery += " and " + ruleQuery + " ";
                                }
                            } else {
                                framedQuery = "SELECT DISTINCT " + tablealias + "." + hd.getFieldName() + ", " + tablealias + "." + CommonUtils.getTableSystemId(hd.getTableName()) + " FROM " + hd.getTableName() + " " + tablealias + " ";
                                whereQuery = " WHERE " + tablealias + "." + hd.getFieldName() + " IS NOT NULL ";
                                Integer currentLevel = levelNo;
                                String ruleQuery = inclusionExclusionRules.get(currentLevel);
                                if (StringUtils.isNotBlank(ruleQuery)) {
                                   
                                    ruleQuery = buildFinalQuery(hd.getTableName(), hd.getFieldName(), ruleQuery, tablealias + ".", tablealias + "." + hd.getFieldName());
                                    whereQuery += " and " + ruleQuery + " ";
                                }
                            }
                        } else {
                            LOGGER.info("M " + levelName);
                            LOGGER.info("M1 " + hd.getLevelName());
                            whereQuery += " AND " + tablealias + "." + hd.getFieldName() + " = " + getDependentJoin(hd.getTableName(), hd.getFieldName(), levelValues.get(i)) + ConstantsUtils.EMPTY;
                        }
                        tableNameMap.put(temp, tablealias);
                    }
                }
            }
            String finalQuery = (framedQuery + tableQuery + whereQuery);

            List returnList = HistRelationshipBuilderLocalServiceUtil.findFilteredLevelValues(finalQuery);
            if (returnList.size() > ConstantsUtils.ZERO_NUM) {
                for (int i = 0; i < returnList.size(); i++) {
                    Object[] obj = (Object[]) returnList.get(i);
                    HierarchyLevelsDTO levelValuesDTO = new HierarchyLevelsDTO();
                    levelValuesDTO.setLevelValue(String.valueOf(obj[0]));
                    levelValuesDTO.setHiddenId(String.valueOf(obj[1]));
                    levelValuesDTO.setParentNode("0");
                    levelValuesDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
                    levelValuesDTO.setHierarchyLevelSystemId(levelSystemId);
                    levelValuesDTO.setLevelName(levelName);
                    levelValuesDTO.setLevelNo(String.valueOf(levelNo));
                    filteredValues.add(levelValuesDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return filteredValues;
    }

    private static String getDependentJoin(String tableName, String fieldName, String levelValue) {
        VwHelperListDto dto = dependenciesMap.get(tableName + "|" + fieldName);
        if (dto != null) {
            String query = ConstantsUtils.EMPTY;
            query = "(SELECT TOP 1 " + dto.getMappingColumnName() + " FROM " + dto.getReferenceTableName();
            query += " WHERE " + dto.getReferenceColumnName() + " = '" + levelValue + "'";
            if (StringUtils.isNotBlank(dto.getListName()) && !"null".equals(dto.getListName())) {
                query += " AND LIST_NAME='" + dto.getListName() + "'";
            }
            query += " ) ";
            return query;
        } else {
            return " '" + levelValue + "' ";
        }
    }

    private static String getJoinCondition(String currentTable, String currentTablealias, Map<String, String> tableNameMap, String levelName, String currentMapValue) {
        String joinCondition = ConstantsUtils.EMPTY;
        String prevTable = ConstantsUtils.EMPTY;
        for (Iterator<String> it = tableNameMap.keySet().iterator(); it.hasNext();) {
            prevTable = it.next();
        }
        LOGGER.info("Current Table: " + currentMapValue + " ---" + currentTable);
        LOGGER.info("Prev Table: " + prevTable);
        String prevTableAlias = tableNameMap.get(prevTable);
        if (("Contract".equals(currentMapValue)) && ("Contract Holder".equals(prevTable))) {
            LOGGER.info("yes came 1st inside");
            joinCondition = " JOIN " + currentTable + " " + currentTablealias + " ON " + currentTablealias + ".CONT_HOLD_COMPANY_MASTER_SID = " + prevTableAlias + ".COMPANY_MASTER_SID";
        } else if (("Contract Holder".equals(currentMapValue)) && ("Trading Partner".equals(prevTable) || "Customer".equals(prevTable))) {
            joinCondition = " JOIN CCP_DETAILS CCPD ON CCPD.COMPANY_MASTER_SID = TP.COMPANY_MASTER_SID";
            joinCondition += " JOIN CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID";
            joinCondition += " JOIN COMPANY_MASTER CH ON CH.COMPANY_MASTER_SID = CM.CONT_HOLD_COMPANY_MASTER_SID";
        } else if (("Contract Holder".equals(currentMapValue)) && ("Contract".equals(prevTable))) {
            joinCondition = " JOIN COMPANY_MASTER CH ON CM.CONT_HOLD_COMPANY_MASTER_SID = CH.COMPANY_MASTER_SID";
        } else if (("Contract".equals(currentMapValue)) && ("Trading Partner".equals(prevTable) || "Customer".equals(prevTable))) {
            joinCondition = " JOIN CCP_DETAILS CCPD ON CCPD.COMPANY_MASTER_SID = TP.COMPANY_MASTER_SID";
            joinCondition += " JOIN CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID";
            joinCondition += " JOIN COMPANY_MASTER CH ON CH.COMPANY_MASTER_SID = CM.CONT_HOLD_COMPANY_MASTER_SID";
        } else if (("Trading Partner".equals(currentMapValue) || "Customer".equals(currentMapValue)) && ("Contract".equals(prevTable))) {
            joinCondition = " JOIN CCP_DETAILS CCPD ON CM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID ";
            joinCondition += "JOIN COMPANY_MASTER TP ON CCPD.COMPANY_MASTER_SID = TP.COMPANY_MASTER_SID ";
            joinCondition += "JOIN COMPANY_MASTER CH ON CH.COMPANY_MASTER_SID = CM.CONT_HOLD_COMPANY_MASTER_SID ";
        } else if (("GL Company".equals(currentMapValue)) && ("Product".equals(prevTable))) {
            joinCondition = " JOIN GL_COST_CENTER_MASTER GLCCM ON GLCCM.NDC8 = IM.NDC8 ";
            joinCondition += " JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLCCM.COMPANY_CODE ";
        } else if (("Product".equals(currentMapValue)) && ("BRAND_MASTER".equals(prevTable))) {
            joinCondition = "JOIN ITEM_MASTER IM ON IM.BRAND_MASTER_SID = B0.BRAND_MASTER_SID";
            joinCondition += " JOIN GL_COST_CENTER_MASTER GLC ON GLC.NDC8 = IM.NDC8";
            joinCondition += " JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE ";
        } else if (("BRAND_MASTER".equals(currentMapValue)) && ("Product".equals(prevTable))) {
            joinCondition = "JOIN BRAND_MASTER B1 ON B1.BRAND_MASTER_SID = IM.BRAND_MASTER_SID";
            joinCondition += " JOIN GL_COST_CENTER_MASTER GLC ON GLC.NDC8 = IM.NDC8";
            joinCondition += " JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE ";
        } else if (("GL Company".equals(currentMapValue)) && ("BRAND_MASTER".equals(prevTable))) {
            joinCondition = "JOIN ITEM_MASTER IM ON IM.BRAND_MASTER_SID = B0.BRAND_MASTER_SID";
            joinCondition += " JOIN GL_COST_CENTER_MASTER GLCCM ON GLCCM.NDC8 = IM.NDC8 ";
            joinCondition += " JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLCCM.COMPANY_CODE  ";
        } else {
            joinCondition = " JOIN CCP_DETAILS CCPD ON CCPD.COMPANY_MASTER_SID = TP.COMPANY_MASTER_SID";
            joinCondition += " JOIN CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID";
            joinCondition += " JOIN COMPANY_MASTER CH ON CH.COMPANY_MASTER_SID = CM.CONT_HOLD_COMPANY_MASTER_SID";
        }
        return joinCondition;
    }

    public static void main7(String[] args) {
        String test = "HDStPl123rUlECONTRACT_STATUS = '870'";
        LOGGER.info(test.substring(test.indexOf(BPMCommonUtils.REPLACE_STRING) + BPMCommonUtils.REPLACE_STRING.length(), test.indexOf(" ")));
    }

    public static List<HierarchyLevelsDTO> getOldValues(int hierarchySysId, int version, int levelNo) {
        List<HierarchyLevelsDTO> returnList = new ArrayList<HierarchyLevelsDTO>();
        try {
            final DynamicQuery levelDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
            levelDynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", hierarchySysId));
                     levelDynamicQuery.add(RestrictionsFactoryUtil.eq("levelNo", levelNo));
            List<HierarchyLevelDefinition> hierarchyLevels = HierarchyLevelDefinitionLocalServiceUtil.dynamicQuery(levelDynamicQuery);

            if (hierarchyLevels != null && !hierarchyLevels.isEmpty()) {
                HierarchyLevelDefinition currentLevelDef = hierarchyLevels.get(0);
                if ("User Defined".equals(currentLevelDef.getLevelValueReference())) {
                    return null;
                }
                int levelSystemId = currentLevelDef.getHierarchyLevelDefinitionSid();
                String tableName = currentLevelDef.getTableName();
                String columnName = currentLevelDef.getFieldName();
                String levelName = currentLevelDef.getLevelName();
                final DynamicQuery hierarchyDynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class);
                hierarchyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.HIERARCHY_DEFINATION_SID, hierarchySysId));
                hierarchyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRIMARY_KEY + ConstantsUtils.VERSION_NO, version));

                List<HistHierarchyDefinition> hierarchyList;
                hierarchyList = dao.getHistHierachyDefinitionList(hierarchyDynamicQuery);

                final List hierarchyDTO = new ArrayList();
                hierarchyDTO.add(hierarchyList.get(0).getHierarchyType());
                final int hierCategoryCode = hierarchyList.get(0).getHierarchyCategory();
                final String hierarchyDefName = hierarchyList.get(0).getHierarchyName();
               
                HelperTable category;
                category = dao.getCategory(hierCategoryCode);
                hierarchyDTO.add(category.getDescription());
                hierarchyDTO.add(2, levelSystemId);
                Integer currentLevel = levelNo;
                hierarchyDTO.add(3, inclusionExclusionRules.get(currentLevel));
                hierarchyDTO.add(4, dependenciesMap);
                String sqlString = finderImplInLogic(tableName, columnName, hierarchyDTO);
                List levelValues = dao.getHistRelationshipBuilderList(sqlString, columnName, hierarchyDTO);
                if (levelValues.size() > ConstantsUtils.ZERO_NUM) {
                    for (int i = 0; i < levelValues.size(); i++) {
                        Object[] obj = (Object[]) levelValues.get(i);
                        HierarchyLevelsDTO levelValuesDTO = new HierarchyLevelsDTO();
                        final String key = String.valueOf(levelSystemId);
                        levelValuesDTO.setLevelValue(String.valueOf(obj[0]));
                        levelValuesDTO.setHiddenId(String.valueOf(obj[1]));
                        levelValuesDTO.setParentNode("0");
                        levelValuesDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
                        levelValuesDTO.setHierarchyLevelSystemId(levelSystemId);
                        levelValuesDTO.setLevelName(levelName);
                        levelValuesDTO.setLevelNo(String.valueOf(levelNo));
                        returnList.add(levelValuesDTO);
                    }
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
        return returnList;
    }

    static String finderImplInLogic(String tableName, String columnName, List hierListValues) throws PortalException, SystemException {

        List list = new ArrayList();

        String hierarchyType = hierListValues.get(0).toString();
        String hierarchyCategory = hierListValues.get(1).toString();
        int levelNo = Integer.valueOf(hierListValues.get(2).toString());
        String rule = String.valueOf(hierListValues.get(3));
        Map<String, VwHelperListDto> dependenciesMap = (HashMap<String, VwHelperListDto>) hierListValues.get(4);

        VwHelperListDto dto = dependenciesMap.get(tableName + "|" + columnName);
        String sqlString = ConstantsUtils.EMPTY;
        if (dto != null) {
            String joinCondition = ConstantsUtils.EMPTY;
            String whereCondition = ConstantsUtils.EMPTY;
            sqlString = "SELECT DISTINCT SUB." + dto.getReferenceColumnName() + ", SUB." + dto.getMappingColumnName() + " FROM " + tableName + " MAIN ";
            joinCondition += " JOIN " + dto.getReferenceTableName() + " SUB ON SUB." + dto.getMappingColumnName() + " = MAIN." + dto.getActualColumnName();
            if (StringUtils.isNotBlank(dto.getListName()) && !"null".equals(dto.getListName())) {
                joinCondition += " AND SUB.LIST_NAME='" + dto.getListName() + "'";
            }
            whereCondition = " WHERE SUB." + dto.getReferenceColumnName() + " IS NOT NULL ";

            if (!rule.equals("null") && StringUtils.isNotBlank(rule)) {

                rule = BpmLogic.buildFinalQuery(tableName, columnName, rule, "MAIN.", "SUB." + dto.getReferenceColumnName());
                whereCondition += " and " + rule + " ";
            }
            sqlString += joinCondition + whereCondition;
        } else {
            sqlString = "SELECT DISTINCT " + columnName + ", " + CommonUtils.getTableSystemId(tableName) + " FROM " + tableName + " WHERE " + columnName + " IS NOT NULL ";
            if (!rule.equals("null") && StringUtils.isNotBlank(rule)) {

                rule = BpmLogic.buildFinalQuery(tableName, columnName, rule, ConstantsUtils.EMPTY, columnName);
                sqlString += " and " + rule + " ";
            }
        }
        if (1 == levelNo && "Data Selection".equals(hierarchyCategory) && "Primary".equals(hierarchyType)) {
            if ("COMPANY_MASTER".equals(tableName)) {
                sqlString += " and COMPANY_TYPE='GLCOMP'";
            }
        }

        return sqlString;

    }

    public static void addTableName(String tableName) {
        tableNames.add(tableName);
    }

    public static void addColumnName(String columnName) {
        columnNames.add(columnName);
    }

    public static Map<String, VwHelperListDto> getDependenciesList() {
        String tableNameValues = tableNames.toString().replace("[", "'").replace("]", "'").replace(", ", "','");
        String columnNameValues = columnNames.toString().replace("[", "'").replace("]", "'").replace(", ", "','");
        String SqlQuery = "select * from VW_HELPER_LIST";
        SqlQuery += " WHERE ACTUAL_TABLE_NAME != REFERENCE_TABLE_NAME";
        SqlQuery += " AND ACTUAL_TABLE_NAME IN (" + tableNameValues + ")";
        SqlQuery += " AND ACTUAL_COLUMN_NAME IN (" + columnNameValues + ")";
        List returnList = HistRelationshipBuilderLocalServiceUtil.findFilteredLevelValues(SqlQuery);
        if (returnList != null && !returnList.isEmpty()) {
            for (int i = 0; i < returnList.size(); i++) {
                VwHelperListDto dto = new VwHelperListDto();
                Object[] obj = (Object[]) returnList.get(i);
                dto.setUniqueId(String.valueOf(obj[0]));
                dto.setActualTableName(String.valueOf(obj[1]));
                dto.setActualColumnName(String.valueOf(obj[2]));
                dto.setReferenceTableName(String.valueOf(obj[3]));
                dto.setReferenceColumnName(String.valueOf(obj[4]));
                dto.setMappingColumnName(String.valueOf(obj[5]));
                dto.setDescColumnName(String.valueOf(obj[6]));
                dto.setListName(String.valueOf(obj[7]));
                dto.setPrimaryKeyColumnName(String.valueOf(obj[8]));
                // Modified for the change of new column called DESC_COLUMN_NAME
                if (StringUtils.isNotBlank(dto.getReferenceTableName()) && !"HELPER_TABLE".equals(dto.getReferenceTableName())) {
                    if (StringUtils.isNotBlank(dto.getDescColumnName())) {
                        dto.setReferenceColumnName(dto.getDescColumnName());
                    }
                }
                dependenciesMap.put(dto.getActualTableName() + "|" + dto.getActualColumnName(), dto);
            }
        }
        return dependenciesMap;
    }

    public static String buildFinalQuery(String tableName, String columnName, String rule, String mainTableAlias, String referenceColumn) {
        String tempRule = rule;
        while (tempRule.contains(BPMCommonUtils.REPLACE_STRING)) {
            String condition = tempRule.substring(tempRule.indexOf("("), tempRule.indexOf(")") + 1);
            if (condition.contains(BPMCommonUtils.REPLACE_STRING + columnName)) {
                rule = rule.replace(BPMCommonUtils.REPLACE_STRING + columnName, referenceColumn);
            } else {
                if (StringUtils.isNotBlank(condition)) {
                    String colArray[] = condition.split(" ");
                    String otherColumn = colArray[1];
                    String conditionMethod = colArray[2];
                    String value = colArray[3];
                    otherColumn = otherColumn.replace(BPMCommonUtils.REPLACE_STRING, ConstantsUtils.EMPTY);
                    VwHelperListDto dto = dependenciesMap.get(tableName + "|" + otherColumn);
                    if (dto != null) {
                        String subQuery = " (" + mainTableAlias + otherColumn + " IN (SELECT " + dto.getMappingColumnName() + " FROM " + dto.getReferenceTableName() + " WHERE " + dto.getReferenceColumnName() + " " + conditionMethod + " " + value + " ";
                        if (StringUtils.isNotBlank(dto.getListName()) && !"null".equals(dto.getListName())) {
                            subQuery += " AND LIST_NAME='" + dto.getListName() + "'";
                        }
                        subQuery += " ))";
                        rule = rule.replace(condition, subQuery);
                    } else {
                        String replaceCondition = condition.replace(BPMCommonUtils.REPLACE_STRING, mainTableAlias);
                        rule = rule.replace(condition, replaceCondition);
                    }
                }
            }
            tempRule = tempRule.replace(condition, ConstantsUtils.EMPTY);
        }
        return rule;
    }


    public static InputStream getSSLConnection(String url) throws Exception {

        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
        // Install the all-trusting trust manager
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

     
        String userpassword = BPMCommonUtils.USER_PASSWORD;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        String authEnc = new Base64Encoder().encode(userpassword.getBytes());
        httpURLConnection.setRequestProperty("Authorization", "Basic " + authEnc);
        InputStream is = httpURLConnection.getInputStream();
        return is;
    }

    public static Map<Integer, String> getInclusionExclusionRulesWithoutBPM(String hierarchyDefName) {
        inclusionExclusionRules.clear();
        inclusionExclusionRules = new HashMap<Integer, String>();
        dependenciesMap.clear();
        dependenciesMap = new HashMap<String, VwHelperListDto>();
        List<String> rulesName = new ArrayList<String>();
        Map<String, Object> ruleMap = new HashMap<String, Object>();
        try {
            String query = "select * from dbo.HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID \n"
                    + "in (select HIERARCHY_DEFINITION_SID from dbo.HIERARCHY_DEFINITION where HIERARCHY_NAME='" + hierarchyDefName + "')";
            int totalLevels = 0;
            List list = HistRelationshipBuilderLocalServiceUtil.executeQuery(query);
            if (list != null && list.size() > 0) {
                totalLevels = list.size();
                for (int i = 0; i < totalLevels; i++) {
                    Object[] obj = (Object[]) list.get(i);
                    String inclusionRuleType = String.valueOf(obj[12]);
                    String exclusionRuleType = String.valueOf(obj[14]);
                    if (!inclusionRuleType.equals(NULL) && !inclusionRuleType.equals(StringUtils.EMPTY) && !inclusionRuleType.equals("Group")) {
                        String inclusionRule = String.valueOf(obj[13]);
                        rulesName.add(inclusionRule);
                    }

                    if (!exclusionRuleType.equals(NULL) && !exclusionRuleType.equals(StringUtils.EMPTY) && !exclusionRuleType.equals("Group")) {
                        String exclusionRule = String.valueOf(obj[15]);
                        rulesName.add(exclusionRule);
                    }

                    if (!inclusionRuleType.equals(NULL) && !inclusionRuleType.equals(StringUtils.EMPTY) && inclusionRuleType.equals("Group")) {
                        String groupRule = String.valueOf(obj[13]);
                        String ruleGroupQuery = "select distinct HRD.RULE_NAME from dbo.HIERARCHY_LEVEL_DEFINITION HLD JOIN dbo.HIERARCHY_RULES_DEFINITION HRD on HLD.INCLUSION_RULE=HRD.RULE_FLOW_GROUP_NAME\n"
                                + "AND HRD.RULE_FLOW_GROUP_NAME='" + groupRule + "'";
                        List ruleGroupList = HistRelationshipBuilderLocalServiceUtil.executeQuery(ruleGroupQuery);
                        if (ruleGroupList != null && ruleGroupList.size() > 0) {
                            for (Object rule : ruleGroupList) {
                                rulesName.add(String.valueOf(rule));
                            }
                        }
                    }
                    if (!exclusionRuleType.equals(NULL) && !exclusionRuleType.equals(StringUtils.EMPTY) && exclusionRuleType.equals("Group")) {
                        String exclusionRule = String.valueOf(obj[15]);
                        String ruleGroupQuery = "select distinct HRD.RULE_NAME from dbo.HIERARCHY_LEVEL_DEFINITION HLD JOIN dbo.HIERARCHY_RULES_DEFINITION HRD on HLD.INCLUSION_RULE=HRD.RULE_FLOW_GROUP_NAME\n"
                                + "AND HRD.RULE_FLOW_GROUP_NAME='" + exclusionRule + "'";
                        List ruleGroupList = HistRelationshipBuilderLocalServiceUtil.executeQuery(ruleGroupQuery);
                        if (ruleGroupList != null && ruleGroupList.size() > 0) {
                            for (Object rule : ruleGroupList) {
                                rulesName.add(String.valueOf(rule));
                            }
                        }
                    }
                }
                if (!rulesName.isEmpty()) {
                    String ruleName1 = getRuleNameString(rulesName);
                    String rulesValue = "select HRD.RULE_NAME,HRD.\"TABLE_NAME\",HRD.\"COLUMN_NAME\",HRD.\"CONDITION\",HRD.\"VALUE\",HRD.INBOUND_STATUS from "
                            + "dbo.HIERARCHY_RULES_DEFINITION HRD where RULE_NAME in (" + ruleName1 + ")";
                    List ruleValueList = HistRelationshipBuilderLocalServiceUtil.executeQuery(rulesValue);
                    if (ruleValueList != null && ruleValueList.size() > 0) {
                        for (int j = 0; j < ruleValueList.size(); j++) {
                            Object[] ob = (Object[]) ruleValueList.get(j);
                            String key = String.valueOf(ob[0]);
                            ruleMap.put(key, ruleValueList.get(j));
                        }
                    }
                }
            }

            for (int i = 0; i < totalLevels; i++) {
                StringBuilder rules = new StringBuilder(ConstantsUtils.EMPTY);
                Integer levelNo = i + 1;
                if (list != null && list.size() > 0) {
                    Object[] obj = (Object[]) list.get(i);
                    String inclusionRuleType = String.valueOf(obj[12]);
                    String inclusionRule = String.valueOf(obj[13]);
                    String inclusionCondition = String.valueOf(obj[16]);
                    String exclusionRuleType = String.valueOf(obj[14]);
                    String exclusionRule = String.valueOf(obj[15]);
                    String exclusionCondition = String.valueOf(obj[17]);
                    HierarchyDefinitionLevelRuleDTO dto = new HierarchyDefinitionLevelRuleDTO();
                    // For Inclusion Rule Group
                    if ("Group".equalsIgnoreCase(inclusionRuleType)) {
                        List<String> ruleNameList = getGroupRuleList(inclusionRule);
                        List<HierarchyDefinitionLevelRuleDTO> inclusionList = new ArrayList<HierarchyDefinitionLevelRuleDTO>();
                        for (String ruleName : ruleNameList) {
                            dto = new HierarchyDefinitionLevelRuleDTO();
                            dto.setRuleName(ruleName);
                            Object[] object = (Object[]) ruleMap.get(ruleName);
                            dto.setCondition1(String.valueOf(object[3]));
                            dto.setDimension(String.valueOf(object[1]));
                            dto.setAttribute(String.valueOf(object[2]));
                            dto.setValue1(String.valueOf(object[4]));
                           
                            inclusionList.add(dto);
                        }
                        if (!inclusionList.isEmpty()) {
                            rules.append(buildQuery(inclusionList, "Inclusion", inclusionCondition));
                        }
                        // For single Inclusion Rule
                    } else {
                        dto = new HierarchyDefinitionLevelRuleDTO();
                        dto.setRuleName(inclusionRule);
                        if (inclusionRule != null && !inclusionRule.equals("null") && !inclusionRule.equals(ConstantsUtils.EMPTY) && ruleMap.get(inclusionRule) != null) {
                            Object[] obje = (Object[]) ruleMap.get(inclusionRule);
                            dto.setCondition1(String.valueOf(obje[3]));
                            dto.setDimension(String.valueOf(obje[1]));
                            dto.setAttribute(String.valueOf(obje[2]));
                            dto.setValue1(String.valueOf(obje[4]));
                            rules.append(buildQuery(dto, "Inclusion"));
                        }
                    }
                    dto = new HierarchyDefinitionLevelRuleDTO();
                    // For Exclusion Rule Group
                    if ("Group".equalsIgnoreCase(exclusionRuleType)) {
                        // Need to code
                        List<String> ruleNameList = getGroupRuleList(exclusionRule);
                        List<HierarchyDefinitionLevelRuleDTO> exclusionList = new ArrayList<HierarchyDefinitionLevelRuleDTO>();
                        for (String ruleName : ruleNameList) {
                            dto = new HierarchyDefinitionLevelRuleDTO();
                            dto.setRuleName(ruleName);
                            Object[] object = (Object[]) ruleMap.get(ruleName);
                            dto.setCondition1(String.valueOf(object[3]));
                            dto.setDimension(String.valueOf(object[1]));
                            dto.setAttribute(String.valueOf(object[2]));
                            dto.setValue1(String.valueOf(object[4]));
                            exclusionList.add(dto);
                        }
                        if (!exclusionList.isEmpty()) {
                            if (rules.length() > 1) {
                                rules.append(" and ");
                            }
                            rules.append(buildQuery(exclusionList, "Exclusion", exclusionCondition));
                        }
                        // For single Exclusion Rule
                    } else {
                        dto.setRuleName(exclusionRule);
                        if (exclusionRule != null && !exclusionRule.equals("null") && !exclusionRule.equals(ConstantsUtils.EMPTY) && ruleMap.get(exclusionRule) != null) {
                            if (rules.length() > 1) {
                                rules.append(" and ");
                            }
                            Object[] obje = (Object[]) ruleMap.get(exclusionRule);
                            dto.setCondition1(String.valueOf(obje[3]));
                            dto.setDimension(String.valueOf(obje[1]));
                            dto.setAttribute(String.valueOf(obje[2]));
                            dto.setValue1(String.valueOf(obje[4]));
                            rules.append(buildQuery(dto, "Exclusion"));
                        }
                    }
                    inclusionExclusionRules.put(levelNo, rules.toString());
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return inclusionExclusionRules;
    }

    public static List<String> getGroupRuleList(String ruleGroup) {
        List<String> ruleList = new ArrayList<String>();
        try {
            String ruleQuery = "select RULE_NAME from dbo.HIERARCHY_RULES_DEFINITION where RULE_FLOW_GROUP_NAME='" + ruleGroup + "'";
            List ruleNameList = HistRelationshipBuilderLocalServiceUtil.executeQuery(ruleQuery);
            if (ruleNameList != null && ruleNameList.size() > 0) {
                for (Object rule : ruleNameList) {
                    ruleList.add(String.valueOf(rule));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return ruleList;
    }

    private static String getRuleNameString(List<String> ruleName) {
        String rule = ConstantsUtils.EMPTY;
        for (int i = 0; i < ruleName.size(); i++) {

            rule = rule + "'" + ruleName.get(i) + "',";
        }
        rule = rule.substring(0, rule.length() - 1);
        return rule;
    }
}
