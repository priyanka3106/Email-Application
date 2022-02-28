package com.email;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.database.DbConnection;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(168, 62, 342, 45);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(149, 129, 472, 424);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Enter Email");
		lblNewLabel_1_2.setBounds(33, 48, 112, 15);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel.add(lblNewLabel_1_2);
		
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Enter New Password");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1_2_1.setBounds(33, 145, 165, 15);
		panel.add(lblNewLabel_1_2_1);
		
		textField = new JTextField();
		textField .setBounds(33, 73, 316, 50);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Renter Password");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1_2_1_1.setBounds(33, 249, 165, 15);
		panel.add(lblNewLabel_1_2_1_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(33, 170, 316, 50);
		panel.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(33, 274, 316, 50);
		panel.add(passwordField_2);
		
		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String emailid=textField.getText();
				String passdb=passwordField_1.getText();
				String passdb_2=passwordField_2.getText();
				try {
				if(passdb.equals(passdb_2))
				{
					DbConnection db=new DbConnection();
					Connection conn=db.getConnection();
					PreparedStatement  stmt=conn.prepareStatement("update userlogin set password=? where email=?");
					stmt.setString(1,passdb);
					stmt.setString(2,emailid);
					int affectedrows=stmt.executeUpdate();
					if(affectedrows>0)
					{
						JOptionPane.showMessageDialog(btnNewButton,"Password Change Successfully");
					}
					else
					{
						JOptionPane.showMessageDialog(btnNewButton,"Password not change!!");
					}
			    }
				else
				{
					JOptionPane.showMessageDialog(btnNewButton,"Please reenter password correctly");
				}
				}
				catch (SQLException e1) {
					JOptionPane.showMessageDialog(btnNewButton, "Error in Changing Password");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		
		});
		btnNewButton.setBounds(97, 357,227, 45);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			  dispose();
          	  Login login=new Login();
          	  login.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setBounds(10, 22, 123, 45);
		contentPane.add(btnBack);
		
		
	}

}
