/*
 * Copyright 2016 Mina.
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
package org.asi.calendarfield.client;

import java.util.Date;


/**
 *
 * @author Mina
 */
public class CalenderFieldUtil {

	
	public static final String DATE_SEPARATOR = "_";

	/**
	 * Helper class to inform the screen reader that the user changed the
	 * selected date. It sets the value of a field that is outside the view, and
	 * is defined as a live area. That way the screen reader recognizes the
	 * change and reads it to the user.
	 */
	public static class CalendarDate extends Date {

		public CalendarDate() {
			super();
		}

		public CalendarDate(int year, int month, int date) {
			super(year, month, date);
		}

		@Override
		public String toString() {
			return this.getYear() + DATE_SEPARATOR + this.getMonth() + DATE_SEPARATOR + this.getDate();
		}
	}

	public static CalendarDate getDateFromString(String str) {

		if (str != null) {
			String[] val = str.split(DATE_SEPARATOR);

			if (val.length == 3) {
				int year = Integer.parseInt(val[0]);
				int month = Integer.parseInt(val[1]);
				int date = Integer.parseInt(val[2]);

				return new CalendarDate(year, month, date);
			}
		}
		return null;
	}

	public static CalendarDate getCalendarDate(Date date) {
		if (date != null) {
			return new CalendarDate(date.getYear(), date.getMonth(), date.getDate());
		}
		return null;
	}

}
