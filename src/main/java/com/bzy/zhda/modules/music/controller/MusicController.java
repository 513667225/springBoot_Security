package com.bzy.zhda.modules.music.controller;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.bzy.zhda.common.utils.P;
import com.bzy.zhda.common.utils.R;
import com.bzy.zhda.modules.base.controller.BaseController;
import com.bzy.zhda.modules.music.entity.MusicEntity;
import com.bzy.zhda.modules.music.service.MusicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: MusicController
 */
@RestController
@RequestMapping("/music")
public class MusicController extends BaseController {

    @Autowired
    private MusicService musicService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    @Qualifier("musicLog")
    private Log log;

   
   
    /**
     * @remark: lkw created by time: 2018/6/25 16:58
     */
    @ApiOperation("获取所有音乐")
    @GetMapping("list-music")
    R listMusic() {
        List<MusicEntity> musicInfo = musicService.listMusic();
        return R.success().data(musicInfo);
    }

    /**
     * @remark: lkw created by time: 2018/6/25 16:58
     */
    @ApiOperation("增加音乐")
    @PostMapping("insert-music")
    R insertMusic(MusicEntity musicEntity) throws Exception {
        validateParams(request,"name");
        validateIsSuccess(musicService.insertMusic(musicEntity));
        return R.success();
    }

    /**
     * @remark: lkw created by time: 2018/6/25 16:58
     */
    @ApiOperation("删除音乐")
    @PostMapping("delete-music")
    R deleteMusic() throws Exception {
        P params = validateParams(request, "id");
        validateIsSuccess(musicService.deleteMusicById(params.getInteger("id")));
        return R.success();
    }

    /**
     * @remark: lkw created by time: 2018/6/25 16:58
     */
    @ApiOperation("修改音乐")
    @PostMapping("update-music")
    R updateMusic(MusicEntity musicEntity) throws Exception {
        validateIsSuccess(musicService.updateMusicById(musicEntity));
        return R.success();
    }

    /**
     * @remark: lkw created by time: 2018/6/25 16:58
     */
    @ApiOperation("分页")
    @GetMapping("page-music")
    R pageMusic(Pagination pagination) throws Exception {
        validateParams(request,"current", "size");
        List<MusicEntity> musicEntityList = musicService.pageMusic(pagination.getCurrent(),pagination.getSize());
        return R.success().data(musicEntityList);
    }

    /**
     * @remark: lkw created by time: 2018/6/25 16:58
     */
    @ApiOperation("分页插件")
    @GetMapping("page-music-byPlugin")
    R pageMusicByPlugin(Pagination pagination) throws Exception {
        validateParams(request,"current", "size");
        Page<MusicEntity> page = new Page<>();
        page.setSize(pagination.getSize());
        page.setCurrent(pagination.getCurrent());
        Page<MusicEntity> musicEntityPage = musicService.pageMusicByPlugin(page);
        return R.success().data(musicEntityPage);
    }

}
