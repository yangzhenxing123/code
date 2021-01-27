package com.ruoyi.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Record;
import com.ruoyi.system.service.IRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 消费记录Controller
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
@RestController
@RequestMapping("/system/record")
public class RecordController extends BaseController
{
    @Autowired
    private IRecordService recordService;

    /**
     * 查询消费记录列表
     */
//    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(Record record)
    {
        startPage();
        List<Record> list = recordService.selectRecordList(record);
        return getDataTable(list);
    }

    /**
     * 导出消费记录列表
     */
//    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "消费记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Record record)
    {
        List<Record> list = recordService.selectRecordList(record);
        ExcelUtil<Record> util = new ExcelUtil<Record>(Record.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取消费记录详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(recordService.selectRecordById(id));
    }

    /**
     * 新增消费记录
     */
//    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "消费记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Record record)
    {
        return toAjax(recordService.insertRecord(record));
    }

    /**
     * 修改消费记录
     */
//    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "消费记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Record record)
    {
        return toAjax(recordService.updateRecord(record));
    }

    /**
     * 删除消费记录
     */
//    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "消费记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(recordService.deleteRecordByIds(ids));
    }
}
