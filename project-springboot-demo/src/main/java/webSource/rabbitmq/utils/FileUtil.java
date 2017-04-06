package webSource.rabbitmq.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/27
 * @description
 */
public class FileUtil {
    public static void saveFile(byte[] data,String filePath) throws IOException {
        InputStream is = new ByteArrayInputStream(data);
        OutputStream fos = new FileOutputStream(filePath);
        //这里对is进行赋值，略
        //...
        // 文件输出流fos
        // openFile()为自定义函数，判断文件是否存在等（略）
        // 将输入流is写入文件输出流fos中
        int ch = 0;
        try {
            while ((ch = is.read()) != -1) {
                fos.write(ch);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            //关闭输入流等（略）
            fos.close();
            is.close();
        }
    }

    public static List<File> getFiles(String path) {
        List<File> filess =new ArrayList<File>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        getFiles(file2.getAbsolutePath());
                    } else {
                        filess.add(file2);
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return filess;
    }

    public static String getFileString(File file){
        StringBuffer data=new StringBuffer();
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null)
            {
                data.append(str);
            }
            in.close();

        }
        catch (IOException e)
        {
            e.getStackTrace();
        }
        return data.toString();
    }

    //获得指定文件的byte数组
    public static byte[] getBytes(File file){
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
