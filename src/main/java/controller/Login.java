package controller;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Bag;
import entity.Player;
import service.BagService;
import service.PlayerService;
import service.impl.BagServiceImpl;
import service.impl.PlayerServiceImpl;
import util.Tool;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("帳號:");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel.setBounds(26, 44, 48, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("文字農場");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(102, 5, 80, 29);
		contentPane.add(lblNewLabel_1);

		username = new JTextField();
		username.setFont(new Font("新細明體", Font.PLAIN, 16));
		username.setBounds(71, 44, 192, 26);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("密碼:");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(26, 95, 48, 29);
		contentPane.add(lblNewLabel_2);

		password = new JTextField();
		password.setFont(new Font("新細明體", Font.PLAIN, 16));
		password.setColumns(10);
		password.setBounds(71, 95, 192, 26);
		contentPane.add(password);

		

		/************************ event ************************/
		
		PlayerService psi=new PlayerServiceImpl();
		BagService bsi=new BagServiceImpl();
		
		//=======================登入=======================//
		
		JButton btnNewButton_1_1_2 = new JButton("登入");
		btnNewButton_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Username=username.getText();
				String Password=password.getText();
				Player player=psi.login(Username, Password);
				if(player!=null) {
					Tool.save_file("player.txt",player);
					
					LoginSuccess login_success=new LoginSuccess();
					login_success.setVisible(true);
					dispose();
				}else {
					LoginError login_error=new LoginError();
					login_error.setVisible(true);
				}
			}
		});
		btnNewButton_1_1_2.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_1_1_2.setBounds(55, 153, 70, 23);
		contentPane.add(btnNewButton_1_1_2);

		//=======================註冊=======================//
		
		JButton btnNewButton_1_1_2_1 = new JButton("註冊");
		btnNewButton_1_1_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddPlayer add_player=new AddPlayer();
				add_player.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1_2_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_1_1_2_1.setBounds(152, 153, 70, 23);
		contentPane.add(btnNewButton_1_1_2_1);

		//=======================修改密碼=======================//
		
		JLabel loss_password = new JLabel("忘記密碼?");
		loss_password.setFont(new Font("新細明體", Font.PLAIN, 12));
		loss_password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//修改顏色
				loss_password.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//修改顏色
				loss_password.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePassword change_password=new ChangePassword();
				change_password.setVisible(true);
				dispose();
			}
		});
		loss_password.setBounds(203, 128, 60, 15);
		contentPane.add(loss_password);
		
	}
}
