package com.stpl.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * The Class UISecurityUtil.
 */
public final class UISecurityUtil {

	/** The Constant CPI_INDEX_FIELD_SECURE_HM. */
	public static final String CPI_INDEX_FIELD_SECURE_HM = "permissionCpiIndexField";

	/** The Constant GL_COST_FIELD_SECURE_HM. */
	public static final String GL_COST_FIELD_SECURE_HM = "permissionGlCostField";

	/** The Constant MASTER_DATA_FIELD_SECURE_HM. */
	public static final String MASTER_DATA_FIELD_SECURE_HM = "permissionMasterDataField";

	/** The Constant ITEM_HIERARCHY_FIELD_SECURE_HM. */
	public static final String ITEM_HIERARCHY_FIELD_SECURE_HM = "permissionItemHierarchyField";

	/** The Constant ITEM_HIERARCHY_DEF_FIELD_SECURE_HM. */
	public static final String ITEM_HIERARCHY_DEF_FIELD_SECURE_HM = "permissioItemHierarchyDefField";

	/** The Constant ACTUALS_MASTER_FIELD_SECURE_HM. */
	public static final String ACTUALS_MASTER_FIELD_SECURE_HM = "permissionActualsMasterField";

	/** The Constant SALES_MASTER_FIELD_SECURE_HM. */
	public static final String SALES_MASTER_FIELD_SECURE_HM = "permissionSalesMasterField";

	/** The Constant GL_BALANCE_MASTER_FIELD_SECURE_HM. */
	public static final String GL_BALANCE_MASTER_FIELD_SECURE_HM = "permissionGLBalanceField";

	/** The Constant FORECASTING_FIELD_SECURE_HM. */
	public static final String FORECASTING_FIELD_SECURE_HM = "permissionForecastSalesField";

	/** The Constant BEST_PRICE_FIELD_SECURE_HM. */
	public static final String BEST_PRICE_FIELD_SECURE_HM = "permissionBestPriceField";

	/** The Constant LOT_MASTER_FIELD_SECURE_HM. */
	public static final String LOT_MASTER_FIELD_SECURE_HM = "permissionLotMasterField";

	/** The Constant AVERAGE_SHELF_LIFE_FIELD_SECURE_HM. */
	public static final String AVERAGE_SHELF_LIFE_FIELD_SECURE_HM = "permissionAvgShelfLifeField";

	/** The Constant AUDIT_MASTER_FIELD_SECURE_HM. */
	public static final String AUDIT_MASTER_FIELD_SECURE_HM = "permissionAuditMasterField";

	/** The Constant FORMULA_DETAILS_FIELD_SECURE_HM. */
	public static final String FORMULA_DETAILS_FIELD_SECURE_HM = "permissionFormulaDetailsField";
	/** The Constant CPI_INDEX_FUNCTION_SECURE_HM. */
	public static final String CPI_INDEX_FUNCTION_SECURE_HM = "permissionCpiIndexFunction";

	/** The Constant GL_COST_FUNCTION_SECURE_HM. */
	public static final String GL_COST_FUNCTION_SECURE_HM = "permissionGlCostFunction";

	/** The Constant MASTER_DATA_FUNCTION_SECURE_HM. */
	public static final String MASTER_DATA_FUNCTION_SECURE_HM = "permissionMasterDataFunction";

	/** The Constant ITEM_HIERARCHY_FUNCTION_SECURE_HM. */
	public static final String ITEM_HIERARCHY_FUNCTION_SECURE_HM = "permissionItemHierarchyFunction";

	/** The Constant ITEM_HIERARCHY_DEF_FUNCTION_SECURE_HM. */
	public static final String ITEM_HIERARCHY_DEF_FUNCTION_SECURE_HM = "permissioItemHierarchyDefFunction";

	/** The Constant ACTUALS_MASTER_FUNCTION_SECURE_HM. */
	public static final String ACTUALS_MASTER_FUNCTION_SECURE_HM = "permissionActualsMasterFunction";

	/** The Constant SALES_MASTER_FUNCTION_SECURE_HM. */
	public static final String SALES_MASTER_FUNCTION_SECURE_HM = "permissionSalesMasterFunction";

	/** The Constant GL_BALANCE_MASTER_FUNCTION_SECURE_HM. */
	public static final String GL_BALANCE_MASTER_FUNCTION_SECURE_HM = "permissionGLBalanceFunction";

	/** The Constant FORECASTING_FUNCTION_SECURE_HM. */
	public static final String FORECASTING_FUNCTION_SECURE_HM = "permissionForecastSalesFunction";

	/** The Constant BEST_PRICE_FUNCTION_SECURE_HM. */
	public static final String BEST_PRICE_FUNCTION_SECURE_HM = "permissionBestPriceFunction";

	/** The Constant LOT_MASTER_FUNCTION_SECURE_HM. */
	public static final String LOT_MASTER_FUNCTION_SECURE_HM = "permissionLotMasterFunction";

	/** The Constant AVERAGE_SHELF_LIFE_FUNCTION_SECURE_HM. */
	public static final String AVERAGE_SHELF_LIFE_FUNCTION_SECURE_HM = "permissionAvgShelfLifeFunction";

	/** The Constant AUDIT_MASTER_FUNCTION_SECURE_HM. */
	public static final String AUDIT_MASTER_FUNCTION_SECURE_HM = "permissionAuditMasterFunction";

	/** The Constant FORMULA_DETAILS_FUNCTION_SECURE_HM. */
	public static final String FORMULA_DETAILS_FUNCTION_SECURE_HM = "permissionFormulaDetailsFunction";

	/** The Constant ACTUALS_MASTER_TAB_SECURE_HM. */
	public static final String ACTUALS_MASTER_TAB_SECURE_HM = "permissionActualsMasterTab";

	/** The Constant CPI_INDEX. */
	public static final String CPI_INDEX = "Cpi Index";

	/** The Constant GL_COST_CENTER. */
	public static final String GL_COST_CENTER = "GL Cost Center";

	/** The Constant MASTER_DATA_ATTRIBUTE. */
	public static final String MASTER_DATA_ATTRIBUTE = "Master Data Attribute";

	/** The Constant ITEM_HIERARCHY_MASTER. */
	public static final String ITEM_HIERARCHY_MASTER = "Item Hierarchy";

	/** The Constant ITEM_HIERARCHY_DEF_MASTER. */
	public static final String ITEM_HIERARCHY_DEF_MASTER = "Item Hierarchy Definition";

	/** The Constant ACTUALS_MASTER. */
	public static final String ACTUALS_MASTER = "Actual Master";

	/** The Constant SALES_MASTER. */
	public static final String SALES_MASTER = "Sales Master";

	/** The Constant GL_BALANCE_MASTER. */
	public static final String GL_BALANCE_MASTER = "GL Balance";

	/** The Constant FORECASTING_MASTER. */
	public static final String FORECASTING_MASTER = "Forecast Sales";

	/** The Constant BEST_PRICE_MASTER. */
	public static final String BEST_PRICE_MASTER = "Best Price";

	/** The Constant LOT_MASTER. */
	public static final String LOT_MASTER = "Lot Master";

	/** The Constant AVERAGE_SHELF_LIFE_MASTER. */
	public static final String AVERAGE_SHELF_LIFE_MASTER = "Average Shelf Life Master";

	/** The Constant AUDIT_MASTER_INBOUND. */
	public static final String AUDIT_MASTER_INBOUND = "Audit Inbound";

	/** The Constant FORMULA_DETAILS. */
	public static final String FORMULA_DETAILS = "Formula Details";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);

	/**
	 * Modify table result.
	 *
	 * @param obj
	 *            the obj
	 * @param header
	 *            the header
	 * @param fieldHM
	 *            the field hm
	 * @return the table result custom
	 */

	public static TableResultCustom modifyTableResult(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM) throws SystemException, PortalException, Exception {

		final TableResultCustom tblResultCustom = new TableResultCustom();
		String str;
		final List<Object> objResultList = new ArrayList();
		final List<String> objResultHeaderList = new ArrayList();

		LOGGER.info("Entering modifyTableResult with obj length:" + obj.length + ", header length:" + header.length + ", fieldHM size:" + fieldHM.size());
		for (int i = 0; i < obj.length; i++) {
			str = String.valueOf(obj[i]);
			if (fieldHM.get(str) != null) {
				final AppPermission appPermission = fieldHM.get(str);

				if (appPermission.isSearchFlag()) {

                        if(!objResultList.contains(obj[i])){
					objResultList.add(obj[i]);
					objResultHeaderList.add(header[i]);
                        }
				}
			}
		}
		Object[] objResult = new Object[objResultList.size()];
		String[] objResultHeader = new String[objResultList.size()];
		for (int i = 0; i < objResultList.size(); i++) {
			objResult[i] = objResultList.get(i);
			objResultHeader[i] = objResultHeaderList.get(i);
		}
		tblResultCustom.setObjResult(objResult);
		tblResultCustom.setObjResultHeader(objResultHeader);

		LOGGER.info("Ends modifyTableResult with tblResultCustom");
		return tblResultCustom;
	}

	/**
	 * The Constructor.
	 */
	private UISecurityUtil() {

	}

}
