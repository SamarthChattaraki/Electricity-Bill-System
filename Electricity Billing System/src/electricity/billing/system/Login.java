package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login  extends JFrame implements ActionListener {
    JTextField userText,passwordText;

    Choice loginChoice;

    JButton loginButton,cancelButton,signUpButton;



    Login()
    {
        super("Login");

        getContentPane().setBackground(Color.white);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(300,60,100,20);
        add(usernameLabel);

         userText = new JTextField();
        userText.setBounds(400,60,150,20);
        add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(300,100,100,20);
        add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(400,100,150,20);
        add(passwordText);

        JLabel loginLabel = new JLabel("Login In Ass");
        loginLabel.setBounds(300,140,100,20);
        add(loginLabel);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400,140,150,20);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(330,180,100,20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton= new JButton("Cancel");
        cancelButton.setBounds(460,180,100,20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(400,215,100,20);
        signUpButton.addActionListener(this);
        add(signUpButton);


        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profiletwo = profileOne.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profiletwo);
        JLabel profileOneLabel = new JLabel(fprofileOne);
        profileOneLabel.setBounds(5,5,250,250);
        add(profileOneLabel);


        setSize(640,300);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginButton)
        {
            String susername = userText.getText();
            String spassword = passwordText.getText();
            String user = loginChoice.getSelectedItem();

            try{
                database c = new database();
                String query ="select * from Signup1 where username='"+susername+"' and password='"+spassword+"' and usertype='"+user+"'";

                ResultSet resultSet =c.statement.executeQuery(query);

                if(resultSet.next())
                {
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(user,meter);

                }

                else{
                    JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                }




            } catch (Exception E) {
                E.printStackTrace();
            }





        } else if (e.getSource()==cancelButton) {
            setVisible(false);
        }
        else if (e.getSource()==signUpButton) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
