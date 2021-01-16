import request from '@/utils/request'

// 查询vip列表
export function listVip(query) {
  return request({
    url: '/system/vip/list',
    method: 'get',
    params: query
  })
}

// 查询vip详细
export function getVip(id) {
  return request({
    url: '/system/vip/' + id,
    method: 'get'
  })
}

// 新增vip
export function addVip(data) {
  return request({
    url: '/system/vip',
    method: 'post',
    data: data
  })
}

// 修改vip
export function updateVip(data) {
  return request({
    url: '/system/vip',
    method: 'put',
    data: data
  })
}

// 删除vip
export function delVip(id) {
  return request({
    url: '/system/vip/' + id,
    method: 'delete'
  })
}

// 导出vip
export function exportVip(query) {
  return request({
    url: '/system/vip/export',
    method: 'get',
    params: query
  })
}