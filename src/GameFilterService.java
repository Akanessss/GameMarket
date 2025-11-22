import java.util.*;

public class GameFilterService {
    private final GameStorage storage;

    public GameFilterService(GameStorage storage) {
        this.storage = storage;
    }

    public List<Game> findByGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) return storage.getAllGames();
        List<Game> result = new ArrayList<>();
        for (Game game : storage.getAllGames()) {
            if (game.getGenre().equalsIgnoreCase(genre.trim())) {
                result.add(game);
            }
        }
        return result;
    }

    public List<Game> findByPlatform(String platform) {
        if (platform == null || platform.trim().isEmpty()) return storage.getAllGames();
        List<Game> result = new ArrayList<>();
        for (Game game : storage.getAllGames()) {
            if (game.getPlatform().equalsIgnoreCase(platform.trim())) {
                result.add(game);
            }
        }
        return result;
    }

    public List<Game> findByPriceRange(Double minPrice, Double maxPrice) {
        List<Game> result = new ArrayList<>();
        for (Game game : storage.getAllGames()) {
            Double price = game.getPrice();
            if (price == null) continue;

            boolean minOk = (minPrice == null) || (price >= minPrice);
            boolean maxOk = (maxPrice == null) || (price <= maxPrice);

            if (minOk && maxOk) {
                result.add(game);
            }
        }
        return result;
    }

    public List<Game> filter(
            String genre,
            String platform,
            Double minPrice,
            Double maxPrice,
            Integer minYear,
            Integer maxYear
    ) {
        List<Game> result = new ArrayList<>();
        for (Game game : storage.getAllGames()) {
            boolean matches = true;
            // Жанр
            if (genre != null && !genre.trim().isEmpty()) {
                if (!game.getGenre().equalsIgnoreCase(genre.trim())) {
                    matches = false;
                }
            }
            // Платформа
            if (matches && platform != null && !platform.trim().isEmpty()) {
                if (!game.getPlatform().equalsIgnoreCase(platform.trim())) {
                    matches = false;
                }
            }

            // Год
            if (matches && minYear != null) {
                if (game.getReleaseYear() < minYear) matches = false;
            }
            if (matches && maxYear != null) {
                if (game.getReleaseYear() > maxYear) matches = false;
            }

            // Цена
            Double price = game.getPrice();
            if (matches && price != null) {
                if (minPrice != null && price < minPrice) matches = false;
                if (maxPrice != null && price > maxPrice) matches = false;
            } else if (matches && (minPrice != null || maxPrice != null)) {
                matches = false;
            }

            if (matches) {
                result.add(game);
            }
        }
        return result;
    }
}
