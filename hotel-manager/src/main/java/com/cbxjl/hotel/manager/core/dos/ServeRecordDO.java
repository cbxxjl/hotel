package com.cbxjl.hotel.manager.core.dos;


import com.cbxjl.hotel.common.enums.ServeStatus;
import com.cbxjl.hotel.common.enums.ServeType;
import com.cbxjl.hotel.manager.core.dto.ServeRecordEditDTO;
import com.cbxjl.hotel.manager.core.dto.ServeRecordPageDTO;
import com.cbxjl.hotel.manager.core.entity.ServeRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : cbxjl
 * @date : 2024/3/18 16:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class ServeRecordDO {
    private Long id;

    /**
     * 员工id
     */
    private Long userId;

    /**
     * 服务类型（0-清洁 1-送餐 2-补充房间物品）
     */
    private Integer serveType;

    /**
     * 房间号
     */
    private String roomNumber;

    /**
     * 服务开始时间
     */
    private Date serveStart;

    /**
     * 服务结束时间
     */
    private Date serveEnd;

    /**
     * 服务状态（0-未开始 1-进行中 2-已完成）
     */
    private Integer status;

    /**
     * 备注
     */
    private String describe;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    public ServeRecordPageDTO doToPage() {
        ServeRecordPageDTO serveRecordPageDTO = new ServeRecordPageDTO();
        BeanUtils.copyProperties(this, serveRecordPageDTO);
        serveRecordPageDTO.setServeTypeStr(ServeType.getServeType(this.getServeType()));
        serveRecordPageDTO.setStatusStr(ServeStatus.getServeStatus(this.getStatus()));

        return serveRecordPageDTO;
    }

    public ServeRecord doToPo() {
        ServeRecord serveRecord = new ServeRecord();
        BeanUtils.copyProperties(this, serveRecord);

        return serveRecord;
    }

    public ServeRecordEditDTO doToEdit() {
        ServeRecordEditDTO serveRecordEditDTO = new ServeRecordEditDTO();
        BeanUtils.copyProperties(this, serveRecordEditDTO);
        List<String> roomWithFloor = new ArrayList<>();

        //["1", "1-1"]前端需要这种格式
        roomWithFloor.add(this.getRoomNumber().split("-")[0]);
        roomWithFloor.add(this.getRoomNumber());

        serveRecordEditDTO.setRoomWithFloor(roomWithFloor);
        return serveRecordEditDTO;
    }
}
