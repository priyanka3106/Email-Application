package com.email;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.database.DbConnection;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField emailtxt;
	private JPasswordField passwordtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(253, 62, 371, 45);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(168, 131, 371, 330);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setBounds(33, 48, 53, 15);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel.add(lblNewLabel_1_2);
		
		emailtxt = new JTextField();
		emailtxt.setBounds(33, 73, 316, 51);
		emailtxt.setColumns(10);
		panel.add(emailtxt);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Password");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1_2_1.setBounds(33, 167, 88, 15);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_2 = new JLabel("New User? Sign up");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register re=new Register();
				re.setVisible(true);
			}
		});
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(93, 275, 194, 45);
		panel.add(lblNewLabel_2);
		
		passwordtxt = new JPasswordField();
		passwordtxt.setBounds(33, 193, 316, 45);
		panel.add(passwordtxt);
		
		JCheckBox showpaswordCheckBox = new JCheckBox("Show Password");
		showpaswordCheckBox.setBounds(30, 244, 171, 21);
		panel.add(showpaswordCheckBox);
		showpaswordCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(showpaswordCheckBox.isSelected()) {
					passwordtxt.setEchoChar((char)0);
					
				}else {
					passwordtxt.setEchoChar('*');
					
				}
			}
		});
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginBtn.setBounds(406, 490, 191, 45);
		contentPane.add(loginBtn);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			  dispose();
          	  ForgotPassword   ForgotPassword_1=new   ForgotPassword();
          	  ForgotPassword_1.setVisible(true);
			}
		});
		btnForgotPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnForgotPassword.setBounds(171, 490, 217, 45);
		contentPane.add(btnForgotPassword);
		
	
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email=emailtxt.getText();
				String password=passwordtxt.getText();
				
				try {
					DbConnection db=new DbConnection();
					Connection conn;
					conn = db.getConnection();
				 	
					PreparedStatement st = (PreparedStatement) conn
	                        .prepareStatement("Select email, password from userlogin where email=? and password=?");

					  st.setString(1, email);
	                  st.setString(2, password);
	                  
	                  ResultSet rs = st.executeQuery(); 
	                  
	                  if (rs.next()) {
	                	  dispose();
	                	  
	                        JOptionPane.showMessageDialog(loginBtn, "You have successfully logged in");
	                        dashboard dash=new dashboard(email);
	                        dash.setVisible(true);
	                        
	                    } else {
	                        JOptionPane.showMessageDialog(loginBtn, "Wrong Username & Password");
	                    }
				}

			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			
		});
	}	
}
