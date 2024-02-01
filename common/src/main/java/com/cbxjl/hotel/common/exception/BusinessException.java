package com.cbxjl.hotel.common.exception;


import com.cbxjl.hotel.common.enums.ResultEnum;

/**
 * @author huawei
 */
public class BusinessException extends RuntimeException {
    /**
     * 业务code
     */
    private Integer code;
    /**
     * 错误内容
     */
    private String errorMessage;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 枚举构造
     *
     * @param resultEnum enum
     */
    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.errorMessage = resultEnum.getMessage();
    }

    public BusinessException(String errorMessage) {
        super(errorMessage);
        this.code = 500;
        this.errorMessage = errorMessage;
    }

    /**
     * 内存优化
     *
     * @return null
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
