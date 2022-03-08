package com.uottawa.project.authentication;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainUsersController {

	@Autowired
	private MainUsersRepository mainUsersRepository;

	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new MainUsers());
	    return "signupForm";
	}
	
	@PostMapping("/process_register")
	public String processRegister(MainUsers user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    mainUsersRepository.save(user);
	    return "registerSuccess";
	}
	
	@GetMapping("/members")
	public String listMembers(Model model) {
	    List<MainUsers> listUsers = mainUsersRepository.findAll();
	    model.addAttribute("listUsers", listUsers);
	    return "users";
	}
}
