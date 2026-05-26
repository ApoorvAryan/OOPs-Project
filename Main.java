import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface LibraryOperations {
    void issueBook();
    void returnBook();
}

class Book {
    private int bookId;
    private String bookName;
    private String authorName;
    private boolean available;

    public Book(int id, String name, String author) {
        bookId = id;
        bookName = name;
        authorName = author;
        available = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailability(boolean status) {
        available = status;
    }

    public void displayBook() {
        System.out.println("\nBook ID: " + bookId);
        System.out.println("Book Name: " + bookName);
        System.out.println("Author: " + authorName);
        System.out.println("Status: " + (available ? "Available" : "Issued"));
    }
}

class Person {
    protected int id;
    protected String name;

    public Person(int i, String n) {
        id = i;
        name = n;
    }

    public void displayDetails() {
        System.out.println("\nID: " + id);
        System.out.println("Name: " + name);
    }
}

class Student extends Person {
    private String department;
    private List<Integer> borrowedBooks;

    public Student(int i, String n, String dept) {
        super(i, n);
        department = dept;
        borrowedBooks = new ArrayList<>();
    }

    public int getStudentId() {
        return id;
    }

    public boolean borrowBook(int bookId) {
        if (borrowedBooks.size() >= 3) {
            System.out.println("\nBorrow limit exceeded!");
            return false;
        }
        borrowedBooks.add(bookId);
        return true;
    }

    public boolean returnBook(int bookId) {
        for (int i = 0; i < borrowedBooks.size(); i++) {
            if (borrowedBooks.get(i) == bookId) {
                borrowedBooks.remove(i);
                return true;
            }
        }
        System.out.println("\nThis book was not borrowed by student!");
        return false;
    }

    @Override
    public void displayDetails() {
        System.out.println("\nStudent ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Department: " + department);
        System.out.print("Borrowed Books: ");

        if (borrowedBooks.isEmpty()) {
            System.out.println("None");
        } else {
            for (int b : borrowedBooks) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }
}

class Librarian extends Person {
    public Librarian(int i, String n) {
        super(i, n);
    }

    @Override
    public void displayDetails() {
        System.out.println("\nLibrarian ID: " + id);
        System.out.println("Librarian Name: " + name);
    }
}

class Library implements LibraryOperations {
    private List<Book> books;
    private List<Student> students;
    private Scanner scanner;

    public Library(Scanner scanner) {
        books = new ArrayList<>();
        students = new ArrayList<>();
        this.scanner = scanner;
    }

    public void addBook(Book b) {
        books.add(b);
        System.out.println("\nBook Added Successfully!");
    }

    public void addStudent(Student s) {
        students.add(s);
        System.out.println("\nStudent Added Successfully!");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("\nNo books available!");
            return;
        }
        for (Book b : books) {
            b.displayBook();
        }
    }

    public void viewStudents() {
        for (Student s : students) {
            s.displayDetails();
        }
    }

    @Override
    public void issueBook() {
        System.out.print("\nEnter Student ID: ");
        int sid = scanner.nextInt();

        System.out.print("Enter Book ID: ");
        int bid = scanner.nextInt();

        for (Book b : books) {
            if (b.getBookId() == bid) {
                if (!b.isAvailable()) {
                    System.out.println("\nBook already issued!");
                    return;
                }
                for (Student s : students) {
                    if (s.getStudentId() == sid) {
                        if (s.borrowBook(bid)) {
                            b.setAvailability(false);
                            System.out.println("\nBook Issued Successfully!");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("\nBook or Student not found!");
    }

    @Override
    public void returnBook() {
        System.out.print("\nEnter Book ID to Return: ");
        int bid = scanner.nextInt();

        for (Book b : books) {
            if (b.getBookId() == bid) {
                for (Student s : students) {
                    if (s.returnBook(bid)) {
                        b.setAvailability(true);
                        System.out.println("\nBook Returned Successfully!");
                        return;
                    }
                }
            }
        }
        System.out.println("\nBook not found!");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library lib = new Library(scanner);
        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Student");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. View Students");
            System.out.println("7. Exit");
            System.out.print("Enter Choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("\nEnter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Book Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();

                    Book b = new Book(id, name, author);
                    lib.addBook(b);
                    break;
                }
                case 2:
                    lib.viewBooks();
                    break;

                case 3: {
                    System.out.print("\nEnter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();

                    Student s = new Student(id, name, dept);
                    lib.addStudent(s);
                    break;
                }
                case 4:
                    lib.issueBook();
                    break;

                case 5:
                    lib.returnBook();
                    break;

                case 6:
                    lib.viewStudents();
                    break;

                case 7:
                    System.out.println("\nThank You!");
                    break;

                default:
                    System.out.println("\nInvalid Choice!");
            }
        } while (choice != 7);

        scanner.close();
    }
}