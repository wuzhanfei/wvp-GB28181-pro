package com.genersoft.iot.vmp.utils;

import lombok.Data;

/**
 * @author wuzhanfei
 * @since 2025-03-27 14:02:38
 */
@Data
public class Consts {

    public static final String TOPIC_SEPERATOR = "/";

    public static final String TOPIC_VMS_PRE = "vms/";

    public static final String TOPIC_CLOUD = "cloud/";

    public static final String TOPIC_OSD = "/osd";

    public static final String TOPIC_STATUS = "/status";

    public static final String TOPIC_EVENTS = "/events";

    public static final String TOPIC_REQUESTS = "/requests";

    public static final String TOPIC_INNER_OSD = "/inner_osd";

    public static final String TOPIC_INNER_STATUS = "/inner_status";

    public static final String TOPIC_INNER_EVENTS = "inner_events";

    public static final String TOPIC_INNER_NOTICES = "inner_notices";


    public static final String REGEX_SN = "[A-Za-z0-9]+";

    public static final String DELIMITER = ":";


    public static final String SERVER_ID = "video_server";

    public static final String TARGET_DEDUP_KEY = SERVER_ID + DELIMITER +"target_dedup"+DELIMITER;

    public static final String NODE_CONFIG_TAG_FARMAT = "%s_%s";

    public static final String CHAIN_EXECUTE_LOCK_KEY = SERVER_ID+DELIMITER+"chain_execute_lock"+DELIMITER;


    public static final String WORKER_STATUS_KEY_PREFIX = SERVER_ID+DELIMITER+"worker:status_hash:";

    public static final String WORKER_STATUS_FIELD_WORKER_ID = "worker_id";

    public static final String WORKER_STATUS_FIELD_LAST_HEARTBEAT_TIME = "last_heartbeat_time";

    // ==================== 任务-Worker 双向关联 Key 前缀 ====================

    public static final String TASK_ASSIGN_PREFIX = SERVER_ID + DELIMITER + "task:assign:";

    public static final String WORKER_TASKS_PREFIX = SERVER_ID + DELIMITER + "worker:tasks:";

    public static final long OFFLINE_THRESHOLD_SECONDS = 30;







}
