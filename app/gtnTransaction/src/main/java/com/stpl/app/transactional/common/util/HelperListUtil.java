/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.util;

import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
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

public class HelperListUtil {

    private static HelperListUtil object;

    private Map<String, List<HelperDTO>> listNameMap = new HashMap<>();

    private Map<Integer, String> idDescMap = new HashMap<>();

    private Map<Integer, HelperDTO> idHelperDTOMap = new HashMap<>();

    private static ResourceBundle listNameBundle = ResourceBundle.getBundle("properties.listname");

    private static final Logger LOGGER = Logger.getLogger(HelperListUtil.class.getName());

    private HelperListUtil() {

    }

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
                            helperList = new ArrayList<>();
                        }
                        helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                                .getDescription()));
                        idDescMap.put(helperTable.getHelperTableSid(), helperTable
                                .getDescription());
                        idHelperDTOMap.put(helperTable.getHelperTableSid(), new HelperDTO(helperTable.getHelperTableSid(), helperTable
                                .getDescription()));
                        currentListName = helperTable.getListName();

                        if (i == list.size() - NumericConstants.ONE) {
                            listNameMap.put(currentListName, helperList);
                        }
                    }
                }
            } catch (SystemException ex) {
                LOGGER.error(ex);
            }
        }
    }

    public Map<String, List<HelperDTO>> getListNameMap() {
        return listNameMap;
    }

    public void setListNameMap(Map<String, List<HelperDTO>> listNameMap) {
        this.listNameMap = listNameMap;
    }

    public Map<Integer, String> getIdDescMap() {
        return idDescMap;
    }

    public void setIdDescMap(Map<Integer, String> idDescMap) {
        this.idDescMap = idDescMap;
    }

    public Map<Integer, HelperDTO> getIdHelperDTOMap() {
        return idHelperDTOMap;
    }

    public void setIdHelperDTOMap(Map<Integer, HelperDTO> idHelperDTOMap) {
        this.idHelperDTOMap = idHelperDTOMap;
    }

    public HelperDTO getHelperDTObyID(int id) {
        return idHelperDTOMap.get(id);
    }
}
