package it.brax.gestorecorsi.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.brax.gestorecorsi.db.corsoDAO;

public class Model {
	
	private List<Course> getCourses() throws SQLException, FileNotFoundException, IOException{
		return corsoDAO.getCourses();
	}

	public List<String> getCoursePeriod() throws SQLException, FileNotFoundException, IOException {
		return corsoDAO.getCoursePeriod();
	}

	public List<String> getCourseIdentifications() throws SQLException, FileNotFoundException, IOException {
		List<Course> courses = getCourses();
		List<String> courseIdentification = new LinkedList<String>();
		for (Course c : courses) {
			if (!(courseIdentification.contains(c.toString())))
				courseIdentification.add(c.toString());
		}
		return courseIdentification;
	}
	
	public List<Course> getCourseByPeriod(String period) throws SQLException, FileNotFoundException, IOException{
		return corsoDAO.getCourseByPeriod(period);
	}
	
	public List<StudentByPeriod> getStudentByPeriod(String period) throws SQLException, FileNotFoundException, IOException {
		return corsoDAO.getStudentByPeriod(period);
	}
	
	public boolean cbPeriodValueValidation(String value) throws IllegalArgumentException, SQLException, FileNotFoundException, IOException {
		List<String> coursePeriod = getCoursePeriod();
		if (coursePeriod.contains(value))
			return true;
		else
			throw new IllegalArgumentException();
	}

}