import request from '@/utils/request'

// 查询客户公司联系人信息列表
export function listContacts(query) {
  return request({
    url: '/customer/contacts/list',
    method: 'get',
    params: query
  })
}

// 查询客户公司联系人信息详细
export function getContacts(id) {
  return request({
    url: '/customer/contacts/' + id,
    method: 'get'
  })
}

// 新增客户公司联系人信息
export function addContacts(data) {
  return request({
    url: '/customer/contacts',
    method: 'post',
    data: data
  })
}

// 修改客户公司联系人信息
export function updateContacts(data) {
  return request({
    url: '/customer/contacts',
    method: 'put',
    data: data
  })
}

// 删除客户公司联系人信息
export function delContacts(id) {
  return request({
    url: '/customer/contacts/' + id,
    method: 'delete'
  })
}

// 导出客户公司联系人信息
export function exportContacts(query) {
  return request({
    url: '/customer/contacts/export',
    method: 'get',
    params: query
  })
}