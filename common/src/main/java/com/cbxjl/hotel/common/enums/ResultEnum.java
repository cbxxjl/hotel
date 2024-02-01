package com.cbxjl.hotel.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 谢金伦
 */

@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(200, "成功"),
    FAILURE(101, "处理失败,稍后再试"),
    PARAM_ERROR(102, "请求参数异常"),
    VERIFY_ERROR(103, "参数校验失败"),
    NO_FOUND_ERROR(104, "查询信息不存在"),
    INNER_ERROR(500, "系统繁忙,稍后再试"),
    SAVE_DB_ERROR(106, "保存数据失败"),
    UPDATE_DB_ERROR(107, "更新数据失败"),
    FILE_UPLOADER_ERROR(108, "文件上传失败"),
    INVALID_FORMAT(109, "参数格式异常"),
    SQL_TOO_LONG(110, "字段有误,请仔细检查"),

    ;
    private Integer code;
    private String message;

}
