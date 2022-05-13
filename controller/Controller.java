package controller;

import view.LoginFrame;
import view.Notification;
import view.QuickEvents;
import view.Report;
import view.CalendarView;
import view.ColorPalette;
import view.Holidays;
import view.ViewOrDeleteActivity;
import view.About;
import view.AddActivity;
import view.CalendarType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.*;

public class Controller {

  private Person person;
  private UserColor userColor=new UserColor();
  private Activity activity;
  private ArrayList<Activity> todayActivities;
  private LoginFrame loginFrame;
  private CalendarView calendar;
  private DBController dbController;
  private int TIME_INTERVAL=60000;
  
  
  public Controller(Person p, Activity a) {
    person = p;
    activity = a;
    dbController=new DBController();
    initController();
     
  }

  public void initController() {

    
    dbController.selectDB();
    // Log user in order to continue
    if (person.getUserId() == null) {
      loginFrame = new LoginFrame("Giriş");
      loginFrame.loginButton.addActionListener(e -> checkUser());
    }

  }

  public void initCalendar() {
 
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        calendar.imgGoz[i * 7 + j].addMouseListener(new InventoryMouseListener(1, i * 7 + j));
        calendar.imgPlus[i * 7 + j].addMouseListener(new InventoryMouseListener(2, i * 7 + j));
        calendar.imgMinus[i * 7 + j].addMouseListener(new InventoryMouseListener(3, i * 7 + j));
      }
    }
    calendar.colorPalette.addMouseListener(new InventoryMouseListener(4, 0));
    calendar.culturalHolidays.addMouseListener(new InventoryMouseListener(5, 0));
    calendar.religiousDays.addMouseListener(new InventoryMouseListener(6, 0));
    calendar.quickEvents.addMouseListener(new InventoryMouseListener(7, 0));
    calendar.reports.addMouseListener(new InventoryMouseListener(8, 0));
    calendar.changeType.addMouseListener(new InventoryMouseListener(9, 0));

    calendar.about.addActionListener(e-> openInfo("About"));
    calendar.contacts.addActionListener(e-> openInfo("Contacts"));
  }
  
  // About and Contacts Panel
  public void openInfo(String infoType) {
    About ab=new About(infoType);
    ab.closeButton.addActionListener(e->closePanel(ab));
  }

  // Refresh Calendar with user defined colors
  public void getUserColor() {
    userColor=dbController.getUserColor(person.getUserId());
  }
  
 
  // To Notify user, get today activities
  public void getTodayActivities() {
    
  
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
       
        @Override
        public void run() {
          String formattedStartDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
         todayActivities=dbController.getActivitiesDB(formattedStartDate, person.getUserId());
          DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");  
          String strDate = dateFormat.format(new Date());  
          calendar.clock.setText(strDate);
          String strHour = new SimpleDateFormat("HH").format(new Date());
          String strMin = new SimpleDateFormat("mm").format(new Date());
          int totalTime=Integer.parseInt(strHour)* 60  + Integer.parseInt(strMin);
          
          for (int i=0;i<todayActivities.size();i++){
            String actHour = new SimpleDateFormat("HH").format(todayActivities.get(i).getStartTime());
            String actMin = new SimpleDateFormat("mm").format(todayActivities.get(i).getStartTime());
            String endHour = new SimpleDateFormat("HH").format(todayActivities.get(i).getFinishTime());
            String endMin = new SimpleDateFormat("mm").format(todayActivities.get(i).getFinishTime());
            int isAttended=todayActivities.get(i).getIsAttended();
            int totalTimeAct=Integer.parseInt(actHour)* 60  + Integer.parseInt(actMin);
            int totalTimeEnd=Integer.parseInt(endHour)* 60  + Integer.parseInt(endMin);
            
            // Activity time came
            if(totalTime==totalTimeAct) {
              Notification notification=new Notification(todayActivities.get(i),false,person.getUserId());
              notification.cancelButton.addActionListener(e -> closePanel(notification));
            }
            // Activity time passed and no  attendance info
            if ((totalTimeEnd<totalTime) && (isAttended==0)) {
              Notification notification=new Notification(todayActivities.get(i),true,person.getUserId());
              notification.cancelButton.addActionListener(e -> closePanel(notification));
            }
        }  //end of for
        }
    };
    timer.schedule(task, 0, TIME_INTERVAL); 
   
  }

 

  // Login Frame Action Functions
  private void checkUser() {

  
    String pass = new String(loginFrame.passwordField.getPassword());
    person=dbController.checkAuthentication(loginFrame.userTextField.getText(), pass);
    if (person!=null){
      // user authenticated so open Calendar
      loginFrame.dispose();

      // Populate Person with database
      calendar = new CalendarView(person);
      initCalendar();
      userColor.setUsername(person.getUserId());
      getUserColor();
      getTodayActivities();

    } else {
      JOptionPane.showMessageDialog(null, "Username or password invalid! Please try again.", "Hata",
          JOptionPane.INFORMATION_MESSAGE);
    }

  }

  
  public void insertActivity(AddActivity addActivityFrame, String datetoAdd) throws ParseException {
    // Insert values into Activity model and save
    if (addActivityFrame.activityField.getText().equals("")) {JOptionPane.showMessageDialog(null, "You must enter an activity description !!");return;}
    
    Date startDate,finishDate;
    int period=0;
    startDate=new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(datetoAdd + " " +addActivityFrame.cbStart.getSelectedItem().toString());
    finishDate=new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(datetoAdd + " " +addActivityFrame.cbFinish.getSelectedItem().toString());
    
    if (startDate.after(finishDate)) {
      JOptionPane.showMessageDialog(null, "Activity start time cannot be greater than finish time !!");
      return;
    }
    activity.setActivityDescription(addActivityFrame.activityField.getText());
    activity.setUserID(person.getUserId());
    activity.setActivityDate(new SimpleDateFormat("dd.MM.yyyy").parse(datetoAdd));

    if (addActivityFrame.urgentR1.isSelected())
      activity.setUrgent(true);
    else
      activity.setUrgent(false);
    if (addActivityFrame.periodicR1.isSelected()) {
      activity.setPeriodic(true);
      if(addActivityFrame.periodicComboBox.getSelectedItem().equals("1 Week")) period=7;
      if(addActivityFrame.periodicComboBox.getSelectedItem().equals("2 Weeks")) period=14;
      if(addActivityFrame.periodicComboBox.getSelectedItem().equals("1 Month")) period=30;
      if(addActivityFrame.periodicComboBox.getSelectedItem().equals("2 Months")) period=60;
    }
    else
      activity.setPeriodic(false);

      activity.setStartTime(startDate);
      activity.setFinishTime(finishDate);
      person.addActivity(activity);
      closePanel(addActivityFrame);
    if (period==0)  {
      if (dbController.insertActivityDB(person, activity))   JOptionPane.showMessageDialog(null, "Activity saved !!"); }
    else {
      // Period is on so do some calculations
      int maxPeriod=5;
      int checkPeriod=0;
      if (period<20) maxPeriod=10; // weekly or 2 weeks insert 10 times otherwise 5 times
      for (int i=0;i<maxPeriod;i++) {
        Date dts = startDate;
        Date dtf = finishDate;
        Calendar c = Calendar.getInstance();
        Calendar f = Calendar.getInstance(); 
        c.setTime(dts); 
        f.setTime(dtf); 
        if (period==7 || period==14) {
            c.add(Calendar.DATE, period*i);
            f.add(Calendar.DATE, period*i);
        }
        if (period==30) {
          c.add(Calendar.MONTH, i);
          f.add(Calendar.MONTH, i);
        }
        if (period==60) {
          c.add(Calendar.MONTH, i*2);
          f.add(Calendar.MONTH, i*2);
        }
        dts = c.getTime();
        dtf = f.getTime();
        activity.setStartTime(dts);
        activity.setFinishTime(dtf);
        if (dbController.insertActivityDB(person, activity)) checkPeriod++; 
      }
      if (maxPeriod==checkPeriod) JOptionPane.showMessageDialog(null, "Activity saved !!"); 
      activity.setStartTime(startDate);
      activity.setFinishTime(finishDate);
    }
  }

  public void viewActivity(ViewOrDeleteActivity da, String selectedDate,String mode) throws ParseException {
    
    ArrayList<Activity> activitiesByDate= dbController.getActivitiesDB(selectedDate,person.getUserId());

    Date sd=new SimpleDateFormat("yyyy-MM-dd").parse(selectedDate);
    for (int j=0;j<calendar.allHolidays.size();j++) {
      if (!sd.before (calendar.allHolidays.get(j).getStartDay()) && !sd.after (calendar.allHolidays.get(j).getEndDay())) {
        da.holidayLabel.setText(calendar.allHolidays.get(j).getHolidayDescription());
        da.holidayLabel.setVisible(true);
      }     
  }
    if (activitiesByDate.size()==0) da.noActivities();
    for (int y=0;y<activitiesByDate.size();y++) {
      da.addActivitiesComponentsToContainer(activitiesByDate.get(y), y,mode,person);
    }
    
  }


  // Close Panel (Generic)
  public void closePanel(JFrame f) {
    f.dispose();
  }

  public void saveColors(ColorPalette cp ) {
    userColor.setAttended("#"+Integer.toHexString(cp.cattended.getBackground().getRGB()).substring(2)); 
    userColor.setMissed("#"+Integer.toHexString(cp.cmissed.getBackground().getRGB()).substring(2));
    userColor.setUpcoming("#"+Integer.toHexString(cp.cupcoming.getBackground().getRGB()).substring(2));
    userColor.setUrgent("#"+Integer.toHexString(cp.curgent.getBackground().getRGB()).substring(2));
    userColor.setPeriodic("#"+Integer.toHexString(cp.cperiodic.getBackground().getRGB()).substring(2));
    dbController.saveColors(userColor);
  }


  public void nationalityChange(Holidays ch,Person p) {
  
    ArrayList<Holiday> holidaysNList= dbController.getHolidaysDB("C",ch.cNationality.getSelectedItem().toString());
    ch.displayHolidays(holidaysNList);
    nationalitySave(p, ch.cNationality.getSelectedItem().toString());
    calendar.allHolidays=calendar.getAllHolidays(p);
  }


  public void religionChange(Holidays ch,Person p) {
    ArrayList<Holiday> holidaysNList= dbController.getHolidaysDB("R",ch.cNationality.getSelectedItem().toString());
    ch.displayHolidays(holidaysNList);
    religionSave(p, ch.cNationality.getSelectedItem().toString());
    calendar.allHolidays=calendar.getAllHolidays(p);  
  }

  public void nationalitySave(Person p,String n) {
    p.setNationality(n);
    if(dbController.saveNationalityDB(p.getUserId(),n))  { 
      calendar.allHolidays=CalendarView.getAllHolidays(p);
      calendar.refreshCalendar((LocalDateTime.now().getMonthValue()-1),LocalDateTime.now().getYear(),p);
    }
  }

  public void religionSave(Person p,String n) {
    p.setreligion(n);
    if (dbController.saveReligionDB(p.getUserId(),n))  {
      calendar.allHolidays=CalendarView.getAllHolidays(p);
      calendar.refreshCalendar((LocalDateTime.now().getMonthValue()-1),LocalDateTime.now().getYear(),p);
    }
  }

  public void addQuickActivity(String a) {
    AddActivity aa = new AddActivity("Adding Activity for Today", a);
    aa.cancelButton.addActionListener(e -> closePanel(aa));
    aa.addButton.addActionListener(e -> {
    try {
     

      String selectdDate=LocalDateTime.now().getDayOfMonth() + "." +  LocalDateTime.now().getMonthValue() + "." + LocalDateTime.now().getYear();
      aa.setTitle("Adding Activity for " + selectdDate);
      insertActivity(aa, selectdDate);
      } catch (ParseException e1) {
      e1.printStackTrace();
      }
    });

  }

  // Overlap Hicri Calendar over Gregorian based on choice
  public void showHicriCalendar(Boolean s) {
    calendar.showHicriCalendar=s;
    calendar.refreshCalendar(4, 2022, person);
  }
 
  // Create Quicd Activity
  public void createQuickActivity(QuickEvents qea) {

    if (qea.creatorField.getText().equals("")) {
      JOptionPane.showMessageDialog(null, "You have to enter a value!!");
      return;
    }
    dbController.insertQuickDB(person.getUserId(),qea.creatorField.getText() );
    qea.dispose();
    QuickEvents qe=new QuickEvents("");
    ArrayList<String> events= dbController.getQuickDB(person.getUserId());
    qe.addQuickEventsToContainer(events);
    qe.addButton.addActionListener(e->addQuickActivity(qe.bg.getSelection().getActionCommand()));
    qe.createButton.addActionListener(e->qe.showQuickCreator(true));
    qe.cancelButton.addActionListener(e -> closePanel(qe));
    qe.cancel2Button.addActionListener(e -> qe.showQuickCreator(false));
    qe.confirmButton.addActionListener(e->createQuickActivity(qe));
  

  }

  //********************** */
  // Below code for capturing mouse click events on images on Calendar Panel. (+ , - , eye, upper menu images, etc.)
  //********************** */
  
  class InventoryMouseListener extends java.awt.event.MouseAdapter {
    private int labelType, index;

    public InventoryMouseListener(int labelType, int index) {

      this.labelType = labelType;
      this.index = index;

    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
      
      //******************************/
      // View Activities *************

      if (labelType == 1) {
        String month,day;
        if ((view.CalendarView.currentMonth + 1)<10) month="0" + (view.CalendarView.currentMonth + 1); else month="" + (view.CalendarView.currentMonth + 1);
        if ((view.CalendarView.childLabel[index].getText()).length()==1) day="0" + view.CalendarView.childLabel[index].getText(); else day="" + view.CalendarView.childLabel[index].getText();

        String selectedDate=view.CalendarView.currentYear + "-" +month + "-" + day;
        ViewOrDeleteActivity da = new ViewOrDeleteActivity("View Activity for " + selectedDate, userColor);
        da.cancelButton.addActionListener(e -> closePanel(da));

        try {
          // View and Add Activity is same frame. Actions determined by mode. V for View, D for Delete
          viewActivity(da, selectedDate,"V");
        } catch (ParseException e) {
         
          e.printStackTrace();
        }
      }
       
      //******************************/
      // Add Activity *************
      
      if (labelType == 2) {
        String selectdDate=view.CalendarView.childLabel[index].getText() + ".0" + (view.CalendarView.currentMonth + 1) + "."
        + view.CalendarView.currentYear;
          AddActivity aa = new AddActivity("Adding Activity for "+ selectdDate, null);
         
          aa.cancelButton.addActionListener(e -> closePanel(aa));
          aa.addButton.addActionListener(e -> {
          try {
             insertActivity(aa, selectdDate);
            } catch (ParseException e1) {
            e1.printStackTrace();
            }
          });
      }
     
     //******************************/
     // Delete Activity *************

      if (labelType == 3) {
        String month,day;
        if ((view.CalendarView.currentMonth + 1)<10) month="0" + (view.CalendarView.currentMonth + 1); else month="" + (view.CalendarView.currentMonth + 1);
        if ((view.CalendarView.childLabel[index].getText()).length()==1) day="0" + view.CalendarView.childLabel[index].getText(); else day="" + view.CalendarView.childLabel[index].getText();

        String selectedDate=view.CalendarView.currentYear + "-" +month + "-" + day;
        ViewOrDeleteActivity da = new ViewOrDeleteActivity("Delete Activity for " + selectedDate, userColor);
        da.cancelButton.addActionListener(e -> closePanel(da));
        try {
           // View and Add Activity is same frame. Actions determined by mode. V for View, D for Delete
           viewActivity(da, selectedDate,"D");
        } catch (ParseException e) {
          e.printStackTrace();
        }
    }
    
    // Color Palette *************
    
    if (labelType == 4) {
      ColorPalette cp=new ColorPalette(userColor);
      cp.cancelButton.addActionListener(e-> closePanel(cp));
      cp.saveButton.addActionListener(e-> saveColors(cp));
    
    }
    
    // Cultural Holidays
    
    if (labelType == 5) {
      Holidays ch= new Holidays("Cultural Holidays", person);
      ch.cNationality.addActionListener(e-> nationalityChange(ch,person));
      ch.cancelButton.addActionListener(e -> closePanel(ch));
    }

    // Religious Days

    if (labelType == 6) {  
      Holidays ch= new Holidays("Religious Days", person);
      ch.cNationality.addActionListener(e-> religionChange(ch,person));
      ch.cancelButton.addActionListener(e -> closePanel(ch));
    }

    // Quick Events
    
    if (labelType == 7) {
      QuickEvents qe=new QuickEvents("");
      ArrayList<String> events= dbController.getQuickDB(person.getUserId());
      qe.addQuickEventsToContainer(events);
      qe.addButton.addActionListener(e->addQuickActivity(qe.bg.getSelection().getActionCommand()));
      qe.createButton.addActionListener(e->qe.showQuickCreator(true));
      qe.cancelButton.addActionListener(e -> closePanel(qe));
      qe.cancel2Button.addActionListener(e -> qe.showQuickCreator(false));
      qe.confirmButton.addActionListener(e->createQuickActivity(qe));

    }
    
    // Reports
    
    if (labelType == 8) {
      Report report=new Report(person.getUserId());
      report.cancelButton.addActionListener(e -> closePanel(report));
    }

    // Calendar Type - Show Hicri Calendar

    if (labelType == 9) {
      CalendarType ct=new CalendarType(calendar.showHicriCalendar);
      ct.closeButton.addActionListener(e -> closePanel(ct));
      ct.showHicri.addActionListener(e->showHicriCalendar(ct.showHicri.isSelected()));
    }

  }
  }

}

