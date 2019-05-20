package it.brax.gestorecorsi.model;

public class StudentByCDS {
	private String codins;
	private String courseName;
	private String stdTot;
	private String CDS;
	public StudentByCDS(String codins, String courseName, String stdTot, String cDS) {
		super();
		this.codins = codins;
		this.courseName = courseName;
		this.stdTot = stdTot;
		CDS = cDS;
	}
	@Override
	public String toString() {
		return String.format("%7s%52s%10s%9s", codins, courseName, stdTot,
				CDS);
	}
	

}
