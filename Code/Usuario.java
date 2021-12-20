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


public class Usuario {
    private int id;
    private String nomeCompleto;
    private String email;
    private int telefone;
    private String nomeUsuario;
    private String senha;
    
    ConexaoBD conexao = new ConexaoBD();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void inserirUsario() {
        String sql = "INSERT INTO usuarios ( id , nome_completo, nome_usuario, senha, email, telefone) VALUES (default,'"+nomeCompleto+"', '"+nomeUsuario+"', '"+senha+"', '"+email+"', "+telefone+");";
                                                                                              
        try{    
                   
        Connection conn = conexao.criarConexao();
        
        Statement stm = conn.createStatement();
        
        stm.executeUpdate(sql);
        
        
        JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso.");
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar o usuario");
        }finally{
            conexao.fecharConexao();
        }
        
    }
    
    public Vector recuperarListaUsuarios(){

        Vector listaUsuarios = new Vector<>();
        
        String sql ="Select * from usuarios";
                
        try{              
            
            Connection con = conexao.criarConexao();        
            
            Statement stmt = con.createStatement();
            
            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                int idResult = rs.getInt("id");
                String nomeCompletoResult = rs.getString("nome_completo");
                String emailResult = rs.getString("email");
                int telefoneResult = rs.getInt("telefone");
                String nomeUsuarioResult = rs.getString("nome_usuario");
                String senhaResult = rs.getString("senha");
                
                Vector temp = new Vector();
                
                temp.add(idResult);
                temp.add(nomeCompletoResult);
                temp.add(emailResult);
                temp.add(telefoneResult);
                temp.add(nomeUsuarioResult);
                temp.add(senhaResult);
                
                listaUsuarios.addElement(temp);
                
            }
            
            
            conexao.fecharConexao();
            return listaUsuarios;
            
        }catch (SQLException e){
            return null;
        } 
      
    }

    public Usuario Perfil(String Nome, String Senha){
        
        String sql ="select * from usuarios "+
                        "where nome_usuario= '" + Nome +"' and senha='"+Senha+"'";
                        
        try{              
            Usuario usuario = new Usuario();
            
            Connection con = conexao.criarConexao();        
            
            Statement stmt = con.createStatement();
            
            ResultSet rs= stmt.executeQuery(sql);
            
            while(rs.next()){
                
                
                usuario.setId(rs.getInt("id"));
                usuario.setNomeCompleto(rs.getString("nome_completo"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getInt("telefone"));     
                usuario.setSenha(Senha);
                usuario.setNomeUsuario(Nome);
            }
        
            
            conexao.fecharConexao();
            return usuario;
            
        }catch (SQLException e){
            return null;
        } 
        
    }
    
    public void editarUsuario(Usuario u){

        String sql = "UPDATE usuarios SET nome_completo=?, email=?, telefone=?, nome_usuario=?, senha=? WHERE id=?";
        
        try{

            Connection conn = conexao.criarConexao();
            PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
            
            stm.setString(1, u.getNomeCompleto());
            stm.setString(2, u.getEmail());
            stm.setInt(3, u.getTelefone());
            stm.setString(4, u.getNomeUsuario());
            stm.setString(5, u.getSenha());
            stm.setInt(6, u.getId());
            
            stm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso.");

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel editar o usuário.");
        } finally{
            conexao.fecharConexao();
        }
   
    }
    
    public void deletarUsuario(Usuario u){
        
            
        String sql ="DELETE FROM usuarios where id=?";
       
        try{  
            
            Connection con = conexao.criarConexao();  
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1, u.getId());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally{
            conexao.fecharConexao();
        }
    
    }
}


