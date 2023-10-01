package service.Vinh;
import dao.Vinh.RoleDao;
import model.Vinh.Role;

import java.util.List;

public class RoleService {
    private final RoleDao roleDAO;

    public RoleService() {
        roleDAO = new RoleDao();
    }

    public List<Role> getRoles(){
        return roleDAO.findAll();
    }

    public Role getRole(int id){
        return roleDAO.findById(id);
    }
}
