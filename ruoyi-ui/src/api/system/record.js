import request from '@/utils/request'

// 查询消费记录列表
export function listRecord(query) {
  return request({
    url: '/system/record/list',
    method: 'get',
    params: query
  })
}

// 查询消费记录详细
export function getRecord(id) {
  return request({
    url: '/system/record/' + id,
    method: 'get'
  })
}

// 新增消费记录
export function addRecord(data) {
  return request({
    url: '/system/record',
    method: 'post',
    data: data
  })
}

// 修改消费记录
export function updateRecord(data) {
  return request({
    url: '/system/record',
    method: 'put',
    data: data
  })
}

// 删除消费记录
export function delRecord(id) {
  return request({
    url: '/system/record/' + id,
    method: 'delete'
  })
}

// 导出消费记录
export function exportRecord(query) {
  return request({
    url: '/system/record/export',
    method: 'get',
    params: query
  })
}