import request from '@/utils/request'

// 查询社交平台信息列表
export function listPlatform(query) {
  return request({
    url: '/basic/social/platform/list',
    method: 'get',
    params: query
  })
}

// 查询社交平台信息详细
export function getPlatform(id) {
  return request({
    url: '/basic/social/platform/' + id,
    method: 'get'
  })
}

// 新增社交平台信息
export function addPlatform(data) {
  return request({
    url: '/basic/social/platform',
    method: 'post',
    data: data
  })
}

// 修改社交平台信息
export function updatePlatform(data) {
  return request({
    url: '/basic/social/platform',
    method: 'put',
    data: data
  })
}

// 删除社交平台信息
export function delPlatform(id) {
  return request({
    url: '/basic/social/platform/' + id,
    method: 'delete'
  })
}

// 导出社交平台信息
export function exportPlatform(query) {
  return request({
    url: '/basic/social/platform/export',
    method: 'get',
    params: query
  })
}

// 查询社交平台信息列表(下拉框)
export function selectPlatformList() {
  return request({
    url: '/basic/social/platform/selectPlatformList',
    method: 'get'
  })
}