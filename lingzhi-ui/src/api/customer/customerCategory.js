import request from '@/utils/request'

// 查询客户类别列表
export function listCategory(query) {
  return request({
    url: '/customer/category/list',
    method: 'get',
    params: query
  })
}

// 查询客户类别详细
export function getCategory(id) {
  return request({
    url: '/customer/category/' + id,
    method: 'get'
  })
}

// 新增客户类别
export function addCategory(data) {
  return request({
    url: '/customer/category',
    method: 'post',
    data: data
  })
}

// 修改客户类别
export function updateCategory(data) {
  return request({
    url: '/customer/category',
    method: 'put',
    data: data
  })
}

// 删除客户类别
export function delCategory(id) {
  return request({
    url: '/customer/category/' + id,
    method: 'delete'
  })
}

// 导出客户类别
export function exportCategory(query) {
  return request({
    url: '/customer/category/export',
    method: 'get',
    params: query
  })
}

// 查询客户类别列表(下拉框)
export function selectCategoryList() {
  return request({
    url: '/customer/category/selectCategoryList',
    method: 'get'
  })
}