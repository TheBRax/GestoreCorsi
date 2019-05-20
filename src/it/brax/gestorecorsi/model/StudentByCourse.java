package it.brax.gestorecorsi.model;

public class StudentByCourse {
	private String codins;
	private String courseName;
	private String matricola;
	private String cognome;
	private String nome;
	private String CDS;
	public StudentByCourse(String codins, String courseName, String matricola, String cognome, String nome,
			String cDS) {
		super();
		this.codins = codins;
		this.courseName = courseName;
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		CDS = cDS;
	}
	@Override
	public String toString() {
		return String.format("%7s%52s%13s%30s%30s%10s",
				codins, courseName, matricola, cognome, nome, CDS);
	}

}
