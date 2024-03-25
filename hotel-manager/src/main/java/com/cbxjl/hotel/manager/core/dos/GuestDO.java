package com.cbxjl.hotel.manager.core.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cbxjl.hotel.manager.core.dto.GuestPageDTO;
import com.cbxjl.hotel.manager.core.entity.Guest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 客户DO
 *
 * @author : cbxjl
 * @date : 2024/3/20 16:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class GuestDO {
    private Long id;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String cardNum;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 性别（0-男，1-女）
     */
    private Integer sex;

    /**
     * 级别
     */
    private Integer level;

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
     * 逻辑删除
     */
    private Integer delFlag;

    public Guest doToPo() {
        Guest guest = new Guest();
        BeanUtils.copyProperties(this, guest);

        return guest;
    }

    public GuestPageDTO doToPage() {
        GuestPageDTO guestPageDTO = new GuestPageDTO();
        BeanUtils.copyProperties(this, guestPageDTO);
        guestPageDTO.setSexStr(this.getSex() == 0 ? "男" : "女");
        guestPageDTO.setLevelStr("VIP" + this.getLevel());
        //身份证不完全显示
        // 提取前4位
        String frontDigits = this.getCardNum().substring(0, 4);
        // 提取后4位
        String backDigits = this.getCardNum().substring(this.getCardNum().length() - 4);

        guestPageDTO.setCardNum(frontDigits + "****" + backDigits);
        return guestPageDTO;
    }
}
