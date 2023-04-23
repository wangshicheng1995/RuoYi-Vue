import request from '@/utils/request'

// 查询已有的 Master Data
export function getOldMasterData(query) {
    return request({
      url: '/dataEntry/getOldMasterData',
      method: 'get',
      params: query
    })
}

// 查询用户导入的 Master Data
export function getNewMasterData(query) {
    return request({
      url: '/dataEntry/getNewMasterData',
      method: 'get',
      params: query
    })
}

// 对比结果
export function getCompareResult(query) {
  return request({
    url: '/dataEntry/getCompareResult',
    method: 'get',
    params: query
  })
}