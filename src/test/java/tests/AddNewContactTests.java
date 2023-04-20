package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$"));
            logger.info("Before method finish logout");
        }

    }

    @Test
    public void  addContactSuccessAllFields()
    {
        logger.info("Start test with name 'addContactSuccessAllFields'");
        logger.info("Test data ---> Name: 'Tony' & LastName : 'Stark'");
        int i= new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Tony")
                .lastname("Stark")
                .address("NY")
                .phone("34343434"+i)
                .email("stark"+i+"@gmail.com")
                .description("all fields")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("Contact Added");


    }
    @Test
    public void  addContactSuccessRequiredFields(){
        logger.info("Test data ---> Name: 'TonyReq' & LastName : 'Stark'");
        int i= new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("TonyReq"+i)
                .lastname("Stark")
                .address("NY")
                .phone("34343434"+i)
                .email("stark"+i+"@gmail.com")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("Contact Added");
    }

    @Test
    public void addNewContactWrongName(){
        logger.info("Test data ---> Name: 'Empty' & LastName : 'Stark'");
        Contact contact = Contact.builder()
                .name("")
                .lastname("Stark")
                .address("NY")
                .phone("3434343434")
                .email("stark@gmail.com")
                .description("empty name")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().getScreen("src/test/screenshots/screen.png");
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        logger.info("AddPageStillDisplayed");

    }
    @Test
    public void  addNewContactWrongAddress(){
        logger.info("Test data ---> Name: 'Tony' & LastName: 'Stark' & Address: 'Empty'");
        Contact contact = Contact.builder()
                .name("Tony")
                .lastname("Stark")
                .address("")
                .phone("3434343434")
                .email("stark@gmail.com")
                .description("empty address")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        logger.info("AddPageStillDisplayed");

    }

    @Test
    public void addNewContactWrongLastName(){
        logger.info("Test data ---> Name: 'Tony' & LastName: 'Empty'");
        int i= new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Tony")
                .lastname("")
                .address("NY")
                .phone("3434343434")
                .email("stark@gmail.com")
                .description("empty last name")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        logger.info("AddPageStillDisplayed");
    }
    @Test
    public void addNewContactWrongPhone(){
        logger.info("Test data ---> Name: 'Tony' & LastName: 'Stark' & Phone: 'Empty'");
        Contact contact = Contact.builder()
                .name("Tony")
                .lastname("Stark")
                .address("NY")
                .phone("")
                .email("stark@gmail.com")
                .description("empty phone")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
        logger.info("Phone not valid");


    }
    @Test
    public void addNewContactWrongEmail(){
        logger.info("Test data ---> Name: 'Tony' & LastName: 'Stark' & Email: 'starkgmail.com'");
        Contact contact = Contact.builder()
                .name("Tony")
                .lastname("Stark")
                .address("NY")
                .phone("1234567891234")
                .email("starkgmail.com")
                .description("wrong email")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid: must be a well-formed email address"));
        logger.info("Assert is alert present 'Email not valid: must be a well-formed email address'");

    }


    // "Contact added" eql "Contact added"
    // "Contact  with ID : 123456 was added" contains
}