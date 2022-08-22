package com.callor.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(UserVO userVO){
//        log.debug("로그인 정보 {}", userVO);
//        return "redirect:/";
//    }

}
