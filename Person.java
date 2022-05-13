package model;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String userid;
    private String password;
    private ArrayList<Activity> activitiesList;
   
    
   // private String calendarType;
    private String nationality;
    private String religion;
    private String email;
    
    
    public Person() {


        activitiesList=new ArrayList<Activity>();

      
    }
    
    public String getUserId() {
        return userid;
    }
    public void setUserId(String userId) {
        this.userid = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void addActivity(Activity activity) {
        activitiesList.add(activity);   
      }
    public List<Activity> getActivities() {
        return activitiesList;    
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getReligion() {
        return religion;
    }
    public void setreligion(String religion) {
        this.religion = religion;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

   }
