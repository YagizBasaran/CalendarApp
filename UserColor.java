package model;

public class UserColor {
    private String username;
    private String attended;
    private String missed;
    private String upcoming;
    private String periodic;
    private String urgent;
    
    public UserColor() {

    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAttended() {
        return attended;
    }
    public void setAttended(String attended) {
        this.attended = attended;
    }
    public String getMissed() {
        return missed;
    }
    public void setMissed(String missed) {
        this.missed = missed;
    }
    public String getUpcoming() {
        return upcoming;
    }
    public void setUpcoming(String upcoming) {
        this.upcoming = upcoming;
    }
    public String getPeriodic() {
        return periodic;
    }
    public void setPeriodic(String periodic) {
        this.periodic = periodic;
    }
    public String getUrgent() {
        return urgent;
    }
    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

   }
