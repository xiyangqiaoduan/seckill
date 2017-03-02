package com.yangcb.seckill.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @author cooper
 * @description 操作redis工具   切片连接池
 * @create 2017-03-01 13:49
 **/
@Component
public class RedisCacheManager {

    private static Logger LOG = LoggerFactory.getLogger(RedisCacheManager.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * 获取Jedis对象
     *
     * @return
     */
    private ShardedJedis getJedis() {
        return shardedJedisPool.getResource();
    }

    /**
     * 释放Jedis资源
     *
     * @param jedis
     */
    private void returnResource(ShardedJedis jedis) {
        if (null != jedis) {
            jedis.close();
        }

    }


    /**
     * set 集合 把一个或者多个元素添加到指定集合
     *
     * @param key
     * @param value
     * @return 0, 没有新的插入，1、插入成功
     */
    public long addSet(String key, String... value) {
        if (key == null || key.equals("")) {
            return 0;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.sadd(key, value);
        } catch (Exception e) {
            LOG.info("添加指定元素到集合:" + e);
        } finally {
            returnResource(jedis);
        }
        return 0;
    }

    /**
     * 添加到Set中（同时设置过期时间）
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public boolean addSet(String key, int seconds, String... value) {
        long result = addSet(key, value);
        if (result > 0) {
            long i = expire(key, seconds);
            System.out.println(i);
            return i == 1;
        }

        return false;
    }


    /**
     * 获取redis Set集合所有元素个数
     *
     * @param key
     * @return
     */
    public long countSet(String key) {

        if (key == null || key.trim().equals("")) {
            return 0;
        }
        ShardedJedis jedis = getJedis();
        Long result = 0L;
        try {
            result = jedis.scard(key);
        } catch (Exception e) {
            LOG.info("获取redis Set集合所有元素个数" + e);
        } finally {
            returnResource(jedis);
        }
        return result;

    }

    /**
     * 获取redis 集合中的数据信息
     *
     * @param key
     * @return
     */
    public Set<String> getSet(String key) {
        if (key == null || key.trim().equals("")) {
            return null;
        }

        ShardedJedis jedis = getJedis();
        Set<String> result = null;
        try {
            result = jedis.smembers(key);
        } catch (Exception e) {
            LOG.info("获取redis Set集合所有元素个数" + e);
        } finally {
            returnResource(jedis);
        }
        return result;

    }

    /**
     * 设置一个key的过期时间（单位：秒）
     *
     * @param key     key值
     * @param seconds 多少秒后过期
     * @return 1：设置了过期时间  、0：没有设置过期时间/不能设置过期时间
     */
    public long expire(String key, int seconds) {

        if (key == null || key.trim().equals("")) {
            return 0;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.expire(key, seconds);
        } catch (Exception e) {
            LOG.info(String.format("设置过期时间:key=%s seconds=%s", key, seconds), e);
        } finally {
            returnResource(jedis);
        }
        return 0;
    }

    /**
     * 设置一个key 在某个时间过期
     *
     * @param key           key值
     * @param unixTimestamp unix时间戳   date.getTime()/1000  获取秒数
     * @return 1：设置了过期时间  、0：没有设置过期时间/不能设置过期时间
     */
    public long expireAt(String key, long unixTimestamp) {
        if (key == null || key.trim().equals("")) {
            return 0;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.expireAt(key, unixTimestamp);
        } catch (Exception e) {
            LOG.info(String.format("设置在某个时间点过期时间:key=%s seconds=%s", key, unixTimestamp), e);
        } finally {
            returnResource(jedis);
        }
        return 0;
    }

    /**
     * 设置一个key在某个时间点过期
     *
     * @param key  key值
     * @param date 过期时间
     * @return 1：设置了过期时间  0：没有设置过期时间/不能设置过期时间
     */
    public long expireAtDate(String key, Date date) {
        if (date == null) {
            return 0;
        }
        try {
            return expireAt(key, date.getTime() / 1000);
        } catch (Exception e) {
            LOG.info("时间转换错误", e);
        }
        return 0;
    }

    /**
     * 截断一个List   TODO 暂时不知道什么意思
     *
     * @param key   列表key
     * @param start 开始位置 从0开始
     * @param end   结束位置
     * @return 状态码
     */
    public String trimList(String key, long start, long end) {

        if (key == null || key.equals("")) {
            return "-";
        }

        ShardedJedis jedis = getJedis();
        try {
            return jedis.ltrim(key, start, end);
        } catch (Exception e) {
            LOG.info(String.format("截断list: key=%s  start=%s end=%s", key, start, end), e);
        } finally {
            returnResource(jedis);
        }
        return "-";
    }

    /**
     * 判断值是否包含在Set中
     *
     * @param key
     * @param value
     * @return
     */
    public boolean containsInSet(String key, String value) {
        if (null == key || value == null) {
            return false;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.sismember(key, value);
        } catch (Exception e) {
            LOG.info(String.format("判断Set集合包含： key=%s value=%s", key, value), e);
        } finally {
            returnResource(jedis);
        }

        return false;
    }


    /**
     * 删除set集合中的value
     *
     * @param key
     * @param value
     * @return
     */
    public boolean removeSetValue(String key, String... value) {
        if (null == key || value == null) {
            return false;
        }
        ShardedJedis jedis = getJedis();
        try {
            long result = jedis.srem(key, value);
            return result > 0;
        } catch (Exception e) {
            LOG.info(String.format("移除SET集合的元素 key=%s", key), e);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    /**
     * 将value 添加到List
     *
     * @param key
     * @param value
     * @return
     */
    public boolean addList(String key, String... value) {
        if (null == key || value == null) {
            return false;
        }
        ShardedJedis jedis = getJedis();
        try {
            long result = jedis.lpush(key, value);
            return result > 0;
        } catch (Exception e) {
            LOG.info(String.format("将元素添加到list key=%s", key), e);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    /***
     * 将list集合添加到redis  list集合
     * @param key
     * @param list
     * @return
     */
    public boolean addList(String key, List<String> list) {
        if (key == null || list == null || list.size() == 0) {
            return false;
        }
        for (String value : list) {
            addList(key, value);
        }
        return true;
    }

    /**
     * 添加到List 设置过期时间
     *
     * @param key     key值
     * @param seconds 过期时间 单位秒
     * @param value
     * @return
     */
    public boolean addList(String key, int seconds, String... value) {

        boolean result = addList(key, value);
        if (result) {
            long i = expire(key, seconds);
            return i == 1;
        }
        return false;
    }

    /**
     * 检查List长度
     *
     * @param key
     * @return
     */
    public long countList(String key) {
        if (key == null) {
            return 0;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.llen(key);
        } catch (Exception ex) {
            LOG.info("检查List长度 key:" + key);
        } finally {
            returnResource(jedis);
        }
        return 0;
    }


    /**
     * 截取List
     *
     * @param key
     * @param start 起始位置
     * @param end   结束位置
     * @return
     */
    public List<String> rangeList(String key, long start, long end) {
        if (key == null || key.equals("")) {
            return null;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.lrange(key, start, end);
        } catch (Exception ex) {
            LOG.info("rangeList 出错[key=" + key + " start=" + start + " end=" + end + "]" + ex.getMessage(), ex);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    /**
     * 从list中删除value
     *
     * @param key
     * @param count 要删除个数
     * @param value
     * @return
     */
    public boolean removeListValue(String key, long count, String value) {
        ShardedJedis jedis = getJedis();
        try {
            long result = jedis.lrem(key, count, value);
            return result > 0;
        } catch (Exception ex) {
            LOG.info("getList error.", ex);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    /**
     * 从list中删除value 默认count 1
     *
     * @param key
     * @param values 值list
     * @return
     */
    public int removeListValue(String key, List<String> values) {
        return removeListValue(key, 1, values);
    }

    /**
     * 从list中删除value
     *
     * @param key
     * @param count
     * @param values 值list
     * @return
     */
    public int removeListValue(String key, long count, List<String> values) {
        int result = 0;
        if (values != null && values.size() > 0) {
            for (String value : values) {
                if (removeListValue(key, count, value)) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 设置HashSet对象
     *
     * @param key   域名
     * @param key   键值
     * @param value Json String or String value
     * @return
     */
    public boolean setHset(String key, String field, String value) {

        if (value == null) {
            return false;
        }
        ShardedJedis jedis = getJedis();
        try {
            long result = jedis.hset(key, field, value);
            return result > 0;
        } catch (Exception e) {
            LOG.info("setHSet error", e);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    /**
     * 获得HashSet对象
     *
     * @param key   key值
     * @param field 键值
     * @return Json String or String value
     */
    public String getHSet(String key, String field) {

        ShardedJedis jedis = getJedis();
        try {
            return jedis.hget(key, field);
        } catch (Exception e) {
            LOG.error("getHSet error.", e);
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 删除HashSet对象
     *
     * @param key   域名
     * @param field 键值
     * @return 删除的记录数
     */
    public long delHset(String key, String field) {
        ShardedJedis jedis = getJedis();
        try {
            return jedis.hdel(key, field);
        } catch (Exception e) {
            LOG.info("delHset", e);
        } finally {
            jedis.close();
        }
        return 0;
    }

    /**
     * 删除HashSet对象
     *
     * @param key   key值
     * @param field 键值
     * @return 删除的记录数
     */
    public long delHset(String key, String... field) {
        ShardedJedis jedis = getJedis();
        try {
            return jedis.hdel(key, field);
        } catch (Exception e) {
            LOG.info("delHset", e);
        } finally {
            jedis.close();
        }
        return 0;
    }


    /**
     * 判断key是否存在
     *
     * @param domain 域名
     * @param key    键值
     * @return
     */
    public boolean existsHSet(String domain, String key) {
        ShardedJedis jedis = getJedis();
        boolean isExist = false;
        try {
            isExist = jedis.hexists(domain, key);
        } catch (Exception ex) {
            LOG.error("existsHSet error.", ex);
        } finally {
            returnResource(jedis);
        }
        return isExist;
    }


    /**
     * 全局扫描hset
     *
     * @param match field匹配模式
     * @return
     */
    public List<Map.Entry<String, String>> scanHSet(String key, String match) {

        ShardedJedis shardedJedis = getJedis();
        try {
            int cursor = 0;
            ScanParams scanParams = new ScanParams();
            scanParams.match(match);
            Jedis jedis = shardedJedis.getShard(key);
            ScanResult<Map.Entry<String, String>> scanResult;
            List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>();
            do {
                scanResult = jedis.hscan(key, String.valueOf(cursor), scanParams);
                list.addAll(scanResult.getResult());
                cursor = Integer.parseInt(scanResult.getStringCursor());
            } while (cursor > 0);
            return list;
        } catch (Exception ex) {
            LOG.error("scanHSet error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /***
     * 通过KEY获取哈希集合中所有的键值
     * @param key
     * @return
     */
    public Set<String> hkeys(String key) {

        if (null == key || key.trim().equals("")) {
            return null;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.hkeys(key);
        } catch (Exception e) {
            LOG.info(String.format("获取哈希表中字段的field KEY=%s", key), e);
        } finally {
            returnResource(jedis);
        }
        return null;

    }

    /**
     * 获取指定KEY的哈希键值总数
     *
     * @param key
     * @return
     */
    public long lenHset(String key) {

        if (null == key || key.trim().equals("")) {
            return 0;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.hlen(key);
        } catch (Exception e) {
            LOG.info(String.format("获取指定KEY的哈希键值总数 KEY=%s", key), e);
        } finally {
            returnResource(jedis);
        }
        return 0;
    }


    /**
     * 设置排序结合
     *
     * @param key
     * @param score
     * @param value
     * @return
     */
    public boolean setSortedSet(String key, long score, String value) {
        if (null == key || key.trim().equals("")) {
            return false;
        }
        ShardedJedis jedis = getJedis();
        try {
            long result = jedis.zadd(key, score, value);
            return result > 0;
        } catch (Exception e) {
            LOG.info("设置排序结合", e);
        }
        return false;
    }

    /**
     * 获得排序集合
     *
     * @param key        KEY值
     * @param startScore 开始
     * @param endScore   结束
     * @param ordeByDesc true -降序、fasle-升序
     * @return
     */
    public Set<String> getSoredSet(String key, long startScore, long endScore, boolean ordeByDesc) {
        if (null == key || key.trim().equals("")) {
            return null;
        }
        ShardedJedis jedis = getJedis();
        try {
            if (ordeByDesc) {
                return jedis.zrangeByScore(key, endScore, startScore);
            } else {
                return jedis.zrangeByScore(key, startScore, endScore);
            }
        } catch (Exception e) {
            LOG.info("获得排序集合", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }


    /**
     * 计算排序长度
     *
     * @param key
     * @param startScore
     * @param endScore
     * @return
     */
    public long countSoredSet(String key, long startScore, long endScore) {
        if (null == key || key.trim().equals("")) {
            return 0;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.zcount(key, startScore, endScore);
        } catch (Exception e) {
            LOG.info("计算排序长度", e);
        } finally {
            returnResource(jedis);
        }
        return 0;

    }


    /**
     * 删除排序集合
     *
     * @param key
     * @param value
     * @return
     */
    public boolean delSoredSet(String key, String value) {

        if (null == key || key.trim().equals("")) {
            return false;
        }

        ShardedJedis jedis = getJedis();
        try {
            long result = jedis.zrem(key, value);
            return result > 0;
        } catch (Exception e) {
            LOG.info("删除排序集合", e);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    /**
     * 获得排序集合
     *
     * @param key
     * @param startRange
     * @param endRange
     * @param orderByDesc
     * @return
     */
    public Set<String> getSoredSetByRange(String key, int startRange, int endRange, boolean orderByDesc) {

        if (null == key || key.trim().equals("")) {
            return null;
        }
        ShardedJedis jedis = getJedis();
        try {
            if (orderByDesc) {
                return jedis.zrevrange(key, startRange, endRange);
            } else {
                return jedis.zrange(key, startRange, endRange);
            }
        } catch (Exception ex) {
            LOG.info("获得排序集合", ex);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    /**
     * 获得排序打分
     *
     * @param key
     * @return
     */
    public Double getScore(String key, String member) {
        if (null == key || key.trim().equals("")) {
            return 0d;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.zscore(key, member);
        } catch (Exception ex) {
            LOG.info("getSoredSet error.", ex);
        } finally {
            returnResource(jedis);
        }
        return 0d;
    }

    /**
     * 存储
     *
     * @param key
     * @param value
     * @param second
     * @return
     */
    public boolean set(String key, String value, int second) {
        if (null == key || key.trim().equals("")) {
            return false;
        }
        ShardedJedis jedis = getJedis();
        try {
            jedis.setex(key, second, value);
            return true;
        } catch (Exception ex) {
            LOG.info("getSoredSet error.", ex);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    /**
     * 普通存，没有过期时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        if (null == key || key.trim().equals("")) {
            return false;
        }
        ShardedJedis jedis = getJedis();
        try {
            jedis.set(key, value);
            return true;
        } catch (Exception ex) {
            LOG.info("没有过期时间.", ex);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String get(String key, String defaultValue) {
        if (null == key || key.trim().equals("")) {
            return defaultValue;
        }
        ShardedJedis jedis = getJedis();
        try {
            return jedis.get(key) == null?defaultValue:jedis.get(key);
        } catch (Exception ex) {
            LOG.info("没有过期时间.", ex);
        } finally {
            returnResource(jedis);
        }
        return defaultValue;
    }

    public long incr(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.incr(key);
        } catch (Exception ex) {
            LOG.info("incr error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    public long decr(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.decr(key);
        } catch (Exception ex) {
            LOG.info("incr error.", ex);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }


}
