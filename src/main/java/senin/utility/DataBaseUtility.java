package senin.utility;

import senin.exception.H2RuntimeException;

import java.sql.*;
import java.util.Objects;

public class DataBaseUtility {
    private static final String DB_CONNECTION = "jdbc:h2:file:/C:/Users/senin/IdeaProjects/WaterPipilineSystems/dbH2";
    public static final String DONT_CREATE_TABLE = "Can`t create table";
    public static final String DONT_SAVE_DATA = "Can`t save data";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static void createPipelineTable() {
        try(Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = Objects.requireNonNull(connection).createStatement();
            statement.execute("CREATE TABLE PIPELINE(start int, end int, length int)");
        } catch (SQLException e) {
            throw new H2RuntimeException(DONT_CREATE_TABLE);
        }
    }

    public static void insertPipeLine(int start, int end, int length) {
        String insertQuery = "INSERT INTO PIPELINE" + "(start, end, length) VALUES" + "(?,?,?)";
        try(Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(insertQuery)) {
            statement.setInt(1, start);
            statement.setInt(2, end);
            statement.setInt(3, length);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new H2RuntimeException(DONT_SAVE_DATA);
        }
    }
}
