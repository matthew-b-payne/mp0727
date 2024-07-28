package com.homedepot.toolrental.utils;

import com.homedepot.toolrental.model.DayTypesResult;

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
     * Checks if the given date is a business day.
     *
     * @param localDate the date to check
     * @return true if the date is a business day, false otherwise
     */
    public static boolean isWeekendDay(LocalDate localDate) {
        return Arrays.stream(weekends).anyMatch(n -> n == localDate.getDayOfWeek());
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

        Month month = dateTime.getMonth();
        int day = dateTime.getDayOfMonth();

        if (month == Month.JULY && (day == 4 && !isWeekendDay(dateTime.toLocalDate()) ||
                (dateTime.getDayOfWeek() == DayOfWeek.FRIDAY && day == 3 && isWeekendDay(dateTime.plusDays(1).toLocalDate())) ||
                (dateTime.getDayOfWeek() == DayOfWeek.MONDAY && day == 5 && isWeekendDay(dateTime.minusDays(1).toLocalDate())))) {
            return true;
        } else if (Month.SEPTEMBER.equals(dateTime.getMonth()) && dateTime.getDayOfMonth() == getLaborDay(dateTime.getYear())) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the number of business days, weekend days, and holidays between two dates.
     *
     * @param checkOutDate the start date
     * @param returnDate   the end date
     * @return an object with the counts: businessDays, weekendDays, holidays
     */
    public static DayTypesResult calculateDayTypes(LocalDate checkOutDate, LocalDate returnDate) {
        int businessDays = 0;
        int weekendDays = 0;
        int holidays = 0;

        for (LocalDate date = checkOutDate; !date.isAfter(returnDate); date = date.plusDays(1)) {
            if (isHoliday(date)) {
                holidays++;
            } else if (isWeekendDay(date)) {
                weekendDays++;
            } else if (isBusinessDay(date)) {
                businessDays++;
            }
        }

        return new DayTypesResult(businessDays, weekendDays, holidays);
    }
}
