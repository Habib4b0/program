/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.logic;

import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.transfercontract.dto.CFPComponentDetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.ComponentDetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.PSComponentDetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.RSComponentDetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.TransferFromDTO;
import com.stpl.app.gcm.transfercontract.dto.TransferToDTO;
import com.stpl.app.gcm.transfercontract.util.CommonUtil;
import com.stpl.app.gcm.transfercontract.util.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author harlin
 */
public class TransferSelectionLogic {

    CommonDao DAO = CommonImpl.getInstance();

    public List<TransferFromDTO> getTransferFromDetails(TransferFromDTO parent, Map<String, Set<String>> resultList) {
        Map<String, String> inputMap = new HashMap<String, String>();
        String query;
        String category;
        Integer level = parent == null ? 0 : parent.getLevel() + 1;
        String systemIds;
        List<TransferFromDTO> retList;
        if (level == 0) {
            query = "tc.getPlaceHolderContract";
            category = Constant.CONTRACT_CATEGORY;
            systemIds = CommonUtil.getSystemIds(resultList.get("contract"));
        } else if (level == 1) {
            query = "tc.getPlaceHolderCFP";
            category = Constant.CFP_CATEGORY;
            inputMap.put("?CMSID?", parent.getContractSid());
            systemIds = CommonUtil.getSystemIds(resultList.get("cfp"));
        } else if (level == NumericConstants.TWO) {
            query = "tc.getPlaceHolderIFP";
            category = Constant.IFP_CATEGORY;
            inputMap.put("?CMSID?", parent.getContractSid());
            systemIds = CommonUtil.getSystemIds(resultList.get("ifp"));
        } else if (level == NumericConstants.THREE) {
            query = "tc.getPlaceHolderPS";
            category = Constant.PS_CATEGORY;
            inputMap.put("?CMSID?", parent.getContractSid());
            systemIds = CommonUtil.getSystemIds(resultList.get("ps"));
        } else {
            query = "tc.getPlaceHolderRS";
            category = Constant.RS_CATEGORY;
            systemIds = CommonUtil.getSystemIds(resultList.get("rs"));
            inputMap.put("?CMSID?", parent.getContractSid());
        }

        if (StringUtils.isBlank(systemIds)) {
            retList = new ArrayList<TransferFromDTO>();
        } else {
            inputMap.put("?SIDS?", systemIds);
            retList = configureFromDetails((List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, query)), category, level);
        }
        return retList;
    }

    private List<TransferFromDTO> configureFromDetails(List<Object[]> resultList, String Category, Integer level) {
        List<TransferFromDTO> retList = new ArrayList<TransferFromDTO>();
        for (Object[] temp : resultList) {
            TransferFromDTO tempDTO = new TransferFromDTO();
            tempDTO.setLevel(level);
            tempDTO.setCategory(Category);
            tempDTO.setId(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDTO.setNumber(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDTO.setName(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            tempDTO.setContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE])));
            if (level == 1) {
                tempDTO.setCfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
            } else if (level == NumericConstants.TWO) {
                tempDTO.setCfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
                tempDTO.setIfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE])));
            } else if (level == NumericConstants.THREE) {
                tempDTO.setCfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
                tempDTO.setIfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE])));
                tempDTO.setPsContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX])));
            } else if (level == NumericConstants.FOUR) {
                tempDTO.setCfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
                tempDTO.setIfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE])));
                tempDTO.setPsContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX])));
                tempDTO.setRsContractSid(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SEVEN])));
            }
            retList.add(tempDTO);

        }
        return retList;
    }

    public List<CFPComponentDetailsDTO> getFromCfpCD(Object parent) {
        List<CFPComponentDetailsDTO> retList = new ArrayList<CFPComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof TransferFromDTO) {
            inputMap.put("?SID?", ((TransferFromDTO) parent).getCfpContractSid());
        } else if (parent instanceof TransferToDTO) {
            inputMap.put("?SID?", ((TransferToDTO) parent).getCfpContractSid());
        }

        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "tc.cfpFromCD"));
        for (Object[] temp : resList) {
            CFPComponentDetailsDTO tempDto = new CFPComponentDetailsDTO();
            int j = -1;
            tempDto.setTradingPartnerNo(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setTradingPartnerName(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setStartDate((Date) temp[++j]);
            tempDto.setEndDate((Date) temp[++j]);
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setTradeClass(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setAttachedDate((Date) temp[++j]);
            retList.add(tempDto);
        }

        return retList;
    }

    public List<ComponentDetailsDTO> getFromIfpCD(Object parent) {
        List<ComponentDetailsDTO> retList = new ArrayList<ComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof TransferFromDTO) {
            inputMap.put("?SID?", ((TransferFromDTO) parent).getIfpContractSid());
        } else if (parent instanceof TransferToDTO) {
            inputMap.put("?SID?", ((TransferToDTO) parent).getIfpContractSid());
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "tc.ifpFromCD"));
        for (Object[] temp : resList) {
            ComponentDetailsDTO tempDto = new ComponentDetailsDTO();
            int j = -1;
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setStartDate((Date) temp[++j]);
            tempDto.setEndDate((Date) temp[++j]);
            tempDto.setAttachedDate((Date) temp[++j]);
            retList.add(tempDto);
        }
        return retList;
    }

    public List<PSComponentDetailsDTO> getFromPsCD(Object parent) {
        List<PSComponentDetailsDTO> retList = new ArrayList<PSComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof TransferFromDTO) {
            inputMap.put("?SID?", ((TransferFromDTO) parent).getPsContractSid());
        } else if (parent instanceof TransferToDTO) {
            inputMap.put("?SID?", ((TransferToDTO) parent).getPsContractSid());
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "tc.psFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            int j = -1;
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setStartDate((Date) temp[++j]);
            tempDto.setEndDate((Date) temp[++j]);
            tempDto.setPriceType(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setPriceProtectionStartDate((Date) temp[++j]);
            tempDto.setPriceProtectionEndDate((Date) temp[++j]);
            tempDto.setPriceProtectionPriceType(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setPriceToleranceInterval(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setPriceToleranceFrequency(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setAttachedDate((Date) temp[++j]);
            retList.add(tempDto);
        }
        return retList;
    }

    public List<RSComponentDetailsDTO> getFromRsCD(Object parent) {
        List<RSComponentDetailsDTO> retList = new ArrayList<RSComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof TransferFromDTO) {
            inputMap.put("?SID?", ((TransferFromDTO) parent).getRsContractSid());
        } else if (parent instanceof TransferToDTO) {
            inputMap.put("?SID?", ((TransferToDTO) parent).getRsContractSid());
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "tc.rsFromCD"));
        for (Object[] temp : resList) {
            RSComponentDetailsDTO tempDto = new RSComponentDetailsDTO();
            int j = -1;
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setStartDate((Date) temp[++j]);
            tempDto.setEndDate((Date) temp[++j]);
            tempDto.setRebatePlanID(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setRebatePlanName(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setRebateAmount(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setBundleNo(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDto.setAttachedDate((Date) temp[++j]);
            retList.add(tempDto);
        }
        return retList;
    }

    public List<TransferToDTO> getTransferToDetails(final TransferToDTO parent, final String searchComponent, final String searchField, final String searchValue) {
        Map<String, String> inputMap = new HashMap<String, String>();
        String category;
        Integer level = parent == null ? 0 : parent.getLevel() + 1;
        List<TransferToDTO> retList;
        inputMap.put("?CT_NO?", "%");
        inputMap.put("?CT_NAME?", "%");
        inputMap.put("?CT_ID?", "%");
        inputMap.put("?CT_SID?", "%");
        inputMap.put("?CFP_NO?", "%");
        inputMap.put("?CFP_NAME?", "%");
        inputMap.put("?CFP_ID?", "%");
        inputMap.put("?CFP_SID?", "%");
        inputMap.put("?IFP_NO?", "%");
        inputMap.put("?IFP_NAME?", "%");
        inputMap.put("?IFP_ID?", "%");
        inputMap.put("?IFP_SID?", "%");
        inputMap.put("?PS_NO?", "%");
        inputMap.put("?PS_NAME?", "%");
        inputMap.put("?PS_ID?", "%");
        inputMap.put("?PS_SID?", "%");
        inputMap.put("?RS_NO?", "%");
        inputMap.put("?RS_NAME?", "%");
        inputMap.put("?RS_ID?", "%");
        inputMap.put("?RS_SID?", "%");
        if (level == 0) {
            inputMap.put("?SELECTION?", CommonUtil.getQuery(null, "tc.getContractSelection"));
            category = Constant.CONTRACT_CATEGORY;
        } else if (level == 1) {
            category = Constant.CFP_CATEGORY;
            inputMap.put("?SELECTION?", CommonUtil.getQuery(null, "tc.getCfpSelection"));
            inputMap.put("?CT_SID?", parent.getContractSid());
        } else if (level == NumericConstants.TWO) {
            category = Constant.IFP_CATEGORY;
            inputMap.put("?SELECTION?", CommonUtil.getQuery(null, "tc.getIfpSelection"));
            inputMap.put("?CT_SID?", parent.getContractSid());
            inputMap.put("?CFP_SID?", parent.getCfpContractSid());
        } else if (level == NumericConstants.THREE) {
            category = Constant.PS_CATEGORY;
            inputMap.put("?SELECTION?", CommonUtil.getQuery(null, "tc.getPsSelection"));
            inputMap.put("?CT_SID?", parent.getContractSid());
            inputMap.put("?CFP_SID?", parent.getCfpContractSid());
            inputMap.put("?IFP_SID?", parent.getIfpContractSid());
        } else {
            category = Constant.RS_CATEGORY;
            inputMap.put("?SELECTION?", CommonUtil.getQuery(null, "tc.getRsSelection"));
            inputMap.put("?CT_SID?", parent.getContractSid());
            inputMap.put("?CFP_SID?", parent.getCfpContractSid());
            inputMap.put("?IFP_SID?", parent.getIfpContractSid());
            inputMap.put("?RS_SID?", parent.getPsContractSid());
        }
        getToInput(inputMap, searchComponent, searchField, searchValue);
        retList = configureToDetails((List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "tc.searchActualContract")), category, level);
        return retList;
    }

    private List<TransferToDTO> configureToDetails(List<Object[]> resultList, String Category, Integer level) {
        List<TransferToDTO> retList = new ArrayList<TransferToDTO>();
        for (Object[] temp : resultList) {
            TransferToDTO tempDTO = new TransferToDTO();
            int j = -1;
            tempDTO.setLevel(level);
            tempDTO.setCategory(Category);
            tempDTO.setContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            if (level == 1) {
                tempDTO.setCfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            } else if (level == NumericConstants.TWO) {
                tempDTO.setCfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
                tempDTO.setIfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            } else if (level == NumericConstants.THREE) {
                tempDTO.setCfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
                tempDTO.setIfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
                tempDTO.setPsContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            } else if (level == NumericConstants.FOUR) {
                tempDTO.setCfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
                tempDTO.setIfpContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
                tempDTO.setPsContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
                tempDTO.setRsContractSid(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            }
            tempDTO.setId(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDTO.setNumber(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            tempDTO.setName(CommonUtil.getPureValue(String.valueOf(temp[++j])));
            retList.add(tempDTO);

        }
        return retList;
    }

    private void getToInput(final Map<String, String> inputMap, final String searchComponent, final String searchField, final String searchValue) {
        if (Constant.CONTRACT_CATEGORY.equals(searchComponent)) {
            if (Constant.CONTRACT_SEARCH[0].equals(searchField)) {
                inputMap.put("?CT_NO?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.CONTRACT_SEARCH[1].equals(searchField)) {
                inputMap.put("?CT_NAME?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.CONTRACT_SEARCH[NumericConstants.TWO].equals(searchField)) {
                inputMap.put("?CT_ID?", CommonUtil.astToPerConverter(searchValue));
            }
        } else if (Constant.CFP_CATEGORY.equals(searchComponent)) {
            if (Constant.CFP_SEARCH[0].equals(searchField)) {
                inputMap.put("?CFP_NO?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.CFP_SEARCH[1].equals(searchField)) {
                inputMap.put("?CFP_NAME?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.CFP_SEARCH[NumericConstants.TWO].equals(searchField)) {
                inputMap.put("?CFP_ID?", CommonUtil.astToPerConverter(searchValue));
            }
        } else if (Constant.IFP_CATEGORY.equals(searchComponent)) {
            if (Constant.IFP_SEARCH[0].equals(searchField)) {
                inputMap.put("?IFP_NO?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.IFP_SEARCH[1].equals(searchField)) {
                inputMap.put("?IFP_NAME?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.IFP_SEARCH[NumericConstants.TWO].equals(searchField)) {
                inputMap.put("?IFP_ID?", CommonUtil.astToPerConverter(searchValue));
            }
        } else if (Constant.PS_CATEGORY.equals(searchComponent)) {
            if (Constant.PS_SEARCH[0].equals(searchField)) {
                inputMap.put("?PS_NO?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.PS_SEARCH[1].equals(searchField)) {
                inputMap.put("?PS_NAME?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.PS_SEARCH[NumericConstants.TWO].equals(searchField)) {
                inputMap.put("?PS_ID?", CommonUtil.astToPerConverter(searchValue));
            }
        } else if (Constant.RS_CATEGORY.equals(searchComponent)) {
            if (Constant.RS_SEARCH[0].equals(searchField)) {
                inputMap.put("?RS_NO?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.RS_SEARCH[1].equals(searchField)) {
                inputMap.put("?RS_NAME?", CommonUtil.astToPerConverter(searchValue));
            } else if (Constant.RS_SEARCH[NumericConstants.TWO].equals(searchField)) {
                inputMap.put("?RS_ID?", CommonUtil.astToPerConverter(searchValue));
            }
        }
    }
}
