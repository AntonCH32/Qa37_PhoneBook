package manager;

import models.Contact;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm()
    {

        click(By.xpath("//a[text()='ADD']"));

    }

    public void fillContactForm( Contact contact)
    {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastname());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type((By.cssSelector("input[placeholder='email']")), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    public void submitContactForm()
    {
        click(By.cssSelector("div[class='add_form__2rsm2'] button"));
    }
    public void returnToHome()
    {
        click(By.xpath("//a[normalize-space()='HOME']"));
    }


}

