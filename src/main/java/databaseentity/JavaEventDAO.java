package databaseentity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JavaEventDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPass;
    private Connection jdbcConnection;
    private JavaEvent javaEvent;

    public JavaEventDAO(String jdbcURL, String jdbcUsername, String jdbcPass){
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPass = jdbcPass;
    }

    public void connect() throws SQLException{
        if(jdbcConnection == null || jdbcConnection.isClosed()){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new   SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPass);
        }
    }

    public void disconnect()throws SQLException{
        if(jdbcConnection !=null && !jdbcConnection.isClosed()){
            jdbcConnection.close();
        }
    }

    public boolean insertJavaEvent(JavaEvent javaEvent) throws SQLException {
        String sql = "INSERT INTO java_event (id, title, description, data_of_event)" +
                " VALUES (?, ?, ?, ?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(2, javaEvent.getTitle());
        statement.setString(3, javaEvent.getDescription());
        statement.setDate(4, javaEvent.getDateOfEvent());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<JavaEvent> listAllJavaEvents()throws SQLException{
        List<JavaEvent> javaEventList = new ArrayList<>();
        String sql = "SELECT * FROM java_event";
        connect();
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            Date dataOfEvent = resultSet.getDate("data_of_event");

            JavaEvent javaEvent = new JavaEvent(id, title, description, dataOfEvent);
            javaEventList.add(javaEvent);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return javaEventList;
    }

    public boolean deleteJavaEvent(JavaEvent javaEvent)throws SQLException{
        String sql = "DELETE FROM java_event WHERE id = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, javaEvent.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateJavaEvent(JavaEvent javaEvent) throws SQLException{
        String sql = "UPDATE java_event SET title = ?, description = ?, data_of_event = ?" +
                " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, javaEvent.getTitle());
        statement.setString(2, javaEvent.getDescription());
        statement.setDate(3, javaEvent.getDateOfEvent());
        statement.setInt(4, javaEvent.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public JavaEvent getJavaEvent(int id) throws SQLException{
        String sql = "SELECT * FROM java_event WHERE id = ?";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            Date dateOfEvent = resultSet.getDate("data_of_event");

            javaEvent = new JavaEvent(id, title, description, dateOfEvent);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return javaEvent;
    }
}
