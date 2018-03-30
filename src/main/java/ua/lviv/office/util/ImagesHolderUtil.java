package ua.lviv.office.util;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * load default image
 */
public class ImagesHolderUtil {
    private static byte[] defaultPictureUser = {};
static private Logger logger=Logger.getLogger(ImagesHolderUtil.class);

    static {
        URL urlDefaultBoy = ImagesHolderUtil.class.getResource("/images/defaultUser.jpg");

        ByteArrayOutputStream outUser = new ByteArrayOutputStream();
        try(InputStream inUser = urlDefaultBoy.openStream()) {

            IOUtils.copy(inUser, outUser);
            defaultPictureUser = outUser.toByteArray();

        } catch(IOException ioe) {
         logger.error("Default image load problem :  "+ioe);
        }
    }

    private ImagesHolderUtil(){}

    public static byte[] getDefaultPictureUser() {
        return defaultPictureUser;
    }
}
