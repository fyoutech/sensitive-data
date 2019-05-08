/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 manticorecao@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package com.gitee.fyoutech.sensitive.data.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * DTO 响应基类
 *
 * @author ipanocloud
 * @since 2019-05-08 10:35
 */
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -3889853704309757082L;

    /**
     * 结果代码
     */
    private int resultCode;

    /**
     * 结果消息
     */
    private String resultMessage;


    /**
     * Gets 结果消息.
     *
     * @return Value of 结果消息.
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Gets 结果代码.
     *
     * @return Value of 结果代码.
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * Sets new 结果消息.
     *
     * @param resultMessage New value of 结果消息.
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * Sets new 结果代码.
     *
     * @param resultCode New value of 结果代码.
     */
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
