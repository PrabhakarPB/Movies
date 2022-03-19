package movies;

import java.util.Scanner;
import java.sql.*;

public class Movies {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies", "root", "");
        boolean run = true;
        while (run) {
            System.out.println("\n*****Welcome*****");
            System.out.println("1.Insert your favorite movies");
            System.out.println("2.Display your favourite movies");
            System.out.println("3.Search your favorite movie based on the lead actor");
            System.out.println("4.Exit"); 
            System.out.println("Enter your choice:");
            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.println("**Enter your favourite movie details**");
                    System.out.println("Enter name of the movie:");
                    String name = sc.next();
                    System.out.println("Enter the name of lead actor of the movie:");
                    String leadactor = sc.next();
                    System.out.println("Enter the name of lead actress of the movie:");
                    String leadactress = sc.next();
                    System.out.println("Enter year of release of the movie:");
                    int year = sc.nextInt();
                    System.out.println("Enter the name of director of the movie:");
                    String director = sc.next();

                    PreparedStatement stmt = con.prepareStatement("insert into movies(name,leadactor,leadactress,year,director) values(?,?,?,?,?)");
                    stmt.setString(1, name);
                    stmt.setString(2, leadactor);
                    stmt.setString(3, leadactress);
                    stmt.setInt(4, year);
                    stmt.setString(5, director);
                    stmt.executeUpdate();
                    break;

                case 2:
                    System.out.println("Your favourite movies:");
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("Select * From movies");

                    while (rs.next()) {
                        System.out.println("***********");
                        System.out.println("Name of the movie: "+rs.getString(1));
                        System.out.println("Name of the lead actor: "+rs.getString(2));
                        System.out.println("Name of the lead actress: "+rs.getString(3));
                        System.out.println("Year of release: "+rs.getInt(4));
                        System.out.println("Name of the director: "+rs.getString(5));
                        System.out.println("***********");
                    }

                    con.close();

                    break;
                    
                case 3:
                    System.out.println("Enter name of leadactor to search movie:");
                   String lactor=sc.next();
                    String sql = "Select * From movies Where leadactor='"+lactor+"'";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                      System.out.println("***********");
                        System.out.println("Name of the movie: "+rst.getString(1));
                        System.out.println("Name of the lead actor: "+rst.getString(2));
                        System.out.println("Name of the lead actress: "+rst.getString(3));
                        System.out.println("Year of release: "+rst.getInt(4));
                        System.out.println("Name of the director: "+rst.getString(5));
                        System.out.println("***********");
            }
            else
            {
            System.out.println("Could'nt find the movie");
            }
                    break;
                    
                case 4:
                    run = false;
                    break;
                default:
                    break;
            }
        }

    }

}
