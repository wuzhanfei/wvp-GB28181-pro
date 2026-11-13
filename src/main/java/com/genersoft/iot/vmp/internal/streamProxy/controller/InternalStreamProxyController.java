package com.genersoft.iot.vmp.internal.streamProxy.controller;

import com.alibaba.fastjson2.JSONObject;
import com.genersoft.iot.vmp.common.StreamInfo;
import com.genersoft.iot.vmp.conf.UserSetting;
import com.genersoft.iot.vmp.conf.exception.ControllerException;
import com.genersoft.iot.vmp.service.bean.ErrorCallback;
import com.genersoft.iot.vmp.service.bean.InviteErrorCode;
import com.genersoft.iot.vmp.streamProxy.bean.StreamProxy;
import com.genersoft.iot.vmp.streamProxy.service.IStreamProxyPlayService;
import com.genersoft.iot.vmp.streamProxy.service.IStreamProxyService;
import com.genersoft.iot.vmp.vmanager.bean.ErrorCode;
import com.genersoft.iot.vmp.vmanager.bean.StreamContent;
import com.genersoft.iot.vmp.vmanager.bean.WVPResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * @author wuzhanfei
 * @since 2026-05-08 13:26:25
 */
@RestController
@RequestMapping("internal/stream-proxy")
@Slf4j
public class InternalStreamProxyController {

    @Resource
    private IStreamProxyService streamProxyService;

    @Autowired
    private UserSetting userSetting;

    @Autowired
    private IStreamProxyPlayService streamProxyPlayService;

    @Value("${wvp.ssl:false}")
    private boolean ssl;

    @PostMapping("/edit")
    @Operation(summary = "内部编辑拉流代理")
    public Integer edit(HttpServletRequest request,@RequestBody StreamProxy param){
        if(param.getId()==0){
            log.info("添加代理： " + JSONObject.toJSONString(param));
            if (ObjectUtils.isEmpty(param.getRelatesMediaServerId())) {
                param.setRelatesMediaServerId(null);
            }
            if (ObjectUtils.isEmpty(param.getType())) {
                param.setType("default");
            }
            if (ObjectUtils.isEmpty(param.getGbId())) {
                param.setGbDeviceId(null);
            }
            param.setServerId(userSetting.getServerId());
            streamProxyService.add(param);
            //初始化代理
            streamProxyService.startProxyAsync(request, param.getId());
        }else{
            log.info("更新代理： " + JSONObject.toJSONString(param));
            if (ObjectUtils.isEmpty(param.getRelatesMediaServerId())) {
                param.setRelatesMediaServerId(null);
            }
            if (ObjectUtils.isEmpty(param.getGbId())) {
                param.setGbDeviceId(null);
            }
            streamProxyService.update(param);
        }
        return param.getId();
    }

    @GetMapping("start")
    public DeferredResult<WVPResult<String>> start(HttpServletRequest request, int id,String mode){
        log.info("播放代理： {}", id);
        StreamProxy streamProxy = streamProxyService.getStreamProxy(id);
        Assert.notNull(streamProxy, "代理信息不存在");

        DeferredResult<WVPResult<String>> result = new DeferredResult<>(10000L);

        ErrorCallback<StreamInfo> callback = (code, msg, streamInfo) -> {
            if (code == InviteErrorCode.SUCCESS.getCode()) {
                WVPResult<String> wvpResult = WVPResult.success();
                if (streamInfo != null) {
                    if (userSetting.getUseSourceIpAsStreamIp()) {
                        streamInfo=streamInfo.clone();//深拷贝
                        String host;
                        try {
                            URL url=new URL(request.getRequestURL().toString());
                            host=url.getHost();
                        } catch (MalformedURLException e) {
                            host=request.getLocalAddr();
                        }
                        streamInfo.changeStreamIp(host);
                    }
                    if (!ObjectUtils.isEmpty(streamInfo.getMediaServer().getTranscodeSuffix())
                            && !"null".equalsIgnoreCase(streamInfo.getMediaServer().getTranscodeSuffix())) {
                        streamInfo.setStream(streamInfo.getStream() + "_" + streamInfo.getMediaServer().getTranscodeSuffix());
                    }
                    wvpResult.setData(streamInfo.getByMode(mode,ssl));
                }else {
                    wvpResult.setCode(code);
                    wvpResult.setMsg(msg);
                }

                result.setResult(wvpResult);
            }else {
                result.setResult(WVPResult.fail(code, msg));
            }
        };

        streamProxyPlayService.start(id, null, callback);
        return result;
    }

    @GetMapping("delete")
    public void delte(int id){
        log.info("移除代理： {}", id);
        streamProxyService.delete(id);
    }


}
