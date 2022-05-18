package com.pfe.GestionReclamation.service.Reclamation;


import java.util.List;

public interface IReclamationService {
    List<Result> getRecalamtionByMonth();
    List<Result> getRecalamtionBySpecialite();

    List<Result> getRecalamtionBySexe();
}
