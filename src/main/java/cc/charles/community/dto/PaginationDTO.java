package cc.charles.community.dto;

import java.util.ArrayList;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName PaginationDTO
 * @description
 * @date 2019/10/13 下午3:39
 * @since 1.8
 */
public class PaginationDTO {
    /**
     * 当前页码
     */
    private Long page;

    /**
     * SQL查询时的offset
     */
    private Long offset;

    /**
     * SQL查询时的limit
     */
    private Long limit;

    /**
     * 最大页码数
     */
    private Long maxPage;

    /**
     * 是否显示前一页按钮
     */
    private Boolean showPrevBtn;

    /**
     * 是否显示后一页按钮
     */
    private Boolean showNextBtn;

    /**
     * 是否显示第一页按钮
     */
    private Boolean showFirstBtn;

    /**
     * 是否显示最后一页按钮
     */
    private Boolean showLastBtn;

    /**
     * 总条数
     */
    private Long totalNum;

    /**
     * 最多显示页码按钮的个数
     */
    private static final Integer SHOW_PAGE_NUM = 7;

    /**
     * 页码列表
     */
    private ArrayList<Long> pages;

    /**
     * 初始化
     *
     * @param page       当前页码
     * @param pageSize   每一页显示的记录条数
     * @param totalCount 记录总数
     */
    public PaginationDTO(Long page, Long pageSize, Long totalCount) {
        this.page = page;
        this.limit = pageSize;
        if (totalCount < 1) {
            this.showPrevBtn = false;
            this.showFirstBtn = false;
            this.showNextBtn = false;
            this.showLastBtn = false;
            this.totalNum = 0L;
            this.offset = 0L;
        } else {
            if (page < 1) {
                page = 1L;
            }
            Long maxPage = calculateMaxPage(pageSize, totalCount);
            if (page > maxPage) {
                page = maxPage;
            }
            this.totalNum = totalCount;
            //当前页是第一页，则为false；否则为true
            this.showPrevBtn = (page != 1L);
            //当前页是最后一页，则为false；否则为true
            this.showNextBtn = !page.equals(maxPage);
            //计算应该显示的页码列表
            this.pages = calculatePages(page, maxPage);
            //页码列表中包含第一页则为false；否则为true
            this.showFirstBtn = !this.pages.contains(1L);
            //页码列表中包含最后一页则为false；否则为true
            this.showLastBtn = !this.pages.contains(maxPage);
            this.maxPage = maxPage;
            this.offset = (page - 1) * pageSize;
        }
    }

    /**
     * 计算应该显示的页码列表
     *
     * @param curPage 当前页码
     * @param maxPage 最大页码
     * @return 应该显示的页码列表
     */
    private ArrayList<Long> calculatePages(Long curPage, Long maxPage) {
        ArrayList<Long> pages = new ArrayList<>();
        int halfPageNum = SHOW_PAGE_NUM / 2;
        //将当前页加入页码列表中
        pages.add(curPage);
        for (int i = 1; i <= halfPageNum; i++) {
            //循环生成比当前页小的页码，并添加到页码列表中
            if (curPage - i > 0) {
                pages.add(0, curPage - i);
            }
            //循环生成比当前页大的页码，并添加到页码列表中
            if (curPage + i <= maxPage) {
                pages.add(curPage + i);
            }
        }
        return pages;
    }

    /**
     * 计算最大页码
     *
     * @param pageSize   每一页显示的数据条数
     * @param totalCount 总记录条数
     * @return 最大页码数
     */
    private Long calculateMaxPage(Long pageSize, Long totalCount) {
        if (totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return totalCount / pageSize + 1;
        }
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Long maxPage) {
        this.maxPage = maxPage;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Boolean getShowPrevBtn() {
        return showPrevBtn;
    }

    public void setShowPrevBtn(Boolean showPrevBtn) {
        this.showPrevBtn = showPrevBtn;
    }

    public Boolean getShowNextBtn() {
        return showNextBtn;
    }

    public void setShowNextBtn(Boolean showNextBtn) {
        this.showNextBtn = showNextBtn;
    }

    public Boolean getShowFirstBtn() {
        return showFirstBtn;
    }

    public void setShowFirstBtn(Boolean showFirstBtn) {
        this.showFirstBtn = showFirstBtn;
    }

    public Boolean getShowLastBtn() {
        return showLastBtn;
    }

    public void setShowLastBtn(Boolean showLastBtn) {
        this.showLastBtn = showLastBtn;
    }

    public ArrayList<Long> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Long> pages) {
        this.pages = pages;
    }
}
