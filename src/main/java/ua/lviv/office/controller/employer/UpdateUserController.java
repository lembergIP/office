package ua.lviv.office.controller.employer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.entity.User;
import ua.lviv.office.service.UserService;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;


@Controller
@RequestMapping(value = "/updateUser")
public class UpdateUserController {

    @Autowired
    private UserService userService;


    private static Logger logger= LoggerFactory.getLogger(UpdateUserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView updateUser(Principal principal){
        ModelAndView modelAndView=new ModelAndView("userUpdateInfo");
        modelAndView.addObject("user",userService.findUserByEmail(principal.getName()));
        return modelAndView;
    }

    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public ModelAndView updateUserInfo(Principal principal, @RequestParam String firstName, @RequestParam String lastName
            , @RequestParam String phoneNumber, @RequestParam Date dateBirth){
        ModelAndView modelAndView=new ModelAndView("redirect:/profile");
       userService.updateUserInfo(principal.getName(),firstName,lastName,phoneNumber,dateBirth);
        return modelAndView;
    }
    @RequestMapping(value = "/password",method = RequestMethod.POST)
    public ModelAndView changePassword(Principal principal, @RequestParam String correctPassword, @RequestParam String newPassword,
                                       @RequestParam String confirmNewPassword){
          ModelAndView modelAndView=new ModelAndView();

          if((userService.updateUserPassword(principal.getName(),correctPassword,newPassword))==true){

              modelAndView.setViewName("redirect:/profile");
          }
          else{
              modelAndView.addObject("user",userService.findUserByEmail(principal.getName()));
              modelAndView.addObject("error1","Enter the correct password !!!");
              modelAndView.setViewName("redirect:/updateUser");
          }

          return modelAndView;
    }

    @RequestMapping(value = "/photo",method = RequestMethod.POST)
    public ModelAndView updateUserImage(Principal principal, @RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView=new ModelAndView();
        if (!file.isEmpty()) {
            try {
                byte[] bytes=file.getBytes();
                if(ImageIO.read(new ByteArrayInputStream(bytes)) == null){
                    logger.error("Uploaded image has a wrong extension");
                    modelAndView.setViewName("profile");
                    return modelAndView;
                }
                User user=userService.findUserByEmail(principal.getName());

               userService.updateUserPhoto(user.getId(),bytes);
                modelAndView.setViewName("redirect:/profile");

            } catch (IOException | JpaSystemException exc) {
                logger.error("Failed to save image", exc);
                modelAndView.setViewName("profile");
                return modelAndView;
            }
        }
        return modelAndView;
    }
}
