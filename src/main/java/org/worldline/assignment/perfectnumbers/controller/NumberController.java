package org.worldline.assignment.perfectnumbers.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.worldline.assignment.perfectnumbers.logging.Logged;
import org.worldline.assignment.perfectnumbers.response.NumberResponseMapper;
import org.worldline.assignment.perfectnumbers.security.BasicAuthAccess;
import org.worldline.assignment.perfectnumbers.services.NumberService;
import org.worldline.assignment.perfectnumbers.services.impl.NumberServiceImpl;
import org.worldline.assignment.perfectnumbers.util.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/v1")
@Api(value = "/api/v1")
public class NumberController {

    private final BasicAuthAccess basicAuthAccess = new BasicAuthAccess();
    private NumberService numberService;
    private static Logger LOGGER = LoggerFactory.getLogger(NumberController.class);

    Gson gson = new Gson();


    @Produces({"application/xml"})
    @GET
    @Logged
    @Path("perfectnumber/{number}")
    @ApiOperation("HTTP GET method for checking the given number is a Perfect Number")
    public Response isPerfectNumber(@PathParam("number") Integer number, @HeaderParam("Authorization") String auth) {

        NumberResponseMapper responseMapper = new NumberResponseMapper();
        Optional<String> authStr = Optional.ofNullable(auth);
        if(!authStr.isPresent()){
            responseMapper.setOperationSuccess(false);
            responseMapper.setMessage("username password not found");
            responseMapper.setResponse(Response.status(Response.Status.NOT_FOUND).build());
            String jsonResp = gson.toJson(responseMapper);
            return Response.status(Response.Status.NOT_FOUND).entity(jsonResp).build();
        }
        if (basicAuthAccess.isAuthenticated(authStr.get())) {

        boolean isPerfectNumber = false;

        try {
            numberService = new NumberServiceImpl();
            isPerfectNumber = numberService.isPerfectNumber(number);
            responseMapper.setOperationSuccess(true);
            responseMapper.setPerfectNumber(isPerfectNumber);
            responseMapper.setResponse(Response.status(Response.Status.OK).build());
            String jsonResp = gson.toJson(responseMapper);
            return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseMapper.setOperationSuccess(false);
            responseMapper.setResponse(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
            String jsonResp = gson.toJson(responseMapper);
            return Response.serverError().entity(jsonResp).build();
        }
        } else {
            responseMapper.setOperationSuccess(false);
            responseMapper.setResponse(Response.status(Response.Status.UNAUTHORIZED).build());
            String jsonResp = gson.toJson(responseMapper);
            return Response.status(Response.Status.UNAUTHORIZED).entity(jsonResp).build();
        }
    }

    @Produces({"application/xml"})
    @GET
    @Logged
    @Path("perfectnumber/{from}/{to}")
    @ApiOperation("HTTP GET method to get series of Perfect Numbers within given range")
    public Response getPerfectNumberSeries(@PathParam("from") Integer from, @PathParam("to") Integer to, @HeaderParam("Authorization") String auth) {

        NumberResponseMapper responseMapper = new NumberResponseMapper();
        Optional<String> authStr = Optional.ofNullable(auth);
        if(!authStr.isPresent()){
            responseMapper.setOperationSuccess(false);
            responseMapper.setMessage(Constants.ERR_USERNAME_PASSWORD_NOT_FOUND);
            responseMapper.setResponse(Response.status(Response.Status.NOT_FOUND).build());
            String jsonResp = gson.toJson(responseMapper);
            return Response.status(Response.Status.NOT_FOUND).entity(jsonResp).build();
        }
        if (basicAuthAccess.isAuthenticated(authStr.get())) {

        List<Integer> numberSeries;

        try {
            numberService = new NumberServiceImpl();
            numberSeries = numberService.getPerfectNumberSeries(from, to);
            if (!numberSeries.isEmpty()) {
                responseMapper.setOperationSuccess(true);
                responseMapper.setPerfectNumber(true);
                responseMapper.setPerfectNumberList(numberSeries);
                responseMapper.setResponse(Response.status(Response.Status.OK).build());
                String jsonResp = gson.toJson(responseMapper);
                return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
            } else if (numberSeries.isEmpty()) {
                responseMapper.setOperationSuccess(false);
                responseMapper.setPerfectNumber(false);
                responseMapper.setMessage(Constants.ERR_PERFECT_NUMBER_NOT_IN_RANGE);
                responseMapper.setPerfectNumberList(numberSeries);
                responseMapper.setResponse(Response.status(Response.Status.OK).build());
                String jsonResp = gson.toJson(responseMapper);
                return Response.ok(jsonResp, MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception e) {
            LOGGER.error("exception occurred in getPerfectNumberSeries() : ", e);
            responseMapper.setOperationSuccess(false);
            responseMapper.setResponse(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
            String jsonResp = gson.toJson(responseMapper);
            return Response.serverError().entity(jsonResp).build();
        }
        } else {
            responseMapper.setOperationSuccess(false);
            responseMapper.setResponse(Response.status(Response.Status.UNAUTHORIZED).build());
            String jsonResp = gson.toJson(responseMapper);
            return Response.status(Response.Status.UNAUTHORIZED).entity(jsonResp).build();
        }
        return Response.serverError().build();
    }
}
