import request from '@/utils/axios'


//条件分页查询
export const page = (query) => {
    return request({
        url: '/fieldTree/page',
        method: 'post',
        data: query
    });
};

export const list = () => {
    return request({
        url: '/fieldTree/list',
        method: 'get',
        params: {}
    });
};

// 判断该字段树名称是否已经存在
export const hasFieldTreeName = (param) => {
    return request({
        url: '/fieldTree/hasFieldTreeName',
        method: 'get',
        params: param
    });
};


// 删除 ids是数组类型
export const remove = (ids) => {
    return request({
        url: '/fieldTree/remove',
        method: 'post',
        data: ids
    });
};

// 删除 ids是数组类型
export const addNode = (node) => {
    return request({
        url: '/fieldTree/save',
        method: 'post',
        data: node
    });
};

export const updateNode = (info) => {
    return request({
        url: '/fieldTree/updateNode',
        method: 'post',
        data: info
    });
};