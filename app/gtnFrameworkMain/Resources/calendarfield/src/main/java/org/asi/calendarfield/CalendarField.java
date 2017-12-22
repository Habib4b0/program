/*
 * Copyright 2016 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asi.calendarfield;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import org.asi.calendarfield.client.CalenderFieldUtil;
import org.asi.calendarfield.client.CalendarFieldState;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Validator;
import com.vaadin.v7.event.FieldEvents;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.v7.shared.ui.datefield.DateFieldConstants;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.LegacyComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.asi.calendarfield.client.CalenderFieldUtil.CalendarDate;

/**
 *
 * @author Abhiram
 */
public class CalendarField extends AbstractField<List> implements
FieldEvents.BlurNotifier, FieldEvents.FocusNotifier, LegacyComponent {

	public CalendarField() {
		setValue(new ArrayList());
	}

	public CalendarField(String caption) {
		this();
		setCaption(caption);
	}
	boolean yearResolution = false;
	String focusYear = null;
	String focusMonth = null;
	String focusDate = null;
	private Matrix matrix = Matrix.ROW_3xCOL_4;

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		if (matrix != null) {
			this.matrix = matrix;
			markAsDirty();
		}
	}

	public static enum Matrix {

		ROW_1xCOL_12(1, 12),
		ROW_2xCOL_6(2, 6),
		ROW_3xCOL_4(3, 4),
		ROW_4xCOL_3(4, 3),
		ROW_6xCOL_2(6, 2),
		ROW_12xCOL_1(12, 1);
		int row;
		int col;

		Matrix(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}
	}
	private boolean lenient = false;
	/**
	 * Determines if week numbers are shown in the date selector.
	 */
	private boolean showISOWeekNumbers = false;
	private TimeZone timeZone = null;
	Set<String> values = new HashSet<String>();
	/**
	 * Determines whether the ValueChangeEvent should be fired. Used to prevent
	 * firing the event when UI has invalid string until uiHasValidDateString
	 * flag is set
	 */
	private boolean preventValueChangeEvent = false;
	private List<WeekDay> disableWeekDays = new ArrayList<WeekDay>();
	private List<Integer> disableMonthlyDays = new ArrayList<Integer>();
	private List<Date> disableDates = new ArrayList<Date>();

	public void setDisableWeekDays(WeekDay... days) {
		this.disableWeekDays = Arrays.asList(days);
		markAsDirty();
	}

	public void setDisableMonthlyDays(Integer... days) {
		this.disableMonthlyDays = Arrays.asList(days);
		markAsDirty();
	}

	public List<Date> getDisabledDates() {
		return disableDates;
	}

	public void setDisableDates(Date... dates) {
		this.disableDates = Arrays.asList(dates);
		markAsDirty();
	}

	public void addDisableDates(Date... dates) {
		if (dates != null && dates.length != 0) {
			this.disableDates.addAll(Arrays.asList(dates));
			markAsDirty();
		}
	}

	public void addDisableDate(Date date) {
		if (date != null) {
			this.disableDates.add(date);
			markAsDirty();
		}
	}

	public void removeDisableDates(Date... dates) {
		if (dates != null && dates.length != 0) {
			this.disableDates.removeAll(Arrays.asList(dates));
			markAsDirty();
		}
	}

	public void removeDisableDate(Date date) {
		if (date != null) {
			this.disableDates.remove(date);
			markAsDirty();
		}
	}

	public List<WeekDay> getDisabledWeekDays() {
		return disableWeekDays;
	}

	public List<Integer> getDisabledMonthlyDays() {
		return disableMonthlyDays;
	}
	private List<WeekDay> selectedWeekDays = new ArrayList<WeekDay>();
	private List<Integer> selectedMonthlyDays = new ArrayList<Integer>();
	private boolean valueFromDate=true;
	private boolean updateDateValue=true;
	public void setSelectedWeekDays(WeekDay... days) {
		List<WeekDay> removedWeekDays = new ArrayList<WeekDay>(this.selectedWeekDays);
		this.selectedWeekDays = new ArrayList<WeekDay>(Arrays.asList(days));
		if (isUpdateDateValue()) {
			boolean update = updateSelectedDays(removedWeekDays, new ArrayList<Integer>());
			if (update) {
				updateValue();
			}
		}
	}
	public void clearAllValue() {
		if (selectedWeekDays != null) {
			selectedWeekDays.clear();
		}
		if (selectedMonthlyDays != null) {
			selectedMonthlyDays.clear();
		}
		if (values != null) {
			values.clear();
		}
		super.setValue(new ArrayList(), false);
	}
	private void updateValue(){
		List<Date> dates = new ArrayList<Date>();
		for (String string : values) {
			dates.add(CalenderFieldUtil.getDateFromString(string));
		}
		valueFromDate=false;
		setValue(dates);
		valueFromDate=true;
	}
	private void updateFromDateValue(List dates){
		for (Object dt : dates) {
			if(dt instanceof Date){
				values.add(CalenderFieldUtil.getCalendarDate((Date)dt).toString());
			}
		}
	}

	public void setSelectedMonthlyDays(Integer... days) {
		List<Integer> removedMonthlyDays = new ArrayList<Integer>(this.selectedMonthlyDays);
		this.selectedMonthlyDays = new ArrayList<Integer>(Arrays.asList(days));
		if (isUpdateDateValue()) {
			boolean update = updateSelectedDays(new ArrayList<WeekDay>(), removedMonthlyDays);
			if (update) {
				updateValue();
			}
		}
	}

	public boolean isUpdateDateValue() {
		return updateDateValue;
	}

	public void setUpdateDateValue(boolean updateDateValue) {
		this.updateDateValue = updateDateValue;
	}

	public List<WeekDay> getSelectedWeekDays() {
		return selectedWeekDays;
	}

	public List<Integer> getSelecteddMonthlyDays() {
		return selectedMonthlyDays;
	}

	public boolean updateSelectedDays(List<WeekDay> removeWeekDays,List<Integer> removeMonthlyDays) {
		boolean ret = false;
		if ((!selectedWeekDays.isEmpty()||!selectedMonthlyDays.isEmpty()||!removeWeekDays.isEmpty()||!removeMonthlyDays.isEmpty()) && getState(false).rangeStart != null && getState(false).rangeEnd != null) {
			CalendarDate startDate = CalenderFieldUtil.getCalendarDate(getState(false).rangeStart);
			CalendarDate endDate = CalenderFieldUtil.getCalendarDate(getState(false).rangeEnd);
			CalendarDate dt = (CalendarDate) startDate.clone();
			while (dt.before(endDate) || dt.toString().equals(endDate.toString())) {
				boolean pass=false;
				if(isAcceptedRemovedWeekDays(dt.getDay(), removeWeekDays)||isAcceptedRemovedMonthlyDays(dt.getDate(), removeMonthlyDays)){
					values.remove(dt.toString());
					pass=true;
				}
				if (isAcceptedSelectedWeekDays(dt.getDay())||isAcceptedSelectedMonthlyDays(dt.getDate())) {
					values.add(dt.toString());
					if(pass){
						pass=false;
					}else{
						pass=true;
					}
				}
				if(pass){
					ret=true;
				}
				dt.setDate(dt.getDate() + 1);
			}
		}
		return ret;
	}

	private boolean isAcceptedSelectedMonthlyDays(int day) {
		if (selectedMonthlyDays == null || selectedMonthlyDays.isEmpty()) {
			return false;
		}
		return selectedMonthlyDays.contains(day);
	}
	private boolean isAcceptedRemovedMonthlyDays(int day,List<Integer> removeMonthlyDays) {
		if (removeMonthlyDays == null || removeMonthlyDays.isEmpty()) {
			return false;
		}
		return removeMonthlyDays.contains(day);
	}

	private boolean isAcceptedSelectedWeekDays(int day) {
		if (selectedWeekDays == null || selectedWeekDays.isEmpty()) {
			return false;
		}
		return selectedWeekDays.contains(WeekDay.getWeekDay(day));
	}
	private boolean isAcceptedRemovedWeekDays(int day,List<WeekDay> removeWeekDays) {
		if (removeWeekDays == null || removeWeekDays.isEmpty()) {
			return false;
		}
		return removeWeekDays.contains(WeekDay.getWeekDay(day));
	}

	/* Component basic features */

	/*
	 * Paints this component. Don't add a JavaDoc comment here, we use the
	 * default documentation from implemented interface.
	 */
	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		target.addAttribute("isYear", isYearResolution());
		target.addAttribute("totalRow", getMatrix().getRow());
		target.addAttribute("totalCol", getMatrix().getCol());

		// Adds the locale as attribute
		final Locale l = getLocale();
		if (l != null) {
			target.addAttribute("locale", l.toString());
		}

		if (!isLenient()) {
			target.addAttribute("strict", true);
		}

		target.addAttribute(DateFieldConstants.ATTR_WEEK_NUMBERS,
				isShowISOWeekNumbers() && false);
		if (focusYear != null) {
			target.addAttribute("focusYear", focusYear);
		}
		if (focusMonth != null) {
			target.addAttribute("focusMonth", focusMonth);
		}
		if (focusDate != null) {
			target.addAttribute("focusDate", focusDate);
		}


		String[] arr = new String[disableWeekDays.size()];
		int i = 0;
		for (WeekDay day : disableWeekDays) {
			arr[i] = "" + day.getDay();
			i++;
		}
		target.addAttribute("disabledWeekDays", arr);
		String[] arr1 = new String[disableMonthlyDays.size()];
		int j = 0;
		for (Integer day : disableMonthlyDays) {
			arr1[j] = "" + day;
			j++;
		}

		target.addAttribute("disabledMonthlyDays", arr1);

		target.startTag("disabledDates");
		for (Date dt : getDisabledDates()) {
			target.startTag("value");
			target.addAttribute("year", dt.getYear());
			target.addAttribute("month", dt.getMonth());
			target.addAttribute("date", dt.getDate());
			target.endTag("value");
		}
		target.endTag("disabledDates");

		target.startTag("paintvalues");
		for (Object col : getValue()) {
			Date dt = (Date) col;
			target.startTag("value");
			target.addAttribute("year", dt.getYear());
			target.addAttribute("month", dt.getMonth());
			target.addAttribute("date", dt.getDate());
			target.endTag("value");
		}
		target.endTag("paintvalues");

	}

	@Override
	protected boolean shouldHideErrors() {
		return super.shouldHideErrors();
	}

	@Override
	protected CalendarFieldState getState() {
		return (CalendarFieldState) super.getState();
	}

	@Override
	protected CalendarFieldState getState(boolean markAsDirty) {
		return (CalendarFieldState) super.getState(markAsDirty);
	}

	/**
	 * Sets the start range for this component. If the value is set before this
	 * date (taking the resolution into account), the component will not
	 * validate. If
	 * <code>startDate</code> is set to
	 * <code>null</code>, any value before
	 * <code>endDate</code> will be accepted by the range
	 *
	 * @param startDate - the allowed range's start date
	 */
	public void setRangeStart(Date startDate) {
		if (startDate != null && getState().rangeEnd != null
				&& startDate.after(getState().rangeEnd)) {
			throw new IllegalStateException(
					"startDate cannot be later than endDate");
		}
		getState().rangeStart = startDate;
	}

	/**
	 * Sets the end range for this component. If the value is set after this
	 * date (taking the resolution into account), the component will not
	 * validate. If
	 * <code>endDate</code> is set to
	 * <code>null</code>, any value after
	 * <code>startDate</code> will be accepted by the range.
	 *
	 * @param endDate - the allowed range's end date (inclusive, based on the
	 * current resolution)
	 */
	public void setRangeEnd(Date endDate) {
		if (endDate != null && getState().rangeStart != null
				&& getState().rangeStart.after(endDate)) {
			throw new IllegalStateException(
					"endDate cannot be earlier than startDate");
		}
		// rangeEnd = endDate;
		getState().rangeEnd = endDate;
	}

	/**
	 * Returns the precise rangeStart used.
	 *
	 * @param startDate
	 *
	 */
	public Date getRangeStart() {
		return getState(false).rangeStart;
	}

	/**
	 * Returns the precise rangeEnd used.
	 *
	 * @param startDate
	 */
	public Date getRangeEnd() {
		return getState(false).rangeEnd;
	}

	/*
	 * Invoked when a variable of the component changes. Don't add a JavaDoc
	 * comment here, we use the default documentation from implemented
	 * interface.
	 */
	@Override
	public void changeVariables(Object source, Map<String, Object> variables) {

		if (!isReadOnly()
				&& (variables.containsKey("values"))) {

			focusYear = (String) variables.get("focusYear");
			focusMonth = (String) variables.get("focusMonth");
			focusDate = (String) variables.get("focusDate");
			String[] valuesFromVariable = (String[]) variables.get("values");
			List<Date> dates = new ArrayList<Date>();
			values.clear();
			for (String value : valuesFromVariable) {
				CalendarDate dt = CalenderFieldUtil.getDateFromString(value);
				values.add(value);
				dates.add(dt);
			}
			setValue(dates, true);
		}

		if (variables.containsKey(FocusEvent.EVENT_ID)) {
			fireEvent(new FocusEvent(this));
		}

		if (variables.containsKey(BlurEvent.EVENT_ID)) {
			fireEvent(new BlurEvent(this));
		}
	}

	/*
	 * only fires the event if preventValueChangeEvent flag is false
	 */
	@Override
	protected void fireValueChange(boolean repaintIsNotNeeded) {
		if (!preventValueChangeEvent) {
			super.fireValueChange(repaintIsNotNeeded);
		}
	}


	/* Property features */

	/*
	 * Gets the edited property's type. Don't add a JavaDoc comment here, we use
	 * the default documentation from implemented interface.
	 */
	@Override
	public Class<List> getType() {
		return List.class;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.vaadin.ui.AbstractField#setValue(java.lang.Object, boolean)
	 */
	@Override
	protected void setValue(List newValue, boolean repaintIsNotNeeded)
			throws Property.ReadOnlyException {

		/*
		 * First handle special case when the client side component have a date
		 * string but value is null (e.g. unparsable date string typed in by the
		 * user). No value changes should happen, but we need to do some
		 * internal housekeeping.
		 */
		if (newValue == null) {
			newValue = new ArrayList();
		}
		if(valueFromDate){
			updateFromDateValue(newValue);
		}
		super.setValue(newValue, repaintIsNotNeeded);
	}


	/**
	 * Specifies whether or not date/time interpretation in component is to be
	 * lenient.
	 *
	 * @see Calendar#setLenient(boolean)
	 * @see #isLenient()
	 *
	 * @param lenient true if the lenient mode is to be turned on; false if it
	 * is to be turned off.
	 */
	public void setLenient(boolean lenient) {
		this.lenient = lenient;
		markAsDirty();
	}

	/**
	 * Returns whether date/time interpretation is to be lenient.
	 *
	 * @see #setLenient(boolean)
	 *
	 * @return true if the interpretation mode of this calendar is lenient;
	 * false otherwise.
	 */
	public boolean isLenient() {
		return lenient;
	}

	@Override
	public void addFocusListener(FocusListener listener) {
		addListener(FocusEvent.EVENT_ID, FocusEvent.class, listener,
				FocusListener.focusMethod);
	}

	/**
	 * @deprecated As of 7.0, replaced by
	 * {@link #addFocusListener(FocusListener)}
	 *
	 */
	
	@Deprecated
	public void addListener(FocusListener listener) {
		addFocusListener(listener);
	}

	@Override
	public void removeFocusListener(FocusListener listener) {
		removeListener(FocusEvent.EVENT_ID, FocusEvent.class, listener);
	}

	/**
	 * @deprecated As of 7.0, replaced by
	 * {@link #removeFocusListener(FocusListener)}
	 *
	 */
	
	@Deprecated
	public void removeListener(FocusListener listener) {
		removeFocusListener(listener);
	}

	@Override
	public void addBlurListener(BlurListener listener) {
		addListener(BlurEvent.EVENT_ID,BlurEvent.class, listener,
				BlurListener.blurMethod);
	}

	/**
	 * @deprecated As of 7.0, replaced by {@link #addBlurListener(BlurListener)}
	 *
	 */
	@Deprecated
	public void addListener(BlurListener listener) {
		addBlurListener(listener);
	}

	@Override
	public void removeBlurListener(BlurListener listener) {
		removeListener(BlurEvent.EVENT_ID,BlurEvent.class, listener);
	}

	/**
	 * @deprecated As of 7.0, replaced by
	 * {@link #removeBlurListener(BlurListener)}
	 *
	 */
	@Deprecated
	public void removeListener(BlurListener listener) {
		removeBlurListener(listener);
	}

	/**
	 * Checks whether ISO 8601 week numbers are shown in the date selector.
	 *
	 * @return true if week numbers are shown, false otherwise.
	 */
	public boolean isShowISOWeekNumbers() {
		return showISOWeekNumbers;
	}

	/**
	 * Sets the visibility of ISO 8601 week numbers in the date selector. ISO
	 * 8601 defines that a week always starts with a Monday so the week numbers
	 * are only shown if this is the case.
	 *
	 * @param showWeekNumbers true if week numbers should be shown, false
	 * otherwise.
	 */
	public void setShowISOWeekNumbers(boolean showWeekNumbers) {
		showISOWeekNumbers = showWeekNumbers;
		markAsDirty();
	}

	/**
	 * Sets the time zone used by this date field. The time zone is used to
	 * convert the absolute time in a Date object to a logical time displayed in
	 * the selector and to convert the select time back to a Date object.
	 *
	 * If no time zone has been set, the current default time zone returned by
	 * {@code TimeZone.getDefault()} is used.
	 *
	 * @see #getTimeZone()
	 * @param timeZone the time zone to use for time calculations.
	 */
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
		markAsDirty();
	}

	/**
	 * Gets the time zone used by this field. The time zone is used to convert
	 * the absolute time in a Date object to a logical time displayed in the
	 * selector and to convert the select time back to a Date object.
	 *
	 * If {@code null} is returned, the current default time zone returned by
	 * {@code TimeZone.getDefault()} is used.
	 *
	 * @return the current time zone
	 */
	public TimeZone getTimeZone() {
		return timeZone;
	}

	public static class UnparsableDateString extends Validator.InvalidValueException {

		public UnparsableDateString(String message) {
			super(message);
		}
	}

	public boolean isYearResolution() {
		return yearResolution;
	}

	public void setYearResolution(boolean yearResolution) {
		this.yearResolution = yearResolution;
	}
}