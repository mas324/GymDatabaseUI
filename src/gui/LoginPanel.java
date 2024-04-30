package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import util.DbConnect;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField url;
	private JTextField name;
	private JTextField password;

	/**
	 * Create the panel.
	 */
	public LoginPanel(Main main) {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblURL = new JLabel("Database URL");
		springLayout.putConstraint(SpringLayout.NORTH, lblURL, 70, SpringLayout.NORTH, this);
		lblURL.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.WEST, lblURL, 75, SpringLayout.WEST, this);
		add(lblURL);

		url = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, url, 0, SpringLayout.NORTH, lblURL);
		springLayout.putConstraint(SpringLayout.WEST, url, 58, SpringLayout.EAST, lblURL);
		springLayout.putConstraint(SpringLayout.EAST, url, -20, SpringLayout.EAST, this);
		add(url);
		url.setColumns(10);

		JLabel lblName = new JLabel("Username");
		springLayout.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, lblURL);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 35, SpringLayout.SOUTH, lblURL);
		lblName.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.EAST, lblName, 0, SpringLayout.EAST, lblURL);
		add(lblName);

		name = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, name, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, name, 0, SpringLayout.WEST, url);
		springLayout.putConstraint(SpringLayout.SOUTH, name, 0, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.EAST, name, 0, SpringLayout.EAST, url);
		add(name);
		name.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblName);
		lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 35, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblURL);
		add(lblPassword);

		password = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, password, 0, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.WEST, password, 0, SpringLayout.WEST, name);
		springLayout.putConstraint(SpringLayout.SOUTH, password, 0, SpringLayout.SOUTH, lblPassword);
		springLayout.putConstraint(SpringLayout.EAST, password, 0, SpringLayout.EAST, name);
		add(password);
		password.setColumns(10);

		JButton btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		springLayout.putConstraint(SpringLayout.WEST, btnConnect, 100, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnConnect, -100, SpringLayout.EAST, this);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DbConnect db;
				if (url.getText().isBlank() || name.getText().isBlank() || password.getText().isBlank())
					db = new DbConnect();
				else
					db = new DbConnect(url.getText(), name.getText(), password.getText());

				if (db.connect()) {
					main.setDatabaseQuery(db);
					main.showQueryPanel();
				} else
					throw new Error("No database exists");
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnConnect, -24, SpringLayout.SOUTH, this);
		add(btnConnect);
	}
}
