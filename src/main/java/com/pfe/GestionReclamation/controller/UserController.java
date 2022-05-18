package com.pfe.GestionReclamation.controller;


import com.pfe.GestionReclamation.model.User;

import com.pfe.GestionReclamation.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
 @RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;

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
}
