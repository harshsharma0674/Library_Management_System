import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available: " + (available ? "Yes" : "No");
    }
}

class User {
    private String username;
    private String password;
    private ArrayList<Book> borrowedBooks;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }
}

class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    public User currentUser;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        // Add some sample books
        books.add(new Book("Core Java", "DR. R. NAGESWARA RAO"));
        books.add(new Book("Oop with C++", "E BALAGURUSAMY"));
        books.add(new Book("Cryptography & N/W Security", "ATUL KAHATE"));
        // Add some sample users
        users.add(new User("Raju", "Raju1234"));
        users.add(new User("Codealpha", "Code1234"));
    }

    public void displayBooks() {
        System.out.println("\n--- Available Books ---");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    public void loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful");
                System.out.println(" Welcome " + username + "!");
                return;
            }
        }
        System.out.println("Invalid username or password. Please try again.");
    }

    public void logoutUser() {
        currentUser = null;
        System.out.println("Logout successful.");
    }

    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.isAvailable()) {
                currentUser.borrowBook(book);
                System.out.println("Book '" + title + "' borrowed successfully.");
                return;
            }
        }
        System.out.println("Book not available or does not exist.");
    }

    public void returnBook(String title) {
        for (Book book : currentUser.getBorrowedBooks()) {
            if (book.getTitle().equals(title)) {
                currentUser.returnBook(book);
                System.out.println("Book '" + title + "' returned successfully.");
                return;
            }
        }
        System.out.println("You did not borrow this book.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            if (library.currentUser == null) {
                System.out.print("\n1. Login\n2. Exit\nEnter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        library.loginUser(username, password);
                        break;

                    case 2:
                        System.out.println("Exiting the E-Library System. Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } else {
                System.out.print("\n1. View Books\n2. Borrow Book\n3. Return Book\n4. Logout\nEnter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        library.displayBooks();
                        break;

                    case 2:
                        System.out.print("Enter the title of the book to borrow: ");
                        String borrowTitle = scanner.nextLine();
                        library.borrowBook(borrowTitle);
                        break;

                    case 3:
                        System.out.print("Enter the title of the book to return: ");
                        String returnTitle = scanner.nextLine();
                        library.returnBook(returnTitle);
                        break;

                    case 4:
                        library.logoutUser();
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
    }
}
