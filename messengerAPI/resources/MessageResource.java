package org.sanket.messengerAPI.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sanket.messengerAPI.model.Message;
import org.sanket.messengerAPI.resources.Beans.MessagefilterBean;
import org.sanket.messengerAPI.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessagefilterBean filterbean) {
		if (filterbean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterbean.getYear());
		}
		if (filterbean.getStart() >= 0 && filterbean.getSize() >= 0) {
			return messageService.getAllMessagesPagination(filterbean.getStart(), filterbean.getSize());
		}
		return messageService.getAllMessages();
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {

		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI url = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(url).build();
		// return messageService.addMessage(message);
	}

	@PUT
	@Path("{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}

	@GET
	@Path("{messageId}")
	public Message getMessage(@PathParam("messageId") long Id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(Id);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");

		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(MessageResource.class, "getcommnetResource").path(commentResource.class)
				.resolveTemplate("messageId", message.getId())

				.build();
		return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build()
				.toString();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId()))
				.build().toString();
		return uri;
	}

	@Path("{messageId}/comments")
	public commentResource getcommnetResource() {
		return new commentResource();
	}
}
