package com.bzy.zhda.modules.music.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bzy.zhda.modules.music.entity.MusicEntity;
import com.bzy.zhda.modules.music.mapper.MusicMapper;
import com.bzy.zhda.modules.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: MusicServiceImpl
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, MusicEntity> implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

    @Override
    public List<MusicEntity> listMusic() {
        EntityWrapper<MusicEntity> wrapper = new EntityWrapper();
//        wrapper.eq("name","尘埃");
        List<MusicEntity> musicEntityList = musicMapper.selectList(wrapper);
        return musicEntityList;
    }

    @Override
    public List<MusicEntity> pageMusic(Integer current, Integer size) {
        List<MusicEntity> musicEntityList = musicMapper.selectPage(
                new Page<MusicEntity>(current, size),
                null
        );
        return musicEntityList;
    }

    @Override
    public Integer insertMusic(MusicEntity musicEntity) {
        return musicMapper.insert(musicEntity);
    }

    @Override
    public Integer deleteMusicById(Integer id) {
        return musicMapper.deleteById(id);
    }

    @Override
    public Integer updateMusicById(MusicEntity musicEntity) {
        return musicMapper.updateById(musicEntity);
    }


    @Override
    public Page<MusicEntity> pageMusicByPlugin(Page<MusicEntity> page) {
        return page.setRecords(musicMapper.pageMusicByPlugin(page));
    }
}
