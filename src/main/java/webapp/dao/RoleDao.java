package webapp.dao;

import webapp.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> getAllRoles();
    Set<Role> getRoleByName(String[] roles);
}