package nanokerb.wermaid.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    public String id;
    public String displayName;
    public String username;
    public String password;
    public List<Role> role = new ArrayList<>();



}
