package com.stpl.app.contract.dashboard.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

import com.stpl.app.contract.dashboard.dto.ContractMember;
import com.stpl.app.contract.dashboard.dto.DetailSearchDTO;
import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts Raw data to User Interface Object and Vice versa
 * @author shrihariharan
 *
 */
public class ConverterUtil {

	
	/**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ConverterUtil.class);
	
	/**
	 * 
	 * @param searchCriteria
	 * @return 
	 */
	public Map<String,String> getContractFilterMap(){
		
		Map<String,String> filterMap = new HashMap<String,String>();
		
		
		
		return filterMap;
	}
	
	
	/**
     * Method used for getCustomizedDTOFromModel.
     *
     * @param list the list
     * @param flag the flag
     * @return the customized dto from model
     */
    public List<ContractMember> getCustomizedDTOFromModel(final List list, final String flag) {
        LOGGER.debug("Entering getCustomizedDTOFromModel method");

        final List<ContractMember> searchList = new ArrayList<ContractMember>();
        
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Object[] record=(Object[])list.get(i);
                if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])), Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[NumericConstants.THREE]), String.valueOf(record[1]), String.valueOf(record[NumericConstants.TWO]), record[NumericConstants.FOUR] != null && !"-Select One-".equals(String.valueOf(record[NumericConstants.FOUR])) ? String.valueOf(record[NumericConstants.FOUR]) : "",
                            Constants.CONTRACT, true, record[NumericConstants.FIVE] != null ? (Date) record[NumericConstants.FIVE] : null, record[NumericConstants.SIX] != null ? (Date) record[NumericConstants.SIX] : null));
                }
                if (ContractUtils.CFP_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])),Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[NumericConstants.THREE]), String.valueOf(record[1]), String.valueOf(record[NumericConstants.TWO]),record[NumericConstants.FOUR]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.FOUR]))?String.valueOf(record[NumericConstants.FOUR]):"", Constants.CFP,
                            true, record[NumericConstants.FIVE] != null ? (Date) record[NumericConstants.FIVE] : null, record[NumericConstants.SIX] != null ? (Date) record[NumericConstants.SIX] : null));
                }
                if (ContractUtils.IFP_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])),Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[NumericConstants.THREE]), String.valueOf(record[1]), String.valueOf(record[NumericConstants.TWO]),record[NumericConstants.FOUR]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.FOUR]))?String.valueOf(record[NumericConstants.FOUR]):"", Constants.IFP, true,record[NumericConstants.FIVE] != null ? (Date) record[NumericConstants.FIVE] : null, record[NumericConstants.SIX] != null ? (Date) record[NumericConstants.SIX] : null));
                }
                if (ContractUtils.PS_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])),Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[NumericConstants.THREE]), String.valueOf(record[1]), String.valueOf(record[NumericConstants.TWO]),record[NumericConstants.FOUR]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.FOUR]))?String.valueOf(record[NumericConstants.FOUR]):"", Constants.PS_VALUE, true,record[NumericConstants.FIVE] != null ? (Date) record[NumericConstants.FIVE] : null, record[NumericConstants.SIX] != null ? (Date) record[NumericConstants.SIX] : null));
                }
                if (ContractUtils.RS_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])),Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[NumericConstants.THREE]), String.valueOf(record[1]), String.valueOf(record[NumericConstants.TWO]),record[NumericConstants.FOUR]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.FOUR]))?String.valueOf(record[NumericConstants.FOUR]):"", Constants.RS_VALUE, false,record[NumericConstants.FIVE] != null ? (Date) record[NumericConstants.FIVE] : null, record[NumericConstants.SIX] != null ? (Date) record[NumericConstants.SIX] : null));
                }
            }
        }
        LOGGER.debug("End of getCustomizedDTOFromModel method");
        return searchList;
    }
	
    	/**
     * Method used for getCustomizedDTOFromModel.
     *
     * @param list the list
     * @param flag the flag
     * @return the customized dto from model
     */
    public List<DetailSearchDTO> getCustomizedDTODetaildFromModel(final List list, final String flag) {
        LOGGER.debug("Entering getCustomizedDTOFromModel method");

        final List<DetailSearchDTO> searchList = new ArrayList<DetailSearchDTO>();
        
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Object[] record=(Object[])list.get(i);
                
                if (ContractUtils.CFP_COMPONENT.equals(flag)) {
                    searchList.add(new DetailSearchDTO(String.valueOf(record[0]),String.valueOf(record[1]),
                            record[NumericConstants.TWO]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.TWO]))?String.valueOf(record[NumericConstants.TWO]):"",
                            record[NumericConstants.THREE]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.THREE]))?String.valueOf(record[NumericConstants.THREE]):"",
                            record[NumericConstants.FOUR]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.FOUR]))?String.valueOf(record[NumericConstants.FOUR]):"",null,null));
                }
                if (ContractUtils.IFP_COMPONENT.equals(flag)) {
                    searchList.add(new DetailSearchDTO(String.valueOf(record[0]),String.valueOf(record[1]),"","",
                            record[NumericConstants.TWO]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.TWO]))?String.valueOf(record[NumericConstants.TWO]):"",
                            record[NumericConstants.THREE]!=null?(Date)record[NumericConstants.THREE]:null,record[NumericConstants.FOUR]!=null?(Date)record[NumericConstants.FOUR]:null));
                }
                if (ContractUtils.PS_COMPONENT.equals(flag)) {
                    searchList.add(new DetailSearchDTO(String.valueOf(record[0]),String.valueOf(record[1]),"","",
                            record[NumericConstants.TWO]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.TWO]))?String.valueOf(record[NumericConstants.TWO]):"",
                            record[NumericConstants.THREE]!=null?(Date)record[NumericConstants.THREE]:null,record[NumericConstants.FOUR]!=null?(Date)record[NumericConstants.FOUR]:null));
                }
                if (ContractUtils.RS_COMPONENT.equals(flag)) {
                    searchList.add(new DetailSearchDTO(String.valueOf(record[0]),String.valueOf(record[1]),"","",
                            record[NumericConstants.TWO]!=null && !"-Select One-".equals(String.valueOf(record[NumericConstants.TWO]))?String.valueOf(record[NumericConstants.TWO]):"",
                            record[NumericConstants.THREE]!=null?(Date)record[NumericConstants.THREE]:null,record[NumericConstants.FOUR]!=null?(Date)record[NumericConstants.FOUR]:null));
                }
            }
        }
        LOGGER.debug("End of getCustomizedDTOFromModel method");
        return searchList;
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
    
}
