package JDprogram;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter the Option");
            System.out.println("1) Create a New Student");
            System.out.println("2) Student List");
            System.out.println("3) Find Students by Name");
            System.out.println("4) Find Student by Roll No");
            System.out.println("5) Exit");
            System.out.print("Enter the Number: ");
            int option = sc.nextInt();
            sc.nextLine();  

            try (Connection con = DBConnection.getConnection()) {
                switch (option) {
                    case 1:
                        System.out.println("Enter Student Name:");
                        String name = sc.nextLine();
                        System.out.println("Enter Roll No:");
                        int roll = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter Department:");
                        String dept = sc.nextLine();

                        PreparedStatement check = con.prepareStatement("SELECT * FROM students WHERE id = ?");
                        check.setInt(1, roll);
                        ResultSet rsCheck = check.executeQuery();
                        if (rsCheck.next()) {
                            System.out.println("Student with this roll number already exists.");
                        } else {
                            PreparedStatement insert = con.prepareStatement("INSERT INTO students (id, name, department) VALUES (?, ?, ?)");
                            insert.setInt(1, roll);
                            insert.setString(2, name);
                            insert.setString(3, dept);
                            insert.executeUpdate();
                            System.out.println("Student added successfully.");
                        }
                        break;

                    case 2:
                        System.out.println("Showing the Student List");
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM students");
                        System.out.println("Name\tRoll No\tDept");
                        while (rs.next()) {
                            System.out.println(rs.getString("name") + "\t" + rs.getInt("id") + "\t" + rs.getString("department"));
                        }
                        break;

                    case 3:
                        System.out.println("Enter the Student Name to search:");
                        String searchName = sc.nextLine();
                        PreparedStatement findByName = con.prepareStatement("SELECT * FROM students WHERE name = ?");
                        findByName.setString(1, searchName);
                        ResultSet rsByName = findByName.executeQuery();
                        System.out.println("Results:");
                        boolean found = false;
                        while (rsByName.next()) {
                            found = true;
                            System.out.println(rsByName.getString("name") + "\t" + rsByName.getInt("id") + "\t" + rsByName.getString("department"));
                        }
                        if (!found) {
                            System.out.println("No student found with that name.");
                        }
                        break;

                    case 4:
                        System.out.println("Enter Roll No:");
                        int findRoll = sc.nextInt();
                        PreparedStatement findByRoll = con.prepareStatement("SELECT * FROM students WHERE id = ?");
                        findByRoll.setInt(1, findRoll);
                        ResultSet rsByRoll = findByRoll.executeQuery();
                        if (rsByRoll.next()) {
                            System.out.println("Here is the student data:");
                            System.out.println("Name: " + rsByRoll.getString("name"));
                            System.out.println("Roll No: " + rsByRoll.getInt("id"));
                            System.out.println("Department: " + rsByRoll.getString("department"));
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            }
        }
    }
}
