import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class Main {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(100);

        String address = "localhost";
        int port = 6379;

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, address, port);

        try(Jedis jedis = jedisPool.getResource()) {
            String result = jedis.hget("user", "user1");
            if (result != null) {
                if (result != "") {
                    System.out.println("Ok");
                } else {
                    System.out.println("Error1");
                }
            } else {
                System.out.println("Error2");
            }
        } catch (JedisConnectionException ex) {
            ex.printStackTrace();
        }

        jedisPool.close();
    }
}
