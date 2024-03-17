package com.cbxjl.hotel.manager.core.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cbxjl.hotel.common.enums.BedType;
import com.cbxjl.hotel.common.enums.RoomStatus;
import com.cbxjl.hotel.common.enums.RoomType;
import com.cbxjl.hotel.common.utils.FileUtils;
import com.cbxjl.hotel.common.utils.StringUtils;
import com.cbxjl.hotel.manager.core.dto.RoomEditDTO;
import com.cbxjl.hotel.manager.core.dto.RoomPageDTO;
import com.cbxjl.hotel.manager.core.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private Long id;

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
     * 房间图片
     */
    private String img;


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
        if (StringUtils.isNotEmpty(this.getImg())) {
            List<String> roomPic = Arrays.asList(this.getImg().split(","));
            pageDTO.setRoomPic(roomPic);
        } else {
            pageDTO.setRoomPic(new ArrayList<>());
        }

        return pageDTO;
    }

    public Room doToPO() {
        Room room = new Room();
        BeanUtils.copyProperties(this, room);

        return room;
    }

    public RoomEditDTO doToEdit() {
        RoomEditDTO editDTO = new RoomEditDTO();
        BeanUtils.copyProperties(this, editDTO);
        if (StringUtils.isNotEmpty(this.getImg())) {
            List<String> roomPic = Arrays.asList(this.getImg().split(","));
            editDTO.setRoomPic(roomPic);
        }else {
            editDTO.setRoomPic(new ArrayList<>());
        }
        return editDTO;
    }
}
