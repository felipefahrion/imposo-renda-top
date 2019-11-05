package src.com.irpf.repository.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContribuinteDAODerby {

    private static ContactDAODerby ref;

    private ContactDAODerby() throws Exception {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("JdbcOdbDriver not found!!");
        }

        try {
            createDB();
        } catch (Exception ex) {
            System.out.println("Problemas para criar o banco: " + ex.getMessage());
            System.exit(0);
        }

    }

    public static ContactDAODerby getInstance() throws Exception {
        if (ref == null)
            ref = new ContactDAODerby();
        return ref;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
    }

    @Override
    public void insert(Person person) {
        try {
            Connection con = getConnection();
            String sql = "INSERT INTO CONTACTS VALUE (?,?)";
            PreparedStatement sta = con.prepareStatement(sql);
            sta.setString(1,person.getName());
            sta.setString(2, person.getPhone());
            sta.executeUpdate(sql);
            sta.close();
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String findPhoneByName(String name) {
        try {
            Connection con = getConnection();
            String sql = "SELECT C.PHONE AS PHONE INTO CONTACTS C WHERE C.NAME = ?";
            PreparedStatement sta = con.prepareStatement(sql);
            sta.setString(1, name);

            ResultSet resultSet = sta.executeQuery();
            resultSet.next();

            String phone = resultSet.getString("PHONE");

            sta.executeUpdate(sql);
            sta.close();
            con.close();

            return phone;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<String> findAllNamesSorted() {

        List<String> names = new ArrayList<>();

        try {
            Connection con = getConnection();
            String sql = "SELECT C.NAME FROM CONTACTS C ORDER BY C.NAME";
            PreparedStatement sta = con.prepareStatement(sql);

            ResultSet resultSet = sta.executeQuery();

            while(resultSet.next()){
                names.add(resultSet.getString("C.NAME"));
            }

            sta.executeUpdate(sql);
            sta.close();
            con.close();

            return names;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void insert(List<Person> people) {

    }

    private static void createDB() throws Exception {
        try {
            Connection con = getConnection();
            Statement sta = con.createStatement();
            String sql = "CREATE TABLE CONTACTS ("
                    + "NAME VARCHAR(100) NOT NULL,"
                    + "PHONE CHAR(11) NOT NULL"
                    + ")";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
