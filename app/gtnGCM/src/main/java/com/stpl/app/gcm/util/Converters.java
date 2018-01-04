/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.promotetptocontract.dto.CompanyTypeDdlbDTO;
import com.stpl.app.gcm.promotetptocontract.dto.ContractHolderDTO;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.promotetptocontract.dto.RebatePlanDTO;
import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.util.Constants.DateFormatConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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

    /**
     * Set the Values to respective fields for Company Search
     *
     * @param resultList
     * @return
     */
    public static List<PromoteTpToChDto> searchTPCompany(List resultList) {
        List<PromoteTpToChDto> promoteTpToChList = new ArrayList<>();
        PromoteTpToChDto promoteTpToChDto;
        int listSize = resultList.size();
        try {
            for (int i = 0, limit = listSize; i < limit; i++) {
                promoteTpToChDto = new PromoteTpToChDto();
                Object objects[] = (Object[]) resultList.get(i);
                promoteTpToChDto.setCompanySystemId(String.valueOf(objects[0]));
                promoteTpToChDto.setCompanyId(String.valueOf(objects[1]));
                promoteTpToChDto.setCompanyNo(String.valueOf(objects[NumericConstants.TWO]));
                promoteTpToChDto.setCompanyName(String.valueOf(objects[NumericConstants.THREE]));
                promoteTpToChDto.setCompanyType(String.valueOf(objects[NumericConstants.FOUR]));
                promoteTpToChDto.setCompanyCategory(convertNullToEmpty(String.valueOf(objects[NumericConstants.FIVE])));
                promoteTpToChDto.setTradeClass(convertNullToEmpty(objects[NumericConstants.SIX]));
                promoteTpToChDto.setAddress1(convertNullToEmpty(objects[NumericConstants.SEVEN]));
                promoteTpToChDto.setAddress2(convertNullToEmpty(objects[NumericConstants.EIGHT]));
                promoteTpToChDto.setCity(convertNullToEmpty(objects[NumericConstants.NINE]));
                promoteTpToChDto.setState(convertNullToEmpty(objects[NumericConstants.TEN]));
                promoteTpToChDto.setZip(convertNullToEmpty(objects[NumericConstants.ELEVEN]));
                promoteTpToChList.add(promoteTpToChDto);
            }
        } catch (Exception ex) {
           LOGGER.error("",ex);
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
    public static List<TradingPartnerDTO> searchCompany(List resultList) throws ParseException {
        List<TradingPartnerDTO> promoteTpToChDtos = new ArrayList<>();
        TradingPartnerDTO tpDto;
        int size = resultList.size();
        for (int loop = 0, limit = size; loop < limit; loop++) {
            tpDto = new TradingPartnerDTO();
            Object objects[] = (Object[]) resultList.get(loop);
            tpDto.setCompanySystemId(String.valueOf(objects[0]));
            tpDto.setCompanyId(String.valueOf(objects[1]));
            tpDto.setCompanyNo(String.valueOf(objects[NumericConstants.TWO]));
            tpDto.setCompanyName(String.valueOf(objects[NumericConstants.THREE]));
            tpDto.setCompanyType(objects[NumericConstants.FOUR] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.FOUR]));
            tpDto.setCompanyCategory(objects[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.FIVE]));
            tpDto.setTradeClass(objects[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.SIX]));
            tpDto.setAddress1(objects[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.SEVEN]));
            tpDto.setAddress2(objects[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.EIGHT]));
            tpDto.setCity(objects[NumericConstants.NINE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.NINE]));
            tpDto.setState(objects[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TEN]));
            tpDto.setZip(objects[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.ELEVEN]));
            tpDto.setCompanyStatus(objects[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWELVE]));
            tpDto.setCompanyStartDate(formatDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.THIRTEEN]))));
            tpDto.setCompanyEndDate(formatDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.FOURTEEN]))));
            tpDto.setTradeClassStartDate(formatDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.FIFTEEN]))));
            tpDto.setTradeClassEndDate(formatDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.SIXTEEN]))));
            tpDto.setLives(convertNullToEmpty(String.valueOf(objects[NumericConstants.SEVENTEEN])));
            tpDto.setCompanyGroup(convertNullToEmpty(String.valueOf(objects[NumericConstants.EIGHTEEN])));
            tpDto.setOrganization(convertNullToEmpty(String.valueOf(objects[NumericConstants.NINETEEN])));
            tpDto.setFinancialSystem(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY])));
            tpDto.setParentStartDate(formatDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_ONE]))));
            tpDto.setParentEndDate(formatDate(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_TWO]))));
            tpDto.setRegionCode(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_THREE])));
            tpDto.setUdc1(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_FOUR])));
            tpDto.setUdc2(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_FIVE])));
            tpDto.setUdc3(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_SIX])));
            tpDto.setUdc4(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_SEVEN])));
            tpDto.setUdc5(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_EIGHT])));
            tpDto.setUdc6(convertNullToEmpty(String.valueOf(objects[NumericConstants.TWENTY_NINE])));
            tpDto.setParentNo(convertNullToEmpty(String.valueOf(objects[NumericConstants.FORTY])));
            tpDto.setParentName(convertNullToEmpty(String.valueOf(objects[NumericConstants.FORTY_ONE])));
            if (objects.length > NumericConstants.FORTY_TWO) {
                tpDto.setCheck(!String.valueOf(objects[NumericConstants.FORTY_TWO]).equals(Constants.NULL) ? String.valueOf(objects[NumericConstants.FORTY_TWO]).equals(Constants.TRUE) ? true : false : false);
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
            SimpleDateFormat parse = new SimpleDateFormat(DateFormatConstants.YYYYMMDDHHMMSSSSS.getConstant());
            SimpleDateFormat format = new SimpleDateFormat(DateFormatConstants.MMDDYYYY.getConstant());
            if (value != null && !StringUtils.EMPTY.equals(value) && !Constants.NULL.equals(value)) {
                date = format.format(parse.parse(value));
            }
        } catch (Exception ex) {
           LOGGER.error("",ex);
        }
        return date;
    }

    public static List<CompanyTypeDdlbDTO> setCompanyTypes(List resultList) {
        List<CompanyTypeDdlbDTO> companyTypeDdlbDTOList = new ArrayList<>();
        CompanyTypeDdlbDTO companyTypeDdlbDTO;
        int listSize = resultList.size();
        try {
            for (int i = 0, limit = listSize; i < limit; i++) {
                companyTypeDdlbDTO = new CompanyTypeDdlbDTO();
                Object objects[] = (Object[]) resultList.get(i);
                companyTypeDdlbDTO.setCompanySystemId(Integer.parseInt(String.valueOf(objects[0])));
                companyTypeDdlbDTO.setCompanyType(String.valueOf(objects[1]));
                companyTypeDdlbDTOList.add(companyTypeDdlbDTO);
            }
        } catch (Exception ex) {
           LOGGER.error("",ex);
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
    public static List<CurrentContractDTO> setCurrentContracts(List resultList) throws ParseException {
        List<CurrentContractDTO> CurrentContractList = new ArrayList<>();
        CurrentContractDTO currentContractDTO;
        int listSize = resultList.size();
        try {
            for (int i = 0, limit = listSize; i < limit; i++) {
                currentContractDTO = new CurrentContractDTO();
                Object objects[] = (Object[]) resultList.get(i);
                currentContractDTO.setCheckRecord(false);
                currentContractDTO.setContractHolder(String.valueOf(objects[1]));
                currentContractDTO.setContractNo(String.valueOf(objects[NumericConstants.TWO]));
                currentContractDTO.setContractName(String.valueOf(objects[NumericConstants.THREE]));
                currentContractDTO.setMarketType(String.valueOf(objects[NumericConstants.FOUR]));
                currentContractDTO.setStartDate(formatDate(String.valueOf(objects[NumericConstants.FIVE])));
                currentContractDTO.setEndDate(formatDate(String.valueOf(objects[NumericConstants.SIX])));
                currentContractDTO.setRebateScheduleNo(String.valueOf(objects[NumericConstants.SEVEN]));
                currentContractDTO.setRebateScheduleName(String.valueOf(objects[NumericConstants.EIGHT]));
                currentContractDTO.setRarCategory("RAR"); 
                currentContractDTO.setStatus(String.valueOf(objects[NumericConstants.NINE]));
                currentContractDTO.setCompanyStartDate(formatDate(String.valueOf(objects[NumericConstants.TEN])));
                currentContractDTO.setCompanyEndDate((Date) objects[NumericConstants.ELEVEN]);
                CurrentContractList.add(currentContractDTO);
            }
        } catch (Exception ex) {
           LOGGER.error("",ex);
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
        List<ContractHolderDTO> returnList = new ArrayList<>();
        ContractHolderDTO contractHolderDTO;
        int resultSize = resultList.size();
        try {
            if (resultSize != 0) {
                for (int loop = 0, limit = resultSize; loop < limit; loop++) {
                    contractHolderDTO = new ContractHolderDTO();
                    Object[] objects = (Object[]) resultList.get(loop);
                    contractHolderDTO.setContractHolderId(String.valueOf(objects[0]));
                    contractHolderDTO.setContractHolderNo(String.valueOf(objects[1]));
                    contractHolderDTO.setContractHolderName(String.valueOf(objects[NumericConstants.TWO]));
                    contractHolderDTO.setContractHolderStatus(String.valueOf(objects[NumericConstants.THREE]));
                    contractHolderDTO.setContractHolderType(String.valueOf(objects[NumericConstants.FOUR]));
                    returnList.add(contractHolderDTO);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("",ex);
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
        List<RebatePlanDTO> returnList = new ArrayList<>();
        RebatePlanDTO rebatePlanDTO;
        int resultSize = resultList.size();
        try {
            if (resultSize != 0) {
                for (int loop = 0, limit = resultSize; loop < limit; loop++) {
                    rebatePlanDTO = new RebatePlanDTO();
                    Object[] objects = (Object[]) resultList.get(loop);
                    rebatePlanDTO.setRebatePlanId(String.valueOf(objects[1]));
                    rebatePlanDTO.setRebatePlanNo(String.valueOf(objects[NumericConstants.TWO]));
                    rebatePlanDTO.setRebatePlanName(convertNullToEmpty(String.valueOf(objects[NumericConstants.THREE])));
                    rebatePlanDTO.setRebatePlanStatus(convertNullToEmpty(String.valueOf(objects[NumericConstants.FOUR])));
                    rebatePlanDTO.setRebatePlanType(convertNullToEmpty(String.valueOf(objects[NumericConstants.FIVE])));
                    returnList.add(rebatePlanDTO);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return returnList;
    }
}
