/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoBD {
    
        private String hostname = "remotemysql.com";
        private int port = 3306;
        private String dbname = "M8loRP2Pjm";
        private String username = "M8loRP2Pjm";
        private String password = "0sk61Xww1i";
        
        private Connection conexao;
        
        public Connection criarConexao(){
            Connection conexao=null;
            
            String conexaoString = "jdbc:mysql://"+hostname+":"+port+"/"+dbname+"?user="+username+"&password="+password;
            
            try{
            
               conexao = DriverManager.getConnection(conexaoString);
            } catch (Exception e) {
                return null;
            }
            return conexao;
        }
        
        public void fecharConexao(){
            if (conexao!=null){
                try {
                    conexao.close();
                    conexao=null;
                } catch (Exception e) {
                   
                }
            }
        };
}
