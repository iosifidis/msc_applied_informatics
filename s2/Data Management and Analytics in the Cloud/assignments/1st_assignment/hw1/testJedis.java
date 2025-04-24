import java.io.*;
import java.util.*;
import redis.clients.jedis.Jedis;

public class testJedis {

	public static void main (String [] args) throws Exception {
		// connect to the default Redis server (localhost, default port)
		Jedis jedis = new Jedis();
		String key, value;

		// read from the input
		Scanner userinput = new Scanner(System.in);

		// insert key, value pair
		System.out.println("Type a key:");
		key = userinput.nextLine();
		System.out.println("Type a value:");
		value = userinput.nextLine();
		jedis.set(key, value);

		// retrieve value given a key
		System.out.println("Type a key:");
		key = userinput.nextLine();
		value = jedis.get(key);
		System.out.println("The value is " + value);
	}
}
