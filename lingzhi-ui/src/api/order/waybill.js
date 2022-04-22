import request from '@/utils/request'

// 查询运单信息列表
export function listWaybill(query) {
  return request({
    url: '/waybill/list',
    method: 'get',
    params: query
  })
}

// 查询运单信息详细
export function getWaybill(id) {
  return request({
    url: '/waybill/' + id,
    method: 'get'
  })
}

// 新增运单信息
export function addWaybill(data) {
  return request({
    url: '/waybill',
    method: 'post',
    data: data
  })
}

// 修改运单信息
export function updateWaybill(data) {
  return request({
    url: '/waybill',
    method: 'put',
    data: data
  })
}

// 删除运单信息
export function delWaybill(id) {
  return request({
    url: '/waybill/' + id,
    method: 'delete'
  })
}

// 导出运单信息
export function exportWaybill(query) {
  return request({
    url: '/waybill/export',
    method: 'get',
    params: query
  })
}