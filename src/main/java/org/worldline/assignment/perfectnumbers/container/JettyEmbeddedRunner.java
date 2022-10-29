package org.worldline.assignment.perfectnumbers.container;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.worldline.assignment.perfectnumbers.controller.NumberController;
import org.worldline.assignment.perfectnumbers.logging.RequestLoggingFilter;
import org.worldline.assignment.perfectnumbers.logging.ResponseLoggingFilter;
import org.worldline.assignment.perfectnumbers.util.Constants;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseFilter;
import java.util.HashSet;
import java.util.Set;

public class JettyEmbeddedRunner {

    private Bus bus;

    /**
     * starting the server, get all the resources into apache CXFNonSpringJaxrsServlet instance before start.
     * @throws Exception
     */
    public void start() throws Exception {

        Server server = new Server( 8080 );


        System.setProperty(BusFactory.BUS_FACTORY_PROPERTY_NAME, CXFBusFactory.class.getName());
        bus = BusFactory.getDefaultBus(true);
        NumberController resource = new NumberController();
        Swagger2Feature swagger2Feature = new Swagger2Feature();
        swagger2Feature.setSupportSwaggerUi(true);
        swagger2Feature.setBasePath(Constants.BASE_PATH);
        swagger2Feature.setPrettyPrint(true);
        ContainerRequestFilter requestLoggingFilter = new RequestLoggingFilter();
        ContainerResponseFilter responseLoggingFilter = new ResponseLoggingFilter();

        Set<Object> resources = new HashSet<>();
        resources.add(requestLoggingFilter);
        resources.add(resource);
        resources.add(swagger2Feature);
        resources.add(responseLoggingFilter);

        final CXFNonSpringJaxrsServlet cxfServlet = new CXFNonSpringJaxrsServlet(resources);

        cxfServlet.setBus(bus);
        ServletHolder servletHolder = new ServletHolder(cxfServlet);
        final ServletContextHandler context = new ServletContextHandler();
        context.setContextPath( "/" );
        context.addServlet( servletHolder, "/*" );

        server.setHandler( context );
        server.start();
        server.join();

    }
}
