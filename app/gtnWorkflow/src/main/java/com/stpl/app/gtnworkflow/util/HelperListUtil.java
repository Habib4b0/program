/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.util;

import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
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
 *
 */
public class HelperListUtil {

    /**
     * The object.
     */
    private static com.stpl.app.gtnworkflow.util.HelperListUtil object;
    ResourceBundle listNameBundle = ResourceBundle.getBundle("properties.listname");
    /**
     * The id desc map.
     */
    private Map<Integer, String> idDescMap = new HashMap<Integer, String>();


    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HelperListUtil.class.getName());

    /**
     * The list name map.
     */
    private Map<String, List<HelperDTO>> listNameMap = new HashMap<String, List<HelperDTO>>();

    /**
     * The id helper dto map.
     */
    private Map<Integer, HelperDTO> idHelperDTOMap = new HashMap<Integer, HelperDTO>();
    /**
     * The id desc map.
     */
    private Map<Integer, String> idDescMapBU = new HashMap<Integer, String>();
    /**
     * The list name map.
     */
    private Map<String, List<HelperDTO>> listNameMapBU = new HashMap<String, List<HelperDTO>>();

    /**
     * The id helper dto map.
     */
    private Map<Integer, HelperDTO> idHelperDTOMapBU = new HashMap<Integer, HelperDTO>();

    /**
     * Instantiates a new helper list util.
     */
    public HelperListUtil() {
        LOGGER.debug("HelperListUtil");
    }

    /**
     * Gets the single instance of HelperListUtil.
     *
     * @return single instance of HelperListUtil
     */
    public static com.stpl.app.gtnworkflow.util.HelperListUtil getInstance() {
        if (object == null) {
            object = new com.stpl.app.gtnworkflow.util.HelperListUtil();
        }
        return object;
    }


    /**
     * Load values with list name.
     *
     * @param listNameBundle
     * @param moduleName the module name
     */
    public void loadValuesWithListName(String moduleName) {
        List<String> listNames = Arrays.asList(listNameBundle.getString(moduleName).split(","));
        idHelperDTOMap.put(0, new HelperDTO(0, GlobalConstants.getSelectOne()));
        if (listNames != null && !listNames.isEmpty()) {
            try {
                List<HelperDTO> helperList = null;
                final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
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
                            helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
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
    
    public HelperDTO getHelperDTObyID(int id){
        
        return idHelperDTOMap.get(id);
    }
    
    /**
     * Load Business Unit Values.
     *
     * @param moduleName the module name
     */
    public void loadBusinessUnitValues() {
        try {
            List<HelperDTO> helperList = new ArrayList<HelperDTO>();
            String query = CustomSQLUtil.get("loadBusinessUnitInWorkflow");
            final List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (list != null) {
                String currentListName = "Business_Unit";
                for (int i = 0; i < list.size(); i++) {
                    final Object[] obj = (Object[]) list.get(i);
                    helperList.add(new HelperDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1])));
                    idDescMapBU.put(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1]));
                    idHelperDTOMapBU.put(Integer.parseInt(String.valueOf(obj[0])), new HelperDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1])));
                    if (i == list.size() - 1) {
                        listNameMapBU.put(currentListName, helperList);
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public Map<Integer, String> getIdDescMapBU() {
        return idDescMapBU;
    }

    public void setIdDescMapBU(Map<Integer, String> idDescMapBU) {
        this.idDescMapBU = idDescMapBU;
    }

    public Map<String, List<HelperDTO>> getListNameMapBU() {
        return listNameMapBU;
    }

    public void setListNameMapBU(Map<String, List<HelperDTO>> listNameMapBU) {
        this.listNameMapBU = listNameMapBU;
    }

    public Map<Integer, HelperDTO> getIdHelperDTOMapBU() {
        return idHelperDTOMapBU;
    }

    public void setIdHelperDTOMapBU(Map<Integer, HelperDTO> idHelperDTOMapBU) {
        this.idHelperDTOMapBU = idHelperDTOMapBU;
    }
}
