/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;


public class Projeto {
    private int id;
    private String nomeProj;
    private String descricaoProj;
    private String usuarioProprietario;
    private int idUsuario;
       
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    ConexaoBD conexao = new ConexaoBD();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNomeProj() {
        return nomeProj;
    }

    public void setNomeProj(String nomeProj) {
        this.nomeProj = nomeProj;
    }

    public String getDescricaoProj() {
        return descricaoProj;
    }

    public void setDescricaoProj(String descricaoProj) {
        this.descricaoProj = descricaoProj;
    }

    public String getUsuarioProprietario() {
        return usuarioProprietario;
    }

    public void setUsuarioProprietario(String usuarioProprietario) {
        this.usuarioProprietario = usuarioProprietario;
    }
    
     public void inserirProj(Projeto p) {
        String sql = ("INSERT INTO projetos (id_projeto, nome_projeto, descricao, id_usuario) values (default, ?,?,?)");
                                                                                              
        try{

            Connection conn = conexao.criarConexao();
            PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
            
            stm.setString(1, p.getNomeProj());
            stm.setString(2, p.getDescricaoProj());            
            stm.setInt(3, p.getIdUsuario());

            
            stm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Projeto inserido com sucesso.");

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel inserir o projeto." + e);
        } finally{
            conexao.fecharConexao();
        }
        
      }

     
     public Vector recuperarListaProjetos(){

        Vector listaProj = new Vector<>();

        String sql ="Select * from projetos join usuarios on projetos.id_usuario = usuarios.id";

        try{

            ConexaoBD conexao = new ConexaoBD();
            Connection con = conexao.criarConexao();

            Statement stmt = con.createStatement();

            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){
                
                int idResult = rs.getInt("id_projeto");
                String nomeProjetoResult = rs.getString("nome_projeto");
                String descricaoResult = rs.getString("descricao");
                String usuario_proprietarioResult = rs.getString("nome_usuario");
                
                Vector temp = new Vector();

                temp.add(idResult);
                temp.add(nomeProjetoResult);
                temp.add(descricaoResult);
                temp.add(usuario_proprietarioResult);
               

                listaProj.addElement(temp);

            }


            conexao.fecharConexao();
            return listaProj;

        }catch (SQLException e){
            return null;
        } 
        
    }
    
    public void editarProj(Projeto p) {
        String sql = "UPDATE projetos SET nome_projeto=?, descricao=? WHERE id_projeto=?";
        
        try{

            Connection conn = conexao.criarConexao();
            PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
            
            stm.setString(1, p.getNomeProj());
            stm.setString(2, p.getDescricaoProj());
           
            stm.setInt(3, p.getId());

            
            stm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Projeto atualizado com sucesso.");
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel editar o projeto.");
        } finally{
            conexao.fecharConexao();
        }

      }
    
        public void excluirProj(int idTroca) {
         
        String sql = "delete from projetos where id_projeto="+idTroca;
                                                                                              
        try{    
            
        ConexaoBD conexao = new ConexaoBD();
        
        Connection conn = conexao.criarConexao();
        
        Statement stm = conn.createStatement();
        
        stm.executeUpdate(sql);
        
        conexao.fecharConexao();
        
        JOptionPane.showMessageDialog(null, "Projeto excluido com sucesso.");
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir o projeto.");
        }
        
      }
    
    
    
                
}
