import request from '@/utils/request'

// 查询test列表
export function listVolunteer(query) {
  return request({
    url: '/test/volunteer/list',
    method: 'get',
    params: query
  })
}

// 查询test详细
export function getVolunteer(vtId) {
  return request({
    url: '/test/volunteer/' + vtId,
    method: 'get'
  })
}


// 新增test
export function addVolunteer(data) {
  return request({
    url: '/test/volunteer',
    method: 'post',
    data: data
  })
}

// 修改test
export function updateVolunteer(data) {
  return request({
    url: '/test/volunteer',
    method: 'put',
    data: data
  })
}

// 删除test
export function delVolunteer(vtId) {
  return request({
    url: '/test/volunteer/' + vtId,
    method: 'delete'
  })
}


// 导出志愿者管理
export function exportVolunteer(query) {
  return request({
    url: '/test/volunteer/exportVolunteer',
    method: 'get',
    params: query
  })
}
//importTemplate
export function importTemplate() {
  return request({
    url: '/test/volunteer/importTemplate',
    method: 'get'
  })
}






