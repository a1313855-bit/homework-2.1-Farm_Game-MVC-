package controller.playInterface;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import dao.BagDao;
import dao.PlayerDao;
import dao.impl.BagDaoImpl;
import dao.impl.PlayerDaoImpl;
import entity.Player;
import entity.Shop;
import service.ShopService;
import service.impl.ShopServiceImpl;
import util.Tool;

public class ShopUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel show_money;

	private Player player;
	private ShopService ssi = new ShopServiceImpl();
	private PlayerDao pdi = new PlayerDaoImpl();
	private BagDao bdi = new BagDaoImpl();
	private JScrollPane scrollPane_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopUI frame = new ShopUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ShopUI() {

		player = (Player) Tool.read_file("player.txt");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("商店");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 40, 22);
		contentPane.add(lblNewLabel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(452, 41, 174, 179);
		contentPane.add(scrollPane_1);

		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);

		show_money = new JLabel("");
		show_money.setHorizontalAlignment(SwingConstants.RIGHT);
		show_money.setFont(new Font("新細明體", Font.PLAIN, 16));
		show_money.setBounds(500, 15, 126, 19);
		contentPane.add(show_money);

		refreshMoney();

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 432, 212);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		loadShopTable();

		JButton buy_button = new JButton("購買");
		buy_button.setBounds(452, 230, 84, 23);
		contentPane.add(buy_button);

		buy_button.addActionListener(e -> buySelectedSeed());

		JButton btnNewButton_1 = new JButton("返回主頁");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainUI main_page = new MainUI();
				main_page.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(542, 230, 84, 23);
		contentPane.add(btnNewButton_1);
	}

	private void loadShopTable() {

		List<Shop> list = ssi.find_all_shop_table();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("商品");
		model.addColumn("價格");
		model.addColumn("成熟時間");

		for (Shop shop : list) {
			Object[] row = new Object[] {
					shop.getSeed_name(),
					shop.getBuy_price(),
					shop.getGrow_time()
			};
			model.addRow(row);
		}

		table.setModel(model);
	}

	private void refreshMoney() {

		Player latestPlayer = pdi.select_name(player.getName());

		if (latestPlayer != null) {
			player = latestPlayer;
			show_money.setText("金錢：" + player.getMoney());
		}
	}

	private void buySelectedSeed() {

		int row = table.getSelectedRow();

		if (row == -1) {
			textArea.setText("請先選擇商品");
			return;
		}

		String shop_name = table.getValueAt(row, 0).toString();

		String result = ssi.buy_seed(player.getName(), shop_name);

		refreshMoney();

		int count = bdi.select_item_count(player.getName(), shop_name);

		textArea.append(
				result + "\n" +
				"購買：" + shop_name + "\n" +
				"目前背包數量：" + count + "\n=====================\n"
		);
	}
}