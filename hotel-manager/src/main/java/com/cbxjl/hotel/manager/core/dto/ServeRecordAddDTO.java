package com.cbxjl.hotel.manager.core.dto;

import com.cbxjl.hotel.manager.core.dos.ServeRecordDO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 客房服务创建DTO
 *
 * @author : cbxjl
 * @date : 2024/3/19 14:41
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServeRecordAddDTO {
    /**
     * 房间号
     */
    @NotBlank(message = "房间号不能为空")
    private String roomNumber;

    /**
     * 服务类型（0-清洁 1-送餐 2-补充房间物品）
     */
    @NotNull(message = "服务类型不能为空")
    private Integer serveType;

    /**
     * 备注
     */
    private String describe;

    public ServeRecordDO addToDo() {
        ServeRecordDO serveRecordDO = new ServeRecordDO();
        BeanUtils.copyProperties(this, serveRecordDO);

        return serveRecordDO;
    }
}
