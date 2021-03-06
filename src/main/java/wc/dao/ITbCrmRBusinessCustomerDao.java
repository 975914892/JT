package wc.dao;

import java.util.List;
import wc.entity.TbCrmRBusinessCustomer;

public interface ITbCrmRBusinessCustomerDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CUSTOMER
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CUSTOMER
     *
     * @mbg.generated
     */
    int insert(TbCrmRBusinessCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CUSTOMER
     *
     * @mbg.generated
     */
    TbCrmRBusinessCustomer selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CUSTOMER
     *
     * @mbg.generated
     */
    List<TbCrmRBusinessCustomer> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CUSTOMER
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbCrmRBusinessCustomer record);
}