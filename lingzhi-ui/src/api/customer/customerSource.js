import request from '@/utils/request'

// 查询客户来源信息列表
export function listSource(query) {
  return request({
    url: '/customer/source/list',
    method: 'get',
    params: query
  })
}

// 查询客户来源信息详细
export function getSource(id) {
  return request({
    url: '/customer/source/' + id,
    method: 'get'
  })
}

// 新增客户来源信息
export function addSource(data) {
  return request({
    url: '/customer/source',
    method: 'post',
    data: data
  })
}

// 修改客户来源信息
export function updateSource(data) {
  return request({
    url: '/customer/source',
    method: 'put',
    data: data
  })
}

// 删除客户来源信息
export function delSource(id) {
  return request({
    url: '/customer/source/' + id,
    method: 'delete'
  })
}

// 导出客户来源信息
export function exportSource(query) {
  return request({
    url: '/customer/source/export',
    method: 'get',
    params: query
  })
}

// 导出客户来源信息
export function selectSourceList() {
  return request({
    url: '/customer/source/selectList',
    method: 'get'
  })
}