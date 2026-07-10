import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingSystem system = new BookingSystem();
        while (true) {
            System.out.println("\n======================================");
            System.out.println("    MOVIE TICKET BOOKING SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. User Registration");
            System.out.println("4. Exit");
            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Admin Username : ");
                    String adminUser = sc.nextLine();
                    System.out.print("Enter Admin Password : ");
                    String adminPass = sc.nextLine();
                    if (system.adminLogin(adminUser, adminPass)) {
                        System.out.println("\nAdmin Login Successful.");
                        boolean adminMenu = true;
                        while (adminMenu) {
                            System.out.println("\n========== ADMIN MENU ==========");
                            System.out.println("1. Add Movie");
                            System.out.println("2. View Movies");
                            System.out.println("3. Search Movie");
                            System.out.println("4. Total Available Seats");
                            System.out.println("5. Logout");
                            System.out.print("Enter Choice : ");
                            int adminChoice = sc.nextInt();
                            sc.nextLine();
                            switch (adminChoice) {
                                case 1:
                                    System.out.print("Movie ID : ");
                                    int id = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Movie Name : ");
                                    String name = sc.nextLine();
                                    System.out.print("Show Time : ");
                                    String time = sc.nextLine();
                                    system.addMovie(id, name, time);
                                    break;
                                case 2:
                                    system.viewMovies();
                                    break;
                                case 3:
                                    System.out.print("Enter Movie ID : ");
                                    int searchId = sc.nextInt();
                                    Movie movie = system.searchMovie(searchId);
                                    if (movie == null) {
                                        System.out.println("Movie Not Found.");
                                    } else {
                                        movie.displayMovie();
                                    }
                                    break;
                                case 4:
                                    system.displayTotalSeats();
                                    break;
                                case 5:
                                    adminMenu = false;
                                    System.out.println("Logged Out Successfully.");
                                    break;
                                default:
                                    System.out.println("Invalid Choice.");
                            }
                        }
                    } else {
                        System.out.println("Invalid Admin Credentials.");
                    }
                    break;
                case 2:
                    System.out.print("Username : ");
                    String username = sc.nextLine();
                    System.out.print("Password : ");
                    String password = sc.nextLine();
                    if (system.userLogin(username, password)) {
                        System.out.println("\nLogin Successful.");
                        boolean userMenu = true;
                        while (userMenu) {
                            System.out.println("\n========== USER MENU ==========");
                            System.out.println("1. View Movies");
                            System.out.println("2. Book Ticket");
                            System.out.println("3. Cancel Ticket");
                            System.out.println("4. Search Movie");
                            System.out.println("5. My Bookings");
                            System.out.println("6. Logout");
                            System.out.print("Enter Choice : ");
                            int userChoice = sc.nextInt();
                            sc.nextLine();
                            switch (userChoice) {
                                case 1:
                                    system.viewMovies();
                                    break;
                                case 2:
                                    System.out.print("Enter Movie ID : ");
                                    int movieId = sc.nextInt();
                                    sc.nextLine();
                                    system.displaySeatLayout(movieId);
                                    System.out.print("\nEnter Seat Number (Example A1): ");
                                    String seat = sc.nextLine();
                                    system.bookTicket(username, movieId, seat);
                                    break;
                                case 3:
                                    System.out.print("Enter Movie ID : ");
                                    int cancelMovie = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter Seat Number : ");
                                    String cancelSeat = sc.nextLine();
                                    system.cancelTicket(username,
                                            cancelMovie,
                                            cancelSeat);
                                    break;
                                case 4:
                                    System.out.print("Enter Movie ID : ");
                                    int movieSearch = sc.nextInt();
                                    Movie m = system.searchMovie(movieSearch);
                                    if (m == null) {
                                        System.out.println("Movie Not Found.");
                                    } else {
                                        m.displayMovie();
                                        m.displaySeats();
                                    }
                                    break;
                                case 5:
                                    system.myBookings(username);
                                    break;
                                case 6:
                                    userMenu = false;
                                    System.out.println("Logged Out Successfully.");
                                    break;
                                default:
                                    System.out.println("Invalid Choice.");
                            }
                        }
                    } else {
                        System.out.println("Invalid Username or Password.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Username : ");
                    String newUser = sc.nextLine();
                    System.out.print("Enter Password : ");
                    String newPass = sc.nextLine();
                    if (system.registerUser(newUser, newPass)) {
                        System.out.println("Registration Successful.");
                    } else {
                        System.out.println("Username Already Exists.");
                    }
                    break;
                case 4:
                    System.out.println("Thank You.");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}