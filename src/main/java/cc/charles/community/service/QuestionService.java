package cc.charles.community.service;

import cc.charles.community.dto.PaginationDTO;
import cc.charles.community.dto.QuestionDTO;
import cc.charles.community.dto.QuestionSearchDTO;
import cc.charles.community.exception.CustomizeErrorCode;
import cc.charles.community.exception.CustomizeException;
import cc.charles.community.mapper.QuestionExtMapper;
import cc.charles.community.mapper.QuestionMapper;
import cc.charles.community.mapper.UserMapper;
import cc.charles.community.model.Question;
import cc.charles.community.model.QuestionExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName QuestionService
 * @description
 * @date 2019/10/3 下午1:17
 * @since 1.8
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    /**
     * 问题列表
     */
    private List<QuestionDTO> questionDTOList;

    /**
     * 分类对象
     */
    private PaginationDTO paginationDTO;

    /**
     * 初始化数据列表
     *
     * @param page     当前页码
     * @param pageSize 每页展示的记录条数
     */
    public void initList(Long page, Long pageSize, String keyword) {
        questionDTOList = new ArrayList<>();
        //计算总记录条数
        QuestionSearchDTO questionSearchDTO = new QuestionSearchDTO();
        questionSearchDTO.setKeyword(keyword);
        Long totalCount = Long.valueOf(questionExtMapper.searchCount(questionSearchDTO));
        //初始化分页类
        paginationDTO = new PaginationDTO(page, pageSize, totalCount);
        //得到数据偏移量
        questionSearchDTO.setOffset(paginationDTO.getOffset());
        //得到每页展示的记录条数
        questionSearchDTO.setPageSize(paginationDTO.getLimit());
        //获取当前页的问题列表数据
        List<Question> questionList = questionExtMapper.pageList(questionSearchDTO);
        for (Question ques : questionList) {
            //循环获取发表文章的作者
            Integer userId = ques.getCreator();
            QuestionDTO questionDTO = new QuestionDTO();
            //复制属性
            BeanUtils.copyProperties(ques, questionDTO);
            questionDTO.setTagList(ques.getTag().split(","));
            questionDTO.setUser(userMapper.selectByPrimaryKey(userId));
            if (ques.getCommentCount() == null) {
                questionDTO.setCommentCount(0);
            }
            questionDTOList.add(questionDTO);
        }
    }

    /**
     * 初始化数据列表
     *
     * @param page     当前页码
     * @param pageSize 每页展示的记录条数
     * @param userId   用户id
     */
    public void initList(Long page, Long pageSize, Integer userId) {
        questionDTOList = new ArrayList<>();
        //计算总记录条数
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Long totalCount = questionMapper.countByExample(questionExample);
        //初始化分页类
        paginationDTO = new PaginationDTO(page, pageSize, totalCount);
        //得到数据偏移量
        Long offset = paginationDTO.getOffset();
        //得到每页展示的记录条数
        Long limit = paginationDTO.getLimit();
        //获取当前页的问题列表数据
        List<Question> questionList = questionExtMapper.listByUserId(offset, limit, userId);
        for (Question ques : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            //复制属性
            BeanUtils.copyProperties(ques, questionDTO);
            questionDTO.setUser(userMapper.selectByPrimaryKey(userId));
            questionDTO.setTagList(ques.getTag().split(","));
            if (ques.getCommentCount() == null) {
                questionDTO.setCommentCount(0);
            }
            questionDTOList.add(questionDTO);
        }
    }

    /**
     * @return 问题列表
     */
    public List<QuestionDTO> getQuestionDTOList() {
        return questionDTOList;
    }

    /**
     * @return 分页对象
     */
    public PaginationDTO getPaginationDTO() {
        return paginationDTO;
    }

    /**
     * 根据问题id获取问题详情数据
     *
     * @param id 问题id
     * @return 问题详情数据
     */
    public QuestionDTO info(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (null == question) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        Integer userId = question.getCreator();
        questionDTO.setUser(userMapper.selectByPrimaryKey(userId));
        questionDTO.setTagList(question.getTag().split(","));
        if (question.getCommentCount() == null) {
            questionDTO.setCommentCount(0);
        }
        return questionDTO;
    }

    /**
     * 保存问题数据
     *
     * @param question 问题对象
     * @return 影响行数
     */
    public Integer save(Question question) {
        if (question.getId() != null) {
            //更新
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            return questionMapper.updateByExample(question, questionExample);
        } else {
            // 插入
            return questionMapper.insert(question);
        }
    }

    /**
     * 阅读数加1
     *
     * @param id 问题id
     */
    public void incView(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        //设置需要增加的阅读数
        if (question.getViewCount() == null) {
            question.setViewCount(1);
            questionMapper.updateByPrimaryKey(question);
        } else {
            question.setViewCount(1);
            questionExtMapper.incViewCount(question);
        }
    }

    /**
     * 获取与当前问题相关联的问题列表
     *
     * @param questionDTO 当前问题
     * @return 与当前问题相关联的问题
     */
    public List<QuestionDTO> relatedQues(QuestionDTO questionDTO) {
        if (questionDTO == null) {
            return null;
        }
        Integer refId = questionDTO.getId();
        if (refId == null || refId <= 0) {
            return null;
        }
        String tags = questionDTO.getTag();
        if (StringUtils.isEmpty(tags)) {
            return null;
        }
        List<Question> questionList = questionExtMapper.relatedQues(tags.split(","), refId);
        return questionList.stream().map(item -> {
            QuestionDTO questionItem = new QuestionDTO();
            //复制属性
            BeanUtils.copyProperties(item, questionItem);
            questionItem.setUser(userMapper.selectByPrimaryKey(item.getCreator()));
            questionItem.setTagList(item.getTag().split(","));
            return questionItem;
        }).collect(Collectors.toList());
    }
}
