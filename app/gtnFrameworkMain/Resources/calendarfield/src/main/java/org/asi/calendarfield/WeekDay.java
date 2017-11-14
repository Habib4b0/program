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
package org.asi.calendarfield;

/**
 *
 * @author Mina
 */
public enum WeekDay {

    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);
    int day;

    WeekDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
    
    /**
         * Gets the WeekDay.
         *
     * @param day
         * @return the WeekDay
         */
        public static  WeekDay getWeekDay(int day) {
        switch (day) {
            case 0:
                return WeekDay.SUNDAY;
            case 1:
                return WeekDay.MONDAY;
            case 2:
                return WeekDay.TUESDAY;
            case 3:
                return WeekDay.WEDNESDAY;
            case 4:
                return WeekDay.THURSDAY;
            case 5:
                return WeekDay.FRIDAY;
            case 6:
                return WeekDay.SATURDAY;
            default:
                return null;
        }
        }
    
}
