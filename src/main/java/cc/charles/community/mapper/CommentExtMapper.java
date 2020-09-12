package cc.charles.community.mapper;

import cc.charles.community.model.Comment;
import org.springframework.stereotype.Component;

/**
 * @author Charles
 * @version 1.0
 */
@Component
public interface CommentExtMapper {
    void incCommentCount(Comment comment);
}
