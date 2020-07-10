package parser;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.users.UserXtrCounters;

import java.util.ArrayList;
import java.util.List;

public class ParserFacade {
    private final Parser parser;

    public ParserFacade() {
        parser = Parser.getInstance();
    }

    public ArrayList<MatchingData> getMatchingDataList(Integer userId) throws ClientException, ApiException, InterruptedException {
        ArrayList<MatchingData> resultList = new ArrayList<>();
        if (!parser.isUserValid(userId)) throw new RuntimeException("Cannot get information about user!");
        List<UserXtrCounters> userList = getUserList(userId);
        for (UserXtrCounters friend : userList) {
            Thread.sleep(300);
            List<GroupFull> groupList = getGroupList(friend);
            ItemData[] groupNodeList = new ItemData[groupList.size()];
            int i = 0;
            for (GroupFull group : groupList) {
                Thread.sleep(300);
                groupNodeList[i] = new ItemData(group.getId(), group.getName(), group.getPhoto50().toString());
                i++;
            }
            addToResultList(resultList, friend, groupNodeList);
        }
        return resultList;
    }

    private void addToResultList(ArrayList<MatchingData> resultList, UserXtrCounters friend, ItemData[] groupNodeList) throws ClientException, ApiException {
        resultList.add(new MatchingData(new ItemData(friend.getId(),
                friend.getFirstName() + " " + friend.getLastName(),
                parser.getAvatarURL(friend.getId())), groupNodeList));
    }

    private List<GroupFull> getGroupList(UserXtrCounters friend) throws ClientException, ApiException {
        List<Integer> groupIdsList = parser.getUserCommunitiesIds(friend.getId());
        List<GroupFull> groupList = parser.getGroupsById(groupIdsList);
        return groupList.subList(0, Math.min(3, groupIdsList.size()));
    }

    private List<UserXtrCounters> getUserList(Integer userId) throws ClientException, ApiException {
        List<Integer> friendsIds = parser.getUserFriendsIds(userId);
        friendsIds.add(0, userId);
        List<UserXtrCounters> friendList = parser.getUsersByIds(friendsIds);
        return friendList.subList(0, Math.min(5, friendList.size()));
    }

}
