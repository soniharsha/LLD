package Splitwise.request;

import lombok.Data;

import java.util.List;

@Data
public class GroupCreationRequest {
    private String groupName;
    private List<String> phoneNumber;
    private List<String> emailIds;
    private Integer creatorUserId;
}
