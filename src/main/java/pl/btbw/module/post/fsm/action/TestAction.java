package pl.btbw.module.post.fsm.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statefulj.fsm.RetryException;
import org.statefulj.fsm.model.Action;
import pl.btbw.module.post.Post;

public class TestAction implements Action<Post> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestAction.class);

	@Override
	public void execute(Post post, String eventName, Object... objects) throws RetryException {
		LOGGER.info("Test action: post:{}, eventName:{}, someObj:{}", new Object[]{post, eventName, objects});
	}
}
