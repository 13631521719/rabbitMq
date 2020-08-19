package com.example.rabbtmq.parent.common;


import java.io.Serializable;

public class MessageInfo implements Serializable {

    private String messageTitle;
    private String messageBody;
    private String messageHeader;

    public MessageInfo(String messageTitle, String messageBody, String messageHeader) {
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
        this.messageHeader = messageHeader;
    }

    public MessageInfo() {
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(String messageHeader) {
        this.messageHeader = messageHeader;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "messageTitle='" + messageTitle + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", messageHeader='" + messageHeader + '\'' +
                '}';
    }
}
