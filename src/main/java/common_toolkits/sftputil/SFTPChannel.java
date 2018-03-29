package common_toolkits.sftputil;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;

/**
 * @author luzj
 * @description:
 * 1.SFTP通道连接、释放的工具
 * 2.ChannelSftp是Jsch的核心实现，他是sftp传输的起点
 * @date 2018/3/18
 */
public class SFTPChannel {
    Session session = null;
    Channel channel = null;

    public static final Logger logger = LoggerFactory.getLogger(SFTPChannel.class);

    /**
     *获取sftp传输用Channel，基本工具类
     * @param sftpDetails ftp服务器的：ip 端口 用户名  密码 远程地址
     * @param timeout 超时时间
     * @return sftp传输通道
     */
    public ChannelSftp getChannel(Map<String,String> sftpDetails,int timeout) throws JSchException {
        //todo sftp服务器基本参数
        String host = sftpDetails.get(SFTPConstant.SFTP_HOST);
        String ftpPort = sftpDetails.get(SFTPConstant.SFTP_PORT);
        String userName = sftpDetails.get(SFTPConstant.SFTP_USERNAME);
        String password = sftpDetails.get(SFTPConstant.SFTP_PASSWORD);

        int port = SFTPConstant.SFTP_DEFAULT_PORT;//23
        if (ftpPort != null && !ftpPort.equals(""))
            port = Integer.valueOf(ftpPort);

        //todo 创建会话连接
        JSch jSch = new JSch();
        session = jSch.getSession(userName,host,port);
        logger.info("session created!");

        if (password != null)
            session.setPassword(password);
        else
            logger.info("no password!");
        Properties config = new Properties();
        config.put("StrictHostKeyChecking","no");
        session.setConfig(config);
        session.setTimeout(timeout);
        session.connect();
        logger.info("session connect!");

        //todo 打开传输Channel
        channel = session.openChannel("sftp");
        channel.connect();//建立通道连接
        logger.info("channel connect");

        logger.debug("connect successfully to host:"+host+",port:"+port+",username:"+userName
        +",channel:"+channel);
        return (ChannelSftp) channel;
    }

    /**
     * 关闭会话和通道
     */
    public void closeChannel(){
        if (channel != null)
            channel.disconnect();
        if (session != null)
            session.disconnect();
    }
}
