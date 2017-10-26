package com.stpl.app.adminconsole.hierarchybuilder.dto;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

// TODO: Auto-generated Javadoc
/**
 * The Class LevelTableGenerator.
 */
public class LevelTableGenerator extends DefaultFieldFactory {

	/**
	 * Creates a field based on the Container, item id, property id and the
	 * component responsible for displaying the field
     * @param container
     * @param itemId
     * @param uiContext
     * @param propertyId
     * @return 
	 */
	public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {

		Field field;

		if (ConstantsUtils.LEVEL_VALUES.equals(propertyId)) {
			field = new TextField();
		} else {
			field = super.createField(container, itemId, propertyId, uiContext);
			field.setReadOnly(true);
		}
		return field;
	}

}
