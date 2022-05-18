package com.pfe.GestionReclamation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Reclamation {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long idReclamation;
    private String motif;
    private Status status;
    private boolean delegue;
    private Date dateRecalamtion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUser")
    @JsonIgnoreProperties({"reclamations","reclamations_a_traiter"})
	private User ownerUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_traiteur")
	@JsonIgnoreProperties({"reclamations_a_traiter","reclamations"})
	private User traiteurUser;

	public Reclamation(String motif, User ownerUser, User traiteurUser) {
		this.motif = motif;
		this.ownerUser = ownerUser;
		this.traiteurUser = traiteurUser;

		this.status = Status.valueOf("EN_COURS");
		this.dateRecalamtion = new Date();
		this.delegue = false;
	}
	public Reclamation() {
		this.status = Status.valueOf("EN_COURS");
		this.dateRecalamtion = new Date();
		this.delegue = false;

	}
}