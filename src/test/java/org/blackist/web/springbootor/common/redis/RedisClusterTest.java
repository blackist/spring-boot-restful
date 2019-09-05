package org.blackist.web.springbootor.common.redis;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * TODO Redis集群交互测试
 *
 * @author L.L Dong<liangl.dong@qq.com>
 * @since 2019/9/5
 */
public class RedisClusterTest {

	@Test
	public void testCluster() {
		Set<HostAndPort> clusterNodes = new HashSet<>();
		clusterNodes.add(new HostAndPort("192.168.11.150", 7001));
		clusterNodes.add(new HostAndPort("192.168.11.150", 7002));
		clusterNodes.add(new HostAndPort("192.168.11.150", 7003));
		clusterNodes.add(new HostAndPort("192.168.11.150", 7004));
		clusterNodes.add(new HostAndPort("192.168.11.150", 7005));
		clusterNodes.add(new HostAndPort("192.168.11.150", 7006));

		JedisPoolConfig cfg = new JedisPoolConfig();
		cfg.setMaxTotal(100);
		cfg.setMaxIdle(20);
		cfg.setMaxWaitMillis(-1);
		cfg.setTestOnBorrow(true);
		JedisCluster jc = new JedisCluster(clusterNodes, 6000, 100, cfg);

		System.out.println(jc.set("name", "black"));
		System.out.println(jc.set("age", "22"));
		System.out.println(jc.get("age"));
		System.out.println(jc.get("name"));
	}
}
