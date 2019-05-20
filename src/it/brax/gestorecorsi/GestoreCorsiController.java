package it.brax.gestorecorsi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.brax.gestorecorsi.model.Course;
import it.brax.gestorecorsi.model.Model;
import it.brax.gestorecorsi.model.Student;
import it.brax.gestorecorsi.model.StudentByCourse;
import it.brax.gestorecorsi.model.StudentByPeriod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class GestoreCorsiController {
	Model model;
	ObservableList<String> CoursePeriod = null;
	ObservableList<String> Courses = null;
	Boolean connectionStatus = false;
	
    public GestoreCorsiController() {
		super();
		this.model = new Model();
	}

	@FXML
    private ComboBox<String> cbPeriod;

    @FXML
    private Button btCourses;

    @FXML
    private Button btStudentsByCourse;

    @FXML
    private ComboBox<String> cbCourse;

    @FXML
    private Button btStudents;

    @FXML
    private Button btStudentsByCDS;

    @FXML
    private TextArea taResult;
    
    @FXML
    private Label lbStatus;
    
    @FXML
    private void initialize() {
    	String promptCbCourse = null;
    	String promptCbPeriod = null;
    	String status = null;
	    try {
	    	CoursePeriod = FXCollections.observableArrayList(model.getCoursePeriod());
	    	Courses = FXCollections.observableArrayList(model.getCourseIdentifications());
	    	promptCbCourse = "Choose a course...";
	    	promptCbPeriod = "Choose a period...";
	    	status = "Status ok: connection to Database successful.";
	    	connectionStatus = true;
	    } catch(SQLException | IOException e) {
	    	status = "Status KO: connection to Database failed.";
	    } finally {
	    	cbPeriod.setItems(CoursePeriod);
	    	cbCourse.setItems(Courses);
	    	cbPeriod.setValue(promptCbPeriod);
	    	cbCourse.setValue(promptCbCourse);
	    	lbStatus.setText(status);
	    }
    }
    
    @FXML
    void btCourses_onActionHandler(ActionEvent event) {
    	List<Course> courseByPeriod = new LinkedList<Course>();
    	String cbPeriodValue = null;
    	try {
    		cbPeriodValue = cbPeriod.getValue();
    		if (model.cbPeriodValueValidation(cbPeriodValue)) {	// non ha else perché nel caso dell'else viene lanciata IllegalArgumentException
    			courseByPeriod = model.getCourseByPeriod(cbPeriodValue);
    			taResult.clear();
    			taResult.appendText("----------------------------------------------------------------\n");
    			taResult.appendText(String.format("%7s%5s%52s\n", "CODINS", "PD", "COURSE NAME"));
    			taResult.appendText("----------------------------------------------------------------\n");
    			for (Course c : courseByPeriod) {
    				taResult.appendText(c.toString() + "\n");
    			}
    		}
    	} catch (IllegalArgumentException e) {
    		taResult.clear();
    		taResult.setText("Choose an available didactic period in the list.");
    	} catch (SQLException | IOException e) {
    		taResult.clear();
    		taResult.setText("Query execution failed.");
    	}   	
    }

    @FXML
    void btStudentsByCDS_onActionHandler(ActionEvent event) {

    }

    @FXML
    void btStudentsByCourse_onActionHandler(ActionEvent event) {
    	List<StudentByPeriod> studentByPeriod = new LinkedList<StudentByPeriod>();
    	String cbPeriodValue = null;
    	try {
    		cbPeriodValue = cbPeriod.getValue();
    		if (model.cbPeriodValueValidation(cbPeriodValue)) {	// non ha else perché nel caso dell'else viene lanciata IllegalArgumentException
    			studentByPeriod = model.getStudentByPeriod(cbPeriodValue);
    			taResult.clear();
    			taResult.appendText("---------------------------------------------------------------------\n");
    			taResult.appendText(String.format("%7s%5s%52s%5s\n", "CODINS", "PD", "COURSE NAME", "TOT"));
    			taResult.appendText("---------------------------------------------------------------------\n");
    			for (StudentByPeriod s : studentByPeriod) {
    				taResult.appendText(s.toString() + "\n");
    			}
    		}
    	} catch (IllegalArgumentException e) {
    		taResult.clear();
    		taResult.setText("Choose an available didactic period in the list.");
    	} catch (SQLException |IOException e) {
    		taResult.clear();
    		taResult.setText("Query execution failed.");
    	}
    }

    @FXML
    void btStudents_onActionHandler(ActionEvent event) {
    	List<StudentByCourse> Students = new LinkedList<StudentByCourse>();
    	String cbCourseValue = null;
    	cbCourseValue = cbCourse.getValue();
    	try {
			if (model.cbCourseValueValidation(cbCourseValue)) {
				Students = model.getStudentByCourse(cbCourseValue);
				taResult.clear();
				//taResult.appendText(cbCourseValue);
				taResult.appendText("----------------------------------------------------------------------------------------------------------------------------------------------\n");
				taResult.appendText(String.format("%7s%52s%13s%30s%30s%10s\n", "CODINS", "COURSE NAME", "STD.SERIAL", "STD.SURNAME", "STD.NAME", "CDS"));
				taResult.appendText("----------------------------------------------------------------------------------------------------------------------------------------------\n");
				for (StudentByCourse s : Students) {
					taResult.appendText(s.toString() + "\n");
				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

}
