package dev.paie.entite;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class BulletinSalaire {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private ZonedDateTime dateCreation;
	
	@ManyToOne
	private RemunerationEmploye remunerationEmploye;
	
	@ManyToOne
	private Periode periode;
	
	private BigDecimal primeExceptionnelle;
	
	public RemunerationEmploye getRemunerationEmploye() {
		return remunerationEmploye;
	}
	
	public void setRemunerationEmploye(RemunerationEmploye remunerationEmploye) {
		this.remunerationEmploye = remunerationEmploye;
	}
	
	public Periode getPeriode() {
		return periode;
	}
	
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
	
	public BigDecimal getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}
	
	public void setPrimeExceptionnelle(BigDecimal primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreation() {
		return dateCreation;
	}

	private void setDateCreation(ZonedDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	@PrePersist
    public void setCreationDate() {
		setDateCreation( ZonedDateTime.now());
    }
	
	public String getDateCreationFormat() {
		return this.getDateCreation().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
	}
}
