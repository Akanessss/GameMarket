import java.util.*;

public class GameApp {
    private final Scanner scanner = new Scanner(System.in);
    private final GameStorage storage = new GameStorage();
    private final GameFilterService filterService = new GameFilterService(storage);

    public static void main(String[] args) {
        new GameApp().run();
    }

    public void run() {
        while (true) {
            showMenu();
            System.out.print("Ваш выбор: ");
            int choice = getInt();

            switch (choice) {
                case 1 -> showAll();
                case 2 -> filterByGenre();
                case 3 -> filterByPlatform();
                case 4 -> filterByPriceRange();
                case 5 -> combinedFilter();
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор.");
            }
            System.out.println("\n--- Нажмите Enter ---");
            scanner.nextLine();
        }
    }

    private void showMenu() {
        System.out.println("\n=== Каталог Игр ===");
        System.out.println("1. Показать все игры");
        System.out.println("2. Фильтр по жанру");
        System.out.println("3. Фильтр по платформе");
        System.out.println("4. Фильтр по цене");
        System.out.println("5. Комбинированный фильтр");
        System.out.println("0. Выход");
    }

    private void showAll() {
        List<Game> games = storage.getAllGames();
        printGames(games);
    }

    private void filterByGenre() {
        System.out.print("Введите жанр (RPG, Shooter и т.д.): ");
        String genre = scanner.nextLine();
        List<Game> games = filterService.findByGenre(genre);
        printGames(games);
    }

    private void filterByPlatform() {
        System.out.print("Введите платформу (PC, PS5, Xbox): ");
        String platform = scanner.nextLine();
        List<Game> games = filterService.findByPlatform(platform);
        printGames(games);
    }

    private void filterByPriceRange() {
        System.out.print("Минимальная цена ($, Enter — без ограничения): ");
        Double min = parseDoubleOrNull(scanner.nextLine().trim());
        System.out.print("Максимальная цена ($, Enter — без ограничения): ");
        Double max = parseDoubleOrNull(scanner.nextLine().trim());
        List<Game> games = filterService.findByPriceRange(min, max);
        printGames(games);
    }

    private void combinedFilter() {
        System.out.print("Жанр (Enter — любой): ");
        String genre = scanner.nextLine().trim();
        if (genre.isEmpty()) genre = null;

        System.out.print("Платформа (Enter — любая): ");
        String platform = scanner.nextLine().trim();
        if (platform.isEmpty()) platform = null;

        System.out.print("Мин. год (Enter — любой): ");
        Integer minYear = parseIntOrNull(scanner.nextLine().trim());
        System.out.print("Макс. год (Enter — любой): ");
        Integer maxYear = parseIntOrNull(scanner.nextLine().trim());

        System.out.print("Мин. цена (Enter — любая): ");
        Double minPrice = parseDoubleOrNull(scanner.nextLine().trim());
        System.out.print("Макс. цена (Enter — любая): ");
        Double maxPrice = parseDoubleOrNull(scanner.nextLine().trim());

        List<Game> games = filterService.filter(genre, platform, minPrice, maxPrice, minYear, maxYear);
        printGames(games);
    }

    private void printGames(List<Game> games) {
        if (games.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            for (int i = 0; i < games.size(); i++) {
                System.out.println((i + 1) + ". " + games.get(i));
            }
        }
    }

    // Вспомогательные методы ввода
    private int getInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private Integer parseIntOrNull(String s) {
        if (s.isEmpty()) return null;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double parseDoubleOrNull(String s) {
        if (s.isEmpty()) return null;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}