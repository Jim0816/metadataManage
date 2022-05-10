
export const checkNameReg = (rule, value, callback) => {
    // 字段名限制
    if (!value) {
        return callback()
    }
    if (value) {
        setTimeout(() => {
            var reg = /^[a-zA-Z]+[\d\w]*$/
            if (!reg.test(value)) {
                callback(new Error('字母开头且仅包含数字、字母、下划线!'))
            } else {
                callback()
            }
        }, 100)
    }
}