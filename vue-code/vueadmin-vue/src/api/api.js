import request from '@/utils/axios'

// 查询单个对象
export const get = (item) => {
    return request({
        url: '/api/get',
        method: 'get',
        params: item
    });
};

//条件分页查询
//条件分页查询
export const page = (query) => {
    let baseUrl = '/api/page'
    let url = baseUrl + (query.current &&  query.size ? '?current=' + query.current + '&size=' + query.size : '')
    console.log(url)
    return request({
        url: url,
        method: 'post',
        data: query
    });
};

// 删除 ids是数组类型
export const remove = (ids) => {
    return request({
        url: '/api/remove',
        method: 'post',
        data: ids
    });
};
