package org.com.testpro.ApplicationConfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class ClusterConfigurationProperties {

	public List<String> getNodes() {
		return nodes;
	}

	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}

	List<String> nodes;


}

@Configuration
class RedisClusterConfig {
	@Autowired
	ClusterConfigurationProperties ClusterConfigurationProperties;

	@Bean
	public LettuceConnectionFactory getLettuceConnectionFactory() {
		return new LettuceConnectionFactory(new RedisClusterConfiguration(
				ClusterConfigurationProperties.getNodes()));

	}
}