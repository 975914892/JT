package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbCrmProduct;

public interface ITbCrmProductService {

    //���
    public int insert(TbCrmProduct record);
    //�޸�
    public int updateByPrimaryKey(TbCrmProduct record);
    
    //�޸�ǰ��ѯ
    public TbCrmProduct listByid(Integer id);
    
    //��ҳ��ѯ
    public List<TbCrmProduct> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    
    //ɾ��
    public  boolean deleteById(Integer id);
	
    //��ҳ��ѯ(ģ����ѯ)
    public List<TbCrmProduct> findByNamePage(@Param("page")int page,@Param("pagesize")int pagesize,@Param("name")String name);
    
    //��ҳ��ѯtotal(ģ����ѯ)
    public int findByNameTotal(String name);
}
