import request from '@/utils/axios'

// 测试本地数据
export const fetchData = query => {
    return request({
        url: './design.json',
        method: 'get',
        params: query
    });
};

// 新增
export const save = (item) => {
    return request({
        url: '/field/save',
        method: 'post',
        data: item
    });
};

// 修改
export const update = (item) => {
    return request({
        url: '/field/update',
        method: 'post',
        data: item
    });
};

// 删除 ids是数组类型
export const remove = (ids) => {
    return request({
        url: '/field/remove',
        method: 'post',
        data: ids
    });
};

// 查询单个对象
export const get = (item) => {
    return request({
        url: '/field/get',
        method: 'get',
        params: item
    });
};

export const list = (query) => {
    return request({
        url: '/field/list',
        method: 'post',
        data: query
    });
};

//条件分页查询
export const page = (query) => {
    let baseUrl = '/field/page'
    let url = baseUrl + (query.current &&  query.size ? '?current=' + query.current + '&size=' + query.size : '')
    console.log(url)
    return request({
        url: url,
        method: 'post',
        data: query
    });
};

