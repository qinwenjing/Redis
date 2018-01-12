package com.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by qwj on 2018/1/11.
 */
public class RedisTest {
    public static void main(String[] args){
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfuly");
        //check whether server is running or not
        System.out.println("Server is running :"+jedis.ping());
        jedis.set("key", "value");
        System.out.println("key = "+jedis.get("key"));
        //store data in redis list
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0 ,1);

        for(int i = 0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }
        Set<String> set =  jedis.keys("*");
        for(String s : set){
            System.out.println("s:: "+s);
        }

    }

}
