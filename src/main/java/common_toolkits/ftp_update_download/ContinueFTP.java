package common_toolkits.ftp_update_download;

/**
 * Created by Administrator on 2018/1/24.
 */

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * FTP实现类，基于apache.commons.net
 * 断点上传下载
 * 上传下载进度汇报
 * 中文目录、文件创建，对中文的支持
 */
public class ContinueFTP {
    public FTPClient ftpClient = new FTPClient();

    public ContinueFTP() {
        //todo 将过程中使用的命令输出到控制台
//        this.ftpClient = ftpClient;
        this.ftpClient.addProtocolCommandListener(
                new PrintCommandListener(new PrintWriter(System.out))
        );
    }

    /**
     * 与FTP服务器建立连接
     * @param hostname 主机名
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return 是否连接成功
     * @throws IOException
     */
    public boolean connect(String hostname,int port,String username,String password) throws IOException {
        ftpClient.connect(hostname, port);
        ftpClient.setControlEncoding("GBK");
        if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
            if (ftpClient.login(username,password)){
                return true;
            }
        }
        return false;
    }

    /**
     *获取remote地址的文件，进行下载，支持断点续传
     * @param remote 远程下载路径
     * @param local 本地接收路径
     * @return 下载状态字符串
     * @throws IOException
     */
    public String download(String remote,String local) throws IOException {

        ftpClient.enterLocalPassiveMode();
        String result;

        FTPFile[] files = ftpClient.listFiles(new String(remote.getBytes("GBK"),"iso-8859-1"));

        //todo 如果不存在，则返回信息
        if (files.length!=1){
            System.out.println("远程文件不存在");
            return DownloadStatus.Remote_File_Noexist;
        }
        long lRemoteSize = files[0].getSize();
        File f = new File(local);
        //todo 本地存在进行断点下载
        if (f.exists()){
            long localSize = f.length();
            //todo 如果本地文件大于远程，则下载中断
            if (localSize >= lRemoteSize){
                System.out.println("本地文件大于远程文件，下载终止");
                return DownloadStatus.Local_Bigger_Remote;
            }

            //todo 断点续传
            FileOutputStream out = new FileOutputStream(f,true);
            ftpClient.setRestartOffset(localSize);
            //todo 将远程地址文件解析为输入流
            InputStream in = ftpClient.retrieveFileStream(
                    new String(remote.getBytes("GBK"),"iso-8859-1"));
            byte[] bytes = new byte[1024];
            long step = lRemoteSize/100;
            long process = localSize/step;
            int c;
            //todo 将输入流写入输出流
            while ((c = in.read(bytes)) != -1){
                out.write(bytes,0,c);
                localSize += c;
                long nowProcess = localSize /step;
                if (nowProcess > process){
                    process = nowProcess;
                    if (process % 10 == 0){
                        System.out.println("下载进度"+process);
                    }
                }
            }

            in.close();
            out.close();
            boolean isDo = ftpClient.completePendingCommand();
            if (isDo){
                result = DownloadStatus.Download_From_Break_Success;
            }else {
                result = DownloadStatus.Download_From_Break_Failed;
            }
        }else{//todo 没有之前的文件，从头开始下载，获取状态值以及输入输出处理同上
            OutputStream out = new FileOutputStream(f);
            InputStream in = ftpClient.retrieveFileStream(
                    new String(remote.getBytes("GBK"),"iso-8859-1"));
            byte[] bytes = new byte[1024];
            long step = lRemoteSize/100;
            long process = 0;
            long localSize = 0L;
            int c;
            while ((c = in.read(bytes)) != -1){
                out.write(bytes,0,c);
                localSize += c;
                long nowProcess = localSize/step;
                if (nowProcess > process){
                    process = nowProcess;
                    if (process % 10 == 0){
                        System.out.println("下载进度:"+process);
                    }
                }
            }
            in.close();
            out.close();
            boolean upNewStatus = ftpClient.completePendingCommand();
            if (upNewStatus){
                result = DownloadStatus.Download_New_Success;
            }else{
                result = DownloadStatus.Download_New_Failed;
            }
        }
        return result;
    }

    /** *//**
     * 上传文件到FTP服务器，支持断点续传
     * @param local 本地文件名称，绝对路径
     * @param remote 远程文件路径，使用/home/directory1/subdirectory/file.ext或是 http://www.guihua.org /subdirectory/file.ext 按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
     * @return 上传结果
     * @throws IOException
     */
    public String upload(String local,String remote) throws IOException {
        //设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        //设置以二进制流的方式传输
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setControlEncoding("GBK");
        String result;
        //todo 对远程目录的处理
        String remoteFileName = remote;
        if(remote.contains("/")){
            remoteFileName = remote.substring(remote.lastIndexOf("/")+1);
            //todo 创建服务器远程目录结构，创建失败直接返回
            if(createDirectory(remote, ftpClient)==UploadStatus.Create_Directory_Fail){
                return UploadStatus.Create_Directory_Fail;
            }
        }

        //todo 检查远程是否存在文件
        FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes("GBK"),"iso-8859-1"));
        if(files.length == 1){
            long remoteSize = files[0].getSize();
            File f = new File(local);
            long localSize = f.length();
            if(remoteSize==localSize){
                return UploadStatus.File_Exits;
            }else if(remoteSize > localSize){
                return UploadStatus.Remote_Bigger_Local;
            }

            //尝试移动文件内读取指针,实现断点续传
            result = uploadFile(remoteFileName, f, ftpClient, remoteSize);

            //如果断点续传没有成功，则删除服务器上文件，重新上传
            if(result == UploadStatus.Upload_From_Break_Failed){
                if(!ftpClient.deleteFile(remoteFileName)){
                    return UploadStatus.Delete_Remote_Faild;
                }
                result = uploadFile(remoteFileName, f, ftpClient, 0);
            }
        }else {
            result = uploadFile(remoteFileName, new File(local), ftpClient, 0);
        }
        return result;
    }

    /**
     * 递归创建远程服务器目录
     * @param remote
     * @param ftpClient
     * @return
     */
    public String createDirectory(String remote,FTPClient ftpClient) throws IOException {
        String status = UploadStatus.Create_Directory_Success;
        String directory = remote.substring(0,remote.lastIndexOf("/")+1);
        //todo 远程目录不存在则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/")&&!ftpClient.changeWorkingDirectory(
                new String(directory.getBytes("GBK"),"iso-8859-1"))){
            //todo 拿到下一级子目录的首尾位置
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")){
                start = 1;
            }else {
                start = 0;
            }
            end = directory.indexOf("/",start);

            //todo 循环创建目录
            while (true){
                String subDirectory = new String(
                        remote.substring(start,end).getBytes("GBK"),"iso-8859-1");
                if (!ftpClient.changeWorkingDirectory(subDirectory)){
                    //todo 创建目录，切进子目录
                    if (ftpClient.makeDirectory(subDirectory)){
                        ftpClient.changeWorkingDirectory(subDirectory);
                    }else {
                        System.out.println("创建目录失败");
                        return UploadStatus.Create_Directory_Success;
                    }
                }

                //todo 拿下一级子目录的首尾位置
                start = end +1;
                end = directory.indexOf("/",start);

                //todo 循环出口
                if (end <= start){
                    break;
                }
            }
        }
        return status;
    }

    /**
     * 断开与远程服务器的连接
     * @throws IOException
     */
    public void disconnect() throws IOException {
        if (ftpClient.isConnected()){
            ftpClient.disconnect();
        }
    }

    /**
     * 上传文件到服务器，新上传和断点续传
     * @param remoteFile
     * @param localFile
     * @param ftpClient
     * @param remoteSize
     * @return
     */
    public String uploadFile(String remoteFile,File localFile,FTPClient ftpClient,long remoteSize) throws IOException {
        String status;
        long step = localFile.length()/100;
        long process=0;
        long localReadBytes = 0L;
        RandomAccessFile raf = new RandomAccessFile(localFile,"r");
        //todo 远程文件包装成输出流
        OutputStream out = ftpClient.appendFileStream(new String(
                remoteFile.getBytes("GBK"),"iso-8859-1"));

        //todo 断点续传
        if (remoteSize >0){
            //todo 设置远程文件开始位置
            ftpClient.setRestartOffset(remoteSize);
            process = remoteSize/step;
            raf.seek(remoteSize);
            localReadBytes = remoteSize;
        }
        byte[] bytes = new byte[1024];
        int c;
        //todo 将localFile 读取到out流里面
        while ((c = raf.read(bytes)) != -1){
            out.write(bytes,0,c);
            localReadBytes += c;
            if (localReadBytes/step != process){
                process = localReadBytes /step;
                //todo 汇报上传进度
                System.out.println("上传进度:"+process);
            }
        }

        out.flush();
        raf.close();
        out.close();

        boolean result = ftpClient.completePendingCommand();
        if (remoteSize > 0){
            status = result?UploadStatus.Upload_From_Break_Success:UploadStatus.Upload_From_Break_Failed;
        }else {
            status = result?UploadStatus.Upload_New_File_Success:UploadStatus.Upload_New_File_Failed;
        }
        return status;
    }

    public static void main(String[] args) {
        ContinueFTP myFtp = new ContinueFTP();
        try {
            myFtp.connect("192.168.21.181", 21, "nid", "123");
//          myFtp.ftpClient.makeDirectory(new String("电视剧".getBytes("GBK"),"iso-8859-1"));
//          myFtp.ftpClient.changeWorkingDirectory(new String("电视剧".getBytes("GBK"),"iso-8859-1"));
//          myFtp.ftpClient.makeDirectory(new String("走西口".getBytes("GBK"),"iso-8859-1"));
//          System.out.println(myFtp.upload("http://www.5a520.cn /yw.flv", "/yw.flv",5));
//          System.out.println(myFtp.upload("http://www.5a520.cn /走西口24.mp4","/央视走西口/新浪网/走西口24.mp4"));
            System.out.println(myFtp.download("/央视走西口/新浪网/走西口24.mp4", "E:\\走西口242.mp4"));
            myFtp.disconnect();
        } catch (IOException e) {
            System.out.println("连接FTP出错："+e.getMessage());
        }
    }
}

