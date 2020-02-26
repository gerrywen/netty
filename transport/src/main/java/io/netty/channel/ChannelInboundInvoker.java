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
package io.netty.channel;

public interface ChannelInboundInvoker {

    /**
     * A {@link Channel} was registered to its {@link EventLoop}.
     *
     * This will result in having the  {@link ChannelInboundHandler#channelRegistered(ChannelHandlerContext)} method
     * called of the next  {@link ChannelInboundHandler} contained in the  {@link ChannelPipeline} of the
     * {@link Channel}.
     */
    /**
     * 一个{@link Channel}注册到它的{@link EventLoop}。
     * 这将导致{@link ChannelInboundHandler#channelRegistered(ChannelHandlerContext)}方法被调用，
     * 该方法调用的下一个{@link ChannelInboundHandler}包含在{@link Channel elpipeline}的{@link Channel}中。
     * @return
     */
    ChannelInboundInvoker fireChannelRegistered();

    /**
     * A {@link Channel} was unregistered from its {@link EventLoop}.
     *
     * This will result in having the  {@link ChannelInboundHandler#channelUnregistered(ChannelHandlerContext)} method
     * called of the next  {@link ChannelInboundHandler} contained in the  {@link ChannelPipeline} of the
     * {@link Channel}.
     */
    /**
     * 一个{@link Channel} 从它的{@link EventLoop}中注销。
     * 这将导致{@link ChannelInboundHandler#channelUnregistered(ChannelHandlerContext)}方法被调用，
     * 该方法调用的下一个{@link ChannelInboundHandler}包含在{@link Channel elpipeline}的{@link Channel}中。
     * @return
     */
    ChannelInboundInvoker fireChannelUnregistered();

    /**
     * A {@link Channel} is active now, which means it is connected.
     *
     * This will result in having the  {@link ChannelInboundHandler#channelActive(ChannelHandlerContext)} method
     * called of the next  {@link ChannelInboundHandler} contained in the  {@link ChannelPipeline} of the
     * {@link Channel}.
     */
    /**
     * 一个{@link Channel}现在是活动的，这意味着它已经连接。
     * 这将导致{@link ChannelInboundHandler#channelActive(ChannelHandlerContext)}方法被调用，
     * 该方法调用的下一个{@link ChannelInboundHandler}包含在{@link Channel elpipeline}的{@link Channel}中。
     * @return
     */
    ChannelInboundInvoker fireChannelActive();

    /**
     * A {@link Channel} is inactive now, which means it is closed.
     *
     * This will result in having the  {@link ChannelInboundHandler#channelInactive(ChannelHandlerContext)} method
     * called of the next  {@link ChannelInboundHandler} contained in the  {@link ChannelPipeline} of the
     * {@link Channel}.
     */
    /**
     * 一个{@link Channel}现在是不活动的，这意味着它是关闭的。
     * 这将导致在{@link Channel}的{@link ChannelPipeline}中包含下一个{@link ChannelInboundHandler}的
     * {@link channelInactive(ChannelHandlerContext)}方法被调用。
     * @return
     */
    ChannelInboundInvoker fireChannelInactive();

    /**
     * A {@link Channel} received an {@link Throwable} in one of its inbound operations.
     *
     * This will result in having the  {@link ChannelInboundHandler#exceptionCaught(ChannelHandlerContext, Throwable)}
     * method  called of the next  {@link ChannelInboundHandler} contained in the  {@link ChannelPipeline} of the
     * {@link Channel}.
     */
    /**
     * 在其入站操作之一中， {@link Channel} 接收到{@link Throwable}这意味着它已经连接。
     * 这将导致{@link ChannelInboundHandler#exceptionCaught(ChannelHandlerContext)}方法被调用，
     * 该方法调用的下一个{@link ChannelInboundHandler}包含在{@link Channel elpipeline}的{@link Channel}中。
     * @param cause
     * @return
     */
    ChannelInboundInvoker fireExceptionCaught(Throwable cause);

    /**
     * A {@link Channel} received an user defined event.
     *
     * This will result in having the  {@link ChannelInboundHandler#userEventTriggered(ChannelHandlerContext, Object)}
     * method  called of the next  {@link ChannelInboundHandler} contained in the  {@link ChannelPipeline} of the
     * {@link Channel}.
     */
    /**
     * 一个{@link Channel} 接收到一个用户定义的事件。
     * 这将导致{@link ChannelInboundHandler#userEventTriggered(ChannelHandlerContext)}方法被调用，
     * 该方法调用的下一个{@link ChannelInboundHandler}包含在{@link Channel elpipeline}的{@link Channel}中。
     * @param event
     * @return
     */
    ChannelInboundInvoker fireUserEventTriggered(Object event);

    /**
     * A {@link Channel} received a message.
     *
     * This will result in having the {@link ChannelInboundHandler#channelRead(ChannelHandlerContext, Object)}
     * method  called of the next {@link ChannelInboundHandler} contained in the  {@link ChannelPipeline} of the
     * {@link Channel}.
     */
    /**
     * 一个{@link Channel}接收到一条消息。
     * 这将导致{@link ChannelInboundHandler#channelRead(ChannelHandlerContext)}方法被调用，
     * 该方法调用的下一个{@link ChannelInboundHandler}包含在{@link Channel elpipeline}的{@link Channel}中。
     * @param msg
     * @return
     */
    ChannelInboundInvoker fireChannelRead(Object msg);

    /**
     * Triggers an {@link ChannelInboundHandler#channelReadComplete(ChannelHandlerContext)}
     * event to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     */
    /**
     * 触发{@link ChannelInboundHandler#channelReadComplete(ChannelHandlerContext)}事件
     * 到{@link ChannelInboundHandler}中的下一个{@link ChannelPipeline}。
     * @return
     */
    ChannelInboundInvoker fireChannelReadComplete();

    /**
     * Triggers an {@link ChannelInboundHandler#channelWritabilityChanged(ChannelHandlerContext)}
     * event to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     */
    /**
     * 触发{@link ChannelInboundHandler#channelWritabilityChanged(ChannelHandlerContext)}事件
     * 到{@link ChannelInboundHandler}中的下一个{@link ChannelPipeline}。
     * @return
     */
    ChannelInboundInvoker fireChannelWritabilityChanged();
}
