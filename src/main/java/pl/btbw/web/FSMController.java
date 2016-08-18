package pl.btbw.web;

import pl.btbw.module.post.Post;
import pl.btbw.module.post.fsm.PostFiniteStateMachine;
import pl.btbw.module.post.PostMockStorage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
public class FSMController {

	@Inject
	private PostFiniteStateMachine postFiniteStateMachine;

	@Inject
	private PostMockStorage postMockStorage;

	@GET
	@Path("/post/{postId}/event/{event}")
	public String process(@PathParam("postId") Integer postId, @PathParam("event") String event) {
		postFiniteStateMachine.process(postId, event);
		return "process...";
	}

	@GET
	@Path("/post/{postId}")
	@Produces("application/json")
	public Post test(@PathParam("postId") Integer postId) {
		return postMockStorage.getPost(postId);
	}
}
