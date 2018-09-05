/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.utils;

import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;

import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class HelperListUtil.
 *
 * @author pvinoth
 */
public class HelperListUtil {

    /**
     * The Constant STATUS.
     */
    public static final String STATUS = "STATUS";

    /**
     * The object.
     */
    private static HelperListUtil object;

    /**
     * The list name map.
     */
    private Map<String, List<HelperDTO>> listNameMap = new HashMap<>();

    /**
     * The id desc map.
     */
    private Map<Integer, String> idDescMap = new HashMap<>();

    /**
     * The id helper dto map.
     */
    private Map<Integer, HelperDTO> idHelperDTOMap = new HashMap<>();

    /**
     * The list name bundle.
     */
    private static ResourceBundle listNameBundle = ResourceBundle.getBundle("properties.listname");

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HelperListUtil.class.getName());

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
    public static synchronized HelperListUtil getInstance() {
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
        idHelperDTOMap.put(0, new HelperDTO(0, GlobalConstants.getSelectOne()));
        try {
            String query = SQlUtil.getQuery("HELPER_TABLE_LOAD");
            StringBuilder replaceQuery = new StringBuilder();
            replaceQuery.append(ARMUtils.SINGLE_QUOTES).append(listNameBundle.getString(moduleName).replace(",", "','")).append(ARMUtils.SINGLE_QUOTES);
            query = query.replace("?", replaceQuery);
            List<HelperDTO> helperList = null;
            final List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (list != null) {
                String currentListName = StringUtils.EMPTY;
                for (int i = 0; i < list.size(); i++) {
                    final Object[] obj = list.get(i);

                    if (StringUtils.isEmpty(currentListName) || !obj[1].equals(currentListName)) {
                        if (helperList != null) {
                            listNameMap.put(currentListName, helperList);
                        }
                        helperList = new ArrayList<>();
                    }
                    if (helperList != null) {
                        helperList.add(new HelperDTO((Integer) obj[0], obj[2].toString()));
                    }
                    idDescMap.put((Integer) obj[0], obj[2].toString());
                    idHelperDTOMap.put((Integer) obj[0], new HelperDTO((Integer) obj[0], obj[2].toString()));
                    currentListName = obj[1].toString();
                    if (i == list.size() - 1) {
                        listNameMap.put(currentListName, helperList);
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error in loadValuesWithListName :" , ex);
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

    public String getDescriptionByID(int id) {
        return id == 0 || idDescMap.get(id) == null ? StringUtils.EMPTY : idDescMap.get(id);
    }

    public HelperDTO getHelperDTOByID(int id) {
        return id == 0 ? new HelperDTO() : idHelperDTOMap.get(id);
    }

    public int getIdByDesc(String listName, String desc) {
        int value = 0;
        if (!StringUtils.isBlank(listName) && !StringUtils.isBlank(desc)) {
            List<HelperDTO> helperList = getListNameMap().get(listName);
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
