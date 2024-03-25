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
import java.util.List;

/**
 * 客房服务编辑DTO
 *
 * @author : cbxjl
 * @date : 2024/3/19 16:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServeRecordEditDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 房间号
     */
    @NotBlank(message = "房间号不能为空")
    private String roomNumber;
    private List<String> roomWithFloor;
    /**
     * 员工id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 服务类型（0-清洁 1-送餐 2-补充房间物品）
     */
    @NotNull(message = "服务类型不能为空")
    private Integer serveType;

    /**
     * 备注
     */
    private String describe;

    public ServeRecordDO editToDo() {
        ServeRecordDO serveRecordDO = new ServeRecordDO();
        BeanUtils.copyProperties(this, serveRecordDO);

        return serveRecordDO;
    }
}
