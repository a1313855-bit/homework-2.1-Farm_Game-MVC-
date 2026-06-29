package controller;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.playInterface.MainUI;
import entity.Player;
import util.Tool;

public class LoginSuccess extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSuccess frame = new LoginSuccess();
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
	public LoginSuccess() {
		
		Player player=(Player) Tool.read_file("player.txt");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(125, 160, 250, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel show_player = new JLabel("");
		show_player.setBounds(47, 10, 155, 15);
		contentPane.add(show_player);
		show_player.setText("登入成功 "+player.getName()+" 歡迎你!");
		
		/************************ event ************************/
		
		JButton btnNewButton = new JButton("確定");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainUI main_page=new MainUI();
				main_page.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(80, 35, 70, 23);
		contentPane.add(btnNewButton);
	}

}
