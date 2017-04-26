package service.util;

import java.io.*;

/**
 * Created by Administrator on 2017/4/26.
 */
public class FileUtil {

    public static void main(String[] args) {
        Test();
    }



    public static void Test() {
        try{
            OutputStream o = new FileOutputStream(new File("H:\\test.jpg"));

            // 将图片转换成字符串
            File f = new File("H:\\timg.jpg");
            FileInputStream fis = new FileInputStream( f );
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();

            // 生成字符串
            String imgStr = byte2hex( bytes );
            System.out.println( imgStr);

            // 将字符串转换成二进制，用于显示图片
            // 将上面生成的图片格式字符串 imgStr，还原成图片显示
            byte[] imgByte = hex2byte( imgStr );
            InputStream in = new ByteArrayInputStream( imgByte );

            byte[] b = new byte[1024];
            int nRead = 0;
            while( ( nRead = in.read(b) ) != -1 ){
                o.write( b, 0, nRead );
            }
            o.flush();
            o.close();
            in.close();


        }catch(Exception e){
            e.printStackTrace();
        }finally{
        }
    }

    public static String byte2hex(byte[] b) // 二进制转字符串
    {
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1){
                sb.append("0" + stmp);
            }else{
                sb.append(stmp);
            }

        }
        return sb.toString();
    }

    public static byte[] hex2byte(String str) { // 字符串转二进制
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }
}
