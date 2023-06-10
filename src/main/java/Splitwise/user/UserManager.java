package Splitwise.user;


import java.util.HashMap;
import java.util.Map;

class UserManager {
    private Integer id;
    private Map<Integer, User> userMap;
    private Map<String, Integer> phoneNumberUserMap;
    private Map<String, Integer> emailIdUserMap;

    public UserManager() {
        this.id = 0;
        this.userMap = new HashMap<>();
    }

    public Integer getNewUserId() {
        return ++this.id;
    }

    public User getUserByUserId(Integer id) {
        return userMap.get(id);
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userMap.get(phoneNumberUserMap.get(phoneNumber));
    }

    public User getUserByEmail(String emailId) {
        return userMap.get(emailIdUserMap.get(emailId));
    }

    public User addUser(User user) {
        this.userMap.put(user.getId(), user);
        if(user.getEmailId()!=null) this.emailIdUserMap.put(user.getEmailId(), user.getId());
        if(user.getPhoneNumber()!=null) this.phoneNumberUserMap.put(user.getPhoneNumber(), user.getId());
        return getUserByUserId(user.getId());
    }

    public void deleteUser(Integer userId) {
        this.userMap.remove(userId);
    }

    public boolean isUserRegisteredWithPhoneNumber(String phoneNumber) {
        if(phoneNumberUserMap.containsKey(phoneNumber)) {
            User user = userMap.get(phoneNumberUserMap.get(phoneNumber));
            return user.isRegistered();
        }
        return false;
    }

    public boolean isUserRegisteredWithEmailId(String emailId) {
        if(emailIdUserMap.containsKey(emailId)) {
            User user = userMap.get(emailIdUserMap.get(emailId));
            return user.isRegistered();
        }
        return false;
    }

}
