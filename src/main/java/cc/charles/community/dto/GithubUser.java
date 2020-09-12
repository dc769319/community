package cc.charles.community.dto;

/**
 * github个人信息对象
 * @author charlesdong
 * @version 1.0
 * @cLassName GithubUser
 * @description
 * @date 2019/8/25 下午10:13
 * @since 1.8
 */
public class GithubUser {
    /**
     * github用户名
     */
    private String login;

    /**
     * github用户id
     */
    private Long id;

    /**
     * github用户名
     */
    private String name;

    /**
     * github注册的个人邮箱
     */
    private String email;

    /**
     * 个人简介
     */
    private String bio;

    private String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
