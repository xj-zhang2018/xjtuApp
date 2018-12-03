package service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.RecordDao;
import pojo.Record;
import service.RecordManager;

@Service
public class RecordManagerImpl implements RecordManager {
	@Autowired
	private RecordDao recordDao;
	
	@Transactional
	@Override
	public Record regist(Record u) {
		return recordDao.addRecord(u);
	}
	
	
	@Transactional
	@Override
	public List<Record> findAllRecord() {
		return recordDao.findAllRecord();
	}


	@Override
	public List<Record> findRangeRecord(Date beginTime, Date endTime) {
		
		return recordDao.findRangeRecord(beginTime, endTime);
	}
	
}
