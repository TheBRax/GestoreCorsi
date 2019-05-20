package it.brax.gestorecorsi.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.brax.gestorecorsi.model.Student;
import it.brax.gestorecorsi.model.StudentByCourse;

public class IscritticorsiDAO {

	public static List<StudentByCourse> getStudentByCourse(String codins) throws SQLException, IOException {
		String query = "SELECT c.codins, c.nome, s.matricola, s.cognome, s.nome, s.CDS " + 
				"FROM iscritticorsi.studente as s, iscritticorsi.corso AS c, iscritticorsi.iscrizione as i " + 
				"WHERE i.codins = ? AND s.matricola = i.matricola AND c.codins = i.codins";
		List<StudentByCourse> studentByCourse = new LinkedList<StudentByCourse>();
		try (
			Connection conn = IscritticorsiConnect.getConnection();
			PreparedStatement st = createOneParamenterPreparedStatement(conn, query, codins);
			ResultSet rs = st.executeQuery();
		) {
			while(rs.next()) {
				studentByCourse.add(new StudentByCourse(rs.getString("codins"),
						rs.getString("nome"), rs.getString("matricola"), rs.getString("cognome"), 
						rs.getString("nome"), rs.getString("CDS")));
			}
			return studentByCourse;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (SQLException e) {
			throw new SQLException();
		} catch (IOException e) {
			throw new IOException();
		}
	}
	
	private static PreparedStatement createOneParamenterPreparedStatement(Connection conn, String query, String arg) throws SQLException {
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, arg);
		return st;	
	}
}

	
