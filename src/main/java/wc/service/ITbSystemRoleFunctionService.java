package wc.service;

import java.util.List;

import wc.entity.TbSystemRoleFunction;

public interface ITbSystemRoleFunctionService {

    //��Ȩ
    public int insert(TbSystemRoleFunction record);
    public List<TbSystemRoleFunction> findByRoleId(Integer roleId);
    public int deleteByRoleId(Integer roleId);
}
