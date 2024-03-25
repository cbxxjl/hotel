package com.cbxjl.hotel.manager.core.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cbxjl.hotel.manager.core.dto.CheckInDetailDTO;
import com.cbxjl.hotel.manager.core.dto.CheckInPageDTO;
import com.cbxjl.hotel.manager.core.entity.CheckInRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 入住管理 DO
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class CheckInRecordDO {
    private Long id;

    /**
     * 房客ids
     */
    private String guestIds;

    /**
     * 房间号
     */
    private String roomNumber;

    /**
     * 入住时间
     */
    private Date checkInTime;

    /**
     * 退房时间
     */
    private Date checkOutTime;

    /**
     * 实际退房时间
     */
    private Date checkOutRealTime;

    /**
     * 费用
     */
    private BigDecimal pay;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    public CheckInRecord doToPo() {
        CheckInRecord checkInRecord = new CheckInRecord();
        BeanUtils.copyProperties(this, checkInRecord);

        return checkInRecord;
    }

    public CheckInPageDTO doToPage() {
        CheckInPageDTO checkInPageDTO = new CheckInPageDTO();
        BeanUtils.copyProperties(this, checkInPageDTO);
        String[] ids = this.getGuestIds().split(",");
        checkInPageDTO.setGuestNum(ids.length);

        return checkInPageDTO;
    }

    public CheckInDetailDTO doToDetail() {
        CheckInDetailDTO checkInDetailDTO = new CheckInDetailDTO();
        BeanUtils.copyProperties(this, checkInDetailDTO);

        return checkInDetailDTO;
    }
}
