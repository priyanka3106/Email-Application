package com.email;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.database.DbConnection;

import javax.swing.JSplitPane;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class dashboard extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String email = null;
				
					dashboard frame = new dashboard(email);
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
	public dashboard(String email) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email Dashboard");
		lblNewLabel.setBounds(311, 62, 371, 45);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		contentPane.add(lblNewLabel);
		
		Panel panel = new Panel();
		panel.setBounds(46, 176, 213, 474);
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		System.out.print(email);
		
		JButton composeBtn = new JButton("Compose Email");
		composeBtn.setForeground(Color.WHITE);
		composeBtn.setBackground(Color.RED);
		composeBtn.setBounds(24, 31, 159, 51);
		panel.add(composeBtn);
		
		JButton btnInbox = new JButton("Inbox");
		btnInbox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				
				DbConnection db=new DbConnection();
				
					try 
					{
						Connection conn=db.getConnection();
						
						PreparedStatement ps=conn.prepareStatement("select sender,subject,contents,emaildate from inboxtable where receiver=? order by emaildate desc");
						ps.setString(1, email);
						
						ResultSet result=ps.executeQuery();
						
						ResultSetMetaData rs= result.getMetaData();//to get information about types and properties of the column in the resultset
						DefaultTableModel model=(DefaultTableModel) table.getModel();
						int cols=rs.getColumnCount();//to get columncount from resultsetMetadaData
						String colname[]= {"From","Subject","Content","Date"};
						model.setColumnIdentifiers(colname);
						String from,subject,content,emaildate;
						
						while(result.next())
						{
					
							from=result.getString(1);
							subject=result.getString(2);
							content=result.getString(3);
							emaildate=result.getString(4);
							String row[]= {from,subject,content,emaildate};
							model.addRow(row);
							
							
						}
					
					} 
					catch (SQLException e1) 
					{
						
						e1.printStackTrace();
					}
				
				
			}
		});
		btnInbox.setForeground(Color.WHITE);
		btnInbox.setBackground(new Color(0, 191, 255));
		btnInbox.setBounds(24, 121, 159, 51);
		panel.add(btnInbox);
		
		JButton btnSent = new JButton("Sent");
		btnSent.setForeground(Color.WHITE);
		btnSent.setBackground(new Color(0, 191, 255));
		btnSent.setBounds(24, 211, 159, 51);
		panel.add(btnSent);
		btnSent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				
				DbConnection db=new DbConnection();
				
					try 
					{
						Connection conn=db.getConnection();
						
						PreparedStatement ps=conn.prepareStatement("select receiver, subject,contents,emaildate from senttable where sender=? order by emaildate desc ");
						ps.setString(1, email);
						ResultSet result=ps.executeQuery();
						ResultSetMetaData rs= result.getMetaData();
						DefaultTableModel model=(DefaultTableModel) table.getModel();
						int cols=rs.getColumnCount();
						String colname[]= {"To","Subject","Content","Date"};
						model.setColumnIdentifiers(colname);
						String to,subject,content,emaildate;
						while(result.next())
						{
							to=result.getString(1);
							subject=result.getString(2);
							content=result.getString(3);
							emaildate=result.getString(4);
							String row[]= {to,subject,content,emaildate};
							model.addRow(row);
						}
					
						
					} 
					catch (SQLException e1) 
					{
						
						e1.printStackTrace();
					}
				
				
			}
		});
		
		
		composeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ComposeEmail ce =new ComposeEmail(null,null,email);
				ce.setVisible(true);
				
				
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 176, 645, 474);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				String tableheader=table.getColumnName(0);
				int row=table.getSelectedRow();
				String to=model.getValueAt(row, 0).toString();
				String subject=model.getValueAt(row, 1).toString();
				String content=model.getValueAt(row, 2).toString();
				String emaildate=model.getValueAt(row, 3).toString();
				dispose();
				EmailDetail ed=new EmailDetail(to,subject,content,emaildate,email,tableheader);
				ed.setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			  dispose();
          	  Login login=new Login();
          	  login.setVisible(true);
			}
		});
		btnNewButton.setBounds(24, 32, 116, 51);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{ 
				String str=textField.getText();
				ArrayList<Integer> list= new ArrayList<Integer>();
				
				for(int i=0;i<table.getRowCount();i++)
				{
					for(int j=0;j<table.getColumnCount();j++)
					{
						if(table.getModel().getValueAt(i, j).equals(str));
						{
							
							list.add(i);
						}
					}
				}
				table.setRowSelectionAllowed(true);
			   table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			   

				//table.setRowSelectionInterval(0,0);
				//table.addRowSelectionInterval(1,1);
				    
				table.setRowSelectionInterval(list.get(0), list.get(0));
			for(int j=1;j<list.size();j++)
		
			{

			
					table.addRowSelectionInterval(list.get(j), list.get(j));
					    
					
				} 
				
				
			}
		});
		textField.setBounds(623, 136, 333, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the String");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(311, 139, 280, 30);
		contentPane.add(lblNewLabel_1);
		
		
		
	}
}
/*DefaultTableModel model=(DefaultTableModel) table.getModel();
TableRowSorter<DefaultTableModel> trs=new TableRowSorter<>(model);//This is an implementation of TableModel that uses a Vector of Vectors to store the cell value objects
table.setRowSorter(trs);
trs.setRowFilter(RowFilter.regexFilter(str));*/
//int array[]=new int[table.getRowCount()];
