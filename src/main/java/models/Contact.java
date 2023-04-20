package models;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Contact
{
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String address;
    private String description;
}
