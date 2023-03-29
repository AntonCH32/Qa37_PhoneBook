package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {

        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        User user = new User().withEmail("don"+i+"@gmail.com").withPassword("Ant1990$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        // Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isLogged(),"check is sing out present");
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
        Assert.assertEquals(app.getHelperUser().getMessage(),"No Contacts here!");


    }

    @Test()
    public void registrationWrongEmail()
    {

        User user = new User().withEmail("cherkasskyantonygmail.com").withPassword("Ant1990$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
         Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
    }

    @Test
    public void registrationWrongPassword()
    {
        User user = new User().withEmail("cherkasskyantony@gmail.com").withPassword("Ant193");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
         Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
    }

    @Test
    public void registrationNeValidPasswordExistUser()

    {
        User user = new User().withEmail("cherkasskyantony@gmail.com").withPassword("Ant19");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
    }

    @Test
    public void registrationExistsUser()

    {
        User user = new User().withEmail("cherkasskyantony@gmail.com").withPassword("Ant1990$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }

}