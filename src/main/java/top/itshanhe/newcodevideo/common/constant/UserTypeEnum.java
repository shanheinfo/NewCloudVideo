package top.itshanhe.newcodevideo.common.constant;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/19
 */
public enum UserTypeEnum {
    USER("USER"),
    ADMIN("ADMIN");
    
    private final String value;
    
    UserTypeEnum(String value) {
        this.value = value;
    }
    
    public static String getValue(UserTypeEnum userType) {
        return userType.value;
    }
}
