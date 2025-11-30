

public class Game {
    private Long id;
    private String title;
    private String genre;    // RPG, Shooter и т.д.
    private String platform;    // PC, PS5, Xbox
    private int releaseYear;    // только год
    private Double price;    // в $
    private Double rating;    // от 0.0 до 10.0

    public Game(Long id, String title, String genre, String platform,
                int releaseYear, Double price, Double rating) {
        setId(id);
        setTitle(title);
        setGenre(genre);
        setPlatform(platform);
        setReleaseYear(releaseYear);
        setPrice(price);
        setRating(rating);
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getPlatform() { return platform; }
    public int getReleaseYear() { return releaseYear; }
    public Double getPrice() { return price; }
    public Double getRating() { return rating; }

    public void setId(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("ID должен быть положительным");
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Название не может быть пустым");
        this.title = title.trim();
    }

    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) throw new IllegalArgumentException("Жанр не может быть пустым");
        this.genre = genre.trim();
    }

    public void setPlatform(String platform) {
        if (platform == null || platform.trim().isEmpty()) throw new IllegalArgumentException("Платформа не может быть пустой");
        this.platform = platform.trim();
    }

    public void setReleaseYear(int releaseYear) {
        int currentYear = java.time.Year.now().getValue();
        if (releaseYear < 1970 || releaseYear > currentYear)
            throw new IllegalArgumentException("Год должен быть от 1970 до " + currentYear);
        this.releaseYear = releaseYear;
    }

    public void setPrice(Double price) {
        if (price != null && price < 0) throw new IllegalArgumentException("Цена не может быть отрицательной");
        this.price = price;
    }

    public void setRating(Double rating) {
        if (rating != null && (rating < 0.0 || rating > 10.0))
            throw new IllegalArgumentException("Рейтинг должен быть от 0.0 до 10.0");
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Название: %-25s | Жанр: %-12s | Платформа: %-10s | Год: %d | Цена: $%6.2f | Рейтинг: %4.1f/10",
                id, title, genre, platform, releaseYear, price == null ? 0.0 : price, rating == null ? 0.0 : rating
        );
    }
}
