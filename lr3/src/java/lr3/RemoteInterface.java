package lr3;

import jakarta.ejb.Remote;  
import java.util.List;

@Remote  
public interface RemoteInterface {
    void addTodo(String todo);
    void removeLast();
    void showTodos();
    List<String> getTodos();
}
