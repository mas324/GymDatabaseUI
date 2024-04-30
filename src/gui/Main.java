package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import util.DbConnect;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginPanel login;
	private QueryPanel query;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.setVisible(true);
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
		setResizable(false);
		login = new LoginPanel(this);
		query = new QueryPanel();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Gym Database UI");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 350);
		getContentPane().add(login);
	}

	public void showQueryPanel() {
		getContentPane().removeAll();
		setSize(1000, 500);
		getContentPane().add(query);
		revalidate();
		repaint();
	}

	public void setDatabaseQuery(DbConnect database) {
		query.setDatabase(database);
	}

}
