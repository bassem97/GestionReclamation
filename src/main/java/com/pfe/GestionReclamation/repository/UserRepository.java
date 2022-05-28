package com.pfe.GestionReclamation.repository;





import com.pfe.GestionReclamation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
	User findByEmail( String email);
	@Query("SELECT u from User u where u.role=1")
	List<User> listeIntervenants() ;
}
