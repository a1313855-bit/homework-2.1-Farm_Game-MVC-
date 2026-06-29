package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PlayerDao;
import entity.Player;
import util.DbConnection;

public class PlayerDaoImpl implements PlayerDao {

	public static void main(String[] args) {
		// new PlayerDaoImpl().insert(new Player("Mr.A","aaa","1234","aaa@gmail.com"));

		// System.out.println(new PlayerDaoImpl().select_id(2));
		// System.out.println(new PlayerDaoImpl().select_name("Mr.C"));

		// Player player=new PlayerDaoImpl().select_mail("aaa@gmail.com");
		// player.setPassword("12345");
		// new PlayerDaoImpl().update_password(player);

	}

	Connection conn = DbConnection.getDb();

	// =======================新增玩家=======================//
	@Override
	public void insert(Player player) {
		String sql = "insert into player(name,username,password,mail,money) " + "values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, player.getName());
			ps.setString(2, player.getUsername());
			ps.setString(3, player.getPassword());
			ps.setString(4, player.getMail());
			ps.setInt(5, player.getMoney());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// =======================查詢ID=======================//

	@Override
	public Player select_id(int id) {
		String sql = "select * from player where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Player player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setUsername(rs.getString("username"));
				player.setPassword(rs.getString("password"));
				player.setMail(rs.getString("mail"));
				player.setMoney(rs.getInt("money"));
				return player;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// =======================查詢名稱=======================//
	@Override
	public Player select_name(String name) {
		Player player = null;
		String sql = "select * from player where name=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setUsername(rs.getString("username"));
				player.setPassword(rs.getString("password"));
				player.setMail(rs.getString("mail"));
				player.setMoney(rs.getInt("money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	// =======================查詢帳號=======================//
	@Override
	public Player select_username(String username) {
		Player player = null;
		String sql = "select * from player where username=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setUsername(rs.getString("username"));
				player.setPassword(rs.getString("password"));
				player.setMail(rs.getString("mail"));
				player.setMoney(rs.getInt("money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	// =======================查詢帳號密碼=======================//
	@Override
	public Player select_username_and_password(String username, String password) {
		Player player = null;
		String sql = "select * from player where username=? and password=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setUsername(rs.getString("username"));
				player.setPassword(rs.getString("password"));
				player.setMail(rs.getString("mail"));
				player.setMoney(rs.getInt("money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	// =======================查詢信箱=======================//
	@Override
	public Player select_mail(String mail) {
		Player player = null;
		String sql = "select * from player where mail=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setUsername(rs.getString("username"));
				player.setPassword(rs.getString("password"));
				player.setMail(rs.getString("mail"));
				player.setMoney(rs.getInt("money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	// =======================修改密碼=======================//
	@Override
	public void update_password(Player player) {
		String sql = "update player set password=? where mail=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, player.getPassword());
			ps.setString(2, player.getMail());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// =======================修改金額=======================//

	@Override
	public void update_data(Player player) {
		String sql = "update player set money=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, player.getMoney());
			ps.setInt(2, player.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
