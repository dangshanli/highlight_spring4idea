package java_web.my_datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

@Configuration
@PropertySource("classpath:src/main/resource/db.properties")
public class JdbcPool implements DataSource {
    private static LinkedList<Connection> listConnections = new LinkedList<>();

   //todo spring-el加载properties文件属性
    @Value("${driver}")
    private static String driver;
    @Value("${url}")
    private static String url;
    @Value("${username}")
    private static String userName;
    @Value("${password}")
    private static String password;
    @Value("jdbcPoolInitSize")
    private static int jdbcPoolInitSize;

    //todo 初始化连接数
    static {
        try {
            Class.forName(driver);
            for (int i = 0; i < jdbcPoolInitSize; i++) {
                Connection conn = DriverManager.getConnection(url, userName, password);
                System.out.println("获取链接" + conn);
                listConnections.add(conn);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {  /*配置文件需要加载的bean*/
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 使用代理获取连接池对象：connection
     * @return 连接池内的Connection对象的代理
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        if (listConnections.size() > 0){
            final Connection conn = listConnections.removeFirst();
            System.out.println("连接池大小为："+listConnections.size());
            return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (!method.getName().equals("close")){
                        return method.invoke(conn,args);
                    }else{
                        listConnections.add(conn);
                        System.out.println(conn+"还给了连接池");
                        System.out.println("连接池大小为"+listConnections.size());
                        return null;
                    }
                }
            });
        }else {
            throw new RuntimeException("连接池为空");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
