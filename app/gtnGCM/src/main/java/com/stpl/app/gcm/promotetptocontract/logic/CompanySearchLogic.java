/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.logic;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.promotetptocontract.dao.PromoteTpDAO;
import com.stpl.app.gcm.promotetptocontract.dao.impl.PromoteTpDAOImpl;
import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.Converters;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class CompanySearchLogic {

    PromoteTpDAO promoteTpDAO = new PromoteTpDAOImpl();
    /**
     * The Constant LOGGER.
     */

    public int companySearchCount(PromoteTpToChDto tpDto, Set<Container.Filter> filters) throws  SystemException {
        Map<String, Object> parameters = new HashMap<>();
        List resultList;
        parameters.put(StringConstantsUtil.LAZY_LOAD_RESULTS, null);
        if (isValidCriteria(tpDto.getCompanyId())) {
            String companyId = tpDto.getCompanyId();
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyId", companyId);
        }
        if (isValidCriteria(tpDto.getCompanyName())) {
            String companyName = tpDto.getCompanyName();
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyName", companyName);
        }
        if (isValidCriteria(tpDto.getCompanyNo())) {
            String companyNo = tpDto.getCompanyNo();
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyNo", companyNo);
        }
        if (isValidSid(tpDto.getCompanyType())) {
            String companyType = tpDto.getCompanyType();
            companyType = companyType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyType", companyType);
        }
        if (isValidSid(tpDto.getCompanyCategory())) {
            String companyCategory = tpDto.getCompanyCategory();
            companyCategory = companyCategory.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyCategory", companyCategory);
        }
        if (tpDto.getTradeClass_DTO()!=null) {
            String tradeClass = tpDto.getTradeClass_DTO().toString();
            tradeClass = tradeClass.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyTradeClass", tradeClass);
        }
        if (isValidCriteria(tpDto.getIdentifier())) {
            String identifier = tpDto.getIdentifier();
            identifier = identifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("identifier", identifier);
        }
        if (isValidSid(tpDto.getIdentifierType())) {
            String identifierType = tpDto.getIdentifierType();
            identifierType = identifierType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("identifierType", identifierType);
        }
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        resultList = promoteTpDAO.searchTPCompanies(parameters);
        return Integer.parseInt(String.valueOf(resultList.get(0)));
    }

    /**
     * Search Companies logic for Promote TP
     *
     * @param tpDTO
     * @param startIndex
     * @param offset
     * @param orderByColumns
     * @param parentCompanySid
     * @param filters
     * @param searchSessionId
     * @return
     * @throws PortalException
     * @throws Exception
     */
    public List<PromoteTpToChDto> searchCompaniesLazy(final PromoteTpToChDto tpDTO, int startIndex, int offset, Set<Container.Filter> filters, String searchSessionId) throws SystemException {
        Map<String, Object> parameters = new HashMap<>();
        List resultList;
        List<PromoteTpToChDto> returnList = null;
        parameters.put("startIndex", startIndex);
        parameters.put("offset", offset);
        parameters.put(StringConstantsUtil.LAZY_LOAD_RESULTS, StringConstantsUtil.LAZY_LOAD_RESULTS);

        if (isValidCriteria(tpDTO.getCompanyId())) {
            String companyId = tpDTO.getCompanyId();
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyId", companyId);
        }
        if (isValidCriteria(tpDTO.getCompanyName())) {
            String companyName = tpDTO.getCompanyName();
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyName", companyName);
        }
        if (isValidCriteria(tpDTO.getCompanyNo())) {
            String companyNo = tpDTO.getCompanyNo();
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyNo", companyNo);
        }
        if (isValidSid(tpDTO.getCompanyType())) {
            String companyType = tpDTO.getCompanyType();
            companyType = companyType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyType", companyType);
        }
        if (isValidSid(tpDTO.getCompanyCategory())) {
            String companyCategory = tpDTO.getCompanyCategory();
            companyCategory = companyCategory.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyCategory", companyCategory);
        }
         if (tpDTO.getTradeClass_DTO()!=null) {
            String tradeClass = tpDTO.getTradeClass_DTO().toString();
            tradeClass = tradeClass.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyTradeClass", tradeClass);
        }
        if (isValidCriteria(tpDTO.getIdentifier())) {
            String identifier = tpDTO.getIdentifier();
            identifier = identifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("identifier", identifier);
        }
        if (isValidSid(tpDTO.getIdentifierType())) {
            String identifierType = tpDTO.getIdentifierType();
            identifierType = identifierType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("identifierType", identifierType);
        }
        if (isValidSid(searchSessionId)) {
            parameters.put("searchSessionId", searchSessionId);
        }
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        resultList = promoteTpDAO.searchTPCompanies(parameters);
        returnList = Converters.searchTPCompany(resultList);
        return returnList;
            }

    private boolean isValidCriteria(String value) {
        boolean isValid = false;
        if (value != null && !Constants.NULL.equals(String.valueOf(value)) && !StringUtils.EMPTY.equals(String.valueOf(value))
                && !StringUtils.isEmpty(String.valueOf(value)) && !StringUtils.isBlank(String.valueOf(value))) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    private boolean isValidSid(String sId) {
        boolean isValid = false;
        if (isValidCriteria(String.valueOf(sId))) {
            if (!Constants.ZEROSTRING.equals(String.valueOf(sId))) {
                isValid = true;
            } else {
                isValid = false;
            }
        } else {
            isValid = false;
        }
        return isValid;
    }
}
