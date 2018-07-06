package wc.service;

import java.util.List;

import wc.entity.PositionVO;
import wc.entity.TbCrmPosition;

public interface ITbCrmPositionService {
	/**
	 * ��ҳ��ѯ
	 */
	List<PositionVO> findByPage(int startNo, int endNo);
	/**
	 * ��ҳ��ѯ������
	 */
	int findtotal();
	List<TbCrmPosition> findALL();
	int insert(TbCrmPosition tcp);
	TbCrmPosition findById(int id);
	int update(TbCrmPosition tcp);
	int deleteById(Integer id);
	int findByNameTotal(String name);
	List<PositionVO> findByNamePage(int startNo, int endNo, String name);

}
