package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.*;

 
public class SignUpFrame extends JFrame implements ActionListener {
 
   
    Container container=getContentPane();
    JLabel mainTitle=new JLabel("New Generational Calendar App");
    JLabel emailLabel=new JLabel("Email");
    JLabel userLabel=new JLabel("Username");
    JLabel passwordLabel=new JLabel("Password");
    JLabel cpasswordLabel=new JLabel("<html>Confirm Password</html>");
    public JTextField emailTextField=new JTextField("");
    public JTextField userTextField=new JTextField("");
    public JPasswordField passwordField=new JPasswordField("");
    public JPasswordField cpasswordField=new JPasswordField("");
    public JButton loginButton=new JButton("Sign Up!");
    JLabel imgLabel = new JLabel(new ImageIcon("lib/agendaNew.png"));
    
 
 
    public SignUpFrame(String title)
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Sign Up");
        setVisible(true);
        setBounds(10,10,450,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.decode("#647687"));
        mainTitle.setFont(new Font("Verdana", Font.PLAIN, 22));
        mainTitle.setForeground(Color.WHITE);
       
        emailLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        emailLabel.setForeground(Color.WHITE);
        emailTextField.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        userLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        userTextField.setFont(new Font("Verdana", Font.PLAIN, 18));
        userLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        passwordLabel.setForeground(Color.WHITE);
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 18));

        cpasswordLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        cpasswordLabel.setForeground(Color.WHITE);
        cpasswordField.setFont(new Font("Verdana", Font.PLAIN, 18));

    }
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
       mainTitle.setBounds(50,5,400,30);
       
       emailLabel.setBounds(50,150,150,30);
       emailTextField.setBounds(150,150,250,30);

       userLabel.setBounds(50,200,150,30);
       userTextField.setBounds(150,200,250,30);

       passwordLabel.setBounds(50,250,100,30);
       passwordField.setBounds(150,250,250,30);

       cpasswordLabel.setBounds(50,300,100,40);
       cpasswordField.setBounds(150,300,250,30);
     
       loginButton.setBounds(150,380,100,30);

       imgLabel.setBounds(150,30,100,100);

    }
    public void addComponentsToContainer()
    {
        //Adding each components to the Container
        container.add(mainTitle); 
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(cpasswordLabel);
        container.add(userTextField);
        container.add(passwordField);  
        container.add(cpasswordField);  
        container.add(loginButton);
        container.add(emailLabel);
        container.add(emailTextField);
        
        container.add(imgLabel);
        container.add(cpasswordLabel);
        loginButton.addActionListener(e-> signupUser(userTextField.getText(), new String(passwordField.getPassword()),  new String(cpasswordField.getPassword()) ,emailTextField.getText()));
   }

    public void signupUser(String username, String pass,  String pass2, String email) {
        
       if (pass.equals("") || pass2.equals("") || username.equals("") || email.equals("") ) {
            JOptionPane.showMessageDialog(null, "You have to fill all fields !");
            return;
       }
        
        if (pass.equals(pass2)) {
           
            if (email.contains("@")) {
                 // do signup
                
                DBController dbController=new DBController();
                Boolean rc= dbController.signUpDB(username,pass,email);
                if (rc) { 
                  
                   

                }

            } else {
                JOptionPane.showMessageDialog(null, "Your e-mail is not valid !");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Passwords are not equal !");
        }

    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }

   
}
 
