package com.onemap.api;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import com.onemap.api.AccessToken.Type;
import com.onemap.api.http.HttpRequest;
import com.onemap.api.http.HttpRequest.HttpRequestException;
import com.onemap.api.json.JSONException;
import com.onemap.api.json.JSONObject;
import com.onemap.api.mapper.ObjectMapper;
import com.onemap.api.service.SMSService;


public class OneMapClient {

    /**
     * 客户端ID。应用的App ID。
     */
    private String clientId;

    /**
     * 客户端密钥。应用的Secret Key。
     */
    private String clientSecret;

    /**
     * Access Token
     */
    private AccessToken accessToken;

    /**
     * API执行器
     */
    private OneMapExecutor executor;

    /**
     * JSON到对象映射策略对象
     */
    private ObjectMapper objectMapper;

    private SMSService smsService;
    
    /**
     * @param clientId
     * @param clientSecret
     */
    public OneMapClient(String clientId, String clientSecret) {
        super();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.executor = new DefaultOneMapExecutor("GBK");
        this.objectMapper = new ObjectMapper();
    }

    public void authorizeWithAuthorizationCode(String code, String redirectUri)
            throws AuthorizationException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", this.clientId);
        params.put("client_secret", this.clientSecret);
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("redirect_uri", redirectUri);
        params.put("token_type", AccessToken.Type.MAC.toString());

        this.accessToken = requestAccessToken(params);
    }

    /**
     * 
     * @param params
     * @return
     * @throws AuthorizationException
     */
    private AccessToken requestAccessToken(Map<String, String> params)
            throws AuthorizationException {
        try {
            HttpRequest request = HttpRequest.post("/oauth/token");
            request.form(params);
            int statusCode = request.code();
            String body = request.body();
            JSONObject response = new JSONObject(body);

            if (statusCode == HttpURLConnection.HTTP_OK && response.has("access_token")) {
                String accessToken = response.getString("access_token");
                String refreshToken = response.has("refresh_token") ? response
                        .getString("refresh_token") : null;
                String macKey = response.has("mac_key") ? response.getString("mac_key") : null;
                String macAlgorithm = response.has("mac_algorithm") ? response
                        .getString("mac_algorithm") : null;

                AccessToken.Type type = response.has("mac_algorithm")
                        && response.has("mac_algorithm") ? AccessToken.Type.MAC
                        : AccessToken.Type.Bearer;

                return new AccessToken(type, accessToken, refreshToken, macKey, macAlgorithm);

            } else {
                throw new AuthorizationException("Authorization failed with Authorization Code. "
                        + response.getString("error") + ": "
                        + response.getString("error_description"));
            }

        } catch (HttpRequestException e) {
            throw new AuthorizationException("Authorization failed. Failed to send http request");
        } catch (JSONException e) {
            throw new AuthorizationException("Parse OAuth response failed. " + e.getMessage());
        }
    }

    public void authorizeWithAccessToken(String bearerToken) throws AuthorizationException {
        this.accessToken = new AccessToken(Type.Bearer, bearerToken, null, null, null);
    }

    public void authorizeWithAccessToken(String macToken, String macKey, String macAlgorithm)
            throws AuthorizationException {
        this.accessToken = new AccessToken(Type.MAC, macToken, null, macKey, macAlgorithm);
    }

    public void authorizeWithAccessToken(AccessToken token) throws AuthorizationException {
        this.accessToken = token;
    }

    public void authorizeWithClientCredentials() throws AuthorizationException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", this.clientId);
        params.put("client_secret", this.clientSecret);
        params.put("grant_type", "client_credentials");
        params.put("token_type", AccessToken.Type.MAC.toString());

        this.accessToken = requestAccessToken(params);
    }

    public void authorizeWithResourceOwnerPassword(String username, String password)
            throws AuthorizationException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", this.clientId);
        params.put("client_secret", this.clientSecret);
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        params.put("token_type", AccessToken.Type.MAC.toString());

        this.accessToken = requestAccessToken(params);
    }
    
    /**
     * @return the smsService
     */
    public SMSService getSMSService(){
        if (smsService == null) {
        	smsService = new SMSService(executor, accessToken, objectMapper);
        }
        return smsService;
    }
}

