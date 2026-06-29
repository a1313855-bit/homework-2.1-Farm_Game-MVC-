package controller.playInterface;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import entity.Bag;
import entity.Player;
import entity.Shop;
import service.BagService;
import service.ShopService;
import service.impl.BagServiceImpl;
import service.impl.ShopServiceImpl;
import util.Tool;

public class PlayerShopUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    private Player player;
    private BagService bsi = new BagServiceImpl();
    private ShopService ssi = new ShopServiceImpl();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlayerShopUI frame = new PlayerShopUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PlayerShopUI() {

        player = (Player) Tool.read_file("player.txt");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);     

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 327, 243);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel = new JLabel("玩家商店");
        lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
        lblNewLabel.setBounds(342, 10, 84, 22);
        contentPane.add(lblNewLabel);

        
        
        /************************ event ************************/
        
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
        btnNewButton_1.setBounds(342, 230, 84, 23);
        contentPane.add(btnNewButton_1);
        
     // =======================出售商品=======================//
        
        JButton btnNewButton_1_1 = new JButton("出售");
        btnNewButton_1_1.setBounds(342, 197, 84, 23);
        contentPane.add(btnNewButton_1_1);

        btnNewButton_1_1.addActionListener(e -> sellSelectedCrop());   

        loadCropTable();
    }

    private void loadCropTable() {

        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "農作物", "數量", "售價" }, 0
        );

        List<Bag> list = bsi.find_all_bag_table(player.getName());

        for (Bag bag : list) {

            String itemName = bag.getItem_name();

            if (bag.getQuantity() > 0 && !itemName.endsWith("種子")) {

                String seedName = itemName + "種子";
                Shop shop = ssi.find_shop_by_name(seedName);

                int sellPrice = 0;

                if (shop != null) {
                    sellPrice = shop.getSell_price();
                }

                model.addRow(new Object[] {
                        itemName,
                        bag.getQuantity(),
                        sellPrice
                });
            }
        }

        table.setModel(model);
    }

    private void sellSelectedCrop() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "請選擇要出售的農作物");
            return;
        }

        String cropName = table.getValueAt(row, 0).toString();

        String result = ssi.sell_crop(player.getName(), cropName);

        JOptionPane.showMessageDialog(null, result);

        loadCropTable();
    }
}