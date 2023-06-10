package Splitwise.request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String name;
    private String phoneNumber;
    private String emailId;
}
