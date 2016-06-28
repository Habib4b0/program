package com.stpl.app.contract.dashboard.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

import com.stpl.app.contract.dashboard.dto.ContractMember;
import com.stpl.app.contract.dashboard.dto.DetailSearchDTO;
import com.stpl.app.contract.util.Constants;
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
	public Map<String,String> getContractFilterMap(final BeanSearchCriteria searchCriteria){
		
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
        LOGGER.info("Entering getCustomizedDTOFromModel method");

        final List<ContractMember> searchList = new ArrayList<ContractMember>();
        
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Object[] record=(Object[])list.get(i);
                if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])), Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[3]), String.valueOf(record[1]), String.valueOf(record[2]), (record[4] != null && !"-Select One-".equals(String.valueOf(record[4])) ? String.valueOf(record[4]) : ""),
                            Constants.CONTRACT, true, record[5] != null ? (Date) record[5] : null, record[6] != null ? (Date) record[6] : null));
                }
                if (ContractUtils.CFP_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])),Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[3]), String.valueOf(record[1]), String.valueOf(record[2]),(record[4]!=null && !"-Select One-".equals(String.valueOf(record[4]))?String.valueOf(record[4]):""), Constants.CFP,
                            true, record[5] != null ? (Date) record[5] : null, record[6] != null ? (Date) record[6] : null));
                }
                if (ContractUtils.IFP_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])),Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[3]), String.valueOf(record[1]), String.valueOf(record[2]),(record[4]!=null && !"-Select One-".equals(String.valueOf(record[4]))?String.valueOf(record[4]):""), Constants.IFP, true,record[5] != null ? (Date) record[5] : null, record[6] != null ? (Date) record[6] : null));
                }
                if (ContractUtils.PS_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])),Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[3]), String.valueOf(record[1]), String.valueOf(record[2]),(record[4]!=null && !"-Select One-".equals(String.valueOf(record[4]))?String.valueOf(record[4]):""), Constants.PS_VALUE, true,record[5] != null ? (Date) record[5] : null, record[6] != null ? (Date) record[6] : null));
                }
                if (ContractUtils.RS_COMPONENT.equals(flag)) {
                    searchList.add(new ContractMember(Integer.valueOf(String.valueOf(record[0])),Integer.valueOf(String.valueOf(record[0])), String.valueOf(record[3]), String.valueOf(record[1]), String.valueOf(record[2]),(record[4]!=null && !"-Select One-".equals(String.valueOf(record[4]))?String.valueOf(record[4]):""), Constants.RS_VALUE, false,record[5] != null ? (Date) record[5] : null, record[6] != null ? (Date) record[6] : null));
                }
            }
        }
        LOGGER.info("End of getCustomizedDTOFromModel method");
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
        LOGGER.info("Entering getCustomizedDTOFromModel method");

        final List<DetailSearchDTO> searchList = new ArrayList<DetailSearchDTO>();
        
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Object[] record=(Object[])list.get(i);
                
                if (ContractUtils.CFP_COMPONENT.equals(flag)) {
                    searchList.add(new DetailSearchDTO(String.valueOf(record[0]),String.valueOf(record[1]),
                            (record[2]!=null && !"-Select One-".equals(String.valueOf(record[2]))?String.valueOf(record[2]):""),
                            (record[3]!=null && !"-Select One-".equals(String.valueOf(record[3]))?String.valueOf(record[3]):""),
                            (record[4]!=null && !"-Select One-".equals(String.valueOf(record[4]))?String.valueOf(record[4]):""),null,null));
                }
                if (ContractUtils.IFP_COMPONENT.equals(flag)) {
                    searchList.add(new DetailSearchDTO(String.valueOf(record[0]),String.valueOf(record[1]),"","",
                            (record[2]!=null && !"-Select One-".equals(String.valueOf(record[2]))?String.valueOf(record[2]):""),
                            (record[3]!=null?(Date)record[3]:null),(record[4]!=null?(Date)record[4]:null)));
                }
                if (ContractUtils.PS_COMPONENT.equals(flag)) {
                    searchList.add(new DetailSearchDTO(String.valueOf(record[0]),String.valueOf(record[1]),"","",
                            (record[2]!=null && !"-Select One-".equals(String.valueOf(record[2]))?String.valueOf(record[2]):""),
                            (record[3]!=null?(Date)record[3]:null),(record[4]!=null?(Date)record[4]:null)));
                }
                if (ContractUtils.RS_COMPONENT.equals(flag)) {
                    searchList.add(new DetailSearchDTO(String.valueOf(record[0]),String.valueOf(record[1]),"","",
                            (record[2]!=null && !"-Select One-".equals(String.valueOf(record[2]))?String.valueOf(record[2]):""),
                            (record[3]!=null?(Date)record[3]:null),(record[4]!=null?(Date)record[4]:null)));
                }
            }
        }
        LOGGER.info("End of getCustomizedDTOFromModel method");
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
