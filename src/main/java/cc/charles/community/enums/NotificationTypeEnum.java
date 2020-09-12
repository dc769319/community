package cc.charles.community.enums;

/**
 * @author charles
 */
public enum NotificationTypeEnum {

    /**
     * 回复了问题
     */
    REPLY_QUESTION((byte) 1, "回复了问题"),

    /**
     * 回复了评论
     */
    REPLY_COMMENT((byte) 2, "回复了评论");

    private Byte type;
    private String name;

    NotificationTypeEnum(Byte type, String name) {
        this.type = type;
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String getNameByType(Byte type) {
        for (NotificationTypeEnum notificationType : NotificationTypeEnum.values()) {
            if (notificationType.getType().equals(type)) {
                return notificationType.getName();
            }
        }
        return null;
    }
}
