package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter;

import org.asi.ui.extfilteringtable.ExtFilterGenerator;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Field;

public class GtnUIFrameworkPagedTableFilterGenerator implements ExtFilterGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedTableFilterGenerator.class);

	private GtnUIFrameworkPagedTableConfig pagedTableConfig;

	public GtnUIFrameworkPagedTableFilterGenerator(GtnUIFrameworkPagedTableConfig pagedTableConfig) {
		super();
		gtnLogger.debug("Initialising Genrator for " + pagedTableConfig);
		this.pagedTableConfig = pagedTableConfig;
	}

	@Override
	public Container.Filter generateFilter(Object propertyId, Object value) {
		return null;
	}

	@Override
	public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
		return null;
	}

	@Override
	public AbstractField<?> getCustomFilterComponent(Object propertyId) {
		gtnLogger.debug("Generating Filter for column " + propertyId);
		if (pagedTableConfig.getCustomFilterConfigMap() != null) {

			GtnUIFrameworkPagedTableCustomFilterConfig filterConfig = pagedTableConfig.getCustomFilterConfigMap()
					.get(propertyId.toString());
			if (filterConfig != null) {
				GtnUIFrameworkComponent component = filterConfig.getGtnComponentType().getGtnComponent();
				Component vaadinComponent = null;
				try {
					vaadinComponent = component.buildVaadinComponent(filterConfig.getGtnComponentConfig());
				} catch (GtnFrameworkGeneralException exception) {
					gtnLogger.error("Exception while creating the filter component", exception);
				}
				return (AbstractField<?>) vaadinComponent;
			}

		}
		return null;
	}

	@Override
	public void filterRemoved(Object propertyId) {
		return;
	}

	@Override
	public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
		return;
	}

	@Override
	public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
		return null;
	}
}
