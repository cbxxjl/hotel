package com.cbxjl.hotel.manager.core.dto;

import com.cbxjl.hotel.manager.core.dos.CheckInRecordDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 入住 创建DTO
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInAddDTO {
    /**
     * 房客
     */
    @NotEmpty(message = "房客不能为空")
    private List<GuestAddDTO> guests;

    /**
     * 房间号
     */
    @NotBlank(message = "房间号不能为空")
    private String roomNumber;

    /**
     * 入住时间
     */
    @NotNull(message = "入住时间不能为空")
    private Date checkInTime;

    /**
     * 退房时间
     */
    @NotNull(message = "退房时间不能为空")
    private Date checkOutTime;


    public CheckInRecordDO addToDo() {
        CheckInRecordDO checkInRecordDO = new CheckInRecordDO();
        BeanUtils.copyProperties(this, checkInRecordDO);

        return checkInRecordDO;
    }
}
