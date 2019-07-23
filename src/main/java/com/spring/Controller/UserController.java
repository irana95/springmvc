package com.spring.Controller;

import com.spring.Model.UserEntity;
import com.spring.Service.Userservice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    Userservice userservice;

    Logger logger=Logger.getLogger(UserController.class);

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public ModelAndView getUserList(HttpServletRequest request ){
        return  userservice.getUserList(request);

    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView getUserList( @Valid @ModelAttribute("user") UserEntity user, BindingResult result){
        return userservice.addUser(user,result);
    }

    @RequestMapping(value = "/deleteUser/{id}",method = RequestMethod.GET)
    public ModelAndView getUserList(@PathVariable("id") int id) {
       return  userservice.deleteUser(id);
    }

    @RequestMapping(value = "/getUser/{id}",method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("id") int id, HttpServletRequest request) {
        return userservice.getUserById(id, request);
    }


    @RequestMapping(value = "/editUser/{id}",method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable("id") int id, HttpServletRequest request) {
        return userservice.editUser(id, request );
    }

    @RequestMapping(value = "/editUser/updateUser",method = RequestMethod.POST)
    public ModelAndView updateUser(@Valid @ModelAttribute("user") UserEntity user, BindingResult result)
    {
        return userservice.updateUser(user,result);
    }


    @RequestMapping(value = "/error",method = RequestMethod.GET)
     public ModelAndView error(){
        ModelAndView model=new ModelAndView("pages/error");
        return  model;
    }


}
