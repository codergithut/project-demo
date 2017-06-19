package webSource.util;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/6/16
 * @description
 */
public class TestBean {
    private String name;

    private String password;

    public String getName() {
        // 密钥的种子，可以是任何形式，本质是字节数组
        String strKey = "lttclaw";
        // 密钥数据
        byte[] rawKey = AESUtil.getRawKey(strKey.getBytes());
        System.out.println("this is " + name);
       // String decryptedPwd = AESUtil.decrypt(hexStringToBytes(name), rawKey);
        return "hh";
    }

    public void setName(String name) {
        // 密钥的种子，可以是任何形式，本质是字节数组
        String strKey = "lttclaw";
        // 密钥数据
        byte[] rawKey = AESUtil.getRawKey(strKey.getBytes());
       // byte[] encode = AESUtil.encrypt(rawKey, name);
       // String encryptedPwd = byte2hex(encode);
        this.name = "hh";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }








    private static String byte2hex(byte[] byteArray)
    {
        if ((byteArray == null) || (byteArray.length < 1))
        {
            throw new IllegalArgumentException(
                    "this byteArray must not be null or empty");
        }
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; ++i)
        {
            if ((byteArray[i] & 0xFF) < 16)
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return hexString.toString().toLowerCase();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
