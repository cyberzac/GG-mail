package gg.proto;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

/**
 * User: zac
 * Date: 2010-jul-06
 * Time: 14:59:46
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
public class HttpRpcController implements RpcController {

    public void reset() {
        //Todo change body of implemented methods use File | Settings | File Templates.
    }

    public boolean failed() {
        return false;  //Todo change body of implemented methods use File | Settings | File Templates.
    }

    public String errorText() {
        return null;  //Todo change body of implemented methods use File | Settings | File Templates.
    }

    public void startCancel() {
        //Todo change body of implemented methods use File | Settings | File Templates.
    }

    public void setFailed(String s) {
        //Todo change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isCanceled() {
        return false;  //Todo change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyOnCancel(RpcCallback<Object> objectRpcCallback) {
        //Todo change body of implemented methods use File | Settings | File Templates.
    }
}
