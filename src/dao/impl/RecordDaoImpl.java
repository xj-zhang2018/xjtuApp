package dao.impl;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import dao.RecordDao;
import pojo.Record;
@Repository
public class RecordDaoImpl implements RecordDao{
	
	@PersistenceContext(name="myorcl") 
	private EntityManager manager;

	
	@Override
	public Record addRecord(Record u) {
		
		manager.persist(u);
		
		return u;	
		
	}

	@Override
	public List<Record> findAllRecord() {
		String jpql="from Record record";
		List<Record> records=manager.createQuery(jpql).getResultList();
		System.out.println("record is"+records);
		return records;
	}

	@Override
	public List<Record> findRangeRecord(Date beginTime, Date endTime) {
		String jpql="from Record record where record.createDate > :beginTime and record.createDate < :endTime";
		List<Record> records=manager.createQuery(jpql).setParameter("beginTime", beginTime).setParameter("endTime", endTime).getResultList();
		System.out.println("record is"+records);
		return records;
	}
}
