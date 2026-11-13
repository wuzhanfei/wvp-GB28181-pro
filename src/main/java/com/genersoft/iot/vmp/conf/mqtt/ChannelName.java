package com.genersoft.iot.vmp.conf.mqtt;

import lombok.Data;

/**
 * @author wuzhanfei
 * @since 2025-03-27 13:36:36
 */
@Data
public class ChannelName {

    public static final String INBOUND = "inbound";

    public static final String DEFAULT = "default";

    public static final String OUTBOUND = "outbound";

    public static final String INBOUND_OSD = "inboundOsd";

    public static final String INBOUND_INNER_EVENTS = "inboundInnerEvents";
}
