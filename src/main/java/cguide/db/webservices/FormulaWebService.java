package cguide.db.webservices;

import cguide.AbstractWebService;
import cguide.db.beans.FormulaBean;
import cguide.db.beans.FormulaManager;
import cguide.db.entities.Formula;
import cguide.db.exception.DAOException;
import org.codehaus.enunciate.jaxrs.ResponseCode;
import org.codehaus.enunciate.jaxrs.StatusCodes;
import org.codehaus.enunciate.jaxrs.TypeHint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: tiago
 * Date: 31-07-2013
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
@Path("/formula")
public class FormulaWebService extends AbstractWebService {

    /**
     * Method to retrieve formula's information.
     * The request must contain a Cookie Header with a valid auth-token and the information
     * will only be delivered if the user has permissions to access it
     * @param idformula The username of the user
     * @return The information about the user in a json object
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @TypeHint(Formula.class)
    @StatusCodes({@ResponseCode( code = 406, condition = "If mandatory parameters are missing"),
            @ResponseCode( code = 500, condition = "If a problem with the database occurs"),
            @ResponseCode( code = 403, condition = "If the auth-token is missing or has no privileges to access the information"),
            @ResponseCode( code = 404, condition = "If the resource was not found"),
            @ResponseCode( code = 200, condition = "If the resource is found")
    })
    public Response formulaDetails(@PathParam("id") String idformula) {
        if (idformula==null)  {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Mandatory params are missing").build();
        }
        if (!isUser()){
            return Response.status(Response.Status.FORBIDDEN).entity("Private information").build();
        }

        boolean validUser = true;
        String formulaJson = "";
        FormulaBean formulaBean = null;
        try{
            Integer.parseInt(idformula);
            formulaBean = FormulaManager.getInstance().getFormulaBeanByID(idformula);
        }catch (Exception e){
            return Response.serverError().entity("Invalid Event Id. Please send a integer number").build();
        }
        if (formulaBean != null){
            formulaJson = Formula.fromBean(formulaBean).toJson();
            return Response.ok(formulaJson, MediaType.APPLICATION_JSON_TYPE).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("Resource not found.").build();
        }
    }
    /**
     * This method receives information about a given user to insert in the system
     *
     * @param parameter           The new name of the formula
     * @param description    The new description of the formula
     * @param idtask      The Guideline Exec ID of the formula
     * @param identifier  The formula id
     */
    @PUT
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @StatusCodes({@ResponseCode( code = 406, condition = "If mandatory parameters are missing"),
            @ResponseCode( code = 500, condition = "If a problem with the database occurs"),
            @ResponseCode( code = 200, condition = "If the rating was successfully inserted"),
            @ResponseCode( code = 403, condition = "If the auth-token is missing or has" +
                    "no privileges to access the information")
    })
    public Response addFormula(@FormParam("idtask")  String idtask,
                            @FormParam("parameter")         String parameter,
                            @FormParam("identifier")         String identifier,
                            @FormParam("description")  String description
    )  {

        if (requestOwner == null){
            return Response.status(Response.Status.FORBIDDEN).entity("User not logged in").build();
        }
        if (!validParam(idtask) ||
                !validParam(parameter) ||
                !validParam(identifier) ||
                !validParam(description))  {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Mandatory params are missing").build();
        }
        FormulaBean formulaBean= FormulaManager.getInstance().createFormulaBean();
        try{

            formulaBean.setIdtask(Long.parseLong(idtask));
        }catch (Exception e){
            return Response.serverError().entity("Invalid Guideexec Id. Please send a integer number").build();
        }
        Timestamp s = new Timestamp(Calendar.getInstance().getTimeInMillis());
        formulaBean.setTime(s.toString());
        formulaBean.setParameter(parameter);
        formulaBean.setDescription(description);
        formulaBean.setIdentifier(identifier);
        try {
            FormulaManager.getInstance().insert(formulaBean);
        } catch (DAOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return Response.ok("Formula successfully registered.").build();
    }





}
