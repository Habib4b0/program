/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import com.stpl.app.gcm.common.HelperListUtil;
import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.promotetptocontract.dto.CompanyTypeDdlbDTO;
import com.stpl.app.gcm.promotetptocontract.dto.ContractHolderDTO;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.promotetptocontract.dto.RebatePlanDTO;
import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.util.Constants.DateFormatConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class Converters {

    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Set the Values to respective fields for Company Search
     *
     * @param resultList
     * @return
     * @throws ParseException
     * @throws PortalException
     * @throws SystemException
     */
    public static List<PromoteTpToChDto> searchTPCompany(List resultList) throws ParseException, PortalException, SystemException {
        List<PromoteTpToChDto> promoteTpToChList = new ArrayList<PromoteTpToChDto>();
        PromoteTpToChDto promoteTpToChDto;
        int listSize = resultList.size();
        try {
            for (int i = 0, limit = listSize; i < limit; i++) {
                promoteTpToChDto = new PromoteTpToChDto();
                Object objects[] = (Object[]) resultList.get(i);
                promoteTpToChDto.setCompanySystemId((String.valueOf(objects[0])));
                promoteTpToChDto.setCompanyId((String.valueOf(objects[1])));
                promoteTpToChDto.setCompanyNo((String.valueOf(objects[2])));
                promoteTpToChDto.setCompanyName(String.valueOf(objects[3]));
                promoteTpToChDto.setCompanyType(String.valueOf(objects[4]));
                promoteTpToChDto.setCompanyCategory(convertNullToEmpty(String.valueOf(objects[5])));
                promoteTpToChDto.setTradeClass(convertNullToEmpty(objects[6]));
                promoteTpToChDto.setAddress1(convertNullToEmpty(objects[7]));
                promoteTpToChDto.setAddress2(convertNullToEmpty(objects[8]));
                promoteTpToChDto.setCity(convertNullToEmpty(objects[9]));
                promoteTpToChDto.setState(convertNullToEmpty(objects[10]));
                promoteTpToChDto.setZip(convertNullToEmpty(objects[11]));
                promoteTpToChList.add(promoteTpToChDto);
            }
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
        return promoteTpToChList;
    }

    /**
     * Set the Values to respective fields for Company Search
     *
     * @param resultList
     * @return
     * @throws ParseException
     * @throws PortalException
     * @throws SystemException
     */
    public static List<TradingPartnerDTO> searchCompany(List resultList) throws ParseException, PortalException, SystemException {
        List<TradingPartnerDTO> promoteTpToChDtos = new ArrayList<TradingPartnerDTO>();
        TradingPartnerDTO tpDto;
        int size = resultList.size();
        for (int loop = 0, limit = size; loop < limit; loop++) {
            tpDto = new TradingPartnerDTO();
            Object objects[] = (Object[]) resultList.get(loop);
            tpDto.setCompanySystemId(String.valueOf(objects[0]));
            tpDto.setCompanyId(String.valueOf(objects[1]));
            tpDto.setCompanyNo(String.valueOf(objects[2]));
            tpDto.setCompanyName(String.valueOf(objects[3]));
            tpDto.setCompanyType(objects[4] == null ? StringUtils.EMPTY : String.valueOf(objects[4]));
            tpDto.setCompanyCategory(objects[5] == null ? StringUtils.EMPTY : String.valueOf(objects[5]));
            tpDto.setTradeClass(objects[6] == null ? StringUtils.EMPTY : String.valueOf(objects[6]));
            tpDto.setAddress1(objects[7] == null ? StringUtils.EMPTY : String.valueOf(objects[7]));
            tpDto.setAddress2(objects[8] == null ? StringUtils.EMPTY : String.valueOf(objects[8]));
            tpDto.setCity(objects[9] == null ? StringUtils.EMPTY : String.valueOf(objects[9]));
            tpDto.setState(objects[10] == null ? StringUtils.EMPTY : String.valueOf(objects[10]));
            tpDto.setZip(objects[11] == null ? StringUtils.EMPTY : String.valueOf(objects[11]));
            tpDto.setCompanyStatus(objects[12] == null ? StringUtils.EMPTY : String.valueOf(objects[12]));
            tpDto.setCompanyStartDate(formatDate(convertNullToEmpty(String.valueOf(objects[13]))));
            tpDto.setCompanyEndDate(formatDate(convertNullToEmpty(String.valueOf(objects[14]))));
            tpDto.setTradeClassStartDate(formatDate(convertNullToEmpty(String.valueOf(objects[15]))));
            tpDto.setTradeClassEndDate(formatDate(convertNullToEmpty(String.valueOf(objects[16]))));
            tpDto.setLives(convertNullToEmpty(String.valueOf(objects[17])));
            tpDto.setCompanyGroup(convertNullToEmpty(String.valueOf(objects[18])));
            tpDto.setOrganization(convertNullToEmpty(String.valueOf(objects[19])));
            tpDto.setFinancialSystem(convertNullToEmpty(String.valueOf(objects[20])));
            tpDto.setParentStartDate(formatDate(convertNullToEmpty(String.valueOf(objects[21]))));
            tpDto.setParentEndDate(formatDate(convertNullToEmpty(String.valueOf(objects[22]))));
            tpDto.setRegionCode(convertNullToEmpty(String.valueOf(objects[23])));
            tpDto.setUdc1(convertNullToEmpty(String.valueOf(objects[24])));
            tpDto.setUdc2(convertNullToEmpty(String.valueOf(objects[25])));
            tpDto.setUdc3(convertNullToEmpty(String.valueOf(objects[26])));
            tpDto.setUdc4(convertNullToEmpty(String.valueOf(objects[27])));
            tpDto.setUdc5(convertNullToEmpty(String.valueOf(objects[28])));
            tpDto.setUdc6(convertNullToEmpty(String.valueOf(objects[29])));
            tpDto.setParentNo(convertNullToEmpty(String.valueOf(objects[40])));
            tpDto.setParentName(convertNullToEmpty(String.valueOf(objects[41])));
            if (objects.length > 42) {
                tpDto.setCheck(!String.valueOf(objects[42]).equals(Constants.NULL) ? String.valueOf(objects[42]).equals(Constants.TRUE) ? true : false : false);
            }
            promoteTpToChDtos.add(tpDto);
        }

        return promoteTpToChDtos;
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || Constants.NULL.equals(String.valueOf(value)) || Constants.SELECT_ONE.equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }

    public static String formatDate(String value) throws ParseException {
        String date = StringUtils.EMPTY;
        try {
            SimpleDateFormat parse = new SimpleDateFormat(DateFormatConstants.yyyyMMddhhmmssSSS.getConstant());
            SimpleDateFormat format = new SimpleDateFormat(DateFormatConstants.MMddyyyy.getConstant());
            if (value != null && !StringUtils.EMPTY.equals(value) && !Constants.NULL.equals(value)) {
                date = format.format(parse.parse(value));
            }
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
        return date;
    }

    public static List<CompanyTypeDdlbDTO> setCompanyTypes(List resultList) throws ParseException, PortalException, SystemException {
        List<CompanyTypeDdlbDTO> companyTypeDdlbDTOList = new ArrayList<CompanyTypeDdlbDTO>();
        CompanyTypeDdlbDTO companyTypeDdlbDTO;
        int listSize = resultList.size();
        try {
            for (int i = 0, limit = listSize; i < limit; i++) {
                companyTypeDdlbDTO = new CompanyTypeDdlbDTO();
                Object objects[] = (Object[]) resultList.get(i);
                companyTypeDdlbDTO.setCompanySystemId((Integer.parseInt(String.valueOf(objects[0]))));
                companyTypeDdlbDTO.setCompanyType((String.valueOf(objects[1])));
                companyTypeDdlbDTOList.add(companyTypeDdlbDTO);
            }
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
        return companyTypeDdlbDTOList;
    }

    /**
     * Set Current Contracts Values to DTO
     *
     * @param resultList
     * @return
     * @throws ParseException
     * @throws PortalException
     * @throws SystemException
     */
    public static List<CurrentContractDTO> setCurrentContracts(List resultList) throws ParseException, PortalException, SystemException {
        List<CurrentContractDTO> CurrentContractList = new ArrayList<CurrentContractDTO>();
        CurrentContractDTO currentContractDTO;
        int listSize = resultList.size();
        try {
            for (int i = 0, limit = listSize; i < limit; i++) {
                currentContractDTO = new CurrentContractDTO();
                Object objects[] = (Object[]) resultList.get(i);
                currentContractDTO.setCheckRecord(false);
                currentContractDTO.setContractHolder((String.valueOf(objects[1])));
                currentContractDTO.setContractNo((String.valueOf(objects[2])));
                currentContractDTO.setContractName(String.valueOf(objects[3]));
                currentContractDTO.setMarketType(String.valueOf(objects[4]));
                currentContractDTO.setStartDate(formatDate(String.valueOf(objects[5])));
                currentContractDTO.setEndDate(formatDate(String.valueOf(objects[6])));
                currentContractDTO.setRebateScheduleNo(String.valueOf(objects[7]));
                currentContractDTO.setRebateScheduleName(String.valueOf(objects[8]));
                currentContractDTO.setRarCategory("RAR"); 
                currentContractDTO.setStatus(String.valueOf(objects[9]));
                currentContractDTO.setCompanyStartDate(formatDate(String.valueOf(objects[10])));
                currentContractDTO.setCompanyEndDate((Date) objects[11]);
                CurrentContractList.add(currentContractDTO);
            }
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
        return CurrentContractList;
    }

    /**
     * Contract Holder Search Results
     *
     * @param resultList
     * @return
     */
    public static List<ContractHolderDTO> convertContractHolderList(final List resultList) {
        List<ContractHolderDTO> returnList = new ArrayList<ContractHolderDTO>();
        ContractHolderDTO contractHolderDTO;
        int resultSize = resultList.size();
        try {
            if (resultSize != 0) {
                for (int loop = 0, limit = resultSize; loop < limit; loop++) {
                    contractHolderDTO = new ContractHolderDTO();
                    Object[] objects = (Object[]) resultList.get(loop);
                    contractHolderDTO.setContractHolderId(String.valueOf(objects[0]));
                    contractHolderDTO.setContractHolderNo(String.valueOf(objects[1]));
                    contractHolderDTO.setContractHolderName(String.valueOf(objects[2]));
                    contractHolderDTO.setContractHolderStatus(String.valueOf(objects[3]));
                    contractHolderDTO.setContractHolderType(String.valueOf(objects[4]));
                    returnList.add(contractHolderDTO);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return returnList;
    }

    /**
     * Set Rebate Plan Details to DTO
     *
     * @param resultList
     * @return
     */
    public static List<RebatePlanDTO> convertRebatePlanList(final List resultList) {
        List<RebatePlanDTO> returnList = new ArrayList<RebatePlanDTO>();
        RebatePlanDTO rebatePlanDTO;
        int resultSize = resultList.size();
        try {
            if (resultSize != 0) {
                for (int loop = 0, limit = resultSize; loop < limit; loop++) {
                    rebatePlanDTO = new RebatePlanDTO();
                    Object[] objects = (Object[]) resultList.get(loop);
                    rebatePlanDTO.setRebatePlanId(String.valueOf(objects[1]));
                    rebatePlanDTO.setRebatePlanNo(String.valueOf(objects[2]));
                    rebatePlanDTO.setRebatePlanName(convertNullToEmpty(String.valueOf(objects[3])));
                    rebatePlanDTO.setRebatePlanStatus(convertNullToEmpty(String.valueOf(objects[4])));
                    rebatePlanDTO.setRebatePlanType(convertNullToEmpty(String.valueOf(objects[5])));
                    returnList.add(rebatePlanDTO);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return returnList;
    }
}
