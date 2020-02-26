/*
 * Copyright 2016 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.util.internal;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a public API that can change at any time (even in minor/bugfix releases).
 *
 * Usage guidelines:
 *
 * <ol>
 *     <li>Is not needed for things located in *.internal.* packages</li>
 *     <li>Only public accessible classes/interfaces must be annotated</li>
 *     <li>If this annotation is not present the API is considered stable and so no backward compatibility can be
 *         broken in a non-major release!</li>
 * </ol>
 */
// 注解只会存在源代码中，将会被编译器丢弃
@Retention(RetentionPolicy.SOURCE)
// 用于描述注解的使用范围
@Target({
        ElementType.ANNOTATION_TYPE, // 注释类型声明
        ElementType.CONSTRUCTOR, // 构造函数声明
        ElementType.FIELD,  // 字段声明(包括enum常量)
        ElementType.METHOD, // 方法声明
        ElementType.PACKAGE, // 包声明
        ElementType.TYPE // 类、接口(包括注释类型)或enum声明
})
// Documented 注解表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的.
@Documented
public @interface UnstableApi {
}
