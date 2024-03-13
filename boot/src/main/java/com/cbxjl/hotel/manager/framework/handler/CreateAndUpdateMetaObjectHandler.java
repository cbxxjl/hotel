package com.cbxjl.hotel.manager.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cbxjl.hotel.common.helper.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * MP注入处理器
 *
 * @author Lion Li
 * @date 2021/4/25
 */
@Slf4j
@Component
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
            this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
            this.strictInsertFill(metaObject, "delFlag", Integer.class, 0);
            this.strictInsertFill(metaObject, "createBy", String.class, Objects.requireNonNull(LoginHelper.getLoginUser()).getUserName());
            this.strictInsertFill(metaObject, "updateBy", String.class, Objects.requireNonNull(LoginHelper.getLoginUser()).getUserName());
            this.strictInsertFill(metaObject, "createId", Long.class, Objects.requireNonNull(LoginHelper.getLoginUser()).getId());
            this.strictInsertFill(metaObject, "updateId", Long.class, Objects.requireNonNull(LoginHelper.getLoginUser()).getId());
        } catch (Exception e) {
            log.error("自动注入失败" + e);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新");
        try {
            this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
            this.strictInsertFill(metaObject, "updateBy", String.class, Objects.requireNonNull(LoginHelper.getLoginUser()).getUserName());
            this.strictInsertFill(metaObject, "updateId", Long.class, Objects.requireNonNull(LoginHelper.getLoginUser()).getId());
        } catch (Exception e) {
            log.error("自动注入失败" + e);
        }
    }
}
