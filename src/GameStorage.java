import java.util.*;

public class GameStorage {
    private final List<Game> games = new ArrayList<>();

    public GameStorage() {
        seedGames();
    }

    private void seedGames() {
        games.add(new Game(1L, "The Witcher 3", "RPG", "PC", 2015, 39.99, 9.3));
        games.add(new Game(2L, "Call of Duty", "Shooter", "PS5", 2023, 69.99, 7.8));
        games.add(new Game(3L, "Civilization VI", "Strategy", "PC", 2016, 29.99, 8.5));
        games.add(new Game(4L, "FIFA 24", "Sports", "Xbox", 2023, 59.99, 6.9));
        games.add(new Game(5L, "Stardew Valley", "Simulation", "PC", 2016, 14.99, 9.1));
        games.add(new Game(6L, "Elden Ring", "RPG", "PS5", 2022, 59.99, 9.5));
        games.add(new Game(7L, "Minecraft", "Sandbox", "PC", 2011, 26.95, 8.7));
        games.add(new Game(8L, "Halo Infinite", "Shooter", "Xbox", 2021, 0.0, 7.2));
        games.add(new Game(9L, "Crusader Kings III", "Strategy", "PC", 2020, 49.99, 8.9));
        games.add(new Game(10L, "God of War", "Action", "PS5", 2018, 49.99, 9.2));
        games.add(new Game(11L, "The Sims 4", "Simulation", "PC", 2014, 0.0, 8.0));
        games.add(new Game(12L, "Red Dead Redemption 2", "Action", "PC", 2019, 59.99, 9.7));
        games.add(new Game(13L, "Destiny 2", "Shooter", "PC", 2017, 0.0, 8.1));
        games.add(new Game(14L, "Portal 2", "Puzzle", "PC", 2011, 9.99, 9.6));
        games.add(new Game(15L, "Forza Horizon 5", "Racing", "Xbox", 2021, 59.99, 9.3));
        games.add(new Game(16L, "Horizon Zero Dawn", "Action", "PS5", 2020, 49.99, 8.8));
        games.add(new Game(17L, "Among Us", "Party", "PC", 2018, 4.99, 8.4));
        games.add(new Game(18L, "XCOM 2", "Strategy", "PC", 2016, 39.99, 8.6));
        games.add(new Game(19L, "Tetris Effect", "Puzzle", "PS5", 2018, 39.99, 8.9));
        games.add(new Game(20L, "Rocket League", "Sports", "PC", 2015, 0.0, 8.5));
        games.add(new Game(21L, "Valorant", "Shooter", "PC", 2020, 0.0, 8.3));
        games.add(new Game(22L, "Counter-Strike 2", "Shooter", "PC", 2023, 0.0, 9.0));
        games.add(new Game(23L, "Overwatch 2", "Shooter", "PC", 2022, 0.0, 7.5));
        games.add(new Game(24L, "Apex Legends", "Shooter", "PC", 2019, 0.0, 8.2));
        games.add(new Game(25L, "Baldur's Gate 3", "RPG", "PC", 2023, 59.99, 9.8));
    }

    public List<Game> getAllGames() {
        return new ArrayList<>(games);
    }

    public Game findById(Long id) {
        if (id == null) return null;
        for (Game game : games) {
            if (game.getId().equals(id)) {
                return game;
            }
        }
        return null;
    }

    public boolean deleteById(Long id) {
        if (id == null) return false;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId().equals(id)) {
                games.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addGame(Game game) {
        Long maxId = 0L;
        for (Game g : games) {
            if (g.getId() > maxId) maxId = g.getId();
        }
        game.setId(maxId + 1);
        games.add(game);
    }
}
