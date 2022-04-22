import request from '@/utils/request'

// 查询客户公海分组信息列表
export function listGroup(query) {
  return request({
    url: '/customer/publicGroup/list',
    method: 'get',
    params: query
  })
}

// 查询客户公海分组信息详细
export function getGroup(id) {
  return request({
    url: '/customer/publicGroup/' + id,
    method: 'get'
  })
}

// 新增客户公海分组信息
export function addGroup(data) {
  return request({
    url: '/customer/publicGroup',
    method: 'post',
    data: data
  })
}

// 修改客户公海分组信息
export function updateGroup(data) {
  return request({
    url: '/customer/publicGroup',
    method: 'put',
    data: data
  })
}

// 删除客户公海分组信息
export function delGroup(id) {
  return request({
    url: '/customer/publicGroup/' + id,
    method: 'delete'
  })
}

// 导出客户公海分组信息
export function exportGroup(query) {
  return request({
    url: '/customer/publicGroup/export',
    method: 'get',
    params: query
  })
}

// 查询客户公海分组信息列表（下拉框）
export function selectPublicGroupList() {
  return request({
    url: '/customer/publicGroup/selectPublicGroupList',
    method: 'get'
  })
}