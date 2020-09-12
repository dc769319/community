package cc.charles.community.mapper;

import cc.charles.community.model.User;
import org.springframework.stereotype.Component;

/**
 * @author charlesdong
 */
@Component
public interface UserExtMapper {

    /**
     * 通过用户名获取用户数据
     *
     * @param name 用户名
     * @return 用户数据
     */
    User getByName(String name);

    /**
     * 通过userId获取用户数据
     *
     * @param userId 用户id
     * @return 用户数据
     */
    User getById(Integer userId);
}