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

package org.restlet.test.ext.jaxrs.services.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.restlet.test.ext.jaxrs.services.tests.RecursiveTest;

/**
 * Test, if there are problems if a resource is a child of itself.
 * 
 * @author Stephan Koops
 * @see RecursiveTest
 */
@Path("/recursiveTest")
@Produces("text/plain")
public class RecursiveTestService {

    private final RecursiveTestService parent;
    
    public RecursiveTestService() {
       this.parent = null; 
    }

    public RecursiveTestService(RecursiveTestService parent) {
        this.parent = parent;
    }

    public int depth() {
        if (this.parent == null) {
            return 0;
        }
        return this.parent.depth() + 1;
    }

    @GET
    public String get() {
        return String.valueOf(depth());
    }

    @Path("a")
    public RecursiveTestService getSubResource2() {
        return new RecursiveTestService(this);
    }
}
