/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.logic;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class PipelineInventoryLookupLogic {
    
    public static final Logger LOGGER = Logger.getLogger(PipelineInventoryLookupLogic.class);

    public int getInventCustomerProductGroupCount(CustomerGroupDTO groupDTO, BeanItemContainer<CustomerGroupDTO> resultsinventoryContainer, boolean viewFlag, AbstractSelectionDTO selectionDto) throws PortalException {
        try {
            getCustomisedConGroupDto(groupDTO, resultsinventoryContainer, viewFlag, selectionDto);

            return resultsinventoryContainer.size();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return 0;
        }
    }

    private void getCustomisedConGroupDto(CustomerGroupDTO groupDTO, BeanItemContainer<CustomerGroupDTO> resultsinventoryContainer, boolean viewFlag, AbstractSelectionDTO selectionDto) {
        List input = new ArrayList();
        String query = StringUtils.EMPTY;
        if (viewFlag) {
            query = "customerViewSearch";
            input.add("" + groupDTO.getViewSid());
        } else {
            query = "customerGroupSearch";
            input.add("" + selectionDto.getProjectionMasterSid());
            input.add("" + selectionDto.getSessionDTO().getUserId());
            input.add("" + selectionDto.getSessionDTO().getSessionId());
            input.add(selectionDto.getCustomerGroupSidSet().isEmpty()
                    ? String.valueOf(NumericConstants.ZERO) : StringUtils.join(selectionDto.getCustomerGroupSidSet(), ARMUtils.COMMA));
        }
        List<Object[]> results = QueryUtils.getItemData(input, query, null);
        int size = results.size();
        for (int i = 0; i < size; i++) {
            Object arr[] = results.get(i);
            CustomerGroupDTO dTO = new CustomerGroupDTO();
            dTO.setCustomerGroupSid(convertNullToEmpty(arr[0]));
            dTO.setCustomerGroupNo(convertNullToEmpty(arr[1]));
            dTO.setCustomerGroupName(convertNullToEmpty(arr[NumericConstants.TWO]));
            dTO.setCustomerGroupDesc(convertNullToEmpty(arr[NumericConstants.THREE]));
            if (!viewFlag) {
                dTO.setIndicator(arr[NumericConstants.FOUR] == null ? null : (Boolean) arr[NumericConstants.FOUR]);
                dTO.setInclude(arr[NumericConstants.FIVE] == null ? false : (boolean) arr[NumericConstants.FIVE]);
                dTO.setSelectedFlag(arr[NumericConstants.SIX] == null ? false : ((int) arr[NumericConstants.SIX]) > 0);
            }
            resultsinventoryContainer.addItem(dTO);
        }
    }

    public List<CustomerGroupDTO> getMovedCustomerProductGroup(CustomerGroupDTO groupDTO) throws PortalException {
        List<CustomerGroupDTO> searchList = new ArrayList<CustomerGroupDTO>();
        List input = new ArrayList();
        String query = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(groupDTO.getCustomerGroupNo())) {
            input.add(astToPerConverter(groupDTO.getCustomerGroupNo()));
        } else {
            input.add("%");
        }
        if (StringUtils.isNotBlank(groupDTO.getCustomerGroupName())) {
            input.add(astToPerConverter(groupDTO.getCustomerGroupName()));
        } else {
            input.add("%");
        }
        query = "movedCustomerGroup";

        List resultList = (List<Object[]>) QueryUtils.getItemData(input, query, null);
        searchList = getMovedCustomisedGroupDto(resultList);
        return searchList;
    }

    private List<CustomerGroupDTO> getMovedCustomisedGroupDto(List results) {
        List<CustomerGroupDTO> searchList = new ArrayList<CustomerGroupDTO>();
        int size = results.size();
        for (int i = 0; i < size; i++) {
            Object arr[] = (Object[]) results.get(i);
            CustomerGroupDTO dTO = new CustomerGroupDTO();
            dTO.setCustomerGroupSid(convertNullToEmpty(arr[0]));
            dTO.setCustomerGroupName(convertNullToEmpty(arr[NumericConstants.TWO]));
            searchList.add(dTO);
        }
        return searchList;
    }

    public static String astToPerConverter(final String inputString) {
        return StringUtils.isBlank(inputString) || "null".equals(inputString) ? "%" : inputString.replace("*", "%");
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || ARMUtils.NULL.equals(String.valueOf(value)) || ConstantsUtils.SELECT_ONE.equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }


    public void saveCustomerGroupValue(List<CustomerGroupDTO> saveList, int projectionId, AbstractSelectionDTO selectionDto) {
        try {
            StringBuilder saveQuery = new StringBuilder(StringUtils.EMPTY);
            saveQuery.append(SQlUtil.getQuery("saveCustomerGroupInventDetails").replace("@PROJECTION_MASTER_SID", "" + projectionId));
            for (CustomerGroupDTO dtoValue : saveList) {
                saveQuery.append("(" + projectionId + "," + (dtoValue.isInclude() == true ? 1 : 0) + ","
                        + (dtoValue.getIndicator() != null ? dtoValue.getIndicator() == true ? 1 : 0 : "null") + ","
                        + (StringUtils.EMPTY.equalsIgnoreCase(dtoValue.getCustomerGroupSid()) ? null : dtoValue.getCustomerGroupSid())
                        + "," + (StringUtils.EMPTY.equalsIgnoreCase(dtoValue.getCompanyMasterSid()) ? null : dtoValue.getCompanyMasterSid()) + "),");
            }
            saveQuery.replace(saveQuery.length() - 1, saveQuery.length(), "");
            HelperTableLocalServiceUtil.executeUpdateQuery( QueryUtil.replaceTableNames(saveQuery.toString(), selectionDto.getSessionDTO().getCurrentTableNames()));
        } catch (Exception ex) {
           LOGGER.error(ex);
        }

    }

    public void getCustomerGroupView(CustomerGroupDTO binderDto, BeanItemContainer<CustomerGroupDTO> custGroupList,
            boolean viewFlag, AbstractSelectionDTO selectionDto, String queryName) {
        try {
            String cgQuery = StringUtils.EMPTY;
            List<Object> listValue;
            if (viewFlag) {
                cgQuery = SQlUtil.getQuery("loadViewCustomerGroupDetails");
                cgQuery = cgQuery.replace("@ARM_VIEW_MASTER_SID", binderDto.getViewSid());
                listValue = HelperTableLocalServiceUtil.executeSelectQuery(cgQuery);
            } else {
                custGroupList.removeAllItems();
                List<String> input = new ArrayList<>();
                input.add("" + selectionDto.getProjectionMasterSid());
                input.add("" + selectionDto.getSessionDTO().getUserId());
                input.add("" + selectionDto.getSessionDTO().getSessionId());
                input.add(selectionDto.getCustomerGroupSidSet().isEmpty()
                    ? String.valueOf(NumericConstants.ZERO) : StringUtils.join(selectionDto.getCustomerGroupSidSet(), ARMUtils.COMMA));
                listValue = QueryUtils.getItemData(input, queryName, null);
            }

            for (int i = 0; i < listValue.size(); i++) {
                Object obj[] = (Object[]) listValue.get(i);
                CustomerGroupDTO dto = new CustomerGroupDTO();
                dto.setCustomerGroupSid(String.valueOf(obj[0]));
                dto.setCustomerGroupName(String.valueOf(obj[1]));
                dto.setIndicator(obj[NumericConstants.TWO] == null ? null : (Boolean) obj[NumericConstants.TWO]);
                dto.setInclude(obj[NumericConstants.THREE] == null ? false : (boolean) obj[NumericConstants.THREE]);
                dto.setSelectedFlag(true);
                custGroupList.addItem(dto);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public List<CustomerGroupDTO> getCustomerView(String viewMasterSid) {
        List<CustomerGroupDTO> custGroupList = new ArrayList();
        try {
            String cgQuery = SQlUtil.getQuery("loadCustomerDetailsQuery");
            cgQuery = cgQuery.replace("@ARM_VIEW_MASTER_SID", viewMasterSid);
            List<Object> listValue = HelperTableLocalServiceUtil.executeSelectQuery(cgQuery);
            for (int i = 0; i < listValue.size(); i++) {
                Object obj[] = (Object[]) listValue.get(i);
                CustomerGroupDTO dto = new CustomerGroupDTO();
                dto.setCompanyMasterSid(String.valueOf(obj[0]));
                dto.setCustomerName(String.valueOf(obj[1]));
                dto.setIndicator(obj[NumericConstants.TWO] == null ? null : (Boolean) obj[NumericConstants.TWO]);
                dto.setInclude((boolean) obj[NumericConstants.THREE]);
                custGroupList.add(dto);
            }
            return custGroupList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return custGroupList;
        }
    }

    public BeanItemContainer<CustomerGroupDTO> getInventCustomerView(String viewMasterSid) {
        BeanItemContainer<CustomerGroupDTO> custGroupList = new BeanItemContainer<CustomerGroupDTO>(CustomerGroupDTO.class);
        try {
            String cgQuery = "    SELECT\n"
                    + "    CM.COMPANY_GROUP_SID,\n"
                    + "    CM.COMPANY_GROUP_NAME,\n"
                    + "    AVD.\"INDICATOR\",\n"
                    + "    AVD.CHECK_RECORD\n"
                    + "FROM ARM_VIEW_DETAILS AVD\n"
                    + "JOIN dbo.COMPANY_GROUP CM  ON CM.COMPANY_GROUP_SID = AVD.COMPANY_GROUP_SID\n"
                    + "WHERE\n"
                    + "    AVD.COMPANY_GROUP_SID IS NOT NULL\n"
                    + "    AND AVD.CHECK_RECORD IS NOT NULL\n"
                    + "    AND AVD.ARM_VIEW_MASTER_SID = @ARM_VIEW_MASTER_SID;";
            cgQuery = cgQuery.replace("@ARM_VIEW_MASTER_SID", viewMasterSid);
            List<Object> listValue = HelperTableLocalServiceUtil.executeSelectQuery(cgQuery);
            for (int i = 0; i < listValue.size(); i++) {
                Object obj[] = (Object[]) listValue.get(i);
                CustomerGroupDTO dto = new CustomerGroupDTO();
                dto.setCustomerGroupSid(String.valueOf(obj[0]));
                dto.setCustomerGroupName(String.valueOf(obj[1]));
                dto.setIndicator(obj[NumericConstants.TWO] == null ? null : (Boolean) obj[NumericConstants.TWO]);
                dto.setInclude((boolean) obj[NumericConstants.THREE]);
                custGroupList.addItemAt(i, dto);
            }
            return custGroupList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return custGroupList;
        }
    }

    public List<CustomerGroupDTO> getPipelineInventory(int projectionId, AbstractSelectionDTO selectionDto) {
        try {
            List<CustomerGroupDTO> customerList = new ArrayList<>();
            boolean checkFlag = true;
            String custQuery = "Select Distinct ATID.COMPANY_MASTER_SID,CM.COMPANY_NAME,ATID.CHECK_RECORD,ATID.\"INDICATOR\"  from dbo.ST_ARM_TR_INVENTORY_DETAILS  ATID\n"
                    + "   Join COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=ATID.COMPANY_MASTER_SID  \n"
                    + "    where ATID.PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID "
                    + " AND ATID.USER_ID=@USER_ID AND ATID.SESSION_ID=@SESSION_ID;";

            custQuery = custQuery.replace("@PROJECTION_MASTER_SID", "" + projectionId).replace("@USER_ID", "" + selectionDto.getSessionDTO().getUserId())
                    .replace("@SESSION_ID", "" + selectionDto.getSessionDTO().getSessionId());
            List<Object> listValue = HelperTableLocalServiceUtil.executeSelectQuery(custQuery);
            if (listValue.isEmpty()) {
                custQuery = SQlUtil.getQuery("LOAD_INVENTORY_CUSTOMERS");
                listValue = HelperTableLocalServiceUtil.executeSelectQuery(custQuery);
                checkFlag = false;
            }
            for (int i = 0; i < listValue.size(); i++) {
                Object obj[] = (Object[]) listValue.get(i);
                CustomerGroupDTO dtoValue = new CustomerGroupDTO();
                dtoValue.setCompanyMasterSid(String.valueOf(obj[0]));
                dtoValue.setCustomerName(String.valueOf(obj[1]));
                if (checkFlag) {
                    dtoValue.setIndicator(obj[NumericConstants.THREE] == null ? null : (Boolean) obj[NumericConstants.THREE]);
                    dtoValue.setInclude(obj[NumericConstants.TWO] == null ? false : (boolean) obj[NumericConstants.TWO]);
                }
                customerList.add(dtoValue);
            }
            return customerList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.EMPTY_LIST;
        }

    }

    /**
     *
     * @param vmSid
     * @param viewCategory = Customer Group or Customer
     * @return
     */
    public List<ViewLookupDTO> getARCSavedPublicViewList(String vmSid, String viewCategory) {
        List<ViewLookupDTO> dtoList = Collections.EMPTY_LIST;
        try {
            String query = "select AVM.ARM_VIEW_MASTER_SID,AVM.VIEW_NAME,AVM.VIEW_TYPE,AVM.CREATED_BY,AVM.CREATED_DATE,AVM.MODIFIED_DATE,AVM.MODIFIED_BY from ARM_VIEW_MASTER AVM \n"
                    + "where  AVM.ARM_VIEW_MASTER_SID=@ARM_VIEW_MASTER_SID AND AVM.VIEW_TYPE ='Public' ";

            query = query.replace("@ARM_VIEW_MASTER_SID", vmSid);

            List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (rawList.size() > 0 && !rawList.isEmpty()) {

                dtoList = new ArrayList();
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = (Object[]) rawList.get(i);
                    ViewLookupDTO dto = new ViewLookupDTO();
                    dto.setViewSid(String.valueOf(obj[0]));
                    dto.setViewName(String.valueOf(obj[1]));
                    dto.setViewType("PublicView");
                    dto.setViewCategory(viewCategory);
                    dto.setCreatedUser(String.valueOf(obj[NumericConstants.THREE]));
                    dto.setCreatedDate((Date) obj[NumericConstants.FOUR]);
                    dto.setModifiedDate((Date) obj[NumericConstants.FIVE]);
                    dtoList.add(dto);
                }
            }
            return dtoList;
        } catch (Exception e) {
            LOGGER.error(e);
            return dtoList;
        }

    }

}
