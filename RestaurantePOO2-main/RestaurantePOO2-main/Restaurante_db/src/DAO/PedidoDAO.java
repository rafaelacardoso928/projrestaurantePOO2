
package DAO;

import DTO.PedidoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class PedidoDAO {
    Connection conexao;
    PreparedStatement pst;
    ResultSet rs;

    // CREATE
    public void cadastrarPedido(PedidoDTO objPedidoDTO) {
        String sql = "INSERT INTO pedidos (id_cliente, data_pedido, valor_total) VALUES (?, ?, ?)";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objPedidoDTO.getId_cliente());
            pst.setDate(2, new java.sql.Date(objPedidoDTO.getData_pedido().getTime()));
            pst.setDouble(3, objPedidoDTO.getValor_total());
            pst.executeUpdate();
            System.out.println("Pedido cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // READ
    public ArrayList<PedidoDTO> listarPedidos() {
        ArrayList<PedidoDTO> lista = new ArrayList<>();
        String sql = "SELECT p.id_pedido, p.id_cliente, p.data_pedido, p.valor_total FROM pedidos p";

        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                PedidoDTO pedido = new PedidoDTO();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setId_cliente(rs.getInt("id_cliente"));
                pedido.setData_pedido(rs.getDate("data_pedido"));
                pedido.setValor_total(rs.getDouble("valor_total"));
                lista.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return lista;
    }

    // UPDATE
    public void atualizarPedido(PedidoDTO objPedidoDTO) {
        String sql = "UPDATE pedidos SET id_cliente = ?, data_pedido = ?, valor_total = ? WHERE id_pedido = ?";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objPedidoDTO.getId_cliente());
            pst.setDate(2, new java.sql.Date(objPedidoDTO.getData_pedido().getTime()));
            pst.setDouble(3, objPedidoDTO.getValor_total());
            pst.setInt(4, objPedidoDTO.getId_pedido());
            pst.executeUpdate();
            System.out.println("Pedido atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pedido: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // DELETE
    public void apagarPedido(int id_pedido) {
        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id_pedido);
            pst.executeUpdate();
            System.out.println("Pedido apagado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao apagar pedido: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // FECHAR CONEXÃO
    private void fecharConexao() {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conexao != null) conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexões: " + ex.getMessage());
        }
    }
}


