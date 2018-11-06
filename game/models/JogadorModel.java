package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

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

  private Connection conn;
  private Statement comando;

  public void find() {
    conectar();
    ResultSet rs;
    try {
       rs = comando.executeQuery("SELECT marca, robo, id FROM jogadores WHERE id = " + this.id);
       rs.next();

       this.marca = rs.getString("marca");
       this.robo = rs.getBoolean("robo");
       this.id = rs.getInt("id");
       System.out.println(id);
    } catch (SQLException e) {
       System.out.println("Erro ao buscar jogador " + e.getMessage());
    } finally {
       fechar();
    }
  }
  
  public void save() {
    conectar();
    
    try {
       String query = "update jogadores set marca = ?, robo = ? where id = ?";
       System.out.println(query + " " + this.marca + " " + this.robo + " " + this.id);
       PreparedStatement preparedStmt = conn.prepareStatement(query);
       preparedStmt.setString(1, this.marca);
       preparedStmt.setBoolean(2, this.robo);
       preparedStmt.setInt(3, this.id);
       preparedStmt.executeUpdate();
    } catch (Exception e) {
       System.out.println("Erro Atualizar jogador " + e.getMessage());
    } finally {
       fechar();
    }
  }

  private void conectar() {
    try {
       conn = ConexaoMySQL.conectar();
       comando = conn.createStatement();
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
       conn.close();
       System.out.println("Conexão Fechada");
    } catch (SQLException e) {
       System.out.println("Erro ao fechar conexão" + e.getMessage());
    }
  }

  public void setMarca(String marca) {
    this.marca = marca;
    
  }

  public void setRobo(boolean robo) {
    this.robo = robo;
    
  }
}
