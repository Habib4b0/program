/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.utils.Constants.CommonConstants;
import static com.stpl.app.utils.Constants.CommonConstants.COLON;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.LabelConstants.ALL_BRANDS;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACTED;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_ADD;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_SEARCH;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_ASSUMPTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_PPA_PROJECTION;
import static com.stpl.app.utils.Constants.RegexConstants.REGEX_EXTRACT_DIGITS;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.TreeTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains the common methods which are used to build the UI
 *
 * @author soundarrajan
 */
public class UiUtils {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UiUtils.class);

	/**
	 * Object for resource bundle
	 */
	private static ResourceBundle resourceBundle;

	/**
	 * Private constructor. This class should not be instantiated
	 */
	private UiUtils() {

	}

	/**
	 * Creates a panel which has common properties that is used across the
	 * module
	 *
	 * @param caption
	 *            Panel caption
	 * @return panel
	 */
	public static Panel addCommonPanel(String caption) {
		Panel panel = new Panel();
		panel.setCaption(caption);
		panel.setSizeFull();
		return panel;
	}

	/**
	 * Creates a GridLayout which has common properties that is used across the
	 * module
	 *
	 * @param column
	 *            No. of columns
	 * @param row
	 *            No. of rows
	 * @return GridLayout
	 */
	public static GridLayout addCommonGridLayout(int column, int row) {
		GridLayout gridLayout = new GridLayout(column, row);
		gridLayout.setMargin(true);
		gridLayout.setSpacing(true);
		return gridLayout;
	}

	/**
	 * Creates start date picker
	 *
	 * @return start date component
	 */
	public static PopupDateField addStartDate() {
		PopupDateField startDate = new PopupDateField();
		startDate.setDateFormat(DATE_FORMAT.getConstant());
		return startDate;
	}

	/**
	 * Creates date picker Use this for end date field or date field with no
	 * validation
	 *
	 * @return date component
	 */
	public static PopupDateField addDate() {
		PopupDateField endDate = new PopupDateField();
		endDate.setDateFormat(DATE_FORMAT.getConstant());
		return endDate;
	}

	/**
	 * To create a native select with indexed container
	 *
	 * @param indexedContainer
	 * @return
	 */
	public static NativeSelect addIndexedNativeSelect(IndexedContainer indexedContainer) {
		NativeSelect indexedNativeSelect = new NativeSelect();
		indexedNativeSelect.setContainerDataSource(indexedContainer);

		return indexedNativeSelect;
	}

	/**
	 * To add an empty space
	 *
	 * @return Label with empty space
	 */
	public static Label addSpaceLabel() {
		final Label space = new Label("&nbsp;", ContentMode.HTML);
		return space;
	}

	/**
	 * To create mode option group for data selection screen
	 *
	 * @return Data selection mode option group
	 */
	public static OptionGroup dataSelectionMode() {
		OptionGroup mode = new OptionGroup();
		mode.addItem(MODE_ADD.getConstant());
		mode.addItem(MODE_SEARCH.getConstant());
		mode.select(MODE_ADD.getConstant());
		return mode;
	}

	/**
	 * To create mode option group for data selection screen
	 *
	 * @return Data selection mode option group
	 */
	public static OptionGroup addBrandType() {
		OptionGroup brandType = new OptionGroup();
		brandType.addItem(CONTRACTED);
		brandType.addItem(ALL_BRANDS);
		brandType.select(CONTRACTED);
		return brandType;
	}

	/**
	 * Customizes the given window
	 *
	 * @param window
	 *            the window to be customized
	 */
	public static void customizeLookUps(Window window) {
		window.setClosable(true);
		window.setModal(true);
		window.center();
	}

	/**
	 * Adds a new textfield
	 *
	 * @return a new textfield
	 */
	public static TextField addTextField() {
		TextField textField = new TextField();
		textField.setImmediate(true);
		return textField;
	}

	/**
	 * Adds a new result Table
	 *
	 * @return a new resultTable
	 */
	public static Table addResultTable() {
		resourceBundle = ResourceBundle.getBundle(Constant.CONFIGURATIONS_DEFAULT);
		Table resultTable = new Table();
		resultTable.setSelectable(Boolean.parseBoolean(resourceBundle.getString("table_selectable")));
		resultTable.setSizeFull();

		resultTable.setWidth(Float.parseFloat(String.valueOf(resourceBundle.getString("max_width"))),
				Sizeable.Unit.valueOf(String.valueOf(resourceBundle.getString("max_width_unit"))));
		return resultTable;
	}

	/**
	 * Adds a new result Table
	 *
	 * @return a new resultTable
	 */
	public static ExtFilterTable addFilterResultTable() {
		resourceBundle = ResourceBundle.getBundle(Constant.CONFIGURATIONS_DEFAULT);
		ExtFilterTable resultTable = new ExtFilterTable();
		resultTable.setSelectable(Boolean.parseBoolean(resourceBundle.getString("table_selectable")));
		resultTable.setSizeFull();
		resultTable.setWidth(Float.parseFloat(String.valueOf(resourceBundle.getString("max_width"))),
				Sizeable.Unit.valueOf(String.valueOf(resourceBundle.getString("max_width_unit"))));
		return resultTable;
	}

	/**
	 * Creates a new label for fields with ';'
	 *
	 * @param value
	 *            The name of the label
	 * @return the label
	 */
	public static Label makeLabel(String value) {
		Label label = new Label(value + COLON);
		label.setSizeFull();
		return label;
	}

	/**
	 * Creates a new label for fields with ';'
	 *
	 * @param value
	 *            The name of the label
	 * @return the label
	 */
	public static Label makeComparisonLookupLabel(String value) {
		Label label = new Label(value + COLON);
		label.setWidth("147px");
		return label;
	}

	/**
	 * Creates a new label for fields with ';'
	 *
	 * @param value
	 *            The name of the label
	 * @param width
	 *            Width for the label
	 * @param unit
	 *            unit of the width
	 * @return the label
	 */
	public static Label makeLabel(String value, float width, Sizeable.Unit unit) {
		Label label = new Label(value + COLON);
		label.setWidth(width, unit);
		return label;
	}

	/**
	 * Adds a default native select with only -Select One- in list
	 *
	 * @return default native select
	 */
	public static NativeSelect addDefaultNativeSelect() {
		NativeSelect defaultNativeSelect = new NativeSelect();
		defaultNativeSelect.setNullSelectionAllowed(true);
		defaultNativeSelect.setNullSelectionItemId(SELECT_ONE);
		return defaultNativeSelect;
	}

	/**
	 * A common method to return Vertical or Horizontal or Form layouts with
	 * default configurations
	 *
	 * @param theClass
	 * @return VerticalLayout or HorizontalLayout or FormLayout
	 */
	public static AbstractOrderedLayout getLayout(AbstractOrderedLayout layout) {
		resourceBundle = ResourceBundle.getBundle(Constant.CONFIGURATIONS_DEFAULT);
		layout.setMargin(Boolean.parseBoolean(resourceBundle.getString("layout_margin")));
		layout.setSpacing(Boolean.parseBoolean(resourceBundle.getString("layout_spacing")));
		return layout;
	}

	/**
	 * To configure the customer and product hierarchy group builder tables
	 *
	 * @param availableTree
	 *            available customer or product table
	 * @param selectedTree
	 *            selected customer or product table
	 */
	public static void configureHierarchyTables(final TreeTable availableTree, final TreeTable selectedTree) {
		resourceBundle = ResourceBundle.getBundle(Constant.CONFIGURATIONS_DEFAULT);
		float tableWidth = Float.parseFloat(resourceBundle.getString("table_group_heirarchy_width"));
		availableTree.setWidth(tableWidth,
				Sizeable.Unit.valueOf(String.valueOf(resourceBundle.getString("default_unit"))));
		selectedTree.setWidth(tableWidth,
				Sizeable.Unit.valueOf(String.valueOf(resourceBundle.getString("default_unit"))));
	}

	/**
	 * To capitalize the captions of components
	 *
	 * @param value
	 *            the value to be capitalized
	 * @return
	 */
	public static String capitalizeCaptions(String value) {
		return value.toUpperCase();
	}

	/**
	 * Resets the given components
	 *
	 * @param components
	 *            varags which contains a collection of components whose values
	 *            are to be reseted
	 */
	public static void componentResetLogic(final Component... components) {
		if (components != null) {

			for (Component component : components) {
				if (component != null) {
					if (component instanceof TextField) {
						TextField tempTextField = (TextField) component;
						tempTextField.setValue(StringUtils.EMPTY);
					} else if (component instanceof Table) {
						Table tempTable = (Table) component;
						tempTable.getContainerDataSource().removeAllItems();
					} else if (component instanceof ExtFilterTable) {
						ExtFilterTable tempTable = (ExtFilterTable) component;
						tempTable.getContainerDataSource().removeAllItems();
						tempTable.resetFilters();
					} else if (component instanceof TreeTable) {
						TreeTable tempTreeTable = (TreeTable) component;
						tempTreeTable.getContainerDataSource().removeAllItems();
					} else if (component instanceof NativeSelect) {
						NativeSelect tempNativeSelect = (NativeSelect) component;
						tempNativeSelect.setValue(null);
					} else if (component instanceof OptionGroup) {
						OptionGroup optionGroup = (OptionGroup) component;
						for (Object itemId : optionGroup.getItemIds()) {
							optionGroup.unselect(itemId);
						}
					} else if (component instanceof PopupDateField) {
						PopupDateField dateField = (PopupDateField) component;
						dateField.setValue(null);
					}
				}
			}
		}
	}

	/**
	 * Parses a string value to integer NOTE: max length of integer that can be
	 * parsed without error is 9
	 *
	 * @param value
	 *            the string input
	 * @return parsed integer output
	 */
	public static int parseStringToInteger(final String value) {
		// max length of integer that can be parsed without error is 9
		return value.equals(StringUtils.EMPTY) ? 0
				: Integer.parseInt(value.replaceAll(REGEX_EXTRACT_DIGITS.getConstant(), StringUtils.EMPTY));
	}

	/**
	 * Generates HQL field from Table column name
	 *
	 * @param fieldName
	 * @param indicator
	 * @return
	 */
	public static String generateHqlField(String fieldName, final String indicator) {
		String finalValue = StringUtils.EMPTY;
		int loop = 0;
		fieldName = fieldName.toLowerCase();
		String[] splitArray = fieldName.split("_");
		if (indicator.equals("table")) {
			loop = 0;
		} else {
			finalValue = splitArray[0];
			loop = 1;
		}
		for (int i = loop, j = splitArray.length; i < j; i++) {
			finalValue += splitArray[i].replaceFirst(String.valueOf(splitArray[i].charAt(0)),
					String.valueOf(splitArray[i].charAt(0)).toUpperCase());
		}
		return finalValue;
	}

	public static List<Integer> convertStringListToIngeter(List<String> stringList) {
		List<Integer> integerList = new ArrayList<>();

		for (String sid : stringList) {
			integerList.add(DataTypeConverter.convertStringToInteger(sid));
		}

		return integerList;
	}

	public static List<Integer> convertStringListToParsedIngeter(List<String> stringList) {
		List<Integer> integerList = new ArrayList<>();

		for (String sid : stringList) {
			if (!StringUtils.EMPTY.equals(String.valueOf(parseStringToInteger(sid)))
					&& !Constant.DASH.equals(String.valueOf(parseStringToInteger(sid)))) {
				integerList.add(parseStringToInteger(sid));
			}
		}

		return integerList;
	}

	public static List<String> convertIngeterListToString(List<Integer> integetList) {
		List<String> stringList = new ArrayList<>();

		for (int sid : integetList) {
			stringList.add(String.valueOf(sid));
		}

		return stringList;
	}

	public static void getMessageNotification(String message) {
		Notification notif = new Notification(message, Notification.Type.HUMANIZED_MESSAGE);
		notif.setPosition(Position.MIDDLE_CENTER);
		notif.setStyleName(Constant.MY_STYLE);
		notif.show(Page.getCurrent());
	}

	public static String stringListToString(List<String> stringList) {
		StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
		if (stringList != null && !stringList.isEmpty()) {
			for (int loop = 0, limit = stringList.size(); loop < limit; loop++) {
				builder.append('\'');
				builder.append(stringList.get(loop));
				builder.append('\'');
				if (loop != (limit - 1)) {
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}

	public static List<String> objectListToStringList(List<Object> objectList) {
		List<String> stringList = new ArrayList<>();
		if (objectList != null) {
			for (Object object : objectList) {
				stringList.add(String.valueOf(object));
			}
		}
		return stringList;
	}

	public static String convertNullToEmpty(Object value) {
		String returnValue;
		if (value == null || CommonConstants.NULL.getConstant().equals(String.valueOf(value))) {
			returnValue = StringUtils.EMPTY;
		} else {
			returnValue = String.valueOf(value);
		}
		return returnValue;
	}

	public static Date parseDate(String value, String format) throws ParseException {
		LOGGER.debug("UiUtils - parseDate period= {}, format= {} " , value, format);
		Date date = null;
		value = convertNullToEmpty(value);
		SimpleDateFormat parse = new SimpleDateFormat(format);
		if (value != null && !StringUtils.EMPTY.equals(value) && !CommonConstants.NULL.getConstant().equals(value)) {
			date = parse.parse(value);
		}
		return date;
	}

	public static void setExtFilterTreeTableColumnWidth(final ExtFilterTreeTable table, final int columnWidth,
			final String screenName) {
		if (table != null) {
			for (Object propertyId : table.getVisibleColumns()) {
				table.setColumnWidth(propertyId, columnWidth);
				if ((screenName.equals(TAB_PPA_PROJECTION.getConstant()))
						&& (String.valueOf(propertyId).toLowerCase().contains("reset"))) {
					table.setColumnWidth(propertyId, 70);
				}
			}
		}
	}

	public static void setFreezePagedFilterTableColumnWidth(final ExtPagedFilterTable table, final int columnWidth,
			final String screenName) {
		if (table != null) {
			for (Object propertyId : table.getVisibleColumns()) {
				if (screenName.equals(TAB_ASSUMPTIONS.getConstant())) {
					if (String.valueOf(propertyId).equals("totalDiscountProjectedPV")) {
						table.setColumnWidth(propertyId, 195);
					} else {
						table.setColumnWidth(propertyId, columnWidth);
					}
				} else {
					table.setColumnWidth(propertyId, columnWidth);
				}
			}
		}
	}

	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String dateFormat = sdf.format(date);
		String[] splitDate = dateFormat.split("/");
		String formatedDate = splitDate[NumericConstants.TWO] + splitDate[1] + splitDate[0];
		return formatedDate;
	}

public static int getDataSelectionFormattedLevelNo(String value) {
		return StringUtils.isBlank(value) ? 0 : Integer.parseInt(value.split(" ")[1]);
    }
}
