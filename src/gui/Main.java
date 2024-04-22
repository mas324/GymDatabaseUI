package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import util.DbConnect;

public class Main {

	private JFrame frame;
	private JTextField url;
	private JTextField name;
	private JTextField password;
	private DbConnect database;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JLabel lblURL = new JLabel("Database URL");
		springLayout.putConstraint(SpringLayout.NORTH, lblURL, 66, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblURL, 75, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblURL);

		url = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, url, 0, SpringLayout.NORTH, lblURL);
		springLayout.putConstraint(SpringLayout.WEST, url, 58, SpringLayout.EAST, lblURL);
		frame.getContentPane().add(url);
		url.setColumns(10);

		JLabel lblName = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 39, SpringLayout.SOUTH, lblURL);
		springLayout.putConstraint(SpringLayout.EAST, lblName, 0, SpringLayout.EAST, lblURL);
		frame.getContentPane().add(lblName);

		name = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, name, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.EAST, name, 0, SpringLayout.EAST, url);
		frame.getContentPane().add(name);
		name.setColumns(10);

		JLabel lblPassword = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 35, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblURL);
		frame.getContentPane().add(lblPassword);

		password = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, password, 0, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.WEST, password, 0, SpringLayout.WEST, url);
		frame.getContentPane().add(password);
		password.setColumns(10);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				database = new DbConnect(url.getText(), name.getText(), password.getText());
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnConnect, 142, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnConnect, -24, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnConnect);
	}
}
