package com.homedepot.toolrental.model;

public class DayTypesResult {
    private int businessDays = 0;
    private int weekendDays = 0;
    private int holidays = 0;

    public DayTypesResult(int businessDays, int weekendDays, int holidays) {
        this.businessDays = businessDays;
        this.weekendDays = weekendDays;
        this.holidays = holidays;
    }

    public int getBusinessDays() {
        return businessDays;
    }

    public void setBusinessDays(int businessDays) {
        this.businessDays = businessDays;
    }

    public int getWeekendDays() {
        return weekendDays;
    }

    public void setWeekendDays(int weekendDays) {
        this.weekendDays = weekendDays;
    }

    public int getHolidays() {
        return holidays;
    }

    public void setHolidays(int holidays) {
        this.holidays = holidays;
    }

    public int getChargeDaysForTool(Tool tool) {
        int chargeDays = this.getBusinessDays();
        // Add weekend days if the tool charges for weekends
        if (tool.getWeekendCharge()) {
            chargeDays += this.getWeekendDays();
        }

        // Add holidays if the tool charges for holidays
        if (tool.getHolidayCharge()) {
            chargeDays += this.getHolidays();
        }
        return chargeDays;

    }
}
