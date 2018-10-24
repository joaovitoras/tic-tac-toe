package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;
import src.ConexaoMySQL; 

public class GameModel {
  private Connection con;   
  private Statement comando;
  
  public ConfiguracaoModel getConfig() {       
    conectar();
    ResultSet rs;
    try {   
       rs = comando.executeQuery(
           "SELECt jogador1_id, jogador2_id FROM configuracoes "
           + "ORDER BY id DESC "
           + "LIMIT 1"
       );
       rs.next();
       
       return new ConfiguracaoModel(rs.getInt("jogador1_id"), rs.getInt("jogador2_id"));
    } catch (SQLException e) {   
       System.out.println("Erro ao buscar config " + e.getMessage());   
    } finally {   
       fechar();   
    }
    
   return null;   
 }
  
  private void conectar() {   
    try {   
       con = ConexaoMySQL.conectar();   
       comando = con.createStatement();   
       System.out.println("Conectado!");   
    } catch (ClassNotFoundException e) {   
       System.out.println("Erro ao carregar o driver " + e.getMessage());   
    } catch (SQLException e) {   
       System.out.println("Erro ao conectar " + e.getMessage());   
    }   
 }   

  private void fechar() {   
    try {   
       comando.close();   
       con.close();   
       System.out.println("Conexão Fechada");   
    } catch (SQLException e) {   
       System.out.println("Erro ao fechar conexão" + e.getMessage());   
    }   
  }
}
