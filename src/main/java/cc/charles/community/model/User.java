package cc.charles.community.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_user.ID
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_user.ACCOUNT_ID
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_user.NAME
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_user.TOKEN
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_user.GMT_CREATE
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_user.GMT_MODIFIED
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_user.BIO
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    private String bio;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_user.AVATAR_URL
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    private String avatarUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_user.ID
     *
     * @return the value of c_user.ID
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_user.ID
     *
     * @param id the value for c_user.ID
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_user.ACCOUNT_ID
     *
     * @return the value of c_user.ACCOUNT_ID
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_user.ACCOUNT_ID
     *
     * @param accountId the value for c_user.ACCOUNT_ID
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_user.NAME
     *
     * @return the value of c_user.NAME
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_user.NAME
     *
     * @param name the value for c_user.NAME
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_user.TOKEN
     *
     * @return the value of c_user.TOKEN
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_user.TOKEN
     *
     * @param token the value for c_user.TOKEN
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_user.GMT_CREATE
     *
     * @return the value of c_user.GMT_CREATE
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_user.GMT_CREATE
     *
     * @param gmtCreate the value for c_user.GMT_CREATE
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_user.GMT_MODIFIED
     *
     * @return the value of c_user.GMT_MODIFIED
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_user.GMT_MODIFIED
     *
     * @param gmtModified the value for c_user.GMT_MODIFIED
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_user.BIO
     *
     * @return the value of c_user.BIO
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public String getBio() {
        return bio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_user.BIO
     *
     * @param bio the value for c_user.BIO
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_user.AVATAR_URL
     *
     * @return the value of c_user.AVATAR_URL
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_user.AVATAR_URL
     *
     * @param avatarUrl the value for c_user.AVATAR_URL
     *
     * @mbg.generated Sat Jul 11 20:38:33 CST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}