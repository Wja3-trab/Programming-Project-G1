package com.medialab.rental.service;

import com.medialab.rental.User;
import com.medialab.rental.UserRole;
import com.medialab.rental.repository.CurrentItemUserRepository;
import com.medialab.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public User getCustomer(String userName) {
        try {
            User myUser = userRepository.findByUsername(userName);
            if (myUser == null) {
                throw new RuntimeException("User with the following name doesn't exist: " + userName);
            }
            System.out.println("This is the name of the user: " + myUser.getUsername());
            return myUser;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public User saveCustomer(String username, String email, String phoneNumber, String address, UserRole role) {
        User oldUser = getCustomer(username);
        oldUser.setEmail(email);
        oldUser.setPhoneNumber(phoneNumber);
        oldUser.setAddress(address);
        oldUser.setRole(role);
        oldUser.setUsername(username);
        userRepository.save(oldUser);
        return oldUser;
    }

    @Transactional
    public User saveCustomer(String username, String email, String phoneNumber, String address, UserRole role, String password) {
        User oldUser = getCustomer(username);
        oldUser.setEmail(email);
        oldUser.setPhoneNumber(phoneNumber);
        oldUser.setAddress(address);
        oldUser.setRole(role);
        oldUser.setUsername(username);
        oldUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(oldUser);
        return oldUser;
    }

    @Transactional
    public User registerCustomer(String username, String password, String email, String phoneNumber, String address) {
        if(userRepository.findByUsername(username) != null) {
            throw UsernameTakenException.of(username);
        }

        User user = new User(username,passwordEncoder.encode(password),email,phoneNumber,address, UserRole.normal);
        userRepository.save(user);
        return user;
    }

}
