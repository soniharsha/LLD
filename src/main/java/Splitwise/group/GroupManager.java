package Splitwise.group;

import java.util.HashMap;
import java.util.Map;

class GroupManager {
    private Integer id;
    private Map<Integer, Group> groupMap;

    public GroupManager() {
        this.id = 0;
        this.groupMap = new HashMap<>();
    }

    public Integer createNewGroupId() {
        return ++this.id;
    }
    public Group getGroupById(Integer groupId) {
        return groupMap.get(groupId);
    }

    public Group addGroup(Group group) {
        this.groupMap.put(group.getId(), group);
        return getGroupById(group.getId());
    }
}
