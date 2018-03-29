package common_toolkits.sftputil;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luzj
 * @description:
 * @date 2018/3/18
 */
public class SftpTest {
    private final String host = "192.168.110.156",port = "7788",username = "root",passwd = "123";
    private final String  host2 = "192.168.10.233",port2 = "22",username2 = "luzj",passwd2="123";

    /**
     * 上传
     * @param src
     * @param tmp
     * @param dst
     */
    public void upload(String src,String tmp,String dst){
        Map<String,String> sftpDetails = getSftpDetails();

       SFTPChannel channel = new SFTPChannel();
        ChannelSftp sftp = null;
        try {
            sftp = channel.getChannel(sftpDetails,60000);
        } catch (JSchException e) {
            e.printStackTrace();
        }

        File file = new File(src);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            sftp.put(bis,tmp,ChannelSftp.OVERWRITE);
            sftp.rename(tmp,dst);
        } catch (SftpException e) {
            e.printStackTrace();
        }finally {
            channel.closeChannel();
        }
    }


    /**
     *
     * @param src 远程服务器的目录地址
     * @param dst 本地下载接受地址
     */
    public void download(String src,String dst){
        Map<String,String> sftpDetails = getSftpDetails();
        SFTPChannel channel = new SFTPChannel();
        ChannelSftp sftp = null;

        try {
            sftp = channel.getChannel(sftpDetails,60000);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(dst));
            sftp.get(src,bos,new MyProgressMonitor());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }finally {
            sftp.quit();
            channel.closeChannel();
        }
    }

    /**
     * 获取sftp配置参数
     * @return
     */
    private Map getSftpDetails(){
        Map<String,String> sftpDetails = new HashMap<>();
        sftpDetails.put(SFTPConstant.SFTP_HOST,host);
        sftpDetails.put(SFTPConstant.SFTP_PORT,port);
        sftpDetails.put(SFTPConstant.SFTP_USERNAME,username);
        sftpDetails.put(SFTPConstant.SFTP_PASSWORD,passwd);
        return sftpDetails;
    }

    public static void main(String[] args) {
        String src = "C:\\sftp_test\\download\\iNodeSetup0.7z";
        String temp = "/home/luzj/iNodeSetup0.TMP";
        String dst = "/home/luzj/iNodeSetup0.7z";
//        new SftpTest().upload(src,temp,dst);
        new SftpTest().download(dst,src);
    }


}
