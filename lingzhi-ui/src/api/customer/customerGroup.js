import request from '@/utils/request'

// 查询客户分组信息列表
export function listGroup(query) {
  return request({
    url: '/customer/group/list',
    method: 'get',
    params: query
  })
}

// 查询客户分组信息详细
export function getGroup(id) {
  return request({
    url: '/customer/group/' + id,
    method: 'get'
  })
}

// 新增客户分组信息
export function addGroup(data) {
  return request({
    url: '/customer/group',
    method: 'post',
    data: data
  })
}

// 修改客户分组信息
export function updateGroup(data) {
  return request({
    url: '/customer/group',
    method: 'put',
    data: data
  })
}

// 删除客户分组信息
export function delGroup(id) {
  return request({
    url: '/customer/group/' + id,
    method: 'delete'
  })
}

// 导出客户分组信息
export function exportGroup(query) {
  return request({
    url: '/customer/group/export',
    method: 'get',
    params: query
  })
}

// 查询客户分组信息列表（下拉框）
export function selectGroupList() {
  return request({
    url: '/customer/group/selectGroupList',
    method: 'get'
    })
}