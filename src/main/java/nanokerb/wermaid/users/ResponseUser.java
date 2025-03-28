package nanokerb.wermaid.users;

import java.util.ArrayList;
import java.util.List;

public class ResponseUser {

    public String id;
    public String displayName;
    public String username;
    public List<Role> role = new ArrayList<>();
}
