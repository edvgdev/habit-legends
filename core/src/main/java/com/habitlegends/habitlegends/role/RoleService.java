package com.habitlegends.habitlegends.role;

import java.util.List;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO getRoleById(Integer id);

    List<RoleDTO> getAllRoles();

    RoleDTO updateRole(Integer id, RoleDTO roleDTO);

    void deleteRole(Integer id);
}
