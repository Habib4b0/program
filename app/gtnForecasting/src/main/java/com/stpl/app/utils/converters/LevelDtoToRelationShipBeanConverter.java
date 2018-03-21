package com.stpl.app.utils.converters;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;

public class LevelDtoToRelationShipBeanConverter {

	public static List<GtnFrameworkRelationshipLevelDefintionBean> convetToRelationBean(
			List<Leveldto> selectedCustomerContractList) {
		List<GtnFrameworkRelationshipLevelDefintionBean> finalForecastList = new ArrayList<>();
		for (Leveldto levelDto : selectedCustomerContractList) {
			GtnFrameworkRelationshipLevelDefintionBean relationBean = convertLevelDtoToRelationBean(levelDto);
			finalForecastList.add(relationBean);
		}
		return finalForecastList;
	}

	public static GtnFrameworkRelationshipLevelDefintionBean convertLevelDtoToRelationBean(Leveldto levelDto) {
		GtnFrameworkRelationshipLevelDefintionBean forecast = new GtnFrameworkRelationshipLevelDefintionBean();
		forecast.setLevelName(levelDto.getLevel());
		forecast.setLevelNo(levelDto.getLevelNo());
		forecast.setRelationshipLevelSid(levelDto.getRelationshipLevelSid());
		forecast.setRelationShipLevelValue(levelDto.getRelationshipLevelValue() == null ? 0
				: Integer.parseInt(levelDto.getRelationshipLevelValue()));
		forecast.setTableName(levelDto.getTableName());
		forecast.setFieldName(levelDto.getFieldName());
		forecast.setHierarchyNo(levelDto.getHierarchyNo());
		forecast.setHierarchyDefinitionSid(levelDto.getHierarchyId());
		forecast.setHierarchyLevelDefinitionSid(levelDto.getHierarchyLevelDefnId() == null ? 0
				: Integer.parseInt(levelDto.getHierarchyLevelDefnId()));
		forecast.setLevelValueReference(levelDto.getLevelValueReference());
		forecast.setRelationshipBuilderSid(levelDto.getRelationShipBuilderId() == null ? 0
				: Integer.parseInt(levelDto.getRelationShipBuilderId()));
		forecast.setRelationshipVersionNo(levelDto.getRelationShipVersionNo());
		forecast.setHierarchyVersionNo(levelDto.getHierarchyVersionNo());
		forecast.setHierarchyCategory(levelDto.getHierarchyType());
		return forecast;
	}

}
