package main;

import java.awt.EventQueue;
import java.awt.print.*;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookShop {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtbprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookShop window = new BookShop();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookShop() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pat;
	ResultSet rs;
	
	
	public void Connect() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookshop", "root", "");
		}
		catch(ClassNotFoundException ex){
			
		}
		catch(SQLException ex) {
			
		}
		
	}
	
	public void table_load() {
		
		try {
			pat = con.prepareStatement("select * from book");
			rs = pat.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Joram BookShop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel.setBounds(349, 11, 279, 57);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(23, 76, 406, 268);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(25, 34, 134, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(25, 124, 95, 30);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(25, 193, 95, 38);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtbname.setBounds(169, 34, 216, 38);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtedition.setColumns(10);
		txtedition.setBounds(169, 116, 216, 38);
		panel.add(txtedition);
		
		txtbprice = new JTextField();
		txtbprice.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtbprice.setColumns(10);
		txtbprice.setBounds(169, 189, 216, 38);
		panel.add(txtbprice);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1_1.setBounds(25, 193, 95, 38);
		panel.add(lblNewLabel_1_1_1_1);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,bprice;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				bprice = txtbprice.getText();
				
				
				try{
					
					pat = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pat.setString(1, bname);
					pat.setString(2, edition);
					pat.setString(3, bprice);
					
					pat.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!!!!!");
					
					table_load();
					
					txtbname.setText("");
					txtedition.setText("");
					txtbprice.setText("");
					txtbname.requestFocus();
					
				}
				
				catch(SQLException el){
					el.printStackTrace();
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(23, 381, 99, 57);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnExit.setBounds(176, 381, 99, 57);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtedition.setText("");
				txtbprice.setText("");
				txtbname.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnClear.setBounds(330, 381, 99, 57);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(464, 79, 422, 359);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(23, 449, 406, 80);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setBounds(42, 24, 96, 33);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try{
					String id = txtbid.getText();
					
					pat = con.prepareStatement("select name,edition,price from book where id = ?");
					pat.setString(1, id);
					ResultSet rs = pat.executeQuery();
					
					if(rs.next()==true) {
						
					String name = rs.getString(1);
					String edition = rs.getString(2);
					String price = rs.getString(3);
					
					txtbname.setText(name);
					txtedition.setText(edition);
					txtbprice.setText(price);
						
				}
					else {
						
						txtbname.setText("");
						txtedition.setText("");
						txtbprice.setText("");
					}
			}
				catch(SQLException ex){
					
				}
			}
		});
		txtbid.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtbid.setBounds(174, 24, 187, 33);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String bname,edition,bprice,bid;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				bprice = txtbprice.getText();
				bid = txtbid.getText();
				
				
				try{
					
					pat = con.prepareStatement("update book set name= ?, edition=?, price=? where id=?");
					pat.setString(1, bname);
					pat.setString(2, edition);
					pat.setString(3, bprice);
					pat.setString(4, bid);
					
					
					pat.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!!!!!");
					
					table_load();
					
					txtbname.setText("");
					txtedition.setText("");
					txtbprice.setText("");
					txtbname.requestFocus();
					
				}
				
				catch(SQLException el){
					el.printStackTrace();
					
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnUpdate.setBounds(449, 449, 147, 70);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnClear_1_1 = new JButton("Delete");
		btnClear_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String bid;
				
				bid = txtbid.getText();
				
				try{
					
					pat = con.prepareStatement("delete from book where id=?");
					
					pat.setString(1, bid);
					
					
					pat.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!!!!!");
					
					table_load();
					
					txtbname.setText("");
					txtedition.setText("");
					txtbprice.setText("");
					txtbname.requestFocus();
					
				}
				
				catch(SQLException el){
					el.printStackTrace();
					
				}
				
				
			}
		});
		btnClear_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnClear_1_1.setBounds(619, 449, 135, 70);
		frame.getContentPane().add(btnClear_1_1);
		
		JButton btnClear_1_1_1 = new JButton("Print");
		btnClear_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.print();
				}
				catch(java.awt.print.PrinterException ex){
					
					System.err.format("No Printer Found", ex.getMessage());
					
				}
				
			}
		});
		btnClear_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnClear_1_1_1.setBounds(780, 449, 135, 70);
		frame.getContentPane().add(btnClear_1_1_1);
	}
}
