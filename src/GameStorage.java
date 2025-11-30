import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStorage {

    // Методы теперь работают с БД, а не с локальным списком

    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT id, title, genre, platform, release_year, price, rating FROM gamestorage ORDER BY id";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Game game = mapResultSetToGame(rs);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all games", e);
        }
        return games;
    }

    public Game findById(Long id) {
        if (id == null) return null;

        String sql = "SELECT id, title, genre, platform, release_year, price, rating FROM gamestorage WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToGame(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find game by ID: " + id, e);
        }
        return null;
    }

    public boolean deleteById(Long id) {
        if (id == null) return false;

        String sql = "DELETE FROM gamestorage WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete game by ID: " + id, e);
        }
    }

    public void addGame(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null");
        }

        String sql = "INSERT INTO gamestorage (title, genre, platform, release_year, price, rating) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, game.getTitle());
            ps.setString(2, game.getGenre());
            ps.setString(3, game.getPlatform());
            ps.setInt(4, game.getReleaseYear());
            ps.setDouble(5, game.getPrice());
            ps.setDouble(6, game.getRating());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        game.setId(rs.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add game", e);
        }
    }

    // Вспомогательный метод для преобразования ResultSet → Game
    private Game mapResultSetToGame(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String title = rs.getString("title");
        String genre = rs.getString("genre");
        String platform = rs.getString("platform");
        int releaseYear = rs.getInt("release_year");
        double price = rs.getDouble("price");
        double rating = rs.getDouble("rating");

        return new Game(id, title, genre, platform, releaseYear, price, rating);
    }
}