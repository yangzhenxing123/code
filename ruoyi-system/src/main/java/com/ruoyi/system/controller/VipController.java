package com.ruoyi.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Vip;
import com.ruoyi.system.service.IVipService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * vipController
 * 
 * @author ruoyi
 * @date 2021-01-14
 */
@RestController
@RequestMapping("/system/vip")
public class VipController extends BaseController
{
    @Autowired
    private IVipService vipService;

    /**
     * 查询vip列表
     */
    @PreAuthorize("@ss.hasPermi('system:vip:list')")
    @GetMapping("/list")
    public TableDataInfo list(Vip vip)
    {
        startPage();
        List<Vip> list = vipService.selectVipList(vip);
        return getDataTable(list);
    }

    /**
     * 导出vip列表
     */
    @PreAuthorize("@ss.hasPermi('system:vip:export')")
    @Log(title = "vip", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Vip vip)
    {
        List<Vip> list = vipService.selectVipList(vip);
        ExcelUtil<Vip> util = new ExcelUtil<Vip>(Vip.class);
        return util.exportExcel(list, "vip");
    }

    /**
     * 获取vip详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:vip:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(vipService.selectVipById(id));
    }

    /**
     * 手机号查重
     */
    @GetMapping("/selectPhone")
    public Boolean selectPhone(@RequestParam String phone)
    {
        return vipService.selectPhone(phone);
    }

    /**
     * 新增vip
     */
    @PreAuthorize("@ss.hasPermi('system:vip:add')")
    @Log(title = "vip", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Vip vip)
    {
        return toAjax(vipService.insertVip(vip));
    }

    /**
     * 修改vip
     */
    @PreAuthorize("@ss.hasPermi('system:vip:edit')")
    @Log(title = "vip", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Vip vip)
    {
        return toAjax(vipService.updateVip(vip));
    }

    /**
     * 删除vip
     */
    @PreAuthorize("@ss.hasPermi('system:vip:remove')")
    @Log(title = "vip", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(vipService.deleteVipByIds(ids));
    }
}
