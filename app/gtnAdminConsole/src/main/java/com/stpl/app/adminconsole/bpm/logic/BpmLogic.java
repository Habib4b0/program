package com.stpl.app.adminconsole.bpm.logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
import org.drools.workbench.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.workbench.models.guided.dtable.shared.model.DTCellValue52;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.eclipse.aether.artifact.Artifact;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.ResourceType;
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
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
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
        conditions.put(CommonUtils.CONTAINS, "like");
    }

    public static List<String> getHierarchies() {

        List<String> hierarchies = new ArrayList<>();
        try (JarFile jar = new JarFile(BPMManagerBean.getEngine().getArtifact().getFile());) {
            Enumeration<JarEntry> sf = jar.entries();
            while (sf.hasMoreElements()) {
                JarEntry temp = sf.nextElement();
                if (!temp.isDirectory() && temp.getName().endsWith(".drl")) {
                        String hierarcy = temp.getName().replace("%20", " ").replace(".drl", ConstantsUtils.EMPTY);
                        hierarchies.add(hierarcy);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return hierarchies;
    }

    public static List<HeirarchyDefinition> getHierarchyLevels(String hierarchyName) {

        List<HeirarchyDefinition> hierarchies = new ArrayList<>();
        String fileName = hierarchyName.replace(" ", "%20") + ".drl";
        try (JarFile jar = new JarFile(BPMManagerBean.getEngine().getArtifact().getFile());){
            Enumeration<JarEntry> sf = jar.entries();
            while (sf.hasMoreElements()) {
                JarEntry temp = sf.nextElement();
                if (!temp.isDirectory() && temp.getName().equals(fileName)) {
                        LOGGER.debug("Inside Hierarchy");
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
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return hierarchies;
    }


    private static String buildQuery(HierarchyDefinitionLevelRuleDTO dto, String ruleType) {
        String query = ConstantsUtils.EMPTY;

        if (CommonUtils.EXCLUSION.equals(ruleType)) {
            query = " not ";
        }
        boolean startsWith = dto.getCondition1().equals("starts with") || dto.getCondition1().equals(CommonUtils.CONTAINS) ? true : false;
        boolean endsWith = dto.getCondition1().equals("ends with") || dto.getCondition1().equals(CommonUtils.CONTAINS) ? true : false;
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
                    query += ConstantsUtils.AND;
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

    public static List<String> getRuleList(String ruleGroup) {
        try {
            List<String> ruleList = new ArrayList<>();
            KieServices ks = KieServices.Factory.get();
            ReleaseId releaseId = ks.newReleaseId(BPMCommonUtils.HR_GROUP, BPMCommonUtils.HR_ARTIFACT_ID, BPMCommonUtils.HR_VERSION);
            Artifact artifact = MavenRepository.getMavenRepository().resolveArtifact(releaseId);
            LOGGER.debug("inside getRuleList method :");
            List<String> fileList = getFilePath(artifact);
            for (String resource : fileList) {
                getRuleListByFile(ruleList, resource, ruleGroup);
            }
            return ruleList;
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {

        }

        return Collections.emptyList();
    }

    public static List<String> getFilePath(Artifact artifact) {
        List<String> fileList = new ArrayList<>();
        JarFile jar = null;
        File file = null;
        InputStream is = null;
        try {
            file = artifact.getFile();
            jar = new JarFile(file);
            Enumeration<JarEntry> sf = jar.entries();
            while (sf.hasMoreElements()) {
                JarEntry temp = sf.nextElement();
                if (!temp.isDirectory() && temp.getName().endsWith(".gdst") && !temp.getName().startsWith("HierarchyDefinitionRules")) {
                            is = jar.getInputStream(temp);
                            fileList.add(IOUtils.toString(is));
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
                    if (i == NumericConstants.TWO && columnName.equals(dtCellValue52.getStringValue())) {
                            rgFlag = true;
                    }
                    if (i == NumericConstants.FOUR && rgFlag && !ruleList.contains(dtCellValue52.getStringValue())) {
                            ruleList.add(dtCellValue52.getStringValue());
                    }
                }
            }
            LOGGER.debug("Rule List value are--------" + ruleList);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return ruleList;
    }

    public static List<HierarchyLevelsDTO> getFilteredValues(int selectedHierarchySysId, int levelNo, List<String> levelValues,
            List<String> primaykeyColumns, List<String> primarykeySID) {
        List<HierarchyLevelsDTO> filteredValues = new ArrayList<>();
        try {
            levelNo = levelNo + 1;
            String levelName = ConstantsUtils.EMPTY;
            int levelSystemId = 0;
            DynamicQuery dynQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
            dynQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", selectedHierarchySysId));
            dynQuery.add(RestrictionsFactoryUtil.le(CommonUtils.LEVEL_NO, levelNo));
            dynQuery.addOrder(OrderFactoryUtil.desc(CommonUtils.LEVEL_NO));
            List<HierarchyLevelDefinition> hierarchyLevels = HierarchyLevelDefinitionLocalServiceUtil.dynamicQuery(dynQuery);
            String framedQuery = ConstantsUtils.EMPTY;
            String tableQuery = ConstantsUtils.EMPTY;
            String whereQuery = ConstantsUtils.EMPTY;
            Map<String, String> tableNameMap = new LinkedHashMap<>();
            LOGGER.debug("Received Hierarchy with ID: " + selectedHierarchySysId + " with levelNo: " + levelNo + " having " + levelValues);
            boolean flag = false;
            String nextlevelTable = ConstantsUtils.EMPTY;
            for (int i = 0; i < hierarchyLevels.size(); i++) {
                HierarchyLevelDefinition hd = hierarchyLevels.get(i);
                if (i == 0 && "User Defined".equals(hd.getLevelValueReference())) {
                    return Collections.emptyList();
                } else {
                    if (i == 0) {
                        levelName = hd.getLevelName();
                        levelSystemId = hd.getHierarchyLevelDefinitionSid();
                    }
                    if (hd.getTableName() != null && StringUtils.isNotBlank(hd.getTableName())) {
                        String tablealias;
                        String temp;
                        if ("CONTRACT_MASTER".equals(hd.getTableName())) {
                            tablealias = "CM";
                            temp = CommonUtils.CONTRACT;
                        } else if (CommonUtils.COMPANY_MASTER.equals(hd.getTableName()) && (CommonUtils.TRADING_PARTNER.equals(hd.getLevelName()) || CommonUtils.CUSTOMER.equals(hd.getLevelName()))) {
                            tablealias = "TP";
                            temp = CommonUtils.TRADING_PARTNER;
                        } else if (CommonUtils.COMPANY_MASTER.equals(hd.getTableName()) && CommonUtils.CONTRACT_HOLDER.equals(hd.getLevelName())) {
                            tablealias = "CH";
                            temp = CommonUtils.CONTRACT_HOLDER;
                        } else if ("ITEM_MASTER".equals(hd.getTableName())) {
                            tablealias = "IM";
                            temp = CommonUtils.PRODUCT;
                        } else if (CommonUtils.COMPANY_MASTER.equals(hd.getTableName())) {
                            tablealias = "CM";
                            temp = CommonUtils.GL_COMPANY;
                        } else {
                            tablealias = hd.getTableName().substring(0, 1) + ConstantsUtils.EMPTY + i;
                            temp = hd.getTableName();
                        }
                        if (i != 0 && tableNameMap.get(temp) == null && !flag) {
                                tableQuery += getJoinCondition(hd.getTableName(), tablealias, tableNameMap, temp);
                                flag = true;
                        }
                        if (i == 0) {
                            VwHelperListDto dto = dependenciesMap.get(hd.getTableName() + "|" + hd.getFieldName());
                            if (dto != null) {
                                framedQuery = "SELECT DISTINCT SUB." + dto.getReferenceColumnName() + ", SUB." + dto.getMappingColumnName() + ConstantsUtils.FROM + hd.getTableName() + " " + tablealias + " ";
                                framedQuery += ConstantsUtils.JOIN + dto.getReferenceTableName() + " SUB ON SUB." + dto.getMappingColumnName() + " = " + tablealias + "." + dto.getActualColumnName();
                                if (StringUtils.isNotBlank(dto.getListName()) && !"null".equals(dto.getListName())) {
                                    framedQuery += " AND SUB.LIST_NAME='" + dto.getListName() + "'";
                                }
                                whereQuery = " WHERE SUB." + dto.getReferenceColumnName() + ConstantsUtils.IS_NOT_NULL;
                                Integer currentLevel = levelNo;
                                String ruleQuery = inclusionExclusionRules.get(currentLevel);
                                if (StringUtils.isNotBlank(ruleQuery)) {
                                    ruleQuery = buildFinalQuery(hd.getTableName(), hd.getFieldName(), ruleQuery, tablealias + ".", "SUB." + dto.getReferenceColumnName());
                                    whereQuery += ConstantsUtils.AND + ruleQuery + " ";
                                }
                            } else {
                                nextlevelTable=hd.getTableName();
                                framedQuery = "SELECT DISTINCT " + tablealias + "." + hd.getFieldName() + ", " + tablealias + "." + CommonUtils.getTableSystemId(hd.getTableName()) + ConstantsUtils.FROM + hd.getTableName() + " " + tablealias + " ";
                                whereQuery = ConstantsUtils.WHERE + tablealias + "." + hd.getFieldName() + ConstantsUtils.IS_NOT_NULL;
                                Integer currentLevel = levelNo;
                                String ruleQuery = inclusionExclusionRules.get(currentLevel);
                                if (StringUtils.isNotBlank(ruleQuery)) {
                                   
                                    ruleQuery = buildFinalQuery(hd.getTableName(), hd.getFieldName(), ruleQuery, tablealias + ".", tablealias + "." + hd.getFieldName());
                                    whereQuery += ConstantsUtils.AND + ruleQuery + " ";
                                }
                            }
                        } else {
                            LOGGER.debug("M " + levelName);
                            LOGGER.debug("M1 " + hd.getLevelName());
                            whereQuery += " AND " + tablealias + "." + hd.getFieldName() + " = " + getDependentJoin(hd.getTableName(), hd.getFieldName(), levelValues.get(i), 
                                    tablealias, primaykeyColumns.get(i), primarykeySID.get(i)) + ConstantsUtils.EMPTY;
                        }
                        tableNameMap.put(temp, tablealias);
                    }
                }
            }
            String finalQuery = framedQuery + tableQuery + whereQuery;

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
                    levelValuesDTO.setPrimaryKeyColumn(CommonUtils.getTableSystemId(nextlevelTable));
                    filteredValues.add(levelValuesDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return filteredValues;
    }

    private static String getDependentJoin(String tableName, String fieldName, String levelValue, String tableAlias, String primarykeyColumn, String primarykeyValue) {
        VwHelperListDto dto = dependenciesMap.get(tableName + "|" + fieldName);
        if (dto != null) {
            String query;
            query = "(SELECT TOP 1 " + dto.getMappingColumnName() + ConstantsUtils.FROM + dto.getReferenceTableName();
            query += ConstantsUtils.WHERE + dto.getReferenceColumnName() + " = '" + levelValue + "'";
            if (StringUtils.isNotBlank(dto.getListName()) && !"null".equals(dto.getListName())) {
                query += " AND LIST_NAME='" + dto.getListName() + "'";
            }
            query += " ) ";
            return query;
        } else {
            return " '" + levelValue + "' AND "+ tableAlias+"."+primarykeyColumn+"="+primarykeyValue+" ";
        }
    }

    private static String getJoinCondition(String currentTable, String currentTablealias, Map<String, String> tableNameMap, String currentMapValue) {
        String joinCondition;
        String prevTable = ConstantsUtils.EMPTY;
        for (Iterator<String> it = tableNameMap.keySet().iterator(); it.hasNext();) {
            prevTable = it.next();
        }
        LOGGER.debug("Current Table: " + currentMapValue + " ---" + currentTable);
        LOGGER.debug("Prev Table: " + prevTable);
        String prevTableAlias = tableNameMap.get(prevTable);
        if ((CommonUtils.CONTRACT.equals(currentMapValue)) && (CommonUtils.CONTRACT_HOLDER.equals(prevTable))) {
            LOGGER.debug("yes came 1st inside");
            joinCondition = ConstantsUtils.JOIN + currentTable + " " + currentTablealias + " ON " + currentTablealias + ".CONT_HOLD_COMPANY_MASTER_SID = " + prevTableAlias + ".COMPANY_MASTER_SID";
        } else if ((CommonUtils.CONTRACT_HOLDER.equals(currentMapValue)) && (CommonUtils.TRADING_PARTNER.equals(prevTable) || CommonUtils.CUSTOMER.equals(prevTable))) {
            joinCondition = " JOIN CCP_DETAILS CCPD ON CCPD.COMPANY_MASTER_SID = TP.COMPANY_MASTER_SID ";
            joinCondition += " JOIN CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID ";
            joinCondition += " JOIN COMPANY_MASTER CH ON CH.COMPANY_MASTER_SID = CM.CONT_HOLD_COMPANY_MASTER_SID ";
        } else if ((CommonUtils.CONTRACT_HOLDER.equals(currentMapValue)) && (CommonUtils.CONTRACT.equals(prevTable))) {
            joinCondition = " JOIN COMPANY_MASTER CH ON CM.CONT_HOLD_COMPANY_MASTER_SID = CH.COMPANY_MASTER_SID";
        } else if ((CommonUtils.CONTRACT.equals(currentMapValue)) && (CommonUtils.TRADING_PARTNER.equals(prevTable) || CommonUtils.CUSTOMER.equals(prevTable))) {
            joinCondition = " JOIN CCP_DETAILS CCPD ON CCPD.COMPANY_MASTER_SID = TP.COMPANY_MASTER_SID";
            joinCondition += " JOIN CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID";
            joinCondition += " JOIN COMPANY_MASTER CH ON CH.COMPANY_MASTER_SID = CM.CONT_HOLD_COMPANY_MASTER_SID";
        } else if ((CommonUtils.TRADING_PARTNER.equals(currentMapValue) || CommonUtils.CUSTOMER.equals(currentMapValue)) && (CommonUtils.CONTRACT.equals(prevTable))) {
            joinCondition = " JOIN CCP_DETAILS CCPD ON CM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID ";
            joinCondition += "JOIN COMPANY_MASTER TP ON CCPD.COMPANY_MASTER_SID = TP.COMPANY_MASTER_SID ";
            joinCondition += "JOIN COMPANY_MASTER CH ON CH.COMPANY_MASTER_SID = CM.CONT_HOLD_COMPANY_MASTER_SID ";
        } else if ((CommonUtils.GL_COMPANY.equals(currentMapValue)) && (CommonUtils.PRODUCT.equals(prevTable))) {
            joinCondition = " JOIN GL_COST_CENTER_MASTER GLCCM ON GLCCM.NDC8 = IM.NDC8 ";
            joinCondition += " JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLCCM.COMPANY_CODE ";
        } else if ((CommonUtils.PRODUCT.equals(currentMapValue)) && (CommonUtils.BRAND_MASTER.equals(prevTable))) {
            joinCondition = "JOIN ITEM_MASTER IM ON IM.BRAND_MASTER_SID = B0.BRAND_MASTER_SID";
            joinCondition += " JOIN GL_COST_CENTER_MASTER GLC ON GLC.NDC8 = IM.NDC8";
            joinCondition += " JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE ";
        } else if ((CommonUtils.BRAND_MASTER.equals(currentMapValue)) && (CommonUtils.PRODUCT.equals(prevTable))) {
            joinCondition = "JOIN BRAND_MASTER B1 ON B1.BRAND_MASTER_SID = IM.BRAND_MASTER_SID";
            joinCondition += " JOIN GL_COST_CENTER_MASTER GLC ON GLC.NDC8 = IM.NDC8";
            joinCondition += " JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE ";
        } else if ((CommonUtils.GL_COMPANY.equals(currentMapValue)) && (CommonUtils.BRAND_MASTER.equals(prevTable))) {
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

    public static void main7() {
        String test = "HDStPl123rUlECONTRACT_STATUS = '870'";
        LOGGER.debug(test.substring(test.indexOf(BPMCommonUtils.REPLACE_STRING) + BPMCommonUtils.REPLACE_STRING.length(), test.indexOf(" ")));
    }

    public static List<HierarchyLevelsDTO> getOldValues(int hierarchySysId, int version, int levelNo) {
        List<HierarchyLevelsDTO> returnList = new ArrayList<>();
        try {
            final DynamicQuery levelDynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class);
            levelDynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", hierarchySysId));
                     levelDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtils.LEVEL_NO, levelNo));
            List<HierarchyLevelDefinition> hierarchyLevels = HierarchyLevelDefinitionLocalServiceUtil.dynamicQuery(levelDynamicQuery);

            if (hierarchyLevels != null && !hierarchyLevels.isEmpty()) {
                HierarchyLevelDefinition currentLevelDef = hierarchyLevels.get(0);
                if ("User Defined".equals(currentLevelDef.getLevelValueReference())) {
                    return Collections.emptyList();
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
               
                HelperTable category;
                category = dao.getCategory(hierCategoryCode);
                hierarchyDTO.add(category.getDescription());
                hierarchyDTO.add(NumericConstants.TWO, levelSystemId);
                Integer currentLevel = levelNo;
                hierarchyDTO.add(NumericConstants.THREE, inclusionExclusionRules.get(currentLevel));
                hierarchyDTO.add(NumericConstants.FOUR, dependenciesMap);
                String sqlString = finderImplInLogic(tableName, columnName, hierarchyDTO);
                List levelValues = dao.getHistRelationshipBuilderList(sqlString, columnName, hierarchyDTO);
                if (levelValues.size() > ConstantsUtils.ZERO_NUM) {
                    for (int i = 0; i < levelValues.size(); i++) {
                        Object[] obj = (Object[]) levelValues.get(i);
                        HierarchyLevelsDTO levelValuesDTO = new HierarchyLevelsDTO();
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
                return Collections.emptyList();
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();
        }
        return returnList;
    }

    static String finderImplInLogic(String tableName, String columnName, List hierListValues) {


        String hierarchyType = hierListValues.get(0).toString();
        String hierarchyCategory = hierListValues.get(1).toString();
        int levelNo = Integer.valueOf(hierListValues.get(NumericConstants.TWO).toString());
        String rule = String.valueOf(hierListValues.get(NumericConstants.THREE));
        Map<String, VwHelperListDto> dependenciesMap = (HashMap<String, VwHelperListDto>) hierListValues.get(NumericConstants.FOUR);

        VwHelperListDto dto = dependenciesMap.get(tableName + "|" + columnName);
        String sqlString;
        if (dto != null) {
            String joinCondition = ConstantsUtils.EMPTY;
            String whereCondition;
            sqlString = "SELECT DISTINCT SUB." + dto.getReferenceColumnName() + ", SUB." + dto.getMappingColumnName() + ConstantsUtils.FROM + tableName + " MAIN ";
            joinCondition += ConstantsUtils.JOIN + dto.getReferenceTableName() + " SUB ON SUB." + dto.getMappingColumnName() + " = MAIN." + dto.getActualColumnName();
            if (StringUtils.isNotBlank(dto.getListName()) && !"null".equals(dto.getListName())) {
                joinCondition += " AND SUB.LIST_NAME='" + dto.getListName() + "'";
            }
            whereCondition = " WHERE SUB." + dto.getReferenceColumnName() + ConstantsUtils.IS_NOT_NULL;
            if (!rule.equals("null") && StringUtils.isNotBlank(rule)) {

                rule = BpmLogic.buildFinalQuery(tableName, columnName, rule, "MAIN.", "SUB." + dto.getReferenceColumnName());
                whereCondition += ConstantsUtils.AND + rule + " ";
            }
            sqlString += joinCondition + whereCondition;
        } else {
            sqlString = "SELECT DISTINCT " + columnName + ", " + CommonUtils.getTableSystemId(tableName) + ConstantsUtils.FROM + tableName + ConstantsUtils.WHERE + columnName + ConstantsUtils.IS_NOT_NULL;
            if (!rule.equals("null") && StringUtils.isNotBlank(rule)) {

                rule = BpmLogic.buildFinalQuery(tableName, columnName, rule, ConstantsUtils.EMPTY, columnName);
                sqlString += ConstantsUtils.AND + rule + " ";
            }
        }
        if (1 == levelNo && "Data Selection".equals(hierarchyCategory) && "Primary".equals(hierarchyType) && CommonUtils.COMPANY_MASTER.equals(tableName)) {
                sqlString += " and COMPANY_TYPE='GLCOMP'";
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
                dto.setActualColumnName(String.valueOf(obj[NumericConstants.TWO]));
                dto.setReferenceTableName(String.valueOf(obj[NumericConstants.THREE]));
                dto.setReferenceColumnName(String.valueOf(obj[NumericConstants.FOUR]));
                dto.setMappingColumnName(String.valueOf(obj[NumericConstants.FIVE]));
                dto.setDescColumnName(String.valueOf(obj[NumericConstants.SIX]));
                dto.setPrimaryKeyColumnName(String.valueOf(obj[NumericConstants.SEVEN]));
                dto.setListName(String.valueOf(obj[NumericConstants.EIGHT]));
                // Modified for the change of new column called DESC_COLUMN_NAME
                if (StringUtils.isNotBlank(dto.getReferenceTableName()) && !"HELPER_TABLE".equals(dto.getReferenceTableName()) && StringUtils.isNotBlank(dto.getDescColumnName())) {
                        dto.setReferenceColumnName(dto.getDescColumnName());
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
                    String conditionMethod = colArray[NumericConstants.TWO];
                    String value = colArray[NumericConstants.THREE];
                    otherColumn = otherColumn.replace(BPMCommonUtils.REPLACE_STRING, ConstantsUtils.EMPTY);
                    VwHelperListDto dto = dependenciesMap.get(tableName + "|" + otherColumn);
                    if (dto != null) {
                        String subQuery = " (" + mainTableAlias + otherColumn + " IN (SELECT " + dto.getMappingColumnName() + ConstantsUtils.FROM + dto.getReferenceTableName() + ConstantsUtils.WHERE + dto.getReferenceColumnName() + " " + conditionMethod + " " + value + " ";
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


    public static InputStream getSSLConnection(String url) throws KeyManagementException, NoSuchAlgorithmException, IOException {

        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                return;
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                return;
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

     
        String userpassword = GlobalConstants.getUserPassword();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        String authEnc = new Base64Encoder().encode(userpassword.getBytes());
        httpURLConnection.setRequestProperty("Authorization", "Basic " + authEnc);
        InputStream is = httpURLConnection.getInputStream();
        return is;
    }

    public static Map<Integer, String> getInclusionExclusionRulesWithoutBPM(String hierarchyDefName) {
        inclusionExclusionRules.clear();
        inclusionExclusionRules = new HashMap<>();
        dependenciesMap.clear();
        dependenciesMap = new HashMap<>();
        List<String> rulesName = new ArrayList<>();
        Map<String, Object> ruleMap = new HashMap<>();
        try {
            String query = "select * from dbo.HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID \n"
                    + "in (select HIERARCHY_DEFINITION_SID from dbo.HIERARCHY_DEFINITION where HIERARCHY_NAME='" + hierarchyDefName + "')";
            int totalLevels = 0;
            List list = HistRelationshipBuilderLocalServiceUtil.executeQuery(query);
            if (list != null && list.size() > 0) {
                totalLevels = list.size();
                for (int i = 0; i < totalLevels; i++) {
                    Object[] obj = (Object[]) list.get(i);
                    String inclusionRuleType = String.valueOf(obj[NumericConstants.TWELVE]);
                    String exclusionRuleType = String.valueOf(obj[NumericConstants.FOURTEEN]);
                    if (!inclusionRuleType.equals(NULL) && !inclusionRuleType.equals(StringUtils.EMPTY) && !inclusionRuleType.equals(CommonUtils.GROUP)) {
                        String inclusionRule = String.valueOf(obj[NumericConstants.THIRTEEN]);
                        rulesName.add(inclusionRule);
                    }

                    if (!exclusionRuleType.equals(NULL) && !exclusionRuleType.equals(StringUtils.EMPTY) && !exclusionRuleType.equals(CommonUtils.GROUP)) {
                        String exclusionRule = String.valueOf(obj[NumericConstants.FIFTEEN]);
                        rulesName.add(exclusionRule);
                    }

                    if (!inclusionRuleType.equals(NULL) && !inclusionRuleType.equals(StringUtils.EMPTY) && inclusionRuleType.equals(CommonUtils.GROUP)) {
                        String groupRule = String.valueOf(obj[NumericConstants.THIRTEEN]);
                        String ruleGroupQuery = "select distinct HRD.RULE_NAME from dbo.HIERARCHY_LEVEL_DEFINITION HLD JOIN dbo.HIERARCHY_RULES_DEFINITION HRD on HLD.INCLUSION_RULE=HRD.RULE_FLOW_GROUP_NAME\n"
                                + "AND HRD.RULE_FLOW_GROUP_NAME='" + groupRule + "'";
                        List ruleGroupList = HistRelationshipBuilderLocalServiceUtil.executeQuery(ruleGroupQuery);
                        if (ruleGroupList != null && ruleGroupList.size() > 0) {
                            for (Object rule : ruleGroupList) {
                                rulesName.add(String.valueOf(rule));
                            }
                        }
                    }
                    if (!exclusionRuleType.equals(NULL) && !exclusionRuleType.equals(StringUtils.EMPTY) && exclusionRuleType.equals(CommonUtils.GROUP)) {
                        String exclusionRule = String.valueOf(obj[NumericConstants.FIFTEEN]);
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
                    String inclusionRuleType = String.valueOf(obj[NumericConstants.TWELVE]);
                    String inclusionRule = String.valueOf(obj[NumericConstants.THIRTEEN]);
                    String inclusionCondition = String.valueOf(obj[NumericConstants.SIXTEEN]);
                    String exclusionRuleType = String.valueOf(obj[NumericConstants.FOURTEEN]);
                    String exclusionRule = String.valueOf(obj[NumericConstants.FIFTEEN]);
                    String exclusionCondition = String.valueOf(obj[NumericConstants.SEVENTEEN]);
                    HierarchyDefinitionLevelRuleDTO dto;
                    // For Inclusion Rule Group
                    if (CommonUtils.GROUP.equalsIgnoreCase(inclusionRuleType)) {
                        List<String> ruleNameList = getGroupRuleList(inclusionRule);
                        List<HierarchyDefinitionLevelRuleDTO> inclusionList = new ArrayList<>();
                        for (String ruleName : ruleNameList) {
                            dto = new HierarchyDefinitionLevelRuleDTO();
                            dto.setRuleName(ruleName);
                            Object[] object = (Object[]) ruleMap.get(ruleName);
                            dto.setCondition1(String.valueOf(object[NumericConstants.THREE]));
                            dto.setDimension(String.valueOf(object[1]));
                            dto.setAttribute(String.valueOf(object[NumericConstants.TWO]));
                            dto.setValue1(String.valueOf(object[NumericConstants.FOUR]));
                           
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
                            dto.setCondition1(String.valueOf(obje[NumericConstants.THREE]));
                            dto.setDimension(String.valueOf(obje[1]));
                            dto.setAttribute(String.valueOf(obje[NumericConstants.TWO]));
                            dto.setValue1(String.valueOf(obje[NumericConstants.FOUR]));
                            rules.append(buildQuery(dto, "Inclusion"));
                        }
                    }
                    dto = new HierarchyDefinitionLevelRuleDTO();
                    // For Exclusion Rule Group
                    if (CommonUtils.GROUP.equalsIgnoreCase(exclusionRuleType)) {
                        // Need to code
                        List<String> ruleNameList = getGroupRuleList(exclusionRule);
                        List<HierarchyDefinitionLevelRuleDTO> exclusionList = new ArrayList<>();
                        for (String ruleName : ruleNameList) {
                            dto = new HierarchyDefinitionLevelRuleDTO();
                            dto.setRuleName(ruleName);
                            Object[] object = (Object[]) ruleMap.get(ruleName);
                            dto.setCondition1(String.valueOf(object[NumericConstants.THREE]));
                            dto.setDimension(String.valueOf(object[1]));
                            dto.setAttribute(String.valueOf(object[NumericConstants.TWO]));
                            dto.setValue1(String.valueOf(object[NumericConstants.FOUR]));
                            exclusionList.add(dto);
                        }
                        if (!exclusionList.isEmpty()) {
                            if (rules.length() > 1) {
                                rules.append(ConstantsUtils.AND);
                            }
                            rules.append(buildQuery(exclusionList, CommonUtils.EXCLUSION, exclusionCondition));
                        }
                        // For single Exclusion Rule
                    } else {
                        dto.setRuleName(exclusionRule);
                        if (exclusionRule != null && !exclusionRule.equals("null") && !exclusionRule.equals(ConstantsUtils.EMPTY) && ruleMap.get(exclusionRule) != null) {
                            if (rules.length() > 1) {
                                rules.append(ConstantsUtils.AND);
                            }
                            Object[] obje = (Object[]) ruleMap.get(exclusionRule);
                            dto.setCondition1(String.valueOf(obje[NumericConstants.THREE]));
                            dto.setDimension(String.valueOf(obje[1]));
                            dto.setAttribute(String.valueOf(obje[NumericConstants.TWO]));
                            dto.setValue1(String.valueOf(obje[NumericConstants.FOUR]));
                            rules.append(buildQuery(dto, CommonUtils.EXCLUSION));
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
        List<String> ruleList = new ArrayList<>();
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
