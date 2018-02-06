
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.common;

import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.LoadDdlbDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.dto.GroupDTO;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.accountclose.dto.WorkflowMasterDTO;
import com.stpl.app.accountclose.gtnbalancereport.dao.DataSelectionDAO;
import com.stpl.app.accountclose.gtnbalancereport.dao.daoImpl.DataSelectionDaoImpl;
import com.stpl.app.accountclose.gtnbalancereport.logic.AbstractFilter;
import com.stpl.app.accountclose.lazyload.DdlbCriteria;
import com.stpl.app.accountclose.logic.CommonQueryLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.BPIWorkFlowGeneratorXML;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import com.stpl.app.accountclose.utils.Converters;
import com.stpl.app.accountclose.utils.QueryUtils;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.vaadin.data.Container;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 *
 * @author santanukumar
 */
public class CommonLogic {

    /**
     * The data selection.
     */
    public static final DataSelectionDAO dataSelection = new DataSelectionDaoImpl();
    public static final HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    CommonQuery commonQuery = new CommonQuery();
    BaseRateDAO dao = new BaseRateDAOImpl();
    public static final Logger LOGGER = Logger.getLogger(CommonLogic.class);
    CommonUtils utils = new CommonUtils();
    private static final DecimalFormat BR_PERCENTAGE_FORMAT = new DecimalFormat("###0.0000%");
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";

    /**
     *
     * @param filterText
     * @return
     * @throws Exception
     */
    public int getCompaniesCount(String filterText) throws Exception {
        int count = 0;

        DynamicQuery helper = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
        helperProjectionList.add(ProjectionFactoryUtil.property("helperTableSid"));
        helper.add(RestrictionsFactoryUtil.eq("listName", "COMP_TYPE"));
        helper.add(RestrictionsFactoryUtil.like("description", "Glcomp"));
        helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
        List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
        int companyId = 0;
        companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        filterText = StringUtils.trimToEmpty(filterText) + "%";
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("companyMasterSid"));
        productProjectionList.add(ProjectionFactoryUtil.property("companyName"));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("companyName", filterText));
        dynamicQuery.add(RestrictionsFactoryUtil.eq("companyType", companyId));

        count = dataSelection.getCompaniesCount(dynamicQuery);
        return count;
    }

    /**
     *
     * @param startIndex
     * @param endIndex
     * @param filterText
     * @param companyDdlbDefault
     * @param selectedCompanyDdlbDto
     * @return
     * @throws Exception
     */
    public List<HelperDTO> getCompanies(int startIndex, int endIndex, String filterText, HelperDTO companyDdlbDefault, HelperDTO selectedCompanyDdlbDto) throws Exception {
        List<HelperDTO> companies = new ArrayList<HelperDTO>();
        if (startIndex == 0) {
            companies.add(companyDdlbDefault);
        }
        DynamicQuery helper = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
        helperProjectionList.add(ProjectionFactoryUtil.property("helperTableSid"));
        helper.add(RestrictionsFactoryUtil.eq("listName", "COMP_TYPE"));
        helper.add(RestrictionsFactoryUtil.like("description", "Glcomp"));
        helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
        List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
        int companyId = 0;
        companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        filterText = StringUtils.trimToEmpty(filterText) + "%";
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("companyMasterSid"));
        productProjectionList.add(ProjectionFactoryUtil.property("companyName"));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("companyName", filterText));
        dynamicQuery.add(RestrictionsFactoryUtil.eq("companyType", companyId));
        dynamicQuery.setLimit(startIndex, endIndex);
        List<Object[]> returnlist = dataSelection.getCompanies(dynamicQuery);
        HelperDTO companyDdlbDto;
        if (selectedCompanyDdlbDto == null) {
            for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
                Object[] objects = (Object[]) returnlist.get(loop);
                companyDdlbDto = new HelperDTO((Integer.valueOf(String.valueOf(objects[0]))), String.valueOf(objects[1]));
                companies.add(companyDdlbDto);
            }
        } else {
            for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
                Object[] objects = (Object[]) returnlist.get(loop);
                if ((Integer.valueOf(String.valueOf(objects[0]))) == selectedCompanyDdlbDto.getId()) {
                    selectedCompanyDdlbDto.setDescription(String.valueOf(objects[1]));
                    companies.add(selectedCompanyDdlbDto);
                } else {
                    companyDdlbDto = new HelperDTO((Integer.valueOf(String.valueOf(objects[0]))), String.valueOf(objects[1]));
                    companies.add(companyDdlbDto);
                }
            }
        }

        return companies;
    }

    //Generic method for dropdown
    public void LazyLoadDdlb(final ComboBox comboBox, String countFlag, String findFlag, String filterValue) {
        LazyContainer containerData = new LazyContainer(HelperDTO.class, new LoadDdlbDAO(countFlag, findFlag, filterValue), new DdlbCriteria());
        comboBox.setPageLength(7);
        comboBox.setContainerDataSource(containerData);
        comboBox.setNullSelectionItemId(ddlbDefaultValue);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId("description");
        containerData.setMinFilterLength(0);
    }

    //This method will retrieve the count for Ddbl
    public int getDdlbCount(String QueryName, final List<String> input) {
        List<Object[]> list = CommonQuery.getDdlbData(input, QueryName, null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    //This method will retrieve the Ddlb values
    public List<HelperDTO> getDdlbList(String QueryName, final List<String> input, boolean isLazyload) {
        List<Object[]> list = CommonQuery.getDdlbData(input, QueryName, null);
        List<HelperDTO> resultList = new ArrayList<>();
        if (isLazyload) {
            if (Integer.valueOf(String.valueOf(input.get(1))) == 0) {
                resultList.add(ddlbDefaultValue);
            }
        } else {
            resultList.add(ddlbDefaultValue);
        }

        for (Object[] str : list) {
            HelperDTO dto = new HelperDTO();
            dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
            dto.setDescription(str[1] == null ? "0" : String.valueOf(str[1]));
            resultList.add(dto);
        }
        return resultList;
    }

    /**
     * Load data
     *
     * @param parentId
     * @param projSelDTO
     * @return results list
     */
    public List<TreeDTO> getConfigureLoadData(Object parentId, TreeDTO projSelDTO, String queryName, int start, int offset) {
        List<TreeDTO> salesList;
        if (parentId instanceof TreeDTO) {
            TreeDTO dto = (TreeDTO) parentId;
            projSelDTO.setLevelNo(dto.getLevelNo() + 1);
            projSelDTO.setIdMainMap(getLevelMap(projSelDTO, dto));
            projSelDTO.setComapnySid(dto.getComapnySid());
        } else {
            projSelDTO.setLevelNo(1);
            projSelDTO.setComapnySid(StringUtils.EMPTY);
        }
        salesList = configureLevels(projSelDTO, queryName, start, offset);

        return salesList;
    }

    /**
     * -
     * get level map
     *
     * @param projSelDTO
     * @param dto
     * @return
     */
    private Map<Integer, Map<String, String>> getLevelMap(TreeDTO projSelDTO, TreeDTO dto) {
        Map<String, String> idMap = new HashMap<String, String>();
        Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>();
        if (projSelDTO.getLevelNo() == 1) {
            idMap.put("Contract", projSelDTO.getContractSid());
        } else if (projSelDTO.getLevelNo() == 2) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getGlComapnySid());
            idMainMap.put(projSelDTO.getLevelNo(), idMap);
        } else if (projSelDTO.getLevelNo() == 3) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getGlComapnySid());
            idMap.put("brand", dto.getBrandMasterSid());
            idMainMap.put(projSelDTO.getLevelNo(), idMap);
        } else if (projSelDTO.getLevelNo() == 4) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getGlComapnySid());
            idMap.put("brand", dto.getBrandMasterSid());
            idMap.put("item", dto.getItemSid());
            idMainMap.put(projSelDTO.getLevelNo(), idMap);
        } else if (projSelDTO.getLevelNo() == 5) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getGlComapnySid());
            idMap.put("brand", dto.getBrandMasterSid());
            idMap.put("rebate", dto.getRebateId());
            idMap.put("item", dto.getItemSid());
            idMainMap.put(projSelDTO.getLevelNo(), idMap);
        }
        return idMainMap;
    }

    /**
     * Get configure level
     *
     * @param projSelDTO
     * @return
     */
    public List<TreeDTO> configureLevelSales(TreeDTO projSelDTO, String queryName) {
        LOGGER.info("Entering configureLevelSales" + projSelDTO.getLevelNo() + "-->" + queryName);
        List<TreeDTO> resultList = new ArrayList<TreeDTO>();
        try {
            String query = commonQuery.getLoadDataQuery(projSelDTO, queryName);
            query = query + "OFFSET  " + projSelDTO.getStartIndex() + "  ROWS FETCH NEXT  " + projSelDTO.getOffSet() + "  ROWS ONLY ;";
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            Set<String> levelName = new HashSet<String>();
            TreeDTO dto;
            for (Object object : list) {
                final Object[] obj = (Object[]) object;
                dto = new TreeDTO();
                dto.setId(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                if (projSelDTO.getLevelNo() == 1) {
                    dto.setComapnySid(obj[1].toString());
                }
                if (projSelDTO.getLevelNo() == 2) {
                    dto.setContractSid(obj[1].toString());
                }

                if (projSelDTO.getLevelNo() == 5) {
                    dto.setItemSid(obj[1].toString());
                }
                dto.setParent(1);
                if (projSelDTO.getLevelNo() == 5) {
                    dto.setParent(0);
                }
                dto.setLevelNo(projSelDTO.getLevelNo());
                if (!utils.getNull(String.valueOf(obj[2]))) {
                    dto.addStringProperties("currRate", String.valueOf(obj[2]));
                }
                if (!utils.getNull(String.valueOf(obj[3]))) {
                    dto.addStringProperties("penRate", String.valueOf(obj[3]));
                }
                resultList.add(dto);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending configureLevelSales================================================" + resultList.size());
        return resultList;
    }

    /**
     * Get sales count
     *
     * @param parentId
     * @param projSelDTO
     * @return integer
     */
    public int getConfigureCount(Object parentId, TreeDTO projSelDTO, String queryName) {
        LOGGER.info("Entering getConfigureCount");
        if (parentId instanceof TreeDTO) {
            TreeDTO dto = (TreeDTO) parentId;
            projSelDTO.setLevelNo(dto.getLevelNo() + 1);
            projSelDTO.setIdMainMap(getLevelMap(projSelDTO, dto));
            projSelDTO.setComapnySid(dto.getComapnySid());
        } else {
            projSelDTO.setLevelNo(1);
            projSelDTO.setComapnySid(StringUtils.EMPTY);
        }

        int count = configureLevelCount(projSelDTO, queryName);
        LOGGER.info("Ending getConfigureCount" + count);
        return count;
    }

    public int configureLevelCount(TreeDTO projSelDTO, String queryName) {
        List<Object[]> list = new ArrayList<Object[]>();
        try {
            List input = new ArrayList();
            Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>(projSelDTO.getIdMainMap());
            Map<String, String> idMap = idMainMap.get(projSelDTO.getLevelNo());
            if (projSelDTO.getLevelNo() == 1) {
                input.add(projSelDTO.getContractSid());
            } else if (projSelDTO.getLevelNo() == 2) {
                input.add(projSelDTO.getContractSid());
            } else if (projSelDTO.getLevelNo() == 3) {
                input.add(projSelDTO.getItemSid());
            } else if (projSelDTO.getLevelNo() == 4) {
                input.add(projSelDTO.getItemSid());
            } else if (projSelDTO.getLevelNo() == 5) {
                input.add(projSelDTO.getItemSid());

            }
            String query = commonQuery.getDataQuery(projSelDTO, queryName);
            list = (List<Object[]>) dao.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("configureLevelCount is" + list.size());
        return list == null ? 0 : list.size();
    }

    public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout) {
        HorizontalLayout controlBar = new HorizontalLayout();
        controlBar.setStyleName("responsivePagedTable");

        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < 8; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
        return controlBar;
    }

    public static ComboBox getComboBox(final ComboBox select, final List<HelperDTO> helperList) throws Exception {
        select.removeAllItems();
        int size = helperList.size();
        for (int i = 0; i < size; i++) {
            final HelperDTO helperDTO = helperList.get(i);
            String itemId1 = StringUtils.EMPTY + helperDTO.getId();
            select.addItem(itemId1);
            select.setItemCaption(itemId1, helperDTO.getDescription());
        }
        select.setValue("0");
        return select;
    }

    public List<GroupDTO> getCustomerProductGroup(GroupDTO groupDTO, Set<Container.Filter> filters) throws PortalException, Exception {
        List<GroupDTO> searchList = new ArrayList<GroupDTO>();
        List input = new ArrayList();
        String query = StringUtils.EMPTY;
        LOGGER.info("Indicator=====================" + groupDTO.getIndicator());
        LOGGER.info("Group name ==================" + groupDTO.getCustomerGroupName());
        if (groupDTO.getIndicator().equals(INDICATOR_PRODUCT_GROUP.getConstant())) {

            if (StringUtils.isNotBlank(groupDTO.getProductGroupNo())) {
                input.add(CommonUtil.astToPerConverter(groupDTO.getProductGroupNo()));
            } else {
                input.add("%");
            }
            if (StringUtils.isNotBlank(groupDTO.getProductGroupName())) {
                input.add(CommonUtil.astToPerConverter(groupDTO.getProductGroupName()));
            } else {
                input.add("%");
            }
            input.add(AbstractFilter.getInstance().gtnBalanceProductLookUp(filters).toString());
            query = "productGroupSearch";
            input.add(" ORDER BY IG.item_Group_Name OFFSET " + groupDTO.getStartIndex() + "  ROWS FETCH NEXT " + groupDTO.getEndIndex() + " ROWS ONLY");
        }
        if (groupDTO.getIndicator().equals(Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP.getConstant())) {
            if (StringUtils.isNotBlank(groupDTO.getCustomerGroupNo())) {
                input.add(CommonUtil.astToPerConverter(groupDTO.getProductGroupNo()));
            } else {
                input.add("%");
            }
            if (StringUtils.isNotBlank(groupDTO.getCustomerGroupName())) {
                input.add(CommonUtil.astToPerConverter(groupDTO.getProductGroupName()));
            } else {
                input.add("%");
            }
            input.add(AbstractFilter.getInstance().gtnBalanceCustomerLookUp(filters).toString());
            query = "customerGroupSearch";
            input.add(" ORDER BY CG.company_Group_Name OFFSET " + groupDTO.getStartIndex() + "  ROWS FETCH NEXT " + groupDTO.getEndIndex() + " ROWS ONLY");
        }
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, query);
        searchList = getCustomisedGroupDto(resultList, groupDTO);
        return searchList;
    }

    public int getCustomerProductGroupCount(GroupDTO groupDTO, Set<Container.Filter> filters) throws PortalException, Exception {
        List input = new ArrayList();
        String query = StringUtils.EMPTY;
        LOGGER.info("Indicator=====================" + groupDTO.getIndicator());
        LOGGER.info("Group name ==================" + groupDTO.getCustomerGroupName());
        if (groupDTO.getIndicator().equals(INDICATOR_PRODUCT_GROUP.getConstant())) {
            if (StringUtils.isNotBlank(groupDTO.getProductGroupNo())) {
                input.add(CommonUtil.astToPerConverter(groupDTO.getProductGroupNo()));
            } else {
                input.add("%");
            }
            if (StringUtils.isNotBlank(groupDTO.getProductGroupName())) {
                input.add(CommonUtil.astToPerConverter(groupDTO.getProductGroupName()));
            } else {
                input.add("%");
            }

            input.add(AbstractFilter.getInstance().gtnBalanceProductLookUp(filters).toString());

            query = "productGroupSearchCount";
        }
        if (groupDTO.getIndicator().equals(Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP.getConstant())) {
            if (StringUtils.isNotBlank(groupDTO.getCustomerGroupNo())) {
                input.add(CommonUtil.astToPerConverter(groupDTO.getCustomerGroupNo()));
            } else {
                input.add("%");
            }
            if (StringUtils.isNotBlank(groupDTO.getCustomerGroupName())) {
                input.add(CommonUtil.astToPerConverter(groupDTO.getCustomerGroupName()));
            } else {
                input.add("%");
            }
            input.add(AbstractFilter.getInstance().gtnBalanceCustomerLookUp(filters).toString());
            query = "customerGroupSearchCount";
        }
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, query);
        Object obj = null;
        if (!resultList.isEmpty()) {
            obj = resultList.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    private List<GroupDTO> getCustomisedGroupDto(List results, GroupDTO groupDTO) {
        List<GroupDTO> searchList = new ArrayList<GroupDTO>();
        int size = results.size();
        if (groupDTO.getIndicator().equals(INDICATOR_PRODUCT_GROUP.getConstant())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                GroupDTO dTO = new GroupDTO();
                dTO.setProductGroupSid(Converters.convertNullToEmpty(arr[0]));
                dTO.setProductGroupName(Converters.convertNullToEmpty(arr[1]));
                dTO.setProductGroupNo(Converters.convertNullToEmpty(arr[2]));
                dTO.setProductGroupVersionNo(Converters.convertNullToEmpty(arr[3]));
                dTO.setProductGroupDesc(Converters.convertNullToEmpty(arr[4]));
                dTO.setCompany(Converters.convertNullToEmpty(arr[5]));
                searchList.add(dTO);
            }
        } else {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                GroupDTO dTO = new GroupDTO();
                dTO.setCustomerGroupSid(Converters.convertNullToEmpty(arr[0]));
                dTO.setCustomerGroupNo(Converters.convertNullToEmpty(arr[1]));
                dTO.setCustomerGroupName(Converters.convertNullToEmpty(arr[2]));
                dTO.setCustomerGroupVersionNo(Converters.convertNullToEmpty(arr[3]));
                dTO.setCustomerGroupDesc(Converters.convertNullToEmpty(arr[4]));
                searchList.add(dTO);
            }
        }
        return searchList;
    }

    /**
     * To update the check record during mass update or adjustment
     *
     * @param session
     * @param hierarchyIndicator
     * @param isCustomView
     * @param relationshipBuilderSid
     * @param customViewDetails
     */
    public void updateCheckRecord(TreeDTO dto, int val, String type) {
        try {

            String query = commonQuery.updateCheckQuery(dto, val, type);

            dao.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    public static String convertStringToDate(String stringDate, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
            Date date = new Date();
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    /**
     * Method to Save the data in WorkflowMaster based on base rate Sid & User
     * ID
     *
     * @param projectionId
     * @param userId
     * @return Status as Saved or Not Saved
     * @throws IOException
     */
    public WorkflowMasterDTO saveWorkflow(int projectionId, String userId, int statusId, boolean flag, WorkflowMasterDTO wfId) throws IOException {
        String filePath1 =CommonUtil.getJbossHome()+"/WorkflowXML/BPIGeneratorIDs.xml";
        String workflowId = StringUtils.EMPTY;
        if (flag) {
            wfId.setWorkflowId(new BPIWorkFlowGeneratorXML().generateId(filePath1, "BR"));
        }
        WorkflowMasterDTO workflowMasterDTO = setBRWorkflowMasterDTO(projectionId, wfId, userId, statusId);
        return saveBRWorkflowMaster(workflowMasterDTO, flag);
    }

    public String saveWorkflowFDA(int projectionId, String userId, int statusId, boolean flag, String wfId, String notes) throws IOException {
        String filePath1 = CommonUtil.getJbossHome()+"/WorkflowXML/BPIGeneratorIDs.xml";
        String workflowId = StringUtils.EMPTY;
        if (flag) {
            workflowId = new BPIWorkFlowGeneratorXML().generateId(filePath1, "FD");
        } else {
            workflowId = wfId;
        }
        WorkflowMasterDTO workflowMasterDTO = setWorkflowMasterDTO(projectionId, workflowId, userId, statusId, notes);
        return saveWorkflowMaster(workflowMasterDTO, flag);

    }

    /**
     * Method to set the values in WF based on base rate Sid,workflowId,userId
     *
     * @param projectionId
     * @param workflowId
     * @param userId
     * @return WorkflowMasterDTO Object
     */
    public WorkflowMasterDTO setWorkflowMasterDTO(int projectionId, String workflowId, String userId, int statusId, String notes) {
        WorkflowMasterDTO workflowMasterDTO = new WorkflowMasterDTO();

        int userIdInt = Integer.parseInt(userId);
        workflowMasterDTO.setWorkflowId(workflowId);
        workflowMasterDTO.setWorkflowStatus(statusId);
        workflowMasterDTO.setCreatedBy(userIdInt);
        workflowMasterDTO.setNotes(notes);
        workflowMasterDTO.setCreatedDate(new Date());
        workflowMasterDTO.setProjectionId(projectionId);
        return workflowMasterDTO;
    }

    /**
     * Method to Save the data in WorkflowMaster
     *
     * @param workflowMasterDTO
     * @return Status as Saved or Not Saved
     */
    public String saveWorkflowMaster(WorkflowMasterDTO workflowMasterDTO, boolean flag) {
        LOGGER.info("inside save workflow master");
        try {
            if (flag) {
                StringBuilder query = new StringBuilder();
                query.append("INSERT INTO WORKFLOW_MASTER (WORKFLOW_ID, PROJECTION_MASTER_SID, WORKFLOW_STATUS_ID, APPROVED_BY, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ACC_CLOSURE_MASTER_SID, NOTES) VALUES");
                query.append("('").append(workflowMasterDTO.getWorkflowId()).append("',").append("NULL").append(",'");
                query.append(workflowMasterDTO.getWorkflowStatus()).append("','").append("0").append("','").append(workflowMasterDTO.getCreatedBy()).append("',").append("CURRENT_TIMESTAMP").append(",'").append(workflowMasterDTO.getCreatedBy()).append("',").append("CURRENT_TIMESTAMP").append(",'").append(workflowMasterDTO.getProjectionId()).append("','").append(workflowMasterDTO.getNotes()).append("')\n");
                dao.executeUpdateQuery(query.toString());

            } else {
                try {
                    String qorkflowid = workflowMasterDTO.getWorkflowId();

                    StringBuilder query = new StringBuilder();
                    query.append("update WORKFLOW_MASTER set WORKFLOW_STATUS_ID='");
                    query.append(workflowMasterDTO.getWorkflowStatus()).append("',MODIFIED_BY='").append(workflowMasterDTO.getCreatedBy()).append("',").append("MODIFIED_DATE = CURRENT_TIMESTAMP, NOTES='").append(workflowMasterDTO.getNotes()).append("' where WORKFLOW_ID ='").append(qorkflowid).append("'\n");
                    dao.executeUpdateQuery(query.toString());
                } catch (Exception e) {
                    LOGGER.error(e);
                }

            }

        } catch (Exception ex) {
            LOGGER.error(ex);
            return CommonUtils.WORKFLOW_NOT_SAVED;
        }
        return workflowMasterDTO.getWorkflowId();
    }

    /**
     * Gets the user name from userId
     *
     * @param userId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public User getUserById(final String userId) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering getUserById method with userId " + userId);
        return dao.getUser(Long.valueOf(userId));
    }

    public void clearTemp() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        Date tempDate = new Date();
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        tempDate.setDate(tempDate.getDate() - 2);
        input.put("?LD", fmt.format(tempDate));
        String query = CommonUtil.getQuery(input, "ac.clearTemp");
        dao.executeUpdateQuery(query);
    }

    public void loadComboBox(ComboBox comboBox, List<HelperDTO> dropDownList) {
        for (int i = 0; dropDownList.size() > i; i++) {
            comboBox.addItem(String.valueOf(dropDownList.get(i)
                    .getId()));
            comboBox.setItemCaption(String.valueOf(dropDownList.get(i)
                    .getId()), dropDownList.get(i).getDescription());

        }
    }

    public static List<HelperDTO> getDropDownList(final String listType) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery helperTableQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        helperTableQuery.add(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType));
        helperTableQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(helperTableQuery);
        helperList.add(ddlbDefaultValue);
        if (list != null) {
            for (HelperTable temp : list) {
                final HelperTable helperTable = (HelperTable) temp;
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));
            }
        }
        return helperList;
    }

    public static ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) throws Exception {
        select.removeAllItems();
        int size = helperList.size();
        for (int i = 0; i < size; i++) {
            final HelperDTO helperDTO = helperList.get(i);
            String itemId1 = StringUtils.EMPTY + helperDTO.getId();
            select.addItem(itemId1);
            select.setItemCaption(itemId1, helperDTO.getDescription());
        }
        select.setValue("0");
        return select;
    }

    public void massUpdateBaseRateSummary(Object value, SessionDTO session, boolean isReasonCode) {
        List input = new ArrayList();
        if (isReasonCode) {
            input.add("STS.REASON_CODE");
        } else {
            input.add("STS.NOTES");
        }
        input.add(value);
        input.addAll(getResultsIntput(session));
        CommonQueryLogic.itemUpdate(input, "Mass populate");
    }

    public static List getResultsIntput(SessionDTO session) {
        List input = new ArrayList();
        input.add(session.getUserId());
        input.add(session.getSessionId());
        return input;
    }

    /**
     * Method to set the values in WF based on base rate Sid,workflowId,userId
     *
     * @param projectionId
     * @param workflowId
     * @param userId
     * @return WorkflowMasterDTO Object
     */
    public WorkflowMasterDTO setBRWorkflowMasterDTO(int projectionId, WorkflowMasterDTO workflowId, String userId, int statusId) {
        WorkflowMasterDTO workflowMasterDTO = new WorkflowMasterDTO();
        int userIdInt = Integer.parseInt(userId);
        workflowMasterDTO.setWorkflowId(workflowId.getWorkflowId());
        workflowMasterDTO.setWorkflowMasterSystemId(workflowId.getWorkflowMasterSystemId());
        workflowMasterDTO.setWorkflowStatus(statusId);
        workflowMasterDTO.setCreatedBy(userIdInt);
        workflowMasterDTO.setCreatedDate(new Date());
        workflowMasterDTO.setProjectionId(projectionId);
        workflowMasterDTO.setNotes(workflowId.getNotes());
        workflowMasterDTO.setNoOfApprovals(workflowId.getNoOfApprovals());
        return workflowMasterDTO;
    }

    /**
     * Method to Save the data in WorkflowMaster
     *
     * @param workflowMasterDTO
     * @return Status as Saved or Not Saved
     */
    public WorkflowMasterDTO saveBRWorkflowMaster(WorkflowMasterDTO workflowMasterDTO, boolean flag) {
        WorkflowMaster workflowMaster = WorkflowMasterLocalServiceUtil.createWorkflowMaster(0);
        workflowMaster.setWorkflowId(workflowMasterDTO.getWorkflowId());
        workflowMaster.setWorkflowStatusId(workflowMasterDTO.getWorkflowStatus());
        workflowMaster.setWorkflowMasterSid(workflowMasterDTO.getWorkflowMasterSystemId());
        workflowMaster.setAccClosureMasterSid(workflowMasterDTO.getProjectionId());
        workflowMaster.setCreatedBy(workflowMasterDTO.getCreatedBy());
        workflowMaster.setCreatedDate(workflowMasterDTO.getCreatedDate());
        workflowMaster.setNotes(workflowMasterDTO.getNotes());
        workflowMaster.setNoOfApproval(workflowMasterDTO.getNoOfApprovals());
        if (!flag) {
            workflowMaster.setModifiedBy(workflowMasterDTO.getCreatedBy());
            workflowMaster.setModifiedDate(workflowMasterDTO.getCreatedDate());
        }
        String workflowmaster_sid = StringUtils.EMPTY;
        try {
            if (flag) {
                workflowMaster = dao.addBRWorkflowMaster(workflowMaster, flag);
            } else {

                String qorkflowid = workflowMasterDTO.getWorkflowId();
                String query = "select WORKFLOW_MASTER_SID,WORKFLOW_ID from WORKFLOW_MASTER where WORKFLOW_ID='" + qorkflowid + "'";
                List list1 = (List) dao.executeSelectQuery(query);
                for (int i = 0; i < list1.size(); i++) {
                    Object[] obje = (Object[]) list1.get(i);
                    workflowmaster_sid = obje[0].toString();
                    LOGGER.info("workflowmaster_sid" + workflowmaster_sid);

                }
                WorkflowMaster workflowMaster1 = WorkflowMasterLocalServiceUtil.getWorkflowMaster(Integer.valueOf(workflowmaster_sid));
                workflowMaster1.setWorkflowStatusId(workflowMasterDTO.getWorkflowStatus());
                workflowMaster1.setModifiedBy(workflowMasterDTO.getCreatedBy());
                workflowMaster1.setModifiedDate(new Date());
                workflowMaster1.setNotes(workflowMasterDTO.getNotes());
                workflowMaster1.setNoOfApproval(workflowMasterDTO.getNoOfApprovals());
                WorkflowMasterLocalServiceUtil.updateWorkflowMaster(workflowMaster1);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        workflowMasterDTO.setWorkflowMasterSystemId(workflowMaster.getWorkflowMasterSid());
        workflowMasterDTO.setAacSid(workflowMaster.getAccClosureMasterSid());
        return workflowMasterDTO;
    }

    /**
     * Get configure level
     *
     * @param projSelDTO
     * @return
     */
    public List<TreeDTO> configureLevels(TreeDTO projSelDTO, String queryName, int start, int offset) {
        LOGGER.info("Entering configureLevelSales" + projSelDTO.getComapnySid() + "-->" + queryName);
        List<TreeDTO> resultList = new ArrayList<TreeDTO>();
        try {
            String query = commonQuery.getDataQuery(projSelDTO, queryName);
            query = query + " OFFSET  " + start + "  ROWS FETCH NEXT  " + offset + "  ROWS ONLY ;";
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            Set<String> levelName = new HashSet<String>();
            TreeDTO dto;
            for (Object object : list) {
                final Object[] obj = (Object[]) object;
                dto = new TreeDTO();

                if (projSelDTO.getLevelNo() == 1) {
                    dto.setId(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                    dto.setComapnySid(obj[1].toString());
                }
                if (projSelDTO.getLevelNo() == 2) {
                    dto.setId(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                    dto.setContractSid(obj[1].toString());
                    dto.setComapnySid(projSelDTO.getComapnySid());
                }
                if (projSelDTO.getLevelNo() == 3) {
                    dto.setId(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                    dto.setComapnySid(projSelDTO.getComapnySid());
                }
                if (projSelDTO.getLevelNo() == 4) {
                    dto.setId(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                    dto.setComapnySid(projSelDTO.getComapnySid());
                }

                if (projSelDTO.getLevelNo() == 5) {
                    dto.setId(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                    dto.setComapnySid(projSelDTO.getComapnySid());
                    dto.setItemSid(obj[1].toString());
                }
                dto.setParent(1);
                if (projSelDTO.getLevelNo() == 5) {
                    dto.setParent(0);
                }
                dto.setLevelNo(projSelDTO.getLevelNo());
                if (!utils.getNull(String.valueOf(obj[2]))) {
                    dto.addStringProperties("currRate", BR_PERCENTAGE_FORMAT.format(Double.valueOf(String.valueOf(obj[2]))));
                }
                if (!utils.getNull(String.valueOf(obj[3]))) {
                    dto.addStringProperties("penRate", String.valueOf(obj[3]));
                }
                resultList.add(dto);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending configureLevelSales================================================" + resultList.size());
        return resultList;
    }

    public String getWorkflow(int sid) throws Exception {

        String query = "select WORKFLOW_ID from WORKFLOW_MASTER where WORKFLOW_MASTER_SID = " + sid;
        List list = (List<Object[]>) dao.executeSelectQuery(query);
        if (!list.isEmpty()) {
            return list.get(0).toString();
        } else {
            return StringUtils.EMPTY;
        }

    }

    public ComboBox commonLoadingDdlb(ComboBox comboBox, final String id, final String query) throws Exception {
        Map<String, String> ids = new HashMap<>();
        if (!"0".equals(id)) {
            ids.put("?", id);
        }
        List<HelperDTO> dtos = getDdlbList(query, ids);
        comboBox = getComboBox(comboBox, dtos);
        comboBox.setNullSelectionAllowed(false);
        return comboBox;
    }

    /**
     * This method will retrieve the Ddlb values
     *
     * @param QueryName
     * @param input
     * @return
     * @throws Exception
     */
    public List<HelperDTO> getDdlbList(String QueryName, final Map<String, String> input) throws Exception {
        List<Object[]> list = null;
        if (!input.isEmpty()) {
            list = (List<Object[]>) QueryUtils.executeSelectQuery(input, QueryName);
        }
        List<HelperDTO> resultList = new ArrayList<>();
        resultList.add(ddlbDefaultValue);
        if (list != null) {
            for (Object[] str : list) {
                HelperDTO dto = new HelperDTO();
                dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                dto.setDescription(str[1] == null ? "0" : String.valueOf(str[1]));
                resultList.add(dto);
            }
        }
        return resultList;
    }

    /**
     * Procedure Call
     *
     * @param procedureName
     * @param orderedArgs
     * @return
     */
    public static boolean callProcedure(String procedureName, Object[] orderedArgs) {
        LOGGER.info(" Inside callProcedure with Procedure Name " + procedureName);
        boolean executed = false;
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                String procedureToCall = "{call " + procedureName;
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall += "(";
                    }
                    procedureToCall += "?,";
                    if (i == noOfArgs - 1) {
                        procedureToCall += ")";
                    }
                }
                procedureToCall = procedureToCall.replace(",)", ")");
                procedureToCall += "}";
                statement = connection.prepareCall(procedureToCall);
                for (int i = 0; i < noOfArgs; i++) {
                    LOGGER.info(orderedArgs[i]);
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                try {
                    executed = statement.execute();
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        } catch (NamingException ex) {
            LOGGER.error(ex);
        } catch (SQLException ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
               
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.info(" Ending callProcedure");
        return executed;
    }

}
