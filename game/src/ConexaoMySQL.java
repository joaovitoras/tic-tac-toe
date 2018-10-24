package src;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class ConexaoMySQL { 
	public static final int MYSQL = 0; 
	private static final String MySQLDriver = "com.mysql.jdbc.Driver"; 
	
	public static Connection conectar() throws ClassNotFoundException, SQLException { 
    Class.forName(MySQLDriver);
		
    // caminho do servidor do BD
    String servidor = "localhost";
    
    // nome do banco de dados
    String database = "jogo_da_velha";

    String url = "jdbc:mysql://" + servidor + "/" + database;
    
    // nome de usuário  
    String nome = "root";

    String senha = "vs123air";
    // senha de acesso
    
		return DriverManager.getConnection(url, nome, senha); 
	} 
}