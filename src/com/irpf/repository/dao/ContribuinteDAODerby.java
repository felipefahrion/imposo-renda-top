package src.com.irpf.repository.dao;

import src.com.irpf.repository.dto.ContribuinteDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContribuinteDAODerby {

    private static ContribuinteDAODerby ref;

    private ContribuinteDAODerby() throws Exception {
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

    public static ContribuinteDAODerby getInstance() throws Exception {
        if (ref == null)
            ref = new ContribuinteDAODerby();
        return ref;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
    }

    public void insert(ContribuinteDTO contribuinteDTO) {
        try {
            Connection con = getConnection();
            String sql = "INSERT INTO CONTRIBUINTE VALUE (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sta = con.prepareStatement(sql);
            sta.setString(1, contribuinteDTO.getNome());
            sta.setString(2, contribuinteDTO.getCPF());
            sta.setInt(3, contribuinteDTO.getIdade());
            sta.setInt(4, contribuinteDTO.getDependentes());
            sta.setBigDecimal(5, contribuinteDTO.getContribuicaoOficial());
            sta.setBigDecimal(6, contribuinteDTO.getRendimentoTotal());
            sta.setBigDecimal(7, contribuinteDTO.getValorIRPF());

            sta.executeUpdate(sql);

            sta.close();
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

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

    public void insert(List<ContribuinteDTO> people) {

    }

    private static void createDB() throws Exception {
        try {
            Connection con = getConnection();
            Statement sta = con.createStatement();
            String sql = "CREATE TABLE CONTRIBUINTE ("
                    + "NOME VARCHAR(100) NOT NULL,"
                    + "CPF VARCHAR(11) NOT NULL,"
                    + "IDADE NUMBER(2) NOT NULL,"
                    + "DEPENDENTES NUMBER(2),"
                    + "CONTRIBUICAO_OFICIAL VARCHAR(11),"
                    + "RENDIMENTO_TOTAL VARCHAR(11) NOT NULL,"
                    + "VALOR_IRPF VARCHAR(11),"
                    + ")";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
