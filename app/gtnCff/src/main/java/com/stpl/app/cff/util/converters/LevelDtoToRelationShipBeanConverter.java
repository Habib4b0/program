package com.stpl.app.cff.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LevelDtoToRelationShipBeanConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelDtoToRelationShipBeanConverter.class);

    private LevelDtoToRelationShipBeanConverter() {
        LOGGER.debug("LevelDtoToRelationShipBeanConverter");
    }
        
	public static List<GtnFrameworkRelationshipLevelDefintionBean> convetToRelationBean(
			List<Leveldto> selectedCustomerContractList) {
		List<GtnFrameworkRelationshipLevelDefintionBean> finalList = new ArrayList<>();
		for (Leveldto levelDto : selectedCustomerContractList) {
			GtnFrameworkRelationshipLevelDefintionBean relationBean = convertLevelDtoToRelationBean(levelDto);
			finalList.add(relationBean);
		}
		return finalList;
	}

	public static GtnFrameworkRelationshipLevelDefintionBean convertLevelDtoToRelationBean(Leveldto levelDto) {
		GtnFrameworkRelationshipLevelDefintionBean relationBean = new GtnFrameworkRelationshipLevelDefintionBean();
		relationBean.setLevelName(levelDto.getLevel());
		relationBean.setLevelNo(levelDto.getLevelNo());
		relationBean.setRelationshipLevelSid(levelDto.getRelationshipLevelSid());
		relationBean.setRelationShipLevelValue(levelDto.getRelationshipLevelValue() == null ? 0
				: Integer.parseInt(levelDto.getRelationshipLevelValue()));
		relationBean.setTableName(levelDto.getTableName());
		relationBean.setFieldName(levelDto.getFieldName());
		relationBean.setHierarchyNo(levelDto.getHierarchyNo());
		relationBean.setHierarchyDefinitionSid(levelDto.getHierarchyId());
		relationBean.setHierarchyLevelDefinitionSid(levelDto.getHierarchyLevelDefnId() == null ? 0
				: Integer.parseInt(levelDto.getHierarchyLevelDefnId()));
		relationBean.setLevelValueReference(levelDto.getLevelValueReference());
		relationBean.setRelationshipBuilderSid(levelDto.getRelationShipBuilderId() == null ? 0
				: Integer.parseInt(levelDto.getRelationShipBuilderId()));
		relationBean.setRelationshipVersionNo(levelDto.getRelationShipVersionNo());
		relationBean.setHierarchyVersionNo(levelDto.getHierarchyVersionNo());
		relationBean.setHierarchyCategory(levelDto.getHierarchyType());
		return relationBean;
	}

}
