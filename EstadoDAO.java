package br.com.crudcidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    private static final String URL = "jdbc:postgresql://indieexpert.ddns.net:5432/fc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "I@Expert@!2025";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    public boolean createEstado(Estado estado) {
        String sql = "INSERT INTO estados (codigo_uf, nome, uf) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estado.getCodigoUf());
            stmt.setString(2, estado.getNome());
            stmt.setString(3, estado.getUf());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Estado> getAllEstados() {
        List<Estado> estados = new ArrayList<>();
        String sql = "SELECT codigo_uf, nome, uf FROM estados";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                estados.add(new Estado(rs.getInt("codigo_uf"), rs.getString("nome"), rs.getString("uf")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estados;
    }
}
