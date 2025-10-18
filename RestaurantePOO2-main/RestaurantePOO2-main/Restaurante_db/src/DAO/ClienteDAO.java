
package DAO;

import DTO.ClienteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClienteDAO {
     Connection conexao;
    PreparedStatement pst;
    ResultSet rs;

    // CREATE
    public void cadastrarCliente(ClienteDTO objClienteDTO) {
        String sql = "INSERT INTO clientes (nome, telefone, email) VALUES (?, ?, ?)";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objClienteDTO.getNome());
            pst.setString(2, objClienteDTO.getTelefone());
            pst.setString(3, objClienteDTO.getEmail());
            pst.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    // READ
    public ArrayList<ClienteDTO> listarClientes() {
        ArrayList<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE
    public void atualizarCliente(ClienteDTO objClienteDTO) {
        String sql = "UPDATE clientes SET nome=?, telefone=?, email=? WHERE id_cliente=?";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objClienteDTO.getNome());
            pst.setString(2, objClienteDTO.getTelefone());
            pst.setString(3, objClienteDTO.getEmail());
            pst.setInt(4, objClienteDTO.getId_cliente());
            pst.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // DELETE
    public void excluirCliente(int id_cliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente=?";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id_cliente);
            pst.executeUpdate();
            System.out.println("Cliente excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}


