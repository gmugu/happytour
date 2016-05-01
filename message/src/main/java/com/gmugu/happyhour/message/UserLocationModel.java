package com.gmugu.happyhour.message;

/**
 * Created by mugu on 16-4-27.
 */
public class UserLocationModel extends BaseModel {
    private Integer userId;
    private String nikename;
    private String phone;
    private Double curLog;
    private Double curLat;

    public UserLocationModel() {
    }

    public UserLocationModel(Integer userId, String nikename, String phone, Double curLog, Double curLat) {
        this.userId = userId;
        this.nikename = nikename;
        this.phone = phone;
        this.curLog = curLog;
        this.curLat = curLat;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getCurLog() {
        return curLog;
    }

    public void setCurLog(Double curLog) {
        this.curLog = curLog;
    }

    public Double getCurLat() {
        return curLat;
    }

    public void setCurLat(Double curLat) {
        this.curLat = curLat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLocationModel that = (UserLocationModel) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (nikename != null ? !nikename.equals(that.nikename) : that.nikename != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (curLog != null ? !curLog.equals(that.curLog) : that.curLog != null) return false;
        return !(curLat != null ? !curLat.equals(that.curLat) : that.curLat != null);

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (nikename != null ? nikename.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (curLog != null ? curLog.hashCode() : 0);
        result = 31 * result + (curLat != null ? curLat.hashCode() : 0);
        return result;
    }
}
