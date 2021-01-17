package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Vip;
import org.apache.ibatis.annotations.Param;

/**
 * vipMapper接口
 * 
 * @author ruoyi
 * @date 2021-01-14
 */
public interface VipMapper 
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
     * 删除vip
     * 
     * @param id vipID
     * @return 结果
     */
    public int deleteVipById(String id);

    /**
     * 批量删除vip
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVipByIds(String[] ids);

    /**
     * 手机号查重
     * @param phone
     * @param createUserId
     * @return
     */
    int selectPhone(@Param("phone") String phone,@Param("createUserId") String createUserId);
}
