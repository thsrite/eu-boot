package com.eu.system.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 路由显示信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMetaVo {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 内链地址（http(s)://开头）
     */
    private String link;

    public SysMetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
}
