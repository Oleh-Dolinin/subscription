package com.example.subscription.repos;

import com.example.subscription.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface UserRepo extends JpaRepository<User, String> {

}
