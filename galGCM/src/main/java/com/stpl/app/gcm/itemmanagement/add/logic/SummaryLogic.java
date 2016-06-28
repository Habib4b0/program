
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.add.logic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.ContractDashboardDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.SummaryDTO;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractFilter;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.util.CommonUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed.hameed
 */
public class SummaryLogic {

    /**
     * Get sales count
     *
     * @param parentId
     * @param projSelDTO
     * @return integer
     */
    public int getConfigureCount(Object parentId, SelectionDTO projSelDTO, ContractDashboardDTO idDTO) {
        if (parentId instanceof ContractDashboardDTO) {
            ContractDashboardDTO dto = (ContractDashboardDTO) parentId;
            projSelDTO.setLevelNo(dto.getLevelNo() + 1);
        } else {
            projSelDTO.setLevelNo(1);
        }
        List input = new ArrayList();
        setMasterId(projSelDTO, idDTO, input);
        List<Object[]> list = ItemQueries.getItemData(input, projSelDTO.getCountQueryName(), null);
        Object obj = list.get(0);
        return obj == null ? 0 : (Integer) obj;
    }

    /**
     * Load data
     *
     * @param parentId
     * @param projSelDTO
     * @return results list
     */
    public List<ContractDashboardDTO> getConfigureLoadData(Object parentId, SelectionDTO projSelDTO, ContractDashboardDTO idDTO) {
        List<ContractDashboardDTO> salesList;
        if (parentId instanceof ContractDashboardDTO) {
            ContractDashboardDTO dto = (ContractDashboardDTO) parentId;
            projSelDTO.setLevelNo(dto.getLevelNo() + 1);
        } else {
            projSelDTO.setLevelNo(1);
        }
        List input = new ArrayList();
        setMasterId(projSelDTO, idDTO, input);
        salesList = configureDashBoardLevel(projSelDTO, input);
        return salesList;
    }

    /**
     * Get configure level
     *
     * @param projSelDTO
     * @return
     */
    public List<ContractDashboardDTO> configureDashBoardLevel(SelectionDTO projSelDTO, List input) {
        List<ContractDashboardDTO> resultList = new ArrayList<ContractDashboardDTO>();
        List<Object[]> list = ItemQueries.getItemData(input, projSelDTO.getDataQueryName(), null);
        Set<String> levelName = new HashSet<String>();
        ContractDashboardDTO dto = new ContractDashboardDTO();
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            if (i == 0) {
                dto = setLevelValue(obj);
                levelName.add(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
            } else if (levelName.add(obj[0] == null ? StringUtils.EMPTY : obj[0].toString())) {
                resultList.add(dto);
                dto = setLevelValue(obj);
            }
        }
        resultList.add(dto);
        return resultList;
    }

    private static ContractDashboardDTO setLevelValue(final Object[] obj) {
        ContractDashboardDTO dto = new ContractDashboardDTO();
        dto.setLevelValue(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
        dto.setComponent(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
        dto.setLevelNo(obj[1] == null ? 0 : (Integer) obj[1]);
        dto.setId(obj[2] == null ? StringUtils.EMPTY : obj[2].toString());
        dto.setNumber(obj[3] == null ? StringUtils.EMPTY : obj[3].toString());
        dto.setName(obj[4] == null ? StringUtils.EMPTY : obj[4].toString());
        dto.setMasterSid(obj[5] == null ? StringUtils.EMPTY : obj[5].toString());
        dto.setContractSid(obj[6] == null ? StringUtils.EMPTY : obj[6].toString());
        dto.setParent(dto.getLevelNo() == 5 ? 0 : 1);
        return dto;
    }

    private static List setMasterId(SelectionDTO selection, ContractDashboardDTO dto, List input) {
        if (selection.getLevelNo() == 1) {
            selection.setCountQueryName("SU-CH Count");
            selection.setDataQueryName("SU-CH");
            input.add(dto.getContractSid());
        } else if (selection.getLevelNo() == 2) {
            selection.setCountQueryName("SU-CFP Count");
            selection.setDataQueryName("SU-CFP");
            input.add(dto.getCfpSid());
            input.add(dto.getContractSid());
        } else if (selection.getLevelNo() == 3) {
            selection.setCountQueryName("SU-IFP Count");
            selection.setDataQueryName("SU-IFP");
            input.add(dto.getIfpSid());
            input.add(dto.getCfpSid());
            input.add(dto.getContractSid());
        } else if (selection.getLevelNo() == 4) {
            selection.setCountQueryName("SU-PS Count");
            selection.setDataQueryName("SU-PS");
            input.add(dto.getPsSid());
            input.add(dto.getIfpSid());
            input.add(dto.getCfpSid());
            input.add(dto.getContractSid());
        } else if (selection.getLevelNo() == 5) {
            selection.setCountQueryName("SU-RS Count");
            selection.setDataQueryName("SU-RS");
            input.add(dto.getRsSid());
            input.add(dto.getPsSid());
            input.add(dto.getIfpSid());
            input.add(dto.getCfpSid());
            input.add(dto.getContractSid());
        }
        return input;
    }

    public int getContractCount(final SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            input.add(selection.getOperation());
        } else {
            input.add(ConstantsUtil.SUMMARY);
        }
        input.add(AbstractFilter.getInstance().summaryfilterQueryGenerator(selection.getFilters()));
        List<Object[]> list = ItemQueries.getItemData(input, "SU-Contracts Count", null);
        Object obj = list.get(0);
        return obj == null ? 0 : (Integer) obj;
    }

    public List<SummaryDTO> getContractResults(final SelectionDTO selection, int start, int offset) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            input.add(selection.getOperation());
        } else {
            input.add(ConstantsUtil.SUMMARY);
        }
        input.add(AbstractFilter.getInstance().summaryfilterQueryGenerator(selection.getFilters()));
        input.add(start);
        input.add(offset);
        List<Object[]> list = ItemQueries.getItemData(input, "SU-Contracts", null);
        List<SummaryDTO> finalResult = new ArrayList<SummaryDTO>();
        for (Object[] str : list) {
            SummaryDTO dto = new SummaryDTO();
            dto.setContractHolder(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setContractNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setContractName(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setMarketType(str[3] == null ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setStartDate(str[4] == null ? null : (Date) (str[4]));
            dto.setEndDate(str[5] == null ? null : (Date) (str[5]));
            dto.setCfp(str[6] == null ? StringUtils.EMPTY : String.valueOf(str[6]));
            dto.setIfp(str[7] == null ? StringUtils.EMPTY : String.valueOf(str[7]));
            dto.setPs(str[8] == null ? StringUtils.EMPTY : String.valueOf(str[8]));
            dto.setRs(str[9] == null ? StringUtils.EMPTY : String.valueOf(str[9]));
            dto.setRarCategory(str[10] == null ? StringUtils.EMPTY : String.valueOf(str[10]));
            dto.setStatus(str[11] == null ? StringUtils.EMPTY : String.valueOf(str[11]));
            dto.setItemstartdate(str[12] == null ? StringUtils.EMPTY : CommonUtils.commonDate.format(str[12]));
            dto.setItemenddate(str[13] == null ? StringUtils.EMPTY : CommonUtils.commonDate.format(str[13]));
            dto.setContractSid(str[14] == null ? StringUtils.EMPTY : String.valueOf(str[14]));
            dto.setCfpSid(str[15] == null ? StringUtils.EMPTY : String.valueOf(str[15]));
            dto.setIfpSid(str[16] == null ? StringUtils.EMPTY : String.valueOf(str[16]));
            dto.setPsSid(str[17] == null ? StringUtils.EMPTY : String.valueOf(str[17]));
            dto.setRsSid(str[18] == null ? StringUtils.EMPTY : String.valueOf(str[18]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    public void deleteContractTree(SelectionDTO selection, Object componentInfoDTO) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(componentInfoDTO);
        Boolean isUpdated = ItemQueries.itemUpdate(input, "SU-Delete Dashboard Tree");
    }

    public void deleteContract(SelectionDTO selection, Object componentInfoDTO) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(componentInfoDTO);
        Boolean isUpdated = ItemQueries.itemUpdate(input, "SU-Delete Dashboard Tree");
    }
}
