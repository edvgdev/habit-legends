package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.dto.RoleDTO;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO getRoleById(Integer id);

    List<RoleDTO> getAllRoles();

    RoleDTO updateRole(Integer id, RoleDTO roleDTO);

    void deleteRole(Integer id);
}
