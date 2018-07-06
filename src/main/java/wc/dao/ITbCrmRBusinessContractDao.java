package wc.dao;

import java.util.List;
import wc.entity.TbCrmRBusinessContract;

public interface ITbCrmRBusinessContractDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CONTRACT
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CONTRACT
     *
     * @mbg.generated
     */
    int insert(TbCrmRBusinessContract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CONTRACT
     *
     * @mbg.generated
     */
    TbCrmRBusinessContract selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CONTRACT
     *
     * @mbg.generated
     */
    List<TbCrmRBusinessContract> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_R_BUSINESS_CONTRACT
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbCrmRBusinessContract record);
}