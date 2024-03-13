package com.cbxjl.hotel.manager.core.dos;

import com.cbxjl.hotel.common.enums.BedType;
import com.cbxjl.hotel.common.enums.RoomStatus;
import com.cbxjl.hotel.common.enums.RoomType;
import com.cbxjl.hotel.manager.core.dto.RoomPageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客房DO
 *
 * @author : cbxjl
 * @date : 2024/3/12 14:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class RoomDO {
    private Integer id;

    /**
     * 房间类型（0-平价房，1-豪华房，2-商务，3-无障碍房）
     */
    private Integer type;

    /**
     * 房间价格
     */
    private BigDecimal price;

    /**
     * 房间编号
     */
    private String number;

    /**
     * 面积
     */
    private Integer area;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 可住人数
     */
    private Integer guestNumber;

    /**
     * 客房状态(0:空闲，1:已预订，2:已入住，3:未清洁)
     */
    private Integer status;

    /**
     * 床型(1:单人床，2:双人床)
     */
    private Integer bedType;

    /**
     * 床数量
     */
    private Integer bedNumber;

    /**
     * 设施说明
     */
    private String installation;

    /**
     * 备注
     */
    private String describe;

    public RoomPageDTO doToPage() {
        RoomPageDTO pageDTO = new RoomPageDTO();
        BeanUtils.copyProperties(this, pageDTO);
        pageDTO.setTypeStr(RoomType.getType(this.getType()));
        pageDTO.setStatusStr(RoomStatus.getType(this.getStatus()));
        pageDTO.setBedTypeStr(BedType.getType(this.getBedType()));
        return pageDTO;
    }
}
