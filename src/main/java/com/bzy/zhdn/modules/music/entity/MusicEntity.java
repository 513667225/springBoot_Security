package com.bzy.zhdn.modules.music.entity;


import com.baomidou.mybatisplus.annotations.TableName;
import com.bzy.zhdn.modules.base.entity.BaseEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: MusicEntity
 */
@TableName("music")
@EntityScan
public class MusicEntity extends BaseEntity {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MusicEntity(String name) {
        this.name = name;
    }

    public MusicEntity() {
    }

    public MusicEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}