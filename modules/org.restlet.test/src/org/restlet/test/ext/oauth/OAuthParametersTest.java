/**
 * Copyright 2005-2013 Restlet S.A.S.
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
package org.restlet.test.ext.oauth;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.restlet.data.Form;
import org.restlet.data.Reference;
import org.restlet.ext.oauth.OAuthParameters;
import org.restlet.representation.Representation;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class OAuthParametersTest {
    
    private OAuthParameters parameters;
    
    @Before
    public void setUp() {
        parameters = new OAuthParameters()
                .add("foo", "val1")
                .add("bar", "val2")
                .add("buz", "val3");
    }
    
    @Test
    public void testToRepresentation() {
        Representation representation = parameters.toRepresentation();
        Form form = new Form(representation);
        assertEquals("val1", form.getFirstValue("foo"));
        assertEquals("val2", form.getFirstValue("bar"));
        assertEquals("val3", form.getFirstValue("buz"));
    }
    
    @Test
    public void testToReference() {
        Reference reference = parameters.toReference("http://localhost/test");
        Form form = reference.getQueryAsForm();
        assertEquals("val1", form.getFirstValue("foo"));
        assertEquals("val2", form.getFirstValue("bar"));
        assertEquals("val3", form.getFirstValue("buz"));
    }
}
