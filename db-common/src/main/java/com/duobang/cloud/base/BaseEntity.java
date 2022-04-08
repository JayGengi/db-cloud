package com.duobang.cloud.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: DiegoSun
 * @time: 2021/5/7 下午6:29
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity<T extends Model<?>> extends Model<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    //@TableField(fill = FieldFill.INSERT)
    @TableField()
    protected Date createTime;

    @TableField(fill = FieldFill.INSERT)
    protected Long creatorId;

    //@TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    @TableField()
    protected Date updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    protected Long updaterId;
}

