import java.util.ArrayList;
public class BookingSystem {
    private ArrayList<Movie> movies;
    private ArrayList<User> users;
    private ArrayList<Booking> bookings;
    public BookingSystem() {
        movies = new ArrayList<>();
        users = new ArrayList<>();
        bookings = new ArrayList<>();
        loadDefaultMovies();
    }
    // Preloaded Movies
    public void loadDefaultMovies() {
        movies.add(new Movie(101, "Mankatha", "7:30 PM"));
        movies.add(new Movie(102, "96", "10:00 AM"));
        movies.add(new Movie(103, "Geetha Govindam", "1:00 PM"));
        movies.add(new Movie(104, "La La Land", "4:00 PM"));
        movies.add(new Movie(105, "Doraemon Movie", "9:00 PM"));

    }
    // -------------------------
    // ADMIN LOGIN
    // -------------------------
    public boolean adminLogin(String username, String password) {

        return username.equals("admin") &&
               password.equals("admin123");
    }
    // -------------------------
    // USER REGISTRATION
    // -------------------------
    public boolean registerUser(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }
        users.add(new User(username, password));
        return true;
    }
    // -------------------------
    // USER LOGIN
    // -------------------------
    public boolean userLogin(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username)
                    && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    // -------------------------
    // ADD MOVIE
    // -------------------------
    public void addMovie(int id,
                         String name,
                         String time) {
        movies.add(new Movie(id, name, time));
        System.out.println("\nMovie Added Successfully.");
    }
    // -------------------------
    // VIEW MOVIES
    // -------------------------
    public void viewMovies() {
        if (movies.isEmpty()) {
            System.out.println("No Movies Available.");
            return;
        }
        for (Movie m : movies) {
            m.displayMovie();
        }
    }
    // -------------------------
    // SEARCH MOVIE
    // -------------------------
    public Movie searchMovie(int movieId) {
        for (Movie m : movies) {
            if (m.getMovieId() == movieId) {
                return m;
            }
        }
        return null;
    }
    // -------------------------
    // DISPLAY TOTAL SEATS
    // -------------------------
    public void displayTotalSeats() {
        int total = 0;
        for (Movie m : movies) {
            total += m.getAvailableSeats();
        }
        System.out.println("\nTotal Available Seats : " + total);
    }
    // Getter Methods
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    // -------------------------
// BOOK TICKET
// -------------------------
public void bookTicket(String username, int movieId, String seatNumber) {
    Movie movie = searchMovie(movieId);
    if (movie == null) {
        System.out.println("Movie Not Found.");
        return;
    }
    seatNumber = seatNumber.toUpperCase();
    if (seatNumber.length() != 2) {
        System.out.println("Invalid Seat Number.");
        return;
    }
    int row = seatNumber.charAt(0) - 'A';
    int col = seatNumber.charAt(1) - '1';
    if (row < 0 || row >= 4 || col < 0 || col >= 6) {
        System.out.println("Invalid Seat.");
        return;
    }
    char[][] seats = movie.getSeats();
    if (seats[row][col] == 'X') {
        System.out.println("Seat Already Booked.");
        return;
    }
    seats[row][col] = 'X';
    movie.getBookedBy()[row][col] = username;
    movie.setAvailableSeats(movie.getAvailableSeats() - 1);
    bookings.add(new Booking(username, movieId, seatNumber));
    System.out.println("\nTicket Booked Successfully.");
    System.out.println("Movie : " + movie.getMovieName());
    System.out.println("Seat : " + seatNumber);
    }
    // -------------------------
// CANCEL TICKET
// -------------------------
public void cancelTicket(String username,
                         int movieId,
                         String seatNumber) {
    Movie movie = searchMovie(movieId);
    if (movie == null) {
        System.out.println("Movie Not Found.");
        return;
    }
    seatNumber = seatNumber.toUpperCase();
    int row = seatNumber.charAt(0) - 'A';
    int col = seatNumber.charAt(1) - '1';
    if (!movie.getBookedBy()[row][col].equals(username)) {
        System.out.println("You didn't book this seat.");
        return;
    }
    movie.getSeats()[row][col] = 'O';
    movie.getBookedBy()[row][col] = "";
    movie.setAvailableSeats(movie.getAvailableSeats() + 1);
    for (int i = 0; i < bookings.size(); i++) {
        Booking b = bookings.get(i);
        if (b.getUsername().equals(username)
                && b.getMovieId() == movieId
                && b.getSeatNumber().equals(seatNumber)) {
            bookings.remove(i);
            break;
        }
    }
    System.out.println("Ticket Cancelled Successfully.");
    }
    // -------------------------
// MY BOOKINGS
// -------------------------
public void myBookings(String username) {
    boolean found = false;
    System.out.println("\n===== MY BOOKINGS =====");
    for (Booking b : bookings) {
        if (b.getUsername().equals(username)) {
            Movie movie = searchMovie(b.getMovieId());
            System.out.println("----------------------------");
            System.out.println("Movie : " + movie.getMovieName());
            System.out.println("Seat  : " + b.getSeatNumber());
            found = true;
        }
    }
    if (!found) {
        System.out.println("No Bookings Found.");
    }
    }
    // -------------------------
// DISPLAY SEATS
// -------------------------
public void displaySeatLayout(int movieId) {
    Movie movie = searchMovie(movieId);
    if (movie == null) {
        System.out.println("Movie Not Found.");
        return;
    }
    movie.displaySeats();
}
}