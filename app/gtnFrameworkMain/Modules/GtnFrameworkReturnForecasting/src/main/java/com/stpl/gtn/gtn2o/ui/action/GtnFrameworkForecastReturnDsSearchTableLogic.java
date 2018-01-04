package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Container.Filter;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;

public class GtnFrameworkForecastReturnDsSearchTableLogic extends GtnUIFrameworkPagedTableLogic
		implements GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastReturnDsSearchTableLogic.class);

	@Override
	public void startSearchProcess(final List<String> vaadinFieldValues, boolean isActive) {
		gtnLogger.debug("Entering startSearchProcess");
		super.setActive(isActive);
		super.setCurrentSearchCriteria(new ArrayList<GtnWebServiceSearchCriteria>());
		addCurrentSearchCriteria(vaadinFieldValues);
		addSpecificSearchCriteriaToGetID(Arrays.asList("returnsForecastMainScreenDataSelection_productHierarchy",
				"returnsForecastMainScreenDataSelection_productGroup"));

		addSpecificSearchCriteriaToGetDescription(Arrays.asList("returnsForecastMainScreenDataSelection_fromPeriod",
				"returnsForecastMainScreenDataSelection_toPeriod"));

		addSpecificSearchCriteriaForSelectedRelationshipValues(
				Arrays.asList("returnsForecastMainScreenDataSelection_dualListBoxComp"));
		super.clearAll();
		super.getFilters().clear();
		super.setRequiredCount(true);
		super.setCurrentPage(1);

	}

	private void addSpecificSearchCriteriaForSelectedRelationshipValues(List<String> vaadinFieldValues) {

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(vaadinFieldValues.get(0), super.getComponentConfig().getSourceViewId());
		List<GtnWsRecordBean> rightTableBeanList = gtnUIFrameworkBaseComponent.getDualListBoxRightTableData();
		StringBuilder values = new StringBuilder();
		for (GtnWsRecordBean gtnWsRecordBean : rightTableBeanList) {
			values.append("'");
			values.append(gtnWsRecordBean.getAdditionalProperties().get(1));
			values.append("'");
			values.append(",");
		}
		if (values.length() > 0) {
			GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
			searchCriteria.setFieldId(vaadinFieldValues.get(0));
			searchCriteria.setFilterValue1("(" + values.substring(0, values.length() - 1) + ")");
			searchCriteria.setExpression(" IN ");
			super.addCurrentSearchCriteria(searchCriteria);
		}
	}

	private void addCurrentSearchCriteria(List<String> vaadinFieldValues) {

		GtnWebServiceSearchCriteria searchCriteria = null;
		if (vaadinFieldValues != null) {
			for (String componentId : vaadinFieldValues) {
				searchCriteria = new GtnWebServiceSearchCriteria();
				Field<?> vaadinField = (Field<?>) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(componentId, super.getComponentConfig().getSourceViewId())
						.getComponent();
				String currentValue = getStringValue(vaadinField.getValue());
				if (currentValue != null && !"".equals(currentValue)) {
					searchCriteria.setFieldId(componentId);
					searchCriteria.setFilterValue1(currentValue);
					searchCriteria.setExpression(currentValue.contains("*") ? "LIKE" : " = ");
					super.addCurrentSearchCriteria(searchCriteria);
				}
			}
		}
	}

	private boolean isValueNull(Object value) {
		return value == null;
	}

	private String getStringValue(Object value) {
		if (isValueNull(value)) {
			return "";
		}
		return String.valueOf(value);
	}

	private void addSpecificSearchCriteriaToGetID(final List<String> fieldValues) {
		GtnWebServiceSearchCriteria specificSearchCriteria = null;
		if (fieldValues != null) {
			for (String componentId : fieldValues) {
				specificSearchCriteria = new GtnWebServiceSearchCriteria();
				Field<?> vaadinField = (Field<?>) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(componentId, super.getComponentConfig().getSourceViewId())
						.getComponent();
				String currentValue = getStringValue(vaadinField.getValue());
				String currentData = getStringValue(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(componentId, super.getComponentConfig().getSourceViewId())
						.getIdFromField());
				if (!"".equals(currentValue) && currentData != null && !"".equals(currentData)) {

					specificSearchCriteria.setFieldId(componentId);
					specificSearchCriteria.setFilterValue1(currentData);
					specificSearchCriteria.setExpression(currentData.contains("*") ? "LIKE" : " = ");
					super.addCurrentSearchCriteria(specificSearchCriteria);

				}
			}
		}

	}

	private void addSpecificSearchCriteriaToGetDescription(final List<String> fieldValues) {
		GtnWebServiceSearchCriteria specificSearchCriteria = null;
		if (fieldValues != null) {
			for (String componentId : fieldValues) {
				specificSearchCriteria = new GtnWebServiceSearchCriteria();
				ComboBox period = (ComboBox) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(componentId, super.getComponentConfig().getSourceViewId())
						.getComponent();
				String periodRange = period.getItemCaption(period.getValue());
				if (periodRange != null && !"".equals(periodRange)) {
					specificSearchCriteria.setFieldId(componentId);
					specificSearchCriteria.setFilterValue1(periodRange);
					specificSearchCriteria.setExpression(componentId.contains("to") ? " <= " : " >= ");
					super.addCurrentSearchCriteria(specificSearchCriteria);

				}
			}
		}
	}

	@Override
	public List<GtnWebServiceSearchCriteria> getSearchCriteriaList(Set<Container.Filter> filterSet) {
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();

		if (super.getCurrentSearchCriteria() != null) {
			for (GtnWebServiceSearchCriteria current : super.getCurrentSearchCriteria()) {
				gtnWebServiceSearchCriteriaList.add(current);
			}
		}
		if (filterSet != null && !filterSet.isEmpty()) {
			gtnWebServiceSearchCriteriaList.addAll(getFilterCondition(filterSet));
		}
		return gtnWebServiceSearchCriteriaList;
	}

	private List<GtnWebServiceSearchCriteria> getFilterCondition(Set<Filter> filterSet) {
		GtnWebServiceSearchCriteria searchCriteria;
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		for (Container.Filter filter : filterSet) {
			searchCriteria = new GtnWebServiceSearchCriteria();
			searchCriteria.setFilter(true);
			if (filter instanceof SimpleStringFilter) {
				searchCriteria.setFieldId(((SimpleStringFilter) filter).getPropertyId().toString());
				searchCriteria.setFilterValue1("%" + ((SimpleStringFilter) filter).getFilterString() + "%");
				searchCriteria.setExpression("LIKE");
			}
			if (filter instanceof Between) {
				searchCriteria.setFieldId(((Between) filter).getPropertyId().toString());
				searchCriteria.setFilterValue1(((Between) filter).getStartValue().toString());
				searchCriteria.setFilterValue2(((Between) filter).getEndValue().toString());
				searchCriteria.setExpression("BETWEEN");
			}
			if (filter instanceof Compare) {
				Compare stringFilter = (Compare) filter;
				Compare.Operation operation = stringFilter.getOperation();
				searchCriteria.setFieldId(stringFilter.getPropertyId().toString());
				searchCriteria.setFilterValue1(stringFilter.getValue().toString());
				searchCriteria.setExpression(toGetOperator(operation.name()));
			}
			gtnWebServiceSearchCriteriaList.add(searchCriteria);

		}
		return gtnWebServiceSearchCriteriaList;

	}

	private String toGetOperator(String operatorString) {
		String operator = "";

		switch (operatorString) {

		case "EQUAL":
			operator = "=";
			break;
		case "GREATER":
			operator = ">";
			break;
		case "LESS":
			operator = "<";
			break;
		case "GREATER_OR_EQUAL":
			operator = ">=";
			break;
		case "LESS_OR_EQUAL":
			operator = "<=";
			break;
		default:
			break;
		}
		return operator;
	}
}
