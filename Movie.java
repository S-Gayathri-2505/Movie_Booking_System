public class Movie {

    private int movieId;
    private String movieName;
    private String showTime;
    private int availableSeats;

    // 4 Rows × 6 Columns
    private char[][] seats;

    // Stores username of person who booked each seat
    private String[][] bookedBy;

    public Movie(int movieId, String movieName, String showTime) {

        this.movieId = movieId;
        this.movieName = movieName;
        this.showTime = showTime;

        seats = new char[4][6];
        bookedBy = new String[4][6];

        availableSeats = 24;

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 6; j++) {

                seats[i][j] = 'O';
                bookedBy[i][j] = "";
            }
        }
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public char[][] getSeats() {
        return seats;
    }

    public String[][] getBookedBy() {
        return bookedBy;
    }

    public void displayMovie() {

        System.out.println("-----------------------------------");
        System.out.println("Movie ID : " + movieId);
        System.out.println("Movie Name : " + movieName);
        System.out.println("Show Time : " + showTime);
        System.out.println("Available Seats : " + availableSeats);
    }

    public void displaySeats() {

        System.out.println("\n              SCREEN\n");

        System.out.print("    ");

        for (int i = 1; i <= 6; i++)
            System.out.print(i + " ");

        System.out.println();

        char row = 'A';

        for (int i = 0; i < 4; i++) {

            System.out.print(row++ + "   ");

            for (int j = 0; j < 6; j++) {

                System.out.print(seats[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println("\nO = Available");
        System.out.println("X = Booked");
    }
}