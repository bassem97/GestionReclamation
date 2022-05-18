package com.pfe.GestionReclamation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reclamation {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long idReclamation;
    private String motif;
    private Status status;
    private boolean delegue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUser")
    @JsonIgnoreProperties({"reclamations"})
	private User ownerUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_traiteur")
	@JsonIgnoreProperties({"reclamations_a_traiter"})
	private User traiteurUser;
}