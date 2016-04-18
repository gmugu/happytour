package com.gmugu.happytour.comment.assist;

/**
 * 校验器
 * <p/>
 * Created by mugu on 16-4-11 下午9:23.
 */
public class Checker {

    /**
     * 检验两字符串是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isEquals(String s1, String s2) {
        return !isNull(s1) && s1.equals(s2);
    }

    /**
     * 检验对象是否为Null
     *
     * @param o
     * @return
     */
    public static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * 检验对象是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || str.length() == 0;
    }

    /**
     * 校验邮箱格式是否有效
     *
     * @param email
     * @return
     */
    public static boolean isEMail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    }

    public static boolean isEffectiveUsername(String username) {
        return !isNull(username) && username.length() > 0;
    }

    public static boolean isEffectivePasswd(String passwd) {
        if (isEmpty(passwd)) {
            return false;
        }
        if (passwd.length() < 6 || passwd.length() > 20) {
            return false;
        }
        return true;
    }

}
