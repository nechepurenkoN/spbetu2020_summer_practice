package parser;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.users.UserXtrCounters;

import java.util.ArrayList;
import java.util.List;

public class ParserFacade {
    private Parser parser;

    public ParserFacade() {
        parser = Parser.getInstance();
    }

    public ArrayList<MatchingData> getMatchingDataList(Integer userId) throws ClientException, ApiException, InterruptedException {
        ArrayList<MatchingData> resultList = new ArrayList<>();
        List<Integer> friendsIds = parser.getUserFriendsIds(userId, 4);
        friendsIds.add(userId);
        List<UserXtrCounters> friendList = parser.getUsersByIds(friendsIds);
        for (UserXtrCounters friend : friendList) {
            Thread.sleep(300);
            List<Integer> groupIdsList = parser.getUserCommunitiesIds(friend.getId(), 3);
            List<GroupFull> groupList = parser.getGroupsById(groupIdsList);
            ItemData[] groupNodeList = new ItemData[groupList.size()];
            int i = 0;
            for (GroupFull group : groupList) {
                Thread.sleep(300);
                groupNodeList[i] = new ItemData(group.getId(), group.getName(), group.getPhoto50().toString());
                i++;
            }
            resultList.add(new MatchingData(new ItemData(friend.getId(),
                    friend.getFirstName() + " " + friend.getLastName(),
                    parser.getAvatarURL(friend.getId())), groupNodeList));
        }
        return resultList;
    }

}
