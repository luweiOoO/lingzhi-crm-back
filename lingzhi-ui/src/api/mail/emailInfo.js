import request from '@/utils/request'

// 查询邮箱账户信息列表
export function listInfo(query) {
  return request({
    url: '/email/info/list',
    method: 'get',
    params: query
  })
}

// 查询邮箱账户信息详细
export function getInfo(emailId) {
  return request({
    url: '/email/info/' + emailId,
    method: 'get'
  })
}

// 新增邮箱账户信息
export function addInfo(data) {
  return request({
    url: '/email/info',
    method: 'post',
    data: data
  })
}

// 修改邮箱账户信息
export function updateInfo(data) {
  return request({
    url: '/email/info',
    method: 'put',
    data: data
  })
}

// 删除邮箱账户信息
export function delInfo(emailId) {
  return request({
    url: '/email/info/' + emailId,
    method: 'delete'
  })
}

// 导出邮箱账户信息
export function exportInfo(query) {
  return request({
    url: '/email/info/export',
    method: 'get',
    params: query
  })
}

//邮箱绑定人修改
export function bindChange(data) {
  return request({
    url: '/email/info/bindChange',
    method: 'post',
    data: data
  })
}