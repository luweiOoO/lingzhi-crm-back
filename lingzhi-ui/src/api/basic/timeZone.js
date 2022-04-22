import request from '@/utils/request'

// 查询世界时区信息列表
export function listZone(query) {
  return request({
    url: '/basic/timeZone/list',
    method: 'get',
    params: query
  })
}

// 查询世界时区信息详细
export function getZone(id) {
  return request({
    url: '/basic/timeZone/' + id,
    method: 'get'
  })
}

// 新增世界时区信息
export function addZone(data) {
  return request({
    url: '/basic/timeZone',
    method: 'post',
    data: data
  })
}

// 修改世界时区信息
export function updateZone(data) {
  return request({
    url: '/basic/timeZone',
    method: 'put',
    data: data
  })
}

// 删除世界时区信息
export function delZone(id) {
  return request({
    url: '/basic/timeZone/' + id,
    method: 'delete'
  })
}

// 导出世界时区信息
export function exportZone(query) {
  return request({
    url: '/basic/timeZone/export',
    method: 'get',
    params: query
  })
}

// 查询世界时区信息列表(下拉框)
export function selectTimeZoneList() {
  return request({
    url: '/basic/timeZone/selectTimeZoneList',
    method: 'get'
  })
}