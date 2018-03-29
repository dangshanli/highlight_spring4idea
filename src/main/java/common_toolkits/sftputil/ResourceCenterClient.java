package common_toolkits.sftputil;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luzj
 * @description: 资源中心传输业务代码
 * @date 2018/3/28
 */
public class ResourceCenterClient {
    /*public static final String host = "10.124.164.106";
    public static final String port = "22";
    public static final String userName = "fzbjf";
    public static final String password = "fzbjftest";*/

    public static final String host = "192.168.110.156";
    public static final String port = "7788";
    public static final String userName = "root";
    public static final String password = "123";
    private ChannelSftp sftp; //jsch的sftp通道连接
    private SFTPChannel sftpChannel;//自定义通道连接
    private String result = "1";

    public ResourceCenterClient() {
        this.sftpChannel = new SFTPChannel();
    }

    /**
     * 获取连接资源中心的channel
     *
     * @return
     * @throws JSchException
     */
    public void getConnection() throws JSchException {
        //todo 连接参数
        Map<String, String> details = new HashMap<>();
        details.put(SFTPConstant.SFTP_HOST, host);
        details.put(SFTPConstant.SFTP_PORT, port);
        details.put(SFTPConstant.SFTP_USERNAME, userName);
        details.put(SFTPConstant.SFTP_PASSWORD, password);

        this.sftp = sftpChannel.getChannel(details, 60000);
    }

    /**
     * 关闭连接
     */
    public void closeSftp() {
        if (sftp != null)
            sftp.disconnect();
        if (sftpChannel != null)
            sftpChannel.closeChannel();
    }

    /**
     * 从scr目录传输文件到des目录
     *
     * @param scr      源文件夹
     * @param des      目标文件夹
     * @param fileName 带传输的文件名或者文件名部分关键词
     * @return 传输结果：result
     * 1成功，
     * 0失败，
     * -1传送失败，
     * -2本地目录指定错误，
     * -3指定目录下没要传送的文件，
     * -4ftp连接失败
     * -5重命名失败
     */
    public String uploadDirectory(String scr, String des, String fileName) {

        File file = new File(scr);
        //todo 指定目录不存在
        if (!file.exists()) {
            result = "-2";
            return result;
        }

        //todo 如果是文件，则直接传输
        if (file.isFile())
            uploadFile(file, des);

        //todo 如果是目录则，遍历目录下文件传输
        File[] files;
        if (file.isDirectory()) {
            List<File> temps = new ArrayList<>();
            files = file.listFiles();
            result = "-3";

            //todo 遍历文件，传输符合条件文件
            for (File f : files) {
                if (f.getName().contains(fileName)) {
                    System.err.println("scr:"+f.getPath());
                    if (f.isFile()) {
                        result = uploadFile(f, des);
                        temps.add(f);
                    } else {
                        result = uploadDirectory(f.getPath(), des, fileName);
                    }
                }
            }

        }
        return result;
    }

    /**
     * 传输单文件到目标目录
     *
     * @param scrFile 源文件
     * @param des     目标文件夹
     * @return
     */
    private String uploadFile(File scrFile, String des) {
        String result = "1";
        String remotePath = des + "/" + scrFile.getName() + ".TMP";
        String renamePath = des + "/"+ scrFile.getName();
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(scrFile));
            System.err.println("remotePath: "+remotePath);
            System.err.println("renamePath: "+renamePath);
            sftp.put(bis, remotePath, ChannelSftp.OVERWRITE);
        } catch (FileNotFoundException e) {
            result = "-3";
            e.printStackTrace();
        } catch (SftpException e) {
            result = "-1";
            e.printStackTrace();
        } finally {
            //todo 文件重命名
            if (result == "1") {
                try {
                    sftp.rename(remotePath, renamePath);
                } catch (SftpException e) {
                    result = "-5";
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    private static void testResourceCenter() {
        String scr = "C:\\sftp_test\\crm";
        String fileName = "NORES201705";
        String des = "/home/luzj";
        ResourceCenterClient client = new ResourceCenterClient();
        String res = "1";
        try {
            client.getConnection();
            res = client.uploadDirectory(scr, des, fileName);
            client.closeSftp();
        } catch (JSchException e) {
            e.printStackTrace();
        }finally {
            client.closeSftp();
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
       /* String scr = "C:\\sftp_test\\crm";
        File file = new File(scr);
        if (file.isFile())
            System.out.println(file.getName());
        else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.println("path: "+f.getPath());
                System.out.println("name: "+f.getName());
            }
        }*/

       testResourceCenter();

    }


}
