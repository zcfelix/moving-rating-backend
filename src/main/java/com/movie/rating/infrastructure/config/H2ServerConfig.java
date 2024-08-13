package com.movie.rating.infrastructure.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.h2.tools.Server;
import org.springframework.context.annotation.Configuration;

import java.net.Socket;
import java.sql.SQLException;

@Configuration
public class H2ServerConfig {

    private Server h2Server;

    @PostConstruct
    public void startH2Server() throws SQLException {
        if (!isPortInUse(9092)) {
            h2Server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
        }
    }

    @PreDestroy
    public void stopH2Server() {
        if (h2Server != null) {
            h2Server.stop();
        }
    }

    private boolean isPortInUse(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}