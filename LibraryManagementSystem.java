
// LibraryManagementSystem.java
// Simple Library Management System using OOP concepts

import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

class User {
    private String name;
    private int userId;
    private List<Book> issuedBooks;

    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
        this.issuedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void issueBook(Book book) {
        if (book.isIssued()) {
            System.out.println("Sorry, this book is already issued.");
        } else {
            book.issueBook();
            issuedBooks.add(book);
            System.out.println(name + " issued " + book.getTitle());
        }
    }

    public void returnBook(Book book) {
        if (issuedBooks.contains(book)) {
            book.returnBook();
            issuedBooks.remove(book);
            System.out.println(name + " returned " + book.getTitle());
        } else {
            System.out.println("You didn’t issue this book.");
        }
    }

    public void showIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println(name + " has no issued books.");
        } else {
            System.out.println(name + "'s issued books:");
            for (Book b : issuedBooks) {
                System.out.println("- " + b.getTitle());
            }
        }
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void showAllBooks() {
        System.out.println("\nAvailable Books in Library:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public Book findBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        library.addBook(new Book("Java Basics", "James Gosling"));
        library.addBook(new Book("Python Guide", "Guido van Rossum"));
        library.addBook(new Book("C++ Primer", "Bjarne Stroustrup"));

        User user1 = new User("Amit", 101);

        int choice;
        do {
            System.out.println("\n==== Library Menu ====");
            System.out.println("1. Show all books");
            System.out.println("2. Issue a book");
            System.out.println("3. Return a book");
            System.out.println("4. Show my books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    library.showAllBooks();
                    break;
                case 2:
                    System.out.print("Enter book title to issue: ");
                    String issueTitle = sc.nextLine();
                    Book issueBook = library.findBookByTitle(issueTitle);
                    if (issueBook != null) {
                        user1.issueBook(issueBook);
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = sc.nextLine();
                    Book returnBook = library.findBookByTitle(returnTitle);
                    if (returnBook != null) {
                        user1.returnBook(returnBook);
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;
                case 4:
                    user1.showIssuedBooks();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
