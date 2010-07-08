package gg.proto;

import com.google.protobuf.*;

import java.net.URL;

/**
 * User: zac
 * Date: 2010-jul-06
 * Time: 15:02:40
 * <p/>
 * Copyright © 2010 Martin Zachrison
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class HttpRpcChannel implements RpcChannel {

    private URL url;
    private HttpRpcController controller;

    public HttpRpcChannel(URL url) {
        this.url = url;
        this.controller = new HttpRpcController();

    }

    public void callMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message, Message message1, RpcCallback<Message> messageRpcCallback) {
        //Todo change body of implemented methods use File | Settings | File Templates.
    }

    public HttpRpcController newRpcControler() {
        return controller;
    }

}
