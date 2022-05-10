package lr3;

import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.Stateless;

@Stateless(mappedName="statelessExample")
public class StatelessSessionBean implements RemoteInterface {
     
    List<String> todos;
     
    public StatelessSessionBean() {
        todos = new ArrayList<>();
    }
    
    @Override
    public void addTodo(String todo) {
        todos.add(todo);
    }
   
    @Override
    public void removeLast() {
        todos.remove(todos.size()-1);
    }

    @Override    
    public List<String> getTodos() {
        return todos;
    }
    
    @Override
    public void showTodos() {
        todos.forEach(System.out::println);
    }
}