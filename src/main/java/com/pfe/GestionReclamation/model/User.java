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
    private Date dateRecrutement;
    private NiveauScolaire niveauScolaire;
    private Specialite specialite;

    @Column(unique=true)
    private String email;
    private String password;
    private String role;

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
        this.dateRecrutement = new Date();
        reclamations = new ArrayList<>();
        reclamations_a_traiter = new ArrayList<>();
    }

    public User(String nom, String prenom, String email, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateRecrutement = new Date();
        this.email = email;
        this.password = password;
        this.role = role;
        reclamations = new ArrayList<>();
        reclamations_a_traiter = new ArrayList<>();
    }

}
