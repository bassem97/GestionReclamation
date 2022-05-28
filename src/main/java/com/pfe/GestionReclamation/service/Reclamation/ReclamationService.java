package com.pfe.GestionReclamation.service.Reclamation;


import com.pfe.GestionReclamation.model.Reclamation;
import com.pfe.GestionReclamation.model.Sexe;
import com.pfe.GestionReclamation.model.Specialite;
import com.pfe.GestionReclamation.model.Status;
import com.pfe.GestionReclamation.repository.ReclamationRepository;
import com.pfe.GestionReclamation.service.Email.EmailService;
import com.pfe.GestionReclamation.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
            if(reclamation1.getStatus().name().equals("VERIFIE")){
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

    @Override
    public List<Result> getRecalamtionBySpecialite() {
        List<Result> result = new ArrayList<>();
        Arrays.stream(Specialite.values()).forEach(specialite -> {
            result.add(new Result(
                    specialite.name(),
                    null,
                    null,
                    (int) this.findAll().stream()
                            .filter(reclamation -> reclamation.getOwnerUser().getSpecialite() == specialite).count()

                    ));
        });
        return result;
    }

    @Override
    public List<Result> getRecalamtionBySexe() {
        List<Result> result = new ArrayList<>();
        Arrays.stream(Sexe.values()).forEach(sexe -> {
            result.add(new Result(
                    null,
                    sexe.name(),
                    null,
                    (int) this.findAll().stream()
                            .filter(reclamation -> reclamation.getOwnerUser().getSexe() == sexe).count()
                   ));
        });
        return result;
    }

    @Override
    public List<Result> getRecalamtionByMonth() {
        Map<String, Integer> dates = new HashMap<>();
        Map<String, Integer> result = new HashMap<>();
        List<Result> results = new ArrayList<>();

        this.findAll().stream().sorted(Comparator.comparing(Reclamation::getDateRecalamtion))
                .collect(Collectors.toList())
                .forEach(reclamation -> {
                    dates.put(
                            new SimpleDateFormat("yyyy-MM-dd").format(reclamation.getDateRecalamtion()),
                            (int) this.findAll().stream()
                                    .filter(reclamation1 -> reclamation.getDateRecalamtion() == reclamation1.getDateRecalamtion()).count()
                    );
                });

        for (Map.Entry<String, Integer> entry  : dates.entrySet()) {
            String key = entry.getKey().split("-")[0] + "/" + entry.getKey().split("-")[1];
            Integer value = entry.getValue();
            Integer oldValue = result.get(key) != null ? result.get(key) : 0;
            Result rss = new Result(null,null,key, oldValue + value);
            results.add(rss);
        }

        return results;
    }
}
