import request from '@/utils/request'

// 查询国家地区信息列表
export function listRegion(query) {
  return request({
    url: '/basic/region/list',
    method: 'get',
    params: query
  })
}

// 查询国家地区信息详细
export function getRegion(id) {
  return request({
    url: '/basic/region/' + id,
    method: 'get'
  })
}

// 新增国家地区信息
export function addRegion(data) {
  return request({
    url: '/basic/region',
    method: 'post',
    data: data
  })
}

// 修改国家地区信息
export function updateRegion(data) {
  return request({
    url: '/basic/region',
    method: 'put',
    data: data
  })
}

// 删除国家地区信息
export function delRegion(id) {
  return request({
    url: '/basic/region/' + id,
    method: 'delete'
  })
}

// 导出国家地区信息
export function exportRegion(query) {
  return request({
    url: '/basic/region/export',
    method: 'get',
    params: query
  })
}

// 查询国家地区信息列表（选择时使用）
export function selectRegionList() {
  return request({
    url: '/basic/region/selectList',
    method: 'get'
  })
}