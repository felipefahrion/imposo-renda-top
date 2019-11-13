package src.com.irpf.repository.dao;

import src.com.irpf.repository.dto.ContribuinteDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContribuinteDAODerby implements ContribuinteDAOInterface {

    private static ContribuinteDAODerby ref;

    private ContribuinteDAODerby() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("JdbcOdbDriver not found!!");
        }

        try {
            createDB();
        } catch (Exception ex) {
            System.out.println("Problemas para criar o banco: " + ex.getMessage());
            System.exit(0);
        }

    }

    public static ContribuinteDAODerby getInstance() {
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

    public ContribuinteDTO find(String cpf) {
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM CONTRIBUINTE C WHERE CPF = ?";
            PreparedStatement sta = con.prepareStatement(sql);
            sta.setString(1, cpf);
            ResultSet resultSet = sta.executeQuery();

            resultSet.next();
            String nome = resultSet.getString("NOME");
            String cpfResult = resultSet.getString("CPF");
            int idade = resultSet.getInt("IDADE");
            int dependentes = resultSet.getInt("DEPENDENTES");
            String contribuicaoOficial = resultSet.getString("CONTRIBUICAO_OFICIAL");
            String rendimentoTotal = resultSet.getString("RENDIMENTO_TOTAL");
            String valorIrpf = resultSet.getString("VALOR_IRPF");

            sta.executeUpdate(sql);
            sta.close();
            con.close();

            return new ContribuinteDTO(nome, cpfResult, idade, dependentes, new BigDecimal(contribuicaoOficial), new BigDecimal(rendimentoTotal), new BigDecimal(valorIrpf));
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


    public List<ContribuinteDTO> findAll() {
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM CONTRIBUINTE C";
            PreparedStatement sta = con.prepareStatement(sql);
            ResultSet resultSet = sta.executeQuery();

            List<ContribuinteDTO> contribuinteDTOS = new ArrayList<>();
            while (resultSet.next()) {
                String nome = resultSet.getString("NOME");
                String cpfResult = resultSet.getString("CPF");
                int idade = resultSet.getInt("IDADE");
                int dependentes = resultSet.getInt("DEPENDENTES");
                String contribuicaoOficial = resultSet.getString("CONTRIBUICAO_OFICIAL");
                String rendimentoTotal = resultSet.getString("RENDIMENTO_TOTAL");
                String valorIrpf = resultSet.getString("VALOR_IRPF");
                ContribuinteDTO contribuinte = new ContribuinteDTO(nome,
                        cpfResult, idade, dependentes,
                        new BigDecimal(contribuicaoOficial),
                        new BigDecimal(rendimentoTotal),
                        new BigDecimal(valorIrpf));
                contribuinteDTOS.add(contribuinte);
            }
            sta.executeUpdate(sql);
            sta.close();
            con.close();

            return contribuinteDTOS;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void insert(List<ContribuinteDTO> people) {
        for (int i = 0; i < people.size(); i++) {
            insert(people.get(i));
        }
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
