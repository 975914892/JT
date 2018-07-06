package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbSystemFunction;

public interface ITbSystemFunctionService {
	
	public List<TbSystemFunction> findFunctionById(List<Integer> list);
	
    //�޸�ǰ��ѯ
    public TbSystemFunction listByid(Integer functionId);
    //�޸�
    public int updateByPrimaryKey(TbSystemFunction functionId);
    
    //��ҳ��ѯ
    public List<TbSystemFunction> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    
    //ɾ��
    public  boolean deleteById(Integer functionId);
	
    //���
    public int insert(TbSystemFunction record);
}
