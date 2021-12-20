package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;



public class Requisitos {

    private int id;
    private int idProjeto;
    private String nome;
    private String modulo;
    private String funcionalidades;
    private String dataCriacao;
    private String autor;
    private String dataAlteracao;
    private String autorAlteracao;
    private int versao;
    private int prioridade;
    private int complexidade;
    private int esforcoHoras;
    private String estado;
    private String fase;
    private String descricao;   
    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(String funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getAutorAlteracao() {
        return autorAlteracao;
    }

    public void setAutorAlteracao(String autorAlteracao) {
        this.autorAlteracao = autorAlteracao;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(int complexidade) {
        this.complexidade = complexidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getId_projeto() {
        return idProjeto;
    }

    public void setId_projeto(int id_Projeto) {
        this.idProjeto = id_Projeto;
    }

    public int getEsforcoHoras() {
        return esforcoHoras;
    }

    public void setEsforcoHoras(int esforcoHoras) {
        this.esforcoHoras = esforcoHoras;
    }
    
    public void inserirRequisitos(Requisitos r) {
        
        
        String sql = "INSERT INTO requisitos ( id_requisito, nome, modulo, funcionalidades, dataCriacao, autor, versao, prioridade, complexidade, eforcoHoras, estado, fase, descricao, id_projeto) "
                +"VALUES (default,'"+nome+"','"+modulo+"','"+funcionalidades+"','"+dataCriacao+"','"+autor+"',"+versao+","+prioridade+","+complexidade+","+esforcoHoras+",'"+estado+"','"+fase+"','"+descricao+"',"+idProjeto+")";
        try{    
            
        ConexaoBD conexao = new ConexaoBD();
        
        Connection conn = conexao.criarConexao();
        
        Statement stm = conn.createStatement();
 
        stm.executeUpdate(sql);
        
        conexao.fecharConexao();
        
        JOptionPane.showMessageDialog(null, "Requisito criado com sucesso.");
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel criar o requisito.");
        }
        
    }
    
    public void editarRequisito(Requisitos r) {
        String sql = "UPDATE requisitos SET  nome=?, modulo=?, funcionalidades=?, dataAlteracao=?, autorAlteracao=?, versao=?, prioridade=?, complexidade=?, eforcoHoras=?, estado=?, fase=?, descricao=? WHERE id_requisito=?";
        ConexaoBD conexao = new ConexaoBD();
        try{
            
            
            Connection conn = conexao.criarConexao();
            PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
            
            
            stm.setString(1, r.getNome());
            stm.setString(2, r.getModulo());
            stm.setString(3, r.getFuncionalidades());
            stm.setString(4,  r.getDataAlteracao());
            stm.setString(5, r.getAutorAlteracao());
            stm.setInt(6, r.getVersao());
            stm.setInt(7, r.getPrioridade());
            stm.setInt(8, r.getComplexidade());
            stm.setInt(9, r.getEsforcoHoras());
            stm.setString(10, r.getEstado());
            stm.setString(11, r.getFase());
            stm.setString(12, r.getDescricao());
            stm.setInt(13, r.getId());

            stm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Requisito atualizado com sucesso.");
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel editar o requisito.");
        } finally{
            conexao.fecharConexao();
        }

      }

    public void excluirRequisito(int idTroca) {
         
        String sql = "delete from requisitos where id_requisito="+idTroca;
                                                                                              
        try{    
            
        ConexaoBD conexao = new ConexaoBD();
        
        Connection conn = conexao.criarConexao();
        
        Statement stm = conn.createStatement();
        
        stm.executeUpdate(sql);
        
        conexao.fecharConexao();
        
        JOptionPane.showMessageDialog(null, "Requisito excluido com sucesso.");
        
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir o requisito.");
        }
        
      }
    
    public Vector recuperarListaRequisitos(int idProj){

        Vector listaReq = new Vector<>();

        String sql ="select requisitos.*, projetos.nome_projeto\n" +
                    "from requisitos join  projetos\n" +
                    "on requisitos.id_projeto = projetos.id_projeto\n" +
                    "where requisitos.id_projeto = "+idProj+";";

        try{

            ConexaoBD conexao = new ConexaoBD();
            Connection con = conexao.criarConexao();

            Statement stmt = con.createStatement();

            ResultSet rs= stmt.executeQuery(sql);

            while(rs.next()){
                
                int idResult = rs.getInt("id_requisito");
                String nomeRequisitoResult = rs.getString("nome");
                String moduloResult = rs.getString("modulo");
                String funcionalidadesResult = rs.getString("funcionalidades");
                Date dataCriacaoResult = rs.getDate("dataCriacao");
                String autorResult = rs.getString("autor");
                Date dataAlteracaoResult = rs.getDate("dataAlteracao");
                String autorAlteracaoResult = rs.getString("autorAlteracao");
                int versaoResult = rs.getInt("versao");
                int prioridadeResult = rs.getInt("prioridade");
                int complexidadeResult = rs.getInt("complexidade");
                int eforcoHorasResult = rs.getInt("eforcoHoras");
                String estadoResult = rs.getString("estado");
                String faseResult = rs.getString("fase");
                String descricaoResult = rs.getString("descricao");
                String id_projetoResult = rs.getString("nome_projeto");
                
                Vector temp = new Vector();

                temp.add(idResult);
                temp.add(nomeRequisitoResult);
                temp.add(moduloResult);
                temp.add(funcionalidadesResult);
                temp.add(dataCriacaoResult);
                temp.add(autorResult);
                temp.add(dataAlteracaoResult);
                temp.add(autorAlteracaoResult);
                temp.add(versaoResult);
                temp.add(prioridadeResult);
                temp.add(complexidadeResult);
                temp.add(eforcoHorasResult);
                temp.add(estadoResult);
                temp.add(faseResult);
                temp.add(descricaoResult);
                temp.add(id_projetoResult);
                

                listaReq.addElement(temp);

            }


            conexao.fecharConexao();
            return listaReq;

        }catch (SQLException e){
            return null;
        } 
        
    }
    
}
