package cc.charles.community.dto;

/**
 * github获取access_token的接口返回的结果对象
 * @author charlesdong
 * @version 1.0
 * @cLassName AccessTokenResult
 * @description
 * @date 2019/8/25 下午10:04
 * @since 1.8
 */
public class AccessTokenResult {

    /**
     * access_token
     */
    private String access_token;

    /**
     * scope
     */
    private String scope;

    /**
     * token_type
     */
    private String token_type;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
