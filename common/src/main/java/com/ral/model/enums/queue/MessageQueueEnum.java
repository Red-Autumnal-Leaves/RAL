package com.ral.model.enums.queue;

import com.google.common.collect.ImmutableMap;
import com.ral.constants.queue.MQConstant;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;

/**
 * Created by victor on 2018/1/5.
 */
public enum MessageQueueEnum implements IndexedEnum<MessageQueueEnum>{
    DEFAULT_DLX_QUEUE(1, MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME,"DLX死信队列"),
    DEFAULT_REPEAT_TRADE_QUEUE(2,MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME,"DLX死信转发队列"),
    EXAMPLE_QUEUE(3,MQConstant.DEFAULT_EXCHANGE,MQConstant.EXAMPLE_QUEUE_NAME,"EXAMPLE测试队列"),
    RAL_OPERATION_LOG_QUEUE(4,MQConstant.DEFAULT_EXCHANGE,MQConstant.RAL_OPERATION_LOG_QUEUE_NAME,"RAL操作日志队列")
    ;

    MessageQueueEnum(int index,String exchange,String name,String desc){
        this.index = index;
        this.exchange = exchange;
        this.name = name;
        this.desc = desc;
    }

    private int index;

    private String exchange;

    private String name;

    private String desc ;

    private static final ImmutableMap<Integer, MessageQueueEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

    public static MessageQueueEnum indexOf(int index){
        return INDEXS.get(index);
    }

    public static MessageQueueEnum nameOf(String name){
        for(MessageQueueEnum queue : values()){
            if(queue.getName().equals(name)){
                return queue;
            }
        }
        return null;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
