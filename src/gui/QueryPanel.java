package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import util.DbConnect;

public class QueryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JTextField fieldFirstName;
	private JTextField fieldLastName;
	private JTable resultTable;
	private JButton btnSearch;
	private JComboBox<String> selectQuery;
	private JScrollPane scrollPane;
	private DbConnect database;

	/**
	 * Create the panel.
	 */
	public QueryPanel() {
		setBackground(new Color(0, 128, 128));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		lblFirstName = new JLabel("First Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblFirstName, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFirstName, 50, SpringLayout.WEST, this);
		add(lblFirstName);

		lblLastName = new JLabel("Last Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblLastName, 20, SpringLayout.SOUTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, lblLastName, 0, SpringLayout.WEST, lblFirstName);
		add(lblLastName);

		fieldFirstName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, fieldFirstName, -2, SpringLayout.NORTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, fieldFirstName, 50, SpringLayout.EAST, lblFirstName);
		springLayout.putConstraint(SpringLayout.SOUTH, fieldFirstName, 2, SpringLayout.SOUTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.EAST, fieldFirstName, -20, SpringLayout.EAST, this);
		add(fieldFirstName);
		fieldFirstName.setColumns(10);

		fieldLastName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, fieldLastName, -2, SpringLayout.NORTH, lblLastName);
		springLayout.putConstraint(SpringLayout.SOUTH, fieldLastName, 2, SpringLayout.SOUTH, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, fieldLastName, 0, SpringLayout.WEST, fieldFirstName);
		springLayout.putConstraint(SpringLayout.EAST, fieldLastName, 0, SpringLayout.EAST, fieldFirstName);
		add(fieldLastName);
		fieldLastName.setColumns(10);

		btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 15, SpringLayout.SOUTH, fieldLastName);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableType = selectQuery.getSelectedIndex() == 0 ? "member" : "trainer";
				String[] columns = selectQuery.getSelectedIndex() == 0
						? new String[] { "MemberID", "First_Name", "Last_Name", "Date_Of_Birth", "Address", "Email",
								"Phone_Number", "Emergency Contact", "Member_Date_Start", "MembershipPlanID" }
						: new String[] { "TrainerID", "First_Name", "Last_Name", "Phone_Number", "Specialization",
								"Certification", "Availability", "Schedule" };
				Vector<String> colVector = new Vector<>();
				colVector.addAll(Arrays.asList(columns));

				String firstField = "First_Name LIKE '" + fieldFirstName.getText() + "'";
				String lastField = "Last_Name LIKE '" + fieldLastName.getText() + "'";
				String query = "SELECT * FROM " + tableType;
				if (!fieldFirstName.getText().isEmpty() && !fieldLastName.getText().isEmpty())
					query += (" WHERE " + firstField + " AND " + lastField);
				else if (!fieldFirstName.getText().isBlank())
					query += (" WHERE " + firstField);
				else if (!fieldLastName.getText().isBlank())
					query += (" WHERE " + lastField);

				ResultSet response = database.query(query);
				try {
					Vector<Vector<Object>> data = new Vector<>();
					while (response.next()) {
						Vector<Object> row = new Vector<>();
						for (String string : columns) {
							row.add(response.getObject(string));
						}
						data.add(row);
					}
					resultTable.setModel(new DefaultTableModel(data, colVector));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 160, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, -160, SpringLayout.EAST, this);
		add(btnSearch);

		resultTable = new JTable();
		resultTable.setFillsViewportHeight(true);

		selectQuery = new JComboBox<String>();
		springLayout.putConstraint(SpringLayout.NORTH, selectQuery, 0, SpringLayout.NORTH, btnSearch);
		springLayout.putConstraint(SpringLayout.WEST, selectQuery, 35, SpringLayout.EAST, btnSearch);
		springLayout.putConstraint(SpringLayout.EAST, selectQuery, -15, SpringLayout.EAST, this);
		selectQuery.setModel(new DefaultComboBoxModel<String>(new String[] { "Using Member", "Using Trainer" }));
		selectQuery.setSelectedIndex(0);
		selectQuery.setMaximumRowCount(2);
		springLayout.putConstraint(SpringLayout.SOUTH, selectQuery, 0, SpringLayout.SOUTH, btnSearch);
		add(selectQuery);

		scrollPane = new JScrollPane(resultTable);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, btnSearch);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		add(scrollPane);
	}

	public void setDatabase(DbConnect database) {
		this.database = database;
	}
}
