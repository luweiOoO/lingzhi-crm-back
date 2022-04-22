import request from '@/utils/request'

// 查询客户联系人联系电话信息列表
export function listPhone(query) {
  return request({
    url: '/customer/phone/list',
    method: 'get',
    params: query
  })
}

// 查询客户联系人联系电话信息详细
export function getPhone(id) {
  return request({
    url: '/customer/phone/' + id,
    method: 'get'
  })
}

// 新增客户联系人联系电话信息
export function addPhone(data) {
  return request({
    url: '/customer/phone',
    method: 'post',
    data: data
  })
}

// 修改客户联系人联系电话信息
export function updatePhone(data) {
  return request({
    url: '/customer/phone',
    method: 'put',
    data: data
  })
}

// 删除客户联系人联系电话信息
export function delPhone(id) {
  return request({
    url: '/customer/phone/' + id,
    method: 'delete'
  })
}

// 导出客户联系人联系电话信息
export function exportPhone(query) {
  return request({
    url: '/customer/phone/export',
    method: 'get',
    params: query
  })
}