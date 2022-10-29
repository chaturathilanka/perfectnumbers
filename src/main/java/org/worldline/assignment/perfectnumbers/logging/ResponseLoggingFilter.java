package org.worldline.assignment.perfectnumbers.logging;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Logged
@Provider
public class ResponseLoggingFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("Header: ").append(responseContext.getHeaders());
        sb.append(" - Entity: ").append(responseContext.getEntity());
        System.out.println("HTTP RESPONSE : " + sb);
    }
}
