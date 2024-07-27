package com.homedepot.toolrental.utils;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;

public class HolidayUtil {

    private static final DayOfWeek[] weekends = new DayOfWeek[]{DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};

    public static boolean isHoliday(LocalDate localDate) {
        return isHoliday(localDate.atStartOfDay(ZoneId.systemDefault()));
    }

    /**
     * Checks if the given date is a business day.
     *
     * @param localDate the date to check
     * @return true if the date is a business day, false otherwise
     */
    public static boolean isBusinessDay(LocalDate localDate) {
        return !isHoliday(localDate.atStartOfDay(ZoneId.systemDefault()))
                && !Arrays.stream(weekends).anyMatch(n -> n == localDate.getDayOfWeek());
    }

    /**
     * Gets the next business day after the given date.
     *
     * @param testDate the date to start from
     * @return the next business day
     */
    public static LocalDate getNextBusinessDay(LocalDate testDate) {
        if (isBusinessDay(testDate)) {
            return testDate;
        } else {
            return getNextBusinessDay(testDate.plusDays(1));
        }
    }

    /**
     * Gets the day of the month for Labor Day in the given year.
     *
     * @param year the year to check
     * @return the day of the month for Labor Day
     */
    public static int getLaborDay(int year) {
        return LocalDate.now()
                .withYear(year)
                .withMonth(Month.SEPTEMBER.getValue())
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
                .getDayOfMonth();
    }

    /**
     * Checks if the given date is a holiday.
     *
     * @param dateTime the date to check
     * @return true if the date is a holiday, false otherwise
     */
    public static boolean isHoliday(ZonedDateTime dateTime) {
        if (Month.JULY.equals(dateTime.getMonth()) && dateTime.getDayOfMonth() == 4) { // 4th of July
            return true;
        } else if (Month.SEPTEMBER.equals(dateTime.getMonth()) && dateTime.getDayOfMonth() == getLaborDay(dateTime.getYear())) {
            return true;
        }
        return false;
    }

}
