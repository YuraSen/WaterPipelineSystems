package senin.utility;

import java.sql.*;

public class DataBaseUtility {

    private static final String DRIVER = "org.h2.Driver";
    private static final String CONNECTION = "jdbc:h2:~/dbH2/pipeline";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String EXCEPTION_TABLE = "Don`t create a table";
    private static final String EXCEPTION_DATA = "Don`t save a data";

    public static void createTableForPipeline() {
        String sql = "CREATE TABLE IF NOT EXISTS PIPELINE(start int, end int, length int)";
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(EXCEPTION_TABLE);
        }
    }

    public static void insertPipeLineToTable(int start, int end, int length) {
        String sql = "INSERT INTO PIPELINE" + "(start, end, length) VALUES" + "(?,?,?)";
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);{
                {
                    statement.setInt(1, start);
                    statement.setInt(2, end);
                    statement.setInt(3, length);
                    statement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(EXCEPTION_DATA);;
        }
    }
}
