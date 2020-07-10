package parser;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiAccessException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.enums.FriendsOrder;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.responses.GetResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;

import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private VkApiClient apiClient;
    private ServiceActor serviceActor;
    private UserActor userActor;
    private static Parser instance;

    private Parser() {
        TransportClient transportClient = new HttpTransportClient();
        apiClient = new VkApiClient(transportClient);
        serviceActor = Auth.getServiceActor();
        userActor = Auth.getUserActor();
    }


    public static Parser getInstance() {
        if (instance == null)
            instance = new Parser();
        return instance;
    }

    public List<Integer> getUserFriendsIds(Integer userId) throws ClientException, ApiException {
        return apiClient.friends().get(serviceActor)
                .lang(Lang.RU)
                .userId(userId)
                .order(FriendsOrder.NAME)
                .count(13)
                .execute()
                .getItems();
    }

    public List<Integer> getUserCommunitiesIds(Integer userId) throws ClientException, ApiException {
        return apiClient.groups().get(userActor)
                .userId(userId)
                .lang(Lang.RU)
                .count(13)
                .execute()
                .getItems();
    }

    public List<GroupFull> getGroupsById(List<Integer> listOfGroupIds) throws ClientException, ApiException {
        return apiClient.groups().getById(userActor)
                .groupIds(listOfGroupIds.stream().map(item -> String.valueOf(item)).collect(Collectors.toList()))
                .execute();
    }

    public List<UserXtrCounters> getUsersByIds(List<Integer> listOfUserIds) throws ClientException, ApiException {
        return apiClient.users().get(userActor)
                .userIds(listOfUserIds.stream().filter(item -> {
                    try {
                        return isUserValid(item);
                    } catch (ClientException e) {
                        e.printStackTrace();
                        return false;
                    } catch (ApiException e) {
                        e.printStackTrace();
                        return false;
                    }
                }).map(item -> String.valueOf(item)).collect(Collectors.toList()))
                .execute();
    }

    public List<Photo> getUserAvatars(Integer userId) throws ClientException, ApiException {
        return apiClient.photos().get(userActor)
                .ownerId(userId).albumId("profile")
                .execute().getItems();
    }

    public String getAvatarURL(Integer userId) throws ClientException, ApiException {
        List<Photo> profilePhotoList = getUserAvatars(userId);
        if (profilePhotoList.size() == 0)
            return "https://vk.com/images/camera_50.png";
        Photo avatarItself = profilePhotoList.get(profilePhotoList.size() - 1);
        return avatarItself.getSizes().get(0).getUrl().toString();
    }

    public boolean isUserValid(Integer userId) throws ClientException, ApiException {
        List<UserXtrCounters> result = apiClient.users().get(serviceActor).userIds(String.valueOf(userId)).execute();
        if (result == null)
            return false;
        UserXtrCounters user = result.get(0);
        if (user == null)
            return false;
        try {
            return !user.getIsClosed() && user.getDeactivated() == null;
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return false;
    }

}
