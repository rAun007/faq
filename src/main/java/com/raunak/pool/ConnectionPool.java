package com.raunak.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by raunak.agrawal on 1/31/17.
 */
public class ConnectionPool {

    private BlockingQueue<Connection> pool;

    private int                       maxPoolSize;

    private int                       initialPoolSize;

    private int                       currentPoolSize;

    private String                    dbUrl;
    private String                    dbUser;
    private String                    dbPassword;
    private long                      timeToLive;     // After that connection will die

    public ConnectionPool(int maxPoolSize, int initialPoolSize, String url, String username, String password,
            String driverClassName) throws ClassNotFoundException, SQLException {

        if ((initialPoolSize > maxPoolSize) || initialPoolSize < 1 || maxPoolSize < 1) {
            throw new IllegalArgumentException("Invalid pool size parameters");
        }

        // default max pool size to 10
        this.maxPoolSize = maxPoolSize > 0 ? maxPoolSize : 10;
        this.initialPoolSize = initialPoolSize;
        this.dbUrl = url;
        this.dbUser = username;
        this.dbPassword = password;
        this.pool = new LinkedBlockingQueue<>(maxPoolSize);

        initPooledConnections(driverClassName);

    }

    private void initPooledConnections(String driverClassName) throws ClassNotFoundException, SQLException {

        // 1. Attempt to load the driver class
        Class.forName(driverClassName);

        // 2. Create and pool connections
        for (int i = 0; i < initialPoolSize; i++) {
            openAndPoolConnection();
        }
    }

    private synchronized void openAndPoolConnection() throws SQLException {
        if (currentPoolSize == maxPoolSize) {
            return;
        }

        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        pool.offer(new ManagedConnection(conn, this));
        currentPoolSize++;
    }

    public synchronized Connection borrowConnection() throws InterruptedException, SQLException {

        // Use from existing pooled idle connection
        // If no idle, open a new connection if size does permit.
        if (pool.peek() == null && currentPoolSize < maxPoolSize) {
            openAndPoolConnection();
        }
        // Borrowing thread will be blocked till connection
        // becomes available in the queue
        return pool.take();
    }

    public void surrenderConnection(Connection conn) {
        if (!(conn instanceof ManagedConnection)) {
            return;
        }
        pool.offer(conn);
    }
}
