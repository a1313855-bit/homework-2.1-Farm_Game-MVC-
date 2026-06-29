package controller.playInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Login;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
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
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("選單");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel.setBounds(120, 23, 40, 24);
		contentPane.add(lblNewLabel);

		/************************ event ************************/

		// =======================商店頁面=======================//

		JButton btnNewButton = new JButton("商店");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShopUI farm_shop=new ShopUI();
				farm_shop.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton.setBounds(36, 61, 85, 23);
		contentPane.add(btnNewButton);

		// =======================背包頁面=======================//

		JButton btnNewButton_1 = new JButton("背包");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BagUI bag_ui=new BagUI();
				bag_ui.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_1.setBounds(164, 61, 85, 23);
		contentPane.add(btnNewButton_1);

		// =======================農場頁面=======================//

		JButton btnNewButton_2 = new JButton("農場");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FarmUI farm_ui=new FarmUI();
				farm_ui.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_2.setBounds(36, 112, 85, 23);
		contentPane.add(btnNewButton_2);

		// =======================小店頁面=======================//

		JButton btnNewButton_2_1 = new JButton("小店");
		btnNewButton_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PlayerShopUI player_shop_ui=new PlayerShopUI();
				player_shop_ui.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_2_1.setBounds(164, 112, 85, 23);
		contentPane.add(btnNewButton_2_1);

		// =======================教學頁面=======================//

		JButton btnNewButton_2_2_1 = new JButton("教學");
		btnNewButton_2_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TeachUI teach_ui=new TeachUI();
				teach_ui.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_2_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_2_2_1.setBounds(36, 164, 85, 23);
		contentPane.add(btnNewButton_2_2_1);

		// =======================登出頁面=======================//

		JButton btnNewButton_2_2 = new JButton("登出");
		btnNewButton_2_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login=new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_2.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_2_2.setBounds(164, 164, 85, 23);
		contentPane.add(btnNewButton_2_2);

	}

}
