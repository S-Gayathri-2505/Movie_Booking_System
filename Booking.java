public class Booking {

    private String username;
    private int movieId;
    private String seatNumber;

    public Booking(String username, int movieId, String seatNumber) {

        this.username = username;
        this.movieId = movieId;
        this.seatNumber = seatNumber;
    }

    public String getUsername() {
        return username;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}