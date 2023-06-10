package Splitwise.user;

import Splitwise.request.UserRegistrationRequest;

public class UserService {
    private UserManager userManager;

    public UserService() {
        this.userManager = new UserManager();
    }

    public User getUser(Integer userId) {
        return userManager.getUserByUserId(userId);
    }

    public User getUserByPhoneNumber(String phoneNumber, Integer userId) {
        //user in friend list of userId
        if(userManager.getUserByPhoneNumber(phoneNumber)!=null) return userManager.getUserByPhoneNumber(phoneNumber);
        User user = new User();
        user.setId(userManager.getNewUserId());
        user.setPhoneNumber(phoneNumber);
        return userManager.addUser(user);
    }

    public User getUserByEmailId(String emailId, Integer userId) {
        //user in friend list of userId
        if(userManager.getUserByEmail(emailId)!=null) return userManager.getUserByEmail(emailId);
        User user = new User();
        user.setId(userManager.getNewUserId());
        user.setEmailId(emailId);
        return userManager.addUser(user);
    }

    public User registerUser(UserRegistrationRequest userRegistrationRequest) {
        User requiredUser = null;
        if(userRegistrationRequest.getPhoneNumber()!=null) {
            requiredUser = userManager.getUserByPhoneNumber(userRegistrationRequest.getPhoneNumber());
        }

        if(requiredUser==null) {
            requiredUser = userManager.getUserByEmail(userRegistrationRequest.getEmailId());
        }

        if(requiredUser==null) {
            requiredUser = new User();
            requiredUser.setId(userManager.getNewUserId());
        }

        if(requiredUser.isRegistered()) return requiredUser;

        requiredUser.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        requiredUser.setEmailId(userRegistrationRequest.getEmailId());
        requiredUser.setRegistered(true);
        requiredUser.setName(userRegistrationRequest.getName());
        return userManager.addUser(requiredUser);
    }
}
