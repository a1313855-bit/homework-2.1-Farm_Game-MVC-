package controller.playInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import entity.Bag;
import entity.Farm;
import entity.Player;
import service.BagService;
import service.FarmService;
import service.impl.BagServiceImpl;
import service.impl.FarmServiceImpl;
import util.Tool;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FarmUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private FarmService fsi;
    private BagService bsi;
    private Player player;

    private JLabel[] timeLabels = new JLabel[4];
    private JComboBox<String>[] comboBoxes = new JComboBox[4];
    private Timer[] timers = new Timer[4];

    private JButton[] plantButtons = new JButton[4];
    private JButton[] harvestButtons = new JButton[4];
    private JButton[] unlockButtons = new JButton[4];

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FarmUI frame = new FarmUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FarmUI() {

        fsi = new FarmServiceImpl();
        bsi = new BagServiceImpl();

        Object obj = Tool.read_file("player.txt");

        if (obj instanceof Player) {
            player = (Player) obj;
        } else {
            player = new Player();
            player.setName("DesignPlayer");
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 570, 385);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        createFarmPanel(0);
        createFarmPanel(1);
        createFarmPanel(2);
        createFarmPanel(3);

        JButton backButton = new JButton("返回主頁");
        backButton.setBounds(461, 312, 84, 23);
        contentPane.add(backButton);
        
        JLabel lblNewLabel_4 = new JLabel("農田");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_4.setFont(new Font("新細明體", Font.PLAIN, 20));
        lblNewLabel_4.setBounds(480, 10, 40, 34);
        contentPane.add(lblNewLabel_4);

        backButton.addActionListener(e -> {
            MainUI main_page = new MainUI();
            main_page.setVisible(true);
            dispose();
        });

        if (player.getName() != null && !player.getName().equals("DesignPlayer")) {
            load_seed();
            refreshFarmUnlockStatus();
            startTimers();
            refreshAllTime();
        }
    }

    private void createFarmPanel(int index) {

        int x = (index % 2) * 230 + 10;
        int y = (index / 2) * 175 + 10;
        int farmId = index + 1;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(Color.GREEN);
        panel.setBounds(x, y, 200, 150);
        contentPane.add(panel);

        JLabel title = new JLabel("第 " + farmId + " 塊田");
        title.setBounds(10, 10, 120, 20);
        panel.add(title);

        comboBoxes[index] = new JComboBox<>();
        comboBoxes[index].setBounds(10, 40, 180, 23);
        panel.add(comboBoxes[index]);

        timeLabels[index] = new JLabel("未種植");
        timeLabels[index].setBounds(10, 73, 180, 20);
        panel.add(timeLabels[index]);

        plantButtons[index] = new JButton("種植");
        plantButtons[index].setBounds(10, 108, 85, 23);
        panel.add(plantButtons[index]);

        plantButtons[index].addActionListener(e -> {
            plantSeed(farmId, index);
        });

        harvestButtons[index] = new JButton("收成");
        harvestButtons[index].setBounds(105, 108, 85, 23);
        panel.add(harvestButtons[index]);

        harvestButtons[index].addActionListener(e -> {
            harvestSeed(farmId, index);
        });

        unlockButtons[index] = new JButton(getUnlockText(farmId));
        unlockButtons[index].setBounds(35, 65, 130, 30);
        panel.add(unlockButtons[index]);

        unlockButtons[index].addActionListener(e -> {
            unlockFarm(farmId, index);
        });
    }

    private String getUnlockText(int farmId) {

        if (farmId == 2) {
            return "200$ 解鎖";
        }

        if (farmId == 3) {
            return "500$ 解鎖";
        }

        if (farmId == 4) {
            return "1000$ 解鎖";
        }

        return "";
    }

    private void refreshFarmUnlockStatus() {

        for (int i = 0; i < 4; i++) {

            int farmId = i + 1;

            Farm farm = fsi.find_one_farm(player.getName(), farmId);

            boolean unlocked = farm != null && farm.getIs_unlock() == 1;

            comboBoxes[i].setVisible(unlocked);
            timeLabels[i].setVisible(unlocked);
            plantButtons[i].setVisible(unlocked);
            harvestButtons[i].setVisible(unlocked);

            unlockButtons[i].setVisible(!unlocked && farmId != 1);
        }
    }

    private void unlockFarm(int farmId, int index) {

        String result = fsi.unlock_farm(player.getName(), farmId);

        JOptionPane.showMessageDialog(null, result);

        player = (Player) Tool.read_file("player.txt");

        load_seed();
        refreshFarmUnlockStatus();
        refreshAllTime();
    }

    private void plantSeed(int farmId, int index) {

        Object selected = comboBoxes[index].getSelectedItem();

        if (selected == null) {
            JOptionPane.showMessageDialog(null, "請選擇種子");
            return;
        }

        String seed = selected.toString().trim();

        String result = fsi.plant_seed(player.getName(), farmId, seed);

        JOptionPane.showMessageDialog(null, result);

        load_seed();
        updateTime(farmId, index);

        Timer once = new Timer(300, e -> updateTime(farmId, index));
        once.setRepeats(false);
        once.start();
    }

    private void harvestSeed(int farmId, int index) {

        int time = fsi.get_remaining_time(player.getName(), farmId);

        if (time < 0) {
            JOptionPane.showMessageDialog(null, "沒有種植");
            return;
        }

        if (time > 0) {
            JOptionPane.showMessageDialog(null, "尚未成熟");
            return;
        }

        String result = fsi.harvest(player.getName(), farmId);

        JOptionPane.showMessageDialog(null, result);

        load_seed();
        updateTime(farmId, index);
    }

    private void updateTime(int farmId, int index) {

        int time = fsi.get_remaining_time(player.getName(), farmId);

        if (time < 0) {
            timeLabels[index].setText("未種植");
        } else if (time == 0) {
            timeLabels[index].setText("可以收成！");
        } else {
            timeLabels[index].setText("剩餘：" + time + " 秒");
        }
    }

    private void refreshAllTime() {

        for (int i = 0; i < 4; i++) {
            updateTime(i + 1, i);
        }
    }

    private void startTimers() {

        for (int i = 0; i < 4; i++) {

            int farmId = i + 1;
            int index = i;

            timers[i] = new Timer(1000, e -> updateTime(farmId, index));
            timers[i].start();
        }
    }

    private void load_seed() {

        List<Bag> list = bsi.find_all_bag_table(player.getName());

        for (int i = 0; i < 4; i++) {
            comboBoxes[i].removeAllItems();
        }

        for (Bag bag : list) {

            if (bag.getQuantity() > 0 && bag.getItem_name().endsWith("種子")) {

                String item = bag.getItem_name().trim();

                for (int i = 0; i < 4; i++) {
                    comboBoxes[i].addItem(item);
                }
            }
        }
    }
}