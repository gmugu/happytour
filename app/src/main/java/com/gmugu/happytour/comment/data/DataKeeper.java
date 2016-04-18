package com.gmugu.happytour.comment.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.gmugu.happytour.comment.assist.Log;
import com.gmugu.happytour.comment.data.cipher.Cipher;
import com.gmugu.happytour.comment.utils.ByteUtil;
import com.gmugu.happytour.comment.utils.HexUtil;


/**
 * @author MaTianyu
 * @date 14-7-10
 */
public class DataKeeper {
    private SharedPreferences sp;
    public static final String KEY_PK_HOME = "msg_pk_home";
    public static final String KEY_PK_NEW = "msg_pk_new";
    private static final String TAG = DataKeeper.class.getSimpleName();

    public DataKeeper(Context context, String fileName) {
        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    /**
     * *************** get ******************
     */

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return sp.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    public Object get(String key) {
        return get(key, (Cipher) null);
    }

    public Object get(String key, Cipher cipher) {
        try {
            String hex = getString(key, null);
            if (hex == null) return null;
            byte[] bytes = HexUtil.decodeHex(hex.toCharArray());
            if (cipher != null) bytes = cipher.decrypt(bytes);
            Object obj = ByteUtil.byteToObject(bytes);
            Log.i(TAG, key + " get: " + obj);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * *************** put ******************
     */
    public void put(String key, Object ser) {
        put(key, ser, null);
    }

    public void put(String key, Object ser, Cipher cipher) {
        try {
            Log.i(TAG, key + " put: " + ser);
            if (ser == null) {
                sp.edit().remove(key).commit();
            } else {
                byte[] bytes = ByteUtil.objectToByte(ser);
                if (cipher != null) bytes = cipher.encrypt(bytes);
                putString(key, HexUtil.encodeHexStr(bytes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putString(String key, String value) {
        if (value == null) {
            sp.edit().remove(key).commit();
        } else {
            sp.edit().putString(key, value).commit();
        }
    }


    public void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public void putFloat(String key, float value) {
        sp.edit().putFloat(key, value).commit();
    }

    public void putLong(String key, long value) {
        sp.edit().putLong(key, value).commit();
    }

    public void putInt(String key, int value) {
        sp.edit().putInt(key, value).commit();
    }


    /**
     * *************************************
     */
    public boolean contains(String key) {
        return sp.contains(key);
    }

}
