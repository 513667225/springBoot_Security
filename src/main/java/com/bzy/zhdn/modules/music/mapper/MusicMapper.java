package com.bzy.zhdn.modules.music.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.bzy.zhdn.modules.music.entity.MusicEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: MusicMapper
 */
public interface MusicMapper extends BaseMapper<MusicEntity> {

    /**
     *
     * 功能描述: 同样支持 MyBatis
     *
     * @param:
     * @return:
     * @auther: lkw
     * @date: 2018/6/24 17:54
     */
    @Select("select * from music")
    List<MusicEntity> selectListBySQL();


    @Select("select * from music")
    List<MusicEntity> pageMusicByPlugin(Pagination page);



}