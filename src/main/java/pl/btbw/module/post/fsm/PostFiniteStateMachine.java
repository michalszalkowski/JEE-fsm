package pl.btbw.module.post.fsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statefulj.fsm.FSM;
import org.statefulj.fsm.model.State;
import org.statefulj.fsm.model.impl.StateImpl;
import org.statefulj.persistence.memory.MemoryPersisterImpl;
import pl.btbw.module.post.Post;
import pl.btbw.module.post.PostMockStorage;
import pl.btbw.module.post.PostState;
import pl.btbw.module.post.fsm.action.TestAction;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class PostFiniteStateMachine {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostFiniteStateMachine.class);

	private FSM<Post> postFiniteStateMachine;

	@Inject
	private PostMockStorage postMockStorage;

	private State<Post> DRAFT = new StateImpl<>(PostState.DRAFT.toString());
	private State<Post> PUBLISHED = new StateImpl<>(PostState.PUBLISHED.toString());
	private State<Post> UNPUBLISHED = new StateImpl<>(PostState.UNPUBLISHED.toString());
	private State<Post> DELETED = new StateImpl<>(PostState.DELETED.toString());

	private List<State<Post>> states = new LinkedList<State<Post>>() {{
		add(DRAFT);
		add(PUBLISHED);
		add(UNPUBLISHED);
		add(DELETED);
	}};

	@PostConstruct
	public void init() {


		DRAFT.addTransition("EventPublish", PUBLISHED, new TestAction());

		PUBLISHED.addTransition("EventUnPublish", UNPUBLISHED);

		DRAFT.addTransition("EventDelete", DELETED);
		UNPUBLISHED.addTransition("EventDelete", DELETED);

		postFiniteStateMachine = new FSM<>(new MemoryPersisterImpl<>(states, DRAFT));
	}

	public void process(Integer postId, String event) {
		LOGGER.info("Process post with id:{}, on event:{}", new Object[]{postId, event});

		Post post = postMockStorage.getPost(postId);

		LOGGER.info("Current state of post:{}", post.getState());

		try {
			postFiniteStateMachine.onEvent(post, event);

			LOGGER.info("New state of post:{}", post.getState());
		} catch (Exception e) {
			LOGGER.error("Something went wrong, message:{}", e.getMessage());
		}
	}

}
