package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
	
	private final UserService userService;
	
	@GetMapping("/")
	public String home(HttpSession session) {
		log.info("home controller!!! - home");
		
		String username = (String) session.getAttribute("username");
		
		if(username != null) {
			return "user";
		}
		
		return "home";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
		log.info("home controller!!! - login");
		
		if(userService.isLoginMatching(username, password)) { // 로그인 성공
			
			session.setAttribute("username", username);
			model.addAttribute("username", username);
			
			if(username.equals("admin")) {
				
				return "admin";
				
			}
			
			return "user";
		}
		
		model.addAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다."); // 로그인 실패 메시지 추가
		
		return "home";
	}
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		log.info("home controller!!! - register");
		
		model.addAttribute("user", new User());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user, Model model) {
		
		if(userService.isUsernameTaken(user.getUsername())) {
			
			model.addAttribute("usernameError", "이미 사용중인 아이디입니다.");
			
			return "register";
			
		}
		
		if(!userService.isPasswordMatching(user)) {
			
			model.addAttribute("passwordMatchingError", "비밀번호가 일치하지 않습니다.");
			
			return "register";
			
		}
		
		userService.saveUser(user);
		
		 return "redirect:/";
		
	}

}



