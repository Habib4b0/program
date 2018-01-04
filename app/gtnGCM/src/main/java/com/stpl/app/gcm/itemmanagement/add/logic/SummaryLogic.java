
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
import com.stpl.ifs.ui.util.NumericConstants;
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
        List<ContractDashboardDTO> resultList = new ArrayList<>();
        List<Object[]> list = ItemQueries.getItemData(input, projSelDTO.getDataQueryName(), null);
        Set<String> levelName = new HashSet<>();
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
        dto.setId(obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : obj[NumericConstants.TWO].toString());
        dto.setNumber(obj[NumericConstants.THREE] == null ? StringUtils.EMPTY : obj[NumericConstants.THREE].toString());
        dto.setName(obj[NumericConstants.FOUR] == null ? StringUtils.EMPTY : obj[NumericConstants.FOUR].toString());
        dto.setMasterSid(obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : obj[NumericConstants.FIVE].toString());
        dto.setContractSid(obj[NumericConstants.SIX] == null ? StringUtils.EMPTY : obj[NumericConstants.SIX].toString());
        dto.setParent(dto.getLevelNo() == NumericConstants.FIVE ? 0 : 1);
        return dto;
    }

    private static List setMasterId(SelectionDTO selection, ContractDashboardDTO dto, List input) {
        if (selection.getLevelNo() == 1) {
            selection.setCountQueryName("SU-CH Count");
            selection.setDataQueryName("SU-CH");
            input.add(dto.getContractSid());
        } else if (selection.getLevelNo() == NumericConstants.TWO) {
            selection.setCountQueryName("SU-CFP Count");
            selection.setDataQueryName("SU-CFP");
            input.add(dto.getCfpSid());
            input.add(dto.getContractSid());
        } else if (selection.getLevelNo() == NumericConstants.THREE) {
            selection.setCountQueryName("SU-IFP Count");
            selection.setDataQueryName("SU-IFP");
            input.add(dto.getIfpSid());
            input.add(dto.getCfpSid());
            input.add(dto.getContractSid());
        } else if (selection.getLevelNo() == NumericConstants.FOUR) {
            selection.setCountQueryName("SU-PS Count");
            selection.setDataQueryName("SU-PS");
            input.add(dto.getPsSid());
            input.add(dto.getIfpSid());
            input.add(dto.getCfpSid());
            input.add(dto.getContractSid());
        } else if (selection.getLevelNo() == NumericConstants.FIVE) {
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
        List<SummaryDTO> finalResult = new ArrayList<>();
        for (Object[] str : list) {
            SummaryDTO dto = new SummaryDTO();
            dto.setContractHolder(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setContractNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setContractName(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setMarketType(str[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setStartDate(str[NumericConstants.FOUR] == null ? null : (Date) (str[NumericConstants.FOUR]));
            dto.setEndDate(str[NumericConstants.FIVE] == null ? null : (Date) (str[NumericConstants.FIVE]));
            dto.setCfp(str[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIX]));
            dto.setIfp(str[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
            dto.setPs(str[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
            dto.setRs(str[NumericConstants.NINE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.NINE]));
            dto.setRarCategory(str[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TEN]));
            dto.setStatus(str[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.ELEVEN]));
            dto.setItemstartdate(str[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : CommonUtils.commonDate.format(str[NumericConstants.TWELVE]));
            dto.setItemenddate(str[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : CommonUtils.commonDate.format(str[NumericConstants.THIRTEEN]));
            dto.setContractSid(str[NumericConstants.FOURTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOURTEEN]));
            dto.setCfpSid(str[NumericConstants.FIFTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIFTEEN]));
            dto.setIfpSid(str[NumericConstants.SIXTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIXTEEN]));
            dto.setPsSid(str[NumericConstants.SEVENTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVENTEEN]));
            dto.setRsSid(str[NumericConstants.EIGHTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHTEEN]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    public void deleteContractTree(SelectionDTO selection, Object componentInfoDTO) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(componentInfoDTO);
        ItemQueries.itemUpdate(input, "SU-Delete Dashboard Tree");
    }

    public void deleteContract(SelectionDTO selection, Object componentInfoDTO) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(componentInfoDTO);
        ItemQueries.itemUpdate(input, "SU-Delete Dashboard Tree");
    }
}
