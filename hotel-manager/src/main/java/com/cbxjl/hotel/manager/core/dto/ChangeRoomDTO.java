package com.cbxjl.hotel.manager.core.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 换房 接受参数DTO
 *
 * @author : cbxjl
 * @date : 2024/3/24 16:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRoomDTO {
    /**
     * 入住记录 id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long checkInId;
    /**
     * 房间号
     */
    @NotBlank(message = "房间号不能为空")
    private String roomNumber;

    /**
     * 费用
     */
    private BigDecimal pay;

    /**
     * 入住时间
     */
    private Date checkInTime;

    /**
     * 退房时间
     */
    private Date checkOutTime;
}
