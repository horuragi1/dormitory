package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	public boolean isUsernameTaken(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		
		return user.isPresent();
	}
	
	@Transactional
	public Long saveUser(User user) {
		
		return userRepository.save(user);
		
	}
	
	public boolean isPasswordMatching(User user) { // 회원 가입 시 비밀번호 일치 여부를 확인
		
		return user.getPassword().equals(user.getConfirmPassword());
		
	}
	
	public boolean isLoginMatching(String username, String password) {
		
		Optional<User> user = userRepository.findByUsername(username);
		
		if(!user.isPresent()) { // 회원이 존재하지 않으면
			return false;
		}
		
		if(!(password.equals(user.get().getPassword()))) { // 회원 비밀번호와 입력 비밀번호가 다르면
			return false;
		}
		
		return true;
		
	}
	
	public Optional<User> findByUsername(String username){
		
		return userRepository.findByUsername(username);
		
	}
	
}
