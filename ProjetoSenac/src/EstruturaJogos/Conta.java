package EstruturaJogos;
import java.sql.*;

public class Conta {
	
	public String bancoDeDados(String query, String coluna) {
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/casino_pi", "root", "password");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				return rs.getString(coluna);
			}else {
				return "Conta Não Encontrada";
			}
		}catch(Exception e) {
			System.out.println(e);
			return "Conta não encontrada";
		}
	}
	
	public String queryConsultaNome(String nome) {
		return "SELECT * FROM conta WHERE Nome = " + "'" + nome +  "'";
	}
	
	public void queryInserirConta(String nome) {
		try{
			String query = String.format("INSERT INTO conta(Nome, Saldo) VALUES('%s',500);", nome);
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/casino_pi", "root", "password");
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(query);
		}catch(Exception  e) {
			System.out.println(e);
		}
	}
	
	public void queryAtualizarSaldo(int saldo, String nome) {
		try{
			
			String query = String.format("UPDATE conta SET Saldo = %s WHERE Nome = '%s';", String.valueOf(saldo), nome);
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/casino_pi", "root", "password");
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(query);
		}catch(Exception  e) {
			System.out.println(e);
		}
	}
}
