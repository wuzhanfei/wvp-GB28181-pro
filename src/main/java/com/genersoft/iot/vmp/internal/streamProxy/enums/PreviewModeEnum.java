package com.genersoft.iot.vmp.internal.streamProxy.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 预览模式枚举
 */
@AllArgsConstructor
@Getter
public enum PreviewModeEnum{

    WEBRTC("webrtc", "WebRTC"),
    FLV("flv", "HTTP-FLV"),
    HLS("hls", "HLS");

    public static final String[] ARRAYS = Arrays.stream(values()).map(PreviewModeEnum::getMode).toArray(String[]::new);

    /**
     * 模式标识
     */
    private final String mode;
    /**
     * 模式名称
     */
    private final String name;

    public static PreviewModeEnum getByMode(String mode) {
        return ArrayUtil.firstMatch(e -> e.getMode().equals(mode), PreviewModeEnum.values());
    }

}
