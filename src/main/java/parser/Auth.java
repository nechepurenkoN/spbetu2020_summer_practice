package parser;

import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Auth {

    private final String CONFIG_PATH = "app.config";
    private static Auth self;
    private AuthData authData;
    private ServiceActor serviceActor;
    private UserActor userActor;

    private Auth() {

        authData = new AuthData(CONFIG_PATH);
        serviceActor = new ServiceActor(
                authData.getAppId(),
                authData.getSecureKey(),
                authData.getServiceToken()
        );

        userActor = new UserActor(
                authData.getAppId(),
                authData.getUserAccessToken()
        );
    }

    private String getUserAccessToken() {
        String authorizeResponseBody = getResponse(getFirstRequestURIString()).body();
        String[] authRequestParams = getRequestParams(authorizeResponseBody);
        String authURIString = getAuthURIString(authRequestParams);
        System.out.println(getAuthURIString(authRequestParams));
        String redirectURIString = getResponse(authURIString).headers().map().get("location").get(0);
        return new String("");
    }

    private HttpResponse<String> getResponse(String uriString) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriString))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getAuthURIString(String[] authRequestParams) {
        return new String("https://login.vk.com/?act=login&soft=1&utf8=1"+
                "&q=1"+
                "&_origin"+authRequestParams[2]+
                "&ip_h="+authRequestParams[0]+
                "&lg_h"+authRequestParams[3]+
                "&from_host=oauth.vk.com"+
                "&to="+authRequestParams[1]+
                "&expire=0"+
                "&email="+authData.getUserLogin()+
                "&pass="+authData.getUserPassword());
    }

    private String[] getRequestParams(String authorizeResponseBody) {
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("name=\"(.*?)\" value=\"(.*?)\"")
                .matcher(authorizeResponseBody);
        while (m.find()) {
            allMatches.add(m.group());
        }
        String[] params = new String[4];
        params[0] = getResponseParamValue(allMatches.get(1));
        params[1] = getResponseParamValue(allMatches.get(3));
        params[2] = getResponseParamValue(allMatches.get(0));
        params[3] = getResponseParamValue(allMatches.get(2));
        return params;
    }

    private String getResponseParamValue(String nameValueString) {
        String temp = nameValueString.split("=\"")[2];
        return temp.substring(0, temp.length() - 1);
    }


    private String getFirstRequestURIString() {
        return new String("https://oauth.vk.com/authorize?" +
                "client_id="+ authData.getAppId() +
                "&scope=262146"+
                "&redirect_uri=http://oauth.vk.com/blank.html"+
                "&display=popup"+
                "&response_type=token");
    }

    public static ServiceActor getServiceActor() {
        if (self == null)
            self = new Auth();
        return self.getServiceActorInstance();
    }

    private ServiceActor getServiceActorInstance() {
        return serviceActor;
    }

    public static UserActor getUserActor() {
        if (self == null)
            self = new Auth();
        return self.getUserActorInstance();
    }

    private UserActor getUserActorInstance() {
        return userActor;
    }

}

class AuthData {

    private Integer appId;
    private String secureKey;
    private String serviceToken;
    private String userLogin;
    private String userPassword;
    private String userAccessToken;

    public Integer getAppId() {
        return appId;
    }

    public String getSecureKey() {
        return secureKey;
    }

    public String getServiceToken() {
        return serviceToken;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserAccessToken() {
        return userAccessToken;
    }

    public AuthData(String path) {
        Path configFilePath = Paths.get(path);

        try(BufferedReader reader = Files.newBufferedReader(configFilePath, Charset.forName("UTF-8"))) {
            appId = Integer.valueOf(reader.readLine());
            secureKey = reader.readLine();
            serviceToken = reader.readLine();
            //userLogin = reader.readLine();
            //userPassword = decryptPassword(reader.readLine());
            userAccessToken = reader.readLine();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private String decryptPassword(String encrypted) {
        byte[] keyBytes = getKey();
        byte[] passwordBytes = getPasswordBytes(encrypted);
        StringBuilder passwordBuilder = new StringBuilder();
        for (int i = 0; i < passwordBytes.length; i++) {
            passwordBuilder.append((char) (passwordBytes[i] ^ keyBytes[i]));
        }
        return passwordBuilder.toString();
    }

    private byte[] getPasswordBytes(String encrypted) {
        String[] splittedChunks = encrypted.split("\\s+");
        byte[] passwordBytes = new byte[splittedChunks.length];
        for (int i = 0; i < passwordBytes.length; i++) {
            passwordBytes[i] = Byte.valueOf(splittedChunks[i]);
        }
        return passwordBytes;
    }

    private byte[] getKey() {
        StringBuilder builder = new StringBuilder();
        builder.append(System.getenv("processor_revision")).append("88");
        return builder.toString().getBytes();
    }

}
