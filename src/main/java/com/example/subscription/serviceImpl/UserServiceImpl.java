package com.example.subscription.serviceImpl;

import com.example.subscription.repos.UserRepo;
import com.example.subscription.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;



}