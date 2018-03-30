package ua.lviv.office.until;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.lviv.office.categories.UnitTest;
import ua.lviv.office.config.TestBaseConfigClass;
import ua.lviv.office.util.ImagesHolderUtil;

@Category(UnitTest.class)
@ContextConfiguration(classes = TestBaseConfigClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ImageHolderUtilTest {

    @Test
    public void testImagesHolderUtil(){
        Assert.assertNotNull( ImagesHolderUtil.getDefaultPictureUser());
    }
}
