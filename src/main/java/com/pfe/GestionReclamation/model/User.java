package com.pfe.GestionReclamation.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String nom;
    private String prenom;
    private Date dateInscription;
    private NiveauScolaire niveauScolaire;
    private Specialite specialite;
    private Sexe sexe;
    @Column(unique=true)
    private String email;
    private String password;
    private Role role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    @JsonIgnoreProperties({"ownerUser"})
    @ToString.Exclude
    private List<Reclamation> reclamations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_traiteur")
    @JsonIgnoreProperties({"traiteurUser"})
    @ToString.Exclude
    private List<Reclamation> reclamations_a_traiter;


    public User() {
        this.dateInscription = new Date();
        reclamations = new ArrayList<>();
        reclamations_a_traiter = new ArrayList<>();
    }

    public User(String nom, String prenom, String email, String password, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateInscription = new Date();
        this.email = email;
        this.password = password;
        this.role = role;
        this.dateInscription = new Date();
        reclamations = new ArrayList<>();
        reclamations_a_traiter = new ArrayList<>();
    }

}
