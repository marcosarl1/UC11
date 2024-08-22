package dao;

import model.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    private static final String CADASTRAR_PRODUTO
            = " INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
    private static final String LISTAR_PRODUTOS
            = "SELECT * FROM produtos WHERE status = 'A Venda'";
    private static final String LISTAR_PRODUTOS_VENDIDO_STRING
            = "SELECT * FROM produtos WHERE status = 'Vendido'";

    public void cadastrarProduto(ProdutosDTO produto) throws SQLException {
        try {
            conn = new conectaDAO().connectDB();
            st = conn.prepareStatement(CADASTRAR_PRODUTO);
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            conectaDAO.closeConnection();
            conectaDAO.closeSt(st);
        }
    }

    public List<ProdutosDTO> listarProdutos() throws SQLException {
        List<ProdutosDTO> listaProdutos = new ArrayList<>();
        try {
            conn = new conectaDAO().connectDB();
            st = conn.prepareStatement(LISTAR_PRODUTOS);
            rs = st.executeQuery();
            while (rs.next()) {
                ProdutosDTO produtosDTO = new ProdutosDTO();
                produtosDTO.setId(rs.getInt("id"));;
                produtosDTO.setNome(rs.getString("nome"));
                produtosDTO.setValor(rs.getInt("valor"));
                produtosDTO.setStatus(rs.getString("status"));
                listaProdutos.add(produtosDTO);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            conectaDAO.closeConnection();
            conectaDAO.closeSt(st);
            conectaDAO.closeRs(rs);
        }
        return listaProdutos;
    }

    public List<ProdutosDTO> listarProdutosVendidos() throws SQLException {
        List<ProdutosDTO> listaProdutos = new ArrayList<>();
        try {
            conn = new conectaDAO().connectDB();
            st = conn.prepareStatement(LISTAR_PRODUTOS_VENDIDO_STRING);
            rs = st.executeQuery();
            while (rs.next()) {
                ProdutosDTO produtosDTO = new ProdutosDTO();
                produtosDTO.setId(rs.getInt("id"));
                produtosDTO.setNome(rs.getString("nome"));
                produtosDTO.setValor(rs.getInt("valor"));
                produtosDTO.setStatus(rs.getString("status"));
                listaProdutos.add(produtosDTO);
            }
        } catch (Exception e) {
        }
        return listaProdutos;
    }
}
