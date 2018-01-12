package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by qwj on 2018/1/11.
 */
public class RedisAPI {
    private static JedisPool pool = null;
    public static JedisPool getPool(){
        if(pool == null){
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(1000*1000);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, "localhost");
        }
        return pool;
    }

    public static void returnResource(JedisPool jedisPool, Jedis jedis){
        if(jedisPool != null){
            jedisPool.returnResource(jedis);
        }
    }
    public static String get(String key){
        JedisPool pool = null;
        Jedis jedis = null;
        String value = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        }catch (Exception e){
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        }finally {
            returnResource(pool, jedis);
        }
        return value;
    }
    public static void main(String[] args){



    }
}
