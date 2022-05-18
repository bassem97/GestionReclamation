package com.pfe.GestionReclamation.service.Reclamation;


import com.pfe.GestionReclamation.model.Reclamation;
import com.pfe.GestionReclamation.repository.ReclamationRepository;
import com.pfe.GestionReclamation.service.Email.EmailService;
import com.pfe.GestionReclamation.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamationService implements IReclamationService, ICrudService<Reclamation,Long > {

    @Autowired
    private ReclamationRepository reclamationRepository;
    @Autowired
    private EmailService emailService;


    @Override
    public Reclamation add(Reclamation reclamation) {
        reclamation.setDelegue(false);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation update(Reclamation reclamation, Long aLong) {
        if (reclamationRepository.findById(aLong).isPresent()) {
            Reclamation  reclamation1   = reclamationRepository.findById(aLong).get();
            reclamation1.setMotif(reclamation.getMotif());
            reclamation1.setStatus(reclamation.getStatus());
            reclamation1.setTraiteurUser(reclamation.getTraiteurUser());
            reclamation1.setDelegue(reclamation.isDelegue());
            if(reclamation1.getStatus().name() == "VERIFIE"){
                emailService.sendSimpleMessage(reclamation1.getOwnerUser().getEmail(),"A propos votre reclamation","votre reclamation a été verifié");
            }
            return reclamationRepository.save(reclamation1);
        }
        return null;
    }

    @Override
    public void delete(Long aLong) {
        reclamationRepository.deleteById(aLong);
    }

    @Override
    public List<Reclamation> findAll() {
        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation findById(Long aLong) {
        return reclamationRepository.findById(aLong).get();
    }
}
