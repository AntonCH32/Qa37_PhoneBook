package manager;

import models.Contact;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm()
    {
        click(By.cssSelector("a[href='/add']"));
    }

    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastname());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type((By.cssSelector("input[placeholder='email']")), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    public void saveContact()
    {
       getScreenElement("src/test/screenshots/screen-btn.png",By.cssSelector(".add_form__2rsm2>button"));
       click(By.cssSelector(".add_form__2rsm2>button"));
    }

    public void returnToHome() {
        click(By.xpath("//a[normalize-space()='HOME']"));
    }


    public boolean isContactAddedByName(String name)
    {
        List<WebElement> list=wd.findElements(By.cssSelector("h2"));
        for (WebElement el :list)
        {
            if(el.getText().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone)
    {
        List<WebElement> list=wd.findElements(By.cssSelector("h3"));
        for(WebElement el: list)
        {
            if(el.getText().equals(phone))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageStillDisplayed()
    {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public int removeOneContact()
    {
        int before = countOfContacts();
        logger.info("Number of contacts list before remove is ---->"+before);
        removeContact();

        int after = countOfContacts();
        logger.info("Number of contacts list after remove is ---->"+after);
        return before - after;
    }

    private void removeContact()
    {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(1000);
    }

    private int countOfContacts()
    {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }

    public void removeAllContacts()
    {
        while(wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size()!=0)
        {
            removeContact();
        }
    }

    public void provideContacts()
    {
        if (countOfContacts() < 3)
        {
            for (int i = 0; i < 3; i++)
            {
                addOneContact();
            }
        }
    }

    private void addOneContact()
    {
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Ant"+i)
                .lastname("Cher")
                .email("ant"+i+"@gmail.com")
                .phone("5547856"+i)
                .address("Rishon")
                .description("Friend")
                .build();
        openContactForm();
        fillContactForm(contact);
        saveContact();
        pause(1000);
    }
}




