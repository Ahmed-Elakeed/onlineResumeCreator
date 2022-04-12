package com.example.onlineresumecreator.repository;

import com.example.onlineresumecreator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserEmail(String userEmail);
    User findUserByUserPhone(String phone);
}
