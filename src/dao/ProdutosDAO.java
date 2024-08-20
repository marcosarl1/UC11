package dao;

import model.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    private static final String CADASTRAR_PRODUTO 
            = " INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) throws SQLException {
        try {
            conn = new conectaDAO().connectDB();
            st = conn.prepareStatement(CADASTRAR_PRODUTO);
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            st.executeUpdate();
        } catch (SQLException e) {
        } finally {
            conectaDAO.closeConnection();
            conectaDAO.closeSt(st);
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }
}
