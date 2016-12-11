package com.gmugu.happyhour.message;


/**
 * 用户信息
 * Created by mugu on 16-4-3 下午5:24.
 */
public class UserInfoModel extends BaseModel implements Cloneable {


    public enum UserType {
        GUIDE,
        PASSENGER,
        ADMIN
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfoModel that = (UserInfoModel) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (portrait != null ? !portrait.equals(that.portrait) : that.portrait != null) return false;
        if (portraitCheckCode != null ? !portraitCheckCode.equals(that.portraitCheckCode) : that.portraitCheckCode != null)
            return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return userType == that.userType;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (portrait != null ? portrait.hashCode() : 0);
        result = 31 * result + (portraitCheckCode != null ? portraitCheckCode.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }

    @Override
    public UserInfoModel clone() {
        UserInfoModel newModel = new UserInfoModel();
        newModel.setUserId(userId);
        newModel.setPortrait(portrait);
        newModel.setPortraitCheckCode(portraitCheckCode);
        newModel.setNickname(nickname);
        newModel.setGender(gender);
        newModel.setHeight(height);
        newModel.setWeight(weight);
        newModel.setBirthday(birthday);
        newModel.setCity(city);
        newModel.setPhone(phone);
        newModel.setUserType(userType);
        return newModel;
    }
}
