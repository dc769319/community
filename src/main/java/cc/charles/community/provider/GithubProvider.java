package cc.charles.community.provider;

import cc.charles.community.dto.AccessTokenDTO;
import cc.charles.community.dto.AccessTokenResult;
import cc.charles.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName GithubProvider
 * @description
 * @date 2019/8/18 下午10:30
 * @since 1.8
 * ConfigurationProperties指定读取的yml配置前缀
 */
@Component
@ConfigurationProperties(prefix = "github")
@Data
public class GithubProvider {

    private String clientId;

    private String clientSecret;

    private String clientRedirectUri;

    private String accessTokenApi;

    private String authorizeUri;

    private String userApi;

    /**
     * 获取access_token
     *
     * @param accessTokenDTO 构造获取accessToken的请求的数据对象
     * @return access_token
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType jsonType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        //将请求的数据对象转换成json字符串
        String json = JSON.toJSONString(accessTokenDTO);
        RequestBody body = RequestBody.create(jsonType, json);
        Request request = new Request.Builder()
                //添加Accept头，github会返回json格式的响应报文
                .addHeader("Accept", "application/json")
                //构造post请求，请求github获取access_token的接口
                .url(accessTokenApi)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //github返回json字符串格式的响应
            String jsonRes = response.body().string();
            //将json字符串转换成本地定义的accessTokenResult对象
            AccessTokenResult accessTokenResult = JSON.parseObject(jsonRes, AccessTokenResult.class);
            //获取access_token字符串
            return accessTokenResult.getAccess_token();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取用户信息
     *
     * @param accessToken 从github获取的access_token
     * @return github用户信息对象
     */
    public GithubUser getUser(String accessToken) {
        Request request = new Request.Builder()
                .addHeader("Authorization", "token " + accessToken)
                .url(userApi)
                .build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            String jsonRes = response.body().string();
            return JSON.parseObject(jsonRes, GithubUser.class);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取回调地址
     * @return 回调地址
     */
    public String getCallbackUrl() {
        //github授权url
        String callbackUrl = "";
        try {
            callbackUrl = URLEncoder.encode(clientRedirectUri, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return String.format(
                "%s?client_id=%s&redirect_uri=%s&scope=%s&state=%s",
                authorizeUri,
                clientId,
                callbackUrl,
                "user",
                "1"
        );
    }
}
