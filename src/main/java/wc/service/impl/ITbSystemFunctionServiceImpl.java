package wc.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import wc.dao.ITbSystemFunctionDao;
import wc.entity.TbSystemFunction;
import wc.service.ITbSystemFunctionService;

@Service("functionService")
public class ITbSystemFunctionServiceImpl implements ITbSystemFunctionService {
	
	@Resource
	private ITbSystemFunctionDao funcDao;
	
	public List<TbSystemFunction> findFunctionById(List<Integer> list) {
		return funcDao.findFunctionById(list);
	}

	public TbSystemFunction listByid(Integer functionId) {
		// TODO Auto-generated method stub
		return funcDao.listByid(functionId);
	}

	public int updateByPrimaryKey(TbSystemFunction functionId) {
		// TODO Auto-generated method stub
		return funcDao.updateByPrimaryKey(functionId);
	}

	public List<TbSystemFunction> findByPage(int page, int pagesize) {
		// TODO Auto-generated method stub
		return funcDao.findByPage(page, pagesize);
	}

	public int findTotal() {
		// TODO Auto-generated method stub
		return funcDao.findTotal();
	}

	public boolean deleteById(Integer functionId) {
		// TODO Auto-generated method stub
		return funcDao.deleteById(functionId);
	}

	public int insert(TbSystemFunction record) {
		// TODO Auto-generated method stub
		return funcDao.insert(record);
	}

}
