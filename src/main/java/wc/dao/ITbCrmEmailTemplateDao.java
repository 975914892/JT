package wc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbCrmEmailTemplate;
import wc.entity.TbCrmSmsTemplate;

public interface ITbCrmEmailTemplateDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMAIL_TEMPLATE
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMAIL_TEMPLATE
     *
     * @mbg.generated
     */
    int insert(TbCrmEmailTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMAIL_TEMPLATE
     *
     * @mbg.generated
     */
    TbCrmEmailTemplate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMAIL_TEMPLATE
     *
     * @mbg.generated
     */
    List<TbCrmEmailTemplate> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMAIL_TEMPLATE
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbCrmEmailTemplate record);

	List<TbCrmEmailTemplate> findByPage(@Param("startNo")int startNo, @Param("endNo")int endNo);

	int findTotal();
}