/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ghsh.share.sina.openapi;

import android.util.SparseArray;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

/**
 * 该类封装了用户接口。
 * 详情请参考<a href="http://t.cn/8F1n1eF">用户接口</a>
 * 
 * @author SINA
 * @since 2014-03-03
 */
public class SinaUsersAPI extends AbsOpenAPI {

    private static final int READ_USER           = 0;
    private static final int READ_USER_BY_DOMAIN = 1;
    private static final int READ_USER_COUNT     = 2;

    private static final String API_BASE_URL = API_SERVER + "/users";

    private static final SparseArray<String> sAPIList = new SparseArray<String>();
    static {
        sAPIList.put(READ_USER,           API_BASE_URL + "/show.json");
        sAPIList.put(READ_USER_BY_DOMAIN, API_BASE_URL + "/domain_show.json");
        sAPIList.put(READ_USER_COUNT,     API_BASE_URL + "/counts.json");
    }

    public SinaUsersAPI(Oauth2AccessToken accessToken) {
        super(accessToken);
    }

    /**
     * 根据用户ID获取用户信息。
     * 
     * @param uid      需要查询的用户ID
     * @param listener 异步请求回调接口
     */
    public void show(long uid, RequestListener listener) {
        WeiboParameters params = new WeiboParameters();
        params.put("uid", uid);
        requestAsync(sAPIList.get(READ_USER), params, HTTPMETHOD_GET, listener);
    }
    
    
    /**
     * -----------------------------------------------------------------------
     * 请注意：以下方法匀均同步方法。如果开发者有自己的异步请求机制，请使用该函数。
     * -----------------------------------------------------------------------
     */
    
    /**
     * @see #show(long, RequestListener)
     */
    public String showSync(long uid) {
        WeiboParameters params = new WeiboParameters();
        params.put("uid", uid);
        return requestSync(sAPIList.get(READ_USER), params, HTTPMETHOD_GET);
    }

}
