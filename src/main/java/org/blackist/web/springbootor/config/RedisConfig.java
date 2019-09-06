package org.blackist.web.springbootor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * TODO Redis集群配置
 *
 * @author L.L Dong<liangl.dong@qq.com>
 * @since 2019/9/1
 */
@Configuration
public class RedisConfig {

	// 注入集群节点信息
	@Value("${spring.redis.cluster.nodes}")
	private String clusterNodes;

	@Bean
	public JedisCluster getJedisCluster() {
		// 分割集群节点
		String[] _nodes = clusterNodes.split(",");
		// 创建node集合
		Set<HostAndPort> nodes = new HashSet<>();
		for (String node : _nodes) {
			String[] hp = node.split(":");
			nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
		}
		JedisCluster jedisCluster = new JedisCluster(nodes);

		return jedisCluster;
	}
}
