package com.app.ktf.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableCassandraRepositories(basePackages = "com.app.ktf.blog.repository.cassandra")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${spring.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.cassandra.local-datacenter}")
    private String localDatacenter;

    @Value("${astra.db.secure-connect-bundle-path}")
    private String secureConnectBundlePath;

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected String getLocalDataCenter() {
        return localDatacenter;
    }

    // Custom configuration for Astra DB Secure Connect Bundle would typically be done in a CqlSession bean
    // But for a standard starter-data-cassandra, this provides the base.
}
