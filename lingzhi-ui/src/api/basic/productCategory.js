import request from '@/utils/request'

// 查询产品类别信息列表
export function listCategory(query) {
  return request({
    url: '/basic/product/category/list',
    method: 'get',
    params: query
  })
}

// 查询产品类别信息详细
export function getCategory(id) {
  return request({
    url: '/basic/product/category/' + id,
    method: 'get'
  })
}

// 新增产品类别信息
export function addCategory(data) {
  return request({
    url: '/basic/product/category',
    method: 'post',
    data: data
  })
}

// 修改产品类别信息
export function updateCategory(data) {
  return request({
    url: '/basic/product/category',
    method: 'put',
    data: data
  })
}

// 删除产品类别信息
export function delCategory(id) {
  return request({
    url: '/basic/product/category/' + id,
    method: 'delete'
  })
}

// 导出产品类别信息
export function exportCategory(query) {
  return request({
    url: '/basic/product/category/export',
    method: 'get',
    params: query
  })
}

// 查询产品类别树形结构
export function selectCategoryTreeList() {
  return request({
    url: '/basic/product/category/categoryTree',
    method: 'get'
  })
}