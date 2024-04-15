package com.felix.constants;

/**
 * @author Felix
 */
public class SystemConstants {
    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    public static final String STATUS_NORMAL = "0";

    /**
     * 友链审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";

    /**
     * 代表是根评论
     */
    public static final int COMMENT_ROOT_ID = -1;
    /**
     * 评论类型为文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论 类型为友链评论
     */
    public static final String LINK_COMMENT = "1";

    //redis中浏览量的key
    public static final String VIEW_COUNT_KEY="article:viewCount";

    //菜单权限
    public static final String MENU_TYPE_MENU="C";
    //按钮权限
    public static final String MENU_TYPE_BUTTON="F";

    //第一层菜单
    public static final Long MENU_PARENT_ID=0L;
    public static final String ADMIN = "1";
}
