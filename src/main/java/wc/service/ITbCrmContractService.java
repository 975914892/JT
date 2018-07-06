package wc.service;

import java.util.List;

import wc.entity.ContractVO;
import wc.entity.ContractVO2;
import wc.entity.PositionVO;
import wc.entity.TbCrmContract;

public interface ITbCrmContractService {

	List<ContractVO> findByPage(int startNo, int endNo);

	int findtotal();

	List<ContractVO2> findByContractVO2();

	ContractVO2 findByIdContractVO2(int tcbsId);

	Long findByUsername(String temp);

	int insert(TbCrmContract tcc);

	TbCrmContract findById(int id);

	int update(TbCrmContract tcc);

	int deleteById(Integer id);

	int findByNumberTotal(String number);

	List<ContractVO> findByNumberPage(int startNo, int endNo, String number);

}
