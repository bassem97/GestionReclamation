package com.pfe.GestionReclamation.repository;





import com.pfe.GestionReclamation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
	User findByEmail( String email);
}
