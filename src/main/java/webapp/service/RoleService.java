package webapp.service;

import webapp.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Set<Role> getRoleByName(String[] roles);
}