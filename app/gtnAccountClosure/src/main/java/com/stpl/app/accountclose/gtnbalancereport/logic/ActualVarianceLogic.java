/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.logic;

import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.gtnbalancereport.dto.ActualVarianceDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Converters;
import com.stpl.app.accountclose.utils.QueryUtils;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanukumar
 */
public class ActualVarianceLogic {

    BaseRateDAO dao = new BaseRateDAOImpl();

    /**
     *
     * @param dto
     * @param filters
     * @param session
     * @return
     * @throws Exception
     */
    public int getCount(BalanceReportDTO dto, Set<Container.Filter> filters, SessionDTO session) throws Exception {
        List results = new ArrayList<Object[]>();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(session.getAcctCloserMasterId()));
        inputMap.put("?USER_ID?", session.getUserId());
        inputMap.put("?SESSION_ID?", session.getSessionId());
        inputMap.put("?CMSID?", "%");
        inputMap.put("?CSID?", "%");
        inputMap.put("?THID?", "%");
        inputMap.put("?BMSID?", "%");
        inputMap.put("?ISID?", "%");

        if (StringUtils.isNotBlank(dto.getComapnySid())) {
            inputMap.put("?CMSID?", dto.getComapnySid());
        }
        if (StringUtils.isNotBlank(dto.getContractSid())) {
            inputMap.put("?CSID?", dto.getContractSid());
        }
        if (StringUtils.isNotBlank(dto.getTherapeuticSid())) {
            inputMap.put("?THID?", dto.getTherapeuticSid());
        }
        if (StringUtils.isNotBlank(dto.getBrandSid())) {
            inputMap.put("?BMSID?", dto.getBrandSid());
        }
        if (StringUtils.isNotBlank(dto.getItemSid())) {
            inputMap.put("?ISID?", dto.getItemSid());
        }
        String query = CommonUtil.getQuery(inputMap, "actualVarianceCountQuery");
        results = (List) QueryUtils.executeSelectQuery(inputMap, query);

        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    /**
     *
     * @param dto
     * @param filters
     * @param session
     * @return
     * @throws Exception
     */
    public List<ActualVarianceDTO> getResults(BalanceReportDTO dto, Set<Container.Filter> filters, SessionDTO session) throws Exception {
        List results = new ArrayList<Object[]>();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(session.getAcctCloserMasterId()));
        inputMap.put("?USER_ID?", session.getUserId());
        inputMap.put("?SESSION_ID?", session.getSessionId());
        inputMap.put("?CMSID?", "%");
        inputMap.put("?CSID?", "%");
        inputMap.put("?THID?", "%");
        inputMap.put("?BMSID?", "%");
        inputMap.put("?ISID?", "%");

        if (StringUtils.isNotBlank(dto.getComapnySid())) {
            inputMap.put("?CMSID?", dto.getComapnySid());
        }
        if (StringUtils.isNotBlank(dto.getContractSid())) {
            inputMap.put("?CSID?", dto.getContractSid());
        }
        if (StringUtils.isNotBlank(dto.getTherapeuticSid())) {
            inputMap.put("?THID?", dto.getTherapeuticSid());
        }
        if (StringUtils.isNotBlank(dto.getBrandSid())) {
            inputMap.put("?BMSID?", dto.getBrandSid());
        }
        if (StringUtils.isNotBlank(dto.getItemSid())) {
            inputMap.put("?ISID?", dto.getItemSid());
        }
        String query = CommonUtil.getQuery(inputMap, "actualVarianceResultQuery");
        results = (List)QueryUtils.executeSelectQuery(inputMap, query);
        return getCustomisedDto(results, StringUtils.EMPTY);
    }

    /**
     *
     * @param results
     * @param string
     * @return
     */
    private List<ActualVarianceDTO> getCustomisedDto(List results, String string) {
        List<ActualVarianceDTO> finalList = new ArrayList<ActualVarianceDTO>();
        if (!results.isEmpty()) {
            int size = results.size();
            for (int i = 0; i < size; i++) {
                final Object[] obj = (Object[]) results.get(i);
                ActualVarianceDTO varianceDTO = new ActualVarianceDTO();
                varianceDTO.setNdcNo(Converters.convertNullToEmpty(obj[0]));
                varianceDTO.setNdcName(Converters.convertNullToEmpty(obj[1]));
                varianceDTO.setNdcDescription(Converters.convertNullToEmpty(obj[2]));
                varianceDTO.setBrand(Converters.convertNullToEmpty(obj[3]));
                varianceDTO.setContractNo(Converters.convertNullToEmpty(obj[4]));
                varianceDTO.setContractName(Converters.convertNullToEmpty(obj[5]));
                varianceDTO.setCustomerNo(Converters.convertNullToEmpty(obj[6]));
                varianceDTO.setCustomerName(Converters.convertNullToEmpty(obj[7]));
                finalList.add(varianceDTO);
            }
        }
        return finalList;
    }
}
