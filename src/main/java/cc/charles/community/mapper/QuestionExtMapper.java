package cc.charles.community.mapper;

import cc.charles.community.dto.QuestionSearchDTO;
import cc.charles.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * QuestionExtMapper与Resources/mapper/QuestionExtMapper.xml映射起来
 * xml文件中编写SQL语句
 */
@Component
public interface QuestionExtMapper {

    List<Question> pageList(QuestionSearchDTO questionSearchDTO);

    List<Question> listByUserId(@Param("offset") Long offset, @Param("limit") Long limit, @Param("userId") Integer userId);

    void incViewCount(Question question);

    void incCommentCount(Question question);

    List<Question> relatedQues(@Param("tagList") String[] tagList, @Param("refId") Integer refId);

    Integer searchCount(QuestionSearchDTO questionSearchDTO);
}