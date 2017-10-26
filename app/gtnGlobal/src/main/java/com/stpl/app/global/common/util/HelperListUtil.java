/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.common.util;

import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class HelperListUtil.
 *
 * @author pvinoth
 */
public class HelperListUtil {

    /**
     * The object.
     */
    private static HelperListUtil object;

    /**
     * The list name map.
     */
    private Map<String, List<HelperDTO>> listNameMap = new HashMap<String, List<HelperDTO>>();

    /**
     * The id desc map.
     */
    private Map<Integer, String> idDescMap = new HashMap<Integer, String>();

    /** The id helper dto map. */
    private Map<Integer, HelperDTO> idHelperDTOMap = new HashMap<Integer, HelperDTO>();

    /**
     * The list name bundle.
     */
    private static ResourceBundle listNameBundle = ResourceBundle.getBundle("properties.listname");

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HelperListUtil.class.getName());

    /**
     * Instantiates a new helper list util.
     */
    private HelperListUtil() {

    }

    /**
     * Returns single instance of HelperListUtil.
     *
     * 
     */
    public static HelperListUtil getInstance() {
        if (object == null) {
            object = new HelperListUtil();
        }
        return object;
    }

    /**
     * Load values with list name.
     *
     * @param moduleName the module name
     */
    public void loadValuesWithListName(String moduleName) {
        List<String> listNames = Arrays.asList(listNameBundle.getString(moduleName).split(","));
        idHelperDTOMap.put(0, new HelperDTO(0, ConstantsUtils.SELECT_ONE));
        if (listNames != null && !listNames.isEmpty()) {
            try {
                List<HelperDTO> helperList = null;
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
                        .forClass(HelperTable.class);
                dynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.LIST_NAME,
                        listNames));
                dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.LIST_NAME));
                dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
                final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
                if (list != null) {
                    String currentListName = StringUtils.EMPTY;
                    for (int i = 0; i < list.size(); i++) {
                        final HelperTable helperTable = (HelperTable) list.get(i);
                        
                            if (StringUtils.isEmpty(currentListName) || !helperTable.getListName().equals(currentListName)) {
                                if (helperList != null) {
                                    listNameMap.put(currentListName, helperList);
                                }
                                helperList = new ArrayList<HelperDTO>();
                            }
                            helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                                    .getDescription()));
                            idDescMap.put(helperTable.getHelperTableSid(), helperTable
                                    .getDescription());
                            idHelperDTOMap.put(helperTable.getHelperTableSid(), new HelperDTO(helperTable.getHelperTableSid(), helperTable
                                    .getDescription()));
                            currentListName = helperTable.getListName();

                            if (i == list.size() - 1) {
                                listNameMap.put(currentListName, helperList);
                            }
                    }
                }
            } catch (SystemException ex) {
                LOGGER.error(ex);
            }
        }
    }

    /**
     * Gets the list name map.
     *
     * @return the list name map
     */
    public Map<String, List<HelperDTO>> getListNameMap() {
        return listNameMap;
    }

    /**
     * Sets the list name map.
     *
     * @param listNameMap the list name map
     */
    public void setListNameMap(Map<String, List<HelperDTO>> listNameMap) {
        this.listNameMap = listNameMap;
    }

    /**
     * Gets the id desc map.
     *
     * @return the id desc map
     */
    public Map<Integer, String> getIdDescMap() {
        return idDescMap;
    }

    /**
     * Sets the id desc map.
     *
     * @param idDescMap the id desc map
     */
    public void setIdDescMap(Map<Integer, String> idDescMap) {
        this.idDescMap = idDescMap;
    }

    /**
     * Gets the id helper dto map.
     *
     * @return the id helper dto map
     */
    public Map<Integer, HelperDTO> getIdHelperDTOMap() {
        return idHelperDTOMap;
    }

    /**
     * Sets the id helper dto map.
     *
     * @param idHelperDTOMap the id helper dto map
     */
    public void setIdHelperDTOMap(Map<Integer, HelperDTO> idHelperDTOMap) {
        this.idHelperDTOMap = idHelperDTOMap;
    }

    public int getIdByDesc(String listName, String desc) {
        int value = 0;
        if (!StringUtils.isBlank(listName) && !StringUtils.isBlank(desc)) {
            List<HelperDTO> helperList = (List) getListNameMap().get(listName);
            for (int i = 0; i < helperList.size(); i++) {
                HelperDTO dto = helperList.get(i);
                if (desc.trim().equalsIgnoreCase(dto.getDescription().trim())) {
                    value = dto.getId();
                    break;
                }
            }
        }
        return value;
    }
}
