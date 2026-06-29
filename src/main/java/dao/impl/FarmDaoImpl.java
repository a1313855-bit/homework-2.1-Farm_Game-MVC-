package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.FarmDao;
import entity.Farm;
import util.DbConnection;

public class FarmDaoImpl implements FarmDao {

    Connection conn = DbConnection.getDb();

    // ====================每位玩家擁有的田====================//

    @Override
    public void insert(String player_name) {
        String sql = "insert into farm(player_name,farm_number,seed_name,plant_time,is_unlock) "
                + "values(?,1,null,null,1),"
                + "(?,2,null,null,0),"
                + "(?,3,null,null,0),"
                + "(?,4,null,null,0)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);
            ps.setString(2, player_name);
            ps.setString(3, player_name);
            ps.setString(4, player_name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ====================查詢某個玩家的土地====================//

    @Override
    public List<Farm> select_player(String player_name) {

        String sql = "select * from farm where player_name=? order by farm_number";
        List<Farm> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Farm farm = new Farm();
                farm.setId(rs.getInt("id"));
                farm.setPlayer_name(rs.getString("player_name"));
                farm.setFarm_number(rs.getInt("farm_number"));
                farm.setSeed_name(rs.getString("seed_name"));
                farm.setPlant_time(rs.getTimestamp("plant_time"));
                farm.setIs_unlock(rs.getInt("is_unlock"));
                list.add(farm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ====================查詢某個玩家的某個土地====================//

    @Override
    public Farm select_one(String player_name, int farm_number) {
        String sql = "select * from farm where player_name=? and farm_number=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);
            ps.setInt(2, farm_number);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Farm farm = new Farm();
                farm.setId(rs.getInt("id"));
                farm.setPlayer_name(rs.getString("player_name"));
                farm.setFarm_number(rs.getInt("farm_number"));
                farm.setSeed_name(rs.getString("seed_name"));
                farm.setPlant_time(rs.getTimestamp("plant_time"));
                farm.setIs_unlock(rs.getInt("is_unlock"));
                return farm;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ====================查詢種子的收成時間====================//

    @Override
    public int select_grow_time(String seed_name) {
        String sql = "select grow_time from shop where seed_name=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, seed_name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("grow_time");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ====================更新田區種植狀態====================//

    @Override
    public void plant_seed(String player_name, int farm_number, String seed_name) {
        String sql = "update farm set seed_name=?, plant_time=now() where player_name=? and farm_number=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, seed_name);
            ps.setString(2, player_name);
            ps.setInt(3, farm_number);

            int row = ps.executeUpdate();
            System.out.println("farm update row = " + row);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ====================更新田區收成狀態====================//

    @Override
    public void clearField(String player_name, int farm_number) {
        String sql = "update farm set seed_name=null, plant_time=null where player_name=? and farm_number=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);
            ps.setInt(2, farm_number);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // ====================修改田區狀態====================//
    
    @Override
    public void unlock_farm(String player_name, int farm_number) {
        String sql = "update farm set is_unlock=1 where player_name=? and farm_number=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);
            ps.setInt(2, farm_number);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
