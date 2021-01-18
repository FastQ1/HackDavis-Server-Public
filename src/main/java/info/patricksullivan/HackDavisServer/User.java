package info.patricksullivan.HackDavisServer;

import java.time.LocalDateTime;
import java.util.Map;

public class User {
    String userID;
    String city;
    Map<String, Integer> hobbies;
    LocalDateTime time;

    public User(String userID, String city, Map<String, Integer> hobbies) {
        this.userID = userID;
        this.city = city;
        this.hobbies = hobbies;
        this.time = LocalDateTime.now();
    }

    public String getCity() {
        return city;
    }

    public String getUserID() {
        return userID;
    }

    public Map<String, Integer> getHobbies() {
        return hobbies;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
