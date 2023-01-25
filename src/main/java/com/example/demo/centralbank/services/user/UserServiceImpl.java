package com.example.demo.centralbank.services.user;
import com.example.demo.centralbank.models.User;
import com.example.demo.centralbank.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addNewUser(User user) {
        String pw = user.getPassword();
        user.setPassword(this.passwordEncoder.encode(pw));
        return this.userRepository.save(user);
    }

    @Override
    public User loadUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public List<User> listUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User findOne(String email) {
        return this.userRepository.findByEmail(email);
    }
}
