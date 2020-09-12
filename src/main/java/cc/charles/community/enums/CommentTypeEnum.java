package cc.charles.community.enums;

public enum CommentTypeEnum {

    QUESTION((byte) 1),
    COMMENT((byte) 2);

    CommentTypeEnum(Byte type) {
        this.type = type;
    }

    private Byte type;

    public Byte getType() {
        return this.type;
    }

    public static Boolean exist(Byte type) {
        for (CommentTypeEnum commentType : CommentTypeEnum.values()) {
            if (commentType.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
