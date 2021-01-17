package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Vip;

/**
 * vipService接口
 * 
 * @author ruoyi
 * @date 2021-01-14
 */
public interface IVipService 
{
    /**
     * 查询vip
     * 
     * @param id vipID
     * @return vip
     */
    public Vip selectVipById(String id);

    /**
     * 查询vip列表
     * 
     * @param vip vip
     * @return vip集合
     */
    public List<Vip> selectVipList(Vip vip);

    /**
     * 新增vip
     * 
     * @param vip vip
     * @return 结果
     */
    public int insertVip(Vip vip);

    /**
     * 修改vip
     * 
     * @param vip vip
     * @return 结果
     */
    public int updateVip(Vip vip);

    /**
     * 批量删除vip
     * 
     * @param ids 需要删除的vipID
     * @return 结果
     */
    public int deleteVipByIds(String[] ids);

    /**
     * 删除vip信息
     * 
     * @param id vipID
     * @return 结果
     */
    public int deleteVipById(String id);

    /**
     * 手机号查重
     * @param phone
     * @return
     */
    Boolean selectPhone(String phone);
}
