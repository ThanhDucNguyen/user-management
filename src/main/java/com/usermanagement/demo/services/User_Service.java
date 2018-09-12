package com.usermanagement.demo.services;

import com.usermanagement.demo.dao.models.User;

public interface User_Service {
    String addUser(User user);
    void postUser(User user);
}
