package com.cbxjl.hotel.manager.core.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cbxjl.hotel.manager.core.dos.RoomDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 客房添加DTO
 *
 * @author : cbxjl
 * @date : 2024/3/13 15:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomAddDTO {
    /**
     * 房间编号
     */
    @NotBlank(message = "房间编号不能为空")
    private String number;

    /**
     * 房间类型（0-平价房，1-豪华房，2-商务，3-无障碍房）
     */
    @NotNull(message = "房间类型不能为空")
    private Integer type;

    /**
     * 面积
     */
    @NotNull(message = "房间面积不能为空")
    private Integer area;

    /**
     * 可住人数
     */
    @NotNull(message = "可住人数不能为空")
    private Integer guestNumber;

    /**
     * 房间价格
     */
    @NotNull(message = "房间价格不能为空")
    private BigDecimal price;

    /**
     * 床型(1:单人床，2:双人床)
     */
    @NotNull(message = "床型不能为空")
    private Integer bedType;

    /**
     * 床数量
     */
    @NotNull(message = "床的数量不能为空")
    private Integer bedNumber;

    /**
     * 客房状态(0:空闲，1:已预订，2:已入住，3:未清洁)
     */
    @NotNull(message = "房间状态不能为空")
    private Integer status;
    /**
     * 设施说明
     */
    @NotBlank(message = "设施说明不能为空")
    private String installation;

    /**
     * 备注
     */
    private String describe;

    private List<String> roomPic;

    public RoomDO addToDO() {
        RoomDO roomDO = new RoomDO();
        BeanUtils.copyProperties(this, roomDO);
        roomDO.setImg(String.join(",", this.roomPic));
        return roomDO;
    }
}