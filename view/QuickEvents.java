package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 
public class QuickEvents extends JFrame implements ActionListener {
 
    Container container=getContentPane();
    JLabel activityNameLabel=new JLabel("Quick Activity Chooser");
    public JLabel holidayLabel=new JLabel();
    public JButton addButton=new JButton("Add");
    public JButton createButton=new JButton("Create");
    public JButton cancelButton=new JButton("Cancel");
    public ButtonGroup bg=new ButtonGroup();  
    public JButton confirmButton=new JButton("Confirm");
    public JButton cancel2Button=new JButton("Cancel"); 
    public JLabel creatorLabel=new JLabel("Please Type an Activity");
    public JTextField creatorField=new JTextField();
     
    

 
    public QuickEvents(String title)
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle(title);
        setVisible(true);
        
        setBounds(10,10,500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));
        
        activityNameLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
        activityNameLabel.setForeground(Color.WHITE);
        creatorLabel.setFont(new Font("Verdana", Font.ITALIC, 16));
        creatorLabel.setForeground(Color.WHITE);
        
    }
    public void showQuickCreator(Boolean show) {
       
            confirmButton.setVisible(show);
            cancel2Button.setVisible(show);
            creatorLabel.setVisible(show);
            creatorField.setVisible(show);

            addButton.setVisible(!show);
            createButton.setVisible(!show);
            cancelButton.setVisible(!show);
        
    }
    
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
      
       activityNameLabel.setBounds(60,20,350,30);
       addButton.setBounds(20,400,100,30);
       createButton.setBounds(190,400,100,30);
       cancelButton.setBounds(360,400,100,30);
       
       confirmButton.setBounds(100,400,100,30);
       cancel2Button.setBounds(250,400,100,30);
       creatorLabel.setBounds(20,320,430,30);
       creatorField.setBounds(20,350,430,30);
       
       confirmButton.setVisible(false);
       cancel2Button.setVisible(false);
       creatorLabel.setVisible(false);
       creatorField.setVisible(false);
       

    }
    public void addComponentsToContainer()
    {
        //Adding each components to the Container
       
        container.add(activityNameLabel);  
        container.add(holidayLabel);
        container.add(addButton);
        container.add(createButton);
        container.add(cancelButton);
        container.add(confirmButton);
        container.add(cancel2Button);
        container.add(creatorLabel);
        container.add(creatorField);
        
        
       
   }
   
  

   public void addQuickEventsToContainer(ArrayList<String> qe)
    {
        //Adding each components to the Container
    
       for (int i=0;i<qe.size();i++) {
            JLabel jl=new JLabel();
            JRadioButton cb=new JRadioButton();
            jl.setText(qe.get(i)); 
            cb.setActionCommand(qe.get(i));   
            cb.setBounds(50,60+i*35,20,30);
            cb.setBackground(Color.decode("#647687"));
            jl.setBounds(90,60+i*35,400,30);
            jl.setForeground(Color.WHITE);
            jl.setFont(new Font("Tahoma", Font.ITALIC, 16));
            container.add(jl);
            container.add(cb);
            bg.add(cb);
            JSeparator s = new JSeparator();
            s.setOrientation(SwingConstants.HORIZONTAL);
            s.setBounds(50,90+i*35,400,30);
            container.add(s);
            System.out.println(qe.get(i));
       }

       
   }

  
 
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}
 
