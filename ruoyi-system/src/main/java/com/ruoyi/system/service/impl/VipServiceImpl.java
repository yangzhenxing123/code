package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.UUID;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.domain.Record;
import com.ruoyi.system.util.TokenService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.VipMapper;
import com.ruoyi.system.domain.Vip;
import com.ruoyi.system.service.IVipService;

/**
 * vipService业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-14
 */
@Service
public class VipServiceImpl implements IVipService 
{
    @Autowired
    private VipMapper vipMapper;

    @Autowired
    private RecordServiceImpl recordService;



    /**
     * 查询vip
     * 
     * @param id vipID
     * @return vip
     */
    @Override
    public Vip selectVipById(String id)
    {
        return vipMapper.selectVipById(id);
    }

    /**
     * 查询vip列表
     * 
     * @param vip vip
     * @return vip
     */
    @Override
    public List<Vip> selectVipList(Vip vip)
    {
        return vipMapper.selectVipList(vip);
    }

    /**
     * 新增vip
     * 
     * @param vip vip
     * @return 结果
     */
    @Override
    public int insertVip(Vip vip)
    {
        // 获取当前的用户
        LoginUser loginUser = SpringUtils.getBean(TokenService1.class).getLoginUser(ServletUtils.getRequest());
        vip.setCreateUserId(loginUser.getUser().getUserId().toString());
        vip.setCreateTime(DateUtils.getNowDate());
        vip.setId(UUID.randomUUID().toString());
        Record record =new Record();
        record.setVipId(vip.getId());
        record.setMoney(vip.getMoney());
        record.setPhone(vip.getPhone());
        record.setCreateUserId(vip.getCreateUserId());
        record.setCreateUserName(vip.getCreateUserName());
        recordService.insertRecord(record);
        return vipMapper.insertVip(vip);
    }

    /**
     * 修改vip
     * 
     * @param vip vip
     * @return 结果
     */
    @Override
    public int updateVip(Vip vip)
    {
        Record record =new Record();
        record.setVipId(vip.getId());
        record.setMoney(vip.getMoney());
        record.setPhone(vip.getPhone());
        record.setCreateUserId(vip.getCreateUserId());
        record.setCreateUserName(vip.getCreateUserName());
        recordService.insertRecord(record);
        return vipMapper.updateVip(vip);
    }

    /**
     * 批量删除vip
     * 
     * @param ids 需要删除的vipID
     * @return 结果
     */
    @Override
    public int deleteVipByIds(String[] ids)
    {
        return vipMapper.deleteVipByIds(ids);
    }

    /**
     * 删除vip信息
     * 
     * @param id vipID
     * @return 结果
     */
    @Override
    public int deleteVipById(String id)
    {
        return vipMapper.deleteVipById(id);
    }
}
