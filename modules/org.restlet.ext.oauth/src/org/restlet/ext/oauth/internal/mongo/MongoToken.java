/**
 * Copyright 2005-2012 Restlet S.A.S.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL
 * 1.0 (the "Licenses"). You can select the license that you prefer but you may
 * not use this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.restlet.com/products/restlet-framework
 * 
 * Restlet is a registered trademark of Restlet S.A.S.
 */
package org.restlet.ext.oauth.internal.mongo;

import com.mongodb.DBObject;
import java.util.List;
import org.restlet.ext.oauth.OAuthResourceDefs;
import org.restlet.ext.oauth.internal.Token;

/**
 * MongoDB implementation of Token interface.
 * 
 * @author Shotaro Uchida <suchida@valleycampus.com>
 */
public class MongoToken implements Token, OAuthResourceDefs {

    public static final String TIMESTAMP = "timestamp";
    private DBObject token;
    
    public MongoToken(DBObject token) {
        this.token = token;
    }
    
    public boolean isExpired() {
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        int elapsedTime = currentTime - getTimestamp();
        if (elapsedTime >= getExpirePeriod()) {
            return true;
        }
        return false;
    }
    
    public int getTimestamp() {
        return ((Number) token.get(TIMESTAMP)).intValue();
    }
    
    public String getAccessToken() {
        return token.get(ACCESS_TOKEN).toString();
    }

    public String getTokenType() {
        return token.get(TOKEN_TYPE).toString();
    }

    public int getExpirePeriod() {
        return ((Number) token.get(EXPIRES_IN)).intValue();
    }

    public String getRefreshToken() {
        return token.get(REFRESH_TOKEN).toString();
    }

    public String[] getScope() {
        List list = (List) token.get(SCOPE);
        String[] scope = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            scope[i] = list.get(i).toString();
        }
        return scope;
    }

    public String getUsername() {
        if (token.containsField(USERNAME)) {
            return token.get(USERNAME).toString();
        } else {
            return null;
        }
    }

    public String getClientId() {
        return token.get(CLIENT_ID).toString();
    }
}
