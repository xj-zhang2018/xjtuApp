package dao;
import java.util.Date;
import java.util.List;
import pojo.Record;

public interface RecordDao {
	public Record addRecord(Record u);
	public List<Record> findAllRecord();
	public List<Record> findRangeRecord(Date beginTime,Date endTime);
	
	
}
