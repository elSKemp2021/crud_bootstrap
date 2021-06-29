package webapp.dao;

import org.springframework.stereotype.Repository;
import webapp.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Set<Role> getRoleByName(String[] roles) {
        Set<Role> roleSet = new HashSet<>();

        for (String role : roles) {
            Query query = entityManager.createQuery("select  r from  Role r where r.name = :name", Role.class);
            query.setParameter("name", role);
            roleSet.add((Role) query.getSingleResult());
        }
        return roleSet;
    }


}