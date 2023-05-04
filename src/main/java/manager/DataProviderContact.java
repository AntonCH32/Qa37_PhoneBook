package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact
{
    @DataProvider
    public Iterator<Object[]> example()
    {
        List<Object[]> list = new ArrayList<>();
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Object[]> contactWrongPhone()
    {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastname("Wick")
                .email("john@gmail.com")
                .phone("123")
                .description("The best John").build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastname("Wick")
                .email("john@gmail.com")
                .phone("1235678910111213141516")
                .description("The best John").build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastname("Wick")
                .email("john@gmail.com")
                .phone("wwwwwwwwwwwww")
                .description("The best John").build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastname("Wick")
                .email("john@gmail.com")
                .phone("")
                .description("The best John").build()});

        return list.listIterator();
    }




    @DataProvider
    public Iterator<Object[]> contactSuccess()
    {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastname("Stark")
                .address("NY")
                .phone("3434343434")
                .email("stark@gmail.com")
                .description("all fields")
                .build()});

        list.add(new Object[]{Contact.builder()
                .name("TonyReq")
                .lastname("Stark")
                .address("NY")
                .phone("3434343434")
                .email("stark1@gmail.com")
                .build()});

        return list.listIterator();
    }

    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while (line!=null)
        {
            String[]all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastname(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()});
            line= reader.readLine();
        }

        return list.listIterator();
    }
}

