package com.pfe.GestionReclamation.service.Reclamation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter @Setter @AllArgsConstructor
public class Result implements Serializable {
    private String specialite;
    private String sexe;
    private String month;
    private int sommeRec;

}
