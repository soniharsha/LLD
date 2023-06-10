package Splitwise.user;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String emailId;
    private boolean registered;
}
