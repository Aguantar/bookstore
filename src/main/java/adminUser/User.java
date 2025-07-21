package adminUser;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String name;
    private String password;
    private String email;
    private String hp;
    private String role;
}