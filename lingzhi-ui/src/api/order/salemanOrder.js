import request from '@/utils/request'

// 查询业务员订单信息列表
export function listSalesmanOrder(query) {
  return request({
    url: '/salesman/list',
    method: 'get',
    params: query
  })
}

// 导入业务员订单信息
export function importSalesmanOrder(id) {
  return request({
    url: '/salesman/import',
    method: 'post',
    data: data
  })
}