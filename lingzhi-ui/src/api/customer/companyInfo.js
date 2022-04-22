import request from '@/utils/request'

// 查询客户公司基础信息列表
export function listInfo(query) {
  return request({
    url: '/customer/company/info/list',
    method: 'get',
    params: query
  })
}

// 查询客户公司基础信息详细
export function getInfo(id) {
  return request({
    url: '/customer/company/info/' + id,
    method: 'get'
  })
}

// 新增客户公司基础信息
export function addInfo(data) {
  return request({
    url: '/customer/company/info',
    method: 'post',
    data: data
  })
}

// 修改客户公司基础信息
export function updateInfo(data) {
  return request({
    url: '/customer/company/info',
    method: 'put',
    data: data
  })
}

// 删除客户公司基础信息
export function delInfo(id) {
  return request({
    url: '/customer/company/info/' + id,
    method: 'delete'
  })
}

// 导出客户公司基础信息
export function exportInfo(query) {
  return request({
    url: '/customer/company/info/export',
    method: 'get',
    params: query
  })
}