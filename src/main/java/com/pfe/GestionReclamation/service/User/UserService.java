package com.pfe.GestionReclamation.service.User;

import com.pfe.GestionReclamation.model.User;
import com.pfe.GestionReclamation.repository.UserRepository;
import com.pfe.GestionReclamation.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService, ICrudService<User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public User add(User user) {
        user.setRole(userRepository.findAll().size() == 0?"ADMIN":"INTERVENANT");
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user, Long aLong) {
        if (userRepository.findById(aLong).isPresent()) {
            User user1 = userRepository.findById(aLong).get();
            user1.setEmail(user.getEmail());
            user1.setNom(user.getNom());
            user1.setPrenom(user.getPrenom());
            user1.setRole(user.getRole());
            user1.setPassword(bcryptEncoder.encode(user.getPassword()));

            return userRepository.save(user1);
        }
        return null;
    }

    @Override
    public void delete(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).get();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}


