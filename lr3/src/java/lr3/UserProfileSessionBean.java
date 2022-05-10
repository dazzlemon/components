package lr3;

import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.Stateless;

import lombok.Setter;
import lombok.Getter;

@Stateless(mappedName="userProfileSessionBean")
public class UserProfileSessionBean implements UserProfile {
    @Getter(onMethod = @__(@Override))
    @Setter(onMethod = @__(@Override))
    private String name;
    
    @Getter(onMethod = @__(@Override))
    private List<String> friends = new ArrayList<String>();

    @Override
    public void addFriend(String friend) {
        friends.add(friend);
    }
    
    @Override
    public void removeFriend(int index) {
        friends.remove(index);
    }
}