

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineCacheExample {

    public static void main(String[] args) throws InterruptedException {
        Cache<Integer, String> userCache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();

        int userId = 101;
        for (int i = 0; i < 5; i++){
            String user = userCache.getIfPresent(userId);

            if (user == null) {
                System.out.println("Cache miss! Fetching from DB...");
                user = fetchFromDatabase(userId);
                userCache.put(userId, user);
            } else {
                System.out.println("Cache hit!");
            }

            System.out.println("Result: " + user);
        }

    }

    private static String fetchFromDatabase(int id) throws InterruptedException {
        // Simulate database latency
        Thread.currentThread().sleep(600);
        return "User_" + id;
    }
}