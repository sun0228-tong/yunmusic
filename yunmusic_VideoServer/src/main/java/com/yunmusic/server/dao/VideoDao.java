package com.yunmusic.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyi.entity.Video;
import com.yunmusic.server.dto.VideoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    /**
     * 李晓帆
     * 首页展示所有视频并可以按照标签进行搜索
     * @param tag
     * @return
     */
    List<Video> selectByTag(@Param("tag") String tag);

    /**
     * 李晓帆
     * 查询所有额标签
     * @return
     */
    List<Video> selectAllTag();

    /**
     * 李晓帆
     * 查询所有的视频类型
     * @return
     */
    List<Video> selectAllType();

    /**
     * 李晓帆
     * 按照视频类型查找视频
     * @param type
     * @return
     */
    List<Video> selectByType(@Param("type") String type);

    /**
     * 李晓帆
     * 按照类型查找属于MV中的视频
     */
    List<Video> selectMVByTag(@Param("tag") String tag);

    /**
     * 李晓帆
     * MV总排行榜
     */
    List<Video> selectByPlaynum();

    /**
     * 李晓帆
     * MV分类排行
     * @param tag
     * @return
     */
    List<Video> selectMVByPlaynumAndTag(@Param("tag") String tag);


    /**
     * 李晓帆
     * 展示单个视频所有信息
     * @param id
     * @return 视频类
     */
    Video selectOneVideo(@Param("id") Integer id);

    /**
     * 最新MV展示
     */
    List<Video> selectNewVideo();

    /**
     * 根据分类展示最新MV
     * @param tag
     * @return
     */
    List<Video> selectNewVideoByTag(@Param("tag") String tag);
}
