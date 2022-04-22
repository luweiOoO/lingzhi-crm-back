import request from '@/utils/request'

// 查询客户标签列表
export function listLabel(query) {
  return request({
    url: '/customer/label/list',
    method: 'get',
    params: query
  })
}

// 查询客户标签详细
export function getLabel(id) {
  return request({
    url: '/customer/label/' + id,
    method: 'get'
  })
}

// 新增客户标签
export function addLabel(data) {
  return request({
    url: '/customer/label',
    method: 'post',
    data: data
  })
}

// 修改客户标签
export function updateLabel(data) {
  return request({
    url: '/customer/label',
    method: 'put',
    data: data
  })
}

// 删除客户标签
export function delLabel(id) {
  return request({
    url: '/customer/label/' + id,
    method: 'delete'
  })
}

// 导出客户标签
export function exportLabel(query) {
  return request({
    url: '/customer/label/export',
    method: 'get',
    params: query
  })
}

// 查询客户标签列表(下拉框)
export function selectLabelList() {
  return request({
    url: '/customer/label/selectLabelList',
    method: 'get'
  })
}