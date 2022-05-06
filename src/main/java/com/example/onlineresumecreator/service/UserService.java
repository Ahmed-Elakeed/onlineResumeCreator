package com.example.onlineresumecreator.service;

import com.example.onlineresumecreator.exception.NoRecordWithThisIdException;
import com.example.onlineresumecreator.exception.SimilarDataAlreadyExistsException;
import com.example.onlineresumecreator.model.User;
import com.example.onlineresumecreator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements ServiceInterface<User>, UserDetailsService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findById(id).orElseThrow(()->new NoRecordWithThisIdException("Not Such Id"));
    }

    @Override
    public User save(User user) {
        if(this.userRepository.findUserByUserEmail(user.getUserEmail())!=null || this.findUserByUserPhone(user.getUserPhone())!=null){
            throw new SimilarDataAlreadyExistsException("Email or Phone already Exist");
        }
        user.setUserPassword(this.passwordEncoder.encode(user.getUserPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User sameEmailUser = this.findUserByUserEmail(user.getUserEmail());
        User samePhoneUser = this.findUserByUserPhone(user.getUserPhone());
        if(sameEmailUser!=null) {
            if (sameEmailUser.getUserId() == id) {
                sameEmailUser = null;
            }
        }
        if(samePhoneUser!=null) {
            if (samePhoneUser.getUserId() == id) {
                samePhoneUser = null;
            }
        }
        if (sameEmailUser == null && samePhoneUser == null) {
            user.setUserId(id);
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            return this.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    public User findUserByUserEmail(String userEmail) {
        try {
            return this.userRepository.findUserByUserEmail(userEmail);
        } catch (Exception exception) {
            return null;
        }
    }

    public User findUserByUserPhone(String phone) {
        try {
            return this.userRepository.findUserByUserPhone(phone);
        } catch (Exception exception) {
            return null;
        }
    }

    public boolean registerUser(User user, String repeat_password) {
        if (this.findUserByUserEmail(user.getUserEmail()) == null && this.findUserByUserPhone(user.getUserPhone()) == null && repeat_password.equals(user.getUserPassword())) {
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            this.save(user);
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.userRepository.findUserByUserEmail(userDetails.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUserEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), new ArrayList<>());
    }
}
