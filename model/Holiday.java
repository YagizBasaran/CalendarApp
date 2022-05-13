package model;

import java.util.Date;

public class Holiday {
    private String holidayDescription;
    private String holidayType;
    private String holidayNationality;
    private String holidayReligion;
    private Date startDay;
    private Date endDay;

    
    
    
    public Holiday() {

    }

    
    public String getHolidayDescription() {
        return holidayDescription;
    }
    public void setHolidayDescription(String holidayDescription) {
        this.holidayDescription = holidayDescription;
    }
    public String getHolidayType() {
        return holidayType;
    }
    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }
    public Date getStartDay() {
        return startDay;
    }
    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }
    public Date getEndDay() {
        return endDay;
    }
    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }
    public void setHolidayNationality(String holidayNationality) {
        this.holidayNationality = holidayNationality;
    }
    public String getHolidayNationality() {
        return holidayNationality;
    }
    public void setHolidayReligion(String holidayReligion) {
        this.holidayReligion = holidayReligion;
    }
    public String getHolidayReligion() {
        return holidayReligion;
    }

    
   }
