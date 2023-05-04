package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {

        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }

    @Test
    public void registrationSuccess(){
        logger.info("Start test with name 'registrationSuccess'");
        logger.info("Test data ---> Email: 'don\"+i+\"@gmail.com' & Password : 'Ant1990$'");
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
        logger.info("registrationSuccess");


    }

    @Test(groups = {"smoke"})
    public void registrationWrongEmail()
    {
        logger.info("Start test with name 'registrationWrongEmail'");
        logger.info("Test data ---> Email: 'cherkasskyantonygmail.com' & Password : 'Ant1990$'");
        User user = new User().withEmail("cherkasskyantonygmail.com").withPassword("Ant1990$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
         Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
        logger.info("check if 'Wrong email or password format' alert present ");
    }

    @Test
    public void registrationWrongPassword()
    {
        logger.info("Start test with name 'registrationWrongPassword'");
        logger.info("Test data ---> Email: 'cherkasskyantonygmail.com' & Password : 'Ant193'");
        User user = new User().withEmail("cherkasskyantony@gmail.com").withPassword("Ant193");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
         Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
        logger.info("check if 'Wrong email or password format' alert present ");
    }

    @Test
    public void registrationNeValidPasswordExistUser()

    {
        logger.info("Start test with name 'registrationNeValidPasswordExistUser'");
        logger.info("Test data ---> Email: 'cherkasskyantonygmail.com' & Password : 'Ant19'");
        User user = new User().withEmail("cherkasskyantony@gmail.com").withPassword("Ant19");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
        logger.info("check if 'Wrong email or password format' alert present ");
    }

    @Test
    public void registrationExistsUser()

    {
        logger.info("Start test with name 'registrationExistsUser'");
        logger.info("Test data ---> Email: 'cherkasskyantony@gmail.com' & Password : 'Ant1990$'");
        User user = new User().withEmail("cherkasskyantony@gmail.com").withPassword("Ant1990$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
        logger.info("check if 'User already exist' message present ");
    }

}