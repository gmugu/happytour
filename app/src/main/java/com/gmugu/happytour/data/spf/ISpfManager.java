package com.gmugu.happytour.data.spf;


import com.gmugu.happyhour.message.UserInfoModel;

import java.io.File;
import java.util.List;

import okhttp3.Cookie;

/**
 * 数据持久化接口
 * <p/>
 * Created by mugu on 16-3-29 下午8:26.
 */
public interface ISpfManager {

    /**
     * 保存用户名和密码
     *
     * @param username
     * @param passwd
     * @throws SpfManagetException
     */
    void saveOrUpdateUsernameAndPasswd(String username, String passwd) throws SpfManagetException;

    /**
     * 获取用户名和密码，找不到则抛出异常
     *
     * @return
     * @throws SpfManagetException
     */
    String[] getUsernameAndPasswd() throws SpfManagetException;

    List<Cookie> getCookies(String host) throws SpfManagetException;

    void saveCookies(String host, List<Cookie> cookies) throws SpfManagetException;

    void saveOrUpdateUserInfo(String userId, UserInfoModel userInfoModel) throws SpfManagetException;

    UserInfoModel getUserInfo(String userId) throws SpfManagetException;

    String saveOrUpdateHeadimg(String userId, byte[] img) throws SpfManagetException;

    String saveOrUpdateHeadimg(String userId, String imgPath) throws SpfManagetException;

    byte[] getHeadimg(String userId) throws SpfManagetException;

    /**
     * 将数据保存到缓存文件夹,返回文件路径,文件将在退出后被删除
     *
     * @param data
     * @return
     * @throws SpfManagetException
     */
    File saveToCacheDir(byte[] data) throws SpfManagetException;

    /**
     * 将文件保存到缓存文件夹,返回文件路径,文件将在退出后被删除
     *
     * @param data
     * @return
     * @throws SpfManagetException
     */
    File saveToCacheDir(File data) throws SpfManagetException;

    /**
     * 将数据保存到File文件夹,返回文件路径
     *
     * @param path 子目录
     * @param name
     * @param data @return
     * @throws SpfManagetException
     */
    String saveToFileDir(String path, String name, String suffit, byte[] data) throws SpfManagetException;

    /**
     * 将文件保存到File文件夹,返回文件路径
     *
     * @param path 子目录
     * @param name
     * @param data @return
     * @throws SpfManagetException
     */
    String saveToFileDir(String path, String name, String suffix, File data) throws SpfManagetException;

}
