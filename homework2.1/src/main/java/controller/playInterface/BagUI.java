package controller.playInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.PlayerDao;
import dao.impl.PlayerDaoImpl;
import entity.Bag;
import entity.Player;
import service.BagService;
import service.impl.BagServiceImpl;
import util.Tool;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;

public class BagUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel show_money;

	private Player player;
	private PlayerDao pdi = new PlayerDaoImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BagUI frame = new BagUI();
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
	public BagUI() {

		player = (Player) Tool.read_file("player.txt");

		// 匯入玩家資料
		Player player = (Player) Tool.read_file("player.txt");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 320);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("背包");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel.setBounds(125, 10, 40, 22);
		contentPane.add(lblNewLabel);

		/************************ event ************************/

		BagService bsi = new BagServiceImpl();

		// =======================背包物品=======================//

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 266, 199);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);

		List<Bag> list = bsi.find_all_bag_table(player.getName());
		DefaultTableModel model = new DefaultTableModel();
		// 建立欄位標題
		model.addColumn("物品");
		model.addColumn("數量");
		for (Bag bag : list) {
			Object[] row = new Object[] { bag.getItem_name(), bag.getQuantity() };
			model.addRow(row);
		}
		table.setModel(model);

		// =======================顯示金額=======================//

		show_money = new JLabel("");
		show_money.setFont(new Font("新細明體", Font.PLAIN, 16));
		show_money.setBounds(20, 252, 74, 19);
		contentPane.add(show_money);
		refreshMoney();

		// =======================返回主頁=======================//

		JButton btnNewButton_1 = new JButton("返回主頁");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainUI main_page = new MainUI();
				main_page.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(192, 250, 84, 23);
		contentPane.add(btnNewButton_1);

	}

	private void refreshMoney() {

		Player latestPlayer = pdi.select_name(player.getName());

		if (latestPlayer != null) {
			player = latestPlayer;
			show_money.setText("金錢：" + player.getMoney());
		}
	}
}
