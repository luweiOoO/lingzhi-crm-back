import request from '@/utils/request'

// 查询生产指示单产品详情列表
export function listOrderDetail(query) {
  return request({
    url: '/orderDetail/list',
    method: 'get',
    params: query
  })
}

// 查询生产指示单产品详情详细
export function getOrderDetail(id) {
  return request({
    url: '/orderDetail/' + id,
    method: 'get'
  })
}

// 新增生产指示单产品详情
export function addOrderDetail(data) {
  return request({
    url: '/orderDetail',
    method: 'post',
    data: data
  })
}

// 修改生产指示单产品详情
export function updateOrderDetail(data) {
  return request({
    url: '/orderDetail',
    method: 'put',
    data: data
  })
}

// 删除生产指示单产品详情
export function delOrderDetail(id) {
  return request({
    url: '/orderDetail/' + id,
    method: 'delete'
  })
}

// 导出生产指示单产品详情
export function exportOrderDetail(query) {
  return request({
    url: '/orderDetail/export',
    method: 'get',
    params: query
  })
}