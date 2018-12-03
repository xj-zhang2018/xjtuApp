package service;

import java.util.Date;
import java.util.List;

import pojo.Record;


public interface RecordManager {
	public Record regist(Record u);
	public List<Record> findAllRecord();
	public List<Record> findRangeRecord(Date beginTime,Date endTime);
}
