package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Record;

/**
 * 消费记录Service接口
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
public interface IRecordService 
{
    /**
     * 查询消费记录
     * 
     * @param id 消费记录ID
     * @return 消费记录
     */
    public Record selectRecordById(String id);

    /**
     * 查询消费记录列表
     * 
     * @param record 消费记录
     * @return 消费记录集合
     */
    public List<Record> selectRecordList(Record record);

    /**
     * 新增消费记录
     * 
     * @param record 消费记录
     * @return 结果
     */
    public int insertRecord(Record record);

    /**
     * 修改消费记录
     * 
     * @param record 消费记录
     * @return 结果
     */
    public int updateRecord(Record record);

    /**
     * 批量删除消费记录
     * 
     * @param ids 需要删除的消费记录ID
     * @return 结果
     */
    public int deleteRecordByIds(String[] ids);

    /**
     * 删除消费记录信息
     * 
     * @param id 消费记录ID
     * @return 结果
     */
    public int deleteRecordById(String id);
}
