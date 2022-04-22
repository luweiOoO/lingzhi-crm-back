import request from '@/utils/request'

// 查询生产指示小单基础信息列表
export function listOrder(query) {
  return request({
    url: '/order/list',
    method: 'get',
    params: query
  })
}

// 查询生产指示小单基础信息详细
export function getOrder(id) {
  return request({
    url: '/order/' + id,
    method: 'get'
  })
}

// 新增生产指示小单基础信息
export function addOrder(data) {
  return request({
    url: '/order',
    method: 'post',
    data: data
  })
}

// 修改生产指示小单基础信息
export function updateOrder(data) {
  return request({
    url: '/order',
    method: 'put',
    data: data
  })
}

// 删除生产指示小单基础信息
export function delOrder(id) {
  return request({
    url: '/order/' + id,
    method: 'delete'
  })
}

// 导出生产指示小单基础信息
export function exportOrder(query) {
  return request({
    url: '/order/export',
    method: 'get',
    params: query
  })
}

// 导入生产指示小单基础信息
export function importOrder(data) {
  return request({
    url: '/order/importOrder',
    method: 'post',
    data: data
  })
}

// 绑定生产指示单运单号
export function bindWaybill(data){
  return request({
    url: '/order/bindWaybill',
    method: 'post',
    data: data
  })
}