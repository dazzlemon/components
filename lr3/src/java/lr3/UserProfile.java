package lr3;

import jakarta.ejb.Remote;  
import java.util.List;

@Remote  
public interface UserProfile {
    String getName();
    void setName(String name);
    
    List<String> getFriends();
    void addFriend(String friend);
    void removeFriend(int index);
}
