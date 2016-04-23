package com.gmugu.happyhour.message;


/**
 * 用户信息
 * Created by mugu on 16-4-3 下午5:24.
 */
public class UserInfoModel extends BaseModel implements Cloneable {


    public enum UserType {
        GUIDE,
        PASSENGER
    }

    //用户ID
    private Integer userId;
    //头像，返回头像下载路径
    private String portrait;
    //头像文件校验值,为了判断服务器图片与本地缓存是否一致
    private String portraitCheckCode;
    //昵称
    private String nickname;
    //性别
    private String gender;
    //身高(单位: cm)
    private Integer height;
    //体重(单位: kg)
    private Float weight;
    //生日,返回时间戳
    private Long birthday;
    //城市
    private String city;
    //联系方式
    private String phone;
    //用户类型
    private UserType userType;


    public UserInfoModel() {
    }

    public UserInfoModel(Integer userId, String portrait, String portraitCheckCode, String nickname, String gender, Integer height, Float weight, Long birthday, String city) {
        this.userId = userId;
        this.portrait = portrait;
        this.portraitCheckCode = portraitCheckCode;
        this.nickname = nickname;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.city = city;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPortraitCheckCode() {
        return portraitCheckCode;
    }

    public void setPortraitCheckCode(String portraitCheckCode) {
        this.portraitCheckCode = portraitCheckCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "userId='" + userId + '\'' +
                ", portrait='" + portrait + '\'' +
                ", portraitCheckCode='" + portraitCheckCode + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", birthday=" + birthday +
                ", city='" + city + '\'' +
                ", userType=" + userType +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public UserInfoModel clone() {
        UserInfoModel newModel = new UserInfoModel();
        newModel.setNickname(nickname);
        newModel.setPortrait(portrait);
        newModel.setBirthday(birthday);
        newModel.setCity(city);
        newModel.setGender(gender);
        newModel.setHeight(height);
        newModel.setPortraitCheckCode(portraitCheckCode);
        newModel.setUserId(userId);
        newModel.setWeight(weight);
        newModel.setPhone(phone);
        newModel.setUserType(userType);
        return newModel;
    }
}
