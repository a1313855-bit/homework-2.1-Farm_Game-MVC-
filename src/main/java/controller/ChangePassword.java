package controller;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class ChangePassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mail;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_2_2 = new JLabel("信箱:");
		lblNewLabel_2_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2_2.setBounds(79, 58, 48, 29);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_1 = new JLabel("忘記密碼");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(174, 10, 80, 29);
		contentPane.add(lblNewLabel_1);

		JLabel error_mail = new JLabel("");
		error_mail.setFont(new Font("新細明體", Font.PLAIN, 14));
		error_mail.setBounds(326, 64, 110, 15);
		contentPane.add(error_mail);

		JLabel lblNewLabel_2_2_1 = new JLabel("新密碼:");
		lblNewLabel_2_2_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2_2_1.setBounds(61, 99, 66, 29);
		contentPane.add(lblNewLabel_2_2_1);

		password = new JTextField();
		password.setFont(new Font("新細明體", Font.PLAIN, 16));
		password.setColumns(10);
		password.setBounds(124, 99, 192, 26);
		contentPane.add(password);

		
		/************************ event ************************/

		PlayerService psi = new PlayerServiceImpl();

		// =======================檢測信箱=======================//

		mail = new JTextField();
		mail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String Mail=mail.getText();
				boolean player=psi.check_mail(Mail);
				if(player==true) {
					error_mail.setText("");
				}else {
					error_mail.setForeground(Color.RED);
					error_mail.setText("未被註冊的信箱");
				}
			}
		});
		mail.setFont(new Font("新細明體", Font.PLAIN, 16));
		mail.setColumns(10);
		mail.setBounds(124, 58, 192, 26);
		contentPane.add(mail);
		
		JLabel error_masege = new JLabel("");
		error_masege.setFont(new Font("新細明體", Font.PLAIN, 14));
		error_masege.setBounds(145, 135, 185, 15);
		contentPane.add(error_masege);
		
		// =======================修改信箱=======================//
		
		JButton btnNewButton_1_1_2_1 = new JButton("修改密碼");
		btnNewButton_1_1_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Mail=mail.getText();
				if(psi.check_mail(Mail)) {
					error_masege.setText("");
					String Password=password.getText();
					psi.chenge_password(Mail, Password);
					ChangePasswordSuccess change_password_success=new ChangePasswordSuccess();
					change_password_success.setVisible(true);
					dispose();
				}else {
					error_masege.setForeground(Color.RED);
					error_masege.setText("請檢查上面資訊是否無誤");
				}
			}
		});
		btnNewButton_1_1_2_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_1_1_2_1.setBounds(164, 166, 103, 23);
		contentPane.add(btnNewButton_1_1_2_1);


	}

}
