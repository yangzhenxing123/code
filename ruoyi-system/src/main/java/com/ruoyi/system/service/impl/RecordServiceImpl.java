package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.UUID;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RecordMapper;
import com.ruoyi.system.domain.Record;
import com.ruoyi.system.service.IRecordService;

/**
 * 消费记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
@Service
public class RecordServiceImpl implements IRecordService 
{
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 查询消费记录
     * 
     * @param id 消费记录ID
     * @return 消费记录
     */
    @Override
    public Record selectRecordById(String id)
    {
        return recordMapper.selectRecordById(id);
    }

    /**
     * 查询消费记录列表
     * 
     * @param record 消费记录
     * @return 消费记录
     */
    @Override
    public List<Record> selectRecordList(Record record)
    {
        return recordMapper.selectRecordList(record);
    }

    /**
     * 新增消费记录
     * 
     * @param record 消费记录
     * @return 结果
     */
    @Override
    public int insertRecord(Record record)
    {
        record.setId(UUID.randomUUID().toString());
        return recordMapper.insertRecord(record);
    }

    /**
     * 修改消费记录
     * 
     * @param record 消费记录
     * @return 结果
     */
    @Override
    public int updateRecord(Record record)
    {
        return recordMapper.updateRecord(record);
    }

    /**
     * 批量删除消费记录
     * 
     * @param ids 需要删除的消费记录ID
     * @return 结果
     */
    @Override
    public int deleteRecordByIds(String[] ids)
    {
        return recordMapper.deleteRecordByIds(ids);
    }

    /**
     * 删除消费记录信息
     * 
     * @param id 消费记录ID
     * @return 结果
     */
    @Override
    public int deleteRecordById(String id)
    {
        return recordMapper.deleteRecordById(id);
    }
}
