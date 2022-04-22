import request from '@/utils/request'

// 查询客户联系人社交账号信息列表
export function listPlatform(query) {
  return request({
    url: '/customer/platform/list',
    method: 'get',
    params: query
  })
}

// 查询客户联系人社交账号信息详细
export function getPlatform(id) {
  return request({
    url: '/customer/platform/' + id,
    method: 'get'
  })
}

// 新增客户联系人社交账号信息
export function addPlatform(data) {
  return request({
    url: '/customer/platform',
    method: 'post',
    data: data
  })
}

// 修改客户联系人社交账号信息
export function updatePlatform(data) {
  return request({
    url: '/customer/platform',
    method: 'put',
    data: data
  })
}

// 删除客户联系人社交账号信息
export function delPlatform(id) {
  return request({
    url: '/customer/platform/' + id,
    method: 'delete'
  })
}

// 导出客户联系人社交账号信息
export function exportPlatform(query) {
  return request({
    url: '/customer/platform/export',
    method: 'get',
    params: query
  })
}