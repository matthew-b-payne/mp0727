package com.homedepot.toolrental.utils;

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
        LocalDate weekday = LocalDate.of(2024, 7, 3); // A Wednesday
        assertTrue(HolidayUtil.isBusinessDay(weekday));
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
}
