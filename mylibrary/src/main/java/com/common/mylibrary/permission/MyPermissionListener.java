package com.common.mylibrary.permission;

/**
 * Copyright (C), 2018-2019, 重庆咕点科技有限公司
 * FileName: PermissionListener
 * Author: Vincent
 * Date: 2019/8/16 8:37
 * Description: 描述
 * History:
 */
public interface MyPermissionListener {
    //有权限，直接执行
    void action();
    void doAfterGrand(String... permission);

    void doAfterDenied(String... permission);

}
