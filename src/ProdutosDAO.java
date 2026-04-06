/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        conn = new conectaDAO().connectDB();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getValor());
            pstm.setString(3, produto.getStatus());

            pstm.execute();
            pstm.close();

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto DAO: " + e.getMessage());
        }
    }
    
    public void venderProduto(int id) {
    String sql = "UPDATE produtos SET status = ? WHERE id = ?";

    conn = new conectaDAO().connectDB();

    try {
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, "Vendido");
        pstm.setInt(2, id);

        pstm.executeUpdate();
        pstm.close();

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Erro ao vender produto DAO: " + e.getMessage());
    }
}

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";

        conn = new conectaDAO().connectDB();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao listar produtos DAO: " + e.getMessage());
        }
    return lista;
}
        
public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

    conn = new conectaDAO().connectDB();
    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    try {
        pstm = conn.prepareStatement(sql);
        rs = pstm.executeQuery();

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            lista.add(produto);
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    }

    return lista;
}
}

