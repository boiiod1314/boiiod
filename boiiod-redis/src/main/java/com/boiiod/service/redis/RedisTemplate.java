package com.boiiod.service.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.*;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

import java.util.*;

@Repository("redisTemplate")
public class RedisTemplate {

    private static final Logger log = LoggerFactory.getLogger(RedisClientTemplate.class);

    @Autowired
    private RedisDataSource redisDataSource;

    public RedisTemplate() {
        log.info("init RedisTemplate ...");
    }


    public void disconnect() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        shardedJedis.disconnect();
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     *
     * @return
     */
    public String set(String key, String value) {
        String result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 获取单个值
     *
     * @param key
     *
     * @return
     */
    public String get(String key) {
        String result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Boolean exists(String key) {
        Boolean result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        if (result == null) result = false;
        return result;
    }

    public String type(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.type(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 在某段时间后实现
     *
     * @param key
     *
     * @return
     */
    public Long expire(String key, int seconds) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.expire(key, seconds);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 在某个时间点失效
     *
     * @param key
     * @param unixTime
     *
     * @return
     */
    public Long expireAt(String key, long unixTime) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.expireAt(key, unixTime);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long ttl(String key) {
        Long result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.ttl(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public boolean setbit(String key, long offset, boolean value) {

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        boolean result = false;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.setbit(key, offset, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public boolean getbit(String key, long offset) {
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();
        boolean result = false;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getbit(key, offset);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public long setrange(String key, long offset, String value) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        long result = 0;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.setrange(key, offset, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String getrange(String key, long startOffset, long endOffset) {
//                ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getrange(key, startOffset, endOffset);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String getSet(String key, String value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getSet(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long setnx(String key, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.setnx(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String setex(String key, int seconds, String value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.setex(key, seconds, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long decrBy(String key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.decrBy(key, integer);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long decr(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.decr(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long incrBy(String key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.incrBy(key, integer);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long incr(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.incr(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long append(String key, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.append(key, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String substr(String key, int start, int end) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.substr(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long hset(String key, String field, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hset(key, field, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String hget(String key, String field) {
        if (key == null || field == null) return null;
        String result = null;

//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hget(key, field);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long hsetnx(String key, String field, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hsetnx(key, field, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }


    public String hmset(String key, Map<String, String> hash) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hmset(key, hash);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<String> hmget(String key, String... fields) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hmget(key, fields);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long hincrBy(String key, String field, long value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hincrBy(key, field, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Boolean hexists(String key, String field) {
        Boolean result = false;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hexists(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long del(String... key) {
        Long result = null;
        Jedis jedis = redisDataSource.getJedis();
        if (jedis == null) {
            return result;
        }

        try {
            result = jedis.del(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            jedis.close();
        }
        return result;
    }

    public Long hdel(String key, String... field) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hdel(key, field);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long hlen(String key) {
        Long result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hlen(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<String> hkeys(String key) {
        Set<String> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hkeys(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<String> hvals(String key) {
        List<String> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hvals(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Map<String, String> hgetAll(String key) {
        Map<String, String> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hgetAll(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    // ================list ====== l表示 list或 left, r表示right====================
    public Long rpush(String key, String string) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.rpush(key, string);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long lpush(String key, String string) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.lpush(key, string);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long llen(String key) {
        Long result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.llen(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<String> lrange(String key, long start, long end) {
        List<String> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.lrange(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String ltrim(String key, long start, long end) {
        String result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.ltrim(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String lindex(String key, long index) {
        String result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.lindex(key, index);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String lset(String key, long index, String value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.lset(key, index, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long lrem(String key, long count, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.lrem(key, count, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String lpop(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.lpop(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String rpop(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.rpop(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    //return 1 add a not exist value ,
    //return 0 add a exist value
    public Long sadd(String key, String... members) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.sadd(key, members);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<String> smembers(String key) {
        Set<String> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.smembers(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long srem(String key, String... member) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();

        Long result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.srem(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String spop(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.spop(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long scard(String key) {
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        Long result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.scard(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Boolean sismember(String key, String member) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Boolean result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.sismember(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    //交集
    public Set<String> sinter(String... keys) {

        Jedis jedis = redisDataSource.getJedis();
        Set<String> result = new HashSet<String>();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.sinter(keys);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            jedis.close();
        }
        return result;
    }

    //交集 并存储到 destination中
    public Long sinterstore(String destination, String... keys) {

        Jedis jedis = redisDataSource.getJedis();
        Long result = null;
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.sinterstore(destination, keys);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            jedis.close();
        }
        return result;
    }

    //并集
    public Set<String> sunion(String... keys) {

        Jedis jedis = redisDataSource.getJedis();
        Set<String> result = new HashSet<String>();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.sunion(keys);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            jedis.close();
        }
        return result;
    }

    //差集
    public Set<String> sdiff(String... keys) {

        Jedis jedis = redisDataSource.getJedis();
        Set<String> result = new HashSet<String>();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.sdiff(keys);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            jedis.close();
        }
        return result;
    }

    //差集 并存储到 destination 中
    public Long sdiffstore(String destination, String... keys) {

        Jedis jedis = redisDataSource.getJedis();
        Long result = null;
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.sdiffstore(destination, keys);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            jedis.close();
        }
        return result;
    }

    public String srandmember(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.srandmember(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zadd(String key, double score, String member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zadd(key, score, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zadd(String key, Map<String, Double> scoreMembers) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zadd(key, scoreMembers);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }


    //计算 Zset 交集， score 做累加
    public Long zinterstore(String dstkey, String... sets) {
        Long result = null;

        if (dstkey == null || sets == null) {
            return null;
        }

        Jedis jedis = redisDataSource.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zinterstore(dstkey, sets);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            jedis.close();
        }
        return result;
    }

    //计算 Zset 并集
    public Long zunionstore(String dstkey, String... sets) {
        Long result = null;

        if (dstkey == null || sets == null) {
            return null;
        }

        Jedis jedis = redisDataSource.getJedis();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.zunionstore(dstkey, sets);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            jedis.close();
        }
        return result;
    }


    public Set<String> zrange(String key, int start, int end) {
        Set<String> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zrange(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zrem(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zrem(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zrem(String key, String... member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zrem(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Double zincrby(String key, double score, String member) {
        Double result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zincrby(key, score, member);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zrank(String key, String member) {
        Long result = null;
        if (key == null || member == null) return result;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrank(key, member);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zrevrank(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrank(key, member);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<String> zrevrange(String key, long start, long end) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrange(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrangeWithScores(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeWithScores(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zcard(String key) {
        Long result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zcard(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Double zscore(String key, String member) {
        Double result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zscore(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<String> sort(String key) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.sort(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<String> sort(String key, SortingParams sortingParameters) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.sort(key, sortingParameters);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zcount(String key, double min, double max) {
        Long result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zcount(key, min, max);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<String> zrangeByScore(String key, double min, double max) {
        Set<String> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zrangeByScore(key, min, max);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<String> zrevrangeByScore(String key, double max, double min) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeByScore(key, max, min);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Set<String> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.zrangeByScore(key, min, max, offset, count);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Set<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeByScore(key, max, min, offset, count);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrangeByScoreWithScores(key, min, max);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeByScoreWithScores(key, max, min);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zremrangeByRank(String key, int start, int end) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zremrangeByRank(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zremrangeByScore(String key, double start, double end) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zremrangeByScore(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.linsert(key, where, pivot, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String set(byte[] key, byte[] value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.set(key, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public byte[] get(byte[] key) {
        byte[] result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Boolean exists(byte[] key) {
        Boolean result = false;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String type(byte[] key) {
        String result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.type(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long expire(byte[] key, int seconds) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.expire(key, seconds);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long expireAt(byte[] key, long unixTime) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.expireAt(key, unixTime);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long ttl(byte[] key) {
        Long result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.ttl(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public byte[] getSet(byte[] key, byte[] value) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.getSet(key, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long setnx(byte[] key, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.setnx(key, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String setex(byte[] key, int seconds, byte[] value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.setex(key, seconds, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long decrBy(byte[] key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.decrBy(key, integer);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long decr(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.decr(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long incrBy(byte[] key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.incrBy(key, integer);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long incr(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.incr(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long append(byte[] key, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.append(key, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public byte[] substr(byte[] key, int start, int end) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.substr(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long hset(byte[] key, byte[] field, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.hset(key, field, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public byte[] hget(byte[] key, byte[] field) {
        byte[] result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hget(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.hsetnx(key, field, value);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String hmset(byte[] key, Map<byte[], byte[]> hash) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.hmset(key, hash);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.hmget(key, fields);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long hincrBy(byte[] key, byte[] field, long value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.hincrBy(key, field, value);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Boolean hexists(byte[] key, byte[] field) {
        Boolean result = false;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hexists(key, field);
        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long hdel(byte[] key, byte[] field) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.hdel(key, field);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long hlen(byte[] key) {
        Long result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hlen(key);
        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<byte[]> hkeys(byte[] key) {
        Set<byte[]> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hkeys(key);
        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Collection<byte[]> hvals(byte[] key) {
        Collection<byte[]> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hvals(key);
        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Map<byte[], byte[]> hgetAll(byte[] key) {
        Map<byte[], byte[]> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.hgetAll(key);
        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long rpush(byte[] key, byte[] string) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.rpush(key, string);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long lpush(byte[] key, byte[] string) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.lpush(key, string);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long llen(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.llen(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<byte[]> lrange(byte[] key, int start, int end) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.lrange(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String ltrim(byte[] key, int start, int end) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.ltrim(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public byte[] lindex(byte[] key, int index) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.lindex(key, index);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String lset(byte[] key, int index, byte[] value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.lset(key, index, value);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long lrem(byte[] key, int count, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.lrem(key, count, value);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public byte[] lpop(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.lpop(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public byte[] rpop(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.rpop(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long sadd(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.sadd(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<byte[]> smembers(byte[] key) {
        Set<byte[]> result = null;
//        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        ShardedJedis shardedJedis = redisDataSource.getRedisReadClient();

        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.smembers(key);
        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long srem(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.srem(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public byte[] spop(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.spop(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long scard(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.scard(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Boolean sismember(byte[] key, byte[] member) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.sismember(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public byte[] srandmember(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.srandmember(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zadd(byte[] key, double score, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zadd(key, score, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<byte[]> zrange(byte[] key, int start, int end) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrange(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zrem(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrem(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Double zincrby(byte[] key, double score, byte[] member) {
        Double result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zincrby(key, score, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zrank(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrank(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zrevrank(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrank(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<byte[]> zrevrange(byte[] key, int start, int end) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrange(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrangeWithScores(byte[] key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrangeWithScores(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeWithScores(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zcard(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zcard(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Double zscore(byte[] key, byte[] member) {
        Double result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zscore(key, member);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<byte[]> sort(byte[] key) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.sort(key);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.sort(key, sortingParameters);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zcount(byte[] key, double min, double max) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zcount(key, min, max);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrangeByScore(key, min, max);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrangeByScore(key, min, max, offset, count);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrangeByScoreWithScores(key, min, max);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeByScore(key, max, min);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeByScore(key, max, min, offset, count);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeByScoreWithScores(key, max, min);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zremrangeByRank(byte[] key, int start, int end) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zremrangeByRank(key, start, end);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long zremrangeByScore(byte[] key, double start, double end) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.zremrangeByScore(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {

            result = shardedJedis.linsert(key, where, pivot, value);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    public List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        List<Object> result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.pipelined(shardedJedisPipeline);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Jedis getShard(byte[] key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Jedis result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getShard(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Jedis getShard(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Jedis result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getShard(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public JedisShardInfo getShardInfo(byte[] key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        JedisShardInfo result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getShardInfo(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public JedisShardInfo getShardInfo(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        JedisShardInfo result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getShardInfo(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public String getKeyTag(String key) {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        String result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getKeyTag(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Collection<JedisShardInfo> getAllShardInfo() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Collection<JedisShardInfo> result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getAllShardInfo();

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public Collection<Jedis> getAllShards() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        Collection<Jedis> result = null;
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.getAllShards();

        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

}