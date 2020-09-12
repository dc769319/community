package cc.charles.community.enums;

/**
 * @author charles
 */
public enum NotificationStatusEnum {
    /**
     * 未读
     */
    UNREAD((byte) 0),

    /**
     * 已读
     */
    READ((byte) 1);

    private Byte status;

    NotificationStatusEnum(Byte status) {
        this.status = status;
    }

    public Byte getStatus() {
        return status;
    }
}
