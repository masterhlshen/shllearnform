package com.shl.test;

import redis.clients.jedis.Jedis;

import java.util.*;

public class RedisTest {
    public static void main(String[] args) {

        Jedis client = connectRedis();
        // redisStringOpt(client);

        // redisHashOpt(client);
        redisListOpt(client);
    }

    private static void redisListOpt(Jedis client) {
        String tag = "redisListOpt>>>>";
        String key = "xcList";
        client.lpush(key, "index", "index2", "index3", "index4");
        sOut(tag, client.lrange(key, 0, 4));
    }

    private static void redisHashOpt(Jedis client) {
        String tag = "redisHashOpt>>>>";
        Map<String, String> varMap = new HashMap<>(4);
        varMap.put("1", "1");
        varMap.put("2", "2");
        varMap.put("3", "3");
        varMap.put("4", "4");
        String key = "xchash";
        client.hset(key, varMap);

        sOut(tag, client.hgetAll(key));
        sOut(tag, client.hlen(key));
    }

    // Redis String 操作
    private static void redisStringOpt(Jedis client) {
        String tag = "redisStringOpt>>>>>>";
        // SET
        String value = "晓窗科技";
        String key = "xc";
        client.set(key, value);
        // GET
        sOut(tag, client.get(key));
        // GETRange
        sOut(tag, client.getrange(key, 0, value.length()));
        // GetSet 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
        sOut(tag, client.getSet(key, "晓窗科技2"));
        sOut(tag, client.get(key));

        client.set("xc2", "晓窗科技3");
        client.set("xc3", "晓窗科技4");

        // 获取所有(一个或多个)给定 key 的值
        sOut(tag, Arrays.toString(client.mget("xc", "xc2", "xc3").toArray()));

        // 返回 key 所储存的字符串值的长度。
        sOut(tag, client.strlen(key));

        // 同时设置一个或多个 key-value 对。
        client.mset("xc", "晓窗科技", "xc2", "晓窗科技");
        // 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
        client.msetnx("xc", "晓窗", "xc4", "行行go");
        sOut(tag, Arrays.toString(client.mget("xc", "xc2", "xc4").toArray()));

        // 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
        client.msetnx( "xc4", "行行go");
        sOut(tag, Arrays.toString(client.mget("xc", "xc2", "xc4").toArray()));

        // 将 key 中储存的数字值增一 如果不是数字型 会报错
        client.set("n", "1");
        client.incr("n");
        sOut(tag, client.get("n"));
        client.incrBy("n", 200);
        sOut(tag, client.get("n"));
        client.decrBy("n", 200);
        sOut(tag, client.get("n"));

        // 如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾
        client.append(key, "_xc");
        sOut(tag, client.get(key));

    }

    // 链接本地 redis
    private static Jedis connectRedis() {
        Jedis jedis = new Jedis("localhost");
        System.out.println("redis 服务正在运行" + jedis.ping());
        return jedis;

    }

    private static void sOut(String tag, Object content) {
        System.out.println(tag + content);
    }
}
