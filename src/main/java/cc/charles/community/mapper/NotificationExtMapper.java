package cc.charles.community.mapper;

import cc.charles.community.model.Notification;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author charles
 */
@Component
public interface NotificationExtMapper {

    /**
     * 获取消息列表
     *
     * @param offset   偏移量
     * @param limit    查询数量
     * @param receiver 收到消息的人
     * @return 收到的消息列表
     */
    List<Notification> listByReceiver(@Param("offset") Long offset, @Param("limit") Long limit, @Param("receiver") Integer receiver);

    /**
     * 刷新未读数
     *
     * @param userId 用户id
     * @return 未读数
     */
    Integer refreshUnReadCount(Integer userId);
}
