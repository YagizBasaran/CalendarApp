package model;

import java.util.Date;

public class Activity {
    private String userID;
    private String activityDescription;
    private Date activityDate;
    private Date startTime;
    private Date finishTime;
    private Boolean isUrgent;
    private Boolean isPeriodic;
    private int isAttended;
    private int period;
    
    
    
    
    public Activity() {

    }

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getActivityDescription() {
        return activityDescription;
    }
    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }
    public Date getActivityDate() {
        return activityDate;
    }
    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getFinishTime() {
        return finishTime;
    }
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    public void setUrgent(Boolean isUrgent) {
        this.isUrgent = isUrgent;
    }
    public Boolean getUrgent() {
        return isUrgent;
    }
    public void setPeriodic(Boolean isPeriodic) {
        this.isPeriodic = isPeriodic;
    }
    public Boolean getPeriodic() {
        return isPeriodic;
    }
    public void setIsAttended(int isAttended) {
        this.isAttended = isAttended;
    }
    public int getIsAttended() {
        return isAttended;
    }
    public void setPeriod(int period) {
        this.period = period;
    }
    public int getPeriod() {
        return period;
    }

    
   }
