package manager;
import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HelperUser extends HelperBase
{
    public HelperUser(WebDriver wd)
    {
        super(wd);
    }

    public void openLoginRegistrationForm()
    {
        click(By.cssSelector("a[href='/login']"));
    }

    public void fillLoginRegistrationForm(String email,String password)
    {
        type(By.name("email"),email);
        type(By.xpath("//input[last()]"),password);
    }

    public void fillLoginRegistrationForm(User user)
    {
        type(By.name("email"), user.getEmail());
        type(By.xpath("//input[last()]"), user.getPassword());
    }


    public void submitLogin()
    {
        click(By.xpath("//button[text()='Login']"));
    }

    public boolean isLogged()
    {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }


    public void logout()
    {
        click(By.xpath("//button[text()='Sign Out']"));
    }


    public boolean isAlertPresent(String message)
    {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        if(alert != null && alert.getText().equals(message))
        {
            alert.accept();
            return true;
        }
        return false;
    }


    public boolean isAlertPresent2(String message)
    {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        if(alert != null && alert.getText().contains(message)){

            alert.accept();
            return true;
        }
        return false;
    }

    public void submitRegistration()
    {
        click(By.xpath("//button[text()='Registration']"));
    }

    public boolean isNoContactsHereDisplayed()
    {
        WebDriverWait wait = new WebDriverWait(wd,Duration.ofSeconds(5));
        boolean res =wait.until(ExpectedConditions.textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")),"No Contacts here!"));
        return res;
    }


    public String getMessage()
    {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1"))));

        return wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")).getText();
    }
    public void login(User user)
    {
        openLoginForm();
        fillLoginForm(user);
        submit();
        closeWindow();
    }
    private void openLoginForm()
    {
        click(By.xpath("//a[@class='active']"));
    }
    public void fillLoginForm(User user) {
        type(By.xpath("//input[@name='email']"), user.getEmail());
        type(By.xpath("//input[@name='password']"), user.getPassword());
    }

    public void closeWindow()
    {
        if (isElementPresent(By.xpath("//button[@name='login']")))
            click(By.xpath("//button[@name='login']"));
    }


    public void returnToHome()
    {
            click(By.xpath("//a[normalize-space()='HOME']"));
    }



}