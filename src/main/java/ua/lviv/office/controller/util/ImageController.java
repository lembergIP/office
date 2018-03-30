package ua.lviv.office.controller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.lviv.office.entity.User;
import ua.lviv.office.service.UserService;
import ua.lviv.office.util.ImagesHolderUtil;

/**
 * Return image by id user
 */
@Controller
public class ImageController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/imageUser{idUser}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public byte[] getProfilePic(@PathVariable long idUser)
            throws
            AccessDeniedException {
        User user=userService.findUserById(idUser);
          if(user.getImage()==null){
             return ImagesHolderUtil.getDefaultPictureUser();
}
              return user.getImage();
    }
}
