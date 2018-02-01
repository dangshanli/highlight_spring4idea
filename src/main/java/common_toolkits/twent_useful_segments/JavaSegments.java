package common_toolkits.twent_useful_segments;

import java.io.*;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description: java中常用的20个代码片段，前7个
 * @date 2018/1/31 15:12
 */
public class JavaSegments {

    /**
     * 数值转换成字符串，
     *
     * @param x int，
     * @return
     */
    public String transferIntToStr(int x) {
        //todo valueOf可以转换double、float、Boolean等类型
        return String.valueOf(x);
    }

    /**
     * 整字符串转成整数
     *
     * @param str
     * @return
     */
    public int transferStrToInt(String str) {
        //todo 字符串转double类型
        /*double d = Double.parseDouble("2.6778");
        System.out.println(d*10);*/

        return Integer.parseInt(str);
    }

    /**
     * 通过字符流向本地文本文件追加字符串
     *
     * @param fileName 本地文件目录名
     */
    public void appendStrToFile(String fileName) {
        BufferedWriter out = null;
        try {
            //todo 获取磁盘文件的字符写入流
            out = new BufferedWriter(new FileWriter(fileName, true));
            out.write("\n我爱萝莉");
        } catch (IOException e) {
            System.err.println("打开文件流异常");
            e.printStackTrace();
        } finally {
            this.printMethodTrace();
            if (out != null)
                try {
                    out.close();
                    System.out.println("流关闭写入完毕");
                } catch (IOException e) {
                    System.err.println("文件流未正常关闭");
                    e.printStackTrace();
                }
        }
    }

    /**
     * 获取当前运行的方法名，这里是"getCurrentMethodName"
     * 谁掉下面的代码片段，显示谁的方法签名
     *
     * @return
     */
    public String getCurrentMethodName() {
        //todo 获取当前线程方法栈最上层的方法名
        return Thread.currentThread().getStackTrace()[1].
                getMethodName();
    }

    /**
     * 打印方法调用栈
     */
    private void printMethodTrace() {
        int i = 0;
        //todo 遍历当前线程方法栈 栈元素集
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            System.out.println("method" + (i++) + ": " + element.getMethodName());
        }
    }

    /**
     * 将格式化字符串转换成Date日期
     *
     * @param myDate
     */
    public void transferStrToDate(String myDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(myDate);
            System.out.println(date.toString());
        } catch (ParseException e) {
            System.err.println("传递的格式不对");
            e.printStackTrace();
        }
    }

    /**
     * 连接oracle数据库，执行查询
     *
     * @throws IOException
     * @throws SQLException
     */
    public void connectOracle() throws IOException, SQLException {
        String driveClass = "oracle.jdbc.driver.OracleDriver";
        Connection connection = null;

        FileInputStream in = new FileInputStream("src/main/resource/oracle.properties");
        Properties props = new Properties();
        String url = null;
        String userName = null;
        String password = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //todo 读取输入流中的文件属性，连接oracle
            props.load(in);
            url = props.getProperty("db.url");
            userName = props.getProperty("db.user");
            password = props.getProperty("db.password");
            Class.forName(driveClass);
            connection = DriverManager.getConnection(url, userName, password);

            //todo 执行查询
            String sql = "select syadate from dual";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //do something
            }
        } catch (IOException e) {
            System.err.println("oracle登录信息properties文件找不到");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("没安装oracle的驱动类");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("数据库连接错误");
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            connection.close();
            in.close();
        }
    }

    /**
     * 将util.date转成sql.date
     * sql.Date是给数据库date类型专用的，是util的子类
     */
    public void transferDate() {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    }

    /**
     * 使用nio API进行快速拷贝，支持大文件拷贝
     * @param from
     * @param to
     * @throws IOException
     */
    public void fileCopy(String from,String to) throws IOException {
        FileChannel inChannel = new FileInputStream(from).getChannel();
        FileChannel outChannel = new FileOutputStream(to).getChannel();
        try {
            //todo 最大单次传输大小
            int maxCount = (64 * 1024 * 1024) - (32 * 1024);
            long size = inChannel.size();
            long position = 0;
            long step = size /100;
            long process = 0;
            while (position < size) {
                position += inChannel.transferTo(position, maxCount, outChannel);
               long a = (position / step > process)?(position/step):process;
                if (a > process)
                    System.out.println("process: "+(process = a)+"%");
            }
        }finally {
            if (inChannel != null)
                inChannel.close();
            if (outChannel != null)
                outChannel.close();
        }
    }

    public static void main(String[] args) {
        JavaSegments javaSegments = new JavaSegments();
//        System.out.println(javaSegments.transferIntToStr(2222));
//        System.out.println(javaSegments.transferStrToInt("234.567"));
//        javaSegments.appendStrToFile("src/main/resource/oracle.properties");
//        System.out.println(javaSegments.getCurrentMethodName());
//        String name = Thread.currentThread().getStackTrace()[1].getMethodName();
//        System.out.println(name);
//        javaSegments.transferStrToDate("2019-11-4");

        try {
            String from = "F:\\迅雷下载\\Python_kfsz_pdf.zip";
            String to = "F:\\file_copy_python.zip";
            javaSegments.fileCopy(from,to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
