package util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {
    private static final String PASSWORD_KEY ="db.password";

    private static final String USER_KEY ="db.user";
    private static final String URL_KEY ="db.url";
    private static BlockingQueue<Connection> pool;
    private static List<Connection> sourceConnections;
    private static final String POOL_SIZE_KEY="db.pool.size";
    private static final int DEFAULT_SIZE=10;

    static {
        loadDriver();
        initConnectionPool();
    }
    private static void initConnectionPool() {
        String poolSize=PropertiesUtil.get(POOL_SIZE_KEY);
        int size=poolSize==null?DEFAULT_SIZE:Integer.parseInt(poolSize);
        pool=new ArrayBlockingQueue<>(size);
        sourceConnections=new ArrayList<>();
        for (int i = 0; i < size; i++) {
            var connection=open();
            var proxyConnection=(Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class}, (proxy, method, args) ->
                            method.getName().equals("close")?pool.add((Connection) proxy):
                                    method.invoke(connection,args)
            );
            pool.add(proxyConnection);
            sourceConnections.add(connection);
        }

    }
    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void closePool() {
        for (Connection connection : sourceConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static Connection get()  {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static Connection open(){
        try  {
            Connection connection = DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
