package it.brax.gestorecorsi.model;

public class StudentByPeriod {
	private String codins;
	private String pd;
	private String nome;
	private int tot;
	public String getCodins() {
		return codins;
	}
	public void setCodins(String codins) {
		this.codins = codins;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public StudentByPeriod(String codins, String pd, String nome, int tot) {
		super();
		this.codins = codins;
		this.pd = pd;
		this.nome = nome;
		this.tot = tot;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentByPeriod other = (StudentByPeriod) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("%7s%5s%52s%5s", codins, pd, nome, tot);
	}
	
	
}
