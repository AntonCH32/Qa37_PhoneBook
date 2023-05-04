package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$"));
        }
        app.helperContact().provideContacts();
        logger.info("Before method finish login");

    }

    @Test(groups = {"smoke"})
    public void removeFirstContact()
    {
        logger.info("Start test with name 'removeFirstContact'");
        Assert.assertEquals(app.helperContact().removeOneContact(),1);
        logger.info("'Contact Deleted'");

    }
    @Test
    public void removeAllContacts()
    {
        logger.info("Start test with name 'removeAllContacts'");
        app.helperContact().removeAllContacts();
        Assert.assertEquals(app.getHelperUser().getMessage(),"No Contacts here!");
        logger.info("'No Contacts here!'");

    }


}