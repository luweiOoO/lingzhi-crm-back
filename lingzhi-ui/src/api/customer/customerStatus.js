import request from '@/utils/request'

// 查询客户状态信息列表
export function listStatus(query) {
  return request({
    url: '/customer/status/list',
    method: 'get',
    params: query
  })
}

// 查询客户状态信息详细
export function getStatus(id) {
  return request({
    url: '/customer/status/' + id,
    method: 'get'
  })
}

// 新增客户状态信息
export function addStatus(data) {
  return request({
    url: '/customer/status',
    method: 'post',
    data: data
  })
}

// 修改客户状态信息
export function updateStatus(data) {
  return request({
    url: '/customer/status',
    method: 'put',
    data: data
  })
}

// 删除客户状态信息
export function delStatus(id) {
  return request({
    url: '/customer/status/' + id,
    method: 'delete'
  })
}

// 导出客户状态信息
export function exportStatus(query) {
  return request({
    url: '/customer/status/export',
    method: 'get',
    params: query
  })
}

// 查询客户状态信息列表(下拉框)
export function selectStatusList() {
  return request({
    url: '/customer/status/selectStatusList',
    method: 'get'
  })
}
