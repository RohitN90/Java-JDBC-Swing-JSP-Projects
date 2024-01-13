import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ItemSearch;
	private JTextField locSearch;
	private JTextField resSearch;
	private JTable table_1;
	private JTable cartTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setTitle("Forsted Bit's");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/Images/Yellow and Black Fun Modern Restaurant Food Logo (1).png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 695);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 238, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ItemSearch = new JTextField();
		ItemSearch.setFont(new Font("Segoe UI", Font.BOLD, 20));
		ItemSearch.setHorizontalAlignment(SwingConstants.CENTER);
		ItemSearch.setBounds(214, 73, 325, 38);
		contentPane.add(ItemSearch);
		ItemSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search :");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(66, 72, 138, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Location :");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(64, 158, 131, 38);
		contentPane.add(lblNewLabel_1);
		
		locSearch = new JTextField();
		locSearch.setHorizontalAlignment(SwingConstants.CENTER);
		locSearch.setToolTipText("Enter your nearby location");
		locSearch.setFont(new Font("Segoe UI", Font.BOLD, 20));
		locSearch.setBounds(214, 157, 325, 38);
		contentPane.add(locSearch);
		locSearch.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Restaurant :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(57, 241, 147, 38);
		contentPane.add(lblNewLabel_1_1);
		
		resSearch = new JTextField();
		resSearch.setHorizontalAlignment(SwingConstants.CENTER);
		resSearch.setFont(new Font("Segoe UI", Font.BOLD, 20));
		resSearch.setColumns(10);
		resSearch.setBounds(214, 240, 325, 38);
		contentPane.add(resSearch);
		
		JLabel lblNewLabel_2 = new JLabel("Or");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(346, 205, 45, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("You can search an item here !");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.ITALIC, 17));
		lblNewLabel_3.setBounds(214, 50, 333, 13);
		contentPane.add(lblNewLabel_3);
		
		JButton btItem = new JButton("Search");
		btItem.setForeground(new Color(255, 255, 255));
		btItem.setBackground(new Color(128, 128, 255));
		btItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Connection coo = MyClass.letConnection();
					String searchkey = ItemSearch.getText();
					String sql = "SELECT items.item_name,items.item_cost,restaurant.res_name FROM items INNER JOIN restaurant ON restaurant.res_id = items.res_id WHERE items.item_name like ?";
					PreparedStatement ps = coo.prepareStatement(sql);
					ps.setString(1,"%" + searchkey + "%");
					
					ResultSet rs = ps.executeQuery();
					
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					String colName[] = {"Restaurant","Items","Cost","Add"};
					model.setColumnIdentifiers(colName);					
					String res1,item,cost;
					model.setDataVector(null, colName);
					
					while(rs.next()) {
						
						res1 = rs.getString(3);
						item = rs.getString(1);
						cost = rs.getString(2);						
						String rows[] = {res1,item,cost};						
						model.addRow(rows);
					}
					
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btItem.setBounds(549, 76, 85, 34);
		contentPane.add(btItem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 327, 575, 295);
		contentPane.add(scrollPane);
		
		JLabel costLab = new JLabel("");
		costLab.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		costLab.setBounds(854, 332, 75, 25);
		contentPane.add(costLab);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel mod = table_1.getModel();
				int row = table_1.getSelectedRow();
				DefaultTableModel mod2 = (DefaultTableModel) cartTable.getModel();
				String columnName1[] = {"Item","Cost"}; 
				mod2.setColumnIdentifiers(columnName1);
				String item,cost;
				item = (String) mod.getValueAt(row, 1);
				cost = (String) mod.getValueAt(row, 2);
				
				String rows[] = {item,cost};
				mod2.addRow(rows);
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		JTableHeader th = table_1.getTableHeader();
		th.setForeground(Color.blue);
		table_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		scrollPane.setViewportView(table_1);
		
		
		JButton btRes = new JButton("Search");
		btRes.setForeground(new Color(255, 255, 255));
		btRes.setBackground(new Color(128, 128, 255));
		btRes.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btRes.setBounds(549, 243, 85, 34);
		contentPane.add(btRes);
		
		JButton btLoc = new JButton("Search");
		btLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Connection coc = MyClass.letConnection();
					String locScr = locSearch.getText();
					String locStr = "select restaurant.res_name,items.item_name,items.item_cost from items inner join restaurant on restaurant.res_id = items.res_id inner join location on location.loc_id = restaurant.loc_id where location.loc_name like ?";
					PreparedStatement ps = coc.prepareStatement(locStr);
					ps.setString(1, "%"+locScr+"%");
					ResultSet rs = ps.executeQuery();
					DefaultTableModel model2 = (DefaultTableModel) table_1.getModel();
					String colName[] = {"Restaurant","Items","Cost","Add"};
					model2.setColumnIdentifiers(colName);
					String res1,item,cost;
					model2.setDataVector(null, colName);
					
					
					while(rs.next()) {
						res1 = rs.getString(1);
						item = rs.getString(2);
						cost = rs.getString(3);						
						String rows[] = {res1,item,cost};						
						model2.addRow(rows);						
					}
					
					btItem.setText(" ");
					btRes.setText(" ");
					
					coc.close();
				}catch(Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btLoc.setForeground(new Color(255, 255, 255));
		btLoc.setBackground(new Color(128, 128, 255));
		btLoc.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btLoc.setBounds(549, 160, 85, 34);
		contentPane.add(btLoc);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(726, 86, 222, 218);
		contentPane.add(scrollPane_1);
		
		cartTable = new JTable();
		scrollPane_1.setViewportView(cartTable);
		cartTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		cartTable.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		
		JLabel lblNewLabel_4 = new JLabel("CART ");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(747, 33, 186, 38);
		contentPane.add(lblNewLabel_4);
		
		JButton buyBut = new JButton("Buy");
		buyBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Order has placed :)");
				DefaultTableModel mod = (DefaultTableModel) cartTable.getModel();
				mod.setDataVector(null, getComponentListeners());
				
			}
		});
		buyBut.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		buyBut.setBounds(747, 379, 186, 47);
		contentPane.add(buyBut);
		
		JLabel lblNewLabel_5 = new JLabel("Total Amount :");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_5.setBounds(726, 332, 118, 25);
		contentPane.add(lblNewLabel_5);
		
		JButton cartBut = new JButton("Clear Cart");
		cartBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel mod = (DefaultTableModel) cartTable.getModel();
				mod.setRowCount(0);
			}
		});
		cartBut.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		cartBut.setBounds(747, 446, 186, 47);
		contentPane.add(cartBut);
		
	}
}
