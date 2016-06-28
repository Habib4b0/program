package com.stpl.app.security.udc.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.security.dao.UdcLogicDAO;
import com.stpl.app.security.dao.impl.UdcLogicDAOImpl;
import com.stpl.app.security.udc.dto.BrandMasterDTO;
import com.stpl.app.security.udc.dto.HelperForm;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.NotificationUtils;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;

public class UdcLogic {

    private static final Logger LOGGER = LogManager
            .getLogger(UdcLogic.class.getName());
    private static final String CATEGORY = "CategoryName";
    UdcLogicDAO dao = new UdcLogicDAOImpl();

    public Container getListNames() {

        List<HelperTable> list = null;
        List<String> helperList = new ArrayList<String>();
        try {
            DynamicQuery helperQuery = DynamicQueryFactoryUtil
                    .forClass(HelperTable.class);
            helperQuery.add(RestrictionsFactoryUtil.like("listName",
                    CATEGORY));
            helperQuery.addOrder(OrderFactoryUtil.asc("description"));
            list = dao.getListName(helperQuery);
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());
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

            LOGGER.error(e.getMessage());
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

    public String SaveHelperTable(ErrorfulFieldGroup helperForm) {
        HelperTable helperTable = HelperTableLocalServiceUtil.createHelperTable(0);

        HelperTable result = null;
        List<HelperTable> list;
        int count = 0;
        Date date = new Date();
        try {
            if (helperForm.getField("category") != null && helperForm.getField("category").getValue() != null
                    && helperForm.getField("description").getValue() != null && helperForm.getField("description").getValue().toString() != "") {

                helperTable.setListName(helperForm.getField("category").getValue().toString());

                helperTable.setDescription(helperForm.getField("description")
                        .getValue().toString());
                helperTable.setRefCount(0);
                helperTable.setCreatedBy(0);
                helperTable.setModifiedBy(0);
                helperTable.setCreatedDate(date);
                helperTable.setModifiedDate(date);

                list = dao.findByHelperTableDetails(helperForm.getField("category").getValue().toString());
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDescription().equalsIgnoreCase(helperTable.getDescription())) {
                        count++;
                    }
                }

                if (count == 0) {
                    result = dao.saveHelperTableDetails(helperTable);
                    return "success";
                }

            }

            return "fail";
        } catch (Exception e) {
            LOGGER.info("exception occured--->" + e.getMessage());
            return "fail";
        }

    }

    public String saveBrandMaster(ErrorfulFieldGroup brandForm, int masterSid) {
        BrandMaster brandTable = BrandMasterLocalServiceUtil.createBrandMaster(0);

        BrandMaster result = null;
        Date date = new Date();
        try {
           LOGGER.info("=======brand id====" +brandForm.getField("brandId").getValue());
            if (brandForm.getField("category") != null && brandForm.getField("category").getValue() != null
                    && brandForm.getField("brandId").getValue() != null && !StringUtils.EMPTY.equals(brandForm.getField("brandId").getValue().toString())) {
               LOGGER.info("=====inside if=====");
                String brandId = brandForm.getField("brandId").getValue().toString();
                String brandName =StringUtils.EMPTY;
                String displayBrand = StringUtils.EMPTY;

                brandTable.setBrandId(brandForm.getField("brandId").getValue().toString());
                if (!StringUtils.EMPTY.equals(brandForm.getField("brandName").getValue().toString())) {
                    brandName = brandForm.getField("brandName")
                            .getValue().toString();
                    brandTable.setBrandName(brandName);
                }
                if (!StringUtils.EMPTY.equals(brandForm.getField("displayBrand").getValue().toString())) {
                    displayBrand = brandForm.getField("displayBrand")
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
                List list = (List) BrandMasterLocalServiceUtil.executeSelectQuery(query, null, null);
                if (!list.isEmpty() && list.size() > 0) {
                    for (Object object : list) {
                        Object[] row = (Object[]) object;
                        BrandMaster brand = dao.getBrandMaster(row[0] != null ? Integer.valueOf(String.valueOf(row[0])) : 0);
                        brand.setInboundStatus("A");
                        brand.setBrandName(brandName);
                        brand.setDisplayBrand(displayBrand);
                        dao.deleteBrandTableByCode(brand);
                        return "success";
                    }

                } else {
                    String saveQuery = " select BRAND_MASTER_SID, BRAND_ID,BRAND_NAME,DISPLAY_BRAND FROM BRAND_MASTER WHERE BRAND_ID='" + brandId +"' AND INBOUND_STATUS <> ('D')";
                    List savedList = (List) BrandMasterLocalServiceUtil.executeSelectQuery(saveQuery, null, null);
                    if (!savedList.isEmpty() && savedList.size() > 0) {
                        NotificationUtils.getWarningNotification("Duplicate", "Brand ID is already exist");
                        return "";
                    } else {
                        result = dao.saveBrandTableDetails(brandTable);
                        return "success";
                    }
                }
                
            } else if (brandForm.getField("category") != null && brandForm.getField("category").getValue() != null
                    && StringUtils.EMPTY.equals(brandForm.getField("brandId").getValue().toString())) {
               LOGGER.info("=====else if=======");
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

            return helperTable;
        } catch (Exception e) {
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
        List list = (List) BrandMasterLocalServiceUtil.executeSelectQuery(query, null, null);
        int count = Integer.valueOf(String.valueOf(list.get(0)));
        return count;
    }

    /**
     *
     */
    public List<BrandMasterDTO> brandFind(String categoryValue, int startIndex, int offset) {
        String query = " select brand_id,BRAND_NAME,DISPLAY_BRAND, BRAND_MASTER_SID from brand_master where inbound_status <> 'D' order by brand_id OFFSET " + startIndex + "ROWS FETCH NEXT " + offset + " ROWS ONLY";
        List list = (List) BrandMasterLocalServiceUtil.executeSelectQuery(query, null, null);
        List<BrandMasterDTO> finalList = getCustomizedBrandResults(list, categoryValue);
        return finalList;
    }

    public List<BrandMasterDTO> getCustomizedBrandResults(final List list, String categoryValue) {
        List<BrandMasterDTO> results = new ArrayList<BrandMasterDTO>();
        for (int i = 0; i < list.size(); i++) {
            BrandMasterDTO brandDto = new BrandMasterDTO();
            Object[] obj = (Object[]) list.get(i);
            brandDto.setBrandId(obj[0] != null ? String.valueOf(obj[0].toString()) : "");
            brandDto.setBrandName(obj[1] != null ? String.valueOf(obj[1].toString()) : "");
            brandDto.setDisplayBrand(obj[2] != null ? String.valueOf(obj[2].toString()) : "");
            brandDto.setBrandMasterSid(obj[3] != null ? Integer.valueOf(obj[3].toString()) : 0);
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
