import request from '@/utils/axios'

//条件分页查询
export const page = (query) => {
    let baseUrl = '/model/page'
    let url = baseUrl + (query.current &&  query.size ? '?current=' + query.current + '&size=' + query.size : '')
    return request({
        url: url,
        method: 'post',
        data: query
    });
};
// 删除 ids是数组类型
export const remove = (ids) => {
    return request({
        url: '/model/remove',
        method: 'post',
        params: ids
    });
};

export const saveByProperties = (info) => {
    return request({
        url: '/model/saveByProperties',
        method: 'post',
        data: info
    });
};