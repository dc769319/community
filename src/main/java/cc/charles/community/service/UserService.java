package cc.charles.community.service;

import cc.charles.community.dto.GithubUser;
import cc.charles.community.mapper.UserMapper;
import cc.charles.community.model.User;
import cc.charles.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName UserService
 * @description
 * @date 2019/11/10 下午10:26
 * @since 1.8
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 保存github属权登录的用户信息
     *
     * @param githubUser github用户对象
     */
    public String saveUser(GithubUser githubUser) {
        //获取账户id
        String accountId = String.valueOf(githubUser.getId());
        //根据账户id获取用户信息
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(example);
        User oldUser = null;
        if (!users.isEmpty()) {
            oldUser = users.get(0);
        }
        String userToken;
        if (oldUser == null) {
            //数据库中没有用户信息，则新增用户
            userToken = UUID.randomUUID().toString();
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(userToken);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userMapper.insert(user);
        } else {
            //数据库中已经存在用户信息，则获取用户token
            userToken = oldUser.getToken();
            oldUser.setAvatarUrl(githubUser.getAvatar_url());
            oldUser.setName(githubUser.getName());
            oldUser.setGmtModified(System.currentTimeMillis());
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(oldUser.getId());
            userMapper.updateByExample(oldUser, userExample);
        }
        return userToken;
    }

    /**
     * 根据token获取用户对象
     *
     * @param token 用户token
     * @return 用户对象
     */
    public User getUserByToken(String token) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }
}
