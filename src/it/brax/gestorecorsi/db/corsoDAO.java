package it.brax.gestorecorsi.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.brax.gestorecorsi.model.Course;
import it.brax.gestorecorsi.model.Student;
import it.brax.gestorecorsi.model.StudentByPeriod;

public class corsoDAO {

	public static List<Course> getCourses() throws SQLException, FileNotFoundException, IOException{	
		List<Course> courses = new ArrayList<Course>();
		String query = 	"select c.codins, c. crediti, c.nome, c.pd from iscritticorsi.corso as c";
		try
		(
			Connection conn = IscritticorsiConnect.getConnection();
			PreparedStatement st = createSimplePreparedStatement(conn, query);
			ResultSet rs = st.executeQuery();
		)
		{
			while(rs.next()) {
				courses.add(new Course(rs.getString("codins"),
						rs.getInt("crediti"),
						rs.getString("nome"),
						rs.getString("pd")
						));
			}
			return courses;
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}	
	
	
	public static List<String> getCoursePeriod() throws SQLException, FileNotFoundException, IOException {
		List<String> coursePeriod = new LinkedList<String>();
		String query = "select distinct c.pd from iscritticorsi.corso as c order by pd asc;";
		try
		(
			Connection conn = IscritticorsiConnect.getConnection();
			PreparedStatement st = createSimplePreparedStatement(conn, query);
			ResultSet rs = st.executeQuery();
		)
		{
			while(rs.next()) {
				coursePeriod.add(rs.getString("pd"));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return coursePeriod;
	}
	
	public static List<Course> getCourseByPeriod(String period) throws SQLException, FileNotFoundException, IOException {
		List<Course> courseByPeriod = new LinkedList<Course>();
		String query = "select c.codins, c.crediti, c.nome, c.pd " + 
				"from iscritticorsi.corso as c " + 
				"where c.pd = ?;";
		try
		(
			Connection conn = IscritticorsiConnect.getConnection();
			PreparedStatement st = createOneParameterPreparedStatement(conn, query, period);
			ResultSet rs = st.executeQuery();
		)
		{
			while(rs.next()) {
				courseByPeriod.add(new Course(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getString("pd")));
			}				
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return courseByPeriod;
	}
	
	public static List<StudentByPeriod> getStudentByPeriod(String period) throws SQLException, FileNotFoundException, IOException {
		List<StudentByPeriod> studentByPeriod = new LinkedList<StudentByPeriod>();
		String query = "select c.codins, c.pd, c.nome, count(*) as tot " + 
				"from iscritticorsi.corso as c, iscritticorsi.iscrizione as i " + 
				"where c.codins = i.codins and c.pd =  ? " + 
				"group by c.codins;";
		try
		(
			Connection conn = IscritticorsiConnect.getConnection()	;
			PreparedStatement st = createOneParameterPreparedStatement(conn, query, period);
			ResultSet rs = st.executeQuery();
		)
		{
			while(rs.next()) {
				studentByPeriod.add(new StudentByPeriod(rs.getString("codins"), rs.getString("pd"), rs.getString("nome"), rs.getInt("tot")));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return studentByPeriod;
	}
	
	private static PreparedStatement createSimplePreparedStatement(Connection conn, String query) throws SQLException {
		return conn.prepareStatement(query);		
	}
	
	private static PreparedStatement createOneParameterPreparedStatement(Connection conn, String query, String period) throws SQLException {
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, period);
		return st;
	}
}
