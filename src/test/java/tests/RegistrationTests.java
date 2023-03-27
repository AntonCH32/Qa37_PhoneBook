package tests;
import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Random;
import static tests.TestBase.app;

public class RegistrationTests extends TestBase
{
    @BeforeMethod
    public void preCondition()
    {
        if (app.getHelperUser().isLogged())
        {
            app.getHelperUser().logout();
        }
    }
    @Test
    public void registrationSuccess()
    {
        Random random = new Random();
        int a = random.nextInt(1500);
        System.out.println(a);

        System.out.println(System.currentTimeMillis());
        int b = (int)(System.currentTimeMillis()/1000);

        User user = new User()
                .setEmail("cherkasskyantony" + a + "@gmail.com")
                .setPassword("anT1990$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);

    }

}
