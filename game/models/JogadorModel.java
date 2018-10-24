package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.AI;
import src.ConexaoMySQL;
import src.Verificador;
import views.TabuleiroView;

public class JogadorModel {
  private int id;
  private String marca;
  private boolean robo;
  private AI ai;
    
  public JogadorModel(String marca, boolean robo) {
    this.marca = marca;
    this.robo = robo;
  }
    
  public JogadorModel(int id) {
    this.id = id;
  }
  
  public JogadorModel get() {
    find();
    return this;
  }
 
  public String getMarca() {
    return this.marca;
  }

  public boolean robo() {
    return this.robo;
  }
  
  public boolean humano() {
    return !this.robo;
  }

  public void jogar() {
    ai.jogar();
  }

  public void iniciarAI(TabuleiroView view, Verificador verificador) {
     ai = new AI(view, verificador);
  }
  
  private Connection con;   
  private Statement comando;
  
  public ConfiguracaoModel find() {       
    conectar();
    ResultSet rs;
    try {   
       rs = comando.executeQuery("SELECT marca, robo FROM jogadores WHERE id = " + this.id);
       rs.next();

       this.marca = rs.getString("marca");
       this.robo = rs.getBoolean("robo");
    } catch (SQLException e) {   
       System.out.println("Erro ao buscar jogador " + e.getMessage());   
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
