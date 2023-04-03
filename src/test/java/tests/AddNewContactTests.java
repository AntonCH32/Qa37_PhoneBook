package tests;
import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Test
public class AddNewContactTests extends TestBase
{
    @BeforeClass
    public void preCondition()
    {
        if (!app.getHelperUser().isLogged())
        {
            app.getHelperUser().login(new User().withEmail("cherkasskyantony@gmail.com").withPassword("anT1990$"));
        }
    }

    @Test
    public void addNewContactSuccessAll()
    {
        Contact contact = Contact.builder()
                .name("Anton1")
                .lastname("Cherkassky1")
                .phone("0542843049")
                .email("cherkasskyantony1@gmail.com")
                .address("Asher levin , Rishon le tsion")
                .description("NewContact")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();

    }

    @AfterMethod
    public void posCondition()
    {
        app.getHelperContact().returnToHome();
    }
}


