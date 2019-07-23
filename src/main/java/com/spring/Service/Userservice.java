package com.spring.Service;

import com.spring.Dao.UserDao;
import com.spring.Exceptions.ExceptionCodes;
import com.spring.Exceptions.ExceptionResp;
import com.spring.Exceptions.Exceptions;
import com.spring.Model.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class Userservice {

    @Autowired
    UserDao userDao;

    static final Logger logger=Logger.getLogger(Userservice.class);

    public  ModelAndView getUserList(HttpServletRequest request) {
        logger.info("request is : "+request.getRequestURI());
        ModelAndView model= new ModelAndView("pages/userList");
        List<UserEntity> userList=userDao.getUserList();
        model.addObject("users",userList);
        logger.info("response is : "+ userList);
        return  model;
    }

    public ModelAndView addUser(UserEntity user, BindingResult result) {
        ModelAndView model= new ModelAndView("index");
        if(!result.hasErrors()){
            try {
                UserEntity userr = userDao.getUserByMail(user.getMail());
                if (userr == null) {
                    userDao.addUser(user);
                    List<UserEntity> users = userDao.getUserList();
                    model = new ModelAndView("pages/userList");
                    model.addObject("users", users);
                    logger.info("user added: " + user);
                } else {
//                    throw new ExceptionResp(ExceptionCodes.USER_EXIST, "user exist with mail : "+user.getMail());
                      throw new ExceptionResp(Exceptions.USER_EXIST.getCode(),Exceptions.USER_EXIST.getDescription());
                }
            }catch (ExceptionResp exceptionResp) {
                      model = new ModelAndView("pages/error");
                      model.addObject("errorMessage", "Error Code is: "+exceptionResp.getIcode() +" and message is:  "+exceptionResp.getMessage());
                      logger.error("Error Code is: "+exceptionResp.getIcode() +" and message is:  "+exceptionResp.getMessage());
                }
        }
       return  model;
    }

    public ModelAndView  deleteUser(int id)  {
                ModelAndView model= new ModelAndView("pages/userList");
        try {
                UserEntity user= userDao.getUserById(id);
            if (user==null){
                model= new ModelAndView("pages/error");
           throw  new ExceptionResp(ExceptionCodes.USER_NOT_FOUND, "User doesnt exist with id= "+id);
            } else{
                  userDao.deleteUser(id);
                  List<UserEntity> users=userDao.getUserList();
                  model.addObject("users",users);
                  logger.info("User with id "+id +" is deleted");
            }
        } catch (ExceptionResp exceptionResp) {
                   model.addObject("errorMessage", exceptionResp.getCode()+" : message: "+exceptionResp.getMessage());
                   System.out.println( exceptionResp.getCode()+" : message: "+exceptionResp.getMessage());
        }
        return  model;

    }

    public ModelAndView getUserById(int id,  HttpServletRequest request)  {
        logger.info("request is: "+ request.getRequestURI());
        ModelAndView model= new ModelAndView("pages/userList");
        try {
            UserEntity user= userDao.getUserById(id);
           if (user==null ){
               model=new ModelAndView("pages/error");
               throw new ExceptionResp(ExceptionCodes.USER_NOT_FOUND, "User doesnt exist with id= "+id);
           } else{
               model=new ModelAndView("pages/userList");
               model.addObject("users",user);
           }

        } catch (ExceptionResp exceptionResp) {
               logger.error("Error code :"+ exceptionResp.getCode()+" and  Message: "+exceptionResp.getMessage());
               model.addObject("errorMessage",exceptionResp.getCode()+" : message: "+exceptionResp.getMessage());
               System.out.println( exceptionResp.getCode()+" : message: "+exceptionResp.getMessage());
        } catch (NullPointerException ex){
               ex.printStackTrace();
        }
        return model;
    }

    public ModelAndView editUser(int id, HttpServletRequest request) {
        ModelAndView model=new ModelAndView("pages/editUser");
        UserEntity user=userDao.getUserById(id);
            try {
                if(user!=null) {
                    model=new ModelAndView("pages/editUser");
                    model.addObject("user",user);
                }else {
                    model=new ModelAndView("pages/error");
                    throw new ExceptionResp(ExceptionCodes.USER_NOT_FOUND, "User doesnt exist with id= "+id);
                }
                    } catch (ExceptionResp exceptionResp) {
                       logger.error("Error code :"+ exceptionResp.getCode()+" and  Message: "+exceptionResp.getMessage());
                       model.addObject("errorMessage",exceptionResp.getCode()+" : message: "+exceptionResp.getMessage());
                       System.out.println( exceptionResp.getCode()+" : message: "+exceptionResp.getMessage());
            }
          return  model;
    }


    public ModelAndView updateUser(UserEntity user, BindingResult result) {
         ModelAndView model=new ModelAndView("pages/userList");
        if(!result.hasErrors()){
            userDao.updateUser(user);
            List<UserEntity> users= userDao.getUserList();
            model.addObject("users", users);
            logger.info("User with id "+ user.getId()+"  updated to " + user);
        }else{
            model=new ModelAndView("pages/editUser");
        }
      return  model;
    }
}
