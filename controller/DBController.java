package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.*;

public class DBController {

  public static String DBConnection_String="remotemysql.com:3306/i3AWa3kyAZ";
  public static String DBConnection_user="i3AWa3kyAZ";
  public static String DBConnection_pass="5ctlFHqcwD";
  
  public DBController() {
    
    initController();
  }

  public void initController() {

    // Log user in order to continue
    

  }

  // This part can be change and e-mail 
  public Person getPasswordDB(String user) {
    Person p=new Person();
       try 
          {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString="";
                sqlString = "Select username,password,email from person where username='" + user + "'";
                System.out.println(sqlString);
                ResultSet rs =  stmt.executeQuery(sqlString); 
               
                while (rs.next()) {
                  
                  p.setUserId(rs.getString(1));
                  p.setPassword(rs.getString(2));
                  p.setEmail(rs.getString(3));

                  con.close();
                  return p;
                
                    
                }
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
                return null;
            }
            return null;

  }
  public void selectDB() {
          try 
          {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString="";
                sqlString = "Select * from person";
                
                ResultSet rs =  stmt.executeQuery(sqlString); 
               
                while (rs.next()) {
                    
               //   System.out.println(rs.getString(2));  
                //  s.getModel().addRow(new Object[]{rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) });
                    
                }
                 con.close();
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
  }
  // Check Username & Password
  public Person checkAuthentication(String user, String pass) {
    Person p=new Person();
    try 
          {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
                Statement stmt = con.createStatement();
                String sqlString="";
                sqlString = "Select * from person where username='"+ user +"' and password='"+ pass +"'";
               
                ResultSet rs =  stmt.executeQuery(sqlString); 
                while (rs.next()) {
                  //user authenticated: Populate user
        
                  p.setUserId(rs.getString(1));
                  p.setPassword(rs.getString(2));
                  p.setreligion(rs.getString(3));
                  p.setNationality(rs.getString(4));
                  p.setEmail(rs.getString(5));
                  con.close();
                  return p;
                }
                
                 return null;
            } catch (Exception ex) {
                
                System.out.println(ex);
            }
            return null;
  }


  // Update nationality
  public Boolean saveNationalityDB(String p, String n) {
    try 
          {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
                String sqlString="";
                sqlString = "update person set nationality='"+ n +"' where username='" + p + "'";
              
                PreparedStatement pstmt = null;
               
                pstmt = con.prepareStatement(sqlString);
                int status = pstmt.executeUpdate();
                if(status > 0) {
                    JOptionPane.showMessageDialog(null, n +" Nationality set to " +  p +" !!");
                }
                 
                con.close();
                return true;
                
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "DB Error !! Nationality NOT set !!");
                System.out.println(ex);
            }
            return false;
  }
// Update religion
public Boolean saveReligionDB(String p, String n) {
  try 
        {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
              String sqlString="";
              sqlString = "update person set religion='"+ n +"' where username='" + p + "'";
            
              PreparedStatement pstmt = null;
              pstmt = con.prepareStatement(sqlString);
              int status = pstmt.executeUpdate();
              if(status > 0) {
                  JOptionPane.showMessageDialog(null, n +" Religion set to " +  p +" !!");
              }
               
              con.close();
              return true;
              
              
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "DB Error !! Religion NOT set !!");
              System.out.println(ex);
          }
          return false;
  }

  // Update attended
  public Boolean saveAttendanceDB(Activity a, String username, int attended) {
    try 
          {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
                String sqlString="";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sqlString = "update activity set isAttended="+ attended +" where owner='" + username + "' and activityName='" + a.getActivityDescription()  
                            + "' and startTime='"+ simpleDateFormat.format(a.getStartTime()) +"' and endTime='" + simpleDateFormat.format(a.getFinishTime()) + "'";
              
                PreparedStatement pstmt = null;
               //System.out.println( sqlString );
                pstmt = con.prepareStatement(sqlString);
                int status = pstmt.executeUpdate();
                if(status > 0) {
                    JOptionPane.showMessageDialog(null, "Noted !!");
                }
                 
                con.close();
                return true;
                
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "DB Error !! Nationality NOT set !!");
                System.out.println(ex);
            }
            return false;
  }

  public Boolean signUpDB(String username,String pass,String email) {

    try 
          {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
                String sqlString="";
               
                sqlString = "insert into person (username,password,email)  values ('"+ username+"','"+pass+"','"+ email+"')";
              
                PreparedStatement pstmt = null;
               
                pstmt = con.prepareStatement(sqlString);
                int status = pstmt.executeUpdate();
                if(status > 0) {
                    JOptionPane.showMessageDialog(null, "User created. Please login !!");
                }
                 
                con.close();
                return true;
                
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "DB Error !! User NOT created !!");
                System.out.println(ex);
            }
            return false;
  }
// Insert quick into table
public Boolean insertQuickDB(String owner, String quick) {
  try 
        {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
              String sqlString="";
             
              sqlString = "insert into quickevent (eventName,owner)  values ('"+ quick+"','"+ owner+"')";
          
              PreparedStatement pstmt = null;
             
              pstmt = con.prepareStatement(sqlString);
              int status = pstmt.executeUpdate();
              if(status > 0) {
                  JOptionPane.showMessageDialog(null, "Quick Activity saved !!");
              }
               
              con.close();
              return true;
              
              
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "DB Error !! Activity NOT saved !!");
              System.out.println(ex);
          }
          return false;
}

  // Insert activity into table
  public Boolean insertActivityDB(Person p, Activity a) {
    try 
          {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
                String sqlString="";
               
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sqlString = "insert into activity (activityName,owner,startTime,endTime,isPeriodic,isUrgent,isAttended)  values ('"+ a.getActivityDescription()+"','"+ p.getUserId()+"','"+ simpleDateFormat.format(a.getStartTime())+"','"+ simpleDateFormat.format(a.getFinishTime())+"',"+ a.getPeriodic()+","+ a.getUrgent()+","+ a.getIsAttended() + ")";
              
                PreparedStatement pstmt = null;
               
                pstmt = con.prepareStatement(sqlString);
                int status = pstmt.executeUpdate();
                if(status > 0) {
                  System.out.println("Success");
                }
                 
                con.close();
                return true;
                
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "DB Error !! Activity NOT saved !!");
                System.out.println(ex);
            }
            return false;
  }
  public ArrayList<Holiday> getHolidaysDB(String typeInfo, String typeDescription) {
    // depending on time typeinfo : nationality or religion
    ArrayList<Holiday> al=new ArrayList<Holiday>();
    String sqlString="";
    String s1="",s2="";
    if (typeInfo.equals("C")) {
      // Cultural
      s1=typeDescription;
    } else {
      // Religious
      s2=typeDescription;
    }
    sqlString = "SELECT * FROM `holidays` WHERE holidayType='"+ typeInfo + "' and `nationality` ='" + s1 + "' and `religion` ='" + s2 + "' order by startDate";
              
    try 
        {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
              Statement stmt = con.createStatement();
              ResultSet rs =  stmt.executeQuery(sqlString); 
              
              
              while (rs.next()) {
                
                Holiday a=new Holiday();
                a.setHolidayType(rs.getString(2));
                a.setHolidayNationality(rs.getString(3));
                a.setHolidayReligion(rs.getString(4));
                a.setHolidayDescription(rs.getString(5));
                a.setStartDay(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(6)));
                a.setEndDay(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(7)));
                al.add(a);
                  
              }
              
                con.close();
                return al;
          } catch (Exception ex) {
              
              System.out.println(ex);
        }
        return null;

  }
  // Get Quick Activities DB
  public ArrayList<String> getQuickDB(String owner) {
    
    ArrayList<String> al=new ArrayList<String>();
    try 
        {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
              Statement stmt = con.createStatement();
              String sqlString="";
              sqlString = "SELECT * FROM `quickevent` WHERE owner='"+ owner + "'";
              ResultSet rs =  stmt.executeQuery(sqlString); 
              
              
              while (rs.next()) {
                
               String event=new String(rs.getString(2));
               al.add(event);

                  
              }
              
                con.close();
                return al;
          } catch (Exception ex) {
              
              System.out.println(ex);
        }
        return null;
  }
  // Get User color definitions from DB
  public Boolean saveColors(UserColor userColor) {
    try 
          {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
                String sqlString="";
                sqlString = "SELECT * FROM `colorpalette` WHERE username='"+ userColor.getUsername()  + "'";
                Statement stmt = con.createStatement();
                ResultSet rs =  stmt.executeQuery(sqlString); 
                if (rs.next()) {
                  // Update sql
                  sqlString = "update colorpalette set attended='"+ userColor.getAttended() +"', missed='"+ userColor.getMissed() 
                            +"', upcoming='"+ userColor.getUpcoming() +"', periodic='"+ userColor.getPeriodic() +"', urgent='"+ userColor.getUrgent() 
                            + "' where username='" + userColor.getUsername() + "'";
              
                } else {
                  // insert sql
                  sqlString = "insert into colorpalette (username,attended,missed,upcoming,periodic,urgent) "
                  +" values ('"+userColor.getUsername()+"' , '"+userColor.getAttended()+"', '"+userColor.getMissed()+"', '"+userColor.getUpcoming()+"', '"+userColor.getPeriodic()+"', '"+userColor.getUrgent()+"')" ;

                }
                
 
                PreparedStatement pstmt = null;
                pstmt = con.prepareStatement(sqlString);
                int status = pstmt.executeUpdate();
                if(status > 0) {
                    JOptionPane.showMessageDialog(null, "Colors saved !!");
                }
                 
                con.close();
                return true;
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "DB Error !! Colors NOT set !!");
                System.out.println(ex);
            }
            return false;
  }

  // Get User color definitions from DB
  public UserColor getUserColor(String owner) {
    
      UserColor uc=new UserColor();
      try 
        {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
              Statement stmt = con.createStatement();
              String sqlString="";
              sqlString = "SELECT * FROM `colorpalette` WHERE username='"+ owner + "'";
              ResultSet rs =  stmt.executeQuery(sqlString); 
              
              while (rs.next()) {
                // if exists then return existing ones
                uc.setUsername(owner);
                uc.setAttended(rs.getString(3));
                uc.setMissed(rs.getString(4));
                uc.setUpcoming(rs.getString(5));
                uc.setPeriodic(rs.getString(6));
                uc.setUrgent(rs.getString(7));
                con.close();
                return uc;
              }
              // if not exists then return default ones
                uc.setUsername(owner);
                uc.setAttended("#b0da9d");
                uc.setMissed("#ef908d");
                uc.setUpcoming("#acc7ee");
                uc.setUrgent("#cc0000");
                uc.setPeriodic("#fcaf78");
                con.close();
                return uc;
          } catch (Exception ex) {
              
              System.out.println(ex);
        }
      return null;
  }


  public ArrayList<Activity> getActivitiesDB(String selectedDate, String owner) {
    
    ArrayList<Activity> al=new ArrayList<Activity>();
    try 
        {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
              Statement stmt = con.createStatement();
              String sqlString="";
              sqlString = "SELECT * FROM `activity` WHERE owner='"+ owner + "' and `startTime` >'" + selectedDate.replace(".", "-") + " 00:00:00' and `startTime` <'" + selectedDate.replace(".", "-") + " 23:59:59' order by startTime";
              ResultSet rs =  stmt.executeQuery(sqlString); 
              
              
              while (rs.next()) {
                
                Activity a=new Activity();
                a.setActivityDescription(rs.getString(2));
                a.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(3)));
                a.setFinishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(4)));
                a.setPeriodic(rs.getBoolean(5));
                a.setUrgent(rs.getBoolean(6));
                a.setIsAttended(rs.getInt(7));
                al.add(a);
                  
              }
              
                con.close();
                return al;
          } catch (Exception ex) {
              
              System.out.println(ex);
        }
        return null;
  }

  public ArrayList<Activity> getWeeklyActivitiesDB(String endDate,String startDate, String owner) {
    
    ArrayList<Activity> al=new ArrayList<Activity>();
    try 
        {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
              Statement stmt = con.createStatement();
              String sqlString="";
              sqlString = "SELECT * FROM `activity` WHERE owner='"+ owner + "' and `startTime` >'" + startDate + " 00:00:00' and `startTime` <'" + endDate + " 23:59:59' and isAttended<>0 order by startTime";
              ResultSet rs =  stmt.executeQuery(sqlString); 
              
              
              while (rs.next()) {
                
                Activity a=new Activity();
                a.setActivityDescription(rs.getString(2));
                a.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(3)));
                a.setFinishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(4)));
                a.setPeriodic(rs.getBoolean(5));
                a.setUrgent(rs.getBoolean(6));
                a.setIsAttended(rs.getInt(7));
                al.add(a);
                  
              }
              
                con.close();
                return al;
          } catch (Exception ex) {
              
              System.out.println(ex);
        }
        return null;
  }

  public Boolean deleteActivityDB(Person p, Activity a) {
    
    int input = JOptionPane.showConfirmDialog(null, "Do you want to delete " + a.getActivityDescription() +"?","Uyarı!", JOptionPane.YES_NO_OPTION);
    
   if (input==0) {
    try 
    {
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con= DriverManager.getConnection("jdbc:mysql://" + DBConnection_String, DBConnection_user, DBConnection_pass);
         
          String sqlString="";
          String formattedStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(a.getStartTime());
          String formattedEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(a.getFinishTime());
          sqlString = "DELETE FROM `activity` WHERE owner='"+ p.getUserId() + "' and `startTime` ='" + formattedStartDate + "' and `endTime` ='" + formattedEndDate+  "' and activityName='"+ a.getActivityDescription()+"'";
          
          PreparedStatement pstmt = null;
               
          pstmt = con.prepareStatement(sqlString);
          int status = pstmt.executeUpdate();
          if(status > 0) {
              JOptionPane.showMessageDialog(null, a.getActivityDescription() + " activity deleted !!");
          }
           
          con.close();
          return true;
          
          
          
      } catch (Exception ex) {
          System.out.println(ex);
          return false;
      }
    } else {return false;}
}
}