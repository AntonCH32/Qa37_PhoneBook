package tests;

import models.Contact;

public class AddNewContactTests extends TestBase {
    public void addNewContactSuccess() {
        Contact contact = Contact.builder()
                .name("Anton")
                .lastname("Cherkassky")
                .phone("0542843049")
                .email("cherkasskyantony@gmail.com")
                .address("Asher levin , Rishon le tsion")
                .description("NewContact")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();
    }
}

