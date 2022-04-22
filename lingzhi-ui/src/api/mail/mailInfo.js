import request from '@/utils/request'

// 查询邮件基础信息列表
export function listInfo(query) {
  return request({
    url: '/mail/info/list',
    method: 'get',
    params: query
  })
}

// 查询邮件基础信息详细
export function getInfo(mailId) {
  return request({
    url: '/mail/info/' + mailId,
    method: 'get'
  })
}

// 新增邮件基础信息
export function addInfo(data) {
  return request({
    url: '/mail/info',
    method: 'post',
    data: data
  })
}

// 修改邮件基础信息
export function updateInfo(data) {
  return request({
    url: '/mail/info',
    method: 'put',
    data: data
  })
}

// 删除邮件基础信息
export function delInfo(mailId) {
  return request({
    url: '/mail/info/' + mailId,
    method: 'delete'
  })
}

// 导出邮件基础信息
export function exportInfo(query) {
  return request({
    url: '/mail/info/export',
    method: 'get',
    params: query
  })
}