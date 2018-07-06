package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbCrmProduct;
import wc.entity.TbCrmProductCategory;

public interface ITbCrmProductCategoryService {

    //��ѯ����
    public List<TbCrmProductCategory> selectAll();
    //���
    public int insert(TbCrmProductCategory record);
    //�޸�
    public int updateByPrimaryKey(TbCrmProductCategory record);
    
    //�޸�ǰ��ѯ
    public TbCrmProductCategory listByid(Integer id);
    
    //��ҳ��ѯ
    public List<TbCrmProductCategory> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    
    //ɾ��
    public  boolean deleteById(Integer id);
}
