package br.com.thiago.testeTecnicoPlugin.dao;

import br.com.thiago.testeTecnicoPlugin.model.PlayerHome;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeDAOImpl implements HomeDAO {
    private final Connection connection;

    public HomeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveHome(PlayerHome home) {
        String sql = "INSERT INTO homes (player_id, home_name, world, x, y, z) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, home.getPlayerId().toString());
            stmt.setString(2, home.getHomeName());
            stmt.setString(3, home.getLocation().getWorld().getName());
            stmt.setDouble(4, home.getLocation().getX());
            stmt.setDouble(5, home.getLocation().getY());
            stmt.setDouble(6, home.getLocation().getZ());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlayerHome getHome(UUID playerId) {
        String sql = "SELECT * FROM homes WHERE player_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, playerId.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String homeName = rs.getString("home_name");
                String worldName = rs.getString("world");
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double z = rs.getDouble("z");
                World world = Bukkit.getWorld(worldName);
                Location location = new Location(world, x, y, z);
                return new PlayerHome(playerId, homeName, location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PlayerHome> getHomes(UUID playerId) {
        String sql = "SELECT * FROM homes WHERE player_id = ?";
        List<PlayerHome> homes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, playerId.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String homeName = rs.getString("home_name");
                String worldName = rs.getString("world");
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double z = rs.getDouble("z");
                World world = Bukkit.getWorld(worldName);
                Location location = new Location(world, x, y, z);
                homes.add(new PlayerHome(playerId, homeName, location));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homes;
    }

    @Override
    public int countHomes(UUID playerId) {
        String sql = "SELECT COUNT(*) FROM homes WHERE player_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, playerId.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
