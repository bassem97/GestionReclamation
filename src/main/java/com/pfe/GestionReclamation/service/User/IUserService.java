package com.pfe.GestionReclamation.service.User;
import com.pfe.GestionReclamation.model.User;

public interface IUserService {
    User findByEmail(String email);
}
