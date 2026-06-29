package controller;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Player;
import service.PlayerService;
import service.impl.PlayerServiceImpl;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPlayer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JTextField password;
	private JTextField mail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlayer frame = new AddPlayer();
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
	public AddPlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("創建帳號");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(176, 10, 80, 29);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("名稱:");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel.setBounds(75, 49, 48, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("帳號:");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(75, 89, 48, 29);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("密碼:");
		lblNewLabel_2_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(75, 129, 48, 29);
		contentPane.add(lblNewLabel_2_1);

		password = new JTextField();
		password.setFont(new Font("新細明體", Font.PLAIN, 16));
		password.setColumns(10);
		password.setBounds(120, 129, 192, 26);
		contentPane.add(password);

		JLabel lblNewLabel_2_2 = new JLabel("信箱:");
		lblNewLabel_2_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2_2.setBounds(75, 169, 48, 29);
		contentPane.add(lblNewLabel_2_2);

		JLabel error_name = new JLabel("");
		error_name.setFont(new Font("新細明體", Font.PLAIN, 14));
		error_name.setBounds(322, 55, 156, 15);
		contentPane.add(error_name);

		JLabel error_username = new JLabel("");
		error_username.setFont(new Font("新細明體", Font.PLAIN, 14));
		error_username.setBounds(322, 97, 156, 15);
		contentPane.add(error_username);

		JLabel error_mail = new JLabel("");
		error_mail.setFont(new Font("新細明體", Font.PLAIN, 14));
		error_mail.setBounds(322, 175, 156, 15);
		contentPane.add(error_mail);
		
		JLabel error_masege = new JLabel("");
		error_masege.setFont(new Font("新細明體", Font.PLAIN, 14));
		error_masege.setBounds(127, 205, 185, 15);
		contentPane.add(error_masege);

		/************************ event ************************/

		PlayerService psi=new PlayerServiceImpl();
		
		// =======================檢測名稱=======================//

		name = new JTextField();
		name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String Name=name.getText();
				boolean player=psi.check_name(Name);
				if(player==true) {
					error_name.setForeground(Color.RED);
					error_name.setText("名稱已被註冊!");
				}else {
					error_name.setText("");
				}
			}
		});
		name.setFont(new Font("新細明體", Font.PLAIN, 16));
		name.setColumns(10);
		name.setBounds(120, 49, 192, 26);
		contentPane.add(name);

		// =======================檢測帳號=======================//

		username = new JTextField();
		username.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String Username=username.getText();
				boolean player=psi.check_username(Username);
				if(player==true) {
					error_username.setForeground(Color.RED);
					error_username.setText("帳號已被註冊!");
				}else {
					error_username.setText("");
				}
			}
		});
		username.setFont(new Font("新細明體", Font.PLAIN, 16));
		username.setColumns(10);
		username.setBounds(120, 89, 192, 26);
		contentPane.add(username);

		// =======================檢測信箱=======================//

		mail = new JTextField();
		mail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String Mail=mail.getText();
				boolean player=psi.check_mail(Mail);
				if(player==true) {
					error_mail.setForeground(Color.RED);
					error_mail.setText("信箱已被註冊");
				}else {
					error_mail.setText("");
				}
			}
		});
		mail.setFont(new Font("新細明體", Font.PLAIN, 16));
		mail.setColumns(10);
		mail.setBounds(120, 169, 192, 26);
		contentPane.add(mail);

		// =======================註冊=======================//

		JButton btnNewButton_1_1_2_1 = new JButton("註冊");
		btnNewButton_1_1_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Name=name.getText();
				String Username=username.getText();
				String Mail=mail.getText();
				if(psi.check_name(Name) || psi.check_username(Username) || psi.check_mail(Mail)) {
					error_masege.setForeground(Color.RED);
					error_masege.setText("請檢查上面資訊是否無誤");
				}else {
					error_masege.setText("");
					String Password=password.getText();
					Player player=new Player(Name,Username,Password,Mail);
					psi.create_player(player);
					AddSuccess add_success=new AddSuccess();
					add_success.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_1_1_2_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_1_1_2_1.setBounds(176, 233, 80, 23);
		contentPane.add(btnNewButton_1_1_2_1);
	}
}
