package com.bzy.zhdn.modules.music.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.bzy.zhdn.modules.music.entity.MusicEntity;

import java.util.List;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: MusicService
 */
public interface MusicService {

    List<MusicEntity> listMusic();

    List<MusicEntity> pageMusic(Integer current, Integer size);

    Page<MusicEntity> pageMusicByPlugin(Page<MusicEntity> page);

    Integer insertMusic(MusicEntity musicEntity);

    Integer deleteMusicById(Integer id);

    Integer updateMusicById(MusicEntity musicEntity);

}
