package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbCrmMessage;

public interface ITbSystemMessageService {

    //添加
    public int insert(TbCrmMessage record);
    //查看后更改时间
    public int updateByPrimaryKey(TbCrmMessage record);
    
    //查询自己写给别人的信
    public List<TbCrmMessage> selectBymyself(@Param("userId")int userId,@Param("page")int page,@Param("pagesize")int pagesize);
    public int findBmTotal(int userId);
    //查询别人写给自己的信
    public List<TbCrmMessage> selectByother(@Param("userId")int userId,@Param("page")int page,@Param("pagesize")int pagesize);
    public int findBoTotal(int userId);
    //查看
    public TbCrmMessage selectByid(Integer id);
    //删除
    public  boolean deleteById(Integer id);
	
}
