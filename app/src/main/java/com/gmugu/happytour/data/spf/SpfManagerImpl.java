package com.gmugu.happytour.data.spf;

import android.content.Context;

import com.gmugu.happytour.comment.data.DataKeeper;
import com.gmugu.happytour.comment.io.FileUtils;
import com.gmugu.happyhour.message.UserInfoModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;

/**
 * Created by mugu on 16-3-29 下午10:07.
 */
public class SpfManagerImpl implements ISpfManager {

    private DataKeeper dataKeeper;
    private Context context;
    private File cacheDir;
    private File fileDir;
    private File imageDir;//放在缓存目录下
    private File cookiesDir;//放在File目录下

    public SpfManagerImpl(Context context) {

        this.context = context;
        cacheDir = context.getCacheDir();
        fileDir = context.getFilesDir();
        imageDir = new File(cacheDir.getAbsolutePath() + File.separator + "image");
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        cookiesDir = new File(fileDir.getAbsolutePath() + File.separator + "cookies");
        if (!cookiesDir.exists()) {
            cookiesDir.mkdirs();
        }
        dataKeeper = new DataKeeper(context, "paole");

    }

    @Override
    public void saveOrUpdateUsernameAndPasswd(String username, String passwd) throws SpfManagetException {
        dataKeeper.putString("username", username);
        dataKeeper.putString("passwd", passwd);
    }

    @Override
    public String[] getUsernameAndPasswd() throws SpfManagetException {
        String[] re = new String[2];
        re[0] = dataKeeper.getString("username", null);
        re[1] = dataKeeper.getString("passwd", null);
        if (re[0] == null || re[1] == null) {
            throw new SpfManagetException("找不到用户名和密码");
        }
        return re;
    }

    //key为host，value为cookie集合
    private Map<String, List<Cookie>> cookiesCache = null;

    /**
     * 判断cookie的有效期
     *
     * @param cookies
     * @return
     */
    private List<Cookie> screenCookies(List<Cookie> cookies) {
        List<Cookie> re = new ArrayList<>();
        for (Cookie cookie : cookies) {
            if (cookie.expiresAt() > System.currentTimeMillis()) {
                re.add(cookie);
            }
        }
        return re;
    }

    @Override
    public List<Cookie> getCookies(String host) throws SpfManagetException {

        if (cookiesCache == null) {
            cookiesCache = new HashMap<>();
            try {
                File[] files = cookiesDir.listFiles();
                for (File f : files) {
                    FileReader reader = new FileReader(f);
                    StringBuilder sb = new StringBuilder();
                    char[] buf = new char[1024];
                    int read;
                    while ((read = reader.read(buf)) != -1) {
                        sb.append(buf, 0, read);
                    }

                    List<Cookie> cookies = new Gson().fromJson(sb.toString(), new TypeToken<List<Cookie>>() {
                    }.getType());
                    cookies = screenCookies(cookies);
                    if (cookies != null && !cookies.isEmpty()) {
                        cookiesCache.put(cookies.get(0).domain(), cookies);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SpfManagetException(e.getMessage());
            }

        }
        return cookiesCache.get(host);
    }

    @Override
    public void saveCookies(String host, List<Cookie> cookies) throws SpfManagetException {
        if (cookiesCache == null) {
            cookiesCache = new HashMap<>();
        }
        FileWriter writer = null;
        try {
            List<Cookie> old = cookiesCache.get(host);
            if (old == null) {
                old = cookies;
            } else {
                List<Cookie> newlist = new ArrayList<>();
                OUT:
                for (Cookie c1 : old) {
                    for (Cookie c2 : cookies) {
                        if (c1.name().equals(c2.name())) {
                            newlist.add(c2);
                            continue OUT;
                        }
                    }
                    newlist.add(c1);
                }
                old = newlist;
            }
            cookiesCache.put(host, old);
            writer = new FileWriter(cookiesDir.getAbsoluteFile() + File.separator + "Cookie_" + host);
            writer.write(new Gson().toJson(old));
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SpfManagetException(e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void saveOrUpdateUserInfo(String userId, UserInfoModel userInfoModel) throws SpfManagetException {
        File userDir = getUserDir(userId);
        if (userDir == null) {
            throw new SpfManagetException("无法获取用户目录");
        }
        File userInfoFile = new File(userDir, "userInfo");
        try {
            String s = new Gson().toJson(userInfoModel);
            FileUtils.writeStringToFile(userInfoFile, s, "utf-8");
        } catch (Exception e) {
            throw new SpfManagetException(e.getMessage());
        }
    }

    @Override
    public UserInfoModel getUserInfo(String userId) throws SpfManagetException {
        File userDir = getUserDir(userId);
        if (userDir == null) {
            throw new SpfManagetException("无法获取用户目录");
        }
        File userInfoFile = new File(userDir, "userInfo");
        try {
            String s = FileUtils.readFileToString(userInfoFile, "utf-8");
            UserInfoModel userInfoModel = new Gson().fromJson(s, UserInfoModel.class);
            return userInfoModel;
        } catch (Exception e) {
            throw new SpfManagetException(e.getMessage());
        }
    }

    @Override
    public String saveOrUpdateHeadimg(String userId, byte[] img) throws SpfManagetException {
        File userDir = getUserDir(userId);
        if (userDir == null) {
            throw new SpfManagetException("无法获取用户目录");
        }
        File userHeadimgFile = new File(userDir, "userHeadimg.png");
        try {
            FileUtils.writeByteArrayToFile(userHeadimgFile, img, false);
            return userHeadimgFile.getAbsolutePath();
        } catch (IOException e) {
            throw new SpfManagetException(e.getMessage());
        }
    }

    @Override
    public String saveOrUpdateHeadimg(String userId, String imgPath) throws SpfManagetException {
        File userDir = getUserDir(userId);
        if (userDir == null) {
            throw new SpfManagetException("无法获取用户目录");
        }
        try {
            File destFile = new File(userDir, "userHeadimg.png");
            File srcFile = new File(imgPath);
            FileUtils.copyFile(srcFile, destFile);
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            throw new SpfManagetException(e.getMessage());
        }
    }

    @Override
    public byte[] getHeadimg(String userId) throws SpfManagetException {
        File userDir = getUserDir(userId);
        if (userDir == null) {
            throw new SpfManagetException("无法获取用户目录");
        }
        File userHeadimgFile = new File(userDir, "userHeadimg.png");
        try {
            return FileUtils.readFileToByteArray(userHeadimgFile);
        } catch (IOException e) {
            throw new SpfManagetException(e.getMessage());
        }
    }

    @Override
    public File saveToCacheDir(byte[] data) throws SpfManagetException {
        try {
            File file = new File(cacheDir, "cache_" + System.currentTimeMillis());
            file.deleteOnExit();
            FileUtils.writeByteArrayToFile(file, data, false);
            return file;
        } catch (Exception e) {
            throw new SpfManagetException(e.getMessage());
        }
    }

    @Override
    public File saveToCacheDir(File data) throws SpfManagetException {
        try {
            File file = new File(cacheDir, "cache_" + System.currentTimeMillis());
            file.deleteOnExit();
            FileUtils.copyFile(data, file);
            return file;
        } catch (Exception e) {
            throw new SpfManagetException(e.getMessage());
        }
    }

    @Override
    public String saveToFileDir(String path, String name, String suffix, byte[] data) throws SpfManagetException {
        try {
            File file = new File(fileDir + File.separator + path, name + "." + suffix);
            FileUtils.writeByteArrayToFile(file, data, false);
            return file.getAbsolutePath();
        } catch (Exception e) {
            throw new SpfManagetException(e.getMessage());
        }
    }

    @Override
    public String saveToFileDir(String path, String name, String suffix, File data) throws SpfManagetException {
        try {
            File file = new File(fileDir + File.separator + path, name + "." + suffix);
            FileUtils.copyFile(data, file);
            return file.getAbsolutePath();
        } catch (Exception e) {
            throw new SpfManagetException(e.getMessage());
        }
    }

    /**
     * 获得用户目录
     *
     * @param userId
     * @return
     */
    private File getUserDir(String userId) {
        File userDir = new File(fileDir + File.separator + userId);
        if (userDir.exists() && userDir.isFile()) {
            if (!userDir.delete()) {
                return null;
            }
        }
        if (!userDir.exists()) {
            if (!userDir.mkdirs()) {
                return null;
            }
        }
        return userDir;
    }

}
