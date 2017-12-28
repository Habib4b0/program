package com.stpl.app.security.udc.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import com.stpl.app.security.dao.UdcLogicDAO;
import com.stpl.app.security.dao.impl.UdcLogicDAOImpl;
import com.stpl.app.security.udc.dto.BrandMasterDTO;
import com.stpl.app.security.udc.dto.HelperForm;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.NotificationUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.IndexedContainer;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.jboss.logging.Logger;

public class UdcLogic {

    private static final Logger LOGGER = Logger
            .getLogger(UdcLogic.class.getName());
    private static final String CATEGORY = "CategoryName";
    private static final String BRAND_NAME = "brandName";
    private static final String DISPLAY_BRAND = "displayBrand";
    private static final String BRAND_ID = "brand_id";
    UdcLogicDAO dao = new UdcLogicDAOImpl();

    public Container getListNames() {

        List<HelperTable> list = null;
        List<String> helperList = new ArrayList<String>();
        try {
            DynamicQuery helperQuery = HelperTableLocalServiceUtil.dynamicQuery();
            helperQuery.add(RestrictionsFactoryUtil.like("listName",
                    CATEGORY));
            helperQuery.addOrder(OrderFactoryUtil.asc(CommonUtils.DESCRIPTION));
            list = dao.getListName(helperQuery);
        } catch (SystemException e) {
            LOGGER.error(e);
        }
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(helperTable.getDescription());
            }

        }
        helperList.add("CategoryName");
        helperList.add("BRAND");
        return new IndexedContainer(helperList);

    }

    public List<HelperForm> getDescrition(String listName) {

        List<HelperTable> list = null;
        List<HelperForm> helperList = new ArrayList<HelperForm>();
        try {
            list = dao.getDescrition(listName);
        } catch (SystemException e) {

            LOGGER.error(e);
        }
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                HelperForm helperForm = new HelperForm();

                HelperTable helperTable = (HelperTable) list.get(i);
                helperForm.setHelperTableSid(helperTable.getHelperTableSid());
                helperForm.setListName(helperTable.getListName());
                helperForm.setDescription(helperTable.getDescription());
                helperList.add(helperForm);
            }
        }
        return helperList;

    }
    
    public List<HelperForm> getFileTypeDescription(String listName) {

        List<HelperTable> list = null;
        List<HelperForm> helperList = new ArrayList<HelperForm>();
        try {
            list = dao.getDescrition(listName);
        } catch (SystemException e) {

            LOGGER.error(e);
        }
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                HelperForm helperForm = new HelperForm();

                HelperTable helperTable = (HelperTable) list.get(i);
                helperForm.setHelperTableSid(helperTable.getHelperTableSid());
                helperForm.setListName(helperTable.getListName());
                helperForm.setAliasName(helperTable.getAliasName());
                helperForm.setDescription(helperTable.getDescription());
                helperList.add(helperForm);
            }
        }
        return helperList;

    }

    public String SaveHelperTable(ErrorfulFieldGroup helperForm) {
        HelperTable helperTable = HelperTableLocalServiceUtil.createHelperTable(0);
        List<HelperTable> list;
        int count = 0;
        Date date = new Date();
        try {
            if (helperForm.getField(CommonUtils.CATEGORY) != null && helperForm.getField(CommonUtils.CATEGORY).getValue() != null
                    && helperForm.getField(CommonUtils.DESCRIPTION).getValue() != null && helperForm.getField(CommonUtils.DESCRIPTION).getValue().toString() != "") {

                helperTable.setListName(helperForm.getField(CommonUtils.CATEGORY).getValue().toString());

                helperTable.setDescription(helperForm.getField(CommonUtils.DESCRIPTION)
                        .getValue().toString());
                helperTable.setRefCount(0);
                helperTable.setCreatedBy(0);
                helperTable.setModifiedBy(0);
                helperTable.setCreatedDate(date);
                helperTable.setModifiedDate(date);

                list = dao.findByHelperTableDetails(helperForm.getField(CommonUtils.CATEGORY).getValue().toString());
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDescription().equalsIgnoreCase(helperTable.getDescription())) {
                        count++;
                    }
                }

                if (count == 0) {
                    dao.saveHelperTableDetails(helperTable);
                    return CommonUtils.SUCCESS;
                }

            }

            return "fail";
        } catch (Exception e) {
            LOGGER.debug("exception occured--->" + e);
            return "fail";
        }

    }
    
    public String SaveFileTypeHelperTable(ErrorfulFieldGroup helperForm) {
        HelperTable helperTable = HelperTableLocalServiceUtil.createHelperTable(0);
        List<HelperTable> list;
        int count = 0;
        Date date = new Date();
        try {
            if (helperForm.getField(CommonUtils.CATEGORY) != null && helperForm.getField(CommonUtils.CATEGORY).getValue() != null
                    && helperForm.getField(CommonUtils.DESCRIPTION).getValue() != null && helperForm.getField(CommonUtils.DESCRIPTION).getValue().toString() != "") {

                helperTable.setListName(helperForm.getField(CommonUtils.CATEGORY).getValue().toString());

                helperTable.setDescription(helperForm.getField(CommonUtils.DESCRIPTION)
                        .getValue().toString());
                 helperTable.setAliasName(helperForm.getField(CommonUtils.ALIASNAME)
                        .getValue().toString());
                helperTable.setRefCount(0);
                helperTable.setCreatedBy(0);
                helperTable.setModifiedBy(0);
                helperTable.setCreatedDate(date);
                helperTable.setModifiedDate(date);

                list = dao.findByHelperTableDetails(helperForm.getField(CommonUtils.CATEGORY).getValue().toString());
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDescription().equalsIgnoreCase(helperTable.getDescription())) {
                        count++;
                    }
                }

                if (count == 0) {
                    dao.saveHelperTableDetails(helperTable);
                    return CommonUtils.SUCCESS;
                }

            }

            return "fail";
        } catch (Exception e) {
            LOGGER.debug("exception occured--->" + e);
            return "fail";
        }

    }

    public String saveBrandMaster(ErrorfulFieldGroup brandForm, int masterSid) {
        BrandMaster brandTable = BrandMasterLocalServiceUtil.createBrandMaster(0);
        Date date = new Date();
        try {
           LOGGER.debug("=======brand id====" +brandForm.getField(CommonUtils.BRAND_ID).getValue());
            if (brandForm.getField(CommonUtils.CATEGORY) != null && brandForm.getField(CommonUtils.CATEGORY).getValue() != null
                    && brandForm.getField(CommonUtils.BRAND_ID).getValue() != null && !StringUtils.EMPTY.equals(brandForm.getField(CommonUtils.BRAND_ID).getValue().toString())) {
               LOGGER.debug("=====inside if=====");
                String brandId = brandForm.getField(CommonUtils.BRAND_ID).getValue().toString();
                String brandName =StringUtils.EMPTY;
                String displayBrand = StringUtils.EMPTY;

                brandTable.setBrandId(brandForm.getField(CommonUtils.BRAND_ID).getValue().toString());
                if (!StringUtils.EMPTY.equals(brandForm.getField(BRAND_NAME).getValue().toString())) {
                    brandName = brandForm.getField(BRAND_NAME)
                            .getValue().toString();
                    brandTable.setBrandName(brandName);
                }
                if (!StringUtils.EMPTY.equals(brandForm.getField(DISPLAY_BRAND).getValue().toString())) {
                    displayBrand = brandForm.getField(DISPLAY_BRAND)
                            .getValue().toString();
                    brandTable.setDisplayBrand(displayBrand);
                }
                brandTable.setCreatedBy(0);
                brandTable.setModifiedBy(0);
                brandTable.setCreatedDate(date);
                brandTable.setModifiedDate(date);
                brandTable.setInboundStatus("A");
                String query = " select BRAND_MASTER_SID, BRAND_ID,BRAND_NAME,DISPLAY_BRAND FROM BRAND_MASTER WHERE BRAND_ID='" + brandId
                        + "' AND INBOUND_STATUS IN ('D') "
                        + " AND RECORD_LOCK_STATUS = '0'";
                List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (!list.isEmpty() && list.size() > 0) {
                    for (Object object : list) {
                        Object[] row = (Object[]) object;
                        BrandMaster brand = dao.getBrandMaster(row[0] != null ? Integer.valueOf(String.valueOf(row[0])) : 0);
                        brand.setInboundStatus("A");
                        brand.setBrandName(brandName);
                        brand.setDisplayBrand(displayBrand);
                        dao.deleteBrandTableByCode(brand);
                        return CommonUtils.SUCCESS;
                    }

                } else {
                    String saveQuery = " select BRAND_MASTER_SID, BRAND_ID,BRAND_NAME,DISPLAY_BRAND FROM BRAND_MASTER WHERE BRAND_ID='" + brandId +"' AND INBOUND_STATUS <> ('D')";
                    List savedList = (List) HelperTableLocalServiceUtil.executeSelectQuery(saveQuery);
                    if (!savedList.isEmpty() && savedList.size() > 0) {
                        NotificationUtils.getWarningNotification("Duplicate", "Brand ID is already exist");
                        return "";
                    } else {
                        dao.saveBrandTableDetails(brandTable);
                        return CommonUtils.SUCCESS;
                    }
                }
                
            } else if (brandForm.getField(CommonUtils.CATEGORY) != null && brandForm.getField(CommonUtils.CATEGORY).getValue() != null
                    && StringUtils.EMPTY.equals(brandForm.getField(CommonUtils.BRAND_ID).getValue().toString())) {
               LOGGER.debug("=====else if=======");
                NotificationUtils.getErrorNotification("Error", "Brand ID should be present");
                return "";
            }

            return "fail";
        } catch (Exception e) {
            LOGGER.error(e);
            return "fail";
        }

    }

    public HelperTable deleteHelperTableByCode(int code) {
        HelperTable helperTable = null;

        try {
            helperTable = dao.deleteHelperTableByCode(code);

        } catch (RuntimeException e) {
            AbstractNotificationUtils.getErrorNotification("Error", "This Value is already in use hence it cannot be deleted");
            LOGGER.error(e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return helperTable;
    }

    /**
     *
     * @param categoryValue
     * @return
     */
    public int brandCount(String categoryValue) {
        String query = " select count(*) from brand_master where inbound_status <> 'D'";
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        int count = Integer.valueOf(String.valueOf(list.get(0)));
        return count;
    }

    /**
     *
     */
    public List<BrandMasterDTO> brandFind(String categoryValue, int startIndex, int offset,final List<OrderByColumn> columns) {
    	String orderQuery = getOrderByStatement(columns);
        String query = " select brand_id,BRAND_NAME,DISPLAY_BRAND, BRAND_MASTER_SID from brand_master where inbound_status <> 'D' "+orderQuery+" OFFSET " + startIndex + "ROWS FETCH NEXT " + offset + " ROWS ONLY";
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        List<BrandMasterDTO> finalList = getCustomizedBrandResults(list, categoryValue);
        return finalList;
    }

    private String getOrderByStatement(final List<OrderByColumn> columns) {
    	StringBuilder orderBuilder = new StringBuilder( "order by "); //
    	Map<String,String> columnIdMap = getBrandColumnIdMap();
    	if(columns != null && !columns.isEmpty()) {
    		for (OrderByColumn orderByColumn : columns) {
    			orderBuilder.append(" ").append(columnIdMap.get(orderByColumn.getName())).append(" ").append(orderByColumn.getType()).append(",");
			}
    		
    		return orderBuilder.toString().substring(0, orderBuilder.length()-1);
    	}
		return orderBuilder.append(BRAND_ID).toString();
				
		
	}

	private Map<String, String> getBrandColumnIdMap() {
		Map<String,String> columnIdMap = new HashMap<>();
		columnIdMap.put("brandId", BRAND_ID);
		columnIdMap.put(BRAND_NAME, "BRAND_NAME");
		columnIdMap.put(DISPLAY_BRAND, "DISPLAY_BRAND");
		columnIdMap.put("category", BRAND_ID);
		return columnIdMap;
	}

	public List<BrandMasterDTO> getCustomizedBrandResults(final List list, String categoryValue) {
        List<BrandMasterDTO> results = new ArrayList<BrandMasterDTO>();
        for (int i = 0; i < list.size(); i++) {
            BrandMasterDTO brandDto = new BrandMasterDTO();
            Object[] obj = (Object[]) list.get(i);
            brandDto.setBrandId(obj[0] != null ? String.valueOf(obj[0].toString()) : "");
            brandDto.setBrandName(obj[NumericConstants.ONE] != null ? String.valueOf(obj[NumericConstants.ONE].toString()) : "");
            brandDto.setDisplayBrand(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO].toString()) : "");
            brandDto.setBrandMasterSid(obj[NumericConstants.THREE] != null ? Integer.valueOf(obj[NumericConstants.THREE].toString()) : 0);
            brandDto.setCategory(categoryValue);
            results.add(brandDto);
        }
        return results;
    }

    public BrandMaster deleteBrandTableByCode(BrandMaster code) {
        BrandMaster brandTable = null;

        try {
            brandTable = dao.deleteBrandTableByCode(code);

            return brandTable;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return brandTable;
    }
}
