package com.example.elasticSearchAndRedis.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
    @Value("${spring.redis.master}")
    private String redisMaster;
    @Value("${spring.redis.nodes}")
    private String redisNodes;
    @Value("${redis.sentinel.mode}")
    private boolean redisMode;
    @Value("${spring.redis.jedis.pool.max-active:5000}")
    private int maxActiveConn;
    @Value("${spring.redis.jedis.pool.max-idle:8}")
    private int maxIdle;


    @Bean
    public JedisConnectionFactory connectionFactory() {
        log.info("::::::::::::JedisConnectionFactory:::::");
        List<String> clusterNodes = Arrays.asList(redisNodes.split(","));
        log.info(":::::clusterNodes {}", clusterNodes);
        if (clusterNodes.size() > 1) {
            return new JedisConnectionFactory(new RedisClusterConfiguration(clusterNodes));
        } else {
            RedisStandaloneConfiguration redisStandaloneConfiguration =
                            new RedisStandaloneConfiguration(clusterNodes.get(0), redisPort);
            return new JedisConnectionFactory(redisStandaloneConfiguration);
        }
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        log.info("::::::redisTemplate Bean creation::::::");
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
