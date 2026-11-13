package com.genersoft.iot.vmp.internal.cloudRecord.controller;

import com.genersoft.iot.vmp.service.ICloudRecordService;
import com.genersoft.iot.vmp.service.bean.CloudRecordItem;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部云端录像接口
 */
@RestController
@RequestMapping("internal/cloud-record")
@Slf4j
public class InternalCloudRecordController {

    @Resource
    private ICloudRecordService cloudRecordService;

    @GetMapping("/list")
    public PageInfo<CloudRecordItem> list(
            @RequestParam(required = true) String app,
            @RequestParam(required = true) String stream,
            @RequestParam int page,
            @RequestParam int count
    ) {
        log.info("[内部云端录像] 查询 app->{}, stream->{}, page->{}, count->{}", app, stream, page, count);
        return cloudRecordService.getList(page, count, null, app, stream, null, null, null, null, null);
    }
}
