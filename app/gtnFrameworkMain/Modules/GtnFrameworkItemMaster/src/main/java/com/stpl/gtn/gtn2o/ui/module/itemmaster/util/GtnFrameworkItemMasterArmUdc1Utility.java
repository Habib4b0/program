package com.stpl.gtn.gtn2o.ui.module.itemmaster.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;

public class GtnFrameworkItemMasterArmUdc1Utility {
	private static GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterArmUdc1Utility.class);
	private static List<String> armUdc1ValueList = null;
	private static List<String> armUdc1CodeList = null;
	private static Map<Integer, String> codeValueArmUdc1HashMap = null;

	private GtnFrameworkItemMasterArmUdc1Utility() {
		/*
		 * Singleton Class
		 */
	}


	private static synchronized void createValueCodeHashMap() {
		if (codeValueArmUdc1HashMap != null) {
			return;
		}
		codeValueArmUdc1HashMap = new HashMap<>();

		for (int i = 0; i < armUdc1ValueList.size(); i++) {
			codeValueArmUdc1HashMap.put(Integer.valueOf(armUdc1CodeList.get(i)), armUdc1ValueList.get(i));
		}
	}

	private static synchronized void getValuesForArmUdc1FromHelperTable() {
		if (armUdc1ValueList != null) {
			return;
		}

		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setComboBoxType(GtnFrameworkItemMasterStringContants.ARM_UDC_1);

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsGeneralRequest(generalWSRequest);

		GtnUIFrameworkWebserviceComboBoxResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken()).getGtnUIFrameworkWebserviceComboBoxResponse();

		armUdc1ValueList = new ArrayList<>(response.getItemValueList());
		armUdc1CodeList = new ArrayList<>(response.getItemCodeList());
	}
	
	private static synchronized void reset() {
		armUdc1ValueList=null;
		armUdc1CodeList=null;
		codeValueArmUdc1HashMap=null;
	}

	public static void initialize() {
		reset();
		getValuesForArmUdc1FromHelperTable();
		createValueCodeHashMap();
	}

	public static int getArmUdc1ItemCode(String itemValue) {
		String value = checkItemPresentInHashMap(itemValue);
		for (Entry<Integer, String> entry : codeValueArmUdc1HashMap.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}
		return 0;
	}

	public static String getArmUdc1ItemValue(int itemCode) {
		return (codeValueArmUdc1HashMap.get(itemCode) == null) ? "" : codeValueArmUdc1HashMap.get(itemCode);
	}

	public static List<String> getArmUdc1ValueList() {
		return Collections.unmodifiableList(armUdc1ValueList);
	}

	public static List<String> getArmUdc1CodeList() {
		return Collections.unmodifiableList(armUdc1CodeList);
	}

	private static String checkItemPresentInHashMap(String itemValue) {
		List<String> itemValueList = Arrays.asList(itemValue.toLowerCase(Locale.ENGLISH).replaceAll("\\s", "").split(","));

		for (String armUdc1Value : armUdc1ValueList) {
			List<String> armUdc1ValueList = Arrays.asList(armUdc1Value.toLowerCase(Locale.ENGLISH).replaceAll("\\s", "").split(","));

			if ((armUdc1ValueList.size() == itemValueList.size()) && (armUdc1ValueList.containsAll(itemValueList))) {
				gtnLogger.debug("Item Present");
				return armUdc1Value;
			}
		}
		gtnLogger.debug("Item Not Present");
		return GtnFrameworkItemMasterStringContants.EMPTY;
	}
}
