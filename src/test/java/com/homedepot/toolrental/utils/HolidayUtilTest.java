package com.homedepot.toolrental.utils;

import com.homedepot.toolrental.model.DayTypesResult;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class HolidayUtilTest {

    @Test
    void testIsHoliday_July4() {
        LocalDate july4 = LocalDate.of(2024, 7, 4);
        assertTrue(HolidayUtil.isHoliday(july4));

        LocalDate july3 = LocalDate.of(2015, 7, 3); // july 4th observed
        assertTrue(HolidayUtil.isHoliday(july3));

        LocalDate july42015 = LocalDate.of(2015, 7, 4); // july 4th on weekend
        assertFalse(HolidayUtil.isHoliday(july42015));
    }

    @Test
    void testIsHoliday_LaborDay() {
        LocalDate laborDay = LocalDate.of(2024, 9, 2); // Labor Day in 2024
        assertTrue(HolidayUtil.isHoliday(laborDay));

    }

    @Test
    void testIsHoliday_NonHoliday() {
        LocalDate nonHoliday = LocalDate.of(2024, 8, 1);
        assertFalse(HolidayUtil.isHoliday(nonHoliday));
    }

    @Test
    void testIsBusinessDay_Weekday() {
        LocalDate july2nd = LocalDate.of(2024, 7, 2); // A Wednesday
        assertTrue(HolidayUtil.isBusinessDay(july2nd));
    }

    @Test
    void testIsBusinessDay_Weekend() {
        LocalDate weekend = LocalDate.of(2024, 7, 6); // A Saturday
        assertFalse(HolidayUtil.isBusinessDay(weekend));
    }

    @Test
    void testIsBusinessDay_Holiday() {
        LocalDate holiday = LocalDate.of(2024, 7, 4); // 4th of July
        assertFalse(HolidayUtil.isBusinessDay(holiday));

        LocalDate july3rd = LocalDate.of(2015, 7, 3);
        assertFalse(HolidayUtil.isBusinessDay(july3rd)); // july 4th is actually observed here
    }

    @Test
    void testGetNextBusinessDay() {
        LocalDate start = LocalDate.of(2024, 7, 5); // A Friday
        LocalDate nextBusinessDay = HolidayUtil.getNextBusinessDay(start);
        assertEquals(LocalDate.of(2024, 7, 5), nextBusinessDay);

        LocalDate holidayStart = LocalDate.of(2024, 7, 4); // A Thursday which is a holiday
        LocalDate holidayNextBusinessDay = HolidayUtil.getNextBusinessDay(holidayStart);
        assertEquals(LocalDate.of(2024, 7, 5), holidayNextBusinessDay);
    }

    @Test
    void testGetLaborDay() {
        int laborDay2024 = HolidayUtil.getLaborDay(2024);
        assertEquals(2, laborDay2024);
    }

    @Test
    void testIsHoliday_ZonedDateTime() {
        ZonedDateTime july4 = ZonedDateTime.of(LocalDate.of(2024, 7, 4), LocalDate.now().atStartOfDay().toLocalTime(), ZoneId.systemDefault());
        assertTrue(HolidayUtil.isHoliday(july4));

        ZonedDateTime nonHoliday = ZonedDateTime.of(LocalDate.of(2024, 8, 1), LocalDate.now().atStartOfDay().toLocalTime(), ZoneId.systemDefault());
        assertFalse(HolidayUtil.isHoliday(nonHoliday));
    }


    @Test
    void testCalculateDayTypes() {
        LocalDate checkOutDate = LocalDate.of(2024, 7, 1);
        LocalDate returnDate = LocalDate.of(2024, 7, 10);

        // Define the expected result
        int expectedBusinessDays = 7;
        int expectedWeekendDays = 2;
        int expectedHolidays = 1; // Assuming July 4th

        // Perform the actual method call
        DayTypesResult result = HolidayUtil.calculateDayTypes(checkOutDate, returnDate);

        // Assert the results
        assertEquals(expectedBusinessDays, result.getBusinessDays(), "Business days count should be 7");
        assertEquals(expectedWeekendDays, result.getWeekendDays(), "Weekend days count should be 2");
        assertEquals(expectedHolidays, result.getHolidays(), "Holidays count should be 1");
    }

}
