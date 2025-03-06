package br.com.crudcidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {
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

    public boolean createCidade(Cidade cidade) {
        String sql = "INSERT INTO cidades (codigo_ibge, nome, latitude, longitude, capital, codigo_uf, siafi_id, ddd, fuso_horario, nome_normalizado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cidade.getCodigoIbge());
            stmt.setString(2, cidade.getNome());
            stmt.setDouble(3, cidade.getLatitude());
            stmt.setDouble(4, cidade.getLongitude());
            stmt.setBoolean(5, cidade.isCapital());
            stmt.setInt(6, cidade.getCodigoUf());
            stmt.setString(7, cidade.getSiafiId());
            stmt.setInt(8, cidade.getDdd());
            stmt.setString(9, cidade.getFusoHorario());
            stmt.setString(10, cidade.getNomeNormalizado());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cidade> getAllCidades() {
        List<Cidade> cidades = new ArrayList<>();
        String sql = "SELECT * FROM cidades";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cidades.add(new Cidade(
                        rs.getInt("codigo_ibge"),
                        rs.getString("nome"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getBoolean("capital"),
                        rs.getInt("codigo_uf"),
                        rs.getString("siafi_id"),
                        rs.getInt("ddd"),
                        rs.getString("fuso_horario"),
                        rs.getString("nome_normalizado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidades;
    }

	public String getNomeEstado(int codigoUf) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cidade> getCidadesPorNome(String nomePesquisa) {
		// TODO Auto-generated method stub
		return null;
	}
}
