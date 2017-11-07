/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.logic;

import com.stpl.app.model.AccrualDetails;
import com.stpl.app.transactional.common.dto.ARPOutboundDTO;
import com.stpl.app.transactional.common.dto.AccrualdetailsDTO;
import com.stpl.app.transactional.common.dto.ArmOutboundDTO;
import com.stpl.app.transactional.common.dto.CFFOutBoundDTO;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import com.stpl.app.util.AbstractNotificationUtils;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Maheshj
 */
public class TransactionalSearchLogic extends PageTableLogic {

    Map<Object, Object> searchValues;
    List<DetailsDTO> moduleDetailsValue = null;
    private String tableName;
    SearchLogic searchLogic = new SearchLogic();
    Map<Integer, Boolean> SelectedAccrualsIdMap = new LinkedHashMap<>();
    AccrualdetailsDTO accDto = null;
    ARPOutboundDTO arpDto = null;
    ArmOutboundDTO gtnDto = null;
    boolean firstGenerated = false;
    boolean isReset =true;
    boolean isSearchFirst = true;
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(TransactionalSearchLogic.class);
    DetailsDTO primaryDTO;
    private int i=NumericConstants.TWENTY_SEVEN;
    public boolean isFirstSearch = false;
    public boolean isFirstTimeSearch = false;
    private int arpCount=0;
    public void configureSearchLogic(final DetailsDTO primaryDTO){
        this.primaryDTO = primaryDTO;
    }
    /**
     * Loads the table in sales projection based on start and end index.
     *
     * @param start
     * @param offset
     * @return
     */
    /**
     * Returns the available number of records based on the generate criteria.
     *
     * @return
     */
    @Override
    public int getCount() {
        int count = 0;
        LOGGER.debug("Entering count with search SearchDAO---> tableName====== " + tableName);
        if (!isReset) {
            try {

                if (tableName != null) {

                    if (searchValues.isEmpty() && this.getFilters() == null) {
                        count = (int) searchLogic.getActualsTotalCount(tableName);
                    } else if ("ActualsMaster".equals(tableName) || "IvldActualMaster".equals(tableName)) {
                        count = searchLogic.getActualSearchResults(searchValues, this.getFilters(), tableName);
                    } else if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                        List<Object[]> list = searchLogic.searchArpOutbound(searchValues, true, this.getFilters());
                        if (list != null) {
                            count = Integer.parseInt(StringUtils.isNotBlank(String.valueOf(list.get(0))) ? String.valueOf(list.get(0)) : "0");
                        }
                        arpCount=count;
                    } else if (tableName.equals(ConstantUtil.ST_CFF_OUTBOUND)) {
                        List<Object> list = searchLogic.cffSearch(searchValues, Boolean.TRUE, this.getFilters(), this.getSortByColumns(), 0, 0, isFirstSearch,tableName);
                        count = list == null && list.isEmpty() ? 0 : (int) list.get(0);
                    } else if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                    List<Object> list = searchLogic.gtnSearch(searchValues, Boolean.TRUE, this.getFilters(), this.getSortByColumns(), 0, 0, isFirstTimeSearch,tableName, Boolean.FALSE);
                        count = list == null || list.isEmpty() ? 0 : list.size();
                    } else {
                        if (searchValues != null && searchValues.get(ACCRUAL_TYPE) != null
                                && !searchValues.get(ACCRUAL_TYPE).equals("Other")
                                && !tableName.equals(ConstantUtil.INVALID_ACCURAL_INBOUND)) {
                            tableName = ACCRUAL_DETAILS;
                        }

                        count = searchLogic.getDynamicQuerySearch(searchValues, this.getFilters(), tableName);
                    primaryDTO.setTotalCount(count);
                    }
                }
                LOGGER.debug("Ends count with count value ::________________________:: " + count);
                return count;
            } catch (SystemException e) {
                LOGGER.error(e);
            } catch (PortalException e) {
                LOGGER.error(e);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return 0;

    }
    public static final String ACCRUAL_DETAILS = "AccrualDetails";
    public static final String ACCRUAL_TYPE = "accrualType";

    /**
     *
     * @param object
     * @param datasource
     * @return
     */
    @Override
    public Object configureContainer(Object object, Container datasource) {
        Class cl = null;
        try {
            if ((tableName.equals(ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW)) || (tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) || (tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW))
                    || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || tableName.equals(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName) || tableName.equals(ConstantUtil.INVALID_COMPANY_MASTER) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(tableName) || ConstantUtil.INVALID_COMPANY_PARENT.equals(tableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(tableName) || tableName.equals("IvldItemMaster") || tableName.equals("IvldItemIdentifier") || tableName.equals("IvldItemPricing") || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName)  || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)) {
                cl = Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName);
            } else if (ACCRUAL_DETAILS.equals(tableName)) {
                cl = Class.forName("com.stpl.app.model.AccrualDetails");
            }else if (ConstantUtil.ARP_OUTBOUND.equals(tableName)) {
                 cl = Class.forName("com.stpl.app.transactional.common.dto.ARPOutboundDTO");
            } else if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                cl = Class.forName("com.stpl.app.transactional.common.dto.CFFOutBoundDTO");
            } else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                cl = Class.forName("com.stpl.app.transactional.common.dto.ArmOutboundDTO");
            } else {
                cl = Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName);
            }
            if (ACCRUAL_DETAILS.equals(tableName)) {
                ((BeanItemContainer<Object>) datasource).addBean(getAccuralsDTO((AccrualDetails) (object)));
            } else if(ConstantUtil.ARP_OUTBOUND.equals(tableName))
            {
                 ((BeanItemContainer<ARPOutboundDTO>) datasource).addBean(arpOutbound(object));
                 return cl.cast(arpOutbound(object));
            } else if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                CFFOutBoundDTO cffDTO = getCFFSearchCustomization(object);
                ((BeanItemContainer<CFFOutBoundDTO>) datasource).addBean(cffDTO);
                return cl.cast(cffDTO);
            } else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                ArmOutboundDTO armDTO = tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL)?gtnOutbound(object):reserveOutbound(object);
                ((BeanItemContainer<ArmOutboundDTO>) datasource).addBean(armDTO);
                return cl.cast(armDTO);
            }  
            else {
                ((BeanItemContainer<Object>) datasource).addBean(cl.cast(object));
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return cl.cast(object);
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(NumericConstants.ONE);
    }

    public int getPageForItem(int pos) {
        int curPage = ((pos - NumericConstants.TWO) / getPageLength()) + NumericConstants.ONE;
        return curPage;
    }

    public int getItemIndex(int pos) {
        int index = (pos - NumericConstants.TWO) % getPageLength();
        return index;
    }

    public int getStartIndex(int count, int index) {
        int start = count - index;
        return start;
    }

    @Override
    protected void createCurrentPageStart() {

        setRefresh(Boolean.FALSE);
    }

    @Override
    protected void createCurrentPageEnd() {

        setRefresh(Boolean.TRUE);
    }

    @Override
    protected void controlButtons() {
        super.controlButtons(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public List loadData(int start, int offset) {
        List<Object> salesList1 = new ArrayList<>();

        LOGGER.debug("map size" + SelectedAccrualsIdMap.size());
        LOGGER.debug("Entering find with startIndex value :::: " + start + " , offset value :::: " + offset + " and columns size ::::" + this.getSortByColumns().size() + " and criteria -->");
        try {
            if (tableName != null) {
                if ("ActualsMaster".equals(tableName) || "IvldActualMaster".equals(tableName)) {
                    salesList1 = searchLogic.searchFindForActualMaster(searchValues, start, start + offset, this.getSortByColumns(), this.getFilters(), tableName, false, null, primaryDTO);
                    } else if (tableName.equals(ConstantUtil.ARP_OUTBOUND)) {
                    salesList1 = searchLogic.searchFindForArpOutBound(searchValues, start, offset, this.getSortByColumns(), this.getFilters(), false,isSearchFirst);
                  isSearchFirst = false;
                } else if (tableName.equals(ConstantUtil.ST_CFF_OUTBOUND)) {
                      salesList1 = searchLogic.cffSearch(searchValues, Boolean.FALSE, this.getFilters(), this.getSortByColumns(), start, offset, Boolean.FALSE,tableName);
                } else if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                    salesList1= searchLogic.gtnSearch(searchValues, Boolean.FALSE, this.getFilters(), this.getSortByColumns(),start,offset,Boolean.FALSE,tableName, Boolean.FALSE);
                    }
                else {
                    if (searchValues != null && searchValues.get(ACCRUAL_TYPE) != null 
                            && !searchValues.get(ACCRUAL_TYPE).equals("Other") 
                            && !tableName.equals(ConstantUtil.INVALID_ACCURAL_INBOUND)) {
                        tableName = ACCRUAL_DETAILS;
                    }
                    salesList1 = searchLogic.searchFind(searchValues, start, start + offset, this.getSortByColumns(), this.getFilters(), tableName,false, null, SelectedAccrualsIdMap,primaryDTO);
                }

                LOGGER.debug("Ends find with salesList1 size ::::" + salesList1.size());
            }
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_3002));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return salesList1;
    }

    public void configureSearchData(final Map<Object, Object> searchValues, String tableName, final List<DetailsDTO> moduleDetailsValue, Map AccrualIds,boolean isReset,boolean isSearch) {
        this.clearAll();
        this.getFilters().clear();
        this.tableName = tableName;
        this.isReset=isReset;
        this.isSearchFirst = isSearch;
        this.searchValues = searchValues;
        this.moduleDetailsValue = moduleDetailsValue;
        this.SelectedAccrualsIdMap = AccrualIds;
        this.setRequiredCount(true);
        this.setCurrentPage(NumericConstants.ONE);
    }

    @Override
    public void setContainerDataSource(Container newDataSource) {
        if (!(newDataSource instanceof Container.Indexed)
                || !(newDataSource instanceof Container.Filterable)) {
            throw new IllegalArgumentException(
                    "ExtPagedTable can only use containers that implement Container.Indexed, Container.Filterable AND Container.Hierarchical");
        }
        container = newDataSource;
        for (ExtPagedTable tbl : getTables()) {
            tbl.setContainerDataSource(container);

        }
    }

    private AccrualdetailsDTO getAccuralsDTO(AccrualDetails acDetails) {
        accDto = new AccrualdetailsDTO();
        accDto.setGlCompanyName(acDetails.getCompanyId());
        accDto.setAccrualId(acDetails.getAccountId());
        accDto.setAccountName(acDetails.getAccountName());
        accDto.setAccountNo(acDetails.getAccountNo());
        accDto.setAccrualDetailsSid(acDetails.getAccrualDetailsSid());
        accDto.setAccrualId(acDetails.getAccrualId());
        accDto.setAccrualPeriodStartDate(acDetails.getAccrualPeriodStartDate());
        accDto.setAccrualPeriodEndDate(acDetails.getAccrualPeriodEndDate());
        acDetails.getAcctIdentifierCodeQualifier();
        acDetails.getAmount();
        accDto.setBatchId(acDetails.getBatchId());
        accDto.setBrandName(acDetails.getBrandName());
        accDto.setCompanyCostCenter(acDetails.getCompanyCostCenter());
        accDto.setContractNo(acDetails.getContractNo());
        accDto.setCreatedBy(String.valueOf(acDetails.getCreatedBy()));
        accDto.setCreatedDate(acDetails.getCreatedDate());
        accDto.setItemName(acDetails.getItemName());
        accDto.setItemNo(acDetails.getItemNo());
        accDto.setPostingDate(acDetails.getPostingDate());
        accDto.setPostingIndicator(acDetails.getPostingIndicator());
        accDto.setProgramNo(acDetails.getProgramNo());
        accDto.setProgramType(String.valueOf(acDetails.getProgramType()));
        accDto.setRecordLockStatus(acDetails.getRecordLockStatus());
        accDto.setSalesMasterId(acDetails.getSalesMasterId());
        accDto.setSource(acDetails.getSource());
        accDto.setSubLedger(acDetails.getSubLedger());
        accDto.setSubLedgerType(acDetails.getSubLedgerType());

        return accDto;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

      private ARPOutboundDTO arpOutbound(Object atpObject) throws ParseException {
         try{
        SimpleDateFormat sdf = new SimpleDateFormat(MM_DD_YYYY);
        arpDto = new ARPOutboundDTO();
        Object[] obj = (Object[]) atpObject;
        arpDto.setArp_Id(checkForNullValue(obj[NumericConstants.ONE]));
        arpDto.setArp_Name(checkForNullValue(obj[NumericConstants.TWO]));
        arpDto.setRsMoldelSid(checkForNullValue(obj[NumericConstants.THREE]));
        arpDto.setProgram(checkForNullValue(obj[NumericConstants.FOUR]));
        arpDto.setCompany_Id(checkForNullValue(obj[NumericConstants.FIVE]));
        arpDto.setCompany_No(checkForNullValue(obj[NumericConstants.SIX]));
        arpDto.setCompany_Name(checkForNullValue(obj[NumericConstants.SEVEN]));
        arpDto.setItem_Id(checkForNullValue(obj[NumericConstants.EIGHT]));
        arpDto.setItem_No(checkForNullValue(obj[NumericConstants.NINE]));
        arpDto.setItem_Name(checkForNullValue(obj[NumericConstants.TEN]));
        arpDto.setBrand_Id(checkForNullValue(obj[NumericConstants.ELEVEN]));
        arpDto.setBrand_Name(checkForNullValue(obj[NumericConstants.TWELVE]));
        arpDto.setItemMasterSid(checkForNullValue(obj[NumericConstants.THIRTEEN]));
        arpDto.setCompanyMasterSid(checkForNullValue(obj[NumericConstants.FOURTEEN]));
        arpDto.setBrandMasterSid(checkForNullValue(obj[NumericConstants.FIFTEEN]));
        arpDto.setAccount(checkForNullValue(obj[NumericConstants.SIXTEEN]));
        arpDto.setAccount_Type(checkForNullValue(obj[NumericConstants.SEVENTEEN]));
        arpDto.setCategory(checkForNullValue(obj[NumericConstants.EIGHTEEN]));
        arpDto.setType(checkForNullValue(obj[NumericConstants.NINETEEN]));
        if (obj[NumericConstants.TWENTY_THREE] != null) {
            Boolean flag = (Boolean) obj[NumericConstants.TWENTY_THREE];
            if (flag) {
                arpDto.setCheck_Record(true);
            } else {
                arpDto.setCheck_Record(false);
            }
        } else {
            arpDto.setCheck_Record(false);
        }
        if (obj[NumericConstants.TWENTY_FOUR] != null) {
            Date date = (Date) obj[NumericConstants.TWENTY_FOUR];
            String creationDate = sdf.format(date);
            arpDto.setArp_Creation_Date(sdf.parse(creationDate));
        }
        if (obj[NumericConstants.TWENTY_FIVE] != null) {
            Date date = (Date) obj[NumericConstants.TWENTY_FIVE];
            String approvalDate = sdf.format(date);
            arpDto.setArp_Approval_Date(sdf.parse(approvalDate));
        }
        if (obj[NumericConstants.TWENTY_SIX] != null) {
            arpDto.setOutbound_Status((Boolean)obj[NumericConstants.TWENTY_SIX]==false ? "N" : "Y");
        } 
        
        arpDto.setOriginal_Batch_ID(checkForNullValue(obj[NumericConstants.TWENTY_SEVEN]));

        arpDto.setCurrent_Year_Jan(checkForDoubleValue(obj[i + NumericConstants.ONE]));
        arpDto.setCurrent_Year_Feb(checkForDoubleValue(obj[i + NumericConstants.TWO]));
        arpDto.setCurrent_Year_Mar(checkForDoubleValue(obj[i + NumericConstants.THREE]));
        arpDto.setCurrent_Year_Apr(checkForDoubleValue(obj[i + NumericConstants.FOUR]));
        arpDto.setCurrent_Year_May(checkForDoubleValue(obj[i + NumericConstants.FIVE]));
        arpDto.setCurrent_Year_June(checkForDoubleValue(obj[i + NumericConstants.SIX]));
        arpDto.setCurrent_Year_July(checkForDoubleValue(obj[i + NumericConstants.SEVEN]));
        arpDto.setCurrent_Year_Aug(checkForDoubleValue(obj[i + NumericConstants.EIGHT]));
        arpDto.setCurrent_Year_Sep(checkForDoubleValue(obj[i + NumericConstants.NINE]));
        arpDto.setCurrent_Year_Oct(checkForDoubleValue(obj[i + NumericConstants.TEN]));
        arpDto.setCurrent_Year_Nov(checkForDoubleValue(obj[i + NumericConstants.ELEVEN]));
        arpDto.setCurrent_Year_Dec(checkForDoubleValue(obj[i + NumericConstants.TWELVE]));

        arpDto.setCurrent_Year_1_Jan(checkForDoubleValue(obj[i + NumericConstants.THIRTEEN]));
        arpDto.setCurrent_Year_1_Feb(checkForDoubleValue(obj[i + NumericConstants.FOURTEEN]));
        arpDto.setCurrent_Year_1_Mar(checkForDoubleValue(obj[i + NumericConstants.FIFTEEN]));
        arpDto.setCurrent_Year_1_Apr(checkForDoubleValue(obj[i + NumericConstants.SIXTEEN]));
        arpDto.setCurrent_Year_1_May(checkForDoubleValue(obj[i + NumericConstants.SEVENTEEN]));
        arpDto.setCurrent_Year_1_June(checkForDoubleValue(obj[i + NumericConstants.EIGHTEEN]));
        arpDto.setCurrent_Year_1_July(checkForDoubleValue(obj[i + NumericConstants.NINETEEN]));
        arpDto.setCurrent_Year_1_Aug(checkForDoubleValue(obj[i + NumericConstants.TWENTY]));
        arpDto.setCurrent_Year_1_Sep(checkForDoubleValue(obj[i + NumericConstants.TWENTY_ONE]));
        arpDto.setCurrent_Year_1_Oct(checkForDoubleValue(obj[i + NumericConstants.TWENTY_TWO]));
        arpDto.setCurrent_Year_1_Nov(checkForDoubleValue(obj[i + NumericConstants.TWENTY_THREE]));
        arpDto.setCurrent_Year_1_Dec(checkForDoubleValue(obj[i + NumericConstants.TWENTY_FOUR]));

        arpDto.setCurrent_Year_2_Jan(checkForDoubleValue(obj[i + NumericConstants.TWENTY_FIVE]));
        arpDto.setCurrent_Year_2_Feb(checkForDoubleValue(obj[i + NumericConstants.TWENTY_SIX]));
        arpDto.setCurrent_Year_2_Mar(checkForDoubleValue(obj[i + NumericConstants.TWENTY_SEVEN]));
        arpDto.setCurrent_Year_2_Apr(checkForDoubleValue(obj[i + NumericConstants.TWENTY_EIGHT]));
        arpDto.setCurrent_Year_2_May(checkForDoubleValue(obj[i + NumericConstants.TWENTY_NINE]));
        arpDto.setCurrent_Year_2_June(checkForDoubleValue(obj[i + NumericConstants.THIRTY]));
        arpDto.setCurrent_Year_2_July(checkForDoubleValue(obj[i + NumericConstants.THIRTY_ONE]));
        arpDto.setCurrent_Year_2_Aug(checkForDoubleValue(obj[i + NumericConstants.THIRTY_TWO]));
        arpDto.setCurrent_Year_2_Sep(checkForDoubleValue(obj[i + NumericConstants.THIRTY_THREE]));
        arpDto.setCurrent_Year_2_Oct(checkForDoubleValue(obj[i + NumericConstants.THIRTY_FOUR]));
        arpDto.setCurrent_Year_2_Nov(checkForDoubleValue(obj[i + NumericConstants.THIRTY_FIVE]));
        arpDto.setCurrent_Year_2_Dec(checkForDoubleValue(obj[i + NumericConstants.THIRTY_SIX]));

        arpDto.setUom(checkForNullValue(obj[i + NumericConstants.THIRTY_SEVEN]));
        arpDto.setAccount_category("-Select One-".equals(checkForNullValue(obj[i + NumericConstants.THIRTY_EIGHT])) ? StringUtils.EMPTY : checkForNullValue(obj[i + NumericConstants.THIRTY_EIGHT]));
        arpDto.setAccount_group("-Select One-".equals(checkForNullValue(obj[i + NumericConstants.THIRTY_NINE]))? StringUtils.EMPTY : checkForNullValue(obj[i + NumericConstants.THIRTY_NINE]));
         }catch(Exception e){
            LOGGER.error(e);
         }
        return arpDto;
    }
    public static final String MM_DD_YYYY = "MM/dd/yyy";

    private String checkForNullValue(Object obj) {
        String value=StringUtils.EMPTY;
       if(obj==null){
           return value;
       }else{
           if(String.valueOf(obj).equals("null")){
               return value; 
           }else{
               return String.valueOf(obj);
           }
       }
       
   }
   
  
      private double checkForDoubleValue(Object obj) {
        String strValue;
        double value=0;
       if(obj==null){
          
       }else{
           if(String.valueOf(obj).equals("null")|| String.valueOf(obj).equals("")){
              
           }else{
               value= Double.parseDouble(String.valueOf(obj));
           }
       }
       if(String.valueOf(value).contains(",")){
           strValue = String.valueOf(value).replaceAll(",","");
            value = Double.parseDouble(strValue);
       }else{
       value = value;
       }
       return value;
   } 
    
    
   private CFFOutBoundDTO getCFFSearchCustomization(Object object) {
        CFFOutBoundDTO cff = null;
        if (object != null) {
            Object[] obj = (Object[]) object;
            cff = new CFFOutBoundDTO();
            cff.setFinancialForecastId(isValid(obj[0]) ? Integer.valueOf(obj[0].toString()) : 0);
            cff.setFinancialForecastName(isValid(obj[NumericConstants.ONE]) ? obj[NumericConstants.ONE].toString() : StringUtils.EMPTY);
            cff.setType(isValid(obj[NumericConstants.TWO]) ? obj[NumericConstants.TWO].toString() : StringUtils.EMPTY);
            cff.setProjectId(isValid(obj[NumericConstants.THREE]) ? obj[NumericConstants.THREE].toString() : StringUtils.EMPTY);
            cff.setProjectionName(isValid(obj[NumericConstants.FOUR]) ? obj[NumericConstants.FOUR].toString() : StringUtils.EMPTY);
            if(isValid(obj[NumericConstants.FIVE])){
                cff.setYear(Integer.valueOf(obj[NumericConstants.FIVE].toString()));
            }
            if(isValid(obj[NumericConstants.SIX])){
                cff.setMonth(Integer.valueOf(obj[NumericConstants.SIX].toString()));
            }
            cff.setContractId(isValid(obj[NumericConstants.SEVEN]) ? obj[NumericConstants.SEVEN].toString() : StringUtils.EMPTY);
            cff.setContractNo(isValid(obj[NumericConstants.EIGHT]) ? obj[NumericConstants.EIGHT].toString() : StringUtils.EMPTY);
            cff.setContractName(isValid(obj[NumericConstants.NINE]) ? obj[NumericConstants.NINE].toString() : StringUtils.EMPTY);
            cff.setContractType(isValid(obj[NumericConstants.TEN]) ? obj[NumericConstants.TEN].toString() : StringUtils.EMPTY);
            cff.setContractHolderId(isValid(obj[NumericConstants.ELEVEN]) ? obj[NumericConstants.ELEVEN].toString() : StringUtils.EMPTY);
            cff.setContractHolderNo(isValid(obj[NumericConstants.TWELVE]) ? obj[NumericConstants.TWELVE].toString() : StringUtils.EMPTY);
            cff.setContractHolderName(isValid(obj[NumericConstants.THIRTEEN]) ? obj[NumericConstants.THIRTEEN].toString() : StringUtils.EMPTY);
            cff.setCustomerId(isValid(obj[NumericConstants.FOURTEEN]) ? obj[NumericConstants.FOURTEEN].toString() : StringUtils.EMPTY);
            cff.setCustomerNo(isValid(obj[NumericConstants.FIFTEEN]) ? obj[NumericConstants.FIFTEEN].toString() : StringUtils.EMPTY);
            cff.setCustomerName(isValid(obj[NumericConstants.SIXTEEN]) ? obj[NumericConstants.SIXTEEN].toString() : StringUtils.EMPTY);
            cff.setItemId(isValid(obj[NumericConstants.SEVENTEEN]) ? obj[NumericConstants.SEVENTEEN].toString() : StringUtils.EMPTY);
            cff.setItemNo(isValid(obj[NumericConstants.EIGHTEEN]) ? obj[NumericConstants.EIGHTEEN].toString() : StringUtils.EMPTY);
            cff.setItemName(isValid(obj[NumericConstants.NINETEEN]) ? obj[NumericConstants.NINETEEN].toString() : StringUtils.EMPTY);
            cff.setSalesDollars(isValid(obj[NumericConstants.TWENTY]) ? obj[NumericConstants.TWENTY].toString() : StringUtils.EMPTY);
            cff.setSalesUnits(isValid(obj[NumericConstants.TWENTY_ONE]) ? obj[NumericConstants.TWENTY_ONE].toString() : StringUtils.EMPTY);
            cff.setSalesInclusion(isValid(obj[NumericConstants.TWENTY_TWO]) ? obj[NumericConstants.TWENTY_TWO].toString() : StringUtils.EMPTY);
            cff.setDeductionId(isValid(obj[NumericConstants.TWENTY_THREE]) ? obj[NumericConstants.TWENTY_THREE].toString() : StringUtils.EMPTY);
            cff.setDeductionNo(isValid(obj[NumericConstants.TWENTY_FOUR]) ? obj[NumericConstants.TWENTY_FOUR].toString() : StringUtils.EMPTY);
            cff.setDeductionName(isValid(obj[NumericConstants.TWENTY_FIVE]) ? obj[NumericConstants.TWENTY_FIVE].toString() : StringUtils.EMPTY);
            cff.setDeductionCategory(isValid(obj[NumericConstants.TWENTY_SIX]) ? obj[NumericConstants.TWENTY_SIX].toString() : StringUtils.EMPTY);
            cff.setDeductionType(isValid(obj[NumericConstants.TWENTY_SEVEN]) ? obj[NumericConstants.TWENTY_SEVEN].toString() : StringUtils.EMPTY);
            cff.setDeductionProgram(isValid(obj[NumericConstants.TWENTY_EIGHT]) ? obj[NumericConstants.TWENTY_EIGHT].toString() : StringUtils.EMPTY);
            cff.setDeductionInclusion(isValid(obj[NumericConstants.TWENTY_NINE]) ? obj[NumericConstants.TWENTY_NINE].toString() : StringUtils.EMPTY);
            cff.setDeductionCategory1(isValid(obj[NumericConstants.THIRTY]) ? obj[NumericConstants.THIRTY].toString() : StringUtils.EMPTY);
            cff.setDeductionCategory2(isValid(obj[NumericConstants.THIRTY_ONE]) ? obj[NumericConstants.THIRTY_ONE].toString() : StringUtils.EMPTY);
            cff.setDeductionCategory3(isValid(obj[NumericConstants.THIRTY_TWO]) ? obj[NumericConstants.THIRTY_TWO].toString() : StringUtils.EMPTY);
            cff.setDeductionCategory4(isValid(obj[NumericConstants.THIRTY_THREE]) ? obj[NumericConstants.THIRTY_THREE].toString() : StringUtils.EMPTY);
            cff.setDeductionCategory5(isValid(obj[NumericConstants.THIRTY_FOUR]) ? obj[NumericConstants.THIRTY_FOUR].toString() : StringUtils.EMPTY);
            cff.setDeductionCategory6(isValid(obj[NumericConstants.THIRTY_FIVE]) ? obj[NumericConstants.THIRTY_FIVE].toString() : StringUtils.EMPTY);
            cff.setDeductionDollars(isValid(obj[NumericConstants.THIRTY_SIX]) ? obj[NumericConstants.THIRTY_SIX].toString() : StringUtils.EMPTY);
            cff.setDeductionRate(isValid(obj[NumericConstants.THIRTY_SEVEN]) ? obj[NumericConstants.THIRTY_SEVEN].toString() : StringUtils.EMPTY);
            cff.setDeductionPerUnit(isValid(obj[NumericConstants.THIRTY_EIGHT]) ? obj[NumericConstants.THIRTY_EIGHT].toString() : StringUtils.EMPTY);
            cff.setNetSalesDollar(isValid(obj[NumericConstants.THIRTY_NINE]) ? obj[NumericConstants.THIRTY_NINE].toString() : StringUtils.EMPTY);
            cff.setCogsAmount(isValid(obj[NumericConstants.FORTY]) ? obj[NumericConstants.FORTY].toString() : StringUtils.EMPTY);
            cff.setCogsPerUnit(isValid(obj[NumericConstants.FORTY_ONE]) ? obj[NumericConstants.FORTY_ONE].toString() : StringUtils.EMPTY);
            cff.setNetProfitDollars(isValid(obj[NumericConstants.FORTY_TWO]) ? obj[NumericConstants.FORTY_TWO].toString() : StringUtils.EMPTY);
            cff.setNetProfitPerUnit(isValid(obj[NumericConstants.FORTY_THREE]) ? obj[NumericConstants.FORTY_THREE].toString() : StringUtils.EMPTY);
            cff.setCompanyId(isValid(obj[NumericConstants.FORTY_FOUR]) ? obj[NumericConstants.FORTY_FOUR].toString() : StringUtils.EMPTY);
            cff.setCompanyNo(isValid(obj[NumericConstants.FORTY_FIVE]) ? obj[NumericConstants.FORTY_FIVE].toString() : StringUtils.EMPTY);
            cff.setCompanyName(isValid(obj[NumericConstants.FORTY_SIX]) ? obj[NumericConstants.FORTY_SIX].toString() : StringUtils.EMPTY);
            cff.setBusinessUnitId(isValid(obj[NumericConstants.FORTY_SEVEN]) ? obj[NumericConstants.FORTY_SEVEN].toString() : StringUtils.EMPTY);
            cff.setBusinessUnitNo(isValid(obj[NumericConstants.FORTY_EIGHT]) ? obj[NumericConstants.FORTY_EIGHT].toString() : StringUtils.EMPTY);
            cff.setBusinessUnitName(isValid(obj[NumericConstants.FORTY_NINE]) ? obj[NumericConstants.FORTY_NINE].toString() : StringUtils.EMPTY);
            cff.setFinancialForecastCreationDate(isValid(obj[NumericConstants.FIFTY]) ? obj[NumericConstants.FIFTY].toString() : StringUtils.EMPTY);
            cff.setFinancialForecastApprovalDate(isValid(obj[NumericConstants.FIFTY_ONE]) ? obj[NumericConstants.FIFTY_ONE].toString() : StringUtils.EMPTY);
            cff.setOutboundStatus(isValid(obj[NumericConstants.FIFTY_TWO]) ? obj[NumericConstants.FIFTY_TWO].toString() : StringUtils.EMPTY);
            cff.setOriginalBatchId(isValid(obj[NumericConstants.FIFTY_THREE]) ? obj[NumericConstants.FIFTY_THREE].toString() : StringUtils.EMPTY);
            cff.setCheckRecord(isValid(obj[NumericConstants.FIFTY_FOUR]) ? (Boolean) obj[NumericConstants.FIFTY_FOUR] : Boolean.FALSE);
            cff.setCffDetailsSid(isValid(obj[NumericConstants.FIFTY_FIVE]) ? Integer.valueOf(obj[NumericConstants.FIFTY_FIVE].toString()) : 0);
            cff.setRsModelSid(isValid(obj[NumericConstants.FIFTY_SIX]) ? Integer.valueOf(obj[NumericConstants.FIFTY_SIX].toString()) : 0);
            cff.setPeriodSid(isValid(obj[NumericConstants.FIFTY_SEVEN]) ? Integer.valueOf(obj[NumericConstants.FIFTY_SEVEN].toString()) : 0);
        }
        return cff;
    }
   
     private ArmOutboundDTO gtnOutbound(Object gtnObject) throws ParseException {
         try{
        SimpleDateFormat sdf = new SimpleDateFormat(MM_DD_YYYY);
        gtnDto = new ArmOutboundDTO();
        Object[] obj = (Object[]) gtnObject;
       gtnDto.setAdjustmentType(isValid(obj[0]) ?  obj[0].toString() : StringUtils.EMPTY);
        gtnDto.setAccountCategory(isValid(obj[NumericConstants.ONE]) ?  obj[NumericConstants.ONE].toString() : StringUtils.EMPTY);
        gtnDto.setAccountType(isValid(obj[NumericConstants.TWO]) ?  obj[NumericConstants.TWO].toString() : StringUtils.EMPTY);
        gtnDto.setAdjustmentLevel(isValid(obj[NumericConstants.THREE]) ?  obj[NumericConstants.THREE].toString() : StringUtils.EMPTY);
        gtnDto.setGlCompanyId(isValid(obj[NumericConstants.FOUR]) ?  obj[NumericConstants.FOUR].toString() : StringUtils.EMPTY);
        gtnDto.setGlCompanyNo(isValid(obj[NumericConstants.FIVE]) ?  obj[NumericConstants.FIVE].toString() : StringUtils.EMPTY);
        gtnDto.setGlCompanyName(isValid(obj[NumericConstants.SIX]) ?  obj[NumericConstants.SIX].toString() : StringUtils.EMPTY);
        gtnDto.setBusinessUnitId(isValid(obj[NumericConstants.SEVEN]) ?  obj[NumericConstants.SEVEN].toString() : StringUtils.EMPTY);
        gtnDto.setBusinessUnitNo(isValid(obj[NumericConstants.EIGHT]) ?  obj[NumericConstants.EIGHT].toString() : StringUtils.EMPTY);
        gtnDto.setBusinessUnitName(isValid(obj[NumericConstants.NINE]) ?  obj[NumericConstants.NINE].toString() : StringUtils.EMPTY);
        gtnDto.setAccount(isValid(obj[NumericConstants.TEN]) ?  obj[NumericConstants.TEN].toString() : StringUtils.EMPTY);
        gtnDto.setGlYear(isValid(obj[NumericConstants.ELEVEN]) ? Integer.valueOf(obj[NumericConstants.ELEVEN].toString()) : 0);
        gtnDto.setGlMonth(isValid(obj[NumericConstants.TWELVE]) ? Integer.valueOf(obj[NumericConstants.TWELVE].toString()) : 0);
             if (obj[NumericConstants.THIRTEEN] != null) {
            gtnDto.setGlDate(sdf.parse( String.valueOf(obj[NumericConstants.THIRTEEN])));
             }
         if (obj[NumericConstants.FOURTEEN] != null) {
            gtnDto.setAdjustmentCreatedDate(sdf.parse(String.valueOf(obj[NumericConstants.FOURTEEN]))); // GAL-8610
        }
        gtnDto.setDeductionAmount(isValid(obj[NumericConstants.FIFTEEN]) ?  obj[NumericConstants.FIFTEEN].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionRate(isValid(obj[NumericConstants.SIXTEEN]) ?  obj[NumericConstants.SIXTEEN].toString() : StringUtils.EMPTY);
         if (obj[NumericConstants.SEVENTEEN] != null) {
            gtnDto.setRedemptionPeriod(sdf.parse(String.valueOf(obj[NumericConstants.SEVENTEEN])));// GAL-8610
        }
        gtnDto.setContractId(isValid(obj[NumericConstants.EIGHTEEN]) ?  obj[NumericConstants.EIGHTEEN].toString() : StringUtils.EMPTY);
        gtnDto.setContractNo(isValid(obj[NumericConstants.NINETEEN]) ?  obj[NumericConstants.NINETEEN].toString() : StringUtils.EMPTY);
        gtnDto.setContractName(isValid(obj[NumericConstants.TWENTY]) ?  obj[NumericConstants.TWENTY].toString() : StringUtils.EMPTY);
        gtnDto.setCompanyId(isValid(obj[NumericConstants.TWENTY_ONE]) ?  obj[NumericConstants.TWENTY_ONE].toString() : StringUtils.EMPTY);
        gtnDto.setCompanyNo(isValid(obj[NumericConstants.TWENTY_TWO]) ?  obj[NumericConstants.TWENTY_TWO].toString() : StringUtils.EMPTY);
        gtnDto.setCompanyName(isValid(obj[NumericConstants.TWENTY_THREE]) ?  obj[NumericConstants.TWENTY_THREE].toString() : StringUtils.EMPTY);
        gtnDto.setItemId(isValid(obj[NumericConstants.TWENTY_FOUR]) ?  obj[NumericConstants.TWENTY_FOUR].toString() : StringUtils.EMPTY);
        gtnDto.setItemNo(isValid(obj[NumericConstants.TWENTY_FIVE]) ?  obj[NumericConstants.TWENTY_FIVE].toString() : StringUtils.EMPTY);
        gtnDto.setItemName(isValid(obj[NumericConstants.TWENTY_SIX]) ?  obj[NumericConstants.TWENTY_SIX].toString() : StringUtils.EMPTY);
        gtnDto.setBrandId(isValid(obj[NumericConstants.TWENTY_SEVEN]) ?  obj[NumericConstants.TWENTY_SEVEN].toString() : StringUtils.EMPTY);
        gtnDto.setBrandName(isValid(obj[NumericConstants.TWENTY_EIGHT]) ?  obj[NumericConstants.TWENTY_EIGHT].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionId(isValid(obj[NumericConstants.TWENTY_NINE]) ?  obj[NumericConstants.TWENTY_NINE].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionNo(isValid(obj[NumericConstants.THIRTY]) ?  obj[NumericConstants.THIRTY].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionName(isValid(obj[NumericConstants.THIRTY_ONE]) ?  obj[NumericConstants.THIRTY_ONE].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionCategory(isValid(obj[NumericConstants.THIRTY_TWO]) ?  obj[NumericConstants.THIRTY_TWO].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionType(isValid(obj[NumericConstants.THIRTY_THREE]) ?  obj[NumericConstants.THIRTY_THREE].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionProgram(isValid(obj[NumericConstants.THIRTY_FOUR]) ?  obj[NumericConstants.THIRTY_FOUR].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionInclusion(isValid(obj[NumericConstants.THIRTY_FIVE]) ?  obj[NumericConstants.THIRTY_FIVE].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionUdc1(isValid(obj[NumericConstants.THIRTY_SIX]) ?  obj[NumericConstants.THIRTY_SIX].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionUdc2(isValid(obj[NumericConstants.THIRTY_SEVEN]) ?  obj[NumericConstants.THIRTY_SEVEN].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionUdc3(isValid(obj[NumericConstants.THIRTY_EIGHT]) ?  obj[NumericConstants.THIRTY_EIGHT].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionUdc4(isValid(obj[NumericConstants.THIRTY_NINE]) ?  obj[NumericConstants.THIRTY_NINE].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionUdc5(isValid(obj[NumericConstants.FORTY]) ?  obj[NumericConstants.FORTY].toString() : StringUtils.EMPTY);
        gtnDto.setDeductionUdc6(isValid(obj[NumericConstants.FORTY_ONE]) ?  obj[NumericConstants.FORTY_ONE].toString() : StringUtils.EMPTY);
        gtnDto.setOriginalBatchId(isValid(obj[NumericConstants.FORTY_TWO]) ?  obj[NumericConstants.FORTY_TWO].toString() : StringUtils.EMPTY);
        gtnDto.setWorkflowId(isValid(obj[NumericConstants.FORTY_THREE]) ?  obj[NumericConstants.FORTY_THREE].toString() : StringUtils.EMPTY);
        gtnDto.setWorkflowName(isValid(obj[NumericConstants.FORTY_FOUR]) ?  obj[NumericConstants.FORTY_FOUR].toString() : StringUtils.EMPTY);
        gtnDto.setWorkflowCreatedBy(isValid(obj[NumericConstants.FORTY_FIVE]) ?  obj[NumericConstants.FORTY_FIVE].toString() : StringUtils.EMPTY);
        if (obj[NumericConstants.FORTY_SIX] != null) {
            gtnDto.setWorkflowCreatedDate(sdf.parse(String.valueOf(obj[NumericConstants.FORTY_SIX])));// GAL-8610
        }
         gtnDto.setWorkflowApprovedBy(isValid(obj[NumericConstants.FORTY_SEVEN]) ?  obj[NumericConstants.FORTY_SEVEN].toString() : StringUtils.EMPTY);
         if (obj[NumericConstants.FORTY_EIGHT] != null) {
            gtnDto.setWorkflowApprovedDate(sdf.parse(String.valueOf(obj[NumericConstants.FORTY_EIGHT])));// GAL-8610
        }
        gtnDto.setCostCenter(isValid(obj[NumericConstants.FORTY_NINE]) ?  obj[NumericConstants.FORTY_NINE].toString() : StringUtils.EMPTY);
        gtnDto.setProject(isValid(obj[NumericConstants.FIFTY]) ?  obj[NumericConstants.FIFTY].toString() : StringUtils.EMPTY);
        gtnDto.setFuture1(isValid(obj[NumericConstants.FIFTY_ONE]) ?  obj[NumericConstants.FIFTY_ONE].toString() : StringUtils.EMPTY);
        gtnDto.setFuture2(isValid(obj[NumericConstants.FIFTY_TWO]) ?  obj[NumericConstants.FIFTY_TWO].toString() : StringUtils.EMPTY);
        gtnDto.setUdc1(isValid(obj[NumericConstants.FIFTY_THREE]) ?  obj[NumericConstants.FIFTY_THREE].toString() : StringUtils.EMPTY);
        gtnDto.setUdc2(isValid(obj[NumericConstants.FIFTY_FOUR]) ?  obj[NumericConstants.FIFTY_FOUR].toString() : StringUtils.EMPTY);
        gtnDto.setUdc3(isValid(obj[NumericConstants.FIFTY_FIVE]) ?  obj[NumericConstants.FIFTY_FIVE].toString() : StringUtils.EMPTY);
        gtnDto.setUdc4(isValid(obj[NumericConstants.FIFTY_SIX]) ?  obj[NumericConstants.FIFTY_SIX].toString() : StringUtils.EMPTY);
        gtnDto.setUdc5(isValid(obj[NumericConstants.FIFTY_SEVEN]) ?  obj[NumericConstants.FIFTY_SEVEN].toString() : StringUtils.EMPTY);
        gtnDto.setUdc6(isValid(obj[NumericConstants.FIFTY_EIGHT]) ?  obj[NumericConstants.FIFTY_EIGHT].toString() : StringUtils.EMPTY);
        gtnDto.setPostingIndicator(isValid(obj[NumericConstants.FIFTY_NINE]) ?  obj[NumericConstants.FIFTY_NINE].toString() : StringUtils.EMPTY);
        if (obj[NumericConstants.SIXTY] != null) {
            gtnDto.setCalculationPeriod(sdf.parse(String.valueOf(obj[NumericConstants.SIXTY])));// GAL-8610
        }
        
        if (obj[NumericConstants.SIXTY_ONE] != null) {
            Boolean flag = (Boolean) obj[NumericConstants.SIXTY_ONE];
            if (flag) {
                gtnDto.setCheckRecord(true);
            } else {
                gtnDto.setCheckRecord(false);
            }
        } else {
            gtnDto.setCheckRecord(false);
        }
         }catch(Exception e){
            LOGGER.error(e);
         }
        return gtnDto;
    }
    
      
      private ArmOutboundDTO reserveOutbound(Object gtnObject) throws ParseException {
         try{
             SimpleDateFormat sdf = new SimpleDateFormat(MM_DD_YYYY);// GAL-8610
        gtnDto = new ArmOutboundDTO();
        Object[] obj = (Object[]) gtnObject;
        gtnDto.setAdjustmentType(isValid(obj[0]) ?  obj[0].toString() : StringUtils.EMPTY);
        gtnDto.setDivision(isValid(obj[NumericConstants.ONE]) ?  obj[NumericConstants.ONE].toString() : StringUtils.EMPTY);
        gtnDto.setBusinessUnitId(isValid(obj[NumericConstants.TWO]) ?  obj[NumericConstants.TWO].toString() : StringUtils.EMPTY);
        gtnDto.setAccount(isValid(obj[NumericConstants.THREE]) ?  obj[NumericConstants.THREE].toString() : StringUtils.EMPTY);
        gtnDto.setJournalName(isValid(obj[NumericConstants.FOUR]) ?  obj[NumericConstants.FOUR].toString() : StringUtils.EMPTY);
        gtnDto.setJournalDescription(isValid(obj[NumericConstants.FIVE]) ?  obj[NumericConstants.FIVE].toString() : StringUtils.EMPTY);
        gtnDto.setBrand(isValid(obj[NumericConstants.SIX]) ?  obj[NumericConstants.SIX].toString() : StringUtils.EMPTY);
        gtnDto.setDebit(isValid(obj[NumericConstants.SEVEN]) ?  obj[NumericConstants.SEVEN].toString() : StringUtils.EMPTY);
        gtnDto.setCredit(isValid(obj[NumericConstants.EIGHT]) ?  obj[NumericConstants.EIGHT].toString() : StringUtils.EMPTY);
        gtnDto.setCurrency(isValid(obj[NumericConstants.NINE]) ?  obj[NumericConstants.NINE].toString() : StringUtils.EMPTY);
        if (obj[NumericConstants.TEN] != null) {
            gtnDto.setAccountingDate(sdf.parse(String.valueOf(obj[NumericConstants.TEN])));// GAL-8610
        }
         
         if (obj[NumericConstants.ELEVEN] != null) {
            gtnDto.setRedemptionPeriod(sdf.parse(String.valueOf(obj[NumericConstants.ELEVEN])));// GAL-8610
        }
         gtnDto.setPostingIndicator(isValid(obj[NumericConstants.TWELVE]) ?  obj[NumericConstants.TWELVE].toString() : StringUtils.EMPTY);
        gtnDto.setCostCenter(isValid(obj[NumericConstants.THIRTEEN]) ?  obj[NumericConstants.THIRTEEN].toString() : StringUtils.EMPTY);
        gtnDto.setProject(isValid(obj[NumericConstants.FOURTEEN]) ?  obj[NumericConstants.FOURTEEN].toString() : StringUtils.EMPTY);
        gtnDto.setFuture1(isValid(obj[NumericConstants.FIFTEEN]) ?  obj[NumericConstants.FIFTEEN].toString() : StringUtils.EMPTY);
        gtnDto.setFuture2(isValid(obj[NumericConstants.SIXTEEN]) ?  obj[NumericConstants.SIXTEEN].toString() : StringUtils.EMPTY);
        gtnDto.setCategory(isValid(obj[NumericConstants.SEVENTEEN]) ?  obj[NumericConstants.SEVENTEEN].toString() : StringUtils.EMPTY);
        gtnDto.setBalanceType(isValid(obj[NumericConstants.EIGHTEEN]) ?  obj[NumericConstants.EIGHTEEN].toString() : StringUtils.EMPTY);
        gtnDto.setDatabase(isValid(obj[NumericConstants.NINETEEN]) ?  obj[NumericConstants.NINETEEN].toString() : StringUtils.EMPTY);
        gtnDto.setDataAccessSet(isValid(obj[NumericConstants.TWENTY]) ?  obj[NumericConstants.TWENTY].toString() : StringUtils.EMPTY);
        gtnDto.setChartOfAccounts(isValid(obj[NumericConstants.TWENTY_ONE]) ?  obj[NumericConstants.TWENTY_ONE].toString() : StringUtils.EMPTY);
        gtnDto.setLedger(isValid(obj[NumericConstants.TWENTY_TWO]) ?  obj[NumericConstants.TWENTY_TWO].toString() : StringUtils.EMPTY);
        gtnDto.setSource(isValid(obj[NumericConstants.TWENTY_THREE]) ?  obj[NumericConstants.TWENTY_THREE].toString() : StringUtils.EMPTY);
        gtnDto.setBatchName(isValid(obj[NumericConstants.TWENTY_FOUR]) ?  obj[NumericConstants.TWENTY_FOUR].toString() : StringUtils.EMPTY);
        gtnDto.setBatchId(isValid(obj[NumericConstants.TWENTY_FIVE]) ?  obj[NumericConstants.TWENTY_FIVE].toString() : StringUtils.EMPTY);
        gtnDto.setReverseJournal(isValid(obj[NumericConstants.TWENTY_SIX]) ?  obj[NumericConstants.TWENTY_SIX].toString() : StringUtils.EMPTY);
        if (obj[NumericConstants.TWENTY_SEVEN] != null) {
            gtnDto.setReversalPeriodDate(sdf.parse(String.valueOf(obj[NumericConstants.TWENTY_SEVEN])));// GAL-8610
        }
        gtnDto.setLineDescription(isValid(obj[NumericConstants.TWENTY_EIGHT]) ?  obj[NumericConstants.TWENTY_EIGHT].toString() : StringUtils.EMPTY);
        gtnDto.setUdc1(isValid(obj[NumericConstants.TWENTY_NINE]) ?  obj[NumericConstants.TWENTY_NINE].toString() : StringUtils.EMPTY);
        gtnDto.setUdc2(isValid(obj[NumericConstants.THIRTY]) ?  obj[NumericConstants.THIRTY].toString() : StringUtils.EMPTY);
        gtnDto.setUdc3(isValid(obj[NumericConstants.THIRTY_ONE]) ?  obj[NumericConstants.THIRTY_ONE].toString() : StringUtils.EMPTY);
        gtnDto.setUdc4(isValid(obj[NumericConstants.THIRTY_TWO]) ?  obj[NumericConstants.THIRTY_TWO].toString() : StringUtils.EMPTY);
        gtnDto.setUdc5(isValid(obj[NumericConstants.THIRTY_THREE]) ?  obj[NumericConstants.THIRTY_THREE].toString() : StringUtils.EMPTY);
        gtnDto.setUdc6(isValid(obj[NumericConstants.THIRTY_FOUR]) ?  obj[NumericConstants.THIRTY_FOUR].toString() : StringUtils.EMPTY);
        gtnDto.setAccountCategory(isValid(obj[NumericConstants.THIRTY_FIVE]) ?  obj[NumericConstants.THIRTY_FIVE].toString() : StringUtils.EMPTY);
        gtnDto.setAccountType(isValid(obj[NumericConstants.THIRTY_SIX]) ?  obj[NumericConstants.THIRTY_SIX].toString() : StringUtils.EMPTY);
        gtnDto.setAdjustmentLevel(isValid(obj[NumericConstants.THIRTY_SEVEN]) ?  obj[NumericConstants.THIRTY_SEVEN].toString() : StringUtils.EMPTY);
         if (obj[NumericConstants.THIRTY_EIGHT] != null) {
            gtnDto.setAdjustmentCreatedDate(sdf.parse(String.valueOf(obj[NumericConstants.THIRTY_EIGHT])));// GAL-8610
        }
         gtnDto.setOriginalBatchId(isValid(obj[NumericConstants.THIRTY_NINE]) ?  obj[NumericConstants.THIRTY_NINE].toString() : StringUtils.EMPTY);
        gtnDto.setWorkflowId(isValid(obj[NumericConstants.FORTY]) ?  obj[NumericConstants.FORTY].toString() : StringUtils.EMPTY);
        gtnDto.setWorkflowName(isValid(obj[NumericConstants.FORTY_ONE]) ?  obj[NumericConstants.FORTY_ONE].toString() : StringUtils.EMPTY);
         gtnDto.setWorkflowCreatedBy(isValid(obj[NumericConstants.FORTY_TWO]) ?  obj[NumericConstants.FORTY_TWO].toString() : StringUtils.EMPTY);
        if (obj[NumericConstants.FORTY_THREE] != null) {
            gtnDto.setWorkflowCreatedDate(sdf.parse(String.valueOf(obj[NumericConstants.FORTY_THREE])));// GAL-8610
        }
      gtnDto.setWorkflowApprovedBy(isValid(obj[NumericConstants.FORTY_FOUR]) ?  obj[NumericConstants.FORTY_FOUR].toString() : StringUtils.EMPTY);  
         if (obj[NumericConstants.FORTY_FIVE] != null) {
            gtnDto.setWorkflowApprovedDate(sdf.parse(String.valueOf(obj[NumericConstants.FORTY_FIVE])));// GAL-8610
        }
         if (obj[NumericConstants.FORTY_SIX] != null) {
            gtnDto.setCalculationPeriod(sdf.parse(String.valueOf(obj[NumericConstants.FORTY_SIX])));// GAL-8610
        }
         if (obj[NumericConstants.FORTY_SEVEN] != null) {
            Boolean flag = (Boolean) obj[NumericConstants.FORTY_SEVEN];
            if (flag) {
                gtnDto.setCheckRecord(true);
            } else {
                gtnDto.setCheckRecord(false);
            }
        } else {
            gtnDto.setCheckRecord(false);
        }
           gtnDto.setGlCompanyName(isValid(obj[NumericConstants.FORTY_EIGHT]) ?  obj[NumericConstants.FORTY_EIGHT].toString() : StringUtils.EMPTY);
         }catch(Exception e){
             LOGGER.error(e);
         }
        return gtnDto;
    }

    
   private boolean isValid(Object obj) {
        if (obj == null || String.valueOf(obj).isEmpty() || "null".equals(String.valueOf(obj))) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
}
    }

    public boolean isIsFirstSearch() {
        return isFirstSearch;
    }

    public void setIsFirstSearch(boolean isFirstSearch) {
        this.isFirstSearch = isFirstSearch;
    }
    
    public void setIsFirstTimeSearch(boolean isFirstTimeSearch) {
        this.isFirstTimeSearch = isFirstTimeSearch;
}
    public int getArpCount() {
        return arpCount;
    }

    public void setArpCount(int arpCount) {
        this.arpCount = arpCount;
    }
    public double getRecordCount(){
        return super.getRecordCount();
    }
    
}
