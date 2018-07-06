package wc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbCrmSmsTemplate;

public interface ITbCrmSmsTemplateDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_SMS_TEMPLATE
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_SMS_TEMPLATE
     *
     * @mbg.generated
     */
    int insert(TbCrmSmsTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_SMS_TEMPLATE
     *
     * @mbg.generated
     */
    TbCrmSmsTemplate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_SMS_TEMPLATE
     *
     * @mbg.generated
     */
    List<TbCrmSmsTemplate> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_SMS_TEMPLATE
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbCrmSmsTemplate record);

	List<TbCrmSmsTemplate> findByPage(@Param("startNo")int startNo, @Param("endNo")int endNo);

	int findTotal();
}