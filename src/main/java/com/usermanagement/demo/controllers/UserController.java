package com.usermanagement.demo.controllers;

import com.usermanagement.demo.dao.models.User;
import com.usermanagement.demo.dto.UserDTO;
import com.usermanagement.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addObject("user", new UserDTO());
        return mav;
    }
    @PostMapping(value = "/login-process")
        public ModelAndView loginProcess(UserDTO userDTO, HttpSession session){
            System.out.println(userDTO);
            session.setAttribute("user", userDTO);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/");
            return mav;
    }
    @GetMapping(value = "/register")
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");
        mav.addObject("user", new UserDTO());
        return mav;
    }
    @PostMapping(value = "/register-process")
    public ModelAndView registerProcess(UserDTO userDTO, HttpSession session){
        System.out.println(userDTO);
        session.setAttribute("user", userDTO);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/login");
        return mav;
    }
    // vì lý do chưa add được User từ form register => nên Cống phải add theo Jason
    @PostMapping(value = "/add-user")
    public @ResponseBody String createUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
