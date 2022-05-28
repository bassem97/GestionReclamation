package com.pfe.GestionReclamation.controller;


import com.pfe.GestionReclamation.model.Role;
import com.pfe.GestionReclamation.model.User;

import com.pfe.GestionReclamation.repository.UserRepository;
import com.pfe.GestionReclamation.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
 @RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("intervenant")
	public List<User> intervenants()
	{
		return userRepository.listeIntervenants();
	}

	@GetMapping("list")
	public List<User> findAll() {
		return userService.findAll();
	}

 	@PostMapping("add")
	public User add(@RequestBody User user) {
		return userService.add(user);
	}

 	@PutMapping("update/{id}")
	public User update(@RequestBody User user, @PathVariable("id") Long id) {
		return userService.update(user, id);
	}

		@DeleteMapping("delete/{id}")
	public void delete(@PathVariable("id") long id) {
		userService.delete(id);
	}


 	@GetMapping("findById/{id}")
	public User findById(@PathVariable("id") Long id) {
		return userService.findById(id);
	}

	@GetMapping("/listIntervenants")
	public List<User> getListeIntervenants()
	{
		List intervenants = new ArrayList();
		for (User u : userService.findAll()) {
			System.out.println(u.getRole());
			if(u.getRole() == Role.INTERVENANT)
				intervenants.add(u);
		}
		return  intervenants;
	}

}
