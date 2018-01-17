/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.Arrays;
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
	 * The Constant PS_STATUS.
	 */
	public static final String PS_STATUS = "PS_STATUS";
	/**
	 * The Constant PS_TYPE.
	 */
	public static final String PS_TYPE = "PS_TYPE";
	/**
	 * The Constant PRICE_TYPE.
	 */
	public static final String PRICE_TYPE = "PS_PRICE_TYPE";
	/**
	 * The Constant PRICE_TOLERANCE.
	 */
	public static final String PRICE_TOLERANCE = "PS_PRICE_TOL";
	/**
	 * The Constant PRICE_TOLERANCE_TYPE.
	 */
	public static final String PRICE_TOLERANCE_TYPE = "PRICE_TOLERANCE_TYPE";
	/**
	 * The Constant PRICE_TOLERANCE_INTERVAL.
	 */
	public static final String PRICE_TOLERANCE_INTERVAL = "PRICE_TOLERANCE_INTERVAL";
	/**
	 * The Constant PRICE_TOLERANCE_FRERQUENCY.
	 */
	public static final String PRICE_TOLERANCE_FRERQUENCY = "PRICE_TOLERANCE_FREQUENCY";
	/**
	 * The Constant PS_DESIGNATION.
	 */
	public static final String PS_DESIGNATION = "PS_DESIGNATION";
	/**
	 * The Constant TRADE_CLASS.
	 */
	public static final String TRADE_CLASS = "PS_TRADE_CLASS";
	/**
	 * The Constant PS_CATEGORY.
	 */
	public static final String PS_CATEGORY = "PS_CATEGORY";
	/**
	 * The Constant RESET_TYPE.
	 */
	public static final String RESET_TYPE = "RESET_TYPE";
	/**
	 * The Constant RESET_TYPE.
	 */
	public static final String LOCKED_STATUS = "LOCKED_STATUS";
	/**
	 * The Constant BASE_PRICE_TYPE.
	 */
	public static final String BASE_PRICE_TYPE = "BASE_PRICE_TYPE";
	/**
	 * Constructor
	 */
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
	private static final ResourceBundle listNameBundle = ResourceBundle.getBundle("properties.listname");

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
	public static HelperListUtil getInstance() {
		if (object == null) {
			object = new HelperListUtil();
		}
		return object;
	}

	/**
	 * Load values with list name.
	 *
	 * @param moduleName
	 *            the module name
	 */
	public void loadValuesWithListName(String moduleName) {
		List<String> listNames = Arrays.asList(listNameBundle.getString(moduleName).split(","));
		idHelperDTOMap.put(0, new HelperDTO(0, ConstantsUtils.SELECT_ONE));
		if (listNames != null && !listNames.isEmpty()) {
			try {
				List<HelperDTO> helperList = null;
				final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
				dynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.LIST_NAME, listNames));
				dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.LIST_NAME));
				dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
				final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
				if (list != null) {
					String currentListName = StringUtils.EMPTY;
					for (int i = 0; i < list.size(); i++) {
						final HelperTable helperTable = (HelperTable) list.get(i);

						if (StringUtils.isEmpty(currentListName)
								|| !helperTable.getListName().equals(currentListName)) {
							if (helperList != null) {
								listNameMap.put(currentListName, helperList);
							}
							helperList = new ArrayList<>();
						}
						helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
						idDescMap.put(helperTable.getHelperTableSid(), helperTable.getDescription());
						idHelperDTOMap.put(helperTable.getHelperTableSid(),
								new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
						currentListName = helperTable.getListName();

						if (i == list.size() - 1) {
							listNameMap.put(currentListName, helperList);
						}
					}
				}
			} catch (SystemException ex) {
				LOGGER.error(ex.getMessage());
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
	 * @param listNameMap
	 *            the list name map
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
	 * @param idDescMap
	 *            the id desc map
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
	 * @param idHelperDTOMap
	 *            the id helper dto map
	 */
	public void setIdHelperDTOMap(Map<Integer, HelperDTO> idHelperDTOMap) {
		this.idHelperDTOMap = idHelperDTOMap;
	}

	public String getDescriptionByID(int id) {
		return id == 0 ? StringUtils.EMPTY : idDescMap.get(id);
	}

	public HelperDTO getHelperDTOByID(int id) {
		return id == 0 ? new HelperDTO() : idHelperDTOMap.get(id);
	}

	public List<HelperTable> getDynamicQuery(DynamicQuery dynamicQuery) throws SystemException {
		return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	public void loadValuesWithid(List<String> listNames) {
		idHelperDTOMap.put(0, new HelperDTO(0, ConstantsUtils.SELECT_ONE));
		if (listNames != null && !listNames.isEmpty()) {
			try {
				List<HelperDTO> helperList = null;
				final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
				dynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.LIST_NAME, listNames));
				dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.LIST_NAME));
				dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
				final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
				if (list != null) {
					String currentListName = StringUtils.EMPTY;
					for (int i = 0; i < list.size(); i++) {
						final HelperTable helperTable = (HelperTable) list.get(i);

						if (StringUtils.isEmpty(currentListName)
								|| !helperTable.getListName().equals(currentListName)) {
							if (helperList != null) {
								listNameMap.put(currentListName, helperList);
							}
							helperList = new ArrayList<>();
						}
						helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
						idDescMap.put(helperTable.getHelperTableSid(), helperTable.getDescription());
						idHelperDTOMap.put(helperTable.getHelperTableSid(),
								new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
						currentListName = helperTable.getListName();

						if (i == list.size() - 1) {
							listNameMap.put(currentListName, helperList);
						}
					}
				}
			} catch (SystemException ex) {
				LOGGER.error(ex.getMessage());
			}
		}
	}

	public int getIdByDescription(String decriptionVlaue, String listName) {
		List<HelperDTO> idDescDtoList = listNameMap.get(listName);
		for (HelperDTO helperDTO : idDescDtoList) {
			if (helperDTO.getDescription().equals(decriptionVlaue)) {
				return helperDTO.getId();
			}
		}
		return 0;
	}
}
