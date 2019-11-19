package com.irpf.repository.dao;

import com.irpf.repository.dto.ContribuinteDTO;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
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
            String sql = "INSERT INTO CONTRIBUINTE " +
                    "(NOME, IDADE, DEPENDENTES, CONTRIBUICAO_OFICIAL, RENDIMENTO_TOTAL, VALOR_IRPF) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement sta = con.prepareStatement(sql);
            sta.setString(1, contribuinteDTO.getNome());
            sta.setInt(2, contribuinteDTO.getIdade());
            sta.setInt(3, contribuinteDTO.getDependentes());
            sta.setBigDecimal(4, contribuinteDTO.getContribuicaoOficial());
            sta.setBigDecimal(5, contribuinteDTO.getRendimentoTotal());
            sta.setBigDecimal(6, contribuinteDTO.getValorIRPF());
            sta.executeUpdate();
            sta.close();
            con.close();
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane optionPane = new JOptionPane("Já existe um usuário cadastrado com esse nome.", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Failure");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<ContribuinteDTO> find(String nomeParcial) {
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM CONTRIBUINTE C WHERE NOME LIKE ?";
            PreparedStatement sta = con.prepareStatement(sql);
            sta.setString(1, "%" + nomeParcial + "%");
            ResultSet resultSet = sta.executeQuery();

            List<ContribuinteDTO> contribuinteDTOS = new ArrayList<>();
            while (resultSet.next()) {
                String nome = resultSet.getString("NOME");
                Integer idade = resultSet.getInt("IDADE");
                Integer dependentes = resultSet.getInt("DEPENDENTES");
                String contribuicaoOficial = resultSet.getString("CONTRIBUICAO_OFICIAL");
                String rendimentoTotal = resultSet.getString("RENDIMENTO_TOTAL");
                String valorIrpf = resultSet.getString("VALOR_IRPF");
                ContribuinteDTO contribuinte = new ContribuinteDTO(nome,
                        idade,
                        dependentes,
                        contribuicaoOficial == null ? null : new BigDecimal(contribuicaoOficial),
                        rendimentoTotal == null ? null : new BigDecimal(rendimentoTotal),
                        valorIrpf == null ? null : new BigDecimal(valorIrpf));
                contribuinteDTOS.add(contribuinte);
            }
            sta.close();
            con.close();

            return contribuinteDTOS;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


    public List<ContribuinteDTO> findAll() {
        try {
            Connection con = getConnection();
            Statement sta = con.createStatement();
            ResultSet resultSet = sta.executeQuery("SELECT * FROM CONTRIBUINTE C");

            List<ContribuinteDTO> contribuinteDTOS = new ArrayList<>();
            while (resultSet.next()) {
                String nome = resultSet.getString("NOME");
                int idade = resultSet.getInt("IDADE");
                int dependentes = resultSet.getInt("DEPENDENTES");
                String contribuicaoOficial = resultSet.getString("CONTRIBUICAO_OFICIAL");
                String rendimentoTotal = resultSet.getString("RENDIMENTO_TOTAL");
                String valorIrpf = resultSet.getString("VALOR_IRPF");
                ContribuinteDTO contribuinte = new ContribuinteDTO(nome,
                        idade,
                        dependentes,
                        contribuicaoOficial == null ? null : new BigDecimal(contribuicaoOficial),
                        rendimentoTotal == null ? null : new BigDecimal(rendimentoTotal),
                        valorIrpf == null ? null : new BigDecimal(valorIrpf));
                contribuinteDTOS.add(contribuinte);
            }
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
                    + " NOME VARCHAR(100) NOT NULL PRIMARY KEY,"
                    + " IDADE NUMERIC NOT NULL,"
                    + " DEPENDENTES NUMERIC,"
                    + " CONTRIBUICAO_OFICIAL VARCHAR(30),"
                    + " RENDIMENTO_TOTAL VARCHAR(30) NOT NULL,"
                    + " VALOR_IRPF VARCHAR(30)"
                    + " )";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
        } catch (SQLException ex) {
//            throw new Exception(ex.getMessage());
        }
    }
}
