package org.blackist.web.springbootor.common.redis;

import com.google.gson.Gson;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * TODO JEdis测试
 *
 * @author L.L Dong<liangl.dong@qq.com>
 * @since 2019/9/4
 */
public class RedisTest {

	// 1.单独连接1台Redis
	private static Jedis jedis;
	// 2.主从、哨兵、 使用shard
	private static ShardedJedis shard;
	// 3.连接池（10 jedis）
	private static ShardedJedisPool pool;

	@BeforeClass
	public static void setUpBeforeClass() {
		// 单个节点
		jedis = new Jedis("192.168.11.150", 6379);

		// 分片
		List<JedisShardInfo> shards = Arrays.asList(
				// 节点1
				new JedisShardInfo("192.168.11.150"));
		shard = new ShardedJedis(shards);

		GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
		goConfig.setMaxTotal(100);
		goConfig.setMaxIdle(20);
		goConfig.setMaxWaitMillis(-1);
		goConfig.setTestOnBorrow(true);
		pool = new ShardedJedisPool(goConfig, shards);
	}

	@Test
	public void test() {
		// System.out.println(jedis);
		jedis.set("name", "blackist");
		List<String> vals = jedis.mget("a1", "a2", "a3");
		System.out.println(vals);
	}

	@Test
	public void testMap() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "black");
		map.put("age", "25");
		map.put("qq", "liangl.dong@qq.com");
		jedis.hmset("user", map);

		// key, fields
		List<String> rmap = jedis.hmget("user", "age", "qq");
		System.out.println(rmap);

		jedis.hdel("user", "age");

		System.out.println(jedis.hmget("user", "age"));
		System.out.println(jedis.hlen("user"));
		System.out.println(jedis.exists("user"));
		System.out.println(jedis.hkeys("user"));
		System.out.println(jedis.hvals("user"));


	}

	@Test
	public void testHashSet() {
		jedis.sadd("names", "foo1");
		jedis.sadd("names", "foo2");
		jedis.sadd("names", "foo3");
		jedis.sadd("names", "foo4");
		jedis.sadd("names", "foo5");
		Set<String> names = jedis.smembers("names");
		System.out.println(names);
		// remove
		jedis.srem("names", "foo1");
		System.out.println(jedis.smembers("names"));
		System.out.println(jedis.sismember("names", "foo1"));
		System.out.println(jedis.scard("names"));
		System.out.println(jedis.srandmember("names"));
	}

	@Test
	public void testAdmin() {

		final String SYS_USER_AGE_11 = "SYS_USER_AGE_11";
		final String SYS_USER_SEX_M = "SYS_USER_SEX_M";
		final String SYS_USER_SEX_W = "SYS_USER_SEX_W";

		Gson gson = new Gson();
		Map<String, String> users = new HashMap<>();

		String uid1 = UUID.randomUUID().toString();
		RedisUser u1 = new RedisUser(uid1, "u1", 11, "man");
		users.put(uid1, gson.toJson(u1));
		// jedis.sadd(SYS_USER_AGE_11, uid1);
		// jedis.sadd(SYS_USER_SEX_M, uid1);

		String uid2 = UUID.randomUUID().toString();
		RedisUser u2 = new RedisUser(uid2, "u2", 12, "man");
		users.put(uid2, gson.toJson(u2));
		// jedis.sadd(SYS_USER_SEX_M, uid2);

		String uid3 = UUID.randomUUID().toString();
		RedisUser u3 = new RedisUser(uid1, "u3", 13, "woman");
		users.put(uid3, gson.toJson(u3));
		// jedis.sadd(SYS_USER_SEX_W, uid3);

		// jedis.hmset("users", users);

		Set<String> uids = jedis.smembers(SYS_USER_SEX_M);
		List<String> _users = jedis.hmget("users", uids.toArray(new String[0]));
		System.out.println(_users);
	}
}
