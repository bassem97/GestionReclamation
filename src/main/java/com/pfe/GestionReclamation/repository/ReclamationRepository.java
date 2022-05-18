package com.pfe.GestionReclamation.repository;

import com.pfe.GestionReclamation.model.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository extends  JpaRepository<Reclamation, Long>{

}
