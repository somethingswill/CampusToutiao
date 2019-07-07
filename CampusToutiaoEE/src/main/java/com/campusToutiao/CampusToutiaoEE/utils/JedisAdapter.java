package com.campusToutiao.CampusToutiaoEE.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 操作redis的工具类
 */
@Component
public class JedisAdapter implements InitializingBean {

    private Jedis jedis = null;
    private JedisPool jedisPool = null;


    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("localhost", 6379);
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public void setex(String key, String value, int second) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(key, second, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if ((null != jedis)) {
                jedis.close();
            }
        }
    }

    public Set<String> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.smembers(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public void sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.sadd(key, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public long srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srem(key, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

}
